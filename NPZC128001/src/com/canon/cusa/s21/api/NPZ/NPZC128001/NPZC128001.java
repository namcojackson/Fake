/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC128001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.parts.NPZC128001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Calculate RDD for PR API.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   CITS            T.Kikuhara      Create          N/A
 * 2018/03/26   XITS            S.Katsuma       Update          QC#22519
 * </pre>
 */
public class NPZC128001 extends S21ApiCommonBase {

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** . */
    public static final String NPZM0179E = "NPZM0179E";
    /** . */
    public static final String NPZM0044E = "NPZM0044E";
    /** . */
    public static final String NPZM0076E = "NPZM0076E";
    /** . */
    public static final String NPZM0059E = "NPZM0059E";
    // START 2018/03/26 S.Katsuma [QC#22519,ADD]
    /** . */
    public static final String NPZM0096E = "NPZM0096E";
    // END 2018/03/26 S.Katsuma [QC#22519,ADD]

    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String PARTS_ITEM = "PARTS_ITEM";
    // START 2018/03/26 S.Katsuma [QC#22519,ADD]
    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";
    // END 2018/03/26 S.Katsuma [QC#22519,ADD]
    /** . */
    public static final String PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";
    /** . */
    public static final String MDSE_OR_PRT_CD = "MDSE_OR_PRT_CD";
    /** . */
    public static final String RPLSH_LT_DAYS_AOT = "RPLSH_LT_DAYS_AOT";
    /** . */
    public static final String SYS_DT_FLG = "SYS_DT_FLG";

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NPZC128001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <p>
     * Calculate RDD for PR API.
     * </p>
     * @param param NPZC128001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC128001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // In-parameter check
        if (!chkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        // Get MDSE or Parts Code
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(MDSE_CD, param.mdseCd.getValue());
        queryParam.put(PARTS_ITEM, PARTS_ITEM);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getMdseOrPartsCd", queryParam);
        String mdseOrPrtCd = "P";
        if (result == null || result.isEmpty()) {
            mdseOrPrtCd = "M";
        }

        // Get Lead Time By Parameter
        queryParam = new HashMap<String, String>();
        queryParam.put(GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(MDSE_CD, param.mdseCd.getValue());
        // START 2018/03/26 S.Katsuma [QC#22519,ADD]
        String prchReqTpCd = null;
        if (PRCH_REQ_TP.WH_TRANSFER.equals(param.prchReqTpCd.getValue()) || PRCH_REQ_TP.KITTING.equals(param.prchReqTpCd.getValue())) {
            prchReqTpCd = param.prchReqTpCd.getValue();
        } else {
            prchReqTpCd = "*";
        }
        queryParam.put(PRCH_REQ_TP_CD, prchReqTpCd);
        // END 2018/03/26 S.Katsuma [QC#22519,ADD]
        queryParam.put(PRCH_REQ_SRC_TP_CD, param.prchReqSrcTpCd.getValue());
        queryParam.put(MDSE_OR_PRT_CD, mdseOrPrtCd);
        result = (Map<String, Object>) ssmBatchClient.queryObject("getLeadTimeByParam", queryParam);

        if (result == null || result.isEmpty()) {
            result = (Map<String, Object>) ssmBatchClient.queryObject("getLeadTimeByDef", queryParam);
        }

        if (result == null || result.isEmpty()) {
            return;
        }

        String sysDtFlg = (String) result.get(SYS_DT_FLG);
        String procDt = param.procDt.getValue();
        int rplshLtDaysAot = ((BigDecimal) result.get(RPLSH_LT_DAYS_AOT)).intValue();
        String rddDt = "";

        if (ZYPConstant.FLG_ON_Y.equals(sysDtFlg)) {
            rddDt = ZYPDateUtil.addDays(procDt, rplshLtDaysAot);
        } else {
            rddDt = ZYPDateUtil.addBusinessDay(param.glblCmpyCd.getValue(), procDt, rplshLtDaysAot);
        }

        ZYPEZDItemValueSetter.setValue(param.rddDt, rddDt);

        msgMap.flush();

        return;

    }

    /**
     * <pre>
     * In-parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean chkParam(S21ApiMessageMap msgMap) {

        NPZC128001PMsg param = (NPZC128001PMsg) msgMap.getPmsg();

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NPZM0179E);
            return false;
        }

        // MDSE_CD
        if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
            msgMap.addXxMsgId(NPZM0044E);
            return false;
        }

        // START 2018/03/26 S.Katsuma [QC#22519,ADD]
        // PRCH_REQ_TP_CD
        if (!ZYPCommonFunc.hasValue(param.prchReqTpCd)) {
            msgMap.addXxMsgId(NPZM0096E);
            return false;
        }
        // END 2018/03/26 S.Katsuma [QC#22519,ADD]

        // PRCH_REQ_SRC_TP_CD
        if (!ZYPCommonFunc.hasValue(param.prchReqSrcTpCd)) {
            msgMap.addXxMsgId(NPZM0076E);
            return false;
        }

        // PROC_DT
        if (!ZYPCommonFunc.hasValue(param.procDt)) {
            msgMap.addXxMsgId(NPZM0059E);
            return false;
        }

        return true;
    }

}
