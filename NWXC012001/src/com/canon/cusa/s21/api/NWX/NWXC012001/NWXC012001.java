/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC012001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CCYTMsg;
import business.parts.NWXC012001PMsg;
import business.parts.NWXC012002PMsg;
import business.parts.NWXC012003PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Promotion Amount Formula API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/04   Fujitsu         Y.Kanefusa      Create          N/A
 * 2015/12/14   Fujitsu         Y.Kanefusa      Update          QC#385
 * 2016/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#9482
 * 2016/08/29   Fujitsu         Y.Kanefusa      Update          S21_NA#10738
 * 2017/08/17   Fujitsu         M.Ohno          Update          S21_NA#18789(L3)
 * 2018/05/13   Fujitsu         Y.Kanefusa      Update          S21_NA#25422
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * </pre>
 */
public class NWXC012001 extends S21ApiCommonBase {

    /** PROGRAM_ID */
    private static final String PROGRAM_ID = "NWXC012001";

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

    /** NWZM1325E */
    private static final String NWZM1325E = "NWZM1325E";

    /** NWZM1355W */
    private static final String NWZM1355W = "NWZM1355W";

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /** PRTY_5 */
    // private static final String PRTY_5 = "5";

    /** negativeValue */
    private static final BigDecimal MINUS = new BigDecimal(-1);

