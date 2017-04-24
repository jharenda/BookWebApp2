<%-- 
    Document   : authorDetails
    Created on : Apr 14, 2017, 7:02:28 PM
    Author     : Jennifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <img src ="owlbook.jpg" width="200" height="120" id="owl" >
            <div class="container"> 
            <h1>
                Enter Author Name to be Edited: 
            </h1>
           
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
                            <input type="text" id="authorName" name="authorName" readonly="readonly"  value="${authorName}">
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
  <tr>
                        <td>
                          Books
                        </td>
                        <td>
                            <input type="text" id="bookSet" name="bookSet" readonly="readonly" value="${bookSet}">
                        </td>
                    </tr>  
                </table>
                <br>
               <sec:authorize access="hasAnyRole('ROLE_MGR','ROLE_USER')">
            Logged in as: <sec:authentication property="principal.username"></sec:authentication> ::
            <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a>
        </sec:authorize>  
            <footer class="footer">
                <p>&copy; 2016<script>new Date().getFullYear() > 2016 && document.write("-" + new Date().getFullYear());</script>, Jennifer Scheidegger.</p>
              
            </footer> 
    </body>
</html>
