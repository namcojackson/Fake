/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NMAL0250;

import static business.blap.NMAL0250.constant.NMAL0250Constant.BIND_GLBL_CMPY_CD;
import static business.blap.NMAL0250.constant.NMAL0250Constant.BIND_PRNT_MDSE_CD;
import static business.blap.NMAL0250.constant.NMAL0250Constant.BIND_ROWNUM;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/28/2018   CITS            K.Kameoka       Create          #22324
 *</pre>
 */
public final class NMAL0250Query extends S21SsmEZDQuerySupport {

    /**
     * 
     */
    private static final NMAL0250Query MY_INSTANCE = new NMAL0250Query();

    private NMAL0250Query() {
        super();
    }

    /**
     * @return NMAL0250Query
     */
    public static NMAL0250Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param rowNum rowNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoMessageList(String glblCmpyCd, String mdseCd, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRNT_MDSE_CD, mdseCd);
        params.put(BIND_ROWNUM, rowNum);
        return getSsmEZDClient().queryObjectList("getBomMessageList", params);
    }

}
