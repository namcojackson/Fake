/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0140;

import static business.blap.NMAL0140.constant.NMAL0140Constant.SCREEN_ID;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL0140.common.NMAL0140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0140BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRAA         K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0140BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0140CMsg bizMsg = (NMAL0140CMsg) cMsg;
            NMAL0140SMsg glblMsg = (NMAL0140SMsg) sMsg;

            if ("NMAL0140Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL0140Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL0140Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL0140Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NMAL0140Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NMAL0140Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

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
    private void doProcess_NMAL0140Scrn00_DeleteSearch(NMAL0140CMsg bizMsg, NMAL0140SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, "NMAM0014E" // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Saved Search Options") });
            return;
        }

        NMAL0140CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0140Scrn00_SaveSearch(NMAL0140CMsg bizMsg, NMAL0140SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "ZZM9000E" //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Search Option Name") });
            return;
        }
        if (NMAL0140CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "NMAM0182E" // You can not [@] this record Because of [@] already exists.
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCREEN_ID, "Save") //
                            , converter.convLabel2i18nLabel(SCREEN_ID, "Search Option Name") });
            return;
        }

        NMAL0140CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}
