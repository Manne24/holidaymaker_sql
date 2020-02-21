package com.holidaymaker;

import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    DataBase db = new DataBase();


    Menu(){
        searchForCustomer();
    }

    private void addCustomer(){
        System.out.println("Please Enter Customers Firstname: ");
        String first_name = input.nextLine();
        System.out.println("Please Enter Customers Lastname: ");
        String last_name = input.nextLine();
        System.out.println("Please Enter Customers Email: ");
        String email = input.nextLine();
        System.out.println("Please Enter Customers Phonenumber: ");
        String phone_nr = input.nextLine();
        db.addCustomersToDb(first_name, last_name,email, phone_nr);

    }

    public void searchForCustomer() {
        System.out.println("Please enter the name of the customer: ");
        String first_name = input.nextLine();
        db.searchForCustomers(first_name);
    }
}
