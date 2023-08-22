package pkg_person;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;

public class StudentManager {
	ObjectOutputStream oos_student = null;
	ObjectInputStream ois_student = null;
	
	File st_file = null;
	
	ArrayList<Student> st_list = null;
	
	@SuppressWarnings("unchecked")
	public StudentManager() {
		st_file = new File("Student.dat");
		st_list = new ArrayList<Student>();
		
		if(st_file.exists()) {
			try {
				ois_student = new ObjectInputStream(new FileInputStream(st_file));
				st_list = (ArrayList<Student>) ois_student.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addStudent(Student student) {
		st_list.add(student);
	}
	
	public Student getstudent(int rollno)
	{
		for(Student st : st_list) {
			if(st.getRollno() == rollno);
			return st;
		}
		return null;
	}
	
	public void viwAllStudent() {
		for(Student st : st_list) {
			System.out.println(st);
		}
	}
	
	public boolean deleteStudent(int deleterollno) {
		ListIterator<Student> st_it = (ListIterator<Student>) st_list.listIterator();
		
		while(st_it.hasNext()) {
			Student st = st_it.next();
			if(st.getRollno() == deleterollno) {
				st_list.remove(st);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateStudent(int update_rollno, String name, String emailId, String phoneNumber, String address, String dob, int std, String division) {
		
		ListIterator<Student> st_it = (ListIterator<Student>) st_list.listIterator();
		
			while(st_it.hasNext()) {
				Student st = st_it.next();
				if(st.getRollno() == update_rollno) {
				st.setAddress(address);
				st.setDivision(division);
				st.setDob(dob);
				st.setEmailId(emailId);
				st.setName(name);
				st.setPhoneNumber(phoneNumber);
				st.setStd(std);
				return true;
			}
		}
		return false;
		
	}
	
	public void writetoFile() {
		try {
			oos_student = new ObjectOutputStream(new FileOutputStream(st_file));
			oos_student.writeObject(st_list);
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
