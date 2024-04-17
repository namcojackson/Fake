/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3050;

import static business.blap.NFCL3050.constant.NFCL3050Constant.SCRN_ID_00;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NFCL3050.common.NFCL3050CommonLogic;
import business.blap.NFCL3050.constant.NFCL3050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFCL3050BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/22   CSAI            K.Uramori       Update          QC#5770
 * 2016/05/19   Fujitsu         S.Fujita        Update          QC#7780
 * 2016/09/05   Hitachi         K.Kojima        Update          QC#11049
 * 2018/07/30   CITS            K.Ogino         Update          QC#26680
 *</pre>
 */
public class NFCL3050BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL3050CMsg bizMsg = (NFCL3050CMsg) cMsg;
            NFCL3050SMsg glblMsg = (NFCL3050SMsg) sMsg;
            cMsg.setCommitSMsg(true);

            if ("NFCL3050Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_DeleteSearch(bizMsg, glblMsg);
            //---- start add 2016/03/22 QC#5770
            } else if ("NFCL3050Scrn00_btnRegen".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_btnRegen(bizMsg, glblMsg);
            //---- end 2016/03/22
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_SaveSearch(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "ZZM9000E"// [@] field is mandatory.
                    , new String[] {converter.convLabel2i18nLabel(NFCL3050Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NFCL3050CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "NLZM2273E" // You can not [@] this record Because of [@] already exists.
                    , new String[] {
                    converter.convLabel2i18nLabel(NFCL3050Constant.SCRN_ID_00, "Save")
                    , converter.convLabel2i18nLabel(NFCL3050Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }

        NFCL3050CommonLogic.callNszc0330forSaveSearch(
                bizMsg, glblMsg, getContextUserInfo().getUserId());
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_DeleteSearch(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, "NLZM2274E" // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NFCL3050CommonLogic.callNszc0330forDeleteSearch(
                bizMsg, glblMsg, getContextUserInfo().getUserId());
    }
    
    /**
     * Regenerate Account Distribution
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    //---- start add 2016/03/22 QC#5770
    private void doProcess_NFCL3050Scrn00_btnRegen(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {

        // START 2016/09/05 K.Kojima [QC#11049,DEL]
        // // START 2016/05/19 S.Fujita [QC#7780,ADD]
        // if(!NFCL3050CommonLogic.checkAcctDist(bizMsg)) {
        // return;
        // }
        // // END 2016/05/19 S.Fujita [QC#7780,ADD]
        // END 2016/09/05 K.Kojima [QC#11049,DEL]

        // call NFZC1030 to generate account distribution 
        NFCL3050CommonLogic.callAcctDistAPI(bizMsg);
        
        // re-search
        // QC#26680
        NFCL3050CommonLogic.search(getGlobalCompanyCode(), bizMsg, glblMsg);
    }
    //---- end 2016/03/22
}
