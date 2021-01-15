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
		
		String [] boardSpaces = {"Bonus bits!", "Chance 1", "Cairo, Eygpt", 
				"California, USA", "Tax 1", "Rome, Italy", "Sydney, Austrailia",
				"Chance 2", "Free parking", "Zambia/Zimbabwe", "Chance 3", 
				"Krong Siem Reap, Cambodia", "Go to Jail", "Chance 4", "Tax 2", "Cuzco, Peru"};// add "You have landed on ..."
		
		int userLocation = 0; //index of the [] boardSpaces where the user is at
		
		String [] properties = {"Giza Pyramids", "Yosemite National Park", 
				"The Colosseum", "Sydney Opera House", "Victoria Falls", 
				"Angkor Wat", "Machu Picchu"}; 
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
			
			displayBits(bits);
			displayRollsLeft(dieRollsLeft);
			System.out.println("\nHit enter to roll the die.");
			input.nextLine();

		}
		
		System.out.println("You are out of die rolls!\n"); //also add display for number of points and properties obtained
		displayBits(bits);
		
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
	
	public static void displayRollsLeft(int dieRollsLeft) {
		System.out.println("Remaining die rolls: " + dieRollsLeft);
	}
	
	public static void displayLocation(int userLocation, String [] boardSpaces) {
		System.out.println("You have landed on " + boardSpaces[userLocation] + ".");
	}

}
