package business.blap.NSAL1070.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NSAL1070.NSAL1070CMsg;
import business.blap.NSAL1070.NSAL1070Query;
import business.blap.NSAL1070.NSAL1070SMsg;
import business.blap.NSAL1070.NSAL1070_ACMsg;
import business.blap.NSAL1070.NSAL1070_ASMsg;
import business.blap.NSAL1070.NSAL1070_BSMsg;
import business.blap.NSAL1070.constant.NSAL1070Constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/26/2015   Fujitsu         M.Hara          Create          N/A
 * 2015/12/09   Hitachi         K.Yamada        Update          CSA QC#1651
 * 2015/12/21   Hitachi         T.Tsuchida      Update          CSA QC#2292
 * 2016/02/23   Hitachi         T.Tsuchida      Update          CSA QC#2382
 * 2016/02/29   Hitachi         K.Kasai         Update          CSA QC#2684
 * 2017/09/28   Hitachi         K.Kojima        Update          QC#18376
 * 2017/12/06   Hitachi         K.Yamada        Update          QC#22891
 * 2018/05/22   Hitachi         K.Kitachi       Update          QC#26070
 *</pre>
 */
public class NSAL1070CommonLogic {

    /**
     * searchXsMtrList
     * @param cMsg NSAL1070CMsg
     * @param sMsg NSAL1070SMsg
     * @param glblCmpyCd String
     */
    public static void searchXsMtrList(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg, String glblCmpyCd) {

        if (cMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.AGGREGATE)) {
            // Aggregate
            srchCalcXsMtrListForArrg(cMsg, sMsg, glblCmpyCd);
        } else {
            // Other
            srchCalcXsMtrList(cMsg, sMsg, glblCmpyCd);
        }

    }

    /**
     * srchCalcXsMtrList
     * @param cMsg NSAL1070CMsg
     * @param sMsg NSAL1070SMsg
     * @param glblCmpyCd String
     */
    public static void srchCalcXsMtrList(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());

        S21SsmEZDResult ssmResult = NSAL1070Query.getInstance().searchXsMtrList(ssmParam, sMsg);
        if (ssmResult.isCodeNormal()) {
            // START 2017/09/28 K.Kojima [QC#18376,MOD]
            // calcXsMtrList(cMsg, sMsg);
            calcXsMtrList(cMsg, sMsg, glblCmpyCd);
            // END 2017/09/28 K.Kojima [QC#18376,MOD]
        } else {
            cMsg.setMessageInfo(NSAL1070Constant.NZZM0000E);
        }
    }

    /**
     * calcXsMtrList
     * @param cMsg NSAL1070CMsg
     * @param sMsg NSAL1070SMsg
     * @param glblCmpyCd String
     */
    // START 2017/09/28 K.Kojima [QC#18376,MOD]
    // public static void calcXsMtrList(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg) {
    public static void calcXsMtrList(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg, String glblCmpyCd) {
    // END 2017/09/28 K.Kojima [QC#18376,MOD]

        NSAL1070_ACMsg acMsg;
        NSAL1070_ASMsg asMsg;
        BigDecimal newRatio, newRate;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            asMsg = sMsg.A.no(i);
            acMsg = cMsg.A.no(i);

            // mod start 2016/02/29 CSA Defect#2684
            ZYPEZDItemValueSetter.setValue(acMsg.mtrLbDescTxt_A1, asMsg.mtrLbDescTxt_A1);
            // mod end 2016/02/29 CSA Defect#2684
            ZYPEZDItemValueSetter.setValue(acMsg.xsMtrCopyQty_A1, asMsg.xsMtrCopyQty_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.xsMtrAmtRate_A1, asMsg.xsMtrAmtRate_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.mtrPrcUpRatio_A1, cMsg.mtrPrcUpRatio);
            ZYPEZDItemValueSetter.setValue(acMsg.newXsCopyQty_A1, asMsg.xsMtrCopyQty_A1);

            // START 2017/09/28 K.Kojima [QC#18376,ADD]
            if (ZYPCommonFunc.hasValue(cMsg.rnwPrcMethCd) && cMsg.rnwPrcMethCd.getValue().equals(RNW_PRC_METH.MARKUP_PERCENT)) {
            // END 2017/09/28 K.Kojima [QC#18376,ADD]

                // mod start 2015/12/09 CSA Defect#1651
                if (ZYPCommonFunc.hasValue(cMsg.mtrPrcUpRatio)) {
                    newRatio = cMsg.mtrPrcUpRatio.getValue().add(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
                    newRate = asMsg.xsMtrAmtRate_A1.getValue().multiply(newRatio).divide(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
                } else {
                    newRate = asMsg.xsMtrAmtRate_A1.getValue();
                }
                // mod end 2015/12/09 CSA Defect#1651

            // START 2017/09/28 K.Kojima [QC#18376,ADD]
            } else if (ZYPCommonFunc.hasValue(cMsg.rnwPrcMethCd) && cMsg.rnwPrcMethCd.getValue().equals(RNW_PRC_METH.MODEL_PERCENT)) {
                if (cMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.FLEET)) {
                    // START 2017/12/06 [QC#22891,MOD]
                    //newRatio = NSAL1070Query.getInstance().getUplftMtrPrcUpRatioForLine(glblCmpyCd, cMsg.dsContrDtlPk.getValue(), asMsg.bllgMtrLbCd_A1.getValue());
                    newRatio = ZYPCodeDataUtil.getNumConstValue("DEF_MDL_RULE_USAGE", glblCmpyCd);
                    // END 2017/12/06 [QC#22891,MOD]
                } else {
                    newRatio = NSAL1070Query.getInstance().getUplftMtrPrcUpRatio(glblCmpyCd, cMsg.dsContrDtlPk.getValue(), asMsg.bllgMtrLbCd_A1.getValue());
                }
                ZYPEZDItemValueSetter.setValue(acMsg.mtrPrcUpRatio_A1, newRatio);
                newRatio = newRatio.add(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
                newRate = asMsg.xsMtrAmtRate_A1.getValue().multiply(newRatio).divide(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
            } else {
                newRate = asMsg.xsMtrAmtRate_A1.getValue();
            }
            // END 2017/09/28 K.Kojima [QC#18376,ADD]

            // START 2018/05/22 K.Kitachi [QC#26070, MOD]
//            ZYPEZDItemValueSetter.setValue(acMsg.newXsMtrAmtRate_A1, newRate);
            ZYPEZDItemValueSetter.setValue(acMsg.newXsMtrAmtRate_A1, newRate.setScale(acMsg.getAttr("newXsMtrAmtRate_A1").getFracDigit(), RoundingMode.HALF_UP));
            // END 2018/05/22 K.Kitachi [QC#26070, MOD]
        }

        cMsg.A.setValidCount(sMsg.A.getValidCount());
    }

    /**
     * srchCalcXsMtrListForArrg
     * @param cMsg NSAL1070CMsg
     * @param sMsg NSAL1070SMsg
     * @param glblCmpyCd String
     */
    private static void srchCalcXsMtrListForArrg(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        // START 2016/02/23 T.Tsuchida [QC#2382, ADD]
        ssmParam.put("dsContrDtlTpCdIsAgg", DS_CONTR_DTL_TP.AGGREGATE);
        // END 2016/02/23 T.Tsuchida [QC#2382, ADD]

        S21SsmEZDResult ssmResult = NSAL1070Query.getInstance().searchXsMtrListForArrg(ssmParam, sMsg);
        if (ssmResult.isCodeNormal()) {
            // START 2017/09/28 K.Kojima [QC#18376,MOD]
            // calcXsMtrListForArrg(cMsg, sMsg);
            calcXsMtrListForArrg(cMsg, sMsg, glblCmpyCd);
            // END 2017/09/28 K.Kojima [QC#18376,MOD]
        } else {
            cMsg.setMessageInfo(NSAL1070Constant.NZZM0000E);
        }
    }

    /**
     * calcXsMtrListForArrg
     * @param cMsg NSAL1070CMsg
     * @param sMsg NSAL1070SMsg
     * @param glblCmpyCd String
     */
    // START 2017/09/28 K.Kojima [QC#18376,MOD]
    // public static void calcXsMtrListForArrg(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg) {
    public static void calcXsMtrListForArrg(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg, String glblCmpyCd) {
    // END 2017/09/28 K.Kojima [QC#18376,MOD]

        NSAL1070_BSMsg bsMsg;

        // KEY is DS_CONTR_PRC_EFF_PK
        LinkedHashMap<BigDecimal, LinkedHashMap<String, List<NSAL1070_BSMsg>>> aggMtrMap
                    = new LinkedHashMap<BigDecimal, LinkedHashMap<String, List<NSAL1070_BSMsg>>>();
        LinkedHashMap<String, List<NSAL1070_BSMsg>> dsContrBllgMtrMap, topDsContrBllgMtrMap;
        List<NSAL1070_BSMsg> dsContrPrcEffMtrList;
        BigDecimal keyDsContrPrcEffPk;
        String keyMtrId;

        // START 2016/02/23 T.Tsuchida [QC#2382, MOD]
//        topDsContrBllgMtrMap = null;
        topDsContrBllgMtrMap = new LinkedHashMap<String, List<NSAL1070_BSMsg>>();
        // END 2016/02/23 T.Tsuchida [QC#2382, MOD]
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            bsMsg = sMsg.B.no(i);

            if (!ZYPCommonFunc.hasValue(bsMsg.xsMtrCopyQty_B1)) {
                // header's row is continue;
                continue;
            }

            // *****************************************************************
            // Get DS_CONTR_PRC_EFF_PK
            // *****************************************************************
            keyDsContrPrcEffPk = bsMsg.dsContrPrcEffPk_B1.getValue();
            dsContrBllgMtrMap = aggMtrMap.get(keyDsContrPrcEffPk);
            if (dsContrBllgMtrMap == null) {
                dsContrBllgMtrMap = new LinkedHashMap<String, List<NSAL1070_BSMsg>>();
                aggMtrMap.put(keyDsContrPrcEffPk, dsContrBllgMtrMap);
            }

            // START 2016/02/23 T.Tsuchida [QC#2382, DEL]
//            if (topDsContrBllgMtrMap == null) {
//                topDsContrBllgMtrMap = dsContrBllgMtrMap;
//            }
            // END 2016/02/23 T.Tsuchida [QC#2382, DEL]

            // *****************************************************************
            // Get & Add DS_CONTR_BLLG_MTR_ID
            // *****************************************************************
            keyMtrId = bsMsg.dsContrBllgMtrId_B1.getValue();

            dsContrPrcEffMtrList = dsContrBllgMtrMap.get(keyMtrId);
            if (dsContrPrcEffMtrList == null) {
                dsContrPrcEffMtrList = new ArrayList<NSAL1070_BSMsg>();
                dsContrBllgMtrMap.put(keyMtrId, dsContrPrcEffMtrList);
            }

            dsContrPrcEffMtrList.add(bsMsg);

            // START 2016/02/23 T.Tsuchida [QC#2382, ADD]
            topDsContrBllgMtrMap.put(keyMtrId, dsContrBllgMtrMap.get(keyMtrId));
            // END 2016/02/23 T.Tsuchida [QC#2382, ADD]
        }

        // *****************************************************************
        // Calculated Total Quantity 
        // *****************************************************************
        LinkedHashMap<String, List<NSAL1070_ACMsg>> sumCMsgMap = new LinkedHashMap<String, List<NSAL1070_ACMsg>>();
        List<NSAL1070_ACMsg> sumCMsgList;
        NSAL1070_ACMsg sumCMsg;
        List<NSAL1070_BSMsg> mtrList;
        int itemIdx;
        BigDecimal sumQty;

        for (Map.Entry<String, List<NSAL1070_BSMsg>> topMtrMap : topDsContrBllgMtrMap.entrySet()) {

            // Loop DS_CONTR_BLLG_MTR_ID 
            itemIdx = 0;
            // Get DS_CONTR_BLLG_MTR_ID
            keyMtrId = topMtrMap.getKey();
            sumCMsgList = new ArrayList<NSAL1070_ACMsg>();

            for (NSAL1070_BSMsg bsTopMsg : topMtrMap.getValue()) {
                // Loop DS_CONTR_BLLG_MTR_ID Index
                sumCMsg = null;

                for (Map.Entry<BigDecimal, LinkedHashMap<String, List<NSAL1070_BSMsg>>> targetAggMtrMap : aggMtrMap.entrySet()) {
                    // Find Same DS_CONTR_BLLG_MTR_ID From DS_CONTR_PRC_EFF_PK's List
                    mtrList = targetAggMtrMap.getValue().get(keyMtrId);

                    if (mtrList == null || mtrList.size() < topMtrMap.getValue().size()) {
                        // Item Count < top Item Count
                        continue;
                    }

                    if (sumCMsg == null) {
                        sumCMsg = new NSAL1070_ACMsg();

                        // mod start 2016/02/29 CSA Defect#2684
                        ZYPEZDItemValueSetter.setValue(sumCMsg.mtrLbDescTxt_A1, bsTopMsg.mtrLbDescTxt_B1);
                        // mod end 2016/02/29 CSA Defect#2684
                        ZYPEZDItemValueSetter.setValue(sumCMsg.xsMtrCopyQty_A1, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(sumCMsg.xsMtrAmtRate_A1, bsTopMsg.xsMtrAmtRate_B1);
                        ZYPEZDItemValueSetter.setValue(sumCMsg.mtrPrcUpRatio_A1, cMsg.mtrPrcUpRatio);
                        // START 2017/09/28 K.Kojima [QC#18376,ADD]
                        ZYPEZDItemValueSetter.setValue(sumCMsg.bllgMtrLbCd_A1, keyMtrId);
                        // END 2017/09/28 K.Kojima [QC#18376,ADD]
                    }

                    // Add Same Array Index Quantity
                    sumQty = sumCMsg.xsMtrCopyQty_A1.getValue();
                    sumQty = sumQty.add(mtrList.get(itemIdx).xsMtrCopyQty_B1.getValue());
                    ZYPEZDItemValueSetter.setValue(sumCMsg.xsMtrCopyQty_A1, sumQty);
                }

                if (sumCMsg != null) {
                    sumCMsgList.add(sumCMsg);
                }

                itemIdx++;
            }

            sumCMsgMap.put(keyMtrId, sumCMsgList);
        }

        // *****************************************************************
        // Set New Value And CMsg.B
        // *****************************************************************
        itemIdx = 0;
        BigDecimal newRatio, newRate;
        for (Map.Entry<String, List<NSAL1070_ACMsg>> acMsgList : sumCMsgMap.entrySet()) {
            for (NSAL1070_ACMsg acMsg : acMsgList.getValue()) {

                ZYPEZDItemValueSetter.setValue(acMsg.newXsCopyQty_A1 , acMsg.xsMtrCopyQty_A1);

                // START 2017/09/28 K.Kojima [QC#18376,ADD]
                if (ZYPCommonFunc.hasValue(cMsg.rnwPrcMethCd) && cMsg.rnwPrcMethCd.getValue().equals(RNW_PRC_METH.MARKUP_PERCENT)) {
                // END 2017/09/28 K.Kojima [QC#18376,ADD]

                    // mod start 2015/12/21 CSA Defect#2292
                    if (ZYPCommonFunc.hasValue(cMsg.mtrPrcUpRatio)) {
                        newRatio = cMsg.mtrPrcUpRatio.getValue().add(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
                        newRate = acMsg.xsMtrAmtRate_A1.getValue().multiply(newRatio).divide(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
                    } else {
                        newRate = acMsg.xsMtrAmtRate_A1.getValue();
                    }
                    // mod end 2015/12/21 CSA Defect#2292

                // START 2017/09/28 K.Kojima [QC#18376,ADD]
                } else if (ZYPCommonFunc.hasValue(cMsg.rnwPrcMethCd) && cMsg.rnwPrcMethCd.getValue().equals(RNW_PRC_METH.MODEL_PERCENT)) {
                    // START 2017/12/06 [QC#22891,MOD]
                    //newRatio = NSAL1070Query.getInstance().getUplftMtrPrcUpRatioForLine(glblCmpyCd, cMsg.dsContrDtlPk.getValue(), acMsg.bllgMtrLbCd_A1.getValue());
                    newRatio = ZYPCodeDataUtil.getNumConstValue("DEF_MDL_RULE_USAGE", glblCmpyCd);
                    // END 2017/12/06 [QC#22891,MOD]
                    ZYPEZDItemValueSetter.setValue(acMsg.mtrPrcUpRatio_A1, newRatio);
                    newRatio = newRatio.add(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
                    newRate = acMsg.xsMtrAmtRate_A1.getValue().multiply(newRatio).divide(BigDecimal.valueOf(NSAL1070Constant.PERCENT_100));
                } else {
                    newRate = acMsg.xsMtrAmtRate_A1.getValue();
                }
                // END 2017/09/28 K.Kojima [QC#18376,ADD]

                // START 2018/05/22 K.Kitachi [QC#26070, MOD]
//                ZYPEZDItemValueSetter.setValue(acMsg.newXsMtrAmtRate_A1, newRate);
                ZYPEZDItemValueSetter.setValue(acMsg.newXsMtrAmtRate_A1, newRate.setScale(acMsg.getAttr("newXsMtrAmtRate_A1").getFracDigit(), RoundingMode.HALF_UP));
                // END 2018/05/22 K.Kitachi [QC#26070, MOD]

                EZDMsg.copy(acMsg, null, cMsg.A.get(itemIdx), null);
                itemIdx++;
            }
        }

        cMsg.A.setValidCount(itemIdx);
    }

}
