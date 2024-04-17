/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1220;

import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_CMN_CLEAR;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_INIT;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_ON_CHANGE_SEARCH_OPTION;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_PAGE_NEXT;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_PAGE_PREV;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_SEARCH;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_SET_ITEM_DESCRIPTION;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_SET_SUPPLIER_NAME;
import static business.blap.NPAL1220.constant.NPAL1220Constant.EVENT_NM_NPAL1220_SET_SUPPLIER_SITE_NAME;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1220.common.NPAL1220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1220 ASL Search
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/17/2015   CITS       Takeshi Tokutomi     Create          N/A
 * 01/11/2018   CITS            S.Katsuma       Update          QC#12226
 *</pre>
 */
public class NPAL1220BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1220_INIT.equals(screenAplID) || EVENT_NM_NPAL1220_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NPAL1220_INIT((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            } else if (EVENT_NM_NPAL1220_SET_ITEM_DESCRIPTION.equals(screenAplID)) {
                doProcess_SetItemDescription((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            } else if (EVENT_NM_NPAL1220_SET_SUPPLIER_NAME.equals(screenAplID)) {
                doProcess_SetSupplierName((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            } else if (EVENT_NM_NPAL1220_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            } else if (EVENT_NM_NPAL1220_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            } else if (EVENT_NM_NPAL1220_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            } else if (EVENT_NM_NPAL1220_ON_CHANGE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_OnChange_SearchOption((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            } else if (EVENT_NM_NPAL1220_SET_SUPPLIER_SITE_NAME.equals(screenAplID)) {
                doProcess_SetSupplierSiteName((NPAL1220CMsg) cMsg, (NPAL1220SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_NPAL1220_INIT(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        cMsg.glblCmpyCd.setValue(glblCmpyCd);

        NPAL1220CommonLogic.createPullDownSearchOption(cMsg, sMsg, glblCmpyCd);
        NPAL1220CommonLogic.createPullDownQualifierType(cMsg, sMsg, glblCmpyCd);
        NPAL1220CommonLogic.createPullDownMerchandiseType(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Set Item Description
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_SetItemDescription(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {

        NPAL1220CommonLogic.setItemDescription(cMsg, sMsg, getGlobalCompanyCode());
    }

    /**
     * Set Supplier Name
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_SetSupplierName(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {

        NPAL1220CommonLogic.setSupplierName(cMsg, sMsg, getGlobalCompanyCode());
    }

    /**
     * Search
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_Search(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // Set Supplier Name to header
        if (ZYPCommonFunc.hasValue(cMsg.prntVndCd)) {
            NPAL1220CommonLogic.setSupplierName(cMsg, sMsg, glblCmpyCd);

            // Clear Error Message. This message is not search
            // function error.
            if (cMsg.prntVndCd.isError()) {
                cMsg.setMessageInfo(null);
                cMsg.clearErrorInfo();
            }
        } else {
            cMsg.prntVndNm.clear();
        }

        // Set Item Name to header
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
            NPAL1220CommonLogic.setItemDescription(cMsg, sMsg, glblCmpyCd);

            // Clear Error Message. This message is not search
            // function error.
            if (cMsg.mdseCd.isError()) {
                cMsg.setMessageInfo(null);
                cMsg.clearErrorInfo();
            }
        } else {
            cMsg.mdseDescShortTxt.clear();
        }

        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        // Set Supplier Site Name to header
        if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            NPAL1220CommonLogic.setSupplierSiteName(cMsg, sMsg, glblCmpyCd);

            // Clear Error Message. This message is not search
            // function error.
            if (cMsg.vndCd.isError()) {
                cMsg.setMessageInfo(null);
                cMsg.clearErrorInfo();
            }
        } else {
            cMsg.locNm.clear();
        }
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]

        NPAL1220CommonLogic.search(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Page Next
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_PageNext(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * Page Prev
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_PagePrev(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Pulldown onChange Event
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_OnChange_SearchOption(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            NPAL1220CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    // START 2018/01/11 S.Katsuma [QC#12226,ADD]
    /**
     * Set Supplier Name
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     */
    private void doProcess_SetSupplierSiteName(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg) {

        NPAL1220CommonLogic.setSupplierSiteName(cMsg, sMsg, getGlobalCompanyCode());
    }
    // END 2018/01/11 S.Katsuma [QC#12226,ADD]
}
