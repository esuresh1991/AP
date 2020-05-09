package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.Testutil;

public class GetAPITest extends TestBase{

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String URL;
	RestClient restclient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod
	public void setUp(){
		testbase  = new TestBase();
		serviceurl = P.getProperty("URL");
		apiurl = P.getProperty("ServiceURL");
		
		URL = serviceurl + apiurl;
		
		
	}
	
	@Test
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		closeablehttpresponse = restclient.get(URL);
				
		//Status Code
		int statuscode  = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code = " + statuscode);
		
		Assert.assertEquals(statuscode , RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//Json String
		String response = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		
		JSONObject responseJSON = new JSONObject(response);
		System.out.println("Response JSON ->"+responseJSON);
		
		//single value assertion:
				//per_page: - JOSN Object
				String perPageValue = Testutil.getValueByJPath(responseJSON, "/per_page");
				System.out.println("value of per page is-->"+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);		
				
				//total:
				String totalValue = Testutil.getValueByJPath(responseJSON, "/total");
				System.out.println("value of total is-->"+ totalValue);		
				Assert.assertEquals(Integer.parseInt(totalValue), 12);	
				
				//get the value from JSON ARRAY:
				String lastName = Testutil.getValueByJPath(responseJSON, "/data[0]/last_name");
				String id = Testutil.getValueByJPath(responseJSON, "/data[0]/id");
				String avatar = Testutil.getValueByJPath(responseJSON, "/data[0]/avatar");
				String firstName = Testutil.getValueByJPath(responseJSON, "/data[0]/first_name");

				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
		
		//All Headers
		Header[] headersArray =  closeablehttpresponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());	
		}
		System.out.println("Header Array -> " + allHeaders);
		}
	
	
	@Test
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test213");
//		headerMap.put("Auth Token", "12345");
		
		closeablehttpresponse = restclient.get(URL,headerMap);
				
		//Status Code
		int statuscode  = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code = " + statuscode);
		
		Assert.assertEquals(statuscode , RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//Json String
		String response = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		
		JSONObject responseJSON = new JSONObject(response);
		System.out.println("Response JSON ->"+responseJSON);
		
		//single value assertion:
				//per_page: - JOSN Object
				String perPageValue = Testutil.getValueByJPath(responseJSON, "/per_page");
				System.out.println("value of per page is-->"+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);		
				
				//total:
				String totalValue = Testutil.getValueByJPath(responseJSON, "/total");
				System.out.println("value of total is-->"+ totalValue);		
				Assert.assertEquals(Integer.parseInt(totalValue), 12);	
				
				//get the value from JSON ARRAY:
				String lastName = Testutil.getValueByJPath(responseJSON, "/data[0]/last_name");
				String id = Testutil.getValueByJPath(responseJSON, "/data[0]/id");
				String avatar = Testutil.getValueByJPath(responseJSON, "/data[0]/avatar");
				String firstName = Testutil.getValueByJPath(responseJSON, "/data[0]/first_name");

				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
		
		//All Headers
		Header[] headersArray =  closeablehttpresponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());	
		}
		System.out.println("Header Array -> " + allHeaders);
		}
	 
	
}
