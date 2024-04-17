/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1160;

import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_COL_CLEAR;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_COL_SAVE;
import static business.blap.NPAL1160.constant.NPAL1160Constant.EVENT_NM_NPAL1160_CMN_SUBMIT;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1160.common.NPAL1160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 11/14/2017   CITS            T.Tokutomi      Create          QC#18689-Sol#303
 * 06/12/2018   CITS            S.Katsuma       Update          QC#26582
 *</pre>
 */
public class NPAL1160BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String glblCmpyCd = getGlobalCompanyCode();

            // START 2018/06/12 S.Katsuma [QC#26582,ADD]
            cMsg.setCommitSMsg(true);
            // END 2018/06/12 S.Katsuma [QC#26582,ADD]

            if (EVENT_NM_NPAL1160_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg);
            } else if (EVENT_NM_NPAL1160_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1160Scrn00_CMN_Submit((NPAL1160CMsg) cMsg, (NPAL1160SMsg) sMsg, glblCmpyCd);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * <pre>
     * Submit Event
     * </pre>
     * @param sMsg NPAL1160CMsg
     * @param cMsg NPAL1160SMsg
     */
    private void doProcess_NPAL1160Scrn00_CMN_Submit(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (!NPAL1160CommonLogic.checkInput(cMsg, sMsg, glblCmpyCd)) {
            return;
        }
        if (!NPAL1160CommonLogic.submitDelete(cMsg, sMsg, glblCmpyCd)) {
            return;
        }
        NPAL1160CommonLogic.submitInsertUpdate(cMsg, sMsg, glblCmpyCd);
    }

}
