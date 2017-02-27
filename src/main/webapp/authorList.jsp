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
    <div class="container"> 
        <body>
            <h1>
                <p id="heading"> Current Authors</p>
            </h1>
            <br><a href="AuthorController?requestType=home">Home Page</a>
            
            <form id="authorFormDelete" name="authorFormDelete" method="POST" action="AuthorController?requestType=deleteAuthor">  
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
                                    <button type="submit" formaction="AuthorController?requestType=editAuthor&id=${author.auth_ID}" value="${author.auth_ID}" name="editAuthor" id="editAuthor">Edit</button>
                                </td>
                                <td>
                                    ${author.auth_ID}
                                </td>
                                <td>
                                    ${author.auth_Name}
                                </td>
                                <td>
                                    <fmt:formatDate pattern="M/d/yyyy" value="${author.date}"/>                                 
                                </td>
                                <td>
                                    <input type="checkbox" name="authorId" value="${author.auth_ID}">
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
            
        </body>
  <footer class="footer">
          <p>&copy; 2016<script>new Date().getFullYear()>2016&&document.write("-"+new Date().getFullYear());</script>, Jennifer Scheidegger.</p>
            <form class="form-inline">Get deals:
                <input type="email" class="form-control" size="50" placeholder="Email Address">
                <button type="button" class="btn btn-danger">Sign Up</button>
            </form>
        </footer> 
    </div>

</html>