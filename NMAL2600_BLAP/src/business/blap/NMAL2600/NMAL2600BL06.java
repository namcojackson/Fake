/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2600;

import static business.blap.NMAL2600.constant.NMAL2600Constant.SCRN_ID_00;
import static business.blap.NMAL2600.constant.NMAL2600Constant.ZZM9000E;
import static business.blap.NMAL2600.constant.NMAL2600Constant.NMAM0182E;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2600.common.NMAL2600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Org Resource Search  NMAL2600BL06
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/21   Fujitsu         Mz.Takahashi    Create          QC#11068
 * </pre>
 */
public class NMAL2600BL06 extends S21BusinessHandler {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL2600Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_SaveSearch((NMAL2600CMsg) cMsg, (NMAL2600SMsg) sMsg);
            } else if ("NMAL2600Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_DeleteSearch((NMAL2600CMsg) cMsg, (NMAL2600SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_SaveSearch(NMAL2600CMsg bizMsg, NMAL2600SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL2600CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM0182E // You can not [@] this record Because of [@] already exists.
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL2600CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

    }

    /**
     * doProcess_NMAL2600Scrn00_DeleteSearch
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NMAL2600Scrn00_DeleteSearch(NMAL2600CMsg bizMsg, NMAL2600SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, ZZM9000E // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL2600CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
}
