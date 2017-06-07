import java.util.*;

public class NumberPrime {
		
	public static void main (String... args) {
		ArrayList<Integer> knownPrimes = new ArrayList<Integer>();
		knownPrimes.add(2);
		int i = 3;
		while (knownPrimes.size() < 10001) {
			boolean isPrime = true;
			for (int prime : knownPrimes) {//int j = 0; j < knownPrimes.size(); j++) {
				if (i % prime == 0) {
					isPrime = false;
					break;
				}			
			}
			if (isPrime == true) {
				knownPrimes.add(i);
			}
			i++;
		}
		System.out.println(knownPrimes.get(10000));
	}
	
}