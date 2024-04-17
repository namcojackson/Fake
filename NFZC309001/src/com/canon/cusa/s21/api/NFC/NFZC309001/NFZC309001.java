package com.canon.cusa.s21.api.NFC.NFZC309001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;

import business.parts.NFZC309001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/**
 * <pre>
 * Due Date Calculation from Payment Term API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/30/2015   Fujitsu         T.Tanaka        Create          N/A
 * </pre>
 */
public class NFZC309001 extends S21ApiCommonBase {

    /** */
    public static final int DEBUG_MSG_LVL = 8;

    /** */
    public static final String DUE_DT = "DUE_DT";

    /** Entry Parameter Error [@] */
    public static final String NFCM0501E = "NFCM0501E";
    
    /** Data does not exist. */
    public static final String NFCM0508E = "NFCM0508E";
    /** */
    private String errMsg = "";

    /** */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * 
     */
    public NFZC309001() {
        super();
    }

    /**
     * 
     * @param param
     * @param onBatchType
     */
    public void execute(final NFZC309001PMsg param, final ONBATCH_TYPE onBatchType) {
        debugLog("execute : start");

        if (param == null) {
            return;
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        if(!checkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        if(!doCalcDueDate(msgMap)) {
            msgMap.flush();
            return;
        }
    }

    /**
     * 
     * @param params
     * @param onBatchType
     */
    public void excute(final List<NFZC309001PMsg> params, final ONBATCH_TYPE onBatchType) {

        if (params != null) {
            for (NFZC309001PMsg msg : params) {
                execute(msg, onBatchType);
            }
            return;
        }
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {
        
        NFZC309001PMsg param = (NFZC309001PMsg) msgMap.getPmsg();

        boolean res = true;
        if(S21StringUtil.isEmpty(param.glblCmpyCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"GLBL_CMPY_CD"});
            res = false;
        }
        if(S21StringUtil.isEmpty(param.pmtTermCashDiscCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"PMT_TERM_CASH_DISC_CD"});
            res = false;
        }
        if(S21StringUtil.isEmpty(param.trxDt.getValue())) {
            msgMap.addXxMsgIdWithPrm(NFCM0501E, new String[]{"TRX_DT"});
            res = false;
        }
        return res;
    }

    /**
     * 
     * @param msgMap
     * @return
     */
    private Boolean doCalcDueDate(S21ApiMessageMap msgMap) {

        NFZC309001PMsg param = (NFZC309001PMsg) msgMap.getPmsg();
        
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("pmtTermCashDiscCd", param.pmtTermCashDiscCd.getValue());
        queryParam.put("trxDt", param.trxDt.getValue());
        queryParam.put("startDt", param.startDt.getValue());

        Boolean res = (Boolean) ssmBatchClient.queryObject("getDueDate", queryParam, new CalcDueDateHandler(msgMap));
        return res;
    }

    private final class CalcDueDateHandler extends S21SsmBooleanResultSetHandlerSupport {

        private NFZC309001PMsg param;
        private S21ApiMessageMap msgMap;

        private CalcDueDateHandler(S21ApiMessageMap msgMap) {
            this.param = (NFZC309001PMsg) msgMap.getPmsg();
            this.msgMap = msgMap;
        }

        protected Boolean doProcessQueryResult(ResultSet rsCalcDueDate) throws SQLException {

            try {
                while(rsCalcDueDate.next()) {
                    ZYPEZDItemValueSetter.setValue(param.dueDt, rsCalcDueDate.getString(DUE_DT));
                }
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }
            if(S21StringUtil.isEmpty(param.dueDt.getValue())) {
                msgMap.addXxMsgId(NFCM0508E);
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
            
        }
    }

    /**
     * 
     * @param logmsg
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, logmsg, this);
    }

}
