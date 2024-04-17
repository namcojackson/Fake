/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0660;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Add general Notes
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kasai         Create          N/A
 * 2016/12/06   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public final class NSAL0660Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0660Query INSTANCE = new NSAL0660Query();

    /**
     * Constructor.
     */
    private NSAL0660Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0660Query
     */
    public static NSAL0660Query getInstance() {
        return INSTANCE;
    }
// mod start 2016/12/06 CSA QC#4210
    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0660_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0660_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
    }
// mod end 2016/12/06 CSA QC#4210
}
