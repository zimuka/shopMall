<%@page contentType="text/html; charset=utf-8" %>
<div class="you fl">
	<h2>个人信息</h2>
	<div class="gxin">
		<div class="tx"><a href="#"><img src="/img/tx.png"/><p id="avatar">修改头像</p></a></div>
		<div class="xx">
			<form action="">
				<h3 class="clearfix"><strong class="fl">基础资料</strong><a href="/user/modifyUserInfor.do" class="fr" id="edit1">编辑</a></h3>
				<div>姓名：六六六</div>
				<div>生日：1995-06-06</div>
				<div>性别：女</div>
				<h3>高级设置</h3>
				<input type="text" name="cardId" value="${bankCard.id }">
			</form>
			
			<!--<div><span class="fl">银行卡</span><a href="#" class="fr">管理</a></div>-->
			<div><span class="fl">账号地区：中国</span><a href="#" class="fr" id="edit2">修改</a></div>
		</div>
	</div>			
</div>