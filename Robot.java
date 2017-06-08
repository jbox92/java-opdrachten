import java.util.*;

public class Robot {
	public enum CompassDirection {
		NORTH, EAST, SOUTH, WEST
	}
	
	public int x;
	public int y;
	public CompassDirection facing;
	public ArrayList<String> program = new ArrayList<>();
	
	public Robot() {
		x = 0;
		y = 0;
		facing = CompassDirection.NORTH;
	}
	
	public Robot(int x, int y, CompassDirection facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}
	
	public void printState() {
		System.out.println("At ("+x+","+y+") facing "+facing+".");
	}
	
	public void turnLeft() {
		switch (facing) {
			case NORTH:
				facing = CompassDirection.WEST;
				break;
			case WEST:
				facing = CompassDirection.SOUTH;
				break;
			case SOUTH:
				facing = CompassDirection.EAST;
				break;
			case EAST:
				facing = CompassDirection.NORTH;
				break;
		}
	}
	
	public void turnRight() {
		switch (facing) {
			case NORTH:
				facing = CompassDirection.EAST;
				break;
			case EAST:
				facing = CompassDirection.SOUTH;
				break;
			case SOUTH:
				facing = CompassDirection.WEST;
				break;
			case WEST:
				facing = CompassDirection.NORTH;
				break;
		}
	}
	
	public void forward() {
		this.forward(1);		
	}
	
	public void forward(int speed) {
		if (!(speed >= -3 && speed <= 3)) {
			speed = 1;
		}
		switch (facing) {
			case NORTH:
				y += speed;
				break;
			case EAST:
				x += speed;
				break;
			case SOUTH:
				y -= speed;
				break;
			case WEST:
				x -= speed;
				break;
		}
	}
	
	public void backward() {
		this.forward(-1);
	}
	
	public void backward(int speed) {
		this.forward(-speed);
	}
	
 	public void execute() {
		String temp;
		for (int i=0; i<program.size(); i++) {
			if (program.get(i).equals("turnLeft")) {
				turnLeft();
			}
			if (program.get(i).equals("turnRight")) {
				turnRight();
			}
			if (program.get(i).equals("forward")) {
				forward();
			}
			if (program.get(i).equals("backward")) {
				backward();
			}
			if (Character.isDigit(program.get(i).charAt(program.get(i).length()-1))) {
				temp = program.get(i).substring(0,program.get(i).length()-1);
				if (temp.equals("forward")) {
					forward(Character.getNumericValue(program.get(i).charAt(program.get(i).length()-1)));
				}
				if (temp.equals("backward")) {
					backward(Character.getNumericValue(program.get(i).charAt(program.get(i).length()-1)));
				}
			}
		}
		printState();
	}
	
	public void addCommand(String command) {
		program.add(command);
	} 
}