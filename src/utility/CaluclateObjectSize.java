package utility;

import java.lang.instrument.Instrumentation;


/**
 * Code courtesy from 
 *
 */

public class CaluclateObjectSize {



	private static Instrumentation instrumentation;

	public static void premain(String args, Instrumentation inst) {
		instrumentation = inst;
	}

	public static long sizeOf(Object o) {
		
		  if (instrumentation == null) {
	            throw new IllegalStateException("Agent not initialized.");
	        }
		return instrumentation.getObjectSize(o);
	}


}
