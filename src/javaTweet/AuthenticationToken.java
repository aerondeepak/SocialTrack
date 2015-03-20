package javaTweet;

import java.io.*;
import java.util.Properties;

public class AuthenticationToken {
	
	String consumerKeyStr;
	String consumerSecretStr;
	String accessTokenStr;
	String accessTokenSecretStr;
	
	AuthenticationToken(){
		loadFromConfigFile();
	}
	
	public void loadFromConfigFile(){
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = this.getClass().getClassLoader().getResourceAsStream("config.properties");
	 
			// load a properties file
			prop.load(input);
			this.consumerKeyStr = prop.getProperty("consumerKeyStr");
			this.consumerSecretStr = prop.getProperty("consumerSecretStr");
			this.accessTokenStr = prop.getProperty("accessTokenStr");
			this.accessTokenSecretStr = prop.getProperty("accessTokenSecretStr");
			
	 
			// get the property value and print it out
			//System.out.println(prop.getProperty("consumerKeyStr"));
			//System.out.println(prop.getProperty("consumerSecretStr"));
			//System.out.println(prop.getProperty("accessTokenStr"));
			//System.out.println(prop.getProperty("accessTokenSecretStr"));
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getConsumerKeyStr() {
		return consumerKeyStr;
	}
	public void setConsumerKeyStr(String consumerKeyStr) {
		this.consumerKeyStr = consumerKeyStr;
	}
	public String getConsumerSecretStr() {
		return consumerSecretStr;
	}
	public void setConsumerSecretStr(String consumerSecretStr) {
		this.consumerSecretStr = consumerSecretStr;
	}
	public String getAccessTokenStr() {
		return accessTokenStr;
	}
	public void setAccessTokenStr(String accessTokenStr) {
		this.accessTokenStr = accessTokenStr;
	}
	public String getAccessTokenSecretStr() {
		return accessTokenSecretStr;
	}
	public void setAccessTokenSecretStr(String accessTokenSecretStr) {
		this.accessTokenSecretStr = accessTokenSecretStr;
	}
	
	public void loadKeyFromFile(String filePath){
		// load the key file that is downloaded and saved locally
		// parse the file and set the key strings using the setter methods. 
	}
	
	
	
	

}
