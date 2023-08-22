package pkg_Book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;

public class BookManager {
		ObjectOutputStream oos_book = null;
		ObjectInputStream ois_book = null;
		
		File book_File = new File("Book.dat");
		
		ArrayList <Book> book_list = null;		// make a arraylist of the book for doing transactions
		
		// file Data take into Arraylist
		@SuppressWarnings("unchecked")
		public BookManager() {
			book_list = new ArrayList <Book>();
			
			if(book_File.exists()) {
				try {
					ois_book = new ObjectInputStream(new FileInputStream(book_File));	// Give the object of FileInputStream to ObjectInputStream
					book_list = (ArrayList<Book>) ois_book.readObject();	
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		public void listBookbySubject(String subject) {
			for(Book bk : book_list) {
				if(bk.getSubject().equals(subject))
					System.out.println(bk);
			}
		}
		public void addaBook(Book book) {	// Add books to the arraylist
			book_list.add(book);
		}
		
		public void viewAllBook() {
			for(Book book : book_list) {		// For printing book
				System.out.println(book);
			}
		}
		
		public Book searhBookByisbn(int search_isbn) {		// search book by its isbn
			for(Book book: book_list) {
				if(book.getIsbn() == search_isbn)
					return book;
			}
			return null;
		}
		
		public boolean deleteBook(int delete_isbn) {		// delete book by isbn
			ListIterator<Book> b_it = (ListIterator<Book>) book_list.listIterator();
			while(b_it.hasNext()) {
				Book book = b_it.next();
				if(book.getIsbn() == delete_isbn) {
					book_list.remove(book);
					return true;
				}
			}
			return false;
		}
		
		public boolean updatebook(int update_isbn, String title, String author, String publisher, int edition, String subject, int available_quantity) // update book by isbn
		{		
			ListIterator<Book> b_it = (ListIterator<Book>) book_list.listIterator();
			while(b_it.hasNext()) {
				Book book = b_it.next();
				if(book.getIsbn() == update_isbn) 
				{
					book.setAuthor(author);
					book.setAvailable_quantity(available_quantity);
					book.setEdition(edition);
					book.setPublisher(publisher);
					book.setSubject(subject);
					book.setTitle(title);
					
					return true;
				}
			}
			return false;
		}
		
		
		public void writetoFile() {
			try {
				oos_book = new ObjectOutputStream(new FileOutputStream(book_File));
				oos_book.writeObject(book_list);
				
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
}
