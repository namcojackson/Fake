/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC260001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NMZC260001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 *  Supersede API (Query)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/27   Fujitsu         N.Sugiura       Create          Create
 * 2016/07/25   Fujitsu         N.Sugiura       Update          QC#11812
 * 2017/10/31   Fujitsu         H.Sugawara      Update          QC#20146(Sol#092)
 * 2018/04/02   Fujitsu         K.Ishizuka      Update          QC#24860
 * 2018/11/09   Fujitsu         M.Ohno          Update          QC#29010
 *</pre>
 */
public class NMZC260001Query {

    /** SSM Client */
    private final S21SsmBatchClient ssmBatchClient;

    /**
     *<pre>
     * Constructor
     *</pre>
     */
    public NMZC260001Query() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * Get Default Sales Rep, Mod 2020/04/24 QC#56638
     * @param param NMZC260001PMsg
     * @param isShipBase boolean
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDefSlsRep(NMZC260001PMsg param, boolean isShipBase) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        if (isShipBase) {
            ssmParam.put("shipToCustCd", param.shipToCustCd.getValue());
        } else if (!isShipBase && ZYPCommonFunc.hasValue(param.billToCustCd)){
            ssmParam.put("billToCustCd", param.billToCustCd.getValue());
        } else {
            ssmParam.put("shipToCustCd", param.shipToCustCd.getValue());
        }

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDefSlsRep", ssmParam);
    }
    // 2016/07/08 QC#11812 Add Start
    /**
     * Get Default Sales Rep for Ds Order Type
     * @param param NMZC260001PMsg
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDefSlsRepForDsOrderType(NMZC260001PMsg param, List<String> trtyGrpTpList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", param.dsOrdTpCd.getValue());
        // 2018/11/09 S21_NA#29010 Add Start
        ssmParam.put("trtyGrpTpList", trtyGrpTpList);
        // 2018/11/09 S21_NA#29010 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDefSlsRepForDsOrderType", ssmParam);
    }
    /**
     * Get Primary Sales Rep
     * @param glblCmpyCd String
     * @param lineBizTpCd String
     * @return Map<String, Object>
     */
    public String getPrimRep(String glblCmpyCd, String lineBizTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("lineBizTpCd", lineBizTpCd);

        return (String) ssmBatchClient.queryObject("getPrimRep", ssmParam);
    }
    // 2016/07/08 QC#11812 Add End

    // Add Start 2017/10/31 QC#20146(Sol#092)
    /**
     * Get FM Dummy Rel
     * @param param NMZC260001PMsg
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getFMDummyRep(NMZC260001PMsg param) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("shipToCustAcctCd", param.shipToCustAcctCd.getValue());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFMDummyRep", ssmParam);
    }
    // Add End 2017/10/31 QC#20146(Sol#092)
    
    // 2018/04/02 S21_NA#24860 Add Start
    /**
     * check Exist Order Category
     * @param pMsg NWZC150001PMsg
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @return true: exist
     */
    public boolean isExistOrdCatg(NMZC260001PMsg pMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.DUMMY_SLS_REP_FOR_PPS_PARTS);
        params.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        params.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());

        return 0 < (Integer) ssmBatchClient.queryObject("isExistOrdCatg", params);
    }
    // 2018/04/02 S21_NA#24860 Add End

}
