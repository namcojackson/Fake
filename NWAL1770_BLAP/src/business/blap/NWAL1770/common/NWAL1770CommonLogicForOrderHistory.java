/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.NUM_OF_LINE_HIST;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForOrderHistory;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/20   Hitachi         T.Fukuta        Create          CSA-QC#61467
 * </pre>
 */
public class NWAL1770CommonLogicForOrderHistory {

    /**
     * Search Order History
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static void searchOrderHistory(NWAL1770CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.H);

        if (!ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd) || !ZYPCommonFunc.hasValue(bizMsg.splyQuoteCatgCd)) {
            return;
        }

        NWAL1770QueryForOrderHistory query = NWAL1770QueryForOrderHistory.getInstance();

        S21SsmEZDResult ssmResult1 = query.getOrderHistory(bizMsg);
        if (ssmResult1.isCodeNotFound()) {
            return;
        }

        int validCount = 0;
        List<Map<String, Object>> orderList = (List<Map<String, Object>>) ssmResult1.getResultObject();
        ORDER_LOOP: for (Map<String, Object> order : orderList) {
            String ordNum = (String)order.get("CPO_ORD_NUM");
            S21SsmEZDResult ssmResult2 = query.getOrderLineHistory(bizMsg, ordNum);
            if (ssmResult2.isCodeNotFound()) {
                continue;
            }
 
            List<Map<String, Object>> lineList = (List<Map<String, Object>>) ssmResult2.getResultObject();
            int listSize = lineList.size();
            int remainCount = NUM_OF_LINE_HIST - validCount;
            int index;
            if (remainCount > listSize) {
                index = listSize - 1;
            } else {
                index = remainCount - 1;
            }
            for (; index >= 0; index--) {
                Map<String, Object> line = lineList.get(index);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).cpoOrdNum_H, (String)line.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).cpoOrdNum_HL, (String)line.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).xxLineNum_H, (String)line.get("LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).mdseCd_H, (String)line.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).mnfItemCd_H, (String)line.get("ORIG_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).mdseDescShortTxt_H, (String)line.get("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).ordCustUomQty_H, (BigDecimal)line.get("ORD_QTY"));
                validCount++;

                if (validCount == NUM_OF_LINE_HIST) {
                    break ORDER_LOOP;
                }
            }
        }
        bizMsg.H.setValidCount(validCount);
    }
}
