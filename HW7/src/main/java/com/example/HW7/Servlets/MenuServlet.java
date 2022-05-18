package com.example.HW7.Servlets;

import com.example.HW7.Menu.Menu;
import com.example.HW7.Repository.SQLRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "menu", value = "/menu")
public class MenuServlet extends HttpServlet {

    private SQLRepository repository;
    private Menu menu;

    public void init() {
        menu = new Menu();
        try {
            repository = new SQLRepository();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("menu.html");

        // build the menu here.
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        out.println("<h1>" + "menu" + "</h1>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
