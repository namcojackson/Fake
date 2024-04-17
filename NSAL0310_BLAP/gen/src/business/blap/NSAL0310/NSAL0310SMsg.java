// This file was automatically generated by Business Component
// Interface Definition Document (Define Business Application Global
// Area) and XLA200710010.
// Generated on :20230417152519000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in
// :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla
// XLA200710010
/*
 * NSAL0310SMsg.java Copyright FUJITSU LIMITED 2007Outline : 1.
 * Release: No. Date Ver Updater Content 1
 */
package business.blap.NSAL0310;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0310 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0310SMsg extends EZDSMsg implements EZDSchemaItemDefines {

    // Serial Version UID
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** DS_ACCT_NUM_H */
    public final EZDSStringItem dsAcctNum_H;

    /** CONTR_VRSN_EFF_FROM_DT_H */
    public final EZDSDateItem contrVrsnEffFromDt_H;

    /** CONTR_VRSN_EFF_THRU_DT_H */
    public final EZDSDateItem contrVrsnEffThruDt_H;

    /** A */
    public final business.blap.NSAL0310.NSAL0310_ASMsgArray A;

    /**
     * NSAL0310SMsg is constructor. The initialization when the
     * instance of NSAL0310SMsg is generated.
     */
    public NSAL0310SMsg() {
        this(false, -1);
    }

    /**
     * NSAL0310SMsg is constructor. The initialization when the
     * instance of NSAL0310SMsg is generated.
     * @param child Flag whether it is child message
     * @param eleNo Index Number of array
     */
    public NSAL0310SMsg(boolean child, int eleNo) {
        super(child, eleNo);

        // Initialization of item

        dsAcctNum_H = (EZDSStringItem) newItem("dsAcctNum_H");
        contrVrsnEffFromDt_H = (EZDSDateItem) newItem("contrVrsnEffFromDt_H");
        contrVrsnEffThruDt_H = (EZDSDateItem) newItem("contrVrsnEffThruDt_H");
        A = (business.blap.NSAL0310.NSAL0310_ASMsgArray) newMsgArray("A");
    }

    /**
     * get the type of array which is stored
     * @return NSAL0310SMsgArray
     */
    public EZDMsgArray getMyArray() {
        return new NSAL0310SMsgArray();
    }

    /**
     * Array of schema data(Basic data)
     */
    private static final String[][] BASE_CONTENTS = {

    {"dsAcctNum_H", "dsAcctNum_H", "H", null, TYPE_HANKAKUEISU, "20", null }, {"contrVrsnEffFromDt_H", "contrVrsnEffFromDt_H", "H", null, TYPE_NENTSUKIHI, "8", null },
            {"contrVrsnEffThruDt_H", "contrVrsnEffThruDt_H", "H", null, TYPE_NENTSUKIHI, "8", null }, {"A", "A", null, "10000", "business.blap.NSAL0310.NSAL0310_ASMsgArray", null, null },

    };

    /**
     * Array of schema data(Visible Field)
     */
    private static final String[][] DISP_CONTENTS = {

    {"DS_ACCT_NUM", NO, null, null, null, NO, NO, NO, NO, null, null, null, null, NO, NO }, // dsAcctNum_H
            {"CONTR_VRSN_EFF_FROM_DT", NO, null, null, null, NO, NO, NO, NO, null, null, null, null, NO, NO }, // contrVrsnEffFromDt_H
            {"CONTR_VRSN_EFF_THRU_DT", NO, null, null, null, NO, NO, NO, NO, null, null, null, null, NO, NO }, // contrVrsnEffThruDt_H
            null, // A
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
