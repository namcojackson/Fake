/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC002001.constant.NSXC002001Constant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service intangible mdseCd
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/25/2013   Fujitsu         S.Nakai         Create          N/A
 *</pre>
 */
public class NSXC002001SvcIntgMdse implements NSXC002001Constant {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC002001SvcIntgMdse.class);

    /**
     * <pre>
     * get parts intangible mdse
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @return intg mdse cd
     */
    public static String getPrtsIntgMdse(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        //TODO
//        String intgMdseCd = (String) SSM_CLIENT.queryObject("getIntgMdseCd", params);
        String intgMdseCd = mdseCd;

        return intgMdseCd;
    }
}
