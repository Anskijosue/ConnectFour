package gameFour;
import java.util.Arrays;
import java.util.Random;
/******************************************************************************
 *  Compilation:  javac Rick.java
 *  Execution:    not stand alone
 *  Dependencies: java.util.Arrays & java.util.Random
 *
 ******************************************************************************/
/**
 * @author Kevin Trujillo
 * The Rick class provides a computer to play connect four. 
 *
 */
public class Rick implements Player {
	private int[][] board;
	private int color;
	private int ROWS = 6;
	private int COLS = 7;
	static Random rand = new Random();

	/**
	 * Generates a new computer (Rick) with an empty board.
	 */
	public Rick() {
		this.board = new int[7][6];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				this.board[j][i] = 0;
			}
		}

	}


	/**
	 * @see gameFour.Player
	 * sets the color of the Computer
	 */
	public void init(Boolean color) {
	if (color == false) {
		this.color = 2;
	} else {
		this.color = 1;
	}

	}
	/**
	 * Prints the current status of ConnectFour board
	 */
	public void printBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				System.out.print(this.board[j][i]);
			}
		System.out.println();
		}
	}

	
	/** 
	 * @see gameFour.Player#name()
	 * prints YR or RR. 
	 */
	public String name() {
		if (this.color == 1) {
			return "RR"; // Red Rick
		} else {
			return "YR"; // Yellow Rick
		} 
	}
	/**
	 * @param array is the array pre-finding best move algorithm
	 * Copies desired board state onto Computer.
	 */
	public void createCopy(int[][] array) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				this.board[j][i] = array[j][i];
			}
		}
	}


	/**
	 * @see gameFour.Player#move()
	 * If it has a winning move, it plays it.
	 * If opponent has a winning move, it plays it himself.
	 * Then it Finds the "best" move by using the Max-Min Algorithm
	 */
	public int move() {
		// Checks if it has a winning move.
		for (int i = 0; i < COLS; i++) {
			int x = this.getYrow(i);
			this.makeMove(i,x);
			if (this.hasWon()) {
				return i;
			}
			this.delMove(i,x);
		}
		// Checks if opponent has a winning move.
		for (int i = 0; i < COLS; i++) {
				int x = this.getYrow(i);
				this.makeMoveEnemy(i,x);
				if (this.hasWon()) {
					this.makeMove(i,x);
					return i;
				}
				this.delMove(i,x);
		}
		// Finds best move using Max-Min algorithm
		int[][] original = new int[7][6];
		for (int j = 0; j < COLS; j++) {
			for (int i = 0; i < ROWS; i++) {
				original[j][i] = this.board[j][i];
			}
		}
		int[] scores = new int[7];
		for (int k = 0; k < 2; k++) {
			outerloop:
			for (int i = 0; i < COLS; i++) {
				if (this.isValid(i)) {
					this.makeMove(i,this.getYrow(i));
					if (this.hasWon()) { 
						if (k == 0) {
							this.createCopy(original);
							this.makeMove(i,this.getYrow(i));
							return i;
						} else {
							scores[i] += 1000;
							this.delMove(i,this.getYrow(i));
							continue;
						}
					}
				}
				for (int j = 0; j < COLS; j++) {
					if (this.isValid(j)) {
						this.makeMoveEnemy(j,this.getYrow(j));
						if (this.hasWon()) {
							scores[i] -= 1000;
							this.createCopy(original);
							continue outerloop;
						}
					}
						}
				}
		this.createCopy(original);
		}
		this.createCopy(original);
		int answer = this.getMax(scores);
		this.makeMove(answer,this.getYrow(answer));
		return answer;
	}

	/**
	 * @param scores are the scores of each move
	 * @return the index of the maximum score.
	 */
	private int getMax(int[] scores) {
		int highest = rand.nextInt(7);
		int compare = scores[highest];
		for (int i = 0; i < scores.length; i++) {
			if (this.isValid(i)) {
				if (compare < scores[i]) {
					highest = i;
					compare = scores[i];
				}
			}
		}
		return highest;
	}
	
	/**
	 * @see gameFour.Player#inform(int)
	 * Lets the computer know the column where the Connectfour game happened.
	 */
	public void inform(int i) {
		if (this.color == 2) {
			this.board[i][this.getYrow(i)] = 1;
		} else {
			this.board[i][this.getYrow(i)] = 2;
		}
		

	}
	/**
	 * @param x is the column
	 * @return the lowest place where the chip will fall
	 * This method is important because it tells you 
	 * where the chip will land.
	 */
	public int getYrow(int x) {
		for (int i = 5; i >= 0; i--) {
			if (this.board[x][i] == 0) {
				return i;
			}
		}
		return 0;
	}
	/**
	 * @param x is the column
	 * @param y is the row
	 * makes a move for the Computer
	 */
	public void makeMove(int x, int y) {
		this.board[x][y] = this.color;
	}
	/**
	 * @param x is the column
	 * @param y is the row
	 * Makes a move for the human.
	 */
	public void makeMoveEnemy(int x, int y) {
		if (this.color == 1 ) {
			this.board[x][y] = 2;
		} else 
			this.board[x][y] = 1;
	}
	/**
	 * @param x is the column
	 * @param y is the row
	 * deletes a move
	 */
	public void delMove(int x, int y) {
		this.board[x][y] = 0;
	}
	
	/** Checks if either has won.
	 * @return true or false
	 */
	public boolean hasWon() {
		// Horizontal lines
		int state = 1;
		for (int j = 0; j < ROWS; j++) {
			for (int x = 0; x < 4; x++) {
				for (int i = 0; i < 4; i++) {
					state *= this.board[i + x][j];
				}
				if (state == 1) {
					return true;
				}
				if (state == 16) {
					return true;
				}
				state = 1;
			}
		}
		// Vertical lines
		for (int i = 0; i < COLS; i++) {
			for (int x = 0; x < 3; x++) {
				for (int j = 0; j < 4; j++) {
					state *= this.board[i][j + x];
				}
				if (state == 1) {
					return true;
				}
				if (state == 16) {
					return true;
				}
				state = 1;
			}
		}
		// Diagonals left to right
		for (int z = 0; z < 3; z++) {
			for (int y = 0; y < 4; y++) {
				for (int j = 5, i = 0, x = 0; x < 4; x++, j--, i++) {
					state *= this.board[i + y][j - z];
				}
				if (state == 1) {
					return true;
				}
				if (state == 16) {
					return true;
				}
				state = 1;
			}
		}
		// Diagonals right to left
		for (int z = 0; z < 3; z++) {
			for (int y = 0; y < 4; y++) {
				for (int j = 5, i = 6, x = 0; x < 4; x++, j--, i--) {
					state *= this.board[i - y][j - z];
				}
				if (state == 1) {
					return true;
				}
				if (state == 16) {
					return true;
				}
				state = 1;
			}
		}
		return false;
	}

	/**
	 * @param x is the column
	 * @return true if the column is not full
	 */
	public boolean isValid(int x) {
		for (int i = 5; i >= 0; i--) {
			if (this.board[x][i] == 0) {
				return true;
			}
		}
		return false;
	}
}
