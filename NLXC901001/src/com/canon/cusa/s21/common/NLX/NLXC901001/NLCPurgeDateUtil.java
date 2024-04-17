package com.canon.cusa.s21.common.NLX.NLXC901001;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;

/**
 * For Data Purge Util.
 */
public final class NLCPurgeDateUtil {

    /** LAST_MONTH */
    private static final int LAST_MONTH = 12;

    /** LAST_TWO_DIGIT_MONTH */
    private static final int LAST_TWO_DIGIT_MONTH = 10;

    private NLCPurgeDateUtil() {
    }

    /**
     * @param glblCmpyCd String
     * @param keepTermTp String
     * @param keepTerm int
     * @return String
     */
    public static String getNotDeleteStartInfo(String glblCmpyCd, String keepTermTp, int keepTerm) {

        if (null == keepTermTp) {
            return null;

        } else if (keepTermTp.equals("Y")) {

            String y = ZYPDateUtil.getBatProcDate().substring(0, 4);
            int yInt = Integer.valueOf(y);

            return String.valueOf(yInt - keepTerm);

        } else if (keepTermTp.equals("M")) {

            String ym = ZYPDateUtil.getBatProcDate().substring(0, 6);

            int yInt = Integer.valueOf(ym.substring(0, 4));
            int mInt = Integer.valueOf(ym.substring(4, 6));

            int yOverCnt = 0;

            for (int i = 0; i < keepTerm; i++) {
                if (1 == mInt) {
                    mInt = LAST_MONTH;
                    yOverCnt++;
                } else {
                    mInt--;
                }
            }

            yInt = yInt - yOverCnt;

            StringBuffer sb = new StringBuffer();
            sb.append(yInt);
            if (mInt < LAST_TWO_DIGIT_MONTH) {
                sb.append("0");
            }
            sb.append(mInt);

            return sb.toString();

        } else if (keepTermTp.equals("D")) {
            String ymd = ZYPDateUtil.getBatProcDate().substring(0, 8);
            return S21CalendarUtil.addDay(ymd, (-1) * keepTerm);

        } else {
            throw new IllegalArgumentException("Error! SRC_RETEN_NUM_TP_CD is Illegal input");
        }

    }

}
