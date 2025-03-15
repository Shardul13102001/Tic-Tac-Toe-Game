import java.util.Scanner;

    class Mygame {

	 //create 2D char array
	static char[][] board;
	
	//Create constructor to automatic initialize and assign space to board
	public Mygame() 
	{
		board=new char[3][3];
		Assignboard();
		
	}
	
	//create method to assign space in board
	void Assignboard() {
		for(int row=0;row<board.length;row++) {
			for(int col=0;col<board[row].length;col++)
			{
				board[row][col]=' ';
			}
		}
	}
	
	//Create method to display board
	static void Displayboard() {
		System.out.println("-------------");
		for(int row=0;row<board.length;row++) {
			System.out.print("| ");
			for(int col=0;col<board[row].length;col++) {
				System.out.print(board[row][col]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	//create method to show mark in board
	static void Displaymark(int row,int col,char mark) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			board[row][col]=mark;
		}
		else
		{
			System.out.println("Invalide position");
		}
	}
	
	//create win condition based on column
	static boolean checkcolwin() 
	{
		for(int col=0;col<=2;col++) 
		{
			if(board[0][col]!=' ' && board[0][col]==board[1][col] && board[1][col]==board[2][col]) 
			{
				return true;
			}
		}
		return false;
	}
	
	//create win condition based on row
	static boolean checkrowwin() 
	{
		for(int row=0;row<=2;row++) 
		{
			if(board[row][0]!=' ' && board[row][0]==board[row][1] && board[row][1]==board[row][2]) 
			{
				return true;
			}
		}
		return false;
	}
	
	//create win condition based on diagonal
		static boolean checkdigwin() {
			if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0])
			{
				return true;
			}
			return false;
		}	
}	
			
		
	//create separate class for players and there moves
	class HumanPlayer{
		String name;
		char mark;
		
		HumanPlayer(String name, char mark)
		{
			this.name=name;
			this.mark=mark;
		}
		
		void move() {
	        Scanner sc=new Scanner(System.in);
	        int row,col;
	        do
	        {
	        	System.out.println("Please enter row and column");
	        	row=sc.nextInt();
	        	col=sc.nextInt();
	        }while(!isvalidmove(row,col));
	        Mygame.Displaymark(row, col, mark);
	        
		}
		
		boolean isvalidmove(int row, int col) {
			if(row>=0 && row<=2 && col>=0 && col<=2)
			{
				if(Mygame.board[row][col]==' ')
				{
					return true;
				}
			}return false;
		}
			
	}
	
	public class LaunchGame {
	    public static void main(String[] args) {
	        Mygame b = new Mygame();

	        HumanPlayer p1 = new HumanPlayer("Player 1", 'X');
	        HumanPlayer p2 = new HumanPlayer("Player 2", 'O');

	        HumanPlayer cp = p1;

	        while (true) {
	            System.out.println(cp.name + ": Your TURN");
	            cp.move();
	            Mygame.Displayboard();

	            // Check for a winner
	            if (Mygame.checkcolwin() || Mygame.checkrowwin() || Mygame.checkdigwin()) {
	                System.out.println(cp.name + " won! Congratulations");
	                break;
	            }

	            // Check for a draw
	            if (isBoardFull()) {
	                System.out.println("Game is a draw!");
	                break;
	            }

	            // Switch player turn
	            cp = (cp == p1) ? p2 : p1;
	        }
	    }

	    // Method to check if the board is full
	    public static boolean isBoardFull() {
	        for (int row = 0; row < Mygame.board.length; row++) {
	            for (int col = 0; col < Mygame.board[row].length; col++) {
	                if (Mygame.board[row][col] == ' ') {
	                    return false;  // Found an empty space, game continues
	                }
	            }
	        }
	        return true;  // No empty spaces left, it's a draw
	    }
	}
