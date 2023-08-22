package pkg_Transaction;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookTransaction implements Serializable{
	private int isbn;
	private int rollno;
	private String issuedate;
	private String returndate;
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public BookTransaction(int isbn, int rollno, String issuedate, String returndate) {
		super();
		this.isbn = isbn;
		this.rollno = rollno;
		this.issuedate = issuedate;
		this.returndate = returndate;
	}
	public BookTransaction() {
		super();
	}
	@Override
	public String toString() {
		return "BookTransaction [isbn=" + isbn + ", rollno=" + rollno + ", issuedate=" + issuedate + ", returndate="
				+ returndate + "]";
	}
	
}
