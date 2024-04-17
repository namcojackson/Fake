/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC023001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLXC023001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;

/**
 * <pre>
 * Get a Shipping Plan Number
 * 
 * Parameter
 *  - Global Company Code (essential)
 *  - Transaction Source Type Code (essential)
 *  - Transaction Header Number (essential)
 *  - Transaction Line Number (essential)
 *  - Transaction Line Sub Number (optional)
 *
 * Date          Company     Name        Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/10/2009    Fujitsu     Y.Fukiage   Create          N/A
 * 04/08/2010    Fujitsu     S.Yoshida   Update          Def.5025,5023
 *</pre>
 */
public class NLXC023001 extends S21ApiCommonBase {

    /** SQL Map bind key : GLBL_CMPY_CD */
    private static final String BIND_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Map bind key : TRX_SRC_TP_CD */
    private static final String BIND_KEY_TRX_SRC_TP_CD = "trxSrcTpCd";

    /** SQL Map bind key : TRX_HDR_NUM */
    private static final String BIND_KEY_TRX_HDR_NUM = "trxHdrNum";

    /** SQL Map bind key : TRX_LINE_NUM */
    private static final String BIND_KEY_TRX_LINE_NUM = "trxLineNum";

    /** SQL Map bind key : TRX_LINE_SUB_NUM */
    private static final String BIND_KEY_TRX_LINE_SUB_NUM = "trxLineSubNum";

    /** SQL Map bind key : SHPG_STS_CANC */
    private static final String SHPG_STS_CANC = "SHPG_STS_CANC";

    /** SQL Statement ID : getShpgPlanNum */
    private static final String STATEMENT_ID_GET_SHPG_PLAN_NUM = "getShpgPlanNum";

    /** Column Name : SHPG_PLN_NUM */
    private static final String COL_NM_SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** Message ID : NLXM1005E */
    private static final String MSG_ID_NLXM1005E = "NLXM1005E";

    /** Message ID : NLXM1009E */
    private static final String MSG_ID_NLXM1009E = "NLXM1009E";

    /** Message ID : NLXM1010E */
    private static final String MSG_ID_NLXM1010E = "NLXM1010E";

    /** SQL Accessor */
    private S21SsmBatchClient ssmClient = null;

    /**
     * Constructor
     */
    public NLXC023001() {
        super();
        // Initialization of SQL Accessor
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * SHPG_PLN_NUM of the SHPG_PLN table is acquired according to a
     * specified argument value (List type)
     * @param listPmsg List of NLXC023001PMsg
     * @param onBatchType ONLINE or BATCH
     */
    public void execute(final List<NLXC023001PMsg> listPmsg, final ONBATCH_TYPE onBatchType) {
        for (NLXC023001PMsg pmsg : listPmsg) {
            execute(pmsg, onBatchType);
        }
    }

    /**
     * SHPG_PLN_NUM of the SHPG_PLN table is acquired according to a
     * specified argument value
     * @param pmsg NLXC023001PMsg
     * @param onBatchType ONLINE or BATCH
     */
    public void execute(final NLXC023001PMsg pmsg, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);

        try {

            // Check on input parameter
            if (!checkInputParameter(msgMap)) {
                return;
            }
            // Execution of SQL
            getShippingPlanNumber(msgMap);

        } finally {
            // Transfer of message
            msgMap.flush();
        }

    }

    /**
     * Check processing of input parameter
     * @param msgMap S21ApiMessageMap
     * @return check result
     */
    private boolean checkInputParameter(S21ApiMessageMap msgMap) {

        // Get a NLXC023001PMsg
        NLXC023001PMsg pmsg = (NLXC023001PMsg) msgMap.getPmsg();

        // When glblCmpyCd is a uninput, it is error
        if (!ZYPCommonFunc.hasValue(pmsg.glblCmpyCd.getValue())) {
            msgMap.addXxMsgId(MSG_ID_NLXM1005E);
            return false;
        }

        // When trxSrcTpCd is a uninput, it is error
        if (!ZYPCommonFunc.hasValue(pmsg.trxSrcTpCd.getValue())) {
            msgMap.addXxMsgId(MSG_ID_NLXM1005E);
            return false;
        }

        // When trxHdrNum is a uninput, it is error
        if (!ZYPCommonFunc.hasValue(pmsg.trxHdrNum.getValue())) {
            msgMap.addXxMsgId(MSG_ID_NLXM1005E);
            return false;
        }

        // When trxLineNum is a uninput, it is error
        if (!ZYPCommonFunc.hasValue(pmsg.trxLineNum.getValue())) {
            msgMap.addXxMsgId(MSG_ID_NLXM1005E);
            return false;
        }

        return true;
    }

    /**
     * Execution processing of SQL
     * @param msgMap S21ApiMessageMap
     */
    private void getShippingPlanNumber(S21ApiMessageMap msgMap) {

        // Set a parameter
        NLXC023001PMsg pmsg = (NLXC023001PMsg) msgMap.getPmsg();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(BIND_KEY_GLBL_CMPY_CD, pmsg.glblCmpyCd.getValue());
        queryParam.put(BIND_KEY_TRX_SRC_TP_CD, pmsg.trxSrcTpCd.getValue());
        queryParam.put(BIND_KEY_TRX_HDR_NUM, pmsg.trxHdrNum.getValue());
        queryParam.put(BIND_KEY_TRX_LINE_NUM, pmsg.trxLineNum.getValue());
        queryParam.put(BIND_KEY_TRX_LINE_SUB_NUM, pmsg.trxLineSubNum.getValue());
        queryParam.put(SHPG_STS_CANC, SHPG_STS.CANCELLED);

        // Execution of SQL
        ssmClient.queryObject(STATEMENT_ID_GET_SHPG_PLAN_NUM, queryParam, new ReadShpgPlan(msgMap));

    }

    /**
     * SSM execution inner class
     */
    private static class ReadShpgPlan extends S21SsmVoidResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap messageMap = null;

        /**
         * Constructor
         * @param msgMap S21ApiMessageMap
         */
        protected ReadShpgPlan(S21ApiMessageMap msgMap) {
            messageMap = msgMap;
        }

        /**
         * Acquired SHPG_PLN_NUM is set to NLXC023001PMsg
         * @param rs ResultSet
         * @throws SQLException
         */
        protected void doProcessQueryResult(ResultSet rs) throws SQLException {

            NLXC023001PMsg pmsg = (NLXC023001PMsg) messageMap.getPmsg();

            if (rs.next()) {
                // Set a shpgPlnNum of NLXC023001PMsg
                pmsg.shpgPlnNum.setValue(rs.getString(COL_NM_SHPG_PLN_NUM));
                if (rs.next()) {
                    // When two or more can be acquired, it clears
                    messageMap.addXxMsgId(MSG_ID_NLXM1010E);
                    pmsg.shpgPlnNum.clear();
                }
            } else {
                // When it is not possible to acquire it, it clears
                messageMap.addXxMsgId(MSG_ID_NLXM1009E);
                pmsg.shpgPlnNum.clear();
            }
        }

    }

}
