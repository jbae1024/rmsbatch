package com.skt.rmsbatch.common;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.skt.rmsbatch.domain.Request;

public class JaxbUtilsServiceImpl implements JaxbUtilsService{
	
	
//	private Request request;
//	
//	public JaxbUtilsServiceImpl(Request request){
//		this.request = request;
//	}
	@Override
	public String jaxbObjectToXMLString(Object request) {
		
        try {
			JAXBContext context = JAXBContext.newInstance(Object.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			StringWriter sw = new StringWriter();
			m.marshal(request, sw);
			
			return sw.toString();
			
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
}
