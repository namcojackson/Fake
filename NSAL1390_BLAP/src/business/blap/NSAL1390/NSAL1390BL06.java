/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1390;

import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0011E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0031E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0033E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0079E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0645E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0696E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NZZM0002I;
import static business.blap.NSAL1390.constant.NSAL1390Constant.ZZZM9004E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1390.common.NSAL1390CommonLogic;
import business.db.RNW_ESCL_LTR_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 *</pre>
 */
public class NSAL1390BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL1390CMsg bizMsg = (NSAL1390CMsg) cMsg;
            NSAL1390SMsg glblMsg = (NSAL1390SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1390Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1390Scrn00_CMN_Submit(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        // Delete
        if (glblMsg.B.getValidCount() > 0) {
            if (!deleteLine(bizMsg, glblMsg.B)) {
                return;
            }
        }

        // cMsg -> sMsg
        int pageFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1390CommonLogic.setPagenation(bizMsg, glblMsg, pageFromNum);

        boolean result = true;
        int errorLineNum = 0;

        // Insert or Update
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NSAL1390_ASMsg asMsg = glblMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(asMsg.ezUpTime_A)) {
                // Update
                if (!updateLine(bizMsg, asMsg) && result) {
                    result = false;
                    errorLineNum = i;
                }
            } else {
                // Insert
                if (!insertLine(bizMsg, asMsg) && result) {
                    result = false;
                    errorLineNum = i;
                }
            }
        }

        if (!result) {
            pageFromNum = (errorLineNum / bizMsg.A.length()) * bizMsg.A.length();
            NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, pageFromNum);
        } else {
            // success
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private boolean deleteLine(NSAL1390CMsg cMsg, NSAL1390_BSMsgArray sbMsgArray) {

        for (int i = 0; i < sbMsgArray.getValidCount(); i++) {

            NSAL1390_BSMsg delMsg = sbMsgArray.no(i);

            RNW_ESCL_LTR_CTRLTMsg tMsg = new RNW_ESCL_LTR_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, delMsg.lineBizTpCd_B);
            ZYPEZDItemValueSetter.setValue(tMsg.svcRgPk, delMsg.svcRgPk_B);
            ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, delMsg.svcContrBrCd_B);

            RNW_ESCL_LTR_CTRLTMsg outTMsg = (RNW_ESCL_LTR_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

            // Exclusive check
            if (outTMsg == null || !ZYPDateUtil.isSameTimeStamp(delMsg.ezUpTime_B.getValue(), delMsg.ezUpTimeZone_B.getValue(), outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return false;
            }

            EZDTBLAccessor.remove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"RNW_ESCL_LTR_CTRL" });
                return false;
            }
        }
        return true;
    }

    private boolean updateLine(NSAL1390CMsg cMsg, NSAL1390_ASMsg updMsg) {

        RNW_ESCL_LTR_CTRLTMsg tMsg = new RNW_ESCL_LTR_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, updMsg.lineBizTpCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgPk, updMsg.svcRgPk_A);
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, updMsg.svcContrBrCd_A);

        RNW_ESCL_LTR_CTRLTMsg outTMsg = (RNW_ESCL_LTR_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

        // Exclusive check
        if (outTMsg == null || !ZYPDateUtil.isSameTimeStamp(updMsg.ezUpTime_A.getValue(), updMsg.ezUpTimeZone_A.getValue(), outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        // Difference check
        if (!hasDifferMsg(updMsg, outTMsg)) {
            return true;
        }

        RNW_ESCL_LTR_CTRLTMsg updTMsg = setRewEsclCtrlMsg(cMsg, updMsg);
        EZDTBLAccessor.update(updTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            updMsg.xxChkBox_A1.setErrorInfo(1, NSAM0031E, new String[] {"RNW_ESCL_LTR_CTRL" });
            return false;
        }
        return true;
    }

    private boolean insertLine(NSAL1390CMsg bizMsg, NSAL1390_ASMsg insMsg) {

        boolean masterCheckResult = true;
        boolean mandatoryCheckResult = true;

        // Mandatory Check
        if (!ZYPCommonFunc.hasValue(insMsg.lineBizTpCd_A)) {
            insMsg.lineBizTpCd_A.setErrorInfo(1, NSAM0645E, new String[] {"LOB" });
            mandatoryCheckResult = false;
        }
        if (!ZYPCommonFunc.hasValue(insMsg.svcRgPk_A)) {
            insMsg.svcRgPk_A.setErrorInfo(1, NSAM0645E, new String[] {"Region" });
            mandatoryCheckResult = false;
        }
        if (!ZYPCommonFunc.hasValue(insMsg.svcContrBrCd_A)) {
            insMsg.svcContrBrCd_A.setErrorInfo(1, NSAM0645E, new String[] {"Branch" });
            mandatoryCheckResult = false;
        }
        if (!mandatoryCheckResult) {
            return mandatoryCheckResult;
        }

        // Master Check
        String svcRgDescTxt = NSAL1390CommonLogic.getSvcRgNm(bizMsg, insMsg.svcRgPk_A.getValue());
        if (!ZYPCommonFunc.hasValue(svcRgDescTxt)) {
            insMsg.svcRgPk_A.setErrorInfo(1, NSAM0011E, new String[] {"Region" });
            masterCheckResult = false;
        }
        ZYPEZDItemValueSetter.setValue(insMsg.svcRgDescTxt_A, svcRgDescTxt);

        String svcContrBrDescTxt = NSAL1390CommonLogic.getSvcContrBrNm(bizMsg, insMsg.svcContrBrCd_A.getValue());
        if (!ZYPCommonFunc.hasValue(svcContrBrDescTxt)) {
            insMsg.svcContrBrCd_A.setErrorInfo(1, NSAM0011E, new String[] {"Branch" });
            masterCheckResult = false;
        }
        ZYPEZDItemValueSetter.setValue(insMsg.svcContrBrDescTxt_A, svcContrBrDescTxt);

        if (!masterCheckResult) {
            return masterCheckResult;
        }

        // Relation Check
        int count = NSAL1390Query.getInstance().cntLobRgBrRelation(bizMsg, insMsg);
        if (count < 1) {
            insMsg.lineBizTpCd_A.setErrorInfo(1, NSAM0696E, new String[] {"LOB", "Region", "Branch" });
            insMsg.svcRgPk_A.setErrorInfo(1, NSAM0696E, new String[] {"LOB", "Region", "Branch" });
            insMsg.svcContrBrCd_A.setErrorInfo(1, NSAM0696E, new String[] {"LOB", "Region", "Branch" });
            return false;
        }

        // Duplication Check
        RNW_ESCL_LTR_CTRLTMsg tMsg = new RNW_ESCL_LTR_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, insMsg.lineBizTpCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgPk, insMsg.svcRgPk_A);
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, insMsg.svcContrBrCd_A);

        RNW_ESCL_LTR_CTRLTMsg outTMsg = (RNW_ESCL_LTR_CTRLTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {
            insMsg.lineBizTpCd_A.setErrorInfo(1, NSAM0079E, new String[] {"The pair of LOB, Region and Branch" });
            insMsg.svcRgPk_A.setErrorInfo(1, NSAM0079E, new String[] {"The pair of LOB, Region and Branch" });
            insMsg.svcContrBrCd_A.setErrorInfo(1, NSAM0079E, new String[] {"The pair of LOB, Region and Branch" });
            return false;
        }

        // Insert
        RNW_ESCL_LTR_CTRLTMsg insTMsg = setRewEsclCtrlMsg(bizMsg, insMsg);
        EZDTBLAccessor.insert(insTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            insMsg.xxChkBox_A1.setErrorInfo(1, NSAM0031E, new String[] {"RNW_ESCL_LTR_CTRL" });
            return false;
        }
        return true;
    }

    /**
     * Difference check for Update
     * @param updMsg NSAL1390_ASMsg
     * @param srchMsg RNW_ESCL_LTR_CTRLTMsg
     * @return boolean all equals: false
     */
    private boolean hasDifferMsg(NSAL1390_ASMsg updMsg, RNW_ESCL_LTR_CTRLTMsg srchMsg) {

        String updRnwLtrFlg = NSAL1390CommonLogic.convCheckBoxValue(updMsg.xxChkBox_A2.getValue());
        String updAnnEsclLtrFlg = NSAL1390CommonLogic.convCheckBoxValue(updMsg.xxChkBox_A3.getValue());
        String updPrintOldPrcFlg = NSAL1390CommonLogic.convCheckBoxValue(updMsg.xxChkBox_A4.getValue());

        if (S21StringUtil.isEquals(srchMsg.rnwLtrFlg.getValue(), updRnwLtrFlg) //
                && S21StringUtil.isEquals(srchMsg.annEsclLtrFlg.getValue(), updAnnEsclLtrFlg) //
                && S21StringUtil.isEquals(srchMsg.printOldPrcFlg.getValue(), updPrintOldPrcFlg)) {
            return false;
        }
        return true;
    }

    /**
     * setRewEsclCtrlMsg
     * @param cMsg NSAL1390CMsg
     * @param updMsg NSAL1390_ASMsg
     * @return RNW_ESCL_LTR_CTRLTMsg
     */
    private RNW_ESCL_LTR_CTRLTMsg setRewEsclCtrlMsg(NSAL1390CMsg cMsg, NSAL1390_ASMsg updMsg) {

        String updRnwLtrFlg = NSAL1390CommonLogic.convCheckBoxValue(updMsg.xxChkBox_A2.getValue());
        String updAnnEsclLtrFlg = NSAL1390CommonLogic.convCheckBoxValue(updMsg.xxChkBox_A3.getValue());
        String updPrintOldPrcFlg = NSAL1390CommonLogic.convCheckBoxValue(updMsg.xxChkBox_A4.getValue());

        RNW_ESCL_LTR_CTRLTMsg tMsg = new RNW_ESCL_LTR_CTRLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, updMsg.lineBizTpCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgPk, updMsg.svcRgPk_A);
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, updMsg.svcContrBrCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.rnwLtrFlg, updRnwLtrFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.annEsclLtrFlg, updAnnEsclLtrFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.printOldPrcFlg, updPrintOldPrcFlg);

        return tMsg;
    }

}
