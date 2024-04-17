/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1150;

import static business.blap.NPAL1150.constant.NPAL1150Constant.ASTERISK;
import static business.blap.NPAL1150.constant.NPAL1150Constant.COMMIT_VALUE;
import static business.blap.NPAL1150.constant.NPAL1150Constant.DISP_ITEM_NAME_PO_LINE_NUM;
import static business.blap.NPAL1150.constant.NPAL1150Constant.DISP_ITEM_NAME_PO_NUM;
import static business.blap.NPAL1150.constant.NPAL1150Constant.ITFC_ID_HEADER;
import static business.blap.NPAL1150.constant.NPAL1150Constant.LINE_NUM_LENGTH;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NAME_XX_CHECK_BOX;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM0005I;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM0049E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1173E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1242E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1285E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1287E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1297E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1299E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1308W;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1309W;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1322E;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1322E_PRM1;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1322E_PRM2;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1322E_PRM3;
import static business.blap.NPAL1150.constant.NPAL1150Constant.NPAM1322E_PRM4;
import static business.blap.NPAL1150.constant.NPAL1150Constant.REGEX_NUMBER;
import static business.blap.NPAL1150.constant.NPAL1150Constant.UNDER_TAB_DETAIL;
import static business.blap.NPAL1150.constant.NPAL1150Constant.UNDER_TAB_HEADER;
import static business.blap.NPAL1150.constant.NPAL1150Constant.ZZZM9013E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1150.common.NPAL1150CommonLogic;
import business.db.ASN_EDI_PROC_STSTMsg;
import business.db.EDI_ASN_DTL_WRKTMsg;
import business.db.EDI_ASN_HDR_WRKTMsg;
import business.db.EDI_PO_DTL_XREFTMsg;
import business.db.EDI_PO_DTL_XREFTMsgArray;
import business.db.POTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASN_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/05/30   Hitachi         T.Kawazu        Update          QC1233
 * 2013/06/13   Hitachi         T.Tomita        Update          QC1233
 * 2013/07/24   Hitachi         T.Kawazu        Update          QC1419
 *</pre>
 */

