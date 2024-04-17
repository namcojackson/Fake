/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2220;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2220.common.NWAL2220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#6283
 * 2016/11/11   Fujitsu         T.Yoshida       Update          QC#14410
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL2220BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NWAL2220CMsg cMsg = (NWAL2220CMsg) arg0;
        NWAL2220SMsg sMsg = (NWAL2220SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NWAL2220_INIT".equals(screenAplID)) {
                doProcess_NWAL2220_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//            } else if ("NWAL2220Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWAL2220Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_CMN_Clear(cMsg, sMsg);
            // END 2022/04/18 J.Evangelista [QC#59934,MOD]
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_Search(cMsg, sMsg);
            // START 2016/04/26 K.Kojima [QC#6283,ADD]
            } else if ("NWAL2220Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_OnChangeSavedSearchOption(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_SaveSearch(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_DeleteSearch(cMsg, sMsg);
            // END 2016/04/26 K.Kojima [QC#6283,ADD]
            // QC#14410 Add Start
            } else if ("NWAL2220Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_PageJump(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_PageNext(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_PagePrev(cMsg, sMsg);
            }
            // QC#14410 Add End
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2220_INIT(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {

        NWAL2220CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        NWAL2220CommonLogic.createPullDown(cMsg);
        NWAL2220CommonLogic.setInitParams(cMsg, sMsg);
        // START 2016/04/26 K.Kojima [QC#6283,ADD]
        NWAL2220CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId());
        // END 2016/04/26 K.Kojima [QC#6283,ADD]
    }

    // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//    private void doProcess_NWAL2220Scrn00_CMN_Reset(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
    private void doProcess_NWAL2220Scrn00_CMN_Clear(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
    // END 2022/04/18 J.Evangelista [QC#59934,MOD]

        doProcess_NWAL2220_INIT(cMsg, sMsg);
    }

    private void doProcess_NWAL2220Scrn00_CMN_Download(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        
        // ADD START S21_NA QC#13856
        if (!NWAL2220CommonLogic.hasInput(cMsg)) {
            return;
        }
        // ADD END S21_NA QC#13856

        NWAL2220CommonLogic.getDownloadData(cMsg);
    }

    private void doProcess_NWAL2220Scrn00_Search(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {

        // QC#14410 Add Start
        if (!NWAL2220CommonLogic.hasInput(cMsg)) {
            return;
        }
        // QC#14410 Add End

        if (NWAL2220CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        NWAL2220CommonLogic.getSearchData(cMsg, sMsg); // QC#14410 Mod
    }

    // START 2016/04/26 K.Kojima [QC#6283,ADD]
    private void doProcess_NWAL2220Scrn00_OnChangeSavedSearchOption(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NWAL2220CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());
        }
    }

    private void doProcess_NWAL2220Scrn00_SaveSearch(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        return;
    }

    private void doProcess_NWAL2220Scrn00_DeleteSearch(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        return;
    }
    // END 2016/04/26 K.Kojima [QC#6283,ADD]

    // QC#14410 Add Start
    /**
     * do Process (Page Jump)
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     */
    private void doProcess_NWAL2220Scrn00_PageJump(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {

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
     * do Process (Page Next)
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     */
    private void doProcess_NWAL2220Scrn00_PageNext(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {

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
     * do Process (PagePrev)
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     */
    private void doProcess_NWAL2220Scrn00_PagePrev(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {

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
    // QC#14410 Add End
}
