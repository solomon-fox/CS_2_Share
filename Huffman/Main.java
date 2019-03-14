import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
	private final String FILENAME = "preamble.txt"; 
	
	public Main()
	{
		String rawText = loadFile(FILENAME);
		System.out.println("Original String: ");
		//prints out the original file, contents now stored in rawText
		System.out.println(rawText);
		
		/** HUFFMAN ENCODING GOES HERE
		 * I suggest following the document HuffmanHandout.pdf.
		 * There are five steps:
		 * 1.	Count the frequency of characters in the String,
		 * 		i.e., how many "A"'s are there?  How many "B"'s?
		 * 2.	For each unique character, make a binary tree node
		 * 		that holds the character and its frequency.
		 * 		I.e., if there were 7 C's in the String, I would make
		 * 		a node that holds both "C" and 7.
		 * 		Add these nodes to a minimal Priority Queue, i.e. a 
		 * 		queue whose "next" item to be removed is the one with 
		 * 		the lowest frequency.
		 * 3. 	Remove two nodes from the queue.  Connect them to
		 * 		a new parent tree node.  The new node's character is
		 * 		irrelevant, and its frequency is the sum of the two children.
		 * 4.	Repeat step 3 until there is only one node left in 
		 * 		the queue.  The one remaining node is the root of the tree.
		 * 5. 	Traverse the tree, recording your path as you go through it.
		 * 		Every left turn should append a "0" to a String sequence, every
		 * 		right turn should append a "1".  When you reach a leaf,
		 * 		record the character in the leaf and its sequence.
		 */
		
		//saves the String rawText into the original file
		saveFile(FILENAME, rawText);
	}
	
	private String loadFile(String fName)
	{
		String ret = "";
		
		try
		{
			Scanner fileReader = new Scanner(new File(fName));
			
			while(fileReader.hasNextLine())
				ret += fileReader.nextLine();
			
			fileReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private void saveFile(String fName, String contents)
	{
		try{
			PrintWriter out = new PrintWriter("output_"+fName);
			out.println(contents);
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new Main();
	}

}
