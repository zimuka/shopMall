<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>cart</title>
		<link rel="stylesheet" type="text/css" href="/css2/public.css"/>
		<link rel="stylesheet" type="text/css" href="/css2/proList.css" />
	</head>
	<body>
	
		<!----------head begin------------>
		<jsp:include page="common/header.jsp"></jsp:include>
		<!-- <%@include file="common/header.jsp" %> -->
		<!---------head end--------->
		
		
		<div class="cart mt">
			<!-----------------logo------------------->
			<!--<div class="logo">
				<h1 class="wrapper clearfix">
					<a href="index.html"><img class="fl" src="img/temp/logo.png"></a>
					<img class="top" src="img/temp/cartTop01.png">
				</h1>
			</div>-->
			<!-----------------site------------------->
			<div class="site">
				<p class=" wrapper clearfix">
					<span class="fl">购物车</span>
					<img class="top" src="/img/temp/cartTop01.png">
					<a href="index.html" class="fr">继续购物&gt;</a>
				</p>
			</div>
			<!-----------------table------------------->
			<div class="table wrapper">
				<div class="tr">
					<div>商品</div>
					<div>单价</div>
					<div>数量</div>
					<div>小计</div>
					<div>操作</div>
				</div>
				
				<div id="shopCarShow">
					<div class="th" v-for="(merchs, index) in shopCart">
							<div class="pro clearfix">
								<label class="fl">
								
									<!-- 绑定复选框Id索引，点击后触发事件merchChecked -->
									<input type="checkbox" v-bind:id="index" v-on:click="merchChecked"/>
		    						<span></span>
								</label>
								<a class="fl" href="#">
									<dl class="clearfix">
										<dt class="fl"><img src="/img/temp/cart01.jpg"></dt>
										<dd class="fl">
											<p>{{merchs.merch.merchName}}</p>
										</dd>
									</dl>
								</a>
							</div>
							<div class="price">￥{{merchs.merch.merchPrice}}</div>
							<div class="number">
								<p class="num clearfix">
									<img class="fl sub" src="/img/temp/sub.jpg" v-on:click="subCount(index)">
									<span class="fl">{{merchs.merchNum}}</span>
									<img class="fl add" src="/img/temp/add.jpg"v-on:click="plusCount(index)">
								</p>
							</div>
							<div class="price sAll">￥{{merchs.merch.merchPrice*merchs.merchNum}}</div>
							<div class="price"><a class="del" href="#2" v-on:click="deleteShop(merchs.id);">删除{{merchs.id}}</a></div>
							
					</div>
						
				
				<div class="goOn">空空如也~<a href="index.html">去逛逛</a></div>
				<div class="tr clearfix">
					<label class="fl">
						<input class="checkAll" id="selectAll" type="checkbox" onclick="selectAll();"/>
						<span></span>
					</label>
					<p class="fl">
						<a href="#">全选</a>
						<a href="#" class="del">删除</a>
					</p>
					<p class="fr">
						<span>共<small id="sl">0</small>件商品</span>
						<span>合计:&nbsp;<small id="totalAmount">￥0.00</small></span>
						<a href="###" class="count" id="settlementButton">结算</a>
					</p>
				</div>
			</div>
			</div>
		</div>
		<div class="mask"></div>
		<div class="tipDel">
			<p>确定要删除该商品吗？</p>
			<p class="clearfix">
				<a class="fl cer" href="#">确定</a>
				<a class="fr cancel" href="#">取消</a>
			</p>
		</div>
		<!--返回顶部-->
		
		<!-- gotop begin -->
		<jsp:include page="common/gotop.jsp"></jsp:include>
		<!-- gotop end -->
		
		<!--footer-->
		<jsp:include page="common/footer.jsp"></jsp:include>
		
		
		<!----------------mask------------------->
		<div class="mask"></div>
		<!-------------------mask内容------------------->
		<div class="proDets">
			<img class="off" src="/img/temp/off.jpg" />
			<div class="proCon clearfix">
				<div class="proImg fr">
					<img class="list" src="/img/temp/proDet.jpg"  />
					<div class="smallImg clearfix">
						<img src="/img/temp/proDet01.jpg" data-src="/img/temp/proDet01_big.jpg">
						<img src="/img/temp/proDet02.jpg" data-src="/img/temp/proDet02_big.jpg">
						<img src="/img/temp/proDet03.jpg" data-src="/img/temp/proDet03_big.jpg">
						<img src="/img/temp/proDet04.jpg" data-src="/img/temp/proDet04_big.jpg">
					</div>
				</div>
				<div class="fl">
					<div class="proIntro change">
						<p>颜色分类</p>
						<div class="smallImg clearfix">
							<p class="fl on"><img src="/img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="/img/temp/proBig01.jpg"></p>
							<p class="fl"><img src="/img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="/img/temp/proBig02.jpg"></p>
							<p class="fl"><img src="/img/temp/prosmall03.jpg" alt="20支快乐花" data-src="/img/temp/proBig03.jpg"></p>
							<p class="fl"><img src="/img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="/img/temp/proBig04.jpg"></p>
						</div>
					</div>
					<div class="changeBtn clearfix">
						<a href="#2" class="fl"><p class="buy">确认</p></a>
						<a href="#2" class="fr"><p class="cart">取消</p></a>
					</div>
				</div>
			</div>
		</div>
		<div class="pleaseC">
			<p>请选择宝贝</p>
			<img class="off" src="/img/temp/off.jpg" />
		</div>

