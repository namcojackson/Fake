/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/22/2012   CSAI            N.Sasaki        Created         ITG#397579
 * </pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB009001;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/31/2012  CSAI         N.Sasaki     Update         ITG#397579
 *</pre>
 */
public final class NLCB009001Query extends S21SsmEZDQuerySupport {

    /** */
    private static final NLCB009001Query MYINSTANCE = new NLCB009001Query();

    /**
     */
    private NLCB009001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NLCB009001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param ssmParam Map
     * @return String
     */
    public String getVarCharConstVal(Map<String, Object> ssmParam) {
        return (String) getSsmEZDClient().queryObject("getVarCharConstVal", ssmParam).getResultObject();
    }

    /**
     * @param ssmParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getWhCdByWmsWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWhCdByWmsWh", ssmParam, -1, -1);
    }

    /**
     * @param ssmParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getEstoreWmsWhInvtyInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getEstoreWmsWhInvtyInfo", ssmParam, -1, -1);
    }

    /**
     * @param ssmParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getWrkForEStore(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWrkForEStore", ssmParam, -1, -1);
    }
}
