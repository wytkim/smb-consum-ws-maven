package com.smband.ws.client.gw;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.smband.ws.client.wsdl.CommonHeader;
import com.smband.ws.client.wsdl.GetCountryRequest;
import com.smband.ws.client.wsdl.GetCountryResponse;
import com.smband.ws.client.wsdl.ObjectFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {

	private final String SoapActionUri = "http://www.smband.com/countries/countryRequest";
	public GetCountryResponse getCountry(String country) {

		GetCountryRequest request = new GetCountryRequest();
		request.setName(country);

		log.info("Requesting location for " + country);

		//getWebServiceTemplate().mar
		
//		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
//				//.marshalSendAndReceive("http://localhost:8080/ws/countries", request,
//				.marshalSendAndReceive("http://localhost:8080/ws", request,
//						new SoapActionCallback(
//								"http://www.smband.com/countries/GetCountryRequest"));

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8081/ws", request, new WebServiceMessageCallback() {
			
			@Override
			public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
				SoapMessage soapMsg = (SoapMessage) message;
				// soapAction 추가.
				soapMsg.setSoapAction(SoapActionUri);
				
				// header 처리.
				// get the header from the SOAP message
				SoapHeader soapHeader = soapMsg.getSoapHeader();
				
				// create the header element
				ObjectFactory factory = new ObjectFactory();
				
				//factory.
				CommonHeader commonHeader = factory.createCommonHeader();
				commonHeader.setAppName("soap app name");
				commonHeader.setSvcName("service soap app");
				commonHeader.setFnCd("1002003");
				
				JAXBContext context;
				try {
					
					JAXBElement<CommonHeader> jaxbCommonHeader = factory.createCommonHeader(commonHeader);
					// create a marshaller
					context = JAXBContext.newInstance(CommonHeader.class);
					Marshaller marshaller = context.createMarshaller();
					
					// marshal the headers into the specified result
					marshaller.marshal(jaxbCommonHeader, soapHeader.getResult());
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//getWebServiceTemplate().
		return response;
	}
}
