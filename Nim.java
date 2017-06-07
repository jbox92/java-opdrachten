import java.util.*;
public class Nim {
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String... args) {
		System.out.println("Do you want to play a 1 or 2 player game?");
		int playStyle = scan.nextInt();
		if (playStyle == 1) {
			computerGame();
		}
		if (playStyle == 2) {
			twoPlayerGame();
		}
	}
	
	public static void twoPlayerGame() {
		int activePlayer = 1;
		int matchesLeft = 11;
		while (matchesLeft > 0) {
			activePlayer = (activePlayer + 1)%2;
			System.out.println("There are "+matchesLeft+" matches. How many matches do you want to take, player "+(activePlayer+1)+"?");
			int matchesTaken = scan.nextInt();
			if (matchesTaken >= 1 && matchesTaken <= 4 && matchesLeft - matchesTaken >= 0) {
				matchesLeft -= matchesTaken;
			} else {
				activePlayer = (activePlayer + 1)%2;
			}
		}
		System.out.println("Player "+(activePlayer+1)+", you took the last match. You lost!");
	}
	
	public static void computerGame() {
		int matchesLeft = 11;
		while (matchesLeft > 0) {
			System.out.println("There are "+matchesLeft+" matches. How many matches do you want to take?");
			int matchesTaken = scan.nextInt();
			if (matchesTaken >= 1 && matchesTaken <= 4 && matchesLeft - matchesTaken >= 0) {
				matchesLeft -= matchesTaken;
				if (matchesLeft == 0) {
					System.out.println("You took the last match. You lost!");
					break;
				}
				matchesLeft -= 5-matchesTaken;
				System.out.println("The computer took "+(5-matchesTaken)+" matches.");
			}
		}
	}
}