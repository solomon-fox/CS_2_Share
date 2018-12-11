import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AStar implements NavigatorSolver
{

	@Override public ArrayList<Point> generateSolution(char[][] maze,
			Point start, Point end)
	{
		
		int startRow = start.y;
		int startCol = start.x;
		int endRow = end.y;
		int endCol = end.x;
		
		//data structure - Queue
		ArrayList<AStarNode> queue = new ArrayList<>();
		
		//closed list - places we've visited
		ArrayList<AStarNode> closedList = new ArrayList<>();
		
		//add the initial Node
		AStarNode temp = new AStarNode();
		temp.row = startRow;
		temp.col = startCol;
		queue.add(temp);
		
		//as long as there are things in the Queue
		while(queue.size() > 0)
		{
			//pull out the next node
			//sort first
			Collections.sort(queue);
			AStarNode node = queue.remove(0);  //now it's a BFS
			
			//see if this node is in the closed list, if so we should not consider it
			if(closedList.contains(node))	//contains uses the equals() method in AStarNode
				continue;	//skip ahead to the next iteration of the loop
			
			//if it's not, add it to the closed list
			closedList.add(node);
			
			//see if it's goal
			if(node.row == endRow && node.col == endCol)
			{
				//if so, build a list of points to get here
				ArrayList<Point> points = new ArrayList<>();
				while(node != null)
				{
					points.add(new Point(node.col, node.row));
					node = node.parent;
				}
				//and we're done
				return points;
			}
			
			//otherwise, add this node's successors to the Queue
			//up, down, left, right
			if(node.row-1 >= 0 && maze[node.row-1][node.col] != 'x')
			{
				AStarNode t = new AStarNode();
				t.row = node.row-1;
				t.col = node.col;
				t.cost = node.cost + 1;
				t.h = Math.abs((t.row - endRow) + (t.col - endCol));
				t.parent = node;
				queue.add(t);
			}
			if(node.row+1 < maze.length && maze[node.row+1][node.col] != 'x')
			{
				AStarNode t = new AStarNode();
				t.row = node.row+1;
				t.col = node.col;
				t.cost = node.cost + 1;
				t.h = Math.abs((t.row - endRow) + (t.col - endCol));
				t.parent = node;
				queue.add(t);
			}
			if(node.col-1 >= 0 && maze[node.row][node.col-1] != 'x')
			{
				AStarNode t = new AStarNode();
				t.row = node.row;
				t.col = node.col-1;
				t.cost = node.cost + 1;
				t.h = Math.abs((t.row - endRow) + (t.col - endCol));
				t.parent = node;
				queue.add(t);
			}
			if(node.col+1 < maze[0].length && maze[node.row][node.col+1] != 'x')
			{
				AStarNode t = new AStarNode();
				t.row = node.row;
				t.col = node.col+1;
				t.cost = node.cost + 1;
				t.h = Math.abs((t.row - endRow) + (t.col - endCol));
				t.parent = node;
				queue.add(t);
			}
		}
		
		return null;
	}

	@Override public String getMessage()
	{
		return null;
	}
}
