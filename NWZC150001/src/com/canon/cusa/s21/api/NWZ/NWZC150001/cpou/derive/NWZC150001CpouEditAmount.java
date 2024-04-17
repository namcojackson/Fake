/**
 * <pre>
 * CPO Update API Edit Amount Process
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/10/11   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2018/02/21   Fujitsu         K.Ishizuka      Update          S21_NA#23815
 * 2019/11/15   Fujitsu         S.Takami        Update          S21_NA#54199
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * 2021/04/19   CITS            A.Marte         Update          QC#58549
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.db.CCYTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150001_priceListPMsgArray;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDiscountBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouExistsCdInDbCheck;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouOtherCheck;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouValidCheck;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001EditPriceAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001EditPriceAmountInfo;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001UnitPriceData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWZC150001CpouEditAmount {

    private static final NWZC150001CpouEditAmount MY_INSTANCE = new NWZC150001CpouEditAmount();

    private S21SsmBatchClient ssmClient = null;

    private NWZC150001CpouEditAmount() {
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    public static NWZC150001CpouEditAmount getInstance() {
        return MY_INSTANCE;
    }
    /**
     * Amount of money edit processing
     * 
     * <pre>
     * ･ Save
     *    It figures out an amount from the amount of the parameter and the result is reflected in each details.
     * ･ Submit
     *    It figures out an amount from the amount of the parameter and the result is reflected in each details.
     * ･ Modify
     *    The shipment amount is calculated and the amount of leaving (shipment quotation case) and the remainder is calculated again by the order amount (order price).
     * ･ Cancel
     *    The amount of money for the order is pulled.
     *    Because it is not possible to cancel, shipped details are left.
     *
     *   About the set commodity, the number of compositions and the ratio of children are acquired from parents' commodity codes, and each child is calculated.
     *   Moreover, the total of the calculation result of the component of the set commodity is set to the set commodity.
     *
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param pMsg NWZC150001PMsg
     */
    public void editAmountForWDS(NWZC150001CpouBean cpouBean, //
            List<NWZC150002PMsg> resPMsgList, //
            NWZC150001PMsg pMsg, //
            NWZC150001CpouLocalCache localCache, //
            S21ApiMessageIdMgr msgIdMgr) {

        String rqstTpCd = cpouBean.getRqstTpCd();
        // ------------------------------------------------------
        // initialize edit Amount for MODIFY, CANCEL
        // ------------------------------------------------------
        if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd) || NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd)) {

            addTotAmtForModify(cpouBean);
        }
        // ------------------------------------------------------
        // edit Amount,Qty and LineStatus by CANCEL
        // ------------------------------------------------------
        if (NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd)) {

            editAmountForCpoByCancel(cpouBean, resPMsgList, pMsg);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }
        }

        // ------------------------------------------------------
        // i.  edit Amount by SAVE, SUBMIT, MODIFY(Add), MODIFY
        // ii. edit LineStatus by MODIFY
        // ------------------------------------------------------
        if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd) || NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd) || NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {

            // calcuration GrossPriceAmount From PMsg Param
            editEntDealGrsUnitPrcAmt(cpouBean, resPMsgList, pMsg);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }
            // calcuration NetPriceAmount From PMsg Param
            editEntDealNetUnitPrcAmt(cpouBean, resPMsgList, pMsg);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // edit CPO/CPO_DTL price Data
            editAmountForCpoByNew(cpouBean, resPMsgList, pMsg, localCache, msgIdMgr);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // edit PRC_DTL price Data
            editAmountForPrcDtlByNew(cpouBean, resPMsgList, pMsg, msgIdMgr);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }
        }

        boolean dealFlag = isCurrencyJudgment(cpouBean);

        setTotalAmount(cpouBean, dealFlag);

        editAmountForPrcDtl(pMsg, cpouBean, resPMsgList);
    }


    // 20121130 M.Fuji WDS Solution#104,105 Pricing Start
    private static void addTotAmtForModify(NWZC150001CpouBean cpouBean) {

        cpouBean.setEntCpoTotDealSlsAmt(ZERO);
        cpouBean.setEntCpoTotDealNetAmt(ZERO);
        cpouBean.setEntCpoTotFuncSlsAmt(ZERO);
        cpouBean.setEntCpoTotFuncNetAmt(ZERO);
        cpouBean.setCpoTotDealSlsAmt(ZERO);
        cpouBean.setCpoTotDealNetAmt(ZERO);
        cpouBean.setCpoTotFuncSlsAmt(ZERO);
        cpouBean.setCpoTotFuncNetAmt(ZERO);

        final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
        reqCpoDtlTMsg.setSQLID("001");
        reqCpoDtlTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        reqCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpouBean.getCpoOrdNum());
        final CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) findByCondition(reqCpoDtlTMsg);

        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
            CPO_DTLTMsg cpoDtlTMsg = cpoDtlTMsgArray.no(i);
            String cpoDtlLineNum = cpoDtlTMsg.cpoDtlLineNum.getValue();
            String cpoDtlLineSubNum = cpoDtlTMsg.cpoDtlLineSubNum.getValue();

            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg.ordLineStsCd.getValue())) {
                continue;
            }

            boolean nonModifyLineFlg = true;
            for (NWZC150001CpouDetailBean cpoDtlBean : cpouBean.getDtlBeanList()) {
                if (cpoDtlLineNum.equals(cpoDtlBean.getCpoDtlLineNum()) && cpoDtlLineSubNum.equals(cpoDtlBean.getCpoDtlLineSubNum())) {
                    nonModifyLineFlg = false;
                    break;
                }
            }

            // S21_NA#2846#16 if (nonModifyLineFlg && !NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtlLineSubNum)) {
            if (nonModifyLineFlg) { // S21_NA#2846#16
                BigDecimal entCpoTotDealSlsAmt = NWZC150001Common.add(cpouBean.getEntCpoTotDealSlsAmt(), cpoDtlTMsg.entCpoDtlDealSlsAmt.getValue());
                cpouBean.setEntCpoTotDealSlsAmt(entCpoTotDealSlsAmt);

                BigDecimal entCpoTotDealNetAmt = NWZC150001Common.add(cpouBean.getEntCpoTotDealNetAmt(), cpoDtlTMsg.entCpoDtlDealNetAmt.getValue());
                cpouBean.setEntCpoTotDealNetAmt(entCpoTotDealNetAmt);

                BigDecimal entCpoTotFuncSlsAmt = NWZC150001Common.add(cpouBean.getEntCpoTotFuncSlsAmt(), cpoDtlTMsg.entCpoDtlFuncSlsAmt.getValue());
                cpouBean.setEntCpoTotFuncSlsAmt(entCpoTotFuncSlsAmt);

                BigDecimal entCpoTotFuncNetAmt = NWZC150001Common.add(cpouBean.getEntCpoTotFuncNetAmt(), cpoDtlTMsg.entCpoDtlFuncNetAmt.getValue());
                cpouBean.setEntCpoTotFuncNetAmt(entCpoTotFuncNetAmt);

                BigDecimal cpoTotDealSlsAmt = NWZC150001Common.add(cpouBean.getCpoTotDealSlsAmt(), cpoDtlTMsg.cpoDtlDealSlsAmt.getValue());
                cpouBean.setCpoTotDealSlsAmt(cpoTotDealSlsAmt);

                BigDecimal cpoTotDealNetAmt = NWZC150001Common.add(cpouBean.getCpoTotDealNetAmt(), cpoDtlTMsg.cpoDtlDealNetAmt.getValue());
                cpouBean.setCpoTotDealNetAmt(cpoTotDealNetAmt);

                BigDecimal cpoTotFuncSlsAmt = NWZC150001Common.add(cpouBean.getCpoTotFuncSlsAmt(), cpoDtlTMsg.cpoDtlFuncSlsAmt.getValue());
                cpouBean.setCpoTotFuncSlsAmt(cpoTotFuncSlsAmt);

                BigDecimal cpoTotFuncNetAmt = NWZC150001Common.add(cpouBean.getCpoTotFuncNetAmt(), cpoDtlTMsg.cpoDtlFuncNetAmt.getValue());
                cpouBean.setCpoTotFuncNetAmt(cpoTotFuncNetAmt);
            }
        }
    }

    private void editAmountForCpoByCancel(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, NWZC150001PMsg pMsg) {

        for (int i = 0, end = cpouBean.getDtlBeanList().size(); i < end; i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);
            CPO_DTLTMsg cpoDtlTMsg = NWZC150001CpouExistsCdInDbCheck.getCpoDtlByPK(cpouBean, cpoDtlBean);

            BigDecimal cancelQty = ZERO;

            SHPG_PLNTMsgArray shpgPlanTMsgArray = getShpgPlanRelationCPOForUpdateNoWait(cpouBean, cpoDtlBean, pMsg);

            if (shpgPlanTMsgArray.getValidCount() > 0) { // S21_NA#1331
                // get CancelQty
                for (int j = 0; j < shpgPlanTMsgArray.getValidCount(); j++) {
    
                    SHPG_PLNTMsg shpgPlnTMsg = shpgPlanTMsgArray.no(j);
                    // 2019/11/27 QC#52339 Mod Start
                    //if (canCancelShpgSts(shpgPlnTMsg) //
                    if (canCancelShpgSts(shpgPlnTMsg, true) //
                    // 2019/11/27 QC#52339 Mod End 
                            || isIttDropShipCancelable(shpgPlnTMsg, cpoDtlTMsg)) { // 2017/10/11 S21_NA#21300 Add isIttDropShipCancelable()
                        cancelQty = NWZC150001Common.add(cancelQty, shpgPlnTMsg.ordQty.getValue());
                    }
                }
                // 20130119 Defect#242 M.Fuji Start
                // get Freight Total Price of Line
                BigDecimal LineTotDealFrtAmt = ZERO;
                BigDecimal LineTotFuncFrtAmt = ZERO;
                for (int j = 0; j < shpgPlanTMsgArray.getValidCount(); j++) {
    
                    SHPG_PLNTMsg shpgPlnTMsg = shpgPlanTMsgArray.no(j);
                    LineTotDealFrtAmt.add(shpgPlnTMsg.spTotDealFrtAmt.getValue());
                    LineTotFuncFrtAmt.add(shpgPlnTMsg.spTotFuncFrtAmt.getValue());
                }
                cpoDtlBean.setLineTotDealFrtAmt(LineTotDealFrtAmt);
                cpoDtlBean.setLineTotFuncFrtAmt(LineTotFuncFrtAmt);
                // 20130119 Defect#242 M.Fuji End
            } else { // S21_NA#1331
                cancelQty = cpoDtlTMsg.ordQty.getValue();
            }
            //In case All SHPG_PLN of CPO_DTL is canceled ("Canceled SHPG_PLN" is updated to cancel).
            if (cancelQty.compareTo(cpoDtlTMsg.ordQty.getValue()) == 0) {

                cpoDtlBean.setOrdLineStsCd(ORD_LINE_STS.CANCELLED);
                cpoDtlBean.setCancQty(cancelQty);

                cpoDtlBean.setEntDealNetUnitPrcAmt(cpoDtlTMsg.entDealNetUnitPrcAmt.getValue());
                cpoDtlBean.setEntCpoDtlDealNetAmt(cpoDtlTMsg.entCpoDtlDealNetAmt.getValue());
                cpoDtlBean.setEntCpoDtlDealSlsAmt(cpoDtlTMsg.entCpoDtlDealSlsAmt.getValue());
                cpoDtlBean.setEntFuncNetUnitPrcAmt(cpoDtlTMsg.entFuncNetUnitPrcAmt.getValue());
                cpoDtlBean.setEntCpoDtlFuncNetAmt(cpoDtlTMsg.entCpoDtlFuncNetAmt.getValue());
                cpoDtlBean.setEntCpoDtlFuncSlsAmt(cpoDtlTMsg.entCpoDtlFuncSlsAmt.getValue());
                cpoDtlBean.setCpoDtlDealNetAmt(cpoDtlTMsg.cpoDtlDealNetAmt.getValue());
                cpoDtlBean.setCpoDtlDealSlsAmt(cpoDtlTMsg.cpoDtlDealSlsAmt.getValue());
                cpoDtlBean.setCpoDtlFuncNetAmt(cpoDtlTMsg.cpoDtlFuncNetAmt.getValue());
                cpoDtlBean.setCpoDtlFuncSlsAmt(cpoDtlTMsg.cpoDtlFuncSlsAmt.getValue());

                //In case All SHPG_PLN of CPO_DTL is not canceled.
            } else {

                final BigDecimal ordQty = NWZC150001Common.subtract(cpoDtlTMsg.ordQty.getValue(), cancelQty);

                // 20130131 Defect#390 M.Fuji Start
                // 2017/03/31 S21_NA#Review structure Lv.2 Mod Start
//                pMsg.ordQty_A1.setValue(ordQty);
                cpouBean.setOrdQty_A1(ordQty);
                // 2017/03/31 S21_NA#Review structure Lv.2 Mod End
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1215E, resPMsgList, i);
                return;
                //                cpoDtlBean.setOrdQty(ordQty);
                //                setOrderedCustomerUOMQuantity(cpouBean, cpoDtlBean, resPMsgList, i);
                //
                //                cpoDtlBean.setPreModifyFlg(ZYPConstant.FLG_ON_Y);
                //
                //                // set CpoDtl Close Status
                //                // In case all SHPG_PLN of CPO_DTL is invoiced.
                //                // (OrdQty of cpoDtlBean is the total number of the OrderQty whose ShippingStatus is later than '25:Allocated')
                //                if (cpoDtlBean.getOrdQty().compareTo(getInvoicedQty(shpgPlanTMsgArray)) == 0) {
                //                    cpoDtlBean.setOrdLineStsCd(ORD_LINE_STS.CLOSED);
                //                }
                //
                //                BigDecimal spDealUnitPrcAmt = getSpDealUnitPrcAmt(shpgPlanTMsgArray);
                //                BigDecimal spFuncUnitPrcAmt = getSpFuncUnitPrcAmt(shpgPlanTMsgArray);
                //
                //                final Integer ccyScale = getDealCcyDigit(cpouBean.getGlblCmpyCd(), cpoDtlBean.getCcyCd());
                //                final Integer ccyScaleFunc = getFuncCcyDigit(cpouBean.getGlblCmpyCd());
                //
                //                cpoDtlBean.setEntDealNetUnitPrcAmt(cpoDtlTMsg.entDealNetUnitPrcAmt.getValue());
                //                cpoDtlBean.setEntCpoDtlDealNetAmt(cpoDtlTMsg.entDealNetUnitPrcAmt.getValue().multiply(ordQty).setScale(ccyScale, RoundingMode.HALF_UP));
                //                cpoDtlBean.setEntCpoDtlDealSlsAmt(spDealUnitPrcAmt.multiply(ordQty).setScale(ccyScale, RoundingMode.HALF_UP));
                //                cpoDtlBean.setEntFuncNetUnitPrcAmt(cpoDtlTMsg.entFuncNetUnitPrcAmt.getValue());
                //                cpoDtlBean.setEntCpoDtlFuncNetAmt(cpoDtlTMsg.entFuncNetUnitPrcAmt.getValue().multiply(ordQty).setScale(ccyScaleFunc, RoundingMode.HALF_UP));
                //                cpoDtlBean.setEntCpoDtlFuncSlsAmt(spFuncUnitPrcAmt.multiply(ordQty).setScale(ccyScaleFunc, RoundingMode.HALF_UP));
                //
                //                cpoDtlBean.setCpoDtlDealNetAmt(cpoDtlBean.getEntCpoDtlDealNetAmt());
                //                cpoDtlBean.setCpoDtlDealSlsAmt(cpoDtlBean.getEntCpoDtlDealSlsAmt());
                //                cpoDtlBean.setCpoDtlFuncNetAmt(cpoDtlBean.getEntCpoDtlFuncNetAmt());
                //                cpoDtlBean.setCpoDtlFuncSlsAmt(cpoDtlBean.getEntCpoDtlFuncSlsAmt());
                //
                //                addTotAmt(cpouBean, cpoDtlBean, pMsg, true);
                //                if (NWZC150001CpouCommon.hasError(resPMsgList)) {
                //                    return;
                //                }
                // 20130131 Defect#390 M.Fuji End
            }
        }
    }

