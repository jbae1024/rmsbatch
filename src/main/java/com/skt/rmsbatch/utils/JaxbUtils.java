package com.skt.rmsbatch.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.skt.rmsbatch.responsepojo.Response;

public class JaxbUtils{
	
	private Response response;
	
	public JaxbUtils(Response response){
		this.response = response;
	}
	
	public String jaxbObjectToXMLString() {
		
        try {
			JAXBContext context = JAXBContext.newInstance(Response.class);
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			StringWriter sw = new StringWriter();
			m.marshal(response, sw);
			
			return sw.toString();
			
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
