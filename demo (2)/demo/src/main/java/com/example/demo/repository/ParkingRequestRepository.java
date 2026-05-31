package com.example.demo.repository;

import com.example.demo.Model.ParkingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRequestRepository
        extends JpaRepository<ParkingRequest, Long> {

    List<ParkingRequest> findByOwnerId(String ownerId);

    List<ParkingRequest> findByRequesterId(String requesterId);

    List<ParkingRequest> findByParkingIdAndStatus(
            Long parkingId,
            String status
    );
    Long countByOwnerIdAndStatus(
            String ownerId,
            String status
    );
    List<ParkingRequest> findByStatus(
            String status
    );
}