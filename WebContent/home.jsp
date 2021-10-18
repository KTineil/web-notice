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
                    <div class="home-wise"><h1>배움에는 끝이없다!</h1></div>
                    <table class="home-notice">
                    	<c:forEach begin="0" end="4">
                        <tr>
                            <td class="home-notice-container" onclick="location.href='#';">
                                <div class="home-notice-content">
                                    <span class="home-notice-title">Hello? What are you doing now?</span>
                                    <span class="home-notice-name">h1</span>
                                </div>
                                <div class="home-notice-img-container">
                                    <img src="C:\Users\gurdn\Desktop\ui\images\bunny_tree.PNG">
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <img class="home-picture" src="C:\Users\gurdn\Desktop\ui\images\bunny_tree.PNG">
                </div>
            </div>
        </section>
        <jsp:include page="/WEB-INF/view/component/footer.jsp"></jsp:include>
    </div>
</body>

</html>