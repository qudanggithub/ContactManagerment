package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import DTB.Customer;
import DTB.CustomerDAO;
import DTB.Database;

public class Main {

	public static void main(String[] args) {
		try (Connection con = Database.getConnection();) {
            CustomerDAO cusDAO = new CustomerDAO(con);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nContact Management System");
                System.out.println("1. Add Contact");
                System.out.println("2. View All Contacts");
                System.out.println("3. Update Contact");
                System.out.println("4. Delete Contact");
               
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                    	System.out.print("Enter id: ");
                        String id = scanner.nextLine();
                    	System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        cusDAO.addCustomer(new Customer(id,phone,name, email));
                        System.out.println("Contact added successfully!");
                        break;

                    case 2:
                        for (Customer cus : cusDAO.getAllContacts()) {
                            System.out.println(cus.getId() + " - " + cus.getPhoneNumber() + " - " + cus.getName() + " - " + cus.getEmail());
                        }
                        break;
                    case 3:
                    	 System.out.print("Enter ID to edit: ");
                         String idToEdit = scanner.nextLine();
                         scanner.nextLine(); // Consume newline
                         
                         System.out.print("Enter new phone: ");
                         String newPhone = scanner.nextLine();
                         System.out.print("Enter new name: ");
                         String newName = scanner.nextLine();
                         
                         System.out.print("Enter new email: ");
                         String newEmail = scanner.nextLine();

                         cusDAO.updateContact(new Customer(idToEdit, newPhone, newName, newEmail));
                         System.out.println("Contact updated successfully!");
                         break;
                    case 4:
                        System.out.print("Enter ID to delete: ");
                        String idd = scanner.nextLine();
                        cusDAO.deleteContact(idd);
                        System.out.println("Contact deleted successfully!");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	}


