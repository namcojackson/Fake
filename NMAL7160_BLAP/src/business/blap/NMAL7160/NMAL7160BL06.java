/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7160;

import static business.blap.NMAL7160.constant.NMAL7160Constant.DO_PROCESS_SUBMIT;
import static business.blap.NMAL7160.constant.NMAL7160Constant.MSG_PARAM_CHECK_BOX;
import static business.blap.NMAL7160.constant.NMAL7160Constant.ZZM9000E;
import static business.blap.NMAL7160.constant.NMAL7160Constant.ZZZM9003I;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7160.common.NMAL7160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/20   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public class NMAL7160BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7160CMsg bizMsg = (NMAL7160CMsg) cMsg;
            NMAL7160SMsg glblMsg = (NMAL7160SMsg) sMsg;

            if ("NMAL7160Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     * @param isCallSave Called Save Button
     */
    private void doProcess_NMAL7160Scrn00_CMN_Submit(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        // Copy cMsg Check Info
        NMAL7160CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        if (checkList.size() == 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CHECK_BOX });
            }
            return;
        }

        if (!NMAL7160CommonLogic.uploadDsInvLine(bizMsg, glblMsg)) {
            return;
        }
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {DO_PROCESS_SUBMIT });
    }
}
