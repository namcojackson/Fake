package oracle.apps.fnd.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

public class ParameterList implements Enumeration {
	public static final String RCS_ID = "$Header: ParameterList.java 115.7 1999/12/07 12:06:54 pkm ship $";

	private static String READ = "READONLY";
	private static String WRITE = "WRITEONLY";

	private boolean mDebugParser = false;
	private String mString;
	private String mStatus = READ;
	private Vector mList;
	private int currIndex;

	public ParameterList() {
		this.mStatus = WRITE;
		this.mList = new Vector();
		this.mString = "";
	}

	public ParameterList(String paramString) throws IllegalArgumentException {
		this.mList = new Vector();

		while (paramString.startsWith(":"))
			paramString = paramString.substring(1);
		this.mString = paramString;
		parse(this.mString);
	}

	public synchronized String getString() {
		while (this.mString.startsWith(":"))
			this.mString = this.mString.substring(1);
		return this.mString;
	}

	private synchronized void setString(String paramString) {
		this.mString = paramString;
		this.mList = new Vector();
		parse(this.mString);
	}

	private synchronized void addString(String paramString) {
		this.mString = (this.mString + ":" + paramString);

		parse(paramString);
	}

	public synchronized void addParameter(NameValueType paramNameValueType) {
		this.mList.addElement(paramNameValueType);

		String str1 = paramNameValueType.getName();
		String str2 = paramNameValueType.getValue();

		this.mString = (this.mString + ":" + str1);

		String str3 = "";
		int i = 0;
		int j = 0;
		while ((j = str2.indexOf('"', i)) != -1) {
			str3 = str3 + str2.substring(i, j);
			str3 = str3 + "\"\"";
			i = j + 1;
		}
		str3 = str3 + str2.substring(i);

		if ((str2.indexOf(':') != -1) || (str2.indexOf('=') != -1)) {
			str3 = "\"" + str3 + "\"";
		}
		this.mString = (this.mString + "=" + str3);
	}

	public synchronized void addParameter(String paramString1,
			String paramString2) {
		addParameter(new NameValueType(paramString1, paramString2));
	}

	public synchronized boolean hasMoreElements() {
		return this.currIndex < this.mList.size();
	}

	public synchronized Object nextElement() {
		Object localObject;
		try {
			localObject = this.mList.elementAt(this.currIndex++);
		} catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
			System.out.println(localArrayIndexOutOfBoundsException);
			return null;
		}
		return localObject;
	}

	public synchronized NameValueType nextParameter() {
		return (NameValueType) nextElement();
	}

	synchronized Hashtable toHashtable() {
		Hashtable localHashtable = new Hashtable();

		for (int i = 0; i < this.mList.size(); i++) {
			try {
				NameValueType localNameValueType = (NameValueType) this.mList
						.elementAt(i++);
				localHashtable.put(localNameValueType.getName(),
						localNameValueType);
			} catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
				System.out.println(localArrayIndexOutOfBoundsException);
				return localHashtable;
			}
		}
		return localHashtable;
	}

	private Vector parse(String paramString) throws IllegalArgumentException {
		Vector localVector = new Vector();

		int i = 0;
		String str1 = "";
		String str2 = "";

		String str3 = paramString;

		while (i == 0) {
			str1 = "";
			str2 = "";

			StringTokenizer localStringTokenizer = new StringTokenizer(str3,
					"=");
			str1 = localStringTokenizer.nextToken();
			try {
				str3 = localStringTokenizer.nextToken("");
			} catch (NoSuchElementException localNoSuchElementException) {
				i = 1;
				throw new IllegalArgumentException(
						"The string you passed to ParameterList is not correctly formated.");
			}

			str3 = str3.substring(1, str3.length());

			if (str3.length() == 0) {
				str2 = "";
				NameValueType localNameValueType1 = new NameValueType(str1,
						str2);
				this.mList.addElement(localNameValueType1);
				localVector.addElement(str1);
				localVector.addElement(str2);
				break;
			}
			int j = str3.charAt(0);

			if (j == 58) {
				str2 = "";
				str3 = str3.substring(1, str3.length());
			} else if (j != 34) {
				if (str3.indexOf(':') != -1) {
					str2 = removeEscape(str3.substring(0, str3.indexOf(':')));

					str3 = str3.substring(str3.indexOf(':') + 1, str3.length());
				} else {
					str2 = removeEscape(str3);
					i = 1;
				}

			} else {
				int k = 0;

				int m = 1;

				while ((m < str3.length()) && (str3.charAt(m) == '"')) {
					m++;
				}

				if (str3.length() == m) {
					k = 1;
				}

				int n = m;
				int i1 = 0;
				int i2;
				if (m % 2 == 0) {
					k = 1;

					for (i2 = 1; i2 <= m / 2; i2++)
						str2 = str2 + "\"";
				} else if (m > 1) {
					while (m > 1) {
						str2 = str2 + "\"";
						m -= 2;
					}

				}

				str3 = str3.substring(n, str3.length());

				while (k == 0) {
					i2 = str3.indexOf('"');

					i1 = i2 + 1;
					while ((i1 < str3.length()) && (str3.charAt(i1) == '"')) {
						i1++;
					}

					str2 = str2 + str3.substring(0, i2);

					int i3 = i1 - i2;

					if (i3 % 2 == 0) {
						for (int i4 = 1; i4 <= i3 / 2; i4++)
							str2 = str2 + "\"";
					} else if (i3 % 2 != 0) {
						k = 1;
						while (i3 > 1) {
							str2 = str2 + "\"";
							i3 -= 2;
						}

					}

					if (k == 0) {
						n = i1;
						str3 = str3.substring(i1, str3.length());
					}

				}

				if (i1 == str3.length())
					i = 1;
				if (i == 0) {
					if (str3.charAt(i1) == ':') {
						str3 = str3.substring(i1 + 1, str3.length());
					} else {
						i2 = str3.indexOf(":");
						String str4 = "";

						if (i2 == -1) {
							i2 = str3.length();
							str4 = str3;
							str3 = "";
							i = 1;
						} else {
							str4 = str3.substring(0, i2);

							str3 = str3.substring(i2 + 1);
						}

						str2 = str2 + removeEscape(str4);
					}
				}

			}

			if (this.mDebugParser)
				System.out.println("param name: " + str1);
			if (this.mDebugParser)
				System.out.println("param value: " + str2);

			NameValueType localNameValueType2 = new NameValueType(str1, str2);
			this.mList.addElement(localNameValueType2);
			localVector.addElement(str1);
			localVector.addElement(str2);
		}

		return localVector;
	}

	private void p(int paramInt) {
		if (this.mDebugParser)
			System.out.println(paramInt);
	}

	private void p(String paramString) {
		if (this.mDebugParser)
			System.out.println(paramString);
	}

	private String removeEscape(String paramString) {
		String str = "";

		int i = 0;

		while ((i = paramString.indexOf("\"\"")) != -1) {
			str = str + paramString.substring(0, i + 1);
			try {
				paramString = paramString.substring(i + 2);
			} catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
				throw new IllegalArgumentException(
						"The string you passed to ParameterList is not correctly formated.");
			}
		}

		str = str + paramString;
		return str;
	}

	public String[] parseCaller(String[] paramArrayOfString) {
		Vector localVector = parse(paramArrayOfString[0]);
		int i = localVector.size();
		String[] arrayOfString = new String[i];
		for (int j = 0; j < i; j++)
			arrayOfString[j] = ((String) localVector.elementAt(j));
		return arrayOfString;
	}
}
