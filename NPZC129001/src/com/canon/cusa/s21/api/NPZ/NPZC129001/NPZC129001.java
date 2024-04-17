/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC129001;

import business.parts.NPZC129001PMsg;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.api.NPZ.NPZC129001.constant.NPZC129001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Change Vendor UOM API.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   CITS            T.Kikuhara      Create          N/A
 * 2018/06/12   CITS            Y.Iwasaki       Update          QC#25507
 * 2019/01/25   CITS            T.Tokutomi      Update          QC#30083
 * 2023/06/02   Hitachi         S.Dong          Update          QC#55629
 * </pre>
 */
public class NPZC129001 extends S21ApiCommonBase {

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NPZC129001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <p>
     * Execute Change Vendor UOM.
     * </p>
     * @param param NPZC129001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC129001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        Map<String, Object> aslInfoMap = new HashMap<String, Object>();

        // In-parameter check
        if (!chkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        // Get ASL Info
        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(NPZC129001Constant.GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(NPZC129001Constant.SLS_DT, param.slsDt.getValue());
        queryParam.put(NPZC129001Constant.VND_CD, param.vndCd.getValue());
        queryParam.put(NPZC129001Constant.MDSE_CD, param.mdseCd.getValue());
        queryParam.put(NPZC129001Constant.MIN_START_DATE, NPZC129001Constant.MIN_START_DATE_VAL);
        queryParam.put(NPZC129001Constant.MAX_START_DATE, NPZC129001Constant.MAX_START_DATE_VAL);

        if (null != param.prntVndCd.getValue() && !param.prntVndCd.getValue().isEmpty()) {
            queryParam.put(NPZC129001Constant.PRNT_VND_CD, param.prntVndCd.getValue());
            aslInfoMap = (Map<String, Object>) ssmBatchClient.queryObject(NPZC129001Constant.GET_ASL_INFO, queryParam);
        } else {
            aslInfoMap = (Map<String, Object>) ssmBatchClient.queryObject(NPZC129001Constant.GET_ASL_INFO_BY_VND, queryParam);
        }

        if (aslInfoMap == null || aslInfoMap.isEmpty()) {
            msgMap.addXxMsgId(NPZC129001Constant.NPAM1364E); // The combination of specified input parameters Supplier, Site and Item does not exist in ASL.
            param.xxWrnSkipFlg.setValue(ZYPConstant.FLG_OFF_N);
            msgMap.flush();
            return;
        }

        // culc PO QTY
        if (!culcPoQty(msgMap, aslInfoMap)) {
            msgMap.flush();
            return;
        }

        msgMap.flush();

    }

    /**
     * <pre>
     * In-parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean chkParam(S21ApiMessageMap msgMap) {

        NPZC129001PMsg param = (NPZC129001PMsg) msgMap.getPmsg();

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NPZC129001Constant.NPZM0179E);
            return false;
        }

        // SLS_DT
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NPZC129001Constant.NPZM0180E);
            return false;
        }

        // VND_CD
        if (!ZYPCommonFunc.hasValue(param.vndCd)) {
            msgMap.addXxMsgId(NPZC129001Constant.NPZM0065E);
            return false;
        }

        // MDSE_CD
        if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
            msgMap.addXxMsgId(NPZC129001Constant.NPZM0044E);
            return false;
        }

        // PO_DISP_QTY and PO_QTY
        if (!ZYPCommonFunc.hasValue(param.poDispQty) && !ZYPCommonFunc.hasValue(param.poQty)) {
            msgMap.addXxMsgId(NPZC129001Constant.NPZM0202E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * culc PO QTY
     * </pre>
     * @param msgMap Message Map
     * @param aslInfoMap Map<String, String>
     * @return check result(OK:true, NG:false)
     */
    private boolean culcPoQty(S21ApiMessageMap msgMap, Map<String, Object> aslInfoMap) {
        NPZC129001PMsg param = (NPZC129001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.adjQtyIncrFlg)) {
            param.adjQtyIncrFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        param.xxWrnSkipFlg.setValue(ZYPConstant.FLG_OFF_N);

        BigDecimal vndUomQty = BigDecimal.ONE;
        if (ZYPCommonFunc.hasValue((BigDecimal) aslInfoMap.get(NPZC129001Constant.VND_UOM_QTY))) {
            vndUomQty = (BigDecimal) aslInfoMap.get(NPZC129001Constant.VND_UOM_QTY);
        }

        BigDecimal baseOrdQty = BigDecimal.ONE;
        if (ZYPCommonFunc.hasValue((BigDecimal) aslInfoMap.get(NPZC129001Constant.BASE_ORD_QTY))) {
            baseOrdQty = (BigDecimal) aslInfoMap.get(NPZC129001Constant.BASE_ORD_QTY);
        }

        BigDecimal vndMinOrdQty = BigDecimal.ONE;
        if (ZYPCommonFunc.hasValue((BigDecimal) aslInfoMap.get(NPZC129001Constant.VND_MIN_ORD_QTY))) {
            vndMinOrdQty = (BigDecimal) aslInfoMap.get(NPZC129001Constant.VND_MIN_ORD_QTY);
            // START 2023/06/02 S.Dong [QC#55629, ADD]
            param.vndMinOrdQty.setValue(vndMinOrdQty);
            // END 2023/06/02 S.Dong [QC#55629, ADD]
        }

        BigDecimal vndIncrOrdQty = BigDecimal.ONE;
        if (ZYPCommonFunc.hasValue((BigDecimal) aslInfoMap.get(NPZC129001Constant.VND_INCR_ORD_QTY))) {
            vndIncrOrdQty = (BigDecimal) aslInfoMap.get(NPZC129001Constant.VND_INCR_ORD_QTY);
        }

        BigDecimal poDispQty = param.poDispQty.getValue();
        BigDecimal poQty = param.poQty.getValue();

        BigDecimal xxPoDispQty = BigDecimal.ZERO;
        BigDecimal xxPoQty = BigDecimal.ZERO;

        if (ZYPCommonFunc.hasValue(poDispQty)) {
            if (ZYPConstant.FLG_ON_Y.equals(param.adjQtyIncrFlg.getValue())) {
                //  Adjust min Order QTY
                if (vndMinOrdQty.compareTo(BigDecimal.ZERO) > 0 && poDispQty.compareTo(vndMinOrdQty) < 0) {
                    xxPoDispQty = vndMinOrdQty;
                } else {
                    xxPoDispQty = poDispQty;
                }
                //  Adjust Order IncrOrdQty
                if (vndIncrOrdQty.compareTo(BigDecimal.ZERO) > 0) {
                    xxPoDispQty = (xxPoDispQty.divide(vndIncrOrdQty, 0, BigDecimal.ROUND_UP)).setScale(0, BigDecimal.ROUND_UP).multiply(vndIncrOrdQty);
                }
                if (xxPoDispQty != null && poDispQty.compareTo(xxPoDispQty) != 0) {
                    msgMap.addXxMsgId(NPZC129001Constant.NPZM0204E);
                    param.xxWrnSkipFlg.setValue(ZYPConstant.FLG_ON_Y);
                }
            } else {
                xxPoDispQty = poDispQty;
            }
            xxPoQty = (xxPoDispQty.divide(vndUomQty, 0, BigDecimal.ROUND_UP).setScale(0, BigDecimal.ROUND_UP).multiply(baseOrdQty));
            param.poQty_O1.setValue(xxPoQty);
            param.poDispQty_O1.setValue(xxPoDispQty);

        } else {
            xxPoDispQty = (poQty.divide(baseOrdQty, 0, BigDecimal.ROUND_UP).setScale(NPZC129001Constant.ROUND_UP_NO_4, BigDecimal.ROUND_UP).multiply(vndUomQty));
            param.poDispQty_O1.setValue(xxPoDispQty);
            if (ZYPConstant.FLG_ON_Y.equals(param.adjQtyIncrFlg.getValue())) {
                // Adjust min Order QTY
                if (vndMinOrdQty.compareTo(BigDecimal.ZERO) > 0 && xxPoDispQty.compareTo(vndMinOrdQty) < 0) {
                    xxPoDispQty = vndMinOrdQty;
                }
                // Adjust Order IncrOrdQty
                if (vndIncrOrdQty.compareTo(BigDecimal.ZERO) > 0) {
                    xxPoDispQty = (xxPoDispQty.divide(vndIncrOrdQty, 0, BigDecimal.ROUND_UP)).setScale(0, BigDecimal.ROUND_UP).multiply(vndIncrOrdQty);
                }
                if (xxPoDispQty != null && param.poDispQty_O1.getValue().compareTo(xxPoDispQty) != 0) {
                    xxPoQty = (xxPoDispQty.divide(vndUomQty, 0, BigDecimal.ROUND_UP).setScale(0, BigDecimal.ROUND_UP).multiply(baseOrdQty));
                    msgMap.addXxMsgId(NPZC129001Constant.NPZM0204E);
                    param.xxWrnSkipFlg.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    xxPoQty = param.poQty.getValue();
                }
                param.poQty_O1.setValue(xxPoQty);
                param.poDispQty_O1.setValue(xxPoDispQty);
            } else {
                // QC#30083 Add. Return param is not nullable.
                // xxPoQty init 0, not null.
                param.poQty_O1.setValue(poQty);
            }
        }

        if (!ZYPCommonFunc.hasValue(xxPoQty)) {
            param.poQty_O1.setValue(poQty);
        }
        if (!ZYPCommonFunc.hasValue(xxPoDispQty)) {
            param.poDispQty_O1.setValue(poDispQty);
        }

        return true;
    }
}
