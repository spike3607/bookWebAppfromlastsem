<%-- 
    Document   : listAuthors
    Created on : Sep 21, 2015, 9:36:05 PM
    Author     : jlombardo
    Purpose    : display list of author records and (in the future) provide
                 a way to add/edit/delete records
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>Author List</title>
    </head>
    <body>
        <h1>Greetings ${sessionScope.sessionName}</h1>
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Author Name</th>
                <th align="right" class="tableHead">Date Added</th>
                <th align="left" class="tableHead">Delete?</th>
                <th align="left" class="tableHead">Edit?</th>
            </tr>
        <c:forEach var="a" items="${authors}" varStatus="rowCount">
            <c:choose>
                <c:when test="${rowCount.count % 2 == 0}">
                    <tr style="background-color: white;">
                </c:when>
                <c:otherwise>
                    <tr style="background-color: #ccffff;">
                </c:otherwise>
            </c:choose>
            <td align="left">${a.authorId}</td>
            <td align="left">${a.authorName}</td>
            <td align="right">
                <fmt:formatDate pattern="M/d/yyyy" value="${a.dateAdded}"></fmt:formatDate>
            </td>
            <td><a href="AuthorController?action=delete&deleteID=${a.authorId}">delete</a></td>
            <td><a href="AuthorController?action=update&updateID=${a.authorId}">edit</a></td>
        </tr>
        </c:forEach>
        </table>
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
        </c:if>
            <a href="addAuthor.jsp">Add a New Author</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="editAuthor.jsp">Edit an Author</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="index.html">Back to Menu</a>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
