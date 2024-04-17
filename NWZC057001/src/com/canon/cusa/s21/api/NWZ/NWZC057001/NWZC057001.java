package com.canon.cusa.s21.api.NWZ.NWZC057001;

import static com.canon.cusa.s21.api.NWZ.NWZC057001.constant.NWZC057001Constant.RETURN_CD_NORMAL;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.db.INV_PRT_CTRLTMsg;
import business.parts.NWZC057001PMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Invoice Process Status Update API for Special Bill
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   CSA             K.Aratani       Create          N/A
 *</pre>
 */

public class NWZC057001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;
    
    /**
     * Constructor
     */
    public NWZC057001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NWZC057001PMsg
     * @param type ONBATCH_TYPE
     */
    public void execute(final NWZC057001PMsg pMsg, final ONBATCH_TYPE type) {
        this.msgMap = new S21ApiMessageMap(pMsg);

        doProcess(pMsg);
        msgMap.flush();
    }

    /**
     * execute
     * @param params List<NWZC057001PMsg>
     * @param type ONBATCH_TYPE
     */
    public void execute(final List<NWZC057001PMsg> params, final ONBATCH_TYPE type) {
        for (NWZC057001PMsg param : params) {
            execute(param, type);
        }
    }

    /**
     * doProcess
     * @param msgMap NWZC057001PMsg
     */
    private void doProcess(NWZC057001PMsg pMsg) {

        // Parameter Check
        if (!checkInputParameter(pMsg)) {
            return;
        }

        // update
        update(pMsg);
        
    }

    /**
     * checkInputParameter
     * @param pMsg NWZC057001PMsg
     * @return boolean
     */
    private boolean checkInputParameter(NWZC057001PMsg pMsg) {
        // NWZM0473E=0,"Global Company Code" is not set.
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId("NWZM0473E");
        }
        // NWZM1918E=0,Consolidated Bill# or Invoice# is mandatory.
        if (!ZYPCommonFunc.hasValue(pMsg.conslBillNum) && !ZYPCommonFunc.hasValue(pMsg.invNum)) {
            msgMap.addXxMsgId("NWZM1918E");
        }
        // NWZM1936E=0,"Invoice Special Bill Process Status Code" is not set.
        if (!ZYPCommonFunc.hasValue(pMsg.invSpclBillProcStsCd)) {
            msgMap.addXxMsgId("NWZM1936E");
        }
        // NWZM1937E=0,"Invoice Special Bill Process Status Code" is invalid value.
        if (ZYPCommonFunc.hasValue(pMsg.invSpclBillProcStsCd) 
        		&& !"1".equals(pMsg.invSpclBillProcStsCd.getValue()) 
        		&& !"2".equals(pMsg.invSpclBillProcStsCd.getValue())) {
            msgMap.addXxMsgId("NWZM1937E");
        }
        
        return !isError(pMsg);
    }

    /**
     * update
     * @param pMsg NWZC057001PMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean update(NWZC057001PMsg pMsg) {

    	List<Map<String, Object>> targetDataList = null;
        if (ZYPCommonFunc.hasValue(pMsg.conslBillNum)) {
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("glblCmpyCd",     pMsg.glblCmpyCd.getValue());
	        paramMap.put("conslBillNum",   pMsg.conslBillNum.getValue());
	        targetDataList = (List<Map<String, Object>>) ssmClient.queryObjectList("queryTargetDataByConsolidatedBillNum", paramMap);
        } else {
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("glblCmpyCd",     pMsg.glblCmpyCd.getValue());
	        paramMap.put("invNum",         pMsg.invNum.getValue());
	        targetDataList = (List<Map<String, Object>>) ssmClient.queryObjectList("queryTargetDataByInvoiceNum", paramMap);
        }
        
        if (targetDataList != null && targetDataList.size() > 0) {
        	for (Map<String, Object> targetDataMap : targetDataList) {
        		if (targetDataMap.get("INV_PRT_CTRL_PK") != null) {
        			INV_PRT_CTRLTMsg tmsg = new INV_PRT_CTRLTMsg();
        	        tmsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        	        tmsg.invPrtCtrlPk.setValue((BigDecimal) targetDataMap.get("INV_PRT_CTRL_PK"));
        	        tmsg = (INV_PRT_CTRLTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        	        if (tmsg == null) {
        	        	//NWZM1919E=0,There is no update data in Invoice Print Control Table.
        	            msgMap.addXxMsgId("NWZM1919E");
        	            return false;
        	        }
        	        tmsg.invSpclBillProcStsCd.setValue(pMsg.invSpclBillProcStsCd.getValue());
        	        tmsg.invSpclBillProcDt.setValue(ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        	        S21FastTBLAccessor.update(tmsg);
        	        if (!RETURN_CD_NORMAL.equals(tmsg.getReturnCode())) {
        	        	//NWZM1920E=0,Update process has been failed in Invoice Print Control Table.
        	            msgMap.addXxMsgId("NWZM1920E");
        	            return false;
        	        }
        		}
        	}
        }
        
        return true;
    }
    /**
     * isError
     * @param pMsg NWZC05700101PMsg
     * @return boolean
     */
    private boolean isError(NWZC057001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }
}
