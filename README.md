# RNGesus Randomizer Tool

This tool acts as a coin flipper and a dice roller with configurable number of sides.

Currently only usable via command line. No GUI is planned as of yet.

Invoke with: java -jar RNGesus.jar <coinflip|diceroll> <number of coins/dice> <sides on dice>
Arguments are optional. If none are given, an interactive menu is displayed instead.

coinflip, flipcoin	Outputs 0 (heads) or 1 (tails) x number of times, where x is the number of coins you wish to flip. Results are tallied
		at the end with a declared victor and final output counts. Silent mode (only show the final results) forthcoming.

diceroll, rolldice, dice (any of these work)	Rolls x number of dice, outputting a number between 0 and y-1, where x = number of dice to roll, and y = number
		of sides on each dice. Each result gets printed and the total value of the dice roll is tallied at the end.
		Currently debating whether there should be a silent mode for this or not, due to the nature of use cases.


RPGesus base functionality exists in this branch; however, it is a work in progress and is considered feature-incomplete. It is accessible through the main menu.
