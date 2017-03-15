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
import edu.wctc.jls.bookwebapp2.model.DbAccessor;
import edu.wctc.jls.bookwebapp2.model.IAuthorDao;
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
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Jennifer
 */
//servlets can use annoations, serciurity and timeouts can't use annotatins- have to use web.xml
//annotations only work in 6 or higher- sensidble defaults, easer than xml
// a lot of stuff we have to use web xml for though- always have it available as an option
//web xml and annotations can co-exist together
// choosing which one depends on what features you might like
// two wizards for mml 
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    public final String REQ_TYPE = "requestType";
    // page variables   
    public final String HOME_PAGE = "/index.jsp";
    public final String AUTH_LIST_PAGE = "/authorList.jsp";
    public final String ADD_AUTHOR_PAGE = "/addAuthor.jsp";
    public final String EDIT_AUTHOR_PAGE = "/editAuthor.jsp";
    public final String INFO_PAGE = "/adminInfo.jsp";
     public final String HELP_PAGE = "/session.jsp";

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
    public final String HELP = "help";
    public final String VIEW_EMAIL_REQ = "viewEmail";
    public final String EDIT_COUNT = "";
    public final String LIST_COUNT = "";

    private String driverClass;
    private String url;
    private String userName;
    private String password;

    private String dbStrategyClassName;
    private String daoClassName;
    private String jndiName;
    private int sessionListPageVisits = 0;
    private int sessionEditPageVisits = 0;
    

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
        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();

        String destination = HOME_PAGE;
        String req_Action = request.getParameter(REQ_TYPE);
        ServletContext sc =
      getServletConfig().getServletContext();
    String adminEmail = sc.getInitParameter("admin email");

        try {
            //AuthorService authorService = new AuthorService(
            //  new AuthorDao(
            //new MySqlDbAccessor(), "com.mysql.jdbc.Driver",
            // "jdbc:mysql://localhost:3306/book",
            // "root", "admin")

            //  AuthorService authorService = new AuthorService(
            //      new AuthorDao(new MySqlDbAccessor(),
            //      driverClass, url, userName, password));
            AuthorService authorService = injectDependenciesAndGetAuthorService();

            switch (req_Action) {

                case AUTHOR_LIST_REQ:
                    destination = AUTH_LIST_PAGE;
                    sessionListPageVisits++;
                    
                    List<Author> authors = authorService.retrieveAuthors(
                            //should fix the magic number here- 50
                            AUTH_TABLE_NAME, 50);
                    request.setAttribute("authors", authors);
                    request.setAttribute("sessionListPageVisits", sessionListPageVisits);
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
                
                case VIEW_EMAIL_REQ:
                    destination= INFO_PAGE;
                     request.setAttribute("adminEmail", adminEmail);
                    break;

                case EDIT_AUTH_REQ:
                    destination = EDIT_AUTHOR_PAGE;
                    sessionEditPageVisits++;
                    String count = Integer.toString(sessionEditPageVisits);
                    // String authorName1 = request.getParameter(AUTH_NAME);
                    String id = request.getParameter(AUTHOR_ID);

                    Author author = authorService.retrieveAuthor(AUTH_TABLE_NAME, AUTH_ID_COL, id);
                    request.setAttribute(AUTH_ID, author.getAuth_ID());
                    request.setAttribute(AUTH_NAME, author.getAuth_Name());
                    request.setAttribute(DATE_ADDED, author.getDate());
                    request.setAttribute(EDIT_COUNT, count);
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
                    } else {
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
                    
                default: 
                    response.sendRedirect("session.jsp");
                    
                    break; 
                    
            }
        } catch (Exception e) {
            destination = HOME_PAGE;
            request.setAttribute("errMsg", e.getMessage());
        }
        RequestDispatcher view
                = request.getRequestDispatcher(response.encodeURL(destination));
        view.forward(request, response);
    }

    /*
        This helper method just makes the code more modular and readable.
        It's single responsibility principle for a method.
     */
    private AuthorService injectDependenciesAndGetAuthorService() throws Exception {
        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DBStrategy based on the class name retrieved
        // from web.xml
        Class dbClass = Class.forName(dbStrategyClassName);
        // Use Java reflection to instanntiate the DBStrategy object
        // Note that DBStrategy classes have no constructor params
        DbAccessor db = (DbAccessor) dbClass.newInstance();

        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DAO based on the class name retrieved above.
        // This one is trickier because the available DAO classes have
        // different constructor params
        IAuthorDao authorDao = null;
        Class daoClass = Class.forName(daoClassName);
        Constructor constructor = null;

        // This will only work for the non-pooled AuthorDao
        try {
            constructor = daoClass.getConstructor(new Class[]{
                DbAccessor.class, String.class, String.class, String.class, String.class
            });
        } catch (NoSuchMethodException nsme) {
            // do nothing, the exception means that there is no such constructor,
            // so code will continue executing below
        }

        // constructor will be null if using connectin pool dao because the
        // constructor has a different number and type of arguments
        if (constructor != null) {
            // conn pool NOT used so constructor has these arguments
            Object[] constructorArgs = new Object[]{
                db, driverClass, url, userName, password
            };
            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);

        } else {
            /*
             Here's what the connection pool version looks like. First
             we lookup the JNDI name of the Glassfish connection pool
             and then we use Java Refletion to create the needed
             objects based on the servlet init params
             */
            Context ctx = new InitialContext();
            //for pcs only
            DataSource ds = (DataSource) ctx.lookup(jndiName);
            //for macs only 
            //Context envCtx = (Context) ctx.lookup("java:comp/env");
            // DataSource ds = (DataSource) envCtx.lookup(jndiName);
            constructor = daoClass.getConstructor(new Class[]{
                DataSource.class, DbAccessor.class
            });
            Object[] constructorArgs = new Object[]{
                ds, db
            };

            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);
        }

        return new AuthorService(authorDao);
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

    @Override
    public void init() throws ServletException {
        driverClass = getServletContext()
                .getInitParameter("db.driver.class");
        url = getServletContext()
                .getInitParameter("db.url");
        userName = getServletContext()
                .getInitParameter("db.username");
        password = getServletContext()
                .getInitParameter("db.password");

        dbStrategyClassName = getServletContext().getInitParameter("dbStrategy");
        daoClassName = getServletContext().getInitParameter("AuthorDao");
        jndiName = getServletContext().getInitParameter("connPoolName");
    }

}
