/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1510;

import static business.blap.NPAL1510.constant.NPAL1510Constant.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.file.NPAL1510F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2017/12/11   CITS            S.Katsuma       Update          SOL#060(QC#14858)
 * 01/30/2018   CITS            K.Ogino         Update          QC#23616
 * 01/31/2018   CITS            K.Mishiro       Update          QC#22521
 * 02/27/2018   CITS            T.Gotoda        Update          QC#21944
 * 02/27/2018   CITS            T.Gotoda        Update          QC#22521-2
 * 09/23/2019   CITS            R.Shimamoto     Update          QC#53271
 * 2022/05/19   Hitachi         A.Kohinata      Update          QC#57934
 * 2023/02/09   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */
public final class NPAL1510Query extends S21SsmEZDQuerySupport {

    /**
     * 
     */
    private static final NPAL1510Query MY_INSTANCE = new NPAL1510Query();

    private NPAL1510Query() {
        super();
    }

    /**
     * @return NPAL1510Query
     */
    public static NPAL1510Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get S21SsmBatchClient for CSV Download
     * @return S21SsmBatchClient
     */
    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList() {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_STRCH_OPT_USR_ID, getContextUserInfo().getUserId());
        params.put(BIND_BUSINESS_APPL_ID, BUSINESS_APPL_ID);
        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     * @param searchCond1 String
     * @param searchCond2 String
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDispLevelPoHeaderSearchList(NPAL1510CMsg bizMsg, String searchCond1, String searchCond2, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_MSG, bizMsg);
        params.put(BIND_SEARCH_COND_ONE, searchCond1);
        params.put(BIND_SEARCH_COND_TWO, searchCond2);
        params.put(BIND_ROWNUM, rowNum);
        params.put(BIND_PO_ORD_DTL_LINE_NUM, SEARCH_HEDER_DTL_NUM);
        return getSsmEZDClient().queryObjectList("getDispLevelPoHeaderSearchList", params);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     * @param searchCond1 String
     * @param searchCond2 String
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDispLevelPoDetailSearchList(NPAL1510CMsg bizMsg, String searchCond1, String searchCond2, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_MSG, bizMsg);
        params.put(BIND_SEARCH_COND_ONE, searchCond1);
        params.put(BIND_SEARCH_COND_TWO, searchCond2);
        params.put(BIND_ROWNUM, rowNum);
        // START 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // QC#23616
        params.put(BIND_MDSE_ITEM_TP_CD_TEXT_ITEM, MDSE_ITEM_TP.TEXT_ITEM);
        // END 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // 2019/09/23 QC#53271 Add Start
        params.put(BIND_PO_LINE_TP_CD_EXPENSE, PO_LINE_TP.EXPENSE);
        params.put(BIND_PO_LINE_TP_CD_EXPENSE_W_RECEIPT, PO_LINE_TP.EXPENSE_WITH_RECEIPT);
        // 2019/09/23 QC#53271 Add End
        // add start 2022/05/19 QC#57934
        params.put("poMdseCmpsnTpRegular", PO_MDSE_CMPSN_TP.REGULAR);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NFBL1130_COA_ACCT_CD, getGlobalCompanyCode());
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(COMMA));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        params.put("coaAcctCdList", coaAcctCdList);
        params.put("drCrTpCr", DR_CR_TP_CREDIT);
        params.put("drCrTpDr", DR_CR_TP_DEBIT);
        // add end 2022/05/19 QC#57934
        return getSsmEZDClient().queryObjectList("getDispLevelPoDetailSearchList", params);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     * @param searchCond1 String
     * @param searchCond2 String
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDispLevelPoAckSearchList(NPAL1510CMsg bizMsg, String searchCond1, String searchCond2, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_MSG, bizMsg);
        params.put(BIND_SEARCH_COND_ONE, searchCond1);
        params.put(BIND_SEARCH_COND_TWO, searchCond2);
        params.put(BIND_PO_ORD_DTL_LINE_NUM, SEARCH_HEDER_DTL_NUM);
        params.put(BIND_ROWNUM, rowNum);
        // START 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // QC#23616
        params.put(BIND_MDSE_ITEM_TP_CD_TEXT_ITEM, MDSE_ITEM_TP.TEXT_ITEM);
        // END 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // 2019/09/23 QC#53271 Add Start
        params.put(BIND_PO_LINE_TP_CD_EXPENSE, PO_LINE_TP.EXPENSE);
        params.put(BIND_PO_LINE_TP_CD_EXPENSE_W_RECEIPT, PO_LINE_TP.EXPENSE_WITH_RECEIPT);
        // 2019/09/23 QC#53271 Add End
        // add start 2022/05/19 QC#57934
        params.put("poMdseCmpsnTpRegular", PO_MDSE_CMPSN_TP.REGULAR);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NFBL1130_COA_ACCT_CD, getGlobalCompanyCode());
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(COMMA));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        params.put("coaAcctCdList", coaAcctCdList);
        params.put("drCrTpCr", DR_CR_TP_CREDIT);
        params.put("drCrTpDr", DR_CR_TP_DEBIT);
        // add end 2022/05/19 QC#57934
        return getSsmEZDClient().queryObjectList("getDispLevelPoAckSearchList", params);
    }

