# beer-order-service

### Introduction 

Simple Spring Webflux based API serves getBeer, getBeers and createBeerOrder

### EndPoints

###### getBeer : GET : http://localhost:8090/beerOrder/beer/Budweiser
###### getBeers : GET : http://localhost:8090/beerOrder/beers?param=name&value=Budweiser
                        http://localhost:8090/beerOrder/beers?param=price&value=52.00
                        http://localhost:8090/beerOrder/beers?param=brewery&value=JB Breweries
###### createOrder : POST : http://localhost:8090/beerOrder
Payload
* {
* "beerId":1001,
* "beerName": "KingFisher",
* "brewer": "Budweiser Breweries",
* "size": 350.0,
* "price": 55.0
* }


It has 3 endpoints
Please check the postman collection available in this folder

### Unit Test

### Build with Docker

##### To create executable jar, execute below command 
./gradlew build
##### To compile and create docker Image
./gradlew build docker
OR 
docker build -t beerOrderService:1 .
##### To run Docker container
docker run -d --name beerOrderService -p 8090:8090 beerOrderService:1
##### To tail follow the logs of container
docker logs -f beerOrderService
