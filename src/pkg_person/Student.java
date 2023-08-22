package pkg_person;

@SuppressWarnings("serial")
public class Student extends person {

	private int rollno;
	private int std;
	private String division;
	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public int getStd() {
		return std;
	}
	public void setStd(int std) {
		this.std = std;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public Student(String name, String emailId, String phoneNumber, String address, String dob, int rollno, int std, String division) {
		super(name, emailId, phoneNumber, address, dob);
		this.rollno = rollno;
		this.std = std;
		this.division = division;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", std=" + std + ", division=" + division + ", name=" + name + ", emailId="
				+ emailId + ", phoneNumber=" + phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
	
	
}
