package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public static void main(String[] args) {
        getConnection();

        Employee employee = new Employee();

        employee.setModel("Slavuta");
        employee.setColor("green");
        employee.setDoors(4);

        save(employee);
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
            PreparedStatement ps = connection.prepareStatement("update cars set model=?,color=?,doors=? where idcar=?");
            ps.setString(1, employee.getModel());
            ps.setString(2, employee.getColor());
            ps.setInt(3, employee.getDoors());
            ps.setInt(4, employee.getIdcar());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int idcar) {

        int status = 0;

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from cars where idcar=?");
            ps.setInt(1, idcar);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Employee getEmployeeById(int idcar) {

        Employee employee = new Employee();

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from cars where idcar=?");
            ps.setInt(1, idcar);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setIdcar(rs.getInt(1));
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

                employee.setIdcar(rs.getInt(1));
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
}
