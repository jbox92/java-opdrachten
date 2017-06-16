import java.util.*;
import java.time.*;

public class Sudoku {
	
	static int[][] puzzleState = new int[9][9];
	static String[] input;
	static ArrayList<int[]> unknowns = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> optionsUnknowns = new ArrayList<>();
	
	public static void main(String... args) {
		LocalTime start = LocalTime.now();
		System.out.println(start);
		input = args[0].split("");
		initialize();
		System.out.println("Initial state:");
		printState();
		solve();
		System.out.println("Solved state:");
		printState();
		LocalTime finish = LocalTime.now();
		System.out.println(finish);
	}
	
	static void initialize() {
		int k = 0;
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				puzzleState[i][j] = Integer.parseInt(input[k]);
				if (Integer.parseInt(input[k]) == 0) {
					int[] coordinates = {j,i};
					unknowns.add(coordinates);
					ArrayList<Integer> possibilities = new ArrayList<>();
					for (int n=1; n<10; n++) {
						possibilities.add(n);
					}
					optionsUnknowns.add(possibilities);
				}
				k++;
			}
		}
	}
	
	static void printState() {
		System.out.println("-------------------");
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				System.out.print("|");
				if (puzzleState[i][j] != 0) {
					System.out.print(puzzleState[i][j]);
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("|\n");
			System.out.println("-------------------");
		}
	}
	
	static void solve() {
		ArrayList<ArrayList<Integer>> oldOptions = new ArrayList<ArrayList<Integer>>();
		while (!solved()) {
			while (optionsUnknowns != oldOptions) {
				oldOptions = optionsUnknowns;
				easyMode();
			}
			hardMode();
			oldOptions = new ArrayList<ArrayList<Integer>>();
			//System.out.println(optionsUnknowns);
			//printState();
		}
	}
	
	static void easyMode() {
		for (int a = 0; a<unknowns.size(); a++) {
			removeRow(unknowns.get(a)[0], unknowns.get(a)[1], a);
			removeColumn(unknowns.get(a)[0], unknowns.get(a)[1], a);
			removeBox(unknowns.get(a)[0], unknowns.get(a)[1], a);
			if (optionsUnknowns.get(a).size() == 1) {
				puzzleState[unknowns.get(a)[1]][unknowns.get(a)[0]] = optionsUnknowns.get(a).get(0);
				unknowns.remove(a);
				optionsUnknowns.remove(a);
			}
		}
	}
	
	static void hardMode() {
		for (int i=0; i<9; i++) {
			checkRow(i);
		}
		for (int j=0; j<9; j++) {
			checkColumn(j);
		}
		for (int k=0; k<3; k++) {
			for (int l=0; l<3; l++) {
				checkBox(l*3,k*3);
			}
		}
	}
	
	static void checkRow(int i) {
		ArrayList<Integer> overlap = new ArrayList<>();
		boolean numberUsed;
		for (int b=0; b<optionsUnknowns.size(); b++) {
			if (unknowns.get(b)[1] == i) {
				for (int n:optionsUnknowns.get(b)) {
					overlap.add(n);
				}
			}
		}
		for (int n=1; n<10; n++) {
			numberUsed = false;
			if (uniqueNumber(n, overlap)) {
				for (int b=0; b<optionsUnknowns.size(); b++) {
					if (unknowns.get(b)[1] == i && optionsUnknowns.get(b).contains(n)) {
						for (int j=0; j<9; j++) {
							if (puzzleState[i][j] == n) {
								numberUsed = true;
							}
						}
						if (!numberUsed) {
							puzzleState[i][unknowns.get(b)[0]] = n;
							unknowns.remove(b);
							optionsUnknowns.remove(b);
						}
					}
				}
			}
		}
	}
	
	static void checkColumn(int j) {
		ArrayList<Integer> overlap = new ArrayList<>();
		boolean numberUsed;
		for (int b=0; b<optionsUnknowns.size(); b++) {
			if (unknowns.get(b)[0] == j) {
				for (int n:optionsUnknowns.get(b)) {
					overlap.add(n);
				}
			}
		}
		for (int n=1; n<10; n++) {
			numberUsed = false;
			if (uniqueNumber(n, overlap)) {
				for (int b=0; b<optionsUnknowns.size(); b++) {
					if (unknowns.get(b)[0] == j && optionsUnknowns.get(b).contains(n)) {
						for (int i=0; i<9; i++) {
							if (puzzleState[i][j] == n) {
								numberUsed = true;
							}
						}
						if (!numberUsed) {
							puzzleState[unknowns.get(b)[1]][j] = n;
							unknowns.remove(b);
							optionsUnknowns.remove(b);
						}
					}
				}
			}
		}
	}
	
	static void checkBox(int x, int y) {
		ArrayList<Integer> overlap = new ArrayList<>();
		boolean numberUsed;
		for (int b=0; b<optionsUnknowns.size(); b++) {
			if (unknowns.get(b)[0] >= x && unknowns.get(b)[0] < x+3 && unknowns.get(b)[1] >= y && unknowns.get(b)[1] < y+3) {
				for (int n:optionsUnknowns.get(b)) {
					overlap.add(n);
				}
			}
		}
		for (int n=1; n<10; n++) {
			numberUsed = false;
			if (uniqueNumber(n, overlap)) {
				for (int b=0; b<optionsUnknowns.size(); b++) {
					if (unknowns.get(b)[0] >= x && unknowns.get(b)[0] < x+3 && unknowns.get(b)[1] >= y && unknowns.get(b)[1] < y+3 && optionsUnknowns.get(b).contains(n)) {
						for (int i=y; i<y+3; i++) {
							for (int j=x; j<x+3; j++) {
								if (puzzleState[i][j] == n) {
									numberUsed = true;
								}
							}
						}
						if (!numberUsed) {
							puzzleState[unknowns.get(b)[1]][unknowns.get(b)[0]] = n;
							unknowns.remove(b);
							optionsUnknowns.remove(b);
						}
					}
				}
			}
		}
	}
	
	static boolean uniqueNumber(int n, ArrayList<Integer> listN) {
		int counter = 0;
		for (int k:listN) {
			if (k == n) {
				counter++;
			}
		}
		return counter == 1;
	}
	
	static boolean solved() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (puzzleState[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void removeRow(int x, int y, int a) {
		for (int j=0; j<9; j++) {
			if (puzzleState[y][j] != 0) {
				optionsUnknowns.get(a).remove(Integer.valueOf(puzzleState[y][j]));
			}
		}
	}

	static void removeColumn(int x, int y, int a) {
		for (int i=0; i<9; i++) {
			if (puzzleState[i][x] != 0) {
				optionsUnknowns.get(a).remove(Integer.valueOf(puzzleState[i][x]));
			}
		}
	}

	static void removeBox(int x, int y, int a) {
		for (int i=y/3*3; i<y/3*3+3; i++) {
			for (int j=x/3*3; j<x/3*3+3; j++) {
				if (puzzleState[i][j] != 0) {
					optionsUnknowns.get(a).remove(Integer.valueOf(puzzleState[i][j]));
				}
			}
		}
	}
}