<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>

<html>
	<head>
		<title>TinyInsta</title>
		<meta charset="utf-8" />
	</head>

	<body>
	
	<b>Liste des utilisateurs</b>

		<%
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		 Query q = new Query("Users");
        List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

			for (Entity user : results) {
		%>
		<figure>
		    <figcaption>
		    <p><%= user.getProperty("nom") %></p>
		    <p><%= user.getProperty("message") %></p>
		    </figcaption>
			<img alt="" src="images/<%=user.getProperty("urlPhoto") %>">
			<p>
			<input type="submit" value="suivre">
			<input type="submit" value="aimer">
			</p>
			<hr width="50%" color="blue" align=left>
		</figure>
		<%
			}
		%>
	</body>
</html>