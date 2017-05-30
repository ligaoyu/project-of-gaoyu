package com.asset.vo;

import lombok.Data;

@Data
public class SelAssetVo {

	private String name;
	
	private String assetNo;
	
	private String parent;
	
	private String type;
	
	public SelAssetVo(String name,String assetNo,String parent,String type){
		this.name = name;
		this.assetNo = assetNo;
		this.parent = parent;
		this.type = type;
	}

}
