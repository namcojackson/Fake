/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1820;

import static business.blap.NWAL1820.constant.NWAL1820Constant.BIZ_ID;
import static business.blap.NWAL1820.constant.NWAL1820Constant.CSV_MAX_ROW;
import static business.blap.NWAL1820.constant.NWAL1820Constant.FETCH_SIZE_MAX;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1820Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 * 2017/03/09   Fujitsu         K.Ishizuka      Update          QC#13856
 *</pre>
 */
public final class NWAL1820Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1820Query MY_INSTANCE = new NWAL1820Query();

    /**
     * Private constructor
     */
    private NWAL1820Query() {
        super();
    }

    /**
     * Get the NWAL1820Query instance.
     * @return NWAL1820Query instance
     */
    public static NWAL1820Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get DS Order Category List
     * @param bizMsg NWAL1820CMsg
     * @return DS Order Category List
     */
    public S21SsmEZDResult getDsOrdCatgList(NWAL1820CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("outOfWhNodeUsgFlg", ZYPConstant.FLG_ON_Y);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", bizMsg.slsDt.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        }

        return getSsmEZDClient().queryObjectList("getDsOrdCatgList", params);
    }

    /**
     * Get DS Order Type List
     * @param bizMsg NWAL1820CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NWAL1820CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("outOfWhNodeUsgFlg", ZYPConstant.FLG_ON_Y);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", bizMsg.slsDt.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        }

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * Get DS Order Type List(Sub Reason)
     * @param bizMsg NWAL1820CMsg
     * @return DS Order Type List(Sub Reason)
     */
    public S21SsmEZDResult getDsOrdTpSubReasonList(NWAL1820CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("outOfWhNodeUsgFlg", ZYPConstant.FLG_ON_Y);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", bizMsg.slsDt.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        }

        return getSsmEZDClient().queryObjectList("getDsOrdTpSubReasonList", params);
    }

    /**
     * Get Loan Ord
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLoanOrd(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msg", bizMsg);
        params.put("outOfWhInvtyProcFlg", ZYPConstant.FLG_ON_Y);
        params.put("outOfWhNodeUsgFlg", ZYPConstant.FLG_ON_Y);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        params.put("shpgStsInstalled", SHPG_STS.INSTALLED);
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxDt_FR) || ZYPCommonFunc.hasValue(bizMsg.xxTrxDt_TO) || ZYPCommonFunc.hasValue(bizMsg.actlShipDt_FR) || ZYPCommonFunc.hasValue(bizMsg.actlShipDt_TO)) {
            params.put("InnerJoinFlgShip", ZYPConstant.FLG_ON_Y);
        }
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getLoanOrd", params, glblMsg.A);
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
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return Boolean
     */
    public Boolean searchForCSV(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msg", bizMsg);
        params.put("outOfWhInvtyProcFlg", ZYPConstant.FLG_ON_Y);
        params.put("outOfWhNodeUsgFlg", ZYPConstant.FLG_ON_Y);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        params.put("shpgStsInstalled", SHPG_STS.INSTALLED);
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxDt_FR) || ZYPCommonFunc.hasValue(bizMsg.xxTrxDt_TO) || ZYPCommonFunc.hasValue(bizMsg.actlShipDt_FR) || ZYPCommonFunc.hasValue(bizMsg.actlShipDt_TO)) {
            params.put("InnerJoinFlgShip", ZYPConstant.FLG_ON_Y);
        }
        params.put("rowNum", CSV_MAX_ROW + 1);

        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        return (Boolean) ssmBatchClient.queryObject("getLoanOrd", params, ssmExecParam, rsSupport);

    }
    // ADD END S21_NA QC#13856

}
