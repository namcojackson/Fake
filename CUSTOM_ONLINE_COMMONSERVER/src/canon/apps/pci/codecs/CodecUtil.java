/**
 * OWASP Enterprise Security API (ESAPI)
 * 
 * This file is part of the Open Web Application Security Project (OWASP)
 * Enterprise Security API (ESAPI) project. For details, please see
 * <a href="http://www.owasp.org/index.php/ESAPI">http://www.owasp.org/index.php/ESAPI</a>.
 *
 * Copyright (c) 2010 - The OWASP Foundation
 * 
 * The ESAPI is published by OWASP under the BSD license. You should read and accept the
 * LICENSE before you use, modify, and/or redistribute this software.
 * 
 * @author Jeff Williams (jeff.williams .at. aspectsecurity.com) <a href="http://www.aspectsecurity.com">Aspect Security</a>
 * @author Patrick Higgins
 * @created 2010
 */
package canon.apps.pci.codecs;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

public class CodecUtil {

    /**
     * Initialize an array to mark which characters are to be encoded. Store the hex
     * string for that character to save time later. If the character shouldn't be
     * encoded, then store null.
     */
    private static final String[] hex = new String[256];
    
	/** Character sets that define characters immune from encoding in various formats */
	public final static char[] IMMUNE_HTML = { ',', '.', '-', '_', ' ' };
        public final static char[] IMMUNE_HTMLATTR = { ',', '.', '-', '_' };
	public final static char[] IMMUNE_JAVASCRIPT = { ',', '.', '_' };
	public final static char[] IMMUNE_SQL = { ' ' };

	static {
		Arrays.sort( IMMUNE_HTML );
		Arrays.sort( IMMUNE_JAVASCRIPT );
		Arrays.sort( IMMUNE_SQL );
	}    
    /**
     * A zero-length array of characters.
     */
    public static final char[] EMPTY_CHARS = new char[0];

    static {
        for ( char c = 0; c < 0xFF; c++ ) {
            if ( c >= 0x30 && c <= 0x39 || c >= 0x41 && c <= 0x5A || c >= 0x61 && c <= 0x7A ) {
                hex[c] = null;
            } else {
                hex[c] = Integer.toHexString(c).intern();
            }
        }
    }
    /**
     * Lookup the hex value of any character that is not alphanumeric.
     * @param c The character to lookup.
     * @return, return null if alphanumeric or the character code
     *  in hex.
     */
    public static String getHexForNonAlphanumeric(char c)
    {
        if(c<0xFF)
            return hex[c];
        return Integer.toHexString(c);
    }

    /**
     * Utility to search a char[] for a specific char.
     * 
     * @param c
     * @param array
     * @return
     */
    public static boolean containsCharacter( char c, char[] array ) {
        for (int i = 0; i < array.length; i++) {
            if (c == array[i]) return true;
        }
        return false;
    }

    public static String carryFormParams(HttpServletRequest paramHttpServletRequest, String[] paramArrayOfString) {
        canon.apps.pci.codecs.HTMLEntityCodec htmlCodec = new canon.apps.pci.codecs.HTMLEntityCodec();
        StringBuffer localStringBuffer = new StringBuffer();

        Vector localVector = null;
        if ((paramArrayOfString != null) && (paramArrayOfString.length > 0)) {
            localVector = new Vector();
            for (int i = 0; i < paramArrayOfString.length; i++) {
                localVector.addElement(paramArrayOfString[i]);
            }
        }
        Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
        while (localEnumeration.hasMoreElements()) {
            String str = null;
            String[] arrayOfString = null;
            str = (String) localEnumeration.nextElement();
            if ((localVector != null) && (!localVector.contains(str))) {
                arrayOfString = paramHttpServletRequest.getParameterValues(str);
                if (arrayOfString != null) {
                    if ((str.equals("tmpl_seq")) || (str.equals("subscription_id")) || (str.equals("category"))) {
                        localStringBuffer.append("<input type=hidden name=" + htmlCodec.encode(IMMUNE_HTML,str) + " value=\"" + htmlCodec.encode(IMMUNE_HTML, paramHttpServletRequest.getParameter(str)) + "\">\n");
                    } else {
                        for (int j = 0; j < arrayOfString.length; j++) {
                            localStringBuffer.append("<input type=hidden name=" + htmlCodec.encode(IMMUNE_HTML,str) + " value=\"" + htmlCodec.encode(IMMUNE_HTML, arrayOfString[j]) + "\">\n");
                        }
                    }
                }
            }
        }
        return localStringBuffer.toString();
    }
  
}
