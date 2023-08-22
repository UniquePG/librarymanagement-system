package pkg_Main;

import java.util.Scanner;

import pkg_Book.Book;
import pkg_Book.BookManager;
import pkg_Transaction.BookTransactionManager;
import pkg_exception.BookNotFoundException;
import pkg_exception.StudentNotFoundException;
import pkg_person.Student;
import pkg_person.StudentManager;

public class Main {

	public static void main(String[] args) {
		int choice;

		Scanner sc = new Scanner(System.in);

		BookManager bm = new BookManager();
		StudentManager sm = new StudentManager();
		BookTransactionManager btm = new BookTransactionManager();

		do {
			System.out.println("Enter 1 if Student \nEnter 2 if Librarian \nEnter 3 if want to Exit");
			choice = sc.nextInt();

			if (choice == 1) // if user is student
			{
				System.out.println("Enter Your Roll Number");
				int rollno = sc.nextInt();

				try {
					Student s = sm.getstudent(rollno);
					if (s == null)
						throw new StudentNotFoundException();

					int stud_choice;
					do {
						System.out.println("  Enter 1 to view all books \n  Enter 2 to Search book by ISBN \n  Enter 3 to list books by subject \n  Enter 4 to Issue a book \n  Enter 5 to Return a book \n  Enter 99 to Exit");
						stud_choice = sc.nextInt();

						switch (stud_choice) {
						case 1:
							System.out.println("All The Books Records are: ");
							bm.viewAllBook();
							break;
						case 2:
							int search_isbn;
							System.out.println("Enter ISBN of the book to Search");
							search_isbn = sc.nextInt();
							Book book = bm.searhBookByisbn(search_isbn);
							if (book == null)
								System.out.println("No Book with this ISBN Exists in our Library");
							else
								System.out.println(book);

							break;

						case 3:
							System.out.println("Enter the subject");
							sc.nextLine();
							String search_sub = sc.nextLine();
							bm.listBookbySubject(search_sub);

							break;

						case 4:
							System.out.println("Enter the ISBN to issue a Book");
							int issue_isbn = sc.nextInt();
							book = bm.searhBookByisbn(issue_isbn);
							try {
								if (book == null) {
									throw new BookNotFoundException();
								}
								if (book.getAvailable_quantity() > 0) {
									if (btm.issueBook(rollno, issue_isbn)) {
										book.setAvailable_quantity(book.getAvailable_quantity() - 1);
										System.out.println("Book has been Issued");
									}
								} else
									System.out.println("The Book has been Issued...");

							} catch (BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
							break;

						case 5:
							System.out.println("Please Enter The ISBN to Return the book");
							int return_isbn = sc.nextInt();
							book = bm.searhBookByisbn(return_isbn);

							if (book != null) {
								if (btm.returnbook(rollno, return_isbn)) {
									book.setAvailable_quantity(book.getAvailable_quantity() + 1);
									System.out.println("Thank you for Returning the Book");
								} else
									System.out.println("Could not Return the Book");
							} else
								System.out.println("Book with this ISBN does not Exists");

							break;

						case 99:
							System.out.println("Thank You for using our Library System");
							break;

						default:
							System.out.println("Invalid choice");
						}

					} while (stud_choice != 99);

				} catch (StudentNotFoundException snfe) {
					System.out.println(snfe);
				}
			}

			else if (choice == 2) // User is librarian
			{
				int lib_choice;
				do {

					System.out.println("  Enter 11 to view all Students \n  Enter 12 to print a Student by Roll number \n  Enter 13 to Register a Student \n  Enter 14 to Update a Student \n  Enter 15 to Delete a Student");
					System.out.println("  Enter 21 to view all Books \n  Enter 22 to print a Book by ISBN \n  Enter 23 to Add a new book \n  Enter 24 to Update a Book \n  Enter 25 to Delete a Book");
					System.out.println("  Enter 31 to view all Transactions");
					System.out.println("  Enter 99 to Exit");
					lib_choice = sc.nextInt();

					switch (lib_choice) {
					case 11: // For view all Students
						System.out.println("All The Student Records");
						sm.viwAllStudent();
						break;
					case 12: // Search Student by Roll number
						System.out.println("Enter Roll Number to Fetch Student's Record");
						int get_rollno = sc.nextInt();

						Student student = sm.getstudent(get_rollno);

						if (student == null)
							System.out.println("No Record Matches for this roll Number");
						else
							System.out.println(student);
						break;

					case 13: // Register a new Student
						System.out.println("Enter Student Details to Add");
						String name;
						String emailId;
						String phoneNumber;
						String address;
						String dob;
						int rollno;
						int std;
						String division;

						sc.nextLine();
						System.out.println("Name");
						name = sc.nextLine();

						System.out.println("Email ID");
						emailId = sc.nextLine();

						System.out.println("Phone Number");
						phoneNumber = sc.nextLine();

						System.out.println("Address");
						address = sc.nextLine();

						System.out.println("Date of Birth");
						dob = sc.nextLine();

						System.out.println("Roll Number as Integer");
						rollno = sc.nextInt();

						System.out.println("Standard(std) as Integer");
						std = sc.nextInt();

						sc.nextLine();

						System.out.println("Division");
						division = sc.nextLine();

						student = new Student(name, emailId, phoneNumber, address, dob, rollno, std, division);
						sm.addStudent(student);
						System.out.println("Student is added");
						break;

					case 14: // update a student
						System.out.println("Enter the Roll number to modify the Record");
						int modify_rollno;
						modify_rollno = sc.nextInt();

						student = sm.getstudent(modify_rollno);
						try {
							if (student == null)
								throw new StudentNotFoundException();

							sc.nextLine();
							System.out.println("Name");
							name = sc.nextLine();

							System.out.println("Email ID");
							emailId = sc.nextLine();

							System.out.println("Phone Number");
							phoneNumber = sc.nextLine();

							System.out.println("Address");
							address = sc.nextLine();

							System.out.println("Date of Birth");
							dob = sc.nextLine();

							System.out.println("Standard as Integer");
							std = sc.nextInt();

							sc.nextLine();

							System.out.println("Division");
							division = sc.nextLine();

							sm.updateStudent(modify_rollno, name, emailId, phoneNumber, address, dob, std, division);
							System.out.println("Student Record is updated");

						} catch (StudentNotFoundException snfe) {
							System.out.println(snfe);
						}
						break;

					case 15: // To delete a Student

						System.out.println("Enter the Roll number to Delete record");
						int delete_rollno;
						delete_rollno = sc.nextInt();

						if (sm.deleteStudent(delete_rollno))
							System.out.println("Student Record is Deleted");
						else
							System.out.println("No Record Exists with given Roll number");

						break;

					// Book management

					
					  case 21: // view all books by isbn
						bm.viewAllBook(); 
						break; 
						
					case 22: //search by isbn 
						int search_isbn;
							
					   System.out.println("Enter ISBN of the book to Search"); 
					   search_isbn = sc.nextInt(); 
					   Book book = bm.searhBookByisbn(search_isbn); // error Book
					   if(book == null)
					      System.out.println("No Book with this ISBN Exists in our Library");
					   else
					     System.out.println(book);
					   break; 
					   
					   case 23: //add a Book
					   System.out.println("Please Enter Book details to Add");
					   int isbn; 
					   String title; 
					   String author; 
					   String publisher;
					   int edition;
					   String subject; 
					   int available_quantity;
					 
					  System.out.println("ISBN"); isbn = sc.nextInt();
					  
					  sc.nextLine();
					  
					  System.out.println("Title"); title = sc.nextLine();
					  
					  System.out.println("Author"); author = sc.nextLine();
					  
					  System.out.println("Publisher"); publisher = sc.nextLine();
					  
					  System.out.println("edition"); edition = sc.nextInt();
					  
					  sc.nextLine();
					  
					  System.out.println("Subject"); subject = sc.nextLine();
					  
					  System.out.println("Quantity"); available_quantity = sc.nextInt();
					  
					  book = new Book(isbn, title, author, publisher, edition, subject, available_quantity);
					  bm.addaBook(book);
					  System.out.println("A Book Record is Added");
					  
					  break;
					  
					  case 24: // update a book
					  System.out.println("Please Enter the ISBN to update the book record"); 
					  int  update_isbn;
					  update_isbn = sc.nextInt();
					  
					  try { 
						  book = bm.searhBookByisbn(update_isbn);
						  if(book == null)
							  throw new BookNotFoundException();
						  
					   sc.nextLine();
					  
					  System.out.println("Title");
					  title = sc.nextLine();
					  
					  System.out.println("Author"); 
					  author = sc.nextLine();
					  
					  System.out.println("Publisher"); 
					  publisher = sc.nextLine();
					  
					  System.out.println("edition"); 
					  edition = sc.nextInt();
					  
					  sc.nextLine();
					  
					  System.out.println("Subject"); 
					  subject = sc.nextLine();
					  
					  System.out.println("Quantity");
					  available_quantity = sc.nextInt();
					  
					  bm.updatebook(update_isbn, title, author, publisher, edition, subject,  available_quantity);
					  
					  }catch(BookNotFoundException bnfe) {
						  bnfe.printStackTrace(); 
					  		} 
					  break;
					  
					  case 25: // delete a record of book
					  System.out.println("Please Enter the ISBN to Delete the book record"); 
					  int delete_isbn; 
					  delete_isbn = sc.nextInt();
					  
					  try {
						  book = bm.searhBookByisbn(delete_isbn); 
						  if(book == null) 
							  throw new BookNotFoundException();
						  bm.deleteBook(delete_isbn);
					  
					  }catch(BookNotFoundException bnfe) { 
						  bnfe.printStackTrace(); 
						  }
					  
					  break;
					  
					  case 31: // To view all transactions
					  System.out.println("All The Transactions are: ");
					  btm.showall();
					  
					  break;
					 
					case 99:
						System.out.println("Thank You for using our Library System");
						break;

					default:
						System.out.println("Invalid choice");

					}

				} while (lib_choice != 99);

			}

		} while (choice != 3);

		sm.writetoFile();
		bm.writetoFile();
		btm.writetoFile();
		sc.close();
	}
}
