<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>
ERROR on ServletException<br>
<%
  if(exception != null) {
	  out.println("Exception: "+exception.getMessage());
	  out.println("<br><br>");
	  StackTraceElement[] st = exception.getStackTrace();
	  for(StackTraceElement e: st){
		  out.println(e.toString()+"<br>");
	  }
  	
  }
%>
</body>
</html>