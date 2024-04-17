/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved.
 * Original Author: ${user}
 * Company: Fujitsu Limited
 * Date: ${date}
 */
package business.servlet.ZZSL0000;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDMsgArray;
import parts.common.EZDSchemaItemDefines;

/**
 * ZZSL0000BMsg is a Screen Data Message class.
 * @author $Author: Katsutoshi Takahashi $ Arti Kumari
 * @version $Revision: 1.3 $ $Date: 2009/07/23 15:57:48 $
 */
public class ZZSL0000BMsg extends EZDBMsg implements EZDSchemaItemDefines {

    /**
     * serial Version UID
     */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** loginID */
    public final EZDBStringItem loginID;

    /** loginPW */
    public final EZDBStringItem loginPW;

    /** _EZBusinessApplicationID*/
    public final EZDBStringItem ezBusinessID;

    /**
     * ZZSL0000BMsg is constructor. This method does the initialization
     * when the instance of ZZSL0000BMsg is generated.
     */
    public ZZSL0000BMsg() {
        this(false, -1);
    }

    /**
     * ZZSL0000BMsg is constructor. This method does the initialization
     * when the instance of ZZSL0000BMsg is generated.
     * @param child Flag whether it is child message
     * @param eleNo Index Number of array
     */
    public ZZSL0000BMsg(boolean child, int eleNo) {
        super(child, eleNo);

        // Initialization of item
        loginID = (EZDBStringItem) newItem("loginID");
        loginPW = (EZDBStringItem) newItem("loginPW");
        ezBusinessID = (EZDBStringItem)newItem("ezBusinessID");
    }

    /**
     * get the type of array which is stored
     * @return ZZSL0000BMsgArray
     */
    public EZDMsgArray getMyArray() {
        return new ZZSL0000BMsgArray();
    }

    /**
     * Array of schema data(Basic data)
     */
    private static final String[][] BASE_CONTENTS = {

    // It is defined as {"Key name", "Variabler", "Qualifier", "Array
            // length", "Data type", "Number of digits", and "Number
            // of
            // decimal digits" } etc.
            {"loginID", "loginID", null, null, TYPE_HANKAKUEISU, "16", null },
            {"loginPW", "loginPW", null, null, TYPE_HANKAKUEISU, "16", null },
            {"ezBusinessID", "ezBusinessID", "01", null, TYPE_HANKAKUEISU, "8", null},
            };

    /**
     * Array of schema data(Visible Field)
     */
    private static final String[][] DISP_CONTENTS = {

    // It is defined as {"Message display character string",
            // "Indispensable input paragraph", "Minimum value",
            // "Maximum
            // value", "Division of I/O item/output item", "\",
            // "Comma", "Zero
            // suppression", "Plus display flag", "Number of display
            // item
            // digits", "Number of display fraction part digits",
            // "Division at
            // the date of the edit at the date", "Display pattern of
            // the year
            // of the edit at the date", "0 burials of the edit at the
            // date",
            // and "Separator of the edit at the date" } etc.
            {"LOGIN_ID", YES, null, null, "1", NO, NO, NO, NO, null, null, null, null, NO, NO }, // loginID
            {"LOGIN_PW", YES, null, null, "1", NO, NO, NO, NO, null, null, null, null, NO, NO }, // loginPW
            {"_EZBusinessApplicationID", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //ezBusinessID
    };

    /**
     * get Array of common (basic) data.
     * @return Array of common (basis) data
     */
    protected String[][] getBaseContents() {
        return BASE_CONTENTS;
    }

    /**
     * get Array of Display Field.
     * @return Array of Display Fields
     */
    protected String[][] getDispContents() {
        return DISP_CONTENTS;
    }

}
