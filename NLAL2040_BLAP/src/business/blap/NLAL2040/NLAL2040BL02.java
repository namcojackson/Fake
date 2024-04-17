/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2040;

import static business.blap.NLAL2040.constant.NLAL2040Constant.ADDLINE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.CLEAR;
import static business.blap.NLAL2040.constant.NLAL2040Constant.COA_MDSE_TP_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.COA_PROD_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.CSV_FILE_NAME;
import static business.blap.NLAL2040.constant.NLAL2040Constant.CSV_HDR;
import static business.blap.NLAL2040.constant.NLAL2040Constant.DELETELINE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.DEST_RTL_SWH_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.DOWNLOAD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.DT_FROM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.EFF_FROM_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.EFF_THRU_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.EXTN_CSV;
import static business.blap.NLAL2040.constant.NLAL2040Constant.FROM_ELPS_MTH_AOT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.FROM_MTR_CNT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.GLBL_CMPY_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.IMPORT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.INIT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.INVTY_OWNR_DESC_TXT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.LIMIT_DL_ROWNUM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MDL_ID;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MODEL;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAL2040_DEF_EFF_THRU_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLCM0025E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NZZM0000E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NZZM0001W;
import static business.blap.NLAL2040.constant.NLAL2040Constant.PAGENEXT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.PAGEPREV;
import static business.blap.NLAL2040.constant.NLAL2040Constant.ROWNUM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.RTL_WH_CATG_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SALES_DATE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SEARCH;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SQ_ID;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SVC_SEG_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SVC_SEG_DESC_TXT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SWH;
import static business.blap.NLAL2040.constant.NLAL2040Constant.TEMPLETE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.TERMINATED_EFF_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.THIRD_PTY_DSP_TP_NM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.TO_ELPS_MTH_AOT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.TO_MTR_CNT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.T_MDL_NM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.UPLOAD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.ZYEM0004E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.ZZM9000E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.ZZM9014E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAM1346E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLAL2040.common.NLAL2040CommonLogic;
import business.blap.NLAL2040.constant.NLAL2040Constant;
import business.file.NLAL2040F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SEG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.THIRD_PTY_DSP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 * 12/01/2016   CSAI            Y.Imazu         Update          QC#16342
 * 12/07/2016   CITS            R.Shimamoto     Update          QC#13056
 * 12/27/2016   CITS            T.Kikuhara      Update          QC#13056-2
 * 01/31/2018   CITS            K.Ogino         Update          QC#22856
 * 03/28/2018   CITS            K.Masaki        Update          QC#24622
 * 
 *</pre>
 */
