import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents an ECA. Each Automaton encapsulates a Rule and an
 * ArrayList of Generations. The ArrayList is ordered by time, with the first
 * element being the initial Generation and the last being the current
 * Generation.
 * 
 * @author Mounir Zahidi
 *
 */
public abstract class Automaton {

	// class fields
	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<Generation>();
	/**
	 * the char representation of the false value.
	 */
	public char falseSymbol = '0';
	
	/**
	 * the char representation of the true value.
	 */
	public char trueSymbol = '1';

	// Constructor
	
	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
		this.rule = createRule(ruleNum);
		this.generations.add(initial);
	}

	protected Automaton(String fileName) throws IOException, RuleNumException {
		try {
			Scanner file = new Scanner(new File(fileName)); // asks about why u write New File
			
			int ruleInput = file.nextInt();
			
			this.rule = createRule(ruleInput);
			file.nextLine();

			this.falseSymbol = file.next().charAt(0);

			this.trueSymbol = file.next().charAt(0);
			file.nextLine();

			String initialCellStates = file.nextLine();

			boolean[] cellStates = new boolean[initialCellStates.length()];

			for (int i = 0; i < initialCellStates.length(); ++i) {
				cellStates[i] = (initialCellStates.charAt(i) == this.trueSymbol);
			}

			Generation addThis = new Generation(cellStates);

			this.generations.add(addThis);
		} catch (NoSuchElementException f) {
			f.printStackTrace();
		}
		
		
	}

	// evolve method
	/**
	 * Evolve the Automaton a given number of steps, appending each successive
	 * Generation to the ArrayList.
	 * 
	 * @param numSteps the number of times to evolve the Automaton
	 * @return the number of times the Automaton has been evolved
	 */
	public int evolve(int numSteps) {
		if (numSteps > 0) {
			for (int i = 0; i < numSteps; ++i) {
				Generation toAdd = this.rule.evolve(this.getCurrentGeneration());
				this.generations.add(toAdd);
			}
			return numSteps;
		} else {
			return 0;
		}
	}

	// getter methods
	
	/**
	 * Return the Generation produced during the given time step.
	 * 
	 * @param stepNum the stepNum of the generation to return.
	 * @return the generation at given step Number
	 */
	public Generation getGeneration(int stepNum) {
		if (stepNum > this.getTotalSteps()) {
			this.evolve(stepNum - this.getTotalSteps());
			return this.generations.get(stepNum);
		} else {
			return this.generations.get(stepNum);
		}
	}
	
	/**
	 * Return the current Generation
	 * 
	 * @return the last generation available
	 */
	public Generation getCurrentGeneration() {
		return this.generations.get(this.generations.size() - 1);
	}
	
	/**
	 * 
	 * @return the Wolfram code for the rule that governs the ECA.
	 */
	public int getRuleNum() {
		return this.rule.getRuleNum();
	}
	
	/**
	 * 
	 * @return the total number of times the Automaton has been evolved
	 */
	public int getTotalSteps() {
		return this.generations.size() - 1;
	}

	// helper methods
	
	/**
	 * Save the content of the Automaton into a file. 
	 * @param fileName the file where we save the information about the Automaton
	 * @throws IOException when the file is not found
	 */
	public void saveEvolution(String fileName) throws IOException {

		FileWriter file = new FileWriter(fileName, false);

		file.write(this.toString());

		file.close();
	}

	@Override
	/**
	 * @return a String representation of the full evolution of the Automaton.
	 */
	public String toString() {
		String result = "";

		result += this.getGeneration(0).getStates(falseSymbol, trueSymbol);

		for (int i = 1; i < this.generations.size(); ++i) {
			result += System.lineSeparator() + this.getGeneration(i).getStates(falseSymbol, trueSymbol);
		}
		return result;
	}
	
	protected abstract Rule createRule (int ruleNum) throws RuleNumException;
	
	/**
	 * 
	 * @return A string representation of the table depicts the rule using the given
	 *         characters to represent false and true.
	 */
	public String ruleTableString() {
		return rule.ruleTableString(falseSymbol, trueSymbol);
	}
	
	/**
	 * Return an instance of the appropriate Automaton child given a
	 * CellularAutomaton value (ECA or TCA)
	 * 
	 * @param ca the CellularAutomaton which decides whether ECA or TCA
	 * @param ruleNum the rule number to use for evolving
	 * @param initial the initial generation to start with 
	 * @return an instance of the appropriate Automaton child
	 * @throws RuleNumException if the ruleNum is not a valid number
	 */
	public static Automaton createAutomaton(CellularAutomaton ca, int ruleNum, Generation initial)
			throws RuleNumException {
		Automaton result = null;
		try {
			if (ca == null) {
				result = null;
			} else if (CellularAutomaton.parse(ca.name()).equals(CellularAutomaton.ECA)) {
				result = new ElementaryAutomaton(ruleNum, initial);
			} else if (CellularAutomaton.parse(ca.name()).equals(CellularAutomaton.TCA)) {
				result = new TotalisticAutomaton(ruleNum, initial);
			}
		} catch (CellularAutomatonNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
