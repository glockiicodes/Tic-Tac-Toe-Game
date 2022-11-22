import java.util.Scanner;

public class TicTacToeGame {
	
  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {

    System.out.print("Enter the size of the grid: ");
    int userSize = keyboard.nextInt();
    char[][] grid = new char[userSize][userSize]; // dimensions of grid

    emptyGrid(grid); // grid with blank slots
    showGrid(grid);  // display grid

    // ask the user x or o 
    System.out.print("Would you like to be X or O? : ");
    char userSymbol = keyboard.next().toUpperCase().charAt(0);
    char computerSymbol = (userSymbol == 'X') ? 'O' : 'X';

    // initialize first move for player
    boolean startingMove = true;

    int turn;  // track turns
    int remainCount = userSize * userSize; // empty slot count

    // starting moves
    if (startingMove == true) {
      turn = 0;
      userPlayer(grid, userSymbol); // user puts first symbol
      
    }else {
      turn = 1;
      computerPlayer(grid, computerSymbol); // computer puts first symbol
      
    }
    
    // show board, and decrement the count of remaining slots as game goes on
    showGrid(grid);
    remainCount--;

    // run till game ends 
    boolean done = false;
    int winner = -1;   // 0 = user, 1 = computer, -1 = draw

    while (!done && remainCount > 0) {
      //  set winner and the finished flag to true
      done = ifGameOver(grid, turn, userSymbol, computerSymbol); 

      if (done)
        winner = turn; 
      else {
        turn = (turn + 1 ) % 2;

        if (turn == 0)
          userPlayer(grid, userSymbol);
        else
          computerPlayer(grid, computerSymbol);

        // show the board after every move, and decrement the remaining count
        showGrid(grid);
        remainCount--;
      }
    }

    // determine winner
    if (winner == 0)
      System.out.println("You Win!");
    else if (winner == 1)
      System.out.println("You Lose :(");
    else
      System.out.println("It's a draw.");

  }

  public static void emptyGrid(char[][] grid) {
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++)
        grid[i][j] = ' ';
    
  }

  public static void showGrid(char[][] grid) {
	  
    int numberRow = grid.length;
    int numberColumn = grid[0].length;

    System.out.println();
    
    System.out.print("    ");
    for (int i = 0; i < numberColumn; i++)
      System.out.print(i + "   ");
    System.out.print('\n');
     
    // board
    for (int i = 0; i < numberRow; i++) {
    	System.out.print(i + "  ");
      
    	for (int j = 0; j < numberColumn; j++) {
    		if (j != 0)
    			System.out.print("|");
    		System.out.print(" " + grid[i][j] + " ");
        
      }

      System.out.println();

      if (i != (numberRow - 1)) {
        System.out.print("   ");
        for (int j = 0; j < numberColumn; j++) {
          if (j != 0)
            System.out.print("+");
          System.out.print("---");
        }
        
        System.out.println();
        
      }
    }
    
    System.out.println();
    
  }

  public static void userPlayer(char[][] grid, char userSymbol) {
	
    System.out.println("Enter row and column choice. " + "(First enter column top to bottom,  " 
    												   + "then row left to right) : ");
    int rowIndex = keyboard.nextInt();
    int columnIndex = keyboard.nextInt();

    while (grid[rowIndex][columnIndex] != ' ') {
      System.out.print("Slot taken, enter new row and column: ");
      rowIndex = keyboard.nextInt();
      columnIndex = keyboard.nextInt();
      System.out.print("Your move:");
    }

    grid[rowIndex][columnIndex] = userSymbol;
    System.out.println();
    System.out.print("Your move:");
    
  }

  public static void computerPlayer(char[][] grid, char computer) {
    // find random and slot
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == ' ') { // empty slot
          grid[i][j] = computer;
          System.out.print("Computers move:");
          return;
          
        }
      }
    }
  }

  public static boolean ifGameOver(char[][] grid, int turn, char userSymbol, char computerSymbol) {
    char symbol;
    if (turn == 0)
      symbol = userSymbol;
    else
      symbol = computerSymbol;

    int i, j;
    boolean win = false;

    // check win by row
    for (i = 0; i < grid.length && !win; i++) {
      for (j = 0; j < grid[0].length; j++) {
        if (grid[i][j] != symbol)
          break;
      }
      
      if (j == grid[0].length)
        win = true;
    }

    // check win by column
    for (j = 0; j < grid[0].length && !win; j++) {
      for (i = 0; i < grid.length; i++) {
        if (grid[i][j] != symbol)
          break;
      }
      
      if (i == grid.length)
        win = true;
      
    }

    // check win by diagonals
    if (!win) {
      for (i = 0; i < grid.length; i++) {
        if (grid[i][i] != symbol)
          break;
      }
      
      if (i == grid.length)
        win = true;
      
    }

    if (!win) {
      for (i = 0; i < grid.length; i++) {
        if (grid[i][grid.length - 1 - i] != symbol)
          break;
        
      }
      
      if (i == grid.length)
        win = true;
    }

    return win;
    
  }
}