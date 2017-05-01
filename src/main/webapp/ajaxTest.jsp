<%-- 
    Document   : ajaxTest
    Created on : May 1, 2017, 5:38:07 PM
    Author     : Jennifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajax Example</title>
    </head>
    <body>
  
        <title>Authors</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/styles.css" /> 
    </head>
    <body>
        <h1>Books Ajax</h1>
        <p class="errMsg">${errMessage == null ? "" : "Sorry, there was a problem: " + errMessage}</p>

        <div class="header">
            <!-- These items are handled by app.js -->
            <input type="text" id="searchKey"/>
            <button id="btnSearch">Search</button>
            <button id="btnAdd">New Book</button>
        </div>

        <div class="leftArea">
            <ul id="hotelList">
            </ul>
        </div>

        <form id="authorForm">

            <div class="mainArea">

                <label>Id:</label>
                <input id="authorId" name="authorId" type="text" readonly />

                <label>Name:</label>
                <input type="text" id="authorName" name="authorName" required>

                

                <button id="btnSave" name="Update" type="button" value="Save">Save</button>
                <button id="btnDelete" name="Update" type="button" value="Delete">Delete</button>

            </div>

            <div class="rightArea">

               

                <label>Notes:</label>
                <textarea id="description" name="description"></textarea>
            </div>

        </form>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="javaScript/app.js"></script>
    </body>
</html>
