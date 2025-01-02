package DTB;

public class Customer {
	private String Id;
	private String PhoneNumber;
	private String Name;
	private String Email;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Customer(String id, String phoneNumber, String name, String email) {
		this.Id = id;
		this.PhoneNumber = phoneNumber;
		this.Name = name;
		this.Email = email;
	}
	
}
