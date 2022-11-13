package com.scheible.springbootgettingstarted.singlemodule;

/**
 *
 * @author sj
 */
public abstract class Logic {

	private Logic() {
	}

	public static int calculate(int value) {
		return value + 42;
	}
}
