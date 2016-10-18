<%-- 
    Document   : editAuthor
    Created on : Oct 5, 2015, 8:14:43 PM
    Author     : mschoenauer1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>Edit Author</title>
    </head>
    <body>
        <h1>Edit Author</h1>
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
    <br>
    <form name="editAuthor" id="editAuthorName" method="POST" action="AuthorController?action=update&colEdit=name">
        Author ID:
        <input type="number" id="author_id" name="author_id" placeholder="ID #">
        &nbsp;&nbsp;&nbsp;&nbsp;
        Author Name:
        <input type="text" id="new_name" name="new_name" placeholder="Author's New Name">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" id="submitNameChange" value="Change">
    </form>
    <br>
    <form name="editAuthor" id="editAuthorDate" method="POST" action="AuthorController?action=update&colEdit=date">
        Author ID:
        <input type="number" id="author_id" name="author_id" placeholder="ID #">
        &nbsp;&nbsp;&nbsp;&nbsp;
        Date Created:
        <input type="date" id="new_date" name="new_date" placeholder ="Author's New Date">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" id="submitDateChange" value="Change">
    </form>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
