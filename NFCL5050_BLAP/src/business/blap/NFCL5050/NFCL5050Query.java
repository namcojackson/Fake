/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Fujitsu         Y.Ookawa        Create          N/A
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 11/05/2010   Fujitsu         T.Tanaka        Update          M81
 * 06/02/2011   Canon           Y.Aikawa        Update          DefID:2271
 *</pre>
 */
package business.blap.NFCL5050;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * NFCL5050Query.
 */
public final class NFCL5050Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL5050Query MY_INSTANCE = new NFCL5050Query();

    /**
     * Constructor
     */
    public NFCL5050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL5050Query
     */
    public static NFCL5050Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL5050SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findApplicationInfoList(Map map, NFCL5050SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findApplicationInfoList", map, sMsg.A);
    }

}
