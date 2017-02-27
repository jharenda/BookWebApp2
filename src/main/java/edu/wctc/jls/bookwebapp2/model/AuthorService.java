/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jennifer
 */
public class AuthorService {
     private IAuthorDao authorDao;

    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }

    public final List<Author> retrieveAuthors(String tableName, int maxRecords) 
            throws ClassNotFoundException, SQLException{
        return authorDao.getAuthorList(tableName,maxRecords);
    }
    
    public final Author retrieveAuthor(String authorTableName, String
            authorIdColName, String authorId) throws ClassNotFoundException, 
            SQLException{
        return authorDao.retrieveAuthor(authorTableName, authorIdColName, authorId);
    }
    
    public final int deleteAuthorById(String authorTableName, String authorIdColName,
            String authorId) throws ClassNotFoundException, SQLException{
        return authorDao.deleteAuthorById(authorTableName, authorIdColName, authorId);
    }
    public final int updateAuthorById(String authorTableName, List<String> colNames, 
            List<Object> colValues, String authorIdColName, Object authorId) 
            throws ClassNotFoundException, SQLException{
        return authorDao.updateAuthorById(authorTableName, colNames, colValues, 
                authorIdColName, authorId);
    }
    public final int addNewAuthor(String authorTableName, List<String> authorTableColNames,
            List<Object> authorTableColValues) throws ClassNotFoundException, 
            SQLException{
        return authorDao.addAuthor(authorTableName, authorTableColNames, 
                authorTableColValues);
    }
    
    public final IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public final void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService test = new AuthorService(
                new AuthorDao(
                        new MySqlDbAccessor(),"com.mysql.jdbc.Driver", 
                        "jdbc:mysql://localhost:3306/book", 
                        "root", "admin")
                        );

    Author author = test.retrieveAuthor("author", "author_id", "5");
        System.out.println(author);
    }
}

