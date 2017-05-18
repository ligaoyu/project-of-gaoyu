package com.asset.model;

import java.util.Date;

import lombok.Data;

@Data
public class Base {

	private Integer id;
	
	private String name;
	
	private Date creation;
	
	private Date lastModified;
	
	private boolean removed;
	
}
