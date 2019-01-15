import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextField;

public class AI
{
	char myToken, oppToken;

	public AI(char token, char opToken)
	{
		myToken = token;
		oppToken = opToken;
	}

	// Choose a move NOT randomly
	// Minimax! minValue and maxValue
	// heuristic function - account for the opponent as well
	// generate successors

	// Method for the AI choosing a move.
	// Initially a random agent.
	public State chooseMove(State st)
	{
		State newState = new State(st);

		// random agent
		Point source = new Point(), destination = new Point();
		HashMap<Point, ArrayList<Point>> jumps = listJumps(newState);

		do
		{
			do
			{
				if (jumps.size() > 0)
				{
					Object[] keys = jumps.keySet().toArray();
					source = (Point) keys[(int) (Math.random() * keys.length)];

					destination = jumps.get(source).get(
							(int) (Math.random() * jumps.get(source).size()));
				}
				else
				{
					source = translateLocationFromInt(
							(int) (Math.random() * 32 + 1));
					destination = translateLocationFromInt(
							(int) (Math.random() * 32 + 1));
				}
			} while (isLegalMove(newState, jumps, source, destination) != 0); // pick a random, legal move

			makeMove(newState, jumps, source, destination);
		} while (jumps.containsKey(destination)); // for multi jumps

		newState.whoseMove = newState.whoseMove == 'b' ? 'r' : 'b'; // switch to the next player

		return newState;
	}

	// Takes human String input in the form "10-19-26", validates, then executes the move.
	public State processMove(State st, String moveText, JTextField textField)
	{
		String[] moves = moveText.split("-");
		Point source = translateLocationFromString(moves[0]);
		State newState = new State(st);
		HashMap<Point, ArrayList<Point>> jumps = listJumps(newState);

		for (int i = 1; i < moves.length; i++)
		{
			Point destination = translateLocationFromString(moves[i]);

			// System.out.println(jumps);
			int test = isLegalMove(newState, jumps, source, destination);
			if (test == 0)
			{
				makeMove(newState, jumps, source, destination);
			}
			else
			{
				textField.setText("Move rejected for reason " + test + "!");
				textField.setSelectionStart(0);
				textField.setSelectionEnd(textField.getText().length());
				return st;
			}
			source = destination;
		}
		newState.whoseMove = newState.whoseMove == 'b' ? 'r' : 'b';

		return newState;
	}

	// Complete the move on specified State, update the jumps map.
	private void makeMove(State newState,
			HashMap<Point, ArrayList<Point>> jumps, Point source,
			Point destination)
	{
		newState.board[destination.x][destination.y] = newState.board[source.x][source.y];
		newState.board[source.x][source.y] = 0;

		if (jumps.get(source) != null
				&& jumps.get(source).contains(destination))
		{
			newState.board[(source.x + destination.x)
					/ 2][(source.y + destination.y) / 2] = 0;
			jumps = listJumps(newState);
		}

		// promote kings
		for (int c = 0; c < newState.board.length; c++)
		{
			if (newState.board[0][c] == 'b') newState.board[0][c] = 'B';
			if (newState.board[7][c] == 'r') newState.board[7][c] = 'R';
		}

	}

	// Checks to see if a specific move is not legal, returns 0 if it is legal, reason number otherwise.
	public int isLegalMove(State st, HashMap<Point, ArrayList<Point>> jumps,
			Point source, Point destination)
	{
		// same place?
		if (source.equals(destination)) return 1;

		// not your token?
		if (isEnemy(st, source.x, source.y) || isEmpty(st, source.x, source.y))
			return 2;

		// wrong direction?
		if (st.board[source.x][source.y] == 'b')
		{
			if (destination.x > source.x) return 3;
		}

		if (st.board[source.x][source.y] == 'r')
		{
			if (destination.x < source.x) return 4;
		}

		// destination isn't empty?
		if (st.board[destination.x][destination.y] != 0) return 5;

		// enforce jump priority
		if (jumps == null) jumps = listJumps(st);
		if (jumps.size() > 0 && // any jumps?
				(!jumps.containsKey(source) // is the source in the collection of jumps?
						|| !jumps.get(source).contains(destination))) // is the destination found under the key?
			return 6;

		// make sure difference is 1, if there aren't any jumps
		if (jumps.size() == 0 && (Math.abs(source.x - destination.x) > 1
				|| Math.abs(source.y - destination.y) > 1))
			return 7;

		return 0;
	}

