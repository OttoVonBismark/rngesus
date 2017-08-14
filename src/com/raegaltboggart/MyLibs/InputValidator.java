package com.raegaltboggart.MyLibs;
/** Custom input validation library 
 * @author - RaegalTBoggart
 * **/
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

/* Input validators */
public class InputValidator {
	
	/* Main method stub */
	
	public static boolean ScanBool() throws IOException{
		Scanner boolAnswer = new Scanner(new FilterInputStream(System.in){public void close(){}});
		boolean typeCheck;
		boolean isDone = false; // Do loop controller. Must be true in order to register as valid.
		do {
			while (!boolAnswer.hasNextBoolean()) {
				System.out.println("Invalid input detected. True or False required.");
				boolAnswer.next();
			}
			typeCheck = boolAnswer.nextBoolean();
			isDone = true;
		} while (isDone == false);
		boolAnswer.close();
		return typeCheck;
	}
	
	public static int PosIntScan() throws IOException{
		Scanner posInt = new Scanner(new FilterInputStream(System.in){public void close(){}});
		int typeCheck;
		do {
			while (!posInt.hasNextInt()) {
				System.out.println("Invalid input detected. Try again.");
				posInt.next();
			}
			typeCheck = posInt.nextInt();
		} while (typeCheck <= 0);
		posInt.close();
		return typeCheck;
	}
	
	public static int IntScan() throws IOException{
		Scanner scint = new Scanner(new FilterInputStream(System.in){public void close(){}});
		int typeCheck;
		boolean isDone = false;
		do {
			while (!scint.hasNextInt()) {
				System.out.println("Invalid input detected. Try again.");
				scint.next();
			}
			typeCheck = scint.nextInt();
			isDone = true;
		} while (isDone == false);
		scint.close();
		return typeCheck;
	}
	
	public static String StringLetterScan() throws IOException{ // This validates strings containing ONLY LETTERS! If there are any numbers present, this will eval false.
		Scanner scstr = new Scanner(new FilterInputStream(System.in){public void close(){}});
		String regex = "^[a-zA-Z]+$";
		String typeCheck = "STFU";
		boolean isDone = false;
		do {
			while (!scstr.hasNext(regex)) {
				System.out.println("Invalid input detected. Try again.");
				scstr.next();
			}
			typeCheck = scstr.next(regex);
			isDone = true;
		} while (isDone == false);
		scstr.close();
		return typeCheck;
	}
}