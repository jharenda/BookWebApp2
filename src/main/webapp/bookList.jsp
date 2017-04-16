<%-- 
    Document   : bookList
    Created on : Apr 4, 2017, 6:19:46 PM
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
       
    
        <link rel="stylesheet" href="authorAdmin.css">
   <link href="https://fonts.googleapis.com/css?family=Raleway:500|Roboto+Slab:400,700" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
    <title>JSP Page</title>
         
    </head>
    <body>
       <img src ="owlbook.jpg" width="200" height="120" id="owl" >
     <div class="container"> 
            <h1>
                <p id="heading"> Book Database </p>
            </h1>
            <br><a href="BookController?requestType=home">Home Page</a>
            
            <form id="bookFormDelete" name="bookFormDelete" method="POST" action=<%=response.encodeURL("BookController?requestType=deleteBook")%>
                            <br>            
            <br>
            <div class="table-responsive">
           <table class="table table-bordered, table table-striped, table table-hover">
              
                <tr>
                    <th>
                       
                    </th>
                     <th>
                   
                        Book ID
                    </th>
                    <th>
                         Book Title 
                    </th>
                    <th>
                       ISBN
                    </th>
                    <th>
                         Author
                    </th>
                    <th>Author Id</th>
                    <th>
                      Delete?
                    </th>
                </tr>    
                <c:forEach var="book" items="${books}" varStatus="varStatus">
                  
                    <td>
                                    <button type="submit" formaction="BookController?requestType=editBook&id=${book.bookId}" value="${book.bookId}" name="editBook" id="editBook">Edit</button>
                                     
                                </td>
                                <td>
                                    ${book.bookId}
                                </td>
                                <td>
                                    ${book.title}
                                </td>
                                <td>
                                 ${book.isbn}                               
                                </td>
                                 <td>
                                 <c:choose>
                                            <c:when test="${not empty book.authorId}">
                                                ${book.authorId.authorName}
                                            </c:when>
                                            <c:otherwise>
                                                None
                                            </c:otherwise>
                                        </c:choose>                           
                                </td>
                                <td>${authorId}</td>
                                <td>
                                    <input type="checkbox" name="bookId" value="${book.bookId}">
                                                                    </td>

                                    
                            </tr>     
                              
                </c:forEach>             
            </table>
            </div>
            <div id="buttons">
            <button type="submit" formaction="BookController?requestType=addBook" name="add" id="add">New Book</button>
                <input type="submit" name="delete" id="delete" value="Delete">
</div>
            </form>  
            </form>       
    </body>
</html>
