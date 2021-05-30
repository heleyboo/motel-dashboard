package com.binh.motel.enums;

public enum RoomDirection {
	DONG("DONG", "Hướng đông"),
	TAY("TAY", "Hướng tây"),
	NAM("NAM", "Hướng nam"),
	BAC("BAC", "Hướng bắc"),
	DONGBAC("DONGBAC", "Hướng đông bắc"),
	DONGNAM("DONGNAM", "Hướng đông nam"),
	TAYBAC("TAYBAC", "Hướng tây bắc"),
	TAYNAM("TAYNAM", "Hướng tây nam");

	private final String code;
	private final String name;

	RoomDirection(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
