import java.util.ArrayList;
import java.util.Scanner;

public class RPNCalc
{
	public RPNCalc()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter an expression: ");
		String expr = input.nextLine();
		
		ArrayList<Integer> stack = new ArrayList<>();
		
		String[] tokens = expr.split(" ");
		for(int i=0; i<tokens.length; i++)
		{
			if( tokens[i].equals("+") )
			{
				int term1 = stack.remove(stack.size() - 1);
				int term2 = stack.remove(stack.size() - 1);
				
				stack.add( term1 + term2 );
			}
			else if(tokens[i].equals("*"))
			{
				
			}
			else
			{
				int x = Integer.parseInt(tokens[i]);
				stack.add(x);
			}
		}
		
		System.out.println(stack.get(0));
		
		
		
		
		
		//String s = "123";
		//int s_converted = Integer.parseInt(s);
		
		
	}
	
	public static void main(String[] args)
	{
		new RPNCalc();
	}

}
