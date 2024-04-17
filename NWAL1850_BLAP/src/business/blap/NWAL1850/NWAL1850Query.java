/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1850;

import static business.blap.NWAL1850.constant.NWAL1850Constant.BIZ_ID;
import static business.blap.NWAL1850.constant.NWAL1850Constant.BIZ_ID_FOR_ENTRY;
import static business.blap.NWAL1850.constant.NWAL1850Constant.CSV_MAX_ROW;
import static business.blap.NWAL1850.constant.NWAL1850Constant.FETCH_SIZE_MAX;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1850Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2022/06/02   Hitachi         K.Kitachi       Update          QC#60037
 *</pre>
 */
public final class NWAL1850Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1850Query MY_INSTANCE = new NWAL1850Query();

    /**
     * Private constructor
     */
    private NWAL1850Query() {
        super();
    }

    /**
     * Get the NWAL1850Query instance.
     * @return NWAL1850Query instance
     */
    public static NWAL1850Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdAgmt(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msg", bizMsg);
        params.put("rowNum", glblMsg.A.length() + 1);
        params.put("schdAgmtLineCancFlg", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryEZDMsgArray("getSchdAgmt", params, glblMsg.A);
    }

    /**
     * Get DS Order Type List
     * @param bizMsg NWAL1850CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpTxt(NWAL1850CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("bizId", BIZ_ID_FOR_ENTRY);
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        }

        return getSsmEZDClient().queryObjectList("getDsOrdTpTxt", params);
    }

    // START 2022/06/02 K.Kitachi [QC#60037, ADD]
    /**
     * Get Delivery Hold List
     * @param bizMsg NWAL1850CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDelyHldTxt(NWAL1850CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getDelyHldTxt", params);
    }
    // END 2022/06/02 K.Kitachi [QC#60037, ADD]

    /**
     * Get DS Order Category List
     * @param bizMsg NWAL1850CMsg
     * @return DS Order Category List
     */
    public S21SsmEZDResult getDsOrdCatgList(NWAL1850CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        params.put("bizId", BIZ_ID_FOR_ENTRY);

        return getSsmEZDClient().queryObjectList("getDsOrdCatgList", params);
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
    
    // ADD START S21_NA QC#13856
    /**
     * Get Search Result For DownLoad
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return Boolean
     */
    public Boolean searchForCSV(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msg", bizMsg);
        params.put("schdAgmtLineCancFlg", ZYPConstant.FLG_OFF_N);
        params.put("rowNum", CSV_MAX_ROW + 1);

        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        return (Boolean) ssmBatchClient.queryObject("getSchdAgmt", params, ssmExecParam, rsSupport);

    }
    // ADD END S21_NA QC#13856

}
