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
		System.out.println(rawText);
		
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
