package com.cetc27.gfg.rwgl.config;

import com.cetc27.gfg.rwgl.webservice.ProductService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private ProductService productService;

    @SuppressWarnings("all")
    @Bean
    public ServletRegistrationBean dispatchServlet() {

        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, productService);
        endpoint.publish("/product");
        return endpoint;
    }
}
