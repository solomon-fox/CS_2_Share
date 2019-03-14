import java.util.Scanner;

public class BSTTester
{
	BinarySearchTree tree = new BinarySearchTree();
	
	public BSTTester()
	{
		Scanner in = new Scanner(System.in);
		
		String s = "";
		
		while(true)
		{
			System.out.print("Enter token, or -1 to stop: ");
			s = in.nextLine();
			if(s.equals("-1"))
				break;
			tree.insert( new BSTNode(s) );	
			//tree.recursiveInsert(new BSTNode(s));
		}
		in.close();
		
		System.out.println("Here's the complete tree, in order: ");
		tree.traverse();
	}
	
	public static void main(String[] args)
	{
		new BSTTester();
	}
}
