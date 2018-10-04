import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class SleepSorterComplete
{
	public SleepSorterComplete()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("How many values to generate? ");
		int n = input.nextInt();

		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(n);

		Vector<Integer> values = new Vector<>();
		for (int i = 0; i < n; i++)
			values.add((int) (Math.random() * 100));

		System.out.println("\nOriginal list:");
		for (int x : values)
		{
			System.out.print(x + " ");
			SleepThreadComplete st = new SleepThreadComplete(x, values, startSignal,
					doneSignal);
			Thread t = new Thread(st);
			t.start();
		}
		System.out.println();

		values.clear();
		startSignal.countDown();
		try
		{
			doneSignal.await();

			System.out.println("\nNew list:");
			for (int x : values)
				System.out.print(x + " ");
		}
		catch (Exception e)
		{}
	}

	public static void main(String[] args)
	{
		new SleepSorterComplete();
	}
}
