public class State
{
	char[][] board;
	State parent = null;
	int h = 0, depth = 0;

	public State(int n)
	{
		board = new char[n][n];
	}

	public State(State other)
	{
		board = new char[other.board.length][other.board.length];
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board.length; c++)
				board[r][c] = other.board[r][c];

		parent = other;
		depth = other.depth + 1;
	}

	public boolean equals(Object other)
	{
		if (other instanceof State)
		{
			for (int r = 0; r < board.length; r++)
				for (int c = 0; c < board.length; c++)
					if (board[r][c] != ((State) other).board[r][c])
						return false;
			return true;
		}
		return false;
	}
}