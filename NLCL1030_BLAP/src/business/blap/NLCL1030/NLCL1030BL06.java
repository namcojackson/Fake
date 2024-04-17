/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1030;

import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_CMN_COL_CLEAR;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_CMN_COL_SAVE;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_DELETESEARCH;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_SAVESEARCH;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_CD_NPAM1549E;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_CD_NPAM1550E;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_PRM_SAVE;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_PRM_SRCH_OPT;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_PRM_SRCH_OPT_NM;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLCL1030.common.NLCL1030CommonLogic;
import business.blap.NLCL1030.constant.NLCL1030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Logic for Update
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1030BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1030SCRN00_DELETESEARCH.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_DeleteSearch((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_SAVESEARCH.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_SaveSearch((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * SaveSearchOption Event
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_SaveSearch(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            cMsg.srchOptNm_S.setErrorInfo(1, NLCL1030Constant.MSG_CD_ZZM9000E, new String[] {MSG_PRM_SRCH_OPT_NM});
            return;
        }

        if (NLCL1030CommonLogic.isExistSaveSearchName(cMsg)) {

            cMsg.srchOptNm_S.setErrorInfo(1, MSG_CD_NPAM1550E, new String[] {MSG_PRM_SAVE, MSG_PRM_SRCH_OPT_NM});

            return;
        }

        NLCL1030CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * DeleteSearchOption Event
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_DeleteSearch(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            cMsg.srchOptPk_S.setErrorInfo(1, MSG_CD_NPAM1549E, new String[] {MSG_PRM_SRCH_OPT});
            return;
        }

        NLCL1030CommonLogic.callNszc0330forDelete(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
}
