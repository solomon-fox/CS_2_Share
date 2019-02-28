import java.text.DecimalFormat;

public class LinkedListSizeTester
{
	final int MAX_ITERATIONS = 32;
	DecimalFormat df = new DecimalFormat("#.00"); 

	public LinkedListSizeTester()
	{
		int iteration = 1; // variable to count how many tests we've tried

		while (iteration <= MAX_ITERATIONS)
		{
			// Let's make successively larger linked lists.
			// We'll try a size 2^iteration, so 2, 4, 8, etc.
			int arraySize = (int) Math.pow(2, iteration);
			
			LinkedList list = new LinkedList(); // Make a fresh list.
			System.gc(); // Runs garbage collection, which makes sure memory is available.
			
			System.out.print("Can we make a linked list of size 2^"
					+ +iteration + "? ...");

			// We expect that we'll eventually run out of memory,
			// so we'll "try" to create the array and "catch"
			// the program if it is unsuccessful.
			try
			{
				// Record the time that we started to build the array.
				long startTime = System.currentTimeMillis();
				
				// We'll have to actually construct nodes and
				// try to connect them...
				for (int i = 0; i < arraySize; i++)
				{
					// Generate a random number in [0 999].
					int num = (int) (Math.random() * 1000);
					// Construct a node to hold the random number.
					LinkedListNode newNode = new LinkedListNode(num);
					// Place it into the list.
					list.add(newNode);
				}

				// If we were successful, we'll get the success message.
				System.out.println("success! We're using "
						+ df.format((Runtime.getRuntime().totalMemory()
								- Runtime.getRuntime().freeMemory()) / 1024.0 / 1024.0)
						+ " megabytes of memory. It took " + 
								(System.currentTimeMillis() - startTime) + "ms.");
			}
			catch (Error e)
			{
				System.out.println("nope, we got a " + e + " error.");
			}

			iteration++; // try the next size up
		}
	}

	public static void main(String[] args)
	{
		new LinkedListSizeTester();
	}
}
