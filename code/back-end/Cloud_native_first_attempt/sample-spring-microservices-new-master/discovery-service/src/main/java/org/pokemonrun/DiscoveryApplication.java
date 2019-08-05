package org.pokemonrun;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
@EnableAdminServer
public class DiscoveryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DiscoveryApplication.class).run(args);
	}

}
