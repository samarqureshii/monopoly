import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.text.NumberFormat;

public class ICS3U_FP_Code_SamarQureshi {
	
	private static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberFormat decimal=NumberFormat.getNumberInstance();
		//Scanner input = new Scanner(System.in); 
		
		Boolean[] obtainedProperties = new Boolean[7]; //initializes array that stores values of if 
		//the user has obtained a property or not, index correlates with index of [] properties
		
		String [] boardSpaces = {"Bonus bits 1", "Chance 1", "Cairo, Egypt", 
				"California, USA", "Tax 1", "Rome, Italy", "Sydney, Australia",
				"Chance 2", "Bonus bits 2", "Victoria Falls, Zimbabwe", "Chance 3", 
				"Krong Siem Reap, Cambodia", "Go to Jail", "Chance 4", "Tax 2", "Cuzco, Peru"};// 
		
		int userLocation = 0; //index of the [] boardSpaces where the user is at
		
		String [] properties = {"The Giza Pyramids", "Yosemite National Park", 
				"The Colosseum", "The Sydney Opera House", "Victoria Falls", 
				"Angkor Wat", "Machu Picchu"}; //these will be accessed as a [] parameter in the landedOnProperty() method
		
		//Element 1: Giza Pyramids; Cairo, Eygpt (0.4 million bits)
		//Element 2: Yosemite National Park; California, USA (1.1 million bits)
		//Element 3: The Colosseum; Rome, Italy (1.7 million bits) 
		//Element 4: Sydney Opera House; Sydney, Austrailia (2 million bits)
		//Element 5: Victoria Falls; Zimbabwe & Zambia (2.3 million bits)
		//Element 6: Angkor Wat; Krong Siem Reap, Cambodia (3.8 million bits)
		//Element 7: Machu Picchu; Cuzco, Peru (5.4 million bits)
		
		System.out.println("Welcome to Worldwide Monopoly. "
				+ "Please remember to hit your return or enter key after every line to continue.");
		inputLine();
		
		System.out.println("Ah, hello there. We've been waiting for you.");
		inputLine();
		
		System.out.println("We've called you here today to provide you with instructions for the adventure of a lifetime.");
		inputLine();
		
		System.out.println("What exactly is this adventure, you ask?");
		inputLine();
		
		System.out.println("You’ve been randomly selected to travel the world, and obtain as many of the most sought after landmarks as"
				+ "\npossible (There are 7 in total).");
		inputLine();
		
		System.out.println("We’ve equipped you with everything you’ll need to start, such as 20 million bits, and 20 die rolls. "
				+ "\nBits can be used to purchase properties, and die rolls will be used at every turn. "
				+ "\nIf you happen to run out of die rolls, the game will end."
				+ "\n\nAlso just to note, we've given you a 4-sided die to roll, just to keep things interesting.");
		inputLine();
		
		System.out.println("However, you may encounter various obstacles that will throw you off course.");
		inputLine();
		
		System.out.println("At the end of the game, you will be awarded points from the number of properties you were able to collect. "
				+ "\nIf you have more points than our smartest AI, you win!");
		inputLine();
		
		System.out.println("One last thing, we didn’t quite catch your name. Please type your name below.");
		String name = input.nextLine();
		
		System.out.println("Ready to conquer the world, "+name+"? "
				+ "\n\nDoesn’t matter if you aren’t, we’re still sending you out there anyways.");
		inputLine();
		
		System.out.println("Here is the game board, for your reference.");
		gameBoard();
		
		System.out.println("\nEnough with all this talk. Let's get started by having you roll the die.");
		
		boolean keepPlaying = true; //condition for the user to keep playing
		
