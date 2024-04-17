/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0170.common;

import static business.blap.NFDL0170.constant.NFDL0170Constant.NFCM0048I;
import static business.blap.NFDL0170.constant.NFDL0170Constant.NZZM0001W;
import static business.blap.NFDL0170.constant.NFDL0170Constant.PERCENT;
import parts.common.EZDMsg;
import business.blap.NFDL0170.NFDL0170CMsg;
import business.blap.NFDL0170.NFDL0170Query;
import business.blap.NFDL0170.NFDL0170SMsg;
import business.blap.NFDL0170.NFDL0170_ACMsg;
import business.blap.NFDL0170.NFDL0170_ACMsgArray;
import business.blap.NFDL0170.NFDL0170_ASMsg;
import business.blap.NFDL0170.NFDL0170_ASMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 *</pre>
 */
public class NFDL0170CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NFDL0170CMsg cMsg, NFDL0170_ACMsgArray cMsgArray, NFDL0170_ASMsgArray sMsgArray) {

        NFDL0170CMsg bizMsg = (NFDL0170CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {
                int indexOfCMsg = i - startIndex;

                NFDL0170_ASMsg sLineMsg = (NFDL0170_ASMsg) sMsgArray.get(i);
                NFDL0170_ACMsg cLineMsg = (NFDL0170_ACMsg) cMsgArray.get(indexOfCMsg);

                EZDMsg.copy(sLineMsg, null, cLineMsg, null);
            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * It copy 'NFDL0170CMsg.A' to 'NFDL0170SMsg.A' .
     * @param bizMsg NFDL0170CMsg
     * @param globalMsg NFDL0170SMsg
     */
    public static void saveCurrentPageToSMsg(NFDL0170CMsg bizMsg, NFDL0170SMsg globalMsg) {
        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        globalMsg.clearErrorInfo();
        globalMsg.A.clearErrorInfo();

        int fromIdx = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(fromIdx + i), null);
        }
    }

    // moved from BL02
    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    public static void search(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NFDL0170Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NFCM0048I);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NFDL0170CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * setSearchFlg
     * @param character String
     * @return searchFlg String
     */
    public static String setSearchFlg(String character) {

        String searchFlg;
        if (character.contains(PERCENT)) {
            searchFlg = ZYPConstant.FLG_ON_Y;
        } else {
            searchFlg = ZYPConstant.FLG_OFF_N;
        }

        return searchFlg;
    }
}
