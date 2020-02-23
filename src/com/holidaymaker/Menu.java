package com.holidaymaker;

import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    public DataBase db = new DataBase();


    Menu(){
        adminPanelMenu();
    }

    public void adminPanelMenu() {

        boolean continueToRun = true;

        while (continueToRun) {

            System.out.println("---HOLIDAYMAKER ADMIN PANEL---");
            System.out.println("  [1].  Register Customer");
            System.out.println("  [2].  Search For Customer");
            System.out.println("  [3].  Search and Book Available rooms");
            System.out.println("  [4].  Cancel booking");
            System.out.println("  [5].  EXIT");
            System.out.println("-----------------------------");

            String option = input.nextLine();

            switch (option) {
                case "1":
                    addCustomer();
                    break;
                case "2":
                    searchForCustomer();
                    break;
                case "3":
                    searchAndBookRoom();
                    break;
                case "4":
                    deleteBooking();
                    break;
                case "5":
                    continueToRun = false;
                    break;
                default:
                    System.out.println("Please enter a Number between 1-5");
                    break;
            }
        }
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
        System.out.println("Please Enter The Name Of The Customer: ");
        String first_name = input.nextLine();
        db.searchForCustomers(first_name);
        System.out.println("...Press Enter To Continue...");
        input.nextLine();
    }

    private void searchAndBookRoom() {
        System.out.println("Enter Vacation Date for Summer 2020 from June TO July ");
        System.out.println("YYYY-MM-DD");
        System.out.println("Check In: ");
        String dateCheckIn = input.nextLine();
        System.out.println("Check Out: ");
        String dateCheckOut = input.nextLine();
        System.out.println("Include Pool: [ENTER] YES:1 OR NO:0 ");
        int pool = Integer.parseInt(input.nextLine());
        System.out.println("Include Entertainment: [ENTER] YES:1 OR NO:0 ");
        int eveEntertainment = Integer.parseInt(input.nextLine());
        System.out.println("Include Child Club: [ENTER] YES:1 OR NO:0 ");
        int childClub = Integer.parseInt(input.nextLine());
        System.out.println("Include Restaurant: [ENTER ] YES:1 OR NO:0 ");
        int restaurant = Integer.parseInt(input.nextLine());
        db.filterRooms(dateCheckIn,dateCheckOut,pool, eveEntertainment,childClub,restaurant);
        db.printAvailableRooms();
        System.out.println("Enter Customer ID: ");
        int customerId = Integer.parseInt(input.nextLine());
        System.out.println("Enter Hotel ID: ");
        int hotelId = Integer.parseInt(input.nextLine());
        db.bookRoom(dateCheckIn,dateCheckOut,customerId,hotelId);
    }

    private void deleteBooking() {
        System.out.println("Please Enter ID of THE Bookings");
        int bookingID = input.nextInt();
        db.deleteBooking(bookingID);
    }
}
