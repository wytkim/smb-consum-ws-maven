
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.smband.ws.client.wsdl;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2023-04-23T14:30:00.905+09:00
 * Generated source version: 2.7.18
 * 
 */

@javax.jws.WebService(
                      serviceName = "countriesPortService",
                      portName = "countriesService",
                      targetNamespace = "http://www.smband.com/countries",
                      wsdlLocation = "file:/D:/javaProject/workspace-sts4-test2/soap-svc-maven/src/main/resources/countries.wsdl",
                      endpointInterface = "com.smband.ws.client.wsdl.CountriesPort")
                      
public class CountriesPortImpl implements CountriesPort {

    private static final Logger LOG = Logger.getLogger(CountriesPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.smband.ws.client.wsdl.CountriesPort#findCountry(com.smband.ws.client.wsdl.GetCountryRequest  countryRequest ,)com.smband.ws.client.wsdl.CommonHeader  commonHeader )*
     */
    public com.smband.ws.client.wsdl.GetCountryResponse findCountry(GetCountryRequest countryRequest,javax.xml.ws.Holder<CommonHeader> commonHeader) { 
        LOG.info("Executing operation findCountry");
        System.out.println(countryRequest);
        System.out.println(commonHeader.value);
        try {
            com.smband.ws.client.wsdl.GetCountryResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
