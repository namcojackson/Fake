/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC170001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CCYTMsg;
import business.db.COA_PROJTMsg;
import business.db.PRC_FMLATMsg;
import business.db.PRC_FMLATMsgArray;
import business.db.PROD_CTRLTMsg;
import business.parts.NWZC170001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Formula API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/08   Fujitsu         Y.Kanefusa      Create          N/A
 * 2016/03/07   Fujitsu         Y.Kanefusa      Update          QC#4901
 * </pre>
 */
public class NWZC170001 extends S21ApiCommonBase {

    private static final String PROGRAM_ID = "NWZC170001";

    private static final String NWZM1325E = "NWZM1325E";

    private static final String NWZM0977E = "NWZM0977E";

    private static final String NWZM1326E = "NWZM1326E";

    private static final String NWZM1394E = "NWZM1394E";

    private static final String NWZM1395E = "NWZM1395E";

    private static final String NWZM1396E = "NWZM1396E";

    private static final String NWZM1397E = "NWZM1397E";

    public static final String MODE_NO_BASE = "01";

    public static final String MODE_BASE = "02";

    private final S21SsmBatchClient ssmBatchClient;

    private ALL_MDSE_VTMsg allMdseVTMsg;

    private PRC_FMLATMsg prcFmlaTMsg;

    private CCYTMsg ccyTMsg;
    
//    private String dummyPrcCatgCd;

    /** Constructor */
    public NWZC170001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    public void execute(NWZC170001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (checkParam(msgMap)) {
            return;
        }

        formulaPriceCalc(msgMap);
    }

    private boolean checkParam(S21ApiMessageMap msgMap) {
        NWZC170001PMsg pMsg = (NWZC170001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        ZYPEZDItemValueSetter.setValue(pMsg.xxCalcPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDiscPrcAmt, BigDecimal.ZERO);

        if (!MODE_NO_BASE.equals(pMsg.xxModeCd.getValue()) && !MODE_BASE.equals(pMsg.xxModeCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM0977E, new String[] {null });
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Global Company Code" });
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Price Based Date" });
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.prcFmlaPk)) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Price Formula PK" });
            msgMap.flush();
            return true;
        }
        if (MODE_BASE.equals(pMsg.xxModeCd.getValue()) && !ZYPCommonFunc.hasValue(pMsg.basePrcAmt)) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Base Price Amount" });
            msgMap.flush();
            return true;
        }

        // Presence Check
        prcFmlaTMsg = getPrcFmla(pMsg);
        if (prcFmlaTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Price Formula PK", pMsg.prcFmlaPk.getValue().toString(), "PRC_FMLA" });
            msgMap.flush();
            return true;
        }

        if (!PRC_FMLA_TP.PRICE_LIST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue()) 
                && !PRC_FMLA_TP.STANDARD_COST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NWZM1397E, new String[] {"PRC_FMLA", "Price Formula Type Code" });
            msgMap.flush();
            return true;
        }

        if (ZYPCommonFunc.hasValue(pMsg.mdseCd)) {
            allMdseVTMsg= allMdseVTMsg(pMsg);
            if (allMdseVTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Merchandise Code", pMsg.mdseCd.getValue(), "ALL_MDSE_V" });
                msgMap.flush();
                return true;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.coaMdseTpCd)) {
            COA_PROJTMsg coaMdseTp = getCoaMdseTp(pMsg);
            if (coaMdseTp == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"COA Merchandise Type Code", pMsg.coaMdseTpCd.getValue(), "COA_MDSE_TP" });
                msgMap.flush();
                return true;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.firstProdCtrlCd)) {
            PROD_CTRLTMsg prodCtrl = getProdCtrl(glblCmpyCd, pMsg.firstProdCtrlCd.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
            if (prodCtrl == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"First Product Control Code", pMsg.firstProdCtrlCd.getValue(), "PROD_CTRL" });
                msgMap.flush();
                return true;
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.scdProdCtrlCd)) {
            PROD_CTRLTMsg prodCtrl = getProdCtrl(glblCmpyCd, pMsg.firstProdCtrlCd.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
            if (prodCtrl == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Second Product Control Code", pMsg.scdProdCtrlCd.getValue(), "PROD_CTRL" });
                msgMap.flush();
                return true;
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.thirdProdCtrlCd)) {
            PROD_CTRLTMsg prodCtrl = getProdCtrl(glblCmpyCd, pMsg.firstProdCtrlCd.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
            if (prodCtrl == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Third Product Control Code", pMsg.thirdProdCtrlCd.getValue(), "PROD_CTRL" });
                msgMap.flush();
                return true;
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.frthProdCtrlCd)) {
            PROD_CTRLTMsg prodCtrl = getProdCtrl(glblCmpyCd, pMsg.firstProdCtrlCd.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
            if (prodCtrl == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Fourth Product Control Code", pMsg.frthProdCtrlCd.getValue(), "PROD_CTRL" });
                msgMap.flush();
                return true;
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.fifthProdCtrlCd)) {
            PROD_CTRLTMsg prodCtrl = getProdCtrl(glblCmpyCd, pMsg.firstProdCtrlCd.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4);
            if (prodCtrl == null) {
                msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Fifth Product Control Code", pMsg.fifthProdCtrlCd.getValue(), "PROD_CTRL" });
                msgMap.flush();
                return true;
            }
        }

        ccyTMsg = getCcy(pMsg);
        if (ccyTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NWZM1326E, new String[] {"Currency Code", pMsg.ccyCd.getValue(), "CCY" });
            msgMap.flush();
            return true;
        }

//        if (MODE_NO_BASE.equals(pMsg.xxModeCd.getValue()) && PRC_FMLA_TP.PRICE_LIST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue())) {
        if (MODE_NO_BASE.equals(pMsg.xxModeCd.getValue()) && PRC_FMLA_TP.STANDARD_COST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.mdseCd)) {
                msgMap.addXxMsgIdWithPrm(NWZM1394E, new String[] {pMsg.prcFmlaPk.getValue().toString(), pMsg.mdseCd.getValue() });
                msgMap.flush();
                return true;
            }
        }

