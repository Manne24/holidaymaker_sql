package com.holidaymaker;

import java.sql.*;

public class DataBase {

    private Connection connection = null;
    private PreparedStatement statement;
    private ResultSet resultSet;


    public DataBase() {
        connectToDb();
    }

    private void connectToDb() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/holidaymaker?user=root&password=mysql&serverTimezone=UTC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addCustomersToDb(String first_name, String last_name, String email, String phone_nr) {

        try {
            statement = connection.prepareStatement("INSERT INTO customers (first_name, last_name, email, phone_nr) VALUES (?, ?, ?, ?)");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setString(4, phone_nr);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error message: " + "\n" + e.getMessage() + "\n");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void searchForCustomers(String first_name) {
        try {
            statement = connection.prepareStatement("SELECT * FROM customers WHERE first_name = ? ");
            statement.setString(1, first_name);


            try {
                resultSet = statement.executeQuery();
            } catch (SQLException e) {
                System.out.println("Error message: " + "\n" + e.getMessage() + "\n");
            }


            while (resultSet.next()) {
                String customerPrint =
                                "ID: " + resultSet.getString("id") + "\n" +
                                "FIRST NAME: " + resultSet.getString("first_name") + "\n" +
                                "LAST NAME: " + resultSet.getString("last_name") + "\n" +
                                "EMAIL: " + resultSet.getString("email") + "\n" +
                                "PHONENUMBER: " + resultSet.getString("phone_nr");
                System.out.println(customerPrint);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void filterRooms(String first_name, String last_name, String email, String phone_nr) {

        try {
            statement = connection.prepareStatement("INSERT INTO customers (first_name, last_name, email, phone_nr) VALUES (?, ?, ?, ?)");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setString(4, phone_nr);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error message: " + "\n" + e.getMessage() + "\n");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
