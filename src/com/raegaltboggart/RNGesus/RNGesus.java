package com.raegaltboggart.RNGesus;

import java.io.IOException;
import java.util.Random;
import com.raegaltboggart.MyLibs.InputValidator;

/** Random number generator for dice rolling and coin flipping. */
class RNGesus {
	public static void main(String args[])
			throws java.io.IOException {
		String argstr = "Initialize Me";
		int argnum1 = -1337;
		int argnum2 = -1337;
		
		/* Argument initialization This is "arginit" */
		try {
			try {
				if(args[0] != null){
					argstr = args[0];
					if(args[1] != null){
						if(Integer.parseInt(args[1]) > 0){ // Here, we make sure that if numbers are given as an argument, that they are validated as positive integers.
							argnum1 = Integer.parseInt(args[1]);
						} else {
							System.out.println("You supplied an invalid second argument: " + args[1]);
						}
					}
					if(args[2] != null){
						if(Integer.parseInt(args[2]) > 0){
							argnum2 = Integer.parseInt(args[2]);
						} else {
							System.out.println("You supplied an invalid third argument: " + args[2]);
						}
					}
				}
			} catch (ArrayIndexOutOfBoundsException arginit){
				/* DEBUG System.err.println("Caught Exception: ArrayIndexOutOfBoundsException in section \"arginit\": " + arginit.getMessage()); */
			} catch (NumberFormatException arginit){
				System.err.println("Caught Exception: NumberFormatException in section \"arginit\": " + arginit.getMessage());
				System.out.println("The arguments I'm expecting are: coinflip|dice num1 num2. That's a string and two ints.");
				System.out.println("Fail that hard enough and you get this message.");
				System.exit(1);
			}
			
			/* Taking arguments and conditionally throwing them at the later methods, skipping the main menu. This be "arglogic"
			 * Here's a cool trick: Notice how some of the cases below are empty? This way you can have multiple case values running the same execution.
			 * For example, if 'flipcoin' is the argument, no code will be executed, and since there's no 'break' within that case's routine, you get
			 * a 'fall through' to 'coinflip' which contains the desired routine, as well as the break statement needed to escape the switch block.
			 *  */
			switch(argstr){
			case "Initialize Me":
				menu();
			case "flipcoin":
			case "coinflip":
				if(argnum1 != -1337 && argnum1 > 0) {
					System.out.println("Gonna run coinFlip using " + argnum1 + " coins.");
					coinFlip(argnum1);
				} else {
					System.out.println("I want to flip coins, but you gave me a bad number of coins. Try a positive integer.");
					System.exit(1);
				}
				break;
			case "diceroll":
			case "rolldice":
			case "dice":
				if(argnum1 != -1337 && argnum2 != -1337) {
					int[] argnumpack = {argnum1, argnum2};
					System.out.println("Gonna run rollDice using " + argnum1 + " dice with " + argnum2 + " sides.");
					rollDice(argnumpack);
				} else {
					System.out.println("I want to roll some dice, but you gave me a bad argument. How does one roll " + argnum1 + " dice with " + argnum2 + " sides?");
					System.exit(1);
				}
				break;
			default:
				System.out.println("Unrecognized first argument: " + argstr);
				System.exit(1);
			}
			
			} catch (ArrayIndexOutOfBoundsException arglogic){
			/* Technically you should never see these lines. */
				System.err.println("Caught Exception: ArrayIndexOutOfBoundsException in section \"arglogic\": " + arglogic.getMessage());
				System.out.println("If you're reading this, that means you gave no arguments, which is totally fine. Proceeding to menu.");
				menu();
		}
	}
	
	private static void menu()
		throws java.io.IOException {
		char choice, ignore;
		for(;;) {
			do {
				System.out.println("RNGesus Random Number Generator Main Menu:");
				System.out.println("  1. Flip a Coin");
				System.out.println("  2. Roll some Dice");
				System.out.print("Pick a number, or enter q to exit:");
				
				choice = (char) System.in.read();
				
				do {
					ignore = (char) System.in.read();
				} while (ignore != '\n');
				
			} while (choice < '1' | choice > '2' & choice != 'q');
			
			if(choice == 'q') {
				System.out.println("Goodbye.");
				System.exit(0);
			}
			
			System.out.println("\n");
			
			switch(choice) {
			case '1':
				questionnaire("coinFlip");
				break;
				
			case '2':
				questionnaire("rollDice");
				break;
			}
			
		}
	}
	
	private static void questionnaire(String selection)
		throws java.io.IOException {
		
		switch(selection) {
		case "coinFlip":
			int coinCount;
			System.out.println("How many coins will you flip?");
			coinCount = InputValidator.PosIntScan();
			coinFlip(coinCount);
			break;
			
		case "rollDice":
			int diceNum, diceSides;
			System.out.println("How many dice are you going to roll?");
			diceNum = InputValidator.PosIntScan();
			System.out.println("How many sides are on each die?");
			diceSides = InputValidator.PosIntScan();
			
			int[] dicePack = {diceNum, diceSides};
			
			rollDice(dicePack);
			break;
		}
	}
	
	private static void coinFlip(int flipCount) throws java.io.IOException { // coinCount is the initializer for flipCount. flipCount does the actual work here.
		Random coinGen = new Random();
		int sumHeads = 0;
		int sumTails = 0;
		for (int i = 1; i <= flipCount; i++) {
			int coinOut = coinGen.nextInt(2);
			
			String face;
			if (coinOut == 0){
				face = "Heads!";
				sumHeads++;
			} else {
				face = "Tails!";
				sumTails++;
			}
			System.out.println("Coin " + i + " came up " + face);
		}
		System.out.println("The totals are...");
		System.out.println("Heads: " + sumHeads + " Tails: " + sumTails);
		if (sumHeads > sumTails){
			System.out.println("Heads wins!\n");
		} else if (sumHeads < sumTails){
			System.out.println("Tails wins!\n");
		} else if (sumHeads == sumTails){
			System.out.println("It's a Tie!\n");
		}
		
		System.out.println("We're done here. Returning to menu.\n");
		menu();
	}
	
	private static void rollDice(int[] diceVals) throws IOException { // Dice Roll method. Two numbers go in (a number of dice to run and how many sides they have) a bunch of numbers come out.
		Random diceGen = new Random();
		int diceOut;
		int diceSum = 0;
		
		System.out.println("Rolling " + diceVals[0] + " dice with " + diceVals[1] + " sides...");
		
		for (int i = 1; i <= diceVals[0]; i++) {
			diceOut = diceGen.nextInt(diceVals[1]) + 1;
			diceSum += diceOut;
			System.out.println("Die #" + i + " rolled " + diceOut + "!");
		}
		System.out.println("Total: " + diceSum);
		System.out.println("We're done here. Returning to menu.\n");
		menu();
	}
}