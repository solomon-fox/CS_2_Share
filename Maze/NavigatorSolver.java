import java.awt.Point;
import java.util.ArrayList;


public interface NavigatorSolver
{
	public ArrayList<Point> generateSolution(char[][] maze, Point start, Point end);
	public String getMessage();
}
