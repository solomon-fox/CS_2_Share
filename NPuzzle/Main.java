import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	Scanner input = new Scanner(System.in);
	final int SIDE_SIZE = 4;
	ArrayList<State> closedList = new ArrayList<>();
	State goal1 = new State(SIDE_SIZE);// , goal2 = new State(SIDE_SIZE);

	public Main(String fileName)
	{
		State current = null;
		if(fileName == null)
			current = new State(SIDE_SIZE);
		else
			current = loadStateFromFile(fileName);
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < SIDE_SIZE * SIDE_SIZE; i++)
			list.add(i);
		for (int r = 0; r < SIDE_SIZE; r++)
			for (int c = 0; c < SIDE_SIZE; c++)
				if (list.size() > 0) goal1.board[r][c] = list.remove(0);

		System.out.println("Starting State h:" + h(current));
		printState(current);
		
		State finalState = solve(current);
		System.out.println("Solution complete!  Press Enter to scroll through the moves:");
		ArrayList<State> path = new ArrayList<>();
		while(finalState != null)
		{
			path.add(finalState);
			finalState = finalState.parent;
		}
		
		for(int i=path.size()-1; i>=0; i--)
		{
			System.out.println("Moves to completion: "+i+"  h: "+h(path.get(i)));
			printState(path.get(i));
			System.out.println("Enter to continue...");
			input.nextLine();
		}
	}
	
	//Returns the goal state.
	private State solve(State startingState)
	{
		return null;
	}
	
	//Heuristic.
	private int h(State st)
	{		
		return 0;
	}
	
	//Successors.
	private ArrayList<State> generateSuccessors(State st)
	{
		ArrayList<State> successors = new ArrayList<>();
		
		return successors;
	}
	
	private boolean isGoal(State st)
	{
		return h(st) == 0;
	}
	
	
	private State loadStateFromFile(String fileName)
	{
		State state = null;
		try
		{
			Scanner in = new Scanner(new File(fileName));
			String s = "";
			while(in.hasNext())
			{
				s += in.nextLine() + ":";
			}
			String[] lines = s.split(":");
			state = new State(lines.length);
			for(int r=0; r<lines.length; r++)
			{
				String[] tokens = lines[r].split(" ");
				for(int c=0; c< tokens.length; c++)
					state.board[r][c] = Integer.parseInt(tokens[c]);
			}
		}
		catch (Exception e) {e.printStackTrace();}
		
		return state;
	}

	private void printState(State st)
	{
		if(st == null)
			return;
		String s = "";
		for (int r = 0; r < st.board.length; r++)
		{
			for (int c = 0; c < st.board.length; c++)
				s += st.board[r][c] + "\t";
			s += "\n";
		}
		System.out.println(s);
	}
	
	public static void main(String[] args)
	{
		//new Main(null); //to make a random board
		new Main("4x4_reversed.txt");	//to load the specified file
	}
}