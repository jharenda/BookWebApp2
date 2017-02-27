/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author Jennifer
 */
public class MySqlDbAccessor implements DbAccessor {
    private Connection conn;
    private Statement stmt;
    private ResultSet resultSet;
    private PreparedStatement prepStmt;
   

    @Override
    public final void openConnection(String driverClass, String url, String userName, 
            String password) throws ClassNotFoundException, SQLException{
        if(driverClass.isEmpty() || driverClass == null || userName.isEmpty() ||
                userName == null || password.isEmpty() || password == null ||
                url.isEmpty() || url == null){        
            throw new IllegalArgumentException("error msg");
        }
        
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url,userName,password);
    }
    
    @Override
    public final void closeConnection() throws SQLException{
        if(conn!= null){
            conn.close();
        }
    }
    
    @Override
    public final Map<String,Object> getSingleRecord(String table, String 
            idColName, String recordId)throws SQLException{
        String sql = "SELECT * FROM " + table + " WHERE " + idColName + " = ?";

        prepStmt = conn.prepareStatement(sql);
        prepStmt.setObject(1, recordId);
        resultSet = prepStmt.executeQuery();
        
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int colCount = rsmd.getColumnCount();
        
        Map<String,Object> map = new LinkedHashMap<>();
        
        while(resultSet.next()){
            for(int col = 1 ; col < colCount+1 ;col++){
                map.put(rsmd.getColumnName(col), resultSet.getObject(col));
            }
        }

        return map;
    }
    
 
    @Override
    public final List<Map<String,Object>> getAllRecords(String table, int maxRecords) 
            throws SQLException{
        if(table == null || table.isEmpty()){
            throw new IllegalArgumentException("errpr");
        }
        String sql = "";
        
        if(maxRecords > 0){
            sql = "SELECT * FROM " + table + " LIMIT " + maxRecords;
        } else {
            sql = "SELECT * FROM " + table;
        }
        
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(sql);
        
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int colCount = rsmd.getColumnCount();
        
        List<Map<String,Object>> results = new ArrayList<>();
        
        while(resultSet.next()){
            Map<String,Object> map = new LinkedHashMap<>();
            
            for(int col = 1 ; col < colCount+1 ;col++){
                map.put(rsmd.getColumnName(col), resultSet.getObject(col));
            }
            results.add(map);
        }
        return results;
    }
 
    @Override
    public final int deleteById(String table, String pkName, Object id) throws 
            SQLException{
        if(table == null ||table.isEmpty() || pkName == null || pkName.isEmpty() 
                || id == null){
            throw new IllegalArgumentException("error");
        }
        int recordsDeleted = 0;
        String sql = "DELETE FROM  " + table + " WHERE " + pkName + 
                " = ?";
            
        prepStmt = conn.prepareStatement(sql);
      prepStmt.setObject(1, id);
        recordsDeleted = prepStmt.executeUpdate();
            
        return recordsDeleted;
    }
    
    @Override
    public final int updateAuthor(String tableName, List<String> colNames, 
            List<Object> colValues, String idColName, Object 
                    idColValue) throws SQLException{
        if(tableName == null || tableName.isEmpty() || colNames == null ||
                colValues == null || idColName == null || 
                idColName.isEmpty() || idColValue ==null){
            throw new IllegalArgumentException("error");
        }
        int recordsUpdated = 0;
        
        String sql = "UPDATE " + tableName + " SET ";
        
        StringJoiner sj = new StringJoiner(",");
        
        for(String colName : colNames){
            sj.add(colName + " = ?");
        }
        sql += sj.toString();
        
        sql += " WHERE " + idColName + " = " + " ? ";
        prepStmt = conn.prepareStatement(sql);
        
        for(int i =0;i < colNames.size() ; i++){
         prepStmt.setObject(i+1, colValues.get(i));
        }
        
     prepStmt.setObject(colNames.size()+1, idColValue);
        recordsUpdated = prepStmt.executeUpdate();

        return recordsUpdated;
    }
    
    @Override
    public final int insertAuthor(String tableName, List<String> colNames, List<Object> 
            colValues) throws SQLException{
        if(tableName == null || tableName.isEmpty() || colNames == null ||
                colValues == null){
             throw new IllegalArgumentException("error");
        }
        int recordsInserted = 0;
        
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(",","(",")");
        
        for(String colName : colNames){
            sj.add(colName);
        }
        sql += sj.toString();
        sql += " VALUES ";
        
        sj = new StringJoiner(",","(",")");
        
        for(Object colValue : colValues){
            sj.add("?");
        }
        
        sql += sj.toString();
        
        prepStmt = conn.prepareStatement(sql);
        
        for(int i = 0; i < colValues.size(); i++){
            prepStmt.setObject(i+1, colValues.get(i));
        }
        
        recordsInserted = prepStmt.executeUpdate();
        
        return recordsInserted;
    } 
}
