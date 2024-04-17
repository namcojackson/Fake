/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0480;

import static business.blap.NSAL0480.constant.NSAL0480Constant.SCRN_ID;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0480.constant.NSAL0480Constant.MSG_ID;
import business.blap.NSAL0480.common.NSAL0480CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/11   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 *</pre>
 */
public class NSAL0480BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        String screenAplId = cMsg.getScreenAplID();

        try {

            if (screenAplId.startsWith("NSAL0480Scrn00")) {
                if (screenAplId.endsWith("SaveSearch")) {
                    doProcess_NSAL0480_SaveSearch((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("DeleteSearch")) {
                    doProcess_NSAL0480_DeleteSearch((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);
                // 2015/10/05 CSA Y.Tsuchimoto Add Start
                } else if (screenAplId.endsWith("CMN_ColSave")) {
                    ZYPGUITableColumn.setColData((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);
                } else if (screenAplId.endsWith("CMN_ColClear")) {
                    ZYPGUITableColumn.clearColData((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);
                }
                // 2015/10/05 CSA Y.Tsuchimoto Add End
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
            return;

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL0480_SaveSearch(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            cMsg.srchOptNm_S.setErrorInfo(1, MSG_ID.ZZM9000E.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Search Option Name") });
            return;
        }
        if (NSAL0480CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_S.setErrorInfo(1, MSG_ID.NSAM0059E.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Search Option Name") });
            return;
        }

        NSAL0480CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }

    private void doProcess_NSAL0480_DeleteSearch(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            cMsg.srchOptPk_S.setErrorInfo(1, MSG_ID.NSAM0199E.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Saved Search Options") });
            return;
        }

        NSAL0480CommonLogic.callNszc0330forDeleteSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }

}
