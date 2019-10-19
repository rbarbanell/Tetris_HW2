import static org.junit.Assert.*;

import java.awt.Color;

import java.awt.Point;

import org.junit.Test;

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
        Square testSquare = new Square(testGrid,Grid.HEIGHT - 2, Grid.WIDTH - 2,Color.BLUE,true);
        //Determines whether the square can move right (should be true)
        assertTrue(testSquare.canMove(Direction.RIGHT));
        //Move the piece to (18,9)
        testSquare.move(Direction.RIGHT);
        //Determines whether the square can move right again. Squares can't move right once col = WIDTH - 1
        // the current position of the square is (HEIGHT - 2, WIDTH - 1)
        assertFalse(testSquare.canMove(Direction.RIGHT));
        //Moves the testSquare all the way to the left
        while(testSquare.getCol() != 0){
            testSquare.move(Direction.LEFT);
        }
        assertTrue(testSquare.getCol() == 0);
        //Now the testSquare can't move to the left but able to move to the right
        assertFalse(testSquare.canMove(Direction.LEFT));
        assertTrue(testSquare.canMove(Direction.RIGHT));
        //Add a new Square object
    }
	
    @Test
    public void testLShape(){
        //Create a grid
        Grid testGrid = new Grid();
        //Create an L Shape
        LShape testL = new LShape(Grid.HEIGHT - 18,Grid.WIDTH - 3,testGrid);
        assertTrue(testL.canMove(Direction.RIGHT));
        //Move testShape to the extreme right of grid
        testL.move(Direction.RIGHT);
        //Will not be able to move right anymore
        assertFalse(testL.canMove(Direction.RIGHT));
        //Move to the extreme left
        for(int col = Grid.WIDTH - 2; col >= 0; col--){
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
	public void testFullRow() {
		// create a new grid
		Grid g = new Grid();
		
		// create rows of square from 0 -> 9
		for (int row = 0; row < (Grid.HEIGHT - 10); row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				g.set(row, col, Color.PINK);
			}
		// must have: (19,4) and (19,5) set
			g.set(10, 4, Color.PINK);
			g.set(10, 5, Color.PINK);	
		}
		
		// create rows of square from 11 -> 20
		for (int row = 11; row < (Grid.HEIGHT); row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				g.set(row, col, Color.PINK);
			}
		}
		// check for full rows
		g.checkRows();
		
		for (int row = 0; row < Grid.HEIGHT; row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				// check the square at (row,col)
				// must have: (19,4) and (19,5) set
				// and all of the others not set
				if ((col == 4 || col == 5) && row == 19) {
					assertTrue(g.isSet(row, col));

				} else {
					assertFalse(g.isSet(row, col));
				}
			}
		}
	}
	/*
	 * Tests All pieces Report canRotate correctly
	 */
	@Test
    public void testCanRotate(){
        //Create a grid
        Grid testGrid = new Grid();
        //Grid is 10 x 20
        //Create an BarShape in center of grid
        Piece testPiece = new BarShape(Grid.HEIGHT / 2,Grid.WIDTH / 2,testGrid);
        assertTrue(testPiece.canRotate());
        
        //Create a grid
        testGrid = new Grid();
        //Create an J Shape in center of grid
        testPiece = new JShape(Grid.HEIGHT / 2,Grid.WIDTH / 2,testGrid);
        assertTrue(testPiece.canRotate());
        
        //Create a grid
        testGrid = new Grid();
        //Create an L Shape in center of grid
        testPiece = new LShape(Grid.HEIGHT / 2,Grid.WIDTH / 2,testGrid);
        assertTrue(testPiece.canRotate());
        
        //Create a grid
        testGrid = new Grid();
        //Create an S Shape in center of grid
        testPiece = new SquareShape(Grid.HEIGHT / 2,Grid.WIDTH / 2,testGrid);
        assertTrue(testPiece.canRotate());
    }
	/*
	 * Tests All pieces Report canRotate correctly when rotation is illegal
	 */
	@Test
    public void testCanNotRotate(){
        //Create a grid
        Grid testGrid = new Grid();
        //Grid is 10 x 20
        //Create an JShape against right wall
        Piece testPiece = new JShape(Grid.HEIGHT / 2,Grid.WIDTH - 2,testGrid);
        assertFalse(testPiece.canRotate());
        
        //Create a grid
        testGrid = new Grid();
        //Create an S Shape in center of grid
        testPiece = new SShape(Grid.HEIGHT / 2,Grid.WIDTH - 2,testGrid);
        //rotate once
        testPiece.rotate();
        //move right
        testPiece.move(Direction.RIGHT);
        assertFalse(testPiece.canRotate());
        
      //Create a grid
        testGrid = new Grid();
        //Create an S Shape in center of grid
        testPiece = new BarShape(Grid.HEIGHT / 2,Grid.WIDTH - 3,testGrid);
        //rotate once
        testPiece.rotate();
        //move right
        testPiece.move(Direction.RIGHT);
        testPiece.move(Direction.RIGHT);
        assertFalse(testPiece.canRotate());
    }
	/*
	 * Tests Shape Rotates correctly when allowed
	 */
	@Test
    public void testDidRotate(){
        //Create a grid
        Grid testGrid = new Grid();
        //Grid is 10 x 20
        //Create a Bar Shape at 5,5 (should have plenty of space to rotate)
        Piece testPiece = new BarShape(5, 5, testGrid);
        //rotate piece
        testPiece.rotate();
        Point[] points = testPiece.getLocations();
        /* Testing reported points
        for(int i = 0; i < 4;i++) {
        	System.out.println(i + "  "+ points[i].x + " , " + points[i].y);
        }
        */
        assertEquals(points[0].x, 5);
        assertEquals(points[0].y, 4);
        assertEquals(points[1].x, 5);
        assertEquals(points[1].y, 5);
        assertEquals(points[2].x, 5);
        assertEquals(points[2].y, 6);
        assertEquals(points[3].x, 5);
        assertEquals(points[3].y, 7);
	}
     
}