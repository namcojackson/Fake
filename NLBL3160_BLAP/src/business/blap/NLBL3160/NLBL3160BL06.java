/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3160;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3160.common.NLBL3160CommonLogic;
import business.blap.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLBL3160BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3160Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_SaveSearch((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_DeleteSearch((NLBL3160CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3160Scrn00_SaveSearch
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     */
    private void doProcess_NLBL3160Scrn00_SaveSearch(NLBL3160CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            cMsg.srchOptNm_S.setErrorInfo(1, "ZZM9000E", new String[] {converter.convLabel2i18nLabel(NLBL3160Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        if (NLBL3160CommonLogic.isExistSaveSearchName(cMsg)) {

            // You can not [@] this record Because of [@] already
            // exists.
            cMsg.srchOptNm_S.setErrorInfo(1, "NLZM2273E", new String[] {converter.convLabel2i18nLabel(NLBL3160Constant.SCREEN_ID, "Save"), converter.convLabel2i18nLabel(NLBL3160Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        NLBL3160CommonLogic.callNszc0330forSaveSearch(cMsg, getContextUserInfo().getUserId(), cMsg.glblCmpyCd.getValue());
    }

    /**
     * doProcess_NLBL3160Scrn00_DeleteSearch
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     */
    private void doProcess_NLBL3160Scrn00_DeleteSearch(NLBL3160CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            // [@] is not selected.
            cMsg.srchOptPk_S.setErrorInfo(1, "NLZM2274E", new String[] {converter.convLabel2i18nLabel(NLBL3160Constant.SCREEN_ID, "Saved Search Options") });
            return;
        }

        NLBL3160CommonLogic.callNszc0330forDeleteSearch(cMsg, getContextUserInfo().getUserId(), cMsg.glblCmpyCd.getValue());
    }
}
