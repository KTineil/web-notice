<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<script type="text/javascript" src="js/check.js"></script>
<body>
	<header class="home-header">
		<a href="home.jsp">Home</a>
	</header>
	<section class="join-section">
		<form action="join" method="POST" id="join-form">
			<table class="join-table">
				<colgroup>
					<col width="20%">
					<col width="80%">
					<col width="10%">
				</colgroup>
				<tr>
					<td><label for="email">Email</label></td>
					<td class="email_functions">
						<input type="text" placeholder="Insert your email" required name="email" id="email" onkeydown="">
						<input type="button" value="중복확인" onclick="openUniqueCheck()">
						
					</td>
				</tr>
				<tr>
					<td><label for="pwd">Password</label></td>
					<td><input type="password" placeholder="Insert your password" required name="pwd" id="pwd"></td>
				</tr>
				<tr>
					<td><label for="pwd-check">Check Password</label></td>
					<td><input type="password" placeholder="retry your password" required name="repwd" id="pwd-check"></td>
				</tr>
				<tr>
					<td><label for="name">Name</label></td>
					<td><input type="text" placeholder="Insert your name" required name="name" id="name"></td>
				</tr>
				<tr>
					<td><label>Gender</label></td>
					<td>
						<label for="man">남자</label>
						<input type="radio" value="1" id="man" name="gender">
						<label for="woman">여자</label>
						<input type="radio" value="2" id="woman" name="gender">
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align:center;">
						<input type="button" value="회원가입" onclick="validCheck()">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>