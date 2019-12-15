<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.google.appengine.api.users.*" %>

<% UserService userService = UserServiceFactory.getUserService(); %>

<!DOCTYPE html>

<html>
	<head>
		<title>Application App Engine</title>
		<meta charset="utf-8" />
	</head>

	<body>
		<h1>Bienvenue dans mon application</h1>

			<p><a href="<%= userService.createLoginURL("/pageAjoutPhoto.jsp") %>">Se connecter</a></p>			
	</body>
</html>