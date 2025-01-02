package DTB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {
	
	private Connection connection;

    public  CustomerDAO(Connection connection) {
        this.connection = connection;
    }
	public void addCustomer(Customer cus) throws SQLException{
		String query = "INSERT INTO Customer (Id,  PhoneNumber,Name, Email) VALUES (?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, cus.getId());            
			stmt.setString(2, cus.getPhoneNumber());
            stmt.setString(3, cus.getName());
            stmt.setString(4, cus.getEmail());
            stmt.executeUpdate();
        }
	}
	 public void updateContact(Customer contact) throws SQLException {
	        String query = "UPDATE Customer SET  PhoneNumber = ?,Name = ?, Email = ? WHERE Id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        	stmt.setString(1, contact.getPhoneNumber());
	        	stmt.setString(2, contact.getName());
	            stmt.setString(3, contact.getEmail());
	            stmt.setString(4, contact.getId());
	            stmt.executeUpdate();
	        }
	    }
	 public List<Customer> getAllContacts() throws SQLException {
	        List<Customer> 	Customers = new ArrayList<>();
	        String query = "SELECT * FROM Customer";
	        try (Statement stmt = connection.createStatement(); 
	        	ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                Customer customer = new Customer(
	                    rs.getString("Id"),
	                    rs.getString("PhoneNumber"),
	                    rs.getString("Name"),
	                    rs.getString("Email")
	                );
	                Customers.add(customer);
	            }
	        }
	        return Customers;
	    }
	 public void deleteContact(String id) throws SQLException {
	        String query = "DELETE FROM Customer WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, id);
	            stmt.executeUpdate();
	        }
	    }
}
