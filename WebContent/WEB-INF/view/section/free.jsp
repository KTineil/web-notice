<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
	<meta charset="UTF-8">
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<script src="/js/search.js"></script>
	<title>자유게시판</title>
</head>
<script>
function search() {
	var value = document.getElementsByName("searchValue")[0].value;
	var url = "freeboard?sv=" + value;
	location.href=url;
}
</script>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/view/component/header.jsp"></jsp:include>
		<section>
	        <div class="screen">
	            <div class="util-box">
	            	<button onclick="location.href='writePost'">글쓰기</button>
	            	<div class="search-box">
		                <i class="fas fa-search"></i>
		                <input type="text" placeholder="Search for" onKeypress="if(window.event.keyCode == 13) {search()}" name="searchValue">
	            	</div>
	            </div>
	            <table class="free-notice">
	                <colgroup>
	                    <col width="7%" />
	                    <col width="50%" />
	                    <col width="13%" />
	                    <col width="15%" />
	                    <col width="10%" />
	                </colgroup>
	                <thead>
	                    <tr>
	                        <th>No</th>
	                        <th>title</th>
	                        <th>name</th>
	                        <th>regDate</th>
	                        <th>hit</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:set var="page" value="${empty param.p ? 1: param.p }"></c:set>
					<c:set var="startPage" value="${page-(page-1)%5}"></c:set>
					<c:set var="endOfPage" value="${endOfPage}"></c:set>
					
					<c:if test="${endOfPage - startPage le 4}">
						<c:set var="lastPage" value="${endOfPage}"></c:set>
					</c:if>
					<c:if test="${endOfPage - startPage gt 4}">
						<c:set var="lastPage" value="${startPage+4}"></c:set>
					</c:if>
					
					<c:forEach var="l" items="${list }" varStatus="stat">
						<tr class="free-notice-content">
							<td>${20*(page-1) + stat.index+1}</td>
							<td><a class="color_link__" href="post?bid=${l.id }">${l.title}</a></td>
							<td>${l.writerName }</td>
							<td><fmt:formatDate value="${l.regDate }" pattern="yyyy-MM-dd"/></td>
							<td>${l.hit }</td>
						</tr>
					</c:forEach>
						<tr style="border-bottom: none;">
							<td colspan="5">
								<div class="free-notice-page">
									<span>${page} / ${endOfPage}</span>
								</div>
							</td>
						</tr>
						<tr style="border-bottom: none;">
							<td colspan="5">
								<div class="free-notice-nav_bar">
								
									<c:if test="${startPage eq 1}">
										<span><i class="fas fa-arrow-circle-left"></i></span>
									</c:if>
									<c:if test="${startPage ne 1}">
										<span><a href="freeboard?p=${startPage-1}"><i class="fas fa-arrow-circle-left"></i></a></span>
									</c:if>
									
									<ul>
										<c:forEach begin="${startPage}" end="${lastPage}" varStatus="stat">
											<li><a class="color_link__" href="freeboard?p=${stat.index}">${stat.index}</a></li>
										</c:forEach>
									</ul>
									
									<c:if test="${endOfPage-(endOfPage-1)%5 eq startPage}">
										<span><i class="fas fa-arrow-circle-right"></i></span>
									</c:if>
									<c:if test="${endOfPage-(endOfPage-1)%5 ne startPage}">
										<span><a href="freeboard?p=${startPage+5}"><i class="fas fa-arrow-circle-right"></i></a></span>
									</c:if>
									
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
		<jsp:include page="/WEB-INF/view/component/footer.jsp"></jsp:include>
	</div>
</body>
</html>