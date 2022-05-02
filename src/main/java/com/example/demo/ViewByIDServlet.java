package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/viewByIDServlet")
public class ViewByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws RuntimeException, IOException {



        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();

        try {
            //In this condition, a reference to the procedure "chkReq"
            // for verifying the correctness of the request in servlet
            if (EmployeeRepository.chkReq(request, out)) {
                String sid = request.getParameter("idcars");
                out.println("sid = " + sid);
                int idcars = Integer.parseInt(sid);
                Employee employee = EmployeeRepository.getEmployeeById(idcars);
                out.print(employee);
            } else
            {
                code = 400;
                out.print("Illegal parameters, error " + code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.close();

        }
    }


