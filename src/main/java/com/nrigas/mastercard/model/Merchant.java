package com.nrigas.mastercard.model;

public class Merchant {

	private final String id;
	private final String name;

	public Merchant(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}
