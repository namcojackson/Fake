/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC218001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateAPI;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.SHPG_PLNTMsg;
import business.parts.NWZC218001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * NWZC2180:Shipping Purchase Order Request API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/06/2013   Fujitsu         K.Shibuya       Create          N/A
 * 10/03/2013   Fujitsu         S.Yamamoto      Update          QC#2484
 * 10/09/2013   Fujitsu         S.Yamamoto      Update          QC#2565
 * 10/17/2013   Fujitsu         D.Yanagisawa    Update          QC#2565(Reopen)
 * 12/10/2013   Fujitsu         T.Nakamura      Update          S21WDS Defect#2957
 * 2016/03/24   Fujitsu         S.Takami        Update          S21_NA#5885 (Del Calling updateTrxCd()
 * 2016/09/20   Fujitsu         Y.Kanefusa      Update          S21_NA#11306
 */
public class NWZC218001 extends S21ApiCommonBase {

    /** Global Company Code is required. */
    public static final String NWZM0188E = "NWZM0188E";

    /** "Sales Date" for the entered parameter is not set. */
    public static final String NWZM0978E = "NWZM0978E";

    /** CPO Order Number for the parameter is not set. */
    public static final String NWZM1205E = "NWZM1205E";

    /** It failed to update the SHPG_PLN. */
    public static final String NWZM0078E = "NWZM0078E";

    /** SSM Client */
    private final S21SsmBatchClient ssmClient;

