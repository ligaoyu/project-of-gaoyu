package com.asset.vo;

import lombok.Data;

@Data
public class BarChart {

	private String[] xAxis;
	
	private String[] legend;
	
	private Series[] series;
	
}
