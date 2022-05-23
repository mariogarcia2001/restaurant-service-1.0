package com.restaurant.service10.restaurantservice.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_RESERVATIONS")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DATE") 
    private LocalDate date;

    @Column(name = "TYPE_OF_PAYMENT") 
    private String typeOfPayment;

    @Column(name = "TOTAL_RESERVATION") 
    private Float totalReservation;

    @Column(name = "NUMBRE_OF_TABLE") 
    private int numberOfTable;

    @OneToMany(mappedBy="order") //nombre del atributo en la clase B       
    private List<Order> orders;
}
