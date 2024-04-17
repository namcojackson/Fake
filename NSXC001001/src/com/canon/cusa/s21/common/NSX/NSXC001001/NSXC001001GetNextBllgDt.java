package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Get Next Billing Date. 
 * 
 * Detail Spec
 * http://eroom.cusa.canon.com/eRoom/S21Program/S21WDS/0_281afb
 * 
 * UI
 * http://eroom.cusa.canon.com/eRoom/S21Program/PhaseII-WDSforOCEDevelopment/0_28ac3e
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/26/2013   WDS Team        K.ARATANI       Create
 * 09/23/2013   Hitachi        T.Yonekura            Update      QC2403
 * </pre>
 */
public class NSXC001001GetNextBllgDt {

    /** DATE format for methods of this class */
    private static final String DATE_FORMAT = S21CalendarUtilConstants.TYPE_YYYYMMDD;

    public String getNextBllgDt(NextBllgInfo nextBllgInfo) {

        String nextBllgDt = null;
        // 1. Input parameter check
        if (!checkMandatory(nextBllgInfo)) {
            return null;
        }
        if (!checkAttribute(nextBllgInfo)) {
            return null;
        }
        // 2. Sales Date
        String slsDtYYYYMMDD = nextBllgInfo.getSlsDt();
        int intSlsDtYYYYMMDD = Integer.parseInt(slsDtYYYYMMDD);
        int intSlsDtDD = Integer.parseInt(slsDtYYYYMMDD.substring(6));
        // 3. Billing Cycle Month
        BLLG_CYCLETMsg bllgCycleTMsg = new BLLG_CYCLETMsg();
        setValue(bllgCycleTMsg.glblCmpyCd, nextBllgInfo.getGlblCmpyCd());
        setValue(bllgCycleTMsg.bllgCycleCd, nextBllgInfo.getBllgCycleCd());
        bllgCycleTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bllgCycleTMsg);
        if (bllgCycleTMsg == null) {
            return null;
        }
        if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bllgCycleTMsg.bllgCycleCd.getValue())) {
            bllgCycleTMsg.bllgCycleMthAot.setValue(BigDecimal.ZERO);
        }

        // Contract Effect From Date
        int intContrEffFromDtYYYYMMDD = 0;
        int intContrEffFromDtDD = 0;
        if (hasValue(nextBllgInfo.getContrEffFromDt())) {
            intContrEffFromDtYYYYMMDD = Integer.parseInt(nextBllgInfo.getContrEffFromDt());
            intContrEffFromDtDD = Integer.parseInt(nextBllgInfo.getContrEffFromDt().substring(6));
        }

        // Invoice Up To Date
        int intInvUpToDtYYYYMMDD = 0;
        int intInvUpToDtDD = 0;
        if (hasValue(nextBllgInfo.getInvUpToDt())) {
            intInvUpToDtYYYYMMDD = Integer.parseInt(nextBllgInfo.getInvUpToDt());
            intInvUpToDtDD = Integer.parseInt(nextBllgInfo.getInvUpToDt().substring(6));
        }

        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(nextBllgInfo.getSvcInvChrgTpCd())) {
            if (BLLG_CYCLE.CONTRACT_PERIOD.equals(nextBllgInfo.getBllgCycleCd())) {
                return nextBllgInfo.getContrEffFromDt();
            }
        }

        // 4. Next Billing Date
        // First Time Billing
        if (!hasValue(nextBllgInfo.getLastBllgDt())) {
            // Billing Timing = Advance(A)
            if (BLLG_TMG_TP.ADVANCE.equals(nextBllgInfo.getBllgTmgTpCd())) {
                // Contract Effective From Date >= Sales Date
                if (intContrEffFromDtYYYYMMDD >= intSlsDtYYYYMMDD) {
                    // Contract Effective From Date(Day) > Billing Day
                    if (intContrEffFromDtDD > convertDay(nextBllgInfo.getBllgDay(), nextBllgInfo.getContrEffFromDt())) {
                        // Contract Effective From Date(Year and Next month) + Billing Day
                        nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getContrEffFromDt(), 1).substring(0, 6), nextBllgInfo.getBllgDay());
                    // Contract Effective From Date(Day) <= Billing Day
                    } else {
                        // Contract Effective From Date(Year and month) + Billing Day
                        nextBllgDt = getBllgDate(nextBllgInfo.getContrEffFromDt().substring(0, 6), nextBllgInfo.getBllgDay());
                    }
                // Contract Effective From Date(Year and month) < Sales Date
                } else {
                    // Sales Date(Day) > Billing Day
                    if (intContrEffFromDtDD > convertDay(nextBllgInfo.getBllgDay(), slsDtYYYYMMDD)) {
                        // Sales Date(Year and Next month) + Billing Day
                        nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                    // Sales Date(Day) <= Billing Day
                    } else {
                        // Sales Date(Year and month) + Billing Day
                        nextBllgDt = getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getBllgDay());
                    }
                }
            // Billing Timing = Arrear(R)
            } else {
                // Billing Cycle = Contract Periods
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(nextBllgInfo.getBllgCycleCd())) {
                    // Contract Effective Thru Date(YYYY/MM) + Billing Day
                    nextBllgDt = getBllgDate(nextBllgInfo.getContrEffThruDt().substring(0, 6), nextBllgInfo.getBllgDay());
                } else {
                    // Contract Effective From Date >= Sales Date
                    if (intContrEffFromDtYYYYMMDD >= intSlsDtYYYYMMDD) {
                        // Closing Day >= Contract Effective From Date(Day)
                        if (convertDay(nextBllgInfo.getContrCloDay(), nextBllgInfo.getContrEffFromDt()) >= intContrEffFromDtDD) {
                            // Closing Day > Billing Day
                            if (compareClosingDayToBillingDay(nextBllgInfo, nextBllgInfo.getContrEffFromDt(), false)) {
                                // Contract Effective From Date(Year and Next month) + Billing Day
                                nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getContrEffFromDt(), 1).substring(0, 6), nextBllgInfo.getBllgDay());
                            } else {
                                // Contract Effective From Date(Year and month) + Billing Day
                                nextBllgDt = getBllgDate(nextBllgInfo.getContrEffFromDt().substring(0, 6), nextBllgInfo.getBllgDay());
                            }
                        } else {
                            // Closing Day > Billing Day
                            if (compareClosingDayToBillingDay(nextBllgInfo, nextBllgInfo.getContrEffFromDt(), false)) {
                                // Contract Effective From Date(Year and After Next month) + Billing Day
                                nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getContrEffFromDt(), 2).substring(0, 6), nextBllgInfo.getBllgDay());
                            } else {
                                // Contract Effective From Date(Year and Next month) + Billing Day
                                nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getContrEffFromDt(), 1).substring(0, 6), nextBllgInfo.getBllgDay());
                            }
                        }
                    } else {
                        //Closing Day >= Sales Date(Day)
                        if (convertDay(nextBllgInfo.getContrCloDay(), slsDtYYYYMMDD) >= intSlsDtDD) {
                            // Closing Day > Billing Day
                            if (compareClosingDayToBillingDay(nextBllgInfo, slsDtYYYYMMDD, false)) {
                                // Sales Date(Year and Next month) + Billing Day
                                nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                            } else {
                                // Sales Date(Year and month) + Billing Day
                                nextBllgDt = getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getBllgDay());
                            }
                        } else {
                            // Contract Effective From Date <  Sales Date(Year and month) + Closing Day
                            if (intContrEffFromDtYYYYMMDD < Integer.parseInt(getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getContrCloDay()))) {
                                // Closing Day > Billing Day
                                if (compareClosingDayToBillingDay(nextBllgInfo, slsDtYYYYMMDD, false)) {
                                    // Sales Date(Year and Next month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                } else {
                                    // Sales Date(Day) > Billing Day
                                    if (intSlsDtDD > convertDay(nextBllgInfo.getBllgDay(), slsDtYYYYMMDD)) {
                                        // Sales Date(Year and Next month) + Billing Day
                                        nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                    } else {
                                        // Sales Date(Year and month) + Billing Day
                                        nextBllgDt = getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getBllgDay());
                                    }
                                }
                            } else {
                                 // Closing Day > Billing Day
                                if (compareClosingDayToBillingDay(nextBllgInfo, slsDtYYYYMMDD, false)) {
                                    // Sales Date(Year and After Next month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 2).substring(0, 6), nextBllgInfo.getBllgDay());
                                } else {
                                    // Sales Date(Year and Next month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                }
                            }
                        }
                    }
                }
            }

        // Not First Time(Last Billing Date <> Blank):
        } else if (hasValue(nextBllgInfo.getLastBllgDt())) {
            // Billing Timing = Advance(A)
            if (BLLG_TMG_TP.ADVANCE.equals(nextBllgInfo.getBllgTmgTpCd())) {
                // Invoice Up To Date >= Sales Date
                if (intInvUpToDtYYYYMMDD >= intSlsDtYYYYMMDD) {
                    // Invoice Up To Date(Day) > Billing Day
                    if (intInvUpToDtDD > convertDay(nextBllgInfo.getBllgDay(), nextBllgInfo.getInvUpToDt())) {
                        // Invoice Up To Date(Year and Next month) + Billing Day
                        nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getInvUpToDt(), 1).substring(0, 6), nextBllgInfo.getBllgDay());
                    } else {
                        // Invoice Up To Date(Year and Month) + Billing Day
                        nextBllgDt = getBllgDate(nextBllgInfo.getInvUpToDt().substring(0, 6), nextBllgInfo.getBllgDay());
                    }
                // Invoice Up To Date < Sales Date
                } else {
                    // Sales Date(Day) > Billing Day
                    if (intSlsDtDD > convertDay(nextBllgInfo.getBllgDay(), slsDtYYYYMMDD)) {
                        // Sales Date(Year and Next month) + Billing Day
                        nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                    } else {
                        // Sales Date(Year and Month) + Billing Day
                        nextBllgDt = getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getBllgDay());
                    }
                }
            // Billing Timing = Arrear(R)
            } else {
                // Billing Cycle = Contract Periods
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(nextBllgInfo.getBllgCycleCd())) {
                    // Contract Effective Thru Date(YYYY/MM)
                    nextBllgDt = getBllgDate(nextBllgInfo.getContrEffThruDt().substring(0, 6), nextBllgInfo.getBllgDay());
                } else {
                    // Last Billing Date + Billing Cycle of Month
                    String lastBllgDtAot = addMonths(nextBllgInfo.getLastBllgDt(), bllgCycleTMsg.bllgCycleMthAot.getValueInt());
                    int intLastBllgDt = Integer.parseInt(nextBllgInfo.getLastBllgDt());
                    int intInvUpToDt = Integer.parseInt(nextBllgInfo.getInvUpToDt());
                    // Last Billing Date > Sales Date
                    if (intLastBllgDt > intSlsDtYYYYMMDD) {
                        return null;
                    }
                    // Last Billing Date >= Invoice Up To Date
                    if (intLastBllgDt >= intInvUpToDt) {
                        // Last Billing Date =< Sales Date
                        if (intLastBllgDt <= intSlsDtYYYYMMDD) {
                            String lastBllgDtAotBllgDay = getBllgDate(lastBllgDtAot.substring(0, 6), nextBllgInfo.getBllgDay());
                            int intLastBllgDtAotBllgDay = Integer.parseInt(lastBllgDtAotBllgDay);
                            // Last Billing Date + Billing Cycle of Month(Year and Month) + Billing Day >= Sales Date
                            if (intLastBllgDtAotBllgDay >= intSlsDtYYYYMMDD) {
                                // Last Billing Date + Billing Cycle of Month(Year and Month) + Billing Day
                                nextBllgDt = lastBllgDtAotBllgDay;
                            } else {
                                // Sales Date(Day) > Billing Day
                                if (intSlsDtDD > convertDay(nextBllgInfo.getBllgDay(), slsDtYYYYMMDD)) {
                                    // Sales Date(Year and Next month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                } else {
                                    // Sales Date(Year and Month) + Billing Day
                                    nextBllgDt = getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getBllgDay());
                                }
                            }
                        }
                    // Last Billing Date < Invoice Up To Date
                    } else {
                        // Invoice Up To Date >= Sales Date
                        if (intInvUpToDt >= intSlsDtYYYYMMDD) {
                            // Closing Day > Invoice Up To Date(Day)
                            if (convertDay(nextBllgInfo.getContrCloDay(), nextBllgInfo.getInvUpToDt()) > intInvUpToDtDD) {
                                // Closing Day > Billing Day
                                if (compareClosingDayToBillingDay(nextBllgInfo, nextBllgInfo.getInvUpToDt(), isLastDateOfMonth(nextBllgInfo.getInvUpToDt()) && isLastDateOfMonth(getBllgDate(nextBllgInfo.getInvUpToDt().substring(0, 6), nextBllgInfo.getContrCloDay())))) {
                                    // Invoice Up To Date(Year and Next month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getInvUpToDt(), 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                } else {
                                    // Invoice Up To Date(Year and Month) + Billing Day
                                    nextBllgDt = getBllgDate(nextBllgInfo.getInvUpToDt().substring(0, 6), nextBllgInfo.getBllgDay());
                                }
                            // Closing Day < Invoice Up To Date(Day)
                            } else {
                                // Closing Day > Billing Day
                                if (compareClosingDayToBillingDay(nextBllgInfo, nextBllgInfo.getInvUpToDt(), isLastDateOfMonth(nextBllgInfo.getInvUpToDt()) && isLastDateOfMonth(getBllgDate(nextBllgInfo.getInvUpToDt().substring(0, 6), nextBllgInfo.getContrCloDay())))) {
                                    // Invoice Up To Date(Year and After Next month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getInvUpToDt(), 2).substring(0, 6), nextBllgInfo.getBllgDay());
                                } else {
                                    // Invoice Up To Date(Year and Next Month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(nextBllgInfo.getInvUpToDt(), 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                }
                            }

                        // Invoice Up To Date < Sales Date
                        } else {
                            // Closing Day >= Sales Date(Day)
                            if (convertDay(nextBllgInfo.getContrCloDay(), slsDtYYYYMMDD) >= intSlsDtDD) {
                                // Closing Day > Billing Day
                                if (compareClosingDayToBillingDay(nextBllgInfo, slsDtYYYYMMDD, false)) {
                                    // Sales Date(Year and Next month) + Billing Day
                                    nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                } else {
                                    //Sales Date(Day) > Billing Day
                                    if (intSlsDtDD > convertDay(nextBllgInfo.getBllgDay(), slsDtYYYYMMDD)) {
                                     // Sales Date(Year and Next month) + Billing Day
                                        nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                    } else {
                                     // Sales Date(Year and Month) + Billing Day
                                        nextBllgDt = getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getBllgDay());
                                    }
                                }
                            // Closing Day < Sales Date(Day)
                            } else {
                                // Closing Day > Billing Day
                                if (compareClosingDayToBillingDay(nextBllgInfo, slsDtYYYYMMDD, false)) {
                                    // Closing Day > Invoice Up To Date(Day)
                                    if (convertDay(nextBllgInfo.getContrCloDay(), slsDtYYYYMMDD) > intInvUpToDtDD) {
                                        // Sales Date(Year and Next month) + Billing Day
                                        nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                    } else {
                                     // Sales Date(Year and After Next month) + Billing Day
                                        nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 2).substring(0, 6), nextBllgInfo.getBllgDay());
                                    }
                                } else {
                                    //Sales Date(Day) > Billing Day
                                    if (intSlsDtDD > convertDay(nextBllgInfo.getBllgDay(), slsDtYYYYMMDD)) {
                                        // Sales Date(Year and Next month) + Billing Day
                                        nextBllgDt = getBllgDate(addMonths(slsDtYYYYMMDD, 1).substring(0, 6), nextBllgInfo.getBllgDay());
                                    } else {
                                        // Sales Date(Year and Month) + Billing Day
                                        nextBllgDt = getBllgDate(slsDtYYYYMMDD.substring(0, 6), nextBllgInfo.getBllgDay());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return nextBllgDt;
    }

    private boolean checkMandatory(NextBllgInfo nextBllgInfo) {
        if (!hasValue(nextBllgInfo.getGlblCmpyCd())) {
            return false;
        }
        if (!hasValue(nextBllgInfo.getSlsDt())) {
            return false;
        }
        if (!hasValue(nextBllgInfo.getLastBllgDt()) && !hasValue(nextBllgInfo.getContrEffFromDt())) {
            return false;
        }
        if (hasValue(nextBllgInfo.getLastBllgDt()) && !hasValue(nextBllgInfo.getInvUpToDt())) {
            return false;
        }
        if (!hasValue(nextBllgInfo.getContrEffThruDt())) {
            return false;
        }
        if (!hasValue(nextBllgInfo.getContrCloDay())) {
            return false;
        }
        if (!hasValue(nextBllgInfo.getBllgDay())) {
            return false;
        }
        if (!hasValue(nextBllgInfo.getBllgCycleCd())) {
            return false;
        }
        if (!hasValue(nextBllgInfo.getBllgTmgTpCd())) {
            return false;
        }
        return true;
    }

    private boolean checkAttribute(NextBllgInfo nextBllgInfo) {
        if (nextBllgInfo.getGlblCmpyCd().length() > 4) {
            return false;
        }
        if (hasValue(nextBllgInfo.getSlsDt())) {
            if (nextBllgInfo.getSlsDt().length() != 8) {
                return false;
            }
            try {
                Integer.parseInt(nextBllgInfo.getSlsDt());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (hasValue(nextBllgInfo.getInvUpToDt())) {
            if (nextBllgInfo.getInvUpToDt().length() != 8) {
                return false;
            }
            try {
                Integer.parseInt(nextBllgInfo.getInvUpToDt());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (hasValue(nextBllgInfo.getLastBllgDt())) {
            if (nextBllgInfo.getLastBllgDt().length() != 8) {
                return false;
            }
            try {
                Integer.parseInt(nextBllgInfo.getLastBllgDt());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (hasValue(nextBllgInfo.getContrEffFromDt())) {
            if (nextBllgInfo.getContrEffFromDt().length() != 8) {
                return false;
            }
            try {
                Integer.parseInt(nextBllgInfo.getContrEffFromDt());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (nextBllgInfo.getContrEffThruDt().length() != 8) {
            return false;
        }
        try {
            Integer.parseInt(nextBllgInfo.getContrEffThruDt());
        } catch (NumberFormatException e) {
            return false;
        }

        if (nextBllgInfo.getContrCloDay().length() == 1) {
            nextBllgInfo.setContrCloDay("0" + nextBllgInfo.getContrCloDay());
        }

        if (nextBllgInfo.getContrCloDay().length() > 2) {
            return false;
        }
        try {
            Integer.parseInt(nextBllgInfo.getContrCloDay());
        } catch (NumberFormatException e) {
            return false;
        }

        if (nextBllgInfo.getBllgDay().length() == 1) {
            nextBllgInfo.setBllgDay("0" + nextBllgInfo.getBllgDay());
        }

        if (nextBllgInfo.getBllgDay().length() > 2) {
            return false;
        }
        try {
            Integer.parseInt(nextBllgInfo.getBllgDay());
        } catch (NumberFormatException e) {
            return false;
        }
        if (nextBllgInfo.getBllgCycleCd().length() > 1) {
            return false;
        }
        if (nextBllgInfo.getBllgTmgTpCd().length() > 1) {
            return false;
        }
        return true;
    }

    /**
     * addDays
     * @param date String
     * @param numberOfDays int
     * @return String
     */
    public static String addDays(String date, int numberOfDays) {
        if (!hasValue(date)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, numberOfDays);
        return format.format(cal.getTime());
    }

    /**
     * addMonths
     * @param date String 
     * @param numberOfMonths int 
     * @return String
     */
    public static String addMonths(String date, int numberOfMonths) {
        if (!hasValue(date)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, numberOfMonths);
        String returnDate = format.format(cal.getTime());
        if (isLastDateOfMonth(date)) {
            returnDate = getLastDateOfMonth(returnDate);
        }
        return returnDate;
    }

    /**
     * get the last date(YYYYMMDD) of the month of the specified date.
     * @param date YYYMMDD
     * @return the last date(YYYYMMDD) of the month
     */
    public static String getLastDateOfMonth(String date) {
        if (!hasValue(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return format.format(cal.getTime());
    }

    /**
     * Returns true if the specified date is the last date(YYYYMMDD)
     * of the month, otherwise false.
     * @param date YYYMMDD
     * @return true if the specified date is the last date of the
     * month, false if not.
     */
    public static boolean isLastDateOfMonth(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        return (day == lastDay);
    }

    private String getBllgDate(String dateYYYYMM, String bllgDayDD) {

        if (!EZDCommonFunc.checkDate(dateYYYYMM.substring(0, 4), dateYYYYMM.substring(4, 6), bllgDayDD)) {
            return getLastDateOfMonth(dateYYYYMM + "01");
        } else {
            return dateYYYYMM + bllgDayDD;
        }
    }

    private int convertDay(String origDay, String baseDate) {

        int convertDay = 0;

        String lastDate = getLastDateOfMonth(baseDate);

        if ("99".equals(origDay)) {
            convertDay = Integer.parseInt(lastDate.substring(6));
            return convertDay;
        }

        convertDay = Integer.parseInt(origDay);
        int lastDay = Integer.parseInt(lastDate.substring(6));

        if (convertDay > lastDay) {
            convertDay = lastDay;
        }

        return convertDay;
    }

    private boolean compareClosingDayToBillingDay(NextBllgInfo nextBllgInfo, String baseDate, boolean invUpToFlg) {

        if (invUpToFlg) {
            baseDate = addMonths(baseDate, 1);
        }

        if (convertDay(nextBllgInfo.getContrCloDay(), baseDate) > convertDay(nextBllgInfo.getBllgDay(), baseDate)) {
            return true;
        }

        return false;
    }

}
