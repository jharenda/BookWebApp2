
package edu.wctc.jls.bookwebapp2.model;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

/**
 *
 * @author Jennifer
 */
public interface DbAccessor {
    public abstract int deleteById(String tableName, String idColName, Object id) 
            throws SQLException;

     
    public int insertAuthor(String tableName, List<String> colNames, List<Object> 
            colValues) throws SQLException;
     
    public abstract List<Map<String, Object>> getAllRecords(String table,
            int maxRecords) throws SQLException;
    
    public abstract Map<String,Object> getSingleRecord(String table, String 
            idColName, String recordId)throws SQLException;
    
    public int updateAuthor(String tableName, List<String> colNamesToSet, 
            List<Object> colValues, String conditionColName, Object 
                    conditionColValue) throws SQLException;
    
    public abstract void openConnection(String driverClass, String url, 
            String userName, String password) throws ClassNotFoundException, 
            SQLException;
    
    public abstract void closeConnection() throws SQLException;
}
