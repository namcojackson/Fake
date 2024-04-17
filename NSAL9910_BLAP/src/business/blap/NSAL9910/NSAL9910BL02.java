/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL9910;

import static business.blap.NSAL9910.constant.NSAL9910Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * General Business Master Maintenance List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL9910BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL9910CMsg cMsg = (NSAL9910CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL9910_INIT".equals(screenAplID)) {
                doProcess_NSAL9910_INIT(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL9910_INIT(NSAL9910CMsg cMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPTableUtil.clear(cMsg.A);

        if (!hasValue(cMsg.bizFuncGrpId) || cMsg.B.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        S21SsmEZDResult ssmResult = NSAL9910Query.getInstance().getTrgtTbl(cMsg, cMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }
}
