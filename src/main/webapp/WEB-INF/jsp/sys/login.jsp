<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--header-->
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<!--header-->

<div id="pbody">
	<div class="container">
		<!-- 首页视频列表 -->
		<div class="index-video">
			<div class="list-video j_tabPanne">
				<form action="${ctx}/user/login" method="post" id="login_form"
					enctype="multipart/form-data">
					<table width="100%" align="center" border="1px">
						<tr>
							<td colspan="2" id="msg" style="color: red">${msg}</td>
						</tr>
						<tr>
							<td>用户名：</td>
							<td><input type="text" id="username" name="username" /></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><input type="password" id="password" name="password" /></td>
						</tr>
						<tr>
							<td>谷歌验证码：</td>
							<td><input type="text" id="googleCode" name="googleCode" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" value="登录" onclick="login()" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<!--footer-->
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
<!--footer-->

</body>
</html>