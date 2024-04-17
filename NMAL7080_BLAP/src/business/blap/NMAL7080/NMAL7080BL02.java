/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7080;

import static business.blap.NMAL7080.constant.NMAL7080Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL7080.constant.NMAL7080Constant.CSV_FILE_NM;
import static business.blap.NMAL7080.constant.NMAL7080Constant.CSV_HEADER;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8054E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8234I;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NZZM0000E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NZZM0001W;
import static business.blap.NMAL7080.constant.NMAL7080Constant.XX_CHKBOX_NAME;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NZZM0002I;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7080.common.NMAL7080CommonLogic;
import business.db.SPLY_AGMT_DOC_TPTMsg;
import business.file.NMAL7080F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_PLN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 * 2016/06/06   Fujitsu         Y.Kanefusa      Update          S21_NA#10387
 * 2016/10/14   Fujitsu         M.Ohno          Update          S21_NA#13253
 * 2018/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#23336
 *</pre>
 */
public class NMAL7080BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7080CMsg bizMsg = (NMAL7080CMsg) cMsg;
            NMAL7080SMsg glblMsg = (NMAL7080SMsg) sMsg;

            if ("NMAL7080_INIT".equals(screenAplID)) {
                doProcess_NMAL7080_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_Search(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_InsertRow(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_DeleteRow(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_CMN_Download(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_CMN_Return(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_OnChange_SelectAll".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_OnChange_SelectAll(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_OnChange_Show".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_OnChenge_Show(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_OnClick_MassUpdate".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_OnClick_MassUpdate(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_OnClick_Apply".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_OnChenge_Apply(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NMAL7080Scrn00_CMN_Return(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.H);
    }

    private void doProcess_NMAL7080Scrn00_CMN_Clear(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.H);
        doProcess_NMAL7080_INIT(bizMsg, glblMsg);

    }

    private void doProcess_NMAL7080Scrn00_OnChenge_Apply(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        doProcess_NMAL7080Scrn00_Search(bizMsg, glblMsg);

    }

    private void doProcess_NMAL7080Scrn00_OnClick_MassUpdate(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        if (bizMsg.A.getValidCount() <= 0) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }
        List<Integer> selectIdx = ZYPTableUtil.getSelectedRows(bizMsg.A, XX_CHKBOX_NAME, ZYPConstant.CHKBOX_ON_Y);
        for (int idx : selectIdx) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).effThruDt_A, bizMsg.xxDt10Dt_MD);
        }
        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void doProcess_NMAL7080Scrn00_OnChenge_Show(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.splyAgmtPlnStsCd)) {
            doProcess_NMAL7080Scrn00_Search(bizMsg, glblMsg);
        }

    }

    private void doProcess_NMAL7080Scrn00_OnChange_SelectAll(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_SD)) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NMAL7080_ACMsg acMsg = bizMsg.A.no(i);
                ZYPEZDItemValueSetter.setValue(acMsg.xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        } else {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NMAL7080_ACMsg acMsg = bizMsg.A.no(i);
                acMsg.xxChkBox_A.clear();
            }
        }

    }

    private void doProcess_NMAL7080Scrn00_CMN_Download(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        NMAL7080CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.xxFileData_A.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENSION);
        NMAL7080F00FMsg fMsg = new NMAL7080F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_A.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HEADER);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            EZDMsg.copy(glblMsg, null, fMsg, null);
            EZDMsg.copy(glblMsg.A.get(i), null, fMsg, null);
            // Header Format
            if (ZYPCommonFunc.hasValue(glblMsg.splyAgmtPlnTpCd)) {
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnTpDescTxt, //
                        ZYPCodeDataUtil.getName(SPLY_AGMT_PLN_TP.class, getGlobalCompanyCode(), glblMsg.splyAgmtPlnTpCd.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.splyAgmtDocTpCd)) {
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtDocTpDescTxt, //
                        ZYPCodeDataUtil.getName(SPLY_AGMT_DOC_TP.class, getGlobalCompanyCode(), glblMsg.splyAgmtDocTpCd.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_F, //
                        NMAL7080CommonLogic.formatDt(glblMsg.effFromDt.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.effThruDt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_T, //
                        NMAL7080CommonLogic.formatDt(glblMsg.effThruDt.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.xxChkBox_AF)) {
                ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, ZYPConstant.FLG_OFF_N);
            }
            // Detail Format
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyAgmtFreqTpCd_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtFreqTpDescTxt_A, //
                        ZYPCodeDataUtil.getName(SPLY_AGMT_FREQ_TP.class, getGlobalCompanyCode(), glblMsg.A.no(i).splyAgmtFreqTpCd_A.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FM, //
                        NMAL7080CommonLogic.formatDt(glblMsg.A.no(i).effFromDt_A.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, //
                        NMAL7080CommonLogic.formatDt(glblMsg.A.no(i).effThruDt_A.getValue()));
            }
            csvOutFile.write();
            fMsg.clear();
        }

        csvOutFile.close();

    }

    private void doProcess_NMAL7080Scrn00_DeleteRow(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        NMAL7080CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        List<Integer> delIdx = ZYPTableUtil.getSelectedRows(bizMsg.A, XX_CHKBOX_NAME, ZYPConstant.CHKBOX_ON_Y);
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxYesNoCd_DR.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_DR, ZYPConstant.FLG_ON_Y);
            bizMsg.setMessageInfo(NMAM8234I);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_DR, ZYPConstant.FLG_OFF_N);
        }

        ZYPTableUtil.deleteRows(bizMsg.A, delIdx);
        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void doProcess_NMAL7080Scrn00_InsertRow(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        NMAL7080CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).xxDt10Dt_AC, ZYPDateUtil.getSalesDate());

        String hdrFlag = null;
        String dtlFlg = null;
        if (ZYPCommonFunc.hasValue(bizMsg.splyAgmtDocTpCd)) {
            SPLY_AGMT_DOC_TPTMsg docInMsg = new SPLY_AGMT_DOC_TPTMsg();
            SPLY_AGMT_DOC_TPTMsg docOutMsg = new SPLY_AGMT_DOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(docInMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(docInMsg.splyAgmtDocTpCd, bizMsg.splyAgmtDocTpCd);
            docOutMsg = (SPLY_AGMT_DOC_TPTMsg) S21CodeTableAccessor.findByKey(docInMsg);
            hdrFlag = docOutMsg.hdrLvlQtyEntryFlg.getValue();
            dtlFlg = docOutMsg.dtlLvlQtyEntryFlg.getValue();
        } else {
            hdrFlag = ZYPConstant.FLG_OFF_N;
            dtlFlg = ZYPConstant.FLG_ON_Y;
        }

        if (ZYPConstant.FLG_ON_Y.equals(hdrFlag)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).splyAgmtFreqTpCd_A, SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER);
        }
        // QC#10387 2016/06/20 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).effFromDt_A, ZYPDateUtil.getSalesDate());
        // QC#10387 2016/06/20 Add End
        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
        ZYPEZDItemValueSetter.setValue(bizMsg.hdrLvlQtyEntryFlg, hdrFlag);
        ZYPEZDItemValueSetter.setValue(bizMsg.dtlLvlQtyEntryFlg, dtlFlg);

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void doProcess_NMAL7080_INIT(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_DR, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CD, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_PL, ZYPConstant.FLG_OFF_N); // 2016/10/14 S21_NA#13253 Add

        ZYPCodeDataUtil.createPulldownList(SPLY_AGMT_PLN_TP.class, bizMsg.splyAgmtPlnTpCd_P, bizMsg.splyAgmtPlnTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SPLY_AGMT_DOC_TP.class, bizMsg.splyAgmtDocTpCd_P, bizMsg.splyAgmtDocTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SPLY_AGMT_PLN_STS.class, bizMsg.splyAgmtPlnStsCd_P, bizMsg.splyAgmtPlnStsDescTxt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnStsCd, SPLY_AGMT_PLN_STS.ACTIVE_ONLY);
        ZYPCodeDataUtil.createPulldownList(SPLY_AGMT_FREQ_TP.class, bizMsg.splyAgmtFreqTpCd_P, bizMsg.splyAgmtFreqTpDescTxt_P);
        // QC#10387 2016/06/20 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_AF, ZYPConstant.FLG_ON_Y);
        // QC#10387 2016/06/20 Add End

        if (ZYPCommonFunc.hasValue(bizMsg.splyAgmtPlnPk)) {
            doProcess_NMAL7080Scrn00_Search(bizMsg, glblMsg);
        }
        EZDMsg.copy(bizMsg, null, glblMsg, null);
    }

    private void doProcess_NMAL7080Scrn00_Search(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.splyAgmtPlnStsCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnStsCd, SPLY_AGMT_PLN_STS.ACTIVE_ONLY);
        }
        NMAL7080CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getSupplyAgreement(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {

            bizMsg.setMessageInfo(NZZM0000E);
            return;

        } else if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0001W);
            glblMsg.A.setValidCount(glblMsg.A.length());
        }
        List<Map< ? , ? >> resultList = (List<Map< ? , ? >>) ssmResult.getResultObject();
        // 2018/04/04 S21_NA#23336 Mod Start
        // NMAL7080CommonLogic.setSeachResult(resultList, glblMsg, bizMsg);
        NMAL7080CommonLogic.setSeachResult(resultList, glblMsg, bizMsg, getGlobalCompanyCode());
        // 2018/04/04 S21_NA#23336 Mod End

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

}
