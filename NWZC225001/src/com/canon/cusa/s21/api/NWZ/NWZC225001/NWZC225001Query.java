/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC225001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_IMPT_ORD_CONFIGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * NWZC2250 : Deal Configuration Order Creation API Query
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/21   Fujitsu         T.Ishii         Create          N/A
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NWZC225001Query {

    /**
     * Singleton instance.
     */
    private static final NWZC225001Query myInstance = new NWZC225001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    protected static NWZC225001Query getInstance() {

        return myInstance;
    }

    /**
     * query map list
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    protected List<Map<String, Object>> queryMapList(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(queryId, ssmParam);

        if (result == null) {

            return new ArrayList<Map<String, Object>>();
        }

        return result;
    }

    /**
     * query map
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    protected Map<String, Object> queryMap(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(queryId, ssmParam);

        if (result == null || result.isEmpty()) {

            return null;
        }
        return result;
    }

    /**
     * query string
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return String
     */
    protected String queryString(String queryId, Map<String, Object> ssmParam) {

        String result = (String) ssmBatchClient.queryObject(queryId, ssmParam);

        return result;
    }

    /**
     * query string list
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<String>
     */
    protected List<String> queryStringList(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<String> result = (List<String>) ssmBatchClient.queryObjectList(queryId, ssmParam);

        if (result == null) {

            return new ArrayList<String>();
        }

        return result;
    }

    /**
     * query big decimal
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    protected BigDecimal queryBigDecimal(String queryId, Map<String, Object> ssmParam) {

        BigDecimal result = (BigDecimal) ssmBatchClient.queryObject(queryId, ssmParam);

        return result;
    }

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /**
     * Get Default Accessory Install Type
     * @param String ordSrcRefNum
     * @param cMsg NWAL2240CMsg
     * @return Map<String, Object>
     */
    protected Map<String, Object> getDefaultAccessoryInstTypeWithConfNum(String ordSrcRefNum, DS_IMPT_ORD_CONFIGTMsg config) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", config.glblCmpyCd.getValue());
        ssmParam.put("ordSrcRefNum", ordSrcRefNum);
        ssmParam.put("dsOrdPosnNum", config.dsOrdPosnNum.getValue());
        ssmParam.put("ConfigCatgCd", config.configCatgCd.getValue());
        List<String> configTpCdList = new ArrayList<String>();
        configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
        ssmParam.put("configTpCdList", configTpCdList);
        ssmParam.put("imptLineFlg", ZYPConstant.FLG_ON_Y);

        return (Map<String, Object>) ssmBatchClient.queryObject("getDefaultAccessoryInstTypeWithConfNum", ssmParam);
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Get Deinstall Information
     * @param String ordSrcRefNum
     * @param DS_IMPT_ORD_CONFIGTMsg config
     * @return Result
     */
    protected Map<String, Object> getDeinstallInfo(String ordSrcRefNum, DS_IMPT_ORD_CONFIGTMsg config) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", config.glblCmpyCd.getValue());
        ssmParam.put("ordSrcRefNum", ordSrcRefNum);
        ssmParam.put("dsOrdPosnNum", config.dsOrdPosnNum.getValue());
        ssmParam.put("ConfigCatgCd", config.configCatgCd.getValue());

        return (Map<String, Object>) ssmBatchClient.queryObject("getDeinstallInfo", ssmParam);
    }

    /**
     * Get Model Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsImptOrdConfigPk
     * @param BigDecimal svcMachMstrPk
     * @return Result
     */
    protected Map<String, Object> getMdlSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsImptOrdConfigPk, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsImptOrdConfigPk", dsImptOrdConfigPk);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcDeinsRuleNum", ssmParam);
    }

    /**
     * Get Mdse Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsImptOrdConfigPk
     * @return Result
     */
    protected Map<String, Object> getMdseSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsImptOrdConfigPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsImptOrdConfigPk", dsImptOrdConfigPk);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcDeinsRuleNum", ssmParam);
    }
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]
}
