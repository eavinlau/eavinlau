<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="application" />
<head>
<title>EavinLau</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/eavinlau.css">
<script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/sys/user.js"></script>
<script type="text/javascript" src="${ctx}/static/js/home/home.js"></script>
</head>
<body>
	<header>
		<div class="container">
			<h1>
				<a href="${ctx}"> 
					<span style="color: #2886db">EavinLau</span>
				</a>
			</h1>
			<div class="head-right">
				<div class="search-head">
					<form action="${ctx}/home/search" method="post" id="search_form">
						<input id="search" type="text" name="searchStr" placeholder="eavinlau..." autocomplete="off"> 
						<input id="ctx" type="hidden" value="${ctx}" />
						<button type="button" onclick="submitForm()">
							<img src="${ctx}/static/images/search.png">
						</button>
					</form>
				</div>
			</div>
		</div>
	</header>
	<nav>
		<div class="container">
			<ul>
				<li><a href="${ctx}">首页</a></li>
				<li><a href="javascript:void(0)">频道</a>
					<div class="Submenu">
						<div>
							<a href="${ctx}/home/typeindex?type=l">eavinlau-l</a>
						</div>
						<div>
							<a href="${ctx}/home/typeindex?type=d">eavinlau-d</a>
						</div>
						<div>
							<a href="${ctx}/home/typeindex?type=b">eavinlau-b</a>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</nav>