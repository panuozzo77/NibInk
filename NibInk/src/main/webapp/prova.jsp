<!-- prova.jsp -->
<html>
<body>

<%@ page language="java" import="com.Model.*" %>
<%DAOConnection test=new DAOConnection();
test.setUsername("root");
test.setPassword("password");
String error = "nessun errore";
 try{ test.sqlConnection();
 } catch(Error e){
	 error=e.getMessage();
 }
 %>
 <%= error %>
</body>
</html>