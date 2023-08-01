/**
 * CellularAutomaton is an enum representing a type of cellular automaton.
 * @author Mounir Zahidi
 *
 */
public enum CellularAutomaton {
	/**
	 * Elementary Cellular Automaton
	 */
	ECA,
	
	/**
	 * Totalistic Cellular Automaton
	 */
	TCA;
	/**
	 * 
	 * @param s the name if the cellular automaton
	 * @return a CellularAutomaton value.
	 * @throws CellularAutomatonNotFoundException if the cellular Automaton is not found with the name s
	 */
	public static CellularAutomaton parse(String s) throws CellularAutomatonNotFoundException {
		if (s.equalsIgnoreCase("ECA")) { 
			return ECA;
		}
		else if (s.equalsIgnoreCase("TCA")) {
			return TCA;
		}
		else {
			throw new CellularAutomatonNotFoundException(s);
		}
	}
}
