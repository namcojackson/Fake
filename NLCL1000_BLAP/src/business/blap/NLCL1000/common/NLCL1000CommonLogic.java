package business.blap.NLCL1000.common;

import java.math.BigDecimal;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NLCL1000.NLCL1000CMsg;
import business.blap.NLCL1000.NLCL1000SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

public class NLCL1000CommonLogic {

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NLCL1000CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A1.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A1.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A1.setValue(shareMsgAry.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NLCL1000CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        int startIndex = bizMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A1.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NLCL1000CMsg
     * @param globalMsg NLCL1000SMsg
     */
    public static void prevPage(NLCL1000CMsg bizMsg, NLCL1000SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum_A1.setValue(bizMsg.xxPageShowFromNum_A1.getValueInt() - bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Next Page
     * @param bizMsg NLCL1000CMsg
     * @param globalMsg NLCL1000SMsg
     */
    public static void nextPage(NLCL1000CMsg bizMsg, NLCL1000SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum_A1.setValue(bizMsg.xxPageShowFromNum_A1.getValueInt() + bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NLCL1000CMsg
     * @param globalMsg NLCL1000SMsg
     */
    public static void lastPage(NLCL1000CMsg bizMsg, NLCL1000SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum_A1.setValue(lastPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NLCL1000CMsg
     * @param globalMsg NLCL1000SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NLCL1000CMsg bizMsg, NLCL1000SMsg globalMsg) {
        int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
        int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }
}
