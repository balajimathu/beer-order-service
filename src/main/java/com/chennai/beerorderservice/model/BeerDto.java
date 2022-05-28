package com.chennai.beerorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 */
@Data
@Builder
@AllArgsConstructor
@Validated
public class BeerDto {

    @NotNull
    private long id;
    @NotNull
    private long beerId;
    @NotBlank
    private String beerName;
    @NotBlank
    private String brewer;
    @NotNull
    private double size;
    @NotNull
    private double price;
}
