<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function validCheck() {
		var title = document.writePostFrm.title;
		var content = document.writePostFrm.content;
		
		if (title.value == "") {
			alert("제목을 입력해주세요");
			title.focus();
		}
		else if (content.value == "") {
			alert("내용을 입력해주세요");
			content.focus();
		}
		else {
			document.writePostFrm.submit();
		}
	}
</script>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/view/component/header.jsp"></jsp:include>
		<section>
			<div class="screen">
				<h1 class="write-h">글작성</h1>
				<form action="/writePostAction" method="POST" class="writePost-frm" name="writePostFrm">
					<table class="write-table">
						<tr>
							<td>
								<input class="write-title" placeholder="제목" name="title">
							</td>
						</tr>
						<tr>
							<td>
								<textarea class="write-content" placeholder="내용" maxlength="2048" rows="10" name="content">
									
								</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<div class="write-files">
									<input class="write-files--content" type="file" placeholder="파일" name="files">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="write-buttons">
									<input class="write-submit" type="button" onclick="validCheck()" value="등록하기">
									<input class="write-reset" type="reset" value="다시쓰기">
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</section>
		<jsp:include page="/WEB-INF/view/component/footer.jsp"></jsp:include>
	</div>
</body>
</html>