package it.xsemantics.example.lambda.typesystem.lambda.validator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.Check;

import com.google.inject.Inject;

import it.xtypes.runtime.AbstractTypeSystemDeclarativeValidator;
import it.xtypes.runtime.TypingJudgmentEnvironment;
import it.xtypes.typesystem.ExpressionType;

import it.xsemantics.example.lambda.lambda.LambdaPackage;

import it.xsemantics.example.lambda.typesystem.lambda.LambdaTypeSystemDefinition;

/**
 * Automatically generated by XTypeS, http://xtypes.sourceforge.net
 * Do not modify it.
 * This class should be instantiated with Google Guide injector.
 */
public class LambdaTypeSystemValidator
		extends
			AbstractTypeSystemDeclarativeValidator {
	@Inject
	protected LambdaTypeSystemDefinition typeSystem;

	protected LambdaPackage basicPackage = LambdaPackage.eINSTANCE;

	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(it.xsemantics.example.lambda.lambda.LambdaPackage.eINSTANCE);
		return result;
	}

	protected ExpressionType createBasicType(String basic) {
		return typeSystem.createBasicType(basic);
	}

	protected ExpressionType createEClassifierType(EClassifier eClassifier) {
		return typeSystem.createEClassifierType(eClassifier);
	}

	protected ExpressionType createCollectionType(ExpressionType expressionType) {
		return typeSystem.createCollectionType(expressionType);
	}

	public static final String CHECK_TYPE_FAILED_PROGRAM = "CheckTypeFailedProgram";

	@Check
	public void checkProgramType(it.xsemantics.example.lambda.lambda.Program object) {
		generateErrors(typeSystem.tryToApply(typeEnvironmentFor(object), "|-",
				":", createEClassifierType(basicPackage.getProgram()),
				createBasicType("String"), object), object,
				CHECK_TYPE_FAILED_PROGRAM);
	}

	protected TypingJudgmentEnvironment typeEnvironmentFor(
			it.xsemantics.example.lambda.lambda.Program object) {
		return getDefaultTypingJudgmentEnvironmentFor(object);
	}

}
