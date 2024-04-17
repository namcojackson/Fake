/**
 * When the user logs in to an OA Framework application, the OA Framework creates an AOL/J oracle.apps.fnd.common.WebAppsContext object and 
 * a browser session-based cookie that together keep track of key Oracle Applications context information like the current responsibility, 
 * organization id and various user attributes such as user name, user id,
 * employee id and so on. 
 */
package oracle.apps.fnd.common;

public class WebAppsContext {
	String userName = "C12945"; // Sachin Mittal

	/*
	 * Mimic OA framework user id, convert employee id to a 36 based number.
	 */
	public int getUserId() {
		return convert(userName);
	}

	/*
	 * N/A in s21
	 */
	public int getRespId() {
		return 0;
	}

	/*
	 * N/A in s21
	 */
	public int getRespApplId() {
		return 0;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * convert employee id to a 36 based number. for example
	 * convert("Q05058")=1572354044
	 */
	public static int convert(String s) {
		String str = s.toUpperCase();
		int result = 0;
		int base = 1;
		char c = 0;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(str.length() - i - 1);
			result += c >= '0' && c <= '9' ? (c - '0') * base : (c - 'A' + 10)
					* base;
			base *= 36;
		}
		return result;
	}

	/*
	 * revert 36 based number to employ id for example
	 * revert(1572354044)="Q05058"
	 */
	public static String revert(int n) {
		String str = "";
		int i = 0;
		do {
			i = n % 36;
			str = (i < 10 ? (char) ('0' + i) : (char) ('A' + i - 10)) + str;
			n /= 36;
		} while (n > 0);

		return str;
	}

	// public static void main(String []aregs){
	// System.out.println(convert("Q05058"));
	// System.out.println(revert(1572354044));
	// System.out.println(convert(""));
	// }

}
