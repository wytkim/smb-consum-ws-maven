
package com.smband.ws.client.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.smband.ws.client.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CommonHeader_QNAME = new QName("http://www.smband.com/countries", "commonHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.smband.ws.client.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CommonHeader }
     * 
     */
    public CommonHeader createCommonHeader() {
        return new CommonHeader();
    }

    /**
     * Create an instance of {@link GetCountryRequest }
     * 
     */
    public GetCountryRequest createGetCountryRequest() {
        return new GetCountryRequest();
    }

    /**
     * Create an instance of {@link GetCountryResponse }
     * 
     */
    public GetCountryResponse createGetCountryResponse() {
        return new GetCountryResponse();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommonHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.smband.com/countries", name = "commonHeader")
    public JAXBElement<CommonHeader> createCommonHeader(CommonHeader value) {
        return new JAXBElement<CommonHeader>(_CommonHeader_QNAME, CommonHeader.class, null, value);
    }

}
