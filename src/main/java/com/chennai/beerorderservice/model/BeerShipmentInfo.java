package com.chennai.beerorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
@Builder
@AllArgsConstructor
public class BeerShipmentInfo {

    private long id;
    private long shipmentId;
    private List<BeerDto> beerList;
    private double totalPrice;
    private String address;
    private String city;
    private String pinCode;
    private String state;
    private String mobileNumber;
    private String status;
    private String trackingId;
}
