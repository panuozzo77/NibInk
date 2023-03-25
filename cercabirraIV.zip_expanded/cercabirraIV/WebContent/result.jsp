<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Collecting Beer Info</title>
 <link href="style.css" rel="stylesheet">
</head>
<body>
<h1 align="CENTER">Beer Selection Page</h1>
<form ACTION="SelectBeer" method="get">
	Select beer characteristics<p> Color:
	<select name="color" size="1">
		<option value="light"> light </option> 
		<option value="amber"> amber </option> 
		<option value="brown"> brown </option> 
		<option value="dark"> dark </option>
	</select>
  <input type="submit">
</form>


	<h1>Beer Recommendations JSP</h1>
	<p>
		<%
			List<?> styles = (List<?>) request.getAttribute("styles");
			
			if(styles != null) {	
		    Iterator<?> it = styles.iterator();
			int i=1;
			while (it.hasNext()) {
	     %>		
			<br>try: <%=i%> <%= it.next()%>
		 <% 
		    i++;
			}
			}
		 %>
</body>
</html>

