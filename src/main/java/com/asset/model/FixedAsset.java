package com.asset.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FixedAsset extends Base {

	private String assetNo;
	
	private String location;
	
}
