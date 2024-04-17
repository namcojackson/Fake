/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * They are common parts in OM getting Work Flow Approvers from  table.
 * For Trial Loan, Sales(Trial), Trial to sales.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/17/2010   Fujitsu         S.Takami        Create          5728
 * </pre>
 */
public class NWXC001001GetApproversListBySalesRep {
    /**
     * This class name.
     */
    private static final String CLASS_NM = NWXC001001GetApproversListBySalesRep.class.getSimpleName();

    /**
     * This class.
     */
    private static final Class CLAZZ = NWXC001001GetApproversListBySalesRep.class;

    /**
     * This fucntion get Work Flow Approvers from
     * table.
     * @param glblCmpyCd Global Company Code.
     * @param slsRepCd Sales Rep Code.
     * @return list of Approvers(String).
     */
    public static List<String> getApproversListBySalesRep(String glblCmpyCd, String slsRepCd) {
        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(CLAZZ);

        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", glblCmpyCd);
        if (slsRepCd != null) {
            queryKey.put("slsRepCd", slsRepCd);
        } else {
            queryKey.put("slsRepCd", null);
        }

        List<String> approversList = (List) ssmBatchClient.queryObjectList("getApproversListBySalesRep", queryKey);
        return approversList;
    }

}