//    private static BigDecimal getSpDealUnitPrcAmt(SHPG_PLNTMsgArray resShpgPlnTMsgArray) {
//
//        BigDecimal spDealUnitPrcAmt = ZERO;
//
//        if (resShpgPlnTMsgArray.getValidCount() > 0) {
//            spDealUnitPrcAmt = resShpgPlnTMsgArray.no(ZERO.intValue()).spDealUnitPrcAmt.getValue();
//        }
//
//        return spDealUnitPrcAmt;
//    }
//
//    private static BigDecimal getSpFuncUnitPrcAmt(SHPG_PLNTMsgArray resShpgPlnTMsgArray) {
//
//        BigDecimal spFuncUnitPrcAmt = ZERO;
//
//        if (resShpgPlnTMsgArray.getValidCount() > 0) {
//            spFuncUnitPrcAmt = resShpgPlnTMsgArray.no(ZERO.intValue()).spFuncUnitPrcAmt.getValue();
//        }
//
//        return spFuncUnitPrcAmt;
//    }

    private static void editAmountForPrcDtlByNew(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {

        int idx = 0;
        for (int i = 0, end = cpouBean.getDtlBeanList().size(); i < end; i++) {

            NWZC150001CpouDetailBean detailMsg = cpouBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd()) && NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(detailMsg.getDtlRqstTpCd())) {
                continue;
            }
            // idx means Set Merchandise Index or Regular Merchandise Index
            idx = i;

            // In case Detail Line is Set BOM Header
            if (ZYPConstant.FLG_ON_Y.equals(detailMsg.getBomHeaderFlg())) {
                List<NWZC150001CpouDiscountBean> discPrcList = new ArrayList<NWZC150001CpouDiscountBean>();
                detailMsg.setDiscPrcList(discPrcList);
                continue;
            }

            String cpoDtlLineNum = detailMsg.getCpoDtlLineNum();
            String cpoDtlLineSubNum = detailMsg.getCpoDtlLineSubNum();

            List<NWZC150001_priceListPMsg> linePriceList = getLinePriceList(cpouBean, cpoDtlLineNum, cpoDtlLineSubNum);

            // get DiscountPrice (dealPerUnitFixAmt, dealPrmoNetUnitPrcAmt and dealSlsPctNum)
            List<NWZC150001CpouDiscountBean> discPrcList = getDiscountPriceList(cpouBean, detailMsg, linePriceList, detailMsg.getEntDealGrsUnitPrcAmt());
            detailMsg.setDiscPrcList(discPrcList);

            // get FuncPrice and SetComponentPrice For Discount
            for (NWZC150001CpouDiscountBean discPrcData : discPrcList) {

                //  get dealPerUnitFixAmt (FuncPrice and SetComponentPrice)
                BigDecimal dealPerUnitFixAmt = discPrcData.getDealPerUnitFixAmt();
                NWXC001001UnitPriceData perUnitFixAmtData = callNWXC001001EditPriceAmount(cpouBean, detailMsg, dealPerUnitFixAmt, dealPerUnitFixAmt, resPMsgList, i);
                if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                    return;
                }

                //  get dealPrmoNetUnitPrcAmt (FuncPrice and SetComponentPrice)
                BigDecimal dealPrmoNetUnitPrcAmt = discPrcData.getDealPrmoNetUnitPrcAmt();
                NWXC001001UnitPriceData prmoNetUnitPrcAmtData = callNWXC001001EditPriceAmount(cpouBean, detailMsg, dealPrmoNetUnitPrcAmt, dealPrmoNetUnitPrcAmt, resPMsgList, i);
                if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                    return;
                }

                // set Detail perUnitFixAmt, prmoNetUnitPrcAmt
                calcPrcDtlPriceAmount(discPrcData, perUnitFixAmtData, prmoNetUnitPrcAmtData, resPMsgList, i);
                if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                    return;
                }

                if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(detailMsg.getCpoDtlLineSubNum())) {
                    // set SetComponentPrice
                    Map<String, NWXC001001UnitPriceData> setPerUnitFixAmtMap = new LinkedHashMap<String, NWXC001001UnitPriceData>();
                    Map<String, NWXC001001UnitPriceData> setPrmoNetUnitPrcMap = new LinkedHashMap<String, NWXC001001UnitPriceData>();

                    for (NWXC001001UnitPriceData setPerUnitFixAmt : perUnitFixAmtData.getChildUnitPrcDataList()) {
                        setPerUnitFixAmtMap.put(setPerUnitFixAmt.getMdseCd(), setPerUnitFixAmt);
                    }
                    for (NWXC001001UnitPriceData setPrmoNetUnitPrc : prmoNetUnitPrcAmtData.getChildUnitPrcDataList()) {
                        setPrmoNetUnitPrcMap.put(setPrmoNetUnitPrc.getMdseCd(), setPrmoNetUnitPrc);
                    }

                    for (int j = i + 1, cmp = cpouBean.getDtlBeanList().size(); j < cmp; j++) {

                        NWZC150001CpouDetailBean cmpDetailMsg = cpouBean.getDtlBeanList().get(j);

                        if (!detailMsg.getCpoDtlLineNum().equals(cmpDetailMsg.getCpoDtlLineNum())) {
                            break;
                        }

                        // S21_NA#2846#3 start
                         // get DiscountPrice (dealPerUnitFixAmt,
                         // dealPrmoNetUnitPrcAmt and dealSlsPctNum)
//                        List<NWZC150001CpouDiscountBean> cmpDiscPrcList = cmpDetailMsg.getDiscPrcList();
//
//                        if (cmpDiscPrcList == null) {
//                            cmpDiscPrcList = new ArrayList<NWZC150001CpouDiscountBean>();
//                            cmpDetailMsg.setDiscPrcList(cmpDiscPrcList);
//                        }
//
//                        NWZC150001CpouDiscountBean cmpDiscPrcData = getCmpDiscountPrice(discPrcData);
//                        cmpDiscPrcList.add(cmpDiscPrcData);
//
//                        NWXC001001UnitPriceData setPerUnitFixAmtData = setPerUnitFixAmtMap.get(cmpDetailMsg.getMdseCd());
//                        if (setPerUnitFixAmtData == null) {
//                            setPerUnitFixAmtData = setPerUnitFixAmtMap.get(cmpDetailMsg.getMdseCd().substring(0, 8));
//                        }
//
//                        NWXC001001UnitPriceData setPrmoNetUnitPrcAmtData = setPrmoNetUnitPrcMap.get(cmpDetailMsg.getMdseCd());
//                        if (setPrmoNetUnitPrcAmtData == null) {
//                            setPrmoNetUnitPrcAmtData = setPrmoNetUnitPrcMap.get(cmpDetailMsg.getMdseCd().substring(0, 8));
//                        }
//
//                        // set Detail perUnitFixAmt, prmoNetUnitPrcAmt
//                        calcPrcDtlPriceAmount(cmpDiscPrcData, setPerUnitFixAmtData, setPrmoNetUnitPrcAmtData, resPMsgList, i);
//                        if (NWZC150001CpouCommon.hasError(resPMsgList)) {
//                            return;
//                        }
                        // S21_NA#2846#3 end
                        idx = j;
                    }
                }
            }
            // skip next Set Merchandise Index or next Regular Merchandise Index
            i = idx;
        }
    }

    private static void editEntDealGrsUnitPrcAmt(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, NWZC150001PMsg pMsg) {

        for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);
            final String cpoDtlLineNum = cpoDtlBean.getCpoDtlLineNum();
            final String cpoDtlLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();

            BigDecimal entDealGrsUnitPrcAmt = ZERO;

            List<NWZC150001_priceListPMsg> linePriceList = getLinePriceList(cpouBean, cpoDtlLineNum, cpoDtlLineSubNum);

            // 20130107 Defect#42 M.Fuji Start
            //            if (exsistSpecialPrice(linePriceList)) {
            if (exsistSpecialPrice(cpoDtlBean)) {
                // 20130107 Defect#42 M.Fuji End
                //set Special Price
                for (NWZC150001_priceListPMsg priceData : linePriceList) {

                    if (PRC_DTL_GRP.SPECIAL_PRICE.equals(priceData.prcDtlGrpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(priceData.prcCondManDelFlg.getValue())) {
                        entDealGrsUnitPrcAmt = NWZC150001Common.add(entDealGrsUnitPrcAmt, priceData.unitPrcAmt.getValue());
                    }
                }
            } else {
                //set Base Price
                for (NWZC150001_priceListPMsg priceData : linePriceList) {

                    if (PRC_DTL_GRP.BASE_PRICE.equals(priceData.prcDtlGrpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(priceData.prcCondManDelFlg.getValue())) {
                        entDealGrsUnitPrcAmt = NWZC150001Common.add(entDealGrsUnitPrcAmt, priceData.unitPrcAmt.getValue());
                    }
                }
            }

            cpoDtlBean.setEntDealGrsUnitPrcAmt(checkAmount(entDealGrsUnitPrcAmt, resPMsgList, i));
        }
    }

    private static void editEntDealNetUnitPrcAmt(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, NWZC150001PMsg pMsg) {

        for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);
            final String cpoDtlLineNum = cpoDtlBean.getCpoDtlLineNum();
            final String cpoDtlLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();

            BigDecimal entDealNetUnitPrcAmt = cpoDtlBean.getEntDealGrsUnitPrcAmt();

            List<NWZC150001_priceListPMsg> linePriceList = getLinePriceList(cpouBean, cpoDtlLineNum, cpoDtlLineSubNum);

            // 20130107 Defect#42 M.Fuji Start
            //            boolean exsistSpPrcFlg = exsistSpecialPrice(linePriceList);
            boolean exsistSpPrcFlg = exsistSpecialPrice(cpoDtlBean);
            // 20130107 Defect#42 M.Fuji End

            for (NWZC150001_priceListPMsg priceData : linePriceList) {
                if (isDiscPrice(priceData, exsistSpPrcFlg)) {
                    entDealNetUnitPrcAmt = NWZC150001Common.subtract(entDealNetUnitPrcAmt, priceData.unitPrcAmt.getValue());
                }
            }

            // S21_NA#2082
            // if (BigDecimal.ZERO.compareTo(entDealNetUnitPrcAmt) >
            // 0) {
            // NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZM0148E, resPMsgList, i);
            // } else {
            cpoDtlBean.setEntDealNetUnitPrcAmt(entDealNetUnitPrcAmt);
            // }
        }
    }

    /**
     * Amount of money edit processing (New)
     * 
     * <pre>
     * When RequestType is only Save, Submit and Modify(Add), it processes it.
     * </pre>
     * @param pMsg NWZC150001PMsg
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void editAmountForCpoByNew(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, NWZC150001PMsg pMsg, NWZC150001CpouLocalCache localCache, S21ApiMessageIdMgr msgIdMgr) {

        int idx = 0;
        for (int i = 0, end = cpouBean.getDtlBeanList().size(); i < end; i++) {

            NWZC150001CpouDetailBean detailMsg = cpouBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd()) && NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(detailMsg.getDtlRqstTpCd())) {
                continue;
            }

            // idx is Set Merchandise Index or Regular Merchandise Index
            idx = i;

            // In case Detail Line is Set BOM Header
            if (ZYPConstant.FLG_ON_Y.equals(detailMsg.getBomHeaderFlg())) {

                calcCpoPriceAmountForBOMHead(detailMsg);
                setOrderedCustomerUOMQuantity(cpouBean, detailMsg, resPMsgList, idx, localCache);
                continue;
            }

            // get ItemPrice (FuncPrice and SetComponentPrice)
            BigDecimal grsPrice = detailMsg.getEntDealGrsUnitPrcAmt();
            BigDecimal netPrice = detailMsg.getEntDealNetUnitPrcAmt();
            NWXC001001UnitPriceData unitPrcData = callNWXC001001EditPriceAmount(cpouBean, detailMsg, grsPrice, netPrice, resPMsgList, i);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // set Detail DealGrossPrice, DealNetPrice, FuncGrossPrice and FuncNetPrice
            calcCpoPriceAmount(cpouBean, unitPrcData, resPMsgList, i);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // 20130109 Defect#54 M.Fuji Start
            // When merchandise is Regular, Set FreightPrice
            // S21_NA#2846#1 start
            // if (!NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(detailMsg.getCpoDtlLineSubNum())) {
            // get FreightPrice (FuncPrice)
            BigDecimal frtAmount = NWZC150001Common.nullToZero(detailMsg.getXxTotFrtAmt()).add(NWZC150001Common.nullToZero(detailMsg.getXxTotSpclChrgAmt()));
            NWXC001001UnitPriceData frtPrcDataForRegular = callNWXC001001EditPriceAmount(cpouBean, detailMsg, frtAmount, frtAmount, resPMsgList, i);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // set Detail FreightPrice, FuncFreightPrice
            calcCpoFreightAmount(cpouBean, frtPrcDataForRegular, resPMsgList, i);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }
            // }
            // 20130131 Defect#490 M.Fuji Start
            // else {
            //    detailMsg.setLineTotDealFrtAmt(ZERO);
            //    detailMsg.setLineTotFuncFrtAmt(ZERO);
            //}
            // S21_NA#2846#1 end
            // 20130131 Defect#490 M.Fuji End
            // 20130109 Defect#54 M.Fuji End

            addTotAmt(cpouBean, detailMsg, pMsg, false, msgIdMgr);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            //set CpoDtl Close Status
            if (NWZC150001CpouConstant.CPO_MODIFY.equals(detailMsg.getDtlRqstTpCd())) {
                SHPG_PLNTMsgArray shpgPlanTMsgArray = getShpgPlanRelationCPOForUpdateNoWait(cpouBean, detailMsg, pMsg);

                // In case all SHPG_PLN of CPO_DTL is invoiced.
                if (detailMsg.getOrdQty().compareTo(getInvoicedQty(shpgPlanTMsgArray)) == 0) {
                    detailMsg.setOrdLineStsCd(ORD_LINE_STS.CLOSED);
                    // 2019/11/15 S21_NA#54199 Add Start
                    detailMsg.setOrdLineDplyStsCd(ORD_LINE_DPLY_STS.CLOSED);
                    // 2019/11/15 S21_NA#54199 Add End
                }
                //ADD Start ITG511838
                else if (detailMsg.getOrdQty().compareTo(getShippedQty(shpgPlanTMsgArray)) == 0) {
                    detailMsg.setOrdLineStsCd(ORD_LINE_STS.SHIPPED);
                    // 2019/11/15 S21_NA#54199 Add Start
                    detailMsg.setOrdLineDplyStsCd(ORD_LINE_DPLY_STS.SHIPPED);
                    // 2019/11/15 S21_NA#54199 Add End
                    // START 2021/04/19 A.Marte [QC#58549, ADD]
                    if (NWZC150001Constant.IR_SRC_TP_CD.equals(cpouBean.getCpoSrcTpCd())) {
                        //in case of partial cancel for IR type, if all orders have been shipped/cancelled, set order line to CLOSED
                        detailMsg.setOrdLineStsCd(ORD_LINE_STS.CLOSED);
                        detailMsg.setOrdLineDplyStsCd(ORD_LINE_DPLY_STS.CLOSED);
                        detailMsg.setOpenFlg(NWZC150001Constant.OPEN_FLG_CLOSED);
                    }
                    // END 2021/04/19 A.Marte [QC#58549, ADD]
                }
                //ADD Start ITG511838
            }

            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(detailMsg.getCpoDtlLineSubNum())) {
                // set SetComponentPrice
                Map<String, NWXC001001UnitPriceData> setItemPricingMap = new LinkedHashMap<String, NWXC001001UnitPriceData>();
                // 20130109 Defect#54 M.Fuji Start
                //                Map<String, NWXC001001UnitPriceData> setFrtPricingMap = new LinkedHashMap<String, NWXC001001UnitPriceData>();
                // 20130109 Defect#54 M.Fuji End

                for (NWXC001001UnitPriceData setItemPrc : unitPrcData.getChildUnitPrcDataList()) {
                    setItemPricingMap.put(setItemPrc.getMdseCd(), setItemPrc);
                }
                // 20130109 Defect#54 M.Fuji Start
                //                for (NWXC001001UnitPriceData setFrtPrc : frtPrcData.getChildUnitPrcDataList()) {
                //                    setFrtPricingMap.put(setFrtPrc.getMdseCd(), setFrtPrc);
                //                }
                // 20130109 Defect#54 M.Fuji End

                for (int j = i + 1, cmp = cpouBean.getDtlBeanList().size(); j < cmp; j++) {

                    NWZC150001CpouDetailBean cmpDetailMsg = cpouBean.getDtlBeanList().get(j);

                    if (!detailMsg.getCpoDtlLineNum().equals(cmpDetailMsg.getCpoDtlLineNum())) {
                        break;
                    }
                    // S21_NA#2846#2 start
                    // In case of Set Compornent, set OrderPrice from
                    // SetParentItem.
                    // NWXC001001UnitPriceData setItemPrc =
                    // setItemPricingMap.get(cmpDetailMsg.getMdseCd());
                    // if (setItemPrc == null) {
                    // setItemPrc =
                    // setItemPricingMap.get(cmpDetailMsg.getMdseCd().substring(0,
                    // 8));
                    // }

                    // set SetComponent OrderQty
                    // BigDecimal childOrdQty =
                    // isOverFlowForDetail(setItemPrc.getChildMdseQty().multiply(detailMsg.getOrdQty()),
                    // resPMsgList, i);

                    // cmpDetailMsg.setOrdQty(childOrdQty);

                    // set Detail DealGrossPrice, DealNetPrice,
                    // FuncGrossPrice and FuncNetPrice
                    // calcCpoPriceAmount(cpouBean, setItemPrc,
                    // resPMsgList, j);
                    calcCpoPriceAmountForComponent(cmpDetailMsg);
                    // S21_NA#2846#2 end

                    if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                        return;
                    }

                    // 20130109 Defect#54 M.Fuji Start
                    //                    NWXC001001UnitPriceData setFrtPrc = setFrtPricingMap.get(cmpDetailMsg.getMdseCd());
                    //                    if (setFrtPrc == null) {
                    //                        setFrtPrc = setFrtPricingMap.get(cmpDetailMsg.getMdseCd().substring(0, 8));
                    //                    }
                    // In case of Set Compornent, set FreightPrice from SetComprnentItem. 
                    // get FreightPrice (FuncPrice)
                    BigDecimal frtAmountForSetItem = NWZC150001Common.nullToZero(cmpDetailMsg.getXxTotFrtAmt()).add(NWZC150001Common.nullToZero(cmpDetailMsg.getXxTotSpclChrgAmt()));
                    NWXC001001UnitPriceData frtPrcDataForSetItem = callNWXC001001EditPriceAmount(cpouBean, cmpDetailMsg, frtAmountForSetItem, frtAmountForSetItem, resPMsgList, i);
                    if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                        return;
                    }
                    // set Detail FreightPrice, FuncFreightPrice
                    calcCpoFreightAmount(cpouBean, frtPrcDataForSetItem, resPMsgList, j);
                    // 20130109 Defect#54 M.Fuji End
                    if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                        return;
                    }
                    //set CpoDtl Close Status
                    if (NWZC150001CpouConstant.CPO_MODIFY.equals(cmpDetailMsg.getDtlRqstTpCd())) {
                        SHPG_PLNTMsgArray cmpShpgPlanTMsgArray = getShpgPlanRelationCPOForUpdateNoWait(cpouBean, cmpDetailMsg, pMsg);

                        // In case all SHPG_PLN of CPO_DTL is invoiced.
                        if (cmpDetailMsg.getOrdQty().compareTo(getInvoicedQty(cmpShpgPlanTMsgArray)) == 0) {
                            cmpDetailMsg.setOrdLineStsCd(ORD_LINE_STS.CLOSED);
                            // 2019/11/15 S21_NA#54199 Add Start
                            cmpDetailMsg.setOrdLineDplyStsCd(ORD_LINE_DPLY_STS.CLOSED);
                            // 2019/11/15 S21_NA#54199 Add End
                        }
                        //ADD Start ITG511838
                        else if (cmpDetailMsg.getOrdQty().compareTo(getShippedQty(cmpShpgPlanTMsgArray)) == 0) {
                            cmpDetailMsg.setOrdLineStsCd(ORD_LINE_STS.SHIPPED);
                            // 2019/11/15 S21_NA#54199 Add Start
                            cmpDetailMsg.setOrdLineDplyStsCd(ORD_LINE_DPLY_STS.SHIPPED);
                            // 2019/11/15 S21_NA#54199 Add End
                            // START 2021/04/19 A.Marte [QC#58549, ADD]
                            if (cpouBean.getCpoSrcTpCd().equals(NWZC150001Constant.IR_SRC_TP_CD)) {
                                //in case of partial cancel for IR type, if all orders have been shipped/cancelled, set order line to CLOSED
                                cmpDetailMsg.setOrdLineStsCd(ORD_LINE_STS.SHIPPED);
                                cmpDetailMsg.setOrdLineDplyStsCd(ORD_LINE_STS.CLOSED);
                                cmpDetailMsg.setOpenFlg(NWZC150001Constant.OPEN_FLG_CLOSED);
                            }
                            // END 2021/04/19 A.Marte [QC#58549, ADD]
                        }
                        //ADD Start ITG511838
                    }
                    i = j;
                    // Def#2100 Set item line number
                    // S21_NA#2502setOrderedCustomerUOMQuantity(cpouBean,
                    // cmpDetailMsg, resPMsgList, idx);
                }
            }
            setOrderedCustomerUOMQuantity(cpouBean, detailMsg, resPMsgList, idx, localCache);
        }
    }

    /**
     * Difference judgment of vehicle currency
     * 
     * <pre>
     * It judges whether the vehicle currency is the same between effective details.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @return true : There is a difference. /false : There is no
     * difference.
     */
    private static boolean isCurrencyJudgment(NWZC150001CpouBean cpouBean) {

        boolean keyIndexSetCompFlag = false;
        int index = 0;

        for (int i = 0, end = cpouBean.getDtlBeanList().size(); i < end; i++) {

            NWZC150001CpouDetailBean detailMsg = cpouBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd()) && NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(detailMsg.getDtlRqstTpCd())) {
                continue;
            } else {

                if (!keyIndexSetCompFlag) {
                    index = i;
                    keyIndexSetCompFlag = true;
                }
            }

            String ccyCd1 = cpouBean.getDtlBeanList().get(index).getCcyCd();
            String ccyCd2 = detailMsg.getCcyCd();

            if (!ccyCd1.equals(ccyCd2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Amount of money of Header setting
     * 
     * <pre>
     * The amount of money of Header item is calculated.
     * When the dealings currency flag is True, Null is set to the function currency item.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param dealFlag boolean
     */
    private static void setTotalAmount(NWZC150001CpouBean cpouBean, boolean dealFlag) {

        if (dealFlag) {
            cpouBean.setEntCpoTotFuncSlsAmt(null);
            cpouBean.setEntCpoTotFuncDiscAmt(null);
            cpouBean.setEntCpoTotFuncNetAmt(null);
            cpouBean.setCpoTotFuncSlsAmt(null);
            cpouBean.setCpoTotFuncDiscAmt(null);
            cpouBean.setCpoTotFuncNetAmt(null);
        }

        BigDecimal entCpoTotDealDiscAmt = NWZC150001Common.subtract(cpouBean.getEntCpoTotDealNetAmt(), cpouBean.getEntCpoTotDealSlsAmt());
        cpouBean.setEntCpoTotDealDiscAmt(entCpoTotDealDiscAmt);

        BigDecimal entCpoTotFuncDiscAmt = NWZC150001Common.subtract(cpouBean.getEntCpoTotFuncNetAmt(), cpouBean.getEntCpoTotFuncSlsAmt());
        cpouBean.setEntCpoTotFuncDiscAmt(entCpoTotFuncDiscAmt);

        BigDecimal cpoTotDealDiscAmt = NWZC150001Common.subtract(cpouBean.getCpoTotDealNetAmt(), cpouBean.getCpoTotDealSlsAmt());
        cpouBean.setCpoTotDealDiscAmt(cpoTotDealDiscAmt);

        BigDecimal cpoTotFuncDiscAmt = NWZC150001Common.subtract(cpouBean.getCpoTotFuncNetAmt(), cpouBean.getCpoTotFuncSlsAmt());
        cpouBean.setCpoTotFuncDiscAmt(cpoTotFuncDiscAmt);
    }

    /**
     * Shipping Plan retrieval (exclusion)
     * 
     * <pre>
     * Shipping Plan is retrieved, and the acquisition result is returned.
     * ＜Search condition＞
     * ・Global Company Code
     * ・Order Line Number
     * ・Order Line Sub Number
     * </pre>
     * @param pMsg NWZC150001PMsg
     * @param apMsg NWZC150001_APMsg
     * @return Retrieval result（SHPG_PLNTMsgArray）
     */
    private static SHPG_PLNTMsgArray getShpgPlanRelationCPOForUpdateNoWait(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpoDtlBean, NWZC150001PMsg pMsg) {
        return NWZC150001CpouOtherCheck.getShpgPlanRelationCPO(cpouBean, cpoDtlBean, pMsg, true);
    }


    /**
     * Amount of money of Header area addition
     * 
     * <pre>
     * The amount of money of details is added to the amount of money of Header.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param pMsg NWZC150001PMsg
     * @param modifyFlg
     */
    private static void addTotAmt(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpoDtlBean, NWZC150001PMsg pMsg, boolean modifyFlg, S21ApiMessageIdMgr msgIdMgr) {

        if (!modifyFlg) {

            BigDecimal entCpoTotDealSlsAmt = NWZC150001Common.add(cpouBean.getEntCpoTotDealSlsAmt(), cpoDtlBean.getEntCpoDtlDealSlsAmt());
            cpouBean.setEntCpoTotDealSlsAmt(checkHeadAmount(entCpoTotDealSlsAmt, pMsg, msgIdMgr));

            BigDecimal entCpoTotDealNetAmt = NWZC150001Common.add(cpouBean.getEntCpoTotDealNetAmt(), cpoDtlBean.getEntCpoDtlDealNetAmt());
            cpouBean.setEntCpoTotDealNetAmt(checkHeadAmount(entCpoTotDealNetAmt, pMsg, msgIdMgr));

            BigDecimal entCpoTotFuncSlsAmt = NWZC150001Common.add(cpouBean.getEntCpoTotFuncSlsAmt(), cpoDtlBean.getEntCpoDtlFuncSlsAmt());
            cpouBean.setEntCpoTotFuncSlsAmt(checkHeadAmount(entCpoTotFuncSlsAmt, pMsg, msgIdMgr));

            BigDecimal entCpoTotFuncNetAmt = NWZC150001Common.add(cpouBean.getEntCpoTotFuncNetAmt(), cpoDtlBean.getEntCpoDtlFuncNetAmt());
            cpouBean.setEntCpoTotFuncNetAmt(checkHeadAmount(entCpoTotFuncNetAmt, pMsg, msgIdMgr));

        } else {

            BigDecimal entCpoTotDealSlsAmt = NWZC150001Common.add(cpouBean.getCpoTotDealSlsAmt(), cpoDtlBean.getCpoDtlDealSlsAmt());
            cpouBean.setEntCpoTotDealSlsAmt(checkHeadAmount(entCpoTotDealSlsAmt, pMsg, msgIdMgr));

            BigDecimal entCpoTotDealNetAmt = NWZC150001Common.add(cpouBean.getCpoTotDealNetAmt(), cpoDtlBean.getCpoDtlDealNetAmt());
            cpouBean.setEntCpoTotDealNetAmt(checkHeadAmount(entCpoTotDealNetAmt, pMsg, msgIdMgr));

            BigDecimal entCpoTotFuncSlsAmt = NWZC150001Common.add(cpouBean.getCpoTotFuncSlsAmt(), cpoDtlBean.getCpoDtlFuncSlsAmt());
            cpouBean.setEntCpoTotFuncSlsAmt(checkHeadAmount(entCpoTotFuncSlsAmt, pMsg, msgIdMgr));

            BigDecimal entCpoTotFuncNetAmt = NWZC150001Common.add(cpouBean.getCpoTotFuncNetAmt(), cpoDtlBean.getCpoDtlFuncNetAmt());
            cpouBean.setEntCpoTotFuncNetAmt(checkHeadAmount(entCpoTotFuncNetAmt, pMsg, msgIdMgr));
        }

        BigDecimal cpoTotDealSlsAmt = NWZC150001Common.add(cpouBean.getCpoTotDealSlsAmt(), cpoDtlBean.getCpoDtlDealSlsAmt());
        cpouBean.setCpoTotDealSlsAmt(checkHeadAmount(cpoTotDealSlsAmt, pMsg, msgIdMgr));

        BigDecimal cpoTotDealNetAmt = NWZC150001Common.add(cpouBean.getCpoTotDealNetAmt(), cpoDtlBean.getCpoDtlDealNetAmt());
        cpouBean.setCpoTotDealNetAmt(checkHeadAmount(cpoTotDealNetAmt, pMsg, msgIdMgr));

        BigDecimal cpoTotFuncSlsAmt = NWZC150001Common.add(cpouBean.getCpoTotFuncSlsAmt(), cpoDtlBean.getCpoDtlFuncSlsAmt());
        cpouBean.setCpoTotFuncSlsAmt(checkHeadAmount(cpoTotFuncSlsAmt, pMsg, msgIdMgr));

        BigDecimal cpoTotFuncNetAmt = NWZC150001Common.add(cpouBean.getCpoTotFuncNetAmt(), cpoDtlBean.getCpoDtlFuncNetAmt());
        cpouBean.setCpoTotFuncNetAmt(checkHeadAmount(cpoTotFuncNetAmt, pMsg, msgIdMgr));
    }

    /**
     * Over digit number check (for Header)
     * 
     * <pre>
     * When the numerical value exceeds the digit number, the error message is set and returned.
     * When the digit number is not exceeded, the numerical value is set to the storage location.
     * </pre>
     * @param val BigDecimal
     * @param pMsg NWZC150001PMsg
     */
    private static BigDecimal checkHeadAmount(BigDecimal val, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {
        if (isMaxAmtOver(val)) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0160E, pMsg);
//            debug("overFlow : Error-NWZC150001CpouConstant.NWZM0160E");
        }
        return val;
    }

    private static void calcPrcDtlPriceAmount(NWZC150001CpouDiscountBean discPrcData, NWXC001001UnitPriceData perUnitFixAmtData, NWXC001001UnitPriceData prmoNetUnitPrcAmtData, List<NWZC150002PMsg> resPMsgList, int i) {

        BigDecimal dealPerUnitFixAmt = perUnitFixAmtData.getDealGrsUnitPrcAmt();
        discPrcData.setDealPerUnitFixAmt(checkAmount(dealPerUnitFixAmt, resPMsgList, i).negate());

        BigDecimal funcPerUnitFixAmt = perUnitFixAmtData.getFuncGrsUnitPrcAmt();
        discPrcData.setFuncPerUnitFixAmt(checkAmount(funcPerUnitFixAmt, resPMsgList, i).negate());

        BigDecimal dealPrmoNetUnitPrcAmt = prmoNetUnitPrcAmtData.getDealGrsUnitPrcAmt();
        discPrcData.setDealPrmoNetUnitPrcAmt(checkAmount(dealPrmoNetUnitPrcAmt, resPMsgList, i));

        BigDecimal funcPrmoNetUnitPrcAmt = prmoNetUnitPrcAmtData.getFuncGrsUnitPrcAmt();
        discPrcData.setFuncPrmoNetUnitPrcAmt(checkAmount(funcPrmoNetUnitPrcAmt, resPMsgList, i));
    }

    /**
     * Over digit number check (for details)
     * 
     * <pre>
     * When numerical value exceeds the digit number, the error message is set and returned.
     * When the digit number is not exceeded, numerical value is set to the storage location.
     * </pre>
     * @param val BigDecimal
     * @param resPMsgList List<NWZC150002PMsg>
     * @param i int
     * @param detailMsg NWZC150001CpouDetailBean
     * @return val
     */
    private static BigDecimal checkAmount(BigDecimal val, List<NWZC150002PMsg> resPMsgList, int i) {
        if (isMaxAmtOver(val)) {
            NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0148E, resPMsgList, i);
        }
        return val;
    }

    private static boolean isMaxAmtOver(BigDecimal bc) {
        return NWZC150001CpouConstant.MAX_AMT.compareTo(bc) == -1;
    }
    /**
     * Details amount of money calculation (automatic input)
     * 
     * <pre>
     * The amount of money of details is calculated based on the processing result of Pricing API.
     * The numerical result is set to the corresponding item.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param unitPrcData NWXC001001UnitPriceData
     * @param resPMsgList List<NWZC150002PMsg>
     * @param i int
     */
    private static void calcCpoPriceAmount(NWZC150001CpouBean cpouBean, NWXC001001UnitPriceData unitPrcData, List<NWZC150002PMsg> resPMsgList, int i) {

        NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);

        final Integer ccyScale = getDealCcyDigit(cpouBean.getGlblCmpyCd(), cpoDtlBean.getCcyCd());
        final Integer ccyScaleFunc = getFuncCcyDigit(cpouBean.getGlblCmpyCd());
        final BigDecimal ordQty = cpoDtlBean.getOrdQty();

        BigDecimal entDealNetUnitPrcAmt = unitPrcData.getDealNetUnitPrcAmt();
        cpoDtlBean.setEntDealNetUnitPrcAmt(checkAmount(entDealNetUnitPrcAmt, resPMsgList, i));

        BigDecimal entCpoDtlDealNetAmt = unitPrcData.getDealNetUnitPrcAmt().multiply(ordQty).setScale(ccyScale, RoundingMode.HALF_UP);
        cpoDtlBean.setEntCpoDtlDealNetAmt(checkAmount(entCpoDtlDealNetAmt, resPMsgList, i));

        BigDecimal entCpoDtlDealSlsAmt = unitPrcData.getDealGrsUnitPrcAmt().multiply(ordQty).setScale(ccyScale, RoundingMode.HALF_UP);
        cpoDtlBean.setEntCpoDtlDealSlsAmt(checkAmount(entCpoDtlDealSlsAmt, resPMsgList, i));

        BigDecimal entFuncNetUnitPrcAmt = unitPrcData.getFuncNetUnitPrcAmt();
        cpoDtlBean.setEntFuncNetUnitPrcAmt(checkAmount(entFuncNetUnitPrcAmt, resPMsgList, i));

        BigDecimal entCpoDtlFuncNetAmt = entFuncNetUnitPrcAmt.multiply(ordQty).setScale(ccyScaleFunc, RoundingMode.HALF_UP);
        cpoDtlBean.setEntCpoDtlFuncNetAmt(checkAmount(entCpoDtlFuncNetAmt, resPMsgList, i));

        BigDecimal entCpoDtlFuncSlsAmt = unitPrcData.getFuncGrsUnitPrcAmt().multiply(ordQty).setScale(ccyScaleFunc, RoundingMode.HALF_UP);
        cpoDtlBean.setEntCpoDtlFuncSlsAmt(checkAmount(entCpoDtlFuncSlsAmt, resPMsgList, i));

        BigDecimal cpoDtlDealNetAmt = unitPrcData.getDealNetUnitPrcAmt().multiply(ordQty).setScale(ccyScale, RoundingMode.HALF_UP);
        cpoDtlBean.setCpoDtlDealNetAmt(checkAmount(cpoDtlDealNetAmt, resPMsgList, i));

        BigDecimal cpoDtlDealSlsAmt = unitPrcData.getDealGrsUnitPrcAmt().multiply(ordQty).setScale(ccyScale, RoundingMode.HALF_UP);
        cpoDtlBean.setCpoDtlDealSlsAmt(checkAmount(cpoDtlDealSlsAmt, resPMsgList, i));

        BigDecimal cpoDtlFuncNetAmt = entFuncNetUnitPrcAmt.multiply(ordQty).setScale(ccyScaleFunc, RoundingMode.HALF_UP);
        cpoDtlBean.setCpoDtlFuncNetAmt(checkAmount(cpoDtlFuncNetAmt, resPMsgList, i));

        BigDecimal cpoDtlFuncSlsAmt = unitPrcData.getFuncGrsUnitPrcAmt().multiply(ordQty).setScale(ccyScaleFunc, RoundingMode.HALF_UP);
        cpoDtlBean.setCpoDtlFuncSlsAmt(checkAmount(cpoDtlFuncSlsAmt, resPMsgList, i));

        BigDecimal dealGrsUnitPrcAmt = unitPrcData.getDealGrsUnitPrcAmt();
        cpoDtlBean.setDealGrsUnitPrcAmt(checkAmount(dealGrsUnitPrcAmt, resPMsgList, i));

        BigDecimal funcGrsUnitPrcAmt = unitPrcData.getFuncGrsUnitPrcAmt();
        cpoDtlBean.setFuncGrsUnitPrcAmt(checkAmount(funcGrsUnitPrcAmt, resPMsgList, i));
    }

    /**
     * <pre>
     * Dealings currency digit number acquisition
     * </pre>
     * @param glblCmpyCd String
     * @param ccyCd String
     */
    private static int getDealCcyDigit(String glblCmpyCd, String ccyCd) {

        CCYTMsg ccyMsg = new CCYTMsg();
        ccyMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyMsg.ccyCd.setValue(ccyCd);
        ccyMsg = (CCYTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(ccyMsg);

        if (ccyMsg != null) {
            return ccyMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }


    /**
     * <pre>
     * Function currency digit number acquisition
     * </pre>
     * @param glblCmpyCd String
     * @param ccyCd String
     */
    private static int getFuncCcyDigit(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyMsg = new GLBL_CMPYTMsg();
        glblCmpyMsg.glblCmpyCd.setValue(glblCmpyCd);
        glblCmpyMsg = (GLBL_CMPYTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(glblCmpyMsg);
        if (glblCmpyMsg == null) {
            return 0;
        }

        CCYTMsg ccyMsg = new CCYTMsg();
        ccyMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyMsg.ccyCd.setValue(glblCmpyMsg.stdCcyCd.getValue());
        ccyMsg = (CCYTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(ccyMsg);
        if (ccyMsg != null) {
            return ccyMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }

    private static void calcCpoFreightAmount(NWZC150001CpouBean cpouBean, NWXC001001UnitPriceData frtPrcData, List<NWZC150002PMsg> resPMsgList, int i) {

        NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);

        BigDecimal lineTotDealFrtAmt = frtPrcData.getDealGrsUnitPrcAmt();
        cpoDtlBean.setLineTotDealFrtAmt(checkAmount(lineTotDealFrtAmt, resPMsgList, i));

        BigDecimal lineTotFuncFrtAmt = frtPrcData.getFuncGrsUnitPrcAmt();
        cpoDtlBean.setLineTotFuncFrtAmt(checkAmount(lineTotFuncFrtAmt, resPMsgList, i));
    }


    private static List<NWZC150001CpouDiscountBean> getDiscountPriceList(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpoDtlBean, List<NWZC150001_priceListPMsg> linePriceList, BigDecimal dealGrsUnitPrcAmt) {

        List<NWZC150001CpouDiscountBean> discPrcList = new ArrayList<NWZC150001CpouDiscountBean>();
        BigDecimal dealPrmoNetUnitPrcAmt = dealGrsUnitPrcAmt;

        // 20130107 Defect#42 M.Fuji Start
        //      boolean exsistSpPrcFlg = exsistSpecialPrice(linePriceList);
        boolean exsistSpPrcFlg = exsistSpecialPrice(cpoDtlBean);
        // 20130107 Defect#42 M.Fuji End

        int index = 0;
        for (int i = 0; i < linePriceList.size(); i++) {

            NWZC150001_priceListPMsg priceData = linePriceList.get(i);

            if (isDiscPrice(priceData, exsistSpPrcFlg)) {
                NWZC150001CpouDiscountBean discPrcData = new NWZC150001CpouDiscountBean();
                index = index + 1;
                discPrcData.setCpoLinePrcNum(BigDecimal.valueOf(index));

                // UnitPrice=UnitPrice(init=Gross)-DealPerUnitFixAmount
                BigDecimal dealPerUnitFixAmt = priceData.unitPrcAmt.getValue();
                discPrcData.setDealPerUnitFixAmt(dealPerUnitFixAmt);

                dealPrmoNetUnitPrcAmt = NWZC150001Common.subtract(dealPrmoNetUnitPrcAmt, dealPerUnitFixAmt);
                discPrcData.setDealPrmoNetUnitPrcAmt(dealPrmoNetUnitPrcAmt);

                // Promotion: return value is minus
                if (PRC_COND_UNIT.PCT.equals(priceData.prcCondUnitCd.getValue())) {
                    if (hasValue(priceData.manPrcAmtRate.getValue())) {
                        discPrcData.setDealSlsPctNum(priceData.manPrcAmtRate.getValue());
                    } else {
                        discPrcData.setDealSlsPctNum(priceData.autoPrcAmtRate.getValue());
                    }
                }
                // START ADD S.Yamamoto [OM003]
                discPrcData.setCoaAcctCd(NWZC150001Common.getCoaAcctCdForPrc(cpouBean, priceData));
                // END   ADD S.Yamamoto [OM003]

                discPrcList.add(discPrcData);
            }
        }
        return discPrcList;
    }

    // 20121211 M.Fuji WDS Solution#104,105 Pricing Start
    /**
     * The number of sets of possible Cancel of the set commodity is
     * requested(After Invoice).
     * 
     * <pre>
     * The number of sets to be able to cancel the set commodity is returned.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param cmptMdseQtyMap Map<String, BigDecimal>
     * @param shpgPlnMsgArray SHPG_PLNTMsgArray
     * @return Number of sets that can be canceled
     */
    private static BigDecimal getInvoicedQty(SHPG_PLNTMsgArray shpgPlnTMsgArray) {

        BigDecimal invoicedQty = ZERO;
        for (int i = 0, end = shpgPlnTMsgArray.getValidCount(); i < end; i++) {
            //MOD StartITG511838 
            //            if (SHPG_STS.INVOICED.equals(shpgPlnTMsgArray.no(i).shpgStsCd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(shpgPlnTMsgArray.no(i).revRecogFlg.getValue())) {
                //MOD End ITG511838 Start
                invoicedQty = NWZC150001Common.add(invoicedQty, shpgPlnTMsgArray.no(i).ordQty.getValue());
            }
        }
        return invoicedQty;
    }


    //ADD Start ITG511838 
    /**
     * The number of sets of possible Cancel of the set commodity is
     * requested(from Ship to install).
     * 
     * <pre>
     * The number of sets to be able to cancel the set commodity is returned.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param cmptMdseQtyMap Map<String, BigDecimal>
     * @param shpgPlnMsgArray SHPG_PLNTMsgArray
     * @return Number of sets that can be canceled
     */
    private static BigDecimal getShippedQty(SHPG_PLNTMsgArray shpgPlnTMsgArray) {

        BigDecimal shippedQty = ZERO;
        List<String> shpgSts = Arrays.asList(new String[] {SHPG_STS.SHIPPED, SHPG_STS.ARRIVED, SHPG_STS.INSTALLED, SHPG_STS.INVOICED });
        for (int i = 0, end = shpgPlnTMsgArray.getValidCount(); i < end; i++) {
            //MOD StartITG511838 
            //            if (SHPG_STS.INVOICED.equals(shpgPlnTMsgArray.no(i).shpgStsCd.getValue())) {
            if (shpgSts.contains(shpgPlnTMsgArray.no(i).shpgStsCd.getValue())) {
                //MOD End ITG511838 Start
                shippedQty = NWZC150001Common.add(shippedQty, shpgPlnTMsgArray.no(i).ordQty.getValue());
            }
        }
        return shippedQty;
    }

    //ADD End ITG511838 

    private static NWXC001001UnitPriceData callNWXC001001EditPriceAmount(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpoDtlBean, BigDecimal grsPrice, BigDecimal netPrice, List<NWZC150002PMsg> resPMsgList, int index) {

        NWXC001001EditPriceAmountInfo editPrcAmtInfo = new NWXC001001EditPriceAmountInfo();

        editPrcAmtInfo.setGlblCmpyCd(cpouBean.getGlblCmpyCd());
        editPrcAmtInfo.setMdseCd(cpoDtlBean.getMdseCd());
        editPrcAmtInfo.setSlsDt(cpouBean.getSlsDt());
        editPrcAmtInfo.setCcyCd(cpoDtlBean.getCcyCd());
        editPrcAmtInfo.setDealGrsPrcAmt(grsPrice);
        editPrcAmtInfo.setDealNetPrcAmt(netPrice);

        editPrcAmtInfo = NWXC001001EditPriceAmount.getCmpsnPriceList(editPrcAmtInfo);

        for (String errorID : editPrcAmtInfo.getXxMsgIdList()) {
            NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(errorID, resPMsgList, index);
        }

        return editPrcAmtInfo.getUnitPrcData();
    }

    /**
     * <pre>
     * Get Line Price list PMsg for directed Detail Line
     * </pre>
     * @param cpouBean NWZC150001CpoBean (bean for process)
     * @param cpoDtlLineNum CPO Detail Line Number
     * @param cpoDtlLineSubNum CPO Detail Line Sub Number
     * @return
     */
    public static List<NWZC150001_priceListPMsg> getLinePriceList(NWZC150001CpouBean cpouBean, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        List<NWZC150001_priceListPMsg> linePriceList = new ArrayList<NWZC150001_priceListPMsg>();

        for (int i = 0; i < cpouBean.getPriceList().getValidCount(); i++) {

            final NWZC150001_priceListPMsg priceData = cpouBean.getPriceList().no(i);

            if (cpoDtlLineNum.equals(priceData.cpoDtlLineNum.getValue()) && cpoDtlLineSubNum.equals(priceData.cpoDtlLineSubNum.getValue())) {
                linePriceList.add(priceData);
            }
        }

        return linePriceList;
    }

    // 20130107 Defect#42 M.Fuji Start
    //    private boolean exsistSpecialPrice(List<NWZC150001_priceListPMsg> linePriceList) {
    //
    //        for (NWZC150001_priceListPMsg priceData : linePriceList) {
    //
    //            if (PRC_DTL_GRP.SPECIAL_PRICE.equals(priceData.prcDtlGrpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(priceData.prcCondManDelFlg.getValue())) {
    //                return true;
    //            }
    //        }
    //
    //        return false;
    //    }
    // 20130107 Defect#42 M.Fuji End

    // 20130107 Defect#42 M.Fuji Start
    private static boolean exsistSpecialPrice(NWZC150001CpouDetailBean cpoDtlBean) {

        return hasValue(cpoDtlBean.getXxTotSpclPrcAmt());
    }

    // 20130107 Defect#42 M.Fuji End

    private static boolean isDiscPrice(NWZC150001_priceListPMsg priceData, boolean exsistSpPrcFlg) {

        if (PRC_DTL_GRP.NET_DISCOUT.equals(priceData.prcDtlGrpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(priceData.prcCondManDelFlg.getValue())) {
            return true;
        } else if (!exsistSpPrcFlg && PRC_DTL_GRP.DISCOUNT.equals(priceData.prcDtlGrpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(priceData.prcCondManDelFlg.getValue())) {
            return true;
        }

        return false;
    }

    private static void calcCpoPriceAmountForBOMHead(NWZC150001CpouDetailBean cpoDtlBean) {

        cpoDtlBean.setEntDealNetUnitPrcAmt(ZERO);
        cpoDtlBean.setEntCpoDtlDealNetAmt(ZERO);
        cpoDtlBean.setEntCpoDtlDealSlsAmt(ZERO);
        cpoDtlBean.setEntFuncNetUnitPrcAmt(ZERO);
        cpoDtlBean.setEntCpoDtlFuncNetAmt(ZERO);
        cpoDtlBean.setEntCpoDtlFuncSlsAmt(ZERO);
        cpoDtlBean.setCpoDtlDealNetAmt(ZERO);
        cpoDtlBean.setCpoDtlDealSlsAmt(ZERO);
        cpoDtlBean.setCpoDtlFuncNetAmt(ZERO);
        cpoDtlBean.setCpoDtlFuncSlsAmt(ZERO);
        cpoDtlBean.setDealGrsUnitPrcAmt(ZERO);
        cpoDtlBean.setFuncGrsUnitPrcAmt(ZERO);
        cpoDtlBean.setLineTotDealFrtAmt(ZERO);
        cpoDtlBean.setLineTotFuncFrtAmt(ZERO);
    }

    private static void calcCpoPriceAmountForComponent(NWZC150001CpouDetailBean cpoDtlBean) {// S21_NA#2502

        cpoDtlBean.setEntDealNetUnitPrcAmt(ZERO);
        cpoDtlBean.setEntCpoDtlDealNetAmt(ZERO);
        cpoDtlBean.setEntCpoDtlDealSlsAmt(ZERO);
        cpoDtlBean.setEntFuncNetUnitPrcAmt(ZERO);
        cpoDtlBean.setEntCpoDtlFuncNetAmt(ZERO);
        cpoDtlBean.setEntCpoDtlFuncSlsAmt(ZERO);
        cpoDtlBean.setCpoDtlDealNetAmt(ZERO);
        cpoDtlBean.setCpoDtlDealSlsAmt(ZERO);
        cpoDtlBean.setCpoDtlFuncNetAmt(ZERO);
        cpoDtlBean.setCpoDtlFuncSlsAmt(ZERO);
        cpoDtlBean.setDealGrsUnitPrcAmt(ZERO);
        cpoDtlBean.setFuncGrsUnitPrcAmt(ZERO);
        cpoDtlBean.setLineTotDealFrtAmt(ZERO);
        cpoDtlBean.setLineTotFuncFrtAmt(ZERO);
    }

    /**
     * Ordered Customer UOM Quantity Calculation processing
     * 
     * <pre>
     * Ordered Customer UOM Quantity is calculated.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param index int
     */
    private static void setOrderedCustomerUOMQuantity(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpoDtlBean, List<NWZC150002PMsg> resPMsgList, int index, NWZC150001CpouLocalCache localCache) {

        // Def#2100 Set or Regular line
        NWZC150001CpouDetailBean detailMsg = cpouBean.getDtlBeanList().get(index);

        if (!hasValue(cpoDtlBean.getCustUomCd())) {
            return;
        }
        if (PKG_UOM.EACH.equals(cpoDtlBean.getCustUomCd())) {
            cpoDtlBean.setOrdCustUomQty(cpoDtlBean.getOrdQty());
        } else {

            // 2018/02/21 S21_NA#23815 Mod Start
            // final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("001");
            final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("005");
            // 2018/02/21 S21_NA#23815 Mod Start
            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
            // Def#2100
            fc.addCondition("pkgUomCd01", detailMsg.getCustUomCd());
            fc.addCondition("mdseCd01", detailMsg.getMdseCdForMstrSrch());
            // fc.addCondition("pkgUomCd01",
            // cpoDtlBean.getCustUomCd());
            // fc.addCondition("mdseCd01",
            // cpoDtlBean.getMdseCdForMstrSrch());
            fc.addCondition("qtyPkgApvlStsCd01", APVL_STS.SUBMITTED);
            fc.addCondition("basePkgUomCd01", detailMsg.getCustUomCd()); // 2018/02/21 S21_NA#23815 Add

            MDSE_STORE_PKGTMsgArray resMdseStorePkgTMsgArray = localCache.mdseStorePkgCache.getTMsgArray(fc);

            // 2018/02/21 S21_NA#23815 Mod Start
            // if (resMdseStorePkgTMsgArray.getValidCount() == 0) {
            if (resMdseStorePkgTMsgArray.getValidCount() != 1) {
            // 2018/02/21 S21_NA#23815 Mod End
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0751E, resPMsgList, index);
//                debug("setOrderedCustomerUOMQuantity index=" + index + " : Error-NWZC150001CpouConstant.NWZM0751E");
            } else {
                BigDecimal inEachQty = resMdseStorePkgTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).inEachQty.getValue();
                BigDecimal custUomQty = cpoDtlBean.getOrdQty().divide(inEachQty, 0, RoundingMode.DOWN);
                cpoDtlBean.setOrdCustUomQty(isOverFlowForDetail(custUomQty, resPMsgList, index));
                cpoDtlBean.setCustUomCd(resMdseStorePkgTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).pkgUomCd.getValue()); // 2018/02/21 S21_NA#23815 Mod Add
            }
        }
    }

    /**
     * Over digit number check (for details)
     * 
     * <pre>
     * When numerical val exceeds the digit number, the error message is set and returned.
     * When the digit number is not exceeded, numerical val is set to the storage location.
     * </pre>
     * @param val BigDecimal）
     * @param resPMsgList List<NWZC150002PMsg>
     * @param i int
     */
    private static BigDecimal isOverFlowForDetail(BigDecimal val, List<NWZC150002PMsg> resPMsgList, int i) {
        if (isMaxQtyOver(val)) {
            NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0148E, resPMsgList, i);
//            debug("overFlow index=" + i + " : Error-NWZC150001CpouConstant.NWZM0148E");
            return ZERO;
        }
        return val;
    }

    private static boolean isMaxQtyOver(BigDecimal bc) {
        return NWZC150001CpouConstant.MAX_QTY.compareTo(bc) == -1;
    }

    /**
     * Shipping Plan Status judgment (It is possible to cancel).
     * 
     * <pre>
     *  True is returned for status to be able to cancel Shipping Status Code of the argument.
     * </pre>
     * @param shpgStsCd String
     * @return True : It is possible to cancel. ／False : It is not
     * possible to cancel.
     */
    // 2019/11/27 QC#52339 Mod Start
    //public boolean canCancelShpgSts(SHPG_PLNTMsg shpgPlnTMsg) {
    public boolean canCancelShpgSts(SHPG_PLNTMsg shpgPlnTMsg, boolean collectIntangbleFlag) {
    // 2019/11/27 QC#52339 Mod End 

        Set<String> canCancelShpgSts = new HashSet<String>();

        canCancelShpgSts.add(SHPG_STS.SAVED);
        canCancelShpgSts.add(SHPG_STS.VALIDATED);
        canCancelShpgSts.add(SHPG_STS.S_OR_O_CANCELLED);
        canCancelShpgSts.add(SHPG_STS.P_OR_O_CANCELLED);
        // 2019/11/27 QC#52339 Add Start
        if (collectIntangbleFlag && NWZC150001Common.isIntangibleItem(shpgPlnTMsg.glblCmpyCd.getValue(), shpgPlnTMsg.mdseCd.getValue())) {
            canCancelShpgSts.add(SHPG_STS.SHIPPED);
            canCancelShpgSts.add(SHPG_STS.ARRIVED);
            canCancelShpgSts.add(SHPG_STS.INSTALLED);
        }
        // 2019/11/27 QC#52339 Add End

        return canCancelShpgSts(shpgPlnTMsg, canCancelShpgSts);
    }

    // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor Plan
    // Order] - START
    private boolean canCancelShpgSts(SHPG_PLNTMsg shpgPlnTMsg, Set<String> canCancelShpgSts) {

        String shpgStsCd = null;

        if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {

            if (canCancelShpgSts.contains(shpgPlnTMsg.shpgStsCd.getValue())) {

                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
                ssmParam.put("setShpgPlnNum", shpgPlnTMsg.shpgPlnNum.getValue());

                shpgStsCd = (String) ssmClient.queryObject("getSetMaxShpgStsCd", ssmParam);
            }

        } else if (hasValue(shpgPlnTMsg.setShpgPlnNum)) {

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
            ssmParam.put("shpgPlnNum", shpgPlnTMsg.setShpgPlnNum.getValue());

            shpgStsCd = (String) ssmClient.queryObject("getSetShpgStsCd", ssmParam);

            if (canCancelShpgSts.contains(shpgStsCd)) {

                ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
                ssmParam.put("setShpgPlnNum", shpgPlnTMsg.setShpgPlnNum.getValue());

                String cmpShpgStsCd = (String) ssmClient.queryObject("getSetMaxShpgStsCd", ssmParam);

                if (!SHPG_STS.HARD_ALLOCATED.equals(cmpShpgStsCd)) {
                    shpgStsCd = cmpShpgStsCd;
                }
            }

        } else {
            shpgStsCd = shpgPlnTMsg.shpgStsCd.getValue();
        }

        if (shpgStsCd == null) {
            shpgStsCd = shpgPlnTMsg.shpgStsCd.getValue();
        }

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        if (SHPG_STS.INSHED.equals(shpgStsCd)) {
//            if (hasValue(shpgPlnTMsg.soNum.getValue())) {
//                return isDoCanceled(shpgPlnTMsg);
//            }
//        }
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        if (SHPG_STS.CANCELLED.equals(shpgStsCd)) {
            return true;
        }

        return canCancelShpgSts.contains(shpgStsCd) || SHPG_STS.INSHED.equals(shpgStsCd);
    }

    // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor Plan
    // Order] - E N D


    // TODO currently, not used
//    private NWZC150001CpouDiscountBean getCmpDiscountPrice(NWZC150001CpouDiscountBean parentPriceData) {
//
//        NWZC150001CpouDiscountBean childDiscPrcData = new NWZC150001CpouDiscountBean();
//
//        childDiscPrcData.setCpoLinePrcNum(parentPriceData.getCpoLinePrcNum());
//        childDiscPrcData.setDealSlsPctNum(parentPriceData.getDealSlsPctNum());
//        childDiscPrcData.setCoaAcctCd(parentPriceData.getCoaAcctCd());
//
//        return childDiscPrcData;
//    }

    // QC#9694 2016/08/01 Mod Start
    private void editAmountForPrcDtl(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean,  List<NWZC150002PMsg> pMsg2List) {

        String cpoOrdNum = cpouBean.getCpoOrdNum();
        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
            cpoOrdNum = cpouBean.getCpoOrdNum_A1();
        }

        List<NWZC150001_priceListPMsg> prcList = new ArrayList<NWZC150001_priceListPMsg>();
        BigDecimal entCpoTotDealSlsAmt = BigDecimal.ZERO;
        BigDecimal entCpoTotDealNetAmt = BigDecimal.ZERO;
        BigDecimal entCpoTotFuncSlsAmt = BigDecimal.ZERO;
        BigDecimal entCpoTotFuncNetAmt = BigDecimal.ZERO;
        BigDecimal cpoTotDealSlsAmt = BigDecimal.ZERO;
        BigDecimal cpoTotDealNetAmt = BigDecimal.ZERO;
        BigDecimal cpoTotFuncSlsAmt = BigDecimal.ZERO;
        BigDecimal cpoTotFuncNetAmt = BigDecimal.ZERO;
        BigDecimal grsAmt = BigDecimal.ZERO;
        BigDecimal netAmt = BigDecimal.ZERO;

        for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {

            grsAmt = BigDecimal.ZERO;
            netAmt = BigDecimal.ZERO;
            prcList = getLinePriceList(cpouBean.getPriceList(), cpouDtlBean.getCpoDtlLineNum(), cpouDtlBean.getCpoDtlLineSubNum());
            for(NWZC150001_priceListPMsg calcBase : prcList){
                if(ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())){
                    continue;
                }
                if(PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())){
                    grsAmt = grsAmt.add(calcBase.calcPrcAmtRate.getValue());
                    netAmt = netAmt.add(calcBase.calcPrcAmtRate.getValue());
                }
                if(PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())){
                    netAmt = netAmt.subtract(calcBase.calcPrcAmtRate.getValue());
                }
                if(PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())){
                    netAmt = netAmt.subtract(calcBase.calcPrcAmtRate.getValue());
                }
            }

