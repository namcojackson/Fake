/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1140;

import static business.blap.NPAL1140.constant.NPAL1140Constant.DISP_ITEM_NAME_PO_LINE_NUM;
import static business.blap.NPAL1140.constant.NPAL1140Constant.DISP_ITEM_NAME_PO_ORD_NUM;
import static business.blap.NPAL1140.constant.NPAL1140Constant.INTFC_ID_HEADER;
import static business.blap.NPAL1140.constant.NPAL1140Constant.LINE_NUM_LENGTH;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NLAM0023E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM0005I;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1173E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1242E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1281W;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1285E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1287E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1297E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1303W;
import static business.blap.NPAL1140.constant.NPAL1140Constant.MSG_ID_ZZZM9013E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.NPAM1322E;
import static business.blap.NPAL1140.constant.NPAL1140Constant.NPAM1322E_PRM1;
import static business.blap.NPAL1140.constant.NPAL1140Constant.NPAM1322E_PRM2;
import static business.blap.NPAL1140.constant.NPAL1140Constant.NPAM1322E_PRM3;
import static business.blap.NPAL1140.constant.NPAL1140Constant.NPAM1322E_PRM4;
import static business.blap.NPAL1140.constant.NPAL1140Constant.PADDING_ZERO;
import static business.blap.NPAL1140.constant.NPAL1140Constant.REGEX_NUMBER;
import static business.blap.NPAL1140.constant.NPAL1140Constant.STR_ASTERISK;
import static business.blap.NPAL1140.constant.NPAL1140Constant.TAB_DETAIL;
import static business.blap.NPAL1140.constant.NPAL1140Constant.TAB_HEADER;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1140.common.NPAL1140CommonLogic;
import business.db.EDI_PO_DTL_XREFTMsg;
import business.db.EDI_PO_DTL_XREFTMsgArray;
import business.db.POTMsg;
import business.db.PO_ACK_DTL_WRKTMsg;
import business.db.PO_ACK_HDR_WRKTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_DTLTMsgArray;
import business.servlet.NPAL1140.NPAL1140Bean;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACK_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 * 07/10/2013   Hitachi         K.Kishimoto     Update          QC1233
 * 07/26/2013   Hitachi         T.Kawazu        Update          QC1419
 * 04/11/2017   CITS            R.Shimamoto     Update          QC#18205
 *</pre>
 */
