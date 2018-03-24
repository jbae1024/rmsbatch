package com.skt.rmsbatch;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ParameterMode;
import org.xml.sax.InputSource;

import com.skt.rmsbatch.common.JaxbUtilsService;
import com.skt.rmsbatch.common.JaxbUtilsServiceImpl;
import com.skt.rmsbatch.domain.Fields;
import com.skt.rmsbatch.domain.Request;

import javax.xml.bind.JAXBContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SpringRestBatchApplication.class)
//@PropertySource("classpath:application.properties")
public class XmlTest {

	private RestTemplate restTemplate;

    @Before
    public void getRestTemplate(){
    }
    
    
    @Value("${server.port}")
    private String serverPort;
    
	@Test
	public void loadRiskWsdl() {
		
		try {
			String strInParam = 
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
					"<request>"+
					"<dataSet>"+
					"	<fields>"+
					"	<sys_key><![CDATA[발급받은sys_key입력]]></sys_key>"+
					"	<svc_id><![CDATA[서비스ID입력]]></svc_id>"+
					"	<sc_strt_dt><![CDATA[20130116]]></sc_strt_dt>"+
					"  	<sc_end_dt><![CDATA[20130212]]></sc_end_dt>"+
					"	<sc_cb_cust><![CDATA[34]]></sc_cb_cust>"+
					"	<sc_prc_yn><![CDATA[E]]></sc_prc_yn>"+
					"	</fields>"+
					"</dataSet>"+
				    "</request>";
			
			String endpoint = "http://localhost:8080/axis/service-managed-by-spring-di?wsdl";
			//endpoint = "http://localhost:8080/studentlist";

	        Service  service = new Service();
	        Call     call    = (Call) service.createCall();

	        call.setTargetEndpointAddress( new java.net.URL(endpoint) );
	        //String ret = (String) call.invoke("sss", "helloWorld", new Object[] { "Hello!" });
	        
	        //InputStream resource = this.getClass().getResourceAsStream("/risk_response.xml");
	        
	        //FileInputStream is = (FileInputStream) this.getClass().getResourceAsStream("/risk_response.xml");
	        
	        //URL resource = this.getClass().getResource("/risk_response.xml");
	        //File is = new File(resource.toURI());
	        //FileInputStream input = new FileInputStream(is);
	        
	        //System.out.println(resource);
	        
	        URL resource = this.getClass().getResource("/risk_response.xml");
	        File fXmlFile = new File(resource.toURI());
	        
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = (Document) dBuilder.parse(fXmlFile);
	        doc.getDocumentElement().normalize();
	        
	        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	        
	        NodeList nList = doc.getElementsByTagName("record");
	        System.out.println("-----------------------");
	        
	        DocumnetParser documnetParser = new DocumnetParser();;
            
	        //DocumnetParser documnetParser = new DocumnetParser();
	        
//	        Node node = documnetParser.getNode("record", nList);
	        
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	
	            Node nNode = nList.item(temp);
	            
	            //System.out.println(documnetParser.getNodeAttr("장애접수번호", nNode));
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("장애접수번호 : " + getTagValue("장애접수번호", eElement));
	               System.out.println("장애제목 : " + getTagValue("장애제목", eElement));
	               System.out.println("장애상세내역 : " + getTagValue("장애상세내역", eElement));
	               System.out.println("심각도 : " + getTagValue("심각도", eElement));
	               System.out.println("장애발생시간 : " + getTagValue("장애발생시간", eElement));
	               System.out.println("장애해결시간 : " + getTagValue("장애해결시간", eElement));
	               //System.out.println("영향범위 : " + getTagValue("영향범위", eElement));
	               //System.out.println("세부원인 : " + getTagValue("세부원인", eElement));
	               System.out.println("관련문의건수 : " + getTagValue("관련문의건수", eElement));
	               //System.out.println("조치내역 : " + getTagValue("조치내역", eElement));
	            }
	        }
	        
	        //doc.getDocumentElement().normalize();
	        
	        //documnetParser.get
	        
	        NodeList messsageNodeList = doc.getElementsByTagName("message");
	        
	        System.out.println("건수 : "+messsageNodeList.getLength());
	        
