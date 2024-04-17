/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1010.common;

import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

import business.blap.NLCL1010.NLCL1010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 * </pre>.
 */
public class NLCL1010CommonLogic {

    /**
     * Set optional parameter. (For SSM)
     *
     * @param queryParam Map<String,Object>
     * @param key String
     * @param value String
     */
    public static void setOptionalParam(Map<String, Object> queryParam, String key, String value) {
        if (ZYPCommonFunc.hasValue(value)) {
            queryParam.put(key, value);
        }
    }

    /**
     * Save from CMsg to SMsg.
     *
     * @param cMsg cMsg.
     * @param cMsgAry from Msg.
     * @param sMsgAry to Msg.
     */
    public static void saveCurrentPage(NLCL1010CMsg cMsg, EZDCMsgArray cMsgAry, EZDSMsgArray sMsgAry) {
        if (cMsg.xxPageShowFromNum.getValueInt() < 0) {
            return;
        }
        for (int i = 0; i < cMsgAry.getValidCount(); i++) {
            int indexOfSMsg = i + cMsg.xxPageShowFromNum.getValueInt() - 1;
            if (indexOfSMsg < sMsgAry.getValidCount()) {
                EZDMsg.copy(cMsgAry.get(i), null, sMsgAry.get(indexOfSMsg), null);
            }
        }
    }

    /**
     * copy from SMsg to CMsg.
     *
     * @param cMsg cMsg.
     * @param cMsgAry to Msg
     * @param sMsgAry from Msg
     */
    public static void dispPage(NLCL1010CMsg cMsg, EZDCMsgArray cMsgAry, EZDSMsgArray sMsgAry) {
        ZYPTableUtil.clear(cMsgAry);
        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int dispRowNum = 0;
        if (cMsg.xxPageShowFromNum.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < cMsgAry.length() && startIndex + dispRowNum < sMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(sMsgAry.get(startIndex + dispRowNum), null, cMsgAry.get(dispRowNum), null);
        }
        cMsgAry.setValidCount(dispRowNum);
        cMsg.xxPageShowToNum.setValue(startIndex + dispRowNum);
        cMsg.xxPageShowOfNum.setValue(sMsgAry.getValidCount());
    }

    /**
     * disp rowNo page.
     *
     * @param cMsg cMsg.
     * @param rowNo target rowNo
     * @param cMsgAry CMsg.
     * @param sMsgAry SMsg.
     */
    public static void jumpPage(NLCL1010CMsg cMsg, int rowNo, EZDCMsgArray cMsgAry, EZDSMsgArray sMsgAry) {
        cMsg.xxPageShowFromNum.setValue(rowNo - rowNo % cMsgAry.length() + 1);
        dispPage(cMsg, cMsgAry, sMsgAry);
    }

    /**
     * prev Page.(copy from SMsg to CMsg)
     * @param cMsg cMsg.
     * @param cMsgAry CMsg.
     * @param sMsgAry SMsg.
     */
    public static void prevPage(NLCL1010CMsg cMsg, EZDCMsgArray cMsgAry, EZDSMsgArray sMsgAry) {
        saveCurrentPage(cMsg, cMsgAry, sMsgAry);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsgAry.length());
        dispPage(cMsg, cMsgAry, sMsgAry);
    }

    /**
     * next Page.(copy from SMsg to CMsg)
     * @param cMsg cMsg.
     * @param cMsgAry CMsg.
     * @param sMsgAry SMsg.
     */
    public static void nextPage(NLCL1010CMsg cMsg, EZDCMsgArray cMsgAry, EZDSMsgArray sMsgAry) {
        saveCurrentPage(cMsg, cMsgAry, sMsgAry);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsgAry.length());
        dispPage(cMsg, cMsgAry, sMsgAry);
    }

    /**
     * copy from SMsg to CMsg.
     *
     * @param cMsg cMsg.
     * @param cMsgAry to Msg
     * @param sMsgAry from Msg
     */
    public static void dispPageForJump(NLCL1010CMsg cMsg, EZDCMsgArray cMsgAry, EZDSMsgArray sMsgAry) {

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsgAry.length(); i++) {
            if (i < sMsgAry.getValidCount()) {
                EZDMsg.copy(sMsgAry.get(i), null, cMsgAry.get(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

}
