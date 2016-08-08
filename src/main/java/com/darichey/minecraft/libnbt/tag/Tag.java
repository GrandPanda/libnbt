package com.darichey.minecraft.libnbt.tag;

/**
 * The most basic form of storage in NBT.
 *
 * @param <T> The type of the value this Tag stores.
 */
public class Tag<T> {

	private String name;
	private T value;

	public Tag(String name, T value) {
		this.name = name;
		this.value = value;
	}

	public Tag() {

	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + (getName() == null ? "None" : ("'" + getName()) + "'") + "): " + getValue();
	}
}
