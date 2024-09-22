package com.reserv_sys_opt_loc.repository;

import com.reserv_sys_opt_loc.entity.BusDetails;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface BusDetailsRepository extends JpaRepository<BusDetails, Long> {

//    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
//    Optional<BusDetails> findWithLockingById(Long id);
}