public class NLAL2040BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (INIT.equals(screenAplID)) {
                doProcess_NLAL2040_INIT((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (SEARCH.equals(screenAplID)) {
                doProcess_NLAL2040_Search((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (IMPORT.equals(screenAplID)) {
                doProcess_NLAL2040_Import((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (DOWNLOAD.equals(screenAplID)) {
                doProcess_NLAL2040_Download((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (TEMPLETE.equals(screenAplID)) {
                doProcess_NLAL2040_UploadTemplate((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (ADDLINE.equals(screenAplID)) {
                doProcess_NLAL2040_AddLine((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (DELETELINE.equals(screenAplID)) {
                doProcess_NLAL2040_DeleteLine((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (PAGENEXT.equals(screenAplID)) {
                doProcess_NLAL2040_PageNext((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (PAGEPREV.equals(screenAplID)) {
                doProcess_NLAL2040_PagePrev((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (CLEAR.equals(screenAplID)) {
                doProcess_NLAL2040Scrn00_CMN_Clear((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
    * Init
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    * @param eventName String
    */
    private void doProcess_NLAL2040_INIT(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        // CMsg clear, SMsg clear
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        EZDMsg.copy(cMsg, null, sMsg, null);
        cMsg.clear();
        sMsg.clear();

        // Get Global Company Code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // Create PulldownList
        ZYPCodeDataUtil.createPulldownList(SVC_SEG.class, cMsg.svcSegCd_PC, cMsg.svcSegDescTxt_PD);
        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, cMsg.coaMdseTpCd_PC, cMsg.coaMdseTpDescTxt_PD);
        ZYPCodeDataUtil.createPulldownList(COA_PROD.class, cMsg.coaProdCd_PC, cMsg.coaProdDescTxt_PD);
        ZYPCodeDataUtil.createPulldownList(THIRD_PTY_DSP_TP.class, cMsg.thirdPtyDspTpCd_PC, cMsg.thirdPtyDspTpDescTxt_PD);

        // Create Sub Ware house PulldownList
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(RTL_WH_CATG_CD, RTL_WH_CATG.EQUIPMENT);
        S21SsmEZDResult result = NLAL2040Query.getInstance().getRtlWhCatgSwPulldownList(ssmParam);
        if (result.isCodeNormal()) {
            List<String> rtlSwhCdList = (List<String>) result.getResultObject();
            int i = 0;
            for (String rtlSwhCd : rtlSwhCdList) {
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_PC.no(i), rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_PD.no(i), rtlSwhCd);
                i++;
            }
        }

        // Get Default Effective Through Date
        String effThruDt = ZYPCodeDataUtil.getVarCharConstValue(NLAL2040_DEF_EFF_THRU_DT, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(effThruDt)) {
            effThruDt = TERMINATED_EFF_DT;
        }
        cMsg.effThruDt.setValue(effThruDt);

        // QC#13056 Add.
        // Create Inventory Owner PulldownList
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLBL_CMPY_CD, getGlobalCompanyCode());
        result = NLAL2040Query.getInstance().getOwnerCdPulldownList(ssmParam);
        if (result.isCodeNormal()) {
            List<Map<String, String>> invOwnrCdList = (List<Map<String, String>>) result.getResultObject();
            int i = 0;
            for (Map<String, String> invOwnrCd : invOwnrCdList) {
                ZYPEZDItemValueSetter.setValue(cMsg.invtyOwnrCd_PC.no(i), (String) invOwnrCd.get("INVTY_OWNR_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.invtyOwnrDescTxt_PD.no(i), (String) invOwnrCd.get("INVTY_OWNR_DESC_TXT"));
                i++;
            }
        }


        // Add New Detail
        doProcess_NLAL2040_AddLine(cMsg, sMsg);

        ZYPGUITableColumn.getColData(cMsg, sMsg);

    }

    /**
     * Search
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    private void doProcess_NLAL2040_Search(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        NLAL2040CommonLogic.search(cMsg, sMsg, SEARCH);
    }

   /**
    * Add Line
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    */
    private void doProcess_NLAL2040_AddLine(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        // check max count
        // QC#22856
        int i = sMsg.A.getValidCount();
        if (i == sMsg.A.length()) {
            cMsg.setMessageInfo(NLCM0025E);
            return; 
        } else {

            // copy data from CMsg onto SMsg
            NLAL2040CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            String salseDate = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).effFromDt_A1, salseDate);
            sMsg.A.setValidCount(i + 1);
            cMsg.xxPageShowFromNum.setValue(-1);

            // copy data from SMsg onto CMsg
            ZYPTableUtil.clear(cMsg.A);
            NLAL2040CommonLogic.setPagePos(cMsg, sMsg);
            NLAL2040CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * Delete Line Data
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    private void doProcess_NLAL2040_DeleteLine(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        NLAL2040CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        int i = 0;
        int j = 0;

        NLAL2040SMsg wkSMsg = new NLAL2040SMsg();

        // add delete row to sMsg.B record
        ZYPTableUtil.clear(sMsg.B);
        for (; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A1)) {
                 ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).mdlId_B1, sMsg.A.no(i).mdlId_A1);
                 ZYPEZDItemValueSetter.setValue(sMsg.B.no(j).sqId_B1, sMsg.A.no(i).sqId_A1);
                j++;
            }
        }
        sMsg.B.setValidCount(j);

        i = 0;
        j = 0;

        // delete row from sMsg.A record
        for (; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A1)) {
                EZDMsg.copy(sMsg.A.no(i), null, wkSMsg.A.no(j), null);
                j++;
            }
        }
        wkSMsg.A.setValidCount(j);
        ZYPTableUtil.clear(sMsg.A);
        EZDMsg.copy(wkSMsg.A, null, sMsg.A, null);

        // copy data from SMsg to CMsg
        cMsg.xxPageShowFromNum.setValue(-1);
        ZYPTableUtil.clear(cMsg.A);
        NLAL2040CommonLogic.setPagePos(cMsg, wkSMsg);
        NLAL2040CommonLogic.copyFromSmsgOntoCmsg(cMsg, wkSMsg);

    }

    /**
     * Page Next
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    private void doProcess_NLAL2040_PageNext(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        // copy data from CMsg onto SMsg
        NLAL2040CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        ZYPTableUtil.clear(cMsg.A);
        NLAL2040CommonLogic.setPagePos(cMsg, sMsg);
        // copy data from SMsg onto CMsg
        NLAL2040CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Page Prev
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    private void doProcess_NLAL2040_PagePrev(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        // copy data from CMsg onto SMsg
        NLAL2040CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        ZYPTableUtil.clear(cMsg.A);
        NLAL2040CommonLogic.setPagePos(cMsg, sMsg);
        // copy data from SMsg onto CMsg
        NLAL2040CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
    }

    /**
    * IMPORT
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    */
    private void doProcess_NLAL2040_Import(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        String path = cMsg.xxFileData_UP.getTempFilePath();
        if (path.length() == 0) {
            cMsg.xxFileData_UP.setErrorInfo(1, ZYEM0004E);
            return;
        }
        String csvPath = ZYPExcelUtil.excelToCsvFile(path);

        NLAL2040F00FMsg fMsg = new NLAL2040F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvPath, fMsg, option);

        int recCnt = 0;
        try {
            // Check Header Record Only
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.xxFileData_UP.setErrorInfo(1, ZYEM0004E);
                return;
            }

            int status = -1;
            while ((status = mappedFile.read()) != 1) {

                // Check Over Max Record Count
                recCnt++;
                if (recCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo(NLCM0025E);
                    return;
                }

                // Check Mandatory
                if (!ZYPCommonFunc.hasValue(fMsg.effFromDt_U1)) {
                    cMsg.setMessageInfo(NLAM1346E, new String[]{DT_FROM });
                    return;
                }
                if (!ZYPCommonFunc.hasValue(fMsg.t_MdlNm_U1)) {
                    cMsg.setMessageInfo(ZZM9000E, new String[]{MODEL });
                    return;
                }
                if (!ZYPCommonFunc.hasValue(fMsg.destRtlSwhCd_U1)) {
                    cMsg.setMessageInfo(ZZM9000E, new String[]{SWH });
                    return;
                }

                // get INVTY_OWNR_CD By Name
                if (ZYPCommonFunc.hasValue(fMsg.thirdPtyDspTpDescTxt_U1)) {
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put(GLBL_CMPY_CD, getGlobalCompanyCode());
                    ssmParam.put(INVTY_OWNR_DESC_TXT, fMsg.invtyOwnrDescTxt_U1);
                    S21SsmEZDResult result = NLAL2040Query.getInstance().getInvtyOwnerCd(ssmParam);
                    if (result.isCodeNormal()) {
                        String invtyOwnrCd = (String) result.getResultObject();
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).invtyOwnrCd_AS, invtyOwnrCd);
                    }
                }

                // get THIRD_PTY_DSP_TP_CD By Name
                if (ZYPCommonFunc.hasValue(fMsg.thirdPtyDspTpDescTxt_U1)) {
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put(GLBL_CMPY_CD, getGlobalCompanyCode());
                    ssmParam.put(THIRD_PTY_DSP_TP_NM, fMsg.thirdPtyDspTpDescTxt_U1);
                    S21SsmEZDResult result = NLAL2040Query.getInstance().getThirdPtyDspTpCd(ssmParam);
                    if (result.isCodeNormal()) {
                        String thirdPtyDspTpCd = (String) result.getResultObject();
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).thirdPtyDspTpCd_AS, thirdPtyDspTpCd);
                    }
                }

                // set import file to sMsg
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).effFromDt_A1, fMsg.effFromDt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).effThruDt_A1, fMsg.effThruDt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).t_MdlNm_A1, fMsg.t_MdlNm_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).fromMtrCnt_A1, fMsg.fromMtrCnt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).toMtrCnt_A1, fMsg.toMtrCnt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).fromElpsMthAot_A1, fMsg.fromElpsMthAot_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).toElpsMthAot_A1, fMsg.toElpsMthAot_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).rtlSwhCd_AS, fMsg.destRtlSwhCd_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).mdlId_A1, fMsg.mdlId_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(recCnt - 1).sqId_A1, fMsg.sqId_U1);
                sMsg.A.setValidCount(recCnt);
            }

            if (0 == sMsg.A.getValidCount()) {
                cMsg.setMessageInfo(ZZM9014E, new String[]{UPLOAD });
                return;
            }

            // Set Import Data Flag
            ZYPEZDItemValueSetter.setValue(sMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);

        } finally {
            mappedFile.close();
            cMsg.xxFileData_UP.deleteTempFile();
            ZYPExcelUtil.deleteFile(csvPath);
        }
        cMsg.xxPageShowFromNum.setValue(-1);

        // copy data from SMsg to CMsg
        NLAL2040CommonLogic.setPagePos(cMsg, sMsg);
        NLAL2040CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
    }

    /**
    * <pre>
    * Template file for Upload
    * </pre>
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    */
    private void doProcess_NLAL2040_UploadTemplate(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        NLAL2040F00FMsg fMsg = new NLAL2040F00FMsg();

        cMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_UP.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HDR);

        csvOutFile.close();
    }

    /**
    * <pre>
    * Common Down load
    * </pre>
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    */
    private void doProcess_NLAL2040_Download(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NLAL2040Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLAL2040Query.getInstance().getClass());
            //create csv file, parameters
            cMsg.xxFileData_DW.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            ssmParam.put(T_MDL_NM, cMsg.t_MdlNm.getValue());
            ssmParam.put(SVC_SEG_CD, cMsg.svcSegCd_PS.getValue());
            ssmParam.put(COA_MDSE_TP_CD, cMsg.coaMdseTpCd_PS.getValue());
            ssmParam.put(COA_PROD_CD, cMsg.coaProdCd_PS.getValue());
            ssmParam.put(EFF_FROM_DT, cMsg.effFromDt.getValue());
            ssmParam.put(EFF_THRU_DT, cMsg.effThruDt.getValue());
            ssmParam.put(ROWNUM, LIMIT_DL_ROWNUM);
            if (ZYPCommonFunc.hasValue(cMsg.xxChkBox)) {
                String salseDate = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
                ssmParam.put(SALES_DATE, salseDate);
            }
            ps = ssmLLClient.createPreparedStatement("search", ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Down load Event
     * @param cMsg      NLAL2040CMsg
     * @param rs ResultSet
     */
    private void writeCsvFile(NLAL2040CMsg cMsg, ResultSet rs) throws SQLException {

        NLAL2040F00FMsg fMsg = new NLAL2040F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DW.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(NLAL2040Constant.CSV_HDR);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= NLAL2040Constant.LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mdlId_U1, rs.getBigDecimal(MDL_ID));
            ZYPEZDItemValueSetter.setValue(fMsg.sqId_U1, rs.getString(SQ_ID));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_U1, rs.getString(EFF_FROM_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_U1, rs.getString(EFF_THRU_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm_U1, rs.getString(T_MDL_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.svcSegDescTxt_U1, rs.getString(SVC_SEG_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd_U1, rs.getString(COA_MDSE_TP_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd_U1, rs.getString(COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.fromMtrCnt_U1, rs.getBigDecimal(FROM_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(fMsg.toMtrCnt_U1, rs.getBigDecimal(TO_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(fMsg.fromElpsMthAot_U1, rs.getBigDecimal(FROM_ELPS_MTH_AOT));
            ZYPEZDItemValueSetter.setValue(fMsg.toElpsMthAot_U1, rs.getBigDecimal(TO_ELPS_MTH_AOT));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlSwhCd_U1, rs.getString(DEST_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.thirdPtyDspTpDescTxt_U1, rs.getString(THIRD_PTY_DSP_TP_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.invtyOwnrDescTxt_U1, rs.getString(INVTY_OWNR_DESC_TXT));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * <pre>
     * Clear Event
     * </pre>
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    private void doProcess_NLAL2040Scrn00_CMN_Clear(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        doProcess_NLAL2040_INIT(cMsg, sMsg);
    }

}
