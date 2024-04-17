package canon.apps.pci.util;

import canon.apps.pci.codecs.HTMLEntityCodec;
import canon.apps.pci.codecs.JavaScriptCodec;
import canon.apps.pci.codecs.OracleCodec;
import java.net.URLEncoder;

public class CanonPCISecurityUtil {

	// HTML Encode:   Cross Site Scripting(XSS)
	public static String htmlEncode(String str) {

		try {
			HTMLEntityCodec htmlCodec = new HTMLEntityCodec();
			if (str != null) {
				str = htmlCodec.encode(canon.apps.pci.codecs.CodecUtil.IMMUNE_HTML, str);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return str;
	}
	
        public static String htmlAttrEncode(String str) {
		try {
			HTMLEntityCodec htmlCodec = new HTMLEntityCodec();
			if (str != null) {
				str = htmlCodec.encode(canon.apps.pci.codecs.CodecUtil.IMMUNE_HTMLATTR, str);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return str;
        
        }
        
	// JS Encode :  Cross Site Scripting(XSS)
	public static String jsEncode(String str) {

		try {
			JavaScriptCodec jsCodec = new JavaScriptCodec();
			if (str != null) {
				str = jsCodec.encode(canon.apps.pci.codecs.CodecUtil.IMMUNE_JAVASCRIPT,str);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return str;
	}
	
	// SQL Injection
	public static String sqlEncode(String str) {
		try {
			OracleCodec oracodec = new OracleCodec();
			if (str != null) {
				str=oracodec.encode(null, str);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return str;
	}

        public static String urlEncode(String str) {
                if ( str == null ) {
                        return null;
                }
                try {
                        return URLEncoder.encode(str, "UTF-8");
                } catch (Exception e) {
			System.out.println(e.getMessage());
                }
                return null;
        }        
	
}
