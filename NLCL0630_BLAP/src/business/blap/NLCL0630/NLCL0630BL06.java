/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0630;

import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_CANCEL;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_BEGIN;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_UPLOAD;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0630.common.NLCL0630CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS         Makoto Okigami     Create          N/A
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 *</pre>
 */
public class NLCL0630BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0630_CANCEL.equals(screenAplID)) {
                doProcess_Cancel((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            // START 2018/12/03 T.Ogura [QC#27978,ADD]
            } else if (EVENT_NM_NLCL0630_BEGIN.equals(screenAplID)) {
                doProcess_Begin((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            } else if (EVENT_NM_NLCL0630_UPLOAD.equals(screenAplID)) {
                doProcess_Upload((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            // END   2018/12/03 T.Ogura [QC#27978,ADD]
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Search
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_Cancel(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.cancel(cMsg, sMsg, glblCmpyCd);

    }

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Begin
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_Begin(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.updateSts(cMsg, glblCmpyCd);

    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Upload
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_Upload(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.updateSts(cMsg, glblCmpyCd);

    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

}