//    QC:11819
//    /**
//     * @param bizMsg NPAL1510CMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getCarrUrl(NPAL1510CMsg bizMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
//        params.put(BIND_MSG, bizMsg);
//        return getSsmEZDClient().queryObjectList("getCarrUrl", params);
//    }

    /**
     * @param bizMsg NPAL1510CMsg
     * @param searchCond1 String
     * @param searchCond2 String
     * @param rowNum int
     * @return boolean
     */
    boolean getDispLevelPoHeaderSearchForCsv(NPAL1510CMsg bizMsg, String searchCond1, String searchCond2, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_MSG, bizMsg);
        params.put(BIND_SEARCH_COND_ONE, searchCond1);
        params.put(BIND_SEARCH_COND_TWO, searchCond2);
        params.put(BIND_ROWNUM, rowNum);
        params.put(BIND_PO_ORD_DTL_LINE_NUM, SEARCH_HEDER_DTL_NUM);
        return (Boolean) getSsmBatchClient().queryObject("getDispLevelPoHeaderSearchForCsv", params, new CsvCreator(bizMsg));
    }

    /**
     * @param bizMsg NPAL1510CMsg
     * @param searchCond1 String
     * @param searchCond2 String
     * @param rowNum int
     * @return boolean
     */
    boolean getDispLevelPoDetailSearchForCsv(NPAL1510CMsg bizMsg, String searchCond1, String searchCond2, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_MSG, bizMsg);
        params.put(BIND_SEARCH_COND_ONE, searchCond1);
        params.put(BIND_SEARCH_COND_TWO, searchCond2);
        params.put(BIND_ROWNUM, rowNum);
        // START 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // QC#23616
        params.put(BIND_MDSE_ITEM_TP_CD_TEXT_ITEM, MDSE_ITEM_TP.TEXT_ITEM);
        // END 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // 2019/09/23 QC#53271 Add Start
        params.put(BIND_PO_LINE_TP_CD_EXPENSE, PO_LINE_TP.EXPENSE);
        params.put(BIND_PO_LINE_TP_CD_EXPENSE_W_RECEIPT, PO_LINE_TP.EXPENSE_WITH_RECEIPT);
        // 2019/09/23 QC#53271 Add End
        // add start 2022/05/19 QC#57934
        params.put("poMdseCmpsnTpRegular", PO_MDSE_CMPSN_TP.REGULAR);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NFBL1130_COA_ACCT_CD, getGlobalCompanyCode());
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(COMMA));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        params.put("coaAcctCdList", coaAcctCdList);
        params.put("drCrTpCr", DR_CR_TP_CREDIT);
        params.put("drCrTpDr", DR_CR_TP_DEBIT);
        // add end 2022/05/19 QC#57934
        return (Boolean) getSsmBatchClient().queryObject("getDispLevelPoDetailSearchForCsv", params, new CsvCreator(bizMsg));
    }

    /**
     * @param bizMsg NPAL1510CMsg
     * @param searchCond1 String
     * @param searchCond2 String
     * @param rowNum int
     * @return boolean
     */
    boolean getDispLevelPoAckSearchForCsv(NPAL1510CMsg bizMsg, String searchCond1, String searchCond2, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_MSG, bizMsg);
        params.put(BIND_SEARCH_COND_ONE, searchCond1);
        params.put(BIND_SEARCH_COND_TWO, searchCond2);
        params.put(BIND_ROWNUM, rowNum);
        params.put(BIND_PO_ORD_DTL_LINE_NUM, SEARCH_HEDER_DTL_NUM);
        // START 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // QC#23616
        params.put(BIND_MDSE_ITEM_TP_CD_TEXT_ITEM, MDSE_ITEM_TP.TEXT_ITEM);
        // END 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // 2019/09/23 QC#53271 Add Start
        params.put(BIND_PO_LINE_TP_CD_EXPENSE, PO_LINE_TP.EXPENSE);
        params.put(BIND_PO_LINE_TP_CD_EXPENSE_W_RECEIPT, PO_LINE_TP.EXPENSE_WITH_RECEIPT);
        // 2019/09/23 QC#53271 Add End
        // add start 2022/05/19 QC#57934
        params.put("poMdseCmpsnTpRegular", PO_MDSE_CMPSN_TP.REGULAR);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NFBL1130_COA_ACCT_CD, getGlobalCompanyCode());
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(COMMA));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        params.put("coaAcctCdList", coaAcctCdList);
        params.put("drCrTpCr", DR_CR_TP_CREDIT);
        params.put("drCrTpDr", DR_CR_TP_DEBIT);
        // add end 2022/05/19 QC#57934
        return (Boolean) getSsmBatchClient().queryObject("getDispLevelPoAckSearchForCsv", params, new CsvCreator(bizMsg));
    }

    /**
     * Create FMsg using ResultSet
     */
    private static class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {
        /** */
        private NPAL1510CMsg bizMsg;

        public CsvCreator(NPAL1510CMsg bizMsg) {
            this.bizMsg = bizMsg;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            rs.last();
            int cnt = rs.getRow();

            if (cnt == 0) {
                bizMsg.setMessageInfo("NZZM0000E");
                return false;
            }

            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

            NPAL1510F00FMsg fMsg = new NPAL1510F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

            String[] csvHeader = getCsvHdrColum();
            csvOutFile.writeHeader(csvHeader,fMsg,ZYPGUITableColumn.getColOrder(bizMsg));

            rs.first();
            String poNumPre = "";
            String poLineNumPre = "";
            do {
                if (rs.getRow() >= MAX_DOWNLOAD_CNT) {
                    bizMsg.setMessageInfo("NZZM0001W", null);
                    break;
                }
                String poNum = rs.getString(RS_PO_ORD_NUM);
                String lineNum = rs.getString(RS_PO_DISP_LINE_NUM);
                setCsvData(fMsg, rs, poNumPre, poNum, poLineNumPre, lineNum);
                csvOutFile.write();
                poNumPre = poNum;
                if(lineNum == null){
                    poLineNumPre = "";
                } else {
                    poLineNumPre = lineNum;
                }
            } while (rs.next());
            csvOutFile.close();

            return true;
        }

        private String[] getCsvHdrColum() {
            List<String> csvHeaderList = new ArrayList<String>();
            csvHeaderList.add(CSV_HDR_1);
            csvHeaderList.add(CSV_HDR_2);
            csvHeaderList.add(CSV_HDR_3);
            csvHeaderList.add(CSV_HDR_4);
            csvHeaderList.add(CSV_HDR_5);
            csvHeaderList.add(CSV_HDR_6);
            csvHeaderList.add(CSV_HDR_7);
            csvHeaderList.add(CSV_HDR_8);
            csvHeaderList.add(CSV_HDR_9);
            csvHeaderList.add(CSV_HDR_10);
            csvHeaderList.add(CSV_HDR_11);
            csvHeaderList.add(CSV_HDR_12);
            csvHeaderList.add(CSV_HDR_13);
            csvHeaderList.add(CSV_HDR_14);
            csvHeaderList.add(CSV_HDR_15);
            csvHeaderList.add(CSV_HDR_16);
            csvHeaderList.add(CSV_HDR_17);
            csvHeaderList.add(CSV_HDR_18);
            csvHeaderList.add(CSV_HDR_19);
            csvHeaderList.add(CSV_HDR_20);
            csvHeaderList.add(CSV_HDR_21);
            csvHeaderList.add(CSV_HDR_22);
            csvHeaderList.add(CSV_HDR_23);
            csvHeaderList.add(CSV_HDR_24);
            csvHeaderList.add(CSV_HDR_25);
            csvHeaderList.add(CSV_HDR_26);
            csvHeaderList.add(CSV_HDR_27);
            csvHeaderList.add(CSV_HDR_28);
            csvHeaderList.add(CSV_HDR_29);
            csvHeaderList.add(CSV_HDR_30);
            csvHeaderList.add(CSV_HDR_31);
            csvHeaderList.add(CSV_HDR_32);
            csvHeaderList.add(CSV_HDR_33);
            csvHeaderList.add(CSV_HDR_34);
            csvHeaderList.add(CSV_HDR_35);
            csvHeaderList.add(CSV_HDR_36);
            csvHeaderList.add(CSV_HDR_37);
            csvHeaderList.add(CSV_HDR_38);
            csvHeaderList.add(CSV_HDR_39);
            csvHeaderList.add(CSV_HDR_40);
            csvHeaderList.add(CSV_HDR_41);
            csvHeaderList.add(CSV_HDR_42);
            csvHeaderList.add(CSV_HDR_43);
            csvHeaderList.add(CSV_HDR_44);
            csvHeaderList.add(CSV_HDR_45);
            csvHeaderList.add(CSV_HDR_46);
            csvHeaderList.add(CSV_HDR_47);
            csvHeaderList.add(CSV_HDR_48);
            csvHeaderList.add(CSV_HDR_49);
            csvHeaderList.add(CSV_HDR_51);
            csvHeaderList.add(CSV_HDR_52);
            // START 2018/01/31 K.Mishiro [QC#22521),ADD]
            csvHeaderList.add(CSV_HDR_53);
            csvHeaderList.add(CSV_HDR_54);
            csvHeaderList.add(CSV_HDR_55);
            csvHeaderList.add(CSV_HDR_56);
            csvHeaderList.add(CSV_HDR_57);
            csvHeaderList.add(CSV_HDR_58);
            // END 2018/01/31 K.Mishiro [QC#22521,ADD]
            // add start 2022/05/19 QC#57934
            csvHeaderList.add(CSV_HDR_59);
            csvHeaderList.add(CSV_HDR_60);
            // add end 2022/05/19 QC#57934
            // START 2023/02/09 TZ.Win [QC#60966, ADD]
            csvHeaderList.add(CSV_HDR_61);
            // END 2023/02/09 TZ.Win [QC#60966, ADD]
            String[] csvHeader = (String[]) csvHeaderList.toArray(new String[csvHeaderList.size()]);
            return csvHeader;
        }

        private void setCsvData(NPAL1510F00FMsg fMsg, ResultSet rs, String poNumPre, String poNum, String poLineNumPre, String dispLineNum) throws SQLException {
            if (!poNumPre.equals(poNum)) {
                // Header
                ZYPEZDItemValueSetter.setValue(fMsg.poOrdNum_A1, rs.getString(RS_PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.dsPoTpDescTxt_A1, rs.getString(RS_DS_PO_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(fMsg.poHdrStsDescTxt_A1, rs.getString(RS_PO_HDR_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(fMsg.poApvlStsDescTxt_A1, rs.getString(RS_PO_APVL_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCustLocNm_A1, rs.getString(RS_SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.destRtlWhCd_A1, rs.getString(RS_DEST_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt_A1, rs.getString(RS_SHPG_SVC_LVL_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum_A1, rs.getString(RS_PRCH_REQ_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.trxRefNum_A1, rs.getString(RS_TRX_REF_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.origPoOrdNum_A1, rs.getString(RS_ORIG_PO_ORD_NUM));
            }
            // Detail
            // START 2018/01/31 K.Mishiro [QC#22521),ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndCd_A1, rs.getString(RS_PRNT_VND_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndNm_A1, rs.getString(RS_PRNT_VND_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndCd_A1, rs.getString(RS_VND_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.vndNm_A1, rs.getString(RS_VND_NM));
            // END 2018/01/31 K.Mishiro [QC#22521,ADD]
            if (!poNumPre.equals(poNum) || !poLineNumPre.equals(dispLineNum)) {
                if (ZYPCommonFunc.hasValue(dispLineNum)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.dispPoDtlLineNum_A1, dispLineNum);
                }else {
                    fMsg.dispPoDtlLineNum_A1.clear();
                }
            }else {
                fMsg.dispPoDtlLineNum_A1.clear();
            }

            ZYPEZDItemValueSetter.setValue(fMsg.poLineTpDescTxt_A1, rs.getString(RS_PO_LINE_TP_DESC_TXT));

            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, rs.getString(RS_MDSE_CD_A0));
            ZYPEZDItemValueSetter.setValue(fMsg.xxMdseCd_A1, rs.getString(RS_XX_MDSE_CD_A0));

            ZYPEZDItemValueSetter.setValue(fMsg.aslMdseCd_A1, rs.getString(RS_ASL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, rs.getString(RS_MDSE_DESC_SHORT_TXT));
            // START 2018/01/31 K.Mishiro [QC#22521),ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd_A1, rs.getString(RS_COA_MDSE_TP_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd_A1, rs.getString(RS_COA_PROD_CD));
            // END 2018/01/31 K.Mishiro [QC#22521,ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.entDealNetUnitPrcAmt_A1, rs.getBigDecimal(RS_ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.poDispQty_A1, rs.getBigDecimal(RS_PO_DISP_QTY));
            // QC#21944 MOD START
            ZYPEZDItemValueSetter.setValue(fMsg.poSubmtDt_A1, rs.getString(RS_PO_SUBMT_DT));
            // QC#21944 MOD END
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlSwhCd_A1, rs.getString(RS_RQST_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.poMatchTpDescTxt_A1, rs.getString(RS_PO_MATCH_TP_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.poLineStsDescTxt_A1, rs.getString(RS_PO_LINE_STS_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.entPoDtlDealNetAmt_A1, rs.getBigDecimal(RS_ENT_PO_DTL_DEAL_NET_AMT));
            String poMdseCmpsnTpCd = rs.getString(RS_PO_MDSE_CMPSN_TP_CD);
            if (!(ZYPConstant.FLG_ON_1.equals(poMdseCmpsnTpCd))) {
                ZYPEZDItemValueSetter.setValue(fMsg.poRcvQty_A1, rs.getBigDecimal(RS_RCV_PO_QTY));
                ZYPEZDItemValueSetter.setValue(fMsg.poInvQty_A1, rs.getBigDecimal(RS_PO_INV_QTY));
                ZYPEZDItemValueSetter.setValue(fMsg.poCancQty_A1, rs.getBigDecimal(RS_PO_CANC_QTY));
                // add start 2022/05/19 QC#57934
                ZYPEZDItemValueSetter.setValue(fMsg.poRcvQty_WO, rs.getBigDecimal(RS_RCV_WO_QTY));
                ZYPEZDItemValueSetter.setValue(fMsg.poInvQty_WO, rs.getBigDecimal(RS_INV_WO_QTY));
                // add end 2022/05/19 QC#57934
            }
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineNum_A1, rs.getString(RS_PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.trxRefLineNum_A1, rs.getString(RS_TRX_REF_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.origDispPoDtlLineNum_A1, rs.getString(RS_ORIG_DISP_PO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.poAckNum_A1, rs.getString(RS_PO_ACK_NUM));
            // QC#21944 MOD START
            ZYPEZDItemValueSetter.setValue(fMsg.poSendDt_A1, rs.getString(RS_PO_SEND_DT_A1));
            // QC#21944 MOD END
            ZYPEZDItemValueSetter.setValue(fMsg.vndPoAckStsCd_A1, rs.getString(RS_VND_PO_ACK_STS_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.vndPoAckStsDescTxt_A1, rs.getString(RS_VND_PO_ACK_STS_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDt10Dt_A1, rs.getString(RS_ORD_LAST_UPD_TS));
            ZYPEZDItemValueSetter.setValue(fMsg.ordQty_A1, rs.getBigDecimal(RS_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(fMsg.xxTotAmt_A1, rs.getBigDecimal(RS_XX_TOT_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.etdDt_A1, rs.getString(RS_ETD_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.etaDt_A1, rs.getString(RS_ETA_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.vndOtbdCarrNm_A1, rs.getString(RS_VND_OTBD_CARR_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.proNum_A1, rs.getString(RS_PRO_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndSoNum_A1, rs.getString(RS_VND_SO_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndSoSlpNum_A1, rs.getString(RS_VND_SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndInvtyLocCd_A1, rs.getString(RS_VND_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.vndShipToCustLocNm_A1, rs.getString(RS_VND_SHIP_TO_CUST_LOC_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndSellToCustLocNm_A1, rs.getString(RS_VND_SELL_TO_CUST_LOC_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.thisMthFobCostAmt_A1, rs.getBigDecimal(RS_THIS_MTH_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.vndChildBomPrcAmt_A1, rs.getBigDecimal(RS_VND_CHILD_BOM_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.vndCpoOrdNum_A1, rs.getString(RS_VND_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndIssPoOrdNum_A1, rs.getString(RS_VND_ISS_PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd, rs.getString(RS_SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.vndPoAckLineStsTxt_A1, rs.getString(RS_VND_PO_ACK_LINE_STS_TXT));
            // START 2023/02/09 TZ.Win [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.rqstShipDt_A1, rs.getString(RS_RQST_SHIP_DT));
            // END 2023/02/09 TZ.Win [QC#60966, ADD]
        }
    }
}
