<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <#assign path="${request.getContextPath()}">
  <!-- 引入样式 -->
  <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-default/index.css">
  <link rel="stylesheet" href="${path}/css/main.css">
</head>
<body>

<div id="app">
	
	<!-- 横向标题 -->
	<el-row>
		<el-col :span="3">
			<div class="grid-content bg-purple-light">
				<img src="${path}/img/tubiao.png" class="img" ></img>
				<font>中软睿达</font>
			</div>
		</el-col>
		<el-col :span="11">
			<div class="grid-content bg-purple-light serch_padding">
				<el-input class="serchInput" placeholder="请输入内容" icon="search"  v-model="input2" :on-icon-click="handleIconClick"></el-input>
			</div>
		</el-col>
		<el-col :span="8">
			<div class="grid-content bg-purple-light">
			</div>
		</el-col>
		<el-col :span="2">
			<div class="grid-content bg-purple-light">
				<el-button class="exit_button" type="warning" ><img src="${path}/img/exit.png" class="exit"></img></el-button>
			</div>
		</el-col>
	</el-row>
	
	<!-- 纵向菜单 -->
	<el-row class="tac full">
		<el-col :span="3" class="fullHeight">
			<el-menu default-active="2" class="el-menu-vertical-demo full" @open="handleOpen" @close="handleClose" >
				<el-submenu index="1">
					<template slot="title">导航一</template>
					<el-menu-item-group title="分组一">
						<el-menu-item index="1-1">选项1</el-menu-item>
						<el-menu-item index="1-2">选项2</el-menu-item>
					</el-menu-item-group>
					<el-menu-item-group title="分组2">
						<el-menu-item index="1-3">选项3</el-menu-item>
					</el-menu-item-group>
		        <el-submenu index="1-4">
					<template slot="title">选项4</template>
						<el-menu-item index="1-4-1">选项1</el-menu-item>
					</el-submenu>
				</el-submenu>
				<el-menu-item index="2">导航二</el-menu-item>
				<el-menu-item index="3">导航三</el-menu-item>
			</el-menu>
		</el-col>
	</el-row>
	
	<!-- 柱形图 -->
	<el-row class="row_bottom">
		<el-col :span="19" :offset="4">
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<div id="bar_chart" class="chart"></div>
		</el-col>
	</el-row>
	
	<el-row :gutter="20" class="row_bottom">
		<el-col :span="9" :offset="5">
			设备1<el-progress :percentage="0"></el-progress>
			设备1<el-progress :percentage="20"></el-progress>
			设备1<el-progress :percentage="40"></el-progress>
			设备1<el-progress :percentage="70"></el-progress>
			设备1<el-progress :percentage="90"></el-progress>
		</el-col>
		<el-col :span="9" >
			<div id="pie_chart" class="chart"></div>
		</el-col>
	</el-row>
	
</div>

  <!-- 先引入 Vue -->
  <script src="https://unpkg.com/vue/dist/vue.js"></script>
  <!-- 引入组件库 -->
  <script src="https://unpkg.com/element-ui/lib/index.js"></script>
  <!-- 引入 echarts.js -->
  <script src="${path}/js/echarts.min.js"></script>
  
<script>
	var app = new Vue({
		el:"#app",
		data:{
			input2:''
		},
		methods: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			handleIconClick(ev) {
      			console.log(ev);
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
        	data: ['直接访问', '邮件营销','联盟广告','视频广告','搜索引擎']
   	 	},
    	grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis:  {
	        type: 'category',
	        data: ['周一','周二','周三','周四','周五','周六','周日']
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [
	        {
	            name: '直接访问',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data: [320, 302, 301, 334, 390, 330, 320]
	        },
	        {
	            name: '邮件营销',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data: [120, 132, 101, 134, 90, 230, 210]
	        },
	        {
	            name: '联盟广告',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data: [220, 182, 191, 234, 290, 330, 310]
	        },
	        {
	            name: '视频广告',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data: [150, 212, 201, 154, 190, 330, 410]
	        },
	        {
	            name: '搜索引擎',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data: [820, 832, 901, 934, 1290, 1330, 1320]
	        }
    	]
	};
	// 使用刚指定的配置项和数据显示图表。
	barChart.setOption(option);

	// 基于准备好的dom，初始化echarts实例
    var pieChart = echarts.init(document.getElementById('pie_chart'));
	
	pieOption = {
    title : {
        text: '某站点用户访问来源',
        subtext: '纯属虚构',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
	};
	
	// 使用刚指定的配置项和数据显示图表。
	pieChart.setOption(pieOption);

</script>
</body>
</html>