<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<html>
    <head>
        <title>Upload Test</title>
    </head>
    <body>
		<form action="/post" method="post">    
		    <table>
		    <tr><b>Faire une poste</b></tr>
		    <tr><td><input type="text" name="nom" placeholder="nom"></td></tr>
		    <tr><td><input type="text" name="message" placeholder="message"></td></tr>
		    <tr>
		    <td><input type="file" name="idPhoto"></td>
		    <td><input type="submit" value="Submit"></td>
		    </tr>
		    <tr><td></td></tr>
		    <tr><td></td></tr>
		    <tr><td></td></tr>
		    <tr><td></td></tr>
		    <tr><td></td></tr>
		    </table>      
        </form>
        <form action="/pageUser.jsp" method="get">
        <p>
        <label>SearchUser</label>
        <input type="text" name="userR" placeholder="nom utilisateur">
        <input type="submit" value="Submit">
        </p>
        </form>
        <table>
        <tr>
		    <td><button onclick="window.location.href='index.html'">Quitter</button></td>
		    </tr>
		    <tr>
		    <td><button onclick="window.location.href='profil.jsp'">Mon Profil</button></td>
		    </tr>
        </table>
    </body>
</html>