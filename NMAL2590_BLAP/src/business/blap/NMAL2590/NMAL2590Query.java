/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2590;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL2590 Geo Code Search Popup
 * </pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/07   Hitachi         T.mizuki        Create          CSA-QC#4096
 *</pre>
 */
public final class NMAL2590Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2590Query MY_INSTANCE = new NMAL2590Query();

    /**
     * Constructor.
     */
    private NMAL2590Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL2590Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * searchList
     * @param bizMsg NMAL2590CMsg
     * @param glblMsg NMAL2590SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchList(NMAL2590CMsg bizMsg, NMAL2590SMsg glblMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("postCd", bizMsg.postCd.getValue());
        ssmParam.put("stCd", bizMsg.stCd.getValue());
        ssmParam.put("ctyAddr", bizMsg.ctyAddr.getValue());
        ssmParam.put("cntyNm", bizMsg.cntyNm.getValue());

        return getSsmEZDClient().queryEZDMsgArray("searchList", ssmParam, glblMsg.A);
    }
}
