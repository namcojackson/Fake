/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0550;

import java.math.BigDecimal;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/08/23   Hitachi         K.Yamada        Update          CSA QC#2148
 * 2016/11/10   Hitachi         K.Ochiai        Update          CSA QC#2148
 * 2017/10/12   CITS            M.Naito         Update          CSA QC#21762
 *</pre>
 */
public final class NSAL0550Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0550Query INSTANCE = new NSAL0550Query();

    /**
     * Constructor.
     */
    private NSAL0550Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0740Query
     */
    public static NSAL0550Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get Service Invoice List.
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0550_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcInvList(Map<String, Object> ssmParam, NSAL0550_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSvcInvList", ssmParam, aSMsgArray);
    }

    /**
     * Get Service Invoice List.
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcInvChrgTypeList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSvcInvChrgTypeList", ssmParam);
    }

    // add start 2016/08/23 CSA Defect#2148
    /**
     * getCtacInfo
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacInfo(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getCtacInfo", ssmParam);
    }
    // add end 2016/08/23 CSA Defect#2148

    // ADD START 2016/11/10 CSA QC#2148
    /**
     * getSvcInvChgTpCnt
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public BigDecimal getSvcInvChgTpCnt(Map<String, String> ssmParam) {
        return  (BigDecimal)getSsmEZDClient().queryObject("getSvcInvChgTpCnt", ssmParam).getResultObject();
    }
    // ADD End 2016/11/10 CSA QC#2148

    // ADD START 2017/10/12 CSA QC#21762
    /**
     * getCreditAndRebillCnt
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public BigDecimal getCreditAndRebillCnt(Map<String, String> ssmParam) {
        return  (BigDecimal)getSsmEZDClient().queryObject("getCreditAndRebillCnt", ssmParam).getResultObject();
    }
    // ADD END 2017/10/12 CSA QC#21762
}
