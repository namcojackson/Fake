/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0450.common;

import static business.blap.NSBL0450.constant.NSBL0450Constant.*;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDMsg;
import business.blap.NSBL0450.NSBL0450CMsg;
import business.blap.NSBL0450.NSBL0450SMsg;
import business.blap.NSBL0450.NSBL0450_ACMsg;
import business.blap.NSBL0450.NSBL0450_ACMsgArray;


/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 * 2017/03/01   Hitachi         A.Kohinata        Update          QC#17608
 *</pre>
 */
public class NSBL0450CommonLogic {

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    public static void copyCurrentPageToSMsg(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        // NSBL0450_ACMsg -> NSBL0450_ASMsg
        NSBL0450_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSBL0450_ACMsg acMsg = (NSBL0450_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;
            EZDMsg.copy(cMsg.A.get(i), null, sMsg.A.get(targetIdxNumA), null);
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // add start 2017/03/01 CSA Defect#17608
    /**
     * convertTime
     * @param mode int
     * @param date String
     * @param time String
     * @param techCd String
     * @return String[]: [0]Date [1]Time
     */
    public static String[] convertTime(int mode, String date, String time, String techCd) {
        if (!hasValue(date) || !hasValue(techCd)) {
            return null;
        }

        String dateTime = date.concat(time);
        SvcTimeZoneInfo svcTimeZoneInfo = NSXC001001SvcTimeZone.convertTimeRtlWh(mode, dateTime, techCd);
        if (svcTimeZoneInfo == null || svcTimeZoneInfo.getDateTime().length() < LENGTH_8) {
            return null;
        }

        String[] convertTime = new String[2];
        convertTime[0] = svcTimeZoneInfo.getDateTime().substring(0, SUB_8);
        convertTime[1] = svcTimeZoneInfo.getDateTime().substring(SUB_8, dateTime.length());
        return convertTime;
    }

    /**
     * formatTime
     * @param time String
     * @param length int
     * @return String
     */
    public static String formatTime(String time, int length) {
        String formatTime = null;
        if (!hasValue(time) || time.length() < length) {
            return formatTime;
        }

        formatTime = ZYPCommonFunc.concatString(ZYPCommonFunc.subByteString(time, SUB_2), COLON, ZYPCommonFunc.subByteString(time, SUB_2, SUB_4));
        if (LENGTH_6 == length) {
            formatTime = ZYPCommonFunc.concatString(formatTime, COLON, ZYPCommonFunc.subByteString(time, SUB_4, SUB_6));
        }
        return formatTime;
    }
    // add end 2017/03/01 CSA Defect#17608
}
