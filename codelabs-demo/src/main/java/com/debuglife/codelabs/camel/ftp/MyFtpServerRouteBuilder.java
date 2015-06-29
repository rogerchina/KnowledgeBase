/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.debuglife.codelabs.camel.ftp;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.apache.camel.component.file.remote.RemoteFile;
import org.apache.camel.component.properties.PropertiesComponent;

/**
 * Server route
 */
public class MyFtpServerRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // configure properties component
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:ftp.properties");

        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        FileProcessor fp = new FileProcessor();
        from("{{sftp.server}}")
            .process(fp)
            .to("file:target/download")
            .log("Downloaded file ${file:name} ${file:name.ext} ${file:path} ${file:length} ${file:modified} complete.");

        // use system out so it stand out
//        System.out.println("*********************************************************************************");
//        System.out.println("Camel will route files from the FTP server: "
//                + getContext().resolvePropertyPlaceholders("{{sftp.server}}") + " to the target/download directory.");
//        System.out.println("You can configure the location of the ftp server in the src/main/resources/ftp.properties file.");
//        System.out.println("Use ctrl + c to stop this application.");
//        System.out.println("*********************************************************************************");
    }
}

class FileProcessor implements Processor{
    @SuppressWarnings("rawtypes")
    @Override
    public void process(Exchange exchange) throws Exception {
        Message msg = exchange.getIn();
        Object body = msg.getBody();
        RemoteFile f = (RemoteFile)body;
        System.out.println("centent of message： " + body);
        System.out.println("content of files: " + f.getBody());
    }
}

class FileFilter<T> implements GenericFileFilter<T>{

    @Override
    public boolean accept(GenericFile<T> file) {
        // we want all directoies
        if(file.isDirectory()){
            return true;
        }
        // we don't accept any files ending with xml in the name
        return !file.getFileName().endsWith(".xml");
    }
    
}
