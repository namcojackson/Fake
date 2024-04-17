/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2360.common;

import static business.blap.NWAL2360.constant.NWAL2360Constant.DB_PARAM_DS_ORD_IMPT_PK;
import static business.blap.NWAL2360.constant.NWAL2360Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWAL2360.constant.NWAL2360Constant.NMAM0038I;
import static business.blap.NWAL2360.constant.NWAL2360Constant.NZZM0001W;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NWAL2360.NWAL2360CMsg;
import business.blap.NWAL2360.NWAL2360Query;
import business.blap.NWAL2360.NWAL2360SMsg;

import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * SOM Approval Detail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2360CommonLogic {

    /**
     * search
     * @param cMsg NWAL2360CMsg
     * @param sMsg NWAL2360SMsg
     * @param glblCmpyCd String
     */
    public static void search(NWAL2360CMsg cMsg, NWAL2360SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DS_ORD_IMPT_PK, cMsg.dsImptOrdPk.getValue());
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        S21SsmEZDResult result = NWAL2360Query.getInstance().search(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }

                // time stamp for display
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtTm_FR, ZYPDateUtil.formatEzd17ToDisp(NWXC412001.getValidTimeStamp(sMsg.A.no(i).wfStartTs.getValue())));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtTm_TO, ZYPDateUtil.formatEzd17ToDisp(NWXC412001.getValidTimeStamp(sMsg.A.no(i).wfEndTs.getValue())));

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
        }
    }
}
