/**
 * <pre>
 * Update / Inser SHPG_PLN Table
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/07   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2017/06/14   Fujitsu         T.Aoi           Update          S21_NA#19111
 * 2017/06/16   Fujitsu         S.Takami        Update          S21_NA#19288
 * 2017/10/11   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2018/06/20   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2019/07/31   Fujitsu         R.Nakamura      Update          S21_NA#52267
 * 2019/08/15   Fujitsu         K.Kato          Update          S21_NA#52620
 * 2019/11/15   Fujitsu         S.Takami        Update          S21_NA#54199
 * 2019/11/22   Fujitsu         M.Ohno          Update          S21_NA#54720
 * 2019/11/26   Fujitsu         M.Ohno          Update          S21_NA#54846
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * 2023/12/22   CITS            K.Ogino         Update          QC#63376
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.update;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import business.db.MDSETMsg;
import business.db.PRC_DTLTMsg;
import business.db.PRC_DTLTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NPZC103001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDiscountBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive.NWZC150001CpouEditAmount;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouExistsCdInDbCheck;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_PLN_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

public class NWZC150001CpouUpdShpgPln {

    // 2017/06/13 S21_NA#18869-2 Del Start
//    private static final NWZC150001CpouUpdShpgPln MY_INSTANCE = new NWZC150001CpouUpdShpgPln();
    // 2017/06/13 S21_NA#18869-2 Del End

    /** SSM Client */
    private static S21SsmBatchClient ssmClient;

    /**
     * Local Hash
     */
    private final HashMap<String, Map<String, String>> dsOrdTpTrxDfn = new HashMap<String, Map<String, String>>();

    private final HashMap<String, String> locRoleTpMap = new HashMap<String, String>();

    public NWZC150001CpouUpdShpgPln() {

        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    private static final Set<String> canCancelShpgSts;

    static {
        canCancelShpgSts = new HashSet<String>();

        canCancelShpgSts.add(SHPG_STS.SAVED);
        canCancelShpgSts.add(SHPG_STS.VALIDATED);
        canCancelShpgSts.add(SHPG_STS.S_OR_O_CANCELLED);
        canCancelShpgSts.add(SHPG_STS.P_OR_O_CANCELLED);
    }

    // 2017/06/13 S21_NA#18869-2 Del Start
//    public static NWZC150001CpouUpdShpgPln getInstance() {
//
//        return MY_INSTANCE;
//    }
    // 2017/06/13 S21_NA#18869-2 Del End
    /**
     * Shipping Plan update
     * 
     * <pre>
     * Submit : It registers newly.
     * Modify : The shipment instruction ending data is left, and, besides, it deletes it. The data that should be newly made as an order is registered.
     * Cancel : The shipment instruction ending data is left, and, besides, it deletes it. The data that should be newly made as an order is registered.(PreModifyFlg=='Y')
     *          It deletes it for all the data that can be canceled.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param removeShpgPlnTMsgList List<SHPG_PLNTMsg> instance for List of remove Shipping Plan
     * @param createShpgPlnTMsgList List<SHPG_PLNTMsg> instance for List of new Shipping Plan
     * @param updateShpgPlnTMsgList List<SHPG_PLNTMsg> instance for List of update Shipping Plan
     * @param cancelShpgPlnTMsgList List<SHPG_PLNTMsg> instance for List of cancel Shipping Plan
     * @param pMsg NWZC150001PMsg
     */
    public boolean updateShpgPln(NWZC150001CpouBean cpouBean, //
            List<SHPG_PLNTMsg> removeShpgPlnTMsgList, //
            List<SHPG_PLNTMsg> createShpgPlnTMsgList, //
            List<SHPG_PLNTMsg> updateShpgPlnTMsgList, //
            List<SHPG_PLNTMsg> cancelShpgPlnTMsgList, //
            NWZC150001PMsg pMsg, //
            List<NWZC150002PMsg> resPMsg2List, //
            ONBATCH_TYPE onBatchType) {
        // 1.2WDS modify start -->
        final String methodNm = "updateShpgPln";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        final boolean isSaveRqst = NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd());
        if (isSaveRqst) {
            // Mod Start 2019/07/31 QC#52267
//            return;
            return true;
            // Mod End 2019/07/31 QC#52267
        }

        try {

            final String glblCmpyCd = cpouBean.getGlblCmpyCd();
            final String cpoOrdNum = cpouBean.getCpoOrdNum();
            Map<String, SHPG_PLNTMsg> setShpgPlnTMsgMap = new LinkedHashMap<String, SHPG_PLNTMsg>();

            // For Performance QC#10321 Add Start
//            List<SHPG_PLNTMsg> insShpgPlnTMsgAry = new ArrayList<SHPG_PLNTMsg>();
            List<SHPG_PLNTMsg> updShpgPlnTMsgAry = new ArrayList<SHPG_PLNTMsg>();
//            List<SHPG_PLNTMsg> delShpgPlnTMsgAry = new ArrayList<SHPG_PLNTMsg>();
            // For Performance QC#10321 Add End

            for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {

                final NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);
                String dtlRqstTpCd = cpoDtlBean.getDtlRqstTpCd();
                String cpoDtlLineNum = cpoDtlBean.getCpoDtlLineNum();
                String cpoDtlLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();
                String preModifyFlg = cpoDtlBean.getPreModifyFlg();

                // --------------------------------------------------
                // SUBMIT
                // --------------------------------------------------
                if (NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(dtlRqstTpCd)) {

                    final SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                    if (setShpgPlnTMsgMap.containsKey(cpoDtlLineNum)) {
                        setShpgPlnTMsg(cpouBean, cpoDtlBean, shpgPlnTMsg, setShpgPlnTMsgMap.get(cpoDtlLineNum), pMsg);
                    } else {
                        setShpgPlnTMsg(cpouBean, cpoDtlBean, shpgPlnTMsg, null, pMsg);
                    }

                    // ***** [insert]
                    // For Performance QC#10321 Mod Start
//                    // insert(shpgPlnTMsg);
//                    insShpgPlnTMsgAry.add(shpgPlnTMsg);
//                    // For Performance QC#10321 Mod End

                    // 20130122 Defect#321 M.Fuji Start
                    createShpgPlnTMsgList.add(shpgPlnTMsg);
                    // 20130122 Defect#321 M.Fuji End

                    if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtlLineSubNum)) {
                        setShpgPlnTMsgMap.put(cpoDtlLineNum, shpgPlnTMsg);
                    }

                    // --------------------------------------------------
                    // MODIFY
                    // --------------------------------------------------
                } else if (NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(dtlRqstTpCd) || ZYPConstant.FLG_ON_Y.equals(preModifyFlg)) {

                    BigDecimal orderQty = cpoDtlBean.getOrdQty();
                    BigDecimal spDealUnitPrcAmt = ZERO;
                    BigDecimal spFuncUnitPrcAmt = ZERO;
                    BigDecimal cpoDtlDealSlsAmt = cpoDtlBean.getCpoDtlDealSlsAmt();
                    BigDecimal cpoDtlDealNetAmt = cpoDtlBean.getCpoDtlDealNetAmt();
                    BigDecimal cpoDtlFuncSlsAmt = cpoDtlBean.getCpoDtlFuncSlsAmt();
                    BigDecimal cpoDtlFuncNetAmt = cpoDtlBean.getCpoDtlFuncNetAmt();
                    // 20121210 M.Fuji WDS Solution#104,105 Pricing Start
                    BigDecimal lineTotDealFrtAmt = cpoDtlBean.getLineTotDealFrtAmt();
                    BigDecimal lineTotFuncFrtAmt = cpoDtlBean.getLineTotFuncFrtAmt();
                    // 20121210 M.Fuji WDS Solution#104,105 Pricing End
                    String prcCatgCd = null;

                    SHPG_PLNTMsgArray resShpgPlnTMsgArray = getShpgPlnTMsgArray(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
                    // 2019/11/15 S21_NA#54199 Add Start
                    BigDecimal totalShpgPlnOrdQty = getTotalShpgPlnOrdQty(resShpgPlnTMsgArray);
                    BigDecimal cpoDtlOrdQty = cpoDtlBean.getOrdQty();
                    boolean isUpdateShpgPln = false;
                    // 2019/11/15 S21_NA#54199 Add End
                    for (int j = 0; j < resShpgPlnTMsgArray.getValidCount(); j++) {

                        SHPG_PLNTMsg resShpgPlnTMsg = resShpgPlnTMsgArray.no(j);

                        // can cancel 'SHPG_STS'.
                        // 2019/08/15 S21_NA#52620 Mod Start
                        //if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg)) {
                        // 2019/11/27 QC#52339 Mod Start 
                        //if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg)
                        if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg, false)
                        // 2019/11/27 QC#52339 Mod End 
                         && !exixtsPRApproved(resShpgPlnTMsg)) {
                        // 2019/08/15 S21_NA#52620 Mod End

                            if (SHPG_STS.INSHED.equals(resShpgPlnTMsg.shpgStsCd.getValue())) {

                                resShpgPlnTMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
                                resShpgPlnTMsg.shipPlnCancDt.setValue(cpouBean.getSlsDt());
                                resShpgPlnTMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
                                // 2017/06/16 S21_NA#19288 Add Start
                                resShpgPlnTMsg.shpgPlnDplyStsCd.setValue(SHPG_PLN_DPLY_STS.CANCELLED);
                                // 2017/06/16 S21_NA#19288 Add End
                                resShpgPlnTMsg.rteStsCd.setValue(RTE_STS.UN_ROUTED);
                                resShpgPlnTMsg.avalHardAllocQty.setValue(ZERO);
                                resShpgPlnTMsg.avalSoftAllocQty.setValue(ZERO);
                                resShpgPlnTMsg.avalSoQty.setValue(ZERO);
                                resShpgPlnTMsg.avalPoQty.setValue(ZERO);
                                resShpgPlnTMsg.softAllocQty.setValue(ZERO);
                                resShpgPlnTMsg.crChkQty.setValue(ZERO);

                                cancelShpgPlnTMsgList.add(resShpgPlnTMsg);
                                continue;
                            }

                            // ***** [logicalRemove]
                            SHPG_PLNTMsg logicalRemoveShpgPlnTMsg = new SHPG_PLNTMsg();
                            logicalRemoveShpgPlnTMsg.glblCmpyCd.setValue(glblCmpyCd);
                            logicalRemoveShpgPlnTMsg.shpgPlnNum.setValue(resShpgPlnTMsg.shpgPlnNum.getValue());

                            // For Performance QC#10321 Mod Start
                            // logicalRemove(logicalRemoveShpgPlnTMsg);
//                            delShpgPlnTMsgAry.add(logicalRemoveShpgPlnTMsg);
                            // For Performance QC#10321 Mod End

                            removeShpgPlnTMsgList.add(resShpgPlnTMsg);

                            // 20130121 Defect#356 M.Fuji Pricing Start
                            //                            if (resShpgPlnTMsg.spDealNetUnitPrcAmt.getValue().compareTo(cpoDtlBean.getEntDealNetUnitPrcAmt()) != 0 || !resShpgPlnTMsg.ccyCd.getValue().equals(cpoDtlBean.getCcyCd())) {
                            //                                spDealUnitPrcAmt = cpoDtlBean.getEntDealNetUnitPrcAmt();
                            //                                spFuncUnitPrcAmt = cpoDtlBean.getEntFuncNetUnitPrcAmt();
                            //                            } else {
                            //                                spDealUnitPrcAmt = resShpgPlnTMsg.spDealUnitPrcAmt.getValue();
                            //                                spFuncUnitPrcAmt = resShpgPlnTMsg.spFuncUnitPrcAmt.getValue();
                            //                            }
                            // 20130121 Defect#356 M.Fuji Pricing End

                            prcCatgCd = resShpgPlnTMsg.prcCatgCd.getValue();

                            // can't cancel 'SHPG_STS'.
                        } else {
                            // 2019/11/15 S21_NA#54199 Add Start
                            // 2019/11/27 QC#52339 Mod Start 
                            //if (canCancelShpgSts.contains(resShpgPlnTMsg.shpgStsCd.getValue())) {
                            if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg, true)) {
                            // 2019/11/27 QC#52339 Mod End
                                if (S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, cpoDtlBean.getOrdLineStsCd())) {
                                    removeShpgPlnTMsgList.add(resShpgPlnTMsg);
                                    continue;
                                } else if (cpoDtlOrdQty.compareTo(totalShpgPlnOrdQty) < 0) {
                                    BigDecimal newShpgPlnOrdQty = resShpgPlnTMsg.ordQty.getValue().subtract(totalShpgPlnOrdQty.subtract(cpoDtlOrdQty));
                                    if (BigDecimal.ZERO.compareTo(newShpgPlnOrdQty) == 0) {
                                        removeShpgPlnTMsgList.add(resShpgPlnTMsg);
                                        continue;
                                    } else {
                                        resShpgPlnTMsg.ordQty.setValue(newShpgPlnOrdQty);
                                        if (BigDecimal.ZERO.compareTo(resShpgPlnTMsg.crChkQty.getValue()) < 0 //
                                                && newShpgPlnOrdQty.compareTo(resShpgPlnTMsg.crChkQty.getValue()) < 0) {
                                            resShpgPlnTMsg.crChkQty.setValue(newShpgPlnOrdQty);
                                        }
                                        isUpdateShpgPln = true;
                                    }
                                }
                            }
                            // 2019/11/15 S21_NA#54199 Add End
                            // For Performance QC#10321 Mod Start
                            // updateShpgPln(cpouBean, cpoDtlBean, resShpgPlnTMsg, updateShpgPlnTMsgList);
                            updateShpgPln(cpouBean, cpoDtlBean, resShpgPlnTMsg, updateShpgPlnTMsgList, updShpgPlnTMsgAry);
                            // For Performance QC#10321 Mod End

                            orderQty = orderQty.subtract(resShpgPlnTMsg.ordQty.getValue());
                            cpoDtlDealSlsAmt = cpoDtlDealSlsAmt.subtract(resShpgPlnTMsg.spTotDealSlsAmt.getValue());
                            cpoDtlDealNetAmt = cpoDtlDealNetAmt.subtract(resShpgPlnTMsg.spTotDealNetAmt.getValue());
                            cpoDtlFuncSlsAmt = cpoDtlFuncSlsAmt.subtract(resShpgPlnTMsg.spTotFuncSlsAmt.getValue());
                            cpoDtlFuncNetAmt = cpoDtlFuncNetAmt.subtract(resShpgPlnTMsg.spTotFuncNetAmt.getValue());
                            // 20121210 M.Fuji WDS Solution#104,105 Pricing Start
                            lineTotDealFrtAmt = lineTotDealFrtAmt.subtract(resShpgPlnTMsg.spTotDealFrtAmt.getValue());
                            lineTotFuncFrtAmt = lineTotFuncFrtAmt.subtract(resShpgPlnTMsg.spTotFuncFrtAmt.getValue());
                            // 20121210 M.Fuji WDS Solution#104,105 Pricing End
                            prcCatgCd = resShpgPlnTMsg.prcCatgCd.getValue();

                            // 20130121 Defect#356 M.Fuji Pricing Start
                            //                            if (resShpgPlnTMsg.spDealNetUnitPrcAmt.getValue().compareTo(cpoDtlBean.getEntDealNetUnitPrcAmt()) != 0 || !resShpgPlnTMsg.ccyCd.getValue().equals(cpoDtlBean.getCcyCd())) {
                            //                                spDealUnitPrcAmt = cpoDtlBean.getEntDealNetUnitPrcAmt();
                            //                                spFuncUnitPrcAmt = cpoDtlBean.getEntFuncNetUnitPrcAmt();
                            //                            } else {
                            //                                spDealUnitPrcAmt = resShpgPlnTMsg.spDealUnitPrcAmt.getValue();
                            //                                spFuncUnitPrcAmt = resShpgPlnTMsg.spFuncUnitPrcAmt.getValue();
                            //                            }
                            // 20130121 Defect#356 M.Fuji Pricing Start
                        }
                    }

                    // 20130121 Defect#356 M.Fuji Pricing Start
                    spDealUnitPrcAmt = cpoDtlBean.getDealGrsUnitPrcAmt();
                    spFuncUnitPrcAmt = cpoDtlBean.getFuncGrsUnitPrcAmt();
                    // 20130121 Defect#356 M.Fuji Pricing End

                    if (orderQty.compareTo(ZERO) > 0 && !isUpdateShpgPln) { // 2019/11/15 S21_NA#54199 Add Condition  && !isUpdateShpgPln

                        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                        shpgPlnTMsg.ordQty.setValue(orderQty);
                        shpgPlnTMsg.spDealUnitPrcAmt.setValue(spDealUnitPrcAmt);
                        shpgPlnTMsg.spFuncUnitPrcAmt.setValue(spFuncUnitPrcAmt);
                        shpgPlnTMsg.spTotDealSlsAmt.setValue(cpoDtlDealSlsAmt);
                        shpgPlnTMsg.spTotDealNetAmt.setValue(cpoDtlDealNetAmt);
                        shpgPlnTMsg.spTotFuncSlsAmt.setValue(cpoDtlFuncSlsAmt);
                        shpgPlnTMsg.spTotFuncNetAmt.setValue(cpoDtlFuncNetAmt);
                        // 20121210 M.Fuji WDS Solution#104,105 Pricing Start
                        shpgPlnTMsg.spTotDealFrtAmt.setValue(lineTotDealFrtAmt);
                        shpgPlnTMsg.spTotFuncFrtAmt.setValue(lineTotFuncFrtAmt);
                        // 20121210 M.Fuji WDS Solution#104,105 Pricing End

                        // START DELETE S.Yamamoto [MEX-LC004]
                        //                        cpoDtlBean.setPrcCatgCd(prcCatgCd);
                        // END   DELETE S.Yamamoto [MEX-LC004]

                        if (setShpgPlnTMsgMap.containsKey(cpoDtlLineNum)) {
                            setShpgPlnTMsg(cpouBean, cpoDtlBean, shpgPlnTMsg, setShpgPlnTMsgMap.get(cpoDtlLineNum), pMsg);
                        } else {
                            setShpgPlnTMsg(cpouBean, cpoDtlBean, shpgPlnTMsg, null, pMsg);
                        }

                        // ***** [insert]
                        // For Performance QC#10321 Mod Start
                        // insert(shpgPlnTMsg);
//                        insShpgPlnTMsgAry.add(shpgPlnTMsg);
                        // For Performance QC#10321 Mod End

                        createShpgPlnTMsgList.add(shpgPlnTMsg);

                        // Add Start 2019/07/31 QC#52267
                        if (!cancelAllocForSvcMachMstr(pMsg, cpoDtlBean, resPMsg2List, onBatchType)) {
                            return false;
                        }
                        // Add End 2019/07/31 QC#52267

                        if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtlLineSubNum)) {
                            setShpgPlnTMsgMap.put(cpoDtlLineNum, shpgPlnTMsg);
                        }
                    }

                    // --------------------------------------------------
                    // CANCEL
                    // --------------------------------------------------
                } else {

                    SHPG_PLNTMsgArray resShpgPlnTMsgArray = getShpgPlnTMsgArray(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);

                    for (int j = 0; j < resShpgPlnTMsgArray.getValidCount(); j++) {

                        SHPG_PLNTMsg resShpgPlnTMsg = resShpgPlnTMsgArray.no(j);

                        // can cancel 'SHPG_STS'?
                        // 2019/11/27 QC#52339 Mod Start
                        //if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg) //
                        if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg, true) //
                        // 2019/11/27 QC#52339 Mod End 
                                || NWZC150001CpouEditAmount.getInstance().isIttDropShipCancelable(resShpgPlnTMsg, null)) { // // 2017/10/11 S21_NA#21300 Add isIttDropShipCancelable()

                            String bfShpgPlnStsCd = resShpgPlnTMsg.shpgStsCd.getValue();

                            resShpgPlnTMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
                            resShpgPlnTMsg.shipPlnCancDt.setValue(cpouBean.getSlsDt());
                            resShpgPlnTMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
                            // 2017/06/16 S21_NA#19288 Add Start
                            resShpgPlnTMsg.shpgPlnDplyStsCd.setValue(SHPG_PLN_DPLY_STS.CANCELLED);
                            // 2017/06/16 S21_NA#19288 Add End
                            resShpgPlnTMsg.rteStsCd.setValue(RTE_STS.UN_ROUTED);
                            resShpgPlnTMsg.avalHardAllocQty.setValue(ZERO);
                            resShpgPlnTMsg.avalSoftAllocQty.setValue(ZERO);
                            resShpgPlnTMsg.avalSoQty.setValue(ZERO);
                            resShpgPlnTMsg.avalPoQty.setValue(ZERO);
                            resShpgPlnTMsg.softAllocQty.setValue(ZERO);
                            resShpgPlnTMsg.crChkQty.setValue(ZERO);

                            if (SHPG_STS.INSHED.equals(bfShpgPlnStsCd)) {
                                cancelShpgPlnTMsgList.add(resShpgPlnTMsg);
                                continue;
                            }

                            // ***** [update]
                            // For Performance QC#10321 Mod Start
                            // update(resShpgPlnTMsg);
                            updShpgPlnTMsgAry.add(resShpgPlnTMsg);
                            // For Performance QC#10321 Mod End

                            // 2019/11/22 S21_NA#54720 Add Start
                            if (!removeOpenPr(resShpgPlnTMsg, resPMsg2List)) {
                                return false;
                            }
                            // 2019/11/22 S21_NA#54720 Add End
                        }
                    }
                }
            }

            // 2019/11/22 S21_NA#54720 Add Start
            for (SHPG_PLNTMsg removeShpgPlnTMsg : removeShpgPlnTMsgList) {
                if (!removeOpenPr(removeShpgPlnTMsg, resPMsg2List)) {
                    return false;
                }
            }
            // 2019/11/22 S21_NA#54720 Add End
            // 2019/11/22 S21_NA#54720 Add End
            // For Performance QC#10321 Add Start
