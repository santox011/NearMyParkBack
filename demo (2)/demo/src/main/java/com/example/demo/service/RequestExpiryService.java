package com.example.demo.service;

import com.example.demo.Model.ParkingRequest;
import com.example.demo.repository.ParkingRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestExpiryService {

    @Autowired
    private ParkingRequestRepository reqRepo;

    @Scheduled(fixedRate = 60000)
    public void expireRequests(){

        List<ParkingRequest> requests =
                reqRepo.findByStatus(
                        "PENDING"
                );

        for(ParkingRequest r : requests){

            if(
                    r.getCreatedAt() != null
                            &&
                            r.getCreatedAt()
                                    .plusMinutes(1)
                                    //.plusHours(24)
                                    .isBefore(
                                            LocalDateTime.now()
                                    )
            ){

                r.setStatus(
                        "EXPIRED"
                );

                reqRepo.save(r);
            }
        }
    }
}