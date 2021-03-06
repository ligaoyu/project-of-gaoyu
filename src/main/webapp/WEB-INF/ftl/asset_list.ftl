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
			<el-menu theme="dark" default-active="2" class="el-menu-vertical-demo full" @select="handleSelect" @open="handleOpen" @close="handleClose" >
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
				<el-form id="form" ref="form" :model="form" label-width="80px">
					<el-row>
						<el-col :span="12">
							<el-form-item label="编号">
								<el-input v-model="form.assetNo" name="assetNo" placeholder="请输入设备编号"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="名称">
							    <el-input v-model="form.name" name="name" placeholder="请输入设备名称"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="12">
							<el-form-item label="设备类型">
								<el-select v-model="form.parent" clearable name="parent" placeholder="请选择设备类型">
									<el-option	v-for="item in parentId"
								      :key="item.id"
								      :label="item.name"
								      :value="item.id"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="状态">
								<el-select v-model="form.type" clearable name="type" placeholder="请选择">
									<el-option label="外借中" value="1" ></el-option>
									<el-option label="空闲" value="2" ></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="4">
							<el-form-item>
								<el-button type="primary" @click="onSubmit">查询</el-button>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-card>
		</el-col>
		
		<el-col :span="19" :offset="4">
			<el-card class="box-card row_bottom">
				<template>
				  <el-table :data="tableData" stripe style="width: 100%" v-loading.body="loading">
				  	<el-table-column type="index" width="100"></el-table-column>
				    <el-table-column prop="assetNo" label="编号"></el-table-column>
				    <el-table-column prop="name" label="名称"></el-table-column>
				    <el-table-column prop="produceDate" min-width="120" label="生产日期"></el-table-column>
				    <el-table-column prop="brand" label="品牌"></el-table-column>
				    <el-table-column prop="model" label="型号"></el-table-column>
				    <el-table-column prop="price" label="单价"></el-table-column>
				    <el-table-column prop="dname" label="所在部门"></el-table-column>
				    <el-table-column label="操作" min-width="120">
						<template scope="scope" >
								<el-button v-if="scope.row.type === 1" size="mini" type="info" @click="getBack(scope.$index, scope.row.id)">收回</el-button>
						        <el-button v-else size="mini" type="success" @click="lend(scope.$index, scope.row.id)">外借</el-button>
						</template>
				    </el-table-column>
				  </el-table>
				</template>
			</el-card>
		</el-col>
	</el-row>
	
	<el-dialog title="选择" :visible.sync="dialogFormVisible" v-loading.body="dialogLoading">
	  <el-form :model="dialogForm" label-width="100px">
	    <el-form-item label="负责人" >
	      <el-input v-model="dialogForm.userName" ></el-input>
	    </el-form-item>
	    <el-form-item label="接收部门">
	      <el-select v-model="dialogForm.deptId" placeholder="请选择部门">
	        <el-option	v-for="item in dept"
			      :key="item.id"
			      :label="item.name"
			      :value="item.id"></el-option>
	      </el-select>
	    </el-form-item>
	    <el-form-item label="存放地点" >
	      <el-input v-model="dialogForm.location" ></el-input>
	    </el-form-item>
	    <el-form-item label="备注">
		    <el-input  type="textarea" v-model="dialogForm.remark" ></el-input>
		</el-form-item>
	  </el-form>
	  <div slot="footer" class="dialog-footer">
	    <el-button @click="dialogFormVisible = false">取 消</el-button>
	    <el-button type="primary" @click="confirm">确 定</el-button>
	  </div>
	</el-dialog>
	
	
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
			loading:false,
			tableData:${assetJson },
			parentId:${assetParent },
			dept:${deptList },
			dialogFormVisible:false,
			dialogLoading:false,
			form: {
	          assetNo: '',
	          name: '',
	          parent: '',
	          type: ''
	        },
	        dialogForm: {
	        	assetId:'',
	          	userName: '',
	          	deptId: '',
	          	location:'',
	          	remark:''
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
    		getBack(index, id) {
				this.$confirm('确认归还?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'info'
		        }).then(() => {
		        	$.ajax({
			             type: "POST",
			             url: "${path}/asset/getBack/",
			             data: {"id":id},
			             dataType: "json",
			             success: function(data){
			             	app.$message({
						  		type: 'success',
						        message: '归还成功!'
						    });
						    app.onSubmit();
			             }
					});
		        
		        }).catch(() => {
		          this.$message({
		            type: 'info',
		            message: '已取消'
		          });          
		        });
			},
			lend(index, id) {
				this.dialogFormVisible=true;
				this.dialogForm.assetId = id;
			},
			confirm(){
				this.dialogLoading = true;
				$.ajax({
		             type: "POST",
		             url: "${path}/asset/lend/",
		             data: app.dialogForm,
		             dataType: "json",
		             success: function(data){
		             	setTimeout(() => {
		             		app.dialogLoading=false;
		             		dialogFormVisible = false;
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
				this.dialogFormVisible = false;
				this.onSubmit();
			},
			onSubmit() {
				this.loading=true;
		        $.ajax({
		             type: "POST",
		             url: "${path}/asset/query/",
		             data: $('#form').serialize(),
		             dataType: "json",
		             success: function(data){
		             	setTimeout(() => {
				        	app.tableData=data;
		             		app.loading=false;
				        }, 500);
		             	
		             }
				});
				
		    }
	    }
	});
	
</script>
</body>
</html>