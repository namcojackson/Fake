/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1540;

import static business.blap.NWAL1540.constant.NWAL1540Constant.NZZM0001W;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1540.common.NWAL1540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1540BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 * 2016/05/24   SRA             E.Inada         Update          QC#8811
 * 2016/07/25   Fujitsu         Y.Taoka         Update          QC#11636
 * 2016/09/28   Fujitsu         N.Sugiura       Update          QC#12187
 * 2017/10/24   Fujitsu         Y.Kanefusa      Update          S21_NA#21994
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL1540BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1540CMsg bizMsg = (NWAL1540CMsg) cMsg;
            NWAL1540SMsg glblMsg = (NWAL1540SMsg) sMsg;

            if ("NWAL1540_INIT".equals(screenAplID)) {
                doProcess_NWAL1540_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_CMN_ColClear(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_CMN_ColSave(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_CMN_Download(bizMsg, glblMsg);

            // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//            } else if ("NWAL1540Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWAL1540Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NWAL1540Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_CMN_Clear(bizMsg, glblMsg);
            // END 2022/04/18 J.Evangelista [QC#59934,MOD]

            } else if ("NWAL1540Scrn00_OnChange_VrsnNum".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_OnChange_VrsnNum(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_Recalculate".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_Recalculate(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_Search(bizMsg, glblMsg);

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
    private void doProcess_NWAL1540_INIT(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        // QC#8811
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd, bizMsg.xxModeCd_IN);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum, bizMsg.trxHdrNum_IN);

        NWAL1540CommonLogic.createPulldownListForMode(bizMsg);
        NWAL1540CommonLogic.createPulldownListForVrsnNum(bizMsg);

        // 2016/07/25 QC#11636
        NWAL1540CommonLogic.getCreditRebillCd(bizMsg);
        if (CR_REBIL.CREDIT.equals(bizMsg.crRebilCd.getValue()) && ZYPCommonFunc.hasValue(bizMsg.ordPrftVrsnNum_CD.no(1))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ordPrftVrsnNum, bizMsg.ordPrftVrsnNum_CD.no(1));
            search(bizMsg, glblMsg);
            // QC#21994 2017/10/23 Add Start
            //if (0 < glblMsg.A.getValidCount()) {
            //    return;
            //}
            // QC#21994 2017/10/23 Add End
        }

//        if (MODE.ORDER.getValue().equals(bizMsg.xxModeCd_IN.getValue())) {
//            search(bizMsg, glblMsg);
//        }

        NWAL1540CommonLogic.getProfitability(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue(1);
        NWAL1540CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * CMN_ColClear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_CMN_ColClear(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        //
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_CMN_ColSave(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        //
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_CMN_Download(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {

//        ResultSet rs = null;
//        PreparedStatement stmtSelect = null;
//        try {
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(NWAL1540Constant.FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NWAL1540Query.getInstance().getClass());
//            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);
//
//            String statementId = "getCsvInfo";
//            HashMap<String, Object> params = new HashMap<String, Object>();
//            params.put("glblCmpyCd", getGlobalCompanyCode());
//            params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());
//            params.put("ordPrftVrsnNum", bizMsg.ordPrftVrsnNum.getValue());
//            params.put("limitRownum", String.valueOf(MAX_DOWNLOAD_CNT + 1));
//            stmtSelect = ssmLLClient.createPreparedStatement(statementId, params, execParam);
//            rs = stmtSelect.executeQuery();
//            if (!rs.next()) {
//                bizMsg.setMessageInfo(ZZZM9001E, null);
//                return;
//            }
//            NWAL1540CommonLogic.writeCsvFileInfo(bizMsg, rs);
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }

        NWAL1540CommonLogic.writeCsvFileInfo(bizMsg, glblMsg);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//    private void doProcess_NWAL1540Scrn00_CMN_Reset(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
    private void doProcess_NWAL1540Scrn00_CMN_Clear(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
    // END 2022/04/18 J.Evangelista [QC#59934,MOD]

        String wkModeCd = bizMsg.xxModeCd_IN.getValue();
        String wkTrxHdrNum = bizMsg.trxHdrNum_IN.getValue();
        String wkComnColOrdTxt = bizMsg.xxComnColOrdTxt.getValue();

        glblMsg.clear();
        bizMsg.clear();// 2016/03/10 S21_NA#2939
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_IN, wkModeCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_IN, wkTrxHdrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, wkComnColOrdTxt);

        doProcess_NWAL1540_INIT(bizMsg, glblMsg);
    }

    /**
     * OnChange_VrsnNum Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_OnChange_VrsnNum(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        // it same as "search" process.
//        doProcess_NWAL1540Scrn00_Search(bizMsg, glblMsg);// 2016/03/10 S21_NA#2939
        search(bizMsg, glblMsg);
        if (glblMsg.A.getValidCount() > 0) {
            return;
        } else {
            // when choose version number is "Online".
            NWAL1540CommonLogic.getProfitability(bizMsg, glblMsg);
        }

    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_PageJump(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWAL1540CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_PageNext(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1540CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_PagePrev(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1540CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Recalculate Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_Recalculate(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        // 2016/03/24 S21_NA#5958 Add Start
        NWAL1540CommonLogic.createPulldownListForVrsnNum(bizMsg);
        // 2016/09/28 S21_NA#12187 Del Start
//        S21SsmEZDResult ssmResult = NWAL1540Query.getInstance().getMaxVerGrossProfit(bizMsg);
//        if (ssmResult.isCodeNormal()) {
//            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
//            BigDecimal ordPrftVrsnNum = (BigDecimal) resultMap.get("ORD_PRFT_VRSN_NUM");
//            ZYPEZDItemValueSetter.setValue(bizMsg.ordPrftVrsnNum, ordPrftVrsnNum);
//        }

//        bizMsg.altGrsPrftPct_RE.clear();
//        bizMsg.funcAltGrsPrftAmt_RE.clear();
//        bizMsg.altGrsPrftPct_MV.clear();
//        bizMsg.funcAltGrsPrftAmt_MV.clear();
        // 2016/03/24 S21_NA#5958 Add End

//        search(bizMsg, glblMsg);
        // 2016/09/28 S21_NA#12187 Del End
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1540Scrn00_Search(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {

        search(bizMsg, glblMsg);
        NWAL1540CommonLogic.getProfitability(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NWAL1540Query.getInstance().searchHeaderInfo(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem81Txt //
                , NWAL1540CommonLogic.editUserId(bizMsg.xxScrItem81Txt.getValue()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem19Txt //
                , NWAL1540CommonLogic.editTs(bizMsg.xxScrItem19Txt.getValue()));

        ssmResult = NWAL1540Query.getInstance().searchDetailInfo(bizMsg, glblMsg, glblMsg.A.length() + 1);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length() - 1);
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NWAL1540CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

}
