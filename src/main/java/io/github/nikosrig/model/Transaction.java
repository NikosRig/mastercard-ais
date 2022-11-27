package io.github.nikosrig.model;

import java.util.ArrayList;

public class Transaction {

	public String transactionId;
	public String bookingDateTime;
	public String remittanceInformationUnstructured;
	public String status;
	public String creditDebitIndicator;
	public String tradeDate;
	public String senderAccountNumber;
	public String recipientAccountNumber;
	public String recipientAccountMassPayment;
	public String recipientBankBicOrSwift;
	public String recipientBankName;
	public String recipientBankCode;
	public String recipientBankCountryCode;
	public String senderAccountMassPayment;
	public String senderBankBicOrSwift;
	public String senderBankName;
	public String senderBankCode;
	public String senderBankCountryCode;
	public String transactionType;
	public Float postTransactionBalance;
	public String mcc;
	public String rejectionReason;
	public String rejectionDate;
	public String holdExpirationDate;
	public String senderName;
	public String recipientName;
	public String senderAccountNumberScheme;
	public ArrayList<String> senderNameAddress;
	public String recipientAccountNumberScheme;
	public TransactionAmount transactionAmount;
	public ArrayList<String> initiatorNameAddress;
	public ArrayList<String> recipientNameAddress;
	public ArrayList<String> recipientBankAddress;
	public ArrayList<String> senderBankAddress;
}
