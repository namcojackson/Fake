/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 * 
 * <pre>
 * Revenue Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/30/2013   Fujitsu         N.Nakazawa      New            WDS R-OM010,R-OM021, R-OM028, R-OM040
 * 09/26/2013   Fujitsu         D.Yanagisawa    Update         WDS Defect#2478
 * 12/10/2013   Fujitsu         T.Nakamura      Update         WDS Defect#2957
 * 05/17/2016   Fujitsu         H.Nagashima     Update         QC#7530
 * 09/28/2017   Fujitsu         H.Nagashima     Update         QC#20840
 * 10/25/2017   Fujitsu         S.Ohki          Update         Sol#430(QC#16347)
 * 12/11/2017   Fujitsu         K.Ishizuka      Update         QC#22931
 * 07/20/2018   Fujitsu         A.Kosai         Update         QC#26836
 *</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB001001;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.REV_RECOG_DTL_WRKTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NLZC002001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class NWCB001001_InventoryUpdate {
    //DEL WDS R-OM010 Start
    private static final String NWCM0090E = "NWCM0090E";
    //DEL WDS R-OM010 End
    private static final String SET_PARENT_LINE_SUB_NUM = "000";
    private static final String WH_DS = "DS";
    private static final String WH_NS = "NS";
    private Map<String, String> locStsCdMap;
    private final S21SsmBatchClient ssmClient;
    public NWCB001001_InventoryUpdate(S21SsmBatchClient ssmClient) {
        this.locStsCdMap = new HashMap<String, String>();
        this.ssmClient = ssmClient;
    }
    
    // 2018/07/20 QC#26836 Mod Start
//    public boolean callInvtyUpdApi(String glblCmpyCd, String salesDt, NLZC002001 dlzc002001, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg, SHPG_ORDTMsg shpgOrdTMsg, REV_RECOG_DTL_WRKTMsg revDtlTMsg){
    public boolean callInvtyUpdApi(String glblCmpyCd, String salesDt, NLZC002001 dlzc002001, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg, SHPG_ORDTMsg shpgOrdTMsg, REV_RECOG_DTL_WRKTMsg revDtlTMsg, BigDecimal unitCostAmt){
    // 2018/07/20 QC#26836 Mod End

        String invtyCtrlFlg = revDtlTMsg.invtyCtrlFlg.getValue();//Y:Tangible, else:Intangible
        String invtyValFlg = revDtlTMsg.invtyValFlg.getValue();//Y:with Cost, else:without Cost
        boolean isIntangibleItem    = ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg);
        boolean isValueControlItem  = ZYPConstant.FLG_ON_Y.equals(invtyValFlg);
        boolean isSetParentLine     = SET_PARENT_LINE_SUB_NUM.equals(cpoDTLTMsg.cpoDtlLineSubNum.getValue());
        boolean isRevRecogProcessed = ZYPConstant.FLG_ON_Y.equals(revDtlTMsg.revRecogFlg.getValue());
        if ((isIntangibleItem && !isValueControlItem) || isSetParentLine || !isRevRecogProcessed) {
            return true;
        }
        // 2018/07/20 QC#26836 Mod Start
//        return exeNLZC0020(glblCmpyCd, salesDt, dlzc002001, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg, shpgOrdTMsg, revDtlTMsg);
        return exeNLZC0020(glblCmpyCd, salesDt, dlzc002001, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg, shpgOrdTMsg, revDtlTMsg, unitCostAmt);
        // 2018/07/20 QC#26836 Mod End
    }
    
    
    // 2018/07/20 QC#26836 Mod Start
