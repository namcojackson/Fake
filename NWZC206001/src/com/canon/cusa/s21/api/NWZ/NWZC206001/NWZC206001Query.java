/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC206001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 *  Supersede API (Query)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/24/2013   Fujitsu         D.Yanagisawa    Create          R-MS013-002
 * 2015/12/22   Hitachi         K.Yamada        Update          CSA QC#1411
 *</pre>
 */
public class NWZC206001Query {

    /** SSM Client */
    private final S21SsmBatchClient ssmBatchClient;

    /**
     *<pre>
     * Constructor
     *</pre>
     */
    public NWZC206001Query() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * Get Supersede Information [To]
     * @param param NWZC206001PMsg
     * @param supdFromMdseCd String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSupdInfoTo(NWZC206001PMsg param, String supdFromMdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("supdFromMdseCd", supdFromMdseCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSupdInfoTo", ssmParam);
    }

    /**
     * Get Supersede Information [From]
     * @param param NWZC206001PMsg
     * @param supdToMdseCd String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSupdInfoFrom(NWZC206001PMsg param, String supdToMdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("supdToMdseCd", supdToMdseCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSupdInfoFrom", ssmParam);
    }

    /**
     * Get Supersede Information [From] As Interchange Flag is N
     * @param param NWZC206001PMsg
     * @param supdToMdseCd String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSupdInfoFromIncgFlgN(NWZC206001PMsg param, String supdToMdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("supdToMdseCd", supdToMdseCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSupdInfoFromIncgFlgN", ssmParam);
    }

    /**
     * Get Inventory Available Quantity
     * @param param NWZC206001PMsg
     * @param mdseList Set<String>
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getInvtyAvalQty(NWZC206001PMsg param, Set<String> mdseCdList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("mdseCdList", mdseCdList);
        ssmParam.put("whCd", param.whCd.getValue());
        ssmParam.put("stkStsCd", param.stkStsCd.getValue());
        // add start 2015/12/22 CSA Defect#1411
        ssmParam.put("locTpWh", LOC_TP.WAREHOUSE);
        ssmParam.put("locTpTech", LOC_TP.TECHNICIAN);
        // add end 2015/12/22 CSA Defect#1411

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvtyAvalQty", ssmParam);

    }

}
