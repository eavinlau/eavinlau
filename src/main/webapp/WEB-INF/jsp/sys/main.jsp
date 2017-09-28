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
				<form action="${ctx}/home/save" method="post" id="save_form"
					enctype="multipart/form-data">
					<table width="100%" align="center" border="1px">
						<tr>
							<td colspan="2" style="color: red">welcome</td>
						</tr>
						<tr>
							<td>用户名：</td>
							<td>${user.username}</td>
						</tr>
						<tr>
							<td>谷歌验证码：</td>
							<td>${user.googleCode}</td>
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