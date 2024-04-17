/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLCL0270;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NLCL0270.constant.NLCL0270Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supersession Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 * 12/19/2018   CITS            T.Tokutomi      Update          QC#29214
 *</pre>
 */
public final class NLCL0270Query extends S21SsmEZDQuerySupport implements NLCL0270Constant {
    /**
     * Singleton instance.
     */
    private static final NLCL0270Query INSTANCE = new NLCL0270Query();

    /**
     * Constructor.
     */
    private NLCL0270Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWBL1010Query
     */
    public static NLCL0270Query getInstance() {
        return INSTANCE;
    }

    /**
     * <pre>
     * execute SSM id="getCoordinatorInfo" in [NLCL0270Query.xml]
     * </pre>
     * @param cMsg NLCL0270CMsg
     * @param sMsg NLCL0270SMsg
     * @param List<String> mdseList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSpssIvntyInfo(NLCL0270CMsg cMsg, NLCL0270SMsg sMsg, List<String> mdseList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_MDSE_LIST, mdseList);
        ssmParam.put("locStsCd",LOC_STS.DC_STOCK);

        return getSsmEZDClient().queryEZDMsgArray("getSpssIvntyInfo", ssmParam, 0, sMsg.A.length(), sMsg.A);
    }
    
    /**
     * Search SS
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSSPullDown(NLCL0270CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        return getSsmEZDClient().queryObjectList("searchSSPullDown", ssmParam);
    }

    // QC#29214 Add method.
    /**
     * getCompatibleItem
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return compatible item list
     */
    public List<String> getCompatibleItem(String glblCmpyCd, String mdseCd) {

        String targetMdseRelnTpCsv = ZYPCodeDataUtil.getVarCharConstValue("NPZC1010_CMPT_MDSE_RELN_TP", glblCmpyCd);
        List<String> targetMdseRelnTpList = null;
        if (targetMdseRelnTpCsv != null) {
            targetMdseRelnTpList = Arrays.asList(targetMdseRelnTpCsv.split(","));
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", glblCmpyCd);
        queryParam.put("MDSE_CD", mdseCd);
        queryParam.put("MDSE_ITEM_RELN_TP_CD", targetMdseRelnTpList);

        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getCompatibleItem", queryParam);

        if (result.isCodeNormal()) {
            return (List<String>) result.getResultObject();
        } else {
            // No Data.
            return new ArrayList<String>();
        }
    }
}
