package com.example.demo;

public class Employee {

    private Integer idcar;
    private String model;
    private String color;
    private int doors;


    public Integer getIdcars() {
        return idcar;
    }

    public void setIdcars(Integer idcar) {
        this.idcar = idcar;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idcar=" + idcar +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", doors=" + doors +
                '}';
    }
}
