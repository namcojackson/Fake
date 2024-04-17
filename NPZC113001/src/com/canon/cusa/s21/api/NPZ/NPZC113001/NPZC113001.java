package com.canon.cusa.s21.api.NPZ.NPZC113001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.VAR_CHAR_CONSTTMsg;
import business.parts.NPZC113001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Primary Vendor from ASL API.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   CITS            M.Ouchi         Create          N/A
 * 2015/10/07   CITS            T.Tokutomi      Update          N/A
 * 2018/04/05   CITS            T.Wada          Update          QC#21170
 * 2023/02/17   Hitachi         S.Dong          Update          QC#60966
 * </pre>
 */
public class NPZC113001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient instance.
     */
    private S21SsmBatchClient client = null;

    // The message Id.

    /**
     * [@] is mandatory.
     */
    public static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    // The constant.

    /**
     * ASL Qualifier Type Code : Customer Specific. (01)
     */
    public static final String ASL_QLFY_TP_CD_CUST_SPEC = "01";

    /**
     * ASL Qualifier Type Code : Big Deal Specific. (02)
     */
    public static final String ASL_QLFY_TP_CD_BIG_DEAL_SPEC = "02";

    /**
     * VarChar Constant Name : ASL_DEF_VND_CD.
     */
    public static final String VAR_CHAR_CONST_NM_ASL_DEF_VND_CD = "ASL_DEF_VND_CD";

    /**
     * VarChar Constant Name : ASL_DEF_PRNT_VND_CD.
     */
    public static final String VAR_CHAR_CONST_NM_ASL_DEF_PRNT_VND_CD = "ASL_DEF_PRNT_VND_CD";

    /**
     * VarChar Constant Name : ASL_DEF_UNIT_PRC_AMT.
     */
    public static final String VAR_CHAR_CONST_NM_ASL_DEF_UNIT_PRC_AMT = "ASL_DEF_UNIT_PRC_AMT";

    /**
     * Constructor.
     */
    public NPZC113001() {
        super();

        // initializes SSM Client.
        this.client = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <p>
     * Get Primary Vendor from ASL.
     * </p>
     * @param param NPZC002001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC113001PMsg param, final ONBATCH_TYPE onBatchType) {
        // initializes message map.
        final S21ApiMessageMap messageMap = new S21ApiMessageMap(param);

        try {
            // if the parameters are invalid, ends the process.
            if (!validateParameters(param, messageMap)) {
                return;
            }

            // if Item Number is EMPTY, set the default value.
            if (ZYPCommonFunc.hasValue(param.mdseCd)) {
                // gets the ASL Details.
                List<Map<String, Object>> aslDtls = getAslDetails(param);

                // specifies the primary ASL Detail.
                Map<String, Object> primaryAslDtl = specifyPrimaryAslDetail(aslDtls);

                // sets the value of Primary ASL Detail.
                if (primaryAslDtl != null) {
                    ZYPEZDItemValueSetter.setValue(param.aslDtlPk, (BigDecimal) primaryAslDtl.get("ASL_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(param.mdseCd, (String) primaryAslDtl.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(param.vndCd, (String) primaryAslDtl.get("VND_CD"));
                    ZYPEZDItemValueSetter.setValue(param.prntVndCd, (String) primaryAslDtl.get("PRNT_VND_CD"));
                    ZYPEZDItemValueSetter.setValue(param.vndUomCd, (String) primaryAslDtl.get("VND_UOM_CD"));
                    ZYPEZDItemValueSetter.setValue(param.unitPrcAmt, (BigDecimal) primaryAslDtl.get("UNIT_PRC_AMT"));
                    if (ZYPCommonFunc.hasValue((BigDecimal) primaryAslDtl.get("VND_MIN_ORD_QTY"))) {
                        ZYPEZDItemValueSetter.setValue(param.vndMinOrdQty, (BigDecimal) primaryAslDtl.get("VND_MIN_ORD_QTY"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(param.vndMinOrdQty, BigDecimal.ONE);
                    }
                    if (ZYPCommonFunc.hasValue((BigDecimal) primaryAslDtl.get("VND_INCR_ORD_QTY"))) {
                        ZYPEZDItemValueSetter.setValue(param.vndIncrOrdQty, (BigDecimal) primaryAslDtl.get("VND_INCR_ORD_QTY"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(param.vndIncrOrdQty, BigDecimal.ONE);
                    }
                    ZYPEZDItemValueSetter.setValue(param.splyItemNum, (String) primaryAslDtl.get("SPLY_ITEM_NUM"));
                    ZYPEZDItemValueSetter.setValue(param.xxErrFlg, ZYPConstant.FLG_OFF_N);
                    // QC#21170
                    if (ZYPCommonFunc.hasValue((BigDecimal) primaryAslDtl.get("VND_LT_DAYS_NUM"))) {
                        ZYPEZDItemValueSetter.setValue(param.vndLtDaysNum, (BigDecimal) primaryAslDtl.get("VND_LT_DAYS_NUM"));
                    }
                    // START 2023/02/17 S.Dong [QC#60966, ADD]
                    if (ZYPCommonFunc.hasValue((BigDecimal) primaryAslDtl.get("VND_SHIP_LT_DAYS_NUM"))) {
                        ZYPEZDItemValueSetter.setValue(param.vndShipLtDaysNum, (BigDecimal) primaryAslDtl.get("VND_SHIP_LT_DAYS_NUM"));
                    }
                    // END 2023/02/17 S.Dong [QC#60966, ADD]
                    return;
                }
            }

            // sets the default value.
            ZYPEZDItemValueSetter.setValue(param.vndCd, getDefaultVndCd(param));
            ZYPEZDItemValueSetter.setValue(param.unitPrcAmt, getDefaultUnitPrcAmt(param));
            ZYPEZDItemValueSetter.setValue(param.prntVndCd, getDefaultParentVndCd(param));
            ZYPEZDItemValueSetter.setValue(param.vndMinOrdQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(param.vndIncrOrdQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(param.xxErrFlg, ZYPConstant.FLG_ON_Y);
        } finally {
            // sends the messages to PMsg.
            messageMap.flush();
        }
    }

    /**
     * <p>
     * Validates the input parameters.
     * </p>
     * @param param the input parameters.(NPZC113001PMsg)
     * @param messageMap the message map
     * @return if the input parameters are valid, returns TRUE.
     */
    private boolean validateParameters(NPZC113001PMsg param, S21ApiMessageMap messageMap) {
        // checks mandatory fields.
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            messageMap.addXxMsgIdWithPrm(MSG_ID_ZZZM9025E, new String[] {"Global Company Code" });
            return false;
        }
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            messageMap.addXxMsgIdWithPrm(MSG_ID_ZZZM9025E, new String[] {"Sales Date" });
            return false;
        }
        return true;
    }

    /**
     * <p>
     * Gets the ASL Details.
     * </p>
     * @param param NPZC113001PMsg
     * @return the ASL Details
     */
    private List<Map<String, Object>> getAslDetails(NPZC113001PMsg param) {

        List<Map<String, Object>> aslDtls = new ArrayList<Map<String, Object>>();
        String bigDealNumber = getBigDealNumber(param);

        // 1. searches by Big Deal#.
        if (ZYPCommonFunc.hasValue(bigDealNumber)) {
            aslDtls = searchAslDetailsByBigDealNum(param, bigDealNumber);
        }
        // 2. if the result is empty, searches by Ship To Customer.
        if (aslDtls.isEmpty() && ZYPCommonFunc.hasValue(param.shipToCustCd)) {
            aslDtls = searchAslDetailsByShipToCustCd(param);
        }
        // 3. if the result is empty, searches by Vendor Code.
        if (aslDtls.isEmpty() && ZYPCommonFunc.hasValue(param.vndCd)) {
            aslDtls = searchAslDetailsByVendorCode(param);
        }
        // 4. if the result is empty, searches by Item Number.
        if (aslDtls.isEmpty()) {
            aslDtls = searchAslDetailsByItemNumber(param);
        }
        return aslDtls;
    }

    /**
     * <p>
     * get Big Deal#.
     * </p>
     * @param param NPZC113001PMsg
     * @return ASL Detail List.
     */
    private String getBigDealNumber(NPZC113001PMsg param) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", param.glblCmpyCd);
        queryParams.put("shipToCustCd", param.shipToCustCd);

        return (String) this.client.queryObject("getBigDealNumber", queryParams);
    }

    /**
     * <p>
     * Searches ASL Detail by Big Deal#.
     * </p>
     * @param param NPZC113001PMsg
     * @return ASL Detail List.
     */
    private List<Map<String, Object>> searchAslDetailsByBigDealNum(NPZC113001PMsg param, String bigDealNumber) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", param.glblCmpyCd);
        queryParams.put("salesDate", param.slsDt.getValue());
        queryParams.put("mdseCd", param.mdseCd);
        queryParams.put("aslQlfyTpCd", ASL_QLFY_TP_CD_BIG_DEAL_SPEC);
        queryParams.put("aslQlfyRefCd", bigDealNumber);

        return searchAslDetails(queryParams);
    }

    /**
     * <p>
     * Searches ASL Detail by Ship To Customer Code.
     * </p>
     * @param param NPZC113001PMsg
     * @return ASL Detail List.
     */
    private List<Map<String, Object>> searchAslDetailsByShipToCustCd(NPZC113001PMsg param) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", param.glblCmpyCd);
        queryParams.put("salesDate", param.slsDt.getValue());
        queryParams.put("mdseCd", param.mdseCd);
        queryParams.put("aslQlfyTpCd", ASL_QLFY_TP_CD_CUST_SPEC);
        queryParams.put("aslQlfyRefCd", param.shipToCustCd);

        return searchAslDetails(queryParams);
    }

    /**
     * <p>
     * Searches ASL Detail by Vendor Code.
     * </p>
     * @param param NPZC113001PMsg
     * @return ASL Detail List.
     */
    private List<Map<String, Object>> searchAslDetailsByVendorCode(NPZC113001PMsg param) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", param.glblCmpyCd);
        queryParams.put("salesDate", param.slsDt.getValue());
        queryParams.put("mdseCd", param.mdseCd);
        queryParams.put("vndCd", param.vndCd);
        queryParams.put("prntVndCd", param.prntVndCd);

        return searchAslDetails(queryParams);
    }

    /**
     * <p>
     * Searches ASL Detail by Item Number.
     * </p>
     * @param param NPZC113001PMsg
     * @return ASL Detail List.
     */
    private List<Map<String, Object>> searchAslDetailsByItemNumber(NPZC113001PMsg param) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", param.glblCmpyCd);
        queryParams.put("salesDate", param.slsDt.getValue());
        queryParams.put("mdseCd", param.mdseCd);
        queryParams.put("primSplyFlg", ZYPConstant.FLG_ON_Y);

        return searchAslDetails(queryParams);
    }

    /**
     * <p>
     * Searches ASL Detail by query parameters.
     * </p>
     * @param queryParams query parameters
     * @return ASL Detail List.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> searchAslDetails(Map<String, Object> queryParams) {
        return (List<Map<String, Object>>) this.client.queryObjectList("searchAslDetails", queryParams);
    }

    /**
     * <p>
     * Specifies the primary ASL Detail from ASL Detail List.<br>
     * If can NOT specify the primary ASL Detail, returns null.<br>
     * </p>
     * @param aslDtls ASL Detail List
     * @return the primary ASL Detail (may return null)
     */
    private Map<String, Object> specifyPrimaryAslDetail(List<Map<String, Object>> aslDtls) {
        // If the search result is one, returns it.
        if (aslDtls.size() == 1) {
            return aslDtls.get(0);
        }

        // If the search result is multiple, returns ASL Detail
        // which is set to 'Y' to Primary Supplier Flag.
        for (Map<String, Object> aslDtl : aslDtls) {
            if (ZYPConstant.FLG_ON_Y.equals((String) aslDtl.get("PRIM_SPLY_FLG"))) {
                return aslDtl;
            }
        }
        // If can NOT specify the primary ASL Detail, returns null.
        return null;
    }

    /**
     * <p>
     * Gets the Default Vendor Code from VAR_CHAR_CONST Table.
     * </p>
     * @param param NPZC113001PMsg
     * @return Default Vendor Code.
     */
    private String getDefaultVndCd(NPZC113001PMsg param) {
        VAR_CHAR_CONSTTMsg msg = new VAR_CHAR_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(msg.varCharConstNm, VAR_CHAR_CONST_NM_ASL_DEF_VND_CD);

        VAR_CHAR_CONSTTMsg result = (VAR_CHAR_CONSTTMsg) S21ApiTBLAccessor.findByKey(msg);
        if (ZYPCommonFunc.hasValue(result.varCharConstVal)) {
            return result.varCharConstVal.getValue();
        } else {
            return null;
        }
    }

    /**
     * <p>
     * Gets the Default Parent Vendor Code from VAR_CHAR_CONST Table.
     * </p>
     * @param param NPZC113001PMsg
     * @return Default Vendor Code.
     */
    private String getDefaultParentVndCd(NPZC113001PMsg param) {
        VAR_CHAR_CONSTTMsg msg = new VAR_CHAR_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(msg.varCharConstNm, VAR_CHAR_CONST_NM_ASL_DEF_PRNT_VND_CD);

        VAR_CHAR_CONSTTMsg result = (VAR_CHAR_CONSTTMsg) S21ApiTBLAccessor.findByKey(msg);
        if (ZYPCommonFunc.hasValue(result.varCharConstVal)) {
            return result.varCharConstVal.getValue();
        } else {
            return null;
        }
    }

    /**
     * <p>
     * Gets the Default Unit Price Amount from VAR_CHAR_CONST Table.
     * </p>
     * @param param NPZC113001PMsg
     * @return Default Unit Price Amount.
     */
    private BigDecimal getDefaultUnitPrcAmt(NPZC113001PMsg param) {
        VAR_CHAR_CONSTTMsg msg = new VAR_CHAR_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(msg.varCharConstNm, VAR_CHAR_CONST_NM_ASL_DEF_UNIT_PRC_AMT);

        VAR_CHAR_CONSTTMsg result = (VAR_CHAR_CONSTTMsg) S21ApiTBLAccessor.findByKey(msg);

        if (ZYPCommonFunc.hasValue(result.varCharConstVal)) {
            return new BigDecimal(result.varCharConstVal.getValue());
        } else {
            return new BigDecimal(0);
        }
    }
}
