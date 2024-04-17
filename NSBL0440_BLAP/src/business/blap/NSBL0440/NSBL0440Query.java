/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0440;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Mods Machine Level Status
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2022/09/08   CITS            E.Sanchez       Update          QC#60527
 *</pre>
 */
public final class NSBL0440Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0440Query INSTANCE = new NSBL0440Query();

    /**
     * Constructor.
     */
    private NSBL0440Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0440Query
     */
    public static NSBL0440Query getInstance() {
        return INSTANCE;
    }

    /**
     * get DtlItem Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSBL0440_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDtlInfo(Map<String, Object> ssmParam, NSBL0440_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getDtlInfo", ssmParam, aSMsgArray);
    }

    // START 2022/09/08 E.Sanchez [QC#60527, ADD]
    /**
     * get Svc Mod Serial Range Info
     * @param glblCmpyCd String
     * @param svcModPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcModSerRng(String glblCmpyCd, BigDecimal svcModPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcModPk", svcModPk);

        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getSvcModSerRng", ssmPrm);
        if (rslt != null && rslt.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            return rsltList;
        }
        return new ArrayList<Map<String, Object>>();
    }
    // START 2022/09/08 E.Sanchez [QC#60527, ADD]
}
