package employee.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;

public class EmployeePerson {

	private static final String NAME_PATTERN = "[a-zA-Z]{2,20}+";
	private static final String NUMBER_PATTERN = "^\\+[0-9]{11}+";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
		
	@Id
	private String id;

	@NotNull(message = "First name is required")
	@Pattern(regexp = NAME_PATTERN)
	private String firstName;
	
	@NotNull(message = "Last name is required")
	@Pattern(regexp = NAME_PATTERN)
	private String lastName;
	
	@Pattern(regexp = NUMBER_PATTERN)
	private String contactNumber;
	
	@Pattern(regexp = EMAIL_PATTERN)
	private String email;
	
	private String positionId;
	private String directHeadId;

	public EmployeePerson(String firstName, String lastName) {
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

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getDirectHeadId() {
		return directHeadId;
	}

	public void setDirectHeadId(String directHeadId) {
		this.directHeadId = directHeadId;
	}
}
