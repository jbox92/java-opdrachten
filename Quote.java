import java.time.*;
public class Quote {
	
	public static String[][] quotes = {
	{"galileo", "eppur si muove"},
	{"archimedes", "eureka!"},
	{"erasmus", "in regione caecorum rex est luscus"},
	{"socrates", "I know nothing except the fact of my ignorance"},
	{"rené descartes", "cogito, ergo sum"},
	{"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
	};
	
	public static void main(String... args) {
		LocalDate date = LocalDate.now();
		printQuote((date.getDayOfMonth()-1)%6);
	}
	
	public static void printQuote(int day) {
		System.out.println("\""+manipulateText(quotes[day][1])+"\" -- "+manipulatePerson(quotes[day][0]));
	}
	
	public static String manipulatePerson(String person) {
		person = person.substring(0,1).toUpperCase()+person.substring(1);
		int space;
		while (person.contains(" ")) {
			space = person.indexOf(' ');
			person = person.substring(0,space)+'0'+person.substring(space+1,space+2).toUpperCase()+person.substring(space+2);			
		}
		person = person.replace('0',' ');
		return person;
	}
	
	public static String manipulateText(String text) {
		text = text.substring(0,1).toUpperCase()+text.substring(1);
		if (!text.endsWith("!")) {
			text = text+".";
		}
		return text;
	}
}