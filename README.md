# RNGesus Randomizer Tool

This tool acts as a coin flipper and a dice roller with configurable number of sides.

Currently only usable via command line. No GUI is planned as of yet.

Invoke with: java -jar RNGesus.jar <coinflip|diceroll> <number of coins/dice> <sides on dice>
Arguments are optional. If none are given, an interactive menu is displayed instead.

coinflip	Outputs 0 (heads) or 1 (tails) x number of times, where x is the number of coins you wish to flip. Results are tallied
		at the end with a declared victor and final output counts. Silent mode (only show the final results) forthcoming.

diceroll	Rolls x number of dice, outputting a number between 0 and y-1, where x = number of dice to roll, and y = number
		of sides on each dice. Each result gets printed and the total value of the dice roll is tallied at the end.
		Currently debating whether there should be a silent mode for this or not, due to the nature of use cases.


Future plans include a silly basic text-based adventure game called RPGesus. I intend to remove all code relating to this from the
master branch and then create an 'experimental/unstable' branch wherein this code shall lie until the work is done. (I'm still fairly
new to git at this point.)


Concerning outside help: Pull requests are unlikely to be considered as this is my personal Java training program, but later down
the road once I'm completely satisfied with my work, I'll take pull requests as critique/advisement.
