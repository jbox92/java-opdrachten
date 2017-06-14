import java.io.*;
import java.util.*;

public class Profiling {
	
	static Scanner scan;
	static ArrayList<Profile> readers = new ArrayList<>();
	
	public static void main(String... args) {
		ArrayList<String> input = new ArrayList<>();
		try {
			File file = new File("profiles.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				input.add(line);
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (String s:input) {
			readers.add(new Profile(s));
		}
		scan = new Scanner(System.in);
		booksReadByCustomer();
		customersReadBook();
		recommendBook();
	}
	
	static void booksReadByCustomer() {
		System.out.print("Customer name: ");
		String tempName = scan.nextLine();
		for (Profile p:readers) {
			if (p.name.toLowerCase().contains(tempName.toLowerCase())) {
				p.printBooks();
			}
		}
	}
	
	static void customersReadBook() {
		System.out.print("Book title: ");
		String tempBook = scan.nextLine();
		ArrayList<Profile> haveRead = new ArrayList<>();
		for (Profile p:readers) {
			for (String s:p.bookTitels) {
				if (s.equals(tempBook)) {
					haveRead.add(p);
				}
			}
		}
		for (Profile p:haveRead) {
			p.printName();
		}
	}
	
	static void recommendBook() {
		System.out.print("Customer name: ");
		String tempName = scan.nextLine();
		boolean nameFound = false;
		int[] matchingBooks = new int[0];
		boolean recommendationFound = false;
		for (Profile p: readers) {
			if (p.name.equals(tempName)) {
				nameFound = true;
				matchingBooks = new int[readers.size()];
				for (String s:p.bookTitels) {
					for (int i=0; i<readers.size(); i++) {
						if (readers.get(i).readBook(s)) {
							matchingBooks[i]++;
						}
					}
				}
				for (int i=0; i<matchingBooks.length; i++) {
					if (matchingBooks[i] > 2 && !readers.get(i).name.equals(tempName)) {
						for (String s:readers.get(i).bookTitels) {
							if (!p.readBook(s)) { //&& !recommendationFound) {
								System.out.println(tempName+", we recommend \""+s+"\" to you.");
								recommendationFound = true;
								break;
							}
						}
					}
				}
			}
		}
		if (!nameFound) {
			System.out.println("Customer is not found.");
		} else if (!recommendationFound) {
			System.out.println("There are no recommendations at this time.");
		}
	}
}

class Profile {
	String name;
	String[] bookTitels;
	
	Profile(String data) {
		String[] splitData = data.split(",");
		name = splitData[0].trim();
		bookTitels = new String[splitData.length-1];
		for (int i = 1; i<splitData.length; i++) {
			bookTitels[i-1] = splitData[i].trim();
		}
	}
	
	void printBooks() {
		System.out.println(Arrays.toString(bookTitels));
	}
	
	void printName() {
		System.out.println(name);
	}
	
	boolean readBook(String book) {
		for (String s:bookTitels) {
			if (s.equals(book)) {
				return true;
			}
		}
		return false;
	}
}