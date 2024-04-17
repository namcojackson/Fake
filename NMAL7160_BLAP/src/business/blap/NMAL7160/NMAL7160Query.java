/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7160;

import static business.blap.NMAL7160.constant.NMAL7160Constant.CSV_MAX_ROW;
import static business.blap.NMAL7160.constant.NMAL7160Constant.FETCH_SIZE_MAX;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_PROC_STS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/20   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public final class NMAL7160Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL7160Query MY_INSTANCE = new NMAL7160Query();

    /**
     * Constructor.
     */
    private NMAL7160Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL7160Query
     */
    public static NMAL7160Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Search Result
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     * @return Search Result List
     */
    public S21SsmEZDResult getSearchResult(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("procStsErr", CSMP_INV_PROC_STS.ERROR);
        params.put("ordNum", bizMsg.cpoOrdNum.getValue());
        params.put("itemCd", bizMsg.mdseCd.getValue());
        params.put("csaNum", bizMsg.dlrRefNum.getValue());
        params.put("invNum", bizMsg.invNum.getValue());
        params.put("csmpNum", bizMsg.csmpContrNum.getValue());
        params.put("errDtFrom", bizMsg.csmpInvErrDt_FR.getValue());
        params.put("errDtTo", bizMsg.csmpInvErrDt_TO.getValue());
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getSearchResult", params, glblMsg.A);
    }

    /**
     * Get Search Result For Download
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return Boolean
     */
    public Boolean searchForCSV(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("procStsErr", CSMP_INV_PROC_STS.ERROR);
        params.put("ordNum", bizMsg.cpoOrdNum.getValue());
        params.put("itemCd", bizMsg.mdseCd.getValue());
        params.put("csaNum", bizMsg.dlrRefNum.getValue());
        params.put("invNum", bizMsg.invNum.getValue());
        params.put("csmpNum", bizMsg.csmpContrNum.getValue());
        params.put("errDtFrom", bizMsg.csmpInvErrDt_FR.getValue());
        params.put("errDtTo", bizMsg.csmpInvErrDt_TO.getValue());
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
