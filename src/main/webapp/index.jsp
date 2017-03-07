<%-- 
    Document   : index
    Created on : Feb 25, 2017, 1:19:31 PM
    Author     : Jennifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <title>Book Web App</title>
            <style>
            /* Remove the navbar's default rounded borders and increase the bottom margin */ 
            .navbar {
                margin-bottom: 50px;
             border-radius: 5px;
             background-color: cadetblue;
             color: black; 
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
            body {
            font-size: large;  
            }
        </style>
    </head>
    <div class="c"> 
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
        </div>
     <img src ="owlbook.jpg" width="200" height="120" id="owl" >
    <div class="container">
        <body>
            <jsp:include page ="header.jsp" /> 
            <h1>
                Administrative Author Tasks
            </h1>
            <p id ="listLink">  <a href="AuthorController?requestType=authorList">View Author List</a></p>
                  
       
<footer class="footer">
          <p>&copy; 2016<script>new Date().getFullYear()>2016&&document.write("-"+new Date().getFullYear());</script>, Jennifer Scheidegger.</p>
           
        </footer>  
        
    </div>
 
</body>
</html>
