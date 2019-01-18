import java.util.ArrayList;
import java.util.Arrays;

public class AI
{
	char myToken = 'O', oppToken = 'X';
	long startTime = 0, MAX_RUNNING_TIME = 30000, MAX_DEPTH = 8;

	public AI(char me, char opp)
	{
		myToken = me;
		oppToken = opp;
	}

	public State chooseMinimax(State st)
	{
		/*
		 * int pickRow, pickCol; do { pickRow = (int)(Math.random() * st.board.length); pickCol = (int)(Math.random() *
		 * st.board.length); }while(st.board[pickRow][pickCol] != '_');
		 * 
		 * State newState = new State(st); newState.board[pickRow][pickCol] = myToken;
		 */
//		int bestMoveHeuristic = maxValue(st);
		startTime = System.currentTimeMillis();
		State bestMove = maxValue(st);

		return bestMove;
	}

	public ArrayList<State> generateSuccessors(State st)
	{
		ArrayList<State> successors = new ArrayList<>();

		//count the number of xs and os to see whose turn it is
		int xs = 0, os = 0;
		for (int r = 0; r < st.board.length; r++)
			for (int c = 0; c < st.board.length; c++)
			{
				if(st.board[r][c] == 'X')
					xs++;
				if(st.board[r][c] == 'O')
					os++;
			}
		
		char token = 'X';
		if(xs > os)
			token = 'O';
		
		for (int r = 0; r < st.board.length; r++)
			for (int c = 0; c < st.board.length; c++)
			{
				if(st.board[r][c] == '_')
				{
					State newState = new State(st);
					newState.board[r][c] = token;
					successors.add(newState);
				}
			}
		
		return successors;
	}

	private State maxValue(State st)
	{
		if (isTerminal(st)) // "terminal" == game over, timed out, deep enough
			return st;

		//tracking the "best" choice so far
		int v = Integer.MIN_VALUE;
		State bestState = null;
		//compare v to each child's value
		for(State child : generateSuccessors(st))
		{
			child.h = h(child);
			v = Math.max(v, minValue(child).h);
			if(bestState == null || child.h == v)
				bestState = child;
		}

		st.h = v;
		return bestState;
	}

	private State minValue(State st)
	{
		if (isTerminal(st)) return st;

		int v = Integer.MAX_VALUE;
		State bestState = null;
		for(State child : generateSuccessors(st))
		{
			child.h = h(child);
			v = Math.min(v, maxValue(child).h);
			if(bestState == null || child.h == v)
				bestState = child;
		}

		st.h = v;
		return bestState;
	}
	
	

	public int h(State st)
	{
		// center squares are worth 10 points
		// corners are worth 5 points
		// edges are worth 1 point
		int total = 0, maxIndex = st.board.length - 1, spaceValue;

		for (int r = 0; r < st.board.length; r++)
			for (int c = 0; c < st.board.length; c++)
			{
				spaceValue = 1;
				if (r == 0 && (c == 0 || c == maxIndex)) spaceValue = 5;
				if (r > 0 && r < maxIndex && c > 0 && c < maxIndex)
					spaceValue = 10;

				if (st.board[r][c] == myToken) total += spaceValue;
				else if (st.board[r][c] == oppToken) total -= spaceValue;
			}

		return total;
	}

	//when should we terminate Minimax
	public boolean isTerminal(State st)
	{
		return ( System.currentTimeMillis() - startTime > MAX_RUNNING_TIME ||
				st.depth >= MAX_DEPTH ||
				checkGameOver(st)); // OR any other conditions, such as depth, time, etc.
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
