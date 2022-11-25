<div align="center">

  <h1>Mastercard Account Infomation Services</h1>
  
<!-- Badges -->
<p>
  <a href="https://www.linkedin.com/in/nick-rigas/">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="linkedin" />
  </a>
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle" />
</p>
<br />
</div>

  <p align="center">
    <span>Integrate with Mastercard Account Information APIs easily and fluently</span>
  </p>
  
   <h4 align="center">
     <a href="https://developer.mastercard.com/open-banking-connect/documentation/aisfeatures/overview/">Documentation</a>
   </h4>
<br />

<!-- Table of Contents -->
## :notebook_with_decorative_cover: Table of Contents

- [Getting Started](#loudspeaker-getting-started)
  * [Prerequisites](#round_pushpin-prerequisites)
  * [Building the MastercardAis](#building-the-mastercardais)
  * [Consents](#consents)
    * [Get Account Information Consent](#get-account-information-consent)


<br/>

<!-- Features -->
### :rocket: Features

- Get List of Available ASPSPs
- Delete Account Information Consent
- Get Raw Account Information Consent
- Exchange the PSU Authorization for Access Consent
- Get List of Accounts
- Get Account Details
- Get Account Balances
- Get Account Transactions
- Get Account Transaction Details
- Get Account Standing Orders

<br/>

<!-- Getting Started -->
## :loudspeaker: Getting Started

<!-- Prerequisites -->
### :round_pushpin: Prerequisites

To access the AIS features you need setup an [Open Banking Connect Account Information Service project](https://developer.mastercard.com/open-banking-connect/documentation/aisfeatures/overview/) and provide to the library the `consumer key`, `keystore password`, `key alias` and the `certificate`.

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
			.withPkcs12FilePath("./your/mastercard-certificate.p12")
			.withSigningKeyAlias("your-key-alias")
			.withSigningKeyPassword("your-key-password")
			.withConsumerKey("your-consumer-key")
			.build();
```

</br>

### Consents


#### Get Account Information Consent



|  Request Options	                   	 		| Description                           				   		|
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `withAspspId(String)`             				| Identification of ASPSP                   			           		|
| `withMerchant(String MerchantId, String MerchantName)`     	| Merchant id and name                  				   		|
| `withIsLivePsuRequest(Boolean)`     				| Flag indicating if request is initiated by PSU   			   		|                
| `withTppRedirectURI(String)`         				| Call back uri                     				           		|
| `addConsentPermission(ConsentPermission)`         		| allPSD2, accounts, balances, transactions, standingorders                		|
| `addAccount(String iban, String currency, String schemeName)` | (Optional) Adds account                      				   		|
| `withPsuAgent(String)`  					| (Optional) PSU's browser agent details        					|
| `withPsuTppCustomerId(String)`         			| (Optional) Identifier of the PSU in TPP system                      		   	|
| `withPsuIPAddress(String)`         				| (Optional) IP address of PSU's terminal device. Required when isLivePsuRequest=true 	| 
| `withValidUntilDateTime(LocalDateTime)`         		| (Optional) Consent valid until date time. Сan only represent future date value      	|           
| `withCredentials(String iban)`         			| (Optional) Elements used to define the credentials provided by PSU      		|


|  Consent Mode	                   	 			| Description                           				   		|
| --------------------------------------------------------------| --------------------------------------------------------------------------------------|
| `consentRequestId`             				| ID of the consent request. 								|
| `scaRedirectUri`     						| Redirect URL for SCA              				   			|



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



