package edu.wctc.jls.bookwebapp2.service;

import edu.wctc.jls.bookwebapp2.entity.Book;
import edu.wctc.jls.bookwebapp2.entity.Book_;
import edu.wctc.jls.bookwebapp2.repository.AuthorRepository;
import edu.wctc.jls.bookwebapp2.repository.BookRepository;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




/**
 * This is a special Spring-enabled service class that delegates work to 
 * various Spring managed repository objects using special spring annotations
 * to perform dependency injection, and special annotations for transactions.
 * It also uses SLF4j to provide logging features.
 * 
 * Don't confuse the Spring @Respository annotation with the repository
 * classes (AuthorRepository, BookRespository). The annotation here is used 
 * solely to tell Spring to translate any exception messages into more
 * user friendly text. Any class annotated that way will do that.
 * 
 * @author jlombardo
 */
@Service
@Transactional(readOnly = true)
public class BookService {
    private transient final Logger LOG = LoggerFactory.getLogger(BookService.class);
    
    @Inject
    private BookRepository bookRepo;
    
    @Inject
    private AuthorRepository authorRepo;

    public BookService() {
    }
    
    
     private EntityManager em;

   
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Book> findAll() {
        return bookRepo.findAll();
    }
    
    public Book findById(String id) {
        return bookRepo.findOne(new Integer(id));
    }
    
    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param book 
     */
    @Transactional
    public void remove(Book book) {
        LOG.debug("Deleting book: " + book.getTitle());
        bookRepo.delete(book);
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param book 
     */
    @Transactional
    public Book edit(Book book) {
        return bookRepo.saveAndFlush(book);
    }
    /**
     * Finds hotels by a search key, which is checked against one of these 
     * fields: name, city, zip
     * @param searchKey - a value or portion of a value of a field for name, 
     * city or zip
     * @return matching hotel records
     */
    public List<Book> searchForBookByAny(String searchKey) {
        // put wildcard symbols on both sides of searchKey
        searchKey = new StringBuilder("%").append(searchKey).append("%").toString();
        // Use Criteria style queries so we can change the search field easily
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);
        
        // Begin by trying the name field
        criteriaQuery.where(builder.like(book.get(Book.title),searchKey));
        TypedQuery<Book> q = getEntityManager().createQuery(criteriaQuery);
        List<Book> books = q.getResultList();
        
        if(books.isEmpty()) {
            // Not found so try another field
            criteriaQuery.where(builder.like(book.get(Book.isbn),searchKey));
            q = getEntityManager().createQuery(criteriaQuery);
            books = q.getResultList();
            
          
        }
        
        return books;
    }
    
}

    

  //  public List<Book> searchByAuthorId(Integer id) {
    //    return bookRepo.searchByAuthorId(id);
  //  }
    
   // public List<Book> searchByAuthorIdAndIsbn(Integer id, String isbn) {
    //    return bookRepo.searchByAuthorIdAndIsbn(id, isbn);
  //  }

  //  public List<Book> searchByIsbn(String isbn) {
   //     return bookRepo.searchByIsbn(isbn);        
   // } 

   // public List<Book> searchByAuthorIdAndTitle(Integer id, String title) {
    //    return bookRepo.searchByAuthorIdAndTitle(id, title);        
   // }

   // public List<Book> searchByTitle(String title) {
  //      return bookRepo.searchByTitle(title);        
  //  }
   
   // public List<Book> searchByAuthorIdAndIsbnAndTitle(Integer id, String isbn, String title) {
   //     return bookRepo.searchByAuthorIdAndIsbnAndTitle(id, isbn, title);        
   // }

  //  public List<Book> searchByIsbnAndTitle(String isbn, String title) {
    //    return bookRepo.searchByIsbnAndTitle(isbn, title);        
   // }    
    
}
