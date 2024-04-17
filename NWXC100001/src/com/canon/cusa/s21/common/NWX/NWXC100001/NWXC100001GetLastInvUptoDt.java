/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Common parts to get Invoice Up to Date, Close Date.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/07/2013   Fujitsu         Y.Murasaki      Create          WDS R-OM050
 * </pre>
 */
public class NWXC100001GetLastInvUptoDt {

    /**
     * This class.
     */
    private static final Class CLAZZ = NWXC100001GetLastInvUptoDt.class;

    /**
     * Search DS_CONTR, DS_CONTR_DT
     * @param glblCmpyCd Global Company Code.
     * @param svcConfigMstrPk SVC_CONFIG_MSTR PK
     * @return list of NWXC100001GetLastInvUptoDtBean.
     */
    @SuppressWarnings("unchecked")
    public static List<NWXC100001GetLastInvUptoDtBean> getLastInvUptoDt(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        List<NWXC100001GetLastInvUptoDtBean> resultList = new ArrayList<NWXC100001GetLastInvUptoDtBean>();

        if (glblCmpyCd == null || svcConfigMstrPk == null) {
            return resultList;
        }

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(CLAZZ);
        Map<String, Object> queryKey = new HashMap<String, Object>();
        queryKey.put("glblCmpyCd", glblCmpyCd);
        queryKey.put("svcConfigMstrPk", svcConfigMstrPk);
        queryKey.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.INACTIVE);
        queryKey.put("dsContrTpCd", DS_CONTR_TP.WARRANTY);

        List<Map<String, Object>> objList = ssmBatchClient.queryObjectList("getLastInvUptoDt", queryKey);

        if (objList != null) {
            for (Map<String, Object> map : objList) {

                resultList.add(setColumnValues(map, svcConfigMstrPk));

                // Search Supply
                if (map.get("FLEET_DS_CONTR_DTL_PK") != null) {
                    Map<String, Object> supQueryKey = new HashMap<String, Object>();
                    supQueryKey.put("glblCmpyCd", glblCmpyCd);
                    supQueryKey.put("prntDsContrDtlPk", (BigDecimal) map.get("FLEET_DS_CONTR_DTL_PK"));
                    supQueryKey.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.INACTIVE);
                    supQueryKey.put("dsContrTpCd", DS_CONTR_TP.WARRANTY);
                    supQueryKey.put("splyLineFlg", ZYPConstant.FLG_ON_Y);

                    List<Map<String, Object>> supobjList = ssmBatchClient.queryObjectList("getSupplyLastInvUptoDt", supQueryKey);
                    if (objList != null) {
                        for (Map<String, Object> supMap : supobjList) {
                            resultList.add(setColumnValues(supMap, svcConfigMstrPk));
                        }
                    }
                }
            }
        }
        return resultList;
    }

    private static NWXC100001GetLastInvUptoDtBean setColumnValues(Map<String, Object> map, BigDecimal svcConfigMstrPk) {

        NWXC100001GetLastInvUptoDtBean bean = new NWXC100001GetLastInvUptoDtBean();

        bean.setSvcConfigMstrPk(svcConfigMstrPk);
        bean.setDsContrNum((String) map.get("DS_CONTR_NUM"));
        bean.setDsContrSqNum((String) map.get("DS_CONTR_SQ_NUM"));
        bean.setDsContrTpCd((String) map.get("DS_CONTR_TP_CD"));
        bean.setDsContrDtlPk((BigDecimal) map.get("DS_CONTR_DTL_PK"));
        bean.setDsContrDtlTpCd((String) map.get("DS_CONTR_DTL_TP_CD"));
        bean.setCloDt((String) map.get("CONTR_CLO_DT"));

        // set latest date as Inv Up To Date.
        if (map.get("MTR_INV_UP_TO_DT") == null && map.get("BASE_INV_UP_TO_DT") == null) {
            bean.setInvUpToDt(null);
        } else if (map.get("MTR_INV_UP_TO_DT") == null && map.get("BASE_INV_UP_TO_DT") != null) {
            bean.setInvUpToDt((String) map.get("BASE_INV_UP_TO_DT"));
        } else if (map.get("MTR_INV_UP_TO_DT") != null && map.get("BASE_INV_UP_TO_DT") == null) {
            bean.setInvUpToDt((String) map.get("MTR_INV_UP_TO_DT"));
        } else {
            if (new BigDecimal((String) map.get("MTR_INV_UP_TO_DT")).compareTo(new BigDecimal((String) map.get("BASE_INV_UP_TO_DT"))) >= 1) {
                bean.setInvUpToDt((String) map.get("MTR_INV_UP_TO_DT"));
            } else {
                bean.setInvUpToDt((String) map.get("BASE_INV_UP_TO_DT"));
            }
        }
        return bean;
    }

}