//            if (!insShpgPlnTMsgAry.isEmpty()) {
//                int cnt = S21FastTBLAccessor.insert(insShpgPlnTMsgAry.toArray(new SHPG_PLNTMsg[0]));
//                if (cnt != insShpgPlnTMsgAry.size()) {
//                    throw new S21AbendException("updateShpgPln : SHPG_PLN Insert Error");
//                }
//            }
            if (!createShpgPlnTMsgList.isEmpty()) {
                int cnt = S21FastTBLAccessor.insert(createShpgPlnTMsgList.toArray(new SHPG_PLNTMsg[0]));
                if (cnt != createShpgPlnTMsgList.size()) {
                    throw new S21AbendException("updateShpgPln : SHPG_PLN Insert Error");
                }
            }

            if (!updShpgPlnTMsgAry.isEmpty()) {
                int cnt = S21FastTBLAccessor.update(updShpgPlnTMsgAry.toArray(new SHPG_PLNTMsg[0]));
                if (cnt != updShpgPlnTMsgAry.size()) {
                    throw new S21AbendException("updateShpgPln : SHPG_PLN Update Error");
                }

                // 2019/11/27 QC#52339 Add Start 
              if (!terminateForSvcMachMstr(pMsg, updShpgPlnTMsgAry, resPMsg2List, onBatchType)) {
                  return false;
              }
              // 2019/11/27 QC#52339 Add End 


            
            }

