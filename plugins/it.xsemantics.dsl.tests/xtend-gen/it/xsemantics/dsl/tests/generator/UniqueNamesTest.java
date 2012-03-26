package it.xsemantics.dsl.tests.generator;

import it.xsemantics.dsl.XsemanticsInjectorProvider;
import it.xsemantics.dsl.generator.UniqueNames;
import it.xsemantics.dsl.tests.generator.XsemanticsGeneratorBaseTest;
import junit.framework.Assert;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@InjectWith(XsemanticsInjectorProvider.class)
@RunWith(XtextRunner.class)
public class UniqueNamesTest extends XsemanticsGeneratorBaseTest {
  @Test
  public void testUnique() {
      UniqueNames _uniqueNames = new UniqueNames();
      final UniqueNames names = _uniqueNames;
      String _createName = names.createName("first");
      Assert.assertEquals("first", _createName);
      String _createName_1 = names.createName("first");
      Assert.assertEquals("first_1", _createName_1);
  }
}
