/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2350.common;

import static business.blap.NWAL2350.constant.NWAL2350Constant.DB_PARAM_DS_ORD_IMPT_PK;
import static business.blap.NWAL2350.constant.NWAL2350Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWAL2350.constant.NWAL2350Constant.NMAM0038I;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NWAL2350.NWAL2350CMsg;
import business.blap.NWAL2350.NWAL2350Query;
import business.blap.NWAL2350.NWAL2350SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * SOM Profitability
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2350CommonLogic {

    /**
     * search
     * @param cMsg NWAL2350CMsg
     * @param sMsg NWAL2350SMsg
     * @param glblCmpyCd String
     */
    public static void search(NWAL2350CMsg cMsg, NWAL2350SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DS_ORD_IMPT_PK, cMsg.dsImptOrdPk.getValue());

        S21SsmEZDResult result = NWAL2350Query.getInstance().search(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            EZDMsg.copy(sMsg, null, cMsg, null);
        } else {
            cMsg.setMessageInfo(NMAM0038I);
        }
    }
}
