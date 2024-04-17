/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2012   CSAI            N.Sasaki        Update          ITG#382732
 * </pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC008001;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 */
public final class NLXC008001Query extends S21SsmEZDQuerySupport {

    /** */
    private static final NLXC008001Query MYINSTANCE = new NLXC008001Query();

    /**
     */
    private NLXC008001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NLXC008001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param param Map < String, Object >
     * @return checkDuplication
     */
    public S21SsmEZDResult checkSerialEvent(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkSerialEvent", ssmParam, -1, -1);
    }
}
