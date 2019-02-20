package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * 
 * this is the response. use the file url in the response and get the image by
 * using it in localhost/magento2/pub/media/catalog/product...rest of the file url
 * 
 * Response: HttpResponseProxy{HTTP/1.1 200 OK [Date: Wed, 20 Feb 2019 17:22:12 GMT, Server: Apache/2.4.37 (Win32) OpenSSL/1.1.1a PHP/7.2.13, X-Powered-By: PHP/7.2.13, Set-Cookie: PHPSESSID=3dje7n1soodbercn1ufop25s57; expires=Wed, 20-Feb-2019 18:22:20 GMT; Max-Age=3600; path=/magento2; domain=localhost; HttpOnly, Expires: Thu, 19 Nov 1981 08:52:00 GMT, Cache-Control: no-store, no-cache, must-revalidate, Pragma: no-cache, X-Frame-Options: SAMEORIGIN, X-UA-Compatible: IE=edge, Content-Length: 348, Keep-Alive: timeout=5, max=100, Connection: Keep-Alive, Content-Type: application/xml; charset=utf-8] ResponseEntityProxy{[Content-Type: application/xml; charset=utf-8,Content-Length: 348,Chunked: false]}}
 * entity is not null
 * <?xml version="1.0"?>
 * <response>
 * 	<item>
 * 		<id>3392</id>
 * 		<media_type>image</media_type>
 * 		<label></label>
 * 		<position>1</position>
 * 		<disabled>false</disabled>
 * 		<types>
 * 			<item>image</item>
 * 			<item>small_image</item>
 * 			<item>thumbnail</item>
 * 		</types>
 * 		<file>/w/s/wsh11-red_main_1.jpg</file>
 * 	</item>
 * </response>
 * 
 **/

public class GetProductImage {
	public static void main(String[] args) {
		BufferedReader br = null;
		
		String token = "xq0o8r3bkuvlf66xv5pmpwp8jax9vvvv";
		String url = "http://localhost/magento2/rest/V1/products/WSH11-29-Red/media/?searchCriteria=";
		
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
