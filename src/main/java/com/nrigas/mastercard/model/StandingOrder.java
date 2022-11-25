package com.nrigas.mastercard.model;

public class StandingOrder {

	public String standingOrderId;
	public StandingOrderPayment firstPayment;
	public StandingOrderPayment nextPayment;
	public StandingOrderPayment finalPayment;
	public StandingOrderSchedule schedule;
	public AccountNumber recipientAccount;
	public String reference;
	public String status;
}
