package org.givenkind.validation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
 
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class captchaValidator{
	public static final String url = "https://www.google.com/recaptcha/api/siteverify";

	public static final String secretKey = "6Leh3BATAAAAAEK79i63S38y3mKK53llXzbX1mQP";

	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static boolean verify(String captchaResponse) throws IOException{
		if(captchaResponse.equals("") || captchaResponse == null){
			return false;
		}
		try{
			//Opening HTTPS connection
			URL urlForTest = new URL(url);
			HttpsURLConnection connection = (HttpsURLConnection) urlForTest.openConnection();
			
			//Adding request header
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String postParams = "secret=" + secretKey + "&response=" + captchaResponse;
			
	        //Send post request
	        connection.setDoOutput(true);
	        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
	        writer.writeBytes(postParams);
	        writer.flush();
	        writer.close();
			
	        
	        //Getting JSON response
	        int responseCode = connection.getResponseCode();
	        System.out.println("\nSending 'POST' request to URL : " + url);
	        System.out.println("Post parameters : " + postParams);
	        System.out.println("Response Code : " + responseCode);
	 
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        
	        // print result
	        System.out.println(response.toString());
	         
	        //parse JSON response and return 'success' value
	        JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
	        JsonObject jsonObject = jsonReader.readObject();
	        jsonReader.close();
	         
	        return jsonObject.getBoolean("success");
	        
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
