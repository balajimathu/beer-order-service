package com.chennai.beerorderservice.controller;

import com.chennai.beerorderservice.model.BeerDto;
import com.chennai.beerorderservice.model.BeerShipmentInfo;
import com.chennai.beerorderservice.service.BeerOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BeerOrderController.class)
@Import(BeerOrderService.class)
class BeerOrderControllerTest {

    @MockBean
    BeerOrderService beerOrderService;
    @Autowired
    private WebTestClient webTestClient;

    /**
     * Failure Scenario, will respond back BadRequest in Response Status
     *
     * @throws Exception this may throw Exception
     */
    @Test
    void ok_getBeer() throws Exception {
        Mockito.when(beerOrderService.get("Budweiser"))
                .thenReturn(Mono.just(BeerDto.builder()
                        .id(5005L).beerId(5005L)
                        .price(50.00).beerName("Budweiser")
                        .brewer("Budwesier Breweries").build()));

        webTestClient.get().uri("/beerOrder/beer/Budweiser").exchange().expectStatus().isOk();
    }

    /**
     * Failure Scenario, will respond back BadRequest in Response Status
     *
     * @throws Exception this may throw Exception
     */
    @Test
    void ok_getBeers() throws Exception {

        List<BeerDto> list = new ArrayList<>();
        list.add(BeerDto.builder().beerId(5005).beerName("Budweiser").brewer("Budweiser Breweries").price(55.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(1001).beerName("KingFisher").brewer("JB Breweries").price(53.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(2002).beerName("Gold5000").brewer("Golden Breweries").price(52.00).size(350.00).build());


        Mockito.when(beerOrderService.getAll("price", "52.00"))
                .thenReturn(Flux.fromIterable(list));

        webTestClient.get().uri("/beerOrder/beers?param=price&value=52.00").exchange().expectStatus().isOk();
    }


    /**
     * Failure Scenario, will respond back BadRequest in Response Status
     *
     * @throws Exception this may throw Exception
     */
    @Test
    void ok_createOrder() throws Exception {
        BeerDto beerDto = BeerDto.builder().beerId(5005).beerName("Budweiser").brewer("Budweiser Breweries").price(55.00).size(350.00).build();

        List<BeerDto> list = new ArrayList<>();
        list.add(BeerDto.builder().beerId(5005).beerName("Budweiser").brewer("Budweiser Breweries").price(55.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(1001).beerName("KingFisher").brewer("JB Breweries").price(53.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(2002).beerName("Gold5000").brewer("Golden Breweries").price(52.00).size(350.00).build());

        Mockito.when(beerOrderService.createOrder(beerDto))
                .thenReturn(Mono.just(BeerShipmentInfo.builder()
                        .id(5005L).beerList(list)
                        .address("150 MG Road").city("Bangalore")
                        .state("Karnataka").pinCode("540003").mobileNumber("9876543210").shipmentId(43243L).build()));

        webTestClient.post().uri("/beerOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(beerDto)).exchange()
                .expectStatus().isOk();
    }


    /**
     * Failure Scenario, will respond back BadRequest in Response Status
     *
     * @throws Exception this may throw Exception
     */
    @Test
    void shouldThrowBadRequest_getBeer() throws Exception {
        Mockito.when(beerOrderService.get("Budweiser"))
                .thenReturn(Mono.just(BeerDto.builder()
                        .id(5005L).beerId(5005L)
                        .price(50.00).beerName("Budweiser")
                        .brewer("Budwesier Breweries").build()));

        webTestClient.get().uri("/beerOrder/beer/ ").exchange().expectStatus().isBadRequest();
    }

    /**
     * Failure Scenario, will respond back BadRequest in Response Status
     *
     * @throws Exception this may throw Exception
     */
    @Test
    void shouldThrowBadRequest_getBeers() throws Exception {

        List<BeerDto> list = new ArrayList<>();
        list.add(BeerDto.builder().beerId(5005).beerName("Budweiser").brewer("Budweiser Breweries").price(55.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(1001).beerName("KingFisher").brewer("JB Breweries").price(53.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(2002).beerName("Gold5000").brewer("Golden Breweries").price(52.00).size(350.00).build());


        Mockito.when(beerOrderService.getAll("price", "55.00"))
                .thenReturn(Flux.fromIterable(list));

        webTestClient.get().uri("/beerOrder/beers?param=price&value= ").exchange().expectStatus().isBadRequest();
    }


    /**
     * Failure Scenario, will respond back BadRequest in Response Status
     *
     * @throws Exception this may throw Exception
     */
    @Test
    void shouldThrowBadRequest_createOrder() throws Exception {
        BeerDto beerDto = BeerDto.builder().beerId(5005).beerName(" ").brewer("Budweiser Breweries").price(55.00).size(350.00).build();

        List<BeerDto> list = new ArrayList<>();
        list.add(BeerDto.builder().beerId(5005).beerName("Budweiser").brewer("Budweiser Breweries").price(55.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(1001).beerName("KingFisher").brewer("JB Breweries").price(53.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(2002).beerName("Gold5000").brewer("Golden Breweries").price(52.00).size(350.00).build());

        Mockito.when(beerOrderService.createOrder(beerDto))
                .thenReturn(Mono.just(BeerShipmentInfo.builder()
                        .id(5005L).beerList(list)
                        .address("150 MG Road").city("Bangalore")
                        .state("Karnataka").pinCode("540003").mobileNumber("9876543210").shipmentId(43243L).build()));

        webTestClient.post().uri("/beerOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(beerDto)).exchange()
                .expectStatus().isBadRequest();
    }
}