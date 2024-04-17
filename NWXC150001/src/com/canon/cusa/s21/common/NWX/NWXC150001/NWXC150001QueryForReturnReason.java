/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NWXC150001Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/08/21   Fujitsu         S.Takami        Create          S21_NA#26767
 * 2019/12/18   Fujitsu         S.Takami        Update          S21_NA#54223
 * </pre>
 */
public class NWXC150001QueryForReturnReason extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWXC150001QueryForReturnReason MY_INSTANCE = new NWXC150001QueryForReturnReason();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    private S21LRUMap<String, String> varCharConstRsltMap = new S21LRUMap<String, String>();

    // 2019/12/18 S21_NA#54223 Del Start
//    private S21LRUMap<Map<String, String>, List<Map<String, String>>> configTpRsltMap = new S21LRUMap<Map<String, String>, List<Map<String, String>>>();
    // 2019/12/18 S21_NA#54223 Del End
    /**
     * @return MY_INSTANCE
     */
    protected static NWXC150001QueryForReturnReason getInstance() {
        return MY_INSTANCE;
    }

    protected List<Map<String, String>> getRelatedReturnReasonList(String glblCmpyCd, String dsOrdTpCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getRelatedReturnReasonList", ssmParam);
    }

    protected Map<String, Object> getDefaultRtrnRsnLineCatgByIb(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("ctxTpIbTrxOrdMap", "IB_TRX_ORD_MAP");

        return (Map<String, Object>) ssmBatchClient.queryObject("getDefaultRtrnRsnLineCatgByIb", ssmParam);
    }

    protected List<String> getVarCharConstValue(String varCharConstNm, String glblCmpyCd) {

        List<String> rsltList = new ArrayList<String>(0);
        String cacheKey = glblCmpyCd + "." + varCharConstNm;
        String varcharVal = this.varCharConstRsltMap.get(cacheKey);

        if (varcharVal == null) {
            varcharVal = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, glblCmpyCd);
            if (varcharVal != null) {
                this.varCharConstRsltMap.put(cacheKey, varcharVal);
            }
        }
        if (varcharVal != null) {
            String[] varcharValArry = varcharVal.split("\\,");
            for (String varcharValStr: varcharValArry) {
                rsltList.add(varcharValStr);
            }
        }
        return rsltList;
    }

    protected List<Map<String, String>> getRelatedConfigTpList(String glblCmpyCd, String cpoSrcTpCd, String dsOrdTpCd, String configCatgCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("configCatgCd", configCatgCd);

        // 2019/12/18 S21_NA#54223 Mod Start
//        List<Map<String, String>> rsltMapList = this.configTpRsltMap.get(ssmParam);
//        if (rsltMapList == null) {
//            rsltMapList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getRelatedConfigTpList", ssmParam);
//            if (rsltMapList != null) {
//                this.configTpRsltMap.put(ssmParam, rsltMapList);
//            }
//        }
//        return rsltMapList;
        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getRelatedConfigTpList", ssmParam);
        // 2019/12/18 S21_NA#54223 Mod End
    }
}
