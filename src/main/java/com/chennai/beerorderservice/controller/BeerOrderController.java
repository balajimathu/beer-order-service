package com.chennai.beerorderservice.controller;

import com.chennai.beerorderservice.model.BeerDto;
import com.chennai.beerorderservice.model.BeerShipmentInfo;
import com.chennai.beerorderservice.service.BeerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/beer/order")
public class BeerOrderController {

    @Autowired
    BeerOrderService beerOrderService;

    /**
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public Mono<BeerDto> get(@NotNull @PathVariable String name) {

        return beerOrderService.get(name);
    }

    /**
     * @param param
     * @param value
     * @return
     */
    @GetMapping("/search")
    public Flux<BeerDto> getAll(@NotNull @RequestParam("param") String param, @NotNull @RequestParam("value") String value) {

        return beerOrderService.getAll(param, value);
    }

    /**
     * @param beerDto
     * @return
     */
    @PostMapping("")
    public Mono<BeerShipmentInfo> createOrder(@Valid @RequestBody BeerDto beerDto) {

        return beerOrderService.createOrder(beerDto);
    }
}
