/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3000.common;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/04/25   Fujitsu         S.Takami        Create          QC#50078
 *</pre>
 */
public class NFCL3000CommonLogicForConst implements NFCL3000Constant {

    /** Instance of NFCL3000CommonLogicForConst */
    private static final NFCL3000CommonLogicForConst myInstance = new NFCL3000CommonLogicForConst();

    /** Map Of Varchar Const Name and Value */
    private Map<String, String> varCharConstMap = null;

    /** Private Constractor */
    private NFCL3000CommonLogicForConst() {

        this.varCharConstMap = new HashMap<String, String>(0);
    }

    /**
     * get instance of NFCL3000CommonLogicForConst.
     * @return instance of NFCL3000CommonLogicForConst
     */
    public static NFCL3000CommonLogicForConst getInstance() {

        return myInstance;
    }

    /**
     * Get var_char_const value from cache or DB.
     * @param glblCmpyCd Global Company Code
     * @param varCharConstNm Varchar Const Name
     * @return Varchar Const Value.
     */
    public String getVarCharConstVal(String glblCmpyCd, String varCharConstNm) {

        String val = varCharConstMap.get(varCharConstNm);
        if (val == null) {
            val = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(val)) {
                this.varCharConstMap.put(varCharConstNm, val);
            } else {
                this.varCharConstMap.put(varCharConstNm, "NFCL3000CommonLogicForConstNullzz");
            }
        }
        if ("NFCL3000CommonLogicForConstNullzz".equals(val)) {
            val = null;
        }
        return val;
    }
}
