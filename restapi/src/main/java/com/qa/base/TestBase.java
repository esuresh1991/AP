package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;

	public static Properties P;
	
	public TestBase()
	{
try {
			
			P = new Properties();
			FileInputStream F = new FileInputStream("C:\\Users\\Suresh\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
			P.load(F);
			System.out.println(P.getProperty("URL"));
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
