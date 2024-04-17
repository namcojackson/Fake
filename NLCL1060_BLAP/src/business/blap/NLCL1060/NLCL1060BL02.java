/**
 *<pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.blap.NLCL1060;


import static business.blap.NLCL1060.constant.NLCL1060Constant.EVENT_NM_NLCL1060_CMN_CLEAR;
import static business.blap.NLCL1060.constant.NLCL1060Constant.EVENT_NM_NLCL1060_INIT;
import static business.blap.NLCL1060.constant.NLCL1060Constant.EVENT_NM_NLCL1060_PAGE_NEXT;
import static business.blap.NLCL1060.constant.NLCL1060Constant.EVENT_NM_NLCL1060_PAGE_PREV;
import static business.blap.NLCL1060.constant.NLCL1060Constant.EVENT_NM_NLCL1060_SEARCH;
import static business.blap.NLCL1060.constant.NLCL1060Constant.EVENT_NM_NLCL1060_TBL_COLUMN_SORT;
import static business.blap.NLCL1060.constant.NLCL1060Constant.NLCM0002I;
import static business.blap.NLCL1060.constant.NLCL1060Constant.NZZM0002I;
import static business.blap.NLCL1060.constant.NLCL1060Constant.ZZZM9002W;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1060_INIT.equals(screenAplID)) {
                doProcess_NLCL1060Scrn00_INIT((NLCL1060CMsg) cMsg, (NLCL1060SMsg) sMsg);

            } else if (EVENT_NM_NLCL1060_SEARCH.equals(screenAplID)) {
                doProcess_NLCL1060Scrn00_Search((NLCL1060CMsg) cMsg, (NLCL1060SMsg) sMsg);

            } else if (EVENT_NM_NLCL1060_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NLCL1060Scrn00_PageNext((NLCL1060CMsg) cMsg, (NLCL1060SMsg) sMsg);

            } else if (EVENT_NM_NLCL1060_PAGE_PREV.equals(screenAplID)) {
                doProcess_NLCL1060Scrn00_PagePrev((NLCL1060CMsg) cMsg, (NLCL1060SMsg) sMsg);

            } else if (EVENT_NM_NLCL1060_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NLCL1060Scrn00_CMN_Clear((NLCL1060CMsg) cMsg, (NLCL1060SMsg) sMsg);

            } else if (EVENT_NM_NLCL1060_TBL_COLUMN_SORT.equals(screenAplID)) {
                doProcess_NLCL1060_TBLColumnSort((NLCL1060CMsg) cMsg, (NLCL1060SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * @param bizMsg NLCL1060CMsg
     * @param sMsg NLCL1060SMsg
     */
    private void doProcess_NLCL1060Scrn00_INIT(NLCL1060CMsg bizMsg, NLCL1060SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_H)) {
            doProcess_Search_Common(bizMsg, sMsg);
        }
    }

    /**
     * @param bizMsg NLCL1060CMsg
     * @param sMsg NLCL1060SMsg
     */
    private void doProcess_NLCL1060Scrn00_Search(NLCL1060CMsg bizMsg, NLCL1060SMsg sMsg) {
        doProcess_Search_Common(bizMsg, sMsg);
    }

    /**
     * @param bizMsg NLCL1060CMsg
     * @param sMsg NLCL1060SMsg
     */
    private void doProcess_Search_Common(NLCL1060CMsg bizMsg, NLCL1060SMsg sMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        S21SsmEZDResult ssmResult = getQuery().getOnhandInventoryInquiry(bizMsg, sMsg, getGlobalCompanyCode(), sMsg.A.length() + 1);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size() && i < sMsg.A.length(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A1, (String) resultMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).vndCd_A1, (String) resultMap.get("VND_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dplyVndNm_A1, (String) resultMap.get("DPLY_VND_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, (String) resultMap.get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCd_A1, (String) resultMap.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).invtyQty_A1, (BigDecimal) resultMap.get("INVTY_QTY"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locStsDescTxt_A1, "");
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).stkStsDescTxt_A1, (String) resultMap.get("STK_STS_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locTpDescTxt_A1, "");

                sMsg.A.setValidCount(i + 1);
            }
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                // Too many search results.  Please narrow your search criteria and retry
                bizMsg.setMessageInfo(ZZZM9002W);
                queryResCnt = sMsg.A.length();
            }

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);
            if (bizMsg.A.getValidCount() == 0) {
                bizMsg.xxPageShowFromNum_A1.setValue(0);
            } else {
                bizMsg.xxPageShowFromNum_A1.setValue(1);
            }
            bizMsg.xxPageShowToNum_A1.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A1.setValue(sMsg.A.getValidCount());
            // The process has been successfully completed.
            bizMsg.setMessageInfo(NZZM0002I);
        } else {
            bizMsg.xxPageShowFromNum_A1.clear();
            bizMsg.xxPageShowToNum_A1.clear();
            bizMsg.xxPageShowOfNum_A1.clear();
            // No data found with this search criteria.
            bizMsg.setMessageInfo(NLCM0002I);
        }
    }

    private void doProcess_NLCL1060Scrn00_PageNext(NLCL1060CMsg cMsg, NLCL1060SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        cMsg.xxPageShowFromNum_A1.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NLCL1060Scrn00_PagePrev(NLCL1060CMsg cMsg, NLCL1060SMsg sMsg) {
        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_A1.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * @param bizMsg NLCL1060CMsg
     * @param sMsg NLCL1060SMsg
     */
    private void doProcess_NLCL1060Scrn00_CMN_Clear(NLCL1060CMsg bizMsg, NLCL1060SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_H, "");
        ZYPEZDItemValueSetter.setValue(bizMsg.vndCd, "");
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
    }

    private void doProcess_NLCL1060_TBLColumnSort(NLCL1060CMsg cMsg, NLCL1060SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A1.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A1.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_A1.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set page no
            cMsg.xxPageShowFromNum_A1.setValue(1);
            cMsg.xxPageShowToNum_A1.setValue(cMsg.A.getValidCount());
        }
    }

    private NLCL1060Query getQuery() {
        return NLCL1060Query.getInstance();
    }
}
