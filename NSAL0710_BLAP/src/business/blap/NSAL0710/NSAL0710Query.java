/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0710;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Update Read Methods
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/18   Hitachi         K.Kasai         Create          N/A
 * 2016/11/18   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public final class NSAL0710Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0710Query INSTANCE = new NSAL0710Query();

    /**
     * Constructor.
     */
    private NSAL0710Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0710Query
     */
    public static NSAL0710Query getInstance() {
        return INSTANCE;
    }

    // mod start 2016/11/18 CSA QC#4210
    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0710_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0710_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
    }
    // mod end 2016/11/18 CSA QC#4210
}
