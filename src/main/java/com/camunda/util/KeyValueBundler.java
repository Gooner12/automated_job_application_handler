package com.camunda.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class KeyValueBundler {
	private String values[];
	
	public KeyValueBundler(String values[]) {
		this.values = values;
	}
	
	public LinkedHashMap<String, String> bundler() {
		List<String> value = Arrays.asList(values);
		int size = value.size();
		
		// generating a list of integers to represent keys
		List<String> key = new ArrayList<>();
		
		for (int i=1; i<= size; i++) {
			key.add(Integer.toString(i)); // converting to string as the hash map is composed of keys with string datatype
		}
		
		// getting a map from the lists of keys and values
        MapBuilder builder = new MapBuilder(key, value);
        LinkedHashMap<String, String> hash_map = builder.createMap();
        
        return hash_map;
	}

}
