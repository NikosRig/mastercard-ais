package hosting.io.github.nikosrig.mastercard.model;

import java.util.ArrayList;

public class Aspsp {

	public String id;
	public String name;
	public String profile;
	public String country;
	public AspspCapabilities capabilities;
	public ArrayList<String> aspspServices;
	public AspspLogo logo;
	public AspspHealth health;
	public ArrayList<AspspCredentialField> credentialFields;
}
