<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="constSet.MainConst" %>
<head>
	<style type="text/css">
		
		.cl_div_profileSection{
			padding-bottom: 20px !important;
		}
		.cl_div_profileHeader{
			margin-top: 22px;
			margin-bottom: 22px;
			font-weight: bold;
		}
		.cl_div_profileArticle{
			opacity: 0.6;
			margin-top: -10px;
			margin-bottom: 22px;
			font-weight: bold;
		    font-style: italic;
    		margin-left: 26px;
		}
		.cl_div_profileImg{
			float : right;
		    margin-top: -20px;
			width : 80px;
			height: 80px;
			background-image: url(/AkashicRecords/img/worker.png);
		}
		.cl_div_discordImg{
			float : right;
		    margin-top: -20px;
			width : 80px;
			height: 80px;
			background-image: url(/AkashicRecords/img/discord.png);
		}
		.cl_div_serverImg{
			float : right;
		    margin-top: -20px;
			width : 80px;
			height: 80px;
			background-image: url(/AkashicRecords/img/server.png);
		}
		
	</style>
</head>
<!--| ***body StarT*** |-->
<div class="sel-1">
	
	<div style="max-width: 700px; min-height: 480px;">
		<div id="id_div_seriesListHeader" class="w3-card w3-bar" style="margin-bottom: 40px;" >
			<div class="w3-bar-item">
				<h5>18-09-21 금</h5>
			</div>
			<a class="w3-right w3-bar-item w3-button w3-mobile" id="id_a_writePost" href="#">
				<h5>프로필 수정</h5>
			</a>
		</div>
		<div class="w3-leftbar cl_div_profileSection w3-white w3-card w3-container">
			<div class="w3-container cl_div_profileHeader">
				<p class="w3-medium">풀스택 개발자를 목표로 하는 잉여공대생 멀록입니다</p>
			</div><!-- cl_div_profileHeader -->
			<div class="w3-container cl_div_profileArticle">
				<p class="w3-small">컴공 3학년 잉여공대생, 게임,영드,미드,비행기 다 좋아함.</p>
			</div><!-- cl_div_profileArticle -->
			<div class="cl_div_profileImg imgRanderer"></div>
		</div><!-- cl_div_profileSection -->
		
		<hr class="cl_hr_customHR">
		
		<div class="w3-leftbar cl_div_profileSection w3-white w3-card w3-container">
			<div class="w3-container cl_div_profileHeader">
				<p class="w3-medium">디코ID & 이메일 주소</p>
			</div><!-- cl_div_profileHeader -->
			<div class="w3-container cl_div_profileArticle">
				<p class="w3-small">Discord : Malloc72#1954</p>
				<p class="w3-small">E-mail  : dase1102@gmail.com</p>
			</div><!-- cl_div_profileArticle -->
			<div class="cl_div_discordImg imgRanderer"></div>
		</div><!-- cl_div_profileSection -->
		
		<hr class="cl_hr_customHR">
		
		<div class="w3-leftbar cl_div_profileSection w3-white w3-card w3-container">
			<div class="w3-container cl_div_profileHeader">
				<p class="w3-medium">블로그 서버 세부정보</p>
			</div><!-- cl_div_profileHeader -->
			<div class="w3-container cl_div_profileArticle">
				<p class="w3-small">CPU : Ryzen 1600 6-core processor</p>
				<p class="w3-small">Ram  : DDR4 8G</p>
				<p class="w3-small">WebServer  : Apache-Tomcat</p>
				<p class="w3-small">DB_Server  : DOCKER-MySql-5</p>
				
			</div><!-- cl_div_profileArticle -->
			<div class="cl_div_serverImg imgRanderer"></div>
		</div><!-- cl_div_profileSection -->
		
	</div>	
</div><!-- sel-1 -->

<!--| ###body EnD### |-->