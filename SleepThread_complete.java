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
		try {
			Thread.sleep(sleepTime);
			theList.add( sleepTime / 100 );
			System.out.println("I'm done!  "
					+ "After "+sleepTime+" ms!" +
					theList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}	
	
	

}