//    private boolean exeNLZC0020(String glblCmpyCd, String salesDt, NLZC002001 dlzc002001, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg, SHPG_ORDTMsg shpgOrdTMsg, REV_RECOG_DTL_WRKTMsg revDtlTMsg){
    private boolean exeNLZC0020(String glblCmpyCd, String salesDt, NLZC002001 dlzc002001, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg, SHPG_ORDTMsg shpgOrdTMsg, REV_RECOG_DTL_WRKTMsg revDtlTMsg, BigDecimal unitCostAmt){
    // 2018/07/20 QC#26836 Mod End
        NLZC002001PMsg dlzc002001PMsg = new NLZC002001PMsg();
        String trxCd = shpgPlnTMsg.trxCd.getValue();
        String trxRsn = shpgPlnTMsg.trxRsnCd.getValue();
        // 2018/07/20 QC#26836 Mod Start
//        setCommomParam(dlzc002001PMsg, glblCmpyCd, salesDt, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg, shpgOrdTMsg, revDtlTMsg);
        setCommomParam(dlzc002001PMsg, glblCmpyCd, salesDt, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg, shpgOrdTMsg, revDtlTMsg, unitCostAmt);
        // 2018/07/20 QC#26836 Mod End
        boolean isIntangibleItem = ZYPConstant.FLG_OFF_N.equals(revDtlTMsg.invtyCtrlFlg.getValue());
        boolean isSellThenBuy = (ZYPConstant.FLG_ON_Y.equals(shpgPlnTMsg.poReqFlg.getValue()));

        if (ZYPConstant.FLG_ON_Y.equals(shpgPlnTMsg.poReqFlg.getValue())) {
            dlzc002001PMsg.invtyLocCd.setValue("DS");
        }
        if (TRX.SALES.equals(trxCd)) {

            if (TRX_RSN.REGULAR_SALES_CASH_LEASE_MDSE.equals(trxRsn)
             || TRX_RSN.REGULAR_SALES_CASH_LEASE_INIT_SUP.equals(trxRsn)
             || TRX_RSN.REGULAR_SALES_CASH_LEASE_SUP.equals(trxRsn)
             || TRX_RSN.REGULAR_SALES_CASH_LEASE_CNTR_SUP.equals(trxRsn)
             || TRX_RSN.REGULAR_SALES_PRT_BILLABLE.equals(trxRsn)
            ) {
                if (!isIntangibleItem) {
                    return slsRegSlsWithCostOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                } else {
                    return slsRegSlsWithCostOMIntgWithCost(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                }
            } else if (TRX_RSN.REGULAR_SALES_ASSET.equals(trxRsn)) {
                return slsRegSlsAsstOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            // 2017/10/25 S21_NA#16347 Add Start
            } else if (TRX_RSN.LOAN_TO_SALES.equals(trxRsn) || TRX_RSN.REGULAR_SALES_ASSET_FOR_AJE_LINK.equals(trxRsn)) {
                return true;
            // 2017/10/25 S21_NA#16347 Add End
            } else {
                S21InfoLogOutput.println(NWCM0090E, new String[]{"TRX_CD:"+trxCd+" RSN_CD:"+trxRsn});
                return false;
            }
        } else if (TRX.EXPENSE_SHIPMENT.equals(trxCd)) {

            if (TRX_RSN.EXPENSE_SHIPMENT.equals(trxRsn)) {
                if (!isIntangibleItem) {
                    return expExpShipOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                } else {
                    return expExpShipOMIntgWithCost(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                }
            } else if (TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT.equals(trxRsn)
                    ||  TRX_RSN.EXPENSE_LOAN_DROP_SHIPMENT_STOCK_OUT.equals(trxRsn)) {
                // skip inventory update for loan intial supply
                return true;
            } else {
                S21InfoLogOutput.println(NWCM0090E, new String[]{"TRX_CD:"+trxCd+" RSN_CD:"+trxRsn});
                return false;
            }
        } else if (TRX.RENTAL_SHIPMENT.equals(trxCd)) {

            if (TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT.equals(trxRsn)) {
                return rntlRntlStkOOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            } else if (TRX_RSN.DROP_SHIPMENT_RENTAL_STOCK_OUT.equals(trxRsn)) {
                return rntlDropShipRntlStkOInbd(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            } else if (TRX_RSN.RENTAL_STOCK_OUT_INTANGIBLE_WITH_COST.equals(trxRsn)) {
                return rntlRntlStkOIntgWithCostOMExp(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            } else if (TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_EXPENSE.equals(trxRsn)) {
                if(isSellThenBuy) {
                    return rntlRntlStkOExpSellThenBuyOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                    
                //} else if (!isIntangibleItem) { // 2017/12/11 S21_NA#22931 MOD
                } else if (isIntangibleItem) {
                    return rntlRntlStkOExpIntgWithCostOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                    
                } else {
                    return rntlRntlStkOExpOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                    
                }
            } else {
                S21InfoLogOutput.println(NWCM0090E, new String[]{"TRX_CD:"+trxCd+" RSN_CD:"+trxRsn});
                return false;
            }
        } else if (TRX.EMSD_SHIPMENT.equals(trxCd)) {

            if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT.equals(trxRsn)) {
                return emsdEmsdStkOOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            } else if (TRX_RSN.EMSD_DROP_SHIPMENT_STOCK_OUT.equals(trxRsn)) {
                return emsdEmsdDropStkOOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            } else if (TRX_RSN.EMSD_STOCK_OUT_INTANGIBLE_WITH_COST.equals(trxRsn)) {
                return emsdEmsdStkOIntgWithCostOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            } else if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_EXPENSE.equals(trxRsn)) {
                if (isSellThenBuy) {
                    return emsdEmsdStkOExpSellThenBuyOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                    
                } else if (isIntangibleItem) {
                    return emsdEmsdStkOExpIntgWithCostOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                    
                } else {
                    return emsdEmsdStkOExpOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                    
                }
            } else if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_ASSET.equals(trxRsn)) {
                return emsdEmsdStkOAssetOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
                
            }
        } else if (TRX.MOVEMENT.equals(trxCd)) {

            if (TRX_RSN.OFF_THE_BOOK_STOCK_OUT.equals(trxRsn)) {
                return moveOffTheBookStkOOM(dlzc002001,dlzc002001PMsg, cpoTMsg, cpoDTLTMsg, shpgPlnTMsg);
            } else {
                S21InfoLogOutput.println(NWCM0090E, new String[]{"TRX_CD:"+trxCd+" RSN_CD:"+trxRsn});
                return false;
            }
            
        }
        S21InfoLogOutput.println(NWCM0090E, new String[]{"TRX_CD:"+trxCd+" RSN_CD:"+trxRsn});
        return false;
    }

    private boolean slsRegSlsWithCostOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        dlzc002001PMsg.invNum.setValue(shpgPlnTMsg.invNum.getValue());
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean slsRegSlsWithCostOMIntgWithCost(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        dlzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_INTG_WITH_COST);
        dlzc002001PMsg.invNum.setValue(shpgPlnTMsg.invNum.getValue());
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean slsRegSlsAsstOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        dlzc002001PMsg.invNum.setValue(shpgPlnTMsg.invNum.getValue());
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean expExpShipOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean expExpShipOMIntgWithCost(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        dlzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_INTG_WITH_COST);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean rntlRntlStkOOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean rntlDropShipRntlStkOInbd(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }
    private boolean rntlRntlStkOIntgWithCostOMExp(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean rntlRntlStkOExpSellThenBuyOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        dlzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SELL_THEN_BUY);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean rntlRntlStkOExpIntgWithCostOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        dlzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_INTG_WITH_COST);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean rntlRntlStkOExpOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean emsdEmsdStkOOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());
        dlzc002001PMsg.invNum.setValue(shpgPlnTMsg.invNum.getValue());      //QC#20840 add

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }
    
    private boolean emsdEmsdDropStkOOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean emsdEmsdStkOIntgWithCostOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        dlzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_INTG_WITH_COST);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean emsdEmsdStkOExpSellThenBuyOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        dlzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SELL_THEN_BUY);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean emsdEmsdStkOExpIntgWithCostOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        dlzc002001PMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_INTG_WITH_COST);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean emsdEmsdStkOExpOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean emsdEmsdStkOAssetOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        setBillSellShip(dlzc002001PMsg, cpoTMsg, cpoDTLTMsg);
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    private boolean moveOffTheBookStkOOM(NLZC002001 dlzc002001,NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OM);
        setSOItem(dlzc002001PMsg, shpgPlnTMsg);
        dlzc002001PMsg.shipToCustCd.setValue(cpoDTLTMsg.shipToCustCd.getValue());
        dlzc002001PMsg.shipToCustNm.setValue(cpoDTLTMsg.shipToLocNm.getValue());
        dlzc002001PMsg.uomCd.setValue(cpoDTLTMsg.custUomCd.getValue());

        //Call NLZC002001 Inventory Update API
        return callInventoryUpdateAPI(dlzc002001, dlzc002001PMsg);
    }

    // 2018/07/20 QC#26836 Mod Start
