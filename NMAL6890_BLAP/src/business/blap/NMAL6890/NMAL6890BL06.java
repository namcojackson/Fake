/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL6890;

import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_CMN_COL_CLEAR;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_CMN_COL_SAVE;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_DELETESEARCHOPTION;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_SAVESEARCHOPTION;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL6890.common.NMAL6890CommonLogic;
import business.blap.NMAL6890.constant.NMAL6890Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NMAL6890BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL6890SCRN00_DELETESEARCHOPTION.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_DeleteSearchOption((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_SAVESEARCHOPTION.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_SaveSearchOption((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * SaveSearchOption Event
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_SaveSearchOption(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SS) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm)) {

            cMsg.srchOptNm.setErrorInfo(1, NMAL6890Constant.MSG_CD_ZZM9000E, new String[] {converter.convLabel2i18nLabel(NMAL6890Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        if (NMAL6890CommonLogic.isExistSaveSearchName(cMsg)) {

            cMsg.srchOptNm.setErrorInfo(1, NMAL6890Constant.MSG_CD_NMAM0182E,
                    new String[] {converter.convLabel2i18nLabel(NMAL6890Constant.SCREEN_ID, "Save"), converter.convLabel2i18nLabel(NMAL6890Constant.SCREEN_ID, "Search Option Name") });

            return;
        }

        NMAL6890CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * DeleteSearchOption Event
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_DeleteSearchOption(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SS)) {

            cMsg.srchOptPk_SS.setErrorInfo(1, NMAL6890Constant.MSG_CD_NMAM0014E, new String[] {converter.convLabel2i18nLabel(NMAL6890Constant.SCREEN_ID, "Saved Search Options") });
            return;
        }

        NMAL6890CommonLogic.callNszc0330forDelete(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}
