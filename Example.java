import java.math.BigInteger;

public class Example
{

	public Example()
	{
		System.out.println("Hello all!");

		// variables
		int x = 0; // short, long,
		String s = "";
		char c = 'A';
		boolean isMaxCorrect = true;
		double pi = 3.14; // decimal number - float also

		// Objects
		BigInteger bigInt = new BigInteger("99988877767665544433221");

		// methods
		System.out.println(bigInt);

		// control structures
		if (c == 'A')
		{
			System.out.println("The letter is A!");
			System.out.print("Potatoes");
		}
		else
		{
			System.out.println("The letter is not A!");
		}
		
		//for loop
		for(int i = 0; i < 10; i++)
		{
			System.out.println(i);
		}
		
		//while loop
		int i = 0;
		while( i < 10 )
		{
			System.out.println(i);
			i++;
		}
		
		i = 0;
		do
		{
			System.out.println(i);
			i++;
		}while(i < 10);

		// ArrayList<String> list = new ArrayList<>(); //auto-import - CTRL SHIFT O
	}	// end of the constructor
	
	//write my own method
	public int add(int value1, int value2)
	{
		return value1 + value2;
	}
	

	public static void main(String[] args)
	{
		new Example(); // call this class' constructor
	}
}
