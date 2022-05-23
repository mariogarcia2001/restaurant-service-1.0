package com.restaurant.service10.restaurantservice.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_ORDERS")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "WAITER")
    private String waiter;

    @Column(name = "STATUS_OF_ORDER")
    private String statusOfOrder;

    @ManyToOne
    @JoinColumn(name="ORDER_ID", nullable=false)
    private Order order;    
    
    //

    @OneToMany(mappedBy="food")      
    private List<Food> foods;
}
