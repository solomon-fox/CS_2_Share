import java.util.ArrayList;
import java.util.Scanner;

public class SleepSort
{

	public SleepSort()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter size of array: ");
		int size = input.nextInt();

		//make an array of the specified size
		int[] numbers = new int[size];	

		// add random numbers in a loop based on the size of the array
		for (int i = 0; i < numbers.length; i++)
		{
			numbers[i] = (int) (Math.random() * 1000);
			System.out.print(numbers[i] + " ");
		}

		//make a blank list. We'll fill it with sorted values
		ArrayList<Integer> finalList = new ArrayList<>();
		//spawn a thread for each value, pass it the List so it knows where to add to
		//TBD

		// print the sorted values
		System.out.println(finalList);
	}

	public static void main(String[] args)
	{
		new SleepSort();
	}
}