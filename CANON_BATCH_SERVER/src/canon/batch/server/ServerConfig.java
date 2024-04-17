package canon.batch.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerConfig {

	static Properties properties = new Properties();

	static {
		try {
			ClassLoader classloader = Thread.currentThread()
					.getContextClassLoader();
			InputStream inStream = classloader
					.getResourceAsStream("s21ext.properties");
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	/**
	 * @throws IllegalArgumentException
	 *             if not found
	 */
	public static boolean getBoolean(String key)
			throws IllegalArgumentException {
		if (properties.containsKey(key)) {
			Boolean b = Boolean.parseBoolean(properties.getProperty(key));
			return b.booleanValue();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		if (properties.containsKey(key)) {
			Boolean b = Boolean.parseBoolean(properties.getProperty(key));
			return b.booleanValue();
		} else {
			return defaultValue;
		}
	}

	/**
	 * @throws IllegalArgumentException
	 *             if not found
	 */
	public static int getInt(String key) throws IllegalArgumentException {
		if (properties.containsKey(key)) {
			Integer i = Integer.parseInt(properties.getProperty(key));
			return i.intValue();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static int getInt(String key, int defaultValue) {
		if (properties.containsKey(key)) {
			Integer i = Integer.parseInt(properties.getProperty(key));
			return i.intValue();
		} else {
			return defaultValue;
		}
	}

	public static void dump() {
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			System.out.println(key + " => " + value);
		}

	}

//	public static void main(String[] args) {
//		test();
//	}

	private static void test() {
		dump();

		System.out.println(getBoolean("IS_PROD"));

		try {
			System.out.println(getBoolean("IS_NOT_PROD"));
		} catch (IllegalArgumentException e) {
			System.out.println("IS_NOT_PROD property not set ");
		}

		System.out.println(getBoolean("IS_NOT_PROD", true));

		System.out.println(getInt("SESSION_TIME_OUT"));

		System.out.println(getInt("HTTP_TIME_OUT", 600));

		try {
			System.out.println(getInt("HTTP_TIME_OUT"));
		} catch (IllegalArgumentException e) {
			System.out.println("HTTP_TIME_OUT property not set ");
		}

		System.out.println("Is proxy server localhost:8080 ? "
				+ ("localhost:8080".equals(getProperty("PROXY_SERVER")) ? "Yes"
						: "No"));
		
		System.out.println("Is proxy server localhost:8000 ? "
				+ ("localhost:8000".equals(getProperty("PROXY_SERVER")) ? "Yes"
						: "No"));
		
	}
}
