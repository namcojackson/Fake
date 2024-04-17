/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7230;

import static business.blap.NMAL7230.constant.NMAL7230Constant.*;
import static business.blap.NMAL7230.constant.NMAL7230Constant.CSV_FILE_NAME;
import static business.blap.NMAL7230.constant.NMAL7230Constant.CSV_HDR;
import static business.blap.NMAL7230.constant.NMAL7230Constant.FREIGHT_ZONE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.MESSAGE_KIND_ERROR;
import static business.blap.NMAL7230.constant.NMAL7230Constant.MESSAGE_KIND_WARNING;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8187E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8425E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NZZM0000E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NZZM0001W;
import static business.blap.NMAL7230.constant.NMAL7230Constant.ZZZM9003I;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7230.common.NMAL7230CommonLogic;
import business.db.GLBL_CMPYTMsg;
import business.file.NMAL7230F00FMsg;

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
 * NMAL7230BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 * 2016/05/13   Fujitsu         W.Honda         Update          QC#8303
 * 2016/05/13   Fujitsu         W.Honda         Update          QC#7040
 * 2016/06/13   Fujitsu         W.Honda         Update          QC#9809
 * 2016/11/21   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7230BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7230CMsg bizMsg = (NMAL7230CMsg) cMsg;
            NMAL7230SMsg glblMsg = (NMAL7230SMsg) sMsg;

            if ("NMAL7230Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7230Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_CMN_Clear(bizMsg, glblMsg);

            // QC#7040 2016/05/26 Del start
//            } else if ("NMAL7230Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NMAL7230Scrn00_CMN_Reset(bizMsg, glblMsg);
            // QC#7040 2016/05/26 Del end

            } else if ("NMAL7230Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7230Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL7230Scrn00_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_OnChange_SavedSearchOption(bizMsg, glblMsg);

            } else if ("NMAL7230_INIT".equals(screenAplID)) {
                doProcess_NMAL7230_INIT(bizMsg, glblMsg);

            } else if ("NMAL7230Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NMAL7230Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_Search(bizMsg, glblMsg);

            // QC#8302 2016/05/13 Add start
            } else if ("NMAL7230Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_TemplateDownload(bizMsg, glblMsg);
            // QC#8302 2016/05/13 Add end

            } else if ("NMAL7230Scrn00_Upload".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_Upload(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_CMN_Download(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7230Query.getInstance().getClass());

            //create csv file, parameters
            Map<String, Object> ssMParam = null;
            String ssmId = "searchForDownload";
            ssMParam = NMAL7230Query.searchForDownload(bizMsg, glblMsg, getGlobalCompanyCode());

            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();
            NMAL7230CommonLogic.writeCsvFileContInfo(rs, bizMsg, getGlobalCompanyCode());

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) && !MESSAGE_KIND_WARNING.equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Download"});
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_CMN_Clear(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {

        doProcess_NMAL7230_INIT(bizMsg, glblMsg);
    }

    // QC#7040 2016/05/26 Del start
//    /**
//     * CMN_Reset Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NMAL7230Scrn00_CMN_Reset(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
//
//        doProcess_NMAL7230_INIT(bizMsg, glblMsg);
//    }
    // QC#7040 2016/05/26 Del end

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_CMN_Submit(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {

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
    private void doProcess_NMAL7230Scrn00_DeleteSearch(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
        // do nothing
    }

    /**
     * InsertRow Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_OnChange_SavedSearchOption(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NMAL7230CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230_INIT(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_P, bizMsg.lineBizTpDescTxt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, ZYPConstant.FLG_ON_Y);

        // QC#9809 2016/06/13 Add start
        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null || !ZYPCommonFunc.hasValue(outTMsg.stdCcyCd)) {
            bizMsg.setMessageInfo(NMAM8433E, new String[] {COUNTRY});
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd, outTMsg.ctryCd);
        }
        // QC#9809 2016/06/13 Add end

        NMAL7230CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_SaveSearch(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
        //do nothing
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_Search(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg, true);
    }


    // QC#8302 2016/05/13 Add start
    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_TemplateDownload(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXTENSION);
        NMAL7230F00FMsg fMsg = new NMAL7230F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(CSV_HDR);

        csvOutFile.close();
    }
    // QC#8302 2016/05/13 Add end

    /**
     * Upload Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_Upload(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {

        String path = bizMsg.xxFileData_UP.getTempFilePath();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;

        int length = glblMsg.A.length();
        ZYPTableUtil.clear(glblMsg.A);

        // Mod Start 2016/11/21 M.Ohno S21_NA#16082
        String csvFilePath = ZYPExcelUtil.excelToCsvFile(path);

        NMAL7230F00FMsg fMsg = new NMAL7230F00FMsg();

        // EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);
        // Mod End   2016/11/21 M.Ohno S21_NA#16082

        try {

            if (!NMAL7230CommonLogic.readHeaderCsvFile(mappedFile, bizMsg)) {
                return;
            }
            fMsg.clear();

            int status = -1;
            int upCnt = 0;

            while ((status = mappedFile.read()) != 1) {

                if (upCnt >= length) {
                    bizMsg.setMessageInfo(NMAM8187E, new String[] {FREIGHT_ZONE, String.valueOf(glblMsg.A.length())});
                    return;
                }

                if (NMAL7230CommonLogic.checkFormatAndCopyToGlblMsg_UPLOAD(status, upCnt, glblMsg.A, fMsg, bizMsg)) {
                    return;
                }

                fMsg.clear();

                upCnt++;
            }

            if (upCnt == 0) {
                bizMsg.setMessageInfo("ZYEM0004E");
                return;
            }

            glblMsg.A.setValidCount(upCnt);

            if (NMAL7230CommonLogic.validateGlblMsg_UPLOAD(glblMsg.A, bizMsg, getGlobalCompanyCode())) {
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
    private void search(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg, boolean isSearch) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7230Query.getInstance().search(bizMsg, glblMsg);

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

            NMAL7230CommonLogic.setGlblMsgAndCopyBizMsg(ssmResult, bizMsg, glblMsg);
        }
    }

}
