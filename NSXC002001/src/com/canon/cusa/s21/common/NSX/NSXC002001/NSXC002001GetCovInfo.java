/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Coverage Information
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/17/2015   Hitachi         T.Tsuchida      Create          NA#Service Pricing API
 * </pre>
 */
public class NSXC002001GetCovInfo {

    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC002001GetCovInfo.class);

    /**
     * Get Coverage Information
     * @param glblCmpyCd Global Company Code
     * @param svcTaskNum Service Task Number
     * @param svcMachMstrPk Service Machine Master PK
     * @return Coverage Information Bean
     */
    public static CovInfoBean getCovInfo(String glblCmpyCd, String svcTaskNum, BigDecimal svcMachMstrPk) {
        List<CovInfoBean> covInfoBeanList = new ArrayList<CovInfoBean>();
        CovInfoBean covInfoBean = new CovInfoBean();
        if (checkParameter(glblCmpyCd, svcTaskNum, svcMachMstrPk)) {
            if (svcTaskNum == null || "".equals(svcTaskNum)) {
                covInfoBean = getCovInfoBySvcMachMstrPk(glblCmpyCd, svcMachMstrPk);
            } else {
                covInfoBean = getCovInfoBySvcTaskNum(glblCmpyCd, svcTaskNum);
            }
        }
        return covInfoBean;
    }

    private static CovInfoBean getCovInfoBySvcTaskNum(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcTaskNum", svcTaskNum);
        return (CovInfoBean) SSM_CLIENT.queryObjectList("getCovInfoBySvcTaskNum", ssmPrm);
    }

    private static CovInfoBean getCovInfoBySvcMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return (CovInfoBean) SSM_CLIENT.queryObject("getCovInfoBySvcMachMstrPk", ssmPrm);
    }

    private static boolean checkParameter(String glblCmpyCd, String svcTaskNum, BigDecimal svcMachMstrPk) {
        if (glblCmpyCd == null || "".equals(glblCmpyCd)) {
            return false;
        }
        if ((svcTaskNum == null || "".equals(svcTaskNum))
                && (svcMachMstrPk == null || svcMachMstrPk.compareTo(BigDecimal.ZERO) <= 0)) {
            return false;
        }
        return true;
    }
}
