package com.holidaymaker;

import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    public DataBase db = new DataBase();


    Menu(){
        searchRoom();
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

    private void searchForCustomer() {
        System.out.println("Please enter the name of the customer: ");
        String first_name = input.nextLine();
        db.searchForCustomers(first_name);
    }

    private void searchRoom() {
        System.out.println("Enter Vacation Date for Summer 2020 from June TO July ");
        System.out.println("YYYY-MM-DD");
        System.out.println("Check In: ");
        String dateCheckIn = input.nextLine();
        System.out.println("Check Out: ");
        String dateCheckOut = input.nextLine();
        System.out.println("Include Pool: [ENTER] 1 OR 0 ");
        int pool = Integer.parseInt(input.nextLine());
        System.out.println("Include Entertainment: [ENTER] 1 OR 0 ");
        int eveEntertainment = Integer.parseInt(input.nextLine());
        System.out.println("Include Child Club: [ENTER] 1 OR 0 ");
        int childClub = Integer.parseInt(input.nextLine());
        System.out.println("Include Restaurant: [ENTER ] 1 OR 0 ");
        int restaurant = Integer.parseInt(input.nextLine());
        db.filterRooms(dateCheckIn,dateCheckOut,pool, eveEntertainment,childClub,restaurant);
        db.printAvailableRooms();
    }
}
