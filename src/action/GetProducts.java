package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;

/**
 * makes an http GET request to a locl magento web store through their web api
 * using an access token and reads it to the console.
 * 
**/
public class GetProducts {
	
	public static void main(String[] args) {
		BufferedReader br = null;
		
		String token = "xq0o8r3bkuvlf66xv5pmpwp8jax9vvvv";
		String url = "http://localhost/magento2/rest/V1/products/?searchCriteria=";
		
		//create the client to execute the request
		HttpClient client = HttpClientBuilder.create().build();
		
		
		//creates the request with HTTP request method for the target url
		HttpGet request = new HttpGet(url);
		
		//add to the head of the request using the token and content type you want back
		request.addHeader("Authorization", "Bearer " + token);
		request.addHeader("Accept", "application/xml");
		request.addHeader("Content-Type", "application/xml");
		
		try {
			//Gets the response from the call
			HttpResponse response = client.execute(request);
			
			System.out.println("Response: " + response);
			
			HttpEntity entity = response.getEntity();
			
			//using EntityUtils to read the xml file
			/*System.out.println("Entity: " + entity + "\n");
			String content = EntityUtils.toString(entity);
			System.out.println(content);*/
			
			//checks if entity is not null
			if(entity != null) {
				System.out.println("entity is not null");
				
				//using bufferedReader to read the response
				br = new BufferedReader(new InputStreamReader(entity.getContent()));
				
				String line = "";
				
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
				
			} else {
				System.out.println("entity is null");
			}
			
		} catch (IOException e) {
			System.out.println("couldnt execute request");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
