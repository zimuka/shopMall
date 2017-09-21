<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>最家</title>
		<link rel="stylesheet" type="text/css" href="/css2/public.css"/>
		<link rel="stylesheet" type="text/css" href="/css2/mygxin.css" />
	</head>
	<body>
		<!------------------------------head------------------------------>
		<div class="head ding">
			<div class="wrapper clearfix">
				<div class="clearfix" id="top">
					<h1 class="fl"><a href="index.html"><img src="/img/logo.png"/></a></h1>
					<div class="fr clearfix" id="top1">
						<p class="fl">
							<a href="login.html" id="login">登录</a>
							<a href="reg.html" id="reg">注册</a>
						</p>
						<form action="#" method="get" class="fl">
							
						</form>
						<div class="btn fl clearfix">
							<a href="mygxin.html"><img src="/img/grzx.png"/></a>
							<a href="#" class="er1"><img src="/img/ewm.png"/></a>
							<a href="cart.html"><img src="/img/gwc.png"/></a>
							<p><a href="#"><img src="/img/smewm.png"/></a></p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="mygxin.html">个人中心</a>
				<span>/</span>
				<a href="address.html" class="on">地址管理</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<div class="zuo fl">
					<h3>
						<a href="#"><img src="/img/tx.png"/></a>
						<p class="clearfix"><span class="fl">[羊羊羊]</span><span class="fr">[退出登录]</span></p>
					</h3>
					<div>
						<h4>我的交易</h4>
						<ul>
							<li><a href="cart.html">我的购物车</a></li>
							<li><a href="myorderq.html">我的订单</a></li>
							<li><a href="myprod.html">评价晒单</a></li>
						</ul>
						<h4>个人中心</h4>
						<ul>
							<li><a href="mygxin.html">我的中心</a></li>
							<li class="on"><a href="address.html">地址管理</a></li>
						</ul>
						<h4>账户管理</h4>
						<ul>
							<li><a href="mygrxx.html">个人信息</a></li>
							<li><a href="remima.html">修改密码</a></li>
						</ul>
					</div>
				</div>
				<div class="you fl" id="addreShow">
					<h2>收货地址</h2>
					<div class="add">
						<div>
							<a href="#2" id="addxad"><img src="/img/jia.png"/></a>
							<span>添加新地址</span>
						</div>
					</div>		
					<div class="add" v-for="(address, index) in addresses">
						<div id="dizhi">
							<p>{{address.realName}}</p>
							<p>{{address.phone}}</p>
							<p>{{address.provinceName}} {{address.cityName}} {{address.areaName}}</p>
							<p>{{address.detailAddress}}</p>
							<span v-on:click="modifyAdd(index)">修改</span>
							<span v-on:click="delAdd(index)">删除</span>
						</div>
					</div>	
						
				</div>
			</div>
		</div>
		<!--编辑弹框-->
		<!--遮罩-->
		<div class="mask"></div>
		<div class="adddz">
			<form action="#" method="get">
				<input type="text" id="realName" placeholder="姓名" class="on" />
				<input type="text" id="phone" placeholder="手机号" />
				<div class="city">
					<select name="" id="addProvince" onchange="listCity();">
						<option value="xxx" title="sss">省份/自治区</option>
					</select>
					<select id="addCity" onchange="listArea();">
						<option value="xxx" title="sss">城市/地区</option>
					</select>
					<select id="addArea">
						<option value="xxx" title="sss">区/县</option>
					</select>
				</div>
				默认地址：
				<input type="radio" id="xx" name="defaultAddress" value="2" style="width: 20px;height: 20px">否
				<input type="radio" id="xxx" name="defaultAddress" value="1" style="width: 20px;height: 20px">是
				
				<textarea id="detailAddress" name="" rows="" cols="" placeholder="详细地址"></textarea>
				<div class="bc">
					<input type="button" id="save" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		
		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.html">
			<dl>
				<dt><img src="/img/gt1.png"/></dt>
				<dd>去购<br />物车</dd>
			</dl>
			</a>
			<a href="#" class="dh">
			<dl>
				<dt><img src="/img/gt2.png"/></dt>
				<dd>联系<br />客服</dd>
			</dl>
			</a>
			<a href="mygxin.html">
			<dl>
				<dt><img src="/img/gt3.png"/></dt>
				<dd>个人<br />中心</dd>
			</dl>
			</a>
			<a href="#" class="toptop" style="display: none">
			<dl>
				<dt><img src="/img/gt4.png"/></dt>
				<dd>返回<br />顶部</dd>
			</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		
		
		<!--footer-->
		<div class="footer">
			<div class="top">
				<div class="wrapper">
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot1.png"/></a>
						<span class="fl">7天无理由退货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot2.png"/></a>
						<span class="fl">15天免费换货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot3.png"/></a>
						<span class="fl">满599包邮</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="/img/foot4.png"/></a>
						<span class="fl">手机特色服务</span>
					</div>
				</div>
			</div>
			<p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br />
			违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		
		
		
		<script type="text/javascript" src="/js/jquery-mini.js"></script>
		<script type="text/javascript" src="/js/vue.min.js"></script>
		<script type="text/javascript">
			var actionFlag = false;
			var delAddressIndex = -1;
			var modifyAddressId = -1;
			var showAddress = {};
			//页面初始化，加载页面是初始
			$(document).ready(function() {
				//使用vue
				showAddress = new Vue({
					el : '#addreShow',
					data : {
						addresses : []
					}, 
					methods : {
						modifyAdd : function(index) {
							
							$('.mask').show();
							$('.adddz').show();
							
							var address = this.addresses[index];
							alert(address.realName);
							initModify(address);
							actionFlag = false;
						},
						delAdd : function(index) {
							var address = this.addresses[index];
							//alert(address.realName);
							delAddressIndex = index;
							del();
						}
					}
				});
				
				
				//点击添加按钮，触发本次事件
				$('#addxad').click(function() {
					//每次点击时需要对其地址初始化
					initAddAddress();
					//遮罩，局部弹出（#号对应id . 对应class）
					$('.mask').show();
					$('.adddz').show();
					listProvince();
					actionFlag = true;
				});
				
				//点击bc class下的任意input，遮罩，弹框隐藏
				//获取bc class下的所有input节点
				$('.bc>input').click(function() {
					$('.mask').hide();
					$('.adddz').hide();
				});
				
				$('#save').click(function() {
					//addAddress();
					choseAction();
				});
				
				loadAddress();
			});
			
			function choseAction() {
				if (actionFlag) {
					addAddress();
					//alert('addAddress');
				} else {
					modifyAddress();
					//alert('modifyAddress');
				}
			}
			
			function del() {
				var delAdd = showAddress.addresses[delAddressIndex];
				
				$.ajax({
		            url:'/address/detele.do',
		            type:'POST', //GET
		            async:false,    //或false,是否异步
		            data:{
		            	addressId : delAdd.id
		            },
		            timeout:5000,    //超时时间
		            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		            beforeSend:function(xhr){
		                console.log(xhr)
		                console.log('发送前')
		            },
		            success:function(data,textStatus,jqXHR){
		            	 if (!data.success) {
		                 	alert(data.message);
		                 	return;
		                 }
		            	 
		            	 alert('success');
		            	 //loadAddress();
		            	 //在前台页面删除集合中的数据，将其显示，不用在数据库中再次查询，提高数据库的性能
		            	 showAddress.addresses.splice(delAddressIndex, 1);
		             },
		            error:function(xhr,textStatus){
		                console.log('错误')
		                console.log(xhr)
		                console.log(textStatus)
		            },
		            complete:function(){
		                console.log('结束')
		            }
		        });
			}
			
			function modifyAddress() {
				$.ajax({
		            url:'/address/modify.do',
		            type:'POST', //GET
		            async:false,    //或false,是否异步
		            data:{
		            	provinceCode : $('#addProvince').val(),
						cityCode : $('#addCity').val(),
						areaCode : $('#addArea').val(),
						provinceName : $("#addProvince option:selected").attr("title"),
						cityName : $("#addCity option:selected").attr("title"),
						areaName : $("#addArea option:selected").attr("title"),
						phone : $('#phone').val(),
						detailAddress : $('#detailAddress').val(),
		            	realName : $('#realName').val(),
		            	defaultAddress : $('input:radio[name="defaultAddress"]:checked').val(),
		            	addressId : modifyAddressId
		            },
		            timeout:5000,    //超时时间
		            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		            beforeSend:function(xhr){
		                console.log(xhr)
		                console.log('发送前')
		            },
		            success:function(data,textStatus,jqXHR){
		            	 if (!data.success) {
		                 	alert(data.message);
		                 	return;
		                 }
		            	 
		            	 alert('success');
		            	 loadAddress();
		             },
		            error:function(xhr,textStatus){
		                console.log('错误')
		                console.log(xhr)
		                console.log(textStatus)
		            },
		            complete:function(){
		                console.log('结束')
		            }
		        });
			}
			
			function initModify(address) {
				
				$('#realName').val(address.realName),
				$('#phone').val(address.phone),
				listProvince();
				$('#addProvince').val(address.provinceCode),
				listCity();
				$('#addCity').val(address.cityCode),
				listArea();
				$('#addArea').val(address.areaCode),
				$('#detailAddress').val(address.detailAddress)
				if (1 == address.defaultAddress) {
					$('input:radio[name="defaultAddress"][value="1"]').prop('checked', true);
				} else {
					$('input:radio[name="defaultAddress"][value="2"]').prop('checked', true);
				}
				
				modifyAddressId = address.id;
			}
			
			
			//查询可用地址
			function loadAddress() {
				$.ajax({
		            url:'/address/listAddress.do',
		            type:'POST', //GET
		            async:false,    //或false,是否异步
		            data:{
		            },
		            timeout:5000,    //超时时间
		            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		            beforeSend:function(xhr){
		                console.log(xhr)
		                console.log('发送前')
		            },
		            success:function(data,textStatus,jqXHR){
		            	 if (!data.success) {
		                 	alert(data.message);
		                 	return;
		                 }
		            	 
						showAddress.addresses = data.data;
						
		             },
		            error:function(xhr,textStatus){
		                console.log('错误')
		                console.log(xhr)
		                console.log(textStatus)
		            },
		            complete:function(){
		                console.log('结束')
		            }
		        });
			}
			
			//增加地址
			function addAddress() {
				
				$.ajax({
		            url:'/address/addAddress.do',
		            type:'POST', //GET
		            async:false,    //或false,是否异步
		            data:{
		            	provinceCode : $('#addProvince').val(),
						cityCode : $('#addCity').val(),
						areaCode : $('#addArea').val(),
						provinceName : $("#addProvince option:selected").attr("title"),
						cityName : $("#addCity option:selected").attr("title"),
						areaName : $("#addArea option:selected").attr("title"),
						phone : $('#phone').val(),
						detailAddress : $('#detailAddress').val(),
		            	realName : $('#realName').val(),
		            	defaultAddress : $('input:radio[name="defaultAddress"]:checked').val()
		            },
		            timeout:5000,    //超时时间
		            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		            beforeSend:function(xhr){
		                console.log(xhr)
		                console.log('发送前')
		            },
		            success:function(data,textStatus,jqXHR){
		            	 if (!data.success) {
		                 	alert(data.message);
		                 	return;
		                 }
		            	 
		            	 alert('success');
		            	 loadAddress();
		             },
		            error:function(xhr,textStatus){
		                console.log('错误')
		                console.log(xhr)
		                console.log(textStatus)
		            },
		            complete:function(){
		                console.log('结束')
		            }
		        });
			}
			
			//清除中途修改的地址（每次点击添加地址按钮式触发）
			function initAddAddress() {
				$('#realName').val(''),
				$('#phone').val(''),
				$('#addProvince').val('xxx'),
				$('#addCity').val('xxx'),
				$('#addArea').val('xxx'),
				$('#detailAddress').val('')
			}
			
			//遍历省级表
			function listProvince() {
		   		$.ajax({
		               url:'/address/listProvince.do',
		               type:'POST', //GET
		               async:false,    //或false,是否异步
		               data:{
		            	  
		               },
		               timeout:5000,    //超时时间
		               dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		               beforeSend:function(xhr){
		                   console.log(xhr)
		                   console.log('发送前')
		                   
		               },
		               success:function(data,textStatus,jqXHR){
		                   
		                   if (!data.success) {
		                   	alert(data.message);
		                   	return;
		                   }
		                   
		                   var results = data.data;
		                   var msg = '';
		                   for (var i=0; i<results.length; i++) {
		                   	var pro = results[i];
		               		msg += '<option value="' + pro.code + '" title="' + pro.name + '">'+ pro.name +'</option>';
		                   }
		                 	
		                   $('#addProvince').append(msg);
		                   
		               },
		               error:function(xhr,textStatus){
		                   console.log('错误')
		                   console.log(xhr)
		                   console.log(textStatus)
		               },
		               complete:function(){
		                   console.log('结束')
		               }
		           });
		   	}
			
			
			//
			function listCity() {
				
		   		$.ajax({
		               url:'/address/listCity.do',
		               type:'POST', //GET
		               async:false,    //或false,是否异步
		               data:{
		            	   provinveCode : $('#addProvince').val()
		               },
		               timeout:5000,    //超时时间
		               dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		               beforeSend:function(xhr){
		                   console.log(xhr)
		                   console.log('发送前')
		                   
		               },
		               success:function(data,textStatus,jqXHR){
		                   
		                   if (!data.success) {
		                   	alert(data.message);
		                   	return;
		                   }
		                   
		                   var results = data.data;
		                   var msg = '<option value="xxx">城市/地区</option>';
		                   for (var i=0; i<results.length; i++) {
		                   	var city = results[i];
		               		msg += '<option value="' + city.code + '" title="' + city.name + '">'+ city.name+'</option>';
		                   }
		                 	
		                   $('#addCity').html(msg);
		                   
		                   $('#addArea').html('<option value="xxx">区/县</option>');
		               },
		               error:function(xhr,textStatus){
		                   console.log('错误')
		                   console.log(xhr)
		                   console.log(textStatus)
		               },
		               complete:function(){
		                   console.log('结束')
		               }
		           });
		   	}
			
			//
			function listArea() {
				
		   		$.ajax({
		               url:'/address/listArea.do',
		               type:'POST', //GET
		               async:false,    //或false,是否异步
		               data:{
		            	   cityCode : $('#addCity').val()
		               },
		               timeout:5000,    //超时时间
		               dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		               beforeSend:function(xhr){
		                   console.log(xhr)
		                   console.log('发送前')
		                   
		               },
		               success:function(data,textStatus,jqXHR){
		                   
		                   if (!data.success) {
		                   	alert(data.message);
		                   	return;
		                   }
		                   
		                   var results = data.data;
		                   var msg = '<option value="xxx">区/县</option>';
		                   for (var i=0; i<results.length; i++) {
		                   	var area = results[i];
		               		msg += '<option value="' + area.code + '" title="' + area.name + '">'+ area.name+'</option>';
		                   }
		                 	
		               	$('#addArea').html(msg);
		               },
		               error:function(xhr,textStatus){
		                   console.log('错误')
		                   console.log(xhr)
		                   console.log(textStatus)
		               },
		               complete:function(){
		                   console.log('结束')
		               }
		           });
		   	}
		</script>
		
	</body>
</html>
