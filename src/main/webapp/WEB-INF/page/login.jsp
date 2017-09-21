<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>大猿ATM(atm.com)-综合性ATM机，全国统一价</title>
	<link rel="stylesheet" href="/css/reset001.css" type="text/css">
	<link rel="stylesheet" href="/css/style001.css" type="text/css">
</head>
<body>
<div id="head">
	<div class="wrap">
		<div class="logo">
			<a href="#"><img width="50px" height="50px" src="/images/dayuan.jpg" alt=""></a>
		</div>
		<h3>欢迎登录</h3>
	</div>
</div>
<div class="login">
	<form id="loginForm">
		<ul class="login_box">
			<li class="login_text">用户名</li>
			<li><input type="text" id="username" name="username" class="user_name"></li>
			<li class="login_text">密码</li>
			<li><input type="password" id="password" name="password" class="user_password"></li>
			<li class="login_check">
			</li>
			<li><input class="login_btn" type="button" value="登录" onclick="login();"></li>
			<li class="co_account">
			</li>
		</ul>
	</form>
</div>
<div class="wrap register">
	<a href="/regist/toRegist.do" class="free_register">免费注册&gt;&gt;</a></div>
<div id="footer">
	<p>客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2017 备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
	</div>
</div>
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/jquery-validate.min.js"></script>
<script type="text/javascript" src="/js/messages_zh.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$("#loginForm").validate({
	    rules: {
	    	username: "required",
	    	password: {
		        required: true,
		        minlength: 1,
		        maxlength: 6
		      }
	    },
	    messages: {
	    	username: "请输入登录名",
	    	password: {
	    		required : "请输入密码",
	    		minlength : "密码最小长度为1",
	    		maxlength : "密码最大长度为6"
	    	}
	    }
	});
});

function login() {
	
	if (!$("#loginForm").valid()) {
		return false;
	}
	
	$.ajax({
        url:'/regist/login.do',
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{
        	username:$('#username').val(),
        	password:$('#password').val()
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
            
            window.location.href='/index.jsp';
            
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