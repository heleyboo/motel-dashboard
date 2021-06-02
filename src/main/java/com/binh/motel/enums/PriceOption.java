package com.binh.motel.enums;

public enum PriceOption {
	ZERO("ZERO", "0 VND");

	private final String value;
	private final String display;

	PriceOption(String value, String display) {
		this.value = value;
		this.display = display;
	}

	public String getValue() {
		return value;
	}

	public String getDisplay() {
		return display;
	}
}
