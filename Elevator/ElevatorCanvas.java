import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class ElevatorCanvas extends Canvas
{
	private int numFloors = 10, numBasements = 3, currFloor = 0;
	
	public ElevatorCanvas(int n, int b)
	{
		numFloors = n-1;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int rectHeight = getHeight() / (numFloors+numBasements);
		
		
		//map the range of the Canvas height from 0 to (getHeight()-rectHeight) to 
		//	floor range [numFloors:numBasements*-1]
		//affine transform:
		// y=(x-a)\frac{d-c}{b-a}+c -- map x in range [a,b] to range [c,d]
		
		int yPos = (currFloor - numFloors)*( (getHeight() - rectHeight)/(-numBasements - numFloors) ) + 0;
//		System.out.println("Calculated yPos "+yPos+" for floor "+currFloor);
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, yPos, getWidth(), rectHeight);
	}
	
	public void setFloor(int c)
	{
		currFloor = c;
	}
}