//            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum); // 2016/08/10 cpoOrdNum -> cpoOrdNum_A1
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
//
//            cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);
//            if (cpoDtlTMsg == null) {
//                setMsgId2(pMsg2, NWZM0074E);
//                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, cpouDtlBean.getDsOrdPosnNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
//                pMsg2List.add(pMsg2);
//                return;
//            }

            NWXC001001UnitPriceData unitPrcData = callNWXC001001EditPriceAmount(cpouBean, cpouDtlBean, grsAmt, netAmt, pMsg2List);
            cpouDtlBean.setEntCpoDtlDealNetAmt(unitPrcData.getDealNetUnitPrcAmt());
            cpouDtlBean.setEntCpoDtlDealSlsAmt(unitPrcData.getDealGrsUnitPrcAmt());
            cpouDtlBean.setEntCpoDtlFuncNetAmt(unitPrcData.getFuncNetUnitPrcAmt());
            cpouDtlBean.setEntCpoDtlFuncSlsAmt(unitPrcData.getFuncGrsUnitPrcAmt());

            cpouDtlBean.setCpoDtlDealNetAmt(unitPrcData.getDealNetUnitPrcAmt());
            cpouDtlBean.setCpoDtlDealSlsAmt(unitPrcData.getDealGrsUnitPrcAmt());
            cpouDtlBean.setCpoDtlFuncNetAmt(unitPrcData.getFuncNetUnitPrcAmt());
            cpouDtlBean.setCpoDtlFuncSlsAmt(unitPrcData.getFuncGrsUnitPrcAmt());

