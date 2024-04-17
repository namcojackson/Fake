/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0810;

import static business.blap.NSAL0810.constant.NSAL0810Constant.LENGTH_30;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NACM0748E;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NSAM0015E;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NSAM0405W;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NSAM0406W;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NSAM0408E;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NZZM0002I;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NSAM0033E;
import static business.blap.NSAL0810.constant.NSAL0810Constant.DS_ACTL_CNT_INTFC;
import static business.blap.NSAL0810.constant.NSAL0810Constant.DS_XS_COPY_INTFC;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0810.common.NSAL0810CommonLogic;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.MTR_LBTMsg;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.parts.NSXC001001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsActlCntIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsAddlChrgIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsContrIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsXsCopyIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationPrcAllocIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.ValidationReturnBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Ds Contract Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/02/15   Hitachi         Y.Takeno        Update          QC#3314
 * 2016/02/16   Hitachi         Y.Takeno        Update          QC#4006
 * 2016/04/01   Hitachi         T.Iwamoto       Update          QC#6334
 * 2016/04/06   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/06/16   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/07/14   Hitachi         Y.Takeno        Update          QC#11918
 * 2016/08/31   Hitachi         T.Mizuki        Update          QC#12566
 * 2016/09/02   Hitachi         T.Zhang         Update          QC#12083
 * 2016/09/12   Hitachi         T.Tomita        Update          QC#11831
 * 2016/10/19   Hitachi         Y.Takeno        Update          QC#15235
 *</pre>
 */
