/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava.http;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPingTest {
	// public static void main(String[] args) {
	// URL url;
	// try {
	// url = new URL("<a href=\"http://www.baidu.com\"
	// target=_blank>http://www.baidu.com</a>");
	// InputStream in = url.openStream();
	// System.out.println("连接可用");
	// } catch (Exception e1) {
	// System.out.println("连接打不开!");
	// url = null;
	// }
	// }

	// public static void main(String[] args) {
	// URLAvailability u=new URLAvailability();
	//// u.isConnect("<a href=\"http://www.baidu.com\"
	// target=_blank>http://www.baidu.com</a>");
	// u.isConnect("mail.163.com");
	// }

	public static void main(String[] args) {

		System.out.println(exists("http://www.baidu.com"));

		System.out.println(exists("http://www.baidu.com/XXXXX.html"));

		System.out.println(exists("http://192.168.230.117:6990/notify/"));

	}

	static boolean exists(String URLName) {
		try {
			// 设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。
			HttpURLConnection.setFollowRedirects(false);

			// 到 URL 所引用的远程对象的连接
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();

			/*
			 * 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE
			 * 以上方法之一是合法的，具体取决于协议的限制。
			 */
			con.setRequestMethod("HEAD");
			con.setAllowUserInteraction(false);
			// con.setRequestMethod("TRACE");

			// 从 HTTP 响应消息获取状态码
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}

class URLAvailability {

	private static URL url;
	private static HttpURLConnection con;
	private static int state = -1;

	/**
	 * 功能：检测当前URL是否可连接或是否有效, 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
	 * 
	 * @param urlStr
	 *            指定URL网络地址
	 * @return URL
	 */
	public synchronized URL isConnect(String urlStr) {
		int counts = 0;
		if (urlStr == null || urlStr.length() <= 0) {
			return null;
		}
		while (counts < 5) {
			try {
				url = new URL(urlStr);
				con = (HttpURLConnection) url.openConnection();
				state = con.getResponseCode();
				System.out.println(counts + "= " + state);
				if (state == 200) {
					System.out.println("URL可用！");
				}
				break;
			} catch (Exception ex) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				counts++;
				System.out.println("URL不可用，连接第 " + counts + " 次");
				urlStr = null;
				continue;
			}
		}
		return url;
	}

}