/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6810;


import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Item Master Template Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public final class NMAL6810Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NMAL6810Query MY_INSTANCE = new NMAL6810Query();

    /**
     * Constructor.
     */
    private NMAL6810Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6810Query
     */
    public static NMAL6810Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getMdseCratTmpl
     * @param cMsg NMAL6810CMsg
     * @param sMsg NMAL6810SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseCratTmpl(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
        ssmParam.put("mdseItemTpCd", cMsg.mdseItemTpCd_HS.getValue());
        ssmParam.put("mdseCratTmplNm", cMsg.mdseCratTmplNm_H.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getMdseCratTmpl", ssmParam, sMsg.A);
    }
}
