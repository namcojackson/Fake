/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7410;

import static business.blap.NMAL7410.constant.NMAL7410Constant.CSV_MAX_ROW;
import static business.blap.NMAL7410.constant.NMAL7410Constant.FETCH_SIZE_MAX;

import java.sql.ResultSet;
import java.util.Map;

import business.blap.NMAL7410.common.NMAL7410CommonLogic;

import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public final class NMAL7410Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL7410Query MY_INSTANCE = new NMAL7410Query();

    /**
     * Constructor.
     */
    private NMAL7410Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL7410Query
     */
    public static NMAL7410Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Search Result
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     * @return Search Result List
     */
    public S21SsmEZDResult getSearchResult(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        Map<String, Object> params = NMAL7410CommonLogic.setSearchParam(bizMsg);
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getSearchResult", params, glblMsg.A);
    }

    /**
     * Get Search Result For Download
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return Boolean
     */
    public Boolean searchForCSV(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {

        Map<String, Object> params = NMAL7410CommonLogic.setSearchParam(bizMsg);
        params.put("rowNum", CSV_MAX_ROW + 1);

        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        return (Boolean) ssmBatchClient.queryObject("getSearchResult", params, ssmExecParam, rsSupport);

    }

}
