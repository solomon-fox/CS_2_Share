import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class SleepThreadComplete implements Runnable
{
	int value = 0;
	final int DELAY_MULTIPLE = 100;
	Vector<Integer> masterList;
	CountDownLatch startSignal, doneSignal;
	
	public SleepThreadComplete(int v, Vector<Integer> list, CountDownLatch start, CountDownLatch done)
	{
		value = v;
		masterList = list;
		startSignal = start;
		doneSignal = done;
	}
	
	public void run()
	{
		try 
		{
			startSignal.await();

			Thread.sleep(value * DELAY_MULTIPLE); 
			System.out.println("\tFinished Thread for value "+value);
			masterList.add(value);
		}
		catch(Exception e) {}
		
		doneSignal.countDown();
	}
}
