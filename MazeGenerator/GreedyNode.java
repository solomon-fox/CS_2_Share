
public class GreedyNode
{
	public int row, col, h = 0;
	GreedyNode parent = null;
	
	public boolean equals(Object other)
	{
		if(other instanceof GreedyNode)
			return ((GreedyNode)other).row == row && ((GreedyNode)other).col == col;
		return false;
	}
}
