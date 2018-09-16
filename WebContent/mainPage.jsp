<%@ taglib 
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="constSet.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>Skynet765's Akashic Records</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="myStyle/myStyle.css">
	<link rel="stylesheet" href="myStyle/main_mediaSet.css">
	<link rel="stylesheet" href="myStyle/css_preset.css">
	<link rel="stylesheet" href="myStyle/subSection.css">
	<link rel="stylesheet" href="https://cdn.iconmonstr.com/1.3.0/css/iconmonstr-iconic-font.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.2.0/anime.js"></script>
	<script src="<%=MainConst.PROJECT_NAME%>/js/jsConstData.js"></script>
  	<script src="<%=MainConst.PROJECT_NAME%>/js/mainPageJS.js"></script>
  	<script src="<%=MainConst.PROJECT_NAME%>/js/mgr_account_MK2.js"></script>
  	<script src="<%=MainConst.PROJECT_NAME%>/js/animeBinder.js"></script>
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		body{
			background: white;
			display: none;
		}
		/*--| ###CSS-StylesheeT EnD### |--*/
		
	</StylE>

	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		$(document).ready(function(){
			//로그인 패널 이벤트 바인더
			bind_Open_Panel("id_a_openLogin"  , "id_div_loginPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_h2_loginCloser" , "id_div_loginPanel" , "id_div_mainContent")
			bind_Close_Panel("id_btn_loginGoBack" , "id_div_loginPanel" ,"id_div_mainContent")
			
			//리지스터 패널 이벤트 바인더
			bind_Open_Panel("id_a_openRegister"  , "id_div_registerPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_h2_registerCloser" , "id_div_registerPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_btn_registerGoBack" , "id_div_registerPanel" ,"id_div_mainContent")
			
			//로그아웃 기능 이벤트 바인더
			logout_eventBinder("id_a_logout")
			register_eventBinder("reg_btn_submit")
			
			//페이지 로드 이펙트 바인더
			$("body").fadeIn(500)
			var post_anime = bind_post_Anime("sub-section" , "post-wrapper")
			
			//FUNCTION 셀렉팅 애니메이션 바인더
			bind_Select_Function("id_div_funcList" , "id_div_subSection" , "id_tempData_in_pageNum")
			
			/* binder_clickRecentPosts($("#sel-2") , $("#id_div_subSection")) */
		})
		
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>
 <body>
	<!--| ***body StarT*** |-->
		<!-- 아카식 사이드바 -->
			<div id="akashicSideBar" class="main-sidebar w3-sidebar w3-bar-block <%=MainConst.THEME_COLOR_MAIN%> w3-card w3-animate-right" style="right: 0px; display: none;"> <!-- 아카식 사이드바 -->
				<c:choose>
				
					<c:when test="${email != null}">
						<div id="id_div_loggedIn_sidebar">
							<h3 class="w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<h4 id="id_div_sidebar_email" class="w3-bar-item">${ uData.getEmail() }</h4>
							<h4>
								<a  id="id_a_logout" class="w3-bar-item w3-button">logout</a>
							</h4>
						</div>
						<div id="id_div_notLoggedIn_sidebar" style="display:none;">
							<h3 class="chk-side-on w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<a class="w3-bar-item w3-button" id="id_a_openLogin">Login</a>
							<a class="w3-bar-item w3-button" id="id_a_openRegister" >Register</a>
						</div>
					</c:when>
					
					<c:otherwise>
						<div id="id_div_loggedIn_sidebar" style="display:none;">
							<h3 class="w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<h4 class="w3-bar-item" id="id_div_sidebar_email">${ uData.getEmail() }</h4>
							<h4><a  class="w3-bar-item w3-button" id="id_a_logout">logout</a></h4>
						</div>
						<div id="id_div_notLoggedIn_sidebar">
							<h3 class="chk-side-on w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<a class="w3-bar-item w3-button" id="id_a_openLogin">Login</a>
							<a class="w3-bar-item w3-button" id="id_a_openRegister" >Register</a>
						</div>
					</c:otherwise>
				</c:choose>			
			</div>
	<!-- 아카식 사이드바 -->
	<!-- 아카식 네비바-->
		<div class="main-navibar w3-bar <%=MainConst.THEME_COLOR_MAIN%>"><!-- 상단바 -->
			<a href="#" class="w3-bar-item" style="text-decoration: none;">
				<h3>Akashic Records Mk.15</h3>
			</a>
			
			<div class="chk-side-on m_mgr_account">
			<c:choose>
				<c:when test="${email != null}">
					<button id="id_btn_emailOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button" onclick="w3_open()">
						<h3 id="id_h3_openerContent" class="chk-side-on w3-bar-item">${ uData.getEmail() }</h3>
					</button>
					<button id="id_btn_keyOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button c_sidebar_toggle_key" style="display:none;"  onclick="w3_open()">
						<h3 class="chk-side-on" ><i class="chk-side-on im im-key"></i></h3>
					</button>
				</c:when>
				<c:otherwise>
					<button id="id_btn_emailOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button" style="display:none;" onclick="w3_open()">
						<h3 id="id_h3_openerContent" class="chk-side-on w3-bar-item">${ uData.getEmail() }</h3>
					</button>
					<button id="id_btn_keyOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button c_sidebar_toggle_key" onclick="w3_open()">
						<h3 class="chk-side-on" ><i class="chk-side-on im im-key"></i></h3>
					</button>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		
