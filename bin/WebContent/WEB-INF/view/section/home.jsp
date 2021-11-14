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

<body>
    <div class="container">
    	<jsp:include page="/WEB-INF/view/component/header.jsp"></jsp:include>
       	<section>
            <div class="screen">
                <div class="home-screen">
                    <div class="home-wise"><h1>Welcome!</h1></div>
                    <table class="home-notice">
                    	<c:forEach var="p" items="${posts}">
                        <tr>
                            <td class="home-notice-container" onclick="location.href='/post?bid=${p.id}';">
                                <div class="home-notice-content">
                                    <span class="home-notice-title">${p.title}</span>
                                    <span class="home-notice-name">${p.writerName}</span>
                                </div>
                                <div class="home-notice-img-container">
                                    <img src="images/bunny.png">
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <img class="home-picture" src="images/smile.png" alt="공고가 없어요">
                </div>
            </div>
        </section>
        <jsp:include page="/WEB-INF/view/component/footer.jsp"></jsp:include>
    </div>
</body>

</html>