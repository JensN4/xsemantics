package it.xsemantics.example.lambda.typesystem.lambda.rules;

import it.xtypes.runtime.*;

public class SubstituteTypeVariableTypeRule extends LambdaTypeSystemRule {

	protected Variable<it.xsemantics.example.lambda.lambda.TypeVariable> var_v = new Variable<it.xsemantics.example.lambda.lambda.TypeVariable>(
			createEClassifierType(basicPackage.getTypeVariable()));

	protected Variable<it.xsemantics.example.lambda.lambda.Type> var_result = new Variable<it.xsemantics.example.lambda.lambda.Type>(
			createEClassifierType(basicPackage.getType()));

	protected TypingJudgmentEnvironment env_G = new TypingJudgmentEnvironment();

	public SubstituteTypeVariableTypeRule() {
		this("SubstituteTypeVariable", "|-", "==>");
	}

	public SubstituteTypeVariableTypeRule(String ruleName,
			String typeJudgmentSymbol, String typeStatementRelation) {
		super(ruleName, typeJudgmentSymbol, typeStatementRelation);
	}

	@Override
	public Variable<it.xsemantics.example.lambda.lambda.TypeVariable> getLeft() {
		return var_v;
	}

	@Override
	public Variable<it.xsemantics.example.lambda.lambda.Type> getRight() {
		return var_result;
	}

	@Override
	public TypingJudgmentEnvironment getEnvironment() {
		return env_G;
	}

	@Override
	public void setEnvironment(TypingJudgmentEnvironment environment) {
		if (environment != null)
			env_G = environment;
	}

	@Override
	public RuntimeRule newInstance() {
		return new SubstituteTypeVariableTypeRule("SubstituteTypeVariable",
				"|-", "==>");
	}

	@Override
	public void applyImpl() throws RuleFailedException {

		boolean or_temp_1 = false;
		// first or branch
		try {

			assignment(
					var_result,
					clone(castto(env(env_G, var_v.getValue().getTypevarName()),
							it.xsemantics.example.lambda.lambda.Type.class)));

			applySubstitutionRule(env_G, var_result, var_result);

			or_temp_1 = true;
		} catch (Throwable e) {
			registerFailure(e);
			// go on with other branches
		}

		// last or branch
		if (!or_temp_1) {
			try {
				assignment(var_result, var_v);

			} catch (Throwable e) {
				registerAndThrowFailure(e);
			}
		}

		// final check for variable initialization

	}

}
