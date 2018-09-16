var xhr = null;
/******************************************************************
 * EVENT-BINDER FUNCTION
 *******************************************************************/
function login_eventBinder(id_btn_loginFunc){	
	$("#"+id_btn_loginFunc).click(function(event){
		var validator = true
		event.preventDefault()
		
		//공백문자열 체크
		if( (validator = emptyValidator_Login()) == false ){
			return validator
		}
		var PARAM_email    = $('#email').val()
		var PARAM_password = $('#password').val()
		console.log("PARAM_email >>> " + PARAM_email)
		console.log("PARAM_password >>> " + PARAM_password)
		loginAjax(PARAM_email , PARAM_password)
				
		console.log("login_eventBinder >>> validator >>> " + validator)
		return validator
	})
}
function register_eventBinder(id_trigger){
	$("#"+id_trigger).click(function(event){
		var validator = true
		event.preventDefault()
		
		//공백문자열 체크
		if( (validator = emptyValidator_Register()) == false ){
			return validator
		}
		if( (validator = passwordValidator("reg_password","reg_passwordCheck")) == false ){
			return validator
		}
		//변수에 input필드의 값들을 저장한다.
		var PARAM_email    = $('#reg_email').val()
		var PARAM_password = $('#reg_password').val()
		var PARAM_nickname = $('#reg_nickname').val()
		console.log("PARAM_email >>> " + PARAM_email)
		console.log("PARAM_password >>> " + PARAM_password)
		console.log("PARAM_nickname >>> " + PARAM_nickname)
		
		//저장한 정보를 서버에 ajax를 통해서 전송한다
		registerAjax(PARAM_email , PARAM_password, PARAM_nickname)
	})
}
function logout_eventBinder(id_trigger){
	$("#"+id_trigger).click(function(){
		logoutAjax()
	})
}

/******************************************************************
 * VALIDATOR FUNCTION
 *******************************************************************/
function emptyValidator_Login(){
	var validator = true
	
	validator = emptyStrChecker('password')
	validator = emptyStrChecker('email')
	
	return validator
}
function emptyValidator_Register(){
	var validator = true
	validator = emptyStrChecker('reg_nickname')
	validator = emptyStrChecker('reg_passwordCheck')
	validator = emptyStrChecker('reg_password')
	validator = emptyStrChecker('reg_email')
	
	return validator
}
function passwordValidator(str_pwd1 , str_pwd2){
	var $pwd1 = $('#'+str_pwd1)
	var $pwd2 = $('#'+str_pwd2)
	var $pwd2_Err = $('#'+str_pwd2+'_err_2')
	if( $pwd1.val() == $pwd2.val()){
		$pwd2_Err.hide()
		return true
	}
	else{
		$pwd1.focus
		$pwd2_Err.show()
		return false
	}
}

function emptyStrChecker(tgt){//공백에러 >>> err_1
	
	var $tgtObj = $('#'+tgt)
	var $tgtErr = $('#'+tgt+'_err_1')
	if( $tgtObj.val() == "" ){
		$tgtObj.focus()
		$tgtErr.show()
		return false
	}
	else{
		$tgtErr.hide()
		return true
	}
}
/******************************************************************
 * AJAX-JQUERY FUNCTION
 *******************************************************************/
/*
 * 	login ajax function
 * 	email, password를 loginProc로서 서버에 전송한다
 * */
function loginAjax(email , password){
	$.ajax(
		{ 
			method : "post",
			url    : AKASHIC.URL+AKASHIC.PROJECT+"/loginProc.do",
			data   : { "email":email, "password":password },
			cache  : false
		}
	)
		.done(function(result){
			var jsonRes = JSON.parse(result)
			//서버로부터 받은 데이터를 json으로 파싱한다
			// 그러면 loginChecker랑 email data를 얻는다
			//		전자는 로그인 성공여부를, 후자는 email문자열값을 가진다
			if(jsonRes.loginChecker=="true"){//로그인 성공시
				panel_fadeOut("id_div_loginPanel")
				bluroff_Tag("id_div_mainContent")
				redraw_LoggedIn_Sidebar(jsonRes.email)
			}
			else{//로그인 실패시
				alert("로그인실패")
			}
		})//done
}//function submitAjax
/*
 * 	login function
 * 	email, password를 loginProc로서 서버에 전송한다
 * */
/*
 * 	register ajax function
 * 	email, password, nickname을 registerProc로서 서버에 전송한다
 * */
function registerAjax(email , password ,nickname){
	$.ajax(
		{ 
			method : "post",
			url    : AKASHIC.URL+AKASHIC.PROJECT+"/registerProc.do",
			data   : { "email":email, "password":password, "nickname":nickname },
			cache  : false
		}
	)
		.done(function(result){
			var jsonRes = JSON.parse(result)
			//받은 json data엔 insertChecker가 있다
			//이걸로 db로의 데이터 입력이 성공적으로 이루어졌는지를 알 수 있다. 
			console.log(jsonRes.insertChecker)
			if(jsonRes.insertChecker == "true"){//성공한 경우
				alert("성공적으로 가입되었습니다")
				panel_fadeOut("id_div_registerPanel")
				bluroff_Tag("id_div_mainContent")
			}
			else{//실패한 경우
				alert("부정한 조작이 감지되었습니다 >>> 회원가입 실패")
			}
		})//done
}//function submitAjax

function logoutAjax(){
	$.ajax(
			{
				method : "get",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/logout.do",
				cache  : false
			}
	)
		.done(function(result){
			redraw_LoggedOff_Sidebar()
		})
}

/************************************
 * AKASHIC-RECORD-AJAX RENDERING MODULE MK1 
************************************/
function redraw_LoggedIn_Sidebar(str_email){
	console.log("redraw_LoggedIn_Sidebar >>> " +str_email )
	
	//1. 로그인버전 사이드바를 보여주고
	$("#id_div_loggedIn_sidebar").css("display","block")
	
	//2. 비로그인버전 사이드바를 숨긴다
	$("#id_div_notLoggedIn_sidebar").css("display","none")
	
	//3.로그인버전 사이드바의  이메일부분을 서버로부터 받은 데이터로 바꿔준다 
	$("#id_div_sidebar_email").text(str_email)
	
	//4.상단바의 오프너의 컨텐츠를 3번에서 넣었던 데이터로 바꿔준다
	$("#id_h3_openerContent").text(str_email)
	
	//5.상단바의 오프너의 종류를 키버전에서 이메일버전으로 전환해준다
	$("#id_btn_emailOpener_sidebar").css("display","block")
	$("#id_btn_keyOpener_sidebar").css("display","none")
}
function redraw_LoggedOff_Sidebar(){
	//1. 로그인버전 사이드바를 숨기고
	$("#id_div_loggedIn_sidebar").css("display","none")
	
	//2. 비로그인버전 사이드바를 보여준다
	$("#id_div_notLoggedIn_sidebar").css("display","block")
	
	//3.로그인버전 사이드바의  이메일부분을 지운다 
	$("#id_div_sidebar_email").text("")
	
	//4.상단바의 오프너의 컨텐츠 역시 지운다
	$("#id_h3_openerContent").text("")
	
	//5.상단바의 오프너의 종류를 이메일버전에서 키버전으로 바꿔준다
	$("#id_btn_emailOpener_sidebar").css("display","none")
	$("#id_btn_keyOpener_sidebar").css("display","block")
	
	
}

