function validCheck(){
	var email = document.getElementById("join-form").email;
	var pwd = document.getElementById("join-form").pwd;
	var repwd = document.getElementById("join-form").repwd;
	var name = document.getElementById("join-form").name;
	var gender = document.getElementById("join-form").gender;
	if (email.value == "") {
		alert("이메일을 입력하여 주세요.");
		email.focus();
	} else if (pwd.value == "") {
		alert("비밀번호을 입력하여 주세요.");
		pwd.focus();
	} else if (pwd.value.length < 8) {
		alert("비밀번호를 8자 이상으로 입력하여 주세요.");
		pwd.focus();
	} else if (pwd.value != repwd.value) {
		alert("비밀번호를 동일하게 입력하여 주세요.");
		pwd.focus();
	} else if (name.value == "") {
		alert("이름을 입력하여 주세요.");
		name.focus();
	} else if (gender.value == null || gender.value == "") {
		alert("성별을 선택하여 주세요.");
	} else {
		document.getElementById("join-form").submit();
	}
}