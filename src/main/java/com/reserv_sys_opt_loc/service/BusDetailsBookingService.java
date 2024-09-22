package com.reserv_sys_opt_loc.service;

import com.reserv_sys_opt_loc.Exception.SeatNotAvailable;
import com.reserv_sys_opt_loc.entity.BusDetails;
import com.reserv_sys_opt_loc.entity.Ticket;
import com.reserv_sys_opt_loc.repository.BusDetailsRepository;
import com.reserv_sys_opt_loc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusDetailsBookingService {

    @Autowired
    private BusDetailsRepository busDetailsRepository;

    @Autowired
    private TicketRepository ticketRepository;

    private void saveTicket(String firstName,String lastName ,String gender,BusDetails busDetails ) throws SeatNotAvailable {
        if (busDetails.getCapacity() <= busDetails.getTickets().size()){
            throw new SeatNotAvailable();
        }
        Ticket ticket = new Ticket();
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setGender(gender);

        busDetails.addTicket(ticket);

        ticketRepository.save(ticket);
    }



    public void bookTicket() throws SeatNotAvailable, InterruptedException {
        Optional<BusDetails> busDetailOptional = busDetailsRepository.findById(1L);
        if (busDetailOptional.isPresent()){
            saveTicket("John","Coleman","Male",busDetailOptional.get());
        }
        Thread.sleep(1000);
    }

    public void bookTicket1() throws SeatNotAvailable, InterruptedException {
        Optional<BusDetails> busDetailOptional = busDetailsRepository.findById(1L);
        if (busDetailOptional.isPresent()){
            saveTicket("Manny","Schrodinzer","FeMale",busDetailOptional.get());
        }
        Thread.sleep(1000);
    }
}
