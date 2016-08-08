package com.darichey.minecraft.libnbt;

/**
 * A list of all the possible data types stored in an NBT file.
 * The {@link #ordinal()} of an element of this enum is representative of its Type ID in the NBT spec.
 */
public enum NBTType {
	TAG_END,
	TAG_BYTE,
	TAG_SHORT,
	TAG_INT,
	TAG_LONG,
	TAG_FLOAT,
	TAG_DOUBLE,
	TAG_BYTE_ARRAY,
	TAG_STRING,
	TAG_LIST,
	TAG_COMPOUND,
	TAG_INT_ARRAY
}
