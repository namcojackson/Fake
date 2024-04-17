package oracle.apps.fnd.util;

public class NameValueType {
	public static final String RCS_ID = "$Header: NameValueType.java 115.2 1999/12/07 12:06:50 pkm ship $";
	public String mName;
	public String mValue;
	public String mType;

	public NameValueType() {
	}

	public NameValueType(String paramString1, String paramString2,
			String paramString3) {
		this.mName = paramString1;
		this.mValue = paramString2;
		this.mType = paramString3;
	}

	public NameValueType(String paramString1, String paramString2) {
		this.mName = paramString1;
		this.mValue = paramString2;
		this.mType = null;
	}

	public void dump() {
		System.out.println("NameValueType object dump:");
		System.out.println("\tName:" + this.mName);
		System.out.println("\tValue:" + this.mValue);
		System.out.println("\tType:" + this.mType);
	}

	public String getName() {
		return this.mName;
	}

	public String getType() {
		return this.mType;
	}

	public String getValue() {
		return this.mValue;
	}
}