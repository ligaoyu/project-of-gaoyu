package com.asset.model;

import java.text.SimpleDateFormat;
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
	
	private String produceDateStr;
	
	private int level;
	
	private int parentId;
	
	private float price;
	
	private int ownerDept;
	
	private String model;
	
	private String brand;
	
	private String remark;
	
	private int type;
	
	private String dname;
	
	public String getProduceDate(){
		if(produceDate == null){
			return null;
		}
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		String str = sdf.format(produceDate);
		return str;
	}
	
}
