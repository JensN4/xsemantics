/**
 * 
 */
package it.xsemantics.dsl.tests.runtime;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.Result3;
import it.xsemantics.runtime.RuleFailedException;

/**
 * @author bettini
 * 
 */
public class ResultTests {

	protected Result<Object> result;
	
	@Before
	public void setUp() {
		result = new Result<Object>(null);
	}

	@Test
	public void testNoFailure() {
		Assert.assertFalse(result.failed());
	}
	
	@Test
	public void testFailure() {
		result = new Result<Object>(new RuleFailedException());
		Assert.assertTrue(result.failed());
	}

	@Test
	public void testResult3() {
		Result3<Object,Object,Object> result3 = new Result3<Object, Object, Object>("foo");
		Assert.assertEquals("foo", result3.getFirst());
		Assert.assertNull(result3.getSecond());
		Assert.assertNull(result3.getThird());
	}

}
