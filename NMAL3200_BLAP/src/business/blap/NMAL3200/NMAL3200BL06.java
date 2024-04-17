/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3200;

import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_01;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_02;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_03;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_04;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_05;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_06;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_07;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_08;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_09;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_10;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_11;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_12;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_13;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_14;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_15;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_16;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_17;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_18;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_19;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_20;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CNTY_PK_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CSV_DELIMITER_CHAR;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CTRY_CD_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CTY_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_CNTY_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_CTRY_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_MKTG_FLD_MAP_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_MKTG_FLD_MAP_PK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_ROWNUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_ST_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DS_ACCT_NM_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DUNS_NUM_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ERROR_ITEM_MSG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ERROR_LENGTH_MSG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ERROR_NULL_MSG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_SUBMIT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_UPLOAD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EZUPTIME;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EZUPTIMEZONE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FIRST_LINE_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FORMAT_TIMESTAMP;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FRTH_LINE_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.GLN_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.HIN_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_PK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_RQST;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_WRK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM0038I;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM0282E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM8121E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM8175E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM8329E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM8509E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM8615E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM8618E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.POST_CD_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.SCD_LINE_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ST_CD_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.SUBMIT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.THIRD_LINE_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.UPLOAD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ZYEM0004E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ZYEM0013E;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ZZZM9003I;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDItemAttribute;
import parts.common.EZDSMsg;
import parts.common.EZDStringUtil;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL3200.common.NMAL3200CommonLogic;
import business.db.MKTG_FLD_MAPTMsg;
import business.db.MKTG_MAP_RQSTTMsg;
import business.db.MKTG_MAP_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileReader;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 * 07/08/2016   CITS            K.Ogino         Update          QC#11477
 *</pre>
 */
public class NMAL3200BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL3200_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else if (EVENT_NM_NMAL3200_UPLOAD.equals(screenAplID)) {
                doProcess_Upload((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Upload
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_Upload(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MKTG_FLD_MAP_PK, cMsg.mktgFldMapPk.getValue());

        S21SsmEZDResult result = NMAL3200Query.getInstance().findSingleRecord("findMktgFldMap", ssmParam);

        if (result.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) result.getResultObject();

            NMAL3200CommonLogic.setMktgFldMap(cMsg, map);

            String path = cMsg.xxFileData.getTempFilePath();

            // Upload File Convert to CSV file.
            String csvFilePath = ZYPExcelUtil.excelToCsvFile(path);

            // File Read
            BufferedReader br = null;
            try {
                // Read CSV file
                br = new BufferedReader(new InputStreamReader(ZYPFileReader.readFile(csvFilePath)));

                List<String> hdrLineList = null;
                List<String> upldLineList = null;

                // Header Line
                String hdrLine = br.readLine();
                if (hdrLine != null) {
                    // Create CSV Header Item List
                    hdrLineList = separateCSVTokens(hdrLine, true);
                } else {
                    cMsg.setMessageInfo(ZYEM0004E);
                    return;
                }

                boolean uploadError = false;

                // Data Line
                int cnt = 0;
                String upldLine = null;

                BigDecimal mktgMapRqstPk = null;
                while ((upldLine = br.readLine()) != null) {

                    cnt++;

                    if (!ZYPCommonFunc.hasValue(mktgMapRqstPk)) {
                        mktgMapRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MKTG_MAP_RQST_SQ);
                    }

                    // maximum number of record check
                    if (cnt > cMsg.numConstVal_CU.getValueInt()) {
                        cMsg.setMessageInfo(ZYEM0013E, new String[] {String.valueOf(cMsg.numConstVal_CU.getValueInt()) });
                        break;
                    }

                    // Create CSV Data Item List
                    upldLineList = separateCSVTokens(upldLine, false);

                    BigDecimal mktgMapWrktPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MKTG_MAP_WRK_SQ);

                    // Validation and Parameter settings
                    MKTG_MAP_WRKTMsg wrkTMsg = validateAndSetMktgMapWrkParams(cMsg, hdrLineList, upldLineList, glblCmpyCd, mktgMapWrktPk, mktgMapRqstPk, map);
                    if (wrkTMsg == null) {
                        return;
                    }

                    if (ZYPCommonFunc.hasValue(wrkTMsg.upldCsvRqstCmntTxt)) {
                        uploadError = true;
                    }

                    S21FastTBLAccessor.insert(wrkTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wrkTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM0282E, new String[] {MKTG_MAP_WRK });
                        return;
                    }
                    upldLineList.clear();
                }

                // Header Line Only
                if (cnt == 0) {
                    cMsg.setMessageInfo(ZYEM0004E);
                    return;
                }

                MKTG_MAP_RQSTTMsg rqstTMsg = setMktgMapRqstParam(cMsg, uploadError, glblCmpyCd, mktgMapRqstPk, path);

