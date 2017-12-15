package gameFour;

/******************************************************************************
 *  Compilation:  javac ConnectGame
 *  Execution:    not stand alone
 *  Dependencies: None.
 *
 ******************************************************************************/
/**
 * @author Kevinice
 *
 */
public class ConnectGame {
	private int[][] board;
	private final int ROWS = 6;
	private final int COLS = 7;

	/**
	 *  Creates a new empty connect game board.
	 *  This can be used to start a new game.
	 */
	public ConnectGame() {
		board = new int[7][6];
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				this.board[i][j] = 0;
			}
		}
		this.board = board;

	}

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

	public boolean isValid(double y) {
		int x = getTheX(y);
		for (int i = 5; i >= 0; i--) {
			if (this.board[x][i] == 0) {
				return true;
			}
		}
		return false;
	}

	public int theYvalue(double y) {
		int x = getTheX(y);
		for (int i = 5; i >= 0; i--) {
			if (board[x][i] == 0) {
				return i;
			}
		}
		return 1;
	}
	public int theYvalue2(int x) {
		for (int i = 5; i >= 0; i--) {
			if (board[x][i] == 0) {
				return i;
			}
		}
		return 1;
	}

	public int getY(double x) {
		int y = getTheX(x);
		if (this.board[y][5] == 0) {
			return 735;
		}
		if (this.board[y][4] == 0) {
			return 618;
		}
		if (this.board[y][3] == 0) {
			return 502;
		}
		if (this.board[y][2] == 0) {
			return 385;
		}
		if (this.board[y][1] == 0) {
			return 270;
		} else
			return 153;
	}

	public static int getTheX(double x) {
		if (x < 120) {
			return 0;
		}
		if (x < 230) {
			return 1;
		}
		if (x < 345) {
			return 2;
		}
		if (x < 450) {
			return 3;
		}
		if (x < 570) {
			return 4;
		}
		if (x < 680) {
			return 5;
		} else {
			return 6;
		}
	}

	public int getTheY(int y) {
		if (this.board[y][5] == 0) {
			return 735;
		}
		if (this.board[y][4] == 0) {
			return 618;
		}
		if (this.board[y][3] == 0) {
			return 502;
		}
		if (this.board[y][2] == 0) {
			return 385;
		}
		if (this.board[y][1] == 0) {
			return 270;
		} else
			return 153;
	}

	public static int getTheX2(double x) {
		if (x < 120) {
			return 66;
		}
		if (x < 230) {
			return 178;
		}
		if (x < 345) {
			return 290;
		}
		if (x < 450) {
			return 402;
		}
		if (x < 570) {
			return 514;
		}
		if (x < 680) {
			return 626;
		} else {
			return 739;
		}
	}

	public void makeMove(int x, int y) {
		this.board[x][y] = 1;
	}
	public void makeMove2(int x, int y) {
		this.board[x][y] = 2;
	}
	public static int getTheX3(double x) {
		if (x == 0) {
			return 66;
		}
		if (x == 1) {
			return 178;
		}
		if (x == 2) {
			return 290;
		}
		if (x == 3) {
			return 402;
		}
		if (x == 4) {
			return 514;
		}
		if (x == 5) {
			return 626;
		} else {
			return 739;
		}
	}
	public void printBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				System.out.print(this.board[j][i]);
			}
		System.out.println();
		}
	}
}
