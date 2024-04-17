/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import parts.common.EZDMsgArray;
import parts.common.EZDPMsg;
import parts.common.EZDSchemaItemDefines;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class ValidationReturnBean extends EZDPMsg implements EZDSchemaItemDefines {
    /** xxMsgIdList*/
    public final com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001xxMsgIdListPMsgArray              xxMsgIdList;
    /**
     * NSZC073001PMsg is constructor.
     * The initialization when the instance of NSZC073001PMsg is generated.
     */
    public ValidationReturnBean() {
        this(false, -1);
    }

    /**
     * NSZC073001PMsg is constructor.
     * The initialization when the instance of NSZC073001PMsg is generated.
     * @param child  Flag whether it is child message
     * @param eleNo  Index Number of array
     */
    public ValidationReturnBean(boolean child, int eleNo) {
        super(child, eleNo);

        // Initialization of item
        xxMsgIdList = (com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
    }

    /**
     * get the type of array which is stored
     * @return NSZC073001PMsgArray
     */
    public EZDMsgArray getMyArray() {
        return new NSXC001001xxMsgIdListPMsgArray();
    }


    /**
     * Array of schema data(Basic data)
     */
    private static final String[][] BASE_CONTENTS = {

    {"xxMsgIdList", "xxMsgIdList", null, "100", "com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001xxMsgIdListPMsgArray", null, null},
    
    };

    /**
     * Array of schema data(Visible Field)
     */
    private static final String[][] DISP_CONTENTS = {

        null,   //xxMsgIdList
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
     * @return Array of  Display  Fields
     */
    protected String[][] getDispContents() {
        return DISP_CONTENTS;
    }
}
