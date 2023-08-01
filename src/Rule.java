/**
 * This class represents any one of the 256 possible rules that govern the
 * evolution of ECAs.
 * 
 * @author Mounir Zahidi // collab with Nick Rodriguez
 *
 */
public abstract class Rule {
	// class fields
	private int ruleNum ;
	
	/**
	 * the 2D array representation of the wolfram code
	 */
	public boolean [][] wolframCode= {
			{false,false,false},
			{false,false, true},
			{false,true,false},
			{false,true,true},
			{true,false,false},
			{true,false,true},
			{true,true,false},
			{true,true,true}
	};
	
	// methods
	/**
	 * Create a Rule corresponding to the given Wolfram code.
	 * @param ruleNum ruleNum in the base 10
	 */
	protected Rule (int ruleNum){
		this.ruleNum = ruleNum;
	}
	
	/**
	 * Return the rule's Wolfram code.
	 * 
	 * @return the rule in wolfram's code
	 */
	public int getRuleNum() {

		return this.ruleNum;
	}
	
	/**
	 * 
	 * @param index index of the cell in the generation
	 * @param gen   the generation containing the cell states
	 * @return the state of the cell with the given index in the given Generation
	 *         along with the states of its two nearest neighbors.
	 */
	public abstract boolean[] getNeighborhood(int index, Generation gen);

	// evolve methods
	
	/**
	 * Return the next state of a cell with the given neighborhood of states. The
	 * input is an array that contains the states of the left neighbor, the cell
	 * itself, and the right neighbor (in that order).
	 * 
	 * 
	 * @param neighborhood the boolean array[] representing the neighborhood of the
	 *                     cell in question.
	 * @return the state of the cell according to the wolfram code rule
	 */
	public abstract boolean evolve(boolean[] neighborhood);
	
	/**
	 * Apply the rule to the given Generation and return the next Generation.
	 * @param gen the generation to evolve
	 * @return the evolved generation
	 */
	public Generation evolve(Generation gen) {
		boolean[] statesCopy = gen.getStates();

		for (int i = 0; i < gen.size(); ++i) {
			boolean[] neighborhood = getNeighborhood(i, gen);
			statesCopy[i] = evolve(neighborhood);
		}

		Generation result = new Generation(statesCopy);

		return result;
	}

	//helper method
	/**
	 * 
	 * @return returns the rule in wolfram code in an array.
	 */
	public int[] ruleIntArray() {

		int[] result = new int[8];
		String ruleAsString = String.format("%8s", Integer.toBinaryString(this.ruleNum)).replace(' ', '0');

		for (int i = 0; i < result.length; ++i) {
			result[i] = Integer.parseInt(String.valueOf(ruleAsString.charAt(result.length - 1 - i)));

		}
		return result;
	}
	
	//Additional methods
	/**
	 * 
	 * @param falseSymbol the char Symbol representing the true value.
	 * @param trueSymbol  the char symbol representing the false value.
	 * @return A string representation of the table depicts the rule using the given
	 *         characters to represent false and true.
	 */
	public abstract String ruleTableString (char falseSymbol, char trueSymbol);

	/**
	 * 
	 * @param idx    the index of the cell in question
	 * @param radius the number of the neighborhoods to take from each side
	 * @param gen    the generation in question
	 * @return he cell states in the neighborhood of the cell with the given index
	 *         and specified radius.
	 */
	public static boolean [] getNeighborhoodByRadius (int idx, int radius, Generation gen) {
		
		boolean [] result = new boolean [2* radius + 1];
		
		result [radius] = gen.getState(idx);
		
		for (int i = 1 ; i <= radius ; ++i) {
			result [radius - i] = gen.getState((idx -i + (1000 * gen.size()))%gen.size());
			result [radius + i] = gen.getState((idx + i ) % gen.size());
		}
		
		return result;
	}
	
	
	
}
