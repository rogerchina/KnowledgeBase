package com.debuglife.codelabs.corejava.ssl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class TestSSLProtocol {
    public static void main(String[] args) throws Exception{
        Properties p = System.getProperties();
        /**
         * https.protocol
         * javax.rmi.ssl.client.enabledProtocols
         * javax.rmi.ssl.client.enabledCipherSuites
         */
        
    }
    
    public void test() throws Exception{
        System.setProperty("https.protocols", "");
        
        SSLSocket sslsocket = (SSLSocket)SSLSocketFactory.getDefault().createSocket();
        String [] protocols = sslsocket.getSupportedProtocols();
        System.out.println("java supported protocols:");
        for(String protocol: protocols){
            System.out.println(protocol);
        }
        
        String [] enabledProtocols1 = sslsocket.getEnabledProtocols();
        System.out.println("java enabled supported protocols:");
        for(String protocol: enabledProtocols1){
            System.out.println(protocol);
        }
        
        String [] ciphers = sslsocket.getSupportedCipherSuites();
        System.out.println("java supported cipher suites:");
        for(String cipher: ciphers){
            System.out.println(cipher);
        }
        
        String a = "hello";
        a.intern();
        
        // work around for disabling ssl v3.0
        // If supported protocols are not explicitly set, remove all SSL protocol versions
        final String[] allProtocols = sslsocket.getSupportedProtocols();
        final List<String> enabledProtocols = new ArrayList<String>(allProtocols.length);
        for (String protocol: allProtocols) {
            if (!protocol.startsWith("SSL")) {
                enabledProtocols.add(protocol);
            }
        }
        sslsocket.setEnabledProtocols(enabledProtocols.toArray(new String[enabledProtocols.size()]));
        
        SSLSocket sslSocket = (SSLSocket)HttpsURLConnection.getDefaultSSLSocketFactory().getDefault().createSocket();
    }
}
