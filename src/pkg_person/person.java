package pkg_person;

import java.io.Serializable;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
abstract public class person implements Serializable {

	protected String name;
	protected String emailId;
	protected String phoneNumber;
	protected String address;
	protected String dob;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		boolean isValidname = Pattern.matches("[a-zA-Z]+", name);
		if(isValidname)
			this.name = name;
		else
			this.name = "Default name";
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		boolean isvalidDob = Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dob);
		
		if(isvalidDob)
			this.dob = dob;
		else
			this.dob = "01-05-2005";
	}
	public person(String name, String emailId, String phoneNumber, String address, String dob) {
		super();
		this.setName(name);
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.setDob(dob);
	}
	public person() {
		super();
	}
	
	
}
