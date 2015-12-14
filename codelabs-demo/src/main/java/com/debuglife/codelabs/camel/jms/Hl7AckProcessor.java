/**
 * The contents of this file are copyright (c) 2013 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.camel.jms;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import ca.uhn.hl7v2.model.Message;

public class Hl7AckProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) throws Exception {
        Message message = exchange.getIn().getBody(Message.class); //dto.getSourceMessage();
        Message ackMessage = message.generateACK();
        exchange.getOut().setBody(ackMessage);
    }
}
