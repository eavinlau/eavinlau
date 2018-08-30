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
				<form action="${ctx}/home/save" method="post" id="save_form" enctype="multipart/form-data">
					<table width="100%" align="center" border="1px" >
						<tr>
							<td>分类：</td>
							<td>
								<label><input type="radio" name="type" value="l" />l</label>
								<label><input type="radio" name="type" value="d" checked="checked" />d</label> 
								<label><input type="radio" name="type" value="b" />b</label>
							</td>
						</tr>
						<tr>
							<td>标题：</td>
							<td><input type="text" name="title" id="title" onblur="checkTitle()" /></td>
						</tr>
						<tr>
							<td>文件：</td>
							<td><input type="file" name="mp4File" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" value="添加视频" onclick="saveForm()" /></td>
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