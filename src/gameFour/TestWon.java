package gameFour;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/****************************************
 * @author Kevin Trujillo
 * 	This class tests some of the methods to see if everythign is working correctly
 *
 **************************************/
public class TestWon {

	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("csis.txt");
		Rick rick = new Rick();
		rick.init(false);
		rick.makeMove(4, 5);
		rick.makeMove(3, 4);
		rick.makeMove(2, 3);
		rick.makeMove(1, 2);
		out.println("Testing hasWon() method, should print true " + rick.hasWon());
		ConnectGame game = new ConnectGame();
		game.makeMove(2,5);
		game.makeMove(3,5);
		game.makeMove(4,5);
		game.makeMove(5,2);
		out.println("Testing hasWon() method, should print false " + game.hasWon());
		rick = new Rick();
		rick.makeMoveEnemy(0,5);
		rick.makeMoveEnemy(0,4);
		rick.makeMoveEnemy(0,3);
		out.println("Opponent wins if he plays chip on first file, so rick should return 0 to mean he moves on row 1: " + rick.move());
		out.close();
	}

}
