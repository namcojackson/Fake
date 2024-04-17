/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.NYEL0010.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NYEL0010.constant.NYEL0010Constant;

public class NYEL0010CommonLogic implements NYEL0010Constant {

    /**
     * Method name: s21getMenu
     * <dd>The method explanation: Warning Button Control.
     * <dd>Remarks:
     */
    public static HashMap<String, String> s21getMenu() {
        
        HashMap<String, String> map = new HashMap<String, String>();   
        
        try {
            final ResourceBundle rb = ResourceBundle.getBundle(S21_PROFILE);
            if (rb == null) {
                return null;
            }

            final Enumeration<String> en = rb.getKeys();

            while (en.hasMoreElements()) {
                final String key = en.nextElement();
                map.put(key, rb.getString(key));
            }
            return map;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Method name: setCommonButton
     * <dd>The method explanation: Common Button Control.
     * <dd>Remarks:
     * @param handler S21CommonHandler
     */
    public static void setCommonButton(EZDCommonHandler handler) {
             
        // Common Button Control
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

    }
}
