/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0480;

import static business.blap.NSBL0480.constant.NSBL0480Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0480.common.NSBL0480CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Access Permits Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0480BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0480CMsg cMsg = (NSBL0480CMsg) arg0;
        NSBL0480SMsg sMsg = (NSBL0480SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0480Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480Scrn00_CMN_Submit(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int length = 0;
        int validCount = 0;
        int pageFromNum = 0;
        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            length = cMsg.A.length();
            validCount = cMsg.A.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            if (!cMsg.techCd_A1.getValue().equals(cMsg.techCd_A2.getValue())) {
                cMsg.techCd_A1.setErrorInfo(1, NSBM0086E, new String[] {cMsg.techCd_A1.getValue(), cMsg.techCd_A2.getValue() });
                return;
            }
        } else {
            length = cMsg.B.length();
            validCount = cMsg.B.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            if (!cMsg.svcAccsPmitNum.getValue().equals(cMsg.techCd_B2.getValue())) {
                cMsg.svcAccsPmitNum.setErrorInfo(1, NSBM0086E, new String[] {cMsg.svcAccsPmitNum.getValue(), cMsg.techCd_B2.getValue() });
                return;
            }
        }

        if (validCount > 0) {
            NSBL0480CommonLogic.setUpdateFlg(cMsg, sMsg);
            NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
            cMsg.setCommitSMsg(true);

            if (!NSBL0480CommonLogic.isErrorSubmitCondition(cMsg, sMsg)) {
                if (ZYPCommonFunc.hasValue(sMsg.xxErrNum)) {
                    int pageNum = NSBL0480CommonLogic.convertPageNumToFirstRowIndex(length, sMsg.xxErrNum.getValueInt() + 1);
                    NSBL0480CommonLogic.pagenation(cMsg, sMsg, pageNum);
                }
                return;
            }

            NSBL0480CommonLogic.updateProcess(cMsg, sMsg);

            if (ZYPCommonFunc.hasValue(sMsg.xxErrNum)) {
                pageFromNum = NSBL0480CommonLogic.convertPageNumToFirstRowIndex(length, sMsg.xxErrNum.getValueInt() + 1);
                NSBL0480CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
                return;
            }
            NSBL0480CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        } else {
            NSBL0480CommonLogic.updateProcess(cMsg, sMsg);
        }
    }

}
