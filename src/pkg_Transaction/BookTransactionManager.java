package pkg_Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class BookTransactionManager {
	ObjectOutputStream oos_BookTransaction = null;
	ObjectInputStream ois_BookTransaction = null;
	
	File BookTransaction_file = null;
	
	ArrayList<BookTransaction> BookTransaction_list = null;
	
	@SuppressWarnings("unchecked")
	public BookTransactionManager() {
		BookTransaction_file = new File("BookTransaction.dat");
		BookTransaction_list = new ArrayList<BookTransaction>();
		
		if(BookTransaction_file.exists()) {
			try {
				ois_BookTransaction = new ObjectInputStream(new FileInputStream(BookTransaction_file));
				BookTransaction_list = (ArrayList<BookTransaction>) ois_BookTransaction.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean issueBook(int rollno, int isbn) {
		int total_book_issue =0;
		
		for(BookTransaction b_trans :BookTransaction_list) {
			
			if((b_trans.getRollno() == rollno) && (b_trans.getReturndate() == null)){
				total_book_issue += 1;
			}
			if(total_book_issue >= 3) {
				return false;
			}
		}
		String issue_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		BookTransaction book_transaction = new BookTransaction(isbn,rollno,issue_date, null);
		BookTransaction_list.add(book_transaction);
		return true;
	}
	
	public boolean returnbook(int rollno, int isbn) {
		for(BookTransaction b_trans :BookTransaction_list) {
			if((b_trans.getRollno() == rollno) && (b_trans.getReturndate() == null) && b_trans.getIsbn() == isbn) {
				String return_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				b_trans.setReturndate(return_date);
				return true;
			}
		}
		return false;
	}
	
	public void writetoFile() {
		try {
			oos_BookTransaction = new ObjectOutputStream(new FileOutputStream(BookTransaction_file));
			oos_BookTransaction.writeObject(BookTransaction_list);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showall() {
		for(BookTransaction book_trans : BookTransaction_list)
			System.out.println(book_trans);
	}
	
	
}
