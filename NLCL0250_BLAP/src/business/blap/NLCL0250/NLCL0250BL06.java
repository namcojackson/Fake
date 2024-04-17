/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0250;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLCL0250.common.NLCL0250CommonLogic;
import business.blap.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 *</pre>
 */
public class NLCL0250BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0250Scrn00_DeleteSearch".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_DeleteSearch((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_SaveSearch".equals(screenAplID)) {

                doProcess_NLCL0250Scrn00_SaveSearch((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_CMN_ColSave".equals(screenAplID)) {

                ZYPGUITableColumn.setColData((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else if ("NLCL0250Scrn00_CMN_ColClear".equals(screenAplID)) {

                ZYPGUITableColumn.clearColData((NLCL0250CMsg) cMsg, (NLCL0250SMsg) sMsg);

            } else {

                return;
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * DeleteSearch Event
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_DeleteSearch(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            cMsg.srchOptPk_S.setErrorInfo(1, NLCL0250Constant.NLZM2274E, new String[] {converter.convLabel2i18nLabel(NLCL0250Constant.SCREEN_ID, "Saved Search Options") });
            return;
        }

        NLCL0250CommonLogic.callNszc0330forDeleteSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }

    /**
     * SaveSearch Event
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    private void doProcess_NLCL0250Scrn00_SaveSearch(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            cMsg.srchOptNm_S.setErrorInfo(1, NLCL0250Constant.ZZM9000E, new String[] {converter.convLabel2i18nLabel(NLCL0250Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        if (NLCL0250CommonLogic.isExistSaveSearchName(cMsg)) {

            cMsg.srchOptNm_S.setErrorInfo(1, NLCL0250Constant.NMAM0182E
                    , new String[] {converter.convLabel2i18nLabel(NLCL0250Constant.SCREEN_ID, "Save")
                    , converter.convLabel2i18nLabel(NLCL0250Constant.SCREEN_ID, "Search Option Name") });

            return;
        }

        NLCL0250CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }
}
