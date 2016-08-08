package com.darichey.minecraft.libnbt;

import java.io.IOException;

/**
 * An exception thrown in the case of any malformation in the structure of an NBT data file.
 */
class MalformedNBTException extends IOException {
	MalformedNBTException(String message) {
		super(message);
	}
}
