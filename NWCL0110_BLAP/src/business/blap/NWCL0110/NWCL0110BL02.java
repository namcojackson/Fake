/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0110;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWCL0110.NWCL0110CMsg;
import business.blap.NWCL0110.NWCL0110SMsg;
import business.blap.NWCL0110.constant.NWCL0110Constant;
import business.db.NUM_CONSTTMsg;
import business.db.NUM_CONSTTMsgArray;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         H.Nagashima     Create          N/A
 * 2017/12/21   SRAA            K.Aratani       Update          QC#18692
 *</pre>
 */
public class NWCL0110BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0110CMsg bizMsg = (NWCL0110CMsg) cMsg;
            NWCL0110SMsg glblMsg = (NWCL0110SMsg) sMsg;

            if ("NWCL0110_INIT".equals(screenAplID)) {
                doProcess_NWCL0110_INIT(bizMsg, glblMsg);
            } else if ("NWCL0110Scrn00_Search".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_Search(bizMsg, glblMsg, false);
            } else if ("NWCL0110Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_TBLColumnSort(bizMsg, glblMsg);
            } else if ("NWCL0110Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NWCL0110Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NWCL0110Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NWCL0110Scrn00_PrintMassRequest".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_PrintMassRequest(bizMsg, glblMsg);
        	} else if ("NWCL0110Scrn00_PageNext".equals(screenAplID)) { // QC#20206(L3#269) Add Start
                doProcess_NWCL0110_PageNext(bizMsg, (NWCL0110SMsg) sMsg);
            } else if ("NWCL0110Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWCL0110_PagePrev(bizMsg, (NWCL0110SMsg) sMsg);
            } else if ("NWCL0110Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWCL0110_PageJump(bizMsg, (NWCL0110SMsg) sMsg); // QC#20206(L3#269) Add END
            } else if ("NWCL0110Scrn00_Select_All".equals(screenAplID)) {
                doProcess_NWCL0110_Select_All(bizMsg, (NWCL0110SMsg) sMsg);
            } else if ("NWCL0110Scrn00_Un_Select_All".equals(screenAplID)) {
                doProcess_NWCL0110_Un_Select_All(bizMsg, (NWCL0110SMsg) sMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * INIT Event
     * </pre>
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110_INIT(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        // create pulldown and initial value
        createPullDown(cMsg);

	    BigDecimal srchFetchSize = getSrchMaxSize(cMsg, getGlobalCompanyCode());
        BigDecimal dplyFetchSize = getDplyMaxSize(cMsg, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtlCnt_H1, srchFetchSize);// Search Max
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtlCnt_H2, dplyFetchSize);// Detail Max
        
        // defalt output date
        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        //String prevDt = ZYPDateUtil.addDays(slsDt, -1);
        ZYPEZDItemValueSetter.setValue(cMsg.procDt_FR, slsDt);
        ZYPEZDItemValueSetter.setValue(cMsg.procDt_TO, slsDt);
        // QC#53014 2019/09/17 Mod Start
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_SP, ZYPConstant.CHKBOX_ON_Y);
        // QC#53014 2019/09/17 Mod End
    }

    /**
     * <pre>
     * CMN_Submit Event
     * </pre>
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_CMN_Submit(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        // clear
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        doProcess_NWCL0110Scrn00_Search(cMsg, sMsg, true);

        cMsg.setMessageInfo(NWCL0110Constant.ZZZM9003I, new String[] {NWCL0110Constant.SUBMIT });
    }

    /**
     * <pre>
     * CMN_Reset Event
     * </pre>
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_CMN_Reset(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        // clear
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        doProcess_NWCL0110_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * CMN_Clear Event
     * </pre>
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_CMN_Clear(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        // clear
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        doProcess_NWCL0110_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * Search Event
     * </pre>
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_Search(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg, boolean submitEventFlg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult ssmResult = NWCL0110Query.getInstance().getInvPrintControl(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // 200over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.xxDtlCnt_H1.getValueInt()) {
                cMsg.setMessageInfo(NWCL0110Constant.NWCM0101W, new String[] {String.valueOf(cMsg.xxDtlCnt_H1.getValueInt()) });
                
                queryResCnt = cMsg.xxDtlCnt_H1.getValueInt();
                sMsg.A.no(cMsg.xxDtlCnt_H1.getValueInt()).clear();
                cMsg.xxPageShowOfNum_10.setValue(cMsg.xxDtlCnt_H1.getValueInt());
                sMsg.A.setValidCount(cMsg.xxDtlCnt_H1.getValueInt());
                
            } else {
                cMsg.setMessageInfo("ZZM8100I");
                cMsg.xxPageShowOfNum_10.setValue(queryResCnt);
            }

//            int i = 0;
//            for (; i < sMsg.A.getValidCount(); i++) {
//                if (i == cMsg.A.length()) {
//                    break;
//                }
//                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
//            }
//            cMsg.A.setValidCount(i);

            int i = 0;
            int bzNum = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
//                EZDMsg.copy(sMsg.A.no(i), "A1", cMsg.C.no(i), "C1");
            }
            if (i > cMsg.A.length()) {
                bzNum = cMsg.A.length();
            } else {
                bzNum = i;
            }
            
            cMsg.A.setValidCount(bzNum);
            sMsg.A.setValidCount(sMsg.A.getValidCount());
            
         // Set page number
            cMsg.xxPageShowFromNum_10.setValue(1);
            cMsg.xxPageShowToNum_10.setValue(cMsg.A.getValidCount());
            
            
        } else {
            if (!submitEventFlg) {
                cMsg.setMessageInfo(NWCL0110Constant.NZZM0000E);
            }
        }
    }

    /**
     * <pre>
     * TBLColumnSort Event
     * </pre>
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_TBLColumnSort(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        if ("A".equals(cMsg.xxSortTblNm.getValue())) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue());
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy (SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
        }
    }


    /**
     * create Pull Down List
     * @param cMsg NWCL0110CMsg
     */
    private void createPullDown(NWCL0110CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(INV_PRT_BAT_TP.class, cMsg.invPrtBatTpCd_PL, cMsg.invPrtBatTpDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(INV_PRT_BR.class, cMsg.invPrtBrCd_PL, cMsg.invPrtBrDescTxt_PL);

        int size = NWCL0110Constant.CONST_OTPT_TP_ARRAY.length;
        for (int i = 0; i < size; i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_PL.no(i), NWCL0110Constant.CONST_OTPT_TP_ARRAY[i][0]);
            ZYPEZDItemValueSetter.setValue(cMsg.procNm_PL.no(i), NWCL0110Constant.CONST_OTPT_TP_ARRAY[i][1]);
        }

        //get EIP_RPT_CTRL
        S21SsmEZDResult ssmResult = NWCL0110Query.getInstance().getEipReportControl(NWCL0110Constant.REPORT_ID_INVOICE);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> rsltCustList = (List<Map<String, String>>) ssmResult.getResultObject();
            int idx = 0;
            for (Map<String, String> rsltMap : rsltCustList) {
                ZYPEZDItemValueSetter.setValue(cMsg.reprRptBrNum_PL.no(idx), (String) rsltMap.get("RPT_BR_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.rptTtlNm_PL.no(idx), (String) rsltMap.get("RPT_TTL_NM"));
                idx++;
            }
        }
    }

    /**
     * <pre>
     * CMN_Submit Event
     * </pre>
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_PrintMassRequest(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        // clear
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        doProcess_NWCL0110Scrn00_Search(cMsg, sMsg, true);

        cMsg.setMessageInfo(NWCL0110Constant.ZZZM9003I, new String[] {NWCL0110Constant.SUBMIT });
    }

    /**
     * execute next page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NWCL0110_PageNext(NWCL0110CMsg bizMsg, NWCL0110SMsg glblMsg) {
        updateSMsg(bizMsg, glblMsg);
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum_10.setValue(bizMsg.xxPageShowToNum_10.getValueInt() + 1);
        loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute previous page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NWCL0110_PagePrev(NWCL0110CMsg bizMsg, NWCL0110SMsg glblMsg) {
        updateSMsg(bizMsg, glblMsg);
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum_10.setValue(bizMsg.xxPageShowFromNum_10.getValueInt() - bizMsg.A.length());
        loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute jump to selected screen transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NWCL0110_PageJump(NWCL0110CMsg bizMsg, NWCL0110SMsg glblMsg) {
        updateSMsg(bizMsg, glblMsg);
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum_10.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum_10.getValueInt() - 1)) + 1);
        loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }
    private void doProcess_NWCL0110_Select_All(NWCL0110CMsg bizMsg, NWCL0110SMsg glblMsg) {
        updateSMsg(bizMsg, glblMsg);
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }
    }
    private void doProcess_NWCL0110_Un_Select_All(NWCL0110CMsg bizMsg, NWCL0110SMsg glblMsg) {
        updateSMsg(bizMsg, glblMsg);
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblMsg.A.no(i).xxChkBox_A.clear();
        }
    }
    
    /**
     * update S message
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    public static void updateSMsg(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {
        int ixG = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(ixG + i), null);
        }
    }
    /**
     * control pagenation
     * @param cMsg NWCL0110CMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg NWCL0110SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NWCL0110CMsg cMsg, EZDCMsgArray cMsgArray, NWCL0110SMsg sMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        cMsg.xxPageShowToNum_10.clear();
        cMsg.xxPageShowOfNum_10.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (cMsg.xxPageShowFromNum_10.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        cMsg.xxPageShowFromNum_10.setValue(startIndex + 1);
        cMsg.xxPageShowToNum_10.setValue(startIndex + cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum_10.setValue(sMsgArray.getValidCount());
    }
    
    public static BigDecimal getSrchMaxSize(NWCL0110CMsg bizMsg, String glblCmpyCd) {

        NUM_CONSTTMsg numConstMsg = new NUM_CONSTTMsg();
        numConstMsg.setSQLID("001");
        numConstMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        numConstMsg.setConditionValue("numConstNm01", "NWCL0110_MAX_SRCH_ROW_CNT");
        
        NUM_CONSTTMsgArray numConstArray = (NUM_CONSTTMsgArray) EZDTBLAccessor.findByCondition(numConstMsg);
        
        if (numConstArray == null || numConstArray.length() <= 0) {
            return BigDecimal.ONE;
        }
        
        numConstMsg = numConstArray.no(0);
        return numConstMsg.numConstVal.getValue();
    }
    
    public static BigDecimal getDplyMaxSize(NWCL0110CMsg bizMsg, String glblCmpyCd) {

        NUM_CONSTTMsg numConstMsg = new NUM_CONSTTMsg();
        numConstMsg.setSQLID("001");
        numConstMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        numConstMsg.setConditionValue("numConstNm01", "NWCL0110_MAX_DPLY_ROW_CNT");
        
        NUM_CONSTTMsgArray numConstArray = (NUM_CONSTTMsgArray) EZDTBLAccessor.findByCondition(numConstMsg);
        
        if (numConstArray == null || numConstArray.length() <= 0) {
            return BigDecimal.ONE;
        }
        
        numConstMsg = numConstArray.no(0);
        return numConstMsg.numConstVal.getValue();
    }
}
