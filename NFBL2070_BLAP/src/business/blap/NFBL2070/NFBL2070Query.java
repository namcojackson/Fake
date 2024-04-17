/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2070;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Compensation Credit I/F Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   CSAI            Miyauchi        Create          N/A
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 * </pre>
 */
public class NFBL2070Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFBL2070Query myInstance = new NFBL2070Query();

    /**
     * Constructor
     */
    public NFBL2070Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFBL2070Query
     */
    public static NFBL2070Query getInstance() {
        return myInstance;
    }

	/**
     * <pre>
     * statementId = "findDetailData"
     * </pre>
     *
     * find Vender Invoice Data
     * @param cMsg Business Message
     * @param sMsg Global Message
     * @return S21SsmEZDResult
    */
    public S21SsmEZDResult findDetailData(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        Map<String, Object> queryPrm = new HashMap<String, Object>();
        queryPrm.put("glblCmpyCd", getGlobalCompanyCode());
        queryPrm.put("cMsg",cMsg);
        queryPrm.put("maxRow",sMsg.A.length() + 1); // ADD 2016/11/28 [QC#16158]

        return getSsmEZDClient().queryObjectList("findDetailData", queryPrm);
    }

}