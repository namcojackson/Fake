/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC309001;

import static com.canon.cusa.s21.api.NLZ.NLZC309001.constant.NLZC309001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ASSET_STGNGTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NLZC309001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ASSET_STGNG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Asset Staging Entry API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         H.Ikeda         Create          N/A
 * 2016/01/12   Fujitsu         H.Nagashima     Update          QC#2598
 * 2016/04/26   Fujitsu         C.Tanaka        Update          QC#7576
 * 2016/05/11   Hitachi         T.Tsuchida      Update          QC#8118
 * 2016/05/17   Hitachi         T.Tsuchida      Update          QC#8425
 * 2016/06/06   Hitachi         T.Tsuchida      Update          QC#9541
 * 2016/06/20   Hitachi         T.Tsuchida      Update          QC#10231
 * 2016/06/23   Hitachi         K.Kojima        Update          QC#10636
 * 2016/07/07   Hitachi         J.Kim           Update          QC#11513
 * 2016/07/13   Hitachi         T.Tsuchida      Update          QC#11767
 * 2017/07/07   CITS            T.Kikuhara      Update          QC#18002
 * 2017/12/15   CITS            K.Ogino         Update          QC#22926
 * 2018/01/18   Hitachi         J.Kim           Update          QC#17985
 * 2018/01/22   Hitachi         J.Kim           Update          QC#22056
 * 2018/03/16   Hitachi         J.Kim           Update          QC#23431
 * 2018/04/10   Hitachi         J.Kim           Update          QC#25381
 * 2018/05/15   Hitachi         J.Kim           Update          QC#25059
 * 2018/06/25   Hitachi         J.Kim           Update          QC#24844
 *</pre>
 */