    /**
     * Constructor
     */
    public NWZC218001() {

        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute for List
     * @param params List<NWZC218001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWZC218001PMsg> params, ONBATCH_TYPE onBatchType) {

        for (NWZC218001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * Execute
     * @param param NWZC218001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWZC218001PMsg param, ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (!checkInput(msgMap)) {
            msgMap.flush();
            return;
        }

        // main
        doProcess(msgMap);
        // end
        msgMap.flush();
    }

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        NWZC218001PMsg param = (NWZC218001PMsg) msgMap.getPmsg();
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0978E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM1205E);
            return false;
        }

        return true;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        NWZC218001PMsg param = (NWZC218001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String cpoOrdNum = param.cpoOrdNum.getValue();

        List<Map<String, Object>> shpgPlnList = getShpgPlnList(param, glblCmpyCd, cpoOrdNum);
        List<SHPG_PLNTMsg> updateShpgPlnList = new ArrayList<SHPG_PLNTMsg>();

        for (Map<String, Object> shpgPlnMap : shpgPlnList) {
            String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");
            String shpgStsCd = (String) shpgPlnMap.get("SHPG_STS_CD");
            String shipCpltCd = (String) shpgPlnMap.get("SHIP_CPLT_CD");
            String setShpgPlnNum = (String) shpgPlnMap.get("SET_SHPG_PLN_NUM");

            if (existShpgPln(shpgPlnNum, updateShpgPlnList)) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals((String) shpgPlnMap.get("PO_REQ_FLG"))) {
                // Target of PO

                if (SHPG_STS.VALIDATED.equals(shpgStsCd)) {
                    if (ZYPCommonFunc.hasValue(shipCpltCd)) {

                        updateSPHldInSameScPO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
                        continue;
                    }

                    if (ZYPCommonFunc.hasValue(setShpgPlnNum)) {

                        updateSPHldInSameSetPO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
                        continue;
                    }

                    if (!ZYPCommonFunc.hasValue(setShpgPlnNum)) {

                        updateSPHldRgPO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
                        continue;
                    }
                }
                updateSPOthrPO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
            } else {
                // Target of SO

                if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd)) {
                    if (ZYPCommonFunc.hasValue(shipCpltCd)) {

                        updateSPHldInSameScSO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
                        continue;
                    }

                    if (ZYPCommonFunc.hasValue(setShpgPlnNum)) {

                        updateSPHldInSameSetSO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
                        continue;
                    }

                    if (!ZYPCommonFunc.hasValue(setShpgPlnNum)) {

                        updateSPHldRgSO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
                        continue;
                    }
                }
                updateSPOthrSO(glblCmpyCd, shpgPlnMap, updateShpgPlnList);
            }
        }

        if (!updateShpgPlnList.isEmpty() && S21FastTBLAccessor.update(updateShpgPlnList.toArray(new SHPG_PLNTMsg[updateShpgPlnList.size()])) <= 0) {
            msgMap.addXxMsgId(NWZM0078E);
        }
    }

    private List<Map<String, Object>> getShpgPlnList(NWZC218001PMsg param, String glblCmpyCd, String cpoOrdNum) {

        Map<String, Object> ssmParamShpgPln = new HashMap<String, Object>();
        ssmParamShpgPln.put("glblCmpyCd", glblCmpyCd);
        ssmParamShpgPln.put("trxHdrNum", cpoOrdNum);
        ssmParamShpgPln.put("shpgStsCd10", SHPG_STS.VALIDATED);
        ssmParamShpgPln.put("shpgStsCd25", SHPG_STS.HARD_ALLOCATED);
        ssmParamShpgPln.put("mdseTpCd2", MDSE_TP.SET);
        ssmParamShpgPln.put("mdseTpCd5", MDSE_TP.SALES_BOM);
        ssmParamShpgPln.put("trxSrcTp010", TRX_SRC_TP.WHOLE_SALES);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getShpgPln", ssmParamShpgPln);
    }

    private void updateSPHldInSameScPO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");
        String trxHdrNum = (String) shpgPlnMap.get("TRX_HDR_NUM");
        String shipCpltCd = (String) shpgPlnMap.get("SHIP_CPLT_CD");
        String trxSrcTpCd = (String) shpgPlnMap.get("TRX_SRC_TP_CD");

        Map<String, Object> ssmParamHldInSameSC = new HashMap<String, Object>();
        ssmParamHldInSameSC.put("glblCmpyCd", glblCmpyCd);
        ssmParamHldInSameSC.put("shipCpltCd", shipCpltCd);
        ssmParamHldInSameSC.put("trxHdrNum", trxHdrNum);
        ssmParamHldInSameSC.put("trxSrcTpCd", trxSrcTpCd);
        ssmParamHldInSameSC.put("hldLvlCd01", HLD_LVL.CPO_LEVEL);
        ssmParamHldInSameSC.put("hldLvlCd02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParamHldInSameSC.put("hldLvlCd03", HLD_LVL.SHIPPING_PLAN_LEVEL);

        BigDecimal hldCnt = (BigDecimal) ssmClient.queryObject("cntHldInSameSC", ssmParamHldInSameSC);

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (hldCnt.intValue() == 0) {
            if (shpgPlnTMsg.avalPoQty.getValueInt() == 0) {
                // START ADD S.Yamamoto [QC#2484]
                if (shpgPlnTMsg.crChkQty.getValueInt() > 0) {
                // END   ADD S.Yamamoto [QC#2484]
                    ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalPoQty, shpgPlnTMsg.ordQty);
                    updateShpgPlnList.add(shpgPlnTMsg);
                }
            }
        } else {
            if (shpgPlnTMsg.avalPoQty.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalPoQty, ZERO);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        }
    }

    private void updateSPHldInSameSetPO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");
        String trxHdrNum = (String) shpgPlnMap.get("TRX_HDR_NUM");
        String trxLineNum = (String) shpgPlnMap.get("TRX_LINE_NUM");
        String setShpgPlnNum = (String) shpgPlnMap.get("SET_SHPG_PLN_NUM");
        String trxSrcTpCd = (String) shpgPlnMap.get("TRX_SRC_TP_CD");

        Map<String, Object> ssmParamHldInSameSet = new HashMap<String, Object>();
        ssmParamHldInSameSet.put("glblCmpyCd", glblCmpyCd);
        ssmParamHldInSameSet.put("trxHdrNum", trxHdrNum);
        ssmParamHldInSameSet.put("trxLineNum", trxLineNum);
        ssmParamHldInSameSet.put("setShpgPlnNum", setShpgPlnNum);
        ssmParamHldInSameSet.put("trxSrcTpCd", trxSrcTpCd);
        ssmParamHldInSameSet.put("hldLvlCd01", HLD_LVL.CPO_LEVEL);
        ssmParamHldInSameSet.put("hldLvlCd02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParamHldInSameSet.put("hldLvlCd03", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParamHldInSameSet.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN);  // QC#11306 2016/09/20 Add

        BigDecimal hldCnt = (BigDecimal) ssmClient.queryObject("cntHldInSameSet", ssmParamHldInSameSet);

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (hldCnt.intValue() == 0) {
            if (shpgPlnTMsg.avalPoQty.getValueInt() == 0) {
                // START ADD S.Yamamoto [QC#2484]
                if (shpgPlnTMsg.crChkQty.getValueInt() > 0) {
                // END   ADD S.Yamamoto [QC#2484]
                    ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalPoQty, shpgPlnTMsg.ordQty);
                    updateShpgPlnList.add(shpgPlnTMsg);
                }
            }
        } else {
            if (shpgPlnTMsg.avalPoQty.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalPoQty, ZERO);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        }
    }

    private void updateSPHldRgPO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");
        String trxHdrNum = (String) shpgPlnMap.get("TRX_HDR_NUM");
        String trxLineNum = (String) shpgPlnMap.get("TRX_LINE_NUM");
        String trxLineSubNum = (String) shpgPlnMap.get("TRX_LINE_SUB_NUM");
        String trxSrcTpCd = (String) shpgPlnMap.get("TRX_SRC_TP_CD");

        Map<String, Object> ssmParamHldRg = new HashMap<String, Object>();
        ssmParamHldRg.put("glblCmpyCd", glblCmpyCd);
        ssmParamHldRg.put("trxHdrNum", trxHdrNum);
        ssmParamHldRg.put("trxLineNum", trxLineNum);
        ssmParamHldRg.put("trxLineSubNum", trxLineSubNum);
        ssmParamHldRg.put("trxSrcTpCd", trxSrcTpCd);
        ssmParamHldRg.put("hldLvlCd01", HLD_LVL.CPO_LEVEL);
        ssmParamHldRg.put("hldLvlCd02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParamHldRg.put("hldLvlCd03", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParamHldRg.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN);  // QC#11306 2016/09/20 Add

        BigDecimal hldCnt = (BigDecimal) ssmClient.queryObject("cntHldRg", ssmParamHldRg);

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (hldCnt.intValue() == 0) {
            if (shpgPlnTMsg.avalPoQty.getValueInt() == 0) {
                // START ADD S.Yamamoto [QC#2484]
                if (shpgPlnTMsg.crChkQty.getValueInt() > 0) {
                // END   ADD S.Yamamoto [QC#2484]
                    ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalPoQty, shpgPlnTMsg.ordQty);
                    updateShpgPlnList.add(shpgPlnTMsg);
                }
            }
        } else {
            if (shpgPlnTMsg.avalPoQty.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalPoQty, ZERO);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        }
    }

    private void updateSPOthrPO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (shpgPlnTMsg.avalPoQty.getValueInt() != 0) {
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalPoQty, ZERO);
            updateShpgPlnList.add(shpgPlnTMsg);
        }
    }

    private void updateSPHldInSameScSO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");
        String trxHdrNum = (String) shpgPlnMap.get("TRX_HDR_NUM");
        String shipCpltCd = (String) shpgPlnMap.get("SHIP_CPLT_CD");
        String trxSrcTpCd = (String) shpgPlnMap.get("TRX_SRC_TP_CD");

        Map<String, Object> ssmParamHldInSameSC = new HashMap<String, Object>();
        ssmParamHldInSameSC.put("glblCmpyCd", glblCmpyCd);
        ssmParamHldInSameSC.put("shipCpltCd", shipCpltCd);
        ssmParamHldInSameSC.put("trxHdrNum", trxHdrNum);
        ssmParamHldInSameSC.put("trxSrcTpCd", trxSrcTpCd);
        ssmParamHldInSameSC.put("hldLvlCd01", HLD_LVL.CPO_LEVEL);
        ssmParamHldInSameSC.put("hldLvlCd02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParamHldInSameSC.put("hldLvlCd03", HLD_LVL.SHIPPING_PLAN_LEVEL);

        BigDecimal hldCnt = (BigDecimal) ssmClient.queryObject("cntHldInSameSC", ssmParamHldInSameSC);

        Map<String, Object> ssmParamShpgPlnInSameSC = new HashMap<String, Object>();
        ssmParamShpgPlnInSameSC.put("glblCmpyCd", glblCmpyCd);
        ssmParamShpgPlnInSameSC.put("shipCpltCd", shipCpltCd);
        ssmParamShpgPlnInSameSC.put("trxHdrNum", trxHdrNum);
        ssmParamShpgPlnInSameSC.put("shpgStsCd10", SHPG_STS.VALIDATED);

        BigDecimal shpgPlnCnt = (BigDecimal) ssmClient.queryObject("cntShpgPlnInSameSC", ssmParamShpgPlnInSameSC);

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (hldCnt.intValue() == 0 && shpgPlnCnt.intValue() == 0) {
            if (shpgPlnTMsg.avalSoQty.getValueInt() == 0) {
                // updateTrxCd(glblCmpyCd, shpgPlnTMsg); 2016/03/24 S21_NA#5885 Del
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalSoQty, shpgPlnTMsg.ordQty);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        } else {
            if (shpgPlnTMsg.avalSoQty.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalSoQty, ZERO);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        }
    }

    private void updateSPHldInSameSetSO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");
        String trxHdrNum = (String) shpgPlnMap.get("TRX_HDR_NUM");
        String trxLineNum = (String) shpgPlnMap.get("TRX_LINE_NUM");
        String setShpgPlnNum = (String) shpgPlnMap.get("SET_SHPG_PLN_NUM");
        String trxSrcTpCd = (String) shpgPlnMap.get("TRX_SRC_TP_CD");

        Map<String, Object> ssmParamHldInSameSet = new HashMap<String, Object>();
        ssmParamHldInSameSet.put("glblCmpyCd", glblCmpyCd);
        ssmParamHldInSameSet.put("trxHdrNum", trxHdrNum);
        ssmParamHldInSameSet.put("trxLineNum", trxLineNum);
        ssmParamHldInSameSet.put("setShpgPlnNum", setShpgPlnNum);
        ssmParamHldInSameSet.put("trxSrcTpCd", trxSrcTpCd);
        ssmParamHldInSameSet.put("hldLvlCd01", HLD_LVL.CPO_LEVEL);
        ssmParamHldInSameSet.put("hldLvlCd02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParamHldInSameSet.put("hldLvlCd03", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParamHldInSameSet.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN);  // QC#11306 2016/09/20 Add

        BigDecimal hldCnt = (BigDecimal) ssmClient.queryObject("cntHldInSameSet", ssmParamHldInSameSet);

        Map<String, Object> ssmParamShpgPlnInSameSet = new HashMap<String, Object>();
        ssmParamShpgPlnInSameSet.put("glblCmpyCd", glblCmpyCd);
        ssmParamShpgPlnInSameSet.put("setShpgPlnNum", setShpgPlnNum);
        ssmParamShpgPlnInSameSet.put("shpgStsCd10", SHPG_STS.VALIDATED);

        BigDecimal shpgPlnCnt = (BigDecimal) ssmClient.queryObject("cntShpgPlnInSameSet", ssmParamShpgPlnInSameSet);

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (hldCnt.intValue() == 0 && shpgPlnCnt.intValue() == 0) {
            if (shpgPlnTMsg.avalSoQty.getValueInt() == 0) {
                // updateTrxCd(glblCmpyCd, shpgPlnTMsg); 2016/03/24 S21_NA#5885 Del
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalSoQty, shpgPlnTMsg.ordQty);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        } else {
            if (shpgPlnTMsg.avalSoQty.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalSoQty, ZERO);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        }
    }

    private void updateSPHldRgSO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");
        String trxHdrNum = (String) shpgPlnMap.get("TRX_HDR_NUM");
        String trxLineNum = (String) shpgPlnMap.get("TRX_LINE_NUM");
        String trxLineSubNum = (String) shpgPlnMap.get("TRX_LINE_SUB_NUM");
        String trxSrcTpCd = (String) shpgPlnMap.get("TRX_SRC_TP_CD");

        Map<String, Object> ssmParamHldRg = new HashMap<String, Object>();
        ssmParamHldRg.put("glblCmpyCd", glblCmpyCd);
        ssmParamHldRg.put("trxHdrNum", trxHdrNum);
        ssmParamHldRg.put("trxLineNum", trxLineNum);
        ssmParamHldRg.put("trxLineSubNum", trxLineSubNum);
        ssmParamHldRg.put("trxSrcTpCd", trxSrcTpCd);
        ssmParamHldRg.put("hldLvlCd01", HLD_LVL.CPO_LEVEL);
        ssmParamHldRg.put("hldLvlCd02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParamHldRg.put("hldLvlCd03", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParamHldRg.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN);  // QC#11306 2016/09/20 Add

        BigDecimal hldCnt = (BigDecimal) ssmClient.queryObject("cntHldRg", ssmParamHldRg);

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (hldCnt.intValue() == 0) {
            if (shpgPlnTMsg.avalSoQty.getValueInt() == 0) {
                // updateTrxCd(glblCmpyCd, shpgPlnTMsg); 2016/03/24 S21_NA#5885 Del
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalSoQty, shpgPlnTMsg.ordQty);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        } else {
            if (shpgPlnTMsg.avalSoQty.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalSoQty, ZERO);
                updateShpgPlnList.add(shpgPlnTMsg);
            }
        }
    }

    private void updateSPOthrSO(String glblCmpyCd, Map<String, Object> shpgPlnMap, List<SHPG_PLNTMsg> updateShpgPlnList) {

        String shpgPlnNum = (String) shpgPlnMap.get("SHPG_PLN_NUM");

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        if (shpgPlnTMsg.avalSoQty.getValueInt() != 0) {
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.avalSoQty, ZERO);
            updateShpgPlnList.add(shpgPlnTMsg);
        }
    }

    private boolean existShpgPln(String shpgPlnNum, List<SHPG_PLNTMsg> updateShpgPlnList) {

        for (SHPG_PLNTMsg shpgPlnTmsg : updateShpgPlnList) {
            if (shpgPlnNum.equals(shpgPlnTmsg.shpgPlnNum.getValue())) {
                return true;
            }
        }
        return false;
    }

}
