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
                System.out.println("Error: " + "\n" + e.getMessage() + "\n");
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
                System.out.println("Error: " + "\n" + e.getMessage() + "\n");
            }


            while (resultSet.next()) {
                String customerPrintResult =
                                "ID: " + resultSet.getString("id") + "\n" +
                                "FIRST NAME: " + resultSet.getString("first_name") + "\n" +
                                "LAST NAME: " + resultSet.getString("last_name") + "\n" +
                                "EMAIL: " + resultSet.getString("email") + "\n" +
                                "PHONENUMBER: " + resultSet.getString("phone_nr");
                System.out.println(customerPrintResult);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void filterRooms(String DateCheckIn, String DateCheckOut, int pool , int eveEnterainment, int childClub, int resutrant) {

        try {
            statement = connection.prepareStatement("SELECT room_number,accommodations.id, `name` AS 'Hotel', city , country FROM bookings\n" +
                    "JOIN rooms ON bookings.room_number_id = rooms.room_number\n" +
                    "JOIN accommodations ON bookings.accommodation_id = accommodations.id\n" +
                    "JOIN adress ON bookings.accommodation_id = adress.id \n" +
                    "WHERE ( ? NOT BETWEEN date_from AND date_to)\n" +
                    "AND ( ? NOT BETWEEN date_from AND date_to)\n" +
                    "AND pool =  ? \n" +
                    "AND evening_enterainment = ? \n" +
                    "AND child_club = ?\n" +
                    "AND resutrant =  ? ");
            statement.setString(1, DateCheckIn);
            statement.setString(2, DateCheckOut);
            statement.setInt(3, pool);
            statement.setInt(4, eveEnterainment);
            statement.setInt(5, childClub);
            statement.setInt(6, resutrant);
            resultSet = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAvailableRooms() {
        try {
            while (resultSet.next()) {
                String roomsPrint = "ROOM NR: " + resultSet.getString("room_number")
                        + ", HOTEL ID: " + resultSet.getString("accommodations.id")
                        + ", HOTEL: " + resultSet.getString("Hotel")
                        + ", CITY: " + resultSet.getString("city")
                        + ", COUNTRY: " + resultSet.getString("country");
                System.out.println(roomsPrint);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void bookRoom(String date_from, String date_to, int customers_id, int accommodation_id){
        try {
            statement = connection.prepareStatement("INSERT INTO bookings (date_from, date_to, customers_id, accommodation_id) VALUES (?, ?, ?, ?)");
            statement.setString(1, date_from);
            statement.setString(2, date_to);
            statement.setInt(3, customers_id);
            statement.setInt(4, accommodation_id);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void deleteBooking(int bookingId) {

        try {
            statement = connection.prepareStatement("DELETE FROM bookings WHERE booking_id = ?");
            statement.setInt(1, bookingId);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
