/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0850;

import static business.blap.NSAL0850.constant.NSAL0850Constant.*;
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
import business.blap.NSAL0850.common.NSAL0850CommonLogic;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_CONTR_PROC_STSTMsg;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationPrcAllocIntfc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ALLOC_INTFC_STS;
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
 * Sales Credit for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Create          QC#5542
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/27   Hitachi         M.Gotou         Update          QC#11853,11854,12077
 * 2016/08/25   Hitachi         T.Mizuki        Update          QC#11853
 * 2016/09/02   Hitachi         T.Zhang         Update          QC#12083
 * 2016/09/12   Hitachi         T.Tomita        Update          QC#11831
 *</pre>
 */
public class NSAL0850BL06 extends S21BusinessHandler {


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0850Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0850Scrn00_CMN_Save((NSAL0850CMsg) cMsg, (NSAL0850SMsg) sMsg);
            } else if ("NSAL0850Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0850Scrn00_CMN_Delete((NSAL0850CMsg) cMsg, (NSAL0850SMsg) sMsg);
            } else if ("NSAL0850Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0850Scrn00_ValidateData((NSAL0850CMsg) cMsg, (NSAL0850SMsg) sMsg);
            } else if ("NSAL0850Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NSAL0850CMsg) cMsg, (NSAL0850SMsg) sMsg);
            } else if ("NSAL0850Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NSAL0850CMsg) cMsg, (NSAL0850SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Save)
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     */
    private void doProcess_NSAL0850Scrn00_CMN_Save(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {

        NSAL0850CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // ADD start 2016/04/22 CSA Defect#6691
        if (!checkParam(sMsg)) {
            int rowIndex = NSAL0850CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0850CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // ADD end 2016/04/22 CSA Defect#6691

        savePrcAllocInterface(cMsg, sMsg);

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
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     */
    private void doProcess_NSAL0850Scrn00_CMN_Delete(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {

        NSAL0850CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        deletePrcAllocInterface(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     */
    private void doProcess_NSAL0850Scrn00_ValidateData(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {

        NSAL0850CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (!existCheckbox(sMsg)) {
            cMsg.setMessageInfo(NZZM0011E);
            return;
        }

        //ADD start 2016/04/22 CSA Defect#6691
        if (!checkSelectedParam(sMsg)) {
            int rowIndex = NSAL0850CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
            NSAL0850CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return;
        }
        // ADD end 2016/04/22 CSA Defect#6691

        // Validation PRC_ALLOC_INTFC
        List<PRC_ALLOC_INTFCTMsg> prcAllocIntfcTMsgList = NSAL0850CommonLogic.setPrcAllocIntfc(cMsg, sMsg);
        NSXC001001ValidationPrcAllocIntfc.validationPrcAllocIntfc(prcAllocIntfcTMsgList);
        NSAL0850CommonLogic.updateValidationResult(cMsg, sMsg, prcAllocIntfcTMsgList, true);

        // 2016/04/07 START [QC#5662, MOD]
        updateProcStatus(cMsg, sMsg, prcAllocIntfcTMsgList);
        // 2016/04/07 END [QC#5662, MOD]

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }

    // 2016/04/07 [QC#5662, ADD]
    private void updateProcStatus(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg, List<PRC_ALLOC_INTFCTMsg> prcAllocIntfcTMsgList) {

        S21SsmEZDResult ssmResult = NSAL0850Query.getInstance().getStatusUpdateTarget(cMsg.glblCmpyCd.getValue(), prcAllocIntfcTMsgList);
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

    // 2016/04/07 [QC#5662, ADD]
    private String getProcStsName(String glblCmpyCd) {
        DS_CONTR_PROC_STSTMsg tMsg = new DS_CONTR_PROC_STSTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
        tMsg = (DS_CONTR_PROC_STSTMsg) S21CodeTableAccessor.findByKey(tMsg);
        return tMsg.dsContrProcStsDescTxt.getValue();
    }

    // 2016/04/07 [QC#5662, ADD]
    private static DS_CONTR_INTFCTMsg findDsContrIntfcForUpdate(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg prmTMsg = new DS_CONTR_INTFCTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    // 2016/04/07 [QC#5662, ADD]
    private void setProcStsForSMsg(NSAL0850SMsg sMsg, BigDecimal targetPk, String procStsNm) {
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
    private boolean existCheckbox(NSAL0850SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                return true;
            }
        }
        return false;

    }

    /**
     * savePrcAllocInterface
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     */
    private void savePrcAllocInterface(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                if (!hasValue(sMsg.A.no(i).prcAllocIntfcPk_A)) {
                    // insert PRC_ALLOC_INTFC
                    if (!insertPrcAllocInterface(cMsg, sMsg, i)) {
                        return;
                    }
                } else {
                    // update PRC_ALLOC_INTFC
                    if (!updatePrcAllocInterface(cMsg, sMsg, i)) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * insert PRC_ALLOC_INTFC
     * 
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     * @param i index
     */
    private boolean insertPrcAllocInterface(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg, int i) {
        PRC_ALLOC_INTFCTMsg tMsg = new PRC_ALLOC_INTFCTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.prcAllocIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_ALLOC_INTFC_SQ));
        setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
        setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
        setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
        setValue(tMsg.allocIntfcStsCd, ALLOC_INTFC_STS.NORMAL);
        setValue(tMsg.prcAllocIntfcTpCd, sMsg.A.no(i).prcAllocIntfcTpCd_A);
        setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
        setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
        setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
        setValue(tMsg.tocCd, sMsg.A.no(i).tocCd_A);
        setValue(tMsg.tocNm, sMsg.A.no(i).tocNm_A);
        setValue(tMsg.prcAllocRate, sMsg.A.no(i).prcAllocRate_A);
        setValue(tMsg.cpoSvcDtlPk, sMsg.A.no(i).cpoSvcDtlPk_A);

        EZDTBLAccessor.insert(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {PRC_ALLOC_INTFC });
            return false;
        }
        return true;
    }

    /**
     * update PRC_ALLOC_INTFC
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     * @param i index
     */
    private boolean updatePrcAllocInterface(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg, int i) {
        PRC_ALLOC_INTFCTMsg tMsg = NSAL0850CommonLogic.getPrcAllocInterface(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).prcAllocIntfcPk_A.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime.getValue(), sMsg.A.no(i).ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
        setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
        setValue(tMsg.prcAllocIntfcTpCd, sMsg.A.no(i).prcAllocIntfcTpCd_A);
        setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
        setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
        setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
        setValue(tMsg.tocCd, sMsg.A.no(i).tocCd_A);
        setValue(tMsg.tocNm, sMsg.A.no(i).tocNm_A);
        setValue(tMsg.prcAllocRate, sMsg.A.no(i).prcAllocRate_A);
        setValue(tMsg.cpoSvcDtlPk, sMsg.A.no(i).cpoSvcDtlPk_A);

        EZDTBLAccessor.update(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {PRC_ALLOC_INTFC });
            return false;
        }
        return true;
    }

    /**
     * deletePrcAllocInterface
     * 
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     */
    private void deletePrcAllocInterface(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {
        List<Integer> rowNumberList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (hasValue(sMsg.A.no(i).prcAllocIntfcPk_A)) {
                    // for update
                    PRC_ALLOC_INTFCTMsg tMsg = NSAL0850CommonLogic.getPrcAllocInterface(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).prcAllocIntfcPk_A.getValue());
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
                        cMsg.setMessageInfo(NSAM0033E, new String[] {PRC_ALLOC_INTFC });
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

    // ADD start 2016/04/22 CSA Defect#6691
    /**
     * <pre>
     * checkInput for table
     * </pre>
     * @param sMsg NSAL0850SMsg
     * @param i int
     * @return count
     */
    private int setErrorMsg(NSAL0850SMsg sMsg, int i) {
        int count = 0;
        // del start 2016/08/25 CSA QC#11853
//        if (!hasValue(sMsg.A.no(i).dsContrIntfcPk_A)) {
//            // START 2016/07/27 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0559E));
//            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0559E);
//            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0559E);
//            // END 2016/07/27 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
//            count++;
//        }
        // del end 2016/08/25 CSA QC#11853

        if (!hasValue(sMsg.A.no(i).dsContrIntfcBatNum_A)) {
            // START 2016/07/27 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM}));
            // END 2016/07/27 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).dsContrSrcRefNum_A)) {
            // START 2016/07/27 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM}));
            // END 2016/07/27 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).contrIntfcSrcTpCd_A)) {
            // START 2016/07/27 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD}));
            // END 2016/07/27 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).prcAllocIntfcTpCd_A)) {
            // START 2016/07/27 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {PRC_ALLOC_INTFC_TP_CD}));
            // END 2016/07/27 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {PRC_ALLOC_INTFC_TP_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

        if (!hasValue(sMsg.A.no(i).tocCd_A)) {
            // START 2016/07/27 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {TOC_CD}));
            // END 2016/07/27 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {TOC_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }

     // START 2016/07/27 M.Gotou [QC#12077, ADD]
        if (hasValue(sMsg.A.no(i).svcMachMstrPk_A)) {
            SVC_MACH_MSTRTMsg tMsg = NSAL0850CommonLogic.getSvcMachMstr(getGlobalCompanyCode(), sMsg.A.no(i).svcMachMstrPk_A.getValue());
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
                BigDecimal svcMachMstrPk = NSAL0850CommonLogic.getIbId(getGlobalCompanyCode(), sMsg.A.no(i).serNum_A.getValue());
                if (!hasValue(svcMachMstrPk)) {
                    setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SER_NUM}));
                    sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SER_NUM});
                    setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                    setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    count++;
                }
            }
        }
        // END 2016/07/27 M.Gotou [QC#12077, ADD]

        // START 2016/09/12 T.Tomita [QC#11831, ADD]
        S21SsmEZDResult ssmResult = NSAL0850Query.getInstance().getDsContrIntfcPk(getGlobalCompanyCode(), sMsg.A.no(i));
        Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
        if (rs == null) {
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0559E));
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0559E);
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0559E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
        }
        // END 2016/09/12 T.Tomita [QC#11831, ADD]

        return count;
    }

    private boolean checkParam(NSAL0850SMsg sMsg) {
        boolean flg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (setErrorMsg(sMsg, i) > 0) {
                flg = false;
            }
        }
        return flg;
    }

    private boolean checkSelectedParam(NSAL0850SMsg sMsg) {
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
