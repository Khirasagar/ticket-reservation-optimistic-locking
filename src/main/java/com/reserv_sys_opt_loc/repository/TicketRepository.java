package com.reserv_sys_opt_loc.repository;

import com.reserv_sys_opt_loc.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}