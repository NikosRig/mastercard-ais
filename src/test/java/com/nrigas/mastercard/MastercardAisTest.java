package com.nrigas.mastercard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class MastercardAisTest {

	@Before
	public void setUp() {

	}

	@Test
	public void testExpectExceptionWhenPck12FilePathIsWrong() {

		MastercardAis.Builder builder = new MastercardAis
				.Builder()
				.isSandboxMode(true)
				.withPkcs12FilePath("wrong file")
				.withConsumerKey("consumerKey")
				.withSigningKeyAlias("signingKey")
				.withSigningKeyPassword("password");

		Assert.assertThrows(FileNotFoundException.class, builder::build);
	}

	@Test
	public void testExpectExceptionWhenConsumerKeyIsMissing() {

		MastercardAis.Builder builder = new MastercardAis
				.Builder()
				.isSandboxMode(true)
				.withSigningKeyAlias("signingKey")
				.withSigningKeyPassword("password")
				.withPkcs12FilePath("file");

		Assert.assertThrows(Exception.class, builder::build);
	}

	@Test
	public void testExpectExceptionWhenSigningKeyMissing() {

		MastercardAis.Builder builder = new MastercardAis
				.Builder()
				.isSandboxMode(true)
				.withConsumerKey("consumerKey")
				.withSigningKeyPassword("password");

		Assert.assertThrows(Exception.class, builder::build);
	}

	@Test
	public void testExpectExceptionWhenSigningPasswordMissing() {

		MastercardAis.Builder builder = new MastercardAis
				.Builder()
				.isSandboxMode(true)
				.withConsumerKey("consumerKey")
				.withSigningKeyAlias("signingKey");

		Assert.assertThrows(Exception.class, builder::build);
	}
}
