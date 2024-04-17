/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0630;

import static business.blap.NSAL0630.constant.NSAL0630Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0630.common.NSAL0630CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract On Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/11/04   Hitachi         T.Tomita        Update          QC#4210
 * 2017/02/10   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0630BL06 extends S21BusinessHandler {

    // START 2016/11/04 T.Tomita [QC#4210, MOD]
    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL0630CMsg cMsg = (NSAL0630CMsg) ezdCMsg;
        NSAL0630SMsg sMsg = (NSAL0630SMsg) ezdSMsg;
        String screenAplId = cMsg.getScreenAplID();
        if ("NSAL0630Scrn00_CMN_Submit".equals(screenAplId)) {
            return checkInput_NSAL0630Scrn00_CMN_Submit(cMsg, sMsg);
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
        }
    }

    private boolean checkInput_NSAL0630Scrn00_CMN_Submit(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        boolean rtnVal = true;
        // START 2017/02/10 K.Ochiai [QC#16331, ADD]
        NSAL0630CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // END   2017/02/10 K.Ochiai [QC#16331, ADD]
        if (!NSAL0630CommonLogic.checkSelect(cMsg, sMsg)) {
            rtnVal = false;
            return rtnVal;
        }
        if (!hasValue(cMsg.svcMemoRsnCd_H)) {
            cMsg.svcMemoRsnCd_H.setErrorInfo(1, NSAM0362E, new String[] {SVC_MEMO });
            rtnVal = false;
        }
        if (!hasValue(cMsg.svcCmntTxt)) {
            cMsg.svcCmntTxt.setErrorInfo(1, NSAM0362E, new String[] {SVC_MEMO });
            rtnVal = false;
        }
        return rtnVal;
    }

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0630CMsg cMsg = (NSAL0630CMsg) arg0;
        NSAL0630SMsg sMsg = (NSAL0630SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0630Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0630Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0630Scrn00_CMN_Submit(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        NSAL0630CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0630CommonLogic.updateContract(cMsg, sMsg);
    }
    // END 2016/11/04 T.Tomita [QC#4210, MOD]
}
