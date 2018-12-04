import java.awt.Point;
import java.util.ArrayList;

public class BFS implements NavigatorSolver
{

	@Override public ArrayList<Point> generateSolution(char[][] maze,
			Point start, Point end)
	{
		
		int startRow = start.y;
		int startCol = start.x;
		int endRow = end.y;
		int endCol = end.x;
		
		//data structure - Queue
		ArrayList<BFSNode> queue = new ArrayList<>();
		
		//closed list - places we've visited
		ArrayList<BFSNode> closedList = new ArrayList<>();
		
		//add the initial Node
		BFSNode temp = new BFSNode();
		temp.row = startRow;
		temp.col = startCol;
		queue.add(temp);
		
		//as long as there are things in the Queue
		while(queue.size() > 0)
		{
			//pull out the next node
			BFSNode node = queue.remove(0);
			
			//see if this node is in the closed list
			if(closedList.contains(node))	//contains uses the equals() method in BFSNode
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
				BFSNode t = new BFSNode();
				t.row = node.row-1;
				t.col = node.col;
				t.parent = node;
				queue.add(t);
			}
			if(node.row+1 < maze.length && maze[node.row+1][node.col] != 'x')
			{
				BFSNode t = new BFSNode();
				t.row = node.row+1;
				t.col = node.col;
				t.parent = node;
				queue.add(t);
			}
			if(node.col-1 >= 0 && maze[node.row][node.col-1] != 'x')
			{
				BFSNode t = new BFSNode();
				t.row = node.row;
				t.col = node.col-1;
				t.parent = node;
				queue.add(t);
			}
			if(node.col+1 < maze[0].length && maze[node.row][node.col+1] != 'x')
			{
				BFSNode t = new BFSNode();
				t.row = node.row;
				t.col = node.col+1;
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
