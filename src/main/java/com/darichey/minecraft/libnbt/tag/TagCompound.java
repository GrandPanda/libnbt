package com.darichey.minecraft.libnbt.tag;

import java.util.List;

/**
 * A list of named tags.
 */
public class TagCompound extends Tag<List<Tag>> {
	public TagCompound(String name, List<Tag> value) {
		super(name, value);
	}

	/**
	 * Gets a tag in the compound by name.
	 *
	 * @param name The name of the tag.
	 * @return The tag with that name or null if one could not be found.
	 */
	public Tag get(String name) {
		return getValue().stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		String string = getClass().getSimpleName() + "(" + (getName() == null ? "None" : ("'" + getName() + "'")) + "):\n{\n";
		for (Tag tag : getValue()) {
			string += tag.toString() + "\n";
		}
		string += "}";
		return string;
	}
}
