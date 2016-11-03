package com.debuglife.codelabs.corejava.xml;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class ParseXml {
	private static URL url = null;
	private static final String username = "";
	private static final String password = "";
	private static final String host = "";
	private static final String port = "8080";

	public ParseXml() {
		initAuth();
	}

	/**
	 * set up the proxy server
	 */
	public static void initAuth() {
		Properties props = System.getProperties();
		props.setProperty("proxySet", "true");
		props.setProperty("http.proxyHost", host);
		props.setProperty("http.proxyPort", port);
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, new String(password).toCharArray());
			}
		});
	}

	public static String returnXmlStringFromUrl(String urlPath) {
		StringBuilder sb = null;
		BufferedReader br = null;
		try {
			url = new URL(urlPath);
			URLConnection urlConn = url.openConnection();
			urlConn.connect();

			br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e) {

			}
		}
		return sb.toString();
	}

	/**
	 * Download a resource from url of web
	 * 
	 * @param urlPath
	 * @param filePath
	 */
	public static void downloadFileFromUrl(String urlPath, String filePath) {
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		try {
			url = new URL(urlPath);
			URLConnection urlConn = url.openConnection();
			urlConn.connect();

			byte[] buff = new byte[1024];
			bis = new BufferedInputStream(urlConn.getInputStream());
			fos = new FileOutputStream(new File(filePath));
			while ((bis.read(buff)) != -1) {
				fos.write(buff, 0, buff.length);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> parseXmlString(String urlPath) {
		if (urlPath == null || "".equals(urlPath)) {
			throw new IllegalArgumentException("urlPath is not allowed null or \"\"");
		}

		List<Map<String, String>> list = new ArrayList<>();
		try {
			String str = returnXmlStringFromUrl(urlPath);
			if (StringUtils.isBlank(str)) {
				return list;
			}

			Document document = DocumentHelper.parseText(str);
			Element rootElement = document.getRootElement();

			List<Node> nodeList = (List<Node>) rootElement.selectNodes("channel");
			for (int i = 0; i < nodeList.size(); i++) {
				List<Node> nodeList1 = (List<Node>) nodeList.get(i).selectNodes("item");
				Map<String, String> map = null;
				for (int j = 0; j < nodeList1.size(); j++) {
					map = new HashMap<>();
					map.put("title", nodeList1.get(j).selectSingleNode("title").getText());
					map.put("link", nodeList1.get(j).selectSingleNode("link").getText());
					map.put("pubDate", nodeList1.get(j).selectSingleNode("pubDate").getText());
					list.add(map);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}

	// @Test
	public static void testMain() throws Exception {
		initAuth();
		System.out.println(parseXmlString("http://tech.qq.com/web/it/telerss.xml"));
	}

	// @Test
	public static void testDownloadFile() {
		downloadFileFromUrl("http://www.flash8.net/uploadflash/76/flash8net_75090.swf", "c:\\flash_demo.swf");
	}

	// Test main
	public static void main(String args[]) throws Exception {
		testMain();
		testDownloadFile();
	}
}
