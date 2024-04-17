/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NWDL0260;

import java.util.HashMap;
import java.util.Map;

import business.blap.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class NWDL0260Query extends S21SsmEZDQuerySupport implements NWDL0260Constant {

    private static final NWDL0260Query INSTANCE = new NWDL0260Query();

    public static NWDL0260Query getInstance() {
        return INSTANCE;
    }

    private NWDL0260Query() {
        super();
    }

    S21SsmEZDResult getHidingInventoryList(NWDL0260CMsg cMsg, NWDL0260_ASMsgArray sMsgArray) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg",       cMsg);
        ssmParam.put("rowNum",     sMsgArray.length() + 1);
        
        // Hidden by me
        if (Y.equals(cMsg.xxChkBox_HD.getValue())) {
            ssmParam.put("hidUserId", getContextUserInfo().getUserId());
        }

        return getSsmEZDClient().queryEZDMsgArray("getHidingInventoryList", ssmParam, sMsgArray);
    }

}
