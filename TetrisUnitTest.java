

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;


public class TetrisUnitTest {

	@Test
	public void testCheckRows() {
		// Create a grid with a full row at the bottom
		// and two squares above the full bottom row
		Grid g = new Grid();
		// full bottom row
		for (int col = 0; col < Grid.WIDTH; col++) {
			g.set(Grid.HEIGHT - 1, col, Color.GREEN);
		}
		// add two squares above the bottom row
		g.set(Grid.HEIGHT - 2, 3, Color.RED);
		g.set(Grid.HEIGHT - 3, 3, Color.RED);
		// remove the full row
		g.checkRows();
		// check that the grid has been updated correctly
		for (int row = 0; row < Grid.HEIGHT; row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				// check the square at (row,col)
				// must have: (18,3) and (19,3) set
				// and all of the others not set
				if ((row == 18 || row == 19) && col == 3) {
					assertTrue(g.isSet(row, col));
				} else {
					assertFalse(g.isSet(row, col));
				}
			}
		}
	}

	@Test
	public void testSquareMove() {
		//Create a grid
		Grid testGrid = new Grid();
		//Creates a square at (HEIGHT - 2, WIDTH - 1)
		Square testSquare = new Square(testGrid, Grid.HEIGHT - 2, Grid.WIDTH - 2, Color.BLUE, true);
		//Determines whether the square can move right (should be true)
		assertTrue(testSquare.canMove(Direction.RIGHT));
		//Move the piece to (18,9)
		testSquare.move(Direction.RIGHT);
		//Determines whether the square can move right again. Squares can't move right once col = WIDTH - 1
		// the current position of the square is (HEIGHT - 2, WIDTH - 1)
		assertFalse(testSquare.canMove(Direction.RIGHT));
		//Moves the testSquare all the way to the left
		while (testSquare.getCol() != 0) {
			testSquare.move(Direction.LEFT);
		}
		assertTrue(testSquare.getCol() == 0);
		//Now the testSquare can't move to the left but able to move to the right
		assertFalse(testSquare.canMove(Direction.LEFT));
		assertTrue(testSquare.canMove(Direction.RIGHT));


	}

	@Test
	public void testLShape() {
		//Create a grid
		Grid testGrid = new Grid();
		//Create an L Shape
		LShape testL = new LShape(Grid.HEIGHT - 18, Grid.WIDTH - 3, testGrid);
		assertTrue(testL.canMove(Direction.RIGHT));
		//Move testShape to the extreme right of grid
		testL.move(Direction.RIGHT);
		//Will not be able to move right anymore
		assertFalse(testL.canMove(Direction.RIGHT));
		//Move to the extreme left
		for (int col = Grid.WIDTH - 2; col >= 0; col--) {
			testL.move(Direction.LEFT);
		}
		//Can't move left
		assertFalse(testL.canMove(Direction.LEFT));
		//Can move right
		assertTrue(testL.canMove(Direction.RIGHT));
		//Move the testL to the bottom row using DROP
		assertTrue(testL.canMove(Direction.DROP));
		testL.move(Direction.DROP);
		assertFalse(testL.canMove(Direction.DOWN));
		//Won't be able to move down because it's already at HEIGHT - 1
	}


	@Test
	public void checkRowsTest() {
		//Create an empty grid
		Grid testGrid = new Grid();
		//Set the top half of the grid using for loop
		for (int row = 0; row <= 9; row++) {
			for (int col = 0; col <= Grid.WIDTH - 1; col++) {
				testGrid.set(row, col, Color.RED);
			}
		}
		//Set row 10 col 4,5 with the same color
		testGrid.set(10, 4, Color.GREEN);
		testGrid.set(10, 5, Color.GREEN);

		//Set the bottom half of the grid using for loop
		for (int row = 11; row <= Grid.HEIGHT - 1; row++) {
			for (int col = 0; col <= Grid.WIDTH - 1; col++) {
				testGrid.set(row, col, Color.RED);
			}
		}
		for (int col = 4; col < 6; col++) {
			assertTrue(testGrid.isSet(10, col));

		}
		for (int row = 0; row <= 9; row++) {
			for (int col = 0; col <= Grid.WIDTH - 1; col++) {
				assertTrue(testGrid.isSet(row, col));
			}
		}
		for (int row = 11; row <= Grid.HEIGHT - 1; row++) {
			for (int col = 0; col <= Grid.WIDTH - 1; col++) {
				assertTrue(testGrid.isSet(row, col));
			}
		}
		testGrid.checkRows();


		//After removal
		for (int row = 0; row <= 18; row++) {
			for (int col = 0; col <= Grid.WIDTH - 1; col++) {
				assertFalse(testGrid.isSet(row, col));
			}
		}
		assertTrue(testGrid.isSet(19,4));
		assertTrue(testGrid.isSet(19,5));
		assertFalse(testGrid.isSet(19,3));

	}
}


















