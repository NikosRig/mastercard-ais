package io.github.nikosrig.model;

public class AccountNumber {

	public final String identification;
	public final String schemeName;
	public String name;

	public AccountNumber(String identification, String schemeName) {
		this.identification = identification;
		this.schemeName = schemeName;
	}
}
