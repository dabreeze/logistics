package com.logisticsapp.logistics.data.repository;

import com.logisticsapp.logistics.data.models.Sender;
import com.logisticsapp.logistics.web.exceptions.SenderNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {
    Optional<Sender>findBySenderEmail(String email) throws SenderNotFoundException;
    Optional<Sender>findByFirstName(String firstName) throws SenderNotFoundException;
}
