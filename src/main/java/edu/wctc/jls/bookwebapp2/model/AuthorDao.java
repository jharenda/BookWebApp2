/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Jennifer
 */


public class AuthorDao implements IAuthorDao {
    private DbAccessor db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    private  final String AUTH_ID_COL= "author_id";
    private final String AUTH_NAME_COL = "author_name";
    private  final String DATE_COL= "date_added";

    public AuthorDao(DbAccessor db, String driverClass, String url, String 
            userName, String password) {
        setDb(db);
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }
    
    @Override
    public final int deleteAuthorById(String tableName, String authIdCol, 
            Object authorId) throws ClassNotFoundException, SQLException{
        db.openConnection(driverClass, url, userName, password);
        int recordsDeleted = db.deleteById(tableName, authIdCol, authorId);
        db.closeConnection();
        return recordsDeleted;
    }
    
    @Override
    public final Author retrieveAuthor(String authorTableName, String 
            authorIdColName, String authorId)throws ClassNotFoundException, 
            SQLException {
        db.openConnection(driverClass, url, userName, password);
        
        Map<String,Object> rawRec = db.getSingleRecord(
                authorTableName, authorIdColName, authorId);
        
        Author author = new Author();
        
        Object objId = rawRec.get(AUTH_ID_COL);
        Integer id = (Integer)objId;
        author.setAuth_ID(id);

        Object objName = rawRec.get(AUTH_NAME_COL);
        String authorName = (objName != null) ? objName.toString() : "";
        author.setAuth_Name(authorName);

        Object objDateAdded = rawRec.get(DATE_COL);
        Date dateAdded = (objDateAdded != null) ? (Date)objDateAdded : null;
        author.setDate(dateAdded);
        
        db.closeConnection();
        
        return author;
    }
    
    @Override
    public final List<Author> getAuthorList(String tableName, int maxRecords) 
            throws ClassNotFoundException, SQLException{
        db.openConnection(driverClass, url, userName, password);
        
        List<Author> records = new ArrayList<>();      
        List<Map<String, Object>> rawData = db.getAllRecords(tableName, 
                maxRecords);
        
        for(Map<String,Object> rawRec : rawData){
            Author author = new Author();
            
            Object objId = rawRec.get(AUTH_ID_COL);
            Integer authorId = (Integer)objId;
            author.setAuth_ID(authorId);
            
            Object objName = rawRec.get(AUTH_NAME_COL);
            //check for null to be safe
            String authorName = (objName != null) ? objName.toString() : "";
            author.setAuth_Name(authorName);
            
            Object objDateAdded = rawRec.get(DATE_COL);
            //works a lot better if we check for null date 
            Date dateAdded = (objDateAdded != null) ? (Date)objDateAdded : null;
            author.setDate(dateAdded);
            
            records.add(author);
        }
        db.closeConnection();
        
        return records;
    }
    @Override
    public final int updateAuthorById(String authorTableName, List<String> colNames, 
            List<Object> colValues, String authorIdColName, Object authorId) 
            throws SQLException, ClassNotFoundException{
        int updatedRecords = 0;
        db.openConnection(driverClass, url, userName, password);
        updatedRecords=  db.updateAuthor(authorTableName, colNames, 
                colValues, authorIdColName, authorId);
        db.closeConnection();
        return updatedRecords;
    }
    @Override
    public final int addAuthor(String tableName, List<String> 
            authorTableColNames, List<Object> authorTableColValues) 
            throws ClassNotFoundException, SQLException{
        
        int auth_Added = 0;
        db.openConnection(driverClass, url, userName, password);
        auth_Added = db.insertAuthor(
                tableName, authorTableColNames, authorTableColValues);
        db.closeConnection();
        
        return auth_Added;
    }
    



    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.driverClass);
        hash = 97 * hash + Objects.hashCode(this.url);
        hash = 97 * hash + Objects.hashCode(this.userName);
        hash = 97 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthorDao other = (AuthorDao) obj;
        if (!Objects.equals(this.driverClass, other.driverClass)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }  
    
    
        @Override
    public final DbAccessor getDb() {
        return db;
    }

    @Override
    public final void setDb(DbAccessor db) {
        this.db = db;
    }

    @Override
    public final String getDriverClass() {
        return driverClass;
    }

    @Override
    public final void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public final String getUrl() {
        return url;
    }

    @Override
    public final void setUrl(String url) {
        this.url = url;
    }

    @Override
    public final String getUserName() {
        return userName;
    }

    @Override
    public final void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override
    public final void setPassword(String password) {
        this.password = password;
    }
}
