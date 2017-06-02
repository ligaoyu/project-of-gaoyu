<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <#assign path="${request.getContextPath()}">
  <title>中软睿达</title>
  <link rel="Shortcut Icon" href="${path}/img/tubiao.png" />
  <!-- 引入样式 -->
  <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-default/index.css">
  <link rel="stylesheet" href="${path}/css/main.css">
</head>
<body>

<div id="app">
	
	<!-- 横向标题 -->
	<el-row class="topdiv">
		<el-col :span="3">
			<div class="grid-content bg-purple-dark">
				<img src="${path}/img/tubiao.png" class="img" ></img>
				<font>中软睿达</font>
			</div>
		</el-col>
		<el-col :span="11">
			<div class="grid-content bg-purple-dark serch_padding">
				<el-input class="serchInput" placeholder="请输入内容" icon="search"  v-model="input2" :on-icon-click="handleIconClick"></el-input>
			</div>
		</el-col>
		<el-col :span="8">
			<div class="grid-content bg-purple-dark">
			</div>
		</el-col>
		<el-col :span="2">
			<div class="grid-content bg-purple-dark">
				<el-button class="exit_button" ><img src="${path}/img/exit.png" class="exit"></img></el-button>
			</div>
		</el-col>
	</el-row>
	
	<!-- 纵向菜单 -->
	<el-row class="tac full">
		<el-col :span="3" class="fullHeight">
			<el-menu theme="dark" default-active="1" class="el-menu-vertical-demo full" @select="handleSelect" @open="handleOpen" @close="handleClose" >
				<el-menu-item index="1">DashBoard</el-menu-item>
				<el-menu-item index="2">设备列表</el-menu-item>
				<el-submenu index="3">
					<template slot="title">设备管理</template>
					<el-menu-item index="3-1">新增设备</el-menu-item>
				</el-submenu>
			</el-menu>
		</el-col>
		<!-- 柱形图 -->
		<el-col :span="19" :offset="4">
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<el-card class="box-card row_bottom">
			  <div id="bar_chart" class="chart"></div>
			</el-card>
			
			<el-row>
				<el-col :span="11">
					<el-card class="box-card row_bottom box-cart-width">
						<div slot="header" class="clearfix">
							<span style="line-height: 36px;">各类资产使用率</span>
						</div>
						<template v-for="item in progress">
							<span>{{item.name}}</span><el-progress :text-inside="true" :stroke-width="18" :percentage="item.percent*100"></el-progress>
						</template>
						
						
					</el-card>
				</el-col>
				<el-col :span="11" :offset="2">
					<el-card class="box-card row_bottom box-cart-width">
					  <div id="pie_chart" class="chart"></div>
					</el-card>
				</el-col>
			</el-row>
		</el-col>
	</el-row>
	
	
	
</div>

  <!-- 先引入 Vue -->
  <script src="${path}/js/vue.js"></script>
  <!-- 引入组件库 -->
  <script src="https://unpkg.com/element-ui/lib/index.js"></script>
  <!-- 引入 echarts.js -->
  <script src="${path}/js/echarts.min.js"></script>
  <script src="${path}/js/jquery.min.js"></script>
  
<script>
	var app = new Vue({
		el:"#app",
		data:{
			input2:'',
			progress:${progress }
		},
		methods: {
			handleSelect(key, keyPath){
				if(key === "1"){
					window.location.href="${path}/asset/main/"; 
				}else if(key === "2"){
					window.location.href="${path}/asset/list/"; 
				}else if(key === "3-1"){
					window.location.href="${path}/asset/add/"; 
				}
			},
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			handleIconClick(ev) {
				console.log(11111);
      			window.location.href="${path}/asset/find/?value="+this.input2; 
    		}
	    }
	});
	
		// 基于准备好的dom，初始化echarts实例
        var barChart = echarts.init(document.getElementById('bar_chart'));

		option = {
    		tooltip : {
        		trigger: 'axis',
        		axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            	type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        	}
	    	},
	    	legend: {
	        	data: ${legend }
	   	 	},
	    	grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis:  {
		        type: 'category',
		        data: ${xAxis }
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: ${series }
		};
		
	// 使用刚指定的配置项和数据显示图表。
	barChart.setOption(option);

	// 基于准备好的dom，初始化echarts实例
    var pieChart = echarts.init(document.getElementById('pie_chart'));
	
	console.log(${arr });
	
	pieOption = {
    title : {
        text: '固定资产类别占比',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ${arr }
    },
    series : [
        {
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:${pieData }
        }
    ]
	};
	
	// 使用刚指定的配置项和数据显示图表。
	pieChart.setOption(pieOption);

</script>
</body>
</html>