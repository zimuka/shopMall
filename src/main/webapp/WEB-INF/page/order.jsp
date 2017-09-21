<%@page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>order</title>
		<link rel="stylesheet" type="text/css" href="/css2/public.css"/>
		<link rel="stylesheet" type="text/css" href="/css2/proList.css" />
		<link rel="stylesheet" type="text/css" href="/css2/mygxin.css" />
	</head>
	<body>
		<!-----------head begin------------>
		<jsp:include page="common/header.jsp"></jsp:include>
		<!-----------head end------------>
		<div class="order cart mt">
			<!-----------------site------------------->
			<div class="site">
				<p class="wrapper clearfix">
					<span class="fl">订单确认</span>
					<img class="top" src="/img/temp/cartTop02.png">
				</p>
			</div>
			<!-----------------orderCon------------------->
			<div class="orderCon wrapper clearfix">
				<div class="orderL fl">
					<!--------h3---------------->
					<h3>收件信息<a href="/address/toAddress2.do" class="fr">新增地址</a></h3>
					<!--------addres---------------->
					<div class="addres clearfix" id="addressVue">
					
						<div class="addre fl" v-for="(address, index) in addressArray" v-bind:id="'xx'+index" v-on:click="changeClass(index)"> 
							<div class="tit clearfix">
								<p class="fl">{{address.realName}}
									<span class="default">[默认地址]</span>
								</p>
								<p class="fr">
									<a href="#">删除</a>
									<span>|</span>
									<a href="#" class="edit">编辑</a>
								</p>
							</div>
							<div class="addCon">
								<p>{{address.provinceName}}&nbsp;{{address.cityName}}&nbsp;{{address.areaName}}&nbsp;{{address.detailAddress}}</p>
								<p>{{address.phone}}</p>
							</div>
						</div>
						
					</div>
					<h3>支付方式</h3>
					<!--------way---------------->
					<div class="way clearfix" id="payChannelDiv">
						<img id="pay1" class="on" src="/img/temp/way01.jpg"> 
						<img id="pay2" src="/img/temp/way02.jpg"> 
						<img id="pay3" src="/img/temp/way03.jpg"> 
						<img id="pay4" src="/img/temp/way04.jpg"> 
					</div>
					<h3>选择快递</h3>
					<!--------dis---------------->
					<div class="dis clearfix">
						<span class="on">顺风快递</span>
						<span>百世汇通</span>
						<span>圆通快递</span>
						<span>中通快递</span>
					</div>
				</div>
				<div class="orderR fr" id="merchShow">
					<div class="msg">
						<h3>订单内容<a href="cart.html" class="fr">返回购物车</a></h3>
						<!--------ul---------------->
						<ul class="clearfix" v-for="merch in merchInfo.merchs">
							<li class="fl">
								<img src="/img/temp/order01.jpg">
							</li>
							<li class="fl">
								<p>{{merch.merchName}}</p>
								<p>数量：{{merch.quantity}}</p>
							</li>
							<li class="fr">￥{{merch.amount}}</li>
						</ul>
					</div>
					<!--------tips---------------->
					<div class="tips">
						<p><span class="fl">商品金额：</span><span class="fr">￥{{merchInfo.amount}}</span></p>
						<p><span class="fl">优惠金额：</span><span class="fr">￥0.00</span></p>
						<p><span class="fl">运费：</span><span class="fr">免运费</span></p>
					</div>
					<!--------tips count---------------->
					<div class="count tips">
						<p><span class="fl">合计：</span><span class="fr">￥{{merchInfo.amount}}</span></p>
					</div>
					<!--<input type="button" name="" value="去支付"> -->
					<a href="###" class="pay" onclick="pay();">去支付</a>
				</div>
			</div>
		</div>
		<!--编辑弹框-->
		<!--遮罩-->
		<div class="mask"></div>
		<div class="adddz editAddre">
			<form action="#" method="get">
				<input type="text" placeholder="姓名" class="on" />
				<input type="text" placeholder="手机号" />
				<div class="city">
					<select name="">
						<option value="省份/自治区">省份/自治区</option>
					</select>
					<select>
						<option value="城市/地区">城市/地区</option>
					</select>
					<select>
						<option value="区/县">区/县</option>
					</select>
					<select>
						<option value="配送区域">配送区域</option>
					</select>
				</div>
				<textarea name="" rows="" cols="" placeholder="详细地址"></textarea>
				<input type="text" placeholder="邮政编码" />
				<div class="bc">
					<input type="button" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--返回顶部-->
		<jsp:include page="common/gotop.jsp"></jsp:include>
		<!--footer-->
		<jsp:include page="common/footer.jsp"></jsp:include>
		
		<script type="text/javascript" src="/js/jquery-mini.js"></script>
		
		<script type="text/javascript" src="/js/vue.min.js"></script>
		
		
		<script type="text/javascript">
			var orderId = '${orderId}';
			//定义一个初始无效地址
			var checkedAddressId = -1;
			//定义默认支付方式
			var checkedPayChannle = 4;
			
			var orderInfo = {};
			var addressInfo = {};
			
			$(document).ready(function () {
				orderInfo = new Vue({
					el : '#merchShow',
					data : {
						merchInfo : {}
					}
				});
				
				addressInfo = new Vue({
					el : '#addressVue',
					data : {
						addressArray : []
					},
					
					methods:{
						changeClass : function(index) {
							$('#xx' + index).addClass("on").siblings().removeClass("on");
							checkedAddressId = this.addressArray[index].id;
							//alert(index);
						}
					},
					
					updated : function() {
						for (var i = 0; i < this.addressArray.length; i++) {
				        	var addInfo = this.addressArray[i];
				        	if (addInfo.defaultAddress === 1) {
				        		$('#xx' + i).addClass("on");
				        		//alert(i+"ppp");
				        	}
				        }
					}
					
				});
				
				loadOrderInfo();
				loadAddress();
				getPayChannel();
			});
			
			function getPayChannel() {
				$.ajax({
				    url:'/pay/getPayChannle.do',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data: {
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
				        
				        var channels = data.data;
				       
						var msg = '';
						for (var i=0;i<channels.length;i++) {
							var channel = channels[i];
							if (channel.payCode === 2) {
								msg += '<img id="'+channel.payCode+'" src="/img/temp/way01.jpg"> ';
							}
							if (channel.payCode === 3) {
								msg += '<img id="'+channel.payCode+'" src="/img/temp/way02.jpg"> ';
							}
							if (channel.payCode === 4) {
								msg += '<img id="'+channel.payCode+'" class="on" src="/img/temp/way05.png"> ';
							}
						}
						
						$('#payChannelDiv').html(msg);
					    payChannelChange();
				        
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
			
			function pay() {
				$.ajax({
				    url:'/userOrder/pay.do',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data: {
				    	orderId : orderId,
				    	payChannle : checkedPayChannle,
				    	addressId : checkedAddressId
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
				        
				        alert("订单生成OK，即将去支付" + data.data.payOrderUrl);
				        
				        window.location.href=data.data.payOrderUrl;
						
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
			
			function payChannelChange() {
				$('.way img').click(function() {
					$(this).addClass("on").siblings().removeClass("on");
					checkedPayChannle = $(this).attr("id");
					alert(checkedPayChannle);
				})
				
				$('.dis span').click(function() {
					$(this).addClass("on").siblings().removeClass("on");
				})
			}
			
			function loadAddress() {
				$.ajax({
				    url:'/address/listAddress.do',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data: {
				    	orderId : orderId
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
				        
				        addressInfo.addressArray = data.data;
						
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
			
			function loadOrderInfo() {
				$.ajax({
				    url:'/userOrder/loadOrderInfo.do',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data: {
				    	orderId : orderId
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
				        
				        orderInfo.merchInfo = data.data;
				        
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
