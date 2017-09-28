<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--header-->
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<!--header-->

<div class="container">
	<div class="wrap-word-text">
		<div class="player-video">
			<div class="player-video-left" id="player">
				<video width="100%" height="100%" controls autoplay loop>
					<source src="${ctx}/static/mp4/${homeData.type}/${homeData.name}" type="video/mp4">
				</video>
			</div>
			<div class="player-list-right">
				<ul id="playerlist">
					<c:forEach items="${homeList}" var="h" varStatus="s">
						<a href="${ctx}/home/typelist?id=${h.id}"> 
							<c:if test="${homeData.id==h.id}">
								<li class="current">
									<span class="title">${h.title}</span><span class="times">${h.view}</span>
								</li>
							</c:if>
							<c:if test="${homeData.id!=h.id}">
								<li>
									<span class="title">${h.title}</span><span class="times">${h.view}</span>
								</li>
							</c:if> 
						</a>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--footer-->
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
<!--footer-->

</body>
</html>