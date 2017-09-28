<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--header-->
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<!--header-->

<div id="pbody">
	<div class="container">
		<!-- 首页视频列表 -->
		<div>
			<h2>${eavinlau}</h2>
			<div class="list-video">
				<c:if test="${homeList.size()>0}">
					<ul>
						<c:forEach items="${homeList}" var="h" varStatus="s">
							<li>
								<div class="pic">
									<a href="${ctx}/home/typelist?id=${h.id}" target="_blank">
										<img src="${ctx}/static/images/ljxd.jpg"
										style="display: block" />
									</a>
								</div>
								<div class="cnt">
									<div class="t">
										<h3>${h.title}</h3>
									</div>
									<div class="fun">
										<span> ${h.view}&nbsp;${h.ctime} </span>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${homeList.size()==0}">
					<h2>&nbsp;&nbsp;没找到视频</h2>
				</c:if>
			</div>
		</div>
	</div>
</div>

<!--footer-->
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
<!--footer-->

</body>
</html>