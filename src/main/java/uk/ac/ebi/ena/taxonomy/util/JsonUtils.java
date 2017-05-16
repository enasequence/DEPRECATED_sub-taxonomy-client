package uk.ac.ebi.ena.taxonomy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	 
	public boolean isJSONObjectValid(String taxonString)
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
			return null;
		}
	}
}
