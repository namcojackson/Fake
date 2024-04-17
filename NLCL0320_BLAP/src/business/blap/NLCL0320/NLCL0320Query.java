/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLCL0320;

import java.util.Map;

import business.blap.NLCL0320.NLCL0320CMsg;
import business.blap.NLCL0320.NLCL0320SMsg;
import business.blap.NLCL0320.NLCL0320Query;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public final class NLCL0320Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0320Query myInstance = new NLCL0320Query();

    /**
     * Constructor.
     */
    private NLCL0320Query() {
        super();
    }

    public static NLCL0320Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getAdjAcctAlias(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg, Map<String, Object> prmMap) {
        return getSsmEZDClient().queryEZDMsgArray("getAdjAcctAlias", prmMap, globalMsg.A);
    }

    public S21SsmEZDResult countDuplicate(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("countDuplicate", prmMap);
    }
}
