/*
 * generated by Xtext
 */
package it.xsemantics.example.expressions.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ExpressionsExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return it.xsemantics.example.expressions.ui.internal.ExpressionsActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return it.xsemantics.example.expressions.ui.internal.ExpressionsActivator.getInstance().getInjector("it.xsemantics.example.expressions.Expressions");
	}
	
}
