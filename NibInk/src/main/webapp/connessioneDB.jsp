<!-- prova.jsp -->
<html>
<body>

<%@ page language="java" import="com.model.*" %>
<%
DAOConnection test=new DAOConnection();
test.setUsername("root");
test.setPassword("password");
String queryExecuted;
 queryExecuted= (request.getParameter("query")!=null) ? request.getParameter("query") : "nessuna query eseguita";
 test.queryInsert(queryExecuted);
%>
 <%= queryExecuted %>
</body>
</html>