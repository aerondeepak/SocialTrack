package javaTweet;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

/**
 * This class is the same as the ApacheHttpRestClient2 class, but with
 * fewer try/catch clauses, and fewer comments.
*/
public class ApacheHttpRestClient {
	
	
	
	static String consumerKeyStr;
	static String consumerSecretStr;
	static String accessTokenStr;
	static String accessTokenSecretStr;
 
  public final static void main(String[] args) {
	  
		AuthenticationToken token = new AuthenticationToken();
		
		consumerKeyStr = token.getConsumerKeyStr();
		consumerSecretStr = token.getConsumerSecretStr();
		accessTokenStr = token.getAccessTokenStr();
		accessTokenSecretStr = token.getAccessTokenSecretStr();
     
    HttpClient httpClient = new DefaultHttpClient();
    try {
   //   HttpGet httpGetRequest = new HttpGet("http://search.twitter.com/search.json?q=%40apple");
    	
    	OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr,
				consumerSecretStr);
		oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
    	
    	HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q=%23superbowl&result_type=recent");
    	oAuthConsumer.sign(httpGetRequest);
    	
      HttpResponse httpResponse = httpClient.execute(httpGetRequest);
 
      System.out.println("----------------------------------------");
      System.out.println(httpResponse.getStatusLine());
      System.out.println("----------------------------------------");
 
      HttpEntity entity = httpResponse.getEntity();
 
      byte[] buffer = new byte[1024];
      if (entity != null) {
        InputStream inputStream = entity.getContent();
        try {
          int bytesRead = 0;
          BufferedInputStream bis = new BufferedInputStream(inputStream);
          while ((bytesRead = bis.read(buffer)) != -1) {
            String chunk = new String(buffer, 0, bytesRead);
            System.out.println(chunk);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try { inputStream.close(); } catch (Exception ignore) {}
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpClient.getConnectionManager().shutdown();
    }
  }
}
