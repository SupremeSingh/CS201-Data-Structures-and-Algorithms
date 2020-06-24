/**
 * 
 */
import java.util.*;
/**
 * @author Manmit Singh
 *
 */
public class PercolationDFSFast extends PercolationDFS {

	/**
	 * @param n
	 */
	// Constructor 1
	public PercolationDFSFast(int size) {
		super(size);
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		if (!inBounds(row, col)) {
			throw new IndexOutOfBoundsException("Coordinates out of bounds"); // Throws out of bounds exception			
		}
		int new_row, new_col, i = 0;
		open(row, col);
		int[] delta_r = {-1, 1, 0, 0};
		int[] delta_c = {0, 0, -1, 1};
		for (int k = 0 ; k < delta_r.length ; k ++) {
			new_row = row + delta_r[k];
			new_col = col + delta_c[k];
			if (inBounds(new_row, new_col) && isFull(new_row, new_col)) {
				i++;
			}
		}
		if (i > 0 || row == 0) {
			dfs(row, col);
			return;
		}
	}

}

//int iters = 0; // Initial index variable
//myGrid[row][col] = OPEN;
//if (row == 0) { // Checks if the cell is in first row
//	iters ++;
//}
//if (inBounds(row-1, col)) { // Checks to top cell
//	if (isFull(row-1, col)) {
//		iters ++;
//	}
//}
//if (inBounds(row, col - 1)) { // Checks the left cell
//	if (isFull(row, col-1)) {
//		iters ++;
//	}
//}
//if (inBounds(row, col+1)) { // Checks the right cell
//	if (isFull(row, col+1)) {
//		iters ++;
//	}
//}
//if (inBounds(row+1, col)) { // Checks the bottom cell
//	if (isFull(row+1, col)) {
//		iters ++;
//	}
//}
//if (iters > 0) {
//	dfs(row, col);
//}