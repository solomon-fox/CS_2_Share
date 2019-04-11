import java.util.ArrayList;

public class State
{
	int[][] board;
	State parent = null;

	public State(int n)
	{
		board = new int[n][n];
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i < n*n; i++)
			list.add(i);
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board.length; c++)
				if(list.size() > 0)
					board[r][c] = list.remove((int)(Math.random()*list.size()));
	}

	public State(State other)
	{
		parent = other;
		board = new int[other.board.length][other.board.length];
		for (int r = 0; r < other.board.length; r++)
			for (int c = 0; c < other.board.length; c++)
				board[r][c] = other.board[r][c];
	}
}
