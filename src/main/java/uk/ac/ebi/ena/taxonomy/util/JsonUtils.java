/*
 * Copyright 2018 EMBL - European Bioinformatics Institute
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

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
