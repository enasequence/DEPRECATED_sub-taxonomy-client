package uk.ac.ebi.ena.taxonomy.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class JsonUtils
{

	public static boolean isJSONArrayValid(String taxonString)
	{
		try
		{
			new JSONArray(taxonString);
		} catch (JSONException ex)
		{
			return false;
		}
		return true;
	}
	 
	public static boolean isJSONObjectValid(String taxonString)
	{
		try
		{
			new JSONObject(taxonString);
		} catch (JSONException ex)
		{
			return false;
		}
		return true;
	}
	 
	public JSONArray getJsonArray(URL url) throws IOException, JSONException
	{
		if(getResponseStream(url) == null) return null;
		try (InputStream is = getResponseStream(url); BufferedReader in = new BufferedReader(new InputStreamReader(is)))
		{
			String currLine = "";
			StringBuilder str = new StringBuilder();
			while ((currLine = in.readLine()) != null)
			{
				str.append(currLine);
			}
			if (JsonUtils.isJSONArrayValid(str.toString()))
			{
				return new JSONArray(str.toString());
			}
			if (JsonUtils.isJSONObjectValid(str.toString()))
			{
				JSONArray jsonarrayObj= new JSONArray();
				jsonarrayObj.put(new JSONObject(str.toString()));
				return jsonarrayObj;
			}
			return null;
		}
	}

	private InputStream getResponseStream(URL url){
		HttpURLConnection connection ;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			if(code == HTTP_OK ){
				return connection.getInputStream();
			} else if(code == HTTP_NOT_FOUND ){
				return null;
			}
			else {
				throw new TaxonomyException("Invalid HTTP response code: " + code);
			}
		} catch (Exception e){
			throw  new TaxonomyException("Error connecting to url:" + url.toString(), e);
		}
	}


}
