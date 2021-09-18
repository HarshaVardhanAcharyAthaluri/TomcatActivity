package com.harshalabs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

class TomcatActivity {

	public static void main(String[] args) {
		
		 URLConnection connection = null;
	        BufferedReader rd = null;
	        StringBuilder sb = null;
	        String line = null;
	 
	        URL serverAddress = null;
	        try
	        {
	            serverAddress = new URL("http://localhost:8080/manager/text/"+args[1]+"?path=/"+args[0]);
	 
	            //Set up the initial connection
	            connection=serverAddress.openConnection();
	            String usepass="admin"+":"+"admin";
	            String basicAuth="Basic "+new String(Base64.getEncoder().encode(usepass.getBytes()));
	            
	           connection.setRequestProperty("Authorization", basicAuth);
	           connection.getContent();
	            connection.connect();
	 
	            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            sb = new StringBuilder();
	 
	            while ((line = rd.readLine()) != null)
	            {
	                sb.append(line + '\n');
	            }
	            System.out.println(sb.toString());
	        }
	        catch (MalformedURLException e)
	        {
	            System.out.println("MalformedURLException: " + e);
	        }
	        catch (ProtocolException e)
	        {
	            System.out.println("ProtocolException: " + e);
	        }
	        catch (IOException e)
	        {
	            System.out.println("IOException: " + e);
	        }
	        finally
	        {
	            
	            rd = null;
	            sb = null;
	            connection = null;
	        }
	}

}
