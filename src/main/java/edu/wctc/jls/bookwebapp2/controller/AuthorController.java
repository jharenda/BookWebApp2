/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.controller;
import edu.wctc.jls.bookwebapp2.model.Author;
import edu.wctc.jls.bookwebapp2.model.AuthorDao;
import edu.wctc.jls.bookwebapp2.model.AuthorService;
import edu.wctc.jls.bookwebapp2.model.DateHelper;
import edu.wctc.jls.bookwebapp2.model.MySqlDbAccessor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jennifer
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    public final String REQ_TYPE = "requestType";
    // page variables   
    public final String HOME_PAGE = "/index.jsp";
    public final String AUTH_LIST_PAGE = "/authorList.jsp";
    public final String ADD_AUTHOR_PAGE = "/addAuthor.jsp";
    public final String EDIT_AUTHOR_PAGE = "/editAuthor.jsp";

    //these are the values "grabbed" from the page
    public final String AUTH_ID = "authorId";
    public final String AUTH_NAME = "authorName";
    public final String DATE_ADDED = "dateAdded";
    public final String AUTH_ID_CBX = "authorId";
    public final String AUTHOR_ID = "id";
    public final String AUTH_TABLE_NAME = "author";
    public final String AUTH_ID_COL = "author_id";
    public final String AUTH_NAME_COL = "author_name";
    public final String DATE_COL = "date_added";

    //different types of requests that may come in from the UI
    public final String AUTHOR_LIST_REQ = "authorList";
    public final String ADD_AUTH_REQ = "addAuthor";
    public final String EDIT_AUTH_REQ = "editAuthor";
    public final String SAVE_REQ = "saveAuthor";
    public final String DELETE_AUTH_REQ = "deleteAuthor";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String destination = HOME_PAGE;
        String req_Action = request.getParameter(REQ_TYPE);

        try {
            AuthorService authorService = new AuthorService(
                    new AuthorDao(
                            new MySqlDbAccessor(), "com.mysql.jdbc.Driver",
                            "jdbc:mysql://localhost:3306/book",
                            "root", "admin")
            );

            switch (req_Action) {
                case AUTHOR_LIST_REQ:
                    destination = AUTH_LIST_PAGE;
                    List<Author> authors = authorService.retrieveAuthors(
                            //should fix the magic number here- 50
                            AUTH_TABLE_NAME, 50);
                    request.setAttribute("authors", authors);
                    break;
                case DELETE_AUTH_REQ:
                    destination = AUTH_LIST_PAGE;
                    String[] authorsToDelete = request.getParameterValues(AUTH_ID_CBX);
                    if (authorsToDelete != null) {
                        for (String id : authorsToDelete) {
                            authorService.deleteAuthorById(AUTH_TABLE_NAME, AUTH_ID_COL, id);
                        }
                    }
                    refreshResults(request, authorService);
                    break;

                case ADD_AUTH_REQ:

                    destination = ADD_AUTHOR_PAGE;

                    break;

             
                case EDIT_AUTH_REQ:
                    destination = EDIT_AUTHOR_PAGE;
                   // String authorName1 = request.getParameter(AUTH_NAME);
                    String id = request.getParameter(AUTHOR_ID);

                   Author author = authorService.retrieveAuthor(AUTH_TABLE_NAME, AUTH_ID_COL, id);
                    request.setAttribute(AUTH_ID, author.getAuth_ID());
                    request.setAttribute(AUTH_NAME, author.getAuth_Name());
                    request.setAttribute(DATE_ADDED, author.getDate());
                    
                    
                    break;
                       case SAVE_REQ:
                    destination = AUTH_LIST_PAGE;

                    String authorName = request.getParameter(AUTH_NAME);
                      String req_id = request.getParameter(AUTH_ID);

                     if (req_id == null || req_id.isEmpty()) {
                    DateHelper dh = new DateHelper();
                    String date = dh.currentDate();

                    List<String> colNames = new ArrayList<>();
                    colNames.add(AUTH_NAME_COL);
                    colNames.add(DATE_COL);
                    List<Object> colValues = new ArrayList<>();
                    colValues.add(authorName);
                    colValues.add(date);

                    authorService.addNewAuthor(AUTH_TABLE_NAME,
                            colNames, colValues);
                     }
                     else {
                          List<String> colNames2 = new ArrayList<>();
                      colNames2.add(AUTH_NAME_COL);
                     List<Object> colValues2 = new ArrayList<>();
                     colValues2.add(authorName);
                      authorService.updateAuthorById(AUTH_TABLE_NAME, colNames2,
                             colValues2, AUTH_ID_COL, req_id);
                     }
                  
                    refreshResults(request, authorService);

                    break;

                case "home":
                    destination = destination = HOME_PAGE;
                    break;
            }
        } catch (Exception e) {
            destination = HOME_PAGE;
            request.setAttribute("errMsg", e.getMessage());
        }
        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private void refreshResults(HttpServletRequest request, AuthorService authorService)
            throws ClassNotFoundException, SQLException {
        List<Author> authors = authorService.retrieveAuthors(
                AUTH_TABLE_NAME, 50);
        request.setAttribute("authors", authors);
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
