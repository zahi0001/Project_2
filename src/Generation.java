import java.util.Arrays;
/**
 * This class represents a row of cells at a fixed time. In other words, one generation 
 * @author Mounir Zahidi
 * @author Nick Rodriguez
 *
 */

public class Generation {
	
	// private fields
	private boolean [] cellStates;
	
	/**
	 * Create a Generation with one cell for each element in the passed array.
	 * @param states a boolean array of the initial cellStates
	 */
	public Generation (boolean ... states) {
		
		if (states == null || states.length == 0) {
			boolean [] r = new boolean [1];
			r [0] = false;
			this.cellStates = Arrays.copyOf(r, r.length);
		}
		
		else {
			this.cellStates = new boolean [states.length];
			
			for (int i =0; i < states.length; ++i) {
				this.cellStates [i] = states [i];
			}
		}
		
	}
	
	/**
	 * Create a Generation with one cell for each character in the passed String. if
	 * the character is equal to trueSymbol passed, then the state of the
	 * corresponding cell is true.
	 * 
	 * @param states String containing a sequence of char
	 * @param trueSymbol the char representing when the cell state is true
	 */
	public Generation (String states, char trueSymbol) {
		
		if (states == null || states == "") {
			boolean [] r = new boolean [1];
			r [0] = false;
			this.cellStates = Arrays.copyOf(r, r.length);
		}
		
		else {
			this.cellStates = new boolean [states.length()];
			
			for (int i =0; i < states.length (); ++i) {
				this.cellStates [i] = (trueSymbol == states.charAt(i));// upper case and lower case issues possible
			}
		}
	}
	
	/**
	 * Return the state of the cell with the given index.
	 * 
	 * @param index index at the generation
	 * @return the state of the element of the array at the index
	 */
	public boolean getState (int index) { // out of bound index problems ??
		return this.cellStates [index];
	}
	
	/**
	 * Return an array with all of the cell states.
	 * 
	 * @return a boolean array containing the states of the cells 
	 */
	public boolean [] getStates () {
		return Arrays.copyOf(this.cellStates, this.cellStates.length); // immutable remember
	}
	
	/**
	 * Return a String representation of the cell states using falseSymbol and
	 * trueSymbol as the symbols for false and true, respectively.
	 * 
	 * @param falseSymbol the char representing when the cell state is true
	 * @param trueSymbol  the char representing when the cell state is false
	 * @return a String containing the representation of the generation using
	 *         Symbols passed.
	 */
	public String getStates (char falseSymbol, char trueSymbol) {
		String result = "";
		
		for (int i =0; i < this.cellStates.length; ++i) {
			if (this.cellStates [i]) {
				result += trueSymbol;
			}
			else {
				result += falseSymbol;
			}
		}
		
		return result;
	}
	
	/**
	 * Return the number of cells.
	 * 
	 * @return the size of the generation, i.e. the number of cells
	 */
	public int size() {
		return this.cellStates.length;
	}
	
}
