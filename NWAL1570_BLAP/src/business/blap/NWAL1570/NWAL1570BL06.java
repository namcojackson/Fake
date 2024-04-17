/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1570;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import static business.blap.NWAL1570.constant.NWAL1570Constant.NWAM0697E;
import static business.blap.NWAL1570.constant.NWAL1570Constant.NWAM0837E;
import static business.blap.NWAL1570.constant.NWAL1570Constant.ZZM9000E;
import static business.blap.NWAL1570.constant.NWAL1570Constant.SCRN_ID_00;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import business.blap.NWAL1570.common.NWAL1570CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;

/**
 *<pre>
 * NWAL1570BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1570BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1570CMsg bizMsg = (NWAL1570CMsg) cMsg;
            NWAL1570SMsg glblMsg = (NWAL1570SMsg) sMsg;
            if ("NWAL1570Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWAL1570Scrn00_DeleteSearch(bizMsg, glblMsg);
            } else if ("NWAL1570Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWAL1570Scrn00_SaveSearch(bizMsg, glblMsg);
            } else if ("NWAL1570Scrn01_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NWAL1570Scrn01_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NWAL1570Scrn02_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NWAL1570Scrn02_CMN_ColClear".equals(screenAplID)) {
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
    private void doProcess_NWAL1570Scrn00_DeleteSearch(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1)) {
            bizMsg.srchOptPk_H1.setErrorInfo(1, NWAM0697E
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NWAL1570CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn00_SaveSearch(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H1)) {
            bizMsg.srchOptNm_H1.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NWAL1570CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_H1.setErrorInfo(1, NWAM0837E
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NWAL1570CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }


}
