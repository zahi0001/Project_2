
/**
 * CellularAutomatonNotFoundException is an exception thrown when no appropriate
 * CellularAutomaton exists.
 * 
 * @author Mounir Zahidi
 *
 */
public class CellularAutomatonNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Throw an error message.
	 * @param s the String to pass with the error Message
	 */
	public CellularAutomatonNotFoundException(String s) {
		super("Unknown cellular automaton type " + s);
	}
}
