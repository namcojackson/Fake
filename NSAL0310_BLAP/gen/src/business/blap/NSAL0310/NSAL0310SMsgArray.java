// This file was automatically generated by Business Component
// Interface Definition Document (Define Business Application Global
// Area) and XLA200710010.
// Generated on :20230417152519000
// Velocity macro:EZDMsgArray.vm V200604010
// Excel Add-in
// :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla
// XLA200710010
/*
 * NSAL0310SMsgArray.java Copyright FUJITSU LIMITED 2007Outline : 1.
 * Release: No. Date Ver Updater Content 1
 */
package business.blap.NSAL0310;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL0310 Business Application Global Area Message Array
 * class.
 * @author
 * @version XLA200710010
 */
public class NSAL0310SMsgArray extends EZDSMsgArray {

    // Serial Version UID
    private static final long serialVersionUID = 1L;

    /**
     * get NSAL0310SMsg of the specified element number .
     * @param n Index Number
     * @return NSAL0310SMsg
     */
    public NSAL0310SMsg no(int n) {
        return (NSAL0310SMsg) get(n);
    }

    /**
     * The message career type in the array is acquired.
     * @return NSAL0310SMsg
     */
    public EZDMsg getMyComponent() {
        return new NSAL0310SMsg();
    }
}