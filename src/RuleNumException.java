/**
 * Controls the number entered to make sure it is within the range allowed.
 * 
 * @author Mounir Zahidi
 *
 */
public class RuleNumException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the object so that the inherited method getMessage returns
	 * 
	 * @param min the lower boundary of the range of the values allowed to be chosen
	 *            from inclusive
	 * @param max the upper boundary of the range of the values allowed to be chosen
	 *            from inclusive
	 */
	public RuleNumException(int min, int max) {
		super("ruleNum is outside the range [" + min + ", " + max + "].");
	}

}
