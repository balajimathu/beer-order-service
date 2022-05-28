package com.chennai.beerorderservice.controller;

import com.chennai.beerorderservice.model.BeerDto;
import com.chennai.beerorderservice.model.BeerShipmentInfo;
import com.chennai.beerorderservice.service.BeerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/beerOrder")
@Validated
public class BeerOrderController {

    @Autowired
    BeerOrderService beerOrderService;

    /**
     * @param name BeerName
     * @return Mono object of BeerDto
     */
    @GetMapping("/beer/{name}")
    public Mono<BeerDto> getBeer(@PathVariable @NotBlank String name) {
        log.info("Getting Beer Info for" + name);
        return beerOrderService.get(name);
    }

    /**
     * @param param beerName or price or Brewer
     * @param value value for the param
     * @return Flux of BeerDto ie.,List
     */
    @GetMapping("/beers")
    public Flux<BeerDto> getBeers(@RequestParam("param") @NotBlank String param, @RequestParam("value") @NotBlank String value) {

        return beerOrderService.getAll(param, value);
    }

    /**
     * @param beerDto requestBody JSON payload
     * @return Shipment details with id
     */
    @PostMapping()
    public Mono<BeerShipmentInfo> createOrder(@Valid @RequestBody BeerDto beerDto) {
        log.info(beerDto.toString());
        return beerOrderService.createOrder(beerDto);
    }
}
