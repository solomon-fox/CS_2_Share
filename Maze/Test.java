import java.awt.Point;
import java.util.ArrayList;

public class Test implements NavigatorSolver
{
	//This method must return a list of points!
	//These points will be highlighted on screen.
	@Override public ArrayList<Point> generateSolution(char[][] maze,
			Point start, Point end)
	{
		//prints out the maze, if you'd like to see how the program stores it
		for (int r = 0; r < maze.length; r++)
		{
			for (int c = 0; c < maze[0].length; c++)
				System.out.print(maze[r][c]);
			System.out.println();
		}

		//a few helper variables
		int startRow = start.y;
		int startCol = start.x;
		int endRow = end.y;
		int endCol = end.x;

		//pick 50 random points
		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i < 50; i++)
			points.add(new Point((int) (Math.random() * maze.length),
					(int) (Math.random() * maze.length)));

		return points;
	}

	//Use this to have messages appear in the GUI
	@Override public String getMessage()
	{
		return "";
	}

}
