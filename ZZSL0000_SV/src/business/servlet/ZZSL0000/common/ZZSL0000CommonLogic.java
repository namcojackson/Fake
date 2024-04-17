package business.servlet.ZZSL0000.common;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ZZSL0000CommonLogic {

    /** ZZSL0000_LANDING_PAGE_ID */
    public static final String ZZSL0000_LANDING_PAGE_ID = "ZZSL0000.Landing.Page.ID";

    /** ZZSL0000 Security config property */
    public static final String ZZSL0000_SECURITY_CONFIG = "ZZSL0000.Security.Config";

    /** ZZSL0000プロパティファイル */
    private static Properties property = new Properties();

    static {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("ZZSL0000");

            if (rb != null) {
                Enumeration<String> en = rb.getKeys();
                while (en.hasMoreElements()) {
                    String key = en.nextElement();
                    property.setProperty(key, rb.getString(key));
                }
            }
        } catch (MissingResourceException e) {
        }
    };

    /**
     * プロパティから指定されたキーの値を返します。
     *
     * @param key プロパティキー名
     * @return プロパティキー値
     */
    public static String getProperty(String key) {
        return (String) property.get(key);
    }
}
