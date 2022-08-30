package com.camunda.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class MapBuilder {
	private List<String> keys;
	private List<String> values;
	
	public MapBuilder (List<String> keys, List<String> values) {
		this.keys = keys;
		this.values = values;
	}
	
	public LinkedHashMap<String, String> createMap() {
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		
		// creating an iterator for iterating over a list and populating the map
		Iterator<String> i1 = keys.iterator();
        Iterator<String> i2 = values.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            map.put(i1.next(), i2.next());
        }
        if (i1.hasNext() || i2.hasNext()) {
            throw new IllegalArgumentException ("Cannot combine lists with dissimilar sizes");
        }
        
        return map;
	}
}
