/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import static business.blap.NWAL1840.constant.NWAL1840Constant.NUM_OF_SA_HISTORY;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/10/10   Hitachi         T.Fukuta        Create          CSA-QC#61730
 * </pre>
 */
public class NWAL1840QueryForSaHistory extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1840QueryForSaHistory MY_INSTANCE = new NWAL1840QueryForSaHistory();

    /**
     * Constructor.
     */
    private NWAL1840QueryForSaHistory() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1840QueryForSaHistory
     */
    public static NWAL1840QueryForSaHistory getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get SA History
     * @param bizMsg NWAL1840CMsg
     * @return SA History
     */
    public S21SsmEZDResult getSaHistory(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("serNum", bizMsg.serNum.getValue());
        params.put("numOfHistory", NUM_OF_SA_HISTORY);

        return getSsmEZDClient().queryObjectList("getSaHistory", params);
    }
}
