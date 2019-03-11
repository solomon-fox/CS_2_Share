import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class LLCanvas extends Canvas
{
	LinkedListManager list = null;
	int pointSize = 10;
	Color bgColor = Color.BLACK, fgColor = Color.MAGENTA;
	
	public LLCanvas(LinkedListManager m)
	{
		list = m;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(bgColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(fgColor);
		for(int i=0; i<list.size(); i++)
		{
			Point p = list.get(i).value;
			g.fillOval(p.x - pointSize/2, p.y - pointSize/2, pointSize, pointSize);
		}
	}
	
	public void setPointSize(int size)
	{
		pointSize = size;
	}
	
}
