/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8880;

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
public final class NYEL8880Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8880Query MY_INSTANCE = new NYEL8880Query();

    /**
     * Private constructor
     */
    private NYEL8880Query() {
        super();
    }

    /**
     * Get the NYEL8850Query instance.
     * @return NYEL8850Query instance
     */
    public static NYEL8880Query getInstance() {
        return MY_INSTANCE;
    }
}
