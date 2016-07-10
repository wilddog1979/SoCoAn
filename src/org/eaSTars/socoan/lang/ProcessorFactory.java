package org.eaSTars.socoan.lang;

import java.util.function.Function;

public abstract class ProcessorFactory {

	public abstract Function<Context, Fragment> createProcessor(String id);
}
