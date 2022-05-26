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
public class BeerOrder {

    private long id;
    private long orderId;
    private List<BeerDto> beerList;
    private double totalPrice;
    private BeerShipmentInfo beerShipmentInfo;
}
