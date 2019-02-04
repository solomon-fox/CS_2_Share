import java.util.Scanner;

public class RadixSort
{

	public RadixSort()
	{
		Scanner input = new Scanner(System.in);
		
		//could use ArrayList or arrays []
		int[] numbers;
		
		System.out.print("Please enter size of array: ");
		int size = input.nextInt();
		
		numbers = new int[size];
		
		//add random numbers in a loop based on the size of the array
		for(int i=0; i<numbers.length; i++)
		{
			//specific index i
						//cast result to an int
								//random double between 0-1
			numbers[i] = (int)(Math.random()*1000);
			System.out.print(numbers[i]+" ");
		}
		
		
	}
	
	public static void main(String[] args)
	{
		new RadixSort();
	}

}
