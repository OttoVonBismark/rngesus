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
			
			/* Taking arguments and conditionally throwing them at the later methods, skipping the main menu. This be "arglogic" */
			switch(argstr){
			case "Initialize Me":
				menu();
			case "coinflip":
				if(argnum1 != -1337 && argnum1 > 0) {
					System.out.println("Gonna run coinFlip using " + argnum1 + " coins.");
					coinFlip(argnum1);
				} else {
					System.out.println("I want to flip coins, but you gave me a bad number of coins. Try a positive integer.");
					System.exit(1);
				}
				break;
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
				System.out.println("  3. Play a sick-nasty RPG\n");
				System.out.print("Pick a number, or enter q to exit:");
				
				choice = (char) System.in.read();
				
				do {
					ignore = (char) System.in.read();
				} while (ignore != '\n');
				
			} while (choice < '1' | choice > '3' & choice != 'q');
			
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
				
			case '3':
				rpgesus();
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
	
	private static void rollDice(int[] diceVals) throws IOException { // Roll dem bitches.
		Random diceGen = new Random();
		int diceOut;
		int diceSum = 0;
		
		/* Stuff goes here */
		
		
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
	
	private static void rpgesus() throws IOException{ // AAAADVENTURE TIME!
		System.out.println("Welcome to RPGesus! Insert Randomized Tagline Here!\n");
		System.out.println("Let's build your party!");
		
		// Please assume the party submission position.
		PlayerChar p1 = new PlayerChar();
		PlayerChar p2 = new PlayerChar();
		PlayerChar p3 = new PlayerChar();
		PlayerChar p4 = new PlayerChar();
		
		p1.charGen();
		p2.charGen();
		p3.charGen();
		p4.charGen();
		
		// ADVENTURE START!
		/* This bit is going to have a ton of randomized text. This could get a bit confusing, so just know that the flow is always <generate enemies> -><scenario> -> <battle>
		 * More explicitly, it goes:
		 * GENERATE ENEMIES
		 * SCENARIO_PART_1... randomize(); [
		 * 	random part goes here
		 * ] SCENARIO_PART_B... randomize(); [
		 *  random part goes here
		 * ]
		 * BATTLE
		 * RESULTS
		 * 
		 * Also,
		 * TODO: Revise this script. Doesn't have to be an oscar winner, but something not generic af would be nice.
		 */
		// Generate Scenario 1 Enemies
		Enemy enemy1A = new Enemy();
		Enemy enemy1B = new Enemy();
		Enemy enemy1C = new Enemy();
		
		enemy1A.enemyGen("easy");
		enemy1B.enemyGen("easy");
		enemy1C.enemyGen("easy");
		
		System.out.println("It was a beautiful day when " + p1.name + " decided he and his pals should fuck some shit up.");
		System.out.println("As it just so happened, " + enemy1A.name + " and THEIR friends were happy to oblige.");
		System.out.println("BATTLE START!\n");
		
		System.out.println(enemy1A.name + "(" + enemy1A.statCurrHealth + "/" + enemy1A.statMaxHealth + "), "
				+ enemy1B.name + "(" + enemy1B.statCurrHealth + "/" + enemy1B.statMaxHealth + "), and "
				+ enemy1C.name + "(" + enemy1C.statCurrHealth + "/" + enemy1C.statMaxHealth + ") attack!");
	}
}

class PlayerChar {
	String name = "Player " + playerCount;
	int statCurrHealth = 20;
	int statMaxHealth = 20;
	int statOffense = 1;
	int statDefense = 1;
	int statAgility = 1;
	int statEvade = 1;
	
	static int playerCount = 1;
	
	public void charGen() throws IOException{
		Random statGen = new Random();
		
		try{
			System.out.println("Please name Player " + playerCount + " using only letters.");
			this.name = InputValidator.StringLetterScan();
			System.out.println("Ok. Player " + playerCount + " shall be called \"" + this.name + "\".");
			System.out.println("Their stats are as follows...");
			System.out.println("HP: " + this.statCurrHealth + "/" + this.statMaxHealth);
			// Let's generate some stats!
			this.statOffense = statGen.nextInt(9) + 2; // Physical attacking power. Range is from 2 to 10.
			this.statDefense = statGen.nextInt(9) + 2; // Defensive power. Incoming damage is reduced by at most this number.
			this.statAgility = statGen.nextInt(10) + 1; // Accuracy. Compared against Evade to determine of hits land or not.
			this.statEvade = statGen.nextInt(10) + 1; // Evasive capability. Compared against Agility to dodge.
			System.out.println("Offense: " + this.statOffense);
			System.out.println("Defense: " + this.statDefense);
			System.out.println("Agility: " + this.statAgility);
			System.out.println("Evasion: " + this.statEvade);
			System.out.println("Go forth and purge the land of beasts!\n");
			
			// Now we iterate the player counter to be fancy.
			playerCount++;
			if (playerCount == 5){
				System.out.println("Your party is assembled! Wage war against the forces of darkness!\n");
			}
			
		} catch (IOException nostrings){
			System.err.println("Nooooo strings! *bweep boop* " + nostrings.getMessage());
			System.exit(1);
		}
		
	}
}

class Enemy {
	String name;
	int nameGen;
	int statCurrHealth;
	int statMaxHealth;
	int statOffense;
	int statDefense;
	int statAgility;
	int statEvade;
	
	public void enemyGen(String difficulty){
		Random statGen = new Random();
		Random enemyRandomize = new Random();
		
		// Generate an enemy based on difficulty level. Accepted difficulties are Easy, Medium, Hard, Boss and Final Boss
		switch(difficulty) {
		case "easy": // EASY BLOCK STARTS HERE!
			nameGen = enemyRandomize.nextInt(6) + 1; // 1-5
			switch(nameGen) {
			case 1:
				name = "Drunk Goblin";
				statCurrHealth = 5;
				statMaxHealth = 5;
				statOffense = statGen.nextInt(3) + 1; // 1-3
				statDefense = statGen.nextInt(2);
				statAgility = statGen.nextInt(4) + 2; // 2-5
				statEvade = statGen.nextInt(2);
				break;
			case 2:
				name = "A Threatening-looking Rock";
				statCurrHealth = 2;
				statMaxHealth = 2;
				statOffense = statGen.nextInt(2);
				statDefense = statGen.nextInt(8) + 1; // 1-7
				statAgility = statGen.nextInt(2);
				statEvade = 0; // It's a rock. It's not exactly nimble.
				break;
			case 3:
				name = "Emaciated Dog";
				statCurrHealth = 3;
				statMaxHealth = 3;
				statOffense = statGen.nextInt(5) + 1; // 1-4
				statDefense = statGen.nextInt(2);
				statAgility = statGen.nextInt(7) + 2; // 2-8
				statEvade = statGen.nextInt(5) + 2; // 2-6
				break;
			case 4:
				name = "Homeless Boy";
				statCurrHealth = 4;
				statMaxHealth = 4;
				statOffense = statGen.nextInt(4) + 1; // 1-3
				statDefense = statGen.nextInt(5) + 1; // 1-4
				statAgility = statGen.nextInt(8) + 1; // 1-7
				statEvade = statGen.nextInt(10) + 1; // 1-9
				break;
			case 5:
				name = "Disinterested Zombie";
				statCurrHealth = 8;
				statMaxHealth = 8;
				statOffense = statGen.nextInt(6) + 1; // 1-5
				statDefense = statGen.nextInt(2);
				statAgility = 1;
				statEvade = 1;
				break;
			}
			break;
		default:
			System.out.println("Code me, please!");
			break;
		}
	}
}