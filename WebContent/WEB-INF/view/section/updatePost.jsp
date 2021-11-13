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
		var content = document.updatePostFrm.content;
		
		if (content.value == "") {
			alert("내용을 입력해주세요");
			content.focus();
		}
		else {
			document.updatePostFrm.submit();
		}
	}
	function reset() {
		alert("실행");
	 	document.updatePostFrm.content.value = "";
	}
</script>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/view/component/header.jsp"></jsp:include>
		<section>
			<div class="screen">
				<h1 class="update-h">글수정</h1>
				<form action="/updatePostAction" method="POST" class="updatePost-frm" name="updatePostFrm">
					<table class="update-table">
						<tr>
							<td>
								<h3 class="update-title">${post.title }</h3>
							</td>
						</tr>
						<tr>
							<td>
								<textarea class="update-content" placeholder="내용" maxlength="2048" rows="10" name="content">${post.content}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<div class="update-files">
									<input class="update-files--content" type="file" placeholder="파일" name="file">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="update-buttons">
									<input class="update-submit" type="button" onclick="validCheck()" value="등록하기">
									<input class="update-reset" type="button" onclick="reset()" value="다시쓰기">
								</div>
							</td>
						</tr>
					</table> 
					<input type="hidden" name="bid" value="${param.bid}"><!-- 게시글 id를 넘겨주기 위한 히든 태그 -->
				</form>
			</div>
		</section>
		<jsp:include page="/WEB-INF/view/component/footer.jsp"></jsp:include>
	</div>
</body>
</html>