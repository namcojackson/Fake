/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2030.common;

import static business.blap.NWAL2030.constant.NWAL2030Constant.*;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2030.NWAL2030CMsg;
import business.blap.NWAL2030.NWAL2030Query;
import business.blap.NWAL2030.NWAL2030SMsg;
import business.db.HLD_PROC_DFNTMsg;
import business.db.HLD_RSNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Hold Set Up Screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Hitachi         K.Kojima        Create          N/A
 * 2016/03/28   Hitachi         K.Kojima        Update          CSA QC#5801
 * 2016/04/04   Fujitsu         M.Suzuki        Update          CSA QC#6370
 *</pre>
 */
public class NWAL2030CommonLogic {

    /**
     * getSearchData
     * @param cMsg NWAL2030CMsg
     * @param notFoundMsgFlg boolean
     * @param sMsg NWAL2030SMsg
     */
    public static void getSearchData(NWAL2030CMsg cMsg, boolean notFoundMsgFlg, NWAL2030SMsg sMsg) { //2016/04/04 S21_NA#6370 MOD 
        S21SsmEZDResult ssmResult = NWAL2030Query.getInstance().getSearchData(cMsg, cMsg.A.length() + 1);
        //2016/04/04 S21_NA#6370 MOD start
        EZDMsg.copy(cMsg.A, null, sMsg.A, null);
        //2016/04/04 S21_NA#6370 MOD end
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NWAM0496W);
            }
        } else if (notFoundMsgFlg == true) {
            cMsg.setMessageInfo(ZZZM9005W);
        }
    }

    /**
     * findByKeyHldRsn
     * @param glblCmpyCd String
     * @param hldRsnCd String
     * @return HLD_RSNTMsg
     */
    public static HLD_RSNTMsg findByKeyHldRsn(String glblCmpyCd, String hldRsnCd) {
        HLD_RSNTMsg inMsg = new HLD_RSNTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.hldRsnCd.setValue(hldRsnCd);
        return (HLD_RSNTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * findByKeyForUpdateNoWaitHldRsn
     * @param glblCmpyCd String
     * @param hldRsnCd String
     * @return HLD_RSNTMsg
     */
    public static HLD_RSNTMsg findByKeyForUpdateNoWaitHldRsn(String glblCmpyCd, String hldRsnCd) {
        HLD_RSNTMsg prmTMsg = new HLD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.hldRsnCd, hldRsnCd);
        return (HLD_RSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * findByKeyForUpdateNoWaitHldProcDfn
     * @param glblCmpyCd String
     * @param hldRsnCd String
     * @param whTpCd String
     * @return HLD_PROC_DFNTMsg
     */
    public static HLD_PROC_DFNTMsg findByKeyForUpdateNoWaitHldProcDfn(String glblCmpyCd, String hldRsnCd, String whTpCd) {
        HLD_PROC_DFNTMsg prmTMsg = new HLD_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.hldRsnCd, hldRsnCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.whTpCd, whTpCd);
        return (HLD_PROC_DFNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

}
