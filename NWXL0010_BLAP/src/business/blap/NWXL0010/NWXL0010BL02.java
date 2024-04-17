/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2010   Fujitsu         K.Tajima        Create          N/A
 * 11/20/2013   Fujitsu         T.Yoshida       Update          DefID:2852
 *</pre>
 */
package business.blap.NWXL0010;

import static business.blap.NWXL0010.common.NWXL0010CommonLogic.checkSqlStatement;
import static business.blap.NWXL0010.common.NWXL0010CommonLogic.getQuery;
import static business.blap.NWXL0010.common.NWXL0010CommonLogic.getUserProfService;
import static business.blap.NWXL0010.common.NWXL0010CommonLogic.toBigDecimal;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWXL0010.NWXL0010Query.EZcancelflag;
import business.blap.NWXL0010.constant.NWXL0010Constant;
import business.db.RPT_SQLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class NWXL0010BL02 extends S21BusinessHandler implements NWXL0010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {

            String scrnAplId = cMsg.getScreenAplID();

            NWXL0010CMsg myCMsg = (NWXL0010CMsg) cMsg;
            NWXL0010SMsg mySMsg = (NWXL0010SMsg) sMsg;

            if (scrnAplId.endsWith("_INIT")) {
                doProcess_INIT(myCMsg, mySMsg);
                return;
            }

            // --------------------------------------------------
            // Scrn00
            // --------------------------------------------------
            if (scrnAplId.contains(ScrnId.NWXL0010Scrn00.toString())) {

                if (scrnAplId.endsWith("_Search")) {
                    doProcess_Scrn00_Search(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_PageNext")) {
                    doProcess_Scrn00_PageNext(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_PagePrev")) {
                    doProcess_Scrn00_PagePrev(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_PageJump")) {
                    doProcess_Scrn00_PageJump(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_TBLColumnSort")) {
                    doProcess_Scrn00_TBLColumnSort(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_NewReport")) {
                    doProcess_Scrn00_NewReport(myCMsg);
                    return;
                }
                if (scrnAplId.endsWith("_History")) {
                    doProcess_Scrn00_History(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_ReportDetail")) {
                    doProcess_Scrn00_ReportDetail(myCMsg);
                    return;
                }
                if (scrnAplId.endsWith("_CMN_Download")) {
                    doProcess_Scrn00_CMN_Download(myCMsg);
                    return;
                }
            }

            // --------------------------------------------------
            // Scrn01
            // --------------------------------------------------
            if (scrnAplId.contains(ScrnId.NWXL0010Scrn01.toString())) {

                if (scrnAplId.endsWith("_CMN_Submit")) {
                    doProcess_Scrn01_CMN_Submit(myCMsg);
                    return;
                }
                if (scrnAplId.endsWith("_CMN_Download")) {
                    doProcess_Scrn01_CMN_Download(myCMsg);
                    return;
                }
                if (scrnAplId.endsWith("_CMN_Delete")) {
                    doProcess_Scrn01_CMN_Delete(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_CMN_Return")) {
                    doProcess_Scrn01_CMN_Return(myCMsg, mySMsg);
                    return;
                }
            }
            
            // --------------------------------------------------
            // Scrn02
            // --------------------------------------------------
            if (scrnAplId.contains(ScrnId.NWXL0010Scrn02.toString())) {
                
                if (scrnAplId.endsWith("_PageNext")) {
                    doProcess_Scrn02_PageNext(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_PagePrev")) {
                    doProcess_Scrn02_PagePrev(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_PageJump")) {
                    doProcess_Scrn02_PageJump(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_TBLColumnSort")) {
                    doProcess_Scrn02_TBLColumnSort(myCMsg, mySMsg);
                    return;
                }
                if (scrnAplId.endsWith("_CMN_Return")) {
                    doProcess_Scrn02_CMN_Return(myCMsg, mySMsg);
                    return;
                }
            }
            

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_INIT(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        doProcess_Scrn00_Search(cMsg, sMsg);
    }
    
    /**
     * Scrn00_CMN_Download.
     * 
     * @param cMsg  NWXL0010CMsg
     */
    private void doProcess_Scrn00_CMN_Download(NWXL0010CMsg cMsg) {

        final NWXL0010_ACMsg selectedLineCMsg = cMsg.A.no(cMsg.xxRadioBtn_A.getValueInt());
            
        // --------------------------------------------------
        // create CSV file.
        // --------------------------------------------------
        final RPT_SQLTMsg rptSqlTMsg = new RPT_SQLTMsg();
        setValue(rptSqlTMsg.glblCmpyCd,    getGlobalCompanyCode());
        setValue(rptSqlTMsg.rptSqlId,      selectedLineCMsg.rptSqlId_A);
        setValue(rptSqlTMsg.rptSqlNm,      selectedLineCMsg.rptSqlNm_A);
        setValue(rptSqlTMsg.rptSqlDescTxt, selectedLineCMsg.rptSqlDescTxt_A);
        
        final boolean isNormalEnd = getQuery().createCsvFile(cMsg.xxFileData, rptSqlTMsg);
        if (!isNormalEnd) {
            cMsg.setMessageInfo(MessageId.NZZM0000E.toString());
            return;
        }

        // --------------------------------------------------
        // write Biz Process Log.
        // --------------------------------------------------
        final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
        bizLogMsg.setSubSysId(MY_BIZ_ID);
        bizLogMsg.setProcId(MY_BIZ_ID);
        bizLogMsg.setEventId(BIZ_LOG_EVENT_ID);
        bizLogMsg.setPrntDocId(rptSqlTMsg.rptSqlId.getValue());
        bizLogMsg.setDocId(rptSqlTMsg.rptSqlId.getValue());
        bizLogMsg.setBizProcCmntTxt_01(rptSqlTMsg.rptSqlNm.getValue());
        bizLogMsg.setBizProcCmntTxt_02(getUserProfService().getContextUserInfo().getUserId());
        bizLogMsg.setBizProcCmntTxt_03(ZYPDateUtil.getSalesDate());
        S21BusinessProcessLog.print(bizLogMsg);
    }

    /**
     * Scrn00_History.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn00_History(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        final NWXL0010_BSMsgArray sMsgArray = sMsg.B;
        final NWXL0010_BCMsgArray cMsgArray = cMsg.B;

        // --------------------------------------------------
        // search RPT_SQL list.
        // --------------------------------------------------
        final NWXL0010_ACMsg selectedLineCMsg = cMsg.A.no(cMsg.xxRadioBtn_A.getValueInt());
        final S21SsmEZDResult ssmRes = getQuery().getRptDownloadHistoryList(selectedLineCMsg, sMsgArray);

        int resCnt = ssmRes.getQueryResultCount();
        if (resCnt == 0) {
            cMsg.setMessageInfo(MessageId.NZZM0000E.toString());
        } else {
            if (resCnt > sMsgArray.length()) {
                cMsg.setMessageInfo(MessageId.NZZM0001W.toString());
                resCnt = sMsgArray.length();
            }
        }

        // set data from 'SMsg' to 'CMsg'.
        EZDMsg.copy(sMsgArray, null, cMsgArray, null);
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(resCnt);
    }

    /**
     * Scrn00_NewReport.
     * 
     * @param cMsg  NWXL0010CMsg
     */
    private void doProcess_Scrn00_NewReport(NWXL0010CMsg cMsg) {

        cMsg.rptSqlId_01.clear();
        cMsg.rptSqlNm_01.clear();
        cMsg.rptSqlFuncId_01.clear();
        cMsg.rptSqlDataTmgTxt_01.clear();
        cMsg.rptSqlReqPsnCd_01.clear();
        cMsg.rptSqlReqDt_01.clear();
        cMsg.rptSqlRgtnPsnCd_01.clear();
        setValue(cMsg.rptSqlRgtnDt_01, ZYPDateUtil.getSalesDate());
        cMsg.rptSqlDescTxt_01.clear();
        cMsg.xxRptSqlTxt_01.clear();

        // --------------------------------------------------
        // create Pull-down list.
        // --------------------------------------------------
        createRptSqlFuncIdPullDown(cMsg);
        createRptSqlDataTmgTxtPullDown(cMsg);
    }
    
    /**
     * Scrn00_PageJump.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn00_PageJump(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        NWXL0010_ACMsgArray cMsgArray = cMsg.A;
        ZYPTableUtil.clear(cMsgArray);

        // --------------------------------------------------
        // Page Jump.
        // --------------------------------------------------
        NWXL0010_ASMsgArray sMsgArray = sMsg.A;
        EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
        EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

        int pageFrom = pageShowFromItem.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsgArray.setValidCount(i - pageFrom);

        setValue(pageShowFromItem, toBigDecimal(pageFrom + 1));
        setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount()));        
    }

    /**
     * Scrn00_PageNext.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn00_PageNext(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        NWXL0010_ACMsgArray cMsgArray = cMsg.A;
        ZYPTableUtil.clear(cMsgArray);

        // --------------------------------------------------
        // Next Page.
        // --------------------------------------------------
        NWXL0010_ASMsgArray sMsgArray = sMsg.A;
        EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
        EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

        setValue(pageShowFromItem, pageShowToItem);

        int pageFrom = pageShowFromItem.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsgArray.length(); i++) {
            if (i == sMsgArray.getValidCount()) {
                break;
            }
            EZDMsg.copy(sMsgArray.no(i), null, cMsgArray.no(i - pageFrom), null);
        }
        cMsgArray.setValidCount(i - pageFrom);

        setValue(pageShowFromItem, toBigDecimal(pageFrom + 1));
        setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount()));        
    }

    /**
     * Scrn00_PagePrev.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn00_PagePrev(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        NWXL0010_ACMsgArray cMsgArray = cMsg.A;
        ZYPTableUtil.clear(cMsgArray);

        // --------------------------------------------------
        // Prev Page.
        // --------------------------------------------------
        NWXL0010_ASMsgArray sMsgArray = sMsg.A;
        EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
        EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

        setValue(pageShowFromItem, toBigDecimal(pageShowFromItem.getValueInt() - cMsgArray.length() - 1));

        int pageFrom = pageShowFromItem.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsgArray.length(); i++) {
            if (i == sMsgArray.getValidCount()) {
                break;
            }
            EZDMsg.copy(sMsgArray.no(i), null, cMsgArray.no(i - pageFrom), null);
        }
        cMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        setValue(pageShowFromItem, toBigDecimal(pageFrom));
        setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount() - 1));        
    }

    /**
     * Scrn00_ReportDetail.
     * 
     * @param cMsg  NWXL0010CMsg
     */
    private void doProcess_Scrn00_ReportDetail(NWXL0010CMsg cMsg) {

        // back-up selected 'RPT_SQL_ID'.
        final String rptSqlId_01 = cMsg.rptSqlId_01.getValue();
        
        doProcess_Scrn00_NewReport(cMsg);

        setValue(cMsg.rptSqlId_01, rptSqlId_01);
        
        // --------------------------------------------------
        // search selected RPT_SQL.
        // --------------------------------------------------
        final boolean isNormalEnd = getRptSqlInfo(cMsg, NWXL0010Query.EZcancelflag.AVAILABLE);
        
        if (!isNormalEnd) {
            cMsg.setMessageInfo(MessageId.NZZM0000E.toString());
            return;
        }
    }

    /**
     * Scrn00_Search.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn00_Search(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        final NWXL0010_ASMsgArray sMsgArray = sMsg.A;
        final NWXL0010_ACMsgArray cMsgArray = cMsg.A;

        // --------------------------------------------------
        // search RPT_SQL list.
        // --------------------------------------------------
        final S21SsmEZDResult ssmRes = getQuery().getRptSqlInfoList(cMsg, sMsgArray);

        int resCnt = ssmRes.getQueryResultCount();
        if (resCnt == 0) {
            if (!cMsg.getScreenAplID().endsWith("_INIT") && !cMsg.getScreenAplID().endsWith("_CMN_Return")) {
                cMsg.setMessageInfo(MessageId.NZZM0000E.toString());
            }
        } else {
            if (resCnt > sMsgArray.length()) {
                cMsg.setMessageInfo(MessageId.NZZM0001W.toString());
                resCnt = sMsgArray.length();
            }
        }

        // set data from 'SMsg' to 'CMsg'.
        EZDMsg.copy(sMsgArray, null, cMsgArray, null);
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(resCnt);
    }

    /**
     * Scrn00_TBLColumnSort.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn00_TBLColumnSort(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {
        
        String sortTblNm  = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            final S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents()), sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            setValue(cMsg.xxPageShowFromNum, toBigDecimal(1));
            setValue(cMsg.xxPageShowToNum,   toBigDecimal(cMsg.A.getValidCount()));
        }
    }

    /**
     * Scrn01_CMN_Delete.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn01_CMN_Delete(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        // --------------------------------------------------
        // search RPT_SQL. (EZCANCELFLAG = '1')
        // --------------------------------------------------
        getRptSqlInfo(cMsg, NWXL0010Query.EZcancelflag.UN_AVAILABLE);
        
        // set 'XX_SCR_EVENT_NM' for GUI control.
        setValue(cMsg.xxScrEventNm, cMsg.getScreenAplID());
    }

    /**
     * Scrn01_CMN_Download.
     * 
     * @param cMsg  NWXL0010CMsg
     */
    private void doProcess_Scrn01_CMN_Download(NWXL0010CMsg cMsg) {

        final EZDCStringItem xxRptSqlTxt = cMsg.xxRptSqlTxt_01;
        
        // --------------------------------------------------
        // SQL Structure Check.
        // --------------------------------------------------
        boolean isNoemalEnd = checkSqlStatement(xxRptSqlTxt);
        if (!isNoemalEnd) {
            return;
        }
        
        // --------------------------------------------------
        // create CSV file.
        // --------------------------------------------------
        final RPT_SQLTMsg rptSqlTMsg = new RPT_SQLTMsg();
        setValue(rptSqlTMsg.glblCmpyCd,    getGlobalCompanyCode());
        setValue(rptSqlTMsg.rptSqlId,      cMsg.rptSqlId_01);
        setValue(rptSqlTMsg.rptSqlNm,      cMsg.rptSqlNm_01);
        setValue(rptSqlTMsg.rptSqlDescTxt, cMsg.rptSqlDescTxt_01);
        
        final boolean isNormalEnd = getQuery().createCsvFile(cMsg.xxFileData, rptSqlTMsg, xxRptSqlTxt.getValue());
        if (!isNormalEnd) {
            cMsg.setMessageInfo(MessageId.NZZM0000E.toString());
            return;
        }
    }

    /**
     * Scrn01_CMN_Return.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn01_CMN_Return(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        doProcess_Scrn00_Search(cMsg, sMsg);
    }

    /**
     * Scrn01_CMN_Submit.
     * 
     * @param cMsg  NWXL0010CMsg
     */
    private void doProcess_Scrn01_CMN_Submit(NWXL0010CMsg cMsg) {
        
        // --------------------------------------------------
        // search RPT_SQL.
        // --------------------------------------------------
        getRptSqlInfo(cMsg, EZcancelflag.AVAILABLE);
   }

    /**
     * Scrn02_CMN_Return.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn02_CMN_Return(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        doProcess_Scrn00_Search(cMsg, sMsg);
    }
    
    /**
     * Scrn02_PageJump.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn02_PageJump(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        NWXL0010_BCMsgArray cMsgArray = cMsg.B;
        ZYPTableUtil.clear(cMsgArray);

        // --------------------------------------------------
        // Page Jump.
        // --------------------------------------------------
        NWXL0010_BSMsgArray sMsgArray = sMsg.B;
        EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
        EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

        int pageFrom = pageShowFromItem.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsgArray.setValidCount(i - pageFrom);

        setValue(pageShowFromItem, toBigDecimal(pageFrom + 1));
        setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount()));        
    }

    /**
     * Scrn02_PageNext.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn02_PageNext(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        NWXL0010_BCMsgArray cMsgArray = cMsg.B;
        ZYPTableUtil.clear(cMsgArray);

        // --------------------------------------------------
        // Next Page.
        // --------------------------------------------------
        NWXL0010_BSMsgArray sMsgArray = sMsg.B;
        EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
        EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

        setValue(pageShowFromItem, pageShowToItem);

        int pageFrom = pageShowFromItem.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsgArray.length(); i++) {
            if (i == sMsgArray.getValidCount()) {
                break;
            }
            EZDMsg.copy(sMsgArray.no(i), null, cMsgArray.no(i - pageFrom), null);
        }
        cMsgArray.setValidCount(i - pageFrom);

        setValue(pageShowFromItem, toBigDecimal(pageFrom + 1));
        setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount()));        
    }

    /**
     * Scrn02_PagePrev.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn02_PagePrev(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {

        NWXL0010_BCMsgArray cMsgArray = cMsg.B;
        ZYPTableUtil.clear(cMsgArray);

        // --------------------------------------------------
        // Prev Page.
        // --------------------------------------------------
        NWXL0010_BSMsgArray sMsgArray = sMsg.B;
        EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
        EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

        setValue(pageShowFromItem, toBigDecimal(pageShowFromItem.getValueInt() - cMsgArray.length() - 1));

        int pageFrom = pageShowFromItem.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsgArray.length(); i++) {
            if (i == sMsgArray.getValidCount()) {
                break;
            }
            EZDMsg.copy(sMsgArray.no(i), null, cMsgArray.no(i - pageFrom), null);
        }
        cMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        setValue(pageShowFromItem, toBigDecimal(pageFrom));
        setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount() - 1));        
    }

    /**
     * Scrn02_TBLColumnSort.
     * 
     * @param cMsg  NWXL0010CMsg
     * @param sMsg  NWXL0010SMsg
     */
    private void doProcess_Scrn02_TBLColumnSort(NWXL0010CMsg cMsg, NWXL0010SMsg sMsg) {
        
        String sortTblNm  = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

        // Table:B
        if ("B".equals(sortTblNm)) {

            // execute column sort function
            final S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents()), sortKey, 0, sMsg.B.getValidCount());

            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            setValue(cMsg.xxPageShowFromNum, toBigDecimal(1));
            setValue(cMsg.xxPageShowToNum,   toBigDecimal(cMsg.B.getValidCount()));
        }
    }

    private static void createRptSqlDataTmgTxtPullDown(NWXL0010CMsg cMsg) {

        final EZDCStringItemArray rptSqlDataTmgTxt_CL = cMsg.rptSqlDataTmgTxt_CL;
        final EZDCStringItemArray rptSqlDataTmgTxt_NL = cMsg.rptSqlDataTmgTxt_NL;
        rptSqlDataTmgTxt_CL.clear();
        rptSqlDataTmgTxt_NL.clear();

        for (int i = 0; i < RptSqlDataTmgTxt.values().length; i++) {
            if (i >= rptSqlDataTmgTxt_CL.length()) {
                break;
            }
            final String rptSqlDataTmgTxt = RptSqlDataTmgTxt.values()[i].getTxt();
            setValue(rptSqlDataTmgTxt_CL.no(i), rptSqlDataTmgTxt);

            // START 2013/11/20 T.Yoshida [Defect#2852 Mod]
            EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
            setValue(rptSqlDataTmgTxt_NL.no(i), converter.convLabel2i18nLabel("", rptSqlDataTmgTxt));
            // END 2013/11/20 T.Yoshida [Defect#2852 Mod]
        }
    }

    private static void createRptSqlFuncIdPullDown(NWXL0010CMsg cMsg) {

        final List<String> functionCodeListForBizAppId = getUserProfService().getFunctionCodeListForBizAppId(MY_BIZ_ID);

        final EZDCStringItemArray rptSqlFuncId_CL = cMsg.rptSqlFuncId_CL;
        final EZDCStringItemArray rptSqlFuncId_NL = cMsg.rptSqlFuncId_NL;
        rptSqlFuncId_CL.clear();
        rptSqlFuncId_NL.clear();

        for (int i = 0; i < functionCodeListForBizAppId.size(); i++) {
            if (i >= rptSqlFuncId_CL.length()) {
                break;
            }
            final String functionCode = functionCodeListForBizAppId.get(i);
            setValue(rptSqlFuncId_CL.no(i), functionCode);
            setValue(rptSqlFuncId_NL.no(i), functionCode);
        }
    }

    private static boolean getRptSqlInfo(NWXL0010CMsg cMsg, NWXL0010Query.EZcancelflag ezcancelflag) {
        
        final S21SsmEZDResult ssmRes = getQuery().getRptSqlInfo(cMsg, ezcancelflag);
        
        if (ssmRes.isCodeNormal()) {
            // RPT_SQL_TXT
            final RPT_SQLTMsg rptSqlTMsg = new RPT_SQLTMsg();
            setValue(rptSqlTMsg.glblCmpyCd, getUserProfService().getGlobalCompanyCode());
            setValue(rptSqlTMsg.rptSqlId,   cMsg.rptSqlId_01);
            setValue(cMsg.xxRptSqlTxt_01,   new RptSqlTxtAccessor(rptSqlTMsg).getRptSqlTxt());
        }
        
        return ssmRes.isCodeNormal();
    }

}
