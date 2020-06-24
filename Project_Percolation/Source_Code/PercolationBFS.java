/**
 * 
 */
import java.util.*;
/**
 * @author Manmit Singh
 *
 */
public class PercolationBFS extends PercolationDFSFast {

	/**
	 * @param size
	 */
	// Constructor
	public PercolationBFS(int size) {
		super(size);
	}
	
	// Finding open cells in the vicinity of a cell and marking them as full ... will be used to mark cells neighbouring a full cell.
	@Override
	protected void dfs(int row, int col) {
	   if (!inBounds(row, col)) { // Checks if the cell is in the grid or not
		   return;
	   }
		 	
	   Queue<Integer> my_queue = new LinkedList<>();  // Declare a queue for integer-type items
	   /*	
	   int[] rowDelta = {-1,1,0,0}; // Declare column and row iterations
	   int[] colDelta = {0,0,-1,1};
	   */     
	   myGrid[row][col] = FULL;  // mark the cell
       Integer int_map = row*myGrid.length+col; // Mark the row and columns to a single Integer-type value
	   my_queue.add(int_map); // Add to queue list
	   
	   while (my_queue.size() != 0){
	         Integer p = my_queue.remove();
	         int row_val = p/myGrid.length; // Get Row value from the map
	         int col_val = p%myGrid.length; // Get Column value from the map
	              
	         if (inBounds(row_val-1, col_val)) { // Checks the cell up top
	        	 if (isOpen(row_val-1, col_val) && !isFull(row_val-1, col_val)) {
	        		 myGrid[row_val-1][col_val] = FULL;
	        		 my_queue.add((row_val - 1) * myGrid.length + col_val);
	        	 }
	          }	         
	         if (inBounds(row_val, col_val-1)) { // Checks the cell on the left-side
	        	 if (isOpen(row_val, col_val-1) && !isFull(row_val, col_val-1)) {
	        		 myGrid[row_val][col_val-1] = FULL;
	        		 my_queue.add((row_val) * myGrid.length + (col_val-1));
	        	 }
	          }
	         if (inBounds(row_val, col_val+1)) { // Checks the cell on the right-side
	        	 if (isOpen(row_val, col_val+1) && !isFull(row_val, col_val+1)) {
	        		 myGrid[row_val][col_val+1] = FULL;
	        		 my_queue.add((row_val) * myGrid.length + (col_val+1));
	        	 }
	          }
	         if (inBounds(row_val+1, col_val)) { // Checks the cell below this one
	        	 if (isOpen(row_val+1, col_val) && !isFull(row_val+1, col_val)) {
	        		 myGrid[row_val+1][col_val] = FULL;
	        		 my_queue.add((row_val + 1) * myGrid.length + col_val);
	        	 }
	          }
	         
	        }
	    }

	}




/*
// out of bounds?
if (! inBounds(row,col)) return;

// full or NOT open, don't process
if (isFull(row, col) || !isOpen(row, col))
	return;

myGrid[row][col] = FULL;
dfs(row - 1, col);
dfs(row, col - 1);
dfs(row, col + 1);
dfs(row + 1, col);
*/