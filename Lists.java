import java.util.*;
public class Lists {
	public static void main (String... args) {
		int[] randomNumbers = new int[10];
		Random rand = new Random();
		for (int i=0; i<10; i++) {
			randomNumbers[i] = rand.nextInt(101);
		}
		System.out.println(java.util.Arrays.toString(randomNumbers));
		
		int highestValue = -1;
		for (int i=0; i<10; i++) {
			if (randomNumbers[i] > highestValue) {
				highestValue = randomNumbers[i];
			}
		}
		System.out.println("Highest value is: "+highestValue);
		
		int lowestValue = 101;
		int secondLowestValue = 102;
		int sumOf2LowestValues;
		for (int i=0; i<10; i++) {
			if (randomNumbers[i] <= lowestValue) {
				secondLowestValue = lowestValue;
				lowestValue = randomNumbers[i];
			} else if (randomNumbers[i] < secondLowestValue) {
				secondLowestValue = randomNumbers[i];
			}
		}
		sumOf2LowestValues = lowestValue + secondLowestValue;
		System.out.println("Sum of 2 lowest values is: "+sumOf2LowestValues);
		
		System.out.print("Even values are: ");
		for (int i=0; i<10; i++) {
			if (randomNumbers[i]%2 == 0) {
				System.out.print(randomNumbers[i]+" ");
			}
		}
		System.out.print("\n");
		
		ArrayList<Integer> divisibleBy2 = new ArrayList<>();
		ArrayList<Integer> divisibleBy3 = new ArrayList<>();
		ArrayList<Integer> divisibleBy5 = new ArrayList<>();
		ArrayList<Integer> other = new ArrayList<>();
		for (int i=0; i<10; i++) {
			if (randomNumbers[i]%2 == 0) {
				divisibleBy2.add(randomNumbers[i]);
			}
			if (randomNumbers[i]%3 == 0) {
				divisibleBy3.add(randomNumbers[i]);
			}
			if (randomNumbers[i]%5 == 0) {
				divisibleBy5.add(randomNumbers[i]);
			}
			if (randomNumbers[i]%2 != 0 && randomNumbers[i]%3 != 0 && randomNumbers[i]%5 != 0) {
				other.add(randomNumbers[i]);
			}
		}
		System.out.println("Numbers divisible by 2: "+divisibleBy2);
		System.out.println("Numbers divisible by 3: "+divisibleBy3);
		System.out.println("Numbers divisible by 5: "+divisibleBy5);
		System.out.println("Other numbers: "+other);
		
		boolean changed = true;
		int temp;
		while (changed) {
			changed = false;
			for (int i=0; i<9; i++) {
				if (randomNumbers[i] > randomNumbers[i+1]) {
					temp = randomNumbers[i+1];
					randomNumbers[i+1] = randomNumbers[i];
					randomNumbers[i] = temp;
					changed = true;
				}
			}
		}
		System.out.println(java.util.Arrays.toString(randomNumbers));
	}
}