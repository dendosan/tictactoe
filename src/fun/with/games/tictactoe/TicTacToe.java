package fun.with.games.tictactoe;

public class TicTacToe {

   protected char[] board;
   protected char userMarker;
   protected char aiMarker;
   protected char winner;
   protected char currentMarker;
   
   public TicTacToe(char playerToken, char aiMarker) {
      this.userMarker = playerToken;
      this.aiMarker = aiMarker;
      this.winner = '-';
      this.board = setBoard();
      this.currentMarker = this.userMarker;
   }

   public static char[] setBoard() {
      char[] board = new char[9];
      for( int i = 0; i < 9; i++ ) {
         board[i] = '-';
      }
      return board;
   }
   
   public boolean playTurn(int spot) {
      boolean isValid = withinRange(spot) && ! isSpotTaken(spot);
      if ( isValid ) {
         this.board[spot-1] = currentMarker;
         this.currentMarker = (this.currentMarker == this.userMarker ) ? this.aiMarker : this.userMarker;
      }
      return isValid;
   }
   
   public boolean withinRange(int number) {
      return (number > 0 && number < (9 + 1));
   }
   
   public boolean isSpotTaken(int number) {
      return this.board[number-1] != '-';
   }
   
   public void printBoard() {
      // | - | - | -
      // -----------
      // | - | - | -
      // -----------
      // | - | - | -
      
      System.out.println();
      for( int i = 0; i < 9; i++ ) {
         if ( (i%3 == 0) && (i != 0) ) {
            System.out.println();
            System.out.println("-------------");
         }
         System.out.print(" | " + this.board[i]);
      }
      System.out.println();
   }

   static public void printIndexBoard() {
      System.out.println();
      for( int i = 0; i < 9; i++ ) {
         if ( (i%3 == 0) && (i != 0) ) {
            System.out.println();
            System.out.println("-------------");
         }
         System.out.print(" | " + (i + 1));
      }
      System.out.println();
   }
   
   public boolean isThereAWinner() {
      boolean diagonalsAndMiddles = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
      boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
      boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';
      if ( diagonalsAndMiddles ) {
         this.winner = board[4];
      } else if ( topAndFirst ) {
         this.winner = board[0];
      } else if ( bottomAndThird ) {
         this.winner = board[8];
      }
      return diagonalsAndMiddles || topAndFirst || bottomAndThird;
   }
   
   public boolean topRow() {
      return board[0] == board[1] && board[1] == board[2];
   }

   public boolean middleRow() {
      return board[3] == board[4] && board[4] == board[5];
   }

   public boolean bottomRow() {
      return board[6] == board[7] && board[7] == board[8];
   }

   public boolean firstCol() {
      return board[0] == board[3] && board[3] == board[6];
   }

   public boolean secondCol() {
      return board[1] == board[4] && board[4] == board[7];
   }

   public boolean thirdCol() {
      return board[2] == board[5] && board[5] == board[8];
   }

   public boolean leftDi() {
      return board[0] == board[4] && board[4] == board[8];
   }

   public boolean rightDi() {
      return board[2] == board[4] && board[4] == board[6];
   }

   public boolean isTheBoardFilled() {
      for ( int i = 0; i < 9; i++ ) {
         if ( board[i] == '-' ) {
            return false;
         }
      }
      return true;
   }
   
   public String gameOver() {
      boolean didSomeoneWin = isThereAWinner();
      if ( didSomeoneWin ) {
         return "We have a winner!  The winner is " + this.winner + "'s";         
      } else if ( isTheBoardFilled() ) {
         return "Draw: Game Over!";
      } else {
         return "notOver";
      }
   }
}
