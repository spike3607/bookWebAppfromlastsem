/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wctc.edu.mss.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wctc.edu.mss.model.Author;
import wctc.edu.mss.model.AuthorDao;
import wctc.edu.mss.model.AuthorDaoStrategy;
import wctc.edu.mss.model.AuthorService;
import wctc.edu.mss.model.DbStrategy;
import wctc.edu.mss.model.MySqlDBStrategy;

/**
 *
 * @author Spike
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {    
    private DbStrategy db;
    
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    
    @Inject
    private AuthorService authService;
    
    @Override
    public void init() throws ServletException {
        this.driverClassName = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/book";
        this.userName = "root";
        this.password = "admin";
    }
    
    private void configDbConnection() {
        authService.getDao().initDao(driverClassName, url, userName, password);
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        String destination = "/index.html";
        String action = request.getParameter("action");
        
        
        
        try {
            
            configDbConnection();
             
            if (action.equals("list")) {
                List<Author> authors = null;
                authors = authService.getAuthorList();
                request.setAttribute("authors", authors);
                destination = "/listAuthors.jsp";

            } else if (action.equals("add")) {
                // Hard coded for now
                authService.addAuthor("Karry Hoof", new Date());
                
                destination = "/index.html";
                
            } else if (action.equals("update")) {
                // Hard code for now
                authService.updateAuthor(4,"author_name","Kathy Schoenauer");
                
                destination = "/index.html";
                
            } else if (action.equals("delete")) {
                // Hard Coded
                authService.deleteAuthor(1);
                
                destination = "/index.html";
                
            } else {
                // Error
                System.out.println("Unable to find action parameter");
            }
            
        } catch (Exception e) {
            request.setAttribute("errTxt", e.getCause().getLocalizedMessage());
        }
               
        
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        processRequest(request, response);
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        processRequest(request, response);
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
