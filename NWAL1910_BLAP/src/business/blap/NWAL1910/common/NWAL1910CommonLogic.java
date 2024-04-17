/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1910.common;

import static business.blap.NWAL1910.constant.NWAL1910Constant.MODE_ORDER_ENTRY;
import static business.blap.NWAL1910.constant.NWAL1910Constant.MODE_SCHEDULE_AGREEMENT;
import static business.blap.NWAL1910.constant.NWAL1910Constant.MODE_SUPPLY_QUOTE;
import static business.blap.NWAL1910.constant.NWAL1910Constant.DEFAULT_LINE_NUM;
import static business.blap.NWAL1910.constant.NWAL1910Constant.YYYYMMDDHHMISS;
import static business.blap.NWAL1910.constant.NWAL1910Constant.NZZM0001W;
import static business.blap.NWAL1910.constant.NWAL1910Constant.PROCESS_LVL_HEADER;
import static business.blap.NWAL1910.constant.NWAL1910Constant.ZZZM9006E;
import static business.blap.NWAL1910.constant.NWAL1910Constant.NWAM0006I;
import parts.common.EZDMsg;
import business.blap.NWAL1910.NWAL1910CMsg;
import business.blap.NWAL1910.NWAL1910Query;
import business.blap.NWAL1910.NWAL1910SMsg;
import business.blap.NWAL1910.NWAL1910_ASMsg;
import business.blap.NWAL1910.NWAL1910_ASMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/12   Fujitsu         M.Ishii            Create          N/A
 *</pre>
 */
public class NWAL1910CommonLogic {
    /**
     * priceHistorySearch
     * @param bizMsg NWAL1910CMsg
     * @param glblMsg NWAL1910SMsg
     * @param glblCmpyCd String
     */
    public static void priceHistorySearch(NWAL1910CMsg bizMsg, NWAL1910SMsg glblMsg, String glblCmpyCd) {
        S21SsmEZDResult ssmResult;
        if (MODE_ORDER_ENTRY.equals(bizMsg.xxViewChngLogSrcCd.getValue())) {
            ssmResult = NWAL1910Query.getInstance().getFromOrderEntry(bizMsg, glblMsg, glblCmpyCd);

        } else if (MODE_SUPPLY_QUOTE.equals(bizMsg.xxViewChngLogSrcCd.getValue())) {
            ssmResult = NWAL1910Query.getInstance().getFromSupplyQuote(bizMsg, glblMsg, glblCmpyCd);

        } else if (MODE_SCHEDULE_AGREEMENT.equals(bizMsg.xxViewChngLogSrcCd.getValue())) {
            ssmResult = NWAL1910Query.getInstance().getFromScheduleAgreement(bizMsg, glblMsg, glblCmpyCd);

        } else {
            // src display is invalid.
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"Original Screen Identifying Code" });
            return;
        }
        if (ssmResult.isCodeNormal()) {

            // Maximum acquisition number exceeded message
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                queryResCnt = glblMsg.A.length();
                bizMsg.setMessageInfo(NZZM0001W);
            }

            //convert Line#
            convertDispLineNum(glblMsg);

            // clear header info
            clearHeaderConfigLineNum(bizMsg);

            // convert date format
            formatTimestampForAGlblMsg(glblMsg.A);

            // Search results copy
            int i = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Set page
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * convertDispLineNum
     * @param glblMsg NWAL1910SMsg
     */
    public static void convertDispLineNum(NWAL1910SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(glblMsg.dsOrdPosnNum)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdPosnNum, (String) DEFAULT_LINE_NUM);
        }

        String lineNum = glblMsg.dsOrdPosnNum.getValue();

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            StringBuilder dispLineNum = new StringBuilder();
            dispLineNum.append(lineNum);
            dispLineNum.append(".");
            dispLineNum.append(glblMsg.A.no(i).xxLineNum_A.getValue());
            glblMsg.A.no(i).xxLineNum_A.setValue(dispLineNum.toString());
        }
    }
    /**
     * clearHeaderConfigLineNum
     * @param bizMsg NWAL1910CMsg
     */
    public static void clearHeaderConfigLineNum(NWAL1910CMsg bizMsg) {
        if (PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd.getValue())) {
            bizMsg.dsOrdPosnNum.clear();
            bizMsg.xxLineNum.clear();
        }
    }
    /**
     * formatTimestampForAGlblMsg
     * @param aGlblMsgAry NWAL1910_ASMsgArray
     */
    public static void formatTimestampForAGlblMsg(NWAL1910_ASMsgArray aGlblMsgAry) {
        for (int i = 0; i < aGlblMsgAry.getValidCount(); i++) {
            NWAL1910_ASMsg aGlblMsg = aGlblMsgAry.no(i);
            String timestamp = S21StringUtil.subStringByLength(aGlblMsg.xxDtTm_A.getValue(), 0, YYYYMMDDHHMISS.length());
            ZYPEZDItemValueSetter.setValue(aGlblMsg.xxDtTm_A, ZYPDateUtil.formatEzd14ToDisp(timestamp, true, true, true));
        }
    }
}
