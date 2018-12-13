import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	Scanner input = new Scanner(System.in);
	final int SIDE_SIZE = 4;
	ArrayList<State> closedList = new ArrayList<>();
	State goal1 = new State(SIDE_SIZE);// , goal2 = new State(SIDE_SIZE);

	public Main()
	{
		State current = new State(SIDE_SIZE);
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
	
	private State solve(State startingState)
	{
		return null;
	}
	
	private int h(State st)
	{		
		return 0;
	}
	
	private ArrayList<State> generateSuccessors(State st)
	{
		ArrayList<State> successors = new ArrayList<>();
		
		return successors;
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
		new Main();
	}
}