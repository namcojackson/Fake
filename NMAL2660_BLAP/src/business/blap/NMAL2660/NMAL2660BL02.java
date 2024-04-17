/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2660;


import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import static business.blap.NMAL2660.constant.NMAL2660Constant.NZZM0001W;
import static business.blap.NMAL2660.constant.NMAL2660Constant.NMAM0007I;
import static business.blap.NMAL2660.constant.NMAL2660Constant.SCREEN_MAX_ROW_SIZE;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL2660BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2660BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2660CMsg bizMsg = (NMAL2660CMsg) cMsg;

            if ("NMAL2660_INIT".equals(screenAplID)) {
                doProcess_NMAL2660_INIT(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2660_INIT(NMAL2660CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        NMAL2660Query query = NMAL2660Query.getInstance();
        S21SsmEZDResult ssmResult = query.getResourceAssignList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0007I);
            return;
        }


        if (ssmResult.getQueryResultCount() > SCREEN_MAX_ROW_SIZE) {
            bizMsg.setMessageInfo(NZZM0001W);
            bizMsg.A.setValidCount(SCREEN_MAX_ROW_SIZE);
        }

    }


}
