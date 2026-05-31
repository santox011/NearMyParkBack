package com.example.demo.Controller;

import com.example.demo.Model.Parking;
import com.example.demo.Model.ParkingRequest;

import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.SearchResponseDTO;

import com.example.demo.repository.ParkingRepository;
import com.example.demo.repository.ParkingRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private ParkingRepository parkingRepo;

    @Autowired
    private ParkingRequestRepository reqRepo;

    // 🔍 SEARCH API
    @PostMapping("/search")
    public List<SearchResponseDTO> search(
            @RequestBody SearchRequestDTO req
    ) {

        List<SearchResponseDTO> result =
                new ArrayList<>();

        // 🔥 SAFE SEARCH DATE
        LocalDateTime searchFrom = null;

        LocalDateTime searchTo = null;

        try {

            searchFrom =
                    LocalDateTime.parse(
                            req.getFromDate()
                                    + "T"
                                    + req.getFromTime()
                    );

            searchTo =
                    LocalDateTime.parse(
                            req.getToDate()
                                    + "T"
                                    + req.getToTime()
                    );

        } catch (Exception e) {

            System.out.println("SEARCH DATE ERROR");

            e.printStackTrace();

            return result;
        }

        // 🔥 GET ALL PARKINGS
        List<Parking> all =
                parkingRepo.findAll();

        for (Parking p : all) {

            try {

                // 🔥 NULL CHECK
                if (p == null) {
                    continue;
                }

                // 🔥 VEHICLE TYPE CHECK
                if (
                        p.getVehicleType() == null
                                ||
                                req.getVehicleType() == null
                ) {
                    continue;
                }

                if (
                        !p.getVehicleType()
                                .equalsIgnoreCase(
                                        req.getVehicleType()
                                )
                ) {
                    continue;
                }

                // 🔥 LOCATION / ADDRESS FILTER
                boolean match = false;

                // LOCATION MATCH
                if (
                        req.getLocation() != null
                                &&
                                !req.getLocation().trim().isEmpty()
                                &&
                                p.getLocation() != null
                ) {

                    if (
                            p.getLocation()
                                    .toLowerCase()
                                    .contains(
                                            req.getLocation()
                                                    .toLowerCase()
                                    )
                    ) {

                        match = true;
                    }
                }

                // ADDRESS MATCH
                if (
                        req.getAddress() != null
                                &&
                                !req.getAddress().trim().isEmpty()
                                &&
                                p.getAddress() != null
                ) {

                    if (
                            p.getAddress()
                                    .toLowerCase()
                                    .contains(
                                            req.getAddress()
                                                    .toLowerCase()
                                    )
                    ) {

                        match = true;
                    }
                }

                // 🔥 NO MATCH
                if (!match) {
                    continue;
                }

                // 🔥 SLOT CHECK
                boolean slotAvailable = true;

                try {

                    List<ParkingRequest> bookings =
                            reqRepo.findByParkingIdAndStatus(
                                    p.getId(),
                                    "ACCEPTED"
                            );

                    for (ParkingRequest booking : bookings) {

                        // NULL CHECK
                        if (
                                booking.getFromDate() == null
                                        ||
                                        booking.getFromTime() == null
                                        ||
                                        booking.getToDate() == null
                                        ||
                                        booking.getToTime() == null
                        ) {

                            continue;
                        }

                        LocalDateTime bookedFrom =
                                LocalDateTime.parse(
                                        booking.getFromDate()
                                                + "T"
                                                + booking.getFromTime()
                                );

                        LocalDateTime bookedTo =
                                LocalDateTime.parse(
                                        booking.getToDate()
                                                + "T"
                                                + booking.getToTime()
                                );

                        // 🔥 IGNORE OLD BOOKINGS
                        if (
                                LocalDateTime.now()
                                        .isAfter(bookedTo)
                        ) {

                            continue;
                        }

                        // 🔥 OVERLAP CHECK
                        boolean overlap =

                                searchFrom.isBefore(bookedTo)
                                        &&
                                        searchTo.isAfter(bookedFrom);

                        if (overlap) {

                            slotAvailable = false;

                            break;
                        }
                    }

                } catch (Exception e) {

                    System.out.println(
                            "BOOKING CHECK ERROR"
                    );

                    e.printStackTrace();
                }

                // 🔥 SAFE DISTANCE
                double distance = 0;

                try {

                    if (

                            req.getLatitude() != null
                                    &&
                                    req.getLongitude() != null
                                    &&
                                    p.getLatitude() != null
                                    &&
                                    p.getLongitude() != null

                    ) {

                        distance =
                                calculateDistance(

                                        req.getLatitude(),

                                        req.getLongitude(),

                                        p.getLatitude(),

                                        p.getLongitude()
                                );
                    }

                } catch (Exception e) {

                    System.out.println(
                            "DISTANCE ERROR"
                    );

                    e.printStackTrace();
                }

                // 🔥 DTO
                SearchResponseDTO dto =
                        new SearchResponseDTO();

                dto.setParking(p);

                dto.setDistance(distance);

                dto.setSlotAvailable(slotAvailable);

                result.add(dto);

            } catch (Exception e) {

                System.out.println(
                        "PARKING LOOP ERROR"
                );

                e.printStackTrace();
            }
        }

        return result;
    }

    // 📏 DISTANCE CALCULATION
    private double calculateDistance(

            double lat1,
            double lon1,

            double lat2,
            double lon2

    ) {

        double R = 6371;

        double dLat =
                Math.toRadians(
                        lat2 - lat1
                );

        double dLon =
                Math.toRadians(
                        lon2 - lon1
                );

        double a =

                Math.sin(dLat / 2)
                        *
                        Math.sin(dLat / 2)

                        +

                        Math.cos(
                                Math.toRadians(lat1)
                        )

                                *

                                Math.cos(
                                        Math.toRadians(lat2)
                                )

                                *

                                Math.sin(dLon / 2)

                                *

                                Math.sin(dLon / 2);

        double c =

                2 * Math.atan2(

                        Math.sqrt(a),

                        Math.sqrt(1 - a)
                );

        return R * c;
    }
}