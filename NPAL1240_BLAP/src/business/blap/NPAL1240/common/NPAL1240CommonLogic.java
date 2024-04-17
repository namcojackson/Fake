/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1240.common;

import parts.common.EZDCItem;
import business.blap.NPAL1240.NPAL1240CMsg;

/**
 * Business ID : NPAL1240 Qualifier Setup
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240CommonLogic {

    /**
     * <p>
     * Sets the error information to the field item.
     * </p>
     * @param item the field item.
     * @param msgCd message code
     * @param params message parameters
     */
    public static void setErrorInfo(EZDCItem item, String msgCd, Object... params) {
        item.setErrorInfo(1, msgCd, toStringArray(params));
    }

    /**
     * <p>
     * Sets the message information to the CMsg.
     * </p>
     * @param bizMsg CMsg
     * @param msgCd message code
     * @param params message parameters
     */
    public static void setMessageInfo(NPAL1240CMsg bizMsg, String msgCd, Object... params) {
        bizMsg.setMessageInfo(msgCd, toStringArray(params));
    }

    /**
     * <p>
     * Converts the array of {@link Object} to the array of
     * {@link String}.
     * </p>
     * @param objects the array of Object.
     * @return the array of String.
     */
    private static String[] toStringArray(Object[] objects) {
        if (objects == null) {
            return null;
        }
        String[] params = new String[objects.length];
        for (int index = 0; index < objects.length; index++) {
            if (objects[index] == null) {
                params[index] = "";
            } else {
                params[index] = objects[index].toString();
            }
        }
        return params;
    }
}
