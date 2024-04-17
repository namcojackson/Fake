/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL6710;

import static business.blap.NYEL8810.constant.NYEL8810Constant.SCRN_ID_00;
import static business.blap.NMAL6710.constant.NMAL6710Constant.NMAM0182E;
import static business.blap.NMAL6710.constant.NMAL6710Constant.ZZM9000E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.workflow.core.context.S21WfHumanTaskExecutionContext;
import com.canon.cusa.s21.framework.workflow.ezd.business.S21WfBusinessHandlerWF02Support;

/**
 *<pre>
 *  Account Search BLAP 06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 09/21/2016   Fujitsu         Mz.Takahashi    Update          QC#11068
 *</pre>
 */
public class NMAL6710BL06 extends S21WfBusinessHandlerWF02Support {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg, S21WfHumanTaskExecutionContext wfExecCtx) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NMAL6710Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_SaveSearch((NMAL6710CMsg) cMsg, (NMAL6710SMsg)sMsg);
            } else if ("NMAL6710Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcessNMAL6710Scrn00_DeleteSearch((NMAL6710CMsg) cMsg, (NMAL6710SMsg)sMsg);
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
    private void doProcess_NMAL6710Scrn00_SaveSearch(NMAL6710CMsg bizMsg, NMAL6710SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL6710CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM0182E // You can not [@] this record Because of [@] already exists.
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL6710CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

    }

    private void doProcessNMAL6710Scrn00_DeleteSearch(NMAL6710CMsg bizMsg, NMAL6710SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, ZZM9000E // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL6710CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
    
    

}
