import java.util.ArrayList;
import java.util.Arrays;

public class AI
{
	char myToken = 'O', oppToken = 'X';
	
	public AI(char me, char opp)
	{
		myToken = me;
		oppToken = opp;
	}
	
	public State chooseMinimax(State st)
	{
		int pickRow, pickCol;
		do
		{
			pickRow = (int)(Math.random() * st.board.length);
			pickCol = (int)(Math.random() * st.board.length);
		}while(st.board[pickRow][pickCol] != '_');
		
		State newState = new State(st);
		newState.board[pickRow][pickCol] = myToken;
		
		return newState;
	}
	
	public ArrayList<State> generateSuccessors(State st)
	{
		ArrayList<State> successors = new ArrayList<>();
		
		
		return successors;
	}
	
	private int maxValue(State st)
	{
		return Integer.MIN_VALUE;
	}
	
	private int minValue(State st)
	{
		return Integer.MAX_VALUE;
	}
	
	public int h(State st)
	{
		//center squares are worth 10 points
		//corners are worth 5 points
		//edges are worth 1 point
		int total = 0, maxIndex = st.board.length-1, spaceValue;
		
		for (int r = 0; r < st.board.length; r++)
			for (int c = 0; c < st.board.length; c++)
			{
				spaceValue = 1;
				if(r == 0 && (c == 0 || c == maxIndex))
					spaceValue = 5;
				if(r > 0 && r < maxIndex && c > 0 && c < maxIndex)
					spaceValue = 10;
				
				if(st.board[r][c] == myToken)
					total += spaceValue;
				else if(st.board[r][c] == oppToken)
					total -= spaceValue;
			}
		
		return total;
	}
	
	public boolean isTerminal(State st)
	{
		return checkGameOver(st); // OR any other conditions, such as depth, time, etc.
	}
	
	public boolean checkGameOver(State st)
	{
		char[][] b = st.board;
		int n = b.length;
		char[] masterRowX = new char[n], masterRowO = new char[n];

		for (int r = 0; r < n; r++)
		{
			masterRowX[r] = 'X';
			masterRowO[r] = 'O';
		}

		// horizontal
		for (int r = 0; r < n; r++)
			if (Arrays.equals(b[r], masterRowX)
					|| Arrays.equals(b[r], masterRowO))
			{
				// System.out.println(b[r][0] + " wins! (horizontal)");
				return true;
			}

		// diagonals
		if (b[0][0] != '_')
		{
			boolean done = true;
			for (int i = 0; i < n - 1; i++)
			{
				if (b[i][i] != b[i + 1][i + 1])
				{
					done = false;
					break;
				}
			}
			if (done)
			{
				// System.out.println(b[0][0]+" wins! (diagonal)");
				return true;
			}
		}

		if (b[n - 1][0] != '_')
		{
			boolean done = true;
			for (int i = 0; i < n - 1; i++)
			{
				if (b[n - 1 - i][i] != b[n - 1 - (i + 1)][i + 1])
				{
					done = false;
					break;
				}
			}
			if (done)
			{
				// System.out.println(b[n-1][0] + " wins! (diagonal)");
				return true;
			}
		}

		// verticals
		for (int c = 0; c < n; c++)
			if (b[0][c] != '_')
			{
				boolean done = true;
				for (int r = 1; r < n; r++)
					if (b[r][c] != b[0][c]) done = false;

				if (done)
				{
					// System.out.println(b[0][c]+" wins! (vertical)");
					return true;
				}
			}

		int count = 0;
		for (int r = 0; r < n; r++)
			for (int c = 0; c < n; c++)
				if (b[r][c] != '_') count++;

		if (count == n * n)
		{
			// System.out.println("It's a draw!");
			return true;
		}

		return false;
	}
}
