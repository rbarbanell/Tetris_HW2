import java.awt.Color;

public class TShape extends AbstractPiece {
	
	public TShape(int r, int c, Grid g) {
		grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;

		// Create the squares
		square[0] = new Square(g, r  , c - 1 , Color.magenta, true);
		square[1] = new Square(g, r , c  , Color.magenta, true);
		square[2] = new Square(g, r , c + 1 , Color.magenta, true);
		square[3] = new Square(g, r + 1 , c , Color.magenta, true);
	}


}