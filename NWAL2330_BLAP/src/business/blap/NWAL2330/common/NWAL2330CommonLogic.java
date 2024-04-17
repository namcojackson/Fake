/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2330.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.blap.NWAL2330.NWAL2330CMsg;
import business.blap.NWAL2330.NWAL2330Query;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL2330CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330CommonLogic {

    /**
     * createPullDown
     * 
     * @param bizMsg NWAL2330CMsg
     * @param workType boolean
     */
    public static void createPullDown(NWAL2330CMsg bizMsg, boolean workType) {
        if (workType) {
            // Order Source
            ZYPCodeDataUtil.createPulldownList(CPO_SRC_TP.class, bizMsg.cpoSrcTpCd_L1, bizMsg.cpoSrcTpDescTxt_L1);
            // Order Category
            ZYPCodeDataUtil.createPulldownList(DS_ORD_CATG.class, bizMsg.dsOrdCatgCd_L1, bizMsg.dsOrdCatgDescTxt_L1);
            // Order Reason
            ZYPCodeDataUtil.createPulldownList(DS_ORD_TP.class, bizMsg.dsOrdTpCd_L1, bizMsg.dsOrdTpDescTxt_L1);
        } else {
            // Order Reason
            ZYPCodeDataUtil.createPulldownList(DS_ORD_TP.class, bizMsg.dsOrdTpCd_L1, bizMsg.dsOrdTpDescTxt_L1);
        }
    }

    /**
     * createPullDown
     * 
     * @param bizMsg NWAL2330CMsg
     */
    public static void createPullDown(NWAL2330CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_BK, bizMsg.dsOrdCatgCd_H1);

        String selectCd = bizMsg.dsOrdCatgCd_H1.getValue();
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        S21SsmEZDResult ssmResult = NWAL2330Query.getInstance().getPullDownDataList(selectCd);
        if (ssmResult.isCodeNormal()) {
            listMap = (List<Map<String, String>>) ssmResult.getResultObject();
        }

        int cnt = 0;
        for (int i = 0; i < listMap.size(); i++) {
            if (i == 0) {
                bizMsg.dsOrdTpCd_H1.clear();
                bizMsg.dsOrdTpCd_L1.clear();
                bizMsg.dsOrdTpDescTxt_L1.clear();
            }
            String prcRuleAttrbCd = (String) listMap.get(i).get("DS_ORD_TP_CD");
            String prcRuleAttrbDesctxt = (String) listMap.get(i).get("DS_ORD_TP_DESC_TXT");

            // Mod 20160412 R.Nakamura Start
            if (ZYPCommonFunc.hasValue(prcRuleAttrbCd)
                    && ZYPCommonFunc.hasValue(prcRuleAttrbDesctxt)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_L1.no(cnt), prcRuleAttrbCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_L1.no(cnt), prcRuleAttrbDesctxt);
                cnt++;
            }
            // Mod 20160412 R.Nakamura End
        }
    }
}
