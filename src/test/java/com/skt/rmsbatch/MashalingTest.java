package com.skt.rmsbatch;

import java.io.File;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.skt.rmsbatch.responsepojo.DataSet;
import com.skt.rmsbatch.responsepojo.Record;
import com.skt.rmsbatch.responsepojo.RecordSet;
import com.skt.rmsbatch.responsepojo.Response;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SpringRestBatchApplication.class)
//@PropertySource("classpath:application.properties")
public class MashalingTest {
	
	@Test
	public void unmarshallTest() {
		
		try{
			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(Response.class);
			if(this.getClass().getResource("/risk_respon1se.xml") != null){
				URL resource = this.getClass().getResource("/risk_respon1se.xml");
		        File fXmlFile = new File(resource.toURI());
		        Response response =  (Response) jaxbContext.createUnmarshaller().unmarshal(fXmlFile); 
				
				DataSet dataSet = response.getDataSet();
				
				System.out.println("----------------------------------");
				System.out.println(dataSet.getMessage().getMessageId());
				
				RecordSet recordSet = dataSet.getRecordSet();
				
				List<Record> records = recordSet.getRecord();
				
				System.out.println(records.size());
				
				for(Record record : records){
					System.out.println(record.toString());
				}
				
				System.out.println("----------------------------------");
			}
			
			
			
		}catch(JAXBException e){
			e.printStackTrace();
		}catch(URISyntaxException e){
			e.printStackTrace();
		}
	}
	
	
//	private String getResponseByHioms( String startDate, String endDate ){
//		
//		LOGGER.info("getResponseByHioms Hioms 정보 Read");
//		StringWriter sw = new StringWriter();
//        Response response = null;
//        String resourceUrl = "/risk_response_"+startDate+".xml";
//        
//		try{
//        	JAXBContext jaxbContext = null;
//    		jaxbContext = JAXBContext.newInstance(Response.class);
//    		
//    		if(this.getClass().getResource(resourceUrl) != null){
//    			URL resource = this.getClass().getResource(resourceUrl);
//                File fXmlFile = new File(resource.toURI());
//                response =  (Response) jaxbContext.createUnmarshaller().unmarshal(fXmlFile);
//                Marshaller m = jaxbContext.createMarshaller();
//    			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//    			m.marshal(response, sw);
//    		}
//        }catch(JAXBException e){
//			e.printStackTrace();
//		}catch(URISyntaxException e){
//			e.printStackTrace();
//		}
//        return sw.toString();
//	}

}

