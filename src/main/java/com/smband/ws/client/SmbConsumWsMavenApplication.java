package com.smband.ws.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.smband.ws.client.gw.CountryClient;
import com.smband.ws.client.wsdl.GetCountryResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SmbConsumWsMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmbConsumWsMavenApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(CountryClient quoteClient) {
		return args -> {
			String country = "Spain";

//			if (args.length > 0) {
//				country = args[0];
//			}
			GetCountryResponse response = quoteClient.getCountry(country);
			log.info("response: {}", response.getCountry());
			//System.err.println(response.getCountry().getCurrency());
		};
	}
}
