import java.util.*;

/**
 * Author: Sharon Zheng
 * Date: 01/25/16
 * Description: This program runs a Tic Tac Toe game in the terminal that allows
 *              the users to specify 2 different modes within the 2 players. 
 *              It generates a board for the game and detects a winner when 
 *              either player has placed 3 checkers in a row or if it is a tie.
 * 
 */

public class TicTacToe
{
  /** A 2D Array board to store characters */
  private char [][] board;
  
  /** An integer storing the number of rows on the game board */
  private int height;
  
  /** An integer storing the number of rows on the game board */
  private int width;

  /** An integer that determined each player's mode */
  private int player1 = 0;
  private int player2 = 0;
  
  /** The default constructor creates a new 2D board that is 3x3. Then, it will store
    * a blank space in each slot.
    */
  public TicTacToe()
  {
    this.height = 3;
    this.width = 3;
    this.board = new char[height][width];
    
    for(int row = 0; row<height; row++){
      for(int column = 0; column<width; column++){
        board[row][column] = ' ';
      }
    }
  }
  
  /** Method to set and return the default board
    * @return toReturn The method returns a string that 
    * displays the board.
    */
  public String toString()
  {
    String toReturn = new String();
    
    // Nested loop to create the board with blank spaces
    for( int row = 0; row<this.height; row++){
      for(int column = 0; column<this.width; column++){
        toReturn += "|" + board[row][column];
      }
      toReturn += "|\n";
    }
    return toReturn;
  }

  /** Method print the positions of the default board
    * @return display The method returns a string that 
    * displays position on the board.
    */
  public String boardPosition()
  {
    String display = new String();
    int num = 0;
    
    // Nested loop to create the board with blank spaces
    for( int row = 0; row<this.height; row++)
    {
      for(int column = 0; column<this.width; column++)
      {
        display += "|" + num;
        num++;
      }
      display += "|\n";
    }
    return display;
  }
  
  /** Method to place the checkers on the 2D board
    * @param position The position to insert the checker
    * @param checker The letter to place in the position
    */
  public void addMove( int position, char checker)
  {
    int row = position / 3;
    int col = position % 3;
    
    if(board[row][col]==' '){
       board[row][col] = checker;
    }
  }
  
  /** Checks to see if the input position is valid
    * @param position The position of the array that is being checked
    * @return true if there is an empty space for the 
    * checker to be placed in that position and false if there
    * is not
    */
  public boolean allowsMove( int position )
  {
    int row = position / 3;
    int col = position % 3;
    if(position>=0 && position<9){
      if(board[row][col]==' '){
        return true;
      }
    }
    return false;
  }
  
  /** Checks to see if the board is filled
    * @return false if there is an empty space and
    * true if there is no space.
    */
  public boolean isFull()
  {
    for( int row = 0; row<this.height; row++){
      for(int column = 0; column<this.width; column++){
        if(board[row][column] == ' '){
          return false;
        }
      }
    }
    return true;
  }
  
  /** Checks if the checker has three in a row either
    * horizontal, vertical, or diagonal in the game board
    * @param checker The character that is being checked for
    * three in a row
    * @return true if there is any occurrence of three in a
    * row and false if there is not
    */
  public boolean winsFor( char checker )
  {
    // Checks for horizontal wins
    for ( int row = 0; row < height; row++ )
    {
      for (int col = 0; col < width-2; col++ )
      {
        if ( board[row][col] == checker &&
            board[row][col+1] == checker && 
            board[row][col+2] == checker )
          return true;
      }
    }
    
    //Checks for vertical wins
    for( int col = 0; col < width; col++)
    {
      for( int row = 0; row < height-2; row++)
      {
        if( board[row][col] == checker &&
           board[row+1][col] == checker && 
           board[row+2][col] == checker )
          return true;
      }
    }
    
    //Checks for diagonal wins that ascends
    for( int col = 0; col < width-2; col++)
    {
      int row = height-1;
      if( board[row][col] == checker &&
        board[row-1][col+1] == checker && 
        board[row-2][col+2] == checker )
        return true;
    }
    
    //Checks for diagonal wins that descends
    for( int col = width-1; col >= 2; col--)
    {
      int row = height-1;
      if( board[row][col] == checker &&
         board[row-1][col-1] == checker && 
         board[row-2][col-2] == checker )
        return true;
    }
    
    return false;
  }
  
