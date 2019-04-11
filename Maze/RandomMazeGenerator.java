import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RandomMazeGenerator extends JPanel
{
	private int TILESIZE = 10;
	private final Color PATH_COLOR = Color.GREEN;
	private int numSquares;
	private char[][] points;
	private ArrayList<Point> majorPoints = new ArrayList<>();

	public ArrayList<Point> getMajorPoints()
	{
		return majorPoints;
	}

	public char[][] getMaze()
	{
		return points;
	}

	public void applySolution(ArrayList<Point> solution)
	{
		if (solution != null && solution.size() > 0)
			for (Point p : solution)
			{
				points[p.y][p.x] = 'p';
			}
	}

	public RandomMazeGenerator(int def_width, int numPoints, int tileSize)
	{
		super();
		TILESIZE = tileSize;
		setSize(def_width, def_width);
		numSquares = def_width / TILESIZE - 1;
		points = new char[numSquares][numSquares];
		for (int r = 0; r < points.length; r++)
			for (int c = 0; c < points.length; c++)
				points[r][c] = 'x';

		MPoint st = new MPoint((int) (Math.random() * numSquares),
				(int) (Math.random() * numSquares), null);
		points[st.r][st.c] = 'S';

		ArrayList<MPoint> frontier = new ArrayList<MPoint>();
		for (int x = -1; x <= 1; x++)
			for (int y = -1; y <= 1; y++)
			{
				if (x == 0 && y == 0 || x != 0 && y != 0)
					continue;
				try
				{
					if (points[st.r + x][st.c + y] == '.')
						continue;
				} catch (Exception e)
				{
					continue;
				}
				frontier.add(new MPoint(st.r + x, st.c + y, st));
			}

		MPoint last = null;
		while (!frontier.isEmpty())
		{
			MPoint current = frontier.remove((int) (Math.random() * frontier
					.size()));
			MPoint op = current.opposite();
			try
			{
				if (points[current.r][current.c] == 'x')
				{
					if (points[op.r][op.c] == 'x')
					{
						points[current.r][current.c] = '.';
						points[op.r][op.c] = '.';

						last = op;

						for (int x = -1; x <= 1; x++)
							for (int y = -1; y <= 1; y++)
							{
								if (x == 0 && y == 0 || x != 0 && y != 0)
									continue;
								try
								{
									if (points[op.r + x][op.c + y] == '.')
										continue;
								} catch (Exception e)
								{
									continue;
								}
								frontier.add(new MPoint(op.r + x, op.c + y, op));
							}
					}
				}
			} catch (Exception e)
			{ // ignore Exceptions
			}
		}

		for (int x = 0; x < numPoints; x++)
		{
			int r = (int) (Math.random() * numSquares);
			int c = (int) (Math.random() * numSquares);

			java.awt.Point p = new java.awt.Point(c, r);
			while (majorPoints.contains(p) || points[p.y][p.x] == 'x')
			{
				r = (int) (Math.random() * numSquares);
				c = (int) (Math.random() * numSquares);

				p = new java.awt.Point(c, r);
			}
			majorPoints.add(p);
		}

	}

	public void paint(Graphics g)
	{
		super.paint(g);
		for (int r = 0; r < points.length; r++)
			for (int c = 0; c < points.length; c++)
			{
				if (points[r][c] == 'p')
					g.setColor(PATH_COLOR);
				else if (points[r][c] != 'x')
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.BLACK);

				g.fillRect(c * TILESIZE, r * TILESIZE, TILESIZE, TILESIZE);
			}

		char ch = 'A';
		Color greenColor = new Color(235, 0, 247);
		g.setFont(new Font("Arial", Font.BOLD, (int)(TILESIZE * 1.8)));
		for (java.awt.Point p : majorPoints)
		{
			int r = p.y;
			int c = p.x;
			g.setColor(Color.RED);
			g.fillRect(c * TILESIZE, r * TILESIZE, TILESIZE, TILESIZE);
			g.setColor(greenColor);
			g.drawString("" + (ch++), c * TILESIZE - 20, r * TILESIZE);
		}
	}
}

class MPoint
{
	Integer r;
	Integer c;
	MPoint parent;

	public MPoint(int x, int y, MPoint p)
	{
		r = x;
		c = y;
		parent = p;
	}

	public MPoint opposite()
	{
		if (this.r.compareTo(parent.r) != 0)
			return new MPoint(this.r + this.r.compareTo(parent.r), this.c, this);
		if (this.c.compareTo(parent.c) != 0)
			return new MPoint(this.r, this.c + this.c.compareTo(parent.c), this);
		return null;
	}
}
