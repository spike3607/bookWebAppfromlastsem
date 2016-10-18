/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wctc.edu.mss.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Spike
 */
@Dependent
public class AuthorDao implements AuthorDaoStrategy, Serializable {

    @Inject
    private DbStrategy db;

    private String driverClassName;
    private String url;
    private String userName;
    private String password;

    public AuthorDao() {

    }

    @Override
    public void initDao(String driverClassName, String url, String userName, String password) {
        setDriverClassName(driverClassName);
        setUrl(url);
        setUserName(userName);
        setPassword(password);

    }

    @Override
    public List<Author> getAllAuthors() throws Exception {
        db.openConnection(driverClassName, url, userName, password);
        List<Author> records = new ArrayList<>();

        List<Map<String, Object>> rawData = db.findAllRecords("author", 500);
        for (Map rawRec : rawData) {
            Author author = new Author();
            Object obj = rawRec.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));

            String name = rawRec.get("author_name") == null ? "" : rawRec.get("author_name").toString();
            author.setAuthorName(name);

            obj = rawRec.get("date_added");
            Date dateAdded = (obj == null) ? new Date() : (Date) rawRec.get("date_added");
            author.setDateAdded(dateAdded);
            records.add(author);
        }

        db.closeConnection();

        return records;

    }

    public Author getAuthorByID(Object key) throws Exception {
        List<Map<String, Object>> rawData = db.findRecordByPK("author", "author_id", (int) key);
        
        Author author = new Author();
        Object obj = rawData.get(1).get("author_id");
        author.setAuthorId(Integer.parseInt(obj.toString()));

        String name = rawData.get(1).get("author_name") == null ? "" : rawData.get(1).get("author_name").toString();
        author.setAuthorName(name);

        obj = rawData.get(1).get("date_added");
        Date dateAdded = (obj == null) ? new Date() : (Date) rawData.get(1).get("date_added");
        author.setDateAdded(dateAdded);

        return author;
    }

    @Override
    public void addAuthor(String name, Date date) throws Exception {
        db.openConnection(driverClassName, url, userName, password);

        ArrayList columns = new ArrayList();
        columns.add("author_name");
        columns.add("date_added");

        ArrayList values = new ArrayList();
        values.add(name);
        values.add(date);

        db.createRecord("author", columns, values);

        db.closeConnection();
    }

    @Override
    public void updateAuthor(Object key, String columnName, Object newObject) throws Exception {
        db.openConnection(driverClassName, url, userName, password);

        db.updateRecordByPrimaryKey("author", columnName, newObject, "author_id", key);

        db.closeConnection();
    }

    @Override
    public void deleteAuthor(Object key) throws Exception {
        db.openConnection(driverClassName, url, userName, password);

        db.deleteRecordByPK("author", "author_id", (int) key);

        db.closeConnection();
    }

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public DbStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DbStrategy db) {
        this.db = db;
    }
}
