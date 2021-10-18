<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
	<form action="/login.jsp" name="backlogin"></form>
</body>
<script type="text/javascript">
function Request(){
    var requestParam ="";
	 
	//getParameter 펑션
	this.getParameter = function(param){
        //현재 주소를 decoding
        var url = unescape(location.href); 
        //파라미터만 자르고, 다시 &구분자를 잘라서 배열에 넣는다. 
        var paramArr = (url.substring(url.indexOf("?")+1,url.length)).split("&"); 
        
        for(var i = 0 ; i < paramArr.length ; i++){
           var temp = paramArr[i].split("="); //파라미터 변수명을 담음
        
           if(temp[0].toUpperCase() == param.toUpperCase()){
             // 변수명과 일치할 경우 데이터 삽입
             requestParam = paramArr[i].split("=")[1]; 
             break;
           }
        }
        return requestParam;
    }
}

// Request 객체 생성
var request = new Request();
// test 라는 파라메터 값을 얻기
var fail = request.getParameter("fail");

if (fail == "0") {
	alert("비밀번호를 다시 확인해주세요.");
} else if (fail == "-1") {
	alert("이메일을 다시 확인해주세요.");
}
var login = document.backlogin;
login.submit();
</script>
</html>