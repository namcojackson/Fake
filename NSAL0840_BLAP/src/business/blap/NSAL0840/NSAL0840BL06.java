/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0840;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL0840.constant.NSAL0840Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0840.common.NSAL0840CommonLogic;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_CONTR_PROC_STSTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsAddlChrgIntfc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADD_CHRG_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
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
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#11853,11854
 * 2016/08/23   Hitachi         T.Mizuki        Update          QC#11855
 * 2016/08/25   Hitachi         T.Mizuki        Update          QC#11853
 * 2016/09/02   Hitachi         T.Zhang         Update          QC#12083
 * 2016/09/12   Hitachi         T.Tomita        Update          QC#11831
 *</pre>
 */
public class NSAL0840BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0840Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_CMN_Save((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_CMN_Delete((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_ValidateData((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Save)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_CMN_Save(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        NSAL0840CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // ADD start 2016/04/22 CSA Defect#6691
        if (!checkParam(sMsg)) {
            int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // ADD end 2016/04/22 CSA Defect#6691
        saveAdditionalChargeInterface(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            // START 2016/09/12 T.Tomita [QC#11831, ADD]
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
            // END 2016/09/12 T.Tomita [QC#11831, ADD]
            return;
        }
    }

    /**
     * do process (delete)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_CMN_Delete(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        NSAL0840CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        deleteAdditionalChargeInterface(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_ValidateData(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        NSAL0840CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        //ADD start 2016/04/22 CSA Defect#6691
        if (!checkSelectedParam(sMsg)) {
            int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // ADD end 2016/04/22 CSA Defect#6691

        // Validation Actual Counter Interface Table
        List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList = NSAL0840CommonLogic.setDsAddlChrgIntfc(cMsg, sMsg);
        NSXC001001ValidationDsAddlChrgIntfc.validationDsAddlChrgIntfc(dsAddlChrgIntfcTMsgList);
        NSAL0840CommonLogic.updateValidationResult(cMsg, sMsg, dsAddlChrgIntfcTMsgList, true);

        // 2016/04/07 START [QC#5662, MOD]
        updateProcStatus(cMsg, sMsg, dsAddlChrgIntfcTMsgList);
        // 2016/04/07 END [QC#5662, MOD]

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    // 2016/04/07 [QC#5662, MOD]
    private void updateProcStatus(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList) {

        S21SsmEZDResult ssmResult = NSAL0840Query.getInstance().getStatusUpdateTarget(cMsg.glblCmpyCd.getValue(), dsAddlChrgIntfcTMsgList, sMsg);
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        String procStsNm = getProcStsName(cMsg.glblCmpyCd.getValue());
        for (Map<String, Object> target : list) {
            DS_CONTR_INTFCTMsg tMsg = NSAL0840CommonLogic.getDsContrIntfc((String) target.get("GLBL_CMPY_CD"), (BigDecimal) target.get("DS_CONTR_INTFC_PK"));
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
    private void setProcStsForSMsg(NSAL0840SMsg sMsg, BigDecimal targetPk, String procStsNm) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (targetPk.compareTo(sMsg.A.no(i).dsContrIntfcPk_A.getValue()) == 0) {
                setValue(sMsg.A.no(i).dsContrProcStsCd_A, DS_CONTR_PROC_STS.ERROR);
                setValue(sMsg.A.no(i).dsContrProcStsDescTxt_A, procStsNm);
            }
        }
    }
    /**
     * existCheckbox
     * @param sMsg
     * @return true/false
     */
    private boolean existCheckbox(NSAL0840SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                return true;
            }
        }
        return false;

    }

    /**
     * saveAdditionalChargeInterface
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void saveAdditionalChargeInterface(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                if (!hasValue(sMsg.A.no(i).dsAddlChrgIntfcPk_A) && !hasValue(sMsg.A.no(i).ezUpTime) && !hasValue(sMsg.A.no(i).ezUpTimeZone)) {
                    // insert ADDR_CHRG_INTFC
                    insertAddrChrgIntfc(cMsg, sMsg, i);

                } else if (!hasValue(sMsg.A.no(i).dsAddlChrgIntfcPk_A) && hasValue(sMsg.A.no(i).ezUpTime) && hasValue(sMsg.A.no(i).ezUpTimeZone)) {
                    // update DS_CONTR_INTFC
                    updateDsConterIntfcOnSave(cMsg, sMsg, i);

                } else if (hasValue(sMsg.A.no(i).dsAddlChrgIntfcPk_A)) {
                    // update ADDR_CHRG_INTFC
                    updateAddrChrgIntfc(cMsg, sMsg, i);
                }
            }
        }
    }

    private void insertAddrChrgIntfc(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, int i) {
        DS_ADDL_CHRG_INTFCTMsg tMsg = new DS_ADDL_CHRG_INTFCTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.dsAddlChrgIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ADDL_CHRG_INTFC_SQ));
        setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
        setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
        setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
        setValue(tMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.NORMAL);
        setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
        setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
        setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
        setValue(tMsg.chrgLvlTpCd, sMsg.A.no(i).chrgLvlTpCd_A);
        setValue(tMsg.addlChrgTpCd, sMsg.A.no(i).addlChrgTpCd_A);
        setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A);
        setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A);
        setValue(tMsg.bllgCycleCd, sMsg.A.no(i).bllgCycleCd_A);
        setValue(tMsg.addlChrgFlatDealPrcAmt, sMsg.A.no(i).addlChrgFlatDealPrcAmt_A);
        setValue(tMsg.addlChrgAplcPct, sMsg.A.no(i).addlChrgAplcPct_A);
        setValue(tMsg.addlChrgInvTpCd, sMsg.A.no(i).addlChrgInvTpCd_A);

        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).printDtlFlg_A.getValue())) {
            setValue(tMsg.printDtlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        }

        EZDTBLAccessor.insert(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {DS_ADDL_CHRG_INTFC });
        }
    }

    private void updateAddrChrgIntfc(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, int i) {
        DS_ADDL_CHRG_INTFCTMsg tMsg = NSAL0840CommonLogic.getDsAddlChrgIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsAddlChrgIntfcPk_A.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
        setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
        setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
        setValue(tMsg.chrgLvlTpCd, sMsg.A.no(i).chrgLvlTpCd_A);
        setValue(tMsg.addlChrgTpCd, sMsg.A.no(i).addlChrgTpCd_A);
        setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A);
        setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A);
        setValue(tMsg.bllgCycleCd, sMsg.A.no(i).bllgCycleCd_A);
        setValue(tMsg.addlChrgFlatDealPrcAmt, sMsg.A.no(i).addlChrgFlatDealPrcAmt_A);
        setValue(tMsg.addlChrgAplcPct, sMsg.A.no(i).addlChrgAplcPct_A);
        setValue(tMsg.addlChrgInvTpCd, sMsg.A.no(i).addlChrgInvTpCd_A);

        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).printDtlFlg_A.getValue())) {
            setValue(tMsg.printDtlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        }

        setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
        setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);

        EZDTBLAccessor.update(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {DS_ADDL_CHRG_INTFC });
            return;
        }
    }

private void updateDsConterIntfcOnSave(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, int i) {
    DS_CONTR_INTFCTMsg tMsg = NSAL0840CommonLogic.getDsContrIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrIntfcPk_A.getValue());
    if (tMsg == null) {
        cMsg.setMessageInfo(ZZZM9004E);
        return;
    }
    if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
        cMsg.setMessageInfo(ZZZM9004E);
        return;
    }

    setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
    setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
    setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
    setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
    setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
    setValue(tMsg.addlChrgTpCd, sMsg.A.no(i).addlChrgTpCd_A);
    setValue(tMsg.contrFromDt, sMsg.A.no(i).effFromDt_A);
    setValue(tMsg.contrThruDt, sMsg.A.no(i).effThruDt_A);
    setValue(tMsg.bllgCycleCd, sMsg.A.no(i).bllgCycleCd_A);
    setValue(tMsg.addlChrgFlatDealPrcAmt, sMsg.A.no(i).addlChrgFlatDealPrcAmt_A);
    setValue(tMsg.addlChrgAplcPct, sMsg.A.no(i).addlChrgAplcPct_A);

    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).printDtlFlg_A.getValue())) {
        setValue(tMsg.printDtlFlg, ZYPConstant.FLG_ON_Y);
    } else {
        setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
    }

    setValue(tMsg.chrgLvlTpCd, sMsg.A.no(i).chrgLvlTpCd_A);
    setValue(tMsg.addlChrgInvTpCd, sMsg.A.no(i).addlChrgInvTpCd_A);

    EZDTBLAccessor.update(tMsg);
    String rtnCd = tMsg.getReturnCode();
    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
        cMsg.setMessageInfo(NSAM0031E, new String[] {DS_CONTR_INTFC });
        return;
    }
}

    /**
     * deleteAdditionalChargeInterface
     * 
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void deleteAdditionalChargeInterface(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        List<Integer> rowNumberList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (hasValue(sMsg.A.no(i).dsAddlChrgIntfcPk_A) && hasValue(sMsg.A.no(i).ezUpTime) && hasValue(sMsg.A.no(i).ezUpTimeZone)) {
                    // delete ADDR_CHRG_INTFC
                    deleteAddrChrgIntfc(cMsg, sMsg, i);

                } else if (!hasValue(sMsg.A.no(i).dsAddlChrgIntfcPk_A) && hasValue(sMsg.A.no(i).ezUpTime) && hasValue(sMsg.A.no(i).ezUpTimeZone)) {
                    // update DS_CONTR_INTFC
                    updateDsConterIntfcOnDelete(cMsg, sMsg, i);

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

    private void deleteAddrChrgIntfc(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, int i) {
        DS_ADDL_CHRG_INTFCTMsg tMsg = NSAL0840CommonLogic.getDsAddlChrgIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsAddlChrgIntfcPk_A.getValue());
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
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {DS_ADDL_CHRG_INTFC });
            return;
        }
    }

    private void updateDsConterIntfcOnDelete(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, int i) {
        DS_CONTR_INTFCTMsg tMsg = NSAL0840CommonLogic.getDsContrIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrIntfcPk_A.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        setValue(tMsg.addlChrgTpCd, (String) null);
        setValue(tMsg.addlChrgFlatDealPrcAmt, (BigDecimal) null);
        setValue(tMsg.addlChrgAplcPct, (BigDecimal) null);
        setValue(tMsg.printDtlFlg, (String) ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.update(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {DS_CONTR_INTFC });
            return;
        }
    }

    // ADD start 2016/04/22 CSA Defect#6691
    /**
     * <pre>
     * set error message for mandatory items
     * </pre>
     * @param sMsg NSAL0840SMsg
     * @param i int
     * @return count
     */
    private int setErrorMsg(NSAL0840SMsg sMsg, int i) {
        int count = 0;
        // del start 2016/08/25 CSA QC#11853
//        if (!hasValue(sMsg.A.no(i).dsContrIntfcPk_A)) {
//            // START 2016/07/22 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0555E));
//            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0555E);
//            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0555E);
//            // END 2016/07/22 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
//            count++;
//        }
        // del end 2016/08/25 CSA QC#11853

        if (!hasValue(sMsg.A.no(i).dsContrIntfcBatNum_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).dsContrSrcRefNum_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).contrIntfcSrcTpCd_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).chrgLvlTpCd_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CHRG_LVL_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CHRG_LVL_TP_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).addlChrgTpCd_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {ADDL_CHRG_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {ADDL_CHRG_TP_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        // START 2016/07/22 M.Gotou [QC#12077, ADD]
        if (hasValue(sMsg.A.no(i).svcMachMstrPk_A)) {
            SVC_MACH_MSTRTMsg tMsg = NSAL0840CommonLogic.getSvcMachMstr(getGlobalCompanyCode(), sMsg.A.no(i).svcMachMstrPk_A.getValue());
            if (tMsg != null) {
                if (hasValue(sMsg.A.no(i).serNum_A)) {
                    if (!tMsg.serNum.getValue().equals(sMsg.A.no(i).serNum_A.getValue())) {
                        setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0556E));
                        sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0556E);
                        setValue(sMsg.A.no(i).xxMsgId_A, NSAM0556E);
                        setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        count++;
                    }
                }
                if (hasValue(sMsg.A.no(i).mdseCd_A)) {
                    if (!tMsg.mdseCd.getValue().equals(sMsg.A.no(i).mdseCd_A.getValue())) {
                        setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0557E));
                        sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0557E);
                        setValue(sMsg.A.no(i).xxMsgId_A, NSAM0557E);
                        setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        count++;
                    }
                }
            } else {
                setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SVC_MACH_MSTR_PK}));
                sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SVC_MACH_MSTR_PK});
                setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                count++;
            }
        } else {
            if (hasValue(sMsg.A.no(i).serNum_A)) {
                BigDecimal svcMachMstrPk = NSAL0840CommonLogic.getIbId(getGlobalCompanyCode(), sMsg.A.no(i).serNum_A.getValue());
                if (!hasValue(svcMachMstrPk)) {
                    setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SER_NUM}));
                    sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SER_NUM});
                    setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                    setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    count++;
                }
            }
        }
        // END 2016/07/22 M.Gotou [QC#12077, ADD]
        // mod start 2016/08/23 CSA QC#11855
        if (ZYPDateUtil.compare(sMsg.A.no(i).effFromDt_A.getValue(), sMsg.A.no(i).effThruDt_A.getValue()) > 0) {
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0327E));
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0327E);
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0327E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }
        // mod end 2016/08/23 CSA QC#11855

        // START 2016/09/12 T.Tomita [QC#11831, ADD]
        S21SsmEZDResult ssmResult = NSAL0840Query.getInstance().getDsContrIntfcPk(getGlobalCompanyCode(), sMsg.A.no(i));
        Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
        if (rs == null) {
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0555E));
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0555E);
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0555E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }
        // END 2016/09/12 T.Tomita [QC#11831, ADD]

        return count;
    }

    private boolean checkParam(NSAL0840SMsg sMsg) {
        boolean flg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (setErrorMsg(sMsg, i) > 0) {
                flg = false;
            }
        }
        return flg;
    }

    private boolean checkSelectedParam(NSAL0840SMsg sMsg) {
        boolean flg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (setErrorMsg(sMsg, i) > 0) {
                    flg = false;
                }
            }
        }
        return flg;
    }
    // ADD end 2016/04/22 CSA Defect#6691

}
