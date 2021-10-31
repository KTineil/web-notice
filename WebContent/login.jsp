<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header class="home-header">
		<div align="right">
			<a href="home.jsp">Home</a>
		</div>
	</header>
	<section class="login-section">
		
		<form action="loginAction" method="POST" id="login-form">
			<input type="text" placeholder="Email" required name="email">
			<input type="password" placeholder="Password" required name="pwd">
			<input type="submit" value="Log In">
			<div align="right"><a href="join.jsp">회원가입</a></div>
		</form>
	</section>
</body>
</html>