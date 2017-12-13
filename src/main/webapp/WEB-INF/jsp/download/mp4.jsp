<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="application" />
<head>
<title>EavinLau</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/eavinlau.css">
<script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.js"></script>
</head>
<body>
	<div class="container">
		<video width="100%" height="100%" controls autoplay loop>
			<source src="${mp4url}" type="video/mp4">
		</video>
	</div>
</body>