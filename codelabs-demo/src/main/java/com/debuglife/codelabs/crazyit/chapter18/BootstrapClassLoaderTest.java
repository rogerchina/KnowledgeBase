package com.debuglife.codelabs.crazyit.chapter18;

import java.net.URL;

public class BootstrapClassLoaderTest {
    @SuppressWarnings("restriction")
    public static void main(String[] args) {
	URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
	for (int i = 0; i < urls.length; i++) {
	    System.out.println(urls[i].toExternalForm());
	}
    }
}
