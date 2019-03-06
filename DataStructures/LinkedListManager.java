
public class LinkedListManager
{
	LinkedListNode root = null;
	
	public LinkedListManager()
	{	}
	
	public void add(LinkedListNode newNode)
	{
		if(root == null)
			root = newNode;
		else
		{
			//there must be at least one node in the list
			//so, let's follow the list until we find a node with no next
			
			//use the "pointer" to next nodes to jump through the list
			LinkedListNode runner = root;	//not a copy, but a pointer
			
			while(runner.next != null)
				runner = runner.next;
			
			//runner is now pointing at the last node in the chain
			//so, set runner's next to the new node
			runner.next = newNode;
		}
	}
	
	
	
}





