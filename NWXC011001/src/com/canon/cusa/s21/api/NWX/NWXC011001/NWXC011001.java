/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC011001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CCYTMsg;
import business.db.CTRYTMsg;
import business.db.FRT_CONDTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.STTMsg;
import business.parts.NWXC011001PMsg;
import business.parts.NWXC011002PMsg;
import business.parts.NWXC011003PMsg;

import com.canon.cusa.s21.api.NWX.NWXC011001.cache.DataCacheForSSM;
import com.canon.cusa.s21.api.NWX.NWXC011001.cache.DataCacheForTBLAccessor;
import com.canon.cusa.s21.api.NWX.NWXC011001.cache.FindCondition;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Freight Charges API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/31   Fujitsu         Y.Kanefusa      Create          N/A
 * 2016/01/04   Fujitsu         Y.Kanefusa      Update          QC#2555
 * 2016/02/15   Fujitsu         Y.Kanefusa      Update          QC#4199
 * 2016/03/16   Fujitsu         Y.Kanefusa      Update          QC#5477
 * 2016/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#9482
 * 2016/07/20   Fujitsu         T.Yoshida       Update          S21_NA#11618
 * 2016/09/14   Fujitsu         Y.Kanefusa      Update          S21_NA#14256
 * 2017/08/17   Fujitsu         M.Ohno          Update          S21_NA#18789(L3)
 * 2017/09/28   Fujitsu         Y.Kanefusa      Update          S21_NA#21389
 * 2017/09/28   Fujitsu         Y.Kanefusa      Update          S21_NA#21390
 * 2018/08/27   Fujitsu         Y.Kanefusa      Update          S21_NA#27792
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * </pre>
 */
public class NWXC011001 extends S21ApiCommonBase {


    /** PROGRAM_ID */
    private static final String PROGRAM_ID = "NWXC011001";

    /** Data Global Company Code is not entered. */
    private static final String NWZM0163E = "NWZM0163E";

    /** Price Based Date of the parameter is not set. */
    private static final String NWZM1155E = "NWZM1155E";

    /** The parameter's "Line Business Type Code" is not set. */
    private static final String NWZM1783E = "NWZM1783E";

    /** The parameter's "Transaction Line Number" is not set. */
    private static final String NWZM0803E = "NWZM0803E";

    /** The parameter's "Transaction Line Sub Number" is not set. */
    private static final String NWZM0804E = "NWZM0804E";

    /** "Ship To Customer Code" for the entered parameter is not set. */
    private static final String NWZM1000E = "NWZM1000E";

    /** Sell To Customer Code does not exist in master. */
    private static final String NWZM1133E = "NWZM1133E";

    /** NWZM1325E */
    private static final String NWZM1325E = "NWZM1325E";

    /** NWZM1326E */
    private static final String NWZM1326E = "NWZM1326E";

    /** NWZM1384E */
    private static final String NWZM1384E = "NWZM1384E";

    /** NWZM1385E */
    private static final String NWZM1385E = "NWZM1385E";

    /** NWZM1386E */
    private static final String NWZM1386E = "NWZM1386E";

    /** NWZM1393E */
    private static final String NWZM1393E = "NWZM1393E";


    /** RATE_DIGIT */
    private static final int RATE_DIGIT = 6;

    /** NMAL7240_QTY_UNIT_TP_CD */
    private static final String NMAL7240_QTY_UNIT_TP_CD = "NMAL7240_QTY_UNIT_TP_CD";

