/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0460.common;

import static business.blap.NSAL0460.constant.NSAL0460Constant.AUTH_UPDATE;
import static business.blap.NSAL0460.constant.NSAL0460Constant.BUSINESS_ID;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0460.NSAL0460CMsg;
import business.blap.NSAL0460.NSAL0460SMsg;
import business.blap.NSAL0460.NSAL0460_ACMsg;
import business.blap.NSAL0460.NSAL0460_ACMsgArray;
import business.blap.NSAL0460.NSAL0460_ASMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2016/01/26   Hitachi         T.Tomita        Update          QC#2883
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#2886
 *</pre>
 */

public class NSAL0460CommonLogic {

    /**
     * copy To ASMsg for Current Page Info
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {

        // NSAL0460_ACMsg -> NSAL0460_ASMsg
        NSAL0460_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0460_ACMsg acMsg = (NSAL0460_ACMsg) acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A.getValueInt() - 1;

            NSAL0460_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            // [QC#2886,MOD]START
            EZDMsg.copy(acMsg, null, asMsg, null);
            // [QC#2886,MOD]END
        }
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL0460CMsg
     */
    public static void createPullDown(NSAL0460CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0460CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.UPDATE_START_METER_READ);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_PC, cMsg.svcMemoRsnNm_PC);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param sMsg NSAL0460_ASMsg
     * @return boolean not protected
     */
    public static boolean notProtectContrLine(NSAL0460_ASMsg sMsg) {
        if (!hasUpdateFuncId()) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(sMsg.actvFlg.getValue())) {
            return true;
        }
        return false;
    }
    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Start Read Capture#(" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    // START 2016/01/26 T.Tomita [QC#2883, ADD]
    /**
     * isExistsStartMtrRead
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcPhysMtrPk BigDecimal
     * @return boolean
     */
    public static boolean isExistsStartMtrRead(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(dsContrDtlPk) || !ZYPCommonFunc.hasValue(svcPhysMtrPk)) {
            return false;
        }

        BigDecimal svcPhysMtrReadGrpSq = NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk);
        if (!ZYPCommonFunc.hasValue(svcPhysMtrReadGrpSq)) {
            return false;
        }
        return true;
    }
    // END 2016/01/26 T.Tomita [QC#2883, ADD]
}
