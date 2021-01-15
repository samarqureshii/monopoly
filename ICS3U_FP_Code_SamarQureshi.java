import java.util.Scanner;
import java.util.Random;

public class ICS3U_FP_Code_SamarQureshi {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int dieRollsLeft = 8;
		
		System.out.println("Welcome to Worldwide Monopoly! "
				+ "In this game, you are travelling around the world, "
				+ "with the objective of buying as many properties "
				+ "as you can in order to gain points. "
				+ "We’ve given you 20 million bits to obtain the most "
				+ "sought after landmarks in the world! Be careful though, "
				+ "you may encounter various obstacles that could "
				+ "throw you off course. "
				+ "Use your die rolls wisely, as you only have 8!"
				+ "\nLet's get started by having you roll the die. "
				+ "Hit enter to continue");
		input.nextLine();
		
		int dieRoll = rollDie(dieRollsLeft);
		System.out.println("Die roll:"+dieRoll);


	}
	
	public static int rollDie(int dieRollsLeft) {
		Random rand = new Random();
		
		int dieRoll = 1+rand.nextInt(7);
		System.out.println("You rolled a "+dieRoll+ "!");
		return(dieRoll);
		

	}

}
