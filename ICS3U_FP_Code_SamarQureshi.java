import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class ICS3U_FP_Code_SamarQureshi {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int dieRollsLeft = 15;//this will allow the user to roll the die 15 times
		double bits = 20; //20 million bits
		
		Boolean[] obtainedProperties = new Boolean[7]; //initializes array that stores values of if 
		//the user has obtained a property or not, index correlates with index of [] properties
		fillArrayFalse(obtainedProperties); //fills Boolean array with all false values
		
		String [] boardSpaces = {"Bonus bits 1", "Chance 1", "Cairo, Eygpt", 
				"California, USA", "Tax 1", "Rome, Italy", "Sydney, Austrailia",
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
		
		System.out.println("Welcome to Worldwide Monopoly! "
				+ "In this game, you are travelling around the world, "
				+ "with the objective of buying \nas many properties "
				+ "as you can in order to gain points. "
				+ "We’ve given you 20 million bits to obtain the most "
				+ "sought \nafter landmarks in the world! Be careful though, "
				+ "you may encounter various obstacles that could "
				+ "throw you off \ncourse. "
				+ "Use your die rolls wisely, as you only have 8!"
				+ "\n\nLet's get started by having you roll the die. "
				+ "Hit enter to continue.");
		input.nextLine();
		
		while(dieRollsLeft>0) { //condition for allowing the game to continue running
			
			//code below is "1 turn"
			
			int dieRoll = rollDie();
			dieRollsLeft--;
			
			userLocation +=dieRoll; //adds die roll to the user's current location
			
			if(userLocation>15) { //to get rid of the index out of bounds error on []boardSpaces
				userLocation -=15;
			}
	
			displayLocation(userLocation, boardSpaces);
			
			//array moves to space, action is executed, decision may be made
			
			if(userLocation==2 || userLocation==3 || userLocation== 5 || userLocation== 6 
					|| userLocation == 9 || userLocation== 11 || userLocation== 15) { //if user lands on a property space
			
				bits = propertyAction(userLocation, bits, properties, obtainedProperties);
			}
			
			else if(userLocation==1 || userLocation==7 || userLocation==10 || userLocation==13) {
				//chance method runs, returned value of bits in chance is passed here
				bits = chance(bits, userLocation, obtainedProperties, properties, dieRoll, dieRollsLeft);
			}
			
			else if(userLocation==0 || userLocation == 8){
				//bonusBits method runs, user collects a random amount of bits
			}
			
			else if(userLocation == 4 || userLocation ==14) {
				//tax method runs, random amount of bits are deducted from the user
				bits = tax(bits);
			}
			
			else {
				//jailState = true, jail method runs
				dieRollsLeft = jail(dieRollsLeft);
				
				
			}
			
			//bits left, and die rolls left is displayed at the end of every turn
			
			displayBits(bits);
			displayRollsLeft(dieRollsLeft);
			System.out.println("\nHit enter to roll the die.");
			input.nextLine();

		}
		
		System.out.println("You are out of die rolls!\n"); //also add display for number of points and properties obtained
		//game is over
		
	}
	
	public static int rollDie() { //will generate a number from 1-6 and print out the die roll
		Random rand = new Random();
		
		int dieRoll = 1+rand.nextInt(6);
		System.out.println("You rolled a "+dieRoll+ "!\n");
		return(dieRoll);
	}
	
	public static void displayBits(double bits) { //displays amount of bits user currently has
		System.out.println("\nTotal bits: "+bits+ " million");
	}
	
	public static void displayRollsLeft(int dieRollsLeft) { //displays the number of die rolls left
		System.out.println("Remaining die rolls: " + dieRollsLeft);
	}
	
	public static void displayLocation(int userLocation, String [] boardSpaces) { //displays to the user which space they've landed on
		System.out.println("You have landed on " + boardSpaces[userLocation] + ".");
	}
	
	public static void fillArrayFalse(Boolean[]array) { //fills a boolean array with all false values
		for(int i = 0; i<array.length; i++) {
			array[i] = false;
		}
	}
	
	
	public static double propertyAction(int userLocation, double bits, String [] properties, Boolean [] obtainedProperties) { 
		//executes if the user lands on a property space, which are indexes 2, 3, 5, 6, 9, 11, or 15
		Scanner input = new Scanner(System.in);
		double price;
		
		if(userLocation == 2) { // case where user landed on Cairo, Eygpt
			price = 0.4;
			displayProperty(properties, 0); //0th index in []properties
			displayPrice(price);
			if(!obtainedProperties[0]) { //user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 0, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[0]+".");
			}
			
		}
		
		else if(userLocation == 3) { //case where user landed on California, USA
			price = 1.1;
			displayProperty(properties, 1); //1st index in []properties
			displayPrice(price);
			if(!obtainedProperties[1]) { //user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 1, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[1]+".");
			}
		}
		
		else if(userLocation == 5) { //case where user landed on Rome, Italy
			price = 1.7;
			displayProperty(properties, 2); //2nd index in []properties
			displayPrice(price);	
			if(!obtainedProperties[2]) { //user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 2, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[2]+".");
			}
		}
		
		else if(userLocation == 6) { //case where user landed on Syndey, Austrailia
			price = 2;
			displayProperty(properties, 3); //3rd index in []properties
			displayPrice(price);
			if(!obtainedProperties[3]) { //user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 3, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[3]+".");
			}
		}
		
		else if(userLocation == 9) { //case where user landed on Victoria Falls, Zimbabwe
			price = 2.3;
			displayProperty(properties, 4); //4th index in []properties
			displayPrice(price);
			if(!obtainedProperties[4]) { //user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 4, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[4]+".");
			}
		}
		
		else if(userLocation == 11) { //case where user landed on Krong Siem Reap, Cambodia
			price = 3.8;
			displayProperty(properties, 5); //5th index in []properties
			displayPrice(price);
			if(!obtainedProperties[5]) { //user will only be allowed to buy the property if they do not have it
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
			if(!obtainedProperties[6]) { //user will only be allowed to buy the property if they do not have it
				bits = propertyDecision(bits, price, obtainedProperties, 6, properties);

			}
			
			else { //if user already owns that property
				System.out.println("\nLooks like you already own "+properties[6]+".");
			}
		}
		return(bits);
		
	}
	
	public static void displayProperty(String [] properties, int propertyIndex) {
		
		System.out.println("Availiable property for sale: " + properties[propertyIndex]);
	}
	
	public static void displayPrice(double price) { //displays the price of the specific property
		System.out.println("Price: " + price + " million bits");
	}
	
	public static double propertyDecision(double bits, double price, Boolean[]obtainedProperties, int index, String [] properties) { //allows user to choose whether or not they would like to buy the property
		Scanner input = new Scanner(System.in);
		//will allow user to purchase a property or not, and change the boolean value in []obtainedProperties to false for the given property in []properties

		
		System.out.println("\n\nPlease select an option from the list below:"
				+ "\nEnter 1 if you would like to buy this property."
				+ "\nEnter 2 if you do not want to buy this property.");
		int decisionToBuy = input.nextInt();
		
		while(decisionToBuy!=1 && decisionToBuy!=2) {
			System.out.println("That is not a valid option."
					+ " Please select an option from the list above.");
			decisionToBuy = input.nextInt();
		}
		
		if(decisionToBuy==1 && bits>=price) {
			obtainedProperties[index] = true;
			System.out.println("\nCongratulations, you have obtained " + properties[index] + "!");
			bits -=price;
		}
		
		else if(decisionToBuy==1 && bits<price) {
			System.out.println("You do not have enough bits to purchase this property.");
		}
		
		return(bits);
	}
	
	public static double chance(double bits, int userLocation, Boolean[]obtainedProperties, String[]properties, int dieRoll, int dieRollsLeft) { //will run if user lands on a chance space
		Random rand = new Random();
		int index = rand.nextInt(7);
		
		//explanations if the user lands on chance 1
		String [] explanations1 = {"\nWhile you were trekking across the Western Desert in Egypt, "
				+ "your camel decided to attack you and left you \nstranded in a sandstorm! "
				+ "In order to compensate you for this tragedy, the President of Egypt has decided \n"
				+ "to give you ownership of the Giza Pyramids.", 
				
				"\nYou and your dog were climbing "
						+ "the Sierra Nevada Mountains in California, but your dog jumped off the cliff! \n"
						+ "As a memento for of dog, you have been transferred ownership of Yosemite National Park.", 
				
				"\nAt an Italian auction, the bids to purchase the Colosseum were getting out of hand, "
						+ "so the auction organizers \ndecided to settle things with an invigorating duel "
						+ "of rock paper scissors. In the last round, you defeated your \nopponent with paper, "
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
		
		
		
			if(!obtainedProperties[index]) { //if user does not have that specific property
				System.out.println(explanations1[index]);
				obtainedProperties[index] = true;
			}
			else { //if user happens to have that specific property
				System.out.println("\nAwe, we were going to give you ownership of "+properties[index]+", but looks like you already have it.");
			}
			
		}
		
		else if(userLocation == 7) { //user lands on Chance 2
		//chance 2 is responsible for giving and taking away properties from the user
		
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
		else { //user lands on Chance 4
			//chance 4 sends user to jail
			jail(dieRollsLeft);
		}
		
		return(bits);
	}
	
	public static int jail(int dieRollsLeft) { //will throw the user in jail
		Random rand = new Random();
		Scanner input = new Scanner(System.in);
		
		int index = rand.nextInt(6);
		String[]reasons = {"You used a fake visa to attempt to gain entry into the United Kingdom, "
				+ "and lied to a customs officer, \nwhich resulted in your denial of entry, and being "
				+ "arrested by British authorities.",
				
				"You assaulted a police officer in Argentina, and failed to show up for your court hearing. "
				+ "\nAs a result, you have been given a jail sentence.",
				
				"You have been found guilty of airline fraud! You are being sent to jail.",
				
				"You stole an elephant in Sri Lanka, and demolished a village! "
				+ "\nYou have been thrown in jail by local authorities.",
				
				"The Russian military has identified you as an international criminal and you are "
				+ "now being sent to a prison \nin St Petersburg!",
				
				"You were caught poaching an endangered species of lemur in Madagascar, and have been thrown in jail!"};
		
		System.out.println(reasons[index]);
		
		boolean jailState = true;
		int dieRoll; 
		
		System.out.println("\nYou must roll a 1 or 6 to get out of jail.");
		
		while(jailState) {
			System.out.println("\nHit enter to roll the die.");
			input.nextLine();
			
			dieRoll = rollDie();
			dieRollsLeft--;
			displayRollsLeft(dieRollsLeft);
			
			if(dieRoll==1 || dieRoll ==6) {
				System.out.println("You are now out of jail!");
				jailState = false;
			}
			
			if(dieRollsLeft==0) {
				return dieRollsLeft;
			}
		}
		
		return dieRollsLeft;
		
		
		
		
	}
	
	public static double tax(double bits) {
		//if user lands on tax 1 or tax 2 they must pay a random tax	
		Random rand = new Random();
		int index = rand.nextInt(6);
		String [] reasons = {"On your most recent flight, your motion sickness got the best of you, "
				+ "so you threw up on a flight attendant! \nThe airline company was not very happy with you, "
				+ "and you ended up paying a hefty fine of 12 million bits.",
				
				"You had over 70 pounds of undeclared illegal goods in your carry on, "
				+ "and customs requires that you pay a fine of \n9.8 million bits.",
				
				"You broke your leg after falling off the Great Wall of China, and had to "
				+ "pay 4.8 million bits in hospital fines!",
				
				"You accidentally ran over a kangaroo with your Jeep in New Zealand!"
				+ " You had to pay a fine of 8 million bits \nfor reckless driving, "
				+ "and another fine of  7.6 million bits for harming an animal native to \nNew Zealand.",
				
				"While in South Korea, you were accidentally driving on the wrong side of the road! "
				+ "\nYou have been fined 14.2 million bits in violation of a road law.",
				
				"You were caught littering in British Columbia! "
				+ "\nLocal authorities require you to pay a fine of 0.2 million bits."};
		
		System.out.println(reasons[index]);
		
		if(index == 0) {
			bits-=12;
		}
		
		else if (index==1) {
			bits-=9.8;
		}
		
		else if(index==2) {
			bits-=4.8;
		}
		
		else if(index==3) {
			bits-=(8+7.6);
		}
		
		else if(index==4) {
			bits-=14.2;
		}
		
		else if(index==5) {
			bits-=0.2;
		}
		
		return(bits);
	}
	
	public static double bonusBits(double bits) {
		
		return(bits);
	}

}
