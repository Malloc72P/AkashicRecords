<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="constSet.MainConst" %>

<head>
	<style type="text/css">
		.guestBookFullWrapper{
			margin-top: 50px;
		}
		.adminMsgWrapper{
		    display: block;
		    width: 100%;
		}
		.adminMsgWrapper:after{
			display: block;
			content: '';
			clear: both;
			margin-bottom: 40px;
		}
		.userMsgWrapper{
		    display: block;
		    width: 100%;
		}
		.msgCoupler{
		    margin-bottom: 50px;
		    content: '';
		    display: block;
		}
		.adminFullWrapper{ 
		    display: flex;
		    position: relative;
		    float: right;
		    margin-right: 12px;
		}
		.adminFullWrapper:after {
		    content: '';
		    display: block;
		    clear: both;
		}
		.adminBalloon {
            display: block;
		    position: relative;
		    background: #eae36d;
	        padding: 10px 24px 10px 24px;
		    margin-top: 10px;
		    float: right;
		    margin-right: 22px;
		    border-radius: 5px;
		    margin-bottom: 10px;
		    max-width: 636px;
		}
		.adminBalloon:after {
		    content: '';
		    position: absolute;
		    border-top: 14px solid #eae36d;
		    border-right: 14px solid transparent;
		    border-left: 14px solid #eae36d;
		    border-bottom: 14px solid transparent;
		    top: 5px;
		    right: -22px;
		    clear: both;
		}
		.adminProfileImg{
			background-image: url(/AkashicRecords/img/profile.jpg);
		    width: 48px;
		    height: 48px;
		    border-radius: 50%;
		    top: -18px;
		    left: 6px;
		    position: relative;
		}
		.adminName{
		    position: absolute;
		    right: 60px;
		    top: -30px;
		}
		.guestBookAdminRegDate{
		    position: absolute;
		    left: 0px;
		    top: -13px;
		    opacity: 0.60;
		}
		.userFullWrapper{ 
		    display: flow-root;
		    position: relative;
		    float: left;
		    margin-left: 12px;
		    margin-bottom: 40px;
		}
		.userFullWrapper:after {
		    content: '';
		    display: block;
		    clear: both;
		}
		.userBalloon {
		    display: block;
		    position: relative;
		    background: #ccc;
		    padding: 10px 24px 10px 24px;
		    margin-top: 10px;
		    float: left;
		    margin-left: 71px;
		    border-radius: 5px;
		    margin-bottom: 10px;
		    max-width: 636px;
		}
		.userBalloon:after {
		    content: '';
		    position: absolute;
		    border-top: 14px solid #ccc;
		    border-right: 14px solid #ccc;
		    border-left: 14px solid transparent;
		    border-bottom: 14px solid transparent;
		    top: 5px;
		    left: -22px;
		    clear: both;
		}
		.userProfileImg{
		    background-image: url(/AkashicRecords/img/profile_default.png);
		    width: 48px;
		    height: 48px;
		    border-radius: 50%;
		    top: -18px;
		    left: -5px;
		    position: relative;
		}
		.userName{
		    position: absolute;
		    left: 57px;
		    top: -30px;
		}
		.guestBookUserRegDate{
		    position: absolute;
		    right : 0px;
		    top: -13px;
		    opacity: 0.60;
		}
		
		.modify-img{
			
		}		
		.guestBookProfileName{
			font-weight: 500;
		}
		.guestBook-text{
			background-image: url(/AkashicRecords/img/profile_default.png);
		    width: 12px;
		    height: 12px;
		    border-radius: 50%;
		    border: 1px solid grey;
		}

	</style>
</head>

<!--| ***body StarT*** |-->
<div class="sel-4">
	<div id="id_div_seriesListHeader" class="w3-card w3-bar" style="margin-bottom: 40px;" >
		<div class="w3-bar-item">
			<h5>4 포스트</h5>
		</div>
		<a class="w3-right w3-bar-item w3-button w3-mobile" id="id_a_writePost" href="#">
			<h5>방명록 작성</h5>
		</a>
	</div>
	<div class="guestBookFullWrapper">
		<c:forEach var="i" begin="1" end="3" step="1">
			<div class="msgCoupler">
				<div class="adminMsgWrapper">
					<div class="adminFullWrapper">
						<div class="adminBalloon">
							<p class="guestBook_text w3-large">Response messsage from admin, testing DummyString
							 testing DummyString testing DummyString testing DummyString
							 testing DummyString testing DummyString testing DummyString
							 testing DummyString testing DummyString testing DummyString
							 testing DummyString testing DummyString testing DummyString </p>
						</div>
						<div class="adminName guestBookProfileName"><h5>Malloc72P</h5></div>
						<div class="imgRanderer adminProfileImg" />
						<div class="guestBookAdminRegDate"><p class="w3-small">2018-09-20, 목</p></div>
					</div>
				</div><!-- msgWrapper -->
	
				<div class="userMsgWrapper">
					<div class="userFullWrapper">
						<div class="userBalloon">
							<p class="guestBook-text w3-large">Request messsage from user</p>
						</div>
						<div class="userName guestBookProfileName"><h5>guest</h5></div>
						<div class="imgRanderer userProfileImg"/>
						<div class="guestBookUserRegDate"><p class="w3-small">2018-09-20, 목</p></div>
					</div>
				</div><!-- msgWrapper -->		
			</div><!-- msgCoupler -->
		</c:forEach>
		

</div>
<!--| ###body EnD### |-->