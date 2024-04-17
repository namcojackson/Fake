/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7240;

import static business.blap.NMAL7240.constant.NMAL7240Constant.CSV_EXTENSION;
import static business.blap.NMAL7240.constant.NMAL7240Constant.CSV_FILE_NAME;
import static business.blap.NMAL7240.constant.NMAL7240Constant.CSV_HDR;
import static business.blap.NMAL7240.constant.NMAL7240Constant.FREIGHT_RATE;
import static business.blap.NMAL7240.constant.NMAL7240Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL7240.constant.NMAL7240Constant.MESSAGE_KIND_ERROR;
import static business.blap.NMAL7240.constant.NMAL7240Constant.MESSAGE_KIND_WARNING;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8187E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8425E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8432E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8433E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NZZM0000E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NZZM0001W;
import static business.blap.NMAL7240.constant.NMAL7240Constant.STANDARD_CURRENCY;
import static business.blap.NMAL7240.constant.NMAL7240Constant.VAR_CHAR_CONST_PER_UNIT_TP_CD;
import static business.blap.NMAL7240.constant.NMAL7240Constant.VAR_CHAR_CONST_QTY_UNIT_TP_CD;
import static business.blap.NMAL7240.constant.NMAL7240Constant.ZYEM0004E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.ZZZM9003I;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7240.common.NMAL7240CommonLogic;
import business.db.GLBL_CMPYTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.file.NMAL7240F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7240BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 * 2016/05/13   Fujitsu         W.Honda         Update          QC#8303
 * 2016/05/17   Fujitsu         W.Honda         Update          QC#7040
 * 2016/05/26   Fujitsu         W.Honda         Update          QC#7040
 * 2016/11/21   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7240BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7240CMsg bizMsg = (NMAL7240CMsg) cMsg;
            NMAL7240SMsg glblMsg = (NMAL7240SMsg) sMsg;

            if ("NMAL7240_INIT".equals(screenAplID)) {
                doProcess_NMAL7240_INIT(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_CMN_Download(bizMsg, glblMsg);

            // QC#7040 2016/05/26 Del start
//            } else if ("NMAL7240Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NMAL7240Scrn00_CMN_Reset(bizMsg, glblMsg);
            // QC#7040 2016/05/26 Del start

            } else if ("NMAL7240Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_DeleteRow(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_InsertRow(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_OnChange_SavedSearchOption(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_Search(bizMsg, glblMsg);

            // QC#8303 2016/05/13 Add start
            } else if ("NMAL7240Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_TemplateDownload(bizMsg, glblMsg);
            // QC#8303 2016/05/13 Add end

            } else if ("NMAL7240Scrn00_Upload".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_Upload(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240_INIT(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_P, bizMsg.lineBizTpDescTxt_P);
        NMAL7240CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, ZYPConstant.FLG_ON_Y);

        // Get VarCharConst
        String qtyUnitTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_QTY_UNIT_TP_CD, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(qtyUnitTpCd)) {
            bizMsg.setMessageInfo(NMAM8432E, new String[] {VAR_CHAR_CONST_QTY_UNIT_TP_CD});
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.qtyUnitTpCd_H, qtyUnitTpCd);
        }

        String perUnitTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_PER_UNIT_TP_CD, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(qtyUnitTpCd)) {
            bizMsg.setMessageInfo(NMAM8432E, new String[] {VAR_CHAR_CONST_PER_UNIT_TP_CD});
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.perUnitTpCd_H, perUnitTpCd);
        }

        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null || !ZYPCommonFunc.hasValue(outTMsg.stdCcyCd)) {
            bizMsg.setMessageInfo(NMAM8433E, new String[] {STANDARD_CURRENCY});
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.frtRateCcyCd_H, outTMsg.stdCcyCd);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_CMN_Clear(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        doProcess_NMAL7240_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_CMN_Download(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7240Query.getInstance().getClass());

            //create csv file, parameters
            Map<String, Object> ssMParam = null;
            String ssmId = "search";
            ssMParam = NMAL7240Query.searchForDownload(bizMsg, glblMsg, getGlobalCompanyCode());

            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();
            NMAL7240CommonLogic.writeCsvFileContInfo(rs, bizMsg, getGlobalCompanyCode());

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) && !MESSAGE_KIND_WARNING.equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Download"});
        }
    }

    // QC#7040 2016/05/26 Del start