//            if (!delShpgPlnTMsgAry.isEmpty()) {
//                int cnt = S21FastTBLAccessor.removeLogical(delShpgPlnTMsgAry.toArray(new SHPG_PLNTMsg[0]));
//                if (cnt != delShpgPlnTMsgAry.size()) {
//                    throw new S21AbendException("updateShpgPln : SHPG_PLN Delete Error");
//                }
//            }

            if (!removeShpgPlnTMsgList.isEmpty()) {
                int cnt = S21FastTBLAccessor.removeLogical(removeShpgPlnTMsgList.toArray(new SHPG_PLNTMsg[0]));
                if (cnt != removeShpgPlnTMsgList.size()) {
                    throw new S21AbendException("updateShpgPln : SHPG_PLN Delete Error");
                }
            }
            // For Performance QC#10321 Add End

            // Add Start 2019/07/31 QC#52267
            return true;
            // Add End 2019/07/31 QC#52267

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
        // 1.2WDS modify end <--
    }

    /**
     * SHPG_PLN Update value setting
     * 
     * <pre>
     * The value registered in SHPG_PLN is set.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param shpgPlnTMsg SHPG_PLNTMsg
     * @param setShpgPlnTMsg SHPG_PLNTMsg
     * @param pMsg NWZC150001PMsg
     * @return SHPG_PLNTMsg
     */
    private void setShpgPlnTMsg(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, SHPG_PLNTMsg shpgPlnTMsg, SHPG_PLNTMsg setShpgPlnTMsg, NWZC150001PMsg pMsg) {
        // 1.2WDS modify start -->
        String cpoOrdNum = NWZC150001Common.getCpoOrdNumFromBean(cpoBean);
        String shpgPlnNum = ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.SHPG_PLN_SQ, 8);
        shpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);
        cpoDtlBean.setShpgPlnNum(shpgPlnTMsg.shpgPlnNum.getValue());

        shpgPlnTMsg.glblCmpyCd.setValue(cpoDtlBean.getGlblCmpyCd());
        String shpgPlnDplyLineNum;
        if (NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(cpoDtlBean.getDtlRqstTpCd())) {
            shpgPlnDplyLineNum = "001";
        } else {
            shpgPlnDplyLineNum = getMaxShpgPlnDplyLineNum(cpoOrdNum, cpoDtlBean);
        }
        shpgPlnTMsg.shpgPlnDplyLineNum.setValue(shpgPlnDplyLineNum);

        shpgPlnTMsg.trxSrcTpCd.setValue(TRX_SRC_TP.WHOLE_SALES);
        shpgPlnTMsg.trxHdrNum.setValue(cpoOrdNum);
        shpgPlnTMsg.trxLineNum.setValue(NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
        shpgPlnTMsg.trxLineSubNum.setValue(NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));
        shpgPlnTMsg.billToCustCd.setValue(cpoBean.getBillToCustCd());
        shpgPlnTMsg.sellToCustCd.setValue(cpoBean.getSellToCustCd());
        shpgPlnTMsg.shipToCustCd.setValue(cpoDtlBean.getShipToCustCd());
        shpgPlnTMsg.shpgStsCd.setValue(SHPG_STS.VALIDATED);
        shpgPlnTMsg.shpgPlnDplyStsCd.setValue(SHPG_PLN_DPLY_STS.ENTERED);
        shpgPlnTMsg.rteStsCd.setValue(RTE_STS.UN_ROUTED);
        setValue(shpgPlnTMsg.reqShpgSvcLvlCd, cpoDtlBean.getShpgSvcLvlCd());
        setValue(shpgPlnTMsg.reqFrtChrgToCd, cpoDtlBean.getFrtChrgToCd());
        setValue(shpgPlnTMsg.reqFrtChrgMethCd, cpoDtlBean.getFrtChrgMethCd());
        shpgPlnTMsg.spDealNetUnitPrcAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt());

        if (NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(cpoDtlBean.getDtlRqstTpCd())) {
            shpgPlnTMsg.spDealUnitPrcAmt.setValue(cpoDtlBean.getDealGrsUnitPrcAmt());
            shpgPlnTMsg.spTotDealSlsAmt.setValue(cpoDtlBean.getEntCpoDtlDealSlsAmt());
            shpgPlnTMsg.spTotDealNetAmt.setValue(cpoDtlBean.getEntCpoDtlDealNetAmt());
            // 20121203 M.Fuji WDS Solution#104,105 Pricing Strat
            shpgPlnTMsg.spTotDealFrtAmt.setValue(cpoDtlBean.getLineTotDealFrtAmt());
            // 20121203 M.Fuji WDS Solution#104,105 Pricing End
        }

        shpgPlnTMsg.spFuncNetUnitPrcAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt());

        if (NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(cpoDtlBean.getDtlRqstTpCd())) {
            shpgPlnTMsg.spFuncUnitPrcAmt.setValue(cpoDtlBean.getFuncGrsUnitPrcAmt());
            shpgPlnTMsg.spTotFuncSlsAmt.setValue(cpoDtlBean.getEntCpoDtlFuncSlsAmt());
            shpgPlnTMsg.spTotFuncNetAmt.setValue(cpoDtlBean.getEntCpoDtlFuncNetAmt());
            // 20121203 M.Fuji WDS Solution#104,105 Pricing Strat
            shpgPlnTMsg.spTotFuncFrtAmt.setValue(cpoDtlBean.getLineTotFuncFrtAmt());
            // 20121203 M.Fuji WDS Solution#104,105 Pricing End
        }

        shpgPlnTMsg.ccyCd.setValue(cpoDtlBean.getCcyCd());
        shpgPlnTMsg.exchRate.setValue(cpoDtlBean.getExchRate());
        shpgPlnTMsg.rddDt.setValue(cpoDtlBean.getRddDt());
        shpgPlnTMsg.rsdDt.setValue(cpoDtlBean.getRsdDt());
        shpgPlnTMsg.shipToLocNm.setValue(cpoDtlBean.getShipToLocNm());
        shpgPlnTMsg.shipToAddlLocNm.setValue(cpoDtlBean.getShipToAddlLocNm());
        shpgPlnTMsg.shipToFirstLineAddr.setValue(cpoDtlBean.getShipToFirstLineAddr());
        shpgPlnTMsg.shipToScdLineAddr.setValue(cpoDtlBean.getShipToScdLineAddr());
        shpgPlnTMsg.shipToCtyAddr.setValue(cpoDtlBean.getShipToCtyAddr());
        shpgPlnTMsg.shipToStCd.setValue(cpoDtlBean.getShipToStCd());
        shpgPlnTMsg.shipToPostCd.setValue(cpoDtlBean.getShipToPostCd());
        shpgPlnTMsg.shipToCtryCd.setValue(cpoDtlBean.getShipToCtryCd());
        shpgPlnTMsg.shipToCntyNm.setValue(cpoDtlBean.getShipToCntyNm());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        shpgPlnTMsg.dslpInfoFlg.setValue(cpoDtlBean.getDslpInfoFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        shpgPlnTMsg.custUomCd.setValue(cpoDtlBean.getCustUomCd());
        shpgPlnTMsg.rqstCarrCd.setValue(cpoDtlBean.getCarrCd());
        shpgPlnTMsg.carrAcctNum.setValue(cpoDtlBean.getCarrAcctNum());
        setValue(shpgPlnTMsg.carrAcctShipNum, cpoDtlBean.getCarrAcctShipNum());

        if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getThirdPtyVndDropYFlg())) {
            shpgPlnTMsg.poReqFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            shpgPlnTMsg.poReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        shpgPlnTMsg.mdseCd.setValue(cpoDtlBean.getMdseCd());
        shpgPlnTMsg.invtyLocCd.setValue(cpoDtlBean.getInvtyLocCd());
        shpgPlnTMsg.stkStsCd.setValue(cpoDtlBean.getStkStsCd());
        shpgPlnTMsg.shipCpltCd.setValue(cpoDtlBean.getShipCpltCd());
        shpgPlnTMsg.uomCpltFlg.setValue(cpoDtlBean.getUomCpltFlg());
        setValue(shpgPlnTMsg.pmtTermCashDiscCd, cpoDtlBean.getPmtTermCashDiscCd());
        setValue(shpgPlnTMsg.pmtTermCd, cpoDtlBean.getPmtTermCd());
        setValue(shpgPlnTMsg.cashDiscTermCd, cpoDtlBean.getCashDiscTermCd());
        shpgPlnTMsg.slsRepTocCd.setValue(cpoDtlBean.getSlsRepOrSlsTeamTocCd());

        if (NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(cpoDtlBean.getDtlRqstTpCd())) {
            shpgPlnTMsg.ordQty.setValue(cpoDtlBean.getOrdQty());
        }

        setValue(shpgPlnTMsg.setMdseCd, cpoDtlBean.getSetMdseCd());
        shpgPlnTMsg.setItemShipCpltFlg.setValue(cpoDtlBean.getSetItemShipCpltFlg());
        shpgPlnTMsg.dropShipFlg.setValue(cpoDtlBean.getDropShipFlg());
        setValue(shpgPlnTMsg.shipToThirdLineAddr, cpoDtlBean.getShipToThirdLineAddr());
        setValue(shpgPlnTMsg.shipToFrthLineAddr, cpoDtlBean.getShipToFrthLineAddr());
        setValue(shpgPlnTMsg.shipToFirstRefCmntTxt, cpoDtlBean.getShipToFirstRefCmntTxt());
        setValue(shpgPlnTMsg.shipToScdRefCmntTxt, cpoDtlBean.getShipToScdRefCmntTxt());
        shpgPlnTMsg.sysSrcCd.setValue(cpoBean.getSysSrcCd());

        setValue(shpgPlnTMsg.origShpgSvcLvlCd, cpoDtlBean.getShpgSvcLvlCd());
        setValue(shpgPlnTMsg.exptFlg, cpoDtlBean.getExptFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(shpgPlnTMsg.reqShpgMethCd, cpoDtlBean.getExptShpgMethCd());
//        setValue(shpgPlnTMsg.reqShpgTermCd, cpoDtlBean.getExptShpgTermCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        if (setShpgPlnTMsg != null) {
            setValue(shpgPlnTMsg.setShpgPlnNum, setShpgPlnTMsg.shpgPlnNum);
        }

        // START MODIFY S.Yamamoto [MEX-LC004]
        //        if (ZYPConstant.FLG_OFF_N.equals(cpoDtlBean.getManPrcFlg()) && hasValue(cpoDtlBean.getPrcCatgCd())) {
        //            setValue(shpgPlnTMsg.prcCatgCd, cpoDtlBean.getPrcCatgCd());
        //        }
        setValue(shpgPlnTMsg.prcCatgCd, cpoDtlBean.getPrcCatgCd());
        // END   MODIFY S.Yamamoto [MEX-LC004]

        // START MODIFY M.Fuji [OM031]
        // START MODIFY S.Yamamoto [OM004]
        //        Map<String, String> getDSOrdTpRsnDfnMap = new HashMap<String, String>();
        //        getDSOrdTpRsnDfnMap.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        //        getDSOrdTpRsnDfnMap.put("dsOrdTpCd", cpoBean.getDSOrdTpCd());
        //        getDSOrdTpRsnDfnMap.put("dsOrdRsnCd", cpoBean.getDSOrdRsnCd());
        //
        //        Map<String, String> dsOrdTpRsn = (Map<String, String>) this.ssmClient.queryObject("getDSOrdTpRsnDfn", getDSOrdTpRsnDfnMap);

        // START ADD S.Yamamoto [QC#2565]

        // START MODIFY M.Fuji [Defect#3223]
        String keylocRoleTpMap = cpoBean.getGlblCmpyCd().concat(cpoDtlBean.getInvtyLocCd());
        String locRoleTpCd;

        if (locRoleTpMap.containsKey(keylocRoleTpMap)) {
            locRoleTpCd = (String) locRoleTpMap.get(keylocRoleTpMap);
        } else {
            Map<String, String> getLocRoleTpMap = new HashMap<String, String>();
            getLocRoleTpMap.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
            getLocRoleTpMap.put("invtyLocCd", cpoDtlBean.getInvtyLocCd());

            // START ADD D.Yanagisawa [QC#2565(Reopen)]
            // START MOD T.Nakamura [WDS#2957]
            //        getLocRoleTpMap.put("locRoleTpCd", LOC_ROLE_TP.RTRN_ASSET_WH);
            getLocRoleTpMap.put("locRoleTpCd", LOC_ROLE_TP.RETURNED_ASSET_WAREHOUSE);
            // END   MOD T.Nakamura [WDS#2957]
            // E N D ADD D.Yanagisawa [QC#2565(Reopen)]

            locRoleTpCd = (String) this.ssmClient.queryObject("getLocRoleTp", getLocRoleTpMap);

            locRoleTpMap.put(keylocRoleTpMap, locRoleTpCd);
        }
        // END MODIFY M.Fuji [Defect#3223]

        // 2015/08/27 CSA Add Start
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getTrxCd()) && ZYPCommonFunc.hasValue(cpoDtlBean.getTrxRsnCd())) {
            setValue(shpgPlnTMsg.trxRsnCd, cpoDtlBean.getTrxRsnCd());
            setValue(shpgPlnTMsg.trxCd, cpoDtlBean.getTrxCd());
        } else
        // 2015/08/27 CSA Add End
        // START UPD D.Yanagisawa [QC#2565(Reopen)]
        if (CPO_ORD_TP.EXPENSE.equals(cpoDtlBean.getCpoOrdTpCd()) && ZYPCommonFunc.hasValue(locRoleTpCd)) {
            // E N D UPD D.Yanagisawa [QC#2565(Reopen)]

            setValue(shpgPlnTMsg.trxRsnCd, TRX_RSN.EXPENSE_SHIPMENT_ASSET);
            setValue(shpgPlnTMsg.trxCd, TRX.EXPENSE_SHIPMENT);

        } else {
            // END   ADD S.Yamamoto [QC#2565]

            // START MODIFY M.Fuji [Defect#3223]
            String key = cpoBean.getGlblCmpyCd().concat(cpoBean.getDSOrdTpCd()).concat(cpoBean.getDSOrdRsnCd()).concat(cpoDtlBean.getInvtyLocCd());
            Map<String, String> dsOrdTpRsn;

            if (dsOrdTpTrxDfn.containsKey(key)) {
                dsOrdTpRsn = (Map<String, String>) dsOrdTpTrxDfn.get(key);
            } else {
                Map<String, String> getDsOrdTpTrxDfnMap = new HashMap<String, String>();
                getDsOrdTpTrxDfnMap.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                getDsOrdTpTrxDfnMap.put("dsOrdTpCd", cpoBean.getDSOrdTpCd());
                getDsOrdTpTrxDfnMap.put("dsOrdRsnCd", cpoBean.getDSOrdRsnCd());
                getDsOrdTpTrxDfnMap.put("invtyLocCd", cpoDtlBean.getInvtyLocCd());

                dsOrdTpRsn = (Map<String, String>) ssmClient.queryObject("getDsOrdTpTrxDfn", getDsOrdTpTrxDfnMap);

                dsOrdTpTrxDfn.put(key, dsOrdTpRsn);
            }
            // END MODIFY M.Fuji [Defect#3223]
            // END MODIFY M.Fuji [OM031]

            // 20121219 M.Fuji Defect#38 Start
            if (dsOrdTpRsn != null && hasValue(dsOrdTpRsn.get("TRX_CD")) && hasValue(dsOrdTpRsn.get("TRX_RSN_CD"))) {
                // 20121219 M.Fuji Defect#38 End
                setValue(shpgPlnTMsg.trxRsnCd, dsOrdTpRsn.get("TRX_RSN_CD"));
                setValue(shpgPlnTMsg.trxCd, dsOrdTpRsn.get("TRX_CD"));
            } else {
                final MDSETMsg mdseMsg = NWZC150001CpouExistsCdInDbCheck.getMdse(cpoBean.getRqstTpCd(), cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getMdseCdForMstrSrch());
                setValue(shpgPlnTMsg.trxRsnCd, getTrxRsnCd(cpoDtlBean, mdseMsg));
                setValue(shpgPlnTMsg.trxCd, getTrxCd(cpoDtlBean));
            }
            // END   MODIFY S.Yamamoto [OM004]
            // START ADD S.Yamamoto [QC#2565]
        }
        // END   ADD S.Yamamoto [QC#2565]

        setDefaultValues(shpgPlnTMsg);
        // 1.2WDS modify end <--
    }

    private static void setDefaultValues(SHPG_PLNTMsg shpgPlnTMsg) {

        // QTY, AMT
        final Set<EZDTBigDecimalItem> amtItemList = new HashSet<EZDTBigDecimalItem>();
        amtItemList.add(shpgPlnTMsg.spDealUnitPrcAmt);
        amtItemList.add(shpgPlnTMsg.spDealNetUnitPrcAmt);
        amtItemList.add(shpgPlnTMsg.spTotDealSlsAmt);
        amtItemList.add(shpgPlnTMsg.spTotDealNetAmt);
        amtItemList.add(shpgPlnTMsg.spTotDealFrtAmt);
        amtItemList.add(shpgPlnTMsg.spFuncUnitPrcAmt);
        amtItemList.add(shpgPlnTMsg.spFuncNetUnitPrcAmt);
        amtItemList.add(shpgPlnTMsg.spTotFuncSlsAmt);
        amtItemList.add(shpgPlnTMsg.spTotFuncNetAmt);
        amtItemList.add(shpgPlnTMsg.spTotFuncFrtAmt);
        amtItemList.add(shpgPlnTMsg.avalHardAllocQty);
        amtItemList.add(shpgPlnTMsg.avalSoftAllocQty);
        amtItemList.add(shpgPlnTMsg.avalSoQty);
        amtItemList.add(shpgPlnTMsg.avalPoQty);
        amtItemList.add(shpgPlnTMsg.exchRate);
        amtItemList.add(shpgPlnTMsg.ordUomQty);
        amtItemList.add(shpgPlnTMsg.softAllocQty);
        amtItemList.add(shpgPlnTMsg.ordQty);
        amtItemList.add(shpgPlnTMsg.crHldQty);
        amtItemList.add(shpgPlnTMsg.slsHldQty);
        amtItemList.add(shpgPlnTMsg.crChkQty);
        amtItemList.add(shpgPlnTMsg.setPrcDetGrpSq);

        for (EZDTBigDecimalItem amtItem : amtItemList) {
            if (!hasValue(amtItem)) {
                setValue(amtItem, ZERO);
            }
        }

        // FLG
        final Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
        flgItemList.add(shpgPlnTMsg.dropShipFlg);
        flgItemList.add(shpgPlnTMsg.dslpInfoFlg);
        flgItemList.add(shpgPlnTMsg.shipAvalFlg);
        flgItemList.add(shpgPlnTMsg.poReqFlg);
        flgItemList.add(shpgPlnTMsg.soCloseFlg);
        flgItemList.add(shpgPlnTMsg.uomCpltFlg);
        flgItemList.add(shpgPlnTMsg.shipPlnHldFlg);
        flgItemList.add(shpgPlnTMsg.shipPlnCancFlg);
        flgItemList.add(shpgPlnTMsg.setItemShipCpltFlg);
        flgItemList.add(shpgPlnTMsg.sendEmlCpltFlg);
        flgItemList.add(shpgPlnTMsg.revRecogFlg);
        flgItemList.add(shpgPlnTMsg.exptFlg);

        for (EZDTStringItem flgItem : flgItemList) {
            if (!hasValue(flgItem)) {
                setValue(flgItem, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    // 20121211 M.Fuji WDS Solution#104,105 Pricing End
    /**
     * Max Display Number Acquisition
     * 
     * <pre>
     * When data has already been registered in SHPG_PLN, the maximum number is acquired including the supersession data.
     * +1 value done to it is returned.
     * "001" is returned when not existing.
     * </pre>
     * @param detailBean NWZC150001CpouDetailBean
     * @return String
     */
    private String getMaxShpgPlnDplyLineNum(String cpoOrdNum, NWZC150001CpouDetailBean detailBean) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", detailBean.getGlblCmpyCd());
        ssmParam.put("trxHdrNum", cpoOrdNum);
        ssmParam.put("trxLineNum", detailBean.getCpoDtlLineNum());
        ssmParam.put("trxLineSubNum", detailBean.getCpoDtlLineSubNum());
        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);

        String dispNum = (String) ssmClient.queryObject("getMaxShpgPlnDplyLineNum", ssmParam);
        if (hasValue(dispNum)) {
            return ZYPCommonFunc.leftPad(String.valueOf(Integer.parseInt(dispNum) + 1), 3, "0");
        } else {
            return "001";
        }
    }


    private static String getTrxCd(NWZC150001CpouDetailBean cpoDtlBean) {

        final String cpoOrdTpCd = cpoDtlBean.getCpoOrdTpCd();

        if (CPO_ORD_TP.SALES.equals(cpoOrdTpCd)) {
            return TRX.SALES;

        } else if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd)) {
            return TRX.EXPENSE_SHIPMENT;

        } else if (CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd)) {
            return TRX.MOVEMENT;

        } else if (CPO_ORD_TP.LOAN.equals(cpoOrdTpCd)) {
            return TRX.LOAN_SHIPMENT;

            // START ADD S.Yamamoto [OM004]
        } else if (CPO_ORD_TP.RENTAL_DS.equals(cpoOrdTpCd)) {
            return TRX.RENTAL_SHIPMENT;
            // END   ADD S.Yamamoto [OM004]
        } else {
            return null;
        }
    }

    private static String getTrxRsnCd(NWZC150001CpouDetailBean cpoDtlBean, MDSETMsg mdseTMsg) {

        final String cpoOrdTpCd = cpoDtlBean.getCpoOrdTpCd();
        final String invtyCtrlFlg = mdseTMsg.invtyCtrlFlg.getValue();
        final String invtyValFlg = mdseTMsg.invtyValFlg.getValue();

        if (CPO_ORD_TP.SALES.equals(cpoOrdTpCd) && ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
            return TRX_RSN.REGULAR_SALES_WITH_COST;

        } else if (CPO_ORD_TP.SALES.equals(cpoOrdTpCd) && ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg) && ZYPConstant.FLG_ON_Y.equals(invtyValFlg)) {
            return TRX_RSN.REGULAR_SALES_WITH_COST;

        } else if (CPO_ORD_TP.SALES.equals(cpoOrdTpCd) && ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg) && ZYPConstant.FLG_OFF_N.equals(invtyValFlg)) {
            return TRX_RSN.REGULAR_SALES_WITHOUT_COST;

        } else if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd) && ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
            return TRX_RSN.EXPENSE_SHIPMENT;

        } else if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd) && ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg) && ZYPConstant.FLG_ON_Y.equals(invtyValFlg)) {
            return TRX_RSN.EXPENSE_SHIPMENT;

        } else if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd) && ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg) && ZYPConstant.FLG_OFF_N.equals(invtyValFlg)) {
            return TRX_RSN.NO_SHIP_EXPENSE;

        } else if (CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd)) {
            // START MODIFY S.Yamamoto [OM004]
            //Upd WDS Defect#1692 Start
            //            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getThirdPtyVndDropYFlg())) {
            //                return TRX_RSN.DROP_SHIPMENT_TRIAL_STOCK_OUT;
            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getThirdPtyVndDropYFlg())) {
                if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
                    return TRX_RSN.DROP_SHIPMENT_TRIAL_STOCK_OUT;
                } else {
                    return TRX_RSN.TRIAL_STOCK_OUT_INTANGIBLE_WITH_COST;
                }
                //Upd WDS Defect#1692 End
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
                    return TRX_RSN.TRIAL_SHIPMENT_STOCK_OUT;
                } else {
                    return TRX_RSN.TRIAL_STOCK_OUT_INTANGIBLE_WITH_COST;
                }
            }
            // END   MODIFY S.Yamamoto [OM004]
        } else if (CPO_ORD_TP.LOAN.equals(cpoOrdTpCd)) {
            // START MODIFY S.Yamamoto [OM004]
            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getThirdPtyVndDropYFlg())) {
                return TRX_RSN.DROP_SHIPMENT_LOAN_STOCK_OUT;
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
                    return TRX_RSN.LOAN_SHIPMENT_STOCK_OUT;
                } else {
                    return TRX_RSN.LOAN_STOCK_OUT_INTANGIBLE_WITH_COST;
                }
            }
            // END   MODIFY S.Yamamoto [OM004]
        } else if (CPO_ORD_TP.RENTAL_DS.equals(cpoOrdTpCd)) {
            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getThirdPtyVndDropYFlg())) {
                return TRX_RSN.DROP_SHIPMENT_RENTAL_STOCK_OUT;
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
                    return TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT;
                } else {
                    return TRX_RSN.RENTAL_STOCK_OUT_INTANGIBLE_WITH_COST;
                }
            }
            // END   MODIFY S.Yamamoto [OM004]
        } else {
            return null;
        }
    }


    // private void updateShpgPln(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, SHPG_PLNTMsg shpgPlnTMsg, List<SHPG_PLNTMsg> updateShpgPlnTMsgList) { // For Performance QC#10321 Mod
    private void updateShpgPln(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, SHPG_PLNTMsg shpgPlnTMsg, List<SHPG_PLNTMsg> updateShpgPlnTMsgList, List<SHPG_PLNTMsg> updShpgPlnTMsgAry) {

        final List<String> shippedBeforeList = new ArrayList<String>();
        shippedBeforeList.add(SHPG_STS.HARD_ALLOCATED);
        shippedBeforeList.add(SHPG_STS.S_OR_O_PRINTED);
        shippedBeforeList.add(SHPG_STS.P_OR_O_PRINTED);
        shippedBeforeList.add(SHPG_STS.PICKED);
        shippedBeforeList.add(SHPG_STS.PACKED);
        shippedBeforeList.add(SHPG_STS.STAGED);

        final List<String> invoicedBeforeList = new ArrayList<String>();
        invoicedBeforeList.add(SHPG_STS.INSHED);
        invoicedBeforeList.add(SHPG_STS.SHIPPED);
        invoicedBeforeList.add(SHPG_STS.ARRIVED);
        invoicedBeforeList.add(SHPG_STS.INSTALLED); // S21_NA#11972,13616 ADD

        String shpgStsCd = null;

        if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {

            // 2019/11/27 QC#52339 Mod Start 
            //if (canCancelShpgSts.contains(shpgPlnTMsg.shpgStsCd.getValue())) {
            if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(shpgPlnTMsg, false)) {
            // 2019/11/27 QC#52339 Mod End 

                Map<String, String> map = new HashMap<String, String>();
                map.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
                map.put("setShpgPlnNum", shpgPlnTMsg.shpgPlnNum.getValue());

                shpgStsCd = (String) ssmClient.queryObject("getSetMaxShpgStsCd", map);
            }

        } else if (hasValue(shpgPlnTMsg.setShpgPlnNum)) {

            Map<String, String> map = new HashMap<String, String>();
            map.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
            map.put("shpgPlnNum", shpgPlnTMsg.setShpgPlnNum.getValue());

            shpgStsCd = (String) ssmClient.queryObject("getSetShpgStsCd", map);

            if (canCancelShpgSts.contains(shpgStsCd)) {

                map = new HashMap<String, String>();
                map.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
                map.put("setShpgPlnNum", shpgPlnTMsg.setShpgPlnNum.getValue());

                String cmpShpgStsCd = (String) ssmClient.queryObject("getSetMaxShpgStsCd", map);

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

        // 2019/08/15 S21_NA#52620 Mod Start
        boolean isExistPRApprove = false;
        // 2019/11/27 QC#52339 Mod Start
        //if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(shpgPlnTMsg) && exixtsPRApproved(shpgPlnTMsg)) {
        if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(shpgPlnTMsg, false) && exixtsPRApproved(shpgPlnTMsg)) {
        // 2019/11/27 QC#52339 Mod End 
            isExistPRApprove = true;
        }
        if (!isExistPRApprove) {
            if (!shippedBeforeList.contains(shpgStsCd) && !invoicedBeforeList.contains(shpgStsCd)) {
                return;
            }
        }

//        if (!shippedBeforeList.contains(shpgStsCd) && !invoicedBeforeList.contains(shpgStsCd)) {
//            return;
//        }
        // 2019/08/15 S21_NA#52620 Mod End

        // 2018/06/20 QC#20154 Add Start
        shpgPlnTMsg.shipToCustCd.setValue(cpoDtlBean.getShipToCustCd());
        shpgPlnTMsg.shipToLocNm.setValue(cpoDtlBean.getShipToLocNm());
        shpgPlnTMsg.shipToAddlLocNm.setValue(cpoDtlBean.getShipToAddlLocNm());
        shpgPlnTMsg.shipToFirstLineAddr.setValue(cpoDtlBean.getShipToFirstLineAddr());
        shpgPlnTMsg.shipToScdLineAddr.setValue(cpoDtlBean.getShipToScdLineAddr());
        shpgPlnTMsg.shipToThirdLineAddr.setValue(cpoDtlBean.getShipToThirdLineAddr());
        shpgPlnTMsg.shipToFrthLineAddr.setValue(cpoDtlBean.getShipToFrthLineAddr());
        shpgPlnTMsg.shipToFirstRefCmntTxt.setValue(cpoDtlBean.getShipToFirstRefCmntTxt());
        shpgPlnTMsg.shipToScdRefCmntTxt.setValue(cpoDtlBean.getShipToScdRefCmntTxt());
        shpgPlnTMsg.shipToCtyAddr.setValue(cpoDtlBean.getShipToCtyAddr());
        shpgPlnTMsg.shipToStCd.setValue(cpoDtlBean.getShipToStCd());
        shpgPlnTMsg.shipToCntyNm.setValue(cpoDtlBean.getShipToCntyNm());
        shpgPlnTMsg.shipToPostCd.setValue(cpoDtlBean.getShipToPostCd());
        shpgPlnTMsg.shipToCtryCd.setValue(cpoDtlBean.getShipToCtryCd());
        // 2018/06/20 QC#20154 Add End

        BigDecimal spDealUnitPrcAmt = ZERO;
        BigDecimal spFuncUnitPrcAmt = ZERO;

        if (shpgPlnTMsg.spDealNetUnitPrcAmt.getValue().compareTo(cpoDtlBean.getEntDealNetUnitPrcAmt()) != 0) {
            spDealUnitPrcAmt = cpoDtlBean.getEntDealNetUnitPrcAmt();
            spFuncUnitPrcAmt = cpoDtlBean.getEntDealNetUnitPrcAmt();
        } else {
            spDealUnitPrcAmt = shpgPlnTMsg.spDealUnitPrcAmt.getValue();
            spFuncUnitPrcAmt = shpgPlnTMsg.spFuncUnitPrcAmt.getValue();
        }

        shpgPlnTMsg.spDealNetUnitPrcAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt());
        shpgPlnTMsg.spDealUnitPrcAmt.setValue(spDealUnitPrcAmt);
        shpgPlnTMsg.spTotDealSlsAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt().multiply(shpgPlnTMsg.ordQty.getValue()));
        shpgPlnTMsg.spTotDealNetAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt().multiply(shpgPlnTMsg.ordQty.getValue()));

        shpgPlnTMsg.spFuncNetUnitPrcAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt());
        shpgPlnTMsg.spFuncUnitPrcAmt.setValue(spFuncUnitPrcAmt);
        shpgPlnTMsg.spTotFuncSlsAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt().multiply(shpgPlnTMsg.ordQty.getValue()));
        shpgPlnTMsg.spTotFuncNetAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt().multiply(shpgPlnTMsg.ordQty.getValue()));

        shpgPlnTMsg.ccyCd.setValue(cpoDtlBean.getCcyCd());
        shpgPlnTMsg.pmtTermCashDiscCd.setValue(cpoDtlBean.getPmtTermCashDiscCd());
        shpgPlnTMsg.pmtTermCd.setValue(cpoDtlBean.getPmtTermCd());
        // 2017/06/14 S21_NA#19111 Mod Start
        //shpgPlnTMsg.cashDiscTermCd.setValue(cpoDtlBean.getCashDiscTermCd());
        setValue(shpgPlnTMsg.cashDiscTermCd, cpoDtlBean.getCashDiscTermCd());
        // 2017/06/14 S21_NA#19111 Mod End
        shpgPlnTMsg.slsRepTocCd.setValue(cpoDtlBean.getSlsRepOrSlsTeamTocCd());
        shpgPlnTMsg.shipCpltCd.setValue(cpoDtlBean.getShipCpltCd());

        // START MODIFY S.Yamamoto [MEX-LC004]
        //        if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getManPrcFlg())) {
        //            shpgPlnTMsg.prcCatgCd.clear();
        //        }
        shpgPlnTMsg.prcCatgCd.setValue(cpoDtlBean.getPrcCatgCd());
        // END   MODIFY S.Yamamoto [MEX-LC004]

        // ***** [update]
        // For Performance QC#10321 Mod Start
        // update(shpgPlnTMsg);
        updShpgPlnTMsgAry.add(shpgPlnTMsg);
        // For Performance QC#10321 Mod End

