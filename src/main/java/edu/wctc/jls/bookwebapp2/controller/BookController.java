/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.controller;

import edu.wctc.jls.bookwebapp2.model.Author;
import edu.wctc.jls.bookwebapp2.model.AuthorFacade;
import edu.wctc.jls.bookwebapp2.model.Book;
import edu.wctc.jls.bookwebapp2.model.BookFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jennifer
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {
 public final String REQ_TYPE = "requestType";

    // page variables   
    public final String HOME_PAGE = "/index.jsp";
    public final String BOOK_LIST_PAGE = "/bookList.jsp";
    public final String ADD_BOOK_PAGE = "/addBook.jsp";
    public final String EDIT_BOOK_PAGE = "/editBook.jsp";
    public final String INFO_PAGE = "/adminInfo.jsp";
     public final String HELP_PAGE = "/session.jsp";

    //these are the values "grabbed" from the page
    public final String BOOL_ID = "id";
    public final String BOOK_NAME = "bookName";
    public final String DATE_ADDED = "dateAdded";
    public final String BOOK_ISBN = "bookIsbn";
      public final String BOOK_AUTHOR = "bookAuthor";
    public final String BOOK_ID_CBX = "bookId";
    public final String BOOK_ID = "id";
    public final String BOOK_TABLE_NAME = "book";
    public final String BOOK_ID_COL = "book_id";
    public final String BOOK_NAME_COL = "book_name";
    public final String DATE_COL = "date_added";

    //different types of requests that may come in from the UI
    public final String BOOK_LIST_REQ = "bookList";
    public final String ADD_BOOK_REQ = "addBook";
    public final String EDIT_BOOK_REQ = "editBook";
    public final String SAVE_REQ = "saveBook";
    public final String NEW_REQ = "addNew";
    public final String NEW_SAVE_REQ = "newSave";
    public final String SAVE_EDIT_REQ = "saveEdit";
    public final String DELETE_BOOK_REQ = "deleteBook";
    public final String HELP = "help";
    public final String VIEW_EMAIL_REQ = "viewEmail";
    public final String EDIT_COUNT = "";
    public final String LIST_COUNT = "";

 
    private int sessionListPageVisits = 0;
    private int sessionEditPageVisits = 0;
    
    // new - this autocreates authorservice- this is automated dependency injection
    @EJB
    private AuthorFacade authorService;
    @EJB 
    private BookFacade bookService; 
   
    
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
    

            switch (req_Action) {

                case BOOK_LIST_REQ:
                    destination = BOOK_LIST_PAGE;
                    sessionListPageVisits++;
                    
                   List<Book> books = bookService.findAll(); 
                            //should fix the magic number here- 50
                       
                    request.setAttribute("books", books);
                    request.setAttribute("sessionListPageVisits", sessionListPageVisits);
                    break;
                case DELETE_BOOK_REQ:
                    destination = BOOK_LIST_PAGE;
                    Book bookToDelete = new Book(); 
                    String[] booksToDelete = request.getParameterValues(BOOK_ID_CBX);
                    if (booksToDelete != null) {
                        for (String id : booksToDelete) {
                            // method no longer exists 
                            bookToDelete = bookService.find(new Integer (id));
                            bookService.remove(bookToDelete); 
                           
                           
                        }
                    }
                    refreshResults(request, bookService);
                    break;

                case ADD_BOOK_REQ:

                    destination = ADD_BOOK_PAGE;

                    break;
               

                case EDIT_BOOK_REQ:
                    destination = EDIT_BOOK_PAGE;
              
                    String bookId = request.getParameter(BOOL_ID);

                    Book book = bookService.find(new Integer(bookId));
                   // request.setAttribute(BOOK_ID, bookId);
                    request.setAttribute(BOOK_NAME, book.getTitle());
                   request.setAttribute(BOOK_ISBN, book.getIsbn());
                  // request.setAttribute(BOOK_AUTHOR, book.getAuthorId().getAuthorName()); 
                //refreshResults(request, bookService);
                   
                   
                   //case EDIT_AUTH_REQ:
                   // destination = EDIT_AUTHOR_PAGE;
                   
                    //String authorId = request.getParameter(AUTHOR_ID);

                   // Author author = authorService.find(new Integer(authorId));
                   // request.setAttribute(AUTH_ID, author.getAuthorId());
                   // request.setAttribute(AUTH_NAME, author.getAuthorName()); 
                   
                   
                   
                   
                   
                   
                    break;

                    
                case NEW_SAVE_REQ:
                      destination = BOOK_LIST_PAGE;
                       String newBookTitle = request.getParameter("title");
                   String  isbn = request.getParameter("bookIsbn");
                    String  authorId = request.getParameter("bookAuthor");
                 
                        Book newBook = new Book();
                      //  newBook.setTitle("lol");
                        newBook.setTitle(newBookTitle); 
                        newBook.setIsbn(isbn);
                        Author author;
                        if(authorId != null){
                            author = authorService.find(new Integer(authorId));
                            newBook.setAuthorId(author);
                        }
                        bookService.create(newBook);
                        refreshResults(request, bookService);
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
  

    private void refreshResults(HttpServletRequest request, BookFacade bookService)
            throws ClassNotFoundException, SQLException {
        List<Book> books = bookService.findAll(); 
               
        request.setAttribute("books",books);
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
