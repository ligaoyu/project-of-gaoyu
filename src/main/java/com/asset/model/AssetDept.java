package com.asset.model;

import java.util.Date;

import lombok.Data;

@Data
public class AssetDept {
	
	private int id;
	
	private Date creation;
	
	private int assetId;
	
	private int deptId;
	
	private String location;
	
	private String remark;

}
