package com.darichey.minecraft.libnbt;

import com.darichey.minecraft.libnbt.tag.TagCompound;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class LibNBT {
	/**
	 * Reads an NBT file into a TagCompound.
	 *
	 * @param file         The file to read
	 * @param isCompressed Whether the file passed is gzip compressed or not.
	 * @return A TagCompound representing the contents of the file.
	 * @throws MalformedNBTException
	 */
	public static TagCompound readFromNBTFile(File file, boolean isCompressed) throws MalformedNBTException {
		try {
			InputStream stream = isCompressed ? new GZIPInputStream(new FileInputStream(file)) : new FileInputStream(file); // Create the correct stream depending on isCompressed
			DataInputStream is = new DataInputStream(stream);
			TagCompound compound = (TagCompound) NBTUtil.readTag(is, null);  // Read the TagCompound. While the type is technically known, we have not read it yet, so pass null.

			stream.close();
			is.close();

			return compound;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads an MCA file into a list of TagCompounds.
	 *
	 * @param file The file to read.
	 * @return A list of TagCompounds representing the contents of the file.
	 */
	public static List<TagCompound> readFromMCAFile(File file) {
		return MCAUtil.readFromMCAFile(file); // Whole method could be placed here but it's not as pretty :(
	}
}
