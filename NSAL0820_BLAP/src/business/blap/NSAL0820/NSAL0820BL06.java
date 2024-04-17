/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0820;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0820.common.NSAL0820CommonLogic;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_CONTR_PROC_STSTMsg;
import business.db.MTR_LBTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NSXC001001PMsg;
import static business.blap.NSAL0820.constant.NSAL0820Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsActlCntIntfc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto       Create          N/A
 * 2016/04/06   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/14   Hitachi         M.Gotou         Update          QC#11832,11853,11854
 * 2016/07/19   Hitachi         M.Gotou         Update          QC#11854
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#12077
 * 2016/08/02   Hitachi         Y.Takeno        Update          QC#11831
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11853
 * 2016/09/02   Hitachi         T.Zhang         Update          QC#12083
 * 2016/09/07   Hitachi         T.Tomita        Update          QC#11836
 *</pre>
 */
public class NSAL0820BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0820Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_CMN_Save((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_CMN_Delete((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_ValidateData((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Save)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_CMN_Save(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        NSAL0820CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // ADD start 2016/04/22 CSA Defect#6691
        if (!checkParam(sMsg, cMsg)) {
            int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // ADD end 2016/04/22 CSA Defect#6691

        // START 2016/09/07 T.Tomita [QC#11836, ADD]
        if (!setMtrLbCd(sMsg, cMsg)) {
            int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // END 2016/09/07 T.Tomita [QC#11836, ADD]

        saveActualCounterInterface(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            // START 2016/09/07 T.Tomita [QC#11836, ADD]
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
            // END 2016/09/07 T.Tomita [QC#11836, ADD]
            return;
        }
    }

    /**
     * do process (delete)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_CMN_Delete(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        NSAL0820CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        deleteActualCounterInterface(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_ValidateData(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        NSAL0820CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        // ADD start 2016/04/22 CSA Defect#6691
        if (!checkSelectedParam(sMsg, cMsg)) {
            int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // ADD end 2016/04/22 CSA Defect#6691

        // START 2016/09/07 T.Tomita [QC#11836, ADD]
        if (!setMtrLbCd(sMsg, cMsg)) {
            int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // END 2016/09/07 T.Tomita [QC#11836, ADD]

        // Validation Actual Counter Interface Table
        List<DS_ACTL_CNT_INTFCTMsg> dsActlCntIntfcTMsgList = NSAL0820CommonLogic.setDsActlCntIntfc(cMsg, sMsg);
        NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
        NSXC001001ValidationDsActlCntIntfc.validationDsActlCntIntfc(prmPmsg, dsActlCntIntfcTMsgList);

        NSAL0820CommonLogic.updateValidationResult(cMsg, sMsg, dsActlCntIntfcTMsgList, true);

        // 2016/04/07 START [QC#5662, MOD]
        updateProcStatus(cMsg, sMsg, dsActlCntIntfcTMsgList);
        // 2016/04/07 END [QC#5662, MOD]

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    // 2016/04/07 [QC#5662, MOD]
    private void updateProcStatus(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg, List<DS_ACTL_CNT_INTFCTMsg> dsActlCntIntfcTMsgList) {

        S21SsmEZDResult ssmResult = NSAL0820Query.getInstance().getStatusUpdateTarget(cMsg.glblCmpyCd.getValue(), dsActlCntIntfcTMsgList);
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        String procStsNm = getProcStsName(cMsg.glblCmpyCd.getValue());
        for (Map<String, Object> target : list) {
            DS_CONTR_INTFCTMsg tMsg = findDsContrIntfcForUpdate((String) target.get("GLBL_CMPY_CD"), (BigDecimal) target.get("DS_CONTR_INTFC_PK"));
            if (tMsg == null) {
                cMsg.setMessageInfo("NACM0747E", new String[] {"DS Contract Interface", ((BigDecimal) target.get("DS_CONTR_INTFC_PK")).toString() });
                return;
            }
            setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
            S21FastTBLAccessor.update(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo("NACM0747E", new String[] {"DS Contract Interface", ((BigDecimal) target.get("DS_CONTR_INTFC_PK")).toString() });
                return;
            }
            setProcStsForSMsg(sMsg, (BigDecimal) target.get("DS_CONTR_INTFC_PK"), procStsNm);
        }
        return;
    }

    // 2016/04/07 [QC#5662, MOD]
    private String getProcStsName(String glblCmpyCd) {
        DS_CONTR_PROC_STSTMsg tMsg = new DS_CONTR_PROC_STSTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
        tMsg = (DS_CONTR_PROC_STSTMsg) S21CodeTableAccessor.findByKey(tMsg);
        return tMsg.dsContrProcStsDescTxt.getValue();
    }

    // 2016/04/07 [QC#5662, MOD]
    private static DS_CONTR_INTFCTMsg findDsContrIntfcForUpdate(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg prmTMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    // 2016/04/07 [QC#5662, MOD]
    private static void setProcStsForSMsg(NSAL0820SMsg sMsg, BigDecimal targetPk, String procStsNm) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (targetPk.compareTo(sMsg.A.no(i).dsContrIntfcPk_A.getValue()) == 0) {
                setValue(sMsg.A.no(i).dsContrProcStsCd_A, DS_CONTR_PROC_STS.ERROR);
                setValue(sMsg.A.no(i).dsContrProcStsDescTxt_A, procStsNm);
                break;
            }
        }
    }
    /**
     * existCheckbox
     * @param sMsg
     * @return true/false
     */
    private boolean existCheckbox(NSAL0820SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                return true;
            }
        }
        return false;

    }

    /**
     * saveActualCounterInterface
     * @param sMsg NSAL0820SMsg
     */
    private void saveActualCounterInterface(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                if (!hasValue(sMsg.A.no(i).dsActlCntIntfcPk_A)) {
                    // insert
                    DS_ACTL_CNT_INTFCTMsg tMsg = new DS_ACTL_CNT_INTFCTMsg();
                    setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    setValue(tMsg.dsActlCntIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ACTL_CNT_INTFC_SQ));
                    setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
                    setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
                    setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
                    setValue(tMsg.actlCntIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
                    setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
                    setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
                    setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
                    setValue(tMsg.physMtrLbCd, sMsg.A.no(i).physMtrLbCd_A);
                    setValue(tMsg.physMtrLbNm, getMtrLbNm(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).physMtrLbCd_A.getValue()));
                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).bllblFlg_A.getValue())) {
                        setValue(tMsg.bllblFlg, ZYPConstant.FLG_ON_Y);
                    } else {
                        setValue(tMsg.bllblFlg, ZYPConstant.FLG_OFF_N);
                    }
                    setValue(tMsg.contrMtrMultRate, sMsg.A.no(i).contrMtrMultRate_A);
                    setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
                    setValue(tMsg.bllgMtrLbNm, getMtrLbNm(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).bllgMtrLbCd_A.getValue()));
                    setValue(tMsg.intgMdseCd, sMsg.A.no(i).intgMdseCd_A);

                    EZDTBLAccessor.insert(tMsg);
                    String rtnCd = tMsg.getReturnCode();
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {DS_ACTL_CNT_INTFC });
                    }
                } else {
                    // update
                    DS_ACTL_CNT_INTFCTMsg tMsg = NSAL0820CommonLogic.getActualCounterInterfac(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsActlCntIntfcPk_A.getValue());
                    if (tMsg == null) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
                    setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
                    setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
                    setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
                    setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
                    setValue(tMsg.physMtrLbCd, sMsg.A.no(i).physMtrLbCd_A);
                    setValue(tMsg.physMtrLbNm, getMtrLbNm(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).physMtrLbCd_A.getValue()));
                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).bllblFlg_A.getValue())) {
                        setValue(tMsg.bllblFlg, ZYPConstant.FLG_ON_Y);
                    } else {
                        setValue(tMsg.bllblFlg, ZYPConstant.FLG_OFF_N);
                    }
                    setValue(tMsg.contrMtrMultRate, sMsg.A.no(i).contrMtrMultRate_A);
                    setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
                    setValue(tMsg.bllgMtrLbNm, getMtrLbNm(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).bllgMtrLbCd_A.getValue()));
                    setValue(tMsg.intgMdseCd, sMsg.A.no(i).intgMdseCd_A);

                    EZDTBLAccessor.update(tMsg);
                    String rtnCd = tMsg.getReturnCode();
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {DS_ACTL_CNT_INTFC });
                        return;
                    }
                }
            }
        }
    }

    /**
     * deleteActualCounterInterface
     * @param sMsg NSAL0820SMsg
     */
    private void deleteActualCounterInterface(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        List<Integer> rowNumberList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (hasValue(sMsg.A.no(i).dsActlCntIntfcPk_A)) {
                    // for update
                    DS_ACTL_CNT_INTFCTMsg tMsg = NSAL0820CommonLogic.getActualCounterInterfac(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsActlCntIntfcPk_A.getValue());
                    if (tMsg == null) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    String rtnCd = tMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0033E, new String[] {DS_ACTL_CNT_INTFC });
                        return;
                    }
                }
                rowNumberList.add(Integer.valueOf(i));
            }
        }
        ZYPTableUtil.deleteRows(sMsg.A, rowNumberList);
        // START 2016/09/02 Y.Zhang [QC#12083, MOD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
        // END 2016/09/02 Y.Zhang [QC#12083, MOD]
    }

    /**
     * getMtrLbNm
     * @param glblCmpyCd String
     * @param mtrLbCd String
     * @return MtrLbNm
     */
    private String getMtrLbNm(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        // START 2016/08/02 Y.Takeno [QC#11831, MOD]
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mtrLbCd", mtrLbCd);
        // END 2016/08/02 Y.Takeno [QC#11831, MOD]
        inMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (inMsg == null || !hasValue(inMsg.mtrLbDescTxt)) {
            return null;
        }
        return ZYPCommonFunc.subByteString(inMsg.mtrLbDescTxt.getValue(), LENGTH_30);
    }

 // ADD start 2016/04/22 CSA Defect#6691
    /**
     * <pre>
     * set error message for mandatory items
     * </pre>
     * @param sMsg NSAL0820SMsg
     * @param i int
     * @return count
     */
    private int setErrorMsg(NSAL0820SMsg sMsg, int i, NSAL0820CMsg cMsg) {
        int count = 0;
        // START 2016/08/09 Y.Takeno [QC#11853, DEL]
//        if (!hasValue(sMsg.A.no(i).dsContrIntfcPk_A)) {
//
//            // START 2016/07/14 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0553E));
//            //setValue(sMsg.A.no(i).intfcErrMsgTxt_A, new EZDMessageInfo(NSAM0458E).getMessage());
//            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0553E);
//            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0553E);
//            // END 2016/07/14 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
//            count++;
//            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
//            return count;
//            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
//        }
        // END 2016/08/09 Y.Takeno [QC#11853, DEL]

        if (!hasValue(sMsg.A.no(i).dsContrIntfcBatNum_A)) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM}));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        }

        if (!hasValue(sMsg.A.no(i).dsContrSrcRefNum_A)) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM}));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        }

        if (!hasValue(sMsg.A.no(i).contrIntfcSrcTpCd_A)) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD}));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        }

        // START 2016/08/02 Y.Takeno [QC#11831, ADD]
        // START 2016/09/07 T.Tomita [QC#11836, ADD]
        if (!hasValue(sMsg.A.no(i).mtrLbDescTxt_AB)) {
        // END 2016/09/07 T.Tomita [QC#11836, ADD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {BLLG_MTR_LB}));
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {BLLG_MTR_LB});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            return count;
        }
        // END 2016/08/02 Y.Takeno [QC#11831, ADD]

        // START 2016/09/07 T.Tomita [QC#11836, ADD]
        if (!hasValue(sMsg.A.no(i).mtrLbDescTxt_AP)) {
        // END 2016/09/07 T.Tomita [QC#11836, ADD]
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {PHYS_MTR_LB_CD_SCRN}));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {PHYS_MTR_LB_CD_SCRN});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        }

        // START 2016/07/28 M.Gotou [QC#12077, ADD]
        if (hasValue(sMsg.A.no(i).svcMachMstrPk_A)) {
            SVC_MACH_MSTRTMsg tMsg = NSAL0820CommonLogic.getSvcMachMstr(getGlobalCompanyCode(), sMsg.A.no(i).svcMachMstrPk_A.getValue());
            if (tMsg != null) {
                if (hasValue(sMsg.A.no(i).serNum_A)) {
                    if (!tMsg.serNum.getValue().equals(sMsg.A.no(i).serNum_A.getValue())) {
                        setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0556E));
                        sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0556E);
                        setValue(sMsg.A.no(i).xxMsgId_A, NSAM0556E);
                        setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        count++;
                        // START 2016/08/02 Y.Takeno [QC#11831, ADD]
                        return count;
                        // END 2016/08/02 Y.Takeno [QC#11831, ADD]
                    }
                }
                if (hasValue(sMsg.A.no(i).mdseCd_A)) {
                    if (!tMsg.mdseCd.getValue().equals(sMsg.A.no(i).mdseCd_A.getValue())) {
                        setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0557E));
                        sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0557E);
                        setValue(sMsg.A.no(i).xxMsgId_A, NSAM0557E);
                        setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        count++;
                        // START 2016/08/02 Y.Takeno [QC#11831, ADD]
                        return count;
                        // END 2016/08/02 Y.Takeno [QC#11831, ADD]
                    }
                }
            } else {
                setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SVC_MACH_MSTR_PK}));
                sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SVC_MACH_MSTR_PK});
                setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                count++;
                // START 2016/08/02 Y.Takeno [QC#11831, ADD]
                return count;
                // END 2016/08/02 Y.Takeno [QC#11831, ADD]
            }
        } else {
            if (hasValue(sMsg.A.no(i).serNum_A)) {
                BigDecimal svcMachMstrPk = NSAL0820CommonLogic.getIbId(getGlobalCompanyCode(), sMsg.A.no(i).serNum_A.getValue());
                if (!hasValue(svcMachMstrPk)) {
                    setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SER_NUM}));
                    sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SER_NUM});
                    setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                    setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    count++;
                    // START 2016/08/02 Y.Takeno [QC#11831, ADD]
                    return count;
                    // END 2016/08/02 Y.Takeno [QC#11831, ADD]
                }
            }
        }
        // END 2016/07/28 M.Gotou [QC#12077, ADD]

        // START 2016/08/02 Y.Takeno [QC#11831, ADD]
        S21SsmEZDResult ssmResult = NSAL0820Query.getInstance().getDsContrIntfcPk(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i));
        Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
        if (rs == null) {
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0553E));
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0553E);
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0553E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            return count;
        }
        // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        return count;
    }

    // START 2016/08/02 Y.Takeno [QC#11831, MOD]
    private boolean checkParam(NSAL0820SMsg sMsg, NSAL0820CMsg cMsg) {
        boolean flg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (setErrorMsg(sMsg, i, cMsg) > 0) {
                flg = false;
            }
        }
        return flg;
    }

    private boolean checkSelectedParam(NSAL0820SMsg sMsg, NSAL0820CMsg cMsg) {
        boolean flg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (setErrorMsg(sMsg, i, cMsg) > 0) {
                    flg = false;
                }
            }
        }
        return flg;
    }
    // END 2016/08/02 Y.Takeno [QC#11831, MOD]
    // ADD end 2016/04/22 CSA Defect#6691

    // START 2016/09/07 T.Tomita [QC#11836, ADD]
    private boolean setMtrLbCd(NSAL0820SMsg sMsg, NSAL0820CMsg cMsg) {
        S21SsmEZDResult ssmResult;
        String mtrLbCd;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ssmResult = NSAL0820Query.getInstance().getMtrLbCd(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), sMsg.A.no(i).mtrLbDescTxt_AP.getValue(), MTR_LB_TP.REGULAR_METER);
            mtrLbCd = (String) ssmResult.getResultObject();
            if (!hasValue(mtrLbCd)) {
                sMsg.A.no(i).physMtrLbCd_A.clear();
                setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[] {PHYS_MTR_LB_CD_SCRN }));
                sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[] {PHYS_MTR_LB_CD_SCRN });
                setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                return false;
            }
            setValue(sMsg.A.no(i).physMtrLbCd_A, mtrLbCd);

            ssmResult = NSAL0820Query.getInstance().getMtrLbCd(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), sMsg.A.no(i).mtrLbDescTxt_AB.getValue(), MTR_LB_TP.BILLING_METER);
            mtrLbCd = (String) ssmResult.getResultObject();
            if (!hasValue(mtrLbCd)) {
                sMsg.A.no(i).bllgMtrLbCd_A.clear();
                setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[] {BLLG_MTR_LB }));
                sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[] {BLLG_MTR_LB });
                setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                return false;
            }
            setValue(sMsg.A.no(i).bllgMtrLbCd_A, mtrLbCd);
        }
        return true;
    }
    // END 2016/09/07 T.Tomita [QC#11836, ADD]
}
