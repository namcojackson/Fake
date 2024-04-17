package business.blap.NWAL1900.common;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1900.NWAL1900CMsg;
import business.blap.NWAL1900.NWAL1900Query;
import business.blap.NWAL1900.NWAL1900SMsg;
import business.blap.NWAL1900.NWAL1900_BCMsg;
import business.blap.NWAL1900.NWAL1900_LCMsg;
import static business.blap.NWAL1900.constant.NWAL1900Constant.PRC_ANLS_TRX_TP;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_MOD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
/**
 *<pre>
 * NWAL1900Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/13   Fujitsu         H.Kumagai       Create          N/A
 * 2018/11/27   Fujitsu         M.Ishii         Update          QC#29361
 * 2018/12/28   Fujitsu         Y.Kanefusa      Update          S21_NA#29752
 *</pre>
 */
public class NWAL1900CommonLogic {


    public static boolean isCharges(String prcDtlGrpCd) {
        return PRC_DTL_GRP.FREIGHT.equals(prcDtlGrpCd) //
                || PRC_DTL_GRP.SPECIAL_CHARGE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.RESTOCKING_FEE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.HANDLING_FEE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.FUEL_SURCHARGE.equals(prcDtlGrpCd)
                || PRC_DTL_GRP.SHIPPING_FEE.equals(prcDtlGrpCd);
    }

    public static void createPullDownList(String glblCmpyCd, NWAL1900CMsg bizMsg){
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TRX_TP.class, bizMsg.prcGrpTrxTpCd_L1, bizMsg.prcGrpTrxTpDescTxt_L1);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrxTpCd_H1, (String) bizMsg.prcGrpTrxTpCd_L1.no(0).getValue());

    }

    public static void setTempTable(String glblCmpyCd, NWAL1900CMsg bizMsg, NWAL1900SMsg glblMsg) {

        int index = 0;
        // 2018/11/27 QC#29361 Del Start
//        boolean manualAdj = false;
        // 2018/11/27 QC#29361 Del Start
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            NWAL1900_LCMsg calcBase = bizMsg.L.no(i);
            if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd_PL.getValue())) {
                if (PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd_PL.getValue())) {
                    // 2018/11/27 QC#29361 Del Start
//                    manualAdj = true;
                    // 2018/11/27 QC#29361 Del End
                } else {
                    S21SsmEZDResult ssmResult = NWAL1900Query.getInstance().getOrdPrcCalcBase(glblCmpyCd, calcBase.specCondPrcPk_PL.getValue());
                    if (ssmResult.isCodeNormal()) {
                        Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
                        setTempData(calcBase, rsltMap, bizMsg, index);
                        index++;
                    }
                }
            } else if (NWAL1900CommonLogic.isCharges(calcBase.prcDtlGrpCd_PL.getValue())) {
                S21SsmEZDResult ssmResult = NWAL1900Query.getInstance().getOrdPrcCalcBase(glblCmpyCd, calcBase.specCondPrcPk_PL.getValue());
                if (ssmResult.isCodeNormal()) {
                    Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
                    setTempData(calcBase, rsltMap, bizMsg, index);
                    index++;
                }
            }
        }
        bizMsg.B.setValidCount(index);
        // 2018/11/27 QC#29361 Del Start
//        if (manualAdj) {
//            NWAL1900_ASMsg row = glblMsg.A.no(0);
//            ZYPEZDItemValueSetter.setValue(row.prcRuleNm_A, NWAL1900Constant.MANUAL_ADJUSTMENT);
//            ZYPEZDItemValueSetter.setValue(row.xxCmntTxt_A3, NWAL1900Constant.APPLIED);
//            glblMsg.A.setValidCount(1);
//        }
        // 2018/11/27 QC#29361 Del End

    }
    
    private static void setTempData(NWAL1900_LCMsg calcBase, Map<String, Object> rsltMap, NWAL1900CMsg bizMsg, int index){
        NWAL1900_BCMsg row = bizMsg.B.no(index);
        ZYPEZDItemValueSetter.setValue(row.prcRuleHdrPk_B, (BigDecimal) rsltMap.get("PRC_RULE_HDR_PK"));
        ZYPEZDItemValueSetter.setValue(row.prcAdjDtlPk_B, (BigDecimal) rsltMap.get("PRC_ADJ_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(row.prcRuleApplyFlg_B, calcBase.prcRuleApplyFlg_PL);
        ZYPEZDItemValueSetter.setValue(row.prcRulePrcdPk_B, calcBase.prcRulePrcdPk_PL);
    }
    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL1900CMsg bizMsg = (NWAL1900CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg csMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(csMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }
    // 2018/11/27 QC#29361 Add Start
    public static boolean chkMnlAdj(NWAL1900CMsg bizMsg, NWAL1900SMsg glblMsg) {
        // QC#29752 2018/12/28 Add Start
        if(!S21StringUtil.isEquals(PRC_RULE_MOD_TP.SELL_PRICE, bizMsg.prcRuleModTpCd.getValue())){
            return false;
        }
        // QC#29752 2018/12/28 Add End
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            NWAL1900_LCMsg calcBase = bizMsg.L.no(i);
            if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd_PL.getValue())) {
                if (PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd_PL.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getTrxRuleTp(String glblCmpyCd, NWAL1900CMsg bizMsg) {
        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(glblCmpyCd, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (tMsg == null) {
            return null;
        }
        return tMsg.firstBizCtxAttrbTxt.getValue();
    }
    
    private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("ordCatgCtxTpCd01", PRC_ANLS_TRX_TP);
        inTMsg.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        inTMsg.setConditionValue("dsOrdTpCd01", dsOrdTpCd);
        ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }
    // 2018/11/27 QC#29361 Add End
}
