/**
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.camel.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ejb.EjbComponent;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
/**
 * when testing the ejb, ejb container is required, so we need to make use of
 * container-based test framework.
 */
public class EJBTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
	CamelContext context = new DefaultCamelContext();
	context.addComponent("ejb", new EjbComponent());
	EjbComponent ejb = context.getComponent("ejb", EjbComponent.class);
	ejb.setContext(createEjbContext());
	return context;
    }
    
    private static Context createEjbContext() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        return new InitialContext(properties);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
	return new RouteBuilder(){
	    @Override
	    public void configure() throws Exception {
		from("direct:start")
			.to("ejb:RAWMessageDAOIntf?method=hello")
			.log(LoggingLevel.INFO, "ejb is invoked successfully.")
			.to("mock:weld");
	    }
	};
    }

    //@Test
    public void testRouteInvokeEJB() throws Exception{
	MockEndpoint mock = this.getMockEndpoint("mock:weld");
	mock.expectedMessageCount(1);
	assertMockEndpointsSatisfied();
    }
}

