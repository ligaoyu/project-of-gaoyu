package com.asset.model;

import java.util.Date;

import lombok.Data;

@Data
public class Asset {

	private int id;
	
	private String name;
	
	private Date creation;
	
	private long lastModified;
	
	private boolean removed;
	
	private String assetNo;
	
	private Date produceDate;
	
	private int level;
	
	private int parentId;
	
	private float price;
	
	private int ownerDept;
	
	private String model;
	
	private String brand;
	
	private String remark;
}
