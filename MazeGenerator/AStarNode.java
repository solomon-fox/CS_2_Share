
public class AStarNode implements Comparable
{
	public int row = 0, col = 0, cost = 0, h = 0;
	public AStarNode parent = null;
	
	public AStarNode()
	{
		
	}

	public AStarNode(int r, int c)
	{
		row = r;
		col = c;
	}

	public AStarNode(AStarNode p)
	{
		this(p.row, p.col);
		cost = p.cost + 1;
		parent = p;
	}

	public AStarNode(AStarNode p, int r, int c)
	{
		this(r, c);
		cost = p.cost + 1;
		parent = p;
	}

	public boolean equals(Object other)
	{
		if (other instanceof AStarNode) return row == ((AStarNode) other).row
				&& col == ((AStarNode) other).col;
		return false;
	}

	public int compareTo(Object other)
	{
		if (other instanceof AStarNode) 
			return (cost - ((AStarNode) other).cost)
				+ (h - ((AStarNode) other).h);
		return 0;
	}
}
