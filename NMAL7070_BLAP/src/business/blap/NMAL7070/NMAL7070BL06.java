/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7070;

import static business.blap.NMAL7070.constant.NMAL7070Constant.NMAM0014E;
import static business.blap.NMAL7070.constant.NMAL7070Constant.NMAM0182E;
import static business.blap.NMAL7070.constant.NMAL7070Constant.SCRN_ID_00;
import static business.blap.NMAL7070.constant.NMAL7070Constant.ZZM9000E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL7070.common.NMAL7070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7070CMsg bizMsg = (NMAL7070CMsg) cMsg;
            NMAL7070SMsg glblMsg = (NMAL7070SMsg) sMsg;

            if ("NMAL7070Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_Delete_Search".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_Delete_Search(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_Save_Search".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_Save_Search(bizMsg, glblMsg);
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
    private void doProcess_NMAL7070Scrn00_Delete_Search(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            bizMsg.srchOptPk.setErrorInfo(1, NMAM0014E, new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL7070CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7070Scrn00_Save_Search(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk) && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            bizMsg.srchOptNm.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL7070CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm.setErrorInfo(1, NMAM0182E, new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL7070CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}
