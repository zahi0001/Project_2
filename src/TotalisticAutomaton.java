import java.io.IOException;

/**
 * TotalisticAutomaton represent any 1D, two-state CAs that evolve according to
 * the rules represented by TotalisticRule
 * 
 * @author Mounir Zahidi
 *
 */
public class TotalisticAutomaton extends Automaton{

	protected TotalisticAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}
	
	protected TotalisticAutomaton(String filename) throws IOException, RuleNumException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		return new TotalisticRule (ruleNum);
	}

}
