package com.example.demo.Controller;

import com.example.demo.Model.Parking;
import com.example.demo.Model.ParkingRequest;
import com.example.demo.dto.RequestResponseDTO;
import com.example.demo.repository.ParkingRepository;
import com.example.demo.repository.ParkingRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "*")
public class ParkingRequestController {

    @Autowired
    private ParkingRequestRepository reqRepo;

    @Autowired
    private ParkingRepository parkingRepo;

    // ✅ CREATE REQUEST
    @PostMapping("/create")
    public ParkingRequest create(
            @RequestBody Map<String, String> req
    ) {

        Long parkingId =
                Long.parseLong(req.get("parkingId"));

        // 🔥 FIND PARKING
        Parking p =
                parkingRepo.findById(parkingId)
                        .orElseThrow();

        // 🔥 CREATE REQUEST
        ParkingRequest r =
                new ParkingRequest();

        r.setParkingId(parkingId);

        r.setOwnerId(p.getUserId());

        r.setRequesterId(req.get("requesterId"));

        r.setStatus("PENDING");

        r.setCreatedAt(LocalDateTime.now());
        // BOOKING TIME
        r.setFromDate(req.get("fromDate"));

        r.setFromTime(req.get("fromTime"));

        r.setToDate(req.get("toDate"));

        r.setToTime(req.get("toTime"));

        return reqRepo.save(r);
    }

    // ✅ OWNER REQUESTS
    @GetMapping("/owner/{ownerId}")
    public List<RequestResponseDTO> ownerRequests(
            @PathVariable String ownerId
    ) {

        List<ParkingRequest> requests =
                reqRepo.findByOwnerId(ownerId);

        List<RequestResponseDTO> result =
                new ArrayList<>();

        for (ParkingRequest r : requests) {

            Parking p =
                    parkingRepo.findById(r.getParkingId())
                            .orElse(null);

            RequestResponseDTO dto =
                    new RequestResponseDTO();

            // REQUEST DETAILS
            dto.setId(r.getId());

            dto.setParkingId(r.getParkingId());

            dto.setRequesterId(r.getRequesterId());

            dto.setStatus(r.getStatus());
            dto.setCancellationReason(
                    r.getCancellationReason()
            );

            dto.setFromDate(r.getFromDate());

            dto.setFromTime(r.getFromTime());

            dto.setToDate(r.getToDate());

            dto.setToTime(r.getToTime());

            // PARKING DETAILS
            if (p != null) {

                dto.setLocation(p.getLocation());

                dto.setAddress(p.getAddress());

                dto.setVehicleType(p.getVehicleType());

                dto.setMapLink(p.getMapLink());

                dto.setMobile(p.getMobile());

                dto.setPrice(p.getPrice());

                dto.setLatitude(p.getLatitude());

                dto.setLongitude(p.getLongitude());
            }

            result.add(dto);
        }

        return result;
    }

    // ✅ DRIVER REQUEST PAGE
    @GetMapping("/user/{userId}")
    public List<RequestResponseDTO> userRequests(
            @PathVariable String userId
    ) {

        List<ParkingRequest> requests =
                reqRepo.findByRequesterId(userId);

        List<RequestResponseDTO> result =
                new ArrayList<>();

        for (ParkingRequest r : requests) {

            Parking p =
                    parkingRepo.findById(r.getParkingId())
                            .orElse(null);

            RequestResponseDTO dto =
                    new RequestResponseDTO();

            dto.setId(r.getId());

            dto.setParkingId(r.getParkingId());

            dto.setRequesterId(r.getRequesterId());

            dto.setStatus(r.getStatus());
            dto.setCancellationReason(
                    r.getCancellationReason()
            );

            dto.setFromDate(r.getFromDate());

            dto.setFromTime(r.getFromTime());

            dto.setToDate(r.getToDate());

            dto.setToTime(r.getToTime());

            if (p != null) {

                dto.setLocation(p.getLocation());

                dto.setAddress(p.getAddress());

                dto.setVehicleType(p.getVehicleType());

                dto.setMapLink(p.getMapLink());

                dto.setMobile(p.getMobile());

                dto.setPrice(p.getPrice());

                dto.setLatitude(p.getLatitude());

                dto.setLongitude(p.getLongitude());
            }

            result.add(dto);
        }

        return result;
    }

    // ✅ ACCEPT REQUEST
    @PutMapping("/accept/{id}")
    public ParkingRequest accept(
            @PathVariable Long id
    ) {

        ParkingRequest r =
                reqRepo.findById(id)
                        .orElseThrow();

        // 🔥 ONLY UPDATE STATUS
        r.setStatus("ACCEPTED");

        return reqRepo.save(r);
    }

    // ✅ REJECT REQUEST
    @PutMapping("/reject/{id}")
    public ParkingRequest reject(
            @PathVariable Long id
    ) {

        ParkingRequest r =
                reqRepo.findById(id)
                        .orElseThrow();

        r.setStatus("REJECTED");

        return reqRepo.save(r);
    }
    @PutMapping("/cancel/{id}")
    public ParkingRequest cancelRequest(
            @PathVariable Long id,
            @RequestBody Map<String,String> req
    ) {

        ParkingRequest r =
                reqRepo.findById(id)
                        .orElseThrow();

        r.setStatus("CANCELLED_BY_DRIVER");

        r.setCancellationReason(
                req.get("reason")
        );

        return reqRepo.save(r);
    }

    @PutMapping("/owner-cancel/{id}")
    public ParkingRequest ownerCancel(
            @PathVariable Long id
    ) {

        ParkingRequest request =
                reqRepo.findById(id)
                        .orElseThrow();

        request.setStatus("CANCELLED_BY_OWNER");

        return reqRepo.save(request);
    }

    @GetMapping("/pending-count/{ownerId}")
    public Long pendingCount(
            @PathVariable String ownerId
    ){

        return reqRepo.countByOwnerIdAndStatus(
                ownerId,
                "PENDING"
        );
    }


}