  /** Method to host the TicTacToe game by alternating
    * players to input their move and checking to see if either
    * has won or both tied. 
    */
  public void hostGame()
  {
    System.out.println("Welcome to Tic Tac Toe! \n");
    System.out.println("Mode 0: Human Player");
    System.out.println("Mode 1: Computer");
    
    //sets the mode of the players
    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    System.out.println("Please Enter mode for Player 1 (X): (0 or 1) ");
    player1 = input.nextInt();

    while ( player1 != 0 && player1 != 1 ){
        System.out.println( "Not a valid mode. Please Enter in 0 or 1." );
        player1 = input.nextInt();
      }

    System.out.println("Please Enter mode for Player 2 (O): (0 or 1)");
    player2 = input.nextInt();
    while ( player2 != 0 && player2 != 1 ){
        System.out.println( "Not a valid mode. Please Enter in 0 or 1." );
        player2 = input.nextInt();
      }
    System.out.println();
    
    int inputPos=0;

    //displays the board positions
    System.out.println("Board Position:");
    System.out.println(this.boardPosition());
    
    //Checks to see if the inputPos is valid and
    //will continuously loop until either the one player wins or ties
    while(inputPos>=0 && inputPos<9){
      
      //Player X inserts the position number
      System.out.println("Player 1 (X) position:");
      if(player1 == 0)
        inputPos = input.nextInt();
      else{

        //Randomly generates a number if the mode is set as Computer
        int randomNum = rand.nextInt(9);

        //Checks to see if the random number is valid or not
        while( !allowsMove(randomNum))
          randomNum = rand.nextInt(9);
        
        inputPos = randomNum;
        System.out.println("Computer: "+ randomNum);
      }
      
      //Checks to make sure that the position is valid if not
      //it will prompt the user to insert a different number that is valid
      //thus making sure the outer while loop will always continue
      while ( !allowsMove( inputPos ) ){
        System.out.println( "Position invalid, Player 1's (X) choice again: " );
        inputPos = input.nextInt();
      }
      
      //Places X into the specified position
      if(allowsMove(inputPos)){
        this.addMove(inputPos, 'X');
        if(this.winsFor('X')){         //checks to see if X has won after the insertion
          System.out.println("Player 1 (X) wins! ");
          System.out.println(this);
          break;
        }
        else if(this.isFull()){       //checks to see if X has tied after the insertion
          System.out.println("It's a tie! ");
          System.out.println(this);
          break;
        }
        System.out.println(this);
      }
      
      
     
      //Player O inserts its position number
      System.out.println("Player 2 (O) positon:");
      if(player2 == 0)
        inputPos = input.nextInt();
      else{

        //Randomly generates a number if the mode is set as Computer
        int randomNum = rand.nextInt(9);

        //Checks to see if the random number is valid or not
        while( !allowsMove(randomNum))
          randomNum = rand.nextInt(9);

        inputPos = randomNum;
        System.out.println("Computer: "+ randomNum);
      }
      
      //Checks to make sure that the position is valid if not
      //it will prompt the user to insert a different number that is valid
      //thus making sure the outer while loop will always continue
      while ( !allowsMove( inputPos ) ){
        System.out.println( "Position invalid, Player 2's (O) choice again: " );
        inputPos = input.nextInt();
      }
      
      //Places O into the specified position
      if(allowsMove(inputPos)){
        this.addMove(inputPos, 'O');
        if(this.winsFor('O')){         //checks to see if O has won after the insertion
          System.out.println("Player 2 (O) wins! ");
          System.out.println(this);    
          break;
        }
        else if(this.isFull()){
          System.out.println("It's a tie! ");
          System.out.println(this);    //checks to see if O has tied after the insertion
          break;
        }
        System.out.println(this);
      }
      
    }

  }

  
  public static void main( String[] args)
  {
    TicTacToe b = new TicTacToe();
    b.hostGame();
  }
  
}