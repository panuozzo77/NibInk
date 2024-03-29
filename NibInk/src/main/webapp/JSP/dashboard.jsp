<%@ page import="com.model.DAOOrder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="it">
<%
    DAOOrder daoOrder = new DAOOrder();
    int openOrdersCount = daoOrder.getOpenOrdersCount("pending");

    //DAOMessage daoMessage = new DAOMessage();
    //int messagesCount = daoMessage.getMessagesCount();

    //DAOTransaction daoTransaction = new DAOTransaction();
    //double earnings = daoTransaction.getMonthlyEarnings();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormat.format(new Date());
%>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/NibInk/CSS/admin.css">
<title>ADMIN - Dashboard</title>
<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
<body>
<%//Admin check
String admin = (String) request.getSession().getAttribute("userType");
if (admin != null && !admin.equals("admin")) {
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
%>
<div class="navbar">
	<jsp:include page="adminNavbar.jsp"/>
</div>
    <div class="dashboard-section">
        <h2>Open Orders</h2>
        <p>Number of open orders: <%= openOrdersCount %></p>
    </div>
</body>
</html>
