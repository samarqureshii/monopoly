import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class ICS3U_FP_Code_SamarQureshi {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int dieRollsLeft = 10;//this will allow the user to roll the die 10 times
		double bits = 20; //20 million bits
		
		Boolean[] obtainedProperties = new Boolean[7]; //initializes array that stores values of if 
		//the user has obtained a property or not, index correlates with index of [] properties
		fillArrayFalse(obtainedProperties); //fills Boolean array with all false values
		
		String [] boardSpaces = {"Bonus bits!", "Chance 1", "Cairo, Eygpt", 
				"California, USA", "Tax 1", "Rome, Italy", "Sydney, Austrailia",
				"Chance 2", "Free parking", "Victoria Falls, Zimbabwe", "Chance 3", 
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
			
			userLocation +=dieRoll;
			
			if(userLocation>15) { //to get rid of the index out of bounds error on []boardSpaces
				userLocation -=15;
			}
	
			displayLocation(userLocation, boardSpaces);
			
			//array moves to space, action is executed, decision may be made
			
			if(userLocation==2 || userLocation==3 || userLocation== 5 || userLocation== 6 
					|| userLocation == 9 || userLocation== 11 || userLocation== 15) { //if user lands on a property space
			
				bits = propertyAction(userLocation, bits, properties, obtainedProperties);
			}
			
			//bits left, and die rolls left is displayed at the end of every turn
			
			displayBits(bits);
			displayRollsLeft(dieRollsLeft);
			System.out.println("\nHit enter to roll the die.");
			input.nextLine();

		}
		
		System.out.println("You are out of die rolls!\n"); //also add display for number of points and properties obtained
		
		
	}
	
	public static int rollDie() { //will generate a number from 1-6 and print out the die roll
		Random rand = new Random();
		
		int dieRoll = 1+rand.nextInt(6);
		System.out.println("You rolled a "+dieRoll+ "!");
		return(dieRoll);
	}
	
	public static void displayBits(double bits) { //displays amount of bits user currently has
		System.out.println("Total bits: "+bits+ " million");
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
	
	
	public static double propertyAction(int userLocation, double bits, String [] properties, Boolean [] obtainedProperties) { //executes if the user lands on a property space, which are indexes 2, 3, 5, 6, 9, 11, or 15
		Scanner input = new Scanner(System.in);
		double price;
		
		if(userLocation == 2) { // case where user landed on Cairo, Eygpt
			price = 0.4;
			displayProperty(properties, 0); //0th index in []properties
			displayPrice(price);
			bits = propertyDecision(bits, price, obtainedProperties, 0, properties);
			
			
		}
		
		else if(userLocation == 3) { //case where user landed on California, USA
			price = 1.1;
			displayProperty(properties, 1); //1st index in []properties
			displayPrice(price);
			bits = propertyDecision(bits, price, obtainedProperties, 1, properties);
			
		}
		
		else if(userLocation == 5) { //case where user landed on Rome, Italy
			price = 1.7;
			displayProperty(properties, 2); //2nd index in []properties
			displayPrice(price);	
			bits = propertyDecision(bits, price, obtainedProperties, 2, properties);
			
		}
		
		else if(userLocation == 6) { //case where user landed on Syndey, Austrailia
			price = 2;
			displayProperty(properties, 3); //3rd index in []properties
			displayPrice(price);
			bits = propertyDecision(bits, price, obtainedProperties, 3, properties);
			
		}
		
		else if(userLocation == 9) { //case where user landed on Victoria Falls, Zimbabwe
			price = 2.3;
			displayProperty(properties, 4); //4th index in []properties
			displayPrice(price);
			bits = propertyDecision(bits, price, obtainedProperties, 4, properties);
			
		}
		
		else if(userLocation == 11) { //case where user landed on Krong Siem Reap, Cambodia
			price = 3.8;
			displayProperty(properties, 5); //5th index in []properties
			displayPrice(price);
			bits =propertyDecision(bits, price, obtainedProperties, 5, properties);
			
		}
		
		else if(userLocation == 15){ //case where user landed on Cuzco, Peru
			price = 5.4;
			displayProperty(properties, 6); //6th index in []properties
			displayPrice(price);
			bits =propertyDecision(bits, price, obtainedProperties, 6, properties);
			
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
		
		if(decisionToBuy==1) {
			obtainedProperties[index] = true;
			System.out.println("\nCongrats, you have purchased " + properties[index] + "!");
			bits -=price;
		}
		
		return(bits);
	}

}
