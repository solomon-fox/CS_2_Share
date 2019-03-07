
public class ListTester
{
	public static void main(String[] args)
	{
		LinkedListManager list = new LinkedListManager();
		for(int i=0; i<100; i++)
		{
			int num = (int) (Math.random() * 1000);
			LinkedListNode newNode = new LinkedListNode(num);
			list.add(newNode);
		}
		
		//test the size
		System.out.println("There are "+list.size()+" nodes.");
		
		//test the get
		System.out.println("The value at index 12 is: "+list.get(12).value);
	}
}
