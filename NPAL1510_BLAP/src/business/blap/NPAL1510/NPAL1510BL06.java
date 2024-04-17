/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1510;

import static business.blap.NPAL1510.constant.NPAL1510Constant.NMAM0014E;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NMAM0182E;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_DELETE_SEARCH_OPTION;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_SAVE_SEARCH_OPTION;
import static business.blap.NPAL1510.constant.NPAL1510Constant.ZZM9000E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1510.common.NPAL1510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2016/02/22   CUSA            K.Ogino         Update          QC#4409
 *</pre>
 */
public class NPAL1510BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1510CMsg bizMsg = (NPAL1510CMsg) cMsg;
            NPAL1510SMsg glblMsg = (NPAL1510SMsg) sMsg;

            if (NPAL1510_SAVE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_SaveSearchOption(bizMsg, glblMsg);
            } else if (NPAL1510_DELETE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_DeleteSearchOption(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Save Search option
     * @param cMsg NPAL1510CMsg
     * @param sMsg NPAL1510SMsg
     */
    private void doProcess_SaveSearchOption(NPAL1510CMsg cMsg, NPAL1510SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            cMsg.srchOptNm_TX.setErrorInfo(1, ZZM9000E, new String[] {"Saved Option Name" });
            return;
        }

        if (NPAL1510CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_TX.setErrorInfo(1, NMAM0182E, new String[] {"Save", "Saved Option Name" });
            return;
        }

        NPAL1510CommonLogic.callNszc0330forSaveSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * Delete Search option
     * @param cMsg NPAL1510CMsg
     * @param sMsg NPAL1510SMsg
     */
    private void doProcess_DeleteSearchOption(NPAL1510CMsg cMsg, NPAL1510SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            cMsg.srchOptPk_SL.setErrorInfo(1, NMAM0014E, new String[] {"Saved Search Options" });
            return;
        }

        NPAL1510CommonLogic.callNszc0330forDeleteSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}
