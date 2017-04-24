<%-- 
    Document   : editAuthor
    Created on : Feb 26, 2017, 7:29:02 PM
    Author     : Jennifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="authorAdmin.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway:500|Roboto+Slab:400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">        
        <title>
            Edit Author
        </title>
    </head>
    
        <body>
                <style>
            /* Remove the navbar's default rounded borders and increase the bottom margin */ 
            .navbar {
                margin-bottom: 50px;
               border-radius: 5px;
            }

            /* Remove the jumbotron's default bottom margin */ 
            .jumbotron {
                margin-bottom: 0;
            }

            /* Add a gray background color and some padding to the footer */
            footer {
                background-color: #f2f2f2;
                padding: 25px;
            }
        </style>
    </head>
    
        <nav class="navbar navbar-inverse">
        <div class="container-fluid" id="nav">
            <div class="navbar-header">
                 
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>                        
                </button>
                <a class="navbar-brand" href="#">Book Web App!</a>
              
            </div>
            <div id ="div">
            <div class="collapse navbar-collapse" >
                <ul class="nav navbar-nav">
                    <li class="active" id="myNavbar"><a href="#">Home</a></li>
                    <li><a href="#">Products</a></li>
                    <li><a href="#">Deals</a></li>
                    <li><a href="#">Locations</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
                </ul>
                </div>
               
            </div>
            
        </div>
             
        </nav>
     <img src ="owlbook.jpg" width="200" height="120" id="owl" >
            <div class="container"> 
            <h1>
                Enter Author Name to be Edited: 
            </h1>
            <form id="editAuthorForm" name="editAuthorForm" method="POST" action="AuthorController?requestType=saveAuthor">
                <table class="table">  

                    <tr>
                        <td>
                            Author ID     
                        </td>
                        <td>
                            <input type="text" id="authorId" name="authorId" readonly="readonly" value="${authorId}">
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

