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
  * [Initialize MastercardAis](#initialize-mastercardais)
<br/>

<!-- Features -->
### :rocket: Features

- Get List of Available ASPSPs
- Get Account Information Consent
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

### Initialize MastercardAis

| Methods                           | Description                           |
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



