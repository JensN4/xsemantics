/**
 * 
 */
package it.xsemantics.example.lambda.tests;

import java.util.List;
import java.util.Set;

import it.xsemantics.example.lambda.lambda.Program;
import it.xsemantics.example.lambda.lambda.TypeVariable;
import it.xsemantics.example.lambda.xsemantics.LambdaTypeVariableFinder;

/**
 * @author bettini
 * 
 */
public class TypeVariableTests extends LambdaAbstractTests {
	LambdaTypeVariableFinder typeVariableFinder = new LambdaTypeVariableFinder();

	public void testNoTypeVariables() throws Exception {
		String programString = "lambda x : int . x x";
		Program program = getLambdaProgram(programString);
		List<TypeVariable> typeVariables = typeVariableFinder
				.findTypeVariables(program.getTerm());
		assertEquals(0, typeVariables.size());
	}

	public void testTypeVariables() throws Exception {
		String programString = "lambda x : X1 . lambda y . lambda z : a . y";
		Program program = getLambdaProgram(programString);
		List<TypeVariable> typeVariables = typeVariableFinder
				.findTypeVariables(program.getTerm());
		assertEquals(2, typeVariables.size());
		assertEquals("X1", typeVariables.get(0).getTypevarName());
		assertEquals("a", typeVariables.get(1).getTypevarName());
	}

	public void testTypeVariableNames() throws Exception {
		String programString = "lambda x : X1 . lambda y : a. lambda z : a . y";
		Program program = getLambdaProgram(programString);
		Set<String> typeVariableNames = typeVariableFinder
				.getTypeVariableNames(program.getTerm());
		assertEquals(2, typeVariableNames.size());
		assertTrue(typeVariableNames.contains("X1"));
		assertTrue(typeVariableNames.contains("a"));
	}
}
