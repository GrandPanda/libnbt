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

	public TagByte getTagByte(String name) {
		return (TagByte) get(name);
	}

	public TagByteArray getTagByteArray(String name) {
		return (TagByteArray) get(name);
	}

	public TagCompound getTagCompound(String name) {
		return (TagCompound) get(name);
	}

	public TagDouble getTagDouble(String name) {
		return (TagDouble) get(name);
	}

	public TagFloat getTagFloat(String name) {
		return (TagFloat) get(name);
	}

	public TagInt getTagInt(String name) {
		return (TagInt) get(name);
	}

	public TagIntArray getTagIntArray(String name) {
		return (TagIntArray) get(name);
	}

	public TagList getTagList(String name) {
		return (TagList) get(name);
	}

	public TagLong getTagLong(String name) {
		return (TagLong) get(name);
	}

	public TagShort getTagShort(String name) {
		return (TagShort) get(name);
	}

	public TagString getTagString(String name) {
		return (TagString) get(name);
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
