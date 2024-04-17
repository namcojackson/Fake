/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1520;

import static business.blap.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_SRCH_OPT_NM;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_SRCH_OPT_PK;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_CMN_COL_CLEAR;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_CMN_COL_SAVE;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_DELETE_SEARCH_OPTION;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SAVE_SEARCH_OPTION;
import static business.blap.NPAL1520.constant.NPAL1520Constant.NMAM0014E;
import static business.blap.NPAL1520.constant.NPAL1520Constant.NMAM0182E;
import static business.blap.NPAL1520.constant.NPAL1520Constant.SCRN_ID;
import static business.blap.NPAL1520.constant.NPAL1520Constant.ZZM9000E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1520.common.NPAL1520CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Update Business Process
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */

public class NPAL1520BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1520_SAVE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_SaveSearchOption((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_DELETE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_DeleteSearchOption((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Save Search option
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_SaveSearchOption(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            cMsg.srchOptNm_TX.setErrorInfo(1, ZZM9000E, new String[] {converter.convLabel2i18nLabel(SCRN_ID, DISPLAY_NM_SRCH_OPT_NM) });
            return;
        }

        if (NPAL1520CommonLogic.isExistSaveSearchName(cMsg)) {
            // Error Message ;
            // You can not [@] this record Because of [@] already
            // exists.
            cMsg.srchOptNm_TX.setErrorInfo(1, NMAM0182E, //
                    new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save"), converter.convLabel2i18nLabel(SCRN_ID, DISPLAY_NM_SRCH_OPT_NM) });
            return;
        }

        NPAL1520CommonLogic.callNszc0330forSaveSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());

    }

    /**
     * Delete Search option
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_DeleteSearchOption(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            // Message ; [@] is not selected.
            cMsg.srchOptPk_SL.setErrorInfo(1, NMAM0014E, new String[] {converter.convLabel2i18nLabel(SCRN_ID, DISPLAY_NM_SRCH_OPT_PK) });
            return;
        }

        NPAL1520CommonLogic.callNszc0330forDeleteSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());

    }

}
