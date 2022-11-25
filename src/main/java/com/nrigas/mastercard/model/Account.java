package com.nrigas.mastercard.model;

import java.util.ArrayList;

public class Account {

	public String resourceId;
	public String currency;
	public String accountHolderType;
	public String accountType;
	public String nameClient;
	public String name;
	public String holderName;
	public String accountNumber;
	public String schemeName;
	public String auxData;
	public String[] holderNameAddress;
	public Object[] accountPsuRelations;
	public HolderAddress holderAddress;
	public ArrayList<Balance> balances;
}
