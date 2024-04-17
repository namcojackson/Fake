/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1800;

import static business.blap.NWAL1800.constant.NWAL1800Constant.CONTR_NUM;
import static business.blap.NWAL1800.constant.NWAL1800Constant.CONTR_SQ_NUM;
import static business.blap.NWAL1800.constant.NWAL1800Constant.DS_ACCT_NUM;
import static business.blap.NWAL1800.constant.NWAL1800Constant.FETCH_SIZE_MAX;
import static business.blap.NWAL1800.constant.NWAL1800Constant.GLOBAL_CMPY_CODE;
import static business.blap.NWAL1800.constant.NWAL1800Constant.LIST_SIZE_1000;
import static business.blap.NWAL1800.constant.NWAL1800Constant.LIST_SIZE_4;
import static business.blap.NWAL1800.constant.NWAL1800Constant.LIST_SIZE_5;
import static business.blap.NWAL1800.constant.NWAL1800Constant.MDSE_CD;
import static business.blap.NWAL1800.constant.NWAL1800Constant.MDSE_LIST;
import static business.blap.NWAL1800.constant.NWAL1800Constant.ROWNUM;
import static business.blap.NWAL1800.constant.NWAL1800Constant.SALESDATE;
import static business.blap.NWAL1800.constant.NWAL1800Constant.SELECT_MIN_ROW_SIZE;
import static business.blap.NWAL1800.constant.NWAL1800Constant.STSCD_ACTIVE;
import static business.blap.NWAL1800.constant.NWAL1800Constant.STS_CD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1800Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public final class NWAL1800Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1800Query MY_INSTANCE = new NWAL1800Query();

    /**
     * Private constructor
     */
    private NWAL1800Query() {
        super();
    }

    /**
     * Get the NWAL1800Query instance.
     * @return NWAL1800Query instance
     */
    public static NWAL1800Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NWAL1800CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NWAL1800CMsg bizMsg) {

        String slsDt = ZYPDateUtil.getSalesDate();
        Map<String, Object> params = new HashMap<String, Object>(LIST_SIZE_4);
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(DS_ACCT_NUM, bizMsg.dsAcctNum);
        params.put(SALESDATE, slsDt);
        ArrayList<String> mdseList = new ArrayList<String>(LIST_SIZE_1000);
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            mdseList.add(bizMsg.B.no(i).mdseCd_B.getValue());
        }
        params.put(MDSE_LIST, mdseList);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getContractList", params);
        return ssmResult;
    }

    /**
     * search
     * @param bizMsg NWAL1800CMsg
     * @param glblCmpyCd GlobalCompanyCode
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return Boolean
     */
    public Boolean search(NWAL1800CMsg bizMsg, String glblCmpyCd, S21SsmBooleanResultSetHandlerSupport rsSupport) {

        String slsDt = ZYPDateUtil.getSalesDate();
        Map<String, Object> params = new HashMap<String, Object>(LIST_SIZE_4);
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(DS_ACCT_NUM, bizMsg.dsAcctNum);
        params.put(SALESDATE, slsDt);
        ArrayList<String> mdseList = new ArrayList<String>(LIST_SIZE_1000);
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            mdseList.add(bizMsg.B.no(i).mdseCd_B.getValue());
        }
        params.put(MDSE_LIST, mdseList);

        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        return (Boolean) ssmBatchClient.queryObject("getContractList", params, ssmExecParam, rsSupport);
    }

    /***
     * getSchedulingNum
     * @param contrNum String
     * @param contrSqNum String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchedulingNum(String contrNum, String contrSqNum, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>(LIST_SIZE_5);
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(STS_CD, STSCD_ACTIVE);
        params.put(CONTR_NUM, contrNum);
        params.put(CONTR_SQ_NUM, contrSqNum);
        params.put(MDSE_CD, mdseCd);
        params.put(ROWNUM, SELECT_MIN_ROW_SIZE);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getSchedulingNum", params);
        return ssmResult;
    }
}
