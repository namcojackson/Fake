/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Supply Contract Info
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/13   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class NSXC001001GetSplyContrInfo {

    public static String PROC_STS_SUCCESS = "0";

    public static String PROC_STS_NOT_FOUND = "1";

    public static String PROC_STS_INPUT_ERR = "2";

    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NSXC001001GetSplyContrInfo.class);

    /**
     * getSplyContrInfo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrNum String
     * @param dsContrSqNum String
     * @param dsContrDtlPk BigDecimal
     * @param svcConfigMstrPk BigDecimal
     * @param mdseCd String
     * @return
     */
    public static List<SplyContrInfoBean> getSplyContrInfo(String glblCmpyCd, BigDecimal dsContrPk, String dsContrNum, String dsContrSqNum, BigDecimal dsContrDtlPk, BigDecimal svcConfigMstrPk, String mdseCd) {

        List<BigDecimal> dsContrDtlPkList = null;

        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            dsContrDtlPkList = new ArrayList<BigDecimal>();
            dsContrDtlPkList.add(dsContrDtlPk);
        }

        return getSplyContrInfo(glblCmpyCd, dsContrPk, dsContrNum, dsContrSqNum, dsContrDtlPkList, svcConfigMstrPk, mdseCd);
    }

    /**
     * getSplyContrInfo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrNum String
     * @param dsContrSqNum String
     * @param dsContrDtlPkList List<BigDecimal>
     * @param svcConfigMstrPk BigDecimal
     * @param mdseCd String
     * @return
     */
    public static List<SplyContrInfoBean> getSplyContrInfo(String glblCmpyCd, BigDecimal dsContrPk, String dsContrNum, String dsContrSqNum, List<BigDecimal> dsContrDtlPkList, BigDecimal svcConfigMstrPk, String mdseCd) {

        if (!inputCheck(glblCmpyCd, dsContrPk, dsContrNum, dsContrSqNum)) {
            return getStsList(PROC_STS_INPUT_ERR);
        }

        List<SplyContrInfoBean> infoList = getSplyContrList(glblCmpyCd, dsContrPk, dsContrNum, dsContrSqNum, dsContrDtlPkList, svcConfigMstrPk, mdseCd);

        if (infoList == null || infoList.size() == 0) {
            return getStsList(PROC_STS_NOT_FOUND);
        }

        return getStsList(infoList, PROC_STS_SUCCESS);
    }

    @SuppressWarnings("unchecked")
    private static List<SplyContrInfoBean> getSplyContrList(String glblCmpyCd, BigDecimal dsContrPk, String dsContrNum, String dsContrSqNum, List<BigDecimal> dsContrDtlPkList, BigDecimal svcConfigMstrPk, String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrPk", dsContrPk);
        queryParam.put("dsContrNum", dsContrNum);
        queryParam.put("dsContrSqNum", dsContrSqNum);
        queryParam.put("dsContrDtlPkList", dsContrDtlPkList);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        queryParam.put("shpgStsCdSaved", SHPG_STS.SAVED);
        queryParam.put("shpgStsCdCancelled", SHPG_STS.CANCELLED);

        return (List<SplyContrInfoBean>) ssmClient.queryObjectList("getSplyContrList", queryParam);
    }

    private static boolean inputCheck(String glblCmpyCd, BigDecimal dsContrPk, String dsContrNum, String dsContrSqNum) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(dsContrPk) && (!ZYPCommonFunc.hasValue(dsContrNum) || !ZYPCommonFunc.hasValue(dsContrSqNum))) {
            return false;
        }
        return true;
    }

    private static List<SplyContrInfoBean> getStsList(String stsCd) {
        List<SplyContrInfoBean> infoList = new ArrayList<SplyContrInfoBean>();
        return getStsList(infoList, stsCd);
    }

    private static List<SplyContrInfoBean> getStsList(List<SplyContrInfoBean> infoList, String stsCd) {
        if (infoList.size() == 0) {
            SplyContrInfoBean infoBean = new SplyContrInfoBean();
            infoBean.setProcStsCd(stsCd);
            infoList.add(infoBean);
        } else {
            infoList.get(0).setProcStsCd(stsCd);
        }
        return infoList;
    }
}
