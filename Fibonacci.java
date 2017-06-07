import java.util.*;

public class Fibonacci {
	
	public static void main (String... args) {
		System.out.print("Give a number: ");
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		ArrayList<Integer> fibo = new ArrayList<Integer>();
		fibo.add(0);
		fibo.add(1);
		for (int i = 2; i < input; i++) {
			fibo.add(fibo.get(i-2) + fibo.get(i-1));
		}
		System.out.println("The value of the " +input+ "th term of the Fibonacci sequence is: " + fibo.get(input-1));
		int sum = 0;
		for (int fibonumber : fibo) {
			if (fibonumber % 2 == 0) {
				sum += fibonumber;
			}
		}
		System.out.println("The sum of all even values of the first "+input+" terms is "+sum);
	}
}