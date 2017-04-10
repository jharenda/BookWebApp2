/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jennifer
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "edu.wctc.jls_BookWebApp2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
      public List<Book> findByTitle(String title) {
        String jpql = "select b from Book b where b.title = ?1";
        TypedQuery<Book> q = getEntityManager().createQuery(jpql, Book.class);
        q.setParameter(1, title);
        return q.getResultList();
    }
      
        public void saveOrUpdate (String id, String title, String isbn, Author author) {
       Book book = new Book();
        if (id == null) {
            book.setTitle(title);
          book.setIsbn(isbn);
          book.setAuthorId(author);
           
          
        } else {
            //update
            book.setBookId(new Integer(id));
      
            book.setTitle(title);
              book.setIsbn(isbn);
            book.setAuthorId(author);
            
        }
            this.getEntityManager().merge(book);
        

    }
    
}
