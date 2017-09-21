<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>个人信息</title>
		<link rel="stylesheet" type="text/css" href="/css2/public.css"/>
		<link rel="stylesheet" type="text/css" href="/css2/mygrxx.css" />
	</head>
	<body>
		<!------------------------------head------------------------------>
		<jsp:include page="common/header.jsp"></jsp:include>
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="mygxin.html" class="on">个人信息</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<jsp:include page="common/left.jsp"></jsp:include>
				
				<jsp:include page="common/right.jsp"></jsp:include>
			</div>
		</div>
		<!--遮罩-->
		<div class="mask"></div>
		<!--编辑弹框-->
		<div class="bj">
			<div class="clearfix"><a href="#" class="fr gb"><img src="/img/icon4.png"/></a></div>
			<h3>编辑基础资料</h3>
			<form action="#" method="get">
				<p><label>姓名：</label><input type="text"  value="六六六" /></p>
				<p><label>生日：</label><input type="text"   /></p>
				<p>
					<label>性别：</label>
					<span><input type="radio"  />男</span>
					<span><input type="radio"   />女</span>
				</p>
				<div class="bc">
					<input type="button" value="保存"  />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--高级设置修改-->
		<div class="xg">
			<div class="clearfix"><a href="#" class="fr gb"><img src="/img/icon4.png"/></a></div>
			<h3>切换账号地区</h3>
			<form action="#" method="get">
				<p><label>姓名：</label><input type="text"  value="六六六" /></p>
				<div class="bc">
					<input type="button" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--修改头像-->
		<div class="avatar">
			<div class="clearfix"><a href="#" class="fr gb"><img src="/img/icon4.png"/></a></div>
			<h3>修改头像</h3>
			<form action="#" method="get">
				<h4>请上传图片</h4>
				<input type="button" value="上传头像" />
				<p>jpg或png，大小不超过2M</p>
				<input type="submit" value="提交" />
			</form>
		</div>
		
		<!--返回顶部-->
		<jsp:include page="common/gotop.jsp"></jsp:include>
		<!--footer-->
		<jsp:include page="common/footer.jsp"></jsp:include>
		<!-- <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/user.js" type="text/javascript" charset="utf-8"></script> -->
	</body>
</html>
