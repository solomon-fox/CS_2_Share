import java.util.Scanner;
import java.util.Arrays;

public class Main_AI
{
	Scanner in = new Scanner(System.in);
	State currentState;
	AI ai;

	public Main_AI()
	{
		initializeBoard();

		/*
		 * Prefilled for testing board[1][0] = 'O'; board[2][0] = 'O'; board[3][0] = 'O'; board[3][1] = 'O'; board[3][2]
		 * = 'O'; board[2][1] = 'O'; board[1][2] = 'O';
		 */

		char humanPlayer = 'X', aiPlayer = 'O', currentPlayer = 'X';
		System.out.print("Human moves first? Y/n: ");
		if (in.nextLine().equalsIgnoreCase("n"))
		{
			humanPlayer = 'O';
			aiPlayer = 'X';
		}
		
		ai = new AI(aiPlayer, humanPlayer);

		while (!ai.checkGameOver(currentState))
		{
			System.out.println(currentPlayer + "'s turn!");
			printBoard(currentState);

			if (currentPlayer == humanPlayer)
			{
				System.out.print("Enter row: ");
				int row = in.nextInt();
				System.out.print("Enter col: ");
				int col = in.nextInt();

				if (row >= 0 && row < currentState.board.length && col >= 0
						&& col < currentState.board.length
						&& currentState.board[row][col] == '_')
				{
					currentState.board[row][col] = currentPlayer;
					if (currentPlayer == 'X') currentPlayer = 'O';
					else currentPlayer = 'X';
				}
				else System.out.println("Invalid move. Try again!");
			}
			else
			{
				currentState = ai.chooseMinimax(currentState);
				if (currentPlayer == 'X') currentPlayer = 'O';
				else currentPlayer = 'X';
				
				System.out.println("Hmmm.... I choose:  h = "+ai.h(currentState));
			}
		}
		
		System.out.println("Game over!  Final State: ");
		printBoard(currentState);
	}

	private void printBoard(State st)
	{
		for (int r = 0; r < st.board.length; r++)
		{
			for (int c = 0; c < st.board[0].length; c++)
				System.out.print(st.board[r][c] + " ");
			System.out.println();
		}
	}

	private void initializeBoard()
	{
//		System.out.print('\u000c');
		System.out.print("Please enter the board size 'n': ");
		int n = in.nextInt();
		in.nextLine();
		currentState = new State(n);
		for (int r = 0; r < currentState.board.length; r++)
			for (int c = 0; c < currentState.board[0].length; c++)
				currentState.board[r][c] = '_';
	}

	public static void main(String[] args)
	{
		new Main_AI();
	}
}