	// Checks whether the specified location on the board is both in bounds and empty.
	public boolean isEmpty(State st, int r, int c)
	{
		return r >= 0 && r < st.board.length && c >= 0 && c < st.board.length
				&& st.board[r][c] == 0;
	}

	// Checks to see if the specified location is an opponent's piece, based on whose turn it is.
	public boolean isEnemy(State st, int dr, int dc)
	{
		if (!(dr >= 0 && dr < st.board.length && dc >= 0
				&& dc < st.board.length))
			return false;
		if (st.whoseMove == 'b')
			return st.board[dr][dc] == 'r' || st.board[dr][dc] == 'R';
		else return st.board[dr][dc] == 'b' || st.board[dr][dc] == 'B';
	}

	// Checks to see if another square is an enemy to the one at [r][c]
	public boolean isEnemy(State st, int r, int c, int dr, int dc)
	{
		if (!(dr >= 0 && dr < st.board.length && dc >= 0
				&& dc < st.board.length))
			return false;
		if (st.board[r][c] == 'b')
			return st.board[dr][dc] == 'r' || st.board[dr][dc] == 'R';
		else return st.board[dr][dc] == 'b' || st.board[dr][dc] == 'B';
	}

	// Produces a Map of all the legal jumps.
	public HashMap<Point, ArrayList<Point>> listJumps(State st)
	{
		HashMap<Point, ArrayList<Point>> jumps = new HashMap<>();
		for (int r = 0; r < st.board.length; r++)
		{
			for (int c = 0; c < st.board.length; c++)
			{
				if ((st.whoseMove == 'b'
						&& (st.board[r][c] == 'b' || st.board[r][c] == 'B'))
						|| (st.whoseMove == 'r' && st.board[r][c] == 'R'))
				{
					// look ahead one row and one column to either side
					if (isEnemy(st, r - 1, c - 1) && isEmpty(st, r - 2, c - 2))
					{
						Point p = new Point(r, c);
						jumps.put(p, new ArrayList<Point>());
						jumps.get(p).add(new Point(r - 2, c - 2));
					}
					if (isEnemy(st, r - 1, c + 1) && isEmpty(st, r - 2, c + 2))
					{
						Point p = new Point(r, c);
						jumps.put(p, new ArrayList<Point>());
						jumps.get(p).add(new Point(r - 2, c + 2));
					}
				}
				if ((st.whoseMove == 'r'
						&& (st.board[r][c] == 'r' || st.board[r][c] == 'R'))
						|| (st.whoseMove == 'b' && st.board[r][c] == 'B'))
				{
					if (isEnemy(st, r + 1, c - 1) && isEmpty(st, r + 2, c - 2))
					{
						Point p = new Point(r, c);
						jumps.put(p, new ArrayList<Point>());
						jumps.get(p).add(new Point(r + 2, c - 2));
					}
					if (isEnemy(st, r + 1, c + 1) && isEmpty(st, r + 2, c + 2))
					{
						Point p = new Point(r, c);
						jumps.put(p, new ArrayList<Point>());
						jumps.get(p).add(new Point(r + 2, c + 2));
					}
				}
			}
		}
		return jumps;
	}

	// Given a Point, convert it back to Checkers coordinates.
	public int translateLocationFromPoint(Point p)
	{
		int c = p.x % 2 == 0 ? p.y : p.y + 1;

		return (p.x * 4) + (c - (c / 2));
	}

	// Given a String, convert it to a Point, where Point.x == row, Point.y == column.
	public Point translateLocationFromString(String squareID)
	{
		return translateLocationFromInt(Integer.parseInt(squareID));
	}

	// Given an integer, convert it to a Point, where Point.x == row, Point.y == column.
	public Point translateLocationFromInt(int id)
	{
		int row = (id - 1) / 4;

		int col = 0;
		if (row % 2 == 0)
		{
			int cid = id % 8;
			col = cid + (cid - 1);
		}
		else
		{
			int cid = (id - 1) % 4;
			col = cid * 2;
		}

		return new Point(row, col);
	}

	// Game is over if there are no pieces left for one side.
	public boolean isGameOver(State st)
	{
		int rs = 0, bs = 0;
		for (int r = 0; r < st.board.length; r++)
			for (int c = 0; c < st.board.length; c++)
			{
				if (st.board[r][c] == 'b' || st.board[r][c] == 'B') bs++;
				if (st.board[r][c] == 'r' || st.board[r][c] == 'R') rs++;
			}

		return rs == 0 || bs == 0;
	}
}
