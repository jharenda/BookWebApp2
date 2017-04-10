<%-- 
    Document   : addBook
    Created on : Apr 5, 2017, 5:42:50 PM
    Author     : Jennifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="authorAdmin.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway:500|Roboto+Slab:400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet"> 
        <title>Add Book</title>
    </head>
    <body>
        <h1>Add a New Book:</h1>
        
        
         <img src ="owlbook.jpg" width="200" height="120" id="owl" >
            <div class="container"> 
            <h1>
                Enter Author Name to be Added: 
            </h1>
            <form id="addBookForm" name="addBookForm" method="POST" action="BookController?requestType=newSave">
                <table class="table">  

                    <tr>
                        <td>
                            Book Title:   
                        </td>
                        <td>
                            <input type="text" id="bookTitle" name="bookTile" value="${bookName}">
                        </td>
                    </tr>
<tr>
                        <td>
                            ISBN:   
                        </td>
                        <td>
                            <input type="text" id="bookIsbn" name="bookIsbn" value="${bookIsbn}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Book Author:   
                        </td>
                        <td>
                            <input type="text" id="bookAuthor" name="bookAuthor" value="${bookAuthor}">
                        </td>
                    </tr>
                   

                </table>
                <br>
                <input type="submit" name="submit" id="submit" value="Submit">

            </form>
            <footer class="footer">
                <p>&copy; 2016<script>new Date().getFullYear() > 2016 && document.write("-" + new Date().getFullYear());</script>, Jennifer Scheidegger.</p>
               
            </footer> 
    </div>  
        
        
        
        
    </body>
</html>
