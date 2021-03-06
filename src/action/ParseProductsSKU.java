package action;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * the http get request asks for all the products in the databse and saves each node 
 * instance of sku in a NodeList 
 * 
**/

public class ParseProductsSKU {
	
	public static void main(String[] args) {
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
		
		LinkedHashSet<String> lhs = null;
		try {
			
			HttpResponse response = client.execute(request);
			
			//xml DOM document parser 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        
	        Document doc = dBuilder.parse(response.getEntity().getContent());
	        
	        NodeList skuList = doc.getElementsByTagName("sku");
	        
	        lhs = new LinkedHashSet<String>();
	        
	        for(int i = 0; i < skuList.getLength(); i++) {
	        	String sku = skuList.item(i).getTextContent();
	        	lhs.add(sku);
	        }
	        
	        //change to array to iterator it
	        String[] lhsArray = (String[]) lhs.toArray();
	        for(int i = 0; i < lhsArray.length; i++) {
	        	System.out.println(lhsArray[i]);
	        }
	        
	        
	        //make an iterator and used it to iterate it through the LinkedHashSet
	        Iterator<String> skuIterator = lhs.iterator();
	        
	        while(skuIterator.hasNext()) {
	        	System.out.println(skuIterator.next());
	        }
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
}
