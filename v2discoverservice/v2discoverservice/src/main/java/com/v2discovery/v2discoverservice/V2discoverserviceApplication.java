package com.v2discovery.v2discoverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class V2discoverserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V2discoverserviceApplication.class, args);
	}

}
