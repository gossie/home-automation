package com.github.gossie.home.roomservice;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class HomeRoomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeRoomServiceApplication.class, args);
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
