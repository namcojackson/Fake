/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0020;

import static business.blap.NSAL0020.constant.NSAL0020Constant.SCRN_ID;
import static business.blap.NSAL0020.constant.NSAL0020Constant.MSG_ID;

import parts.common.EZDCMsg;

import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0020.common.NSAL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0020BL06 extends S21BusinessHandler implements ZYPConstant {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0020CMsg cMsg = (NSAL0020CMsg) arg0;
        NSAL0020SMsg sMsg = (NSAL0020SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);
        String screenAplId = cMsg.getScreenAplID();
        try {
            if (screenAplId.startsWith("NSAL0020Scrn00")) {
                if (screenAplId.endsWith("SaveSearch")) {
                    doProcess_NSAL0020_SaveSearch((NSAL0020CMsg) cMsg, (NSAL0020SMsg) sMsg);
                } else if (screenAplId.endsWith("DeleteSearch")) {
                    doProcess_NSAL0020_DeleteSearch((NSAL0020CMsg) cMsg, (NSAL0020SMsg) sMsg);
                } else if (screenAplId.endsWith("CMN_ColSave")) {
                    ZYPGUITableColumn.setColData((NSAL0020CMsg) cMsg, (NSAL0020SMsg) sMsg);
                } else if (screenAplId.endsWith("CMN_ColClear")) {
                    ZYPGUITableColumn.clearColData((NSAL0020CMsg) cMsg, (NSAL0020SMsg) sMsg);
                }
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0020_SaveSearch(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            cMsg.srchOptNm_S.setErrorInfo(1, MSG_ID.ZZM9000E.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Search Option Name") });
            return;
        }
        if (NSAL0020CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_S.setErrorInfo(1, MSG_ID.NSAM0059E.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Search Option Name") });
            return;
        }

        NSAL0020CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }

    private void doProcess_NSAL0020_DeleteSearch(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            cMsg.srchOptPk_S.setErrorInfo(1, MSG_ID.NSAM0199E.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Saved Search Options") });
            return;
        }

        NSAL0020CommonLogic.callNszc0330forDeleteSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }
}
