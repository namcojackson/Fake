/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0650;

import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0394W;
import static business.blap.NSAL0650.constant.NSAL0650Constant.MTR_EST_TP;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0362E;
import static business.blap.NSAL0650.constant.NSAL0650Constant.SVC_MEMO;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0650.common.NSAL0650CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 * Update Auto Estimation Round
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 * 2016/12/02   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0650BL06 extends S21BusinessHandler {

    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL0650CMsg cMsg = (NSAL0650CMsg) ezdCMsg;
        NSAL0650SMsg sMsg = (NSAL0650SMsg) ezdSMsg;
        String screenAplId = cMsg.getScreenAplID();
        if ("NSAL0650Scrn00_CMN_Submit".equals(screenAplId)) {
            return checkInput_NSAL0650Scrn00_CMN_Submit(cMsg, sMsg);
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
        }
    }

    private boolean checkInput_NSAL0650Scrn00_CMN_Submit(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        boolean rtnVal = true;
        // mod start 2016/12/02 CSA QC#4210
        int pageIdx = 0;
        NSAL0650CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        if (NSAL0650CommonLogic.checkSelect(cMsg, sMsg)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NSAL0650_ASMsg asMsg = sMsg.A.no(i);
                if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue())) {
                    if (!hasValue(asMsg.mtrEstTpCd_AH)) {
                        asMsg.mtrEstTpCd_AH.setErrorInfo(1, NSAM0362E, new String[] {MTR_EST_TP });
                        if (rtnVal) {
                            pageIdx = i;
                        }
                        rtnVal = false;
                    }
                }
            }
            if (!rtnVal) {
                cMsg.setMessageInfo(NSAM0394W);
                NSAL0650CommonLogic.showTargetPage(cMsg, sMsg, pageIdx);
            }
            // mod end 2016/12/02 CSA QC#4210
        } else {
            rtnVal = false;
            return rtnVal;
        }
        if (!hasValue(cMsg.svcMemoRsnCd_H)) {
            cMsg.svcMemoRsnCd_H.setErrorInfo(1, NSAM0362E, new String[] {SVC_MEMO });
            rtnVal = false;
        }
        if (!hasValue(cMsg.svcCmntTxt_H)) {
            cMsg.svcCmntTxt_H.setErrorInfo(1, NSAM0362E, new String[] {SVC_MEMO });
            rtnVal = false;
        }
        return rtnVal;
    }

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0650CMsg cMsg = (NSAL0650CMsg) arg0;
        // mod start 2016/12/02 CSA QC#4210
        NSAL0650SMsg sMsg = (NSAL0650SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0650Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0650Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0650Scrn00_CMN_Submit(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        NSAL0650CommonLogic.updateContract(cMsg, sMsg);
        // mod end 2016/12/02 CSA QC#4210
    }
}
