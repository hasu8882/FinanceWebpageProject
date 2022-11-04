/**
 * 
 */
function checkDelete() {
	if (document.frm.pw.value.length === 0) {
		alert("암호를 입력해주세요");
		frm.pw.focus();
		return false;
	}
}
function checkId() {
	if (document.frm.id.value === "") {
		alert("아이디를 입력하여 주세요");
		return;
	}
	var url = "checkId.do?id=" + document.frm.id.value;
	window.open(url, "_black_1", "width=600, height=200");

}

// idcheck.jsp 에서 사용버튼 클릭시, 동작할 함수

function idOk() {
	opener.frm.id.value = document.frm.id.value;
	opener.frm.checkid.value = document.frm.id.value;
	self.close();

}
function checkUpdate() {
	if (document.frm.pw.value.length === 0) {
		alert("암호를 입력해주세요");
		frm.pw.focus();
		return false;
	}
	if (document.frm.pw.value !== document.frm.pw_check.value) {
		alert("암호가 일치하지 않습니다.");
		frm.pw_check.focus();
		return false;
	}

}
function checkJoin() {
	if (document.frm.name.value.length === 0) {
		alert("이름을 입력해주세요");
		frm.name.focus();
		return false;
	}
	if (document.frm.id.value.length === 0) {
		alert("아이디를 입력해주세요");
		frm.id.focus();
		return false;
	}
	if (document.frm.pw.value.length === 0) {
		alert("암호를 입력해주세요");
		frm.pw.focus();
		return false;
	}
	if (document.frm.pw.value !== document.frm.pw_check.value) {
		alert("암호가 일치하지 않습니다.");
		frm.pw_check.focus();
		return false;
	}

	if (document.frm.checkid.value.length === 0) {
		alert("중복체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
	return true;
}

function findid() {
	if (document.frm.name.value.length === 0) {
		alert("이름을 입력해주세요");
		frm.name.focus();
		return false;
	}
	if (document.frm.phone1.value.length === 0) {
		alert("핸드폰 번호를 입력해주세요");
		frm.phone1.focus();
		return false;
	}
	if (document.frm.phone2.value.length === 0) {
		alert("핸드폰 번호를 입력해주세요");
		frm.phone2.focus();
		return false;
	}
	if (document.frm.phone3.value.length === 0) {
		alert("핸드폰 번호를 입력해주세요");
		frm.phone3.focus();
		return false;
	}

}




function findpw() {
	if (document.frm.id.value.length === 0) {
		alert("아이디를 입력해주세요");
		frm.id.focus();
		return false;
	}
	if (document.frm.phone1.value.length === 0) {
		alert("핸드폰 번호를 입력해주세요");
		frm.phone1.focus();
		return false;
	}
	if (document.frm.phone2.value.length === 0) {
		alert("핸드폰 번호를 입력해주세요");
		frm.phone2.focus();
		return false;
	}
	if (document.frm.phone3.value.length === 0) {
		alert("핸드폰 번호를 입력해주세요");
		frm.phone3.focus();
		return false;
	}
	if (document.frm.email.value.length === 0) {
		alert("이메일울 입력해주세요");
		frm.email.focus();
		return false;
	}
}

