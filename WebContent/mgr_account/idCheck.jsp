<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	boolean checker = (Boolean)request.getAttribute("emailChecker");
%>

{
	"checker":"<%=checker %>"
}


<%@ taglib
	prefix = "c"
	uri    = "http://java.sun.com/jsp/jstl/core"	
%>
<%@page import="dbConnector.userDAO, constSet.MainConst"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:choose>
	<c:when test="${ loginChecker == true }">
			<script type="text/javascript">
				window.onload = function(){
					alert("로그인 성공");
					location.href="<%=MainConst.PROJECT_NAME%>/mainPage.do";
				}
			</script>
	</c:when>
	
	<c:otherwise>
			<script type="text/javascript">
				window.onload = function(){
					alert("로그인 실패");
					location.href="<%=MainConst.PROJECT_NAME%>/mainPage.do";
				}
			</script>
	</c:otherwise>
</c:choose>

</head>
<body>

</body>
</html>