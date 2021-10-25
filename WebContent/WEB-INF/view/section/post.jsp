<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/view/component/header.jsp"></jsp:include>
		<section>
			<div class="screen">
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
					<div class="post-comment">
						
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/view/component/footer.jsp"></jsp:include>
	</div>
</body>
</html>