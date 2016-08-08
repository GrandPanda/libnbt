package com.darichey.minecraft.libnbt.tag;

import java.util.Arrays;

/**
 * A Tag which holds an array of bytes.
 */
public class TagByteArray extends Tag<byte[]> {
	public TagByteArray(String name, byte[] value) {
		super(name, value);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "('" + getName() + "'): " + Arrays.toString(getValue());
	}
}
