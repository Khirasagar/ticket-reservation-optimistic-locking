package com.reserv_sys_opt_loc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bus_details")
public class BusDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "seat_capacity", nullable = false)
    private Integer Capacity;


    @OneToMany(mappedBy = "busDetails")
    private Set<Ticket> tickets;

    @Column(name = "number")
    private String number;

    public void addTicket(Ticket ticket){
        ticket.setBusDetails(this);
        getTickets().add(ticket);
    }


}