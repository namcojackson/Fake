/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0110.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0110.NFDL0110CMsg;
import business.blap.NFDL0110.NFDL0110Query;
import business.blap.NFDL0110.NFDL0110SMsg;
import business.blap.NFDL0110.NFDL0110_ACMsgArray;
import business.blap.NFDL0110.NFDL0110_ASMsgArray;
import business.blap.NFDL0110.constant.NFDL0110Constant;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0110CommonLogic implements NFDL0110Constant {

    /**
     * Get AR Account Date
     * @param glblCmpyCd String
     * @return String
     */
    public static String getDsAccountCustName(String glblCmpyCd, String dsAcctNum) {

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        String dsAcctNm = null;
        SELL_TO_CUSTTMsgArray outMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray != null && outMsgArray.getValidCount() > 0) {
            dsAcctNm = outMsgArray.no(0).dsAcctNm.getValue();
        }
        return dsAcctNm;
    }

    /**
     * Get AR Account Date
     * @param glblCmpyCd String
     * @return String
     */
    public static String getBillToCustName(String glblCmpyCd, String billToCustCd) {

        Map<String, String> param = new HashMap<String, String>(); 
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("billToCustCd", billToCustCd);

        S21SsmEZDResult result = NFDL0110Query.getInstance().getBillToCustName(param);
        String billToCustNm = (String) result.getResultObject();
        return billToCustNm;
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFDL0110CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {

        NFDL0110_ACMsgArray bizMsgAry_A = bizMsg.A;

        NFDL0110_ASMsgArray shareMsgAry_A = globalMsg.A;

        ZYPTableUtil.clear(bizMsgAry_A);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry_A.length() && startIndex + dispRowNum < shareMsgAry_A.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry_A.get(startIndex + dispRowNum), null, bizMsgAry_A.get(dispRowNum), null);
        }
        bizMsgAry_A.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry_A.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {
        NFDL0110_ACMsgArray bizMsgAry_A = bizMsg.A;

        NFDL0110_ASMsgArray shareMsgAry_A = globalMsg.A;

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry_A.length() && dispRowNum < bizMsgAry_A.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry_A.get(dispRowNum), null, shareMsgAry_A.get(startIndex + dispRowNum), null);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NFDL0110CMsg
     * @param globalMsg NFDL0110SMsg
     */
    public static void prevPage(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length());
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Next Page
     * @param bizMsg NFDL0110CMsg
     * @param globalMsg NFDL0110SMsg
     */
    public static void nextPage(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsg.A.length());
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Next Page
     * @param bizMsg NFDL0110CMsg
     * @param globalMsg NFDL0110SMsg
     */
    public static void jumpPage(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_A, new BigDecimal(bizMsg.A.length() * (bizMsg.xxPageShowCurNum_A.getValueInt() - 1) + 1));
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Last Page
     * @param bizMsg NFDL0110CMsg
     * @param globalMsg NFDL0110SMsg
     */
    public static void lastPage(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum_A.setValue(lastPageFromNum);
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NFDL0110CMsg
     * @param globalMsg NFDL0110SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {
        int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
        int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

}
