/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0290;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0290.common.NSBL0290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0290BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0290CMsg cMsg = (NSBL0290CMsg) arg0;
        NSBL0290SMsg sMsg = (NSBL0290SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0290Scrn00_CMN_Submit(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        if (cMsg.A.getValidCount() > 0) {
            NSBL0290CommonLogic.setUpdateFlg(cMsg, sMsg);

            int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
            NSBL0290CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
            cMsg.setCommitSMsg(true);

            if (!NSBL0290CommonLogic.isErrorSearchCondition(cMsg, sMsg)) {
                if (ZYPCommonFunc.hasValue(sMsg.xxNum)) {
                    int pageNum = NSBL0290CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), sMsg.xxNum.getValueInt() + 1);
                    NSBL0290CommonLogic.pagenation(cMsg, sMsg, pageNum);
                }
                return;
            }

            NSBL0290CommonLogic.updateProcess(cMsg, sMsg);

            if (ZYPCommonFunc.hasValue(sMsg.xxNum)) {
                pageFromNum = NSBL0290CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), sMsg.xxNum.getValueInt() + 1);
            }

            NSBL0290CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        } else {
            NSBL0290CommonLogic.updateProcess(cMsg, sMsg);
        }
    }

}
