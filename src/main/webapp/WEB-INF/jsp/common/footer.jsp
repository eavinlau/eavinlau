<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--footer-->
<hr style="margin-top: 20px;border: solid 1px #cccccc;" />
<div class="container">
	<div class="footer">
		<div class="foot-link">
		    <c:if test="${username=='eavinlau'}">
				<a href="${ctx}/user/main">E</a><span>-</span>
				<a href="${ctx}/home/goAdd">A</a><span>-</span>
				<a href="${ctx}/home/goDel">V</a><span>-</span>
				<a href="${ctx}/user/main">I</a><span>-</span>
				<a href="${ctx}/druid" target="_blank">N</a><span>-</span>
				<a href="${ctx}/user/goDel">L</a><span>-</span>
				<a href="${ctx}/user/goRegister">A</a><span>-</span>
				<a href="${ctx}/user/exit">U</a>
			</c:if>
			<c:if test="${username!='eavinlau'}">
				<a href="${ctx}/user/main">E</a><span>-</span>
				<a>A</a><span>-</span>
				<a>V</a><span>-</span>
				<a>I</a><span>-</span>
				<a>N</a><span>-</span>
				<a>L</a><span>-</span>
				<a>A</a><span>-</span>
				<a href="${ctx}/user/exit">U</a>
			</c:if>
		</div>
		<p style="margin-top: 10px;" id='copyright'></p>
	</div>
</div>
<script type="text/javascript">
	var year = new Date().getFullYear();
	var copyright = "Copyright © 1990 - "+year+" EavinLau All Rights Reserved";
	document.getElementById('copyright').innerHTML=copyright;
</script>
</body>