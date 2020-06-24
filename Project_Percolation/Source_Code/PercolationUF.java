/**
 * 
 */
import java.util.*;
/**
 * @author Manmit Singh
 *
 */
public class PercolationUF implements IPercolate {

	// Instance Variables
	protected boolean[][] myGrid; // Represents whether a cell is open, all cells should be set to false when they are generated in the grid 
	protected int myOpenCount;// Returns the number of open cells
	protected IUnionFind myFinder; // Stores the IUnionFind object passed to the constructor
	protected final int VTOP;
	protected final int VBOTTOM;
	
	/**
	 * 
	 */
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		myFinder = finder;
		myFinder.initialize(size*size + 2);
		VTOP = size * size;
		VBOTTOM = size * size + 1;
		myOpenCount = 0;
	}

	/* (non-Javadoc)
	 * @see IPercolate#open(int, int)
	 */
	@Override
	public void open(int row, int col) {
	
	if (!inBounds(row, col)) { // Checks if the cell exists on that grid 
		throw new IndexOutOfBoundsException("OUT OF BOUNDS");
	}
	if (!isOpen(row, col)) { // Checks for cells that aren't open yet
		myGrid[row][col] = true; // Mark that cell open
		myOpenCount++;
		
		if (inBounds(row - 1, col) && isOpen(row - 1, col)) { // Check union on cell at the top
			myFinder.union(gridIndex(row, col), gridIndex(row - 1, col));
		}	
		if (inBounds(row, col - 1) && isOpen(row, col - 1)) { // Check right
			myFinder.union(gridIndex(row, col), gridIndex(row, col - 1));
		}
		if (inBounds(row, col + 1) && isOpen(row, col + 1)) { // Check left
			myFinder.union(gridIndex(row, col), gridIndex(row, col + 1));
		}
		if (inBounds(row + 1, col) && isOpen(row + 1, col)) { // Check bottom
			myFinder.union(gridIndex(row, col), gridIndex(row + 1, col));
		}
		if (row == 0) { // Check top row 
			myFinder.union(gridIndex(row, col), VTOP);
		}
		if (row == myGrid.length - 1) { // Check bottom row
			myFinder.union(gridIndex(row, col), VBOTTOM);
		}
	}
}

	/* (non-Javadoc)
	 * @see IPercolate#isOpen(int, int)
	 */
	@Override
	public boolean isOpen(int row, int col) { // Checks if cell is open or not
		if (!inBounds(row, col)) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		return myGrid[row][col];
	}

	/* (non-Javadoc)
	 * @see IPercolate#isFull(int, int)
	 */
	@Override
	public boolean isFull(int row, int col) { // Checks if cell is full or not
		if (!inBounds(row, col)) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		return myFinder.connected(gridIndex(row, col), VTOP);
	}

	/* (non-Javadoc)
	 * @see IPercolate#percolates()
	 */
	@Override
	public boolean percolates() { // Checks for percolation in the structure
		return myFinder.connected(VTOP, VBOTTOM);
	}

	/* (non-Javadoc)
	 * @see IPercolate#numberOfOpenSites()
	 */
	@Override
	public int numberOfOpenSites() { // Returns the number of total open cells in the structure.
		return myOpenCount;
	}
	// Helper Method 1 - Checks if cell exists in grid
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	// Helper Method 2 - Returns index of a cell in the grid
	public int gridIndex(int row, int col) {
		return row * myGrid.length + col;
	}

}
