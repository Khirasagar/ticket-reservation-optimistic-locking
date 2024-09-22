package com.reserv_sys_opt_loc.controller;

import com.reserv_sys_opt_loc.entity.BusDetails;
import com.reserv_sys_opt_loc.repository.BusDetailsRepository;
import com.reserv_sys_opt_loc.service.BusDetailsBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.function.FailableRunnable;
@RestController
@RequestMapping("/api")
public class LockingDemoController {

    @Autowired
    private BusDetailsBookingService busDetailsBookingService;

    @Autowired
    private BusDetailsRepository busDetailsRepository;

    @GetMapping("/bookTicket")
    public void bookTicket(){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(run(busDetailsBookingService::bookTicket));
        executor.execute(run(busDetailsBookingService::bookTicket1));
        executor.shutdown();

    }

    @GetMapping("/addBus")
    public void addBus(@RequestParam String number,@RequestParam int capacity){

        BusDetails busDetails = new BusDetails();
        busDetails.setCapacity(capacity);
        busDetails.setNumber(number);
        busDetails.setDepartureTime(LocalDateTime.now());
        busDetailsRepository.save(busDetails);

    }

    private Runnable run(FailableRunnable<Exception> runnable) {
            return ()->{
                try {
                    runnable.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
    }

}
