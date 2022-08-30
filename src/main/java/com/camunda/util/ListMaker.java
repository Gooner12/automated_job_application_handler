package com.camunda.util;

import java.util.Arrays;
import java.util.List;

public class ListMaker {
	private String key[];
	private String value[];
	
	public ListMaker(String key[], String value[]) {
		this.key = key;
		this.value = value;
	}
	
	public List<String> getKeyList() {
		// converting the array of string into list
		List<String> keys = Arrays.asList(key);
		return keys;
	}
	
	public List<String> getValueList() {
		// converting the array of string into list
		List<String> values = Arrays.asList(value);
		return values;
	}
}