//            S21ApiTBLAccessor.update(cpoDtlTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
//                setMsgId2(pMsg2, NWZM0081E);
//                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, cpouDtlBean.getDsOrdPosnNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
//                return;
//            }

            entCpoTotDealSlsAmt = entCpoTotDealSlsAmt.add(cpouDtlBean.getEntCpoDtlDealSlsAmt());
            entCpoTotDealNetAmt = entCpoTotDealNetAmt.add(cpouDtlBean.getEntCpoDtlDealNetAmt());
            entCpoTotFuncSlsAmt = entCpoTotFuncSlsAmt.add(cpouDtlBean.getEntCpoDtlFuncSlsAmt());
            entCpoTotFuncNetAmt = entCpoTotFuncNetAmt.add(cpouDtlBean.getEntCpoDtlFuncNetAmt());
            cpoTotDealSlsAmt = cpoTotDealSlsAmt.add(cpouDtlBean.getCpoDtlDealSlsAmt());
            cpoTotDealNetAmt = cpoTotDealNetAmt.add(cpouDtlBean.getCpoDtlDealNetAmt());
            cpoTotFuncSlsAmt = cpoTotFuncSlsAmt.add(cpouDtlBean.getCpoDtlFuncNetAmt());
            cpoTotFuncNetAmt = cpoTotFuncNetAmt.add(cpouDtlBean.getCpoDtlFuncSlsAmt());
        }
