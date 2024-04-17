/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0830;

import static business.blap.NSAL0830.constant.NSAL0830Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0830.common.NSAL0830CommonLogic;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_CONTR_PROC_STSTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsXsCopyIntfc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_COPY_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5648
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#5450
 * 2016/04/19   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#12077
 * 2016/07/22   Hitachi         Y.Takeno        Update          QC#11860
 * 2016/09/02   Hitachi         T.Zhang         Update          QC#12083
 * 2016/09/08   Hitachi         T.Tomita        Update          QC#11860
 * 2016/09/12   Hitachi         T.Tomita        Update          QC#11831
 *</pre>
 */
public class NSAL0830BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0830Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_CMN_Save((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_CMN_Delete((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
                //  MOD start 2016/04/19 CSA Defect#6691
//            } else if ("NSAL0830Scrn00_DeletePricingRow".equals(screenAplID)) {
//                doProcess_NSAL0830Scrn00_DeletePricingRow((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
                // MOD end 2016/04/19 CSA Defect#6691
            } else if ("NSAL0830Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_ValidateData((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Save)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_CMN_Save(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // ADD start 2016/04/19 CSA Defect#6691
        // START 2016/07/22 M.Gotou [QC#12077, MOD]
        if (!NSAL0830CommonLogic.checkParam(sMsg, cMsg)) {
            int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        NSAL0830CommonLogic.clearTargetDeleteList(cMsg, sMsg);
        // END 2016/07/22 M.Gotou [QC#12077, MOD]
        // ADD end 2016/04/19 CSA Defect#6691
        saveXsCopyInterface(cMsg, sMsg);

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
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_CMN_Delete(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        // ADD start 2016/04/19 CSA Defect#6691
        NSAL0830CommonLogic.clearSelectedTargetDeleteList(cMsg, sMsg);
        // ADD end 2016/04/19 CSA Defect#6691

        deleteXsCopyInterface(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    // DELETE start 2016/04/19 CSA Defect#6691
//    /**
//     * do process (DeletePricingRow)
//     * @param cMsg NSAL0830CMsg
//     * @param sMsg NSAL0830SMsg
//     */
//    private void doProcess_NSAL0830Scrn00_DeletePricingRow(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
//
//        if (!hasValue(cMsg.xxRadioBtn_H)) {
//            cMsg.setMessageInfo(NSAM0034E);
//            return;
//        }
//
//        // START 2016/03/23 [QC#5450, MOD]
//        int rowIndex = cMsg.xxRadioBtn_H.getValueInt();
//        deletePricingRow(cMsg, sMsg, rowIndex);
//        // END   2016/03/23 [QC#5450, MOD]
//
//        // numbering rowNum
//        NSAL0830CommonLogic.numberingRowNum(sMsg);
//
//        if (!hasValue(cMsg.getMessageCode())) {
//            cMsg.setMessageInfo(NZZM0002I);
//            return;
//        }
//    }
    // DELETE end 2016/04/19 CSA Defect#6691

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_ValidateData(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        // ADD start 2016/04/19 CSA Defect#6691
        // START 2016/07/22 M.Gotou [QC#12077, MOD]
        if (!NSAL0830CommonLogic.checkSelectedParam(sMsg, cMsg)) {
            int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        NSAL0830CommonLogic.clearSelectedTargetDeleteList(cMsg, sMsg);
        // END 2016/07/22 M.Gotou [QC#12077, MOD]
        // ADD end 2016/04/19 CSA Defect#6691

        // Validation Excess Copy Interface Table
        List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList = NSAL0830CommonLogic.setDsXsCopyIntfc(cMsg, sMsg);
        NSXC001001ValidationDsXsCopyIntfc.validationDsXsCopyIntfc(dsXsCopyIntfcTMsgList);
        NSAL0830CommonLogic.updateValidationResult(cMsg, sMsg, dsXsCopyIntfcTMsgList, true);

        // ADD start 2016/04/19 CSA Defect#6691
        updateProcStatus(cMsg, sMsg, dsXsCopyIntfcTMsgList);
        // ADD end 2016/04/19 CSA Defect#6691

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    // ADD start 2016/04/19 CSA Defect#6691
    private void updateProcStatus(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList) {

        S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().getStatusUpdateTarget(cMsg.glblCmpyCd.getValue(), dsXsCopyIntfcTMsgList, sMsg);
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        String procStsNm = getProcStsName(cMsg.glblCmpyCd.getValue());
        for (Map<String, Object> target : list) {
            DS_CONTR_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsContrIntfc((String) target.get("GLBL_CMPY_CD"), (BigDecimal) target.get("DS_CONTR_INTFC_PK"));
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

    private String getProcStsName(String glblCmpyCd) {
        DS_CONTR_PROC_STSTMsg tMsg = new DS_CONTR_PROC_STSTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
        tMsg = (DS_CONTR_PROC_STSTMsg) S21CodeTableAccessor.findByKey(tMsg);
        return tMsg.dsContrProcStsDescTxt.getValue();
    }

    private void setProcStsForSMsg(NSAL0830SMsg sMsg, BigDecimal targetPk, String procStsNm) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (targetPk.compareTo(sMsg.A.no(i).dsContrIntfcPk_A.getValue()) == 0) {
                setValue(sMsg.A.no(i).dsContrProcStsCd_A, DS_CONTR_PROC_STS.ERROR);
                setValue(sMsg.A.no(i).dsContrProcStsDescTxt_A, procStsNm);
            }
        }
    }
    //  ADD end 2016/04/19 CSA Defect#6691

    /**
     * existCheckbox
     * @param sMsg
     * @return true/false
     */
    private boolean existCheckbox(NSAL0830SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * saveXsCopyInterface
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void saveXsCopyInterface(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                if (XX_TP_CD_H.equals(sMsg.A.no(i).xxTpCd_A.getValue())) {
                    // update DS_CONTR_INTFC
                    updateDsConterIntfcOnSave(cMsg, sMsg, i);

                } else if (XX_TP_CD_D.equals(sMsg.A.no(i).xxTpCd_A.getValue()) && !hasValue(sMsg.A.no(i).dsXsCopyIntfcPk_A)) {
                    // insert DS_XS_COPY_INTFC
                    insertXsCopyIntfc(cMsg, sMsg, i);

                } else if (XX_TP_CD_D.equals(sMsg.A.no(i).xxTpCd_A.getValue()) && hasValue(sMsg.A.no(i).dsXsCopyIntfcPk_A)) {
                    // update DS_XS_COPY_INTFC
                    updateXsCopyIntfc(cMsg, sMsg, i);
                }
            }
        }
    }

    private void insertXsCopyIntfc(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, int i) {
        DS_XS_COPY_INTFCTMsg tMsg = new DS_XS_COPY_INTFCTMsg();

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.dsXsCopyIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_XS_COPY_INTFC_SQ));
        setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
        setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
        setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
        setValue(tMsg.xsCopyIntfcStsCd, XS_COPY_INTFC_STS.NORMAL);
        tMsg.intfcErrMsgTxt.clear();
        setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
        setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
        setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
        setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
        // START 2016/09/08 T.Tomita [QC#11860, DEL]
//        // START 2016/03/22 K.Yamada [QC#5648, MOD]
//        // START 2016/07/22 [QC#11860, MOD]
//        // setValue(tMsg.bllgMtrLbNm, sMsg.A.no(i).mtrLbDescTxt_A);
//        if (hasValue(sMsg.A.no(i).mtrLbDescTxt_A)) {
//            int length = sMsg.A.no(i).mtrLbDescTxt_A.getValue().length();
//            if (length > LENGTH_30) {
//                length = LENGTH_30;
//            }
//            setValue(tMsg.bllgMtrLbNm, sMsg.A.no(i).mtrLbDescTxt_A.getValue().substring(0, length));
//        }
//        // END 2016/07/22 [QC#11860, MOD]
//        // END 2016/03/22 K.Yamada [QC#5648, MOD]
        // END 2016/09/08 T.Tomita [QC#11860, DEL]
        setValue(tMsg.xsMtrCopyQty, sMsg.A.no(i).xsMtrCopyQty_A);
        setValue(tMsg.xsMtrAmtRate, sMsg.A.no(i).xsMtrAmtRate_A);
        setValue(tMsg.xsMtrLvlNum, sMsg.A.no(i).xsMtrLvlNum_A);

        EZDTBLAccessor.insert(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {DS_XS_COPY_INTFC });
        }
    }

    private void updateXsCopyIntfc(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, int i) {
        DS_XS_COPY_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsXsCopyIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsXsCopyIntfcPk_A.getValue());
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
        setValue(tMsg.xsCopyIntfcStsCd, sMsg.A.no(i).dsContrIntfcStsCd_A);
        setValue(tMsg.intfcErrMsgTxt, sMsg.A.no(i).intfcErrMsgTxt_A);
        setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
        setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
        setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
        setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
        // START 2016/09/08 T.Tomita [QC#11860, DEL]
//        // START 2016/03/22 K.Yamada [QC#5648, MOD]
//        // START 2016/07/22 [QC#11860, MOD]
//        // setValue(tMsg.bllgMtrLbNm, sMsg.A.no(i).mtrLbDescTxt_A);
//        if (hasValue(sMsg.A.no(i).mtrLbDescTxt_A)) {
//            int length = sMsg.A.no(i).mtrLbDescTxt_A.getValue().length();
//            if (length > LENGTH_30) {
//                length = LENGTH_30;
//            }
//            setValue(tMsg.bllgMtrLbNm, sMsg.A.no(i).mtrLbDescTxt_A.getValue().substring(0, length));
//        }
//        // END 2016/07/22 [QC#11860, MOD]
//        // END 2016/03/22 K.Yamada [QC#5648, MOD]
        // END 2016/09/08 T.Tomita [QC#11860, DEL]
        setValue(tMsg.xsMtrCopyQty, sMsg.A.no(i).xsMtrCopyQty_A);
        setValue(tMsg.xsMtrAmtRate, sMsg.A.no(i).xsMtrAmtRate_A);
        setValue(tMsg.xsMtrLvlNum, sMsg.A.no(i).xsMtrLvlNum_A);

        EZDTBLAccessor.update(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {DS_XS_COPY_INTFC });
            return;
        }
    }

    private void updateDsConterIntfcOnSave(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, int i) {
        DS_CONTR_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsContrIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrIntfcPk_A.getValue());
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
        setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
        // START 2016/09/08 T.Tomita [QC#11860, DEL]
//        // START 2016/03/22 K.Yamada [QC#5648, MOD]
//        // START 2016/07/22 [QC#11860, MOD]
//        //setValue(tMsg.bllgMtrLbNm, sMsg.A.no(i).mtrLbDescTxt_A);
//        if (hasValue(sMsg.A.no(i).mtrLbDescTxt_A)) {
//            int length = sMsg.A.no(i).mtrLbDescTxt_A.getValue().length();
//            if (length > LENGTH_30) {
//                length = LENGTH_30;
//            }
//            setValue(tMsg.bllgMtrLbNm, sMsg.A.no(i).mtrLbDescTxt_A.getValue().substring(0, length));
//        }
//        // END 2016/07/22 [QC#11860, MOD]
//        // END 2016/03/22 K.Yamada [QC#5648, MOD]
        // END 2016/09/08 T.Tomita [QC#11860, DEL]
        setValue(tMsg.xsMtrCopyQty, sMsg.A.no(i).xsMtrCopyQty_A);
        setValue(tMsg.xsMtrAmtRate, sMsg.A.no(i).xsMtrAmtRate_A);

        EZDTBLAccessor.update(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {DS_CONTR_INTFC });
            return;
        }
    }

    // DELETE start 2016/04/19 CSA Defect#6691
//// START 2016/03/23 [QC#5450, ADD]
//    /**
//     * deletePricingRow
//     * 
//     * @param cMsg NSAL0830CMsg
//     * @param sMsg NSAL0830SMsg
//     * @param rowIndex rowIndex
//     */
//    private void deletePricingRow(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, int rowIndex) {
//
//        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
//        cMsg.setCommitSMsg(true);
//
//        List<Integer> rowNumberList = new ArrayList<Integer>();
//        if (XX_TP_CD_H.equals(sMsg.A.no(rowIndex).xxTpCd_A.getValue())) {
//            // update DS_CONTR_INTFC
//            updateDsConterIntfcOnDelete(cMsg, sMsg, rowIndex);
//
//        } else if (XX_TP_CD_D.equals(sMsg.A.no(rowIndex).xxTpCd_A.getValue())) {
//            if (hasValue(sMsg.A.no(rowIndex).ezUpTime) && hasValue(sMsg.A.no(rowIndex).ezUpTimeZone)) {
//                // delete DS_XS_COPY_INTFC
//                deleteXsCopyIntfc(cMsg, sMsg, rowIndex);
//            }
//
//            // delete sMsg row
//            rowNumberList.add(Integer.valueOf(rowIndex));
//        }
//
//        ZYPTableUtil.deleteRows(sMsg.A, rowNumberList);
//    }
//// END   2016/03/23 [QC#5450, ADD]
    // DELETE end 2016/04/19 CSA Defect#6691

    /**
     * deleteXsCopyInterface
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void deleteXsCopyInterface(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        List<Integer> rowNumberList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (XX_TP_CD_H.equals(sMsg.A.no(i).xxTpCd_A.getValue())) {
                    // update DS_CONTR_INTFC
                    updateDsConterIntfcOnDelete(cMsg, sMsg, i);

                } else if (XX_TP_CD_D.equals(sMsg.A.no(i).xxTpCd_A.getValue())) {
                    // MOD start 2016/04/19 CSA Defect#6691
                    if (hasValue(sMsg.A.no(i).dsXsCopyIntfcPk_A)) {
                        // delete DS_XS_COPY_INTFC
//                        deleteXsCopyIntfc(cMsg, sMsg, i);
                        NSAL0830CommonLogic.deleteForDeleteList(sMsg.A.no(i), cMsg);
                    }
                    // MOD end 2016/04/19 CSA Defect#6691
                }
                // START 2016/09/02 Y.Zhang [QC#12083, MOD]
                // delete sMsg row
                rowNumberList.add(Integer.valueOf(i));
                // END 2016/09/02 Y.Zhang [QC#12083, MOD]
            }
        }
        ZYPTableUtil.deleteRows(sMsg.A, rowNumberList);
        // START 2016/09/02 Y.Zhang [QC#12083, MOD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
        // END 2016/09/02 Y.Zhang [QC#12083, MOD]
    }

    // DELETE start 2016/04/19 CSA Defect#6691
//    private void deleteXsCopyIntfc(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, int i) {
//        DS_XS_COPY_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsXsCopyIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsXsCopyIntfcPk_A.getValue());
//        if (tMsg == null) {
//            cMsg.setMessageInfo(ZZZM9004E);
//            return;
//        }
//        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
//            cMsg.setMessageInfo(ZZZM9004E);
//            return;
//        }
//        EZDTBLAccessor.logicalRemove(tMsg);
//        String rtnCd = tMsg.getReturnCode();
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//            cMsg.setMessageInfo(NSAM0033E, new String[] {DS_XS_COPY_INTFC });
//            return;
//        }
//
//        setValue(sMsg.A.no(i).ezUpTime, tMsg.ezUpTime);
//        setValue(sMsg.A.no(i).ezUpTimeZone, tMsg.ezUpTimeZone);
//    }
    // DELETE end 2016/04/19 CSA Defect#6691

    private void updateDsConterIntfcOnDelete(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, int i) {
        DS_CONTR_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsContrIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrIntfcPk_A.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        sMsg.A.no(i).xsMtrCopyQty_A.clear();
        sMsg.A.no(i).xsMtrAmtRate_A.clear();

        setValue(tMsg.xsMtrCopyQty, sMsg.A.no(i).xsMtrCopyQty_A);
        setValue(tMsg.xsMtrAmtRate, sMsg.A.no(i).xsMtrAmtRate_A);

        EZDTBLAccessor.update(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {DS_CONTR_INTFC });
            return;
        }

        setValue(sMsg.A.no(i).ezUpTime, tMsg.ezUpTime);
        setValue(sMsg.A.no(i).ezUpTimeZone, tMsg.ezUpTimeZone);
    }
}