//        if (shippedBeforeList.contains(shpgStsCd)) { // S21_NA#11972,13616 DEL
            updateShpgPlnTMsgList.add(shpgPlnTMsg);
//        }
    }

    /**
     * <pre>
     * Get Shipping Plan Array related to detail object.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param trxHdrNum Trx Header Number = CPO_ORD_NUM
     * @param trxLineNum Trx Line Number = CPO_DTL_LINE_NUM
     * @param trxLineSubNum Trx Line Sub Number = CPO_DTL_LINE_SUB_NUM
     * @return
     */
    public static SHPG_PLNTMsgArray getShpgPlnTMsgArray(String glblCmpyCd, String trxHdrNum, String trxLineNum, String trxLineSubNum) {

        final SHPG_PLNTMsg reqTMsg = new SHPG_PLNTMsg();
        reqTMsg.setSQLID("008");
        reqTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        reqTMsg.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES);
        reqTMsg.setConditionValue("trxHdrNum01", trxHdrNum);
        reqTMsg.setConditionValue("trxLineNum01", trxLineNum);
        reqTMsg.setConditionValue("trxLineSubNum01", trxLineSubNum);

        return (SHPG_PLNTMsgArray) findByCondition(reqTMsg);
    }


    public void cancelShpgPlnModeInShed(List<SHPG_PLNTMsg> cancelShpgPlnTMsgList) {
        final String methodNm = "cancelShpgPlnModeInShed";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // ***** [update]
            for (int i = 0; i < cancelShpgPlnTMsgList.size(); i++) {
                update(cancelShpgPlnTMsgList.get(i));
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    // 20121130 M.Fuji WDS Solution#104,105 Pricing Start
    /**
     * PRC_DTL update For WDS
     * @param cpoBean NWZC150001CpouBean
     * @param removeShpgPlnTMsgList List<SHPG_PLNTMsg>
     * @param createShpgPlnTMsgList List<SHPG_PLNTMsg>
     * @param updateShpgPlnTMsgList List<SHPG_PLNTMsg> // S21_NA#11972,13616 ADD
     */
    public void updatePrcDtlForWDS(NWZC150001CpouBean cpoBean, List<SHPG_PLNTMsg> removeShpgPlnTMsgList, List<SHPG_PLNTMsg> createShpgPlnTMsgList, List<SHPG_PLNTMsg> updateShpgPlnTMsgList) {

        final String rqstTpCd = cpoBean.getRqstTpCd();
        // --------------------------------------------------
        // SAVE
        // --------------------------------------------------
        if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd)) {
            return;
        }

        // S21_NA#11972,13616 ADD START
        // Shipping Plan List for delete PRC_DTL
        List<SHPG_PLNTMsg> removeUpdateShpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();
        removeUpdateShpgPlnTMsgList.addAll(removeShpgPlnTMsgList);
        removeUpdateShpgPlnTMsgList.addAll(updateShpgPlnTMsgList);

        // Shipping Plan List for create PRC_DTL
        List<SHPG_PLNTMsg> createUpdateShpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();
        createUpdateShpgPlnTMsgList.addAll(createShpgPlnTMsgList);
        createUpdateShpgPlnTMsgList.addAll(updateShpgPlnTMsgList);
        // S21_NA#11972,13616 ADD END

        // --------------------------------------------------
        // MODIFY, CANCEL
        // --------------------------------------------------
        if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd) || NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd)) {

            // For Performance QC#10321 Add Start
            List<PRC_DTLTMsg> delPrcDtlTMsgAry = new ArrayList<PRC_DTLTMsg>();
            // For Performance QC#10321 Add End

//            for (SHPG_PLNTMsg removeShpgPlnTMsg : removeShpgPlnTMsgList) {
            for (SHPG_PLNTMsg removeShpgPlnTMsg : removeUpdateShpgPlnTMsgList) { // S21_NA#11972,13616 MOD
                PRC_DTLTMsg reqPrcDtlTMsg = new PRC_DTLTMsg();
                reqPrcDtlTMsg.setSQLID("005");
                reqPrcDtlTMsg.setConditionValue("glblCmpyCd01", removeShpgPlnTMsg.glblCmpyCd.getValue());
                reqPrcDtlTMsg.setConditionValue("shpgPlnNum01", removeShpgPlnTMsg.shpgPlnNum.getValue());
                PRC_DTLTMsgArray resPrcDtlTMsgArray = (PRC_DTLTMsgArray) findByConditionForUpdate(reqPrcDtlTMsg);

                for (int i = 0; i < resPrcDtlTMsgArray.getValidCount(); i++) {

                    PRC_DTLTMsg resPrcDtlTMsg = resPrcDtlTMsgArray.no(i);
                    // ***** [logicalRemove]
                    // For Performance QC#10321 Mod Start
                    // logicalRemove(resPrcDtlTMsg);
                    delPrcDtlTMsgAry.add(resPrcDtlTMsg);
                    // For Performance QC#10321 Mod End
                }
            }

            // For Performance QC#10321 Add Start
            if (!delPrcDtlTMsgAry.isEmpty()) {
                int cnt = S21FastTBLAccessor.removeLogical(delPrcDtlTMsgAry.toArray(new PRC_DTLTMsg[0]));
                if (cnt != delPrcDtlTMsgAry.size()) {
                    throw new S21AbendException("updatePrcDtlForWDS : PRC_DTL Delete Error");
                }
            }
            // For Performance QC#10321 Add End
        }

        // --------------------------------------------------
        // SUBMIT, MODIFY(Add), MODIFY, CANCEL
        // --------------------------------------------------
        // ***** [insert]