//    private void setCommomParam(NLZC002001PMsg dlzc002001PMsg, String glblCmpyCd, String salesDt, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg, SHPG_ORDTMsg shpgOrdTMsg, REV_RECOG_DTL_WRKTMsg revDtlTMsg){
    private void setCommomParam(NLZC002001PMsg dlzc002001PMsg, String glblCmpyCd, String salesDt, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg, SHPG_PLNTMsg shpgPlnTMsg, SHPG_ORDTMsg shpgOrdTMsg, REV_RECOG_DTL_WRKTMsg revDtlTMsg, BigDecimal unitCostAmt){
    // 2018/07/20 QC#26836 Mod End

        dlzc002001PMsg.glblCmpyCd.setValue(glblCmpyCd);
        dlzc002001PMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        dlzc002001PMsg.mdseCd.setValue(shpgPlnTMsg.mdseCd.getValue());
        dlzc002001PMsg.invtyLocCd.setValue(shpgPlnTMsg.invtyLocCd.getValue());

        if (CR_REBIL.REBILL.equals(cpoDTLTMsg.crRebilCd.getValue())){
            ZYPEZDItemValueSetter.setValue(dlzc002001PMsg.locStsCd, LOC_STS.DC_STOCK);
        } else {
            ZYPEZDItemValueSetter.setValue(dlzc002001PMsg.locStsCd, LOC_STS.WAITING_FOR_INSTALLATION);
        }

        dlzc002001PMsg.stkStsCd.setValue(cpoDTLTMsg.stkStsCd.getValue());
        dlzc002001PMsg.xxRqstQty.setValue(shpgPlnTMsg.ordQty.getValue());
        
        dlzc002001PMsg.trxCd.setValue(shpgPlnTMsg.trxCd.getValue());
        dlzc002001PMsg.trxRsnCd.setValue(shpgPlnTMsg.trxRsnCd.getValue());
        dlzc002001PMsg.invtyTrxDt.setValue(salesDt);
        dlzc002001PMsg.sysSrcCd.setValue(cpoTMsg.sysSrcCd.getValue());
        dlzc002001PMsg.cpoOrdNum.setValue(cpoDTLTMsg.cpoOrdNum.getValue());
        dlzc002001PMsg.cpoDtlLineNum.setValue(cpoDTLTMsg.cpoDtlLineNum.getValue());
        dlzc002001PMsg.cpoDtlLineSubNum.setValue(cpoDTLTMsg.cpoDtlLineSubNum.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(revDtlTMsg.poReqFlg.getValue())) {
            dlzc002001PMsg.shipFromLocCustCd.setValue(WH_DS);
        } else if (ZYPConstant.FLG_OFF_N.equals(revDtlTMsg.invtyCtrlFlg.getValue())) {
            dlzc002001PMsg.shipFromLocCustCd.setValue(WH_NS);
        } else {
            dlzc002001PMsg.shipFromLocCustCd.setValue(shpgPlnTMsg.invtyLocCd.getValue());
        }
        dlzc002001PMsg.expProjCd.setValue(cpoDTLTMsg.coaProjCd.getValue());
        // 2018/07/20 QC#26836 Add Start
        if (ZYPCommonFunc.hasValue(unitCostAmt)) {
            dlzc002001PMsg.shipCostAmt.setValue(unitCostAmt.multiply(cpoDTLTMsg.ordQty.getValue()));
        }
        // 2018/07/20 QC#26836 Add End
    }

    private void setSOItem(NLZC002001PMsg dlzc002001PMsg, SHPG_PLNTMsg shpgPlnTMsg){
        dlzc002001PMsg.soNum.setValue(shpgPlnTMsg.soNum.getValue());
        dlzc002001PMsg.soSlpNum.setValue(shpgPlnTMsg.soSlpNum.getValue());
        dlzc002001PMsg.bolNum.setValue(shpgPlnTMsg.bolNum.getValue());
        dlzc002001PMsg.proNum.setValue(shpgPlnTMsg.proNum.getValue());
        dlzc002001PMsg.carrCd.setValue(shpgPlnTMsg.carrCd.getValue());
    }

    private void setBillSellShip(NLZC002001PMsg dlzc002001PMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDTLTMsg){
        dlzc002001PMsg.billToCustCd.setValue(cpoTMsg.billToCustCd.getValue());
        dlzc002001PMsg.sellToCustCd.setValue(cpoTMsg.sellToCustCd.getValue());
        dlzc002001PMsg.shipToCustCd.setValue(cpoDTLTMsg.shipToCustCd.getValue());
        dlzc002001PMsg.shipToCustNm.setValue(cpoDTLTMsg.shipToLocNm.getValue());
    }

    private boolean callInventoryUpdateAPI(NLZC002001 dlzc002001, NLZC002001PMsg pMsg) {

        // --------------------------------------------------
        // NLZC002001 - Inventory Update API
        // --------------------------------------------------
        dlzc002001.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }
        return true;
    }

}
