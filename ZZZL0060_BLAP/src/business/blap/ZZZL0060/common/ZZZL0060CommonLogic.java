package business.blap.ZZZL0060.common;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZZL0060.ZZZL0060CMsg;
import business.blap.ZZZL0060.constant.ZZZL0060Constant;
import business.db.GLBL_CMPYTMsg;

public class ZZZL0060CommonLogic implements ZZZL0060Constant {
    
    /**
     * Method name: nvl
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param str String
     * @return String
     */
    public static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    
    /**
     * Method name: isEmpty
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param str String
     * @return String
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * Method name: chkGlbCmpCd
     * <dd>The method explanation: Search GLBL_CMPY table by Primary
     * Key
     * @param cMsg ZZZL0060CMsg
     * @return boolean true or false
     */
    public static boolean chkGlbCmpCd(ZZZL0060CMsg cMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code" });
            return false;
        }
        return true;
    }
}
