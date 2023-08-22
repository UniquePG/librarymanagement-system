package pkg_person;

@SuppressWarnings("serial")
public class Librarian extends person {
	
	private int id;
	private String dateofjoing;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateofjoing() {
		return dateofjoing;
	}
	public void setDateofjoing(String dateofjoing) {
		this.dateofjoing = dateofjoing;
	}
	@Override
	public String toString() {
		return "Librarian [id=" + id + ", dateofjoing=" + dateofjoing + ", name=" + name + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
	public Librarian(String name, String emailId, String phoneNumber, String address, String dob, int id,
			String dateofjoing) {
		super(name, emailId, phoneNumber, address, dob);
		this.id = id;
		this.dateofjoing = dateofjoing;
	}
	public Librarian() {
		super();
	}
	
	
}
