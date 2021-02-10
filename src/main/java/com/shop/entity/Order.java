package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)

    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem){  //①
        orderItems.add(orderItem);
        orderItem.setOrder(this);  //②
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setMember(member);  //③
        for(OrderItem orderItem : orderItemList){  //④
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);  //⑤
        order.setOrderDate(LocalDateTime.now());  //⑥
        return order;
    }

    public int getTotalPrice(){  //⑦
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCEL;

        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }



}

