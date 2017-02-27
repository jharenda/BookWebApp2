/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;


import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Jennifer
 */
public interface IAuthorDao {
        public abstract List<Author> getAuthorList(String tableName, int maxRecords) 
            throws ClassNotFoundException, SQLException;
    
    public abstract Author retrieveAuthor(String authorTableName, String 
            authorIdColName, String authorId)throws ClassNotFoundException, 
            SQLException;
    
    public abstract int deleteAuthorById(String tableName, String authorIdColName,
            Object authorId) throws ClassNotFoundException, SQLException;
    
    public int addAuthor(String tableName, List<String> colNames, List<Object> 
            colValues) throws ClassNotFoundException, SQLException;
    
    public int updateAuthorById(String authorTableName, List<String> colNames, 
            List<Object> colValues, String authorIdColName, Object 
                    authorId) throws SQLException,ClassNotFoundException;
    
    public abstract DbAccessor getDb();

    public abstract String getDriverClass();

    public abstract String getPassword();

    public abstract String getUrl();

    public abstract String getUserName();


    public abstract void setDb(DbAccessor db);

    public abstract void setDriverClass(String driverClass);

    public abstract void setPassword(String password);

    public abstract void setUrl(String url);

    public abstract void setUserName(String userName);
    
}
