/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7290.common;

import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_LINE_BIZ_TP;
import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_PRC_PRCD_ACT_TP;
import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_PRC_RULE_CATG;
import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_PRC_RULE_COND_TP;
import parts.common.EZDFStringItem;
import business.blap.NMAL7290.NMAL7290CMsg;
import business.blap.NMAL7290.constant.NMAL7290Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/**
 *<pre>
 * NMAL7290CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/08/15   SRAA            K.Aratani       Update          S21_NA#13311
 * 2016/09/27   Fujitsu         R.Nakamura      Update          QC#6931
 *</pre>
 */
public class NMAL7290CommonLogic {

    /**
     * Create Pull Down List
     * @param bizMsg NMAL7290CMsg
     */
    public static void createPullDown(NMAL7290CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(TBL_PRC_PRCD_ACT_TP, bizMsg.prcPrcdActTpCd_L, bizMsg.prcPrcdActTpDescTxt_L); // QC#9694 2016/07/08 Add
        ZYPCodeDataUtil.createPulldownList(TBL_PRC_RULE_CATG, bizMsg.prcRuleCatgCd_L, bizMsg.prcRuleCatgDescTxt_L);
        ZYPCodeDataUtil.createPulldownList(TBL_LINE_BIZ_TP, bizMsg.lineBizTpCd_L, bizMsg.lineBizTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList(TBL_PRC_RULE_COND_TP, bizMsg.prcRuleCondTpCd_L, bizMsg.prcRuleCondTpDescTxt_L);

        //ZYPCodeDataUtil.createPulldownList(TBL_PRC_RULE_OP_TP, bizMsg.prcRuleOpTpCd_L, bizMsg.prcRuleOpTpDescTxt_L); // QC#9694 2016/07/08 Del
        //ZYPCodeDataUtil.createPulldownList(TBL_PRC_RULE_EVTR_TP, bizMsg.prcRuleEvtrTpCd_L, bizMsg.prcRuleEvtrTpDescTxt_L);// QC#9694 2016/07/08 Del
    }

    /**
     * Get Status
     * @param actvFlg String
     * @param effFromDt String
     * @param effThruDt String
     * @return status
     */
    public static String getStatus(String actvFlg, String effFromDt, String effThruDt) {
        String status = "Inactive";
        String slsDt = ZYPDateUtil.getSalesDate();
        //QC#13311
        if (!ZYPCommonFunc.hasValue(effThruDt)) {
        	effThruDt = "99991231";
        }
        if (ZYPConstant.FLG_ON_Y.equals(actvFlg) && 0 <= ZYPDateUtil.compare(slsDt, effFromDt) && 0 >= ZYPDateUtil.compare(slsDt, effThruDt)) {
            status = "Active";
        }
        return status;
    }

    // Add Start 2016/09/27 QC#6931
    public static String convertDateFormat(String dt) {
        return dt == null ? null : dt.replaceAll(NMAL7290Constant.DT_CONV_FORMAT[0], NMAL7290Constant.DT_CONV_FORMAT[1]);
    }

    public static void setStringItem(EZDFStringItem strItem, String setValue) {
        if (ZYPCommonFunc.hasValue(setValue)) {
            ZYPEZDItemValueSetter.setValue(strItem, setValue);
        } else {
            strItem.clear();
        }
    }
    // Add End 2016/09/27 QC#6931
}
