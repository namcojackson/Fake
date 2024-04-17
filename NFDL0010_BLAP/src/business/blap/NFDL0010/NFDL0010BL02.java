package business.blap.NFDL0010;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0010.common.NFDL0010CommonLogic;
import business.blap.NFDL0010.constant.NFDL0010Constant;
import business.file.NFDL0010F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/04/20   Fujitsu         C.Naito         Update          QC#7218
 * 2016/04/27   Fujitsu         S.Fujita        Update          QC#7218
 * 2016/06/20   Hitachi         K.Kojima        Update          QC#10147
 * 2016/06/21   Hitachi         K.Kojima        Update          QC#10182
 * 2016/06/29   Hitachi         K.Kojima        Update          QC#10166
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#11417
 * 2016/08/29   Hitachi         K.Kojima        Update          QC#10786
 * 2017/01/19   Hitachi         E.Kameishi      Update          QC#16808
 * 2017/07/31   Hitachi         T.Tsuchida      Update          QC#19574
 * 2018/05/16   CITS            S.Katsuma       Update          QC#24793
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26679
 * 2018/07/19   Hitachi         Y.Takeno        Update          QC#26679
 * 2018/07/30   Fujitsu         Y.Matsui        Update          QC#27081
 * 2018/08/27   Hitachi         Y.Takeno        Update          QC#27603
 * 2018/10/30   Hitachi         J.Kim           Update          QC#28937
 * 2019/10/23   Fujitsu         S.Takami        Update          QC#53536
 *</pre>
 */
