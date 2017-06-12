import java.time.*;
import java.util.*;

public class LcdClock {
	
	static String[] clock;
	static int size = 2;
	static boolean ampm;
	static int hour;
	static int minute;
	
	public static void main(String... args) {
		for (int i=0; i<args.length; i++) {
			if (args[i].equals("-s")) {
				if (Integer.parseInt(args[i+1]) >= 1 && Integer.parseInt(args[i+1]) <= 5) {
					size = Integer.parseInt(args[i+1]);
				}
			}
			if (args[i].equals("-12")) {
				ampm = true;
			}
		}
		clock = new String[3+2*size];
		for (int i=0; i<(3+2*size); i++) {
			clock[i] = "";
		}
		getTime();
		if (ampm) {
			addAmPm();
		}
		addTime();		
		printTime();	
	}
	
	public static void getTime() {
		LocalTime time = LocalTime.now();
		hour = time.getHour();
		minute = time.getMinute();
	}
	
	public static void addTime() {
		addNumber(hour/10);
		addNumber(hour%10);
		addDots();
		addNumber(minute/10);
		addNumber(minute%10);
	}
	
	public static void addAmPm() {
		if (hour > 12) {
			addP();
			hour = hour%12;
		} else {
			addA();
		}
	}
	
	public static void addNumber(int number) {
		switch (number) {
			case 0:
				add0();
				break;
			case 1:
				add1();
				break;
			case 2:
				add2();
				break;
			case 3:
				add3();
				break;
			case 4:
				add4();
				break;
			case 5:
				add5();
				break;
			case 6:
				add6();
				break;
			case 7:
				add7();
				break;
			case 8:
				add8();
				break;
			case 9:
				add9();
				break;
		}
	}
	
	public static void drawHorizontalLine(int l, char c) {
		clock[l] += " ";
		for (int i=0; i<size; i++) {
			clock[l] += c;
		}
		clock[l] += " ";
	}
	
	public static void drawVerticalLine(int l, char c) {
		for (int i=0; i<size; i++) {
			clock[l+i] += c;
		}
	}
	
	public static void addWhiteSpace(int l) {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				clock[i+l] += " ";
			}
		}
	}
	
	public static void addP() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,' ');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,' ');
	}
	
	public static void addA() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,' ');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void addDots() {
		for (int i=0; i<(3+2*size); i++) {
			if (i == 0 || i == (1+size) || i== (2+2*size)) {
				drawHorizontalLine(i,' ');
			} else {
				drawHorizontalLine(i,'-');
			}
		}
	}
	
	public static void add0() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,' ');
		drawHorizontalLine(2+2*size,'-');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add1() {
		drawHorizontalLine(0,' ');
		drawHorizontalLine(1+size,' ');
		drawHorizontalLine(2+2*size,' ');
		drawVerticalLine(1,' ');
		drawVerticalLine(2+size,' ');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add2() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,'-');
		drawVerticalLine(1,' ');
		drawVerticalLine(2+size,'|');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,' ');
	}
	
	public static void add3() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,'-');
		drawVerticalLine(1,' ');
		drawVerticalLine(2+size,' ');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add4() {
		drawHorizontalLine(0,' ');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,' ');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,' ');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add5() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,'-');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,' ');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,' ');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add6() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,'-');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,' ');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add7() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,' ');
		drawHorizontalLine(2+2*size,' ');
		drawVerticalLine(1,' ');
		drawVerticalLine(2+size,' ');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add8() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,'-');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void add9() {
		drawHorizontalLine(0,'-');
		drawHorizontalLine(1+size,'-');
		drawHorizontalLine(2+2*size,'-');
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,' ');
		addWhiteSpace(1);
		addWhiteSpace(2+size);
		drawVerticalLine(1,'|');
		drawVerticalLine(2+size,'|');
	}
	
	public static void printTime() {
		for (int i=0; i<(3+2*size); i++) {
			System.out.println(clock[i]);
		}
	}
}