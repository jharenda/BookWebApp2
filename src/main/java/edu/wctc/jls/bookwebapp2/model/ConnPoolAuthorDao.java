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
import javax.sql.DataSource;

/**
 *
 * @author Jennifer
 */
public class ConnPoolAuthorDao implements IAuthorDao {

    private final String AUTH_ID_COL = "author_id";
    private final String AUTH_NAME_COL = "author_name";
    private final String DATE_COL = "date_added";
    private DataSource ds;
    private DbAccessor db;

    public ConnPoolAuthorDao(DataSource ds, DbAccessor db) {
        this.ds = ds;
        this.db = db;
    }

    @Override
    public final int deleteAuthorById(String tableName, String authIdCol,
            Object authorId) throws ClassNotFoundException, SQLException {
        //db.openConnection(driverClass, url, userName, password);
        db.openConnection(ds);
        int recordsDeleted = db.deleteById(tableName, authIdCol, authorId);
        db.closeConnection();
        return recordsDeleted;
    }

    @Override
    public final Author retrieveAuthor(String authorTableName, String authorIdColName, String authorId) throws ClassNotFoundException,
            SQLException {
        // db.openConnection(driverClass, url, userName, password);
        db.openConnection(ds);

        Map<String, Object> rawRec = db.getSingleRecord(
                authorTableName, authorIdColName, authorId);

        Author author = new Author();

        Object objId = rawRec.get(AUTH_ID_COL);
        Integer id = (Integer) objId;
        author.setAuth_ID(id);

        Object objName = rawRec.get(AUTH_NAME_COL);
        String authorName = (objName != null) ? objName.toString() : "";
        author.setAuth_Name(authorName);

        Object objDateAdded = rawRec.get(DATE_COL);
        Date dateAdded = (objDateAdded != null) ? (Date) objDateAdded : null;
        author.setDate(dateAdded);

        db.closeConnection();

        return author;
    }

    @Override
    public final List<Author> getAuthorList(String tableName, int maxRecords)
            throws ClassNotFoundException, SQLException {
        //   db.openConnection(driverClass, url, userName, password);
        db.openConnection(ds);

        List<Author> records = new ArrayList<>();
        List<Map<String, Object>> rawData = db.getAllRecords(tableName,
                maxRecords);

        for (Map<String, Object> rawRec : rawData) {
            Author author = new Author();

            Object objId = rawRec.get(AUTH_ID_COL);
            Integer authorId = (Integer) objId;
            author.setAuth_ID(authorId);

            Object objName = rawRec.get(AUTH_NAME_COL);
            //check for null to be safe
            String authorName = (objName != null) ? objName.toString() : "";
            author.setAuth_Name(authorName);

            Object objDateAdded = rawRec.get(DATE_COL);
            //works a lot better if we check for null date 
            Date dateAdded = (objDateAdded != null) ? (Date) objDateAdded : null;
            author.setDate(dateAdded);

            records.add(author);
        }
        db.closeConnection();

        return records;
    }

    @Override
    public final int updateAuthorById(String authorTableName, List<String> colNames,
            List<Object> colValues, String authorIdColName, Object authorId)
            throws SQLException, ClassNotFoundException {
        int updatedRecords = 0;
        //   db.openConnection(driverClass, url, userName, password);
        db.openConnection(ds);
        updatedRecords = db.updateAuthor(authorTableName, colNames,
                colValues, authorIdColName, authorId);
        db.closeConnection();
        return updatedRecords;
    }

    @Override
    public final int addAuthor(String tableName, List<String> authorTableColNames, List<Object> authorTableColValues)
            throws ClassNotFoundException, SQLException {

        int auth_Added = 0;
        //  db.openConnection(driverClass, url, userName, password);
        db.openConnection(ds);
        auth_Added = db.insertAuthor(
                tableName, authorTableColNames, authorTableColValues);
        db.closeConnection();

        return auth_Added;
    }


    @Override
    public final DbAccessor getDb() {
        return db;
    }

    @Override
    public final void setDb(DbAccessor db) {
        this.db = db;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public String getDriverClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUserName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDriverClass(String driverClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUserName(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