		while(keepPlaying) {
			//the following lines "prepare" the game if the user would like to play again
			int dieRollsLeft = 20;
			double bits = 20;
			fillArrayFalse(obtainedProperties); //fills Boolean array with all false values
			
			while(dieRollsLeft>0) { //condition for allowing the game to continue running
				
				//code below is "1 turn"
				
				displayBits(bits);
				System.out.println("Number of properties obtained: "+numberOfProperties(obtainedProperties));
				displayRollsLeft(dieRollsLeft); //displays die rolls left + bits left at start of every turn
				
				System.out.println("\nHit enter to roll the die.");
				input.nextLine();
				
				int dieRoll = rollDie();
				dieRollsLeft--;
				
				userLocation +=dieRoll; //adds die roll to the user's current location
				
				if(userLocation>15) { //to get rid of the index out of bounds error on []boardSpaces
					userLocation -=15;
				}
		
				displayLocation(userLocation, boardSpaces); //shows user where they landed
				
				//array moves user to space, action is executed, decision may be made
				
				if(userLocation==2 || userLocation==3 || userLocation== 5 || userLocation== 6 
						|| userLocation == 9 || userLocation== 11 || userLocation== 15) { //if user lands on a property space
				
					bits = propertyAction(userLocation, bits, properties, obtainedProperties);
				}
				
				else if(userLocation==1 || userLocation==7 || userLocation==10) {
					//chance 1,2 or 3 method runs, returned value of bits in chance is passed here
					bits = chance(bits, userLocation, obtainedProperties, properties, dieRoll, dieRollsLeft);
					
		
				}
				
				else if(userLocation==13) { //user lands on Chance 4
					//chance 4 sends user to jail
					dieRollsLeft = jail(dieRollsLeft);

				}
				
				else if(userLocation==0 || userLocation == 8){
					//bonusBits method runs, user collects a random amount of bits
					bits = bonusBits(bits);
				}
				
				else if(userLocation == 4 || userLocation ==14) {
					//tax method runs, random amount of bits are deducted from the user
					bits = tax(bits);
				}
				
				else {
					//user has landed on go to jail, they need to roll a 1 or 6 to be freed				
					dieRollsLeft = jail(dieRollsLeft);
					
				}

			}
			
			//game is over, user can choose to leave or stay
			endOfGame(obtainedProperties);
			keepPlaying = keepPlaying();
		}
		
