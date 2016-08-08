package com.darichey.minecraft.libnbt.tag;

import java.util.Arrays;

/**
 * A Tag which holds an array of integers.
 */
public class TagIntArray extends Tag<int[]> {
	public TagIntArray(String name, int[] value) {
		super(name, value);
	}

	@Override
	public String toString() {
		return getName() + ": " + Arrays.toString(getValue());
	}
}
