/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0060;


import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLGL0060.common.NLGL0060CommonLogic;
import business.blap.NLGL0060.constant.NLGL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/09   CITS            M.Furugoori         Create          N/A
 *</pre>
 */
public class NLGL0060BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            String scrnAplId = cMsg.getScreenAplID();

            if ("NLGL0060Scrn00_CMN_Submit".equals(scrnAplId)) {
                doProcess_CMN_Submit((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg, getGlobalCompanyCode());
            } else {
                throw new S21AbendException("Illegal Screen Application Id. : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * CMN_Submit
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     * @param glblCmpyCd String
     */
    private void doProcess_CMN_Submit(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg, String glblCmpyCd) {

        NLGL0060CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        boolean changedFlg = false;

        int firstErrIdx = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            boolean removeFlg = false;
            boolean updateFlg = false;

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                removeFlg = true;
                changedFlg = true;
            }
            if (NLGL0060CommonLogic.changedValue(sMsg.A.no(i).procStsCd_A1.getValue(), sMsg.A.no(i).procStsCd_BK.getValue())) {
                updateFlg = true;
                changedFlg = true;

                if (NLGL0060Constant.PROC_STS_CD_ERROR.equals(sMsg.A.no(i).procStsCd_A1.getValue())) {
                    sMsg.A.no(i).procStsCd_A1.setErrorInfo(1, NLGL0060Constant.NLGM0060E, new String[] {"Error", "Error" });
                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

            }

            if (removeFlg && updateFlg) {
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLGL0060Constant.NLGM0035E);
                sMsg.A.no(i).procStsCd_A1.setErrorInfo(1, NLGL0060Constant.NLGM0035E);
                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }

            }
        }

        if (!changedFlg) {
            cMsg.setMessageInfo(NLGL0060Constant.NLGM0091E);
            return;

        }

        if (firstErrIdx != -1) {
            int startIdx = (firstErrIdx / cMsg.A.length()) * cMsg.A.length();

            NLGL0060CommonLogic.saveSMsgToCMsg(cMsg, sMsg, startIdx);
            return;

        }

        // Submit
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                // logical remove
                NLGL0060CommonLogic.deleteRow(cMsg, sMsg, i);
            }
            if (NLGL0060CommonLogic.changedValue(sMsg.A.no(i).procStsCd_A1.getValue(), sMsg.A.no(i).procStsCd_BK.getValue())) {
                // update
                NLGL0060CommonLogic.updateRow(cMsg, sMsg, i);
            }
        }

        int fromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;

        NLGL0060CommonLogic.saveSMsgToCMsg(cMsg, sMsg, fromIdx);
        return;

    }

}
