/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLEL0070.common;

import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLEL0070.NLEL0070CMsg;
import business.blap.NLEL0070.NLEL0070Query;
import business.blap.NLEL0070.constant.NLEL0070Constant;
import business.db.ACCT_MTH_CTRLTMsg;
import business.db.ASSET_BOOK_CTRLTMsg;
import business.db.ASSET_BOOK_CTRLTMsgArray;
import business.db.COA_ACCTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Asset Book Control
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 * 2016/06/27   Hitachi         K.Kojima        Update          QC#10041
 *</pre>
 */
public class NLEL0070CommonLogic {

    /**
     * Get CalTpNm
     * @param glblCmpyCd String
     * @param cMsg NLEL0070CMsg
     */
    public static void search(String glblCmpyCd, NLEL0070CMsg cMsg, String slsDt) {

        S21SsmEZDResult ssmResult = NLEL0070Query.getInstance().getAssetBookCtrl(glblCmpyCd, cMsg, slsDt);
        if (ssmResult.getQueryResultCount() == 0) {
            cMsg.setMessageInfo(NLEL0070Constant.NZZM0000E);
            return;
        }
    }

    /**
     * <pre>
     * @param cMsg        NLEL0070CMsg
     * </pre>
     */
    public static void createPulldownList(NLEL0070CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList("ASSET_TP", cMsg.assetTpCd_P1, cMsg.assetTpDescTxt_P1);
        ZYPCodeDataUtil.createPulldownList("ASSET_TP", cMsg.assetTpCd_Q1, cMsg.assetTpDescTxt_Q1);
    }

    /**
     * <pre>
     * @param cMsg        NLEL0070CMsg
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public static void createEffFromDtPulldownList(String glblCmpyCd, NLEL0070CMsg cMsg) {
        cMsg.effFromDt_P1.clear();
        cMsg.effFromDt_P2.clear();

        if (!ZYPCommonFunc.hasValue(cMsg.assetTpCd_H1)) {
            return;
        }
        S21SsmEZDResult rslt = NLEL0070Query.getInstance().getEffFrom(glblCmpyCd, cMsg.assetTpCd_H1.getValue());
        if (rslt.isCodeNotFound()) {
            return;
        }

        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            String effFromDt = (String) rsltMap.get("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_P1.no(i), effFromDt);
            ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_P2.no(i), effFromDt);
            i++;
        }
    }

    public static void clearCMsg(NLEL0070CMsg cMsg) {
        cMsg.assetTpCd_H1.clear();
        cMsg.effFromDt_H1.clear();
        cMsg.effFromDt_P1.clear();
        cMsg.effFromDt_P2.clear();
        clearTargetCMsg(cMsg);
    }

    public static void clearTargetCMsg(NLEL0070CMsg cMsg) {
        cMsg.assetTpCd_M1.clear();
        cMsg.effFromDt_M1.clear();
        cMsg.effThruDt_M1.clear();
        cMsg.assetCoaAcctCd_M1.clear();
        cMsg.depcCoaAcctCd_M1.clear();
        cMsg.accumDepcCoaAcctCd_M1.clear();
        cMsg.depcAdjCoaAcctCd_M1.clear();
        cMsg.slsPrcdCoaAcctCd_M1.clear();
        cMsg.rmvCostCoaAcctCd_M1.clear();
        cMsg.gainLossCoaAcctCd_M1.clear();
        cMsg.clingCoaAcctCd_M1.clear();
        cMsg.adjCoaAcctCd_M1.clear();
        cMsg.defDepcMthNum_M1.clear();
        cMsg.assetBookCtrlDescTxt_M1.clear();
    }

    public static boolean isExistCoaAcct(String glblCmpyCd, String coaAcctCd) {
        COA_ACCTTMsg tMsg = getCoaAcct(glblCmpyCd, coaAcctCd);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    public static COA_ACCTTMsg getCoaAcct(String glblCmpyCd, String coaAcctCd) {
        COA_ACCTTMsg tMsg = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, coaAcctCd);
        return (COA_ACCTTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    // START 2016/06/27 K.Kojima [QC#10041,DEL]
    // public static ACCT_MTH_CTRLTMsg getAcctMthCtrl(String
    // glblCmpyCd, String acctMthCtrlCd) {
    // ACCT_MTH_CTRLTMsg tMsg = new ACCT_MTH_CTRLTMsg();
    // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(tMsg.acctMthCtrlCd,
    // acctMthCtrlCd);
    // return (ACCT_MTH_CTRLTMsg) S21FastTBLAccessor.findByKey(tMsg);
    // }
    // END 2016/06/27 K.Kojima [QC#10041,DEL]
    // START 2018/07/31 J.Kim [QC#26333, ADD]
    public static ACCT_MTH_CTRLTMsg getAcctMthCtrl(String glblCmpyCd, String acctMthCtrlCd) {
        ACCT_MTH_CTRLTMsg tMsg = new ACCT_MTH_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.acctMthCtrlCd, acctMthCtrlCd);
        return (ACCT_MTH_CTRLTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }
    // END 2018/07/31 J.Kim [QC#26333, ADD]

    public static ASSET_BOOK_CTRLTMsgArray getAssetBookCtrlForUpdate(String glblCmpyCd, String assetTpCd, String effFromDt) {

        final ASSET_BOOK_CTRLTMsg condition = new ASSET_BOOK_CTRLTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("assetTpCd01", assetTpCd);
        condition.setConditionValue("effThruDt01", effFromDt);

        return (ASSET_BOOK_CTRLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(condition);
    }

    // START 2016/06/27 K.Kojima [QC#10041,ADD]
    /**
     * @param glblCmpyCd String
     * @param cMsg NLEL0070CMsg
     */
    public static String getLastDepcPeriod(String glblCmpyCd, NLEL0070CMsg cMsg){
        return NLEL0070Query.getInstance().getLastDepcPeriod(glblCmpyCd, cMsg.assetTpCd_H1.getValue());
    }
    // END 2016/06/27 K.Kojima [QC#10041,ADD]
}
