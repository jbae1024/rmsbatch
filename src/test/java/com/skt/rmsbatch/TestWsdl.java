package com.skt.rmsbatch;

import java.io.StringReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.skt.rmsbatch.responsepojo.Response;

public class TestWsdl {

	@Test
	public void getWsdlService(){
		
		try {
			String strInParam = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<request>"+
				"<dataSet>"+
				"	<fields>"+
				"		<sys_key><![CDATA[7w+u2V7CRQwC/bK7oSF7sQ==]]></sys_key>"+
				"		<svc_id><![CDATA[svcs00042]]></svc_id>"+
				"		<ds_cd><![CDATA[0]]></ds_cd>"+
				"  		<search_strt_date><![CDATA[20160729]]></search_strt_date>"+
				"  		<search_end_date><![CDATA[20160729]]></search_end_date>"+
				"		<trbl_accp_no><![CDATA[ER160729-000009]]></trbl_accp_no>"+
				"	</fields>"+
				"</dataSet>"+
			    "</request>";

			// 웹 서비스 access point 지정 
	        String endpoint = "http://203.235.210.94:9280/omwebservice/services/OmWebserviceImpl?wsdl";
	        
	        // 원격 웹 서비스에 대한 Service 객체 생성후, Call 객체 생성 
	        Service service = new Service();
	        Call call = (Call) service.createCall();

	        // 입출력 인자정보 및 웹 서비스 access point 를 Call 객체에 바인딩
	        call.setTargetEndpointAddress( new URL(endpoint) );
	        call.setOperationName( 
	        		new QName("http://webservice.om.skcc.com", "getOmWebservice"));
	        
	        //call.addParameter("request", XMLType.XSD_STRING, ParameterMode.IN);
	        //call.setReturnType(XMLType.XSD_STRING);

	        // 입력 인자값을 가지고 웹 서비스 호출하여 실행결과 얻어옴
	        String result = (String)call.invoke(new Object[] {new String(strInParam)});        
	        
	        System.out.println(result);
	        
	        JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        
	        Response response = (Response)unmarshaller.unmarshal(new StringReader(result));
	        
	        System.out.println(response.getDataSet().getMessage().getResult());
	        System.out.println(response.getDataSet().getRecordSet().getRecord().size());
	        
	        //SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
	        //JAXBContext jc = JAXBContext.newInstance(AddressBook.class);
	        //parser.parse("", new SAXSampleParser());
	        
//	        Document doc = convertStringToDocument(result);
//			// 결과값 출력 
//			
//			NodeList root = doc.getChildNodes();
//			
//			DocmonetParser docmonetParser = new DocmonetParser();
//			
//			
//			// Navigate down the hierarchy to get to the CEO node
//		    Node comp = docmonetParser.getNode("response", root);
//		    Node transaction = docmonetParser.getNode("dataSet", comp.getChildNodes() );
//		    
//		    
//		    //String execType = docmonetParser.getNodeAttr("type", exec);
//		 
//		    // Load the executive's data from the XML
//		    NodeList nodes = transaction.getChildNodes();
//		    
//		    String messageResult = docmonetParser.getNodeValue("id", nodes);
//		    
//		    //System.out.println(messageResult);
//		    
//		    
//		    NodeList messageNodeList = doc.getElementsByTagName("message");
//		    
//		    for(int temp=0; temp < messageNodeList.getLength(); temp++){
//		    	
//		    	Node nNode = messageNodeList.item(temp);
//		    	if(nNode.getNodeType() == Node.ELEMENT_NODE){
//		    		
//		    		Element eElement = (Element)nNode;
//		    		
//		    		System.out.println(eElement);
//		    		
//		    	}
//		    	
//		    }
//		    
		    
		    
//		    String lastName = docmonetParser.getNodeValue("LastName", nodes);
//		    String firstName = docmonetParser.getNodeValue("FirstName", nodes);
//		    String street = docmonetParser.getNodeValue("street", nodes);
//		    String city = docmonetParser.getNodeValue("city", nodes);
//		    String state = docmonetParser.getNodeValue("state", nodes);
//		    String zip = docmonetParser.getNodeValue("zip", nodes);
//		 
//		    System.out.println("Executive Information:");
//		    System.out.println("Type: " + execType);
//		    System.out.println(lastName + ", " + firstName);
//		    System.out.println(street);
//		    System.out.println(city + ", " + state + " " + zip);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Document convertStringToDocument(String xmlStr){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try{
			builder = factory.newDocumentBuilder();
			Document doc = (Document)builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class DocmonetParser{
	protected Node getNode(String tagName, NodeList nodes) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            return node;
	        }
	    }
	 
	    return null;
	}
	 
	protected String getNodeValue( Node node ) {
	    NodeList childNodes = node.getChildNodes();
	    for (int x = 0; x < childNodes.getLength(); x++ ) {
	        Node data = childNodes.item(x);
	        if ( data.getNodeType() == Node.TEXT_NODE )
	            return data.getNodeValue();
	    }
	    return "";
	}
	 
	protected String getNodeValue(String tagName, NodeList nodes ) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            NodeList childNodes = node.getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++ ) {
	                Node data = childNodes.item(y);
	                if ( data.getNodeType() == Node.TEXT_NODE )
	                    return data.getNodeValue();
	            }
	        }
	    }
	    return "";
	}
	 
	protected String getNodeAttr(String attrName, Node node ) {
	    NamedNodeMap attrs = node.getAttributes();
	    for (int y = 0; y < attrs.getLength(); y++ ) {
	        Node attr = attrs.item(y);
	        if (attr.getNodeName().equalsIgnoreCase(attrName)) {
	            return attr.getNodeValue();
	        }
	    }
	    return "";
	}
	 
	protected String getNodeAttr(String tagName, String attrName, NodeList nodes ) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            NodeList childNodes = node.getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++ ) {
	                Node data = childNodes.item(y);
	                if ( data.getNodeType() == Node.ATTRIBUTE_NODE ) {
	                    if ( data.getNodeName().equalsIgnoreCase(attrName) )
	                        return data.getNodeValue();
	                }
	            }
	        }
	    }
	 
	    return "";
	}
}
