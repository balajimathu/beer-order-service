package com.chennai.beerorderservice.service;

import com.chennai.beerorderservice.exceptions.DataNotFoundException;
import com.chennai.beerorderservice.model.BeerDto;
import com.chennai.beerorderservice.model.BeerOrder;
import com.chennai.beerorderservice.model.BeerShipmentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Slf4j
public class BeerOrderService {

    private static List<BeerDto> list;
    private static Map<Long, Double> prices;


    public BeerOrderService() {
        list = new ArrayList<>();
        list.add(BeerDto.builder().beerId(5005).beerName("Budweiser").brewer("Budweiser Breweries").price(55.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(1001).beerName("KingFisher").brewer("JB Breweries").price(53.00).size(350.00).build());
        list.add(BeerDto.builder().beerId(2002).beerName("Gold5000").brewer("Golden Breweries").price(52.00).size(350.00).build());

        prices = new HashMap<Long, Double>();
        prices.put(5005L, 55.00);
        prices.put(1001L, 53.00);
        prices.put(2002L, 52.00);

        log.info("Initialised");

    }

    /**
     * @param name
     * @return
     */
    public Mono<BeerDto> get(String name) {
        //TODO : Call Inventory Search and collect

        log.info("Requested Beer " + name);
        log.info("List " + list.toString() + list.size());
        list.forEach(System.out::println);
        return
                Mono.just(list.stream().filter(beerDto -> beerDto.getBeerName().equalsIgnoreCase(name)).findFirst().get());
    }

    /**
     * @param param
     * @param value
     * @return
     */
    public Flux<BeerDto> getAll(String param, String value) {
        //TODO:  Search Inventory and return all

        if (param.toLowerCase().contains("price"))
            return Flux.fromIterable(list.stream().filter(beerDto -> beerDto.getPrice() == Double.parseDouble(value)).collect(Collectors.toList()));
        if (param.toLowerCase().contains("name"))
            return Flux.fromIterable(list.stream().filter(beerDto -> beerDto.getBeerName().equalsIgnoreCase(value)).collect(Collectors.toList()));
        if (param.toLowerCase().contains("brew"))
            return Flux.fromIterable(list.stream().filter(beerDto -> beerDto.getBrewer().equalsIgnoreCase(value)).collect(Collectors.toList()));

        return Flux.fromIterable(list.stream().filter(beerDto -> beerDto.getBrewer().equalsIgnoreCase(value)).collect(Collectors.toList()));

    }

    /**
     * @param beerDto
     * @return
     */
    public Mono<BeerShipmentInfo> createOrder(BeerDto beerDto) {

        //Search Inventory and confirm
        if (list.stream().filter(beerDtoIterable -> beerDtoIterable.getBeerId() == beerDto.getBeerId()).count() > 0) {
            if (prices.get(beerDto.getBeerId()) > 0) {
                //TODO: beerOrderRepository.createOrder();
                List<BeerDto> beerList = new ArrayList<>();
                beerList.add(beerDto);
                BeerOrder.builder()
                        .orderId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                        .beerList(beerList)
                        .totalPrice(beerDto.getPrice())
                        .build();

                return createShipment(beerList);

            }
        } else {
            throw new DataNotFoundException("Reqeusted Beer Not Available now, please try after few hours.");
        }

        //Get latest price from PriceService
        //Create Order and Persist in DB
        //Create Shipment and collect shipment details
        //Respond back with orderdetails and shipment id
        return Mono.just(null);

    }

    /**
     * @param beerDtoList
     * @return
     */
    private Mono<BeerShipmentInfo> createShipment(List<BeerDto> beerDtoList) {

        return Mono.just(BeerShipmentInfo.builder()
                .shipmentId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                .address("12, 2nd Avenue")
                .city("Bangalore")
                .pinCode("5040403")
                .state("Karnataka")
                .mobileNumber("098324324432")
                .id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                .status("CREATED")
                .totalPrice(beerDtoList.stream().mapToDouble(BeerDto::getPrice).sum())
                .beerList(beerDtoList)
                .build());
    }
}
