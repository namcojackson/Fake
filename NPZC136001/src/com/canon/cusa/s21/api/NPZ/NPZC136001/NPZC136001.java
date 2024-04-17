/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC136001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.RCV_ASN_VNDTMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC129001PMsg;
import business.parts.NPZC136001PMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NPZ.NPZC129001.NPZC129001;
import com.canon.cusa.s21.api.NPZ.NPZC136001.constant.NPZC136001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Get Vendor Minimum Order Qty API.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/06/02   Hitachi         S.Dong          Create          QC#55629
 * </pre>
 */
public class NPZC136001 extends S21ApiCommonBase {

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NPZC136001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }


    /** Global Company Code */
    private String globalCompanyCode = null;

    /** Sales Date */
    private String salesDate = null;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    public void execute(final NPZC136001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // In-parameter check
        if (!chkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        this.globalCompanyCode = param.glblCmpyCd.getValue();
        this.salesDate = param.slsDt.getValue();

        // Get ASL Min Qty
        NPZC129001PMsg pMsg = getMinVndQty(param);

        if (pMsg!=null) {
            param.vndMinOrdQty.setValue(pMsg.vndMinOrdQty.getValue());
            param.prchReqQty.setValue(pMsg.poDispQty_O1.getValue());
        }

        msgMap.flush();
        return;

    }

    private boolean chkParam(S21ApiMessageMap msgMap) {

        NPZC136001PMsg param = (NPZC136001PMsg) msgMap.getPmsg();

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NPZC136001Constant.NPZM0179E);
            return false;
        }

        // SLS_DT
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NPZC136001Constant.NPZM0180E);
            return false;
        }

        // MDSE_CD
        if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
            msgMap.addXxMsgId(NPZC136001Constant.NPZM0044E);
            return false;
        }

        // PRCH_REQ_QTY
        if (!ZYPCommonFunc.hasValue(param.prchReqQty)) {
            msgMap.addXxMsgId(NPZC136001Constant.NPZM0064E);
            return false;
        }

        return true;
    }

    // get Vendor min Qty from ASL
    private NPZC129001PMsg getMinVndQty (NPZC136001PMsg param){

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        NPZC113001PMsg vendorInfo = getPrimaryVendorInfo(param, param.mdseCd.getValue(), true, false);
        BigDecimal prchReqQty = param.prchReqQty.getValue();
        if (vendorInfo == null) {
            return null;
        }
        NPZC129001PMsg npzc129001Pmsg = getVendorPoQty(vendorInfo.vndCd.getValue(), vendorInfo.mdseCd.getValue(), prchReqQty);
        if (!ZYPCommonFunc.hasValue(npzc129001Pmsg.vndMinOrdQty)) {
            return null;
        }
        if (0 < npzc129001Pmsg.xxMsgIdList.getValidCount() && prchReqQty.compareTo(npzc129001Pmsg.poDispQty_O1.getValue()) >= 0) {
            msgMap.addXxMsgId(npzc129001Pmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return null;
        }

        return npzc129001Pmsg;
    }

    // exec NPZC129001
    private NPZC129001PMsg getVendorPoQty(String vndCd, String mdseCd, BigDecimal reqQty) {

        NPZC129001 api = new NPZC129001();
        NPZC129001PMsg param = new NPZC129001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.vndCd, vndCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(param.poDispQty, reqQty);
        ZYPEZDItemValueSetter.setValue(param.adjQtyIncrFlg, ZYPConstant.FLG_ON_Y);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    // get primary vendor
    private NPZC113001PMsg getPrimaryVendorInfo(NPZC136001PMsg param, String mdseCd, boolean isSupd, boolean isInvty) {

        NPZC113001PMsg vendor = null;
        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(param.prchReqRecTpCd.getValue())
                && ZYPCommonFunc.hasValue(param.prntVndCd)
                && ZYPCommonFunc.hasValue(param.vndCd)) {

            String bigDealNumber = getBigDealNumber(param.shipToCustCd.getValue());
            // 1. searches by Big Deal#.
            if (ZYPCommonFunc.hasValue(bigDealNumber)) {
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put("glblCmpyCd", globalCompanyCode);
                queryParams.put("salesDate", salesDate);
                queryParams.put("mdseCd", mdseCd);
                queryParams.put("aslQlfyTpCd", "02"); // ASL_QLFY_TP_CD_BIG_DEAL_SPEC
                queryParams.put("aslQlfyRefCd", bigDealNumber);
                vendor = searchAslDtl(queryParams);
            }
            // 2. if the result is empty, searches by Ship To Customer.
            if (vendor == null && ZYPCommonFunc.hasValue(param.shipToCustCd)) {
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put("glblCmpyCd", globalCompanyCode);
                queryParams.put("salesDate", salesDate);
                queryParams.put("mdseCd", mdseCd);
                queryParams.put("aslQlfyTpCd", "01"); // ASL_QLFY_TP_CD_CUST_SPEC
                queryParams.put("aslQlfyRefCd", param.shipToCustCd.getValue());
                vendor = searchAslDtl(queryParams);
            }
            // 3. if the result is empty, searches by Vendor Code.
            if (vendor == null && ZYPCommonFunc.hasValue(param.vndCd)) {
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put("glblCmpyCd", globalCompanyCode);
                queryParams.put("salesDate", salesDate);
                queryParams.put("mdseCd", mdseCd);
                queryParams.put("vndCd", param.vndCd.getValue());
                queryParams.put("prntVndCd", param.prntVndCd.getValue());
                vendor = searchAslDtl(queryParams);
            }
            // 4. no data found asl
            if (vendor == null) {
                vendor = new NPZC113001PMsg();
                ZYPEZDItemValueSetter.setValue(vendor.xxErrFlg, ZYPConstant.FLG_ON_Y);
            }
        }

        if (vendor == null) {
            vendor = getPrimaryVendor(mdseCd);
        }

        if (0 < vendor.xxMsgIdList.getValidCount()) {
            return null;
        } else if (ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            if (isSupd) {
                NWZC206001PMsg nWZC206001PMsg = new NWZC206001PMsg();
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.mdseCd, param.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                NWZC206001 nWZC206001 = new NWZC206001();
                nWZC206001.execute(nWZC206001PMsg, ONBATCH_TYPE.BATCH);

                List<String> errList = S21ApiUtil.getXxMsgIdList(nWZC206001PMsg);
                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                        if (xxMsgId.endsWith("E")) {
                            return null;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(nWZC206001PMsg.A.no(0).mdseCd) && !mdseCd.equals(nWZC206001PMsg.A.no(0).mdseCd.getValue())) {
                    vendor = getPrimaryVendorInfo(param, nWZC206001PMsg.A.no(0).mdseCd.getValue(), false, false);
                }
            }

            if (isSupd && vendor == null) {
                return null;
            } else if (vendor == null || ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
                if (isInvty) {
                    return null;
                } else {
                    return null;
                }
            } else {
                return vendor;
            }
        } else {

            if (!isInvty) {
                RCV_ASN_VNDTMsg rcvAsnVndTMsg = new RCV_ASN_VNDTMsg();
                ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.rcvAsnVndCd, vendor.vndCd.getValue());
                rcvAsnVndTMsg = (RCV_ASN_VNDTMsg) S21ApiTBLAccessor.findByKey(rcvAsnVndTMsg);

                if (rcvAsnVndTMsg == null || ZYPConstant.FLG_ON_Y.equals(rcvAsnVndTMsg.itemFlipFlg.getValue())) {

                    NWZC206001PMsg nWZC206001PMsg = new NWZC206001PMsg();
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.slsDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.mdseCd, param.mdseCd.getValue());
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                    NWZC206001 nWZC206001 = new NWZC206001();
                    nWZC206001.execute(nWZC206001PMsg, ONBATCH_TYPE.BATCH);

                    List<String> errList = S21ApiUtil.getXxMsgIdList(nWZC206001PMsg);
                    if (errList.size() > 0) {
                        for (String xxMsgId : errList) {
                            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                            if (xxMsgId.endsWith("E")) {
                                return null;
                            }
                        }
                    }

                    if (ZYPCommonFunc.hasValue(nWZC206001PMsg.A.no(0).mdseCd) && !mdseCd.equals(nWZC206001PMsg.A.no(0).mdseCd.getValue())) {
                        vendor = getPrimaryVendorInfo(param, nWZC206001PMsg.A.no(0).mdseCd.getValue(), false, isInvty);
                    }

                }
            }
            return vendor;
        }
    }

    // get big deal number
    private String getBigDealNumber(String shipToCustCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String bigDealNum = "";

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put("glblCmpyCd", globalCompanyCode);
            queryParams.put("shipToCustCd", shipToCustCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getBigDealNumber", queryParams, execParam);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Unique
                bigDealNum = resultSet.getString("BIG_DEAL_NUM");
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return bigDealNum;
    }

    // get primary vendor
    private NPZC113001PMsg getPrimaryVendor(String mdseCd) {

        NPZC113001 api = new NPZC113001();
        NPZC113001PMsg param = new NPZC113001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    // search ASL detail
    private NPZC113001PMsg searchAslDtl(Map<String, Object> queryParams) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        NPZC113001PMsg vendor = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("searchAslDetails", queryParams, execParam);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vendor = new NPZC113001PMsg();
                ZYPEZDItemValueSetter.setValue(vendor.aslDtlPk, resultSet.getBigDecimal("ASL_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(vendor.mdseCd, resultSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.vndCd, resultSet.getString("VND_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.prntVndCd, resultSet.getString("PRNT_VND_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.vndUomCd, resultSet.getString("VND_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.unitPrcAmt, resultSet.getBigDecimal("UNIT_PRC_AMT"));
                if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal("VND_MIN_ORD_QTY"))) {
                    ZYPEZDItemValueSetter.setValue(vendor.vndMinOrdQty, resultSet.getBigDecimal("VND_MIN_ORD_QTY"));
                } else {
                    ZYPEZDItemValueSetter.setValue(vendor.vndMinOrdQty, BigDecimal.ONE);
                }
                if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal("VND_INCR_ORD_QTY"))) {
                    ZYPEZDItemValueSetter.setValue(vendor.vndIncrOrdQty, resultSet.getBigDecimal("VND_INCR_ORD_QTY"));
                } else {
                    ZYPEZDItemValueSetter.setValue(vendor.vndIncrOrdQty, BigDecimal.ONE);
                }
                ZYPEZDItemValueSetter.setValue(vendor.splyItemNum, resultSet.getString("SPLY_ITEM_NUM"));
                ZYPEZDItemValueSetter.setValue(vendor.xxErrFlg, ZYPConstant.FLG_OFF_N);
                if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal("VND_LT_DAYS_NUM"))) {
                    ZYPEZDItemValueSetter.setValue(vendor.vndLtDaysNum, resultSet.getBigDecimal("VND_LT_DAYS_NUM"));
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return vendor;
    }

}