	        for (int temp = 0; temp < messsageNodeList.getLength(); temp++) {
	        	
	        	Node nNode = messsageNodeList.item(temp);
	        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	        		Element eElement = (Element) nNode;
	        		System.out.println("result : " + getTagValue("result", eElement));
		            System.out.println("messageId : " + getTagValue("messageId", eElement));
	        	}
	        }
	        
	        
	        Node node = documnetParser.getNode("message", messsageNodeList);
	        
	        System.out.println("node : "+node);
	        
	        System.out.println(messsageNodeList.getLength());
	        
	        System.out.println(messsageNodeList.item(0).getNodeValue());
	        
	        System.out.println(documnetParser.getNodeValue("result", messsageNodeList));
	        //messsageNodeList.getLength();
	        
	        //File file = new File("risk_response.xml");
	        
	        //System.out.println(file);;
			
		}catch (Exception e) {
			System.err.println(e.toString());
		}
    }
	
	private static String getTagValue(String sTag, Element eElement) {
		
		 NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		 Node nValue = (Node) nlList.item(0);
		 if(nValue != null ){
			 return nValue.getNodeValue();
		 }
		 return "";
	}
	
	
//	private static Document convertStringToDocument(String xmlStr) {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
//        DocumentBuilder builder;  
//        try  
//        {  
//            builder = factory.newDocumentBuilder();  
//            Document doc = (Document)builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
//            return doc;
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        } 
//        return null;
//    }
	
	@Test
	public void xmlParseTest() {
		
		Fields fields = new Fields ();
//		fields.setSysKey("7w+u2V7CRQwC/bK7oSF7sQ==");
//		fields.setSysId("svcs00042");
//		fields.setDsCd("0");
//		fields.setSearchDate("20160729");
//		fields.setTrblAccpNo("ER160729-000009");
		
		Request request = new Request();
		request.setFields(fields);
		
		//RequestInterface requestInterface = new Request(); 
		
		//requestInterface.se;
		
		//String requestString = (String)jaxbObjectToXMLString(request);
		
//		JaxbUtils jaxbUtils = new JaxbUtils(request);
//		
//		
//		String requestString  = jaxbUtils.jaxbObjectToXMLString();
		
		
		//String requestString = (String)jaxbUtil.jaxbObjectToXMLString();
		
		
		//JaxbUtilsService jaxbUtilsService = new JaxbUtilsServiceImpl();
		
		//String requestString = (String)jaxbUtilsService.jaxbObjectToXMLString(request);
		
		//String requestString = JaxbUtilsService
		
//		System.out.println(requestString);
	}
	
//	private static String jaxbObjectToXMLString(Object object) {
//        try {
//        	StringWriter sw = new StringWriter();
//        	
//			JAXBContext context = JAXBContext.newInstance();
//			Marshaller m = context.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			
//			m.marshal(object, sw);
//			
//			return sw.toString();
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
	
}

//class JaxbUtils<E>{
//	
//	private static String jaxbObjectToXMLString(Request request) {
//        try {
//			JAXBContext context = JAXBContext.newInstance(Request.class);
//			Marshaller m = context.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			
//			StringWriter sw = new StringWriter();
//			m.marshal(request, sw);
//			
//			return sw.toString();
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}

class DocumnetParser{
	
	
	protected Node getNode(String tagName, NodeList nodes){
		for (int x=0; x<nodes.getLength(); x++){
			Node node = nodes.item(x);
			if(node.getNodeName().equalsIgnoreCase(tagName)){
				return node;
			}
		}
		return null;
	}
	
	protected String getNodeValue(Node node){
		NodeList childNodes = node.getChildNodes();
		for (int x = 0; x<childNodes.getLength(); x++){
			Node data = childNodes.item(x);
			if( data.getNodeType() == Node.TEXT_NODE){
				return data.getNodeValue();
			}
		}
		return "";
	}
	
	protected String getNodeValue(String tagName, NodeList nodes){
		for (int x = 0; x < nodes.getLength(); x++){
			Node node = nodes.item(x);
			if(node.getNodeName().equalsIgnoreCase(tagName)){
				NodeList childNodes = node.getChildNodes();
				for(int y = 0; y < childNodes.getLength(); y++){
					Node data = childNodes.item(y);
					if(data.getNodeType() == Node.TEXT_NODE){
						return data.getNodeValue();
					}
				}
			}
		}
		return "";
	}
	
	protected String getNodeAttr(String attrName, Node node){
		NamedNodeMap attrs = node.getAttributes();
		for(int y = 0; y< attrs.getLength(); y++){
			Node attr = attrs.item(y);
			if(attr.getNodeName().equalsIgnoreCase(attrName)){
				return attr.getNodeValue();
			}
		}
		return "";
	}
	
	protected String getNodeAttr(String tagName, String attrName, NodeList nodes){
		for(int x = 0; x<nodes.getLength(); x++){
			Node node = nodes.item(x);
			if( node.getNodeName().equalsIgnoreCase(tagName) ){
				NodeList childNodes =  node.getChildNodes();
				for(int y=0; y<childNodes.getLength(); y++){
					Node data = childNodes.item(y);
					if(data.getNodeName().equalsIgnoreCase(attrName))
						return data.getNodeValue();
				}
			}
		}
		return "";
	}
	
}

