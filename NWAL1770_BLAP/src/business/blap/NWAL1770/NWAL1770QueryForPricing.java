/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         T.Yoshida       Create          N/A
 * </pre>
 */
public final class NWAL1770QueryForPricing extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForPricing MY_INSTANCE = new NWAL1770QueryForPricing();

    /**
     * Constructor.
     */
    private NWAL1770QueryForPricing() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770Query
     */
    public static NWAL1770QueryForPricing getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Price Category Code
     * @param bizMsg NWAL1770CMsg
     * @param prcCatgNm Price Category Name
     * @return Price Category Code
     */
    public S21SsmEZDResult getPrcCatgCd(NWAL1770CMsg bizMsg, String prcCatgNm) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prcCatgNm", prcCatgNm);

        return getSsmEZDClient().queryObject("getPrcCatgCd", queryParam);
    }
}
