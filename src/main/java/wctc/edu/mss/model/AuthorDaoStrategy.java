/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wctc.edu.mss.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Spike
 */
public interface AuthorDaoStrategy {

    void addAuthor(String name, Date date) throws Exception;

    void deleteAuthor(Object key) throws Exception;

    List<Author> getAllAuthors() throws Exception;

    DbStrategy getDb();

    String getDriverClassName();

    String getPassword();

    String getUrl();

    String getUserName();

    void initDao(String driverClassName, String url, String userName, String password);

    void setDb(DbStrategy db);

    void setDriverClassName(String driverClassName);

    void setPassword(String password);

    void setUrl(String url);

    void setUserName(String userName);

    void updateAuthor(Object key, String columnName, Object newObject) throws Exception;
    
}
