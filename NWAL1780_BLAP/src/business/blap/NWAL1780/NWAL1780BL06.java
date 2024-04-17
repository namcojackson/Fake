/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1780;

import static business.blap.NWAL1780.constant.NWAL1780Constant.SCRN_ID_00;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWAL1780.common.NWAL1780CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public class NWAL1780BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1780CMsg bizMsg = (NWAL1780CMsg) cMsg;
            NWAL1780SMsg glblMsg = (NWAL1780SMsg) sMsg;

            if ("NWAL1780Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_DeleteSearch(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWAL1780Scrn00_SaveSearch(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NWAL1780Scrn00_CMN_ColClear".equals(screenAplID)) {
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
    private void doProcess_NWAL1780Scrn00_DeleteSearch(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, "NSAM0199E" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NWAL1780CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1780Scrn00_SaveSearch(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        if (!NWAL1780CommonLogic.searchValidCheck(bizMsg)) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "ZZM9000E", new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        if (NWAL1780CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "NSAM0059E", new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NWAL1780CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId());
    }
}
