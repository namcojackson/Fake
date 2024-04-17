/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1710;
import static business.blap.NWAL1710.constant.NWAL1710Constant.ACTIVE_ONLY;
import static business.blap.NWAL1710.constant.NWAL1710Constant.EFFECTIVE_FROM;
import static business.blap.NWAL1710.constant.NWAL1710Constant.EFFECTIVE_THRU;
import static business.blap.NWAL1710.constant.NWAL1710Constant.FETCH_SIZE;
import static business.blap.NWAL1710.constant.NWAL1710Constant.GLOBAL_CMPY_CODE;
import static business.blap.NWAL1710.constant.NWAL1710Constant.LEVEL_H;
import static business.blap.NWAL1710.constant.NWAL1710Constant.LINE_OF_BUSINESS;
import static business.blap.NWAL1710.constant.NWAL1710Constant.ORDER_CATEGORY;
import static business.blap.NWAL1710.constant.NWAL1710Constant.ORD_PROC_TP_LVL_CD;
import static business.blap.NWAL1710.constant.NWAL1710Constant.REASON_CODE;
import static business.blap.NWAL1710.constant.NWAL1710Constant.WORK_FLOW;
import static business.blap.NWAL1710.constant.NWAL1710Constant.ROW_NUM;
import static business.blap.NWAL1710.constant.NWAL1710Constant.COMPDTMAX;
import static business.blap.NWAL1710.constant.NWAL1710Constant.COMPDTMAX_VALUE;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1710Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 * 2016/05/11   Fujitsu         M.Suzuki        Update          S21_NA#7565
 *</pre>
 */
public final class NWAL1710Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1710Query MY_INSTANCE = new NWAL1710Query();

    /**
     * Private constructor
     */
    private NWAL1710Query() {
        super();
    }

    /**
     * Get the NWAL1710Query instance.
     * @return NWAL1710Query instance
     */
    public static NWAL1710Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getWorkflowPulldownList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWorkflowPulldownList() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ORD_PROC_TP_LVL_CD, LEVEL_H);
        return getSsmEZDClient().queryObjectList("getWorkflowPulldownList", ssmParam);
    }

    /**
     * getOrderCategoryList
     * @param bizMsg NWAL1710SMsg
     * @param glblMsg NWAL1710CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderCategoryList(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ORDER_CATEGORY, bizMsg.dsOrdCatgNm.getValue());
        ssmParam.put(REASON_CODE, bizMsg.dsOrdTpNm.getValue());
        ssmParam.put(WORK_FLOW, bizMsg.ordProcTpCd.getValue());
        ssmParam.put(LINE_OF_BUSINESS, bizMsg.lineBizTpCd.getValue());
        ssmParam.put(EFFECTIVE_FROM, bizMsg.effFromDt.getValue());
        ssmParam.put(EFFECTIVE_THRU, bizMsg.effThruDt.getValue());
        ssmParam.put(ACTIVE_ONLY, bizMsg.actvFlg.getValue());
        ssmParam.put(ROW_NUM, glblMsg.A.length() + 1);
        //2016/05/11 S21_NA#7565 MOD Start --------------
        ssmParam.put(COMPDTMAX, COMPDTMAX_VALUE);
        //2016/05/11 S21_NA#7565 MOD Start --------------
        return getSsmEZDClient().queryObjectList("getOrderCategoryList", ssmParam);
    }

    /**
     * searchForCSV
     * @param bizMsg NWAL1710CMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return Boolean 
     */
    public Boolean searchForCSV(NWAL1710CMsg bizMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ORDER_CATEGORY, bizMsg.dsOrdCatgNm.getValue());
        ssmParam.put(REASON_CODE, bizMsg.dsOrdTpNm.getValue());
        ssmParam.put(WORK_FLOW, bizMsg.ordProcTpCd.getValue());
        ssmParam.put(LINE_OF_BUSINESS, bizMsg.lineBizTpCd.getValue());
        ssmParam.put(EFFECTIVE_FROM, bizMsg.effFromDt.getValue());
        ssmParam.put(EFFECTIVE_THRU, bizMsg.effThruDt.getValue());
        ssmParam.put(ACTIVE_ONLY, bizMsg.actvFlg.getValue());
        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(FETCH_SIZE);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        return (Boolean) ssmBatchClient.queryObject("getOrderCategoryList", ssmParam, ssmExecParam, rsSupport);
   }

}
