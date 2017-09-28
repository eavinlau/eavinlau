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
							<th>#</th>
							<th>id</th>
							<th>名称</th>
							<th>类别</th>
							<th>标题</th>
							<th>浏览量</th>
							<th>创建时间</th>
							<th>修改时间</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${homeList}" var="h" varStatus="s">
							<tr>
								<td>${s.count}</td>
								<td>${h.id}</td>
								<td>${h.name}</td>
								<td>${h.type}</td>
								<td>${h.title}</td>
								<td>${h.view}</td>
								<td>${h.ctime}</td>
								<td>${h.mtime}</td>
								<td><a href="${ctx}/home/del?id=${h.id}">删除</a></td>
							</tr>
						</c:forEach>
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