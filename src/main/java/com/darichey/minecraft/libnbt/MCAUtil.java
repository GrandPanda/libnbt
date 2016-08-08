package com.darichey.minecraft.libnbt;

import com.darichey.minecraft.libnbt.tag.TagCompound;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.InflaterInputStream;

class MCAUtil {
	static List<TagCompound> readFromMCAFile(File file) {
		List<TagCompound> tags = new ArrayList<>();
		try {
			byte[] data = Files.readAllBytes(file.toPath()); // Get whole contents of file

			byte[] mcaHeader = new byte[8192]; // The header is the first 8 KiB
			System.arraycopy(data, 0, mcaHeader, 0, 8192);

			List<byte[]> chunkLocs = new ArrayList<>(); // Read the header into 4096 chunk locations
			for (int i = 0; i < 4096; i += 4) {
				chunkLocs.add(Arrays.copyOfRange(mcaHeader, i, i + 4));
			}

			chunkLocs.parallelStream()
					.filter(bytes -> ((bytes[2] & 0xFF) | ((bytes[1] & 0xFF) << 8) | ((bytes[0] & 0x0F) << 16)) != 0) // Don't operate on ones which have an offset of 0. These have yet to be generated.
					.forEach(bytes -> {
						int chunkPosition = ((bytes[2] & 0xFF) | ((bytes[1] & 0xFF) << 8) | ((bytes[0] & 0x0F) << 16)) * 4096; // Get the position of the chunk in bytes in the file.

						int sectorLength = bytes[3]; // How many sectors this chunk takes up. 1 sector = 4096 bytes
						byte[] chunkPayload = new byte[(4096 * sectorLength) - 5]; // The contents of the chunk. Zlib compressed
						System.arraycopy(data, chunkPosition + 5, chunkPayload, 0, (4096 * sectorLength) - 5);

						try (DataInputStream chunkPayloadStream = new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(chunkPayload)))) {
							tags.add((TagCompound) NBTUtil.readTag(chunkPayloadStream, null)); // Read the data into a TagCompound
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tags;
	}
}
