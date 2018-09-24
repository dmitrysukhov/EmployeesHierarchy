package employee;


import org.springframework.data.annotation.Id;

public class EmployeePerson {


	@Id private String id;

	private String firstName;
	private String lastName;
	private String contactNumber;
	private String email;
	private String position;
	private String directHead;

	public EmployeePerson(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDirectHead() {
		return directHead;
	}

	public void setDirectHead(String directHead) {
		this.directHead = directHead;
	}
}
