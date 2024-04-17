/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC026001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmStringResultSetHandlerSupport;

/**
 * <pre>
 * WMS Stock Status Code is converted into S21 Stock Status Code
 * 
 * Date          Company     Name        Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/31/2009    Canon       Y.Fukiage   Create          N/A
 *</pre>
 */
public class NLXC026001 extends S21ApiCommonBase {

    /** SQL Map bind key : GLBL_CMPY_CD */
    private static final String BIND_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Map bind key : TRX_LINE_SUB_NUM */
    private static final String BIND_KEY_S80_STK_STS_CD = "s80StkStsCd";

    /** SQL Statement ID : getStkStsConv */
    private static final String STATEMENT_ID_GET_STK_STS_CONV = "getStkStsConv";

    /** Column Name : STK_STS_CD */
    private static final String COL_NM_STK_STS_CD = "STK_STS_CD";

    /**
     * Stock Status Code is conversion process
     * @param glblCmpyCd Global Company Code
     * @param wmsStkSts WMS Stock Status Code
     * @return conversion result
     */
    public static String conversionStockStatusCodeWMSToS21(String glblCmpyCd, String wmsStkSts) {

        if (!ZYPCommonFunc.hasValue(wmsStkSts)) {
            return null;
        } else {
            // Initialization of SQL Accessor
            S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NLXC026001.class);
            // Set a parameter
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(BIND_KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(BIND_KEY_S80_STK_STS_CD, wmsStkSts);
            // Execution of SQL
            return (String) ssmClient.queryObject(STATEMENT_ID_GET_STK_STS_CONV, queryParam, new ReadStkStsConv());
        }
    }

    /**
     * SSM execution inner class
     */
    private static class ReadStkStsConv extends S21SsmStringResultSetHandlerSupport {

        /**
         * Acquired S21 Stock Status Code is returned
         * @param rs ResultSet
         * @return S21 Stock Status Code
         * @throws SQLException
         */
        protected String doProcessQueryResult(ResultSet rs) throws SQLException {
            String returnStr;
            if (rs.next()) {
                // When it is possible to acquire it,
                // it is returned
                returnStr = rs.getString(COL_NM_STK_STS_CD);
                if (rs.next()) {
                    // When two or more can be acquired,
                    // NULL is returned
                    returnStr = null;
                }
            } else {
                // When it is not possible to acquire it,
                // NULL is returned
                returnStr = null;
            }
            return returnStr;
        }

    }

}
