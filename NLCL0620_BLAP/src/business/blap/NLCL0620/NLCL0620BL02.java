/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0620;

import static business.blap.NLCL0620.constant.NLCL0620Constant.CONST_VALUE_KEY_TECH_PI_LOC_TP_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.CONST_VALUE_KEY_TECH_PI_THRHD_OPEN_ORD_DAYS;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_CMN_DOWNLOAD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_CMN_RESET;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_INIT;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_PAGE_NEXT;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_PAGE_PREV;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_SEARCH;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_SEARCH_LOCATION;
import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_SEARCH_TECHNICIAN;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0620.common.NLCL0620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   CITS         Makoto Okigami     Create          N/A
 * 12/11/2018   Fujitsu         S.Ohki          Update          QC#28755
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 * 06/25/2020   CITS          M.Furugoori       Update          QC#56979
 *</pre>
 */
public class NLCL0620BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0620_INIT.equals(screenAplID)
                    || EVENT_NM_NLCL0620_CMN_RESET.equals(screenAplID)) {
                doProcess_NLCL0620_INIT((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            } else if (EVENT_NM_NLCL0620_SEARCH.equals(screenAplID)) {
                doProcess_Search((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            } else if (EVENT_NM_NLCL0620_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            } else if (EVENT_NM_NLCL0620_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            } else if (EVENT_NM_NLCL0620_SEARCH_TECHNICIAN.equals(screenAplID)) {
                doProcess_SearchTechnician((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            // START 2018/12/11 S.Ohki [QC#28755,ADD]
            } else if (EVENT_NM_NLCL0620_SEARCH_LOCATION.equals(screenAplID)) {
            	doProcess_SearchLocation((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            // END 2018/12/11 S.Ohki [QC#28755,ADD]
            // START 2019/12/17 T.Ogura [QC#54986,ADD]
            } else if (EVENT_NM_NLCL0620_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_Download((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            // END   2019/12/17 T.Ogura [QC#54986,ADD]
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_NLCL0620_INIT(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        ZYPEZDItemValueSetter.setValue(cMsg.numConstVal, ZYPCodeDataUtil.getNumConstValue(CONST_VALUE_KEY_TECH_PI_THRHD_OPEN_ORD_DAYS, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal, ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_TECH_PI_LOC_TP_CD, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.numConstVal, ZYPCodeDataUtil.getNumConstValue(CONST_VALUE_KEY_TECH_PI_THRHD_OPEN_ORD_DAYS, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.varCharConstVal, ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_TECH_PI_LOC_TP_CD, glblCmpyCd));

        NLCL0620CommonLogic.getPhysicalInventoryStatusName(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Search
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_Search(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0620CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        NLCL0620CommonLogic.searchLastPhysicalInventory(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Page Next
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_PageNext(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                // START 2020/06/25 [QC#56979,ADD]
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).proNum_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).carrTrkUrl_A1)) {
                    NLCL0620CommonLogic.editCarrTrkUrl(sMsg, i);
                }
                // END 2020/06/25 [QC#56979,ADD]
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
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_PagePrev(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                // START 2020/06/25 [QC#56979,ADD]
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).proNum_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).carrTrkUrl_A1)) {
                    NLCL0620CommonLogic.editCarrTrkUrl(sMsg, i);
                }
                // END 2020/06/25 [QC#56979,ADD]
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
     * Search Technician
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_SearchTechnician(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0620CommonLogic.searchTechnician(cMsg, sMsg, glblCmpyCd);

    }

    // START 2018/12/11 S.Ohki [QC#28755,ADD]
    /**
     * Search Location
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_SearchLocation(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0620CommonLogic.searchLocation(cMsg, sMsg, glblCmpyCd);
    }
    // END 2018/12/11 S.Ohki [QC#28755,ADD]

    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * Download
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_Download(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0620CommonLogic.download(cMsg, sMsg, glblCmpyCd);
        NLCL0620Query.getInstance().createCSV(cMsg, sMsg, glblCmpyCd);
    }
    // END   2019/12/17 T.Ogura [QC#54986,ADD]
}