//        CPOTMsg cpoTMsg = new CPOTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
//        cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsg);
//        if (!existsCpo(cpoTMsg)) {
//            setMsgId(pMsg, NWZM0073E);
//        }
        cpouBean.setEntCpoTotDealSlsAmt(entCpoTotDealSlsAmt);
        cpouBean.setEntCpoTotDealNetAmt(entCpoTotDealNetAmt);
        cpouBean.setEntCpoTotFuncSlsAmt(entCpoTotFuncSlsAmt);
        cpouBean.setEntCpoTotFuncNetAmt(entCpoTotFuncNetAmt);
        cpouBean.setCpoTotDealSlsAmt(cpoTotDealSlsAmt);
        cpouBean.setCpoTotDealNetAmt(cpoTotDealNetAmt);
        cpouBean.setCpoTotFuncSlsAmt(cpoTotFuncSlsAmt);
        cpouBean.setCpoTotFuncNetAmt(cpoTotFuncNetAmt);

        cpouBean.setEntCpoTotDealDiscAmt(entCpoTotDealNetAmt.subtract(entCpoTotDealSlsAmt));
        cpouBean.setEntCpoTotFuncDiscAmt(entCpoTotFuncNetAmt.subtract(entCpoTotFuncSlsAmt));
        cpouBean.setCpoTotDealDiscAmt(cpoTotDealNetAmt.subtract(cpoTotDealSlsAmt));
        cpouBean.setCpoTotFuncDiscAmt(cpoTotFuncNetAmt.subtract(cpoTotFuncSlsAmt));
