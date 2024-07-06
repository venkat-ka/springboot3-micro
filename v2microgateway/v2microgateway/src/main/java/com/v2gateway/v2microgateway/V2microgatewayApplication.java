package com.v2gateway.v2microgateway;

import org.apache.http.protocol.HTTP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import jakarta.ws.rs.HttpMethod;
import java.net.InetAddress;
import java.net.UnknownHostException;


@EnableDiscoveryClient
@SpringBootApplication
public class V2microgatewayApplication {

	
	public static void main(String[] args) {
		InetAddress ip;
	    String hostname;
		 try {
	            ip = InetAddress.getLocalHost();
	            hostname = ip.getHostName();
	            System.out.println("Your current IP address : " + ip);
	            System.out.println("Your current Hostname : " + hostname);
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        }
        
        
		SpringApplication.run(V2microgatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder rlb, AuthorizationHeaderFilter authorizationHeaderFilter) {
		System.out.println("cccfff checking");
		return rlb.routes().route(p -> p.path("/users-ws/users/status/check")
				.filters(f -> f.removeRequestHeader("Cookie").rewritePath("/users-ws/(?<segment>.*)", "/$\\{segment}")
						.filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
				.uri("lb://users-ws"))
				
				.build();
		
//		return rlb.routes()
//				.route("status_check", p -> p.path("/users-ws/users/status/check").and().method("GET")
//				.filters(f -> f.removeRequestHeader("Cookie").rewritePath("/users-ws/(?<segment>.*)", "/$\\{segment}")
//						.filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
//				.uri("lb://users-ws"))
//				.route("user_login", p -> p.path("/users-ws/users/login").and().method("POST")
//						.filters(f -> f.removeRequestHeader("Cookie").rewritePath("/users-ws/(?<segment>.*)", "/$\\{segment}")
//								)
//						.uri("lb://users-ws"))
//				.build();
	}
	
	
}
