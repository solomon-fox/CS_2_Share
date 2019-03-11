import java.awt.Point;

public class LinkedListManager
{
	LinkedListPointNode root = null;
	
	public LinkedListManager()
	{	}
	
	public void add(LinkedListPointNode newNode)
	{
		if(root == null)
			root = newNode;
		else
		{
			//there must be at least one node in the list
			//so, let's follow the list until we find a node with no next
			
			//use the "pointer" to next nodes to jump through the list
			LinkedListPointNode runner = root;	//not a copy, but a pointer
			
			while(runner.next != null)
				runner = runner.next;
			
			//runner is now pointing at the last node in the chain
			//so, set runner's next to the new node
			runner.next = newNode;
		}
	}
	
	public int size()
	{
		if(root == null)
			return 0;
		
		int count = 1;
		LinkedListPointNode runner = root;
		while(runner.next != null)
		{
			runner = runner.next;
			count++;
		}
		return count;
	}
	
	public LinkedListPointNode get(int index)
	{
		if(root == null)
			return null;
		
		int count = 0;
		LinkedListPointNode runner = root;
		while(runner.next != null && count != index)
		{
			runner = runner.next;
			count++;
		}
		return runner;
	}
	
	public LinkedListPointNode remove(int index)
	{
		if(root == null)
			return null;
		
		if(index == 0)
			root = root.next;
		
		int count = 1;
		LinkedListPointNode runner = root.next, lastNode = root;
		while(count != index)
		{
			lastNode = runner;
			runner = runner.next;
			count++;
		}
		
		lastNode.next = runner.next;
		return runner;
	}
	
	public int find(Point p)
	{
		if(root != null)
		{
			LinkedListPointNode runner = root;
			int count = 0;
			while(runner != null)
			{
				if(runner.value.equals(p))
					return count;
				runner = runner.next;
				count++;
			}
		}
		return -1;
	}
	
}





