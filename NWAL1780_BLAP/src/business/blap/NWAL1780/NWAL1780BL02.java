/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1780;

import static business.blap.NWAL1780.constant.NWAL1780Constant.BIZ_ID;
import static business.blap.NWAL1780.constant.NWAL1780Constant.ITEMS_HDR_ID;
import static business.blap.NWAL1780.constant.NWAL1780Constant.LIMIT_DOWNLOAD;
import static business.blap.NWAL1780.constant.NWAL1780Constant.NZZM0001W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.ResultSet;
import java.sql.SQLException;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1780.common.NWAL1780CommonLogic;
import business.file.NWAL1780F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Fujitsu         T.Murai         Create          N/A
 * 2016/09/14   Hitachi         T.Mizuki        Update          S21_NA#14249
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2019/08/08   Fujitsu         R.Matsuki       Update          QC#52501
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 * </pre>
 */
public class NWAL1780BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1780CMsg bizMsg = (NWAL1780CMsg) cMsg;
            NWAL1780SMsg glblMsg = (NWAL1780SMsg) sMsg;

            if ("NWAL1780_INIT".equals(screenAplID)) {
                doProcess_NWAL1780_INIT(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_Search(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_OnBlur_DeriveFromCategory".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_PagePrev(bizMsg, glblMsg);
            // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//            } else if ("NWAL1780Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWAL1780Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_CMN_Clear(bizMsg, glblMsg);
            // END 2022/04/18 J.Evangelista [QC#59934,MOD]
            } else if ("NWAL1780_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);
            // mod start 2016/09/14 CSA QC#14249
            } else if ("NWAL1780Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_CMN_Download(bizMsg, glblMsg);
            }
            // mod end 2016/09/14 CSA QC#14249
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (Init)
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     */
    private void doProcess_NWAL1780_INIT(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPGUITableColumn.getColData(bizMsg, glblMsg, ITEMS_HDR_ID);

        // Create Pulldown
        NWAL1780CommonLogic.createPulldown(bizMsg);

        // Setup Search Select Box
        NWAL1780CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
    }

    /**
     * do Process (Search)
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     */
    private void doProcess_NWAL1780Scrn00_Search(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        if (!NWAL1780CommonLogic.searchValidCheck(bizMsg)) {
            return;
        }

        if (!NWAL1780CommonLogic.getQuote(bizMsg, glblMsg)) {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

        int i = 0;
        for (; i < glblMsg.A.getValidCount(); i++) {
            if (i == bizMsg.A.length()) {
                break;
            }
            EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
        }
        bizMsg.A.setValidCount(i);

        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
    }

    /**
     * do Process (OnBlur - Category)
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     */
    private void doProcess_NWAL1780Scrn00_OnBlur_DeriveFromCategory(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        if (!NWAL1780CommonLogic.checkExistCatg(bizMsg)) {
            return;
        }

        // Create Reason Code Pulldown
        bizMsg.dsOrdTpCd.clear();
        NWAL1780CommonLogic.createRsnPullDownForCatg(bizMsg);
    }

    /**
     * do Process (Page Jump)
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     */
    private void doProcess_NWAL1780Scrn00_PageJump(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    /**
     * do Process (Page Next)
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     */
    private void doProcess_NWAL1780Scrn00_PageNext(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    /**
     * do Process (PagePrev)
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     */
    private void doProcess_NWAL1780Scrn00_PagePrev(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

    /**
     * do Process (CMN_Clear)
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     */
    // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//    private void doProcess_NWAL1780Scrn00_CMN_Reset(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {
    private void doProcess_NWAL1780Scrn00_CMN_Clear(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {
    // END 2022/04/18 J.Evangelista [QC#59934,MOD]

        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        doProcess_NWAL1780_INIT(bizMsg, glblMsg);
    }

    /**
     * do Process (OnChangeSavedSearchOption)
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1780Scrn00_OnChangeSavedSearchOption(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NWAL1780CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }
    // mod start 2016/09/14 CSA QC#14249
    // MOD START S21_NA QC#13856
    /**
     * do Process (CMN_Download)
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1780Scrn00_CMN_Download(NWAL1780CMsg cMsg, NWAL1780SMsg sMsg) {

        //try {
            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            if (!NWAL1780CommonLogic.searchValidCheck(cMsg)) {
                return;
            }

            NWAL1780Query.getInstance().getQuoteForCsv(cMsg, sMsg);
//            if (!rs.next()) {
//                cMsg.setMessageInfo(ZZZM9001E, null);
//                return;
//            }
//
//            NWAL1780F00FMsg fMsg = new NWAL1780F00FMsg();
//            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
//            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
//            fMsg.addExclusionItem("xxChkBox_D1");
//            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Write csv file
     * @param bizMsg NWAL1780CMsg
     * @param globalMsg NWAL1780SMsg
     * @param rs S21SsmEZDResult
     * @param fMsg NWAL1780F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    //@SuppressWarnings("unchecked")
    public static  void writeCsvFileForHeaderTab(NWAL1780CMsg bizMsg, NWAL1780SMsg globalMsg, ResultSet rs, NWAL1780F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        //List<Map<String, Object>> resultList = (List<Map<String, Object>>) rs.getResultObject();

        //int i = 0;
        do {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // resultset -> fMsg
            // Map<String, Object> map = resultList.get(i);
            setValue(fMsg.splyQuoteNum_A, rs.getString("SPLY_QUOTE_NUM"));
            setValue(fMsg.cpoOrdNum_A, rs.getString("CPO_ORD_NUM"));
            setValue(fMsg.sellToCustCd_A, rs.getString("SELL_TO_CUST_CD"));
            setValue(fMsg.soldToCustLocCd_A, rs.getString("SOLD_TO_CUST_LOC_CD"));
            setValue(fMsg.dsAcctNm_AO, rs.getString("SOLD_TO_CUST_ACCT_NM"));
            setValue(fMsg.xxAllLineAddr_SO, rs.getString("SOLD_TO_ADDR"));
            setValue(fMsg.shipToCustAcctCd_A, rs.getString("SHIP_TO_CUST_ACCT_CD"));
            setValue(fMsg.shipToCustCd_A, rs.getString("SHIP_TO_CUST_CD"));
            setValue(fMsg.dsAcctNm_AI, rs.getString("SHIP_TO_CUST_ACCT_NM"));
            setValue(fMsg.xxAllLineAddr_SI, rs.getString("SHIP_TO_ADDR"));
            setValue(fMsg.dsOrdCatgDescTxt_A, rs.getString("DS_ORD_CATG_DESC_TXT"));
            setValue(fMsg.dsOrdTpDescTxt_A, rs.getString("DS_ORD_TP_DESC_TXT"));
            setValue(fMsg.splyQuoteDt_A, rs.getString("SPLY_QUOTE_DT"));
            setValue(fMsg.splyQuoteStsDescTxt_A, rs.getString("SPLY_QUOTE_STS_DESC_TXT"));
            setValue(fMsg.custIssPoNum_A, rs.getString("CUST_ISS_PO_NUM"));
            setValue(fMsg.xxPsnNm_A, rs.getString("CREATED_BY"));
            // 2019/08/08 QC#52501 ADD START
            setValue(fMsg.splyQuoteNm_A, rs.getString("SPLY_QUOTE_NM"));
            // 2019/08/08 QC#52501 ADD END
            csvOutFile.write();
        }while(rs.next());
        csvOutFile.close();
    }

    /**
     * @param writeCsvFileHeader
     * @param fMsg
     * @param cMsg
     */
    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NWAL1780F00FMsg fMsg, NWAL1780CMsg cMsg) {
        // 2019/08/08 QC#52501 MOD START
//        final String[] csvHeader = new String[] {"Quote #", "Sold To Acct #", "Sold To Loc #", "Sold To Acct Name", "Sold To Address",
//                "Ship To Acct #", "Ship To Loc #", "Ship To Acct Name", "Ship To Address", "Category", 
//                "Reason", "Quote Date", "Quote Status", "Customer PO #", "Created By", "Order #"};
        final String[] csvHeader = new String[] {"Quote #", "Sold To Acct #", "Sold To Loc #", "Sold To Acct Name", "Sold To Address",
                "Ship To Acct #", "Ship To Loc #", "Ship To Acct Name", "Ship To Address", "Category", 
                "Reason", "Quote Date", "Quote Status", "Customer PO #", "Created By", "Order #", "Quote Name"};
        // 2019/08/08 QC#52501 MOD END
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }
    // MOD END S21_NA QC#13856
    // mod end 2016/09/14 CSA QC#14249
}
