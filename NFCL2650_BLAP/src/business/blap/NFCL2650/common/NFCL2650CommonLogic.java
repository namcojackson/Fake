/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2650.common;

import static business.blap.NFCL2650.constant.NFCL2650Constant.ONL_BAT_TP;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NFCL2650CommonLogic {
    /**
     * @param glblCmpyCd String
     * @return String
     */
    public static String findArAcctDtInfoForAcctDt(String glblCmpyCd) {
        String outGetVarCharConstValue = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", glblCmpyCd);
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.subSysCd.setValue(outGetVarCharConstValue);
        inMsg.onlBatTpCd.setValue(ONL_BAT_TP);

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg.acctDt.getValue();
    }
}
