package com.smband.ws.client.gw;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;

import com.smband.ws.client.wsdl.CommonHeader;
import com.smband.ws.client.wsdl.GetCountryRequest;
import com.smband.ws.client.wsdl.GetCountryResponse;
import com.smband.ws.client.wsdl.ObjectFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountryClient2 extends WebServiceGatewaySupport {

	public GetCountryResponse getCountry(final String country) {
		String uri = "http://localhost:8081/ws";
		
		final ResponseWrapper resultWrapper = new ResponseWrapper();
		
		boolean success = getWebServiceTemplate().sendAndReceive(uri, new WebServiceMessageCallback() {
			
			@Override
			public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
				SoapMessage soapMessage = (SoapMessage)message;
				
				SoapBody soapBody = soapMessage.getSoapBody();
				SoapHeader soapHeader = soapMessage.getSoapHeader();
				
				GetCountryRequest request = new GetCountryRequest();
				request.setName(country);
				
				CommonHeader commonHeader = new CommonHeader();
				commonHeader.setAppName("request app name");
				commonHeader.setSvcName("req svc name");
				commonHeader.setFnCd("req fn cd");
				
				ObjectFactory factory = new ObjectFactory();
				try {
					JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
					Marshaller marshaller = context.createMarshaller();
					
					marshaller.marshal(request, soapBody.getPayloadResult());
					
				 	JAXBElement<CommonHeader> jaxbHeader = factory.createCommonHeader(commonHeader);
					marshaller.marshal(jaxbHeader, soapHeader.getResult());
				 	
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}, new WebServiceMessageCallback() {
			
			@Override
			public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
				SoapMessage soapMessage = (SoapMessage)message;
				
				SoapBody soapBody = soapMessage.getSoapBody();
				SoapHeader soapHeader = soapMessage.getSoapHeader();
				SoapHeaderElement soapHeaderEle = soapHeader.examineAllHeaderElements().next();
				
				try {
					JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
					//Marshaller marshaller = context.createMarshaller();
					Unmarshaller unmarshaller = context.createUnmarshaller();
					
					GetCountryResponse response = (GetCountryResponse)unmarshaller.unmarshal(soapBody.getPayloadSource());
					
					JAXBElement<CommonHeader> jaxbHeader = (JAXBElement<CommonHeader>)unmarshaller.unmarshal(soapHeaderEle.getSource());
					CommonHeader commonHeader = jaxbHeader.getValue();

					log.info("response: {}, res header: {}", response.getCountry(), commonHeader);
					
					resultWrapper.setResponse(response);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		if(!success) return null;
		
		return resultWrapper.getResponse();
	}
	
	@Getter
	@Setter
	public class ResponseWrapper{
		private GetCountryResponse response;
	}
}