    /** Constructor */
    public NWXC012001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWXC012001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWXC012001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParam(msgMap)) {
            return;
        }
        deleteCalcBase(msgMap);
        calculation(msgMap);
        return;
    }

    /**
     * execute
     * @param params List<NWXC012001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWXC012001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC012001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    private boolean checkParam(S21ApiMessageMap msgMap) {
        NWXC012001PMsg pMsg = (NWXC012001PMsg) msgMap.getPmsg();

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
            msgMap.addXxMsgIdWithPrm(NWZM1783E, new String[] {PROGRAM_ID, "Line Business Type Code" });
            msgMap.flush();
            return true;
        }
        for (int i = 0; i < pMsg.NWXC012002PMsg.getValidCount(); i++) {

            NWXC012002PMsg line = pMsg.NWXC012002PMsg.no(i);
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

            if (!ZYPCommonFunc.hasValue(line.specCondPrcPk)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Specific Condition Price PK" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.prcBaseDt)) {
                lineMap.addXxMsgIdWithPrm(NWZM1155E, null);
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.mdseCd) && !ZYPCommonFunc.hasValue(line.mdlId)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Merchandise Code or Model ID" });
                lineMap.flush();
                return true;
            }

        }
        return false;
    }

    private void deleteCalcBase(S21ApiMessageMap msgMap) {
        NWXC012001PMsg param = (NWXC012001PMsg) msgMap.getPmsg();
        NWXC012002PMsg line;
        NWXC012003PMsg calcBase;
        List<Integer> deleteList = new ArrayList<Integer>();

        for (int i = 0; i < param.NWXC012002PMsg.getValidCount(); i++) {
            line = param.NWXC012002PMsg.no(i);
            if (!isFrozen(line)) {
                for (int j = 0; j < line.NWXC012003PMsg.getValidCount(); j++) {
                    calcBase = line.NWXC012003PMsg.no(j);
                    if (!ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
                        if (isEquals(line.specCondPrcPk.getValue(), calcBase.specCondPrcPk.getValue())) {
                            deleteList.add(j);
                        }
                    }
                }
                ZYPTableUtil.deleteRows(line.NWXC012003PMsg, deleteList);
                deleteList.clear();
            }
        }
    }

    private void calculation(S21ApiMessageMap msgMap) {
        NWXC012001PMsg param = (NWXC012001PMsg) msgMap.getPmsg();
        Map<String, Object> paramHeader = new HashMap<String, Object>();
        List<Map< ? , ? >> promList =  new  ArrayList<Map< ? , ? >>();
        Map<List<Object>, List<Map< ? , ? >>> cache = new HashMap<List<Object>, List<Map< ? , ? >>>();
        String dsOrdPosnNum = "";
        List<NWXC012002PMsg> configList = new ArrayList<NWXC012002PMsg>();

        for (int i = 0; i < param.NWXC012002PMsg.getValidCount(); i++) {

            NWXC012002PMsg line = param.NWXC012002PMsg.no(i);
            if (!ZYPCommonFunc.hasValue(line.dsOrdPosnNum)) {
                continue;
            }

            // ADD START 2015/12/14 #385
            Map<String, BigDecimal> promoMap = new HashMap<String, BigDecimal>();
            // ADD END 2015/12/14 #385

            List<Object> key = createNewKey(line);
            promList = cache.get(key);
            if (promList == null) {
                // DEL START 2015/12/14 #385
                // deleteDiscountRow(line);
                // DEL START 2015/12/14 #385
                paramHeader.put("glblCmpyCd", param.glblCmpyCd.getValue());
                paramHeader.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
                paramHeader.put("actvFlg", ZYPConstant.FLG_ON_Y);
                paramHeader.put("dsAcctNum", param.dsAcctNum.getValue());
                paramHeader.put("dsAcctNm", param.dsAcctNm.getValue());
                paramHeader.put("dsCustSicCd", param.dsCustSicCd.getValue());
                paramHeader.put("locNum", param.locNum.getValue());
                paramHeader.put("dsAcctClsCd", param.dsAcctClsCd.getValue());
                paramHeader.put("dsAcctTpCd", param.dsAcctTpCd.getValue());
                paramHeader.put("dsAcctDlrCd", param.dsAcctDlrCd.getValue());
                paramHeader.put("csmpNum", line.csmpNum.getValue());
                paramHeader.put("dlrRefNum", line.dlrRefNum.getValue());
                paramHeader.put("prcContrNum", line.prcContrNum.getValue());
                if (!S21StringUtil.isEquals(param.dsAcctNum.getValue(), line.dsAcctNum_SH.getValue())) {
                    paramHeader.put("dsAcctNum_SH", line.dsAcctNum_SH.getValue());
                    paramHeader.put("dsAcctNm_SH", line.dsAcctNm_SH.getValue());
                    paramHeader.put("dsCustSicCd_SH", line.dsCustSicCd_SH.getValue());
                    paramHeader.put("locNum_SH", line.locNum_SH.getValue());
                    paramHeader.put("dsAcctClsCd_SH", line.dsAcctClsCd_SH.getValue());
                    paramHeader.put("dsAcctTpCd_SH", line.dsAcctTpCd_SH.getValue());
                    paramHeader.put("dsAcctDlrCd_SH", line.dsAcctDlrCd_SH.getValue());
                    // S21_NA#18789 add start
                    paramHeader.put("coaChCd_SH", line.coaChCd_SH.getValue());
                    // S21_NA#18789 add end
                }
                if (!S21StringUtil.isEquals(param.dsAcctNum.getValue(), line.dsAcctNum_BL.getValue())) {
                    paramHeader.put("dsAcctNum_BL", line.dsAcctNum_BL.getValue());
                    paramHeader.put("dsAcctNm_BL", line.dsAcctNm_BL.getValue());
                    paramHeader.put("dsCustSicCd_BL", line.dsCustSicCd_BL.getValue());
                    paramHeader.put("locNum_BL", line.locNum_BL.getValue());
                    paramHeader.put("dsAcctClsCd_BL", line.dsAcctClsCd_BL.getValue());
                    paramHeader.put("dsAcctTpCd_BL", line.dsAcctTpCd_BL.getValue());
                    paramHeader.put("dsAcctDlrCd_BL", line.dsAcctDlrCd_BL.getValue());
                    // S21_NA#18789 add start
                    paramHeader.put("coaChCd_BL", line.coaChCd_BL.getValue());
                    // S21_NA#18789 add end
                }

                paramHeader.put("prcBaseDt", line.prcBaseDt.getValue());
                paramHeader.put("delFlg", ZYPConstant.FLG_OFF_N);
                paramHeader.put("pubFlg_Y", ZYPConstant.FLG_ON_Y);
                paramHeader.put("pubFlg_N", ZYPConstant.FLG_OFF_N);
                paramHeader.put("lineBizTpCd", param.lineBizTpCd.getValue());
                if (S21StringUtil.isEquals(param.lineBizTpCd.getValue(), LINE_BIZ_TP.ALL)) {
                    paramHeader.put("lineBizTpCdAll", param.lineBizTpCd.getValue());
                }
                paramHeader.put("coaChCd01", param.coaChCd.getValue());
                paramHeader.put("dsAcctGrpCd", param.dsAcctGrpCd.getValue());
                paramHeader.put("coaBrCd", line.coaBrCd.getValue());

                paramHeader.put("PrcGrpTrgtAttrbCd_14", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
                paramHeader.put("PrcGrpTrgtAttrbCd_15", PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME);
                paramHeader.put("PrcGrpTrgtAttrbCd_16", PRC_GRP_TRGT_ATTRB.SIC_CODE);
                paramHeader.put("PrcGrpTrgtAttrbCd_17", PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER);
                paramHeader.put("PrcGrpTrgtAttrbCd_18", PRC_GRP_TRGT_ATTRB.CLASSIFICATION);
                paramHeader.put("PrcGrpTrgtAttrbCd_19", PRC_GRP_TRGT_ATTRB.CATEGORY);
                paramHeader.put("PrcGrpTrgtAttrbCd_20", PRC_GRP_TRGT_ATTRB.DEALER_CODE);
                paramHeader.put("PrcGrpTrgtAttrbCd_21", PRC_GRP_TRGT_ATTRB.CSMP_NUMBER);
                paramHeader.put("PrcGrpTrgtAttrbCd_22", PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER);
                paramHeader.put("PrcGrpTrgtAttrbCd_24", PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER);
                // S21_NA#18789 add start
                paramHeader.put("PrcGrpTrgtAttrbCd_28", PRC_GRP_TRGT_ATTRB.CHANNEL);
                // S21_NA#18789 add end

                paramHeader.put("prcGrpOpCd01", PRC_GRP_OP.EQ);
                paramHeader.put("prcGrpOpCd02", PRC_GRP_OP.NOT_EQ);
                paramHeader.put("prcGrpOpCd03", PRC_GRP_OP.BETWEEN);
                paramHeader.put("prcGrpOpCd04", PRC_GRP_OP.LIKE);

                List<Map< ? , ? >> ssmResList = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("selectPromotionHeader", paramHeader);
                promList = getPriceMarketingPromotion(line, ssmResList);

                cache.put(key, promList);
            }
            //get same Config line-data.
            if (!S21StringUtil.isEquals(dsOrdPosnNum, line.dsOrdPosnNum.getValue())) {
                configList.clear();
                for(int j = 0; j < param.NWXC012002PMsg.getValidCount(); j++){
                    if(S21StringUtil.isEquals(line.dsOrdPosnNum.getValue(), param.NWXC012002PMsg.no(j).dsOrdPosnNum.getValue())){
                        configList.add(param.NWXC012002PMsg.no(j));
                    }
                }
            }
            
            for (Map< ? , ? > mapRes : promList) {

                //Promotion
                Map<String, Object> paramDetal = new HashMap<String, Object>();
                paramDetal.put("glblCmpyCd", param.glblCmpyCd.getValue());
                paramDetal.put("prcMktPromPk", (BigDecimal) mapRes.get("PRC_MKT_PRMO_PK"));
                paramDetal.put("delFlg", ZYPConstant.FLG_OFF_N);
                paramDetal.put("prcBaseDt", line.prcBaseDt.getValue());
//                paramDetal.put("prcQlfyTp_01", PRC_QLFY_TP.ITEM_CODE);
//                paramDetal.put("prcQlfyTp_02", PRC_QLFY_TP.MERCHANDISE_TYPE);
//                if (ZYPCommonFunc.hasValue(line.mdlId.getValue())) {
//                    paramDetal.put("mdlId", String.valueOf(line.mdlId.getValue()));
//                } else {
//                    paramDetal.put("mdlId", "");
//                }
                paramDetal.put("mdseCd", line.mdseCd.getValue());
                paramDetal.put("ordTakeMdseCd", line.ordTakeMdseCd.getValue());
                paramDetal.put("prcPrmoQlfyTp_03", PRC_PRMO_QLFY_TP.BUNDLE);
                paramDetal.put("prcCatgCd", line.prcCatgCd.getValue());
                List<Map< ? , ? >> ssmResListDetail = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("selectPromotionDetail", paramDetal);
                // DEL START 2015/12/14 #385
                // editPromotionDetail(param, line, ssmResListDetail);
                // DEL END 2015/12/14 #385
                // ADD START 2015/12/14 #385
                promoMap = hasPromotionDetail(configList, ssmResListDetail, promoMap);
                // ADD END 2015/12/14 #385

                //Bundle
                Map<String, Object> paramBundle = new HashMap<String, Object>();
                paramBundle.put("glblCmpyCd", param.glblCmpyCd.getValue());
                paramBundle.put("prcMktPromPk", (BigDecimal) mapRes.get("PRC_MKT_PRMO_PK"));
                paramBundle.put("delFlg", ZYPConstant.FLG_OFF_N);
                paramBundle.put("prcBaseDt", line.prcBaseDt.getValue());
                paramBundle.put("prcPrmoQlfyTp_03", PRC_PRMO_QLFY_TP.BUNDLE);
                paramBundle.put("prcCatgCd", line.prcCatgCd.getValue());
                paramBundle.put("mdseCd", line.mdseCd.getValue());
                List<Map< ? , ? >> ssmResListBundle = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("selectPromotionBundle", paramBundle);
                // DEL START 2015/12/14 #385
                // List<Map<?, ?>>list = getMachingBundle(param, line, ssmResListBundle);
                // DEL START 2015/12/14 #385
                // editPromotionBundle(param, line, list);
                // DEL END 2015/12/14 #385
                // ADD START 2015/12/14 #385
                promoMap = hasPromotionBundle(configList, ssmResListBundle, promoMap);
                // ADD END 2015/12/14 #385
            }
            // ADD START 2015/12/14 #385
            editPriceAmount(param, configList, promoMap);
            // ADD END 2015/12/14 #385
            dsOrdPosnNum = line.dsOrdPosnNum.getValue();
        }
    }
    
    private List<Object> createNewKey(NWXC012002PMsg line){
        List<Object> key = new ArrayList<Object>();
        key.add(line.csmpNum.getValue());
        key.add(line.dlrRefNum.getValue());
        key.add(line.prcContrNum.getValue());
        key.add(line.dsAcctNum_SH.getValue());
        key.add(line.dsAcctNum_BL.getValue());
        return key;
    }

// DEL START 2015/12/14 #385
//    private void deleteDiscountRow(NWXC012002PMsg line) {
//        List<Integer> delIndex = new ArrayList<Integer>();
//        for (int j = 0; j < line.NWXC012003PMsg.getValidCount(); j++) {
//            NWXC012003PMsg calcBase = line.NWXC012003PMsg.no(j);
//            if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue()) && calcBase.specCondPrcPk.getValue().equals(line.specCondPrcPk.getValue())) {
//                delIndex.add(j);
//            }
//        }
//        if (!delIndex.isEmpty()) {
//            ZYPTableUtil.deleteRows(line.NWXC012003PMsg, delIndex);
//        }
//        return;
//    }
// DEL END 2015/12/14 #385

    private List<Map< ? , ? >> getPriceMarketingPromotion(NWXC012002PMsg line, List<Map< ? , ? >> ssmResList) {
        List<Map< ? , ? >> promList = new ArrayList<Map< ? , ? >>();
        String prtyNum = null;
        for (Map< ? , ? > map : ssmResList) {
            if (prtyNum == null) {
                prtyNum = map.get("PRTY_NUM").toString();
            }
//            if (ZYPCommonFunc.hasValue(line.csmpNum.getValue()) || ZYPCommonFunc.hasValue(line.dlrRefNum.getValue())) {
//                if (map.get("PRTY_NUM").toString().compareTo(PRTY_5) > 0) {
//                    continue;
//                }
//            }
            if (prtyNum.equals(map.get("PRTY_NUM").toString())) {
                promList.add(map);
            }
        }
        return promList;
    }

// DEL START 2015/12/14 #385
//    private void editPromotionDetail(NWXC012001PMsg pMsg, NWXC012002PMsg line, List<Map<?, ?>> ssmResList) {
//        NWXC012003PMsg base = null;
//
//        if(ssmResList == null){
//            return;
//        }
//        // Get Base row
//        for (int i = 0; i < line.NWXC012003PMsg.getValidCount(); i++) {
//            NWXC012003PMsg calcBase = line.NWXC012003PMsg.no(i);
//            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
//                base = calcBase;
//            }
//        }
//        if (base == null) {
//            return;
//        }
//
//        for (Map<?, ?> map : ssmResList) {
//            if (isMdseMatching(line, (String)map.get("MDSE_CD"))) {
//                BigDecimal promoAmt = (BigDecimal) map.get("PRMO_AMT");
//
//                NWXC012003PMsg desc = new NWXC012003PMsg();
//                ZYPEZDItemValueSetter.setValue(desc.trxLineNum, line.trxLineNum);
//                ZYPEZDItemValueSetter.setValue(desc.trxLineSubNum, line.trxLineSubNum);
//                desc.prcCondTpCd.clear();
//                desc.prcCondTpDescTxt.clear();
//                ZYPEZDItemValueSetter.setValue(desc.prcDtlGrpCd, PRC_DTL_GRP.DISCOUNT);
//                desc.prcJrnlGrpCd.clear();
//                ZYPEZDItemValueSetter.setValue(desc.prcCatgCd, base.prcCatgCd.getValue());
//                ZYPEZDItemValueSetter.setValue(desc.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(desc.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(desc.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(desc.pkgUomCd, base.pkgUomCd.getValue());
//                ZYPEZDItemValueSetter.setValue(desc.prcCondUnitCd, PRC_COND_UNIT.AMT);
//                desc.prcCalcMethCd.clear();
//                ZYPEZDItemValueSetter.setValue(desc.autoPrcCcyCd, base.autoPrcCcyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(desc.autoPrcAmtRate, promoAmt);
//                desc.maxPrcAmtRate.clear();
//                desc.minPrcAmtRate.clear();
//                ZYPEZDItemValueSetter.setValue(desc.calcPrcAmtRate, promoAmt);
//                ZYPEZDItemValueSetter.setValue(desc.unitPrcAmt, promoAmt.multiply(line.ordQty.getValue()));
//                desc.dsMdsePrcPk.clear();
//                ZYPEZDItemValueSetter.setValue(desc.specCondPrcPk, line.specCondPrcPk.getValue());
//                desc.frtPerWtPk.clear();
//                EZDMsg.copy(desc, null, line.NWXC012003PMsg.no(line.NWXC012003PMsg.getValidCount()), null);
//                line.NWXC012003PMsg.setValidCount(line.NWXC012003PMsg.getValidCount() + 1);
//            }
//        }
//    }

//    private List<Map<?,?>> getMachingBundle(NWXC012001PMsg pMsg, NWXC012002PMsg line, List<Map<?, ?>> ssmResList) {
//        if(ssmResList == null || ssmResList.isEmpty()){
//            return null;
//        }
//        NWXC012002PMsg data;
//        List<Map<?, ?>> list = new ArrayList<Map<?, ?>>();
//        boolean hitFlg = false;
//        for (Map<?, ?> map : ssmResList) {
//            if (isMdseMatching(line, (String) map.get("MDSE_CD"))) {
//                hitFlg = false;
//                for (int i = 0; i < pMsg.NWXC012002PMsg.getValidCount(); i++) {
//                    data = pMsg.NWXC012002PMsg.no(i);
//                    if (isMdseMatching(data, (String) map.get("PRC_QLFY_VAL_TXT"))) {
//                        list.add(map);
//                        hitFlg = true;
//                    }
//                }
//                if (hitFlg == false) {
//                    return null;
//                }
//            }
//        }
//        return list;
//    }
//
//    private void editPromotionBundle(NWXC012001PMsg pMsg, NWXC012002PMsg line, List<Map<?, ?>> list) {
//        NWXC012003PMsg base = null;
//        if(list == null || list.isEmpty()){
//            return;
//        }
//        
//        // Get Base row
//        for (int i = 0; i < line.NWXC012003PMsg.getValidCount(); i++) {
//            NWXC012003PMsg calcBase = line.NWXC012003PMsg.no(i);
//            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
//                base = calcBase;
//            }
//        }
//        Map<?, ?> map = list.get(0); //get first row.
//        if (base == null) {
//            return;
//        }
//        BigDecimal promoAmt = (BigDecimal) map.get("PRMO_AMT");
//
//        NWXC012003PMsg desc = new NWXC012003PMsg();
//        ZYPEZDItemValueSetter.setValue(desc.trxLineNum, line.trxLineNum);
//        ZYPEZDItemValueSetter.setValue(desc.trxLineSubNum, line.trxLineSubNum);
//        desc.prcCondTpCd.clear();
//        desc.prcCondTpDescTxt.clear();
//        ZYPEZDItemValueSetter.setValue(desc.prcDtlGrpCd, PRC_DTL_GRP.DISCOUNT);
//        ZYPEZDItemValueSetter.setValue(desc.prcCatgCd, base.prcCatgCd.getValue());
//        ZYPEZDItemValueSetter.setValue(desc.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(desc.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(desc.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(desc.pkgUomCd, base.pkgUomCd);
//        ZYPEZDItemValueSetter.setValue(desc.prcCondUnitCd, PRC_COND_UNIT.AMT);
//        desc.prcCalcMethCd.clear();
//        ZYPEZDItemValueSetter.setValue(desc.autoPrcCcyCd, base.autoPrcCcyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(desc.autoPrcAmtRate, promoAmt);
//        desc.maxPrcAmtRate.clear();
//        desc.minPrcAmtRate.clear();
//        desc.manPrcAmtRate.clear();
//        ZYPEZDItemValueSetter.setValue(desc.calcPrcAmtRate, promoAmt);
//        ZYPEZDItemValueSetter.setValue(desc.unitPrcAmt, promoAmt.multiply(line.ordQty.getValue()));
//        desc.dsMdsePrcPk.clear();
//        ZYPEZDItemValueSetter.setValue(desc.specCondPrcPk, line.specCondPrcPk.getValue());
//        desc.frtPerWtPk.clear();
//        desc.ordPrcCalcBasePk.clear();
//
//        EZDMsg.copy(desc, null, line.NWXC012003PMsg.no(line.NWXC012003PMsg.getValidCount()), null);
//        line.NWXC012003PMsg.setValidCount(line.NWXC012003PMsg.getValidCount() + 1);
//    }
// DEL END 2015/12/14 #385
// ADD START 2015/12/14 #385
    private Map<String, BigDecimal> hasPromotionDetail(List<NWXC012002PMsg> configList, List<Map< ? , ? >> ssmList, Map<String, BigDecimal> promoMap) {
        if (ssmList == null || ssmList.size() == 0) {
            return promoMap;
        }
        String prcQlfyTp = null;
        String prcQlfyValTxt = null;
        String mdseCd = null;
        BigDecimal promoAmt = BigDecimal.ZERO;
        for (Map< ? , ? > map : ssmList) {
            prcQlfyTp = (String) map.get("PRC_QLFY_TP_CD");
            prcQlfyValTxt = (String) map.get("PRC_QLFY_VAL_TXT");
            mdseCd = (String) map.get("MDSE_CD");
            promoAmt = (BigDecimal) map.get("PRMO_AMT");
            for (NWXC012002PMsg data : configList) {
                if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(prcQlfyTp) && isModelMatching(data, prcQlfyValTxt)) {
                    promoMap = addMap(promoMap, mdseCd, promoAmt);
                    break;
                }
                if (PRC_PRMO_QLFY_TP.ITEM_CODE.equals(prcQlfyTp) && isMdseMatching(data, prcQlfyValTxt)) {
                    promoMap = addMap(promoMap, mdseCd, promoAmt);
                    break;
                }
            }
        }
        return promoMap;
    }

    private Map<String, BigDecimal> hasPromotionBundle(List<NWXC012002PMsg> configList, List<Map< ? , ? >> ssmList, Map<String, BigDecimal> promoMap) {
        if (ssmList == null || ssmList.size() == 0) {
            return promoMap;
        }
        String mdseCd = null;
        String prcQlfyValTxt = null;
        BigDecimal promoAmt = BigDecimal.ZERO;
        boolean adoptFlg = true;
        boolean hitFlg = false;
        for (Map< ? , ? > map : ssmList) {
            prcQlfyValTxt = (String) map.get("PRC_QLFY_VAL_TXT");
            for (NWXC012002PMsg data : configList) {
                if (isMdseMatching(data, prcQlfyValTxt)) {
                    hitFlg = true;
                    break;
                }
            }
            if (!hitFlg) {
                adoptFlg = false;
            }
        }
        if (adoptFlg) {
            // get first row
            Map< ? , ? > map = ssmList.get(0);
            mdseCd = (String) map.get("MDSE_CD");
            promoAmt = (BigDecimal) map.get("PRMO_AMT");
            promoMap = addMap(promoMap, mdseCd, promoAmt);
        }
        return promoMap;
    }

    private Map<String, BigDecimal> addMap(Map<String, BigDecimal> promoMap, String mdseCd, BigDecimal promoAmt) {
        BigDecimal amt = promoMap.get(mdseCd);
        if (amt == null) {
            amt = BigDecimal.ZERO;
        }
        amt = amt.add(promoAmt);
        promoMap.put(mdseCd, amt);
        return promoMap;
    }

    private void editPriceAmount(NWXC012001PMsg param, List<NWXC012002PMsg> configList, Map<String, BigDecimal> promoMap) {
        for (String key : promoMap.keySet()) {
            BigDecimal amt = promoMap.get(key);
            for (NWXC012002PMsg line : configList) {
                if (isMdseMatching(line, key)) {
                    editCalcBase(param, line, amt);
                }
            }
        }
    }

    private void editCalcBase(NWXC012001PMsg param, NWXC012002PMsg line, BigDecimal amt) {
        NWXC012003PMsg base = null;
        NWXC012003PMsg desc = null;
        List<Integer> delList = new ArrayList<Integer>(); // QC#10738 2016/08/29 Add
        for (int i = 0; i < line.NWXC012003PMsg.getValidCount(); i++) {
            NWXC012003PMsg calcBase = line.NWXC012003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                base = calcBase;
            // QC#10738 2016/08/29 Mod Start
            } else if(PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd.getValue())) {
                delList.add(i);
            // QC#10738 2016/08/29 Mod End
            } else if (line.specCondPrcPk.getValue().compareTo(calcBase.specCondPrcPk.getValue()) == 0) {
                desc = calcBase;
            }
        }
        if (base == null) {
            return;
        }
        // QC#10738 2016/08/29 Mod Start
        // delete manual price
        if (delList.size() > 0) {
            ZYPTableUtil.deleteRows(line.NWXC012003PMsg, delList);
            delList.clear();
        }
        // QC#10738 2016/08/29 Mod End
        // QC#25422 2018/05/13 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(base.prcCondManEntryFlg.getValue())) {
            return;
        }
        // QC#25422 2018/05/13 Add End
        if(desc == null){
            desc = line.NWXC012003PMsg.no(line.NWXC012003PMsg.getValidCount());
            line.NWXC012003PMsg.setValidCount(line.NWXC012003PMsg.getValidCount() + 1);
        }
        if(ZYPConstant.FLG_ON_Y.equals(desc.prcCondManEntryFlg.getValue())){
            return;
        }
//        BigDecimal calcPrcAmtRate = base.calcPrcAmtRate.getValue();
//
//        calcPrcAmtRate = calcPrcAmtRate.add(amt);
        if (!isFrozen(line)) {
//            ZYPEZDItemValueSetter.setValue(base.autoPrcAmtRate, price);
//            ZYPEZDItemValueSetter.setValue(base.calcPrcAmtRate, price);
//            ZYPEZDItemValueSetter.setValue(base.unitPrcAmt, multiply(param.glblCmpyCd.getValue(), price, line.ordQty.getValue(), base.autoPrcCcyCd.getValue()));
//            if (calcPrcAmtRate.compareTo(base.unitPrcAmt.getValue()) != 0) {
//                S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
//                lineMap.addXxMsgIdWithPrm(NWZM1355W, new String[] {"Promotion Price Calcuration" });
//                lineMap.flush();
//            }
            BigDecimal price = divide(param.glblCmpyCd.getValue(), amt.multiply(MINUS), line.ordQty.getValue(), base.autoPrcCcyCd.getValue());

            ZYPEZDItemValueSetter.setValue(desc.trxLineNum, line.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(desc.trxLineSubNum, line.trxLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(desc.configCatgCd, line.configCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(desc.prcDtlGrpCd, PRC_DTL_GRP.DISCOUNT);
            ZYPEZDItemValueSetter.setValue(desc.prcCatgCd, line.prcCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(desc.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(desc.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(desc.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(desc.pkgUomCd, base.pkgUomCd.getValue());
            ZYPEZDItemValueSetter.setValue(desc.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(desc.autoPrcCcyCd, base.autoPrcCcyCd.getValue());
            ZYPEZDItemValueSetter.setValue(desc.autoPrcAmtRate, price);
            ZYPEZDItemValueSetter.setValue(desc.calcPrcAmtRate, price.multiply(line.ordQty.getValue()));
            ZYPEZDItemValueSetter.setValue(desc.unitPrcAmt, price);
            ZYPEZDItemValueSetter.setValue(desc.specCondPrcPk, line.specCondPrcPk.getValue());
            // QC#9700  2018/09/03 Add Start
            ZYPEZDItemValueSetter.setValue(desc.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(desc.prcRulePrcdPk, param.prcRulePrcdPk);
            // QC#9700  2018/09/03 Add End
        }
    }

// ADD END 2015/12/14 #385
    private boolean isFrozen(NWXC012002PMsg line) {
        if (BigDecimal.ZERO.compareTo(line.invQty.getValue()) != 0 || ZYPConstant.FLG_ON_Y.equals(line.xxPrcCloFlg.getValue())) {
            return true;
        }
        return false;
    }

//    private boolean isManualEntry(NWXC012002PMsg line) {
//        for (int i = 0; i < line.NWXC012003PMsg.getValidCount(); i++) {
//            NWXC012003PMsg calcBase = line.NWXC012003PMsg.no(i);
//            if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManEntryFlg.getValue())) {
//                return true;
//            }
//        }
//        return false;
//    }

    private boolean isMdseMatching(NWXC012002PMsg line, String mdse) {
        if (ZYPCommonFunc.hasValue(line.ordTakeMdseCd)) {
            if (S21StringUtil.isEquals(mdse, line.ordTakeMdseCd.getValue())) {
                return true;
            }
        } else {
            if (S21StringUtil.isEquals(mdse, line.mdseCd.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isModelMatching(NWXC012002PMsg line, String modelId) {
        if (ZYPCommonFunc.hasValue(line.mdlId)) {
            if (line.mdlId.getValue().compareTo(new BigDecimal(modelId)) == 0) {
                return true;
            }
        }
        return false;
    }

 // ADD START 2015/12/14 #385
//    private BigDecimal multiply(String glblCmpyCd, BigDecimal num1, BigDecimal num2, String dealCcyCd) {
//        if (num1 == null || num2 == null) {
//            return BigDecimal.ZERO;
//        }
//        int scale = getDealCcyDigit(glblCmpyCd, dealCcyCd);
//        num1 = round(num1, scale);
//        return round(num1.multiply(num2), scale);
//    }

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

    private BigDecimal round(BigDecimal unitPrice, int digit) {
        return unitPrice.setScale(digit, BigDecimal.ROUND_HALF_UP);
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

    private boolean isEquals(BigDecimal num1, BigDecimal num2) {

        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }

        if (num1.compareTo(num2) == 0) {
            return true;
        }

        return false;
    }
// ADD END 2015/12/14 #385
}
