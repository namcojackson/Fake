package com.canon.cusa.s21.api.NPZ.NPZC108001;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import business.parts.NPZC108001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * NPZC108001 : Get Procurement Source API.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/20   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPZC108001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient instance.
     */
    private S21SsmBatchClient client = null;

    /**
     * MRP_INFO_RGTN_STS_CD : AVAILABLE. (="P20")
     */
    private static final String MRP_INFO_RGTN_STS_CD_AVAILABLE = "P20";

    /**
     * Constructor.
     */
    public NPZC108001() {
        super();

        // initializes SSM Client.
        this.client = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <p>
     * Get Procurement Source.
     * </p>
     * @param param NPZC108001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC108001PMsg param, final ONBATCH_TYPE onBatchType) {
        // initializes message map.
        final S21ApiMessageMap messageMap = new S21ApiMessageMap(param);

        try {
            // if the parameters are invalid, ends the process.
            if (!validateParameters(param, messageMap)) {
                return;
            }

            // searches the procurement source info.
            Map<String, String> result = getProcurementSourceInfo(param);

            // If the result is empty, returns null;
            if (result.isEmpty()) {
                return;
            }

            // sets the procurement source info to PMsg.
            ZYPEZDItemValueSetter.setValue(param.srcLocCd, result.get("SRC_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(param.srcRtlWhCd, result.get("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(param.srcRtlSwhCd, result.get("SRC_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(param.procrTpCd, result.get("PROCR_TP_CD"));

        } finally {
            // sends the messages to PMsg.
            messageMap.flush();
        }
    }

    /**
     * <p>
     * Validates the input parameters.
     * </p>
     * @param param the input parameters.(NPZC108001PMsg)
     * @param messageMap the message map
     * @return if the input parameters are valid, returns TRUE.
     */
    private boolean validateParameters(NPZC108001PMsg param, S21ApiMessageMap messageMap) {
        // checks mandatory fields.
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(param.invtyLocCd)) {
            return false;
        }
        return true;
    }

    /**
     * <p>
     * Gets the procurement source info.<br>
     * If the result is NULL, returns the empty map.<br>
     * </p>
     * @param param NPZC108001PMsg
     * @return the procurement source info
     */
    private Map<String, String> getProcurementSourceInfo(NPZC108001PMsg param) {

        // creates the query parameter.
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", param.glblCmpyCd);
        queryParams.put("invtyLocCd", param.invtyLocCd);
        queryParams.put("mdseCd", param.mdseCd);
        queryParams.put("mrpInfoRgtnStsCd", MRP_INFO_RGTN_STS_CD_AVAILABLE);
        queryParams.put("salesDate", param.slsDt);

        // searches MRP Info.
        Map<String, String> mrpInfo = this.queryObject("searchMrpInfo", queryParams);
        if (!mrpInfo.isEmpty()) {
            return mrpInfo;
        }
        // If the result is empty, searches DS Warehouse Info.
        Map<String, String> warehouseInfo = this.queryObject("searchDsWarehouseInfo", queryParams);
        if (!warehouseInfo.isEmpty()) {
            return warehouseInfo;
        }
        // If the result is empty, searches DS Merchandise Info.
        Map<String, String> mdseInfo = this.queryObject("searchDSMerchandiseInfo", queryParams);
        if (!mdseInfo.isEmpty()) {
            return mdseInfo;
        }
        // If the result is empty, returns empty map.
        return Collections.emptyMap();
    }

    /**
     * <p>
     * Queries the object.<br>
     * If the result is NULL, returns the empty map.<br>
     * </p>
     * @param param NPZC108001PMsg
     * @return Map (query result)
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> queryObject(String statementId, Map<String, Object> queryParams) {
        Object result = this.client.queryObject(statementId, queryParams);
        if (result != null) {
            return (Map<String, String>) this.client.queryObject(statementId, queryParams);
        } else {
            return Collections.emptyMap();
        }
    }

}