public class NFDL0010BL02 extends S21BusinessHandler implements NFDL0010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0010_INIT".equals(screenAplID)) {
                doProcess_NFDL0010_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0010CMsg) cMsg, (NFDL0010SMsg) sMsg);
            } else if ("NFDL0010Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFDL0010Scrn00_CMN_Reset(cMsg, sMsg);

            } else if ("NFDL0010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0010Scrn00_PageNext(cMsg, sMsg);

            } else if ("NFDL0010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0010Scrn00_PagePrev(cMsg, sMsg);

            } else if ("NFDL0010Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFDL0010Scrn00_PageJump(cMsg, sMsg);

            } else if ("NFDL0010Scrn00_OnClick_Search".equals(screenAplID)) {
                doProcess_NFDL0010Scrn00_Search(cMsg, sMsg);

            } else if ("NFDL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0010Scrn00_TBLColumnSort(cMsg, sMsg);

            // START 2018/05/16 S.Katsuma [QC#24793,ADD]
            } else if ("NFDL0010Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0010Scrn00_CMN_Download(cMsg, sMsg);

            // END 2018/05/16 S.Katsuma [QC#24793,ADD]
            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0010_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0010_INIT================================START", this);

        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010SMsg globalMsg = (NFDL0010SMsg) sMsg;

        // START 2017/07/31 T.Tsuchida [QC#19574,MOD]
        bizMsg.cltDispTpCd_H.clear();
        // START 2018/07/11 [QC#26679, ADD]
        bizMsg.xxModeCd_H.clear();
        // END   2018/07/11 [QC#26679, ADD]
        bizMsg.xxQueryFltrTxt_H1.clear();
        bizMsg.cltPsnNm_H1.clear();
        bizMsg.xxQueryFltrTxt_H2.clear();
        bizMsg.dsAcctNm_H.clear();
        bizMsg.xxChkBox_H.clear();
        // START 2018/08/27 [QC#27603, ADD]
        bizMsg.xxChkBox_H.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H, ZYPConstant.CHKBOX_ON_Y);
        // END   2018/08/27 [QC#27603, ADD]
        // END 2017/07/31 T.Tsuchida [QC#19574,MOD]
        // START 2018/07/19 [QC#26679, ADD]
        bizMsg.cltPtfoNm_H.clear();
        // END   2018/07/19 [QC#26679, ADD]

        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        ZYPTableUtil.clear(bizMsg.A);

        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);

        ZYPCodeDataUtil.createPulldownList(CLT_DISP_TP.class, bizMsg.cltDispTpCd_LC, bizMsg.cltDispTpDescTxt_LD, ":");

        // START 2018/07/11 [QC#26679, ADD]
        bizMsg.xxModeCd_LC.clear();
        bizMsg.xxModeNm23Txt_LD.clear();
        bizMsg.xxModeCd_LC.no(0).setValue(SEARCH_MODE_CD_LOOKUP);
        bizMsg.xxModeCd_LC.no(1).setValue(SEARCH_MODE_CD_SUMMARY);
        bizMsg.xxModeNm23Txt_LD.no(0).setValue(SEARCH_MODE_NM_LOOKUP);
        bizMsg.xxModeNm23Txt_LD.no(1).setValue(SEARCH_MODE_NM_SUMMARY);
        // START 2018/07/30 [QC#27081, MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_H, SEARCH_MODE_CD_SUMMARY);
        // END   2018/07/30 [QC#27081, MOD]
        // END   2018/07/11 [QC#26679, ADD]

        EZDDebugOutput.println(1, "doProcess_NFDL0010_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0010Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_CMN_Reset================================START", this);

        doProcess_NFDL0010_INIT(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_CMN_Reset================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0010Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Next=====================================START", this);

        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010SMsg globalMsg = (NFDL0010SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length();
        int endCnt = pagenationFrom + bizMsg.A.length();

        for (int i = pagenationFrom; i < endCnt; i++) {

            if (i < globalMsg.A.getValidCount()) {

                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);

            } else {
                break;
            }
        }

        ZYPTableUtil.clear(bizMsg.A);

        pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int cnt = 0;
        endCnt = pagenationFrom + bizMsg.A.length();
        for (int i = pagenationFrom; i < endCnt; i++) {

            if (i < globalMsg.A.getValidCount()) {

                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
                cnt = i;

            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(cnt + 1 - pagenationFrom);

        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Next======================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0010Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Prev======================================START", this);

        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010SMsg globalMsg = (NFDL0010SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() + bizMsg.A.length();
        int endCnt = pagenationFrom + bizMsg.A.length();

        for (int i = pagenationFrom; i < endCnt; i++) {

            if (i < globalMsg.A.getValidCount()) {

                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);

            } else {
                break;
            }
        }

        ZYPTableUtil.clear(bizMsg.A);

        pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int cnt = 0;
        endCnt = pagenationFrom + bizMsg.A.length();

        for (int i = pagenationFrom; i < endCnt; i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
                cnt = i;
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(cnt + 1 - pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Prev==================================END", this);
    }
    //START 2017/01/19 E.Kameishi [QC#16808,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0010Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Jump======================================START", this);

        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010SMsg globalMsg = (NFDL0010SMsg) sMsg;

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for ( ; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());


        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Jump==================================END", this);
    }
    //END 2017/01/19 E.Kameishi [QC#16808,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0010Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Search================================START", this);

        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010SMsg globalMsg = (NFDL0010SMsg) sMsg;
        // START 2017/01/19 E.Kameishi [QC#16808,DEL]
        // START 2016/07/08 K.Kojima [QC#11417,ADD]
        //if (!ZYPCommonFunc.hasValue(bizMsg.cltDispTpCd_H)) {
        //    if(!ZYPCommonFunc.hasValue(bizMsg.xxQueryFltrTxt_H2) && !ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H)) {
        //        bizMsg.xxQueryFltrTxt_H2.setErrorInfo(1, NFCM0850E);
        //        bizMsg.dsAcctNm_H.setErrorInfo(1, NFCM0850E);
        //        return;
        //    }
        //}
        // END 2016/07/08 K.Kojima [QC#11417,ADD]
        // END 2017/01/19 E.Kameishi [QC#16808,DEL]
        getCollectionSummaryList(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_Search================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0010Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010SMsg globalMsg = (NFDL0010SMsg) sMsg;
        
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

            // Copy
            int i = 0;

            for (; i < globalMsg.A.getValidCount(); i++) {

                if (i == bizMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }

            bizMsg.A.setValidCount(i);

            // set Page
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }
    
    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getCollectionSummaryList(NFDL0010CMsg bizMsg, NFDL0010SMsg globalMsg) {

        // START 2018/10/24 J.Kim [QC#28869,ADD]
        NFDL0010CommonLogic.outputSearchLog(bizMsg);
        // END 2018/10/24 J.Kim [QC#28869,ADD]

        Map<String, Object> ssmMap = queryCollectionSummaryList(bizMsg);
        // START 2018/10/24 J.Kim [QC#28937,ADD]
        ssmMap.put("rowNum", String.valueOf(globalMsg.A.length() + 1));
        S21SsmEZDResult ssmResult = NFDL0010Query.getInstance().getCollectionSummaryList(ssmMap, globalMsg);
        // END 2018/10/24 J.Kim [QC#28937,ADD]
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {

                bizMsg.setMessageInfo("NZZM0001W");

                queryResCnt = globalMsg.A.length();
            // START 2016/04/27 S.Fujita [QC#7218,MOD]
//            } else if(queryResCnt == 0) {
//                //[QC#7218] INSERT start 
//                // No search results found. 
//                bizMsg.setMessageInfo("NFDM0034I");
//                //[QC#7218] INSERT end
//            } else {
//                //[QC#7218] UPDATE start 
//                // Search ended normally
//                bizMsg.setMessageInfo("NFDM0033I");
                //[QC#7218] UPDATE end
            // END 2016/04/27 S.Fujita [QC#7218,MOD]
            }

            int i;
            for (i = 0; i < globalMsg.A.getValidCount(); i++) {

                if (i < bizMsg.A.length()) {

                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);

                } else {
                    break;
                }
            }

            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            //[QC#7218] UPDATE start
            // No search results found. 
            bizMsg.setMessageInfo("NFDM0034I");
            //[QC#7218] UPDATE end
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(0);
        }
    }

    // START 2018/05/16 S.Katsuma [QC#24793,ADD]
    /**
     * CMN_Download Event
     * @param bizMsg NFDL0010CMsg
     * @param glblMsg NFDL0010SMsg
     */
    private void doProcess_NFDL0010Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_CMN_Download======================================START", this);

        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;
        NFDL0010SMsg globalMsg = (NFDL0010SMsg) sMsg;

        writeCsvFile(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0010Scrn00_CMN_Download==================================END", this);
    }

    /**
     * writeCsvFile
     * @param bizMsg NFDL0010CMsg
     * @param globalMsg NFDL0010SMsg
     */
    private void writeCsvFile(NFDL0010CMsg bizMsg, NFDL0010SMsg globalMsg) {
        NFDL0010F00FMsg fMsg = new NFDL0010F00FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0010Constant.CSV_NAME), NFDL0010Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        // write header
        csvOutFile.writeHeader(NFDL0010Constant.CSV_HEADER);

        // START 2018/10/30 J.Kim [QC#28937,MOD]
        // NFDL0010SMsg globalMsgBk = new NFDL0010SMsg();;
        // EZDMsg.copy(globalMsg.A, null, globalMsgBk.A, null);
        //
        // S21SsmEZDResult ssmResult = queryCollectionSummaryList(bizMsg, globalMsg);
        // if (ssmResult.isCodeNormal()) {
        //     if (globalMsg.A.getValidCount() >= NFDL0010Constant.CSV_LIMIT_COUNT) {
        //         bizMsg.setMessageInfo("NZZM0001W");
        //     }
        //
        //  for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
        //        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, globalMsg.A.no(i).dsAcctNum_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, globalMsg.A.no(i).dsAcctNm_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.pmtTermDescTxt, globalMsg.A.no(i).pmtTermDescTxt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.dsoAmt_F1, globalMsg.A.no(i).dsoAmt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltPsnNm, globalMsg.A.no(i).cltPsnNm_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltPtfoNm, globalMsg.A.no(i).cltPtfoNm_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.dsoAmt_F2, globalMsg.A.no(i).dsoAmt_A2);
        //        ZYPEZDItemValueSetter.setValue(fMsg.totBalAmt, globalMsg.A.no(i).totBalAmt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.netAmt, globalMsg.A.no(i).netAmt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.dealCltDsptAmt, globalMsg.A.no(i).dealCltDsptAmt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.dealCltPrmsAmt, globalMsg.A.no(i).dealCltPrmsAmt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltPrmsCratDt, globalMsg.A.no(i).cltPrmsCratDt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltPrmsDt, globalMsg.A.no(i).cltPrmsDt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltPrmsBrknFlg, globalMsg.A.no(i).cltPrmsBrknFlg_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm, globalMsg.A.no(i).xxFullNm_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_F1, globalMsg.A.no(i).dsCtacPntValTxt_AT);
        //        // START 2018/07/30 Y.Matsui [QC#27081,ADD]
        //        ZYPEZDItemValueSetter.setValue(fMsg.cratTsDplyTxt, globalMsg.A.no(i).cratTsDplyTxt_A1);
        //        // END   2018/07/30 Y.Matsui [QC#27081,ADD]
        //        ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_F2, globalMsg.A.no(i).dsCtacPntValTxt_AE);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltHdrNoteTxt, globalMsg.A.no(i).cltHdrNoteTxt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem30Txt, globalMsg.A.no(i).xxScrItem30Txt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltStrgyNm, globalMsg.A.no(i).cltStrgyNm_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.cltWrkItemNm, globalMsg.A.no(i).cltWrkItemNm_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.rcptDt, globalMsg.A.no(i).rcptDt_A1);
        //        ZYPEZDItemValueSetter.setValue(fMsg.dealRcptAmt, globalMsg.A.no(i).dealRcptAmt_A1);
        //
        //         csvOutFile.write();
        //     }
        //     csvOutFile.close();
        // } else {
        //     bizMsg.setMessageInfo("NFDM0034I");
        // }
        //
        // EZDMsg.copy(globalMsgBk.A, null, globalMsg.A, null);

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFDL0010Query.getInstance().getClass());

            st = ssmLLClient.createPreparedStatement("getCollectionSummaryList", queryCollectionSummaryList(bizMsg), execParam);
            rs = st.executeQuery();

            boolean noRecord = true;
            while (rs.next()) {

                noRecord = false;
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, rs.getString("DS_ACCT_NUM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.pmtTermDescTxt, rs.getString("PMT_TERM_DESC_TXT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsoAmt_F1, rs.getBigDecimal("DSO_AMT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltPsnNm, rs.getString("CLT_PSN_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltPtfoNm, rs.getString("CLT_PTFO_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsoAmt_F2, rs.getBigDecimal("DSO_AMT_A2"));
                ZYPEZDItemValueSetter.setValue(fMsg.totBalAmt, rs.getBigDecimal("OVD_BAL_AMT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.netAmt, rs.getBigDecimal("NET_AMT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dealCltDsptAmt, rs.getBigDecimal("DEAL_CLT_DSPT_AMT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dealCltPrmsAmt, rs.getBigDecimal("DEAL_CLT_PRMS_AMT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltPrmsCratDt, rs.getString("CLT_PRMS_CRAT_DT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltPrmsDt, rs.getString("CLT_PRMS_DT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltPrmsBrknFlg, rs.getString("CLT_PRMS_BRKN_FLG_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm, rs.getString("CTAC_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_F1, rs.getString("CTAC_TEL_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.cratTsDplyTxt, rs.getString("CRAT_TS_DPLY_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_F2, rs.getString("CTAC_EML_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltHdrNoteTxt, rs.getString("CLT_HDR_NOTE_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem30Txt, rs.getString("XX_SCR_ITEM_30_TXT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltStrgyNm, rs.getString("CLT_STRGY_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltWrkItemNm, rs.getString("CLT_WRK_ITEM_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.rcptDt, rs.getString("RCPT_DT_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dealRcptAmt, rs.getBigDecimal("DEAL_RCPT_AMT_A1"));

                csvOutFile.write();
            }
            csvOutFile.close();

            if (noRecord) {
                bizMsg.setMessageInfo("NFDM0034I");
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(st, rs);
        }
        // END 2018/10/30 J.Kim [QC#28937,MOD]
    }

    private Map<String, Object> queryCollectionSummaryList(NFDL0010CMsg bizMsg) {
        String salesDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("dsAcctNm", bizMsg.dsAcctNm_H.getValue());
        ssmMap.put("salesDt", salesDt);
        ssmMap.put("userId", getContextUserInfo().getUserId());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("invTpCd", AR_TRX_TP.INVOICE);
        ssmMap.put("crmTpCd", AR_TRX_TP.CREDIT_MEMO);
        // START 2016/06/21 K.Kojima [QC#10182,ADD]
        ssmMap.put("rcpTpCd", AR_TRX_TP.RECEIPT);
        // END 2016/06/21 K.Kojima [QC#10182,ADD]
        ssmMap.put("applyStsU", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("applyStsP", AR_CASH_APPLY_STS.PARTIAL);
        // START 2016/06/20 K.Kojima [QC#10147,ADD]
        ssmMap.put("dsCtacPntTpPhoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        ssmMap.put("dsCtacPntTpEmailWork", DS_CTAC_PNT_TP.EMAIL_WORK);
        // END 2016/06/20 K.Kojima [QC#10147,ADD]
        // START 2016/06/29 K.Kojima [QC#10166,ADD]
        ssmMap.put("cltAcctTpCd", "20");
        // END 2016/06/29 K.Kojima [QC#10166,ADD]
        // START 2016/08/29 K.Kojima [QC#10786,ADD]
        // START 2017/07/31 T.Tsuchida [QC#19574,MOD]
        ssmMap.put("arTrxTpCdReceipt", AR_TRX_TP.RECEIPT);
        ssmMap.put("accTpCd", AR_TRX_TP.ON_ACCOUNT);
        ssmMap.put("maxDt", MAX_DT);
        // END 2017/07/31 T.Tsuchida [QC#19574,MOD]
        ssmMap.put("cltDsptStsApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/08/29 K.Kojima [QC#10786,ADD]

        if (ZYPCommonFunc.hasValue(bizMsg.xxQueryFltrTxt_H1)) {
            String[] cltPsnCdList = bizMsg.xxQueryFltrTxt_H1.getValue().split(",");
            ssmMap.put("cltPsnCdList", cltPsnCdList);
        } else {
            ssmMap.put("cltPsnCdList", null);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxQueryFltrTxt_H2)) {
            String[] dsAcctNumList = bizMsg.xxQueryFltrTxt_H2.getValue().split(",");
            ssmMap.put("dsAcctNumList", dsAcctNumList);
        } else {
            ssmMap.put("dsAcctNumList", null);
        }
        // START 2017/07/31 T.Tsuchida [QC#19574,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.cltPtfoNm_H)) {
            bizMsg.cltPtfoNm_H.setValue(bizMsg.cltPtfoNm_H.getValue().toUpperCase());
        }
        // END 2017/07/31 T.Tsuchida [QC#19574,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H)) {
            bizMsg.dsAcctNm_H.setValue(bizMsg.dsAcctNm_H.getValue().toUpperCase());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.cltPsnNm_H1)) {
            bizMsg.cltPsnNm_H1.setValue(bizMsg.cltPsnNm_H1.getValue().toUpperCase());
        }
        // START 2017/08/07 T.Tsuchida [QC#19576,DEL]
//        if (ZYPCommonFunc.hasValue(bizMsg.cltPsnNm_H2)) {
//            bizMsg.cltPsnNm_H2.setValue(bizMsg.cltPsnNm_H2.getValue().toUpperCase());
//        }
//        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnTelNum_H)) {
//            bizMsg.ctacPsnTelNum_H.setValue(bizMsg.ctacPsnTelNum_H.getValue().toUpperCase());
//        }
//        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnEmlAddr_H)) {
//            // START 2016/06/21 K.Kojima [QC#10147,DEL]
//            // bizMsg.ctacPsnEmlAddr_H.setValue(bizMsg.ctacPsnEmlAddr_H.getValue());
//            // END 2016/06/21 K.Kojima [QC#10147,DEL]
//            // START 2016/06/21 K.Kojima [QC#10147,ADD]
//            ssmMap.put("ctacPsnEmlAddr_H", bizMsg.ctacPsnEmlAddr_H.getValue().toUpperCase());
//            // END 2016/06/21 K.Kojima [QC#10147,ADD]
//        }
        // START 2017/08/07 T.Tsuchida [QC#19576,DEL]

        // START 2018/10/30 J.Kim [QC#28937,DEL]
        // ssmMap.put("rowNum", String.valueOf(globalMsg.A.length() + 1));
        // S21SsmEZDResult ssmResult = NFDL0010Query.getInstance().getCollectionSummaryList(ssmMap, globalMsg);
        // END 2018/10/30 J.Kim[QC#28937,DEL]

        // START 2019/10/23 S.Takami [QC#53536,ADD]
        String firstDayOfLastMonth = getFirstDayOfLastMonth(salesDt);
        String lastDayOfLastMonth = getLastDayOfLastMonth(salesDt);
        ssmMap.put("firstDayOfLastMonth", firstDayOfLastMonth);
        ssmMap.put("lastDayOfLastMonth", lastDayOfLastMonth);
        if (ZYPCommonFunc.hasValue(bizMsg.xxQueryFltrTxt_H2) || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H)) {
            ssmMap.put("useAcctForDso", ZYPConstant.FLG_ON_Y);
        } else {
            ssmMap.remove("useAcctForDso");
        }
        // End 2019/10/23 S.Takami [QC#53536,ADD]
        return ssmMap;
    }
    // END 2018/05/16 S.Katsuma [QC#24793,ADD]

    // START 2019/10/23 S.Takami [QC#53536,ADD]
    private String getFirstDayOfLastMonth(String slsDt) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        int iYear = Integer.valueOf(slsDt.substring(0, 4));
        int iMonth = Integer.valueOf(slsDt.substring(4, 6)) - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(iYear, iMonth, 1);

        cal.add(Calendar.MONTH, -1);
        return sdf.format(cal.getTime());
    }

    private String getLastDayOfLastMonth(String slsDt) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        int iYear = Integer.valueOf(slsDt.substring(0, 4));
        int iMonth = Integer.valueOf(slsDt.substring(4, 6)) - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(iYear, iMonth, 1);

        cal.add(Calendar.DATE, -1);
        return sdf.format(cal.getTime());
    }
    // End 2019/10/23 S.Takami [QC#53536,ADD]
}
