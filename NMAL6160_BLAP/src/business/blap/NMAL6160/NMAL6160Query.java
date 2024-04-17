/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6160;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Multi Candinate Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NMAL6160Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL6160Query MY_INSTANCE = new NMAL6160Query();

    /**
     * Private constructor
     */
    private NMAL6160Query() {
        super();
    }

    /**
     * Get the NMAL6160Query instance.
     * @return NMAL6160Query instance
     */
    public static NMAL6160Query getInstance() {
        return MY_INSTANCE;
    }

}
