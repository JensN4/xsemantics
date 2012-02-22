/**
 * <copyright>
 * </copyright>
 *
 * $Id: ProgramTest.java,v 1.1 2009-11-02 11:25:13 bettini Exp $
 */
package it.xsemantics.example.fj.tests;

import it.xsemantics.example.fj.fj.Class;
import it.xsemantics.example.fj.fj.Field;
import it.xsemantics.example.fj.fj.FjFactory;
import it.xsemantics.example.fj.fj.Method;
import it.xsemantics.example.fj.fj.MethodBody;
import it.xsemantics.example.fj.fj.Parameter;
import it.xsemantics.example.fj.fj.Program;
import it.xsemantics.example.fj.fj.Selection;
import it.xsemantics.example.fj.fj.This;
import it.xsemantics.example.fj.fj.Type;
import it.xsemantics.example.fj.util.ClassFactory;
import it.xsemantics.example.fj.util.FjTypeUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import junit.textui.TestRunner;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Program</b></em>'. <!-- end-user-doc -->
 * 
 * @generated NOT
 */
public class FormattingTest extends TestWithLoader {

	/**
	 * The fixture for this Program test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected Program fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(FormattingTest.class);
	}

	/**
	 * Constructs a new Program test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FormattingTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Program test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Program fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Program test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Program getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setFixture(FjFactory.eINSTANCE.createProgram());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Test the formatting of a class model directly
	 * 
	 * @throws IOException
	 */
	public void testResourceWithOneClass() throws IOException {
		Resource resource = createResource();

		resource.getContents().add(fixture);
		Class cl = ClassFactory.createClass("A");
		fixture.getClasses().add(cl);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		resource.save(outputStream, null);
		System.out.println("saved: " + outputStream.toString());
		assertEquals("class A {\n}", outputStream.toString());
	}

	/**
	 * Test the formatting of a more complex program model directly
	 * 
	 * @throws IOException
	 */
	public void testFieldsAndMethods() throws IOException {
		Resource resource = createResource();

		resource.getContents().add(fixture);

		Class cl = ClassFactory.createClass("A");
		fixture.getClasses().add(cl);

		Type clType = FjTypeUtils.createClassType(cl);

		Field field = FjFactory.eINSTANCE.createField();
		field.setName("f1");
		field.setType(clType);
		cl.getMembers().add(field);
		field = FjFactory.eINSTANCE.createField();
		field.setName("f2");
		clType = FjTypeUtils.createClassType(cl);
		field.setType(clType);
		cl.getMembers().add(field);

		clType = FjTypeUtils.createClassType(cl);
		Method method = FjFactory.eINSTANCE.createMethod();
		method.setName("myMeth");
		method.setType(clType);
		clType = FjTypeUtils.createClassType(cl);
		Parameter parameter = FjFactory.eINSTANCE.createParameter();
		parameter.setName("p1");
		parameter.setType(clType);
		method.getParams().add(parameter);
		clType = FjTypeUtils.createClassType(cl);
		parameter = FjFactory.eINSTANCE.createParameter();
		parameter.setName("p2");
		parameter.setType(clType);
		method.getParams().add(parameter);

		This t = FjFactory.eINSTANCE.createThis();
		t.setVariable("this");

		Selection selection = FjFactory.eINSTANCE.createSelection();
		selection.setReceiver(t);
		selection.setMessage(field);
		MethodBody methodBody = FjFactory.eINSTANCE.createMethodBody();
		methodBody.setExpression(selection);

		method.setBody(methodBody);

		cl.getMembers().add(method);
		
		cl = ClassFactory.createClass("B");
		fixture.getClasses().add(cl);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		resource.save(outputStream, null);
		System.out.println("saved: " + outputStream.toString());
		assertEquals(
				"class A {\n\tA f1;\n\tA f2;\n\tA myMeth(A p1, A p2) { return this.f2; }\n}\n\nclass B {\n}",
				outputStream.toString());
	}

} // FormattingTest
