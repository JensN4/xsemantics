package it.xsemantics.example.lambda.tests;

import it.xsemantics.example.lambda.lambda.Abstraction;
import it.xsemantics.example.lambda.lambda.ArrowType;
import it.xsemantics.example.lambda.lambda.Program;
import it.xsemantics.example.lambda.lambda.TypeVariable;
import it.xtypes.runtime.TypeSystemResult;

import static it.xsemantics.example.lambda.tests.util.LambdaTestsUtil.*;

public class OccurCheckTest extends LambdaAbstractTests {

	public void testVarNotOccurInArrow() throws Exception {
		String programString = "lambda x : X . lambda y : int -> string. x";
		Program program = getLambdaProgram(programString);
		Abstraction abstraction = getAbstraction(program.getTerm());
		TypeVariable typeVariable = getTypeVariable(abstraction.getParam()
				.getType());
		ArrowType arrowType = getArrowType(getAbstraction(abstraction.getTerm())
				.getParam().getType());
		TypeSystemResult<Boolean> result = typeSystem.checkNotoccur(typeVariable, arrowType);
		printApplicationTrace(result);
	}
	
	public void testVarNotOccurInArrow2() throws Exception {
		String programString = "lambda x : X . lambda y : (int -> string) -> (string -> int). x";
		Program program = getLambdaProgram(programString);
		Abstraction abstraction = getAbstraction(program.getTerm());
		TypeVariable typeVariable = getTypeVariable(abstraction.getParam()
				.getType());
		ArrowType arrowType = getArrowType(getAbstraction(abstraction.getTerm())
				.getParam().getType());
		TypeSystemResult<Boolean> result = typeSystem.checkNotoccur(typeVariable, arrowType);
		assertApplicationTrace("./output/notoccur_in_arrow_arrow.txt", result);
	}
	
	public void testVarOccurInArrow2() throws Exception {
		String programString = "lambda x : X . lambda y : (int -> string) -> (string -> X). x";
		Program program = getLambdaProgram(programString);
		Abstraction abstraction = getAbstraction(program.getTerm());
		TypeVariable typeVariable = getTypeVariable(abstraction.getParam()
				.getType());
		ArrowType arrowType = getArrowType(getAbstraction(abstraction.getTerm())
				.getParam().getType());
		TypeSystemResult<Boolean> result = typeSystem.checkNotoccur(typeVariable, arrowType);
		assertFailureOutput("./output/occur_in_arrow_arrow.txt", result);
	}
}
