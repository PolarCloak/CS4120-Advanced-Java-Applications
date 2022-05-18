package com.example.HW7.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Welcome", value = "/welcome")
public class WelcomeScreenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // build the menu here.
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        out.println("<h1>" + "Welcome to my website where I sometimes do things." + "</h1>");


        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
