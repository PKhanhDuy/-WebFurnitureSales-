package com.supportjobsearch.controller2;

import com.supportjobsearch.Bean.User;
import com.supportjobsearch.InsertData;
import com.supportjobsearch.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String uname= req.getParameter("uname");
        String pass= req.getParameter("pass");
        String hashPass = InsertData.hashPassword(pass);
        UserService authService=  new UserService();
        User u = authService.checkLogin(uname,hashPass);
        System.out.println("Check user success");
        if(u!=null){
            System.out.println("Login is successful");
            HttpSession session = req.getSession();
            session.setAttribute("auth",u);
            resp.sendRedirect("/kenes");

        }else{
            req.setAttribute("error","Đăng nhập không thành công!!!");
            req.getRequestDispatcher("Login.jsp").forward(req,resp);
        }
    }
}
