package test.net.sourceforge.pmd.rules;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.rules.ExcessiveImportsRule;

public class ExcessiveImportsRuleTest extends RuleTst {

    private Rule rule;

    public ExcessiveImportsRuleTest() {
        super();
        rule = new ExcessiveImportsRule();
        rule.addProperty("minimum", "20");
    }

    public void testSimpleBad() throws Throwable {
        super.runTestFromFile("ExcessiveImports1.java", 1, rule);
    }

    public void testSimpleOK() throws Throwable {
        super.runTestFromFile("ExcessiveImports2.java", 0, rule);
    }
}