//    /**
//     * CMN_Reset Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NMAL7240Scrn00_CMN_Reset(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
//
//        doProcess_NMAL7240_INIT(bizMsg, glblMsg);
//
//    }
    // QC#7040 2016/05/26 Del end

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_CMN_Submit(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) && !MESSAGE_KIND_WARNING.equals(bizMsg.getMessageKind())) {
            ZYPTableUtil.clear(bizMsg.A);
            ZYPTableUtil.clear(glblMsg.A);

            search(bizMsg, glblMsg, false);
            if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) && !MESSAGE_KIND_WARNING.equals(bizMsg.getMessageKind())) {
                bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Submit"});
            }
        }
    }

    /**
     * DeleteRow Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_DeleteRow(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        //
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_DeleteSearch(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        return;
    }

    /**
     * InsertRow Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_InsertRow(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        int idx = bizMsg.A.getValidCount();
        StringBuilder sb = new StringBuilder();
        boolean isErr = false;

        if (ZYPCommonFunc.hasValue(bizMsg.qtyUnitTpCd_H)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).qtyUnitTpCd_A1, bizMsg.qtyUnitTpCd_H);
        } else {
            sb.append(VAR_CHAR_CONST_QTY_UNIT_TP_CD);
            isErr = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.perUnitTpCd_H)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).perUnitTpCd_A1, bizMsg.perUnitTpCd_H);
        } else {
            if (isErr) {
                sb.append(", ");
            }
            sb.append(VAR_CHAR_CONST_PER_UNIT_TP_CD);
            isErr = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.frtRateCcyCd_H)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).frtRateCcyCd_A1, bizMsg.frtRateCcyCd_H);
        } else {
            if (isErr) {
                sb.append(", ");
            }
            sb.append(STANDARD_CURRENCY);
            isErr = true;
        }

        // default value
        if (ZYPCommonFunc.hasValue(bizMsg.lineBizTpCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).lineBizTpCd_A1, bizMsg.lineBizTpCd);

            // QC#7040 2016/05/17 Mod start
//            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).lineBizTpDescTxt_A1, ZYPCodeDataUtil.getName(LINE_BIZ_TP.class, getGlobalCompanyCode(), bizMsg.lineBizTpCd.getValue()));
            LINE_BIZ_TPTMsg lineBizTp = (LINE_BIZ_TPTMsg) ZYPCodeDataUtil.findByCode(LINE_BIZ_TP.class, getGlobalCompanyCode(), bizMsg.lineBizTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).lineBizTpDescTxt_A1, lineBizTp.lineBizTpDescTxt.getValue());
            // QC#7040 2016/05/17 Mod end
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).effFromDt_A1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
        if (isErr) {
            bizMsg.setMessageInfo(NMAM8433E, new String[] {sb.toString()});
        }
    }

    /**
     * OnChange_SavedSearchOption Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_OnChange_SavedSearchOption(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NMAL7240CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_SaveSearch(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        return;
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_Search(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        // search
        search(bizMsg, glblMsg, true);
    }

    // QC#8303 2016/05/13 Add start
    /**
     * TemplateDownload Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_TemplateDownload(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXTENSION);
        NMAL7240F00FMsg fMsg = new NMAL7240F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(CSV_HDR);

        csvOutFile.close();
    }
    // QC#8303 2016/05/13 Add end

    /**
     * Upload Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_Upload(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        String path = bizMsg.xxFileData_UP.getTempFilePath();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;

        int length = glblMsg.A.length();
        ZYPTableUtil.clear(glblMsg.A);

        // Mod Start 2016/11/21 M.Ohno S21_NA#16082
        String csvFilePath = ZYPExcelUtil.excelToCsvFile(path);

        NMAL7240F00FMsg fMsg = new NMAL7240F00FMsg();

        // EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);
        // Mod End   2016/11/21 M.Ohno S21_NA#16082

        try {

            if (!NMAL7240CommonLogic.readHeaderCsvFile(mappedFile, bizMsg)) {
                return;
            }
            fMsg.clear();

            int status = -1;
            int upCnt = 0;

            while ((status = mappedFile.read()) != 1) {

                if (upCnt >= length) {
                    bizMsg.setMessageInfo(NMAM8187E, new String[] {FREIGHT_RATE, String.valueOf(glblMsg.A.length())});
                    return;
                }

                if (NMAL7240CommonLogic.checkFormatAndCopyToGlblMsg_UPLOAD(status, upCnt, glblMsg.A, fMsg, bizMsg)) {
                    return;
                }

                fMsg.clear();

                upCnt++;
            }

            if (upCnt == 0) {
                bizMsg.setMessageInfo(ZYEM0004E);
                return;
            }

            glblMsg.A.setValidCount(upCnt);

            if (NMAL7240CommonLogic.validateGlblMsg_UPLOAD(glblMsg.A, bizMsg, getGlobalCompanyCode())) {
                bizMsg.setMessageInfo(NMAM8425E);
            }

            ZYPTableUtil.clear(bizMsg.A);
            EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
            bizMsg.setCommitSMsg(true);

        } finally {

            mappedFile.close();
            bizMsg.xxFileData_UP.deleteTempFile();
            // Add Start 2016/11/21 M.Ohno S21_NA#16082\
            ZYPExcelUtil.deleteFile(csvFilePath);
            // Add End   2016/11/21 M.Ohno S21_NA#16082
            ZYPTableUtil.clear(glblMsg.A);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     * @param isSearch Call event is submit flag
     */
    private void search(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg, boolean isSearch) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7240Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            if (isSearch) {
                bizMsg.setMessageInfo(NZZM0000E);
                bizMsg.setCommitSMsg(true);
            }
        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                if (isSearch) {
                    bizMsg.setMessageInfo(NZZM0001W);
                }
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            NMAL7240CommonLogic.setGlblMsgAndCopyBizMsg(ssmResult, bizMsg, glblMsg);

        }
    }

}
