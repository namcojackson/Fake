/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1370.common;

import static business.blap.NPAL1370.constant.NPAL1370Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1370.constant.NPAL1370Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NPAL1370.constant.NPAL1370Constant.DB_PARAM_RTL_SWH_CD;
import java.util.HashMap;
import java.util.Map;

import business.blap.NPAL1370.NPAL1370Query;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370CommonLogic {

    /**
     * get Warehouse Name
     * @param glblCmpyCd String
     * @param whCd String
     * @return Warehouse Name(String) or Null(whcd does not exists)
     */
    public static String getWHName(String glblCmpyCd, String whCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, whCd);

        // Execute
        S21SsmEZDResult result = NPAL1370Query.getInstance().getWHName(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        }

        return null;
    }

    /**
     * get Sub Warehouse Name
     * @param glblCmpyCd String
     * @param swhCd String
     * @return Sub Warehouse Name(String) or Null(swhcd does not
     * exists)
     */
    public static String getSWHName(String glblCmpyCd, String swhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_SWH_CD, swhCd);

        // Execute
        S21SsmEZDResult result = NPAL1370Query.getInstance().getSWHName(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        }

        return null;
    }
}
