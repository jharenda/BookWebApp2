<%-- 
    Document   : editBook
    Created on : Apr 5, 2017, 5:43:04 PM
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
        
        
        <title>Edit Book</title>
    </head>
    <body>
        <h1>Edit a Book:</h1>
        <img src ="owlbook.jpg" width="200" height="120" id="owl" >
            <div class="container"> 
           
            <form id="editBookForm" name="editBookForm" method="POST" action="BookController?requestType=saveBook">
                <table class="table">  

                    <tr>
                        <td>
                            Book ID     
                        </td>
                        <td>
                            <input type="text" id="bookId" name="bookId" readonly="readonly" value="${bookId}">
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Book Name   
                        </td>
                        <td>
                            <input type="text" id="bookName" name="bookName" value="${bookName}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Author Name   
                        </td>
                        <td>
                            <input type="text" id="authorName" name="authorName" value="${authorName}">
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Date Added
                        </td>
                        <td>
                            <input type="text" id="dateAdded" name="dateAdded" readonly="readonly" value="${dateAdded}">
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
