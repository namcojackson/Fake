/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLCL0270;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0270.common.NLCL0270CommonLogic;
import business.blap.NLCL0270.constant.NLCL0270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Supersession Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 * 07/04/2017   CITS            R.Shimamoto     Update          QC#18187
 * 11/13/2017   CITS            S.Katsuma       Update          QC#22230
 *</pre>
 */
public class NLCL0270BL02 extends S21BusinessHandler implements NLCL0270Constant {

    /**
     * Screen Application ID
     */
    private String screenAplID;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0270_INIT.equals(screenAplID)) {
                doProcess_NLCL0270_INIT((NLCL0270CMsg) cMsg, (NLCL0270SMsg) sMsg);
            } else if (EVENT_NM_NLCL0270SCRN00_SET_ITEM_DESCRIPTION.equals(screenAplID)) {
              doProcess_SetItemDescription((NLCL0270CMsg) cMsg, (NLCL0270SMsg) sMsg);
            } else if (EVENT_NM_NLCL0270SCRN00_ONCLICK_SEARCH.equals(screenAplID)) {
                doProcess_NLCL0270Scrn00_OnClick_Search((NLCL0270CMsg) cMsg, (NLCL0270SMsg) sMsg);
            } else if (EVENT_NM_NLCL0270SCRN00_PAGE_PREV.equals(screenAplID)) {
                doProcess_NLCL0270Scrn00_PagePrev((NLCL0270CMsg) cMsg, (NLCL0270SMsg) sMsg);
            } else if (EVENT_NM_NLCL0270SCRN00_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NLCL0270Scrn00_PageNext((NLCL0270CMsg) cMsg, (NLCL0270SMsg) sMsg);
            } else if (EVENT_NM_NLCL0270SCRN00_CLEAR.equals(screenAplID)) {
                doProcess_NLCL0270_INIT((NLCL0270CMsg) cMsg, (NLCL0270SMsg) sMsg);
            } else if (EVENT_NM_NLCL0270SCRN00_TBLCOLUMNSORT.equals(screenAplID)) {
                doProcess_NLCL0270_TBLColumnSort((NLCL0270CMsg) cMsg, (NLCL0270SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * The method explanation: The method explanation: It is a method
     * of the execution when the event[INIT] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0270_INIT(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNLCL0270 start[doProcess_NLCL0270_INIT]", null);

        // =============================================
        // Acquisition of global company code
        // =============================================
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_G1, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)
                && !ZYPCommonFunc.hasValue(cMsg.stkStsCd_H1)
                && !ZYPCommonFunc.hasValue(cMsg.rtlWhNm_H1)
                && !ZYPCommonFunc.hasValue(cMsg.rtlSwhNm_H1)) {

            NLCL0270CommonLogic.clearMsg(cMsg, sMsg);
        } else {

            doProcess_NLCL0270Scrn00_OnClick_Search(cMsg, sMsg);
        }

        S21SsmEZDResult ssmResult = NLCL0270Query.getInstance().searchSSPullDown(cMsg);
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("NPAM0020E", new String[]{"STK_STS Table"});
            return;
        }
        List<Map<String, String>> stkStsList = (List<Map<String, String>>) ssmResult.getResultObject();
        cMsg.stkStsCd_L1.clear();
        cMsg.stkStsNm_L1.clear();
        for (int i = 0; i < stkStsList.size(); i++) {
            if (i >= cMsg.stkStsCd_L1.length() || i >= cMsg.stkStsNm_L1.length()) {
                break;
            }
            Map<String, String> stkStsTpMap = stkStsList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.stkStsCd_L1.no(i), stkStsTpMap.get("STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.stkStsNm_L1.no(i), stkStsTpMap.get("STK_STS_DESC_TXT"));
        }

        //ZYPCodeDataUtil.createPulldownList(STK_STS.class, cMsg.stkStsCd_L1, cMsg.stkStsNm_L1);

        EZDDebugOutput.println(1, "PerformanceNLCL0270 end[doProcess_NLCL0270_INIT]", null);
    }

    /**
     * Set Item Description
     * @param cMsg NLCL0270CMsg
     * @param sMsg NLCL0270SMsg
     */
    private void doProcess_SetItemDescription(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {

        EZDDebugOutput.println(1, "NLCL0270 start[doProcess_SetItemDescription]", null);

        NLCL0270CommonLogic.setItemDescription(cMsg, sMsg, getGlobalCompanyCode());

        EZDDebugOutput.println(1, "NLCL0270 end[doProcess_SetItemDescription]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_Search] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0270Scrn00_OnClick_Search(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNLCL0270 start[doProcess_NLCL0270Scrn00_OnClick_Search]", null);

        cMsg.xxOrdTakeMdseFlg_G1.clear();
        EZDMsg.copy(cMsg, null, sMsg, null);

        // =================================================
        // Search Retail Warehouse Process
        // =================================================

        S21SsmEZDResult ssmResult = NLCL0270CommonLogic.search(cMsg, sMsg, this);

        if (ssmResult.isCodeNormal()) {

            // 1000over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // 1page copy（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // 07/04/2017 R.Shimamoto QC#18187 Mod.
            NLCL0270CommonLogic.checkOrdTakeFromOrgMdse(cMsg);

            // pagePre,PageNext
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            cMsg.setMessageInfo(NZZM0002I);
        } else {
            // START 2017/11/13 S.Katsuma [QC#22230,ADD]
//            cMsg.xxPageShowFromNum.clear();
//            cMsg.xxPageShowToNum.clear();
//            cMsg.xxPageShowOfNum.clear();
//            cMsg.A.setValidCount(0);
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(1);
            cMsg.xxPageShowOfNum.setValue(1);
            setDummyLine(cMsg, sMsg);
            NLCL0270CommonLogic.checkOrdTakeFromOrgMdse(cMsg);
            // When there is no retrieval result of details
            // information
            // Setting of message to 0 acquisition numbers
//            cMsg.setMessageInfo(NZZM0000E);
            cMsg.setMessageInfo(NZZM0002I);
            // END 2017/11/13 S.Katsuma [QC#22230,ADD]
        }

        EZDDebugOutput.println(1, "PerformanceNLCL0270 end[doProcess_NLCL0270Scrn00_OnClick_Search]", null);

    }

    private void doProcess_NLCL0270Scrn00_PagePrev(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {
        // Save current page to SMsg
        NLCL0270CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        int startIndex = 0;

        startIndex = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;

        // Load previous page from SMsg
        NLCL0270CommonLogic.copySMsgToCMsg(cMsg, sMsg, startIndex);
    }

    private void doProcess_NLCL0270Scrn00_PageNext(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {
        // Save current page data to SMsg
        NLCL0270CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // Load next page data from SMsg
        int startIndex = cMsg.xxPageShowToNum.getValueInt();
        NLCL0270CommonLogic.copySMsgToCMsg(cMsg, sMsg, startIndex);
    }

    /**
     * table column sort
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0270_TBLColumnSort(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if (NLCL0270Constant.TABLE_A.equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            cMsg.xxPageShowFromNum.setValue(1);
            NLCL0270CommonLogic.dispPage(cMsg, cMsg.A, sMsg.A);
        }
        
    }

    // START 2017/11/13 S.Katsuma [QC#22230,ADD]
    /**
     * setDummyLine
     * @param cMsg
     * @param sMsg
     */
    private void setDummyLine(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).mdseCd_O1, sMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).mdseCd_A1, sMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).mdseDescShortTxt_A1, sMsg.mdseDescShortTxt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).rtlWhNm_A1, sMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).rtlSwhNm_A1, sMsg.rtlSwhNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).stkStsCd_A1, sMsg.stkStsCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).invtyAvalQty_A1, new BigDecimal(0));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).invtyQty_A1, new BigDecimal(0));
        EZDMsg.copy(sMsg.A.no(0), null, cMsg.A.no(0), null);
        sMsg.A.setValidCount(1);
        cMsg.A.setValidCount(1);
    }
    // END 2017/11/13 S.Katsuma [QC#22230,ADD]
}