<script type="text/javascript" src="/js/json2.js"></script>		
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue.min.js"></script>
<script type="text/javascript" charset="utf-8">
	
	//vue方法定义方式
	var shopCarShow = {};
	
	$(document).ready(function() {
		shopCarShow = new Vue({
			el : '#shopCarShow',
			data : {
				shopCart : [
				]
			},
			
			//定义方法methods（包含增减购物数量的触发事件）
			methods : {
				deleteShop : function(id){
					deleteShop(id);
				},
			
				plusCount : function(index) {
					var cg = this.shopCart[index];
					cg.merchNum += 1;
					countAmount();
				},
				
				subCount : function(index) {
					var cg = this.shopCart[index];
					if (cg.merchNum <=1) {
						return;
					}
					cg.merchNum -= 1;
					countAmount();
				},
				
				merchChecked : function() {
					countAmount();
				}
			}
		});
		
		$('#settlementButton').click(function() {
			//settlement();
			//settlment4Json();
			settlement4JsonBody();
		});
		
		getShopCarDetails();
	});
	
	
	function settlement4JsonBody() {
		var buyMerchArray = [];
			$('.th :checkbox').each(function () {
				if ($(this).is(':checked')) {
					var cardIndex = $(this).attr('id');
					var cardMerch = shopCarShow.shopCart[cardIndex];
					buyMerchArray.push({merchId : cardMerch.merchId , merchNum : cardMerch.merchNum });
				}
		});
		
		var msg = JSON.stringify(buyMerchArray);
		
		$.ajax({
		    url:'/userOrder/createOrder4JsonBody.do',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data: msg,
		    contentType:"application/json",
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
		        
		        //跳转到结算页
		        window.location.href = '/shopCar/toOrder.do?orderId=' + data.data.id;
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
	
	
	function settlment4Json() {
		var buyMerchArray = [];
		$('.th :checkbox').each(function () {
			if ($(this).is(':checked')) {
				var cardIndex = $(this).attr('id');
				alert(cardIndex);
				var cardMerch = shopCarShow.shopCart[cardIndex];
				buyMerchArray.push(cardMerch);
			}
		});
		
		var msg = JSON.stringify(buyMerchArray);
		alert(msg);
		
		$.ajax({
		    url:'/userOrder/createOrder4Json.do',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	buyMsg : msg
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
		        
		        //跳转到结算页
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
	
	function settlement() {
		//alert("sdfwf");
		var buyMerchArray = [];
		$('.th :checkbox').each(function () {
			//使用jq遍历(each)所有标签下的复选框，筛选出勾选中的商品
			if ($(this).is(':checked')) {
				var cardIndex = $(this).attr('id');
				//将下标IDpush到buyMerchArray数组中
				var cardMerch = shopCarShow.shopCart[cardIndex];
				buyMerchArray.push(cardMerch);
			}
		});
		
		//获取商品数量和商品Id
		var msg = '';
		for (var i = 0; i < buyMerchArray.length; i++) {
			var merch = buyMerchArray[i];
			msg += merch.merchId + '-' + merch.merchNum + '$';
		}
		
		$.ajax({
		    url:'/userOrder/createOrder.do',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	buyMsg : msg
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		        console.log(xhr)
		        console.log('发送前')
		    },
		    success:function(data,textStatus,jqXHR){
		        
		        if (!data.success) {
		        	//alert(data.message);
		        	return;
		        }
		        
		        alert('success');
		        
		        //跳转到结算页
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
	
	//复选框全选（遍历.th class下所有的复选框，增加checked字段：true为全选，false为全不选）
	function selectAll() {
		$('.th :checkbox').each(function () {
			if ($('#selectAll').is(':checked')) {
				   $(this).prop('checked', true);
			   } else {
				   $(this).prop('checked', false);
			   }
		});
		countAmount();
	}
	//复选框选中后商品价格展示
	function countAmount() {
		//定义一个集合用来存放筛选出来的选中商品的复选框ID
		var merchArray = [];
		//使用jq遍历(each)所有标签下的复选框，筛选出勾选中的商品
		$('.th :checkbox').each(function () {
			if ($(this).is(':checked')) {
				var attrValue = $(this).attr('id');
				//alert(attrValue);
				//将下标IDpush到merchArray数组中
				merchArray.push(attrValue);
			}
		});
		var total = 0;
		for (var i=0; i<merchArray.length; i++) {
			var index = merchArray[i];
			var merchs = shopCarShow.shopCart[index];
			var price = merchs.merch.merchPrice;
			var num = merchs.merchNum;
			total += price * num;
		}
		$('#totalAmount').html(total);
	}
	
	function getShopCarDetails() {
		
		var id = ${regist.id};
		//alert(id);
		
   		$.ajax({
               url:'/shopCar/ShopCarDetails.do',
               type:'POST', //GET
               async:true,    //或false,是否异步
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
                   
                   shopCarShow.shopCart = data.data;
                   var len = data.data.length;
                   for (var i=0; i<len;i++) {
                   	var cg = shopCarShow.shopCart[i];
                   	getMersh(cg, i);
                   }
                 
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
	
	function getMersh(cg, index) {
   		$.ajax({
               url:'/merch/toMerchDetail2.do',
               type:'POST', //GET
               async:true,    //或false,是否异步
               data:{
            	   merchId:cg.merchId,
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
                   
                   cg["merch"] = data.data;
                   shopCarShow.shopCart.splice(index, 1, cg)
                   
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
	
	function deleteShop(id) {
		//alert(id);
		if (confirm("你确定要删除吗？")) {
			$.ajax({
				url:'/shopCar/delectShopDetail.do',
				type:'POST',//请求类型：post，get
				async:true, //是否异步
				data:{
					shopCarId:id
				},
				timeout:5000,
				dataType:'json',//返回数据格式，使用json
				beforeSend:function(xhr){
                    console.log(xhr)
                    console.log('发送前')
                },
				success:function(data,textStatus,jqXHR){
                    
                    if (!data.success) {
                    	alert(data.message);
                    	return;
                    }
                    
                    alert('删除成功');
                    window.location.href='/shopCar/toCart.do';
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
		} else {
			alert('删除失败');
		}
	}

</script>

	</body>
</html>
