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
				<form action="${ctx}/user/register" method="post" id="register_form">
					<table width="100%" align="center" border="1px">
						<tr>
							<td>用户名：</td>
							<td><input type="text" name="username" id="username" onblur="checkUsername1()" /></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><input type="password" name="password" id="password" /></td>
						</tr>
						<tr>
							<td>重复密码：</td>
							<td><input type="password" id="password2" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" value="注册" onclick="register1()" /></td>
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