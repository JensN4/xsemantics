/*
* generated by Xtext
*/
package it.xsemantics.dsl.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import it.xsemantics.dsl.services.XsemanticsGrammarAccess;

public class XsemanticsParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private XsemanticsGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected it.xsemantics.dsl.parser.antlr.internal.InternalXsemanticsParser createParser(XtextTokenStream stream) {
		return new it.xsemantics.dsl.parser.antlr.internal.InternalXsemanticsParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "XsemanticsSystem";
	}
	
	public XsemanticsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(XsemanticsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
