package com.chennai.beerorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *
 */
@Data
@Builder
@AllArgsConstructor
public class BeerDto {

    private long id;
    private long beerId;
    private String beerName;
    private String brewer;
    private double size;
    private double price;
}
