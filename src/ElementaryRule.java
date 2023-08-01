import java.util.Arrays;
import java.util.StringJoiner;

/**
 * ElementaryRule represents any one of the 256 rules that govern the evolution
 * of elementary CAs.
 * 
 * @author Mounir Zahidi
 *
 */
public class ElementaryRule extends Rule {

	protected ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		
		if (ruleNum <0 || ruleNum>=256)
			throw new RuleNumException (0, 255);
	}
	
	/**
	 * 
	 * @param index index of the cell in the generation
	 * @param gen   the generation containing the cell states
	 * @return the state of the cell with the given index in the given Generation
	 *         along with the states of its two nearest neighbors.
	 */
	@Override
	public boolean[] getNeighborhood(int index, Generation gen) {

		return Rule.getNeighborhoodByRadius(index, 1, gen);
	}
	
	
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
	@Override
	public boolean evolve(boolean[] neighborhood) {
		int indexInWolfram = -1;

		for (int i = 0; i < 8; ++i) {
			if (Arrays.compare(neighborhood, super.wolframCode[i]) == 0) {
				indexInWolfram = i;
				break;
			}
		}
		// apply the rule of the neighborhood array
		int[] rule = super.ruleIntArray();
		// return the middle cell state a.k.a the value at the index of rule array
		return (rule[indexInWolfram] == 1);
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

		for (int i = 7; i >= 0; --i) {
			String result = "";
			for (int j = 0; j < 3; ++j) {
				if (wolframCode[i][j] == true) {
					result += trueSymbol;
				} else {
					result += falseSymbol;
				}
			}
			sj.add(result);
		}

		StringJoiner sj2 = new StringJoiner("   ", " ", " ");

		int[] rule = ruleIntArray();

		for (int i = rule.length - 1; i >= 0; --i) {
			if (rule[i] == 1) {
				sj2.add(trueSymbol + "");
			} else {
				sj2.add(falseSymbol + "");
			}
		}

		return (sj + System.lineSeparator() + sj2);

	}

}
