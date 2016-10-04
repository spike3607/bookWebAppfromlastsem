/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wctc.edu.mss.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Spike
 */
@SessionScoped
public class AuthorService implements Serializable {
    @Inject
    private AuthorDaoStrategy dao;

    public AuthorService() {
    }

//    private List<Author> fakeDatabase = Arrays.asList(
//        new Author(1,"Sally Smith", new Date(2010, 12, 2)),
//        new Author(2,"Steve Kennedy", new Date(2014, 1, 30)),
//        new Author(3,"Kevin Rogers", new Date(2016, 5, 5)));
//    
//    public List findAllAuthors () {
//       return fakeDatabase;
//    }
    public List<Author> getAuthorList() throws Exception {
        return dao.getAllAuthors();
    }

    public void addAuthor(String name, Date dateCreated) throws Exception {
        dao.addAuthor(name, dateCreated);
    }

    public void updateAuthor(Object key, String columnName, Object newObject) throws Exception {
        dao.updateAuthor(key, columnName, newObject);
    }

    public void deleteAuthor(Object key) throws Exception {
        dao.deleteAuthor(key);
    }

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {

    }
}
