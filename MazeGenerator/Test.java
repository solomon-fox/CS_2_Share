import java.awt.Point;
import java.util.ArrayList;

public class Test implements NavigatorSolver
{

	@Override public ArrayList<Point> generateSolution(char[][] maze,
			Point start, Point end)
	{
		for (int r = 0; r < maze.length; r++)
		{
			for (int c = 0; c < maze[0].length; c++)
				System.out.print(maze[r][c]);
			System.out.println();
		}
		// A* GOES HERE
		int startRow = start.y;
		int startCol = start.x;
		int endRow = end.y;
		int endCol = end.x;

		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i < 50; i++)
			points.add(new Point((int) (Math.random() * maze.length),
					(int) (Math.random() * maze.length)));

		return points;
	}

	@Override public String getMessage()
	{
		return "";
	}

}
