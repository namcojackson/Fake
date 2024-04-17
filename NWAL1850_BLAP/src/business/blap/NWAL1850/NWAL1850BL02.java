/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1850;

import static business.blap.NWAL1850.constant.NWAL1850Constant.NZZM0000E;
import static business.blap.NWAL1850.constant.NWAL1850Constant.NZZM0001W;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1850.common.NWAL1850CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1850BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2022/06/02   Hitachi         K.Kitachi       Update          QC#60037
 * 2022/07/11   Hitachi         T.NEMA          Update          QC#60037
 *</pre>
 */
public class NWAL1850BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1850CMsg bizMsg = (NWAL1850CMsg) cMsg;
            NWAL1850SMsg glblMsg = (NWAL1850SMsg) sMsg;

            if ("NWAL1850_INIT".equals(screenAplID)) {
                doProcess_NWAL1850_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData((NWAL1850CMsg) bizMsg, (NWAL1850SMsg) glblMsg, "AHEAD");

            } else if ("NWAL1850Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_CMN_ColClear(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_CMN_ColSave(bizMsg, glblMsg);
// START 2022/07/11 T.NEMA [QC#60037, MOD]
//            } else if ("NWAL1850Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWAL1850Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NWAL1850Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_CMN_Clear(bizMsg, glblMsg);
// END   2022/07/11 T.NEMA [QC#60037, MOD]

            } else if ("NWAL1850Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_OnBlur_DeriveFromCategory".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWAL1850_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1850Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);

            } else if ("NWAL1850Scrn00_CMN_Download".equals(screenAplID)) { // MOD S21_NA QC#13856
                doProcess_NWAL1850Scrn00_CMN_Download(bizMsg, glblMsg); // MOD S21_NA QC#13856

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
    private void doProcess_NWAL1850_INIT(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        NWAL1850CommonLogic.clearAll(bizMsg, glblMsg);

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPCodeDataUtil.createPulldownList(SCHD_AGMT_STS.class, bizMsg.schdAgmtStsCd_CD, bizMsg.schdAgmtStsDescTxt_NM);
        NWAL1850CommonLogic.createRsnPullDown(bizMsg);
        // START 2022/06/02 K.Kitachi [QC#60037, ADD]
        NWAL1850CommonLogic.createDelyHldPullDown(bizMsg);
        // END 2022/06/02 K.Kitachi [QC#60037, ADD]

        //Setup select box 
        NWAL1850CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
    }

    /**
     * CMN_ColClear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_CMN_ColClear(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        // do nothing
        return;
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_CMN_ColSave(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        // do nothing
        return;
    }

 // START 2022/07/11 T.NEMA [QC#60037, ADD]
    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_CMN_Clear(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        doProcess_NWAL1850_INIT(bizMsg, glblMsg);
        ZYPGUITableColumn.getColData((NWAL1850CMsg) bizMsg, (NWAL1850SMsg) glblMsg, "AHEAD");

    }
// END   2022/07/11 T.NEMA [QC#60037, ADD]
    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_CMN_Reset(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
// START 2022/07/11 T.NEMA [QC#60037, MOD]
//        doProcess_NWAL1850_INIT(bizMsg, glblMsg);
//        ZYPGUITableColumn.getColData((NWAL1850CMsg) bizMsg, (NWAL1850SMsg) glblMsg, "AHEAD");
        // do nothing
        return;
// END   2022/07/11 T.NEMA [QC#60037, MOD]
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_DeleteSearch(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        // do nothing
        return;
    }

    /**
     * OnBlur_DeriveFromCategory Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_OnBlur_DeriveFromCategory(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        if (!NWAL1850CommonLogic.checkExistCatg(bizMsg)) {
            return;
        }

        // Create Reason Code Pulldown
        NWAL1850CommonLogic.createRsnPullDown(bizMsg);
        //ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, bizMsg.dsOrdTpCd_CD.no(0));
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_OnChangeSavedSearchOption(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            NWAL1850CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_PageJump(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWAL1850CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_PageNext(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1850CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_PagePrev(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1850CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_SaveSearch(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        // do nothing
        return;
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1850Scrn00_Search(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NWAL1850Query.getInstance().getSchdAgmt(bizMsg, glblMsg);

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
            NWAL1850CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
    
    // ADD START S21_NA QC#13856
    /**
     * do Process (CMN_Download)
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     */
    private void doProcess_NWAL1850Scrn00_CMN_Download(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {

        NWAL1850CommonLogic.csvDownload(bizMsg, glblMsg);

    }
    // ADD END S21_NA QC#13856

}