    /** NMAL7240_PER_UNIT_TP_CD */
    private static final String NMAL7240_PER_UNIT_TP_CD = "NMAL7240_PER_UNIT_TP_CD";

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /** Constructor */
    public NWXC011001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWXC011001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWXC011001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParam(msgMap)) {
            return;
        }
        calcFreight(msgMap);

    }

    /**
     * execute
     * @param params List<NWXC011001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWXC011001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC011001PMsg param : params) {
            execute(param, onBatchType);
        }
    }


    private boolean checkParam(S21ApiMessageMap msgMap) {
        NWXC011001PMsg pMsg = (NWXC011001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM0163E, null);
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
            msgMap.addXxMsgIdWithPrm(NWZM1155E, null);
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.lineBizTpCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM1783E, null);
            msgMap.flush();
            return true;
        }
        for (int i = 0; i < pMsg.NWXC011002PMsg.getValidCount(); i++) {

            NWXC011002PMsg line = pMsg.NWXC011002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);

            if (!ZYPCommonFunc.hasValue(line.trxLineNum)) {
                lineMap.addXxMsgIdWithPrm(NWZM0803E, null);
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.trxLineSubNum)) {
                lineMap.addXxMsgIdWithPrm(NWZM0804E, null);
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.prcBaseDt)) {
                lineMap.addXxMsgIdWithPrm(NWZM1155E, null);
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.shipToCustCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1000E, null);
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.frtCondCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Freight Condition Code" });
                lineMap.flush();
                return true;
            }

            // QC#21389 2017/09/28 Add Start
            //if (!ZYPCommonFunc.hasValue(line.stCd)) {
            //    lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Ship-To State Code" });
            //    lineMap.flush();
            //    return true;
            //}
            // QC#21389 2017/09/28 Add End

            if (!ZYPCommonFunc.hasValue(line.ctryCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Ship-To Country Code" });
                lineMap.flush();
                return true;
            }

             if (!ZYPCommonFunc.hasValue(line.postCd)) {
                 lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Ship-To Post Code" });
                 lineMap.flush();
                 return true;
             }

            if (!ZYPCommonFunc.hasValue(line.shpgSvcLvlCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Shipping Service Level Code" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.frtChrgToCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Freight Charge To Code" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.frtChrgMethCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Freight Charge Method Code" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.unitNetWt)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "In Pound Unit Weight" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.ordQty)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Line Qty" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.invQty)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Invoiced Qty" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.xxPrcCloFlg)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Close Flag" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.specCondPrcPk)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Specific Condition Price PK" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.ccyCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Currency" });
                lineMap.flush();
                return true;
            }
        }

        // existence check
        LINE_BIZ_TPTMsg lineBizTpTMsg = getLineBizTp(pMsg);
        if (lineBizTpTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {PROGRAM_ID, "Line Business Type Code" });
            msgMap.flush();
            return true;
        }
        for (int i = 0; i < pMsg.NWXC011002PMsg.getValidCount(); i++) {

            NWXC011002PMsg line = pMsg.NWXC011002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);

            SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust(pMsg.glblCmpyCd.getValue(), line);
            if (shipToCustTMsg == null) {
                lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {PROGRAM_ID, "Ship To Customer Code" });
                lineMap.flush();
                return true;
            }
            if (ZYPCommonFunc.hasValue(line.dsAcctNum)) {
                Map<String, String> dsAcctCustMap = getDsAcctCust(pMsg.glblCmpyCd.getValue(), line.dsAcctNum.getValue(), pMsg.prcBaseDt.getValue());
                if (dsAcctCustMap == null) {
                    msgMap.addXxMsgIdWithPrm(NWZM1133E, null);
                    return true;
                } else {
                    ZYPEZDItemValueSetter.setValue(line.dsAcctNm, dsAcctCustMap.get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(line.dsCustSicCd, dsAcctCustMap.get("DS_CUST_SIC_CD"));
                    ZYPEZDItemValueSetter.setValue(line.locNum, dsAcctCustMap.get("LOC_NUM"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctClsCd, dsAcctCustMap.get("DS_ACCT_CLS_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctTpCd, dsAcctCustMap.get("DS_ACCT_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctDlrCd, dsAcctCustMap.get("DS_ACCT_DLR_CD"));
                    ZYPEZDItemValueSetter.setValue(line.dsAcctGrpCd, dsAcctCustMap.get("DS_ACCT_GRP_CD"));
                    ZYPEZDItemValueSetter.setValue(line.coaChCd, dsAcctCustMap.get("COA_CH_CD"));
                }
            }

            FRT_CONDTMsg frtCondTMsg = getFrtCond(pMsg.glblCmpyCd.getValue(), line);
            if (frtCondTMsg == null) {
                lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Freight Condition Code", line.frtCondCd.getValue(), "FRT_COND"});
                lineMap.flush();
                return true;
            } else {
                if (ZYPConstant.FLG_OFF_N.equals(frtCondTMsg.frtCalcFlg.getValue())) {
                    lineMap.addXxMsgIdWithPrm(NWZM1384E, new String[] {line.frtCondCd.getValue() });
                    lineMap.flush();
                    return true;
                }
                if (!frtCondTMsg.frtChrgMethCd.getValue().equals(line.frtChrgMethCd.getValue())) {
                    lineMap.addXxMsgIdWithPrm(NWZM1385E, new String[] {line.frtChrgMethCd.getValue(), line.frtCondCd.getValue() });
                    lineMap.flush();
                    return true;
                }
                if (!frtCondTMsg.frtChrgToCd.getValue().equals(line.frtChrgToCd.getValue())) {
                    lineMap.addXxMsgIdWithPrm(NWZM1386E, new String[] {line.frtChrgToCd.getValue(), line.frtCondCd.getValue() });
                    lineMap.flush();
                    return true;
                }
            }
            if (ZYPCommonFunc.hasValue(line.stCd)) { // QC#21389 2017/09/28 Add
                STTMsg stTMsg = getSt(pMsg.glblCmpyCd.getValue(), line);
                if (stTMsg == null) {
                    lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Ship-To State Code", line.stCd.getValue(), "ST" });
                    lineMap.flush();
                    return true;
                }
            }
            CTRYTMsg ctryTMsg = getCtry(pMsg.glblCmpyCd.getValue(), line);
            if (ctryTMsg == null) {
                lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Ship-To Country Code", line.ctryCd.getValue(), "CTRY" });
                lineMap.flush();
                return true;
            }
            SHPG_SVC_LVLTMsg shpgSvcLvlTMsg = getShpgSvcLvl(pMsg.glblCmpyCd.getValue(), line);
            if (shpgSvcLvlTMsg == null) {
                lineMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Shipping Service Level Code", line.shpgSvcLvlCd.getValue(), "SHPG_SVC_LVL"});
                lineMap.flush();
                return true;
            }
        }
        return false;
    }

    private void calcFreight(S21ApiMessageMap msgMap) {
        List<NWXC011001FrtSumInfoBean> sumList = new ArrayList<NWXC011001FrtSumInfoBean>();
        NWXC011001PMsg pMsg = (NWXC011001PMsg) msgMap.getPmsg();

        BigDecimal frtAmtTotal = BigDecimal.ZERO;
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal targetAmt = BigDecimal.ZERO;
        BigDecimal freAmtFromRule = BigDecimal.ZERO;
        BigDecimal shpgFrtRate = BigDecimal.ZERO;
        BigDecimal frtRatePerNum = BigDecimal.ZERO;
        NWXC011002PMsg line = null;
        NWXC011002PMsg lastLine = null;
        String qtyUnitTpCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL7240_QTY_UNIT_TP_CD, pMsg.glblCmpyCd.getValue());
        String perUnitTpCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL7240_PER_UNIT_TP_CD, pMsg.glblCmpyCd.getValue());

        // Create Summary Info
        for (int i = 0; i < pMsg.NWXC011002PMsg.getValidCount(); i++) {
            line = pMsg.NWXC011002PMsg.no(i);
            if (!hasSumList(line, sumList)) {
                NWXC011001FrtSumInfoBean bean = createSummryInfo(pMsg, line);
                sumList.add(bean);
            }
        }
        // Delete CalcBase(Freight rows)
        for (int i = 0; i < pMsg.NWXC011002PMsg.getValidCount(); i++) {
            line = pMsg.NWXC011002PMsg.no(i);
            if (!isFrozen(line)) {
                deleteCalcBase(line);
            }
        }

        for (NWXC011001FrtSumInfoBean bean : sumList) {
            Map<String, Object> mapParam = new HashMap<String, Object>();
            mapParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            mapParam.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
            mapParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
            mapParam.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
            mapParam.put("shipAcctNum", bean.getDsAcctNum());
            mapParam.put("shipToStCd", bean.getStCd());
            mapParam.put("shipToCtryCd", bean.getCtryCd());
            mapParam.put("shipToPostCd", bean.getPostCd());
            mapParam.put("prcBaseDt", bean.getPrcBaseDt());
            mapParam.put("PrcGrpTrgtAttrbCd_14", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_15", PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME);
            mapParam.put("PrcGrpTrgtAttrbCd_16", PRC_GRP_TRGT_ATTRB.SIC_CODE);
            mapParam.put("PrcGrpTrgtAttrbCd_17", PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_18", PRC_GRP_TRGT_ATTRB.CLASSIFICATION);
            mapParam.put("PrcGrpTrgtAttrbCd_19", PRC_GRP_TRGT_ATTRB.CATEGORY);
            mapParam.put("PrcGrpTrgtAttrbCd_20", PRC_GRP_TRGT_ATTRB.DEALER_CODE);
            mapParam.put("PrcGrpTrgtAttrbCd_21", PRC_GRP_TRGT_ATTRB.CSMP_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_22", PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER);
            mapParam.put("PrcGrpTrgtAttrbCd_24", PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME);
            // S21_NA#18789 add start
            mapParam.put("PrcGrpTrgtAttrbCd_28", PRC_GRP_TRGT_ATTRB.CHANNEL);
            // S21_NA#18789 add end
            mapParam.put("dsAcctNum", bean.getDsAcctNum());
            mapParam.put("dsAcctNm", bean.getDsAcctNm());
            mapParam.put("dsCustSicCd", bean.getDsCustSicCd());
            mapParam.put("locNum", bean.getLocNum());
            mapParam.put("dsAcctClsCd", bean.getDsAcctClsCd());
            mapParam.put("dsAcctTpCd", bean.getDsAcctTpCd());
            mapParam.put("dsAcctDlrCd", bean.getDsAcctDlrCd());
            mapParam.put("dsAcctGrpCd", bean.getDsAcctGrpCd());
            mapParam.put("coaChCd", bean.getCoaChCd());
            mapParam.put("csmpNum", bean.getCsmpNum());
            mapParam.put("dlrRefNum", bean.getDlrRefNum());
            mapParam.put("prcContrNum", bean.getPrcContrNum());
            mapParam.put("prcGrpOpCd01", PRC_GRP_OP.EQ);
            mapParam.put("prcGrpOpCd02", PRC_GRP_OP.NOT_EQ);
            mapParam.put("prcGrpOpCd03", PRC_GRP_OP.BETWEEN);
            mapParam.put("prcGrpOpCd04", PRC_GRP_OP.LIKE);
            mapParam.put("ordQty", bean.getTtlWeight().abs());
            mapParam.put("qtyUnitTpCd", qtyUnitTpCd);
            mapParam.put("shpgSvcLvlCd", bean.getShpgSvcLvlCd());
            mapParam.put("ccyCd", bean.getCcyCd());
            mapParam.put("perUnitTpCd", perUnitTpCd);

            List<Map<String, Object>> ssmResList =  DataCacheForSSM.getInstance().getFrtRateShpg(mapParam, ssmBatchClient);

            if (ssmResList == null || ssmResList.isEmpty()) {
                msgMap.addXxMsgIdWithPrm(NWZM1393E, null);
                msgMap.flush();
                return;
            }
            if (BigDecimal.ZERO.compareTo(bean.getActivWeight()) == 0) {
                continue;
            }

            Map<String, Object> mapRes = ssmResList.get(0);
            shpgFrtRate = (BigDecimal) mapRes.get("SHPG_FRT_RATE");
            frtRatePerNum = (BigDecimal) mapRes.get("FRT_RATE_PER_NUM");

            freAmtFromRule = bean.getTtlWeight().multiply(shpgFrtRate).divide(frtRatePerNum, getDealCcyDigit(pMsg.glblCmpyCd.getValue(), bean.getCcyCd()), BigDecimal.ROUND_HALF_UP);
            targetAmt = freAmtFromRule.subtract(bean.getFrozenFrtAmt());
            frtAmtTotal = BigDecimal.ZERO;
            BigDecimal calcPrcAmtRate = null;
            BigDecimal maxValue = null;
            for (int i = 0; i < pMsg.NWXC011002PMsg.getValidCount(); i++) {
                line = pMsg.NWXC011002PMsg.no(i);
                if (!isFrozen(line) && isTarget(line, bean)) {
                    BigDecimal wt = line.ordQty.getValue().multiply(line.unitNetWt.getValue());
                    rate = wt.divide(bean.getActivWeight(), RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                    // QC#27792 2018/08/27 Mod End
                    // QC#21390 2017/09/28 Mod Start
                    //frtAmtTotal = frtAmtTotal.add(editCalcBase(pMsg, line, targetAmt, rate));
                    //!lastLine = line;
                    //if(!isManual(line)){
                    //    lastLine = line;
                    //}
                    // QC#21390 2017/09/28 Mod End
                    calcPrcAmtRate = editCalcBase(pMsg, line, targetAmt, rate);
                    frtAmtTotal = frtAmtTotal.add(calcPrcAmtRate);
                    if (maxValue == null) {
                        lastLine = line;
                        maxValue = calcPrcAmtRate.abs();
                    } else if (calcPrcAmtRate.abs().compareTo(maxValue) > 0) {
                        lastLine = line;
                        maxValue = calcPrcAmtRate.abs();
                    }
                    // QC#27792 2018/08/27 Mod End
                }
            }
            if (frtAmtTotal.compareTo(targetAmt) != 0) {
                BigDecimal fraction = targetAmt.subtract(frtAmtTotal);
                ajustFraction(pMsg, bean, fraction, lastLine);
            }
        }
    }

    private boolean hasSumList(NWXC011002PMsg line, List<NWXC011001FrtSumInfoBean> list) {
        for (NWXC011001FrtSumInfoBean bean : list) {
            if (isTarget(line, bean)) {
                return true;
            }
        }
        return false;
    }

    private void deleteCalcBase(NWXC011002PMsg line) {
        List<Integer> delIndex = new ArrayList<Integer>();
        for (int j = 0; j < line.NWXC011003PMsg.getValidCount(); j++) {
            NWXC011003PMsg calcBase = line.NWXC011003PMsg.no(j);
            if (PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue()) 
                    && line.specCondPrcPk.getValue().equals(calcBase.specCondPrcPk.getValue()) 
                    && ZYPConstant.FLG_OFF_N.equals(calcBase.prcCondManEntryFlg.getValue())) {
                delIndex.add(j);
            }
        }
        if (!delIndex.isEmpty()) {
            ZYPTableUtil.deleteRows(line.NWXC011003PMsg, delIndex);
        }
    }

    private NWXC011001FrtSumInfoBean createSummryInfo(NWXC011001PMsg pMsg, NWXC011002PMsg line) {
        NWXC011001FrtSumInfoBean bean = new NWXC011001FrtSumInfoBean();
        bean.setPrcBaseDt(line.prcBaseDt.getValue());
        bean.setConfigCatgCd(line.configCatgCd.getValue());// QC#14256 2016/09/14 Add
        bean.setShipToCustCd(line.shipToCustCd.getValue());
        bean.setDsAcctNum(line.dsAcctNum.getValue());
        bean.setDsAcctNm(line.dsAcctNm.getValue());
        bean.setDsCustSicCd(line.dsCustSicCd.getValue());
        bean.setLocNum(line.locNum.getValue());
        bean.setDsAcctClsCd(line.dsAcctClsCd.getValue());
        bean.setDsAcctTpCd(line.dsAcctTpCd.getValue());
        bean.setDsAcctDlrCd(line.dsAcctDlrCd.getValue());
        bean.setDsAcctGrpCd(line.dsAcctGrpCd.getValue());
        bean.setCoaChCd(line.coaChCd.getValue());
        bean.setCsmpNum(line.csmpNum.getValue());
        bean.setDlrRefNum(line.dlrRefNum.getValue());
        bean.setPrcContrNum(line.prcContrNum.getValue());
        bean.setStCd(line.stCd.getValue());
        bean.setCtryCd(line.ctryCd.getValue());
        bean.setPostCd(line.postCd.getValue());
        bean.setShpgSvcLvlCd(line.shpgSvcLvlCd.getValue());
        bean.setCcyCd(line.ccyCd.getValue());

        for (int i = 0; i < pMsg.NWXC011002PMsg.getValidCount(); i++) {
            NWXC011002PMsg data = pMsg.NWXC011002PMsg.no(i);
            if (isTarget(data, bean)) {
                bean.addOrdQty(data.ordQty.getValue());
                BigDecimal wt = data.ordQty.getValue().multiply(data.unitNetWt.getValue());
                bean.addTtlWeight(wt);
                if (isFrozen(data)) {
                    bean.addFrozenFrtAmt(getCalcAmtRate(data));
                } else {
                    bean.addActivWeight(wt);
                }
            }
        }
        return bean;
    }

    private BigDecimal editCalcBase(NWXC011001PMsg pMsg, NWXC011002PMsg line, BigDecimal baseAmt, BigDecimal rate) {
        NWXC011003PMsg calcBase = null;
        NWXC011003PMsg base = null;
        NWXC011003PMsg desc = null;
        for (int i = 0; i < line.NWXC011003PMsg.getValidCount(); i++) {
            calcBase = line.NWXC011003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                base = calcBase;
            }
            if (line.specCondPrcPk.getValue().compareTo(calcBase.specCondPrcPk.getValue()) == 0) {
                desc = calcBase;
            }
        }
        if (base == null) {
            return BigDecimal.ZERO;
        }
        if (desc == null) {
            desc = line.NWXC011003PMsg.no(line.NWXC011003PMsg.getValidCount());
            line.NWXC011003PMsg.setValidCount(line.NWXC011003PMsg.getValidCount() + 1);
        }
        // QC#21390 2017/09/28 Del Start
        //if (ZYPConstant.FLG_ON_Y.equals(desc.prcCondManEntryFlg.getValue())) {
        //   return BigDecimal.ZERO;
        //}
        // QC#21390 2017/09/28 Del End

        BigDecimal frtAmt = multiply(pMsg.glblCmpyCd.getValue(), baseAmt, rate, base.autoPrcCcyCd.getValue());
        BigDecimal frtPrc = divide(pMsg.glblCmpyCd.getValue(), frtAmt, line.ordQty.getValue(), base.autoPrcCcyCd.getValue());
        // QC#21390 2017/09/28 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(desc.prcCondManEntryFlg.getValue())) {
            return frtPrc.multiply(line.ordQty.getValue());
        }
        // QC#21390 2017/09/28 Add End

        ZYPEZDItemValueSetter.setValue(desc.trxLineNum, line.trxLineNum);
        ZYPEZDItemValueSetter.setValue(desc.trxLineSubNum, line.trxLineSubNum);
        ZYPEZDItemValueSetter.setValue(desc.configCatgCd, line.configCatgCd);
        desc.prcCondTpCd.clear();
        desc.prcCondTpDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(desc.prcDtlGrpCd, PRC_DTL_GRP.FREIGHT);
        desc.prcJrnlGrpCd.clear();
        ZYPEZDItemValueSetter.setValue(desc.prcCatgCd, base.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(desc.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(desc.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(desc.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(desc.pkgUomCd, base.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(desc.prcCondUnitCd, PRC_COND_UNIT.AMT);
        desc.prcCalcMethCd.clear();
        ZYPEZDItemValueSetter.setValue(desc.autoPrcCcyCd, base.autoPrcCcyCd);
        ZYPEZDItemValueSetter.setValue(desc.autoPrcAmtRate, frtPrc);
        desc.maxPrcAmtRate.clear();
        desc.minPrcAmtRate.clear();
        desc.manPrcAmtRate.clear();
        ZYPEZDItemValueSetter.setValue(desc.calcPrcAmtRate, frtPrc.multiply(line.ordQty.getValue()));
        ZYPEZDItemValueSetter.setValue(desc.unitPrcAmt, frtPrc);
        desc.dsMdsePrcPk.clear();
        ZYPEZDItemValueSetter.setValue(desc.specCondPrcPk, line.specCondPrcPk);
        desc.frtPerWtPk.clear();
        desc.ordPrcCalcBasePk.clear();
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(desc.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(desc.prcRulePrcdPk, pMsg.prcRulePrcdPk);
        // QC#9700  2018/09/03 Add End

        return frtPrc.multiply(line.ordQty.getValue());
    }

    private void ajustFraction(NWXC011001PMsg pMsg, NWXC011001FrtSumInfoBean bean, BigDecimal fraction, NWXC011002PMsg targetLine ){
        NWXC011003PMsg calcBase = null;
        NWXC011003PMsg base = null;
        NWXC011003PMsg fractionCalcBase = null;
        // QC#21390 2017/09/28 Add Start
        if(targetLine == null){
            return;
        }
        // QC#21390 2017/09/28 Add End
        for (int i = 0; i < targetLine.NWXC011003PMsg.getValidCount(); i++) {
            calcBase = targetLine.NWXC011003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                base = calcBase;
            }
        }
        fractionCalcBase = targetLine.NWXC011003PMsg.no(targetLine.NWXC011003PMsg.getValidCount());
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.trxLineNum, targetLine.trxLineNum);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.trxLineSubNum, targetLine.trxLineSubNum);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.configCatgCd, targetLine.configCatgCd);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcDtlGrpCd, PRC_DTL_GRP.ROUNDING_FACTOR_1);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCatgCd, base.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.pkgUomCd, base.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.autoPrcCcyCd, base.autoPrcCcyCd);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.autoPrcAmtRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.calcPrcAmtRate, fraction);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.unitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.specCondPrcPk, targetLine.specCondPrcPk);
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcRulePrcdPk, pMsg.prcRulePrcdPk);
        // QC#9700  2018/09/03 Add End
        targetLine.NWXC011003PMsg.setValidCount(targetLine.NWXC011003PMsg.getValidCount() + 1);

    }
    private boolean isTarget(NWXC011002PMsg line, NWXC011001FrtSumInfoBean bean) {
        if (S21StringUtil.isEquals(line.prcBaseDt.getValue(), bean.getPrcBaseDt())
                && S21StringUtil.isEquals(line.configCatgCd.getValue(), bean.getConfigCatgCd()) // QC#14256 2016/09/14 Add
                && S21StringUtil.isEquals(line.shipToCustCd.getValue(), bean.getShipToCustCd())
                && S21StringUtil.isEquals(line.dsAcctNum.getValue(), bean.getDsAcctNum())
                && S21StringUtil.isEquals(line.csmpNum.getValue(), bean.getCsmpNum())
                && S21StringUtil.isEquals(line.dlrRefNum.getValue(), bean.getDlrRefNum())
                && S21StringUtil.isEquals(line.prcContrNum.getValue(), bean.getPrcContrNum())
                && S21StringUtil.isEquals(line.stCd.getValue(), bean.getStCd())
                && S21StringUtil.isEquals(line.ctryCd.getValue(), bean.getCtryCd())
                && S21StringUtil.isEquals(line.postCd.getValue(), bean.getPostCd())
                && S21StringUtil.isEquals(line.shpgSvcLvlCd.getValue(), bean.getShpgSvcLvlCd())
                && S21StringUtil.isEquals(line.ccyCd.getValue(), bean.getCcyCd())) {
            return true;
        }
        return false;
    }

    private boolean isFrozen(NWXC011002PMsg line) {
        if (BigDecimal.ZERO.compareTo(line.invQty.getValue()) != 0
                || ZYPConstant.FLG_ON_Y.equals(line.xxPrcCloFlg.getValue())) {
            return true;
        }
        return false;
    }

    // QC#21390 2017/09/28 Add Start
    private boolean isManual(NWXC011002PMsg line) {
        NWXC011003PMsg calcBase = null;
        for (int i = 0; i < line.NWXC011003PMsg.getValidCount(); i++) {
            calcBase = line.NWXC011003PMsg.no(i);
            if (line.specCondPrcPk.getValue().compareTo(calcBase.specCondPrcPk.getValue()) == 0) {
                if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
    // QC#21390 2017/09/28 Add End

    private BigDecimal getCalcAmtRate(NWXC011002PMsg line) {
        BigDecimal calcPrcAmtRate = BigDecimal.ZERO;
        for (int i = 0; i < line.NWXC011003PMsg.getValidCount(); i++) {
            NWXC011003PMsg calcBase = line.NWXC011003PMsg.no(i);
            if ((PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue()) 
                    || PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(calcBase.prcDtlGrpCd.getValue()))
                    && line.specCondPrcPk.getValue().equals(calcBase.specCondPrcPk.getValue())) {
                if(ZYPCommonFunc.hasValue(calcBase.calcPrcAmtRate)){
                    calcPrcAmtRate = calcPrcAmtRate.add(calcBase.calcPrcAmtRate.getValue());
                }
            }
        }
        return calcPrcAmtRate;
    }

    private BigDecimal round(BigDecimal unitPrice, int digit) {
        return unitPrice.setScale(digit, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal multiply(String glblCmpyCd, BigDecimal num1, BigDecimal num2, String dealCcyCd) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        int scale = getDealCcyDigit(glblCmpyCd, dealCcyCd);
        num1 = round(num1, scale);
        return round(num1.multiply(num2), scale);
    }

    private BigDecimal divide(String glblCmpyCd, BigDecimal num1, BigDecimal num2, String dealCcyCd) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        if (BigDecimal.ZERO.compareTo(num2) == 0) {
            return BigDecimal.ZERO;
        }
        int scale = getDealCcyDigit(glblCmpyCd, dealCcyCd);
        num1 = round(num1, scale);
        return round(num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP), scale);
    }

    // Data Access
    private LINE_BIZ_TPTMsg getLineBizTp(NWXC011001PMsg pMsg) {
        LINE_BIZ_TPTMsg inTMsg = new LINE_BIZ_TPTMsg();
        inTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        inTMsg.lineBizTpCd.setValue(pMsg.lineBizTpCd.getValue());
        LINE_BIZ_TPTMsg outTMsg = (LINE_BIZ_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, NWXC011002PMsg line) {
        final FindCondition condition = new FindCondition("004");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("shipToCustCd01", line.shipToCustCd.getValue());
        SHIP_TO_CUSTTMsgArray tMsgArry = DataCacheForTBLAccessor.getInstance().getShipToCustTMsgArray(condition);

        if (tMsgArry.length() == 0) {
            return null;
        }
        return (SHIP_TO_CUSTTMsg) tMsgArry.get(0);
    }

    private Map<String, String> getDsAcctCust(String glblCmpyCd, String dsAcctCd, String prcBaseDt) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("dsAcctNum", dsAcctCd);
        mapParam.put("prcBaseDt", prcBaseDt);

        return DataCacheForSSM.getInstance().getDsAcctCust(mapParam, ssmBatchClient);
    }

    private FRT_CONDTMsg getFrtCond(String glblCmpyCd, NWXC011002PMsg line) {
        FRT_CONDTMsg inTMsg = new FRT_CONDTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.frtCondCd.setValue(line.frtCondCd.getValue());
        FRT_CONDTMsg outTMsg = (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private STTMsg getSt(String glblCmpyCd, NWXC011002PMsg line) {
        STTMsg inTMsg = new STTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.stCd.setValue(line.stCd.getValue());
        STTMsg outTMsg = (STTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private CTRYTMsg getCtry(String glblCmpyCd, NWXC011002PMsg line) {
        CTRYTMsg inTMsg = new CTRYTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.ctryCd.setValue(line.ctryCd.getValue());
        CTRYTMsg outTMsg = (CTRYTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private SHPG_SVC_LVLTMsg getShpgSvcLvl(String glblCmpyCd, NWXC011002PMsg line) {
        SHPG_SVC_LVLTMsg inTMsg = new SHPG_SVC_LVLTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.shpgSvcLvlCd.setValue(line.shpgSvcLvlCd.getValue());
        SHPG_SVC_LVLTMsg outTMsg = (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private int getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, dealCcyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }

}
