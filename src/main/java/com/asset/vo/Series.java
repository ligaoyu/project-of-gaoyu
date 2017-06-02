package com.asset.vo;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Series {

	private String name;
	
	private final String type = "bar";
	
	private final String stack = "总量";
	
	private Map<String,Map<String,String>> label = new HashMap<String,Map<String,String>>();
	
	private int[] data;
	
	public Series(){
		Map<String,String> normal = new HashMap<String,String>();
//		normal.put("show", "true");
//		normal.put("position", "inside");
		label.put("normal", normal);
	}
	
}
