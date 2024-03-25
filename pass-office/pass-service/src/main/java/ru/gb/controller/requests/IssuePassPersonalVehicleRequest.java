package ru.gb.controller.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssuePassPersonalVehicleRequest {

    private long personId;
    private long vehicleId;
    private String passGroup;
    private LocalDateTime validUntil;
    private String cardUID;     // cardUID is generated proximity-card ID
    private Integer cardNo;     // cardNo is serial number printed on card
    private String ciphers;
    private String allowedFacilities;
    private LocalDateTime issuedAt;
    private LocalDateTime returnedAt;

}
