import java.util.ArrayList;

public class SleepThread implements Runnable
{
	int sleepTime = 0;
	ArrayList<Integer> theList;
	
	public SleepThread(int n, ArrayList<Integer> list)
	{
		sleepTime = n * 100;
		theList = list;
	}	
	
	@Override public void run()
	{
		//Sleep for a specific amount of time
		try {
			Thread.sleep(sleepTime);
		}
		catch(Exception e) {}
		
		System.out.println("Finished sleep for value "+ (sleepTime/100));
		//Add a value to the list
		theList.add(sleepTime / 100);
	}

}
