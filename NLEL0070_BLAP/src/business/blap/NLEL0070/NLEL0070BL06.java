/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0070;

import static business.blap.NLEL0070.constant.NLEL0070Constant.NLEM0033E;
import static business.blap.NLEL0070.constant.NLEL0070Constant.NLEM0034E;
import static business.blap.NLEL0070.constant.NLEL0070Constant.ZZZM9012E;
import static business.blap.NLEL0070.constant.NLEL0070Constant.ZZZM9013E;
import static business.blap.NLEL0070.constant.NLEL0070Constant.ZZZM9014E;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLEL0070.common.NLEL0070CommonLogic;
import business.db.ASSET_BOOK_CTRLTMsg;
import business.db.ASSET_BOOK_CTRLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Asset Book Control
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 * 2016/06/08   Hitachi         T.Tsuchida      Update          QC#9637
 *</pre>
 */
public class NLEL0070BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            // START 2016/06/08 T.Tsuchida [QC#9637,MOD]
            //if ("NLEL0070Scrn00_CMN_Save".equals(screenAplID)) {
            if ("NLEL0070Scrn00_CMN_Submit".equals(screenAplID)) {
            // END 2016/06/08 T.Tsuchida [QC#9637,MOD]
                doProcess_NLEL0070Scrn00_CMN_Save((NLEL0070CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * NLEL0070Scrn00_CMN_Submit
     * </pre>
     * @param cMsg NLEL0070CMsg
     */
    private void doProcess_NLEL0070Scrn00_CMN_Save(NLEL0070CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate();

        if (!validate(glblCmpyCd, cMsg, slsDt)) {
            return;
        }

        String effFromDt = cMsg.effFromDt_M1.getValue();
        ASSET_BOOK_CTRLTMsgArray array = NLEL0070CommonLogic.getAssetBookCtrlForUpdate(glblCmpyCd, cMsg.assetTpCd_M1.getValue(), cMsg.effFromDt_M1.getValue());
        ASSET_BOOK_CTRLTMsg targetTMsg = null;
        int cnt = array.getValidCount();
        List<ASSET_BOOK_CTRLTMsg> deleteList = new ArrayList<ASSET_BOOK_CTRLTMsg>();
        for (int i = 0; i < cnt; i++) {
            ASSET_BOOK_CTRLTMsg tMsg = array.no(i);
            if (ZYPCommonFunc.hasValue(tMsg.effThruDt) && tMsg.effThruDt.getValue().compareTo(effFromDt) < 0) {
                continue;
            }
            if (tMsg.effFromDt.getValue().compareTo(effFromDt) <= 0 && (!ZYPCommonFunc.hasValue(tMsg.effThruDt) || tMsg.effThruDt.getValue().compareTo(effFromDt) >= 0)) {
                if (targetTMsg == null) {
                    targetTMsg = tMsg;
                }
                continue;
            }
            deleteList.add(tMsg);
        }

        if (targetTMsg != null) {
            if (targetTMsg.effFromDt.getValue().equals(effFromDt)) {
                ZYPEZDItemValueSetter.setValue(targetTMsg.assetCoaAcctCd, cMsg.assetCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.depcCoaAcctCd, cMsg.depcCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.accumDepcCoaAcctCd, cMsg.accumDepcCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.depcAdjCoaAcctCd, cMsg.depcAdjCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.slsPrcdCoaAcctCd, cMsg.slsPrcdCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.rmvCostCoaAcctCd, cMsg.rmvCostCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.gainLossCoaAcctCd, cMsg.gainLossCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.clingCoaAcctCd, cMsg.clingCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.adjCoaAcctCd, cMsg.adjCoaAcctCd_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.defDepcMthNum, cMsg.defDepcMthNum_M1);
                ZYPEZDItemValueSetter.setValue(targetTMsg.assetBookCtrlDescTxt, cMsg.assetBookCtrlDescTxt_M1);
                S21FastTBLAccessor.update(targetTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(ZZZM9013E, new String[] {targetTMsg.getReturnCode() });
                }
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(targetTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
                S21FastTBLAccessor.update(targetTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(ZZZM9013E, new String[] {targetTMsg.getReturnCode() });
                    return;
                }
            }
        }

        if (deleteList.size() > 0) {
            int res = S21FastTBLAccessor.removePhysical(deleteList.toArray((new ASSET_BOOK_CTRLTMsg[deleteList.size()])));
            if (res < 0) {
                cMsg.setMessageInfo(ZZZM9014E, new String[] {String.valueOf(res) });
                return;
            }
        }

        insertAssetBookCtrl(cMsg, glblCmpyCd);
    }

    private boolean insertAssetBookCtrl(NLEL0070CMsg cMsg, String glblCmpyCd) {
        ASSET_BOOK_CTRLTMsg insertTMsg = new ASSET_BOOK_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.assetTpCd, cMsg.assetTpCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.effFromDt, cMsg.effFromDt_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.assetCoaAcctCd, cMsg.assetCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.depcCoaAcctCd, cMsg.depcCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.accumDepcCoaAcctCd, cMsg.accumDepcCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.depcAdjCoaAcctCd, cMsg.depcAdjCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.slsPrcdCoaAcctCd, cMsg.slsPrcdCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rmvCostCoaAcctCd, cMsg.rmvCostCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.gainLossCoaAcctCd, cMsg.gainLossCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.clingCoaAcctCd, cMsg.clingCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.adjCoaAcctCd, cMsg.adjCoaAcctCd_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.defDepcMthNum, cMsg.defDepcMthNum_M1);
        ZYPEZDItemValueSetter.setValue(insertTMsg.assetBookCtrlDescTxt, cMsg.assetBookCtrlDescTxt_M1);

        S21FastTBLAccessor.insert(insertTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9012E, new String[] {insertTMsg.getReturnCode() });
            return false;
        }
        return true;
    }

    private boolean validate(String glblCmpyCd, NLEL0070CMsg cMsg, String slsDt) {
        boolean result = true;

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.assetCoaAcctCd_M1.getValue())) {
            cMsg.assetCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.depcCoaAcctCd_M1.getValue())) {
            cMsg.depcCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.accumDepcCoaAcctCd_M1.getValue())) {
            cMsg.accumDepcCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.depcAdjCoaAcctCd_M1.getValue())) {
            cMsg.depcAdjCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.slsPrcdCoaAcctCd_M1.getValue())) {
            cMsg.slsPrcdCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.rmvCostCoaAcctCd_M1.getValue())) {
            cMsg.rmvCostCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.gainLossCoaAcctCd_M1.getValue())) {
            cMsg.gainLossCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.clingCoaAcctCd_M1.getValue())) {
            cMsg.clingCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (!NLEL0070CommonLogic.isExistCoaAcct(glblCmpyCd, cMsg.adjCoaAcctCd_M1.getValue())) {
            cMsg.adjCoaAcctCd_M1.setErrorInfo(1, NLEM0033E, new String[] {"COA_ACCT" });
            result = false;
        }

        if (cMsg.effFromDt_M1.getValue().compareTo(slsDt) < 0) {
            cMsg.effFromDt_M1.setErrorInfo(1, NLEM0034E);
            result = false;
        }

        return result;
    }
}