//        createPrcDtlForWDS(cpoBean, createShpgPlnTMsgList);
        createPrcDtlForWDS(cpoBean, createUpdateShpgPlnTMsgList); // S21_NA#11972,13616 MOD
    }

    /**
     * PRC_DTL update For WDS
     * 
     * <pre>
     * | CPO Update API                                           | Pricing API
     * ―――――――――――――――――――――――――――――――――――――――――――――――――――――
     * | Merchandise Code | CPO Line Number | CPO Line Sub Number | ORG | Pricing(A table) | Discount
     * ―――――――――――――――――――――――――――――――――――――――――――――――――――――
     * | A                | 001             | 000                 | 0   | pricing data1    | Discount data1
     * |                  |                 |                     |     |                  | Discount data2
     * | B                | 001             | 001                 |     | pricing data2    | Discount data1
     * |                  |                 |                     |     |                  | Discount data2
     * | C                | 001             | 002                 |     | pricing data3    | Discount data1
     * |                  |                 |                     |     |                  | Discount data2
     * ----------------------------------------------------------------------------------------------------------
     * | D                | 002             | 001                 | 1   | pricing data1    | Discount data1
     * |                  |                 |                     |     |                  | Discount data2
     *</pre>
     * @param cpoBean NWZC150001CpouBean
     * @param createPrcDtlForWDS List<SHPG_PLNTMsg>
     */
    private static void createPrcDtlForWDS(NWZC150001CpouBean cpoBean, List<SHPG_PLNTMsg> createShpgPlnTMsgList) {

        // For Performance QC#10321 Add Start
        List<PRC_DTLTMsg> insPrcDtlTMsgAry = new ArrayList<PRC_DTLTMsg>();
        // For Performance QC#10321 Add End

        for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

            // 20130122 Defect#321 M.Fuji Start
            //            String dtlRqstTpCd = cpoDtlBean.getDtlRqstTpCd();
            //            if (NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(dtlRqstTpCd) || NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(dtlRqstTpCd)) {
            //                if (!isShpgPlnCreateLineData(cpoDtlBean, createShpgPlnTMsgList)) {
            //                    continue;
            //                }
            //            }

            // S21_NA#11972,13616 MOD START
//            SHPG_PLNTMsg shpgPlnTMsg = getShpgPlnTMsgOfCreateLineData(cpoDtlBean, createShpgPlnTMsgList);
//            if (shpgPlnTMsg == null) {
//                continue;
//            }
            // 20130122 Defect#321 M.Fuji End

            List<SHPG_PLNTMsg> shpgPlnTMsgList = getShpgPlnTMsgOfCreateLineData(cpoDtlBean, createShpgPlnTMsgList);
            if (shpgPlnTMsgList == null || shpgPlnTMsgList.size() == 0) {
              continue;
            }
            // S21_NA#11972,13616 MOD END

            for (SHPG_PLNTMsg shpgPlnTMsg : shpgPlnTMsgList) { // S21_NA#11972,13616 MOD
                if (cpoDtlBean.getDiscPrcList().size() > 0) {

                    for (NWZC150001CpouDiscountBean discPrcData : cpoDtlBean.getDiscPrcList()) {

                        // 20130122 Defect#321 M.Fuji Start
                        final PRC_DTLTMsg reqPrcDtlTMsg = createPrcDtlTMsgForInsertWDS(cpoBean, cpoDtlBean, discPrcData, shpgPlnTMsg);
                        // 20130122 Defect#321 M.Fuji End

                        // For Performance QC#10321 Mod End
                        // insert(reqPrcDtlTMsg);
                        // if (!RTNCD_NORMAL.equals(reqPrcDtlTMsg.getReturnCode())) {
                        //     throw new S21AbendException("createPricingDetail : Insert Error");
                        // }
                        insPrcDtlTMsgAry.add(reqPrcDtlTMsg);
                        // For Performance QC#10321 Mod End
                    }
                } else {
                    // 20130122 Defect#321 M.Fuji Start
                    final PRC_DTLTMsg reqPrcDtlTMsg = createPrcDtlTMsgForInsertWDS(cpoBean, cpoDtlBean, null, shpgPlnTMsg);
                    // 20130122 Defect#321 M.Fuji End

                    // For Performance QC#10321 Mod Start
                    // insert(reqPrcDtlTMsg);
                    // if (!RTNCD_NORMAL.equals(reqPrcDtlTMsg.getReturnCode())) {
                    //     throw new S21AbendException("createPricingDetail : Insert Error");
                    // }
                    insPrcDtlTMsgAry.add(reqPrcDtlTMsg);
                    // For Performance QC#10321 Mod End
                }
            }
        }

        // For Performance QC#10321 Add Start
        if (!insPrcDtlTMsgAry.isEmpty()) {
            int cnt = S21FastTBLAccessor.insert(insPrcDtlTMsgAry.toArray(new PRC_DTLTMsg[0]));
            if (cnt != insPrcDtlTMsgAry.size()) {
                throw new S21AbendException("createPrcDtlForWDS : PRC_DTL Insert Error");
            }
        }
        // For Performance QC#10321 Add End
    }
    // 20130122 Defect#321 M.Fuji Start
    /**
     * getShpgPlnTMsgOfCreateLineData 
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param createShpgPlnTMsgList List<SHPG_PLNTMsg>
     * @return SHPG_PLNTMsgArray  // S21_NA#11972,13616 MOD
     */
    private static List<SHPG_PLNTMsg> getShpgPlnTMsgOfCreateLineData(NWZC150001CpouDetailBean cpoDtlBean, List<SHPG_PLNTMsg> createShpgPlnTMsgList) {

        // S21_NA#11972,13616 ADD
        List<SHPG_PLNTMsg> shpgPlnList = new ArrayList<SHPG_PLNTMsg>();

        //        final String cpoOrdNum = cpoDtlBean.getCpoOrdNum();
        final String cpoDtlLineNum = cpoDtlBean.getCpoDtlLineNum();
        final String cpoDtlLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();

        for (SHPG_PLNTMsg shpgPlnTMsg : createShpgPlnTMsgList) {

            //            final String trxHdrNum = shpgPlnTMsg.trxHdrNum.getValue();
            final String trxLineNum = shpgPlnTMsg.trxLineNum.getValue();
            final String trxLineSubNum = shpgPlnTMsg.trxLineSubNum.getValue();

            if (cpoDtlLineNum.equals(trxLineNum) && cpoDtlLineSubNum.equals(trxLineSubNum)) {
                // S21_NA#11972,13616 MOD
//                return shpgPlnTMsg;
                shpgPlnList.add(shpgPlnTMsg);
            }
        }
        // S21_NA#11972,13616 MOD
//        return null;
        return shpgPlnList;
    }

    // 20130122 Defect#321 M.Fuji End

    /**
     * PRC_DTL update value setting
     * 
     * <pre>
     * The value that is registered, and updated to PRC_DTL is set.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param priceData NWZC150001_priceListPMsg
     * @return PRC_DTLTMsg
     */
    private static PRC_DTLTMsg createPrcDtlTMsgForInsertWDS(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, NWZC150001CpouDiscountBean discPrcData, SHPG_PLNTMsg shpgPlnTMsg) {

        final PRC_DTLTMsg prcDtlTMsg = new PRC_DTLTMsg();

        prcDtlTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        prcDtlTMsg.prcDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_DTL_SQ));

        prcDtlTMsg.cpoOrdNum.setValue(NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        prcDtlTMsg.cpoDtlLineNum.setValue(NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
        prcDtlTMsg.cpoDtlLineSubNum.setValue(NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));
        // 20130122 Defect#321 M.Fuji Start
        //prcDtlTMsg.shpgPlnNum.setValue(cpoDtlBean.getShpgPlnNum());
        prcDtlTMsg.shpgPlnNum.setValue(shpgPlnTMsg.shpgPlnNum.getValue());
        // 20130122 Defect#321 M.Fuji End

        prcDtlTMsg.prcDt.setValue(cpoBean.getSlsDt());
        // 20130122 Defect#321 M.Fuji Start
        //prcDtlTMsg.shipUnitQty.setValue(cpoDtlBean.getOrdQty());
        prcDtlTMsg.shipUnitQty.setValue(shpgPlnTMsg.ordQty.getValue());
        // 20130122 Defect#321 M.Fuji End
        BigDecimal shipUnitQty = prcDtlTMsg.shipUnitQty.getValue();

        prcDtlTMsg.dealUnitPrcAmt.setValue(cpoDtlBean.getDealGrsUnitPrcAmt());
        prcDtlTMsg.dealLastNetUnitPrcAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt());
        prcDtlTMsg.dealNetAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt().multiply(shipUnitQty));
        prcDtlTMsg.funcUnitPrcAmt.setValue(cpoDtlBean.getFuncGrsUnitPrcAmt());
        prcDtlTMsg.funcLastNetUnitPrcAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt());
        prcDtlTMsg.funcNetAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt().multiply(shipUnitQty));

        if (discPrcData != null) {
            prcDtlTMsg.cpoLinePrcNum.setValue(String.valueOf(discPrcData.getCpoLinePrcNum()));
            prcDtlTMsg.dealDiscAmt.setValue(discPrcData.getDealPerUnitFixAmt().multiply(shipUnitQty));
            prcDtlTMsg.dealPrmoNetUnitPrcAmt.setValue(discPrcData.getDealPrmoNetUnitPrcAmt());
            prcDtlTMsg.funcDiscAmt.setValue(discPrcData.getFuncPerUnitFixAmt().multiply(shipUnitQty));
            prcDtlTMsg.funcPrmoNetUnitPrcAmt.setValue(discPrcData.getFuncPrmoNetUnitPrcAmt());
            prcDtlTMsg.dealPerUnitFixAmt.setValue(discPrcData.getDealPerUnitFixAmt());
            prcDtlTMsg.dealSlsPctNum.setValue(discPrcData.getDealSlsPctNum());
            prcDtlTMsg.funcPerUnitFixAmt.setValue(discPrcData.getFuncPerUnitFixAmt());
            // START ADD S.Yamamoto [OM003]
            setValue(prcDtlTMsg.coaAcctCd, discPrcData.getCoaAcctCd());
            // END   ADD S.Yamamoto [OM003]
        } else {
            // 20130122 Defect#321 M.Fuji Start
            //            prcDtlTMsg.dealPrmoNetUnitPrcAmt.setValue(cpoDtlBean.getEntCpoDtlDealSlsAmt());
            //            prcDtlTMsg.funcPrmoNetUnitPrcAmt.setValue(cpoDtlBean.getEntCpoDtlFuncSlsAmt());
            prcDtlTMsg.dealPrmoNetUnitPrcAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt());
            prcDtlTMsg.funcPrmoNetUnitPrcAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt());
            // 20130122 Defect#321 M.Fuji End
        }

        setDefaultValues(prcDtlTMsg);

        return prcDtlTMsg;
    }

    private static void setDefaultValues(PRC_DTLTMsg prcDtlTMsg) {

        // QTY, AMT
        final Set<EZDTBigDecimalItem> amtItemList = new HashSet<EZDTBigDecimalItem>();
        amtItemList.add(prcDtlTMsg.shipUnitQty);
        amtItemList.add(prcDtlTMsg.dealUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.dealLastNetUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.dealNetAmt);
        amtItemList.add(prcDtlTMsg.dealDiscAmt);
        amtItemList.add(prcDtlTMsg.dealPrmoNetUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.dealPerUnitFixAmt);
        amtItemList.add(prcDtlTMsg.dealSlsPctNum);
        amtItemList.add(prcDtlTMsg.funcPerUnitFixAmt);
        amtItemList.add(prcDtlTMsg.funcUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.funcLastNetUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.funcNetAmt);
        amtItemList.add(prcDtlTMsg.funcDiscAmt);
        amtItemList.add(prcDtlTMsg.funcPrmoNetUnitPrcAmt);

        for (EZDTBigDecimalItem amtItem : amtItemList) {
            if (!hasValue(amtItem)) {
                setValue(amtItem, ZERO);
            }
        }
    }

    // Add Start 2019/07/31 QC#52267
    private boolean cancelAllocForSvcMachMstr(NWZC150001PMsg pMsg, NWZC150001CpouDetailBean cpoDtlBean, List<NWZC150002PMsg> resPMsg2List, ONBATCH_TYPE prmOnBatchType) {

        if (!ZYPCommonFunc.hasValue(cpoDtlBean.getSvcMachMstrPk())) {
            return true;
        }

        NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, cpoDtlBean.getSvcMachMstrPk());

        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, prmOnBatchType);

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < machMstrUpdMsg.xxMsgIdList.getValidCount(); i++) {
                NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                NWZC150001Common.setMsgId2(pMsg2, machMstrUpdMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, cpoDtlBean.getDsOrdPosnNum());
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpoDtlBean.getCpoDtlLineNum());
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpoDtlBean.getCpoDtlLineSubNum());
                resPMsg2List.add(pMsg2);

                isApiResultSuccess = false;
            }
        }
        return isApiResultSuccess;
    }
    // Add End 2019/07/31 QC#52267

    // 2019/08/15 S21_NA#52620 Add Start
    private boolean exixtsPRApproved(SHPG_PLNTMsg shpgPlnTMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
        queryParam.put("shpgPlanNum", shpgPlnTMsg.shpgPlnNum.getValue());
        queryParam.put("prchReqApvlFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("prchReqLineSts", PRCH_REQ_LINE_STS.CANCELLED);
        queryParam.put("prchReqSts", PRCH_REQ_STS.CANCELLED);
        // 2019/11/26 S21_NA#54846 Add Start
        queryParam.put("poStsCancel", PO_STS.CANCELLED);
        // 2019/11/26 S21_NA#54846 Add End
        // 2023/12/21 QC#63776 Add Start
        if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue()) || ZYPCommonFunc.hasValue(shpgPlnTMsg.setShpgPlnNum)) {
            queryParam.put("isSetItem", ZYPConstant.FLG_ON_Y);
        }
        // 2023/12/21 QC#63776 Add End

        BigDecimal rsltCnt = (BigDecimal) ssmClient.queryObject("exixtsPRApproved", queryParam);

        return BigDecimal.ZERO.compareTo(rsltCnt) < 0;
    }
    // 2019/08/15 S21_NA#52620 Add End

    // 2019/11/15 S21_NA#54199 Add Start
    private BigDecimal getTotalShpgPlnOrdQty(SHPG_PLNTMsgArray shpgPlnTMsgArray) {

        long totalShpgPlnOrdQty = 0;
        for (int i = 0; i < shpgPlnTMsgArray.getValidCount(); i++) {
            SHPG_PLNTMsg shpgPlnTMsg = shpgPlnTMsgArray.no(i);

            if (!S21StringUtil.isEquals(SHPG_STS.CANCELLED, shpgPlnTMsg.shpgStsCd.getValue())) {
                totalShpgPlnOrdQty += shpgPlnTMsg.ordQty.getValueInt();
            }
        }
        return BigDecimal.valueOf(totalShpgPlnOrdQty);
    }
    // 2019/11/15 S21_NA#54199 Add End

    // 2019/11/22 S21_NA#54720 Add Start
    private boolean removeOpenPr(SHPG_PLNTMsg shpgPlnTMsg, List<NWZC150002PMsg> resPMsg2List) {
        Map<Object, Object> queryParam = new HashMap<Object, Object>();
        queryParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
        queryParam.put("shpgPlnNum", shpgPlnTMsg.shpgPlnNum.getValue());
        queryParam.put("openY", ZYPConstant.FLG_ON_Y);
        queryParam.put("approveN", ZYPConstant.FLG_OFF_N);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getOpenPr", queryParam);

        int cnt = 0;
        NPZC103001PMsg params = null;
        boolean apiResult = true;

        for (Map<String, Object> result : resultList) {
            String prchReqNum = (String) result.get("PRCH_REQ_NUM");

            if (cnt == 0) {
                    params = new NPZC103001PMsg();
                    ZYPEZDItemValueSetter.setValue(params.xxModeCd, NPZC103001Constant.MODE_CANCEL);
                    ZYPEZDItemValueSetter.setValue(params.eventId, NPZC103001Constant.EVENT_SUBMIT);
                    ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, shpgPlnTMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(params.prchReqNum, prchReqNum);
            }

            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineNum, (String) result.get("PRCH_REQ_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineSubNum, (BigDecimal) result.get("PRCH_REQ_LINE_SUB_NUM"));
            cnt++;
        }

        if (params != null) {
            params.prchReqInfo.setValidCount(cnt);

            NPZC103001 api = new NPZC103001();
            api.execute(params, ONBATCH_TYPE.ONLINE);

            if (params.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < params.xxMsgIdList.getValidCount(); i++) {
                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                    NWZC150001Common.setMsgId2(pMsg2, params.xxMsgIdList.no(i).xxMsgId.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, shpgPlnTMsg.trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, shpgPlnTMsg.trxLineSubNum.getValue());
                    resPMsg2List.add(pMsg2);

                    apiResult = false;
                }
            }
        }

        return apiResult;
    }
    // 2019/11/22 S21_NA#54720 Add End

    // 2019/11/27 QC#52339 Add Start 
    private boolean terminateForSvcMachMstr(NWZC150001PMsg pMsg,  List<SHPG_PLNTMsg> updShpgPlnTMsgAry, List<NWZC150002PMsg> resPMsg2List, ONBATCH_TYPE prmOnBatchType) {

        boolean result = true;

        for (int i = 0; i < updShpgPlnTMsgAry.size(); i++) {
            List<NSZC001001PMsg> machMstrUpdMsgList = new ArrayList<NSZC001001PMsg>();

            SHPG_PLNTMsg shpgPlnTMsg = updShpgPlnTMsgAry.get(i);
            
            if (!NWZC150001Common.isIntangibleItem(shpgPlnTMsg.glblCmpyCd.getValue(), shpgPlnTMsg.mdseCd.getValue())) {
                continue;
            }

            List<Map<String, Object>> svcMachMstrList = getServiceMachineMaster(shpgPlnTMsg);

            if (svcMachMstrList == null || svcMachMstrList.isEmpty()) {
                continue;
            }

            BigDecimal ordQty = BigDecimal.ZERO;
            BigDecimal cancelQty = BigDecimal.ZERO;
            if (SHPG_STS.CANCELLED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                cancelQty = shpgPlnTMsg.ordQty.getValue();
            } else {
                ordQty = shpgPlnTMsg.ordQty.getValue();
                cancelQty = (new BigDecimal(svcMachMstrList.size())).subtract(ordQty);
            }

            if (ordQty.compareTo(new BigDecimal(svcMachMstrList.size())) >= 0) {
                continue;
            }

            int cnt = 0;
            for (int j = 0; j < svcMachMstrList.size(); j++) {
                if (cancelQty.compareTo(new BigDecimal(cnt)) <= 0) {
                    break;
                }
                Map<String, Object> svcMachMstr = svcMachMstrList.get(j);
                NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.slsDt, pMsg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.xxModeCd, ProcessMode.UPDATE_TERMINATION.code);
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, (BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.effThruDt, pMsg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachQty, (BigDecimal) svcMachMstr.get("SVC_MACH_QTY"));
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoOrdNum, shpgPlnTMsg.trxHdrNum.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoDtlLineNum, shpgPlnTMsg.trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoDtlLineSubNum, shpgPlnTMsg.trxLineSubNum.getValue());

                machMstrUpdMsgList.add(machMstrUpdMsg);
                cnt++;
            }

            if (!(machMstrUpdMsgList == null || machMstrUpdMsgList.isEmpty())) {
                NSZC001001 machMstrUpdApi = new NSZC001001();
                machMstrUpdApi.execute(machMstrUpdMsgList, prmOnBatchType);

                for (int j = 0; j < machMstrUpdMsgList.size(); j++) {
                    NSZC001001PMsg machMstrUpdMsg = machMstrUpdMsgList.get(j);
                    if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
                        for (int k = 0; k < machMstrUpdMsg.xxMsgIdList.getValidCount(); k++) {
                            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                            NWZC150001Common.setMsgId2(pMsg2, machMstrUpdMsg.xxMsgIdList.no(k).xxMsgId.getValue());
                            ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, (String) svcMachMstrList.get(0).get("DS_ORD_POSN_NUM"));
                            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, machMstrUpdMsg.cpoDtlLineNum.getValue());
                            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, machMstrUpdMsg.cpoDtlLineSubNum.getValue());
                            resPMsg2List.add(pMsg2);

                            result = false;
                        }
                    }
                }
            }
        }

        return result;
    }

    private List<Map<String, Object>> getServiceMachineMaster(SHPG_PLNTMsg shpgPlnTMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", shpgPlnTMsg.trxHdrNum.getValue());
        queryParam.put("cpoDtlLineNum", shpgPlnTMsg.trxLineNum.getValue());
        queryParam.put("CpoDtlLineSubNum", shpgPlnTMsg.trxLineSubNum.getValue());
        String[] machMstrStsList = {MACH_MSTR_STS.WAITING_FOR_INSTALLATION, MACH_MSTR_STS.INSTALLED, MACH_MSTR_STS.DEALER_SERVICE };
        queryParam.put("machMstrStsList", machMstrStsList);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getServiceMachineMaster", queryParam);
    }
    // 2019/08/15 S21_NA#52620 Add End
}
