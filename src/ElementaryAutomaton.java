import java.io.IOException;

/**
 * ElementaryAutomaton represent any 1D, two-state CAs that evolve according to
 * the rules represented by ElementaryRule
 * 
 * @author Mounir Zahidi
 *
 */
public class ElementaryAutomaton extends Automaton {

	protected ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}

	protected ElementaryAutomaton(String filename) throws IOException, RuleNumException {
		super(filename);
	}
	
	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		return new ElementaryRule(ruleNum);
	}
	

}
