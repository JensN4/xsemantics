package it.xsemantics.example.expressions.tests

import com.google.inject.Inject
import it.xsemantics.example.expressions.ExpressionsInjectorProvider
import it.xsemantics.example.expressions.expressions.Model
import it.xsemantics.runtime.util.TraceUtils
import junit.framework.Assert
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(ExpressionsInjectorProvider))
class ExpressionsInterpreterTests extends ExpressionsBaseTests {

	@Inject extension ParseHelper<Model>
	
	@Inject extension TraceUtils
	
	@Test
	def void testInterpreter() {
		assertResult('''i = 10 - 5 - 1''', 0, 4)
	}
	
	@Test
	def void testInterpreterAndOr() {
		assertResult('''i = true && (false || true)''', 0, true)
	}
	
	@Test
	def void testInterpreterAndOrWithString() {
		assertResult('''i = true && (false || 'true')''', 0, true)
	}
	
	@Test
	def void testInterpreterMulti() {
		assertResult('''i = 2 * 3''', 0, 6)
	}
	
	@Test
	def void testInterpreterMultiWithString() {
		assertResult('''i = 2 * '3' ''', 0, 6)
	}
	
	@Test
	def void testInterpreterDiv() {
		assertResult('''i = 6 / 3''', 0, 2)
	}
	
	@Test
	def void testInterpreterMinus() {
		assertResult('''i = 6 - 2''', 0, 4)
	}
	
	@Test
	def void testInterpreterMinusWithString() {
		assertResult('''i = 6 - '2' ''', 0, 4)
	}
	
	@Test
	def void testSigned() {
		assertResult('''i = -2''', 0, -2)
	}
	
	@Test
	def void testSignedWithString() {
		assertResult('''i = -'2' ''', 0, -2)
	}
	
	@Test
	def void testNegation() {
		assertResult('''i = !true''', 0, false)
	}
	
	@Test
	def void testNegationWithString() {
		assertResult('''i = !'true' ''', 0, false)
	}
	
	@Test
	def void testInterpreterVariableReference() {
		assertResultAndTrace('''
		b = true && 'true'
		i = b && (false || b)''', 1, true,
'''
InterpretAndOr: [] |- b && (false || b) ~> true
 InterpretVariableRefenrence: [expected <- BooleanType] |- b ~> true
  InterpretAndOr: [expected <- BooleanType] |- true && 'true' ~> true
   InterpretBooleanLiteral: [expected <- BooleanType] |- true ~> true
   InterpretStringLiteral: [expected <- BooleanType] |- 'true' ~> true
 InterpretAndOr: [expected <- BooleanType] |- false || b ~> true
  InterpretBooleanLiteral: [expected <- BooleanType] |- false ~> false
  InterpretVariableRefenrence: [expected <- BooleanType] |- b ~> true
   InterpretAndOr: [expected <- BooleanType] |- true && 'true' ~> true
    InterpretBooleanLiteral: [expected <- BooleanType] |- true ~> true
    InterpretStringLiteral: [expected <- BooleanType] |- 'true' ~> true''')
	}
	
	def void assertResult(CharSequence program, int variableIndex, Object expectedResult) {
		assertResultAndTrace(program, variableIndex, expectedResult, null)		
	}
	
	def void assertResultAndTrace(CharSequence program, int variableIndex, 
			Object expectedResult, CharSequence expectedTrace) {
		val expression = program.parse.variables.get(variableIndex).expression
		val result = typeSystem.interpret(null, trace, expression)
		if (result.failed) {
			Assert::fail("unexpected failure: " + 
				result.ruleFailedException.failureTraceAsString
			)
		}
		Assert::assertEquals(expectedResult.toString, result.value.toString)		
		if (expectedTrace != null)
			Assert::assertEquals(expectedTrace.toString, trace.traceAsString)
	}
}