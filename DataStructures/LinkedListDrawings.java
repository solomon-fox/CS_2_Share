import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class LinkedListDrawings extends JFrame implements MouseListener
{
	
	//coordinates that will be drawn, as x1, y1, x2, y2, ..., xn, yn
	int[] coordinates = new int[] { 5, 6, 		//(5, 6)
									57, 48, 	//(57, 48)
									112, 214,	//(112, 214)
									154, 238,	//(154, 238)
	};
	LinkedListManager list = new LinkedListManager();
	LLCanvas canvas;
	
	
	public LinkedListDrawings()
	{
		super("Drawing with Linked Lists!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		assert coordinates.length % 2 == 0 : "Error, odd number of coordinates!";

		for(int i=0; i<coordinates.length; i+=2)
		{
			Point p = new Point(coordinates[i], coordinates[i+1]);
			LinkedListPointNode newNode = new LinkedListPointNode(p);
			list.add(newNode);
		}
		
		canvas = new LLCanvas(list);
		canvas.addMouseListener(this);
		
		add(canvas, BorderLayout.CENTER);
		setVisible(true);
	}
	
	@Override public void mouseClicked(MouseEvent e)
	{
		Point p = e.getPoint();
		int loc = list.find(p);
		//is this point already in the list?  If so, remove it
		if(loc != -1)
			list.remove(loc);
		//otherwise, add it
		else
			list.add(new LinkedListPointNode(p));
		
		canvas.repaint();
	}

	public static void main(String[] args)
	{
		new LinkedListDrawings();
	}

	@Override public void mouseEntered(MouseEvent e)
	{}

	@Override public void mouseExited(MouseEvent e)
	{}

	@Override public void mousePressed(MouseEvent e)
	{}

	@Override public void mouseReleased(MouseEvent e)
	{}
}
