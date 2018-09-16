<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	boolean loginChecker = (Boolean)session.getAttribute("loginChecker");
	String email = null;
	if(loginChecker != false){
		email = (String)session.getAttribute("email");
		System.out.println("loginProc>>> "+email);	
	}
%>
{"loginChecker":"<%=loginChecker %>","email":"<%=email %>"}