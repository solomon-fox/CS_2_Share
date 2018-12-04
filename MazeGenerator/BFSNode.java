
public class BFSNode
{
	public int row, col;
	BFSNode parent = null;
	
	public boolean equals(Object other)
	{
		if(other instanceof BFSNode)
			return ((BFSNode)other).row == row && ((BFSNode)other).col == col;
		return false;
	}
}
