<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<title>Insert title here</title>
</head>
<script>
function validCheck() {
	var commentContent = document.commentFrm.commentContent;
	if (commentContent.value == "") {
		alert("댓글을 입력해주세요");
		commentContent.focus();
	}
	else {
		document.commentFrm.submit();
	}
}
</script>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/view/component/header.jsp"></jsp:include>
		<section>
			<div class="screen">
	            <div class="util-box">
	            	<div class="util-buttons">
	            	<c:if test="${post.uid eq cookie.uid.value}">
	            		<div class="util-user--buttons">
	            			<a href="deleteAction?bid=${param.bid}"><i style="color: red;" class="fas fa-trash-alt"></i></a>
	            			<a><i class="fas fa-pencil-alt"></i></a>
	            		</div>
            		</c:if>
	            		<button onclick="location.href='writePost'">글쓰기</button>
	            	</div>
	            </div>
				<div class="post-title">
					<div class="post-title--inform">
						<span class="post-writer">${post.writerName }</span>
						<span class="post-hit"><i class="far fa-eye"></i>${post.hit }</span>
					</div>
					<div class="post-title-content">
						<span class="post-title-date"><i class="far fa-clock"></i>${post.regDate }</span>
						<h3>${post.title }</h3>
					</div>
				</div>
				
				<div class="post-content">
					<span class="post-content--files">첨부자료 : ${post.files }</span>
					<hr class="post--line">
					<span class="post-content--real">${post.content }</span>
				</div>
				
				<div class="post-comment--wrap">
					<div class="post-comment--title">
						<span>댓글</span>
					</div>
					
					<c:forEach var="co" items="${comments}">
					<div class="post-comment">
						<div class="post-comment--box">
							<div class="post-comment-header">
								<div class="post-comment-header--profile">
									<div class="profile-image">
									</div>
									<div>
										<span>${co.name}(${co.email})</span>
										<span>${co.date}</span>
									</div>
								</div>
								<div class="post-comment-header--like">
									<span>like</span>
									<span>unlike</span>
								</div>
							</div>
							<div class="post-comment-section">
								<span>${co.content }</span>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
				<form action="commentAction?bid=${param.bid}" method="POST" name="commentFrm">
					<div class="post-comment--write">
						<textarea class="post-comment--content" name="commentContent" rows="5"></textarea>
						<button class="post-comment--submit" type="button" onclick="validCheck()"><i class="far fa-paper-plane" style="margin-right: 5px;"></i>등록</button>
					</div>
				</form>
			</div>
		</section>
		<jsp:include page="/WEB-INF/view/component/footer.jsp"></jsp:include>
	</div>
</body>
</html>