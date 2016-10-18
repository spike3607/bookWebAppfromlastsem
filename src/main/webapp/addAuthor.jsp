<%-- 
    Document   : addAuthor
    Created on : Oct 5, 2015, 7:38:10 PM
    Author     : mschoenauer1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>Add Author</title>
    </head>
    <body>
        <h1>Add New Author</h1>
        <form name="newAuthor" id="newAuthor" method="POST" action="AuthorController?action=add">
            Author Name: 
            <input type="text" id="newName" name="newName" form="newAuthor" placeholder="Enter New Author's Name">
            <br>
            <input type="submit" id="submit" name="submit" value="Add New Author">
        </form>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