public class NPAL1140BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            NPAL1140CMsg bizMsg = (NPAL1140CMsg) cMsg;
            NPAL1140SMsg globalMsg = (NPAL1140SMsg) sMsg;
            String screenAplID = bizMsg.getScreenAplID();

            if ("NPAL1140Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_CMN_Save(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_ackReprocess".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_ackReprocess(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_ackCancel".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_ackCancel(bizMsg, globalMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL1140Scrn00_CMN_Save(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        boolean errFlg = false;
        boolean warnFlg = false;
        String beforeLine = "";
        ZYPEZDItemValueSetter.setValue(bizMsg.xxErrFlg, ZYPConstant.FLG_OFF_0);

        String poOrdNum = bizMsg.poOrdNum_HT.getValue();

        if (!ZYPCommonFunc.hasValue(bizMsg.poOrdNum_HT)) {
            bizMsg.poOrdNum_HT.setErrorInfo(1, MSG_ID_NPAM1173E, new String[] {DISP_ITEM_NAME_PO_ORD_NUM });
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_HEADER);
            return;
        } else {
            if (!isValidPo(bizMsg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_HEADER);
                return;
            }
        }

        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);

        String showAll = bizMsg.xxChkBox_B0.getValue();
        globalMsg.xxChkBox_B0.setValue(ZYPConstant.CHKBOX_ON_Y);

        NPAL1140CommonLogic.detailListDisp(bizMsg, globalMsg);

        boolean hasNullEdiLineNum = false;

        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD)) {
                hasNullEdiLineNum = true;
            }

            if (!NPAL1140CommonLogic.isSameEdiLineNum(beforeLine, bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue())) {
                // QC#18205 Mod.
//                if (ZYPCommonFunc.hasValue(bizMsg.B.no(idx).poOrdDtlLineNum_B0)) {
                if (ZYPCommonFunc.hasValue(bizMsg.B.no(idx).dispPoDtlLineNum_B0)) {
//                    if (!bizMsg.B.no(idx).poOrdDtlLineNum_B0.getValue().matches(REGEX_NUMBER)
                    if (!bizMsg.B.no(idx).dispPoDtlLineNum_B0.getValue().matches(REGEX_NUMBER)
//                            || Integer.parseInt(bizMsg.B.no(idx).poOrdDtlLineNum_B0.getValue().substring(0, 1)) == 0) {
                            || Integer.parseInt(bizMsg.B.no(idx).dispPoDtlLineNum_B0.getValue().substring(0, 1)) == 0) {
//                        bizMsg.B.no(idx).poOrdDtlLineNum_B0.setErrorInfo(1, MSG_ID_NPAM1242E, new String[] {DISP_ITEM_NAME_PO_LINE_NUM });
                        bizMsg.B.no(idx).dispPoDtlLineNum_B0.setErrorInfo(1, MSG_ID_NPAM1242E, new String[] {DISP_ITEM_NAME_PO_LINE_NUM });
                        errFlg = true;

                    } else {
                        String dtlLine = ZYPCommonFunc.leftPad(bizMsg.B.no(idx).poOrdDtlLineNum_HD.getValue(), LINE_NUM_LENGTH, PADDING_ZERO);
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).poOrdDtlLineNum_HD, dtlLine);

                        List<Boolean> result = checkPoDtl(poOrdNum, dtlLine, bizMsg.B.no(idx).poOrdDtlLineNum_HD, bizMsg.B.no(idx).poOrdDtlLineNum_BS, bizMsg.B.no(idx).mdseCd_B0);

                        if (result.get(0) == true) {
                            errFlg = true;
                        }

                        if (result.get(1) == true) {
                            bizMsg.B.no(idx).batErrMsgTxt_BV.setErrorInfo(2, MSG_ID_NPAM1281W);
                            warnFlg = true;
                        }
                    }

                } else {
                    // QC#18205 Mod.
//                    if (!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).poOrdDtlLineNum_B0)) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).dispPoDtlLineNum_B0)) {
//                        bizMsg.B.no(idx).poOrdDtlLineNum_B0.setErrorInfo(1, MSG_ID_NPAM1173E, new String[] {DISP_ITEM_NAME_PO_LINE_NUM });
                        bizMsg.B.no(idx).dispPoDtlLineNum_B0.setErrorInfo(1, MSG_ID_NPAM1173E, new String[] {DISP_ITEM_NAME_PO_LINE_NUM });
                        errFlg = true;

                    } else {
                        String dtlLine = getPoOrdDtlLineNum(bizMsg.B.no(idx).poOrdDtlLineNum_HD.getValue(), bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue());

                        List<Boolean> result = checkPoDtl(poOrdNum, dtlLine, bizMsg.B.no(idx).poOrdDtlLineNum_HD, bizMsg.B.no(idx).poOrdDtlLineNum_BS, bizMsg.B.no(idx).mdseCd_B0);

                        if (result.get(0) == true) {
                            errFlg = true;
                        }

                        if (result.get(1) == true) {
                            warnFlg = true;
                        }
                    }
                }
            }
            beforeLine = bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue();
        }

        EZDMsg.copy(bizMsg.B, null, globalMsg.B, null);

        if (errFlg) {
            NPAL1140CommonLogic.claerWarningLine(bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DETAIL);
            return;
        }

        if (warnFlg) {
            bizMsg.setMessageInfo(MSG_ID_NPAM1303W);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxErrFlg, ZYPConstant.FLG_ON_1);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DETAIL);
            return;
        }

        // Update PO_ACK_HDR_WRK
        PO_ACK_HDR_WRKTMsg hInTMsg = new PO_ACK_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(hInTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(hInTMsg.poAckHdrWrkPk, bizMsg.poAckHdrWrkPk_HT);

        PO_ACK_HDR_WRKTMsg hOutMsg = (PO_ACK_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(hInTMsg);

        if (hOutMsg == null) {
            bizMsg.setMessageInfo(MSG_ID_NPAM1297E);
            NPAL1140CommonLogic.claerWarningLine(bizMsg);
            return;
        }

        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_HT.getValue(), bizMsg.ezUpTimeZone_HT.getValue(), hOutMsg.ezUpTime.getValue(), hOutMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(MSG_ID_NPAM1297E);
            NPAL1140CommonLogic.claerWarningLine(bizMsg);
            return;
        }

        ZYPEZDItemValueSetter.setValue(hOutMsg.ackEdiProcStsCd, ACK_EDI_PROC_STS.REPROCESS);
        ZYPEZDItemValueSetter.setValue(hOutMsg.poAckUpdProcFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.update(hOutMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hOutMsg.getReturnCode())) {
            bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {hInTMsg.getReturnCode() });
            NPAL1140CommonLogic.claerWarningLine(bizMsg);
            return;
        }

        // Update EDI_PO_DTL_XREF
        if (!xRefPoOrdNumUpdate(bizMsg)) {
            return;
        }
        beforeLine = "";
        String poLineNum = "";
        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            if (!hasNullEdiLineNum) {
                if (!NPAL1140CommonLogic.isSameEdiLineNum(beforeLine, bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue())) {
                    if (!xRefUpdate(bizMsg, idx)) {
                        return;
                    }
                }
            } else {
                if (!NPAL1140CommonLogic.isSameEdiLineNum(beforeLine, bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue())) {
                    poLineNum = getPoOrdDtlLineNum(bizMsg.B.no(idx).poOrdDtlLineNum_HD.getValue(), bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue());
                }
                if (!poAckDtlWrkUpdate(bizMsg, idx, poLineNum)) {
                    return;
                }
            }
            beforeLine = bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue();
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            NPAL1140CommonLogic.detailListDisp(bizMsg, globalMsg);
            bizMsg.setMessageInfo(MSG_ID_NPAM0005I);
            if (!ZYPConstant.CHKBOX_ON_Y.equals(showAll)) {
                globalMsg.xxChkBox_B0.clear();
                NPAL1140CommonLogic.detailListDisp(bizMsg, globalMsg);
            }
        }
    }

    private boolean xRefPoOrdNumUpdate(NPAL1140CMsg bizMsg) {
        EDI_PO_DTL_XREFTMsg xInTMsg = new EDI_PO_DTL_XREFTMsg();
        xInTMsg.setSQLID("001");
        xInTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        xInTMsg.setConditionValue("ediPoOrdNum01", bizMsg.ediPoOrdNum_HT.getValue());
        EDI_PO_DTL_XREFTMsgArray xOutTMsgArray = (EDI_PO_DTL_XREFTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(xInTMsg);
        if (xOutTMsgArray.getValidCount() == 0) {
            EDI_PO_DTL_XREFTMsg xOutTMsg = new EDI_PO_DTL_XREFTMsg();
            ZYPEZDItemValueSetter.setValue(xOutTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(xOutTMsg.ediPoOrdNum, bizMsg.ediPoOrdNum_HT.getValue());
            ZYPEZDItemValueSetter.setValue(xOutTMsg.ediPoOrdDtlLineNum, STR_ASTERISK);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdNum, bizMsg.poOrdNum_HT.getValue());
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdDtlLineNum, STR_ASTERISK);
            EZDTBLAccessor.create(xOutTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xOutTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {xOutTMsg.getReturnCode() });
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return false;
            }
        } else {
            EDI_PO_DTL_XREFTMsg xOutTMsg;
            for (int idx = 0; idx < xOutTMsgArray.getValidCount(); idx++) {
                xOutTMsg = xOutTMsgArray.no(idx);
                ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdNum, bizMsg.poOrdNum_HT.getValue());
                EZDTBLAccessor.update(xOutTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xOutTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {xOutTMsg.getReturnCode() });
                    NPAL1140CommonLogic.claerWarningLine(bizMsg);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean poAckDtlWrkUpdate(NPAL1140CMsg bizMsg, int idx, String poOrdDtlLineNum) {
        PO_ACK_DTL_WRKTMsg inPoAckDtlWrkTMsg = new PO_ACK_DTL_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inPoAckDtlWrkTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inPoAckDtlWrkTMsg.poOrdNum, bizMsg.poOrdNum_HT);
        ZYPEZDItemValueSetter.setValue(inPoAckDtlWrkTMsg.poAckDtlWrkPk, bizMsg.B.no(idx).poAckDtlWrkPk_B0);
        PO_ACK_DTL_WRKTMsg outPoAckDtlWrkTMsg = (PO_ACK_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inPoAckDtlWrkTMsg);
        if (outPoAckDtlWrkTMsg == null) {
            bizMsg.setMessageInfo(MSG_ID_NPAM1297E);
            NPAL1140CommonLogic.claerWarningLine(bizMsg);
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(outPoAckDtlWrkTMsg.poOrdDtlLineNum, poOrdDtlLineNum);
            EZDTBLAccessor.update(outPoAckDtlWrkTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outPoAckDtlWrkTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {outPoAckDtlWrkTMsg.getReturnCode() });
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return false;
            }
        }
        return true;
    }

    private boolean xRefUpdate(NPAL1140CMsg bizMsg, int idx) {

        EDI_PO_DTL_XREFTMsg xInTMsg = new EDI_PO_DTL_XREFTMsg();

        ZYPEZDItemValueSetter.setValue(xInTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(xInTMsg.ediPoOrdNum, bizMsg.ediPoOrdNum_HT);
        ZYPEZDItemValueSetter.setValue(xInTMsg.ediPoOrdDtlLineNum, bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD);

        String poOrdDtlLineNum = getPoOrdDtlLineNum(bizMsg.B.no(idx).poOrdDtlLineNum_HD.getValue(), bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD.getValue());
        EDI_PO_DTL_XREFTMsg xOutTMsg = (EDI_PO_DTL_XREFTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(xInTMsg);

        if (xOutTMsg == null) {

            xOutTMsg = new EDI_PO_DTL_XREFTMsg();

            ZYPEZDItemValueSetter.setValue(xOutTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(xOutTMsg.ediPoOrdNum, bizMsg.ediPoOrdNum_HT);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.ediPoOrdDtlLineNum, bizMsg.B.no(idx).ediPoOrdDtlLineNum_HD);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdNum, bizMsg.poOrdNum_HT);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdDtlLineNum, poOrdDtlLineNum);
            EZDTBLAccessor.create(xOutTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xOutTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {xOutTMsg.getReturnCode() });
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return false;
            }

        } else {
            ZYPEZDItemValueSetter.setValue(xOutTMsg.ediPoOrdNum, bizMsg.ediPoOrdNum_HT);
            ZYPEZDItemValueSetter.setValue(xOutTMsg.poOrdDtlLineNum, poOrdDtlLineNum);
            EZDTBLAccessor.update(xOutTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xOutTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {xOutTMsg.getReturnCode() });
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return false;
            }
        }
        return true;
    }

    private void doProcess_NPAL1140Scrn00_ackReprocess(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);

        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(globalMsg.A, NPAL1140Bean.xxChkBox_A0, ZYPConstant.CHKBOX_ON_Y);
        if (chkYList.isEmpty()) {
            bizMsg.setMessageInfo(MSG_ID_NLAM0023E);
            return;
        }

        for (int idx = 0; idx < chkYList.size(); idx++) {
            int rowIndex = chkYList.get(idx).intValue();

            PO_ACK_HDR_WRKTMsg hInTMsg = new PO_ACK_HDR_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(hInTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(hInTMsg.poAckHdrWrkPk,  globalMsg.A.no(rowIndex).poAckHdrWrkPk_A0.getValue());

            PO_ACK_HDR_WRKTMsg hOutMsg = (PO_ACK_HDR_WRKTMsg)EZDTBLAccessor.findByKeyForUpdateNoWait(hInTMsg);
            if (hOutMsg == null) {
                bizMsg.setMessageInfo(MSG_ID_NPAM1297E);
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return;
            }
            
            if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(rowIndex).ezUpTime_A0.getValue(), globalMsg.A.no(rowIndex).ezUpTimeZone_A0.getValue(), hOutMsg.ezUpTime.getValue(), hOutMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(MSG_ID_NPAM1297E);
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return;
            }
            ZYPEZDItemValueSetter.setValue(hOutMsg.ackEdiProcStsCd, ACK_EDI_PROC_STS.REPROCESS);
            ZYPEZDItemValueSetter.setValue(hOutMsg.poAckUpdProcFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(hOutMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hOutMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {hOutMsg.getReturnCode() });
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return;
            }
        }

        bizMsg.setMessageInfo(MSG_ID_NPAM0005I);
        NPAL1140CommonLogic.claerWarningLine(bizMsg);
        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
    }

    private List<Boolean> checkPoDtl(String poOrdNum, String dtlLine, EZDCStringItem poOrdDtlLineNumBX, EZDCStringItem poOrdDtlLineNumBS, EZDCStringItem mdseCdB0) {

        boolean errFlg = false;
        boolean warnFlg = false;

        PO_DTLTMsg poDtlInTMsg = new PO_DTLTMsg();

        poDtlInTMsg.setSQLID("003");
        poDtlInTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        poDtlInTMsg.setConditionValue("poOrdNum01", poOrdNum);
        poDtlInTMsg.setConditionValue("poOrdDtlLineNum01", dtlLine);

        PO_DTLTMsgArray poDtlTMsgArray = (PO_DTLTMsgArray) EZDTBLAccessor.findByCondition(poDtlInTMsg);

        if (poDtlTMsgArray.length() == 0) {
            poOrdDtlLineNumBX.setErrorInfo(1, MSG_ID_NPAM1287E);
            errFlg = true;

        } else {
            PO_DTLTMsg poDtlOutMsg = poDtlTMsgArray.no(0);
            if (PO_STS.CANCELLED.equals(poDtlOutMsg.poStsCd.getValue())) {
                poOrdDtlLineNumBX.setErrorInfo(1, MSG_ID_NPAM1285E);
                errFlg = true;

            } else {
                if (!poOrdDtlLineNumBX.getValue().equals(poOrdDtlLineNumBS.getValue())) {
                    if (!poDtlOutMsg.mdseCd.getValue().equals(mdseCdB0.getValue())) {
                        poOrdDtlLineNumBX.setErrorInfo(2, MSG_ID_NPAM1281W);
                        warnFlg = true;
                    }
                }
                ZYPEZDItemValueSetter.setValue(poOrdDtlLineNumBS, poOrdDtlLineNumBX);
            }
        }
        List<Boolean> result = new ArrayList<Boolean>();
        result.add(errFlg);
        result.add(warnFlg);
        return result;
    }

    private void doProcess_NPAL1140Scrn00_ackCancel(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);

        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(globalMsg.A, NPAL1140Bean.xxChkBox_A0, ZYPConstant.CHKBOX_ON_Y);
        if (chkYList.isEmpty()) {
            bizMsg.setMessageInfo(MSG_ID_NLAM0023E);
            return;
        }

        for (int idx = 0; idx < chkYList.size(); idx++) {
            int rowIndex = chkYList.get(idx).intValue();

            PO_ACK_HDR_WRKTMsg hInTMsg = new PO_ACK_HDR_WRKTMsg();
            
            ZYPEZDItemValueSetter.setValue(hInTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(hInTMsg.poAckHdrWrkPk,  globalMsg.A.no(rowIndex).poAckHdrWrkPk_A0.getValue());

            PO_ACK_HDR_WRKTMsg hOutMsg = (PO_ACK_HDR_WRKTMsg)EZDTBLAccessor.findByKeyForUpdateNoWait(hInTMsg);
            if (hOutMsg == null) {
                bizMsg.setMessageInfo(MSG_ID_NPAM1297E);
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return;
            }
            
            if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(rowIndex).ezUpTime_A0.getValue(), globalMsg.A.no(rowIndex).ezUpTimeZone_A0.getValue(), hOutMsg.ezUpTime.getValue(), hOutMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(MSG_ID_NPAM1297E);
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return;
            }

            ZYPEZDItemValueSetter.setValue(hOutMsg.ackEdiProcStsCd, ACK_EDI_PROC_STS.CANCELLED);
            ZYPEZDItemValueSetter.setValue(hOutMsg.poAckUpdProcFlg, ZYPConstant.FLG_ON_Y);
            EZDTBLAccessor.update(hOutMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hOutMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ZZZM9013E, new String[] {hOutMsg.getReturnCode() });
                NPAL1140CommonLogic.claerWarningLine(bizMsg);
                return;
            }
        }

        bizMsg.setMessageInfo(MSG_ID_NPAM0005I);
        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
    }

    private boolean isValidPo(NPAL1140CMsg bizMsg) {
        POTMsg inPoTMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inPoTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inPoTMsg.poOrdNum, bizMsg.poOrdNum_HT);
        POTMsg outPoTMsg = (POTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inPoTMsg);
        if (outPoTMsg == null) {
            bizMsg.poOrdNum_HT.setErrorInfo(1, MSG_ID_NPAM1287E);
            return false;
        }
        if (PO_STS.CANCELLED.equals(outPoTMsg.poStsCd.getValue())) {
            bizMsg.poOrdNum_HT.setErrorInfo(1, MSG_ID_NPAM1285E);
            return false;
        }
        // Vendor check
        if (isPoVndCdError(bizMsg, outPoTMsg)) {
            bizMsg.poOrdNum_HT.setErrorInfo(1, NPAM1322E, new String[] {NPAM1322E_PRM1, NPAM1322E_PRM2, NPAM1322E_PRM3, NPAM1322E_PRM4 });
            return false;
        }
        return true;
    }

    /**
     * isPoVndCdError
     *   Add QC1419
     * @param cMsg
     * @param result
     * @return
     */
    private boolean isPoVndCdError(NPAL1140CMsg cMsg, POTMsg result) {
        // EDI_ASN_HDR_WRK.VND_CD has value.
        if (ZYPCommonFunc.hasValue(cMsg.vndCd_HT)) {
            if (!cMsg.vndCd_HT.getValue().equals(result.vndCd.getValue())) {
                // Error.
                return true;
            }
            // no Error.
            return false;
        }

        // EDI_ASN_HDR_WRK.VND_CD is null
        if (!ZYPCommonFunc.hasValue(cMsg.itrlIntfcId.getValue())) {
            return true;
        }
        String keyCode = INTFC_ID_HEADER + cMsg.itrlIntfcId.getValue();
        String keyVelue = ZYPCodeDataUtil.getVarCharConstValue(keyCode, getGlobalCompanyCode());
        if (keyVelue == null) {
            return true;
        }
        if (keyVelue.indexOf(result.vndCd.getValue()) == -1) {
            return true;
        }
        return false;
    }

    private String getPoOrdDtlLineNum(String poOrdDtlLineNum, String ediPoOrdDtlLineNum) {
        String result;
        if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
            return poOrdDtlLineNum;
        }
        if (!ZYPCommonFunc.hasValue(ediPoOrdDtlLineNum)) {
            return "";
        }
        if (ediPoOrdDtlLineNum.length() > LINE_NUM_LENGTH) {
            result = ediPoOrdDtlLineNum.substring(ediPoOrdDtlLineNum.length() - LINE_NUM_LENGTH);
        } else {
            result = ediPoOrdDtlLineNum;
        }
        return result;
    }
}
