import java.awt.Point;
import java.util.ArrayList;

public class Greedy implements NavigatorSolver
{

	@Override public ArrayList<Point> generateSolution(char[][] maze,
			Point start, Point end)
	{
		
		int startRow = start.y;
		int startCol = start.x;
		int endRow = end.y;
		int endCol = end.x;
		
		//closed list - places we've visited
		ArrayList<GreedyNode> closedList = new ArrayList<>();
		
		//add the initial Node
		GreedyNode runner = new GreedyNode();
		runner.row = startRow;
		runner.col = startCol;
		runner.h = Math.abs((endRow - runner.row) + (endCol - runner.col));

		//as long as there are things in the Queue
		GreedyNode previousNode = runner;
		do
		{
			//see if this runner is in the closed list, if so we should not consider it
			if(closedList.contains(runner))	//contains uses the equals() method in GreedyNode
				break;	//skip ahead to the next iteration of the loop
			
			//if it's not, add it to the closed list
			closedList.add(runner);
			
			//see if it's goal
			if(runner.row == endRow && runner.col == endCol)
			{
				//if so, build a list of points to get here
				break;
			}
			
			previousNode = runner;
			//otherwise, add this runner's successors to the Queue
			//up, down, left, right
			if(runner.row-1 >= 0 && maze[runner.row-1][runner.col] != 'x')
			{
				GreedyNode t = new GreedyNode();
				t.row = runner.row-1;
				t.col = runner.col;
				t.parent = runner;
				t.h = Math.abs((endRow - t.row) + (endCol - t.col));
				if(t.h < runner.h)
					runner = t;
			}
			if(runner.row+1 < maze.length && maze[runner.row+1][runner.col] != 'x')
			{
				GreedyNode t = new GreedyNode();
				t.row = runner.row+1;
				t.col = runner.col;
				t.parent = runner;
				t.h = Math.abs((endRow - t.row) + (endCol - t.col));
				if(t.h < runner.h)
					runner = t;
			}
			if(runner.col-1 >= 0 && maze[runner.row][runner.col-1] != 'x')
			{
				GreedyNode t = new GreedyNode();
				t.row = runner.row;
				t.col = runner.col-1;
				t.parent = runner;
				t.h = Math.abs((endRow - t.row) + (endCol - t.col));
				if(t.h < runner.h)
					runner = t;
			}
			if(runner.col+1 < maze[0].length && maze[runner.row][runner.col+1] != 'x')
			{
				GreedyNode t = new GreedyNode();
				t.row = runner.row;
				t.col = runner.col+1;
				t.parent = runner;
				t.h = Math.abs((endRow - t.row) + (endCol - t.col));
				if(t.h < runner.h)
					runner = t;
			}
		} while( !(previousNode.row == runner.row && 
				previousNode.col == runner.col) );
		
		//building the path from the start
		ArrayList<Point> points = new ArrayList<>();
		while(runner != null)
		{
			points.add(new Point(runner.col, runner.row));
			runner = runner.parent;
		}
		//and we're done
		return points;
	}

	@Override public String getMessage()
	{
		return null;
	}

}
