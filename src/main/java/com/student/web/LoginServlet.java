package com.student.web;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Admin credentials (in a real app, these would be in a database with encrypted passwords)
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
       
    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is already logged in
        HttpSession session = request.getSession(false);
        System.out.println(session.getAttribute("username") );
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath() + "/students/list");
            return;
        }
        
        // Handle logout
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        // Forward to login page
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Simple validation (in a real app, use proper validation and security measures)
        if (username != null && password != null && 
            username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(30 * 60); // 30 minutes
           
            // Redirect to student list
            response.sendRedirect(request.getContextPath() + "/students/list");
         
        } else {
        	System.out.println("login failed");
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}