package com.debuglife.codelabs.camel.jdbc;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentClientAcknowledge;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JdbcTest extends CamelTestSupport{
    private JdbcTemplate jdbc;

    @Before
    public void setupDatabase() throws Exception {
        DataSource ds = context.getRegistry().lookupByNameAndType("dataSource", DataSource.class);
        jdbc = new JdbcTemplate(ds);

        jdbc.execute("create table incoming_orders "
            + "( part_name varchar(20), quantity int, customer varchar(20))");
    }

    @After
    public void dropDatabase() throws Exception {
        jdbc.execute("drop table incoming_orders");
    }
    
    @Override    
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jms", jmsComponentClientAcknowledge(connectionFactory));

        return camelContext;
    }    
    
    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry jndi = super.createRegistry();
        jndi.bind("orderToSql", new OrderToSqlBean()); 
        
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("org.hsqldb.jdbcDriver");
//        ds.setUrl("jdbc:hsqldb:mem:order");
//        ds.setUsername("sa");
//        ds.setPassword("");
        
        // !!! maybe above datasource has issue, so alternative is below.
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass("org.hsqldb.jdbcDriver");
        ds.setJdbcUrl("jdbc:hsqldb:mem:order");
        ds.setUser("sa");
        ds.setPassword("");

        jndi.bind("dataSource", ds);
        return jndi;
    }    
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            
            @Override
            public void configure() throws Exception {
                from("jms:accounting")
                    .to("bean:orderToSql")
                    .to("jdbc:dataSource")
                    .to("mock:result");
            }
        };
    }

    @Test
    public void testJdbcInsert() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        assertEquals(0, jdbc.queryForInt("select count(*) from incoming_orders"));

        template.sendBody("jms:accounting", "<order name=\"motor\" amount=\"1\" customer=\"honda\"/>");
        
        mock.assertIsSatisfied();
        
        assertEquals(1, jdbc.queryForInt("select count(*) from incoming_orders"));
    }

}
