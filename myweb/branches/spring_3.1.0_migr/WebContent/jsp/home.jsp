<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ include file="include-jstl.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'home.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<ul>
  		<li><a href="handleMessage.html">Message</a></li>
  		<li><a href="handleDynamicMessage.html?message=Hello Dynamic World">Dynamic Message</a></li>
  		<li><a href="listCustomers.html">List Customers</a></li>
  		<li><a href="updateCustomer.html">Update Customer</a></li>
  	</ul>
  </body>
</html>
