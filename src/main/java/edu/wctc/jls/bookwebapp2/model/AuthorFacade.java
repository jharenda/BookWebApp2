/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jennifer
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.jls_BookWebApp2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    public void deleteAuthorById(String id) {
        Author author = this.find(new Integer(id)); 
        this.remove(author);
        
    }
    
    public void deleteByIdJPQL(String id) {
        
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", new Integer(id));
        q.executeUpdate(); 
    }

    
    
    
    public void saveOrUpdate (String id, String name, String dateAdded) {
        Author author = new Author();
        if (id == null) {
            author.setAuthorName(name);
            author.setDateAdded(new Date());
        } else {
            //update
            author.setAuthorId(new Integer(id));
           author.setDateAdded(new Date(dateAdded));
            author.setAuthorName(name);
            
        }
            this.getEntityManager().merge(author);
        

    }
}
