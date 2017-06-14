import java.io.*;
import java.util.*;

public class DecisionTree {
	
	static ArrayList<String> input = new ArrayList<>();
	static ArrayList<Node> nodes = new ArrayList<>();
	static ArrayList<Edge> edges = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String... args) {		
		readInput();
		createNodesAndEdges();
		findStartingQuestion();
		execute();
	}
	
	static void readInput() {
		try {
			File file = new File("decisiontree.txt");
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
	}
	
	static void createNodesAndEdges() {
		for (String s:input) {
			String[] splitS = s.split(",");
			if (splitS.length == 2) {
				nodes.add(new Node(splitS[0], splitS[1].trim()));
			}
			if (splitS.length == 3) {
				edges.add(new Edge(splitS[0], splitS[1].trim(), splitS[2].trim()));
			}
		}
	}
	
	static void findStartingQuestion() {
		boolean possibleStart;
		for (Node n:nodes) {
			possibleStart = true;
			for (Edge e:edges) {
				if (n.node.equals(e.destination)) {
					possibleStart = false;
					break;
				}
			}
			if (possibleStart) {
				n.startingQuestion = true;
				break;
			}
		}
	}
	
	static void execute() {
		boolean answerFound = false;
		int currentNode = -1;
		String jaNee = "";
		for (int i=0; i<nodes.size(); i++) {
			if (nodes.get(i).startingQuestion) {
				currentNode = i;
			}
		}
		while (!answerFound) {
			System.out.println(nodes.get(currentNode).text);
			jaNee = scan.nextLine();
			for (Edge e:edges) {
				if (e.origin.equals(nodes.get(currentNode).node) && e.answer.equals(jaNee)) {
					for (int i=0; i<nodes.size(); i++) {
						if (nodes.get(i).node.equals(e.destination)) {
							currentNode = i;
							if (!nodes.get(currentNode).text.endsWith("?")) {
								answerFound = true;
							}
							break;
						}
					}
					break;
				}
			}
		}
		System.out.println(nodes.get(currentNode).text);
	}
}

class Node {
	String node;
	String text;
	boolean startingQuestion = false;
	Node(String n, String t) {
		node = n;
		text = t;
	}
}

class Edge {
	String origin;
	String destination;
	String answer;
	Edge(String o, String d, String a) {
		origin = o;
		destination = d;
		answer = a;
	}
}