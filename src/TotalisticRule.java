import java.util.StringJoiner;

/**
 * TotalisticRule represents any one of the 64 rules that govern the evolution
 * of 1D, two-state totalistic CAs with a neighborhood radius of 2.
 * 
 * @author Mounir Zahidi
 *
 */
public class TotalisticRule extends Rule {

	protected TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);

		if (ruleNum < 0 || ruleNum > 63) {
			throw new RuleNumException(0, 63);
		}
	}
	
	/**
	 * 
	 * @param index index of the cell in the generation
	 * @param gen   the generation containing the cell states
	 * @return the state of the cell with the given index in the given Generation
	 *         along with the states of its two nearest neighbors from each side.
	 */
	@Override
	public boolean[] getNeighborhood(int index, Generation gen) {
		return Rule.getNeighborhoodByRadius(index, 2, gen);
	}
	
	/**
	 * Return the next state of a cell with the given neighborhood of states. The
	 * input is an array that contains the states of two left neighbors, the cell
	 * itself, and two right neighbors (in that order).
	 * 
	 * 
	 * @param neighborhood the boolean array[] representing the neighborhood of the
	 *                     cell in question.
	 * @return the state of the cell according to the wolfram code rule
	 */
	@Override
	public boolean evolve(boolean[] neighborhood) {
		int trueCount = 0;

		for (int i = 0; i < neighborhood.length; ++i) {
			if (neighborhood[i]) {
				++trueCount;
			}
		}

		int[] ruleArray = this.ruleIntArray();

		if (ruleArray[trueCount] == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param falseSymbol the char Symbol representing the true value.
	 * @param trueSymbol  the char symbol representing the false value.
	 * @return A string representation of the table depicts the rule using the given
	 *         characters to represent false and true.
	 */
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		StringJoiner sj = new StringJoiner(" ");

		int[] rule = new int[] { 5, 4, 3, 2, 1, 0 };

		for (int i = 0; i < 6; ++i) {
			sj.add(Integer.toString(rule[i]));
		}

		StringJoiner sj2 = new StringJoiner(" ");
		for (int i = 5; i >= 0; --i) {
			if (this.ruleIntArray()[i] == 1) {
				sj2.add(trueSymbol + "");
			} else {
				sj2.add(falseSymbol + "");
			}
		}

		return sj + System.lineSeparator() + sj2;
	}

	// helper method
	/**
	 * 
	 * @return returns the rule in wolfram code in an array.
	 */
	public int[] ruleIntArray() {

		int[] result = new int[6];
		String ruleAsString = String.format("%6s", Integer.toBinaryString(super.getRuleNum())).replace(' ', '0');

		for (int i = 0; i < result.length; ++i) {
			result[i] = Integer.parseInt(String.valueOf(ruleAsString.charAt(result.length - 1 - i)));
		}
		return result;
	}

}
