/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wctc.edu.mss.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Spike
 */
@Dependent
public class MySqlDBStrategy implements DbStrategy, Serializable {

    private Connection conn;

    @Override
    public void openConnection(String driverClass, String url, String userName,
            String password) throws Exception {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws Exception {
        conn.close();
    }

    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws Exception {
        String sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Map<String, Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();

        while (rs.next()) {
            Map<String, Object> record = new LinkedHashMap<>();
            for (int i = 1; i < colCount + 1; i++) {
                String colName = rsmd.getColumnName(i);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);
        }
        return records;
    }

    @Override
    public int deleteRecordByPK(String tableName, String primaryKey, int value) throws Exception {
        //DELETE FROM author WHERE author_id = 1
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, value);
        int recordsUpdated = stmt.executeUpdate();
        return recordsUpdated;
    }

    @Override
    public int createRecord(String tableName, List<String> colNames, List<Object> colValues) throws Exception {
        //INSERT INTO author (author_id, author_name, date_added) VALUES ('4', 'max maxian', '2009-12-24')

        String sql = "INSERT INTO " + tableName + "("; //first_name,last_name)" + " VALUES('Billy','Carter')";

        for (int i = 0; i < colNames.size(); i++) {
            if (i == (colNames.size() - 1)) {
                sql += colNames.get(i) + ") VALUES(";
            } else {
                sql += colNames.get(i) + ",";
            }
        }

        for (int i = 0; i < colValues.size(); i++) {
            if (i == (colValues.size() - 1)) {
                sql += " ? )";
            } else {
                sql += " ? ,";
            }
        }

        System.out.println(sql);

        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (int i = 0; i < colValues.size(); i++) {
            pstmt.setObject(i + 1, colValues.get(i));
        }

        int updateCount = pstmt.executeUpdate();

        return updateCount;
    }

    @Override
    public int updateRecordByPrimaryKey(String tableName, String columnName, Object newValue, String keyIdentifier, Object key) throws Exception {
        PreparedStatement pstmt = null;
        String sql = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + keyIdentifier + " = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, newValue);
        pstmt.setObject(2, key);

        int updateCount = pstmt.executeUpdate();

        return updateCount;
    }

    public static void main(String[] args) throws Exception {
        MySqlDBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");

//        ArrayList<String> newRecordColumns = new ArrayList<>();
//        newRecordColumns.add("author_name");
//        newRecordColumns.add("date_added");
//        
//        ArrayList<Object> newRecordValues = new ArrayList<>();
//        newRecordValues.add("Mike Schoenauer");
//        newRecordValues.add(new Date(2015,4,19));
        int recordsUpdated = db.updateRecordByPrimaryKey("author", "author_name", "Steven Schoenauer", "author_id", 4);

        System.out.println(recordsUpdated);

        List<Map<String, Object>> records = db.findAllRecords("author", 500);
        System.out.println(records);

        db.closeConnection();
    }
}
