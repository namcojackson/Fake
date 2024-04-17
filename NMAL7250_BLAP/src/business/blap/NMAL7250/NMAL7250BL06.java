/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7250;

import static business.blap.NMAL7250.constant.NMAL7250Constant.SCRN_ID_00;
import static business.blap.NMAL7250.constant.NMAL7250Constant.NMAM0014E;
import static business.blap.NMAL7250.constant.NMAL7250Constant.NMAM0182E;
import static business.blap.NMAL7250.constant.NMAL7250Constant.ZZM9000E;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NMAL7250.common.NMAL7250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7250BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7250BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7250CMsg bizMsg = (NMAL7250CMsg) cMsg;
            NMAL7250SMsg glblMsg = (NMAL7250SMsg) sMsg;

            if ("NMAL7250Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NMAL7250CMsg) cMsg, (NMAL7250SMsg) sMsg);

            } else if ("NMAL7250Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NMAL7250CMsg) cMsg, (NMAL7250SMsg) sMsg);

            } else if ("NMAL7250Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_SaveSearch(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL7250Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL7250Scrn00_CMN_Submit(NMAL7250CMsg cMsg, NMAL7250SMsg sMsg) {
        return;
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7250Scrn00_DeleteSearch(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            bizMsg.srchOptPk.setErrorInfo(1, NMAM0014E
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL7250CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7250Scrn00_SaveSearch(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            bizMsg.srchOptNm.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL7250CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm.setErrorInfo(1, NMAM0182E
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL7250CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
}
