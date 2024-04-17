package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;

import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSXC003001CalcSchdTermAndAmt {

    /** MONTH_END */
    private static final String MONTH_END = "99";

    /**
     * @param inBean CalcSchdTermAndAmtBean
     * @return List<CalcSchdTermAndAmtOutBean>
     */
    public static CalcSchdTermAndAmtBean calcSchdTermAndAmt(CalcSchdTermAndAmtBean inBean) {

        List<CalcSchdTermAndAmtLineBean> outBeanList = new ArrayList<CalcSchdTermAndAmtLineBean>();

        String glblCmpyCd = inBean.getGlblCmpyCd();
        String bllgCycleCd = inBean.getBllgCycleCd();
        String dsContrCloDay = inBean.getDsContrCloDay();

        BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycle(glblCmpyCd, bllgCycleCd);
        BigDecimal bllgCycleMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();

        Calendar bllgSchdFromDt = toCalendar(inBean.getBllgSchdFromDt());
        Calendar bllgSchdThruDt = toCalendar(inBean.getBllgSchdThruDt());
        Calendar thruDt = (Calendar) bllgSchdFromDt.clone();

        int sqNum = 0;

        do {
            sqNum++;

            CalcSchdTermAndAmtLineBean outBean = new CalcSchdTermAndAmtLineBean();

            if (MONTH_END.equals(dsContrCloDay)) {
                // add cycle month
                thruDt.add(Calendar.MONTH, bllgCycleMthAot.intValue() - 1);
                int cloDay = thruDt.getActualMaximum(Calendar.DATE);
                thruDt.set(Calendar.DATE, cloDay);
            } else {
                // add cycle month
                thruDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
                thruDt.set(Calendar.DATE, Integer.parseInt(dsContrCloDay));
            }

            if (bllgSchdThruDt.compareTo(thruDt) < 0) {
                thruDt = (Calendar) bllgSchdThruDt.clone();
            }

            outBean.setDsContrBllgSchdSqNum(String.valueOf(sqNum));
            outBean.setBllgSchdFromDt(toString(bllgSchdFromDt));
            outBean.setBllgSchdThruDt(toString(thruDt));
            outBean.setBasePrcDealAmt(inBean.getBasePrcDealAmt());
            outBean.setBasePrcAdjDealAmt(BigDecimal.ZERO);
            outBeanList.add(outBean);

            bllgSchdFromDt = (Calendar) thruDt.clone();
            bllgSchdFromDt.add(Calendar.DATE, 1);
            thruDt = (Calendar) bllgSchdFromDt.clone();

        } while (bllgSchdFromDt.compareTo(bllgSchdThruDt) < 0);

        // adjust amount
        BigDecimal basePrcDealAmt = inBean.getBasePrcDealAmt();
        BigDecimal basePrcAdjDealAmt = inBean.getBasePrcAdjDealAmt();

        if (basePrcDealAmt == null) {
            basePrcDealAmt = BigDecimal.ZERO;
        }
        if (basePrcAdjDealAmt == null) {
            basePrcAdjDealAmt = BigDecimal.ZERO;
        }

        if (basePrcAdjDealAmt.compareTo(BigDecimal.ZERO) != 0) {
            for (int i = outBeanList.size() - 1; i >= 0; i--) {
                outBeanList.get(i).setBasePrcDealAmt(basePrcDealAmt.add(basePrcAdjDealAmt));
                if (basePrcDealAmt.add(basePrcAdjDealAmt).compareTo(BigDecimal.ZERO) < 0) {
                    outBeanList.get(i).setBasePrcDealAmt(BigDecimal.ZERO);
                    outBeanList.get(i).setBasePrcAdjDealAmt(basePrcDealAmt.negate());
                    basePrcAdjDealAmt = basePrcAdjDealAmt.add(basePrcDealAmt);
                } else {
                    outBeanList.get(i).setBasePrcDealAmt(basePrcDealAmt.add(basePrcAdjDealAmt));
                    outBeanList.get(i).setBasePrcAdjDealAmt(basePrcAdjDealAmt);
                    break;
                }
            }
        }
        inBean.setLine(outBeanList);
        return inBean;
    }

    private static BLLG_CYCLETMsg getBllgCycle(String glblCmpyCd, String bllgCycleCd) {

        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.bllgCycleCd, bllgCycleCd);
        return (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static Calendar toCalendar(String strDate) {

        if (!hasValue(strDate)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(strDate);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal;
    }

    private static String toString(Calendar cal) {
        Calendar tmp = (Calendar) cal.clone();
        SimpleDateFormat sdf = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        return sdf.format(tmp.getTime());
    }
}
