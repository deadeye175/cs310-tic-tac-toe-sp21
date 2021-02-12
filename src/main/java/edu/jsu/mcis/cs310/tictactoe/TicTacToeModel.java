package edu.jsu.mcis.cs310.tictactoe;

/**
* TicTacToeModel implements the Model for the Tic-Tac-Toe game.
*
* @author  Adam Parker
* @version 2.0
*/
public class TicTacToeModel {
    
    /**
     * The contents of the Tic-Tac-Toe game board
     */
    private TicTacToeSquare[][] board;
    
    /**
     * xTurn is true if X is the current player, or false if O is the current
     * player
     */
    private boolean xTurn;
    
    /**
     * The dimension (width and height) of the game board
     */
    private int dimension;

    /**
    * Default Constructor (uses the default dimension)
    */    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_DIMENSION);
        
    }
    
    /**
    * Constructor (uses specified dimension)
    * 
    * @param dimension The <em>dimension</em> (width and height) of the new
    * Tic-Tac-Toe board.
    */
    public TicTacToeModel(int dimension) {
        
        /* Initialize dimension; X goes first */
        
        this.dimension = dimension;
        xTurn = true;
        
        /* Create board as a 2D TicTacToeSquare array */
        
        board = new TicTacToeSquare[dimension][dimension];

        /* Initialize board (fill with TicTacToeSquare.EMPTY) */

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                board[i][j] = TicTacToeSquare.EMPTY;
            }
        }
        
    }
    /**
    * Use isValidSquare(int, int) to check if the specified square is in range,
    * and use isSquareMarked(int, int) to check if the square is currently
    * empty.  If both conditions are satisfied, create a mark in the square for
    * the current player, then toggle xTurn from true to false (or vice-versa)
    * to switch to the other player before returning TRUE.  Otherwise, return
    * FALSE.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value representing the result of the attempt to
    * make this mark: true if the attempt was successful, and false otherwise
    * @see         TicTacToeSquare
    */
    public boolean makeMark(int row, int col) {
        
        boolean valid = isValidSquare(row, col);
        boolean marked = isSquareMarked(row, col);
        if(valid && !marked){
            if(xTurn){
                board[row][col] = TicTacToeSquare.X;
                xTurn = false;
            } else {
                board[row][col] = TicTacToeSquare.O;
                xTurn = true;
            }
            return true;
        } else {
            return false;
        }
        
    }
    
    /**
    * Checks if the specified square is within range (that is, within the bounds
    * of the game board).
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value: true if the square is in range, and false
    * if it is not
    */
    private boolean isValidSquare(int row, int col) {
        
        if(row < dimension && row >= 0){
            if(col < dimension && col >= 0){
                    return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
    * Checks if the specified square is marked.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value: true if the square is marked, and false
    * if it is not
    */
    private boolean isSquareMarked(int row, int col) {
        
        if(!isValidSquare(row, col)){
            return true;
        }
        if(board[row][col] == TicTacToeSquare.EMPTY){
            return false;
        } else {
            return true;
        }
            
    }
    
    /**
    * Returns a {@link TicTacToeSquare} object representing the content of the
    * specified square of the Tic-Tac-Toe board.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      the content of the specified square
    * @see         TicTacToeSquare
    */
    public TicTacToeSquare getSquare(int row, int col) {
        
        if(board[row][col] == TicTacToeSquare.X){
            return TicTacToeSquare.X;
        } else if(board[row][col] == TicTacToeSquare.O){
            return TicTacToeSquare.O;
        } else {
            return TicTacToeSquare.EMPTY;
        }
            
    }
    
    /**
    * Use isMarkWin() to determine if X or O is the winner, if the game is a
    * tie, or if the game is still in progress. Return the current state of the
    * game as a {@link TicTacToeState} object.
    *
    * @return      the current state of the Tic-Tac-Toe game
    * @see         TicTacToeState
    */
    public TicTacToeState getState() {
        
        boolean xwin = isMarkWin(TicTacToeSquare.X);
        boolean owin = isMarkWin(TicTacToeSquare.O);
        boolean tiebetween = isTie();
        if(xwin){
            return TicTacToeState.X;
        } else if(owin){
            return TicTacToeState.O;
        } else if(tiebetween){
            return TicTacToeState.TIE;
        } else {
            return TicTacToeState.NONE;
        }
        
    }
    
    /**
    * Check the squares of the Tic-Tac-Toe board to see if the specified player
    * is the winner.
    *
    * @param  mark  the mark representing the player to be checked (X or O)
    * @return       true if the specified player is the winner, or false if not
    * @see          TicTacToeSquare
    */
    private boolean isMarkWin(TicTacToeSquare mark) {
        
        boolean markwon = false;
        int numofmark = 0;
        for(int i = 0; i < dimension; i++){
            if(board[i][i] == mark){
                numofmark++;
            }
        }
        if(numofmark == dimension){
            markwon = true;
        }
        numofmark = 0;
        int dim = dimension - 1;
        for(int i = 0; i < dimension; i++){
            if(board[dim][i] == mark){
                numofmark++;
            }
            dim--;
        }
        if(numofmark == dimension){
            markwon = true;
        }
        for(int i = 0; i < dimension; i++){
            numofmark = 0;
            for(int j = 0; j < dimension; j++){
                if(board[i][j] == mark){
                    numofmark++;
                }
                if(numofmark == dimension){
                    markwon = true;
                }
            }
        }
        for(int i = 0; i < dimension; i++){
            numofmark = 0;
            for(int j = 0; j < dimension; j++){
                if(board[j][i] == mark){
                    numofmark++;
                }
                if(numofmark == dimension){
                    markwon = true;
                }
            }
        }
        if(markwon){
            return true;
        } else {
            return false;
        }
        
    }
    
    /**
    * Check the squares of the board to see if the Tic-Tac-Toe game is currently
    * in a tie state.
    *
    * @return  true if the game is currently a tie, or false otherwise
    */	
    private boolean isTie() {
        boolean tie = true;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(board[i][j] == TicTacToeSquare.EMPTY){
                    tie = false;
                }
            }
        }
        if(tie){
            return true;
        } else {
            return false;
        }
    }

    /**
    * Uses {@link #getState() getState} to checks if the Tic-Tac-Toe game is
    * currently over, either because a player has won or because the game is
    * in a tie state.
    *
    * @return  true if the game is currently over, or false otherwise
    */	
    public boolean isGameover() {
        
        return TicTacToeState.NONE != getState();
        
    }

    /**
    * Getter for xTurn.
    *
    * @return  true if X is the current player, or false if O is the current
    * player
    */
    public boolean isXTurn() {
        
        return xTurn;
        
    }
    
    /**
    * Getter for dimension.
    *
    * @return  the <em>dimension</em> (width and height) of the Tic-Tac-Toe
    * game board
    */
    public int getDimension() {
        
        return dimension;
        
    }
    
    /**
    * <p>Returns the current content and state of the Tic-Tac-Toe game board as
    * a formatted String.  This method <em>must</em> use a {@link StringBuilder}
    * to compose the output String, which should not include any empty lines.</p>
    * <p>Here is an example of how the output for a 3x3 game board should
    * appear (also see the example output on Canvas):</p>
    * <code>&nbsp;&nbsp;012<br>0&nbsp;O&nbsp;&nbsp;<br>1&nbsp;&nbsp;X&nbsp;<br>2&nbsp;O&nbsp;X</code>
    * @return      the representation of the Tic-Tac-Toe game board
    * @see         StringBuilder
    */
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder();
        
        output.append("  ");
        for(int i = 0; i < dimension; i++){
            output.append(i);
        }
        output.append("\n");
        for(int j = 0; j < dimension; j++){
            output.append(j + " ");
            for(int k = 0; k < dimension; k++){
                output.append(board[j][k]);
            }
            if(j != dimension - 1){
                output.append("\n");
            }
        }
        output.append("\n");
        output.append("\n");
        
        return output.toString();
        
    }
    
}