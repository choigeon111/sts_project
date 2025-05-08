function fn_submit() {
	let frm = document.frm;
	
	let id = frm.id.value;
	let pwd = frm.id.value;
	let name = frm.id.value;
	let email = frm.id.value;
	
	if(id.length == 0 || id == "") {
		alert("아이디가 입력되지 않았습니다. 다시 입력하세요.");
		frm.id.focus();
	} else if (pwd.length == 0 || pwd == "") {
		alert("암호가 입력되지 않았습니다. 다시 입력하세요.");
		frm.pwd.focus();
	} else if (name.length == 0 || name == "") {
		alert("이름이 입력되지 않았습니다. 다시 입력하세요.");
		frm.name.focus();
	} else if (email.length == 0 || email == "") {
		alert("이메일이 입력되지 않았습니다. 다시 입력하세요.");
		frm.email.focus();
	} else {
		frm.action = "/member/joinMember";
		frm.method = "post";
		frm.submit();
	}
}