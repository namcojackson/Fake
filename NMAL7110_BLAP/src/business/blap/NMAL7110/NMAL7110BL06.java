package business.blap.NMAL7110;

import static business.blap.NMAL7110.constant.NMAL7110Constant.NMAM0014E;
import static business.blap.NMAL7110.constant.NMAL7110Constant.NMAM0182E;
import static business.blap.NMAL7110.constant.NMAL7110Constant.SCRN_ID_00;
import static business.blap.NMAL7110.constant.NMAL7110Constant.ZZM9000E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NMAL7110.common.NMAL7110CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7110BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NMAL7110BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7110CMsg bizMsg = (NMAL7110CMsg) cMsg;
            NMAL7110SMsg glblMsg = (NMAL7110SMsg) sMsg;

            if ("NMAL7110Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_DeleteSearch(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_SaveSearch(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_DeleteSearch(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            bizMsg.srchOptPk.setErrorInfo(1, NMAM0014E
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL7110CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_SaveSearch(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            bizMsg.srchOptNm.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL7110CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm.setErrorInfo(1, NMAM0182E
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL7110CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}
