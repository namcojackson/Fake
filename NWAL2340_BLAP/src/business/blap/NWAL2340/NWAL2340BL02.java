/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2340;

import static business.blap.NWAL2340.constant.NWAL2340Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWAL2340.constant.NWAL2340Constant.DB_PARAM_MAX_ROWS;
import static business.blap.NWAL2340.constant.NWAL2340Constant.DB_PARAM_SELL_TO_CUST_CD;
import static business.blap.NWAL2340.constant.NWAL2340Constant.DB_PARAM_SHIP_TO_CUST_CD;
import static business.blap.NWAL2340.constant.NWAL2340Constant.EVENT_NM_NWAL2340_CMN_CLOSE;
import static business.blap.NWAL2340.constant.NWAL2340Constant.EVENT_NWAL2340SCRN00_MASSAPPLY;
import static business.blap.NWAL2340.constant.NWAL2340Constant.NWAM0168E;
import static business.blap.NWAL2340.constant.NWAL2340Constant.PARAM_VAL_MAX_ROWS;
import static business.blap.NWAL2340.constant.NWAL2340Constant.ZZZM9001E;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2340.common.NWAL2340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * SOM Address Mass Apply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/20   Fujitsu         R.Nakamura      Update          QC#21114
 *</pre>
 */
public class NWAL2340BL02 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            // Mod Start 2017/09/20 QC#21114
            if (EVENT_NM_NWAL2340_CMN_CLOSE.equals(screenAplID)) {
                doProcess_NWAL2340_CMN_Close((NWAL2340CMsg) cMsg, (NWAL2340SMsg) sMsg);
            } else if (EVENT_NWAL2340SCRN00_MASSAPPLY.equals(screenAplID)) {
                doProcess_NWAL2340Scrn00_MassApply((NWAL2340CMsg) cMsg);
            }
            // Mod End 2017/09/20 QC#21114

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2340_CMN_Close(NWAL2340CMsg cMsg, NWAL2340SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NWAL2340CommonLogic.checkRelation(cMsg, sMsg, glblCmpyCd);
    }

    // Add Start 2017/09/20 QC#21114
    private void doProcess_NWAL2340Scrn00_MassApply(NWAL2340CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.shipToCustAcctCd_H) && !ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H)) {
            cMsg.shipToCustAcctCd_H.setErrorInfo(1, NWAM0168E, new String[] {"Ship To Location" });
            cMsg.shipToCustCd_H.setErrorInfo(1, NWAM0168E, new String[] {"Ship To Location" });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SELL_TO_CUST_CD, cMsg.shipToCustAcctCd_H.getValue());
        ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, cMsg.shipToCustCd_H.getValue());
        ssmParam.put(DB_PARAM_MAX_ROWS, PARAM_VAL_MAX_ROWS);

        S21SsmEZDResult ssmResult = NWAL2340Query.getInstance().getAcctInfoList(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            cMsg.shipToCustAcctCd_H.setErrorInfo(1, ZZZM9001E, new String[] {"Ship To Location" });
            cMsg.shipToCustCd_H.setErrorInfo(1, ZZZM9001E, new String[] {"Ship To Location" });
            return;
        }

        List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (shipToCustInfoList.size() > 1) {
            cMsg.shipToCustAcctCd_H.setErrorInfo(1, NWAM0168E, new String[] {"Ship To Location" });
            cMsg.shipToCustCd_H.setErrorInfo(1, NWAM0168E, new String[] {"Ship To Location" });
            return;
        }
        NWAL2340CommonLogic.setAcctInfoList(cMsg, shipToCustInfoList.get(0));
    }
    // Add End 2017/09/20 QC#21114
}