public class NSAL0810BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0810Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_CMN_Save((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_CMN_Delete((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_ValidateData((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_ResubmitInterface".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_ResubmitInterface((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_ResubmitBatInterface".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_ResubmitBatInterface((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_SubmitBatValidation".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_SubmitBatValidation((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Save)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_CMN_Save(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        // 3.Insert Or Update
        NSAL0810CommonLogic.createAndUpdateDsContrIntfc(cMsg, sMsg, getGlobalCompanyCode(), true, true);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            // START 2016/09/12 T.Tomita [QC#11831, ADD]
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
            // END 2016/09/12 T.Tomita [QC#11831, ADD]
            return;
        }
    }

    /**
     * do process (delete)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_CMN_Delete(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        boolean isChecked = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg asMsg = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (hasValue(asMsg.xxChkBox_A) && ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A.getValue())) {
                isChecked = true;
                break;
            }
        }
        if (!isChecked) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        List<Integer> dispDelList = new ArrayList<Integer>();
        BigDecimal cntComplete = BigDecimal.ZERO;
        BigDecimal cntErrorOrReprocess = BigDecimal.ZERO;
        BigDecimal cntInProcess = BigDecimal.ZERO;
        BigDecimal cntError = BigDecimal.ZERO;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg asMsg = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (hasValue(asMsg.xxChkBox_A) && ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A.getValue())) {
                if (hasValue(asMsg.dsContrIntfcPk_A)) {
                    dispDelList.add(i);

// START 2016/02/16 [QC#4006, MOD]
                    if (!deleteChildTables(cMsg, asMsg.dsContrIntfcPk_A.getValue())) {
                        return;
                    }
// END   2016/02/16 [QC#4006, MOD]

                    DS_CONTR_INTFCTMsg tmsg = new DS_CONTR_INTFCTMsg();
                    ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tmsg.dsContrIntfcPk, asMsg.dsContrIntfcPk_A.getValue());
                    EZDTBLAccessor.logicalRemove(tmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                        cMsg.setMessageInfo(NACM0748E, new String[] {"DS Contract Interface" });
                        return;
                    }
                } else {
                    dispDelList.add(i);
                }
            } else {
                if (!hasValue(asMsg.dsContrIntfcPk_A)) {
                    if (DS_CONTR_PROC_STS.COMPLEATED.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                        cntComplete = cntComplete.add(BigDecimal.ONE);
                    } else if (DS_CONTR_PROC_STS.ERROR.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                        cntErrorOrReprocess = cntErrorOrReprocess.add(BigDecimal.ONE);
                        cntError = cntError.add(BigDecimal.ONE);
                    } else if (DS_CONTR_PROC_STS.REPROCESS.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                        cntErrorOrReprocess = cntErrorOrReprocess.add(BigDecimal.ONE);
                    } else if (DS_CONTR_PROC_STS.IN_PROCESS.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                        cntInProcess = cntInProcess.add(BigDecimal.ONE);
                    }
                }
            }
        }

        setValue(cMsg.xxSrchCnt_SP, cntComplete);
        setValue(cMsg.xxSrchCnt_SR, cntErrorOrReprocess);
        setValue(cMsg.xxSrchCnt_SU, cntInProcess);
        setValue(cMsg.xxSrchCnt_SI, cntError);

        ZYPTableUtil.deleteRows(sMsg.A, dispDelList);
        // START 2016/09/02 Y.Zhang [QC#12083, MOD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
        // END 2016/09/02 Y.Zhang [QC#12083, MOD]
        // set Paging Data
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }


    }

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_ValidateData(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        int selectedCnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg asMsg = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A.getValue())) {
                selectedCnt++;
            }
        }
        if (selectedCnt == 0) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        Set<String> selectedDsContrIntfcBatNumSet = NSAL0810CommonLogic.getDsContrIntfcBatNumSet(sMsg);
        // MOD start 2016/04/01 CSA Defect#6334
        if (selectedDsContrIntfcBatNumSet.size() > 2) {
            cMsg.setMessageInfo(NSAM0408E);
            return;
        }
        // MOD end 2016/04/01 CSA Defect#6334
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg asMsg = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (selectedDsContrIntfcBatNumSet.contains(asMsg.dsContrIntfcBatNum_A.getValue())) {
                setValue(asMsg.xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        // 1.Interface Default Rule
        NSAL0810CommonLogic.setDefaultRule(getGlobalCompanyCode(), cMsg, sMsg, false);

        // 2.Validation
        List<DS_CONTR_INTFCTMsg> dsContrIntfcTMsgList = NSAL0810CommonLogic.setDsContrIntfc(cMsg, sMsg, false);
        // mod start 2016/08/31 CSA QC#12566
        String masterKey = null;
        NSXC001001PMsg prmDbErrorPmsg = new NSXC001001PMsg();
        List<DS_CONTR_INTFCTMsg> chckTMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();
        for (int i = 0; i < dsContrIntfcTMsgList.size(); i++) {

            if (i == 0) {
                masterKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();
                chckTMsgList.add(dsContrIntfcTMsgList.get(i));
            }
            if (i > 0) {
                String targetKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();

                if (targetKey.equals(masterKey)) {
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                } else {
                    NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
                    NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

                    NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
                    NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, false);

                    chckTMsgList.clear();
                    masterKey = targetKey;
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                }
            }
        }
        NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
        NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

        NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
        NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, false);
        // mod end 2016/08/31 CSA QC#12566
        S21SsmEZDResult ssmResult = null;
        for (DS_CONTR_INTFCTMsg tMsg : dsContrIntfcTMsgList) {
            if (!selectedDsContrIntfcBatNumSet.contains(tMsg.dsContrIntfcBatNum.getValue())) {
                continue;
            }

            if (!hasValue(tMsg.dsContrIntfcPk.getValue())) {
                continue;
            }

            // Common validation
// START 2016/02/15 [QC#3314, MOD]
//          commonValidation(ssmResult, cMsg, tMsg);
          commonValidation(ssmResult, cMsg, sMsg, tMsg, true, false);
//END   2016/02/15 [QC#3314, MOD]
        }

        // set Paging Data
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    /**
     * do process (ResubmitInterface)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_ResubmitInterface(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        int selectedCnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg asMsg = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A.getValue())) {
                selectedCnt++;
            }
        }
        if (selectedCnt == 0) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        Set<String> selectedDsContrIntfcBatNumSet = NSAL0810CommonLogic.getDsContrIntfcBatNumSet(sMsg);
        // MOD start 2016/04/01 CSA Defect#6334
        if (selectedDsContrIntfcBatNumSet.size() > 2) {
            cMsg.setMessageInfo(NSAM0408E);
            return;
        }
        // MOD end 2016/04/01 CSA Defect#6334

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg asMsg = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (selectedDsContrIntfcBatNumSet.contains(asMsg.dsContrIntfcBatNum_A.getValue())) {
                setValue(asMsg.xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        // 1.Interface Default Rule
        NSAL0810CommonLogic.setDefaultRule(getGlobalCompanyCode(), cMsg, sMsg, false);

        // 2.Validation
        List<DS_CONTR_INTFCTMsg> dsContrIntfcTMsgList = NSAL0810CommonLogic.setDsContrIntfc(cMsg, sMsg, false);
        // mod start 2016/08/31 CSA QC#12566
        String masterKey = null;
        NSXC001001PMsg prmDbErrorPmsg = new NSXC001001PMsg();
        List<DS_CONTR_INTFCTMsg> chckTMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();
        for (int i = 0; i < dsContrIntfcTMsgList.size(); i++) {

            if (i == 0) {
                masterKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();
                chckTMsgList.add(dsContrIntfcTMsgList.get(i));
            }
            if (i > 0) {
                String targetKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();

                if (targetKey.equals(masterKey)) {
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                } else {
                    NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
                    NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

                    NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
                    NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, false);

                    chckTMsgList.clear();
                    masterKey = targetKey;
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                }
            }
        }
        NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
        NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

        NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
        NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, false);
        // mod end 2016/08/31 CSA QC#12566
        S21SsmEZDResult ssmResult = null;
        for (DS_CONTR_INTFCTMsg tMsg : dsContrIntfcTMsgList) {
            if (!selectedDsContrIntfcBatNumSet.contains(tMsg.dsContrIntfcBatNum.getValue())) {
                continue;
            }

            if (!hasValue(tMsg.dsContrIntfcPk.getValue())) {
                continue;
            }

            // Common validation
// START 2016/02/15 [QC#3314, MOD]
//          commonValidation(ssmResult, cMsg, tMsg);
          commonValidation(ssmResult, cMsg, sMsg, tMsg, true, false);
//END   2016/02/15 [QC#3314, MOD]
        }

        // 3.Insert Or Update
        NSAL0810CommonLogic.createAndUpdateDsContrIntfc(cMsg, sMsg, getGlobalCompanyCode(), false, false);

        // START 2016/07/14 [QC#11918, ADD]
        // count up Proc Status
        NSAL0810CommonLogic.countupSMsgProcStatus(cMsg, sMsg);
        // END   2016/07/14 [QC#11918, ADD]

        // START 2016/06/16 [QC#4061, MOD]
        // set Paging Data
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        // END   2016/06/16 [QC#4061, MOD]

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    /**
     * getMtrLbNm
     * @param glblCmpyCd String
     * @param mtrLbCd String
     * @return MtrLbNm
     */
    private String getMtrLbNm(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrLbTpCd01", mtrLbCd);
        inMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (inMsg == null || !ZYPCommonFunc.hasValue(inMsg.mtrLbDescTxt)) {
            return null;
        }
        return ZYPCommonFunc.subByteString(inMsg.mtrLbDescTxt.getValue(), LENGTH_30);
    }

    /**
     * do process (ResubmitBatInterface)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_ResubmitBatInterface(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxErrFlg_B.getValue())) {
            cMsg.xxErrFlg_B.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NSAM0406W);
            return;
        } else {
            cMsg.xxErrFlg_B.clear();
        }

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // 1.Interface Default Rule
        NSAL0810CommonLogic.setDefaultRule(getGlobalCompanyCode(), cMsg, sMsg, true);

        // 2.Validation
        List<DS_CONTR_INTFCTMsg> dsContrIntfcTMsgList = NSAL0810CommonLogic.setDsContrIntfc(cMsg, sMsg, true);
        // mod start 2016/08/31 CSA QC#12566
        String masterKey = null;
        NSXC001001PMsg prmDbErrorPmsg = new NSXC001001PMsg();
        List<DS_CONTR_INTFCTMsg> chckTMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();
        for (int i = 0; i < dsContrIntfcTMsgList.size(); i++) {

            if (i == 0) {
                masterKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();
                chckTMsgList.add(dsContrIntfcTMsgList.get(i));
            }
            if (i > 0) {
                String targetKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();

                if (targetKey.equals(masterKey)) {
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                } else {
                    NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
// mod start 2016/08/31 CSA QC#15235
//                    NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, dsContrIntfcTMsgList);
                    NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

//                    NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, dsContrIntfcTMsgList);
//                    NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, dsContrIntfcTMsgList, true, true);
                    NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
                    NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, true);
// mod end 2016/08/31 CSA QC#15235

                    chckTMsgList.clear();
                    masterKey = targetKey;
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                }
            }
        }
        NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
        NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

        NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
        NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, true);
        // mod end 2016/08/31 CSA QC#12566
        S21SsmEZDResult ssmResult = null;
        for (DS_CONTR_INTFCTMsg tMsg : dsContrIntfcTMsgList) {
            if (!hasValue(tMsg.dsContrIntfcPk.getValue())) {
                continue;
            }
            // Common validation
// START 2016/02/15 [QC#3314, MOD]
//          commonValidation(ssmResult, cMsg, tMsg);
          commonValidation(ssmResult, cMsg, sMsg, tMsg, true, true);
//END   2016/02/15 [QC#3314, MOD]
        }

        // 3.Insert Or Update
        NSAL0810CommonLogic.createAndUpdateDsContrIntfc(cMsg, sMsg, getGlobalCompanyCode(), true, false);

        // START 2016/07/14 [QC#11918, ADD]
        // count up Proc Status
        NSAL0810CommonLogic.countupSMsgProcStatus(cMsg, sMsg);
        // END   2016/07/14 [QC#11918, ADD]

        // set Paging Data
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());

        if (prmDbErrorPmsg.xxMsgIdList.getValidCount() == 0) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    /**
     * do process (SubmitBatValidation)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_SubmitBatValidation(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxErrFlg_A.getValue())) {
            setValue(cMsg.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NSAM0405W);
            return;
        } else {
            cMsg.xxErrFlg_A.clear();
        }

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // 1.Interface Default Rule
        NSAL0810CommonLogic.setDefaultRule(getGlobalCompanyCode(), cMsg, sMsg, true);

        // 2.Validation Ds Contract Interface Table
        List<DS_CONTR_INTFCTMsg> dsContrIntfcTMsgList = NSAL0810CommonLogic.setDsContrIntfc(cMsg, sMsg, true);
        // mod start 2016/08/31 CSA QC#12566
        String masterKey = null;
        NSXC001001PMsg prmDbErrorPmsg = new NSXC001001PMsg();
        List<DS_CONTR_INTFCTMsg> chckTMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();
        for (int i = 0; i < dsContrIntfcTMsgList.size(); i++) {

            if (i == 0) {
                masterKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();
                chckTMsgList.add(dsContrIntfcTMsgList.get(i));
            }
            if (i > 0) {
                String targetKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();

                if (targetKey.equals(masterKey)) {
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                } else {
                    NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
                    NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

                    NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
                    NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, true);

                    chckTMsgList.clear();
                    masterKey = targetKey;
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                }
            }
        }
        NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
        NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

        NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
        NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, true);

        // mod end 2016/08/31 CSA QC#12566
        S21SsmEZDResult ssmResult = null;
        for (DS_CONTR_INTFCTMsg tMsg : dsContrIntfcTMsgList) {
            if (!hasValue(tMsg.dsContrIntfcPk.getValue())) {
                continue;
            }
            // Common validation
// START 2016/02/15 [QC#3314, MOD]
//            commonValidation(ssmResult, cMsg, tMsg);
            commonValidation(ssmResult, cMsg, sMsg, tMsg, true, true);
// END   2016/02/15 [QC#3314, MOD]
        }

        // set Paging Data
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());

        if (prmDbErrorPmsg.xxMsgIdList.getValidCount() == 0) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

// START 2016/02/15 [QC#3314, MOD]
//    private void commonValidation(S21SsmEZDResult ssmResult, NSAL0810CMsg cMsg, DS_CONTR_INTFCTMsg tMsg){
    private void commonValidation(S21SsmEZDResult ssmResult, NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, DS_CONTR_INTFCTMsg tMsg, boolean validFlg, boolean allFlg) {
        // Validation Check(DS_ACTL_CNT_INTFC)
        ssmResult = NSAL0810Query.getInstance().getDsActlCntrIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
        List<DS_ACTL_CNT_INTFCTMsg> dsActlCntrIntfcTMsgList = NSAL0810CommonLogic.getDsActlCntrIntfcList(ssmResult);
        NSXC001001PMsg pMsgDsActlCntIntfc = new NSXC001001PMsg();
        NSXC001001ValidationDsActlCntIntfc.validationDsActlCntIntfc(pMsgDsActlCntIntfc, dsActlCntrIntfcTMsgList);
        NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, pMsgDsActlCntIntfc, tMsg, validFlg, allFlg);

        // Validation Check(DS_XS_COPY_INTFC)
        ssmResult = NSAL0810Query.getInstance().getDsXsCopyIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
        List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList = NSAL0810CommonLogic.getDsXsCopyIntfcList(ssmResult);
        ValidationReturnBean vrbDsXsCopyIntfc = NSXC001001ValidationDsXsCopyIntfc.validationDsXsCopyIntfc(dsXsCopyIntfcTMsgList);
        NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, vrbDsXsCopyIntfc, tMsg, validFlg, allFlg);

        // Validation Check(DS_ADDL_CHRG_INTFC)
        ssmResult = NSAL0810Query.getInstance().getDsAddlChrgIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
        List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList = NSAL0810CommonLogic.getDsAddlChrgIntfcList(ssmResult);
        ValidationReturnBean vrbDsAddlChrgIntfc = NSXC001001ValidationDsAddlChrgIntfc.validationDsAddlChrgIntfc(dsAddlChrgIntfcTMsgList);
        NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, vrbDsAddlChrgIntfc, tMsg, validFlg, allFlg);

        // Validation Check(PRC_ALLOC_INTFC)
        ssmResult = NSAL0810Query.getInstance().getPrcAllocIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
        List<PRC_ALLOC_INTFCTMsg> prcAllocIntfcTMsgList = NSAL0810CommonLogic.getPrcAllocIntfcList(ssmResult);
        ValidationReturnBean vrbPrcAllocIntfc = NSXC001001ValidationPrcAllocIntfc.validationPrcAllocIntfc(prcAllocIntfcTMsgList);
        NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, vrbPrcAllocIntfc, tMsg, validFlg, allFlg);
    }
// END   2016/02/15 [QC#3314, MOD]

// START 2016/02/16 [QC#4006, MOD]

    private boolean deleteChildTables(NSAL0810CMsg cMsg, BigDecimal dsContrIntfcPk) {

        S21SsmEZDResult ssmResult = null;

        // DS_ACTL_CNT_INTFC
        ssmResult = NSAL0810Query.getInstance().getDsActlCntrIntfc(getGlobalCompanyCode(), dsContrIntfcPk);
        List<DS_ACTL_CNT_INTFCTMsg> dsActlCntrIntfcTMsgList = NSAL0810CommonLogic.getDsActlCntrIntfcList(ssmResult);
        for (DS_ACTL_CNT_INTFCTMsg tMsg : dsActlCntrIntfcTMsgList) {
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {DS_ACTL_CNT_INTFC });
                return false;
            }
        }

        // DS_XS_COPY_INTFC
        ssmResult = NSAL0810Query.getInstance().getDsXsCopyIntfc(getGlobalCompanyCode(), dsContrIntfcPk);
        List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList = NSAL0810CommonLogic.getDsXsCopyIntfcList(ssmResult);
        for (DS_XS_COPY_INTFCTMsg tMsg : dsXsCopyIntfcTMsgList) {
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {DS_XS_COPY_INTFC });
                return false;
            }
        }

        // DS_ADDL_CHRG_INTFC
//        ssmResult = NSAL0810Query.getInstance().getDsAddlChrgIntfcForDelete(getGlobalCompanyCode(), dsContrIntfcPk);
//        List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList = NSAL0810CommonLogic.getDsAddlChrgIntfcList(ssmResult);
//        for (DS_ADDL_CHRG_INTFCTMsg tMsg : dsAddlChrgIntfcTMsgList) {
//            EZDTBLAccessor.logicalRemove(tMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//                cMsg.setMessageInfo(NSAM0033E, new String[] {DS_ADDL_CHRG_INTFC });
//                return false;
//            }
//        }

        // PRC_ALLOC_INTFC
//        ssmResult = NSAL0810Query.getInstance().getPrcAllocIntfcForDelete(getGlobalCompanyCode(), dsContrIntfcPk);
//        List<PRC_ALLOC_INTFCTMsg> percAllocIntfcTMsgList = NSAL0810CommonLogic.getPrcAllocIntfcList(ssmResult);
//        for (PRC_ALLOC_INTFCTMsg tMsg : percAllocIntfcTMsgList) {
//            EZDTBLAccessor.logicalRemove(tMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//                cMsg.setMessageInfo(NSAM0033E, new String[] {PRC_ALLOC_INTFC });
//                return false;
//            }
//        }

        return true;
    }

// END   2016/02/16 [QC#4006, MOD]
}
