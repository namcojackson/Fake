/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8850;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8850Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public final class NYEL8850Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8850Query MY_INSTANCE = new NYEL8850Query();

    /**
     * Private constructor
     */
    private NYEL8850Query() {
        super();
    }

    /**
     * Get the NYEL8850Query instance.
     * @return NYEL8850Query instance
     */
    public static NYEL8850Query getInstance() {
        return MY_INSTANCE;
    }
}
