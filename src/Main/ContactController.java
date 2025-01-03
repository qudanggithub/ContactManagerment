package Main;

import DTB.CustomerDAO;
import DTB.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;

public class ContactController {

    @FXML
    private TableView<Customer> contactTable;
    @FXML
    private TableColumn<Customer, String> idColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    
    @FXML
    private TextField idtextfd;
    @FXML
    private TextField nametextfd;
    @FXML
    private TextField pnumtextfd;
    @FXML
    private TextField emailtextfd;
    
    private CustomerDAO contactDAO;
    private ObservableList<Customer> contactList;

    public void initialize(Connection connection) {
        contactDAO = new CustomerDAO(connection);

        // Configure table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

        loadContacts();
    }
    
    private void loadCustomerDetails(Customer customer) {
    	idtextfd.setText(String.valueOf(customer.getId()));
    	nametextfd.setText(customer.getName());
    	pnumtextfd.setText(customer.getPhoneNumber());
    	emailtextfd.setText(customer.getEmail());
    }
    private void loadContacts() {
        try {
            contactList = FXCollections.observableArrayList(contactDAO.getAllContacts());
            contactTable.setItems(contactList);
            contactTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    loadCustomerDetails(newSelection);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdd() {
        // Lấy dữ liệu từ các TextField
    	String id = idtextfd.getText().trim();
        String name = nametextfd.getText().trim();
        String phone = pnumtextfd.getText().trim();
        String email = emailtextfd.getText().trim();

        // Kiểm tra dữ liệu đầu vào
        if (id.isEmpty()||name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showAlert("Validation Error", "All fields must be filled out.");
            return;
        }

        // Tạo đối tượng Contact mới
        Customer newContact = new Customer();
        newContact.setId(id);
        newContact.setName(name);
        newContact.setPhoneNumber(phone);
        newContact.setEmail(email); 

        try {
        	contactDAO.addCustomer(newContact);
            // Thêm vào danh sách hiển thị
            contactList.add(newContact);

            // Xóa dữ liệu trong TextField sau khi thêm
            idtextfd.clear();
            nametextfd.clear();
            pnumtextfd.clear();
            emailtextfd.clear();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database Error", "Failed to add contact.");
        }
    }

    @FXML
    private void handleEdit() {
    	Customer selected = contactTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Logic to edit the selected contact
            System.out.println("Edit button clicked for ID: " + selected.getId());
        } else {
            showAlert("No Selection", "Please select a contact to edit.");
        }
    }

    @FXML
    private void handleDelete() {
    	Customer selected = contactTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
            	 Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            	    confirmationAlert.setTitle("Confirmation");
            	    confirmationAlert.setHeaderText("Delete Confirmation");
            	    confirmationAlert.setContentText("Are you sure you want to delete this customer?");
            	    ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
            	    if(result == ButtonType.OK) {
            	    	contactDAO.deleteContact(selected.getId());
                        contactList.remove(selected);
                        showAlert("Success!!", "Customer deleted succesfully!!");
            	    }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("No Selection", "Please select a contact to delete.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}