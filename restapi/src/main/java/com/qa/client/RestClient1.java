package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient1 {

//GET method	
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient =  HttpClients.createDefault(); //Create http client
		HttpGet httpget = new HttpGet(url);  //Http get request
		CloseableHttpResponse closeablehttpresponse =  httpclient.execute(httpget); //hit the GET URL. And it will return Closeable Http response
		
		//Status Code
		int statuscode  = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code = " + statuscode);
		
		//Json String
		String response = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		
		JSONObject responseJSON = new JSONObject(response);
		System.out.println("Response JSON ->"+responseJSON);
		
		//All Headers
		Header[] headersArray =  closeablehttpresponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
			
		}
		
		System.out.println("Header Array -> " + allHeaders);
	}
	
}
