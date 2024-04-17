/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2580.common;

import static business.blap.NMAL2580.constant.NMAL2580Constant.CSV_DOWNLOAD_HEADER_NMAL2460;
import static business.blap.NMAL2580.constant.NMAL2580Constant.CSV_DOWNLOAD_HEADER_NMAL2620;
import static business.blap.NMAL2580.constant.NMAL2580Constant.CSV_DOWNLOAD_HEADER_NMAL2710;
import static business.blap.NMAL2580.constant.NMAL2580Constant.CSV_DOWNLOAD_HEADER_NMAL2720;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FILE_NAME_NMAL2460;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FILE_NAME_NMAL2620;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FILE_NAME_NMAL2710;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2460;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2620;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2710;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2720;

import java.sql.ResultSet;
import java.sql.SQLException;

import parts.common.EZDFMsg;
import business.blap.NMAL2580.NMAL2580CMsg;
import business.blap.NMAL2580.NMAL2580SMsg;
import business.db.MOVE_ORG_UPD_WRKFMsg;
import business.db.POST_UPD_WRKFMsg;
import business.file.NMAL2580F00FMsg;
import business.file.TRTY_UPD_RQST_DTLFMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * NMAL2580CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2016/11/28   Fujitsu         M.Ohno          Update          S21_NA#15623
 * 2017/10/16   Hitachi         J.Kim           Update          QC#21299
 * 2017/12/08   Fujitsu         N.Sugiura       Update          QC#21692
 *</pre>
 */