                EZDTBLAccessor.insert(rqstTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rqstTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0282E, new String[] {MKTG_MAP_RQST });
                    return;
                }

                cMsg.mktgMapRqstPk_DL.setValue(mktgMapRqstPk);
                cMsg.setMessageInfo(ZZZM9003I, new String[] {UPLOAD });

            } catch (IOException ie) {
                cMsg.setMessageInfo(NMAM8329E, new String[] {getUploadFileNm(csvFilePath) });
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        cMsg.setMessageInfo(NMAM8329E, new String[] {getUploadFileNm(csvFilePath) });
                        return;
                    }
                }
                cMsg.xxFileData.deleteTempFile();
                ZYPExcelUtil.deleteFile(csvFilePath);
            }
        } else {
            cMsg.setMessageInfo(NMAM0038I);
        }
    }

    /**
     * CMN_Submit
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_CMN_Submit(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (ZYPCommonFunc.hasValue(cMsg.mktgFldMapNm_SC)) {
            // Insert Process
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_MKTG_FLD_MAP_NM, cMsg.mktgFldMapNm_SC.getValue());
            S21SsmEZDResult result = NMAL3200Query.getInstance().findSingleRecord("findMktgFldMapPk", ssmParam);

            if (result.isCodeNotFound()) {
                // MKTG_FLD_MAP Data Insert
                boolean bool = insertMktgFldMap(cMsg, glblCmpyCd);
                if (!bool) {
                    // Data insert failure
                    return;
                }
            } else {
                cMsg.mktgFldMapNm_SC.setErrorInfo(1, NMAM8509E);
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.mktgFldMapNm_DB, cMsg.mktgFldMapNm_SC);
            cMsg.mktgFldMapNm_SC.clear();
        } else {
            // Update Process
            if (!ZYPCommonFunc.hasValue(cMsg.mktgFldMapPk)) {

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_MKTG_FLD_MAP_NM, cMsg.mktgFldMapNm_DB.getValue());
                S21SsmEZDResult result = NMAL3200Query.getInstance().findSingleRecord("findMktgFldMapPk", ssmParam);

                if (result.isCodeNormal()) {
                    Map<String, Object> map = (Map<String, Object>) result.getResultObject();
                    ZYPEZDItemValueSetter.setValue(cMsg.mktgFldMapPk, (BigDecimal) map.get(MKTG_FLD_MAP_PK));
                    ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, (String) map.get(EZUPTIME));
                    ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, (String) map.get(EZUPTIMEZONE));
                } else {
                    cMsg.mktgFldMapNm_DB.setErrorInfo(1, NMAM8121E, new String[] {MKTG_FLD_MAP });
                    return;
                }
            }

            // Use check in MKTG_MAP_RQST
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_MKTG_FLD_MAP_PK, cMsg.mktgFldMapPk.getValue());
            ssmParam.put(DB_PARAM_ROWNUM, 1);

            S21SsmEZDResult result = NMAL3200Query.getInstance().findSingleRecord("findMktgMapRqst", ssmParam);

            if (result.isCodeNormal()) {
                cMsg.mktgFldMapNm_DB.setErrorInfo(1, NMAM8615E);
                return;
            }

            // Duplicate check in MKTG_FLD_MAP
            ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_MKTG_FLD_MAP_NM, cMsg.mktgFldMapNm_DB.getValue());
            ssmParam.put(DB_PARAM_MKTG_FLD_MAP_PK, cMsg.mktgFldMapPk.getValue());
            result = NMAL3200Query.getInstance().findSingleRecord("findMktgFldMapPk", ssmParam);

            if (result.isCodeNotFound()) {
                boolean bool = updateMktgFldMap(cMsg, glblCmpyCd);
                if (!bool) {
                    // Data update failure
                    return;
                }
            } else {
                cMsg.mktgFldMapNm_DB.setErrorInfo(1, NMAM8509E);
                return;
            }
        }
        cMsg.setMessageInfo(ZZZM9003I, new String[] {SUBMIT });
    }

    /**
     * set MKTG_MAP_RQSTTMsg Parameter
     * @param cMsg NMAL3200CMsg
     * @param uploadError boolean
     * @param glblCmpyCd String
     * @param mktgMapRqstPk BigDecimal
     * @param uploadFilePath String
     * @return
     */
    private MKTG_MAP_RQSTTMsg setMktgMapRqstParam(NMAL3200CMsg cMsg, boolean uploadError, String glblCmpyCd, BigDecimal mktgMapRqstPk, String uploadFilePath) {

        // insert MKTG_MAP_WRK
        MKTG_MAP_RQSTTMsg tMsg = new MKTG_MAP_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgMapRqstPk, mktgMapRqstPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgFldMapPk, cMsg.mktgFldMapPk);
        ZYPEZDItemValueSetter.setValue(tMsg.contrAssnTrgtTpCd, cMsg.contrAssnTrgtTpCd_SL);
        if (ZYPCommonFunc.hasValue(cMsg.exactCondFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.exactCondFlg, cMsg.exactCondFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.exactCondFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.prtlCondFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.prtlCondFlg, cMsg.prtlCondFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prtlCondFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.dunsCondFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.dunsCondFlg, cMsg.dunsCondFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.dunsCondFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.glnCondFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.glnCondFlg, cMsg.glnCondFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.glnCondFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.ovrdVldFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.ovrdVldFlg, cMsg.ovrdVldFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.ovrdVldFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.upldFileNm, getUploadFileNm(uploadFilePath));
        ZYPEZDItemValueSetter.setValue(tMsg.cratUsrId, getUserProfileService().getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(tMsg.cratTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP));
        if (uploadError) {
            ZYPEZDItemValueSetter.setValue(tMsg.upldErrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.upldErrFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.mktgMapRqstProcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.exactMatchFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.prtlMatchFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);

        return tMsg;
    }

    /**
     * get upload file name
     * @param uploadFilePath String
     * @return String
     */
    private String getUploadFileNm(String uploadFilePath) {
        String fileNm = uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1, uploadFilePath.lastIndexOf("_@"));
        fileNm = fileNm.substring(0, fileNm.lastIndexOf("_"));
        String fileEx = ZYPFileNameUtil.getFileEx(uploadFilePath);
        return fileNm + "." + fileEx;
    }

    /**
     * set MKTG_MAP_WRK TMsg
     * @param cMsg NMAL3200CMsg
     * @param hdrLineList List<String>
     * @param upldLineList List<String>
     * @param glblCmpyCd String
     * @param mktgMapWrktPk BigDecimal
     * @param mktgMapRqstPk BigDecimal
     * @param map Map<String, Object> map
     * @return MKTG_MAP_WRKTMsg
     */
    private MKTG_MAP_WRKTMsg validateAndSetMktgMapWrkParams(NMAL3200CMsg cMsg, List<String> hdrLineList, List<String> upldLineList, String glblCmpyCd, BigDecimal mktgMapWrktPk, BigDecimal mktgMapRqstPk, Map<String, Object> map) {

        // insert MKTG_MAP_WRK
        MKTG_MAP_WRKTMsg tMsg = new MKTG_MAP_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgMapWrkPk, mktgMapWrktPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgMapRqstPk, mktgMapRqstPk);

        boolean isError = false;

        // Validation and Set dsAcctNm property
        MktgMapWrkBean bean = new MktgMapWrkBean(tMsg.getAttr("dsAcctNm"), tMsg.dsAcctNm, hdrLineList, upldLineList, (String) map.get(DS_ACCT_NM_COL_DFN_NM), cMsg.dsAcctNm, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(DS_ACCT_NM_COL_DFN_NM) });
                return null;
            }

        }

        // Validation and Set firstLineAddr property
        bean = new MktgMapWrkBean(tMsg.getAttr("firstLineAddr"), tMsg.firstLineAddr, hdrLineList, upldLineList, (String) map.get(FIRST_LINE_ADDR_COL_DFN_NM), cMsg.firstLineAddr, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParamSubStr(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(FIRST_LINE_ADDR_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set scdLineAddr property
        bean = new MktgMapWrkBean(tMsg.getAttr("scdLineAddr"), tMsg.scdLineAddr, hdrLineList, upldLineList, (String) map.get(SCD_LINE_ADDR_COL_DFN_NM), cMsg.scdLineAddr, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(SCD_LINE_ADDR_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set thirdLineAddr property
        bean = new MktgMapWrkBean(tMsg.getAttr("thirdLineAddr"), tMsg.thirdLineAddr, hdrLineList, upldLineList, (String) map.get(THIRD_LINE_ADDR_COL_DFN_NM), cMsg.thirdLineAddr, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(THIRD_LINE_ADDR_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set frthLineAddr property
        bean = new MktgMapWrkBean(tMsg.getAttr("frthLineAddr"), tMsg.frthLineAddr, hdrLineList, upldLineList, (String) map.get(FRTH_LINE_ADDR_COL_DFN_NM), cMsg.frthLineAddr, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(FRTH_LINE_ADDR_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set ctyAddr property
        bean = new MktgMapWrkBean(tMsg.getAttr("ctyAddr"), tMsg.ctyAddr, hdrLineList, upldLineList, (String) map.get(CTY_ADDR_COL_DFN_NM), cMsg.ctyAddr, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParamSubStr(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(CTY_ADDR_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set stCd property
        bean = new MktgMapWrkBean(tMsg.getAttr("stCd"), tMsg.stCd, hdrLineList, upldLineList, (String) map.get(ST_CD_COL_DFN_NM), cMsg.stCd, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ST_CD_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set cntyPk property
        if (ZYPCommonFunc.hasValue(tMsg.stCd)) {
            bean = new MktgMapWrkBean(tMsg.getAttr("cntyPk"), tMsg.cntyPk, hdrLineList, upldLineList, (String) map.get(CNTY_PK_COL_DFN_NM), cMsg.cntyPk, tMsg.upldCsvRqstCmntTxt);
            isError = setMktgMapWrkParam4CntyPk(bean, tMsg.stCd.getValue(), glblCmpyCd);
            if (isError) {
                if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                    return tMsg;
                } else {
                    cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(CNTY_PK_COL_DFN_NM) });
                    return null;
                }
            }
        } else {
            tMsg.upldCsvRqstCmntTxt.setValue(bean.getAttr().getColumnName() + ERROR_NULL_MSG);
        }

        // Validation and Set postCd property
        bean = new MktgMapWrkBean(tMsg.getAttr("postCd"), tMsg.postCd, hdrLineList, upldLineList, (String) map.get(POST_CD_COL_DFN_NM), cMsg.postCd, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(POST_CD_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set ctryCd property
        bean = new MktgMapWrkBean(tMsg.getAttr("ctryCd"), tMsg.ctryCd, hdrLineList, upldLineList, (String) map.get(CTRY_CD_COL_DFN_NM), cMsg.ctryCd, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam4CtryCd(bean, glblCmpyCd);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(CTRY_CD_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set dunsNum property
        bean = new MktgMapWrkBean(tMsg.getAttr("dunsNum"), tMsg.dunsNum, hdrLineList, upldLineList, (String) map.get(DUNS_NUM_COL_DFN_NM), cMsg.dunsNum, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(DUNS_NUM_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set glnNum property
        bean = new MktgMapWrkBean(tMsg.getAttr("glnNum"), tMsg.glnNum, hdrLineList, upldLineList, (String) map.get(GLN_COL_DFN_NM), cMsg.glnNum, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(GLN_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set glnNum property
        bean = new MktgMapWrkBean(tMsg.getAttr("hinNum"), tMsg.hinNum, hdrLineList, upldLineList, (String) map.get(HIN_COL_DFN_NM), cMsg.hinNum, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(HIN_COL_DFN_NM) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_01 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_01"), tMsg.mktgMapAttrbTxt_01, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_01), cMsg.mktgMapAttrbTxt_01, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_01) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_02 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_02"), tMsg.mktgMapAttrbTxt_02, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_02), cMsg.mktgMapAttrbTxt_02, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_02) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_03 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_03"), tMsg.mktgMapAttrbTxt_03, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_03), cMsg.mktgMapAttrbTxt_03, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_03) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_04 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_04"), tMsg.mktgMapAttrbTxt_04, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_04), cMsg.mktgMapAttrbTxt_04, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_04) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_05 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_05"), tMsg.mktgMapAttrbTxt_05, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_05), cMsg.mktgMapAttrbTxt_05, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_05) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_06 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_06"), tMsg.mktgMapAttrbTxt_06, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_06), cMsg.mktgMapAttrbTxt_06, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_06) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_07 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_07"), tMsg.mktgMapAttrbTxt_07, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_07), cMsg.mktgMapAttrbTxt_07, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_07) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_08 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_08"), tMsg.mktgMapAttrbTxt_08, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_08), cMsg.mktgMapAttrbTxt_08, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_08) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_09 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_09"), tMsg.mktgMapAttrbTxt_09, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_09), cMsg.mktgMapAttrbTxt_09, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_09) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_10 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_10"), tMsg.mktgMapAttrbTxt_10, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_10), cMsg.mktgMapAttrbTxt_10, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_10) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_11 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_11"), tMsg.mktgMapAttrbTxt_11, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_11), cMsg.mktgMapAttrbTxt_11, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_11) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_12 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_12"), tMsg.mktgMapAttrbTxt_12, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_12), cMsg.mktgMapAttrbTxt_12, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_12) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_13 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_13"), tMsg.mktgMapAttrbTxt_13, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_13), cMsg.mktgMapAttrbTxt_13, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_13) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_14 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_14"), tMsg.mktgMapAttrbTxt_14, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_14), cMsg.mktgMapAttrbTxt_14, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_14) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_15 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_15"), tMsg.mktgMapAttrbTxt_15, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_15), cMsg.mktgMapAttrbTxt_15, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_15) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_16 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_16"), tMsg.mktgMapAttrbTxt_16, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_16), cMsg.mktgMapAttrbTxt_16, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_16) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_17 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_17"), tMsg.mktgMapAttrbTxt_17, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_17), cMsg.mktgMapAttrbTxt_17, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_17) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_18 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_18"), tMsg.mktgMapAttrbTxt_18, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_18), cMsg.mktgMapAttrbTxt_18, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_18) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_19 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_19"), tMsg.mktgMapAttrbTxt_19, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_19), cMsg.mktgMapAttrbTxt_19, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_19) });
                return null;
            }
        }

        // Validation and Set mktgMapAttrbTxt_20 property
        bean = new MktgMapWrkBean(tMsg.getAttr("mktgMapAttrbTxt_20"), tMsg.mktgMapAttrbTxt_20, hdrLineList, upldLineList, (String) map.get(ATTRB_TXT_COL_DFN_NM_20), cMsg.mktgMapAttrbTxt_20, tMsg.upldCsvRqstCmntTxt);
        isError = setMktgMapWrkParam(bean);
        if (isError) {
            if (ZYPCommonFunc.hasValue(tMsg.upldCsvRqstCmntTxt)) {
                return tMsg;
            } else {
                cMsg.setMessageInfo(NMAM8618E, new String[] {(String) map.get(ATTRB_TXT_COL_DFN_NM_20) });
                return null;
            }
        }
        return tMsg;
    }

    /**
     * Insert MKTG_FLD_MAP
     * @param cMsg NMAL3200CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean insertMktgFldMap(NMAL3200CMsg cMsg, String glblCmpyCd) {

        MKTG_FLD_MAPTMsg tMsg = new MKTG_FLD_MAPTMsg();
        BigDecimal mktgFldMapPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MKTG_FLD_MAP_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgFldMapPk, mktgFldMapPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgFldMapNm, cMsg.mktgFldMapNm_SC);

        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNmColDfnNm, cMsg.dsAcctNmColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddrColDfnNm, cMsg.firstLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddrColDfnNm, cMsg.scdLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddrColDfnNm, cMsg.thirdLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddrColDfnNm, cMsg.frthLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddrColDfnNm, cMsg.ctyAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.cntyPkColDfnNm, cMsg.cntyPkColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.stCdColDfnNm, cMsg.stCdColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.postCdColDfnNm, cMsg.postCdColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCdColDfnNm, cMsg.ctryCdColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsNumColDfnNm, cMsg.dunsNumColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.glnColDfnNm, cMsg.glnColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgFldMapNm, cMsg.mktgFldMapNm_SC);
        ZYPEZDItemValueSetter.setValue(tMsg.hinColDfnNm, cMsg.hinColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_01, cMsg.attrbTxtColDfnNm_01);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_02, cMsg.attrbTxtColDfnNm_02);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_03, cMsg.attrbTxtColDfnNm_03);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_04, cMsg.attrbTxtColDfnNm_04);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_05, cMsg.attrbTxtColDfnNm_05);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_06, cMsg.attrbTxtColDfnNm_06);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_07, cMsg.attrbTxtColDfnNm_07);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_08, cMsg.attrbTxtColDfnNm_08);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_09, cMsg.attrbTxtColDfnNm_09);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_10, cMsg.attrbTxtColDfnNm_10);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_11, cMsg.attrbTxtColDfnNm_11);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_12, cMsg.attrbTxtColDfnNm_12);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_13, cMsg.attrbTxtColDfnNm_13);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_14, cMsg.attrbTxtColDfnNm_14);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_15, cMsg.attrbTxtColDfnNm_15);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_16, cMsg.attrbTxtColDfnNm_16);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_17, cMsg.attrbTxtColDfnNm_17);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_18, cMsg.attrbTxtColDfnNm_18);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_19, cMsg.attrbTxtColDfnNm_19);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_20, cMsg.attrbTxtColDfnNm_20);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0282E, new String[] {MKTG_FLD_MAP });
            return false;
        }
        return true;
    }

    /**
     * Update MKTG_FLD_MAP
     * @param cMsg NMAL3200CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean updateMktgFldMap(NMAL3200CMsg cMsg, String glblCmpyCd) {

        MKTG_FLD_MAPTMsg tMsg = lockMktgFldMapForUpdate(cMsg, glblCmpyCd);
        if (NMAM8175E.equals(cMsg.getMessageCode())) {
            return false;
        }
        if (tMsg == null) {
            cMsg.setMessageInfo(NMAM8175E, new String[] {MKTG_FLD_MAP, MKTG_FLD_MAP_NM, cMsg.mktgFldMapNm_DB.getValue() });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.mktgFldMapNm, cMsg.mktgFldMapNm_DB);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNmColDfnNm, cMsg.dsAcctNmColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddrColDfnNm, cMsg.firstLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddrColDfnNm, cMsg.scdLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddrColDfnNm, cMsg.thirdLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddrColDfnNm, cMsg.frthLineAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddrColDfnNm, cMsg.ctyAddrColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.cntyPkColDfnNm, cMsg.cntyPkColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.stCdColDfnNm, cMsg.stCdColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.postCdColDfnNm, cMsg.postCdColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCdColDfnNm, cMsg.ctryCdColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsNumColDfnNm, cMsg.dunsNumColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.glnColDfnNm, cMsg.glnColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.hinColDfnNm, cMsg.hinColDfnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_01, cMsg.attrbTxtColDfnNm_01);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_02, cMsg.attrbTxtColDfnNm_02);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_03, cMsg.attrbTxtColDfnNm_03);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_04, cMsg.attrbTxtColDfnNm_04);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_05, cMsg.attrbTxtColDfnNm_05);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_06, cMsg.attrbTxtColDfnNm_06);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_07, cMsg.attrbTxtColDfnNm_07);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_08, cMsg.attrbTxtColDfnNm_08);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_09, cMsg.attrbTxtColDfnNm_09);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_10, cMsg.attrbTxtColDfnNm_10);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_11, cMsg.attrbTxtColDfnNm_11);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_12, cMsg.attrbTxtColDfnNm_12);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_13, cMsg.attrbTxtColDfnNm_13);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_14, cMsg.attrbTxtColDfnNm_14);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_15, cMsg.attrbTxtColDfnNm_15);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_16, cMsg.attrbTxtColDfnNm_16);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_17, cMsg.attrbTxtColDfnNm_17);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_18, cMsg.attrbTxtColDfnNm_18);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_19, cMsg.attrbTxtColDfnNm_19);
        ZYPEZDItemValueSetter.setValue(tMsg.attrbTxtColDfnNm_20, cMsg.attrbTxtColDfnNm_20);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0282E, new String[] {MKTG_FLD_MAP });
            return false;
        }
        return true;
    }

    /**
     * MKTG_FLD_MAP Table Recode Lock
     * @param cMsg NMAL3200CMsg
     * @param glblCmpyCd String
     * @return MKTG_FLD_MAPTMsg
     */
    private MKTG_FLD_MAPTMsg lockMktgFldMapForUpdate(NMAL3200CMsg cMsg, String glblCmpyCd) {

        MKTG_FLD_MAPTMsg tMsg = new MKTG_FLD_MAPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mktgFldMapPk, cMsg.mktgFldMapPk);

        tMsg = (MKTG_FLD_MAPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            return null;
        }

        String findEzUpTime = tMsg.ezUpTime.getValue();
        String findEzUpTimeZone = tMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = cMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = cMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NMAM8175E, new String[] {MKTG_FLD_MAP, MKTG_FLD_MAP_NM, cMsg.mktgFldMapNm_DB.getValue() });
            return null;
        }
        return tMsg;
    }

    /**
     * set MKTG_MAP_WRK Property
     * @param bean MktgMapWrkBean
     * @return boolean
     */
    private static boolean setMktgMapWrkParam(MktgMapWrkBean bean) {

        String dbStr = bean.getDbStr();
        List<String> hdrLineList = bean.getHdrLineList();
        List<String> upldLineList = bean.getUpldLineList();
        EZDItemAttribute attr = bean.getAttr();
        EZDTItem item = bean.getItem();
        EZDCItem cItem = bean.getCItem();
        EZDTStringItem upldCsvRqstCmntTxtItem = bean.getUpldCsvRqstCmntTxtItem();

        if (!ZYPCommonFunc.hasValue(dbStr)) {
            return false;
        }

        // Check included in Upload file header values
        String dbUStr = dbStr.toUpperCase();
        if (!hdrLineList.contains(dbUStr)) {
            // Not included
            return true;
        }
        // Get the value of the line rows that match the value of the
        // header and DB
        for (int i = 0; i < hdrLineList.size(); i++) {
            String hdrStr = hdrLineList.get(i);
            if (dbUStr.equals(hdrStr)) {
                setEZDTItemValue(attr, item, upldLineList.get(i), cItem, upldCsvRqstCmntTxtItem);
                break;
            }
        }

        if (ZYPCommonFunc.hasValue(upldCsvRqstCmntTxtItem)) {
            // Line Error
            return true;
        }

        return false;
    }

    /**
     * set MKTG_MAP_WRK Property for cntyPk
     * @param bean MktgMapWrkBean
     * @param stCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean setMktgMapWrkParam4CntyPk(MktgMapWrkBean bean, String stCd, String glblCmpyCd) {

        String dbStr = bean.getDbStr();
        List<String> hdrLineList = bean.getHdrLineList();
        List<String> upldLineList = bean.getUpldLineList();
        EZDItemAttribute attr = bean.getAttr();
        EZDTItem item = bean.getItem();
        EZDCItem cItem = bean.getCItem();
        EZDTStringItem upldCsvRqstCmntTxtItem = bean.getUpldCsvRqstCmntTxtItem();

        if (!ZYPCommonFunc.hasValue(dbStr)) {
            return false;
        }

        // Check included in Upload file header values
        String dbUStr = dbStr.toUpperCase();
        if (!hdrLineList.contains(dbUStr)) {
            // Not included
            return false;
        }

        // Get the value of the line rows that match the value of the
        // header and DB
        for (int i = 0; i < hdrLineList.size(); i++) {
            String hdrStr = hdrLineList.get(i);
            if (dbUStr.equals(hdrStr)) {

                String cntyNm = upldLineList.get(i);
                if (!ZYPCommonFunc.hasValue(cntyNm)) {
                    break;
                }

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_CNTY_NM, cntyNm.toUpperCase());
                ssmParam.put(DB_PARAM_ST_CD, stCd.toUpperCase());

                // Execute
                S21SsmEZDResult result = NMAL3200Query.getInstance().findSingleRecord("findCntyPk", ssmParam);

                if (result.isCodeNormal()) {
                    setEZDTItemValue(attr, item, String.valueOf(result.getResultObject()), cItem, upldCsvRqstCmntTxtItem);
                } else {
                    upldCsvRqstCmntTxtItem.setValue(attr.getColumnName() + ERROR_NULL_MSG);
                }
                break;
            }
        }

        if (ZYPCommonFunc.hasValue(upldCsvRqstCmntTxtItem)) {
            // Line Error
            return true;
        }

        return false;
    }

    /**
     * set MKTG_MAP_WRK Property for ctryCd
     * @param bean MktgMapWrkBean
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean setMktgMapWrkParam4CtryCd(MktgMapWrkBean bean, String glblCmpyCd) {

        String dbStr = bean.getDbStr();
        List<String> hdrLineList = bean.getHdrLineList();
        List<String> upldLineList = bean.getUpldLineList();
        EZDItemAttribute attr = bean.getAttr();
        EZDTItem item = bean.getItem();
        EZDCItem cItem = bean.getCItem();
        EZDTStringItem upldCsvRqstCmntTxtItem = bean.getUpldCsvRqstCmntTxtItem();

        if (!ZYPCommonFunc.hasValue(dbStr)) {
            return false;
        }

        // Check included in Upload file header values
        String dbUStr = dbStr.toUpperCase();
        if (!hdrLineList.contains(dbUStr)) {
            // Not included
            return false;
        }

        // Get the value of the line rows that match the value of the
        // header and DB
        for (int i = 0; i < hdrLineList.size(); i++) {
            String hdrStr = hdrLineList.get(i);
            if (dbUStr.equals(hdrStr)) {

                String ctryCd = upldLineList.get(i);
                if (!ZYPCommonFunc.hasValue(ctryCd)) {
                    break;
                }

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_CTRY_CD, ctryCd.toUpperCase());

                // S21 Country Code check
                S21SsmEZDResult result = NMAL3200Query.getInstance().findSingleRecord("findCtryCd", ssmParam);

                if (result.isCodeNormal()) {
                    setEZDTItemValue(attr, item, (String) result.getResultObject(), cItem, upldCsvRqstCmntTxtItem);
                } else {
                    // ISO Country Code check
                    result = NMAL3200Query.getInstance().findSingleRecord("findCtryMapCd", ssmParam);
                    if (result.isCodeNormal()) {
                        setEZDTItemValue(attr, item, (String) result.getResultObject(), cItem, upldCsvRqstCmntTxtItem);
                    } else {
                        upldCsvRqstCmntTxtItem.setValue(attr.getColumnName() + ERROR_NULL_MSG);
                    }
                }
                break;
            }
        }

        if (ZYPCommonFunc.hasValue(upldCsvRqstCmntTxtItem)) {
            // Line Error
            return true;
        }

        return false;
    }

    /**
     * Set the MKTG_MAP_WRK(ctyAddr and firstLineAddr) property,
     * however, in the case of digits over and cut the string.
     * @param bean MktgMapWrkBean
     * @return boolean
     */
    private static boolean setMktgMapWrkParamSubStr(MktgMapWrkBean bean) {

        String dbStr = bean.getDbStr();
        List<String> hdrLineList = bean.getHdrLineList();
        List<String> upldLineList = bean.getUpldLineList();
        EZDItemAttribute attr = bean.getAttr();
        EZDTItem item = bean.getItem();
        EZDCItem cItem = bean.getCItem();
        EZDTStringItem upldCsvRqstCmntTxtItem = bean.getUpldCsvRqstCmntTxtItem();

        if (!ZYPCommonFunc.hasValue(dbStr)) {
            return false;
        }

        // Check included in Upload file header values
        String dbUStr = dbStr.toUpperCase();
        if (!hdrLineList.contains(dbUStr)) {
            // Not included
            return true;
        }
        // Get the value of the line rows that match the value of the
        // header and DB
        for (int i = 0; i < hdrLineList.size(); i++) {
            String hdrStr = hdrLineList.get(i);
            if (dbUStr.equals(hdrStr)) {
                setEZDTItemValueSubStr(attr, item, upldLineList.get(i), cItem, upldCsvRqstCmntTxtItem);
                break;
            }
        }

        if (ZYPCommonFunc.hasValue(upldCsvRqstCmntTxtItem)) {
            // Line Error
            return true;
        }

        return false;
    }

    /**
     * set EZDTItem Value
     * @param targetItem EZDTItem
     * @param fromItem String
     * @param cItem EZDCItem
     * @param upldCsvRqstCmntTxtItem EZDTStringItem
     */
    private static void setEZDTItemValue(EZDItemAttribute attr, EZDTItem targetItem, String fromItem, EZDCItem cItem, EZDTStringItem upldCsvRqstCmntTxtItem) {
        if (targetItem instanceof EZDTBigDecimalItem) {
            if (!ZYPCommonFunc.hasValue(fromItem)) {
                return;
            }
            if (ZYPCommonFunc.isNumeric(fromItem)) {
                int max = attr.getDigit();
                if (max >= fromItem.length()) {
                    BigDecimal val = new BigDecimal(fromItem);
                    ZYPEZDItemValueSetter.setValue((EZDCBigDecimalItem) cItem, val);
                    ZYPEZDItemValueSetter.setValue(targetItem, cItem);
                } else {
                    upldCsvRqstCmntTxtItem.setValue(attr.getColumnName() + ERROR_LENGTH_MSG);
                }
            } else {
                upldCsvRqstCmntTxtItem.setValue(attr.getColumnName() + ERROR_ITEM_MSG);
            }
        } else {
            if (!ZYPCommonFunc.hasValue(fromItem)) {
                return;
            }
            int max = attr.getDigit();
            if (max >= fromItem.length()) {
                ZYPEZDItemValueSetter.setValue((EZDCStringItem) cItem, fromItem);
                ZYPEZDItemValueSetter.setValue(targetItem, cItem);
            } else {
                upldCsvRqstCmntTxtItem.setValue(attr.getColumnName() + ERROR_LENGTH_MSG);
            }

        }
        cItem.clear();
    }

    /**
     * set the EZDTItem Value however, in the case of digits over and
     * cut the string.
     * @param targetItem EZDTItem
     * @param fromItem String
     * @param cItem EZDCItem
     * @param upldCsvRqstCmntTxtItem EZDTStringItem
     */
    private static void setEZDTItemValueSubStr(EZDItemAttribute attr, EZDTItem targetItem, String fromItem, EZDCItem cItem, EZDTStringItem upldCsvRqstCmntTxtItem) {

        if (!ZYPCommonFunc.hasValue(fromItem)) {
            return;
        }
        int max = attr.getDigit();
        if (max >= fromItem.length()) {
            ZYPEZDItemValueSetter.setValue((EZDCStringItem) cItem, fromItem);
            ZYPEZDItemValueSetter.setValue(targetItem, cItem);
        } else {
            String txt = fromItem.substring(0, max);
            ZYPEZDItemValueSetter.setValue((EZDCStringItem) cItem, txt);
            ZYPEZDItemValueSetter.setValue(targetItem, cItem);
        }

        cItem.clear();
    }

    /**
     * Create CSV Line
     * @param str String
     * @param upperFlag boolean
     * @return List<String>
     */
    private static List<String> separateCSVTokens(String str, boolean upperFlag) {

        List<String> rtnAry = new ArrayList<String>();
        if (str.length() == 0) {
            rtnAry.add(null);
            return rtnAry;
        }

        StringBuilder buf = new StringBuilder(str);
        while (buf.length() > 0) {
            rtnAry.add(getCSVToken(buf, true, upperFlag));
        }

        if (str.charAt(str.length() - 1) == CSV_DELIMITER_CHAR) {
            rtnAry.add(null);
        }
        return rtnAry;
    }

    /**
     * Create CSV Token
     * @param org StringBuilder
     * @param quoteFlag boolean
     * @param upperFlag boolean
     * @return
     */
    private static String getCSVToken(StringBuilder org, boolean quoteFlag, boolean upperFlag) {

        int count = org.length();

        int sidx = 0;
        int eidx = count;
        int mark = count;

        char preC = (char) 0;
        boolean isQuote = (org.charAt(0) == '\"');
        if (isQuote && !quoteFlag) {
            isQuote = false;
        }

        for (int i = 0; i < count; i++) {
            char c = org.charAt(i);
            if (c == CSV_DELIMITER_CHAR) {
                if (isQuote) {
                    if (preC == '\"' && i != 1) {
                        eidx = i;
                        mark = i + 1;
                        break;
                    }
                } else {
                    eidx = i;
                    mark = i + 1;
                    break;
                }
            }
            preC = c;
        }

        if (isQuote && quoteFlag) {
            if (org.charAt(eidx - 1) == '\"' && eidx != 1) {
                sidx = 1;
                eidx--;
            }
        }

        String rtn = org.substring(sidx, eidx);
        rtn = EZDStringUtil.trimEndSpace(rtn);

        rtn = rtn.replaceAll("\"\"", "\"");

        org.delete(0, mark);
        if (rtn.length() == 0 && !isQuote) {
            return null;
        }

        if (rtn.length() != 0 && upperFlag) {
            return rtn.toUpperCase();
        } else {
            return rtn;
        }
    }
}
