import java.awt.Point;
import java.util.ArrayList;

public class BreadthFirstSearch implements NavigatorSolver
{

	@Override public ArrayList<Point> generateSolution(char[][] maze,
			Point start, Point end)
	{
		ArrayList<Point> path = new ArrayList<>();
		
		// a few helper variables
		int startRow = start.y;
		int startCol = start.x;
		int endRow = end.y;
		int endCol = end.x;
		
		//create the initial state
		BFSState startState = new BFSState(startRow, startCol);
		
		//Algorithm starts here!
		//Starting with the initial state,
			//add states to a queue
		ArrayList<BFSState> queue = new ArrayList<>();
		queue.add(startState);
		
		BFSState currentState;
		while(queue.size() > 0)
		{
			//dequeue one state
			currentState = queue.remove(0);
			//is it the goal?  If so, we're done!
			if( currentState.row == endRow && currentState.col == endCol)
				break;
			
			//otherwise, build its children, and add them to the queue
			//test all the legal moves
			//for each one that is possible, make a new state 
				//and add it to the queue
			
			//up
			if(currentState.row > 0 && 
					maze[currentState.row-1][currentState.col] != 'x')
			{
				BFSState up = new BFSState(currentState.row-1,
											currentState.col);
				up.parent = currentState;
				queue.add(up);
			}
			
			//down
			if(currentState.row < maze.length-1 && 
					maze[currentState.row+1][currentState.col] != 'x')
			{
				BFSState d = new BFSState(currentState.row+1,
											currentState.col);
				d.parent = currentState;
				queue.add(d);
			}
			
			//left
			if(currentState.col > 0 && 
					maze[currentState.row][currentState.col-1] != 'x')
			{
				BFSState l = new BFSState(currentState.row,
											currentState.col-1);
				l.parent = currentState;
				queue.add(l);
			}
			
			//right
			if(currentState.col < maze.length-1 && 
					maze[currentState.row][currentState.col+1] != 'x')
			{
				BFSState r = new BFSState(currentState.row,
											currentState.col+1);
				r.parent = currentState;
				queue.add(r);
			}
		}

		//construct the path from the goal
		
		
		return path;
	}

	@Override public String getMessage()
	{
		return null;
	}

}
