package uk.ac.ebi.ena.taxonomy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;

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
		try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())))
		{
			String currLine = "";
			StringBuilder str = new StringBuilder();
			while ((currLine = in.readLine()) != null)
			{
				str.append(currLine);
			}
			if (str != null && JsonUtils.isJSONArrayValid(str.toString()))
			{
				return new JSONArray(str.toString());
			}
			if (str != null && JsonUtils.isJSONObjectValid(str.toString()))
			{
				JSONArray jsonarrayObj= new JSONArray();
				jsonarrayObj.put(new JSONObject(str.toString()));
				return jsonarrayObj;
			}
			return null;
		}
	}
	
	public static boolean checkServerError(URL url)
	{
		try{
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.connect();
		if (con.getResponseCode() >= 500)
		{
			throw new TaxonomyException(new BufferedReader(
					new InputStreamReader(con.getErrorStream())).lines()
					.collect(Collectors.joining("\n")));
		}
		return false;
		}catch(Exception e)
		{
			throw new TaxonomyException(e.getMessage()+"("+url.toString()+")");
		}

	}

	public static boolean checkClientError(URL url) 
	{
		try
		{
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.connect();
		if (con.getResponseCode() >= 400 && con.getResponseCode() < 500)
		{
			return true;
		}
		return false;
		}catch(Exception e)
		{
			throw new TaxonomyException(e.getMessage());
		}

	}
}