//        S21ApiTBLAccessor.update(cpoTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
//            setMsgId(pMsg, NWZM1368E);
//            return;
//        }
    }

    private List<NWZC150001_priceListPMsg> getLinePriceList( NWZC150001_priceListPMsgArray calcBaseList, String cpoDtlLineNum, String cpoDtlLineSubNum){
        List<NWZC150001_priceListPMsg> rtrnList = new ArrayList<NWZC150001_priceListPMsg>();
        NWZC150001_priceListPMsg calcBase = null;
        for (int i = 0; i < calcBaseList.getValidCount(); i++) {
            calcBase =  calcBaseList.no(i);
            if(S21StringUtil.isEquals(calcBase.cpoDtlLineNum.getValue(), cpoDtlLineNum) 
                    && S21StringUtil.isEquals(calcBase.cpoDtlLineSubNum.getValue(), cpoDtlLineSubNum)){
                rtrnList.add(calcBase);
            }
        }
        return rtrnList;
    }

    private NWXC001001UnitPriceData callNWXC001001EditPriceAmount(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpouDtlBean, BigDecimal grsPrice, BigDecimal netPrice, List<NWZC150002PMsg> pMsg2List) {

        NWXC001001EditPriceAmountInfo editPrcAmtInfo = new NWXC001001EditPriceAmountInfo();

        editPrcAmtInfo.setGlblCmpyCd(cpouBean.getGlblCmpyCd());
        editPrcAmtInfo.setMdseCd(cpouDtlBean.getMdseCd());
        editPrcAmtInfo.setSlsDt(cpouBean.getSlsDt());
        editPrcAmtInfo.setCcyCd(cpouDtlBean.getCcyCd());
        editPrcAmtInfo.setDealGrsPrcAmt(grsPrice);
        editPrcAmtInfo.setDealNetPrcAmt(netPrice);

        editPrcAmtInfo = NWXC001001EditPriceAmount.getCmpsnPriceList(editPrcAmtInfo);

        for (String errorID : editPrcAmtInfo.getXxMsgIdList()) {
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            NWZC150001Common.setMsgId2(pMsg2, errorID);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, cpouDtlBean.getDsOrdPosnNum());
            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
            pMsg2List.add(pMsg2);
        }

        return editPrcAmtInfo.getUnitPrcData();
    }
    // QC#9694 2016/08/01 Mod End}

    /**
     * <pre>
     * Calculation Total Amount for CPO from CPO_DTL.
     * Before calling this method, Sales Amount and Net Amount of CPO_DTL table
     * should be calculated.
     * </pre>
     * @param pMsg NWZC150001 parameter message.
     * @param cpouBean edited data for update.
     */
    public void setAmountFromCpoDtl(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean) {

        CPO_DTLTMsg queryCpoDtlTMsg = new CPO_DTLTMsg();
        queryCpoDtlTMsg.setSQLID("001");
        queryCpoDtlTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        String cpoOrdNum = NWZC150001Common.getCpoOrdNumFromBean(cpouBean);
        queryCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(queryCpoDtlTMsg);
        if (cpoDtlTMsgArray == null || cpoDtlTMsgArray.getValidCount() == 0) {
            return;
        }

        BigDecimal entCpoTotDealSlsAmt = cpouBean.getEntCpoTotDealSlsAmt();
        BigDecimal entCpoTotDealNetAmt = cpouBean.getEntCpoTotDealNetAmt();
        BigDecimal entCpoTotFuncSlsAmt = cpouBean.getEntCpoTotFuncSlsAmt();
        BigDecimal entCpoTotFuncNetAmt = cpouBean.getEntCpoTotFuncNetAmt();
        BigDecimal cpoTotDealSlsAmt = cpouBean.getCpoTotDealSlsAmt();
        BigDecimal cpoTotDealNetAmt = cpouBean.getCpoTotDealNetAmt();
        BigDecimal cpoTotFuncSlsAmt = cpouBean.getCpoTotFuncSlsAmt();
        BigDecimal cpoTotFuncNetAmt = cpouBean.getCpoTotFuncNetAmt();

        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
            CPO_DTLTMsg cpoDtlTMsg = cpoDtlTMsgArray.no(i);

            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg.ordLineStsCd.getValue())) {
                continue;
            }

            entCpoTotDealSlsAmt = NWZC150001Common.add(entCpoTotDealSlsAmt, cpoDtlTMsg.entCpoDtlDealSlsAmt.getValue());
            entCpoTotDealNetAmt = NWZC150001Common.add(entCpoTotDealNetAmt, cpoDtlTMsg.entCpoDtlDealNetAmt.getValue());
            entCpoTotFuncSlsAmt = NWZC150001Common.add(entCpoTotFuncSlsAmt, cpoDtlTMsg.entCpoDtlFuncSlsAmt.getValue());
            entCpoTotFuncNetAmt = NWZC150001Common.add(entCpoTotFuncNetAmt, cpoDtlTMsg.entCpoDtlFuncNetAmt.getValue());
            cpoTotDealSlsAmt = NWZC150001Common.add(cpoTotDealSlsAmt, cpoDtlTMsg.cpoDtlDealSlsAmt.getValue());
            cpoTotDealNetAmt = NWZC150001Common.add(cpoTotDealNetAmt, cpoDtlTMsg.cpoDtlDealNetAmt.getValue());
            cpoTotFuncSlsAmt = NWZC150001Common.add(cpoTotFuncSlsAmt, cpoDtlTMsg.cpoDtlFuncSlsAmt.getValue());
            cpoTotFuncNetAmt = NWZC150001Common.add(cpoTotFuncNetAmt, cpoDtlTMsg.cpoDtlFuncNetAmt.getValue());
        }

        cpouBean.setEntCpoTotDealSlsAmt(entCpoTotDealSlsAmt);
        cpouBean.setEntCpoTotDealNetAmt(entCpoTotDealNetAmt);
        cpouBean.setEntCpoTotFuncSlsAmt(entCpoTotFuncSlsAmt);
        cpouBean.setEntCpoTotFuncNetAmt(entCpoTotFuncNetAmt);
        cpouBean.setCpoTotDealSlsAmt(cpoTotDealSlsAmt);
        cpouBean.setCpoTotDealNetAmt(cpoTotDealNetAmt);
        cpouBean.setCpoTotFuncSlsAmt(cpoTotFuncSlsAmt);
        cpouBean.setCpoTotFuncNetAmt(cpoTotFuncNetAmt);

        cpouBean.setEntCpoTotDealDiscAmt(entCpoTotDealNetAmt.subtract(entCpoTotDealSlsAmt));
        cpouBean.setEntCpoTotFuncDiscAmt(entCpoTotFuncNetAmt.subtract(entCpoTotFuncSlsAmt));
        cpouBean.setCpoTotDealDiscAmt(cpoTotDealNetAmt.subtract(cpoTotDealSlsAmt));
        cpouBean.setCpoTotFuncDiscAmt(cpoTotFuncNetAmt.subtract(cpoTotFuncSlsAmt));
    }

    /**
     * <pre>
     * Calculation Total Amount for CPO from DS_CPO_RTRN_DTL.
     * Before calling this method, Sales Amount and Net Amount of CPO_DTL table
     * should be calculated.
     * </pre>
     * @param pMsg NWZC150001 parameter message.
     * @param cpouBean edited data for update.
     */
    public void setAmountFromCpoRtrnDtl(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean) {

        DS_CPO_RTRN_DTLTMsg queryDsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        queryDsCpoRtrnDtlTMsg.setSQLID("001");
        queryDsCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        queryDsCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
        DS_CPO_RTRN_DTLTMsgArray cpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(queryDsCpoRtrnDtlTMsg);
        if (cpoRtrnDtlTMsgArray == null || cpoRtrnDtlTMsgArray.getValidCount() == 0) {
            return;
        }

        BigDecimal entCpoTotDealSlsAmt = cpouBean.getEntCpoTotDealSlsAmt();
        BigDecimal entCpoTotDealNetAmt = cpouBean.getEntCpoTotDealNetAmt();
        BigDecimal entCpoTotFuncSlsAmt = cpouBean.getEntCpoTotFuncSlsAmt();
        BigDecimal entCpoTotFuncNetAmt = cpouBean.getEntCpoTotFuncNetAmt();
        BigDecimal cpoTotDealSlsAmt = cpouBean.getCpoTotDealSlsAmt();
        BigDecimal cpoTotDealNetAmt = cpouBean.getCpoTotDealNetAmt();
        BigDecimal cpoTotFuncSlsAmt = cpouBean.getCpoTotFuncSlsAmt();
        BigDecimal cpoTotFuncNetAmt = cpouBean.getCpoTotFuncNetAmt();

        for (int i = 0; i < cpoRtrnDtlTMsgArray.getValidCount(); i++) {
            DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg = cpoRtrnDtlTMsgArray.no(i);

            if (RTRN_LINE_STS.CANCELLED.equals(cpoRtrnDtlTMsg.rtrnLineStsCd.getValue())) {
                continue;
            }

            entCpoTotDealSlsAmt = NWZC150001Common.add(entCpoTotDealSlsAmt, cpoRtrnDtlTMsg.entCpoDtlDealSlsAmt.getValue());
            entCpoTotDealNetAmt = NWZC150001Common.add(entCpoTotDealNetAmt, cpoRtrnDtlTMsg.entCpoDtlDealNetAmt.getValue());
            entCpoTotFuncSlsAmt = NWZC150001Common.add(entCpoTotFuncSlsAmt, cpoRtrnDtlTMsg.entCpoDtlFuncSlsAmt.getValue());
            entCpoTotFuncNetAmt = NWZC150001Common.add(entCpoTotFuncNetAmt, cpoRtrnDtlTMsg.entCpoDtlFuncNetAmt.getValue());
            cpoTotDealSlsAmt = NWZC150001Common.add(cpoTotDealSlsAmt, cpoRtrnDtlTMsg.cpoDtlDealSlsAmt.getValue());
            cpoTotDealNetAmt = NWZC150001Common.add(cpoTotDealNetAmt, cpoRtrnDtlTMsg.cpoDtlDealNetAmt.getValue());
            cpoTotFuncSlsAmt = NWZC150001Common.add(cpoTotFuncSlsAmt, cpoRtrnDtlTMsg.cpoDtlFuncSlsAmt.getValue());
            cpoTotFuncNetAmt = NWZC150001Common.add(cpoTotFuncNetAmt, cpoRtrnDtlTMsg.cpoDtlFuncNetAmt.getValue());
        }

        cpouBean.setEntCpoTotDealSlsAmt(entCpoTotDealSlsAmt);
        cpouBean.setEntCpoTotDealNetAmt(entCpoTotDealNetAmt);
        cpouBean.setEntCpoTotFuncSlsAmt(entCpoTotFuncSlsAmt);
        cpouBean.setEntCpoTotFuncNetAmt(entCpoTotFuncNetAmt);
        cpouBean.setCpoTotDealSlsAmt(cpoTotDealSlsAmt);
        cpouBean.setCpoTotDealNetAmt(cpoTotDealNetAmt);
        cpouBean.setCpoTotFuncSlsAmt(cpoTotFuncSlsAmt);
        cpouBean.setCpoTotFuncNetAmt(cpoTotFuncNetAmt);

        cpouBean.setEntCpoTotDealDiscAmt(entCpoTotDealNetAmt.subtract(entCpoTotDealSlsAmt));
        cpouBean.setEntCpoTotFuncDiscAmt(entCpoTotFuncNetAmt.subtract(entCpoTotFuncSlsAmt));
        cpouBean.setCpoTotDealDiscAmt(cpoTotDealNetAmt.subtract(cpoTotDealSlsAmt));
        cpouBean.setCpoTotFuncDiscAmt(cpoTotFuncNetAmt.subtract(cpoTotFuncSlsAmt));
    }
    // 2017/10/11 S21_NA#21300 Add Start
    /**
     * <pre>
     * Check The Shipping Plan Record is:
     *     1) Shipping Status = P/O Printed
     *     2) If status is P/O Printed, Order Line Source is ITT Drop Ship.
     *     3) If the record is ITT Drop Ship, Check P/O Detail Status.
     *     4) If P/O Detail Status is before receiving, this record is able to be cancelled.
     *     1) - 4) are AND conditions.
     * </pre>
     * @param shpgPlnTMsg Shipping Plan Record
     * @param cpoDtlTMsg CPO Detail Record (If this parameter is null, this method will select this record.)
     * @return true: Can Cancel, false: not.
     */
    public boolean isIttDropShipCancelable(SHPG_PLNTMsg shpgPlnTMsg, CPO_DTLTMsg cpoDtlTMsg) {

        String shpgStsCd = shpgPlnTMsg.shpgStsCd.getValue();
        if (SHPG_STS.P_OR_O_PRINTED.equals(shpgStsCd)) {
            if (cpoDtlTMsg == null) {
                cpoDtlTMsg = NWZC150001CpouExistsCdInDbCheck.getCpoDtlByPK(shpgPlnTMsg);
                if (cpoDtlTMsg == null) {
                    return false;
                }
            }
            String canCancLnSrc = ZYPCodeDataUtil.getVarCharConstValue(NWZC150001CpouConstant.NWAL1500_ITT_AVAL_CANC_LN_SRC, shpgPlnTMsg.glblCmpyCd.getValue());
            if (canCancLnSrc == null) {
                return false;
            }
            String[] canCancLnSrcArray = canCancLnSrc.split(",");
            String cpoDtlLnSrcCd = cpoDtlTMsg.ordLineSrcCd.getValue();
            boolean searched = false;
            for (String canCancLnSrcCd : canCancLnSrcArray) {
                if (S21StringUtil.isEquals(canCancLnSrcCd, cpoDtlLnSrcCd)) {
                    searched = true;
                    break;
                }
            }
            if (!searched) {
                return false;
            }
            BigDecimal cantCancelPoCnt = getCantCancelPoData(shpgPlnTMsg);
            return BigDecimal.ZERO.compareTo(cantCancelPoCnt) == 0;
        } else {
          return false;
        }
    }

    private BigDecimal getCantCancelPoData(SHPG_PLNTMsg shpgPlnTMsg) {

        String canCancPoStsSrc = ZYPCodeDataUtil.getVarCharConstValue(NWZC150001CpouConstant.NWAL1500_ITT_AVAL_CANC_PO_STS, shpgPlnTMsg.glblCmpyCd.getValue());
        if (canCancPoStsSrc == null) {
            return BigDecimal.ONE;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", shpgPlnTMsg.trxHdrNum.getValue());
        queryParam.put("cpoDtlLineNum", shpgPlnTMsg.trxLineNum.getValue());
        queryParam.put("cpoDtlLineSubNum", shpgPlnTMsg.trxLineSubNum.getValue());
        queryParam.put("poStsCdList", canCancPoStsSrc.split(","));

        BigDecimal rsltCnt = (BigDecimal) this.ssmClient.queryObject("getCantCancelPoData", queryParam);

        return rsltCnt;
    }
    // 2017/10/11 S21_NA#21300 Add End
}
