package com.example.HW7.Servlets;

import com.example.HW7.Menu.Menu;
import com.example.HW7.Menu.Subtotal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Subtotal", value = "/subtotal")
public class SubtotalServlet extends HttpServlet {


    private Subtotal subtotal;

    public void init() {
        subtotal = new Subtotal();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // build the menu here.
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        out.println("<h1>" + "subtotal" + "</h1>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
