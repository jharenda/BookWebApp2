<%-- 
    Document   : authorList
    Created on : Feb 26, 2017, 7:28:06 PM
    Author     : Jennifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>
           Administrative Author List
        </title>
    
        <link rel="stylesheet" href="authorAdmin.css">
   <link href="https://fonts.googleapis.com/css?family=Raleway:500|Roboto+Slab:400,700" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
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
    
       <jsp:include page ="header.jsp" /> 
    
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
                <p id="heading"> Current Authors</p>
            </h1>
            <br><a href="AuthorController?requestType=home">Home Page</a>
            
            <form id="authorFormDelete" name="authorFormDelete" method="POST" action=<%=response.encodeURL("AuthorController?requestType=deleteAuthor")%>
                            <br>            
            <br>
            <div class="table-responsive">
           <table class="table table-bordered, table table-striped, table table-hover">
              
                <tr>
                    <th>
                       
                    </th>
                    
                    <th>
                        Author ID
                    </th>
                    <th>
                         Author Name
                    </th>
                    <th>
                       Date Added
                    </th>
                    <th>
                      Delete?
                    </th>
                </tr>    
                <c:forEach var="author" items="${authors}" varStatus="varStatus">
                  
                    <td>
                                    <button type="submit" formaction="AuthorController?requestType=editAuthor&id=${author.authorId}" value="${author.authorId}" name="editAuthor" id="editAuthor">Edit</button>
                                </td>
                                <td>
                                    ${author.authorId}
                                </td>
                                <td>
                                    ${author.authorName}
                                </td>
                                <td>
                                    <fmt:formatDate pattern="M/d/yyyy" value="${author.dateAdded}"/>                                 
                                </td>
                                <td>
                                    <input type="checkbox" name="authorId" value="${author.authorId}">
                                                                    </td>

                                    
                            </tr>     
                              
                </c:forEach>             
            </table>
            </div>
            <div id="buttons">
            <button type="submit" formaction="AuthorController?requestType=addAuthor" name="add" id="add">New Author</button>
                <input type="submit" name="delete" id="delete" value="Delete">
</div>
            </form>  
            </form>       
            <br>
            <br>
           <p>Visits to this page this session: ${sessionListPageVisits}</p>
           
        </body>
  <footer class="footer">
          <p>&copy; 2016<script>new Date().getFullYear()>2016&&document.write("-"+new Date().getFullYear());</script>, Jennifer Scheidegger.</p>
          
        </footer> 
    </div>

</html>