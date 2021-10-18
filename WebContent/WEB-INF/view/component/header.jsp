<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<header>
	    <div class="header">
	        <div class="logo-left">
	        	<h3>NoticeSite</h3>
	        	<h1>Bunny</h1>
	        </div>
	        <div class="logo"><img src="images/bunny_tree.PNG"></div>
	        <c:if test="${empty cookie.uid}">
	        <div class="logo-right"><a href="login.jsp">Log In</a></div>
	        </c:if>
	        <c:if test="${!empty cookie.uid}">
	        <div class="logo-right">
	        	<h3>Hello ${cookie.name.value}</h3>
	        	<a href="/logout">Log Out</a>
	        </div>
	        </c:if>
	    </div>
	    <div class="nav">
	        <ul>
	            <li><a href="home.jsp">Home</a></li>
	            <li><a href="freeboard">Free</a></li>
	            <li><a href="#">Tech</a></li>
	            <li><a href="#">About</a></li>
	        </ul>
	    </div>
	</header>
</body>
</html>