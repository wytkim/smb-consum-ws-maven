package com.smband.ws.client.gw;

import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.smband.ws.client.wsdl.CommonHeader;
import com.smband.ws.client.wsdl.ObjectFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountryInterceptor implements ClientInterceptor {

	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		// TODO Auto-generated method stub

		try {
		SaajSoapMessage soapResponse = (SaajSoapMessage) messageContext.getResponse();
		SoapHeader resHeader = soapResponse.getSoapHeader();
		
		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		Iterator<SoapHeaderElement> itr = resHeader.examineAllHeaderElements();
		while(itr.hasNext()) {
			SoapHeaderElement headerElement = itr.next();
			QName qName = headerElement.getName();
			log.info("part: {}, namespaceUri: {}", qName.getLocalPart(), qName.getNamespaceURI());
			
			JAXBElement<CommonHeader> jaxbHeader =
			(JAXBElement<CommonHeader>)unmarshaller.unmarshal(headerElement.getSource());
			
			CommonHeader header = jaxbHeader.getValue();
			log.info("res header: {}", header);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
//		if(resHeader != null) {
//			try {
//				//resHeader.getResult().
////				JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
////				Unmarshaller unmarshaller = context.createUnmarshaller();
//
////				log.info("header source: {}", resHeader.getResult());
////				JAXBElement<CommonHeader> resCommonHeader =(JAXBElement<CommonHeader>)
////						unmarshaller.unmarshal(resHeader.getSource());
////
////				CommonHeader responseHeader = resCommonHeader.getValue();
////				log.info("response header: {}", responseHeader);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
		// TODO Auto-generated method stub

	}

}