		System.out.println(goodbye(name)); //random goodbye message for the user
	}
	
	public static int rollDie() { 
		//will generate a number from 1-6 and print out the die roll
		Random rand = new Random();
		
		int dieRoll = 1+rand.nextInt(4);
		System.out.println("You rolled a "+dieRoll+ "!");
		return(dieRoll);
	}
	
	public static void displayBits(double bits) { 
		//displays amount of bits user currently has
		NumberFormat decimal=NumberFormat.getNumberInstance();
		System.out.println("\nTotal bits: "+decimal.format(bits)+ " million");
	}
	
	public static void displayRollsLeft(int dieRollsLeft) { 
		//displays the number of die rolls left
		System.out.println("Remaining die rolls: " + dieRollsLeft);
	}
	
	public static void displayLocation(int userLocation, String [] boardSpaces) { 
		//displays to the user which space they've landed on
		System.out.println("You have landed on " + boardSpaces[userLocation] + ".\n");
		inputLine();
	}
	
	public static void fillArrayFalse(Boolean[]array) { 
		//fills a boolean array with all false values
		for(int i = 0; i<array.length; i++) {
			array[i] = false;
		}
	}
	
	public static double propertyAction(int userLocation, double bits, String [] properties, Boolean [] obtainedProperties) { 
		//executes if the user lands on a property space, which are indexes 2, 3, 5, 6, 9, 11, or 15
		double price;
		
		//brings together other property methods to allow user to make a purchase decision, and subtracts price from their total bits
		
		if(userLocation == 2) { // case where user landed on Cairo, Eygpt
			price = 0.4;
			displayProperty(properties, 0); //0th index in []properties
			displayPrice(price);
			if(!obtainedProperties[0]) { 
				//user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 0, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[0]+".");
			}
			
		}
		
		else if(userLocation == 3) { 
			//case where user landed on California, USA
			price = 1.1;
			displayProperty(properties, 1); 
			//1st index in []properties
			displayPrice(price);
			if(!obtainedProperties[1]) { 
				//user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 1, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[1]+".");
			}
		}
		
		else if(userLocation == 5) { //case where user landed on Rome, Italy
			price = 1.7;
			
			displayProperty(properties, 2);
			//2nd index in []properties
			displayPrice(price);	
			if(!obtainedProperties[2]) { 
				//user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 2, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[2]+".");
			}
		}
		
		else if(userLocation == 6) { 
			//case where user landed on Syndey, Austrailia
			price = 2;
			displayProperty(properties, 3); 
			//3rd index in []properties
			displayPrice(price);
			if(!obtainedProperties[3]) { 
				//user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 3, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[3]+".");
			}
		}
		
		else if(userLocation == 9) { 
			//case where user landed on Victoria Falls, Zimbabwe
			price = 2.3;
			displayProperty(properties, 4); 
			//4th index in []properties
			displayPrice(price);
			if(!obtainedProperties[4]) { 
				//user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 4, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[4]+".");
			}
		}
		
		else if(userLocation == 11) { 
			//case where user landed on Krong Siem Reap, Cambodia
			price = 3.8;
			displayProperty(properties, 5); 
			//5th index in []properties
			displayPrice(price);
			
			if(!obtainedProperties[5]) { 
				//user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 5, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[5]+".");
			}
		}
		
		else { //case where user landed on Cuzco, Peru
			price = 5.4;
			displayProperty(properties, 6); //6th index in []properties
			displayPrice(price);
			if(!obtainedProperties[6]) { 
				//user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 6, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[6]+".");
			}
		}
		return(bits); //returns new number of bits after purchase
		
	}
	
	public static void displayProperty(String [] properties, int propertyIndex) {
		//displays property available for user to potentially buy
		
		System.out.println("Available property for sale: " + properties[propertyIndex]);
		
	}
	
	public static void displayPrice(double price) { 
		//displays the price of the specific property
		System.out.println("Price: " + price + " million bits");
		inputLine();
	}
	
	public static double propertyDecision(double bits, double price, Boolean[]obtainedProperties, int index, String [] properties) { //allows user to choose whether or not they would like to buy the property
		//will allow user to purchase a property or not, and change the boolean value in []obtainedProperties to false for the given property in []properties

		System.out.println("\n\nPlease select an option from the list below:"
				+ "\nEnter 1 if you would like to buy this property."
				+ "\nEnter 2 if you do not want to buy this property.");
		String decisionToBuy = input.nextLine();
		
		decisionToBuy = invalidInput(decisionToBuy);
		
		if(decisionToBuy.equals("1") && bits>=price) {
			obtainedProperties[index] = true; 
			//marks specific property as "obtained" in boolean array
			
			//draws asterick box around congratulatory message when user buys property
			System.out.println("\n");
			asterickLine(properties, index,38);
			System.out.println("\n*Congratulations, you have obtained " + properties[index] + "!*");
			asterickLine(properties, index, 38);
			System.out.println("\n");
			
			bits -=price;
		}
		
		else if(decisionToBuy.equals("1") && bits<price) {
			System.out.println("You do not have enough bits to purchase this property.");
		}
		
		return(bits);
	}
		
	public static double chance(double bits, int userLocation, Boolean[]obtainedProperties, String[]properties, int dieRoll, int dieRollsLeft) { //will run if user lands on a chance space
		Random rand = new Random();
		int index = rand.nextInt(7); 
		//generates random explanations as to why property was given/taken awy from user
		
		//explanations if the user lands on chance 1
		String [] explanations1 = {"\nWhile you were trekking across the Western Desert in Egypt, "
				+ "your camel decided to attack you and left you \nstranded in a sandstorm! "
				+ "In order to compensate you for this tragedy, the President of Egypt has decided \n"
				+ "to give you ownership of the Giza Pyramids.", 
				
				"\nYou and your dog were climbing "
						+ "the Sierra Nevada Mountains in California, but your dog jumped off the cliff! \n"
						+ "As a memento for your dog, you have been transferred ownership of Yosemite National Park.", 
				
				"\nAt an Italian auction, the bids to purchase the Colosseum were getting out of hand, "
						+ "so the auction organizers \ndecided to settle things with an invigorating duel "
						+ "of rock-paper-scissors. In the last round, you defeated your \nopponent with paper, "
						+ "and the Colosseum is now yours!", 
				
				"\nYou decided to raid the Sydney Opera House, "
								+ "and held everyone for ransom! \nScared for their safety, "
								+ "the opera house trust has now given you full and private ownership "
								+ "of the \nSydney Opera House.", 
				
				"\nWhile standing at the top of Victoria Falls, "
										+ "you dropped your computer, which had all your bits stored inside of "
										+ "\nit as cryptocurrency!"
										+ " The Zimbabwean government wanted to compensate you for loss, "
										+ "and you now have total \nownership of Victoria Falls, and they gave you back all the bits you lost.", 
										
				"\nFor many centuries, "
												+ "the Buddhist temple Angkor Wat has struggled with unstable infrastructure "
												+ "due to heavy rains. \nYou have engineered a new design that will support "
												+ "the building during monsoon season, and as a thank you \ngesture, "
												+ "the building is now in your name!", 
						
				"\nThere are many mysteries of Machu Picchu, "
														+ "an Inca citadel located in the Eastern Cordillera of Peru. "
														+ "\nOne of your recent archaeological findings explains why this landmark "
														+ "was built specifically on this hill. \nIn compensation for your shocking discovery, "
														+ "the Peruvian government has given you full ownership of \nMachu Picchu."};
			
		//explanations if the user lands on chance 2
		String [] explanations2 = {"You spilled 10 thousand gallons of oil into the Nile River! "
				+ "\nThe Egyptian president is not very happy about this, so he has taken away the Giza Pyramids from you.",
				
				"You graffitied the Sierra Nevada Mountains! "
				+ "\nThe park caretakers are not pleased with you, and have revoked your ownership of Yosemite National Park.",
				
				"You stole a gondola to travel across the Grand Canal in Venice! "
				+ "\nAs a result of your criminal behaviour, the Italian police have seized your rights to owning the Colosseum.",
				
				"You sold tickets to a performance at the Sydney Opera House illegally, and as a result of this, "
				+ "\nthe opera house trust has revoked your ownership of the Sydney Opera House.",
				
				"You were caught cliff diving off Victoria Falls! \nThe Zimbabwean government does not think you are setting a "
				+ "good example for sightseers, and have taken away your \nrights to owning Victoria Falls.", 
				
				"You robbed a Cambodian bank, and the monks at Angkor Wat are not pleased with you. "
				+ "\nYou have lost ownership of Angkor Wat.",
				
				"While residing in  Peru, you were found guilty of tax evasion. "
				+ "\nInstead of going to jail, you have agreed to give up ownership of Machu Picchu."};
		
		if(userLocation == 1) {//user lands on Chance 1
		//chance 1 is responsible for giving the user a property if they do not own it	
			
		//selects a random index in []obtainedProperties, and will give it to the user IF they do not already have it
		//if user already has the property, nothing happens
		
		
		
			if(!obtainedProperties[index]) { 
				//if user does not have that specific property
				System.out.println(explanations1[index]);
				obtainedProperties[index] = true;
				
			}
			else { //if user happens to have that specific property
				System.out.println("\nAwe, we were going to give you ownership of "+properties[index]+", but looks like you already have it.");
			
			}
			
		}
		
		else if(userLocation == 7) { //user lands on Chance 2
		//chance 2 is responsible for taking away properties from the user
		
			//selects a random index in []obtainedProperties, and will take it away from the user IF they have it
			//if user does not own this property, nothing will happen
			if(obtainedProperties[index]) { // if the user has the property, it is taken away from them
				System.out.println(explanations2[index]); 
				obtainedProperties[index]=false;
			}
			
			else {
				System.out.println("We were going to take away " + properties[index]+
						" from you, but looks like you don't already own it. \nYou got lucky!");
			}
			
		}
		
		
		else if(userLocation == 10) {//user lands on Chance 3
		//chance 3 is responsible giving and taking away bits from the user
		int randomBits = -20+rand.nextInt(38);
		
			if(randomBits<0) { //if user loses bits
				System.out.println("Oh no! You have lost "+Math.abs(randomBits)+" million bits.");
				
			}
			
			else if (randomBits>0){
				System.out.println("Congratulations! You have won " + randomBits + " million bits.");
			}
			
			else {
				System.out.println("You did not lose or gain any bits.");
			}
			
		bits +=randomBits; //adds or subtracts randomBits to total amount of bits
			
		}
		
		return(bits);
	}
	
	public static int jail(int dieRollsLeft) { 
//will throw the user in jail
		Random rand = new Random();
		//Scanner input = new Scanner(System.in);
		
		int index = rand.nextInt(10); //generates a random index which will display a random reason as to why the user went to jail
		String[]reasons = {"You used a fake visa in an attempt to gain entry into the United Kingdom, "
				+ "and lied to a customs officer, \nwhich resulted in your denial of entry, and your "
				+ "arrest by British authorities.",
				
				"You assaulted a police officer in Argentina, and failed to show up for your court hearing. "
				+ "\nAs a result, you have been given a jail sentence.",
				
				"You have been found guilty of airline fraud! You are being sent to jail.",
				
				"You stole an elephant in Sri Lanka, and demolished a village! "
				+ "\nYou have been thrown in jail by local authorities.",
				
				"The Russian military has identified you as an international criminal and you are "
				+ "now being sent to a prison \nin St Petersburg!",
				
				"You were caught poaching an endangered species of lemur in Madagascar, and have been thrown in jail!",
				
				"A customs officer found traces of narcotics in your carry on suitcase, and you are now under arrest.",
				
				"You were caught flying a stolen helicopter in Uzbekistan, and are being sent to jail!",
				
				"Canadian customs found an unlawful firearm in your possession, and you are now under arrest!",
				
				"You have pleaded guilty to embezzlement while staying in Taiwan, and as a result, have received \na prison sentence."};
		
		System.out.println(reasons[index]);
		inputLine();
		
		boolean jailState = true;
		int dieRoll; 
		int jailCounter = 0; //counts how many turns  the user spent in jail
		
		System.out.println("\nYou must roll a 1 or 4 to get out of jail. "
				+ "\nHowever, if you're still stuck in here after 3 die rolls, we'll let you out.");
		
		while(jailState) {
			
			if(dieRollsLeft==0) { 
				//if the user happens to run out of die rolls while in jail, this will ensure that the game ends, so dieRollsLeft does not go into negative numbers
				return dieRollsLeft;
			}
			
			System.out.println("\nHit enter to roll the die.");
			input.nextLine();
			
			dieRoll = rollDie();
			dieRollsLeft--;
			jailCounter++;
			
			if(dieRoll==1 || dieRoll ==4 || jailCounter == 3) {
				System.out.println("You are now out of jail!");
				jailState = false;
			}
						
			
			displayRollsLeft(dieRollsLeft);
			
			
		}
		
		return dieRollsLeft;
		
		
		
		
	}
	
	public static double tax(double bits) {
		//if user lands on tax 1 or tax 2 they must pay a random tax	
		Random rand = new Random();
		int index = rand.nextInt(9);
		//generates a random reason for taxing/fining the user
		
		int fine = 1+rand.nextInt(11); 
		//will generate a fine between 1 and 10 million bits
		
		String [] reasons = {"On your most recent flight, your motion sickness got the best of you, "
				+ "so you threw up on a flight attendant! \nThe airline company was not very happy with you, "
				+ "and you ended up paying a hefty fine of "+fine+" million bits.",
				
				"You had over 70 pounds of undeclared illegal goods in your suitcase, "
				+ "and customs requires that you pay a fine \nof "+fine+" million bits.",
				
				"You broke your leg after falling off the Great Wall of China, and had to "
				+ "pay "+fine+" million bits in hospital fines!",
				
				"You accidentally ran over a kangaroo with your Jeep in New Zealand!"
				+ " You had to pay a fine of "+fine+" million bits for \nharming an animal native to New Zealand.",
				
				"While in South Korea, you were accidentally driving on the wrong side of the road! "
				+ "\nYou have been fined "+fine+" million bits in violation of a road law.",
				
				"You were caught littering in Nigeria! "
				+ "\nLocal authorities require you to pay a fine of "+fine+" million bits.",
				
				"You are required to pay "+fine+" million bits in import tax.",
				
				"At an airport in Singapore, you had to pay a toll of "+fine+" million bits.",
				
				"You had to pay "+fine+" million in duty fees."};
		
		System.out.println(reasons[index]);
		
		bits-=fine;//subtracts fine amount from user's current bits
		
		return(bits); //returns new balance
	}

	public static double bonusBits(double bits) {
		Random rand = new Random();
		int index = rand.nextInt(6);
		//generates random reason as to why user is being given bits
		
		int bonus = 1+rand.nextInt(16);
		
		String [] reasons = {"You have won the Nobel Peace Prize for your substantial "
				+ "contributions around the world, along with 10 million bits!", 
				
				"Tesla has decided to pay all of its international stockholders "
				+ "(which includes you) a special dividend of \n"+bonus+" million bits each!",
				
				"You have collected enough points with the airline that you frequently fly with, "
				+ "and you were able to redeem \n"+bonus+" million bits!",
				
				"Your bond with the French government has matured, and you’ve made "+bonus+" million bits "
				+ "in interest!",
				
				"You have won "+bonus+" million bits from an eating competition in Ireland!",
				
				"A travellers convention that you attended in South Africa decided to give all "
				+ "attendees "+bonus+" million bits!"};
		
		System.out.println(reasons[index]);
		bits +=bonus;
		
		return(bits);
	}
	
	public static void endOfGame(Boolean[]obtainedProperties) {
		Random rand = new Random();
		
		int obtained; //number of obtained properties
		
		System.out.println("\nLooks like you're out of die rolls!");
		
		//calculates how many points the user obtained
		obtained = numberOfProperties(obtainedProperties);
		
		 // max number of points is 10^7, as there are 7 properties
		int userPoints = (int)Math.pow(10, obtained);
		 
		//generates a random number between 1 and 10^7, which is the range for how many points the user can possibly obtain
		int compPoints = 1+rand.nextInt(10000001);
		
		System.out.println("\nYou have obtained " + obtained + " properties, which managaed to get you "+userPoints+" point(s)."
				+ "\nHowever, our AI managed to get "+compPoints+" points.");
		
		if(userPoints>compPoints) {
			System.out.println("\nCongratulations on conquering the world!");
		}
		
		else if(userPoints<compPoints) {
			System.out.println("\nI guess the odds weren't in your favor, better luck next time!");
		}
		
		else { //super rare case where userPoints = compPoints
			System.out.println("\nAccording to my calculations, there appears to be a tie.\n"
					+ "How can this be...?");
		}
	}
	
	public static boolean keepPlaying() {
		//allows user to make a decision on whether or not they would like to play again
		
		boolean valid = true;
		
		System.out.println("\n\nPlease select an option from the list below:"
				+ "\nEnter 1 if you would like to play again."
				+ "\nEnter 2 if you would like to leave this game.");
		String option = input.nextLine();
		
		option = invalidInput(option); //if user inputs an option not listed
		
		if(option.equals("1")) { //user chose to stay
			System.out.println("Here we go again.\n");
			
			valid = true;
			
		}
		
		else if(option.equals("2")){ //user chose to leave
			
			valid = false;
		}
		
		return valid;
		
	}
	
	public static String goodbye (String name) {
		//runs at the end of the game
        Random rand = new Random();
        
        System.out.println("We meet again, "+name+".");
        inputLine();
        
        System.out.println("\nI guess your travels have finally come to an end."
        		+ "\n\nThanks for playing Worldwide Monopoly!");
        String message;
        int index = rand.nextInt(5);
        String[] options = {"\nHope to see you again!" , "\nUntil next time!" , 
        		"\nSee you soon!", "\nNice meeting you!", "\nGoodbye!"};
        message = options[index];
        //generates and prints random goodbye message for the user
        return(message);
    }
	
	public static String invalidInput(String choice) { 
		//runs in any other method that requires 1 or 2 as input, and forces user to only entering '1' or '2'
		while(!choice.equals("1") && !choice.equals("2")) {
			System.out.println("That is not a valid option. Please select an option from the list above.");
			choice = input.nextLine();
		}
		
		return(choice);
	}
	
	public static void asterickLine(String[] properties, int index, int strLength) {
		//used to create the asterick box around congratulatory message when user buys a property
		int length = properties[index].length();
		
		for(int i = 1; i<=strLength+length; i++) {
			System.out.print("*");
		}
		
	}
	
	public static void gameBoard() {
		//first row
		line(51);
		newLine();
		
		for(int i  = 1; i<=3; i++) {
			emptySpace();
			empty(9);	
		}
		
		System.out.print("\n"
				+ "|  Bonus  "
				+ "| Victoria"
				+ "| Chance 3"
				+ "|  Krong  "
				+ "|  Go to  |"
				+ ""
				+ "\n"
				+ "| Bits 2  "
				+ "|  Falls, "
				+ "|         "
				+ "|Siem Reap"
				+ "|   Jail  |"
				+ "\n"
				+ "|         "
				+ "| Zimbabwe"
				+ "|         "
				+ "| Cambodia"
				+ "|         |");
		
		
		newLine();
		line(51);
		
		//second row
		newLine();
		emptySpace();
		empty(29);
		emptySpace();
		System.out.print("\n| Chance 2|");
		empty(29);
		System.out.print("| Chance 4|\n");
		
		for(int i = 1; i<=2; i++) {
			emptySpace();
			empty(29);
			emptySpace();
			newLine();
		}
		line(11);
		empty(29);
		line(11);
		
		//row 3
		newLine();
		emptySpace();
		empty(29);
		emptySpace();
		System.out.print("\n"
				+ "| Sydney, |");
		empty(29);
		System.out.print("|  Tax 2  |"
				+ "\n|Australia|");
		empty(10);
		System.out.print("WORLDWIDE");
		empty(10);
		emptySpace();
		newLine();
		emptySpace();
		empty(10);
		System.out.print(" MONOPOLY");
		empty(10);
		emptySpace();
		newLine();
		line(11);
		empty(29);
		line(11);
		
		//row 4
		newLine();
		emptySpace();
		empty(3);
		System.out.print("Where will you go today?");
		empty(2);
		emptySpace();
		System.out.print("\n"
				+ "|  Rome,  |");
		empty(29);
		System.out.print("|  Cuzco, |");
		System.out.print("\n"
				+ "|  Italy  |");
		empty(29);
		System.out.print("|  Peru   |");
		newLine();
		emptySpace();
		empty(29);
		emptySpace();
		newLine();
		line(51);
		
		//row 5
		newLine();
		
		for(int i = 1; i<=3; i++) {
			System.out.print("|         |         ");
		}

		System.out.print("\n"
				+ "|  Tax 1  "
				+ "|   CA.   "
				+ "|  Cairo, "
				+ "| Chance 1"
				+ "|  Bonus  |"
				+ "\n"
				+ "|         "
				+ "|   USA   "
				+ "|  Egypt  "
				+ "|         "
				+ "|  Bits 1 |");
		newLine();
		for(int i = 1; i<=2; i++) {
			System.out.print("|         |         ");
		}
		System.out.print("|<<- START|");
		newLine();
		line(51);
		newLine();
		
	}
	
	public static void line(int limit) { 
		//prints out full line
		for(int i = 1; i<=limit; i++) {
			System.out.print("_");
		}
	}
	
	public static void empty(int limit) {
		//prints out empty filler space
		for(int i =1; i<=limit; i++) {
			System.out.print(" ");
		}
	}
	
	public static void emptySpace() {
		//additional filler for individual space
		System.out.print("|         |");
	}
	
	public static void newLine() {
		System.out.print("\n");
	}

	public static void inputLine() {
		//allows user to hit return to continue
		input.nextLine();
	}

	public static int numberOfProperties(Boolean [] obtainedProperties) {
	int obtained = 0;
		
		for(int i = 0; i<obtainedProperties.length; i++) {
			
			if(obtainedProperties[i]) {
				obtained++;
			}
		}
	
	return(obtained);
	}
}
