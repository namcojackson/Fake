/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1820;

import static business.blap.NWAL1820.constant.NWAL1820Constant.NZZM0000E;
import static business.blap.NWAL1820.constant.NWAL1820Constant.NZZM0001W;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1820.common.NWAL1820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1820BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 * 2017/03/09   Fujitsu         K.Ishizuka      Update          QC#13856
 *</pre>
 */
public class NWAL1820BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1820CMsg bizMsg = (NWAL1820CMsg) cMsg;
            NWAL1820SMsg glblMsg = (NWAL1820SMsg) sMsg;

            if ("NWAL1820_INIT".equals(screenAplID)) {
                doProcess_NWAL1820_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData((NWAL1820CMsg) bizMsg, (NWAL1820SMsg) glblMsg, "AHEAD");

            } else if ("NWAL1820Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_OnChange_OrderCategory".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_OnChange_OrderCategory(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_OnChange_OrderReason".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_OnChange_OrderReason(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_CMN_ColSave(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NWAL1820Scrn00_CMN_ColClear(bizMsg, glblMsg);

            } else if ("NWAL1820Scrn00_CMN_Download".equals(screenAplID)) { // ADD S21_NA QC#13856
                doProcess_NWAL1820Scrn00_CMN_Download(bizMsg, glblMsg); // ADD S21_NA QC#13856
                
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
    private void doProcess_NWAL1820_INIT(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        NWAL1820CommonLogic.clearAll(bizMsg, glblMsg);

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        NWAL1820CommonLogic.createCatgPullDown(bizMsg);
        NWAL1820CommonLogic.createRsnPullDown(bizMsg);
        NWAL1820CommonLogic.createSubRsnPullDown(bizMsg);

        //Setup select box 
        NWAL1820CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_PageNext(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1820CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_PagePrev(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1820CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_Search(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * OnChange_OrderCategory Event
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     */
    private void doProcess_NWAL1820Scrn00_OnChange_OrderCategory(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // Create Reason Code Pulldown
        NWAL1820CommonLogic.createRsnPullDown(bizMsg);
        bizMsg.dsOrdTpCd.clear();
//        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, bizMsg.dsOrdTpCd_CD.no(0));
        // Create Sub Reason Code Pulldown
        NWAL1820CommonLogic.createSubRsnPullDown(bizMsg);
        bizMsg.dsOrdRsnCd.clear();
    }


    /**
     * OnChange_OrderReason Event
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     */
    private void doProcess_NWAL1820Scrn00_OnChange_OrderReason(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // Create Sub Reason Code Pulldown
        NWAL1820CommonLogic.createSubRsnPullDown(bizMsg);
        bizMsg.dsOrdRsnCd.clear();
//        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd, bizMsg.dsOrdRsnCd_CD.no(0));
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_OnChangeSavedSearchOption(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            NWAL1820CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_DeleteSearch(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // do nothing
        return;
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_SaveSearch(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // do nothing
        return;
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_CMN_Clear(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        doProcess_NWAL1820_INIT(bizMsg, glblMsg);
        ZYPGUITableColumn.getColData((NWAL1820CMsg) bizMsg, (NWAL1820SMsg) glblMsg, "AHEAD");
    }

    /**
     * CMN_ColClear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_CMN_ColClear(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // do nothing
        return;
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_CMN_ColSave(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // do nothing
        return;
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NWAL1820Query.getInstance().getLoanOrd(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NWAL1820CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
    
    // ADD START S21_NA QC#13856
    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1820Scrn00_CMN_Download(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        
        NWAL1820CommonLogic.csvDownload(bizMsg, glblMsg);
        
    }
    // ADD END S21_NA QC#13856

}
