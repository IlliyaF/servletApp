package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("idcar");
        Integer idcar = Integer.parseInt(sid);

        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String d = request.getParameter("doors");
        int doors = Integer.parseInt(d);


        Employee employee = new Employee();
        employee.setIdcars(idcar);
        employee.setModel(model);
        employee.setDoors(doors);
        employee.setColor(color);


        int status = EmployeeRepository.update(employee);

        if (status > 0) {
            response.sendRedirect("viewServlet");
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}
