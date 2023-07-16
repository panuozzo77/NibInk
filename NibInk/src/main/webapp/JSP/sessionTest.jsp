<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cosa c'è dentro la Session?</title>
	<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
    
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Attributo</th>
                <th>Valore</th>
            </tr>
        </thead>
        <tbody>
            <% HttpSession s = request.getSession(); %>
            <% Enumeration<String> attributeNames = s.getAttributeNames(); %>
            <% while (attributeNames.hasMoreElements()) { %>
                <% String attributeName = attributeNames.nextElement(); %>
                <% Object attributeValue = session.getAttribute(attributeName); %>
                <tr>
                    <td><%= attributeName %></td>
                    <td><%= attributeValue %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <button onclick="location.href = '/NibInk/JSP/catalog.jsp';">Sito</button>
    
</body>
</html>
