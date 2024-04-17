/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2020;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2020Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public final class NWAL2020Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2020Query MY_INSTANCE = new NWAL2020Query();

    /**
     * Private constructor
     */
    private NWAL2020Query() {
        super();
    }

    /**
     * Get the NWAL2020Query instance.
     * @return NWAL2020Query instance
     */
    public static NWAL2020Query getInstance() {
        return MY_INSTANCE;
    }
}