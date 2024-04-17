/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0280;

import static business.blap.NSBL0280.constant.NSBL0280Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0280.common.NSBL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Resource Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0280BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0280CMsg cMsg = (NSBL0280CMsg) arg0;
        NSBL0280SMsg sMsg = (NSBL0280SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0280Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0280Scrn00_CMN_Submit(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        int length = 0;
        int validCount = 0;
        int pageFromNum = 0;
        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            length = cMsg.A.length();
            validCount = cMsg.A.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            if (!cMsg.psnCd.getValue().equals(cMsg.psnCd_A.getValue())) {
                cMsg.psnCd.setErrorInfo(1, NSBM0086E, new String[] {cMsg.psnCd.getValue(), cMsg.psnCd_A.getValue() });
                return;
            }
        } else {
            length = cMsg.B.length();
            validCount = cMsg.B.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        }

        if (validCount > 0) {
            NSBL0280CommonLogic.setUpdateFlg(cMsg, sMsg);
            NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
            cMsg.setCommitSMsg(true);

            if (!NSBL0280CommonLogic.isErrorSubmitCondition(cMsg, sMsg)) {
                if (ZYPCommonFunc.hasValue(sMsg.xxNum)) {
                    int pageNum = NSBL0280CommonLogic.convertPageNumToFirstRowIndex(length, sMsg.xxNum.getValueInt() + 1);
                    NSBL0280CommonLogic.pagenation(cMsg, sMsg, pageNum);
                }
                return;
            }

            NSBL0280CommonLogic.updateProcess(cMsg, sMsg);

            if (ZYPCommonFunc.hasValue(sMsg.xxNum)) {
                pageFromNum = NSBL0280CommonLogic.convertPageNumToFirstRowIndex(length, sMsg.xxNum.getValueInt() + 1);
                NSBL0280CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
                return;
            }
            NSBL0280CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        } else {
            NSBL0280CommonLogic.updateProcess(cMsg, sMsg);
        }
    }

}
