/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2120;

import static business.blap.NFBL2120.constant.NFBL2120Constant.GET_SEARCH_CONDITION_FLD_NM_LIST;
import static business.blap.NFBL2120.constant.NFBL2120Constant.GET_SEARCH_RESULT_FLD_NM_LIST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NFBL2120Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public final class NFBL2120Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFBL2120Query MY_INSTANCE = new NFBL2120Query();

    /**
     * Private constructor
     */
    private NFBL2120Query() {
        super();
    }

    /**
     * Get the NFBL2120Query instance.
     * @return NFBL2120Query instance
     */
    public static NFBL2120Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getThereforeCatgoryNo
     * @param bizMsg NFBL2120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getThereforeCatgoryNo(NFBL2120CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.docMgtCatgCd_H)) {
            params.put("docMgtCatgCdList", new String[] {bizMsg.docMgtCatgCd_H.getValue()});
        } else {
            List<String> docMgtCatgList = new ArrayList<String>();
            for (int i = 0; i < bizMsg.docMgtCatgCd_L.length(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.docMgtCatgCd_L.no(i))) {
                    docMgtCatgList.add(bizMsg.docMgtCatgCd_L.no(i).getValue());
                } else {
                    break;
                }
            }

            String[] docMgtCatgArray = new String[docMgtCatgList.size()];
            for (int i = 0; i < docMgtCatgList.size(); i++) {
                docMgtCatgArray[i] = docMgtCatgList.get(i);
            }
            params.put("docMgtCatgCdList", docMgtCatgArray);
        }

        return getSsmEZDClient().queryObjectList("getThereforeCatgoryNo", params);
    }

    /**
     * Search
     * @param docMgtCatgCd String
     * @param isCondition boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchDocMgtFld(String docMgtCatgCd, boolean isCondition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("docMgtCatgCd", docMgtCatgCd);
        if (isCondition) {
            // for Search Condition Field.
            params.put("docMgtFldDescTxtList", GET_SEARCH_CONDITION_FLD_NM_LIST);
        } else {
            // for Search Result Field.
            params.put("docMgtFldDescTxtList", GET_SEARCH_RESULT_FLD_NM_LIST);
        }

        return getSsmEZDClient().queryObjectList("getSearchDocMgtFld", params);
    }
}
