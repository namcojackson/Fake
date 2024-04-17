package business.blap.NMAL7440;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * NMAL7440Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 *</pre>
 */
public final class NMAL7440Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7440Query MY_INSTANCE = new NMAL7440Query();

    /**
     * Private constructor
     */
    private NMAL7440Query() {
        super();
    }

    /**
     * Get the NMAL7440Query instance.
     * @return NMAL7440Query instance
     */
    public static NMAL7440Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getPrcGrpTrgtTp
     * @param bizMsg NMAL7440CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcGrpTrgtTp(NMAL7440CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcGrpTpCd", bizMsg.prcGrpTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getPrcGrpTrgtTp", params);
    }

    /**
     * getPrcGrpAttrb
     * @param bizMsg NMAL7440CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcGrpAttrb(NMAL7440CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcGrpTpCd", bizMsg.prcGrpTpCd.getValue());
        params.put("prcGrpTrgtTpCd", bizMsg.prcGrpTrgtTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getPrcGrpAttrb", params);
    }
}
