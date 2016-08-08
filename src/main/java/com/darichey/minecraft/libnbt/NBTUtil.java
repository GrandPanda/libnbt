package com.darichey.minecraft.libnbt;

import com.darichey.minecraft.libnbt.tag.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to interact with the contents of an NBT file.
 */
class NBTUtil {

	/**
	 * Read a tag with either known or unknown type.
	 *
	 * @param stream The data stream to read the tag from.
	 * @param type   The type of the tag to be read. If this data has not yet been read, pass null.
	 * @return The tag representation of the read data.
	 * @throws IOException
	 */
	static Tag readTag(DataInputStream stream, NBTType type) throws IOException {
		boolean named = false; // Will remain false if this tag is to be put into a TagList (elements are unnamed)
		if (type == null) { // Determine the type of the tag if it was not passed.
			type = readType(stream);
			if (type == NBTType.TAG_END) return null;
			named = true; // Weird workaround for TagLists. If the type was found, then there is a name because elements in a TagList are not prefixed with type.
		}
		String name = named ? readString(stream) : null;
		switch (type) { // Read the correct tag based on the type
			case TAG_BYTE:
				return new TagByte(name, stream.readByte());
			case TAG_SHORT:
				return new TagShort(name, stream.readShort());
			case TAG_INT:
				return new TagInt(name, stream.readInt());
			case TAG_LONG:
				return new TagLong(name, stream.readLong());
			case TAG_FLOAT:
				return new TagFloat(name, stream.readFloat());
			case TAG_DOUBLE:
				return new TagDouble(name, stream.readDouble());
			case TAG_BYTE_ARRAY:
				return new TagByteArray(name, readByteArray(stream));
			case TAG_STRING:
				return new TagString(name, readString(stream));
			case TAG_LIST:
				return new TagList(name, readList(stream));
			case TAG_COMPOUND:
				return new TagCompound(name, readTagCompound(stream));
			case TAG_INT_ARRAY:
				return new TagIntArray(name, readIntArray(stream));
			default:
				return null;
		}
	}

	private static byte[] readByteArray(DataInputStream stream) throws IOException {
		byte[] byteArray = new byte[stream.readInt()];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = stream.readByte();
		}
		return byteArray;
	}

	private static String readString(DataInputStream stream) throws IOException {
		int length = stream.readUnsignedShort();
		String string = "";
		for (int i = 0; i < length; i++) {
			string += (char) stream.readByte();
		}
		return string;
	}

	private static List<Tag> readList(DataInputStream stream) throws IOException {
		List<Tag> tags = new ArrayList<>();
		NBTType type = readType(stream);
		int length = stream.readInt();

		for (int i = 0; i < length; i++) {
			tags.add(readTag(stream, type));
		}
		return tags;
	}

	private static List<Tag> readTagCompound(DataInputStream stream) throws IOException {
		List<Tag> tags = new ArrayList<>();
		Tag tag;
		while ((tag = readTag(stream, null)) != null) {
			tags.add(tag);
		}

		return tags;
	}

	private static int[] readIntArray(DataInputStream stream) throws IOException {
		int[] intArray = new int[stream.readInt()];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = stream.readInt();
		}
		return intArray;
	}

	private static NBTType readType(DataInputStream stream) throws IOException {
		int typeValue = stream.readUnsignedByte();
		if (typeValue < 0 || typeValue > 11) throw new MalformedNBTException("Unrecognized data type.");
		return NBTType.values()[typeValue];
	}
}
