package javaTweet;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


public class JavaRestTweet {
	
	static String consumerKeyStr;
	static String consumerSecretStr;
	static String accessTokenStr;
	static String accessTokenSecretStr;

	public static void main(String[] args) throws Exception {
		
		AuthenticationToken token = new AuthenticationToken();
		consumerKeyStr = token.getConsumerKeyStr();
		consumerSecretStr = token.getConsumerSecretStr();
		accessTokenStr = token.getAccessTokenStr();
		accessTokenSecretStr = token.getAccessTokenSecretStr();
		
		
		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr,
				consumerSecretStr);
		oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);

		HttpPost httpPost = new HttpPost(
				"https://api.twitter.com/1.1/statuses/update.json?status=welcome");

		oAuthConsumer.sign(httpPost);

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpPost);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode + ':'
				+ httpResponse.getStatusLine().getReasonPhrase());
		System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));

	}
}

