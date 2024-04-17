/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         T.Murai         Create          N/A
 * 2018/04/05   Fujitsu         Y.Kanefusa      Update          S21_NA#25014
 * </pre>
 */
public final class NWAL1840QueryForPricing extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1840QueryForPricing MY_INSTANCE = new NWAL1840QueryForPricing();

    /**
     * Constructor.
     */
    private NWAL1840QueryForPricing() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1840Query
     */
    public static NWAL1840QueryForPricing getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Price Category Code
     * @param bizMsg NWAL1840CMsg
     * @param prcCatgNm Price Category Name
     * @return Price Category Code
     */
    public S21SsmEZDResult getPrcCatgCd(NWAL1840CMsg bizMsg, String prcCatgNm) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prcCatgNm", prcCatgNm);

        return getSsmEZDClient().queryObject("getPrcCatgCd", queryParam);
    }

    // QC#25014 2018/04/05 Add Start
    public String getDsLineCatgCd(Map<String, Object> ssmParam) {

        String result = (String) getSsmEZDClient().queryObject("getDsLineCatgCd", ssmParam).getResultObject();

        return result;
    }
    // QC#25014 2018/04/05 Add End
}
