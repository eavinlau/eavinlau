<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="application" />
<head>
<title>EavinLau</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/eavinlau.css">
<script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	function doSearch(){
		var searchurl=$("#searchurl").val();
		var xm=$("#ctx").val();
		var url = "${ctx}/download/search";
		var params = {
		   'searchurl':searchurl
		};
		$.post(url, params, checkResult, 'json');
	}
	
	function checkResult(){
		
	}
</script>
<base target="_blank" />
</head>
<body>
	<header>
		<div class="container">
			<h1>
				<a href="${ctx}"> 
					<span style="color: #6666ff">EavinLau</span>
				</a>
			</h1>
			<div class="head-right">
				<div class="search-head">
					<input id="searchurl" type="text" name="url" placeholder="eavinlau..." autocomplete="off"> 
					<a href="javascript:void(0)" onclick="doSearch()">
						<img src="${ctx}/static/images/search.png">
					</a>
				</div>
			</div>
		</div>
	</header>
<div class="container">
	<div class="footer">
		<div class="foot-link">
			<a href="${ctx}/user/goLogin">E</a><span>-</span>
			<a href="${ctx}/home/goAdd">A</a><span>-</span>
			<a href="${ctx}/home/goDel">V</a><span>-</span>
			<a href="${ctx}/user/main">I</a><span>-</span>
			<a href="${ctx}/druid" target="_blank">N</a><span>-</span>
			<a href="${ctx}/user/goDel">L</a><span>-</span>
			<a href="${ctx}/user/goRegister">A</a><span>-</span>
			<a href="${ctx}/user/exit">U</a>
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