//        if(MODE_NO_BASE.equals(pMsg.xxModeCd.getValue()) && PRC_FMLA_TP.STANDARD_COST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue())){
        if(MODE_NO_BASE.equals(pMsg.xxModeCd.getValue()) && PRC_FMLA_TP.PRICE_LIST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue())){
            if (ZYPCommonFunc.hasValue(pMsg.mdseCd) 
                    || ZYPCommonFunc.hasValue(pMsg.coaMdseTpCd) 
                    || ZYPCommonFunc.hasValue(pMsg.firstProdCtrlCd) 
                    || ZYPCommonFunc.hasValue(pMsg.scdProdCtrlCd) 
                    || ZYPCommonFunc.hasValue(pMsg.thirdProdCtrlCd)
                    || ZYPCommonFunc.hasValue(pMsg.frthProdCtrlCd) 
                    || ZYPCommonFunc.hasValue(pMsg.fifthProdCtrlCd)) {
            } else {
                msgMap.addXxMsgIdWithPrm(NWZM1396E, new String[] {pMsg.prcFmlaPk.getValue().toString() });
                msgMap.flush();
                return true;
            }
        }
        return false;
    }
    
    private void formulaPriceCalc(S21ApiMessageMap msgMap) {
        NWZC170001PMsg pMsg = (NWZC170001PMsg) msgMap.getPmsg();
        BigDecimal prcAmt = BigDecimal.ZERO;
        BigDecimal calcAmt = BigDecimal.ZERO;

        if (MODE_NO_BASE.equals(pMsg.xxModeCd.getValue())) {
            if (PRC_FMLA_TP.PRICE_LIST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue())) {
                Map<String, Object> mapParam = new HashMap<String, Object>();
                mapParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                mapParam.put("prcCatgCd", prcFmlaTMsg.prcCatgCd.getValue());
                mapParam.put("prcQlfyTpCd01", PRC_QLFY_TP.ITEM_CODE);
                mapParam.put("prcQlfyTpCd02", PRC_QLFY_TP.MERCHANDISE_TYPE);
                mapParam.put("prcQlfyTpCd03", PRC_QLFY_TP.PRODUCT_HIERARCHY_1);
                mapParam.put("prcQlfyTpCd04", PRC_QLFY_TP.PRODUCT_HIERARCHY_2);
                mapParam.put("prcQlfyTpCd05", PRC_QLFY_TP.PRODUCT_HIERARCHY_3);
                mapParam.put("prcQlfyTpCd06", PRC_QLFY_TP.PRODUCT_HIERARCHY_4);
                mapParam.put("prcQlfyTpCd07", PRC_QLFY_TP.PRODUCT_HIERARCHY_5);
                mapParam.put("mdseCd", pMsg.mdseCd.getValue());
                mapParam.put("ordTakeMdse", pMsg.ordTakeMdseCd.getValue());
                mapParam.put("coaMdseTpCd", pMsg.coaMdseTpCd.getValue());
                mapParam.put("firstProdCtrlCd", pMsg.firstProdCtrlCd.getValue());
                mapParam.put("scdProdCtrlCd", pMsg.scdProdCtrlCd.getValue());
                mapParam.put("thirdProdCtrlCd", pMsg.thirdProdCtrlCd.getValue());
                mapParam.put("frthProdCtrlCd", pMsg.frthProdCtrlCd.getValue());
                mapParam.put("fifthProdCtrlCd", pMsg.fifthProdCtrlCd.getValue());
                mapParam.put("ordCustUomQty", pMsg.ordCustUomQty.getValue());
                mapParam.put("pkgUomCd", pMsg.pkgUomCd.getValue());
                mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
                mapParam.put("prcBaseDt", pMsg.prcBaseDt.getValue());
                List<Map< ? , ? >> ssmResList = (List<Map<?, ?>>) ssmBatchClient.queryObjectList("selectPrclListEquipPrcLst", mapParam);
                if (ssmResList == null || ssmResList.isEmpty()) {
                    msgMap.addXxMsgIdWithPrm(NWZM1395E, new String[] {pMsg.prcFmlaPk.getValue().toString(), pMsg.mdseCd.getValue() });
                    msgMap.flush();
                    return;
                }
                if (ssmResList.size() > 1) {
                    msgMap.addXxMsgIdWithPrm(NWZM1395E, new String[] {pMsg.prcFmlaPk.getValue().toString(), pMsg.mdseCd.getValue() });
                    msgMap.flush();
                    return;
                }
                prcAmt = (BigDecimal) ssmResList.get(0).get("PRC_LIST_EQUIP_PRC_AMT");
                calcAmt = calcPrcAmt(prcAmt, prcFmlaTMsg, ccyTMsg);
                ZYPEZDItemValueSetter.setValue(pMsg.xxCalcPrcAmt, calcAmt);
                ZYPEZDItemValueSetter.setValue(pMsg.xxDiscPrcAmt, calcAmt.subtract(prcAmt));
            } else if (PRC_FMLA_TP.STANDARD_COST.equals(prcFmlaTMsg.prcFmlaTpCd.getValue())) {
                prcAmt = allMdseVTMsg.thisMthTotStdCostAmt.getValue();
                calcAmt = calcPrcAmt(prcAmt, prcFmlaTMsg, ccyTMsg);
                ZYPEZDItemValueSetter.setValue(pMsg.xxCalcPrcAmt, calcAmt);
                ZYPEZDItemValueSetter.setValue(pMsg.xxDiscPrcAmt, calcAmt.subtract(prcAmt));
            }
        } else {
            prcAmt = pMsg.basePrcAmt.getValue();
            calcAmt = calcPrcAmt(prcAmt, prcFmlaTMsg, ccyTMsg);
            ZYPEZDItemValueSetter.setValue(pMsg.xxCalcPrcAmt, calcAmt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDiscPrcAmt, calcAmt.subtract(prcAmt));
        }
    }

    private BigDecimal calcPrcAmt(BigDecimal prcAmt, PRC_FMLATMsg prcFmlaTMsg, CCYTMsg ccyTMsg) {
        BigDecimal calcAmt = BigDecimal.ZERO;
        if (PRC_FMLA_OP.ADD.equals(prcFmlaTMsg.prcFmlaOpCd.getValue())) {
            calcAmt = prcAmt.add(prcFmlaTMsg.prcFmlaNum.getValue());
        } else if (PRC_FMLA_OP.SUBTRACT.equals(prcFmlaTMsg.prcFmlaOpCd.getValue())) {
            calcAmt = prcAmt.subtract(prcFmlaTMsg.prcFmlaNum.getValue());
        } else if (PRC_FMLA_OP.MULTIPLY.equals(prcFmlaTMsg.prcFmlaOpCd.getValue())) {
            calcAmt = prcAmt.multiply(prcFmlaTMsg.prcFmlaNum.getValue());
        } else if (PRC_FMLA_OP.DIVIDE.equals(prcFmlaTMsg.prcFmlaOpCd.getValue())) {
            if(BigDecimal.ZERO.compareTo(prcFmlaTMsg.prcFmlaNum.getValue()) == 0){
                return BigDecimal.ZERO;
            }
            calcAmt = prcAmt.divide(prcFmlaTMsg.prcFmlaNum.getValue(), ccyTMsg.aftDeclPntDigitNum.getValueInt(), BigDecimal.ROUND_HALF_UP);
        }
        return calcAmt.setScale(ccyTMsg.aftDeclPntDigitNum.getValueInt(), BigDecimal.ROUND_HALF_UP);
    }

    private PRC_FMLATMsg getPrcFmla(NWZC170001PMsg pMsg) {
        PRC_FMLATMsg inTMsg = new PRC_FMLATMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("prcFmlaPk01", pMsg.prcFmlaPk.getValue());
        inTMsg.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        inTMsg.setConditionValue("effFromDt01", pMsg.prcBaseDt.getValue());
        inTMsg.setConditionValue("effThruDt01", pMsg.prcBaseDt.getValue());
        PRC_FMLATMsgArray tMsgArry = (PRC_FMLATMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);
        if (tMsgArry.length() == 0) {
            return null;
        }
        return (PRC_FMLATMsg) tMsgArry.get(0);
    }

    private ALL_MDSE_VTMsg allMdseVTMsg(NWZC170001PMsg pMsg) {
        ALL_MDSE_VTMsg inTMsg = new ALL_MDSE_VTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("mdseCd01", pMsg.mdseCd.getValue());
        inTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        ALL_MDSE_VTMsgArray tMsgArry = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);
        if (tMsgArry.length() == 0) {
            return null;
        }
        return (ALL_MDSE_VTMsg) tMsgArry.get(0);
    }

    private COA_PROJTMsg getCoaMdseTp(NWZC170001PMsg pMsg) {
        COA_PROJTMsg inTMsg = new COA_PROJTMsg();
        inTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        inTMsg.coaProjCd.setValue(pMsg.coaMdseTpCd.getValue());
        COA_PROJTMsg outTMsg = (COA_PROJTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private PROD_CTRLTMsg getProdCtrl(String glblCmpyCd, String prodCtrlCd, String mdseStruElmntTp) {
        PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prodCtrlCd.setValue(prodCtrlCd);
        inTMsg.mdseStruElmntTpCd.setValue(mdseStruElmntTp);
        PROD_CTRLTMsg outTMsg = (PROD_CTRLTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private CCYTMsg getCcy(NWZC170001PMsg pMsg) {
        CCYTMsg inTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.ccyCd, pMsg.ccyCd.getValue());
        CCYTMsg outTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

}
