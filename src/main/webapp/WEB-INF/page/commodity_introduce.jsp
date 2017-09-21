<%@page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
	<link rel="stylesheet" href="/css/reset004.css" type="text/css">
	<link rel="stylesheet" href="/css/style004.css" type="text/css">
</head>
<body class="grey">

<div id="head">
	<div class="head_top">
		<div class="wrap clearfix">
			<div class="leftArea">
				<a href="#" id="collection">收藏京西</a>
			</div>
			<div class="rightArea">欢迎
			<c:if test="${not empty regist }">
				<a href="/user/toUserInfor.do?registId=${regist.id }">${regist.username }</a>
			</c:if>来到京西网！
			
			<!--<c:if test="${not empty regist }">
				<a href="/regist/logout.do">[注销]</a>
			</c:if> -->
			
			<c:if test="${empty regist }">
				<a href="/regist/toLogin.do">[登录]</a><a href="/regist/toRegist.do">[免费注册]</a>
			</c:if>
			
			</div>
		</div>
	</div>
	
	<div class="search">
		<div class="wrap">
			<div class="logo">
				<a href="#"><img src="/images/logo.png" alt="京西商城"></a>
			</div>
			
			<div class="shop_car">
				<a href="/shopCar/toCart.do" >
					<span class="car">购物车</span>
					<span class="num_text">0</span>
				</a>
			</div>
		</div>
	</div>
	<div class="nav">
		
	</div>
	
</div>

<div class="bread wrap"><a class="index" href="#">首页</a><em>&gt;</em><a href="#">${merch.merchName }</a></div>
<div class="commodity_info wrap clearfix">
	<div class="info_left">
		<div class="/commodity_img"><img src="/images2/sp.jpg" alt="商品图片"></div>
		<ul class="clearfix">
			<li class="imgOn"><img src="/images2/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images2/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images2/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images2/img_list.jpg" alt="缩略图"></li>
			<li><img src="/images2/img_list.jpg" alt="缩略图"></li>
		</ul>
	</div>
	<div class="info_right">
		<h3 class="shop_name">${merch.merchName }</h3>
		<dl class="price">
			<dt>京西价</dt>
			<dd><b>￥</b>${merch.merchPrice }</dd>
		</dl>
		<dl class="favourable">
			
		</dl>
		<div class="selection">
			<dl>
				<dt></dt>
				<dd class="clearfix">
					<span class="limit_num">库存数量<b>${merch.merchStock }</b>件</span>	
					<span class="limit_num">限购<b>9</b>件</span>	
				</dd>
				
				
				<dt>购买数量</dt>
				<dd class="clearfix">
					<div class="num_select">
		
						<input id="666" type="text" name="merchNums" value="1">
						
					</div>	
				</dd>
				
			</dl>
		</div>
		<div class="buy">
			
			<div class="buy_btn">
				<a href="#" onclick="addShopCar();">加入购物车</a>
				<span class="ver_line"></span>
				<a href="#">立即购买</a>
			</div>
			
		</div>
	</div>
</div>
<div class="main wrap clearfix">
	
</div>

<div id="footer">
	<p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="/images2/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images2/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images2/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images2/pj.jpg" alt="信用评价"></a>
	</div>
</div>

<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/vue.min.js"></script>
<script type="text/javascript" charset="utf-8">

function addShopCar() {
	
	$.ajax({
        url:'/shopCar/joinShopCar.do',
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{
        	merchNum:$('#666').val(),
        	merchId:${merch.id}
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend:function(xhr){
            console.log(xhr)
            console.log('发送前')
        },
        success:function(data,textStatus,jqXHR){
            var obj = data;
            alert(obj.success);
            
            if (!data.success) {
            	alert(data.message);
            	return;
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

</script>

</body>
</html>