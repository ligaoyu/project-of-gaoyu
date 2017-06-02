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

<div id="app" >
	
	<!-- 横向标题 -->
	<el-row  class="topdiv">
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
	<el-row class="tac full" >
		<el-col :span="3" class="fullHeight">
			<el-menu theme="dark" default-active="3-1" class="el-menu-vertical-demo full" @select="handleSelect" @open="handleOpen" @close="handleClose" >
				<el-menu-item index="1">DashBoard</el-menu-item>
				<el-menu-item index="2">设备列表</el-menu-item>
				<el-submenu index="3">
					<template slot="title">设备管理</template>
					<el-menu-item index="3-1">新增设备</el-menu-item>
				</el-submenu>
			</el-menu>
		</el-col>
		
		<el-col :span="19" :offset="4">
			<el-card class="box-card row_bottom">
				<el-form ref="form" :model="form" label-width="80px" v-loading.body="loading" class="demo-ruleForm">
					<el-form-item label="编号" >
						<el-input v-model="form.assetNo" name="assetNo" placeholder="请输入设备编号"></el-input>
					</el-form-item>
					<el-form-item label="名称" >
					    <el-input v-model="form.name" name="name" placeholder="请输入设备名称"></el-input>
					</el-form-item>
					<el-form-item label="设备类型" >
						<el-select v-model="form.parentId" clearable name="parent" placeholder="请选择设备类型">
							<el-option	v-for="item in parentIds"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="生产日期" >
						<el-date-picker type="date" placeholder="请选择生产日期" v-model="form.produceDate" style="width: 500"></el-date-picker>
					</el-form-item>
					<el-form-item label="单价">
					    <el-input v-model="form.price" name="price" placeholder="请输入单价"></el-input>
					</el-form-item>
					<el-form-item label="品牌">
					    <el-input v-model="form.brand" name="brand" placeholder="请输入品牌"></el-input>
					</el-form-item>
					<el-form-item label="型号">
					    <el-input v-model="form.model" name="model" placeholder="请输入型号"></el-input>
					</el-form-item>
					<el-form-item label="备注">
					    <el-input  type="textarea" v-model="form.remark" name="remark"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary"  @click="onSubmit('form')">创建</el-button>
					</el-form-item>
				</el-form>
			</el-card>
		</el-col>
	</el-row>
	
	
	
</div>

  <!-- 先引入 Vue -->
  <script src="${path}/js/vue.js"></script>
  <!-- 引入组件库 -->
  <script src="https://unpkg.com/element-ui/lib/index.js"></script>
  <script src="${path}/js/jquery.min.js"></script>
  
<script>

	var app = new Vue({
		el:"#app",
		data:{
			input2:'',
			loading:false,
			parentIds:${assetParent },
			form: {
	          assetNo: '',
	          name: '',
	          parentId: '',
	          produceDate: '',
	          price:'',
	          brand:'',
	          model:'',
	          remark:'',
	          loading: false
	        }
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
      			window.location.href="${path}/asset/find/?value="+this.input2; 
    		},
			onSubmit() {
				this.loading=true;
		        $.ajax({
		             type: "POST",
		             url: "${path}/asset/save/",
		             data: app.form,
		             dataType: "json",
		             success: function(data){
		             
		             	setTimeout(() => {
							app.loading=false;
							if(data == 1){
								app.$notify({
						          title: '成功',
						          message: '创建成功',
						          type: 'success'
						        });
							}else{
								app.$notify({
						          title: '失败',
						          message: '创建失败',
						          type: 'warning'
						        });
							}
				        }, 500);
		             	
		             }
				});
				
		    }
	    }
	});
	
</script>
</body>
</html>