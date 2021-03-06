package com.github.gossie.home.roomservice;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;

@EnableEurekaClient
@EnableBinding(Sink.class)
@IntegrationComponentScan
@SpringBootApplication
public class RoomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomServiceApplication.class, args);
	}

	@Bean
    public CommandLineRunner initializeTestData(RoomRepository repository) {
	    return args -> {
	        Room livingRoom = new Room(null, "Wohnzimmer");
	        Room kitchen = new Room(null, "Küche");
	        Room bathroom = new Room(null, "Badezimmer");
	        Room bedroom = new Room(null, "Schlafzimmer");
	        repository.saveAll(Arrays.asList(livingRoom, kitchen, bathroom, bedroom));
        };
    }
}
