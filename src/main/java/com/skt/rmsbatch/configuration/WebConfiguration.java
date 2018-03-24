//package com.skt.rmsbatch.configuration;
//
//import org.h2.server.web.WebServlet;
//import org.springframework.boot.context.embedded.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * WenConfig
// */
//@Configuration
//public class WebConfiguration {
//	
//	/**
//	 * H2DB 콘솔 URL Mapping
//	 * @return
//	 */
//    @Bean
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//    }
//    
//}