public class NPAL1150BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1150Scrn00_Reprocess".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Reprocess((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_Cancel".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Cancel((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_CMN_Save((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * doProcess_NPAL1150Scrn00_CMN_Save
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_CMN_Save(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        boolean isError = false;
        boolean isWarning = false;

        if (!ZYPCommonFunc.hasValue(cMsg.B.no(0).poOrdNum_H1)) {

            cMsg.B.no(0).poOrdNum_H1.setErrorInfo(1, NPAM1173E, new String[] {DISP_ITEM_NAME_PO_NUM });
            setValue(cMsg.xxDplyTab, UNDER_TAB_HEADER);
            return;

        }

        if (!checkPo(cMsg)) {

            setValue(cMsg.xxDplyTab, UNDER_TAB_HEADER);
            return;

        }

        String showAllFlg = cMsg.xxChkBox_H1.getValue();

        copyDetailListToSave(cMsg, sMsg);

        boolean hasNullEdiLineNum = false;

        for (int idx = 0; idx < cMsg.C.getValidCount(); idx++) {

            if (!ZYPCommonFunc.hasValue(cMsg.C.no(idx).ediPoOrdDtlLineNum_HD)) {

                hasNullEdiLineNum = true;

            }

            if (!ZYPCommonFunc.hasValue(cMsg.C.no(idx).ediPoOrdDtlLineNum_HD) && !ZYPCommonFunc.hasValue(cMsg.C.no(idx).dispPoDtlLineNum_D1)) {

                cMsg.C.no(idx).dispPoDtlLineNum_D1.setErrorInfo(1, NPAM1173E, new String[] {DISP_ITEM_NAME_PO_LINE_NUM });
                setValue(cMsg.xxDplyTab, UNDER_TAB_DETAIL);
                isError = true;
                continue;
            }

            if (ZYPCommonFunc.hasValue(cMsg.C.no(idx).dispPoDtlLineNum_D1)) {

                if (!cMsg.C.no(idx).dispPoDtlLineNum_D1.getValue().matches(REGEX_NUMBER)) {

                    cMsg.C.no(idx).dispPoDtlLineNum_D1.setErrorInfo(1, NPAM1242E, new String[] {DISP_ITEM_NAME_PO_LINE_NUM });
                    isError = true;
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).dispPoDtlLineNum_HD, cMsg.C.no(idx).dispPoDtlLineNum_D1.getValue());

            }

            // PO_DTL MdseCd check
            List<Boolean> result = checkPoDtl(cMsg, idx);

            // there are ERROR , Result(0) TRUE
            if (result.get(0) == true) {
                isError = true;
            }

            // there are WARNING , Result(1) TRUE
            if (result.get(1) == true) {
                isWarning = true;
            }

            if (isWarning) {

                if (!cMsg.C.no(idx).dispPoDtlLineNum_HD.getValue().equals(cMsg.C.no(idx).dispPoDtlLineNum_BK.getValue())) {
                    setValue(cMsg.xxErrFlg_A1, ZYPConstant.FLG_OFF_0);
                }

                setValue(cMsg.C.no(idx).dispPoDtlLineNum_BK, cMsg.C.no(idx).dispPoDtlLineNum_HD);
            }
        }

        if (isError) {
            return;
        }

        // 1st time Warning return , 2nd time update
        if (isWarning && cMsg.xxErrFlg_A1.getValue().equals(ZYPConstant.FLG_OFF_0)) {
            cMsg.setMessageInfo(NPAM1308W);
            setValue(cMsg.xxErrFlg_A1, ZYPConstant.FLG_ON_1);
            return;
        }

        // Update process
        updateForSave(cMsg, sMsg, hasNullEdiLineNum);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(showAllFlg)) {
                cMsg.xxChkBox_H1.clear();
                NPAL1150CommonLogic.detailListDisp(cMsg, sMsg);
            }
            setValue(cMsg.xxDplyTab, UNDER_TAB_HEADER);
            cMsg.setMessageInfo(NPAM0005I);
        }
    }

    /**
     * updateForSave
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     * @param hasNullEdiLineNum boolean
     */
    private void updateForSave(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg, boolean hasNullEdiLineNum) {

        // Update EDI_ASN_HDR_WRK. Reprocess
        EDI_ASN_HDR_WRKTMsg hInTMsg = new EDI_ASN_HDR_WRKTMsg();
        setValue(hInTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(hInTMsg.asnSoNum, cMsg.B.no(0).asnSoNum_H1.getValue());
        int selectLine = cMsg.xxNum.getValueInt();
        int fromNo = cMsg.xxPageShowFromNum.getValueInt();
        int sMsgIndex = selectLine + fromNo - 1;

        boolean rtnValue = NPAL1150CommonLogic.editEdiAsnHdrWrkToReprocess(cMsg, sMsg, sMsgIndex, hInTMsg);

        // rtnValue = false , there are Error.
        if (!rtnValue) {
            return;
        }

        if (!updatePoToXref(cMsg)) {
            return;
        }

        // Update EDI_ASN_HDR_WRK, EDI_PO_DTL_XREFTMsg
        String preEdiLineNum = "";
        String poOrdDtlLineNUm = "";
        for (int idx = 0; idx < cMsg.C.getValidCount(); idx++) {

            if (!hasNullEdiLineNum) {

                if (!NPAL1150CommonLogic.isSameEdiLineNum(cMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue(), preEdiLineNum)) {

                    // edit xref
                    if (!editXRef(cMsg, idx)) {
                        return;
                    }
                }

            } else {

                if (!NPAL1150CommonLogic.isSameEdiLineNum(cMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue(), preEdiLineNum)) {
                    poOrdDtlLineNUm = getPoOrdLineNum(cMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue(), cMsg.C.no(idx).dispPoDtlLineNum_HD.getValue());
                }

                // edit EDI_ASN_DTL_WRK
                if (!dtlUpdate(cMsg, idx, poOrdDtlLineNUm)) {
                    return;
                }
            }

            preEdiLineNum = cMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue();
        }

        // Update EDI_ASN_HDR_WRK
        EDI_ASN_HDR_WRKTMsg eahwTMsg = new EDI_ASN_HDR_WRKTMsg();
        setValue(eahwTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(eahwTMsg.asnSoNum, cMsg.B.no(0).asnSoNum_H1.getValue());
        eahwTMsg = (EDI_ASN_HDR_WRKTMsg) EZDTBLAccessor.findByKey(hInTMsg);
        if (eahwTMsg == null) {
            cMsg.setMessageInfo(NPAM1299E);
            return;
        }
        if (!eahwTMsg.getReturnCode().equals(RTNCD_NORMAL)) {
            cMsg.setMessageInfo(NPAM1299E);
            return;
        }
        setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).ezUpTime_B1, eahwTMsg.ezUpTime);
        setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).ezUpTimeZone_B1, eahwTMsg.ezInTimeZone);

        ASN_EDI_PROC_STSTMsg aepsTMsg = new ASN_EDI_PROC_STSTMsg();

        setValue(aepsTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(aepsTMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.REPROCESS);

        aepsTMsg = (ASN_EDI_PROC_STSTMsg) EZDTBLAccessor.findByKey(aepsTMsg);

        if (aepsTMsg == null) {
            cMsg.setMessageInfo(NPAM1299E);
            return;
        }

        if (!aepsTMsg.getReturnCode().equals(RTNCD_NORMAL)) {
            cMsg.setMessageInfo(NPAM1299E);
            return;
        }

        setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).openAsnWrkFlg_B1, aepsTMsg.openAsnWrkFlg);
        setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).asnEdiProcStsNm_B1, ZYPCodeDataUtil.getName(ASN_EDI_PROC_STS.class, getGlobalCompanyCode(), ASN_EDI_PROC_STS.REPROCESS));
        setValue(cMsg.xxErrFlg_A1, ZYPConstant.FLG_OFF_0);

    }

    /**
     * editXRef
     * @param cMsg NPAL1150CMsg
     * @param idx int
     * @return
     */
    private boolean editXRef(NPAL1150CMsg cMsg, int idx) {

        return xRefUpdate(cMsg, idx);

    }

    /**
     * checkPoDtl
     * @param cMsg NPAL1150CMsg
     * @param idx int
     * @return List<Boolean>
     */
    private List<Boolean> checkPoDtl(NPAL1150CMsg cMsg, int idx) {

        boolean isError = false;
        boolean isWarning = false;

        String poOrdNum = cMsg.B.no(0).poOrdNum_H1.getValue();
        String dispPoDtlLineNum = cMsg.C.no(idx).dispPoDtlLineNum_HD.getValue();

        Map<String, Object> poDtlResult = NPAL1150Query.getInstance().getPoDtl(poOrdNum, dispPoDtlLineNum);

        if (poDtlResult == null) {

            cMsg.C.no(idx).dispPoDtlLineNum_D1.setErrorInfo(1, NPAM1287E);
            isError = true;

        } else {

            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).dispPoDtlLineNum_HD, (String) poDtlResult.get("PO_ORD_DTL_LINE_NUM"));

            if (PO_STS.CANCELLED.equals(poDtlResult.get("PO_STS_CD"))) {

                cMsg.C.no(idx).dispPoDtlLineNum_D1.setErrorInfo(1, NPAM1285E, new String[] {DISP_ITEM_NAME_PO_LINE_NUM });
                isError = true;

            } else {

                if (!poDtlResult.get("ASL_MDSE_CD").equals(cMsg.C.no(idx).mdseCd_D1.getValue())) {
                    cMsg.C.no(idx).dispPoDtlLineNum_D1.setErrorInfo(2, NPAM1309W);
                    isWarning = true;
                }

            }

        }

        List<Boolean> result = new ArrayList<Boolean>();
        result.add(isError);
        result.add(isWarning);
        return result;
    }

    /**
     * xRefUpdate
     * @param cMsg NPAL1150CMsg
     * @param poOrdNum
     * @param idx int
     * @return
     */
    private boolean xRefUpdate(NPAL1150CMsg cMsg, int idx) {

        String poOrdNum = cMsg.B.no(0).poOrdNum_H1.getValue();

        EDI_PO_DTL_XREFTMsg xInTMsg = new EDI_PO_DTL_XREFTMsg();
        setValue(xInTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(xInTMsg.ediPoOrdNum, cMsg.B.no(0).ediPoOrdNum_H1);
        setValue(xInTMsg.ediPoOrdDtlLineNum, cMsg.C.no(idx).ediPoOrdDtlLineNum_HD);

        EDI_PO_DTL_XREFTMsg xOutTMsg = (EDI_PO_DTL_XREFTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(xInTMsg);

        if (xOutTMsg == null) {

            // XRef Insert
            xOutTMsg = new EDI_PO_DTL_XREFTMsg();
            ZYPEZDItemValueSetter.setValue(xOutTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(xOutTMsg.ediPoOrdNum, cMsg.B.no(0).ediPoOrdNum_H1);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.ediPoOrdDtlLineNum, cMsg.C.no(idx).ediPoOrdDtlLineNum_HD);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdNum, poOrdNum);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdDtlLineNum, getPoOrdLineNum(cMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue(), cMsg.C.no(idx).dispPoDtlLineNum_HD.getValue()));

            EZDTBLAccessor.insert(xOutTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xOutTMsg.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9013E, new String[] {xOutTMsg.getReturnCode() });
                return false;
            }

        } else {

            // Xref Update
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdNum, poOrdNum);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdDtlLineNum, getPoOrdLineNum(cMsg.C.no(idx).ediPoOrdDtlLineNum_HD.getValue(), cMsg.C.no(idx).dispPoDtlLineNum_HD.getValue()));

            EZDTBLAccessor.update(xOutTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xOutTMsg.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9013E, new String[] {xOutTMsg.getReturnCode() });
                return false;
            }

        }
        return true;
    }

    /**
     * dtlUpdate
     * @param cMsg NPAL1150CMsg
     * @param poOrdNum
     * @param idx int
     * @return
     */
    private boolean dtlUpdate(NPAL1150CMsg cMsg, int idx, String poOrdDtlLineNUm) {

        EDI_ASN_DTL_WRKTMsg eadwTMsg = new EDI_ASN_DTL_WRKTMsg();
        setValue(eadwTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(eadwTMsg.asnSoNum, cMsg.C.no(idx).asnSoNum_D1);
        setValue(eadwTMsg.asnLineNum, cMsg.C.no(idx).asnLineNum_D1);

        eadwTMsg = (EDI_ASN_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(eadwTMsg);

        if (eadwTMsg == null) {
            cMsg.setMessageInfo(NPAM1299E);
            return false;
        }

        if (!eadwTMsg.getReturnCode().equals(RTNCD_NORMAL)) {
            cMsg.setMessageInfo(NPAM1299E);
            return false;
        }

        setValue(eadwTMsg.poOrdDtlLineNum, poOrdDtlLineNUm);

        EZDTBLAccessor.update(eadwTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(eadwTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9013E, new String[] {eadwTMsg.getReturnCode() });
            return false;
        }

        return true;
    }

    /**
     * doProcess_NPAL1150Scrn00_Cancel
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Cancel(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        // cMsg -> sMsg
        NPAL1150CommonLogic.copyFromCMsgOntoSMsg(cMsg, sMsg);

        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(sMsg.A, NAME_XX_CHECK_BOX, ZYPConstant.CHKBOX_ON_Y);
        if (chkYList.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        for (int idx = 0; idx < chkYList.size(); idx++) {

            int rowIndex = chkYList.get(idx).intValue();
            EDI_ASN_HDR_WRKTMsg headerTMsg = new EDI_ASN_HDR_WRKTMsg();
            setValue(headerTMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(headerTMsg.asnSoNum, sMsg.A.no(rowIndex).asnSoNum_B1.getValue());

            headerTMsg = (EDI_ASN_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(headerTMsg);

            if (headerTMsg == null) {
                cMsg.setMessageInfo(NPAM1297E);
                return;
            }

            if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(rowIndex).ezUpTime_B1.getValue(), sMsg.A.no(rowIndex).ezUpTimeZone_B1.getValue(), headerTMsg.ezUpTime.getValue(), headerTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NPAM1297E);
                return;
            }

            setValue(headerTMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.CANCELLED);
            setValue(headerTMsg.procFlg, ZYPConstant.FLG_ON_1);

            EZDTBLAccessor.update(headerTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(headerTMsg.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9013E, new String[] {headerTMsg.getReturnCode() });
                return;
            }
        }

        cMsg.setTransactionMode(COMMIT_VALUE);

    }

    /**
     * doProcess_NPAL1150Scrn00_Reprocess
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Reprocess(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        // cMsg -> sMsg
        NPAL1150CommonLogic.copyFromCMsgOntoSMsg(cMsg, sMsg);

        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(sMsg.A, NAME_XX_CHECK_BOX, ZYPConstant.CHKBOX_ON_Y);
        if (chkYList.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        for (int idx = 0; idx < chkYList.size(); idx++) {

            int rowIndex = chkYList.get(idx).intValue();
            EDI_ASN_HDR_WRKTMsg headerTMsg = new EDI_ASN_HDR_WRKTMsg();
            setValue(headerTMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(headerTMsg.asnSoNum, sMsg.A.no(rowIndex).asnSoNum_B1.getValue());

            boolean rtnValue = NPAL1150CommonLogic.editEdiAsnHdrWrkToReprocess(cMsg, sMsg, rowIndex, headerTMsg);

            if (!rtnValue) {
                return;
            }
        }
        cMsg.setTransactionMode(COMMIT_VALUE);
    }

    /**
     * Check PO
     * @param cMsg NPAL1150CMsg
     */
    private boolean checkPo(NPAL1150CMsg cMsg) {

        POTMsg inMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, cMsg.B.no(0).poOrdNum_H1);
        POTMsg result = (POTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (result == null) {
            cMsg.B.no(0).poOrdNum_H1.setErrorInfo(1, NPAM1287E);
            return false;
        }

        // PO Status check
        if (PO_STS.CANCELLED.equals(result.poStsCd.getValue())) {
            cMsg.B.no(0).poOrdNum_H1.setErrorInfo(1, NPAM1285E);
            return false;
        }

        // Vender check
        if (isPoVndCdError(cMsg, result)) {
            cMsg.B.no(0).poOrdNum_H1.setErrorInfo(1, NPAM1322E, new String[] {NPAM1322E_PRM1, NPAM1322E_PRM2, NPAM1322E_PRM3, NPAM1322E_PRM4 });
            return false;
        }

        return true;
    }

    /**
     * isPoVndCdError Add QC1419
     * @param cMsg
     * @param result
     * @return
     */
    private boolean isPoVndCdError(NPAL1150CMsg cMsg, POTMsg result) {

        // EDI_ASN_HDR_WRK.VND_CD has value.
        if (ZYPCommonFunc.hasValue(cMsg.B.no(0).vndCd_H1)) {

            if (!cMsg.B.no(0).vndCd_H1.getValue().equals(result.vndCd.getValue())) {

                return true;
            }

            return false;
        }

        // EDI_ASN_HDR_WRK.VND_CD is null
        if (!ZYPCommonFunc.hasValue(cMsg.B.no(0).itrlIntfcId_H1.getValue())) {
            return true;
        }

        String keyCode = ITFC_ID_HEADER + cMsg.B.no(0).itrlIntfcId_H1.getValue();
        String keyVelue = ZYPCodeDataUtil.getVarCharConstValue(keyCode, getGlobalCompanyCode());

        if (keyVelue == null) {
            return true;
        }

        if (keyVelue.indexOf(result.vndCd.getValue()) == -1) {
            return true;
        }

        return false;
    }

    /**
     * Update PO to EDI_PO_DTL_XREF
     * @param cMsg NPAL1150CMsg
     */
    private boolean updatePoToXref(NPAL1150CMsg cMsg) {

        EDI_PO_DTL_XREFTMsg inMsg = new EDI_PO_DTL_XREFTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("ediPoOrdNum01", cMsg.B.no(0).ediPoOrdNum_H1.getValue());

        EDI_PO_DTL_XREFTMsgArray resultArray = (EDI_PO_DTL_XREFTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

        if (resultArray == null || resultArray.getValidCount() == 0) {

            // Insert
            EDI_PO_DTL_XREFTMsg xrefTMsg = new EDI_PO_DTL_XREFTMsg();
            ZYPEZDItemValueSetter.setValue(xrefTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(xrefTMsg.ediPoOrdNum, cMsg.B.no(0).ediPoOrdNum_H1);
            ZYPEZDItemValueSetter.setValue(xrefTMsg.ediPoOrdDtlLineNum, ASTERISK);
            ZYPEZDItemValueSetter.setValue(xrefTMsg.poOrdNum, cMsg.B.no(0).poOrdNum_H1);
            ZYPEZDItemValueSetter.setValue(xrefTMsg.poOrdDtlLineNum, ASTERISK);

            EZDTBLAccessor.insert(xrefTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xrefTMsg.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9013E, new String[] {xrefTMsg.getReturnCode() });
                return false;
            }

        } else {
            // Update
            for (int i = 0; i < resultArray.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(resultArray.no(i).poOrdNum, cMsg.B.no(0).poOrdNum_H1);

                EZDTBLAccessor.update(resultArray.no(i));

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(resultArray.no(i).getReturnCode())) {
                    cMsg.setMessageInfo(ZZZM9013E, new String[] {resultArray.no(i).getReturnCode() });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getPoOrdLineNum
     * @param ediPoOrdLineNum String
     * @param poOrdLineNum String
     * @return
     */
    private String getPoOrdLineNum(String ediPoOrdLineNum, String poOrdLineNum) {

        String rtnLineNum = poOrdLineNum;

        if (ZYPCommonFunc.hasValue(rtnLineNum)) {

            return rtnLineNum;

        }

        if (ZYPCommonFunc.hasValue(ediPoOrdLineNum)) {

            if (ediPoOrdLineNum.length() > LINE_NUM_LENGTH) {

                rtnLineNum = ediPoOrdLineNum.substring(ediPoOrdLineNum.length() - LINE_NUM_LENGTH);

            } else {

                rtnLineNum = ediPoOrdLineNum;
            }
        }
        return rtnLineNum;
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void copyDetailListToSave(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        NPAL1150CommonLogic.copyFromCCListOntoSCList(cMsg, sMsg);
        cMsg.xxChkBox_H1.setValue(ZYPConstant.CHKBOX_ON_Y);
        NPAL1150CommonLogic.detailListDisp(cMsg, sMsg);
    }

}
