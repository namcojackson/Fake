/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2012   Fujitsu         M.Nakamura      Create
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;

/**
 * To calculate the schedule.
 */
public class NWXC100001CalcSchd {

    /** Error Message  */
    public static final String NWAM0458E = "NWAM0458E";
    /** Error Message  */
    public static final String NWAM0459E = "NWAM0459E";
    /** Error Message  */
    public static final String NWAM0460E = "NWAM0460E";

    /**
     * <pre>
     * To calculate the schedule.
     * </pre>
     * @param slsDt String
     * @param effFrmDt String
     * @param effThruDt String
     * @param trgtQty BigDecimal
     * @param intvlMth BigDecimal
     * @param endPerMth BigDecimal
     * @param unitQty BigDecimal
     * @param warrantyDays BigDecimal
     * @return NWXC100001CalcSchdData
     */
    public static NWXC100001CalcSchdData calculateSchedule(String slsDt, String effFrmDt, String effThruDt, BigDecimal trgtQty, BigDecimal intvlMth, BigDecimal endPerMth, BigDecimal unitQty, BigDecimal warrantyDays) {

        NWXC100001CalcSchdData calcSchdData = new NWXC100001CalcSchdData();
        calcSchdData.setDelyDtList(new ArrayList<String>());

        if (unitQty.multiply(endPerMth).compareTo(trgtQty) > 0) {
            calcSchdData.setMsgId(NWAM0458E);
            return calcSchdData;
        }

        String startDt = ZYPDateUtil.addDays(effFrmDt, warrantyDays.intValue());
        if (ZYPDateUtil.compare(slsDt, startDt) > 0) {
            calcSchdData.setMsgId(NWAM0459E);
            return calcSchdData;
        }

        String endDt = addMonths(startDt, intvlMth.intValue() * (endPerMth.intValue() - 1));
        if (ZYPDateUtil.compare(endDt, effThruDt) > 0) {
            calcSchdData.setMsgId(NWAM0460E);
            return calcSchdData;
        }

        calcSchdData.addDelyDtList(startDt);

        // 2012/09/24 A.Wada Update Start ------------------------------------
//        String befDt = startDt;
//        for (int i = 2; i <= endPerMth.intValue(); i++) {
//            String trgtDt = addMonths(befDt, intvlMth.intValue());
//            calcSchdData.addDelyDtList(trgtDt);
//            befDt = trgtDt;
//        }
        for (int i = 2; i <= endPerMth.intValue(); i++) {
            String trgtDt = addMonths(startDt, (intvlMth.intValue() * (i - 1)));
            calcSchdData.addDelyDtList(trgtDt);
        }
        // 2012/09/24 A.Wada Update End   ------------------------------------
        return calcSchdData;
    }

    /**
     * Get AddMonth
     * @param yyyyMMdd this month
     * @param months next or prev month count
     * @return String Month
     */
    private static String addMonths(String yyyyMMdd, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(yyyyMMdd.substring(0, 4))
                , Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1
                , Integer.parseInt(yyyyMMdd.substring(6, 8)));
        calendar.add(Calendar.MONTH, months);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(calendar.getTime());
    }
}
