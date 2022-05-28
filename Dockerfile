FROM adoptopenjdk/openjdk11
COPY build/libs/*SNAPSHOT.jar beerOrderService.jar
EXPOSE 8090
CMD ["java", "-jar", "beerOrderService.jar" ]