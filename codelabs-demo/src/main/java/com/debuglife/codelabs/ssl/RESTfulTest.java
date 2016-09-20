package com.debuglife.codelabs.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class RESTfulTest {

    public static void main(String[] args) throws Exception {
	String url = "https://localhost:8443/p4m/rest/testpatient";
	String body = send(url, null, "utf-8");
	System.out.println("body's length：" + body.length());
	System.out.println("body：" + body);
	System.out.println("-----------------------------------");
	url = "https://kyfw.12306.cn/otn/";
	body = send(url, null, "utf-8");
	System.out.println("body's length：" + body.length());
	System.out.println("body：" + body);

	// ssl();
    }

    public static CloseableHttpClient createSSLClientDefault() {
	try {
	    KeyStore ks = KeyStore.getInstance("JKS");
	    char[] password = "changeit".toCharArray();

	    java.io.FileInputStream fis = null;
	    fis = new java.io.FileInputStream(new File("C:\\Users\\roger.zhang\\Desktop\\coc.client.jks"));
	    ks.load(fis, password);

	    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
		    ks, new TrustStrategy() {
			// trust all
			@Override
			public boolean isTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws java.security.cert.CertificateException {
			    return true;
			}
		    }).build();

	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
	    return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return HttpClients.createDefault();
    }

    /**
     * bypass verify
     * 
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws Exception {
	SSLContext sc = SSLContext.getInstance("TLSv1");

	// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
	X509TrustManager trustManager = new X509TrustManager() {
	    @Override
	    public void checkClientTrusted(
		    java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
	    }

	    @Override
	    public void checkServerTrusted(
		    java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
	    }

	    @Override
	    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return null;
	    }
	};

	sc.init(null, new TrustManager[] { trustManager }, null);
	return sc;
    }

    /**
     * set to trust self-signed certificate
     * 
     * @param keyStorePath
     *            keyStorePath
     * @param keyStorepass
     *            keyStorePassword
     * @return
     */
    public static SSLContext createVerifySSL(String keyStorePath, String keyStorepass) {
	SSLContext sc = null;
	FileInputStream instream = null;
	KeyStore trustStore = null;
	try {
	    trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    instream = new FileInputStream(new File(keyStorePath));
	    trustStore.load(instream, keyStorepass.toCharArray());
	    // 相信自己的CA和所有自签名的证书
	    sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		instream.close();
	    } catch (IOException e) {
	    }
	}
	return sc;
    }

    /**
     * simulate to request.
     * 
     * @param url
     *            resources url
     * @param map
     *            parameter list
     * @param encoding
     *            encoding
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ClientProtocolException
     */
    /**/
    public static String send(String url, Map<String, String> map, String encoding) throws Exception {
	String body = "";
	// 采用绕过验证的方式处理https请求
	// SSLContext sslcontext = createIgnoreVerifySSL();

	// 采用自签名证书验证方式处理https请求
	String keyStorePath = "C:\\Users\\roger.zhang\\Desktop\\coc.client.jks";
	String keyStorePassword = "changeit";
	SSLContext sslcontext = createVerifySSL(keyStorePath, keyStorePassword);

	// 设置协议http和https对应的处理socket链接工厂的对象
	Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
		.<ConnectionSocketFactory> create()
		.register("http", PlainConnectionSocketFactory.INSTANCE)
		.register("https", new SSLConnectionSocketFactory(sslcontext))
		.build();
	PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
	HttpClients.custom().setConnectionManager(connManager);

	// 创建自定义的httpclient对象
	CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
	// CloseableHttpClient client = HttpClients.createDefault();

	// 创建post方式请求对象
	// HttpPost httpPost = new HttpPost(url);
	HttpGet httpPost = new HttpGet(url);

	// 装填参数
	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	if (map != null) {
	    for (Map.Entry<String, String> entry : map.entrySet()) {
		nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
	    }
	}
	// 设置参数到请求对象中
	// httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

	System.out.println("请求地址：" + url);
	System.out.println("请求参数：" + nvps.toString());

	// 设置header信息
	// 指定报文头【Content-type】、【User-Agent】
	httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
	// httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

	String username = "medavisadmin";
	String password = "PeterPan";
	String authorization = "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes());
	httpPost.setHeader("Authorization", authorization);

	// 执行请求操作，并拿到结果（同步阻塞）
	CloseableHttpResponse response = client.execute(httpPost);
	// 获取结果实体
	HttpEntity entity = response.getEntity();
	if (entity != null) {
	    // 按指定编码转换结果实体为String类型
	    body = EntityUtils.toString(entity, encoding);
	}
	EntityUtils.consume(entity);
	// 释放链接
	response.close();
	return body;
    }

    /**
     * HttpClient连接SSL
     */
    public static void ssl() {
	CloseableHttpClient httpclient = null;
	try {
	    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    FileInputStream instream = new FileInputStream(new File("C:\\Users\\roger.zhang\\Desktop\\coc.client.jks"));
	    String keyStorePassword = "changeit";
	    try {
		// load keyStore
		trustStore.load(instream, keyStorePassword.toCharArray());
	    } catch (CertificateException e) {
		e.printStackTrace();
	    } finally {
		try {
		    instream.close();
		} catch (Exception ignore) {
		}
	    }

	    // trust all ca and self-signed certificates
	    SSLContext sslcontext = SSLContexts
		    .custom()
		    .loadTrustMaterial(trustStore,new TrustSelfSignedStrategy())
		    .build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		    sslcontext, 
		    new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" },
		    null,
		    SSLConnectionSocketFactory.getDefaultHostnameVerifier());

	    httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	    HttpGet http = new HttpGet("https://localhost:8443/p4m/rest/testpatient");
	    // HttpPost http = new HttpPost("https://localhost:8443/p4m/rest/chart-service/chart/single-number/hello");

	    // fill parameters
	    Map<String, String> map = new HashMap<>();
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    if (map != null) {
		for (Entry<String, String> entry : map.entrySet()) {
		    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
	    }

	    // 设置参数到请求对象中
	    // http.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

	    // set up header info
	    // http.setHeader("Content-type", "application/x-www-form-urlencoded");
	    // http.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	    String username = "medavisadmin";
	    String password = "PeterPan";
	    String authorization = "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes());
	    http.setHeader("Authorization", authorization);

	    System.out.println("executing request " + http.getRequestLine());
	    CloseableHttpResponse response = httpclient.execute(http);
	    try {
		HttpEntity entity = response.getEntity();
		System.out.println(response.getStatusLine());
		if (entity != null) {
		    System.out.println("Response content length: " + entity.getContentLength());
		    System.out.println(EntityUtils.toString(entity));
		    EntityUtils.consume(entity);
		}
	    } finally {
		response.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (httpclient != null) {
		try {
		    httpclient.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
