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
		<a href="home">Home</a>
	</header>
	<section class="join-section">
		<form action="joinAction" method="POST" id="join-form">
			<table class="join-table" border="1">
				<tbody>
					<tr>
						<td><input type="text" placeholder="이메일" name="email"></td>
					</tr>
					<tr>
						<td><input type="text" placeholder="이름" name="name"></td>
					</tr>
					<tr>
						<td><input type="password" placeholder="비밀번호" name="pwd"></td>
					</tr>
					<tr>
						<td><input type="password" placeholder="비밀번호 확인" name="repwd"></td>
					</tr>
					<tr>
						<td>
							<input type="radio" id="man" name="gender" value="남">
							<label for="man">남자</label>
							<input type="radio" id="woman" name="gender" value="여">
							<label for="woman">여자</label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" onclick="validCheck()" value="회원가입">
						</td>
					</tr>
					<tr>
						<td>
							<input type="reset" value="다시쓰기">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</section>
</body>
</html>