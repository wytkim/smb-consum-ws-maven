package com.smband.ws.client.gw;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.smband.ws.client.wsdl.GetCountryRequest;
import com.smband.ws.client.wsdl.GetCountryResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {

	public GetCountryResponse getCountry(String country) {

		GetCountryRequest request = new GetCountryRequest();
		request.setName(country);

		log.info("Requesting location for " + country);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
				//.marshalSendAndReceive("http://localhost:8080/ws/countries", request,
				.marshalSendAndReceive("http://localhost:8080/ws", request,
						new SoapActionCallback(
								"http://www.smband.com/countries/GetCountryRequest"));

		return response;
	}
}