public class NLZC309001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /**
     * constructor
     */
    public NLZC309001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param params List<NLZC309001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC309001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NLZC309001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * execute
     * @param param NLZC309001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC309001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        try {
            checkInPrm(msgMap);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            doProcess(msgMap, onBatchType);

            // locked by another user
        } catch (EZDDBRecordLockedException e) {
            throw e;

        } finally {
            // Flush Message Map.
            msgMap.flush();
        }
    }

    private void doProcess(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC309001PMsg param = (NLZC309001PMsg) msgMap.getPmsg();
        DS_ASSET_STGNGTMsg tMsg = new DS_ASSET_STGNGTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAssetStgngPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_STGNG_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.fromSvcConfigMstrPk, param.fromSvcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.toSvcConfigMstrPk, param.toSvcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.procModeCd, param.procModeCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, param.mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, param.serNum);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, param.svcMachMstrPk);
        // 02/11/2016 mod start
        // ZYPEZDItemValueSetter.setValue(tMsg.baseCmptFlg,
        // param.baseCmptFlg);
        String baseCmptFlg = param.baseCmptFlg.getValue();
        if (!ZYPCommonFunc.hasValue(baseCmptFlg)) {
            baseCmptFlg = ZYPConstant.FLG_OFF_N;
        }
        // START 2016/06/06 T.Tsuchida [QC#9541,MOD]
        if (PROC_MODE_SERVICE_EXCHANGE.equals(param.procModeCd.getValue())) {
            baseCmptFlg = ZYPConstant.FLG_ON_Y;

            // START 2018/02/19 J.Kim [QC#23431,ADD]
            // DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = getDsCpoRtrnDtlByCondition(param);
            //if (dsCpoRtrnDtlTMsg != null) {
            //    ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, dsCpoRtrnDtlTMsg.svcMachMstrPk);
            // }
            // END 2018/02/19 J.Kim [QC#23431,ADD]
            // START 2018/05/15 J.Kim [QC#25059,ADD]
            if (!getSvcMachMstrByCondition(param)) {
                return;
            }
            // END 2018/05/15 J.Kim [QC#25059,ADD]
        }
        // END 2016/06/06 T.Tsuchida [QC#9541,MOD]
        // START 2016/06/23 K.Kojima [QC#10636,ADD]
        if (PROC_MODE_AP_INVOICE.equals(param.procModeCd.getValue())) {
            baseCmptFlg = ZYPConstant.FLG_ON_Y;
        }
        // END 2016/06/23 K.Kojima [QC#10636,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.baseCmptFlg, baseCmptFlg);
        // 02/11/2016 mod end
        ZYPEZDItemValueSetter.setValue(tMsg.assetTpCd, param.assetTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shpgPlnNum, param.shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyTrxPk, param.invtyTrxPk);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, param.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, param.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdPosnNum, param.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustAcctCd, param.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, param.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, param.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.soldToCustLocCd, param.soldToCustLocCd);
        // START 2018/01/15 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(tMsg.invNum, param.invNum);
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvNum, param.invNum);
        // END 2018/01/15 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, param.invDt);
        ZYPEZDItemValueSetter.setValue(tMsg.csmpPrcAmt, param.csmpPrcAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.ordAdjAmt, param.ordAdjAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.stdCostAmt, param.stdCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAssetStgngStsCd, DS_ASSET_STGNG_STS.NEW);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAssetMstrPk, param.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.rtnWhCd, param.rtrnWhCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepTocCd, param.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvSqNum, param.apVndInvSqNum);
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.apVndInvLineNum, param.apVndInvLineNum);
        // END 2018/04/10 J.Kim [QC#25381,ADD]
        BigDecimal totAssetQty = BigDecimal.ONE;
        if (ZYPCommonFunc.hasValue(param.totAssetQty) && BigDecimal.ZERO.compareTo(param.totAssetQty.getValue()) != 0) {
            totAssetQty = param.totAssetQty.getValue();
            // START 2016/05/17 T.Tsuchida [QC#8425,ADD]
        } else {
            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, param.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, param.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum);

            cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(cpoDtlTMsg);

            if (cpoDtlTMsg != null && ZYPCommonFunc.hasValue(cpoDtlTMsg.shipQty)) {
                totAssetQty = cpoDtlTMsg.shipQty.getValue();
            }
            // END 2016/05/17 T.Tsuchida [QC#8425,ADD]
        }
        ZYPEZDItemValueSetter.setValue(tMsg.totAssetQty, totAssetQty);

        // START 2018/06/25 J.Kim [QC#24844]
        if (ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            String glblCmpyCd = param.glblCmpyCd.getValue();
            String cpoOrdNum = param.cpoOrdNum.getValue();
            CPOTMsg cpoTMsg = findByKeyCpo(glblCmpyCd, cpoOrdNum);
            if (cpoTMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, cpoTMsg.custIssPoNum);
            }
        }

        if (ZYPCommonFunc.hasValue(param.toSvcConfigMstrPk)) {
            Map<String, Object> contrNumInfo = getContrNumInfo(param);
            if (contrNumInfo != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, (String) contrNumInfo.get("DS_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.contrEffFromDt, (String) contrNumInfo.get("CONTR_EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(tMsg.contrEffThruDt, (String) contrNumInfo.get("CONTR_EFF_THRU_DT"));
            }
        }
        // END 2018/06/25 J.Kim [QC#24844]

        // if (!ZYPCommonFunc.hasValue(param.stdCostAmt) &&
        // ZYPCommonFunc.hasValue(param.mdseCd)) {
        //
        // MDSETMsg mdseTMsg = new MDSETMsg();
        // ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd,
        // param.glblCmpyCd.getValue());
        // ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd,
        // param.mdseCd.getValue());
        //
        // mdseTMsg = (MDSETMsg)
        // S21ApiTBLAccessor.findByKey(mdseTMsg);
        //
        // if (mdseTMsg == null) {
        //
        // msgMap.addXxMsgId(NLXM1024E);
        // return;
        // }
        //
        // ZYPEZDItemValueSetter.setValue(tMsg.stdCostAmt,
        // mdseTMsg.thisMthTotStdCostAmt.getValue());
        // }

        String procMode = param.procModeCd.getValue();
        // START 2016/05/11 T.Tsuchida [QC#8118,MOD]
        // if (PROC_MODE_RENTAL_SHIP.equals(procMode) ||
        // PROC_MODE_EMSD_SHIP.equals(procMode)) {
        if (PROC_MODE_RENTAL_SHIP.equals(procMode) || PROC_MODE_EMSD_SHIP.equals(procMode) || PROC_MODE_SERVICE_EXCHANGE.equals(procMode)) {
            // END 2016/05/11 T.Tsuchida [QC#8118,MOD]
            List<Map<String, Object>> list = getUnitCostAmt(param.glblCmpyCd.getValue(), param.shpgPlnNum.getValue());
            if (list == null || list.isEmpty()) {
                msgMap.addXxMsgId(NLZM2418E);
                return;
            }

            Map<String, Object> map = list.get(0);
            // START 2016/05/11 T.Tsuchida [QC#8118,MOD]
            // String invtyAcctCd = (String)map.get("INVTY_ACCT_CD");
            // BigDecimal unitCostAmt =
            // (BigDecimal)map.get("UNIT_COST_AMT");
            // if (PROC_MODE_RENTAL_SHIP.equals(procMode)) {
            // ZYPEZDItemValueSetter.setValue(tMsg.stdCostAmt,
            // unitCostAmt);
            // } else if (PROC_MODE_EMSD_SHIP.equals(procMode) &&
            // !INVTY_ACCT.ASSET.equals(invtyAcctCd)) {
            // ZYPEZDItemValueSetter.setValue(tMsg.stdCostAmt, new
            // BigDecimal("1.08").multiply(unitCostAmt).setScale(4,
            // BigDecimal.ROUND_DOWN));
            // }
            ZYPEZDItemValueSetter.setValue(tMsg.stdCostAmt, (BigDecimal) map.get("UNIT_COST_AMT"));
            // END 2016/05/11 T.Tsuchida [QC#8118,MOD]
            if (!ZYPCommonFunc.hasValue(param.invDt)) {
                ZYPEZDItemValueSetter.setValue(tMsg.invDt, ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()));
            }
            // START 2016/06/20 T.Tsuchida [QC#10231,ADD]
        } else if (PROC_MODE_ASSET_ADJ_OR_DISPOSAL.equals(procMode)) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(param.glblCmpyCd.getValue(), param.svcMachMstrPk.getValue());
            if (svcMachMstrTMsg == null) {
                msgMap.addXxMsgId(NLZM2386E);
                return;
            }
            // QC#18002 UPD START
            ZYPEZDItemValueSetter.setValue(tMsg.rtnWhCd, svcMachMstrTMsg.curLocNum);
            // QC#18002 UPD END
            // END 2016/06/20 T.Tsuchida [QC#10231,ADD]
        } else if (PROC_MODE_AP_INVOICE.equals(procMode)) {
            ZYPEZDItemValueSetter.setValue(tMsg.stdCostAmt, param.stdCostAmt);
        }

        S21FastTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NLZM2353E);
        }

    }

    /**
     * <pre>
     * Check Input Parameters
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private void checkInPrm(S21ApiMessageMap msgMap) {

        NLZC309001PMsg inPrmPMsg = (NLZC309001PMsg) msgMap.getPmsg();

        // Process Mode check
        String procMode = inPrmPMsg.procModeCd.getValue();
        if (!ZYPCommonFunc.hasValue(procMode)) {
            msgMap.addXxMsgId(NLZM2087E);
            return;
        }
        if (!checkProcMode(procMode)) {
            msgMap.addXxMsgId(NLZM2088E);
            return;
        }

        // Parameters check
        // Common check
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NLZM2332E);
        }
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.slsDt)) {
            msgMap.addXxMsgId(NLZM2333E);
        }

        // Item check
        if (procMode.equals(PROC_MODE_RENTAL_SHIP)) {
            checkParamMerchandiseCode(msgMap, inPrmPMsg);
            checkParamServiceConfigrationMasterPkTo(msgMap, inPrmPMsg);
            checkParamBaseCompornentFlag(msgMap, inPrmPMsg);
            checkParamShippingPlanNumber(msgMap, inPrmPMsg);
            checkParamCpoOrderNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailLineNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailSubNumber(msgMap, inPrmPMsg);
            checkParamOrderPositionNumber(msgMap, inPrmPMsg);
            checkParamShipToAccountCode(msgMap, inPrmPMsg);
            checkParamShipToCustomerCode(msgMap, inPrmPMsg);
            checkParamSellToCustomerCode(msgMap, inPrmPMsg);
            checkParamSoldToCustomerLocCode(msgMap, inPrmPMsg);
            checkParamSalesRepTocCode(msgMap, inPrmPMsg);
            // QC#2598
            // checkParamInvoiceNumber(msgMap, inPrmPMsg);
            // checkParamInvoiceDate(msgMap, inPrmPMsg);
        } else if (procMode.equals(PROC_MODE_EMSD_SHIP)) {
            checkParamMerchandiseCode(msgMap, inPrmPMsg);
            checkParamServiceConfigrationMasterPkTo(msgMap, inPrmPMsg);
            checkParamBaseCompornentFlag(msgMap, inPrmPMsg);
            checkParamShippingPlanNumber(msgMap, inPrmPMsg);
            checkParamCpoOrderNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailLineNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailSubNumber(msgMap, inPrmPMsg);
            checkParamOrderPositionNumber(msgMap, inPrmPMsg);
            checkParamShipToAccountCode(msgMap, inPrmPMsg);
            checkParamShipToCustomerCode(msgMap, inPrmPMsg);
            checkParamSellToCustomerCode(msgMap, inPrmPMsg);
            checkParamSoldToCustomerLocCode(msgMap, inPrmPMsg);
            checkParamSalesRepTocCode(msgMap, inPrmPMsg);
            // checkParamInvoiceNumber(msgMap, inPrmPMsg);
            // checkParamInvoiceDate(msgMap, inPrmPMsg);
        } else if (procMode.equals(PROC_MODE_RENTAL_CONVERSION_EARLY_BUYOUT)) {
            checkParamMerchandiseCode(msgMap, inPrmPMsg);
            checkParamServiceConfigrationMasterPkTo(msgMap, inPrmPMsg);
            checkParamServiceMachineMasterPk(msgMap, inPrmPMsg);
            checkParamBaseCompornentFlag(msgMap, inPrmPMsg);
            checkParamCpoOrderNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailLineNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailSubNumber(msgMap, inPrmPMsg);
            checkParamOrderPositionNumber(msgMap, inPrmPMsg);
            checkParamShipToAccountCode(msgMap, inPrmPMsg);
            checkParamShipToCustomerCode(msgMap, inPrmPMsg);
            checkParamSellToCustomerCode(msgMap, inPrmPMsg);
            checkParamSoldToCustomerLocCode(msgMap, inPrmPMsg);
            checkParamSalesRepTocCode(msgMap, inPrmPMsg);
            checkParamInvoiceNumber(msgMap, inPrmPMsg);
            checkParamInvoiceDate(msgMap, inPrmPMsg);
        } else if (procMode.equals(PROC_MODE_RETURN_TANGIBLE)) {
            checkParamMerchandiseCode(msgMap, inPrmPMsg);
            checkParamServiceMachineMasterPk(msgMap, inPrmPMsg);
            checkParamReturnWarehouseCode(msgMap, inPrmPMsg);
            checkParamCpoOrderNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailLineNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailSubNumber(msgMap, inPrmPMsg);
            checkParamOrderPositionNumber(msgMap, inPrmPMsg);
        } else if (procMode.equals(PROC_MODE_CONFIGRATION_CHANGE)) {
            checkParamMerchandiseCode(msgMap, inPrmPMsg);
            // START 2016/07/13 T.Tsuchida [QC#11767,DEL]
            //checkParamServiceConfigrationMasterPkFrom(msgMap, inPrmPMsg);
            // END 2016/07/13 T.Tsuchida [QC#11767,DEL]
            // START 2016/07/07 J.Kim [QC#11513,DEL]
            // checkParamSerialNumber(msgMap, inPrmPMsg);
            // END 2016/07/07 J.Kim [QC#11513,DEL]
            checkParamServiceMachineMasterPk(msgMap, inPrmPMsg);
        } else if (procMode.equals(PROC_MODE_SERVICE_EXCHANGE)) {
            checkParamServiceConfigrationMasterPkTo(msgMap, inPrmPMsg);
            checkParamShippingPlanNumber(msgMap, inPrmPMsg);
            checkParamCpoOrderNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailLineNumber(msgMap, inPrmPMsg);
            checkParamCpoDetailSubNumber(msgMap, inPrmPMsg);
            checkParamOrderPositionNumber(msgMap, inPrmPMsg);
            checkParamShipToAccountCode(msgMap, inPrmPMsg);
            checkParamShipToCustomerCode(msgMap, inPrmPMsg);
            checkParamSellToCustomerCode(msgMap, inPrmPMsg);
            checkParamSoldToCustomerLocCode(msgMap, inPrmPMsg);
            checkParamSalesRepTocCode(msgMap, inPrmPMsg);
            // START 2016/04/26 C.Tanaka [QC#7576, DEL]
            // checkParamInvoiceNumber(msgMap, inPrmPMsg);
            // checkParamInvoiceDate(msgMap, inPrmPMsg);
            // END 2016/04/26 C.Tanaka [QC#7576, DEL]
        } else if (procMode.equals(PROC_MODE_AP_INVOICE)) {
            // START 2018/01/22 J.Kim [QC#22056,DEL]
            // checkParamMerchandiseCode(msgMap, inPrmPMsg);
            // END 2018/01/22 J.Kim [QC#22056,DEL]
            // checkParamSellToCustomerCode(msgMap, inPrmPMsg); //
            // 02/11/2016 delete
            // checkParamSoldToCustomerLocCode(msgMap, inPrmPMsg); //
            // 02/11/2016 delete
            // checkParamSalesRepTocCode(msgMap, inPrmPMsg); //
            // 02/11/2016 delete
            checkParamInvoiceNumber(msgMap, inPrmPMsg);
            checkParamInvoiceDate(msgMap, inPrmPMsg);
            checkParamApVndInvSqNum(msgMap, inPrmPMsg);
            checkParamStdCostAmt(msgMap, inPrmPMsg);
            checkParamTotAssetQty(msgMap, inPrmPMsg);
        } else if (procMode.equals(PROC_MODE_ASSET_ADJ_OR_DISPOSAL)) {
            checkParamMerchandiseCode(msgMap, inPrmPMsg);
            checkParamServiceMachineMasterPk(msgMap, inPrmPMsg);
        }
    }

    // Merchandise Code
    private void checkParamMerchandiseCode(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.mdseCd)) {
            msgMap.addXxMsgId(NLZM2334E);
        }
    }

    // START 2016/07/13 T.Tsuchida [QC#11767,DEL]
    //// Service Configration Master PK(FROM)
    //private void checkParamServiceConfigrationMasterPkFrom(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
    //    if (!ZYPCommonFunc.hasValue(inPrmPMsg.fromSvcConfigMstrPk)) {
    //        msgMap.addXxMsgId(NLZM2335E);
    //    }
    //}
    // END 2016/07/13 T.Tsuchida [QC#11767,DEL]

    // Service Configration Master PK(TO)
    private void checkParamServiceConfigrationMasterPkTo(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.toSvcConfigMstrPk)) {
            msgMap.addXxMsgId(NLZM2336E);
        }
    }

    // START 2016/07/07 J.Kim [QC#11513,DEL]
    // // Serial Number
    // private void checkParamSerialNumber(S21ApiMessageMap msgMap,
    // NLZC309001PMsg inPrmPMsg) {
    // if (!ZYPCommonFunc.hasValue(inPrmPMsg.serNum)) {
    // msgMap.addXxMsgId(NLZM2337E);
    // }
    // }
    // END 2016/07/07 J.Kim [QC#11513,DEL]

    // Service Machine Master PK
    private void checkParamServiceMachineMasterPk(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.svcMachMstrPk)) {
            msgMap.addXxMsgId(NLZM2338E);
        }
    }

    // Base Compornent Flag
    private void checkParamBaseCompornentFlag(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.baseCmptFlg)) {
            msgMap.addXxMsgId(NLZM2339E);
        }
    }

    // Return Warehouse Code
    private void checkParamReturnWarehouseCode(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.rtrnWhCd)) {
            msgMap.addXxMsgId(NLZM2340E);
        }
    }

    // Asset Type Code

    // Shipping Plan Number
    private void checkParamShippingPlanNumber(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.shpgPlnNum)) {
            msgMap.addXxMsgId(NLZM2341E);
        }
    }

    // Inventory Transaction PK

    // CPO Order Number
    private void checkParamCpoOrderNumber(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.cpoOrdNum)) {
            msgMap.addXxMsgId(NLZM2342E);
        }
    }

    // CPO Detail Line Number
    private void checkParamCpoDetailLineNumber(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.cpoDtlLineNum)) {
            msgMap.addXxMsgId(NLZM2343E);
        }
    }

    // CPO Detail Sub Number
    private void checkParamCpoDetailSubNumber(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.cpoDtlLineSubNum)) {
            msgMap.addXxMsgId(NLZM2344E);
        }
    }

    // Order Position Number
    private void checkParamOrderPositionNumber(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.dsOrdPosnNum)) {
            msgMap.addXxMsgId(NLZM2345E);
        }
    }

    // Ship To Account Code
    private void checkParamShipToAccountCode(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.shipToCustAcctCd)) {
            msgMap.addXxMsgId(NLZM2346E);
        }
    }

    // Ship To Customer Code
    private void checkParamShipToCustomerCode(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.shipToCustCd)) {
            msgMap.addXxMsgId(NLZM2347E);
        }
    }

    // Sell To Customer Code
    private void checkParamSellToCustomerCode(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.sellToCustCd)) {
            msgMap.addXxMsgId(NLZM2348E);
        }
    }

    // Sold To Customer Loc Code
    private void checkParamSoldToCustomerLocCode(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.soldToCustLocCd)) {
            msgMap.addXxMsgId(NLZM2349E);
        }
    }

    // Sales Rep Toc Code
    private void checkParamSalesRepTocCode(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.slsRepTocCd)) {
            msgMap.addXxMsgId(NLZM2350E);
        }
    }

    // Invoice Number
    private void checkParamInvoiceNumber(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.invNum)) {
            msgMap.addXxMsgId(NLZM2351E);
        }
    }

    // Invoice Date
    private void checkParamInvoiceDate(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.invDt)) {
            msgMap.addXxMsgId(NLZM2352E);
        }
    }

    // AP Invoice Sequence Number
    private void checkParamApVndInvSqNum(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.apVndInvSqNum)) {
            msgMap.addXxMsgId(NLZM2415E);
        }
    }

    // Standard Cost Amount
    private void checkParamStdCostAmt(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.stdCostAmt)) {
            msgMap.addXxMsgId(NLZM2416E);
        }
    }

    // Total Asset Quantity
    private void checkParamTotAssetQty(S21ApiMessageMap msgMap, NLZC309001PMsg inPrmPMsg) {
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.totAssetQty)) {
            msgMap.addXxMsgId(NLZM2417E);
        }
    }

    // checkProcMode
    private boolean checkProcMode(String procMode) {

        if (PROC_MODE_RENTAL_SHIP.equals(procMode)) {
            return true;
        } else if (PROC_MODE_EMSD_SHIP.equals(procMode)) {
            return true;
        } else if (PROC_MODE_RENTAL_CONVERSION_EARLY_BUYOUT.equals(procMode)) {
            return true;
        } else if (PROC_MODE_RETURN_TANGIBLE.equals(procMode)) {
            return true;
        } else if (PROC_MODE_CONFIGRATION_CHANGE.equals(procMode)) {
            return true;
        } else if (PROC_MODE_SERVICE_EXCHANGE.equals(procMode)) {
            return true;
        } else if (PROC_MODE_AP_INVOICE.equals(procMode)) {
            return true;
        } else if (PROC_MODE_ASSET_ADJ_OR_DISPOSAL.equals(procMode)) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getUnitCostAmt(String glblCmpyCd, String shpgPlnNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("shpgPlnNum", shpgPlnNum);
        List<String> trxCdList = new ArrayList<String>();
        trxCdList.add(TRX.RENTAL_SHIPMENT);
        trxCdList.add(TRX.EMSD_SHIPMENT);
        ssmPrm.put("trxCdList", trxCdList);
        // QC#22926
        ssmPrm.put("invtyLocCdDS", ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_WH_CD", glblCmpyCd));
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getUnitCostAmt", ssmPrm);
    }

    private static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg svcMachMstr = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstr.svcMachMstrPk, svcMachMstrPk);
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstr);
        return svcMachMstrTMsg;
    }

    // START 2018/05/15 J.Kim [QC#25059,DEL]
    // // START 2018/03/16 J.Kim [QC#23431,ADD]
    // private static DS_CPO_RTRN_DTLTMsg
    // getDsCpoRtrnDtlByCondition(NLZC309001PMsg param) {
    // DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtl = new DS_CPO_RTRN_DTLTMsg();
    // dsCpoRtrnDtl.setConditionValue("glblCmpyCd01",
    // param.glblCmpyCd.getValue());
    // dsCpoRtrnDtl.setConditionValue("svcConfigMstrPk01",
    // param.toSvcConfigMstrPk.getValue());
    // dsCpoRtrnDtl.setConditionValue("mdseCd01",
    // param.mdseCd.getValue());
    // dsCpoRtrnDtl.setSQLID("004");
    // DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArray =
    // (DS_CPO_RTRN_DTLTMsgArray)
    // EZDTBLAccessor.findByCondition(dsCpoRtrnDtl);
    // if (dsCpoRtrnDtlTMsgArray.length() == 0) {
    // return null;
    // }
    // DS_CPO_RTRN_DTLTMsg out = (DS_CPO_RTRN_DTLTMsg)
    // dsCpoRtrnDtlTMsgArray.get(0);
    // return out;
    // }
    // // END 2018/03/16 J.Kim [QC#23431,ADD]
    // END 2018/05/15 J.Kim [QC#25059,DEL]

    // START 2018/05/15 J.Kim [QC#25059,ADD]
    private static boolean getSvcMachMstrByCondition(NLZC309001PMsg param) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        svcMachMstrTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        svcMachMstrTMsg.setConditionValue("svcConfigMstrPk01", param.toSvcConfigMstrPk.getValue());
        svcMachMstrTMsg.setConditionValue("mdseCd01", param.mdseCd.getValue());
        svcMachMstrTMsg.setSQLID("004");

        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(svcMachMstrTMsg);
        if (svcMachMstrTMsgArray.length() == 0) {
            return false;
        }

        String svcMachTpCd = svcMachMstrTMsgArray.no(0).svcMachTpCd.getValue();
        if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
            return true;
        }

        return false;
    }
    // END 2018/05/15 J.Kim [QC#25059,ADD]

    // START 2018/06/25 J.Kim [QC#24844]
    private static CPOTMsg findByKeyCpo(String glblCmpyCd, String cpoOrdNum) {
        CPOTMsg cpo = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpo.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpo.cpoOrdNum, cpoOrdNum);
        CPOTMsg cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpo);
        return cpoTMsg;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getContrNumInfo(NLZC309001PMsg param) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmPrm.put("contrHldFlg", ZYPConstant.FLG_OFF_N);
        ssmPrm.put("svcConfigMstrPk", param.toSvcConfigMstrPk.getValue());
        return (Map<String, Object>) ssmBatchClient.queryObject("getContrNumInfo", ssmPrm);
    }
    // END 2018/06/25 J.Kim [QC#24844]
}
