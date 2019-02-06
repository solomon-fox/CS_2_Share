import java.util.ArrayList;
import java.util.Scanner;

public class RadixSort
{

	public RadixSort()
	{
		Scanner input = new Scanner(System.in);

		// could use ArrayList or arrays []
		int[] numbers;

		System.out.print("Please enter size of array: ");
		int size = input.nextInt();

		numbers = new int[size];

		// add random numbers in a loop based on the size of the array
		for (int i = 0; i < numbers.length; i++)
		{
			// specific index i
			// cast result to an int
			// random double between 0-1
			numbers[i] = (int) (Math.random() * 1000);
			System.out.print(numbers[i] + " ");
		}

		ArrayList<Integer>[] bins = new ArrayList[10];
		for (int i = 0; i < bins.length; i++)
			bins[i] = new ArrayList<Integer>();	//instantiating - filling in

		int placeValue = 1;
		do
		{

			// for each value in the array of numbers,
			for (int i = 0; i < numbers.length; i++)
			{
				// numbers[i]
				int binIndex = numbers[i] 	//considering number at index i
						% (int) Math.pow(10, placeValue)	//mod by a power of ten
						/ (int) Math.pow(10, placeValue - 1);	//divide by the next lower power of ten
				// place it in a "bin" based on its place value - ArrayLists
				bins[binIndex].add(numbers[i]);
				// "add" things to a bin
				// "remove"
				// array of ArrayLists, one list per bin
			}
			
			
			// empty the bins in order into the original array
			int index = 0;
			for (int i = 0; i < bins.length; i++)
			{
				ArrayList<Integer> temp = bins[i];
				//keep focusing on temp until it's empty
				while(temp.size() > 0)
				{
					int value = temp.remove(0);	//takes an item out of the bin
					numbers[ index ] = value;
					index++;					
				}
			}
			
			placeValue++;
		} while (placeValue < 10);
		// repeat for next place value
		
		//print the sorted values
		System.out.println();
		for (int i = 0; i < numbers.length; i++)
			System.out.print(numbers[i] + " ");
		
	}

	public static void main(String[] args)
	{
		new RadixSort();
	}

}
