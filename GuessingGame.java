import java.util.*;

public class GuessingGame {
	
	public static void main (String... args) {
		Random rand = new Random();
		int correctNumber = rand.nextInt(50)+1;
		Scanner scan = new Scanner(System.in);
		boolean correct = false;
		int guess;
		int numberOfGuesses = 1;
		while (!correct && numberOfGuesses <= 10) {
			System.out.print("Guess a number: ");
			guess = scan.nextInt();
			if (guess == correctNumber) {
				System.out.println("Correct!");
				correct = true;
				break;
			} else if (guess > correctNumber) {
				System.out.println("Lower");
			} else {
				System.out.println("Higher");
			}
			numberOfGuesses++;
			if (numberOfGuesses == 11) {
				System.out.println("You LOST!");
			}
		}				
	}
}