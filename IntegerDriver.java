
public class IntegerDriver
{
	IntegerNode root = null;
	
	public IntegerDriver()
	{
		//add some random numbers to a Linked list
		for(int i=0; i< 15; i++)
		{
			int rando = (int)(Math.random()*100);
			//put rando into my linked list
			IntegerNode newNode = new IntegerNode(rando);
			if(root == null)
				root = newNode;	//making a connection to this new piece of info
			else
				add(newNode);
		}
		
		printAll();	//print out the contents of the list
	}
	
	public void printAll()
	{
		IntegerNode runner = root;
		System.out.print("{ ");
		
		while(runner.next != null)	//is there a next?
		{
			System.out.print(runner.value + 
					(runner.next.next == null ? "" : ", ")	//ternary statement - inline if			
					);
			runner = runner.next;	//go there!
		}
		
		System.out.print(" }");
	}
	
	public void add(IntegerNode newNode)
	{
		//follow the path along the nodes until 
		//	we find a node with no next value
		IntegerNode runner = root;
		
		while(runner.next != null)	//is there a next?
			runner = runner.next;	//go there!
		
		runner.next = newNode;
	}
	

	public static void main(String[] args)
	{
		new IntegerDriver();
	}

}
