public class Elevator implements Runnable
{
	final int SPEED = 1000; // how quickly should the elevator update its position?
	int currentFloor = 0; // where is the elevator now?
	ElevatorCanvas canvas;

	public Elevator(ElevatorCanvas c)
	{
		canvas = c;
	}

	@Override public void run()
	{
		//Describe an elevator's behavior here!
		
		
		
		
		
		// updates the GUI
		canvas.setFloor(currentFloor);
		canvas.repaint();

		try
		{
			Thread.sleep(SPEED);
		} // pause for a bit to simulate movement
		catch (Exception e)
		{}

	}
}
