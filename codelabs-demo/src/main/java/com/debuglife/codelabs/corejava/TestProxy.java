package com.debuglife.codelabs.corejava;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Test;

public class TestProxy {

		private static final Logger logg = Logger.getLogger(TestProxy.class.getName());
		private static final String username = "";
		private static final String password = "";
		private static final String host = "";
		private static final String port = "";
		
		/**
		 * init proxy
		 */
		public static void initAuth() {
			Properties props = System.getProperties();
			props.setProperty("proxySet", "true");
			props.setProperty("http.proxyHost", host);
			props.setProperty("http.proxyPort", port);
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,
							new String(password).toCharArray());
				}
			});
		}

		@Test
		public void test() {
			initAuth();
			logg.info(getHtmlShow("http://tech.qq.com/web/it/telerss.xml"));
			getHtmlToSaveFile("http://tech.qq.com/web/it/telerss.xml", "c:\\", "do.html");
		}
		
		/**
		 * get html from url
		 * @param address
		 * @return
		 */
		private static String getHtmlShow(String address) {
			StringBuffer html = new StringBuffer();
			String result = null;
			try {
				URL url = new URL(address);
				URLConnection conn = url.openConnection();
				conn.setRequestProperty(
						"User-Agent",
						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; GTB5; .NET CLR 2.0.50727; CIBA)");
				BufferedInputStream in = new BufferedInputStream(
						conn.getInputStream());
				try {
					String inputLine;
					byte[] buf = new byte[4096];
					int bytesRead = 0;
					while (bytesRead >= 0) {
						inputLine = new String(buf, 0, bytesRead, "ISO-8859-1");
						html.append(inputLine);
						bytesRead = in.read(buf);
						inputLine = null;
					}
					buf = null;
				} finally {
					in.close();
					conn = null;
					url = null;
				}
				result = new String(html.toString().trim().getBytes("ISO-8859-1"),
						"gb2312").toLowerCase();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			html = null;
			return result;
		}
		
		/**
		 * save file
		 * @param address
		 * @return
		 */
		private static void getHtmlToSaveFile(String address,String path,String fileName) {
			try {
				URL url = new URL(address);
				URLConnection conn = url.openConnection();
				BufferedInputStream in = new BufferedInputStream(
						conn.getInputStream());
				FileOutputStream fs = new FileOutputStream(path+fileName);
				try {
					byte[] buf = new byte[1024];
					int bytesRead = 0;
					while ((bytesRead = in.read(buf)) != -1) {
						bytesRead += bytesRead;
						fs.write(buf);
					 }
					logg.info(""+bytesRead);
				} finally {
					in.close();
					conn = null;
					url = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

