/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7000;

import static business.blap.NMAL7000.constant.NMAL7000Constant.SCRN_ID_00;
import static business.blap.NMAL7000.constant.NMAL7000Constant.NMAM0014E;
import static business.blap.NMAL7000.constant.NMAL7000Constant.NMAM0182E;
import static business.blap.NMAL7000.constant.NMAL7000Constant.ZZM9000E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NMAL7000.common.NMAL7000CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;


/**
 *<pre>
 * NMAL7000BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7000BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7000CMsg bizMsg = (NMAL7000CMsg) cMsg;
            NMAL7000SMsg glblMsg = (NMAL7000SMsg) sMsg;

            if ("NMAL7000Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NMAL7000Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NMAL7000Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_DeleteSearch(bizMsg, glblMsg);
            } else if ("NMAL7000Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_SaveSearch(bizMsg, glblMsg);
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
    private void doProcess_NMAL7000Scrn00_DeleteSearch(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1)) {
            bizMsg.srchOptPk_H1.setErrorInfo(1, NMAM0014E
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL7000CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7000Scrn00_SaveSearch(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H1)) {
            bizMsg.srchOptNm_H1.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL7000CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_H1.setErrorInfo(1, NMAM0182E
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL7000CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}
