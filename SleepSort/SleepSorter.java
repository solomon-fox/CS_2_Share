import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SleepSorter
{
	public SleepSorter()
	{

		Scanner input = new Scanner(System.in);
		System.out.print("How many values to generate? ");
		int n = input.nextInt();

		ArrayList<Integer> values = new ArrayList<>();
		for (int i = 0; i < n; i++)
			values.add((int) (Math.random() * 100));

		//make an empty list to hold the sorted values
		ArrayList<Integer> finalList = new ArrayList<>();
		
		System.out.println("\nOriginal list:");
		for (int x : values)
		{
			System.out.print(x + " ");
			
			//Spawn and start a new thread for this value
			//	Create a new instance of SleepThread
			SleepThread st = new SleepThread(x, finalList);
			Thread t = new Thread( st );
			t.start();
		}
		
		try { Thread.sleep(11000); }
		catch(Exception e) {}
		
		System.out.println();
		System.out.println(finalList);
	}
	
	public static void main(String[] args)
	{
		new SleepSorter();
	}

}
