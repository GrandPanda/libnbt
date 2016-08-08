package com.darichey.minecraft.libnbt.tag;

import java.util.List;

/**
 * A collection of nameless tags of the same type.
 * A tag is "nameless" if its name == null.
 */
public class TagList extends Tag<List<Tag>> {
	public TagList(String name, List<Tag> value) {
		super(name, value);
	}

	public Tag get(int index) {
		return getValue().get(index);
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