<!-- 아카식 네비바 -->

<!-- 아카식 메인컨텐츠 -->
	<div id="id_div_mainContent" class="cl_div_mainContent boxShadow-lite">


		
		<!-- 아카식 배너 이미지 -->
		<div class="imgRanderer banner-img w3-sepia">
			<div class="profile-img imgRanderer w3-normal">
				<h4 class="profile-name">Guest</h4>
			</div>
		</div>
		<!-- 아카식 배너 이미지 -->
		
		
		
		<!-- 아카식 컨텐츠 래퍼 -->
		<div class="main-section">
		
		<!-- 아카식 FUNCTION-SELECTOR -->
		<div id="id_div_funcList" class="func-list w3-bar">
				<!-- func-element-1 -->
				<a id="sel-1"  class="w3-bar-item w3-button func-element1 w3-mobile" href="#sel-1" style="width:20%;" ><h4>Profile</h4></a>
				
				<!-- func-element-2 -->
				<a id="sel-2"  class="w3-bar-item w3-button func-element2 w3-mobile" href="#sel-selected" style="width:20%;" ><h4>Post</h4></a>
				
				<!-- func-element-3 -->
				<a id="sel-3"  class="w3-bar-item w3-button func-element3 w3-mobile" href="#sel-3" style="width:20%;" ><h4>post-list</h4></a>
				
				<!-- func-element-4 -->
				<a id="sel-4"  class="w3-bar-item w3-button func-element4 w3-mobile" href="#sel-4" style="width:20%;" ><h4>Web-Tools</h4></a>
				
				<!-- func-element-5 -->
				<a id="sel-5"  class="w3-bar-item w3-button func-element5 w3-mobile" href="#sel-5" style="width:20%;" ><h4>Preferences</h4></a>
			
		</div>
		<!-- 아카식 FUNCTION-SELECTOR -->
		
		
		<!-- 아카식 SUB-SECTION -->
		
		<div id="id_div_subSection" class="sub-section" style="margin-bottom: 80px;">
			
		</div>
		
		<form id="id_form_tempData" name="form_tempData">
			<input id="id_tempData_in_pageNum"  type="hidden" value="1">
		</form>
		
		<!-- 아카식 SUB-SECTION -->
		
		
		</div><!-- wrapper-content -->
		
		

	</div><!-- id_div_mainContent -->
<!-- 아카식 메인컨텐츠 -->

	<!--| ###body EnD### |-->
		<div style="margin-bottom: 193px;"></div> <!-- 아카식 FOOTER -->
		<div class="w3-bar <%=MainConst.THEME_COLOR_MAIN%>" style="text-align: right; width: 100%;font-weight: bold; border-top: 1px solid #cbcbcb; bottom: 0px;">
			<h3 class="w3-bar-item w3-right">Akashic Records</h3>
		</div>
		<!-- AKASHIC MUSIC MODULE -->
<%-- 			<div class="w3-container <%=MainConst.THEME_COLOR_MAIN%>" style="position: fixed; width: 100%; bottom: 0; left: 0;">
				<!-- SCM Music Player http://scmplayer.co -->
				<script type="text/javascript" src="<%=MainConst.PROJECT_NAME%>/SCM-Music-Player/script.js" 
				data-config="{'skin':'skins/black/skin.css',
							  'volume':40,
							  'autoplay':true,
							  'shuffle':false,'repeat':1,
							  'placement':'bottom',
							  'showplaylist':false,
							  'playlist':[{'title':'Anime Piano Music','url':'http://youtu.be/OgP3_aeh0U4'}]}" >
				</script>
				<!-- SCM Music Player script end -->
			</div> --%>
		
	
	
		<!-- popPannel_login -->
			<jsp:include page="mgr_account/login.jsp"></jsp:include>
			
		<!-- popPannel_register -->
			<jsp:include page="mgr_account/register.jsp"></jsp:include>
 </body>
</html>