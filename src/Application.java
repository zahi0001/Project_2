import java.util.Arrays;

/**
 * The Application class includes a main method accepting command line arguments
 * to create and simulate an appropriate CA.
 * 
 * @author Mounir Zahidi
 *
 */
public class Application {

	private static final int NUM_EXPECTED_ARGS = 6;

	private static final int IDX_CA = 0;
	private static final int IDX_RULE_NUM = 1;
	private static final int IDX_FALSE_SYMBOL = 2;
	private static final int IDX_TRUE_SYMBOL = 3;
	private static final int IDX_INITIAL_GENERATION = 4;
	private static final int IDX_NUM_EVOLVE = 5;

	private static final String ARG_NAMES = "ca rule-num false-symbol true-symbol initial-generation num-evolutions";

	// Source and class file names must match, so Application can be hard-coded
	private static final String USAGE_FMT_STRING_CLASS = "Usage: java Application " + ARG_NAMES;

	// The jar file may be renamed, so this will be varied
	private static final String USAGE_FMT_STRING_JAR = "Usage: java -jar %s " + ARG_NAMES;

	private String[] appArgs;
	
	/**
	 * Validate the number of arguments passed and set the appArgs variable.
	 * 
	 * @param args the String array containing the arguments to parse
	 */
	public Application(String[] args) {
		this.validateNumArgs(args);
		this.appArgs = Arrays.copyOf(args, args.length);
	}

	/**
	 * Check to see if the number of arguments is valid.
	 * @param args the String array containing the arguments to parse
	 */
	private void validateNumArgs(String[] args) {
		if (args.length != NUM_EXPECTED_ARGS) {
			throwRuntimeExceptionWithUsageMessage();
		}
	}

	private void throwRuntimeExceptionWithUsageMessage() {
		// Implementation provided
		if (runningAsJar()) {
			// Get the path to the executing file
			String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			// Only take path after last slash (to get file name).
			// A hard-coded slash is fine since Java URLs use /
			String file = path.substring(path.lastIndexOf("/") + 1);
			throw new RuntimeException(String.format(USAGE_FMT_STRING_JAR, file));
		} else {
			throw new RuntimeException(String.format(USAGE_FMT_STRING_CLASS));
		}
	}

	private boolean runningAsJar() {
		// Implementation provided
		return Application.class.getResource("Application.class").toString().startsWith("jar");
	}

	private void parseArgs(String[] args) {
		try {
			String ca = args[IDX_CA];
			// use the enum CellularAutomaton to create the ca
			CellularAutomaton CA = CellularAutomaton.parse(ca);
			
			int ruleNum = Integer.parseInt(args[IDX_RULE_NUM]);
			char falseSymbol = args[IDX_FALSE_SYMBOL].charAt(0);
			char trueSymbol = args[IDX_TRUE_SYMBOL].charAt(0);
			String initialGen = args[IDX_INITIAL_GENERATION];
			int numEvolutions = Integer.parseInt(args[IDX_NUM_EVOLVE]);

			// turn the String [] into a boolean to make the gen (using initGen, true and
			// false symbols)
			Generation initGen = new Generation(initialGen, trueSymbol);

			// call the create Automaton (using ca, initgen, and ruleNum)
			Automaton use = Automaton.createAutomaton(CA, ruleNum, initGen);
			use.falseSymbol = falseSymbol;
			use.trueSymbol = trueSymbol;
			// call the evolve method
			use.evolve(numEvolutions);
			// and then call the toString method
			System.out.println(use.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * Runs the Application
	 */
	public void run() {
		parseArgs(this.appArgs);
	}

	/**
	 * Constructs and runs an Application using the supplied main method arguments.
	 * @param args the String array containing the arguments to parse
	 */
	public static void main(String[] args) {

		try {
			Application app = new Application(args);
			app.run();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}

	}
}
