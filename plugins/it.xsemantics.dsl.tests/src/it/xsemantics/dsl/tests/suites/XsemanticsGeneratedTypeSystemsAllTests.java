package it.xsemantics.dsl.tests.suites;

import it.xsemantics.dsl.tests.generator.fj.FjActualGeneratedTypeSystemTests;
import it.xsemantics.dsl.tests.generator.fj.FjActualGeneratedValidatorTests;
import it.xsemantics.dsl.tests.generator.fj.FjAltGeneratedTypeSystemTests;
import it.xsemantics.dsl.tests.generator.fj.FjAltGeneratedValidatorTests;
import it.xsemantics.dsl.tests.generator.fj.FjFirstGeneratedTypeSystemTests;
import it.xsemantics.dsl.tests.generator.fj.FjFirstGeneratedValidatorTests;
import it.xsemantics.dsl.tests.generator.fj.FjSepGeneratedTypeSystemTests;
import it.xsemantics.dsl.tests.generator.fj.FjSepGeneratedValidatorTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FjFirstGeneratedTypeSystemTests.class,
	FjAltGeneratedTypeSystemTests.class,
	FjFirstGeneratedValidatorTests.class,
	FjAltGeneratedValidatorTests.class,
	FjActualGeneratedTypeSystemTests.class,
	FjActualGeneratedValidatorTests.class,
	FjSepGeneratedTypeSystemTests.class,
	FjSepGeneratedValidatorTests.class
})
public class XsemanticsGeneratedTypeSystemsAllTests {

}
