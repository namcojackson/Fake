/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0110.common;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZPL0110.ZZPL0110CMsg;
import business.db.GLBL_CMPYTMsg;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/13   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110CommonLogic {

    /**
     * Check existence of global company code
     * @param cMsg ZZPL0110CMsg
     * @return flag of existence of global company code
     */
    public static boolean checkGlblCmpCd(ZZPL0110CMsg cMsg) {
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return false;
        } else {
            return true;
        }
    }
}
