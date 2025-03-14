/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author NC PC
 */
public class LoginServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("view/login.jsp").forward(request, response);

        } else {
            response.sendRedirect("user");
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        String userN = request.getParameter("user");
        String pass = request.getParameter("pass");
        User user = UserDAO.getUserByEmailAndPassword(userN, pass);
        if (user != null && "loginSuccessfully".equals(action)) {
            session.setAttribute("user", user);
            response.sendRedirect("user");
        } else if ("registerSuccessfully".equals(action)) {
            String idCard = (String)request.getParameter("idCard");
            String fullName = (String)request.getParameter("fullName");
            String email = (String)request.getParameter("email");
            String password = (String)request.getParameter("password");
            String phone = (String)request.getParameter("phone");
            String address = (String)request.getParameter("address");
            
            if (UserDAO.getUserByUserID(idCard) != null) {
                request.setAttribute("errorR", "ID existed");
                request.setAttribute("optionLoginOrRegister", "register");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }
            if (UserDAO.getUserByEmail(email) != null) {
                request.setAttribute("errorR", "Email existed");
                request.setAttribute("optionLoginOrRegister", "register");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }
            User tmp = new User();
            tmp.setEmail(email);
            tmp.setFullName(fullName);
            tmp.setUserId(idCard);
            tmp.setPassword(password);
            tmp.setPhone(phone);
            tmp.setAddress(address);
            
            tmp = UserDAO.addUser(tmp);
            if(tmp==null){
                request.setAttribute("errorR", "Add unsuccessfully");
                request.setAttribute("optionLoginOrRegister", "register");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            } else{
                request.setAttribute("mes", "Add SUCCESSFULLY");
                request.setAttribute("optionLoginOrRegister", "register");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            } 
        } else {//Sua: Them error tai login.jsp
            request.setAttribute("error", "Cannot find user");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
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