public class NMAL2580CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param bizMsg NMAL2580CMsg
     * @param glblMsg NMAL2580SMsg
     */
    public static void loadOnePageToCMsg(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = bizMsg.A.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + bizMsg.A.length(); i++) {

            if (i < glblMsg.A.getValidCount()) {

                int indexOfCMsg = i - startIndex;

                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).xxRowNum_A, glblMsg.A.no(i).xxRowNum_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).trtyUpdRqstHdrPk_A, glblMsg.A.no(i).trtyUpdRqstHdrPk_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).rqstUsrId_A, glblMsg.A.no(i).rqstUsrId_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).fill103Txt_A, glblMsg.A.no(i).fill103Txt_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).rqstDtTmTsTxt_A, glblMsg.A.no(i).rqstDtTmTsTxt_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).rqstCratSysTpDescTxt_A, glblMsg.A.no(i).rqstCratSysTpDescTxt_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).rqstRsltTpDescTxt_A, glblMsg.A.no(i).rqstRsltTpDescTxt_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).rqstRsltCmntTxt_A, glblMsg.A.no(i).rqstRsltCmntTxt_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).massUpdRsnCmntTxt_A, glblMsg.A.no(i).massUpdRsnCmntTxt_A); // Add 2017/12/08 QC#21692

            } else {

                break;
            }
        }

        bizMsg.A.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
    }

    // TODO [Template] if the input data have to copy to glblMsg,
    // uncomment below method and call this method
    /**
     * Update the global Message.
     * @param bizMsg NMAL2580CMsg
     * @param glblMsg NMAL2580SMsg
     */
    public static void updateGlblMsg(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            int indexOfCMsg = ixG + i;

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).xxRowNum_A, bizMsg.A.no(i).xxRowNum_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).trtyUpdRqstHdrPk_A, bizMsg.A.no(i).trtyUpdRqstHdrPk_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).rqstUsrId_A, bizMsg.A.no(i).rqstUsrId_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).fill103Txt_A, bizMsg.A.no(i).fill103Txt_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).rqstDtTmTsTxt_A, bizMsg.A.no(i).rqstDtTmTsTxt_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).rqstCratSysTpDescTxt_A, bizMsg.A.no(i).rqstCratSysTpDescTxt_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).rqstRsltTpDescTxt_A, bizMsg.A.no(i).rqstRsltTpDescTxt_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).rqstRsltCmntTxt_A, bizMsg.A.no(i).rqstRsltCmntTxt_A);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexOfCMsg).massUpdRsnCmntTxt_A, bizMsg.A.no(i).massUpdRsnCmntTxt_A); // Add 2017/12/08 QC#21692
        }
    }

    /**
     * clearHeaderData
     * @param bizMsg NMAL2580CMsg
     */
    public static void clearHeaderData(NMAL2580CMsg bizMsg) {

        bizMsg.trtyUpdRqstHdrPk.clear();
        bizMsg.rqstUsrId.clear();
        bizMsg.fill103Txt.clear();
        bizMsg.rqstCratSysTpCd.clear();
        bizMsg.rqstRsltTpCd.clear();
        bizMsg.rqstRsltCmntTxt.clear();
        bizMsg.effFromDt.clear();
        bizMsg.effToDt.clear();

    }

    /**
     * clearSearchData
     * @param bizMsg NMAL2580CMsg
     */
    public static void clearSearchData(NMAL2580CMsg bizMsg) {

        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        bizMsg.xxSortTblNm.clear();
        bizMsg.xxSortItemNm.clear();
        bizMsg.xxSortOrdByTxt.clear();

    }

    /**
     * clearDetailData
     * @param bizMsg NMAL2580CMsg
     * @param glblMsg NMAL2580SMsg
     */
    public static void clearDetailData(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

    }

    /**
     * setFileName
     * @param funcId String return fileName
     * @return fileName
     */
    public static String setFileName(String funcId) {

        if (FUNCTION_ID_NMAL2460.equals(funcId)) {
            return FILE_NAME_NMAL2460;
        } else if (FUNCTION_ID_NMAL2620.equals(funcId)) {
            return FILE_NAME_NMAL2620;
        } else if (FUNCTION_ID_NMAL2710.equals(funcId)) {
            return FILE_NAME_NMAL2710;
        } else if (FUNCTION_ID_NMAL2720.equals(funcId)) {
            return FILE_NAME_NMAL2710;
        } else {
            return "";
        }

    }

    /**
     * writeCsvFileNMAL2460
     * @param bizMsg NMAL2580CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileNMAL2460(NMAL2580CMsg bizMsg, ResultSet rs) throws SQLException {

        // START 2017/10/16 J.Kim [QC#21299,MOD]
        //// Mod Start 2016/11/28 M.Ohno S21_NA#15623
        //// ACCT_TRTY_RESRC_RQST_DTLFMsg fMsg = new ACCT_TRTY_RESRC_RQST_DTLFMsg();
        //ACCT_TRTY_RESRC_ASG_WRKFMsg fMsg = new ACCT_TRTY_RESRC_ASG_WRKFMsg();
        //// Mod End   2016/11/28 M.Ohno S21_NA#15623
        NMAL2580F00FMsg fMsg = new NMAL2580F00FMsg();
        // END 2017/10/16 J.Kim [QC#21299,MOD]
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        String[] csvHeader = CSV_DOWNLOAD_HEADER_NMAL2460;
        fileWriter.writeHeader(csvHeader);

        int cnt = 0;
        while (rs.next()) {
            executeFmsgItem(fMsg); // Add 2016/11/28 M.Ohno S21_NA#15623
            writeCsvLineNMAL2460(fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }

        fileWriter.close();
    }

    // Mod Start 2016/11/28 M.Ohno S21_NA#15623
    /**
     * writeCsvLineNMAL2460
     * @param fMsg ACCT_TRTY_RESRC_ASG_WRKFMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    // START 2017/10/16 J.Kim [QC#21299,MOD]
    // public static void writeCsvLineNMAL2460(NMA2610001FMsg fMsg, ResultSet rs) throws SQLException {
    public static void writeCsvLineNMAL2460(NMAL2580F00FMsg fMsg, ResultSet rs) throws SQLException {
    // END 2017/10/16 J.Kim [QC#21299,MOD]

//        ZYPEZDItemValueSetter.setValue(fMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//        ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyResrcRqstHdrPk, rs.getBigDecimal("ACCT_TRTY_RESRC_RQST_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.locNum, rs.getString("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctTpDescTxt, rs.getString("DS_ACCT_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyResrcAddr, rs.getString("ADDRESS"));
//        ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, rs.getString("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(fMsg.stCd, rs.getString("ST_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.postCd, rs.getString("POST_CD"));
        // START 2017/10/16 J.Kim [QC#21299,MOD]
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_01, rs.getString("BEF_ACCT_TRTY_ORG_NM_01"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_02, rs.getString("BEF_ACCT_TRTY_ORG_NM_02"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_03, rs.getString("BEF_ACCT_TRTY_ORG_NM_03"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_04, rs.getString("BEF_ACCT_TRTY_ORG_NM_04"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_05, rs.getString("BEF_ACCT_TRTY_ORG_NM_05"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_06, rs.getString("BEF_ACCT_TRTY_ORG_NM_06"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_07, rs.getString("BEF_ACCT_TRTY_ORG_NM_07"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_08, rs.getString("BEF_ACCT_TRTY_ORG_NM_08"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_09, rs.getString("BEF_ACCT_TRTY_ORG_NM_09"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_10, rs.getString("BEF_ACCT_TRTY_ORG_NM_10"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_11, rs.getString("BEF_ACCT_TRTY_ORG_NM_11"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_12, rs.getString("BEF_ACCT_TRTY_ORG_NM_12"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_13, rs.getString("BEF_ACCT_TRTY_ORG_NM_13"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_14, rs.getString("BEF_ACCT_TRTY_ORG_NM_14"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_15, rs.getString("BEF_ACCT_TRTY_ORG_NM_15"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_16, rs.getString("BEF_ACCT_TRTY_ORG_NM_16"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_17, rs.getString("BEF_ACCT_TRTY_ORG_NM_17"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_18, rs.getString("BEF_ACCT_TRTY_ORG_NM_18"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_19, rs.getString("BEF_ACCT_TRTY_ORG_NM_19"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm_20, rs.getString("BEF_ACCT_TRTY_ORG_NM_20"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_01, rs.getString("AFT_ACCT_TRTY_ORG_NM_01"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_02, rs.getString("AFT_ACCT_TRTY_ORG_NM_02"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_03, rs.getString("AFT_ACCT_TRTY_ORG_NM_03"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_04, rs.getString("AFT_ACCT_TRTY_ORG_NM_04"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_05, rs.getString("AFT_ACCT_TRTY_ORG_NM_05"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_06, rs.getString("AFT_ACCT_TRTY_ORG_NM_06"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_07, rs.getString("AFT_ACCT_TRTY_ORG_NM_07"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_08, rs.getString("AFT_ACCT_TRTY_ORG_NM_08"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_09, rs.getString("AFT_ACCT_TRTY_ORG_NM_09"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_10, rs.getString("AFT_ACCT_TRTY_ORG_NM_10"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_11, rs.getString("AFT_ACCT_TRTY_ORG_NM_11"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_12, rs.getString("AFT_ACCT_TRTY_ORG_NM_12"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_13, rs.getString("AFT_ACCT_TRTY_ORG_NM_13"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_14, rs.getString("AFT_ACCT_TRTY_ORG_NM_14"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_15, rs.getString("AFT_ACCT_TRTY_ORG_NM_15"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_16, rs.getString("AFT_ACCT_TRTY_ORG_NM_16"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_17, rs.getString("AFT_ACCT_TRTY_ORG_NM_17"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_18, rs.getString("AFT_ACCT_TRTY_ORG_NM_18"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_19, rs.getString("AFT_ACCT_TRTY_ORG_NM_19"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm_20, rs.getString("AFT_ACCT_TRTY_ORG_NM_20"));
        // END 2017/10/16 J.Kim [QC#21299,MOD]
        ZYPEZDItemValueSetter.setValue(fMsg.manEntryFlg, rs.getString("MAN_ENTRY_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.upldCsvRsnCmntTxt, rs.getString("MASS_UPD_RSN_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.upldCsvRqstCmntTxt, rs.getString("RQST_DTL_RSLT_CMNT_TXT"));

    }
    // Mod End   2016/11/28 M.Ohno S21_NA#15623

    /**
     * writeCsvFileNMAL2620
     * @param bizMsg NMAL2580CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileNMAL2620(NMAL2580CMsg bizMsg, ResultSet rs) throws SQLException {

        TRTY_UPD_RQST_DTLFMsg fMsg = new TRTY_UPD_RQST_DTLFMsg(); 
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        String[] csvHeader = CSV_DOWNLOAD_HEADER_NMAL2620;
        fileWriter.writeHeader(csvHeader);

        int cnt = 0;
        while (rs.next()) {
            writeCsvLineNMAL2620(fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }

        fileWriter.close();
    }

    // Mod Start 2016/11/28 M.Ohno S21_NA#15623
    /**
     * writeCsvLineNMAL2620
     * @param fMsg TRTY_UPD_RQST_DTLFMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvLineNMAL2620(TRTY_UPD_RQST_DTLFMsg fMsg, ResultSet rs) throws SQLException {

//        ZYPEZDItemValueSetter.setValue(fMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//        ZYPEZDItemValueSetter.setValue(fMsg.trtyUpdRqstDtlPk, rs.getBigDecimal("TRTY_UPD_RQST_DTL_PK"));
//        ZYPEZDItemValueSetter.setValue(fMsg.trtyUpdRqstHdrPk, rs.getBigDecimal("TRTY_UPD_RQST_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.trtyOrgNm, rs.getString("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curPsnNum, rs.getString("CUR_PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.movePsnNum, rs.getString("MOVE_PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt, formatDt(rs.getString("MOVE_EFF_FROM_DT")));
        ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt, formatDt(rs.getString("MOVE_EFF_THRU_DT")));
        ZYPEZDItemValueSetter.setValue(fMsg.massUpdRsnCmntTxt, rs.getString("MASS_UPD_RSN_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.rqstDtlRsltCmntTxt, rs.getString("RQST_DTL_RSLT_CMNT_TXT"));

    }
    // Mod End   2016/11/28 M.Ohno S21_NA#15623

    /**
     * writeCsvFileNMAL2710
     * @param bizMsg NMAL2580CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileNMAL2710(NMAL2580CMsg bizMsg, ResultSet rs) throws SQLException {

        // Mod Start 2016/11/28 M.Ohno S21_NA#15623
        // POST_UPD_RQST_DTLFMsg fMsg = new POST_UPD_RQST_DTLFMsg();
        POST_UPD_WRKFMsg fMsg = new POST_UPD_WRKFMsg();
        // Mod End   2016/11/28 M.Ohno S21_NA#15623
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        String[] csvHeader = CSV_DOWNLOAD_HEADER_NMAL2710;
        fileWriter.writeHeader(csvHeader);

        int cnt = 0;
        while (rs.next()) {
            executeFmsgItem(fMsg); // Add 2016/11/28 M.Ohno S21_NA#15623
            writeCsvLineNMAL2710(fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }

        fileWriter.close();
    }

    // Mod Start 2016/11/28 M.Ohno S21_NA#15623
    /**
     * writeCsvLineNMAL2710
     * @param fMsg POST_UPD_WRKFMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvLineNMAL2710(POST_UPD_WRKFMsg fMsg, ResultSet rs) throws SQLException {

        //ZYPEZDItemValueSetter.setValue(fMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
        //ZYPEZDItemValueSetter.setValue(fMsg.postUpdRqstDtlPk, rs.getBigDecimal("POST_UPD_RQST_DTL_PK"));
        //ZYPEZDItemValueSetter.setValue(fMsg.postUpdRqstHdrPk, rs.getBigDecimal("POST_UPD_RQST_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.trtyRuleOprdTpDescTxt, rs.getString("TRTY_RULE_OPRD_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.trtyRuleFromValTxt, rs.getString("TRTY_RULE_FROM_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.trtyRuleThruValTxt, rs.getString("TRTY_RULE_THRU_VAL_TXT"));
//        ZYPEZDItemValueSetter.setValue(fMsg.orgNm_OD, rs.getString("OLD_ORG_NM"));
//        ZYPEZDItemValueSetter.setValue(fMsg.orgNm_NW, rs.getString("NEW_ORG_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm, rs.getString("OLD_ORG_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.newOrgNm, rs.getString("NEW_ORG_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt, formatDt(rs.getString("MOVE_EFF_FROM_DT_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt, formatDt(rs.getString("MOVE_EFF_THRU_DT_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.massUpdRsnCmntTxt, rs.getString("MASS_UPD_RSN_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.upldCsvRqstCmntTxt, rs.getString("RQST_DTL_RSLT_CMNT_TXT"));

    }
    // Mod End   2016/11/28 M.Ohno S21_NA#15623

    /**
     * writeCsvFileNMAL2720
     * @param bizMsg NMAL2580CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileNMAL2720(NMAL2580CMsg bizMsg, ResultSet rs) throws SQLException {

        // Mod Start 2016/11/28 M.Ohno S21_NA#15623
        // MOVE_ORG_RQST_DTLFMsg fMsg = new MOVE_ORG_RQST_DTLFMsg();
        MOVE_ORG_UPD_WRKFMsg fMsg = new MOVE_ORG_UPD_WRKFMsg();
        // Mod Start 2016/11/28 M.Ohno S21_NA#15623
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        String[] csvHeader = CSV_DOWNLOAD_HEADER_NMAL2720;
        fileWriter.writeHeader(csvHeader);

        int cnt = 0;
        while (rs.next()) {
            executeFmsgItem(fMsg); // Add 2016/11/28 M.Ohno S21_NA#15623
            writeCsvLineNMAL2720(fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }

        fileWriter.close();
    }

    // Mod Start 2016/11/28 M.Ohno S21_NA#15623
    /**
     * MOVE_ORG_UPD_WRKFMsg
     * @param fMsg MOVE_ORG_RQST_DTLFMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvLineNMAL2720(MOVE_ORG_UPD_WRKFMsg fMsg, ResultSet rs) throws SQLException {

//        ZYPEZDItemValueSetter.setValue(fMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//        ZYPEZDItemValueSetter.setValue(fMsg.moveOrgRqstDtlPk, rs.getBigDecimal("MOVE_ORG_RQST_DTL_PK"));
//        ZYPEZDItemValueSetter.setValue(fMsg.moveOrgRqstHdrPk, rs.getBigDecimal("MOVE_ORG_RQST_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.curOrgNm, rs.getString("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curPsnNum, rs.getString("CUR_PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.movePsnNum, rs.getString("MOVE_PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt, rs.getString("MOVE_EFF_FROM_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt, rs.getString("MOVE_EFF_THRU_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.massUpdRsnCmntTxt, rs.getString("MASS_UPD_RSN_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.upldCsvRqstCmntTxt, rs.getString("RQST_DTL_RSLT_CMNT_TXT"));

    }
    // Mod End  2016/11/28 M.Ohno S21_NA#15623

    // Add Start 2016/11/28 M.Ohno S21_NA#15623
    private static void executeFmsgItem(EZDFMsg fmsg) {
        fmsg.addExclusionItem("ezTableID");
        fmsg.addExclusionItem("ezCancelFlag");
        fmsg.addExclusionItem("ezInTime");
        fmsg.addExclusionItem("ezInTimeZone");
        fmsg.addExclusionItem("ezInCompanyCode");
        fmsg.addExclusionItem("ezInUserID");
        fmsg.addExclusionItem("ezInAplID");
        fmsg.addExclusionItem("ezUpTime");
        fmsg.addExclusionItem("ezUpTimeZone");
        fmsg.addExclusionItem("ezUpCompanyCode");
        fmsg.addExclusionItem("ezUpUserID");
        fmsg.addExclusionItem("ezUpAplID");
        fmsg.addExclusionItem("glblCmpyCd");
        fmsg.addExclusionItem("upldCsvRqstPk");
        fmsg.addExclusionItem("upldCsvRqstRowNum");
    }

    private static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > 8) {
            dt = dt.substring(0, 8);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }
    // Add End 2016/11/28 M.Ohno S21_NA#15623
}
