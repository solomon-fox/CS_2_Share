import java.util.ArrayList;

//Create Flowers and watch them grow over time
public class Garden
{
	// a List to hold Flower instances
	ArrayList<Flower> flowers = new ArrayList<>();
	// number of Flowers to create
	final int NUM_FLOWERS = 6;
	// all the viable color Strings
	String[] possibleColors = {"Blue", "Pink", "Chartreuse", "Mauve", "Periwinkle"};
	int[] arr = new int[1000];	//1000 indexes for integers
	int [][] blob = {
			{1, 2, 3, 4},
			{5, 6, 7, 8}
		};	//2D array
	int[][] blob2 = new int[1000][2];	//2D array

	public Garden()
	{
		// counting var, condition, increment
		for (int i = 0; i < NUM_FLOWERS; i++)
		{
			// create a new Flower instance
			//Flower f = new Flower("Chartreuse");
			int randomIndex = (int) (Math.random()*possibleColors.length);
			Flower f = new Flower(possibleColors[randomIndex]);

			// add it to the list of Flowers
			flowers.add(f);
		}
		System.out.println("Created " + flowers.size() + " flowers.");

		// List operations
		// fetch one element in the list
		System.out.println(
				"The second flower's color is: " + flowers.get(1).color);

		// remove an element in the list
		if (flowers.get(0).color.equals("Red")) 
			flowers.remove(0);
		
		//simulate the flowers growing over time
		int numGenerations = 15;
		while(numGenerations > 0)
		{
			numGenerations = numGenerations - 1;
			
			//grow each flower by its growth variable
			//for-each loop - affect all of the items in a list
			for(Flower f: flowers)
			{
				f.height += f.growthAmnt;
				System.out.println("The "+f.color+" flower is now height "+f.height);
			}
			System.out.println("------------------\n");
			
			//slow down the simulation
			try	//something might go wrong in the following block
			{
				Thread.sleep(1000);	//pause # of milliseconds
			}
			catch(Exception e)	//prevent it from crashing the program
								// and do this block instead
			{
				//save the user's work to a file?
				//warn the user that the program will exit?
				e.printStackTrace();  //red error text
			}
		}
	}

	// starts the program
	public static void main(String[] args)
	{
		new Garden();
	}

}
