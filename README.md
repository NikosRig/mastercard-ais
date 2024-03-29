<div align="center">

  <h1>Mastercard Account Information Services</h1>

<!-- Badges -->
<p>
  <a href="https://www.linkedin.com/in/nick-rigas/">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="linkedin" />
  </a>
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java" />
</p>
<br />
</div>

  <p align="center">
    <span>Integrate with Mastercard Account Information APIs easily and fluently</span>
  </p>

   <h4 align="center">
     <a href="https://developer.mastercard.com/open-banking-connect/documentation/aisfeatures/overview/">Mastercard Documentation</a>
   </h4>
<br />

<!-- Table of Contents -->

## :notebook_with_decorative_cover: Table of Contents

- [Getting Started](#rocket-getting-started)
    * [Prerequisites](#round_pushpin-prerequisites)
    * [Installation](#installation)
    * [Building the MastercardAis](#building-the-mastercardais)
    * [Consents](#consents)
        * [Get Account Information Consent](#get-account-information-consent)
        * [Exchange the PSU Authorization for Access Consent](#exchange-the-psu-authorization-for-access-consent)
        * [Get Raw Account Information Consent](#get-raw-account-information-consent)
        * [Delete Account Information Consent](#delete-account-information-consent)
    * [Accounts](#accounts)
        * [Get List of Accounts](#get-list-of-accounts)
        * [Get Account Details](#get-account-details)
        * [Get Account Balances](#get-account-balances)
        * [Get Account Standing Orders](#get-account-standing-orders)
    * [Transactions](#transactions)
        * [Get Account Transaction Details](#get-account-transaction-details)
        * [Get Account Transactions](#get-account-transactions)
    * [ASPSPs](#aspsps)
        * [Get List of Available ASPSPs](#get-list-of-available-aspsps)

<br/>
<br/>

<!-- Getting Started -->

## :rocket: Getting Started

<!-- Prerequisites -->

### :round_pushpin: Prerequisites

To access the AIS features you need setup
an [Open Banking Connect Account Information Service project](https://developer.mastercard.com/open-banking-connect/documentation/aisfeatures/overview/)
and provide to the library the `consumer key`, `keystore password`, `key alias` and the `certificate`.

### Installation

```bash
<dependency>
  <groupId>io.github.nikosrig</groupId>
  <artifactId>mastercard-ais</artifactId>
  <version>1.1.0</version>
</dependency>
```

</br>

### Building the MastercardAis

| Options                           | Description                           |
| ----------------------------------| ------------------------------------- |
| `enableSandboxMode()`             | Enable sandbox mode                   |
| `withPkcs12FilePath(String)`      | Set .p12 Certificate                  |
| `withSigningKeyAlias(String)`     | Set signing key alias                 |
| `withSigningKeyPassword(String)`  | Set signing key password              |
| `withConsumerKey(String)`         | Set consumer key                      |

#### Example

```bash
	MastercardAis mastercardAis = new MastercardAis.Builder()
			.enableSandboxMode()
			.withPkcs12FilePath("./mastercard-key.p12")
			.withSigningKeyAlias("your-key-alias")
			.withSigningKeyPassword("your-key-password")
			.withConsumerKey("your-consumer-key")
			.build();
```

</br>

### Consents

#### Get Account Information Consent

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    |                
| `withTppRedirectURI(String)`                        | Call back uri                                                    |
| `addConsentPermission(ConsentPermission)`                | allPSD2, accounts, balances, transactions, standingorders                        |
| `addAccount(String iban, String currency, String schemeName)` | (Optional) Adds account                                            |
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withValidUntilDateTime(LocalDateTime)`                | (Optional) Consent valid until date time. Сan only represent future date value        |           
| `withCredentials(String iban)`                    | (Optional) Elements used to define the credentials provided by PSU            |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |

|  Consent                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `consentRequestId`                            | ID of the consent request                                |
| `scaRedirectUri`                            | Redirect URL for SCA                                        |

#### Example

```bash
	GetConsentRequest request = new GetConsentRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withMerchant("MerchantId", "MerchantName")
			.withIsLivePsuRequest(true)
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("127.0.0.1")
			.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.addConsentPermission(ConsentPermission.allPSD2)
			.addAccount("ACCNUMBR1234567", "EUR", "IBAN")
			.withTppRedirectURI("https://tpp-ob.com/callback")
			.withCredentials("DE357543513")
			.build();
	Consent consent = mastercardAis.consents().get(request);
```

<br/>

#### Exchange the PSU Authorization for Access Consent

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    | 
| `withAuthorization(String)`                        | The authorization query received after PSU has authorized the consent (e.g code=xx)    |
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |

|  Authorized Consent                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `consentId`                                | Consent Id                                        |
| `consentRequestId`                        | ID of the consent request                                    |
| `originalRequestInfo`                        | Original xRequestId given by the client on request                        |
| `signatureStatus`                        | Status of validation of ASPSP's signature                            |

#### Example

```bash
	AuthConsentRequest request = new AuthConsentRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withMerchant("MerchantId", "MerchantName")
			.withIsLivePsuRequest(true)
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("127.0.0.1")
			.withAuthorization("code=UKaccountEsbGdTB2a9MbSdt53serRsv0aUK001&state=38948933-38ae-45af-953e-25a69fefa39e")
			.build();
	AuthorizedConsent consent = mastercardAis.consents().authorize(request);
```

<br/>

#### Get Raw Account Information Consent

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    |                
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |

|  Raw Consent                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `rawConsent`                                | Raw consent data received from ASPSP and encoded Base64                |
| `originalRequestInfo`                        | Original xRequestId given by the client on request                        |

#### Example

```bash
	GetRawConsentRequest request = new GetRawConsentRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withConsentId("GFiTpF3:EBy5xGqQMatk")
			.withMerchant("MerchantId", "MerchantName")
			.withIsLivePsuRequest(true)
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("127.0.0.1")
			.build();
	RawConsent rawConsent = mastercardAis.consents().getRaw(request);
```

<br/>

#### Delete Account Information Consent

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withConsentId(String)`                        | Consent identification                                |                
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |

#### Example

```bash
	DeleteConsentRequest request = new DeleteConsentRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withMerchant("MerchantId", "MerchantName")
			.withConsentId("GFiTpF3:EBy5xGqQMatk")
			.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.build();
	mastercardAis.consents().delete(request);
```

### Accounts

#### Get List of Accounts

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    | 
| `withConsentId(String)`                        | Consent identification                                | 
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |

|  AccountList                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `accounts`                                | List of Account Models                                |

#### Example

```bash
	ListAccountsRequest request = new ListAccountsRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withMerchant("MerchantId", "MerchantName")
			.withConsentId("GFiTpF3:EBy5xGqQMatk")
			.withIsLivePsuRequest(true)
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("127.0.0.1")
			.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.build();
	AccountList accountList = mastercardAis.accounts().list(request);
```

<br/>

#### Get Account Details

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withAccountId(String)`                        | Account reference                                            |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    | 
| `withConsentId(String)`                        | Consent identification                                |
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |

|  Account                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `resourceId`                                | Account reference identification                            |
| `currency`                                | Currency code                                        |
| `accountHolderType`                            | Specifies the type of account                                |
| `accountType`                                | Specifies the sub type of account                            |
| `nameClient`                                | Accounts name client                                    |
| `name`                                    | Account name                                        |
| `holderName`                                | Holder name                                        |
| `accountNumber`                                | Account number                                    |
| `schemeName`                                | List of Account Models                                |
| `auxData`                                | List of Account Models                                |
| `accountPsuRelations`                            | Description of relations between PSU and an Account                    |
| `balances`                                | Balance Model                                        |
| `holderAddress`                                | 											|
| `holderNameAddress`                            | 											|

#### Example

```bash
	GetAccountRequest request = new GetAccountRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withMerchant("MerchantId", "MerchantName")
			.withConsentId("GFiTpF3:EBy5xGqQMatk")
			.withAccountId("aa:q648383844dhhfHhTV")
			.withIsLivePsuRequest(true)
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("127.0.0.1")
			.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.build();
	Account account = mastercardAis.accounts().get(request);
```

<br/>

#### Get Account Balances

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withAccountId(String)`                        | Account reference                                            |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    | 
| `withConsentId(String)`                        | Consent identification                                |
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |

|  Account                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `resourceId`                                | Account reference identification                            |
| `name`                                    | Account name                                        |
| `balances`                                | Balance Model                                        |

|  Balance                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `balanceType`                                | Type of balance                                    |
| `dateTime`                                | Datetime                                        |
| `creditDebitIndicator`                            | Indicated the type of the resource                            |
| `balanceAmount`                                | Balance Model                                        |

#### Example

```bash
	GetBalanceRequest request = new GetBalanceRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withMerchant("MerchantId", "MerchantName")
			.withConsentId("GFiTpF3:EBy5xGqQMatk")
			.withAccountId("aa:q648383844dhhfHhTV")
			.withIsLivePsuRequest(true)
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("127.0.0.1")
			.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.build();
	Account account = mastercardAis.accounts().balance(request);
```

<br/>

#### Get Account Standing Orders

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withAccountId(String)`                        | Account reference                                            |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    | 
| `withConsentId(String)`                        | Consent identification                                |
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |
| `withLimit(Integer)`                        | (Optional) Number of transactions to be returned                        |
| `withOffset(String)`                        | (Optional) Offset value obtained from previous calls to get transactions              |

|  Standing Orders List                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `offset`                                    | Offset value obtained from previous calls to get transactions                |
| `dateTime`                                | Datetime                                        |
| `standingOrders`                                | List of standing order models                                |

|  Standing Order                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `standingOrderId`                            | Standing order identification                                |
| `firstPayment`                                | StandingOrderPayment model                                |
| `nextPayment`                                | StandingOrderPayment model                                |
| `finalPayment`                                | StandingOrderPayment model                                |
| `schedule`                                | StandingOrderSchedule model                                |
| `recipientAccount`                            | AccountNumber model                                    |
| `reference`                                | Reference                                        |
| `status`                                    | Status                                        |

|  AccountNumber                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `date`                                    | Payment date                                        |
| `currency`                                | Currency code                                        |
| `amount`                                    | Amount value                                        |

|  StandingOrderPayment                            | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `identification`                                | Account identification                                |
| `schemeName`                                | Account schema                                    |
| `name`

|  StandingOrderSchedule                            | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `frequency`                                | Scheduling frequency                                    |

#### Example

```bash
	ListStandingOrdersRequest request = new ListStandingOrdersRequestBuilder()
			.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withMerchant("MerchantId", "MerchantName")
			.withConsentId("GFiTpF3:EBy5xGqQMatk")
			.withIsLivePsuRequest(true)
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("127.0.0.1")
			.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withLimit(20)
			.withAccountId("aa:q648383844dhhfHhTV")
			.withOffset("ofset4prev$earch12345")
			.build();
	StandingOrdersList standingOrdersList = mastercardAis.standingOrders().list(request);
```

<br/>

### Transactions

#### Get Account Transaction Details

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withAccountId(String)`                        | Account reference                                            |
| `withTransactionId(String)`                        | Transaction reference                                            |
| `withConsentId(String)`                        | Consent identification                                |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    | 
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |

|  Transaction                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `transactionId`                                | Transaction identification                                |
| `bookingDateTime`                            | Date and time when a transaction entry is posted to an account.            |
| `remittanceInformationUnstructured`                    | Description of the payment                                |
| `status`                                    | Status of the transaction.                                |
| `creditDebitIndicator`                            | Indicated the type of the resourc                            |
| `tradeDate`                                | Transaction trade date time.                                |
| `senderAccountNumber`                            | Sender account number                                    |
| `recipientAccountNumber`                            | Recepient account number                                |
| `recipientAccountMassPayment`                        | Recipient mass payment account number                            |
| `recipientBankBicOrSwift`                        | Recipient bank BIC/SWIFT code                                |
| `recipientBankName`                            | Recipient bank name                                    |
| `recipientBankCode`                            | Recipient bank code                                    |
| `recipientBankCountryCode`                        | Recipient bank country code                                |
| `senderAccountMassPayment`                        | Sender mass payment account number.                            |
| `senderBankBicOrSwift`                            | Sender bank BIC/SWIFT code                                |
| `senderBankName`                                | Sender bank name                                    |
| `senderBankCode`                                | Sender bank code                                    |
| `senderBankCountryCode`                            | Sender bank country code                                |
| `transactionType`                            | Transaction type                                    |
| `postTransactionBalance`                            | Post transaction balance                                |
| `mcc`                                    | Merchant Category Code of the Card Acceptor                        |
| `rejectionReason`                            | Rejection reason                                    |
| `rejectionDate`                                | Rejection date                                    |
| `holdExpirationDate`                            | Hold expiration date                                    |
| `senderName`                                | Name of the debtor in current transaction                        |
| `recipientName`                                | Name of the creditor in current transaction (recipient's name)            |
| `senderAccountNumberScheme`                        | Scheme name of the account number that belongs to the sender/debtor            |
| `senderNameAddress`                            | Balance Model                                        |
| `recipientAccountNumberScheme`                        | Scheme name of the account number that belongs to the recipient/creditor        |
| `transactionAmount`                            | TransactionAmount Model                                |

#### Example

```bash
	GetTransactionRequest request = new GetTransactionRequestBuilder()
			.withConsentId("MatkBJbqtZ8sPNznYtfV:5g")
			.withAspspId("b806ae68-a45b-49d6-b25a-69fdb81dede6")
			.withIsLivePsuRequest(false)
			.withMerchant("MerchantId", "MerchantName")
			.withPsuAgent("PostmanRuntime/7.20.1")
			.withPsuIPAddress("192.168.0.1")
			.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
			.withAccountId("qqCfw:XwAa:665hs5:r55dM")
			.withTransactionId("7ccs6s5:r55a:4MctP")
			.build();
	Transaction transaction = mastercardAis.transactions().get(request);

```

<br/>

### Get Account Transactions

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`                            | Identification of ASPSP                                            |
| `withAccountId(String)`                        | Account reference                                            |
| `withIsLivePsuRequest(Boolean)`                    | Flag indicating if request is initiated by PSU                    | 
| `withConsentId(String)`                        | Consent identification                                |
| `withPsuAgent(String)`                    | (Optional) PSU's browser agent details                            |
| `withPsuIPAddress(String)`                        | (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true    | 
| `withPsuTppCustomerId(String)`                    | (Optional) Identifier of the PSU in TPP system                                |
| `withMerchant(String MerchantId, String MerchantName)`        | (Optional) Merchant id and name                                    |
| `withLimit(Integer)`                        | (Optional) Number of transactions to be returned                        |
| `withOffset(String)`                        | (Optional) Offset value obtained from previous calls to get transactions              |
| `withBookingDateFrom(String)`                    | (Optional) Inclusive lower bound of the transaction booking date                    |
| `withbookingDateTo(String)`                    | (Optional) Inclusive upper bound of the transaction booking date                |

|  Transactions List                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `offset`                                    | Offset value obtained from previous calls to get transactions                |
| `transactions`                                | List of transaction models                                |

#### Example

```bash
	ListTransactionsRequest request = new ListTransactionsRequestBuilder()
		.withAccountId("aa:q648383844dhhfHhTV")
		.withConsentId("GFiTpF3:EBy5xGqQMatk")
		.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
		.withIsLivePsuRequest(true)
		.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
		.withPsuIPAddress("127.0.0.1")
		.withPsuAgent("PostmanRuntime/7.20.1")
		.withMerchant("MerchantId", "MerchantName")
		.withLimit(20)
		.withBookingDateFrom("2018-09-23")
		.withBookingTo("2018-11-21")
		.build();
	TransactionList transactionList = mastercardAis.transactions().list(request);

```

<br/>

### ASPSPs

#### Get List of Available ASPSPs

|  Request Options                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withId(String)`                            | (Optional) Filter by internal id of ASPSP                                        |
| `withName(String)`                        | (Optional) Filter by name of ASPSP                                        |
| `withCountry(Boolean)`                    | (Optional) Filter by country code                                | 
| `withLimit(String)`                        | (Optional) Number of rows per page                                |
| `addAdditionalData(String)`                | (Optional) AdditionalData Model                                    |
| `withOffset(String)`                        | (Optional) Pagination offset, the value returned from the previous request            | 

|  AdditionalData                                | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `capabilities`                                | 											|
| `logo`                                    | 											|
| `health`                                    | 											|
| `credentials`                                | 											|

|  AspspList                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `offset`                                    | Pagination offset                                    |
| `aspsps`                                    | Aspsp Model                                        |

|  AspspList                                    | Description                                                |
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `id`                                    | Pagination offset                                    |
| `name`                                    | Name of the ASPSP                                    |
| `profile`                                | ASPSP profile                                        |
| `country`                                | Country code                                        |
| `capabilities`                                | AspspCapabilities Model                                |
| `logo`                                    | AspspLogo Model                                    |
| `health`                                    | AspspHealth Model                                    |
| `credentialFields`                            | List of AspspCredentialField Models                            |
| `offset`                                    | Offset used in the next request to get next page                    |

#### Example

```bash
	ListAspspsRequest request = new ListAspspsRequestBuilder()
			.withId("018d02c8-9be6-4363-9f3a-9009b2c89768")
			.withName("Apollo Bank")
			.withCountry("GB")
			.withLimit(20)
			.addAdditionalData(AdditionalData.capabilities)
			.addAdditionalData(AdditionalData.credentials)
			.addAdditionalData(AdditionalData.health)
			.addAdditionalData(AdditionalData.logo)
			.build();
	AspspList aspspList = mastercardAis.aspsps().list(request);

```

<br/>

### :handshake: Contact

Nikos Rigas - nikosrigas95@gmail.com

### Licence

Distributed under the MIT License

