/**
 * <pre>
 * Update / Inser HLD Table
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/10   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2022/08/16   CITS            F.Fadriquela    Update          QC#60341
 * 2023/02/20   CITS            F.Fadriquela    Update          QC#61176
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.insert;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.logicalRemove;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.removeByPartialKey;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.update;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.updateSelectionField;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateAPI;
import static java.math.BigDecimal.ZERO;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NORMAL;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouHldBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

public class NWZC150001CpouUpdHld {

    // 2017/06/13 S21_NA#18869-2 Del Start
//    private static final NWZC150001CpouUpdHld MY_INSTANCE = new NWZC150001CpouUpdHld();
    // 2017/06/13 S21_NA#18869-2 Del End

    private S21SsmBatchClient ssmClient = null;

    public NWZC150001CpouUpdHld() {

        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    // 2017/06/13 S21_NA#18869-2 Del Start
//    public static NWZC150001CpouUpdHld getInstance() {
//
//        return MY_INSTANCE;
//    }
    // 2017/06/13 S21_NA#18869-2 Del End

    /**
     * HOLD update
     * 
     * <pre>
     * Logic is deleted for the data judged to be possible Cancel.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param pList List<NWZC150002PMsg>
     * @param removeShpgPlnTMsgList List<SHPG_PLNTMsg>
     * @param releaseHoldList List<HLDTMsg>
     * @param createShpgPlnTMsgList
     */
    @SuppressWarnings("unchecked")
    public void updateHld(NWZC150001CpouBean cpoBean, //
            List<SHPG_PLNTMsg> removeShpgPlnTMsgList, //
            List<HLDTMsg> releaseHoldList, //
            List<SHPG_PLNTMsg> createShpgPlnTMsgList, //
            boolean isPayerChanged) {
        final String methodNm = "updateHld";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            if (removeShpgPlnTMsgList != null && removeShpgPlnTMsgList.size() > 0) {

                Set<String> hldRsnSet = new HashSet<String>();

                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                ssmParam.put("hldTpCdSH", HLD_TP.SALES_HOLD);
                ssmParam.put("hldLvlCdSP", HLD_LVL.SHIPPING_PLAN_LEVEL);
                ssmParam.put("nightBatFlg", ZYPConstant.FLG_OFF_N);
                ssmParam.put("dtmBatFlg", ZYPConstant.FLG_OFF_N);
                ssmParam.put("chkPntOrdVldFlg", ZYPConstant.FLG_OFF_N);

                List<String> createHldRsnList = (List<String>) ssmClient.queryObjectList("getCreateHldRsnList", ssmParam);

                if (createHldRsnList != null && createHldRsnList.size() > 0) {
                    for (String hldRsnCd : createHldRsnList) {
                        hldRsnSet.add(hldRsnCd);
                    }
                }

                for (SHPG_PLNTMsg shpgPlnTMsg : removeShpgPlnTMsgList) {
                    final HLDTMsg reqHldTMsg = new HLDTMsg();
                    reqHldTMsg.setSQLID("021");
                    reqHldTMsg.setConditionValue("glblCmpyCd01", shpgPlnTMsg.glblCmpyCd.getValue());
                    reqHldTMsg.setConditionValue("shpgPlnNum01", shpgPlnTMsg.shpgPlnNum.getValue());
                    final HLDTMsgArray hldTMsgArray = (HLDTMsgArray) findByCondition(reqHldTMsg);
                    for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {

                        if (isUpdateHld(createShpgPlnTMsgList, hldTMsgArray.no(i), hldRsnSet)) {

                            HLDTMsg hldMsg = (HLDTMsg) hldTMsgArray.no(i).clone();
                            hldMsg.shpgPlnNum.setValue(getShpgPlnNum(createShpgPlnTMsgList, hldTMsgArray.no(i)));
                            update(hldMsg);
                        } else {
                            // ***** [logicalRemove]
                            logicalRemove(hldTMsgArray.no(i));
                        }
                    }
                }
            }

            boolean cancelFlg = true;

            for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

                final HLDTMsg reqHldTMsg = new HLDTMsg();
                // START 2022/08/16 F.Fadriquela [QC#60341, MOD]
                // reqHldTMsg.setSQLID("005");
                reqHldTMsg.setSQLID("032");
                // END 2022/08/16 F.Fadriquela [QC#60341, MOD]
                reqHldTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
                reqHldTMsg.setConditionValue("cpoOrdNum01", cpoBean.getCpoOrdNum());
                reqHldTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlBean.getCpoDtlLineNum());
                reqHldTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlBean.getCpoDtlLineSubNum());

                final HLDTMsgArray hldTMsgArray = (HLDTMsgArray) findByCondition(reqHldTMsg);

                // cancel impossible
                if (!NWZC150001Common.isCancelled(cpoDtlBean)) {
                    if (cancelFlg) {
                        cancelFlg = false;
                    }
                    for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {

                        final HLDTMsg hldTMsg = hldTMsgArray.no(i);

                        if (ZYPConstant.FLG_ON_Y.equals(hldTMsg.relFlg.getValue())) {
                            continue;
                        }

                        final BigDecimal hldQty = hldTMsg.hldQty.getValue();
                        BigDecimal ordQty;
                        BigDecimal slsHldQty;
                        BigDecimal crHldQty;

                        // SHPG_PLN_LVL
                        if (hasValue(hldTMsg.shpgPlnNum)) {

                            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                            setValue(shpgPlnTMsg.glblCmpyCd, hldTMsg.glblCmpyCd);
                            setValue(shpgPlnTMsg.shpgPlnNum, hldTMsg.shpgPlnNum);
                            shpgPlnTMsg = (SHPG_PLNTMsg) findByKey(shpgPlnTMsg);

                            ordQty = shpgPlnTMsg.ordQty.getValue();

                            slsHldQty = shpgPlnTMsg.slsHldQty.getValue();
                            crHldQty = shpgPlnTMsg.crHldQty.getValue();

                            if (BigDecimal.ZERO.compareTo(slsHldQty) != 0) {
                                shpgPlnTMsg.slsHldQty.setValue(ordQty);
                            }
                            if (BigDecimal.ZERO.compareTo(crHldQty) != 0) {
                                shpgPlnTMsg.crHldQty.setValue(ordQty);
                            }

                            if (slsHldQty.compareTo(shpgPlnTMsg.slsHldQty.getValue()) != 0 || crHldQty.compareTo(shpgPlnTMsg.crHldQty.getValue()) != 0) {

                                update(shpgPlnTMsg);
                            }

                            // CPO_DTL_LVL
                        } else if (hasValue(hldTMsg.cpoDtlLineNum)) {

                            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                            shpgPlnTMsg.setSQLID("001");
                            shpgPlnTMsg.setConditionValue("glblCmpyCd01", hldTMsg.glblCmpyCd.getValue());
                            shpgPlnTMsg.setConditionValue("trxHdrNum01", hldTMsg.cpoOrdNum.getValue());
                            shpgPlnTMsg.setConditionValue("trxLineNum01", hldTMsg.cpoDtlLineNum.getValue());
                            shpgPlnTMsg.setConditionValue("trxLineSubNum01", hldTMsg.cpoDtlLineSubNum.getValue());
                            SHPG_PLNTMsgArray shpgPlnTMsgs = (SHPG_PLNTMsgArray) findByCondition(shpgPlnTMsg);

                            for (int j = 0; j < shpgPlnTMsgs.getValidCount(); j++) {

                                shpgPlnTMsg = shpgPlnTMsgs.no(j);

                                ordQty = shpgPlnTMsg.ordQty.getValue();

                                slsHldQty = shpgPlnTMsg.slsHldQty.getValue();

                                shpgPlnTMsg.slsHldQty.setValue(ordQty);

                                if (slsHldQty.compareTo(shpgPlnTMsg.slsHldQty.getValue()) != 0) {

                                    update(shpgPlnTMsg);
                                }
                            }

                            ordQty = cpoDtlBean.getOrdQty();

                        } else {
                            continue;
                        }

                        if (!NWZC150001Common.nullToZero(hldQty).equals(NWZC150001Common.nullToZero(ordQty))) {
                            // ***** [update]
                            setValue(hldTMsg.hldQty, ordQty);
                            update(hldTMsg);
                        }
                    }
                    continue;
                }

                for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {
                    // ***** [logicalRemove]
                    // START 2023/02/20 F.Fadriquela [QC#61176, MOD]
                    //logicalRemove(hldTMsgArray.no(i));
                    if (ZYPConstant.FLG_OFF_N.equals(hldTMsgArray.no(i).relFlg.getValue())) {
                        logicalRemove(hldTMsgArray.no(i));
                    }
                    // END 2023/02/20 F.Fadriquela [QC#61176, MOD]
                }
            }

            // if (!cancelFlg) {
            // // Because PAYER can change after SUBMIT, HLD(Floor
            // Plan) clear.
            // final String rqstTpCd = cpoBean.getRqstTpCd();
            // if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {
            // final HLDTMsg reqHldTMsg = new HLDTMsg();
            // reqHldTMsg.setSQLID("008");
            // reqHldTMsg.setConditionValue("glblCmpyCd01",
            // cpoBean.getGlblCmpyCd());
            // reqHldTMsg.setConditionValue("cpoOrdNum01",
            // cpoBean.getCpoOrdNum());
            // reqHldTMsg.setConditionValue("hldRsnCd01",
            // HLD_RSN.FLOOR_PLAN);
            // final HLDTMsgArray hldTMsgArray = (HLDTMsgArray)
            // findByCondition(reqHldTMsg);
            // for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {
            // // ***** [logicalRemove]
            // logicalRemove(hldTMsgArray.no(i));
            // releaseHoldList.add(hldTMsgArray.no(i));
            // }
            // }
            // return;
            // }

            // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor
            // Plan Order] - START
            if (!cancelFlg) {
                if (isPayerChanged) {

                    NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(), " #isPayerChanged=[" + isPayerChanged + "]");

                    final String glblCmpyCd = cpoBean.getGlblCmpyCd();
                    final String cpoOrdNum = cpoBean.getCpoOrdNum();

                    // 'SHPG_PLN_NUM' set.
                    final Set<String> shpgPlnNumSet = new HashSet<String>();

                    // --------------------------------------------------
                    // delete all 'Credit' holds.
                    // --------------------------------------------------
                    final Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("cpoOrdNum", cpoOrdNum);
                    ssmParam.put("creditHoldTp", HLD_TP.CREDIT_HOLD);

                    final List<Map> crHldList = ssmClient.queryObjectList("getCreditHldList", ssmParam);

                    for (Map crHld : crHldList) {

                        if (HLD_LVL.SHIPPING_PLAN_LEVEL.equals((String) crHld.get("HLD_LVL_CD"))) {

                            // [HLD]
                            final HLDTMsg hldTMsg = new HLDTMsg();
                            setValue(hldTMsg.glblCmpyCd, glblCmpyCd);
                            setValue(hldTMsg.hldPk, (BigDecimal) crHld.get("HLD_PK"));
                            // ***** [logicalRemove]
                            logicalRemove(hldTMsg);
                            releaseHoldList.add(hldTMsg);

                            shpgPlnNumSet.add((String) crHld.get("SHPG_PLN_NUM"));
                        }
                    }

                    // --------------------------------------------------
                    // get 'Validated' and 'Hard Allocated' shpg plns.
                    // --------------------------------------------------
                    ssmParam.clear();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("trxHdrNum", cpoOrdNum);
                    final Set<String> shpgStsSet = new LinkedHashSet<String>();
                    shpgStsSet.add(SHPG_STS.VALIDATED);
                    shpgStsSet.add(SHPG_STS.HARD_ALLOCATED);
                    ssmParam.put("shpgStsSet", shpgStsSet);

                    shpgPlnNumSet.addAll(ssmClient.queryObjectList("getShpgPlnNumList", ssmParam));

                    // --------------------------------------------------
                    // update 'SHPG_PLN.CR_CHK_QTY' to ZERO.
                    // --------------------------------------------------
                    for (String shpgPlnNum : shpgPlnNumSet) {

                        // [SHPG_PLN]
                        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
                        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

                        if (shpgPlnTMsg != null) {

                            // CR_CHK_QTY => 0
                            final BigDecimal crChkQty = shpgPlnTMsg.crChkQty.getValue();
                            setValue(shpgPlnTMsg.crChkQty, ZERO);
                            // Def#1481
                            final BigDecimal crHldQty = shpgPlnTMsg.crHldQty.getValue();
                            setValue(shpgPlnTMsg.crHldQty, ZERO);

                            // 'SHIP_PLN_HLD_FLG', 'CR_HLD_QTY',
                            // 'AVAL_SO_QTY' and 'AVAL_PO_QTY'
                            // will be updated by
                            // NWZC004001:Validation Process Manager
                            // API's Recalculation.

                            // ***** [updateSelectionField]
                            // Def#1481
                            // NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(),
                            // "  - shpgPlnNum=[" + shpgPlnNum +
                            // "] : crChkQty=[" + crChkQty + "] => ["
                            // + shpgPlnTMsg.crChkQty.getValue() +
                            // "]");
                            // updateSelectionField(shpgPlnTMsg, new
                            // String[] {"crChkQty"});
                            NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(), "  - shpgPlnNum=[" + shpgPlnNum + "] : crChkQty=[" + crChkQty + "] => [" + shpgPlnTMsg.crChkQty.getValue() + "],crHldQty=[" + crHldQty + "] => ["
                                    + shpgPlnTMsg.crHldQty.getValue() + "]");
                            updateSelectionField(shpgPlnTMsg, new String[] {"crChkQty", "crHldQty" });
                        }
                    }

                    if (isNullOrEmpty(releaseHoldList)) {
                        releaseHoldList.add(new HLDTMsg()); // set
                        // dummy
                        // HLDTMsg
                        // to
                        // execute
                        // NWZC004001:Validation
                        // Process
                        // Manager
                        // API's
                        // Recalculation.
                    }
                    return;
                }
            }
            // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor
            // Plan Order] - E N D

            final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
            reqCpoDtlTMsg.setSQLID("001");
            reqCpoDtlTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            reqCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoBean.getCpoOrdNum());
            final CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) findByCondition(reqCpoDtlTMsg);

            boolean orderCancel = true;
            for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
                String ordLineStsCd = cpoDtlTMsgArray.no(i).ordLineStsCd.getValue();
                // START 2023/02/20 F.Fadriquela [QC#61176, MOD]
                //if (!ORD_LINE_STS.CLOSED.equals(ordLineStsCd) && !ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
                if (!ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
                // END 2023/02/20 F.Fadriquela [QC#61176, MOD]
                    orderCancel = false;
                    break;
                }
            }

            if (!orderCancel) {
                return;
            }

            final HLDTMsg reqHldTMsg = new HLDTMsg();
            // START 2022/08/16 F.Fadriquela [QC#60341, MOD]
            //reqHldTMsg.setSQLID("004");
            reqHldTMsg.setSQLID("033");
            // END 2022/08/16 F.Fadriquela [QC#60341, MOD]
            reqHldTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            reqHldTMsg.setConditionValue("cpoOrdNum01", cpoBean.getCpoOrdNum());
            final HLDTMsgArray hldTMsgArray = (HLDTMsgArray) findByCondition(reqHldTMsg);
            for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {
                // ***** [logicalRemove]
                // START 2023/02/20 F.Fadriquela [QC#61176, MOD]
                //logicalRemove(hldTMsgArray.no(i));
                if (ZYPConstant.FLG_OFF_N.equals(hldTMsgArray.no(i).relFlg.getValue())) {
                    logicalRemove(hldTMsgArray.no(i));
                }
                // END 2023/02/20 F.Fadriquela [QC#61176, MOD]
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    /**
     * HLD update
     * @param cpoBean NWZC150001CpouBean
     */
    public void updateHld(NWZC150001CpouBean cpouBean) {
        // 1.2WDS add start -->
        String rqstTpCd = cpouBean.getRqstTpCd();

        // 20121217 Defect#27 M.Fuji Strat
        // update save data to submit Data
        if (NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd) && hasValue(cpouBean.getCpoOrdNum())) {
            HLDTMsg delDtlHldTMsg = new HLDTMsg();
            delDtlHldTMsg.setSQLID("004");
            delDtlHldTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
            delDtlHldTMsg.setConditionValue("cpoOrdNum01", cpouBean.getCpoOrdNum());

            HLDTMsgArray hldTMsgArray = (HLDTMsgArray) findByConditionForUpdate(delDtlHldTMsg);

            for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {
                HLDTMsg updateHldTMsg = hldTMsgArray.no(i);
                updateHldTMsg.cpoOrdNum.setValue(NWZC150001Common.getCpoOrdNumFromBean(cpouBean));

                BigDecimal ordQty = updateHldTMsg.hldQty.getValue();
                for (NWZC150001CpouDetailBean cpoDtlBean : cpouBean.getDtlBeanList()) {
                    Boolean isChangeHldQtyFlg = isChangeHldQty(updateHldTMsg, cpoDtlBean);

                    if (isChangeHldQtyFlg) {
                        ordQty = cpoDtlBean.getOrdQty();
                    }
                }
                updateHldTMsg.hldQty.setValue(ordQty);

                // ***** [remove]
                update(updateHldTMsg);
                // 20131018 Defect#2762 N.Sugiura Start
                printBizProcLog(updateHldTMsg);
                // 20131018 Defect#2762 N.Sugiura End
            }
        }
        // 20121217 Defect#27 M.Fuji End

        if ((NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd) //
                || NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd)) //
                || NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {
            // 2017/03/31 S21_NA#Review structure Lv.2 Mod Start
//            for (int i = 0; i < cpoBean.getDwzc001001HoldListPMsgArray().getValidCount(); i++) {
//
//                NWZC150001_holdListPMsg dwzc001001HoldListPMsg = (NWZC150001_holdListPMsg) cpoBean.getDwzc001001HoldListPMsgArray().no(i);
//                HLDTMsg reqHldTMsg = new HLDTMsg();
//
//                if (NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(dwzc001001HoldListPMsg.xxRqstTpCd.getValue())) {
//
//                    setHldTMsgForNew(cpoBean, dwzc001001HoldListPMsg, reqHldTMsg);
//                    // ***** [insert]
//                    insert(reqHldTMsg);
//                    if (!RTNCD_NORMAL.equals(reqHldTMsg.getReturnCode())) {
//                        throw new S21AbendException(NWZC150001CpouConstant.NWZM1023E, new String[] {reqHldTMsg.getTableName(), reqHldTMsg.hldPk.getValue().toString() });
//                    }
//                } else if (NWZC150001CpouConstant.REQUEST_TYPE_MODIFY.equals(dwzc001001HoldListPMsg.xxRqstTpCd.getValue())) {
//
//                    setValue(reqHldTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
//                    setValue(reqHldTMsg.hldPk, dwzc001001HoldListPMsg.hldPk);
//                    reqHldTMsg = (HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(reqHldTMsg);
//
//                    setHldTMsgForModify(cpoBean, dwzc001001HoldListPMsg, reqHldTMsg);
//                    // ***** [update]
//                    update(reqHldTMsg);
//                    if (!RTNCD_NORMAL.equals(reqHldTMsg.getReturnCode())) {
//                        throw new S21AbendException(NWZC150001CpouConstant.NWZM1024E, new String[] {reqHldTMsg.getTableName(), reqHldTMsg.hldPk.getValue().toString() });
//                    }
//                } else if (NWZC150001CpouConstant.REQUEST_TYPE_DELETE.equals(dwzc001001HoldListPMsg.xxRqstTpCd.getValue())) {
//
//                    setValue(reqHldTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
//                    setValue(reqHldTMsg.hldPk, dwzc001001HoldListPMsg.hldPk);
//                    reqHldTMsg = (HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(reqHldTMsg);
//
//                    // ***** [delete]
//                    removeByPartialKey(reqHldTMsg);
//                    if (!RTNCD_NORMAL.equals(reqHldTMsg.getReturnCode())) {
//                        throw new S21AbendException(NWZC150001CpouConstant.NWZM1025E, new String[] {reqHldTMsg.getTableName(), reqHldTMsg.hldPk.getValue().toString() });
//                    }
//                }
//                // 20131018 Defect#2762 N.Sugiura Start
//                if (!NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd)) {
//                    printBizProcLog(reqHldTMsg);
//                }
//                // 20131018 Defect#2762 N.Sugiura End
//            }
            for (NWZC150001CpouHldBean cpouHldBean : cpouBean.getHldBeanList()) {

                HLDTMsg reqHldTMsg = new HLDTMsg();

                if (NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(cpouHldBean.getXxRqstTpCd())) {

                    setHldTMsgForNew(cpouBean, cpouHldBean, reqHldTMsg);
                    // ***** [insert]
                    insert(reqHldTMsg);
                    if (!RTNCD_NORMAL.equals(reqHldTMsg.getReturnCode())) {
                        throw new S21AbendException(NWZC150001CpouConstant.NWZM1023E, new String[] {reqHldTMsg.getTableName(), reqHldTMsg.hldPk.getValue().toString() });
                    }
                } else if (NWZC150001CpouConstant.REQUEST_TYPE_MODIFY.equals(cpouHldBean.getXxRqstTpCd())) {

                    setValue(reqHldTMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                    setValue(reqHldTMsg.hldPk, cpouHldBean.getHldPk());
                    reqHldTMsg = (HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(reqHldTMsg);

                    setHldTMsgForModify(cpouBean, cpouHldBean, reqHldTMsg);
                    // ***** [update]
                    update(reqHldTMsg);
                    if (!RTNCD_NORMAL.equals(reqHldTMsg.getReturnCode())) {
                        throw new S21AbendException(NWZC150001CpouConstant.NWZM1024E, new String[] {reqHldTMsg.getTableName(), reqHldTMsg.hldPk.getValue().toString() });
                    }
                } else if (NWZC150001CpouConstant.REQUEST_TYPE_DELETE.equals(cpouHldBean.getXxRqstTpCd())) {

                    setValue(reqHldTMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                    setValue(reqHldTMsg.hldPk, cpouHldBean.getHldPk());
                    reqHldTMsg = (HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(reqHldTMsg);

                    // ***** [delete]
                    removeByPartialKey(reqHldTMsg);
                    if (!RTNCD_NORMAL.equals(reqHldTMsg.getReturnCode())) {
                        throw new S21AbendException(NWZC150001CpouConstant.NWZM1025E, new String[] {reqHldTMsg.getTableName(), reqHldTMsg.hldPk.getValue().toString() });
                    }
                }
                // 20131018 Defect#2762 N.Sugiura Start
                if (!NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd)) {
                    printBizProcLog(reqHldTMsg);
                }
                // 20131018 Defect#2762 N.Sugiura End
            }
            // 2017/03/31 S21_NA#Review structure Lv.2 Mod End
        }
        // 1.2WDS add end <--
    }

    private Boolean isChangeHldQty(HLDTMsg updateHldTMsg, NWZC150001CpouDetailBean cpoDtlBean) {

        Boolean isChangeHldQtyFlg = false;

        String hldOrdLineNum = updateHldTMsg.cpoDtlLineNum.getValue();
        String hldOrdLineSubNum = updateHldTMsg.cpoDtlLineSubNum.getValue();
        BigDecimal hldQty = updateHldTMsg.hldQty.getValue();

        String parmOrdLineNum = cpoDtlBean.getCpoDtlLineNum();
        String parmOrdLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();
        BigDecimal parmOrdQty = cpoDtlBean.getOrdQty();

        if (parmOrdLineNum.equals(hldOrdLineNum) && parmOrdLineSubNum.equals(hldOrdLineSubNum)) {
            if (hldQty.compareTo(parmOrdQty) != 0) {
                isChangeHldQtyFlg = true;
            }
        }

        return isChangeHldQtyFlg;
    }

    private String getShpgPlnNum(List<SHPG_PLNTMsg> createShpgPlnTMsgList, HLDTMsg hldMsg) {

        String cpoDtlLineNum = hldMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = hldMsg.cpoDtlLineSubNum.getValue();

        for (SHPG_PLNTMsg shpgPlnTMsg : createShpgPlnTMsgList) {

            String trxLineNum = shpgPlnTMsg.trxLineNum.getValue();
            String trxLineSubNum = shpgPlnTMsg.trxLineSubNum.getValue();
            String shpgPlnNum = shpgPlnTMsg.shpgPlnNum.getValue();

            if (cpoDtlLineNum.equals(trxLineNum) && cpoDtlLineSubNum.equals(trxLineSubNum)) {
                return shpgPlnNum;
            }
        }

        return null;
    }

    private boolean isUpdateHld(List<SHPG_PLNTMsg> createShpgPlnTMsgList, HLDTMsg hldMsg, Set<String> hldRsnSet) {

        String cpoDtlLineNum = hldMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = hldMsg.cpoDtlLineSubNum.getValue();
        String relFlg = hldMsg.relFlg.getValue();
        String hldRsnCd = hldMsg.hldRsnCd.getValue();

        if (hldRsnSet == null) {
            return false;
        } else if (!hldRsnSet.contains(hldRsnCd)) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(relFlg)) {
            return false;
        }

        for (SHPG_PLNTMsg shpgPlnTMsg : createShpgPlnTMsgList) {

            String trxLineNum = shpgPlnTMsg.trxLineNum.getValue();
            String trxLineSubNum = shpgPlnTMsg.trxLineSubNum.getValue();

            if (cpoDtlLineNum.equals(trxLineNum) && cpoDtlLineSubNum.equals(trxLineSubNum)) {
                return true;
            }
        }
        return false;
    }


    private boolean isNullOrEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

//  private void setHldTMsgForNew(NWZC150001CpouBean cpoBean, NWZC150001_holdListPMsg dwzc001001HoldListPMsg, HLDTMsg hldTMsg) {
    private void setHldTMsgForNew(NWZC150001CpouBean cpoBean, NWZC150001CpouHldBean cpouHldBean, HLDTMsg hldTMsg) {
        // 1.2WDS add start -->
        setValue(hldTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        setValue(hldTMsg.hldPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
        setValue(hldTMsg.cpoOrdNum, NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        setValue(hldTMsg.cpoDtlLineNum, cpouHldBean.getCpoDtlLineNum());
        setValue(hldTMsg.cpoDtlLineSubNum, cpouHldBean.getCpoDtlLineSubNum());
        setValue(hldTMsg.hldRsnCd, cpouHldBean.getHldRsnCd());
        setValue(hldTMsg.hldDt, cpoBean.getSlsDt());
        for (NWZC150001CpouDetailBean bean : cpoBean.getDtlBeanList()) {
            if (hasValue(hldTMsg.cpoDtlLineNum) && hldTMsg.cpoDtlLineNum.getValue().equals(bean.getCpoDtlLineNum()) && hasValue(hldTMsg.cpoDtlLineSubNum) && hldTMsg.cpoDtlLineSubNum.getValue().equals(bean.getCpoDtlLineSubNum())) {
                setValue(hldTMsg.hldQty, bean.getOrdQty());
                break;
            }
        }

        setValue(hldTMsg.hldDtlTxt, cpouHldBean.getHldDtlTxt());
        setValue(hldTMsg.relFlg, ZYPConstant.FLG_OFF_N);
        setValue(hldTMsg.rvwFlg, ZYPConstant.FLG_OFF_N);
        // 1.2WDS add end <--
    }

//  private void setHldTMsgForModify(NWZC150001CpouBean cpoBean, NWZC150001_holdListPMsg dwzc001001HoldListPMsg, HLDTMsg hldTMsg) {
    private void setHldTMsgForModify(NWZC150001CpouBean cpoBean, NWZC150001CpouHldBean cpouHldBean, HLDTMsg hldTMsg) {
        // 1.2WDS add start -->
        setValue(hldTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        setValue(hldTMsg.hldPk, cpouHldBean.getHldPk());
        setValue(hldTMsg.cpoOrdNum, NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        setValue(hldTMsg.hldDtlTxt, cpouHldBean.getHldDtlTxt());
        // 1.2WDS add end <--
    }

    // 20131018 Defect#2762 N.Sugiura Start
    private void printBizProcLog(HLDTMsg param) {

        S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();

        // SubSysId
        bizLogMsg.setSubSysId(NWZC150001CpouConstant.SUB_SYS_ID);
        // ProcId
        bizLogMsg.setProcId(NWZC150001CpouConstant.PROCESS_ID);
        // EventId
        bizLogMsg.setEventId(NWZC150001CpouConstant.EVENT_ID);
        // DocTpCd
        bizLogMsg.setDocTpCd(NWZC150001CpouConstant.DOCUMENT_TYPE);
        // DocId
        // set HLD_LVL_CD
        String hldLvlCd = null;
        if (hasValue(param.cpoOrdNum) && !hasValue(param.cpoDtlLineNum) && !hasValue(param.cpoDtlLineSubNum) && !hasValue(param.shpgPlnNum)) {
            hldLvlCd = HLD_LVL.CPO_LEVEL;
        } else if (hasValue(param.cpoOrdNum) && hasValue(param.cpoDtlLineNum) && hasValue(param.cpoDtlLineSubNum) && !hasValue(param.shpgPlnNum)) {
            hldLvlCd = HLD_LVL.CPO_DETAIL_LEVEL;
        } else if (hasValue(param.cpoOrdNum) && hasValue(param.cpoDtlLineNum) && hasValue(param.cpoDtlLineSubNum) && hasValue(param.shpgPlnNum)) {
            hldLvlCd = HLD_LVL.SHIPPING_PLAN_LEVEL;
        }

        if (HLD_LVL.CPO_DETAIL_LEVEL.equals(hldLvlCd) || HLD_LVL.SHIPPING_PLAN_LEVEL.equals(hldLvlCd)) {
            String docId = ZYPCommonFunc.concatString(param.cpoDtlLineNum.getValue(), ".", param.cpoDtlLineSubNum.getValue());
            bizLogMsg.setDocId(docId);
        }
        // PrntDocId
        bizLogMsg.setPrntDocId(param.cpoOrdNum.getValue());
        // CmntTxt_02
        bizLogMsg.setBizProcCmntTxt_02(param.hldRsnCd.getValue());

        S21BusinessProcessLog.print(bizLogMsg);
    }
    // 20131018 Defect#2762 N.Sugiura End
}
