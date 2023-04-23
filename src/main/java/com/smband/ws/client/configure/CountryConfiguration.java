package com.smband.ws.client.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

import com.smband.ws.client.gw.CountryClient;
import com.smband.ws.client.gw.CountryClient2;
import com.smband.ws.client.gw.CountryInterceptor;

@Configuration
public class CountryConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.smband.ws.client.wsdl");
		return marshaller;
	}

	@Bean
	public CountryClient countryClient(Jaxb2Marshaller marshaller) {
		CountryClient client = new CountryClient();
		client.setDefaultUri("http://localhost:8081/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		
		client.setInterceptors(new ClientInterceptor[] {new CountryInterceptor()});
		return client;
	}
	
	@Bean
	public CountryClient2 countryClient2(Jaxb2Marshaller marshaller) {
		CountryClient2 client = new CountryClient2();
		client.setDefaultUri("http://localhost:8081/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		
		//client.setInterceptors(new ClientInterceptor[] {new CountryInterceptor()});
		return client;
	}
}
