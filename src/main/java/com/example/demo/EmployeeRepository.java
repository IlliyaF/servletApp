package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class EmployeeRepository {

    public static void main(String[] args) throws SQLException {

        Connection connection = EmployeeRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from cars");
        ResultSet resultSet = ps.executeQuery();
        ResultSetMetaData rsData = resultSet.getMetaData();
        System.out.println(rsData.getColumnCount() + "; ");
        ArrayList <String> nms = new ArrayList<>();
        for (int i=1; i<=rsData.getColumnCount(); i++) {
            nms.add(rsData.getColumnName(i));
            System.out.println("ColumnName (" + ") = " + rsData.getColumnName(i));
        }


    }

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employee";
        String user = "postgres";
        String password = "gavgav37";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(Employee employee) {
        int status = 0;
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into cars(model,color,doors) values (?,?,?)");
            ps.setString(1, employee.getModel());
            ps.setString(2, employee.getColor());
            ps.setInt(3, employee.getDoors());


            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(Employee employee) {

        int status = 0;

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update cars set model=?,color=?,doors=? where idcars=?");
            ps.setString(1, employee.getModel());
            ps.setString(2, employee.getColor());
            ps.setInt(3, employee.getDoors());
            ps.setInt(4, employee.getIdcars());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int idcars) {

        int status = 0;

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from cars where idcars=?");
            ps.setInt(1, idcars);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Employee getEmployeeById(int idcars) {

        Employee employee = new Employee();

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from cars where idcars=?");
            ps.setInt(1, idcars);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setIdcars(rs.getInt(1));
                employee.setModel(rs.getString(2));
                employee.setColor(rs.getString(3));
                employee.setDoors(rs.getInt(4));

            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> getAllEmployees() {

        List<Employee> listEmployees = new ArrayList<>();

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from cars");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setIdcars(rs.getInt(1));
                employee.setModel(rs.getString(2));
                employee.setColor(rs.getString(3));
                employee.setDoors(rs.getInt(4));


                listEmployees.add(employee);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEmployees;
    }
    //My auxiliary methods:
    public static boolean chkReq(HttpServletRequest req, PrintWriter out) throws SQLException {


        Enumeration <String> parReq = req.getParameterNames();

        ArrayList <String> nms = new ArrayList<>(); //Array for Field Names in the request

        while (parReq.hasMoreElements()){
            nms.add(parReq.nextElement());
           }

        if(chkFieldName(nms)) {
            return true;
        }

    return false;
}
    public static boolean chkFieldName(ArrayList<String> n) throws SQLException {
        int quantityCoincidences = 0;
        for (int i = 0; i < n.size(); i++) {
            if(EmployeeRepository.verifyNames(n.get(i))) {
                quantityCoincidences++;
            }
            }
        if (quantityCoincidences == n.size()){
            return true;
        }
        return false;
    }

    public static boolean verifyNames(String fieldName) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from cars");
        ResultSet resultSet = ps.executeQuery();
        ResultSetMetaData rsData = resultSet.getMetaData();

        for (int i=1; i<=rsData.getColumnCount();i++){

            if (Objects.equals(fieldName, rsData.getColumnName(i))) {
                return true;
            }
        }
    return false;
    }
}

