/**
 * The Customer Class is the basic structure for the customer object.
 * It contains attributes and methods to access the attributes.
 * It also has a toString method for data processing
 */
public class Customer {
	private String firstName;
	private String lastName;
	private int id;
	private String address;
	
	public Customer(String firstName, String lastName, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return String.format("First Name: %S%nLast Name: %S%nCustID: %d%n"
				+ "Address: %s%n", firstName, lastName, id, address);
	}
}
