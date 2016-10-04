/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wctc.edu.mss.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Spike
 */
public interface DbStrategy {

    void closeConnection() throws Exception;

    List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws Exception;

    void openConnection(String driverClass, String url, String userName, String password) throws Exception;
    
    int createRecord(String tableName, List<String> colNames, List<Object> colValues) throws Exception;
    
    int deleteRecordByPK(String tableName, String primaryKey, int value) throws Exception;
    
    int updateRecordByPrimaryKey(String tableName, String columnName, Object newValue, 
                                String keyIdentifier, Object key) throws Exception;
    
}
