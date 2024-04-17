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
import business.db.CCYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 * 07/11/2016   Hitachi         K.Kishimoto     Update          QC#4961
 * 07/13/2016   Hitachi         K.Kishimoto     Update          QC#11805
 * 03/31/2017   Hitachi         K.Kitachi       Update          QC#18117
 * 04/25/2017   Hitachi         K.Kitachi       Update          QC#18222
 * 07/10/2017   Hitachi         T.Tomita        Update          QC#19823
 * 2018/04/19   Hitachi         K.Kojima        Update          QC#25607
 * 2018/04/26   Hitachi         K.Kojima        Update          QC#25801
 * 2018/08/27   Hitachi         K.Kishimoto     Update          QC#24555
 * 2018/11/05   Fujitsu         M.Yamada        Update          QC#29061
 *</pre>
 */
public class NSXC003001CalcSchdSmryTermAndAmt implements ZYPConstant {

    /** MODE01_CONTRACT */
    public static final String MODE01_CONTRACT = "01";

    /** MODE02_ANNUAL_ESCALATION */
    public static final String MODE02_ANNUAL_ESCALATION = "02";

    /** MONTH_END */
    private static final String MONTH_END = "99";

    /** DAYS_OF_YEAR */
    private static final int DAYS_OF_YEAR = 365;

    /** Sequence3 */
    private static final int SQ3 = 3;

    // START 2017/03/31 K.Kitachi [QC#18117, DEL]
//    /** millis of day */
//    private static final int MILLIS_OF_DAY = 1000 * 60 * 60 * 24;
    // END 2017/03/31 K.Kitachi [QC#18117, DEL]

    // Mod Start 07/11/2016 <QC#11805>
    /** Month Max */
    private static final int MTH_MAX = 12;

    // Mod End 07/11/2016 <QC#11805>

    /**
     * @param inBean CalcSchdSmryTermAndAmtBean
     * @return CalcSchdSmryTermAndAmtBean
     */
    public static CalcSchdSmryTermAndAmtBean calcSchdSmryTermAndAmt(CalcSchdSmryTermAndAmtBean inBean) {

        CalcSchdSmryTermAndAmtBean outBean = new CalcSchdSmryTermAndAmtBean();
        List<CalcSchdSmryTermAndAmtForBaseBean> outBaseList = new ArrayList<CalcSchdSmryTermAndAmtForBaseBean>();
        List<CalcSchdSmryTermAndAmtForUsageBean> outUsageList = new ArrayList<CalcSchdSmryTermAndAmtForUsageBean>();

        String glblCmpyCd = inBean.getGlblCmpyCd();
        String bllgCycleCd = inBean.getBllgCycleCd();
        String baseChrgFlg = inBean.getBaseChrgFlg();
        String usgChrgFlg = inBean.getUsgChrgFlg();
        String ccyCd = inBean.getCcyCd();
        BigDecimal basePrcDealAmt = inBean.getBasePrcDealAmt();
        if (!hasValue(basePrcDealAmt)) {
            inBean.setBasePrcDealAmt(BigDecimal.ZERO);
        }
        BigDecimal basePrcTermDealAmtRate = inBean.getBasePrcTermDealAmtRate();

        CCYTMsg ccyTMsg = getCcy(glblCmpyCd, ccyCd);
        if (ccyTMsg == null) {
            return outBean;
        }

        BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycle(glblCmpyCd, bllgCycleCd);
        if (bllgCycleTMsg == null) {
            return outBean;
        }

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            baseChrgProcess(inBean, outBaseList, bllgCycleTMsg);
        }

        if (FLG_ON_Y.equals(usgChrgFlg)) {
            usgChrgProcess(inBean, outUsageList, bllgCycleTMsg);
        }

        // Calculation Base Price Deal Amount
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            calcBasePrcDealAmt(inBean, outBaseList, ccyTMsg);
        }

        // Calculation Term Amount
        if (FLG_ON_Y.equals(baseChrgFlg) && hasValue(basePrcTermDealAmtRate)) {
            calcTermAmount(outBaseList, basePrcTermDealAmtRate, ccyTMsg);
        }

        outBean.setBaseList(outBaseList);
        outBean.setUsageList(outUsageList);
        return outBean;
    }

    /**
     * @param inBean
     * @param outBaseList
     * @param baseChrgFlg
     * @param bllgCycleTMsg
     */
    private static void baseChrgProcess(CalcSchdSmryTermAndAmtBean inBean, List<CalcSchdSmryTermAndAmtForBaseBean> outBaseList, BLLG_CYCLETMsg bllgCycleTMsg) {

        CalcSchdSmryTermAndAmtForBaseBean outBaseBean;
        BigDecimal bllgCycleMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();
        BigDecimal bllgCycleStartMth = toBigDecimal(bllgCycleTMsg.bllgCycleStartMth.getValue());
        String contrCloDay = inBean.getContrCloDay();

        int seqNum = 0;

        Calendar freqProdEndDt = getFreqEndDt(inBean.getBllgSchdFromDt(), inBean.getContrCloDay());
        Calendar bllgSchdFromDt = toCalendar(inBean.getBllgSchdFromDt());
        Calendar bllgSchdThruDt = toCalendar(inBean.getBllgSchdThruDt());
        Calendar nextThruDt = (Calendar) freqProdEndDt.clone();

        // ----------------------------------------
        // 1st Cycle
        // ----------------------------------------
        if (!hasValue(bllgCycleStartMth)) {
            // Not fixed end month

            Calendar freqStartDt = (Calendar) freqProdEndDt.clone();
            freqStartDt.add(Calendar.DATE, 1);

            if (freqStartDt.compareTo(bllgSchdFromDt) != 0) {
                // mod start 2017/07/11 QC#19823
//                if (freqProdEndDt.compareTo(bllgSchdFromDt) <= 0) {
//                    freqProdEndDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
//                    if (MONTH_END.equals(contrCloDay)) {
//                        freqProdEndDt = setEndMonth(freqProdEndDt);
//                    }
//                    nextThruDt = freqProdEndDt;
//                    // START 2017/04/25 K.Kitachi [QC#18222, ADD]
//                    if (nextThruDt.compareTo(bllgSchdThruDt) > 0) {
//                        nextThruDt = bllgSchdThruDt;
//                    }
//                    // END 2017/04/25 K.Kitachi [QC#18222, ADD]
//
//                } else if (bllgSchdFromDt.compareTo(bllgSchdThruDt) > 0) {
//
//                    nextThruDt = bllgSchdThruDt;
//                } else {
//                    nextThruDt = freqProdEndDt;
//                }
                int cloDay = Integer.parseInt(contrCloDay);
                int bllgSchdFromDay = bllgSchdFromDt.get(Calendar.DAY_OF_MONTH);
                nextThruDt = (Calendar) bllgSchdFromDt.clone();
                if (cloDay < bllgSchdFromDay) {
                    nextThruDt.add(Calendar.MONTH, 1);
                    nextThruDt.set(Calendar.DATE, cloDay);
                } else {
                    if (MONTH_END.equals(contrCloDay)) {
                        nextThruDt = setEndMonth(nextThruDt);
                    } else {
                        nextThruDt.set(Calendar.DATE, cloDay);
                    }
                }
                // mod end   2017/07/11 QC#19823

                // START 2018/04/19 K.Kojima [QC#25607,ADD]
                if (nextThruDt.compareTo(bllgSchdThruDt) > 0) {
                    nextThruDt = (Calendar) bllgSchdThruDt.clone();
                }
                // END 2018/04/19 K.Kojima [QC#25607,ADD]

                // per days
                seqNum++;
                int perSchdNum = getDiffDays(nextThruDt, bllgSchdFromDt) + 1;
                outBaseBean = new CalcSchdSmryTermAndAmtForBaseBean();
                outBaseBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
                outBaseBean.setPerSchdNum(new BigDecimal(perSchdNum));
                outBaseBean.setPerBllgCycleCd(BLLG_CYCLE.DAILY);
                outBaseBean.setBllgSchdFromDt(toString(bllgSchdFromDt));
                outBaseBean.setBllgSchdThruDt(toString(nextThruDt));
                outBaseList.add(outBaseBean);
            }

        } else {
            // Fixed end month
            // Mod Start 07/11/2016 <QC#11805>
            int fromDay;
            if (MONTH_END.equals(contrCloDay)) {
                fromDay = 1;
            } else {
                fromDay = Integer.parseInt(contrCloDay) + 1;
            }
            // START 2018/04/26 K.Kojima [QC#25801,MOD]
            // String tmpYear = String.valueOf(bllgSchdFromDt.get(Calendar.YEAR));
            String tmpYear = String.valueOf(bllgSchdFromDt.get(Calendar.YEAR) - 1);
            // END 2018/04/26 K.Kojima [QC#25801,MOD]
            String tmpMth = ZYPCommonFunc.leftPad(bllgCycleTMsg.bllgCycleStartMth.getValue(), 2, "0");
            String tmpDay = ZYPCommonFunc.leftPad(Integer.toString(fromDay), 2, "0");
            Calendar tmpFromDt = toCalendar(tmpYear + tmpMth + tmpDay);
            Calendar tmpThruDt = (Calendar) tmpFromDt.clone();
            tmpThruDt.add(Calendar.DATE, -1);
            tmpThruDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
            if (MONTH_END.equals(contrCloDay)) {
                tmpThruDt = setEndMonth(tmpThruDt);
            }
            // START 2018/04/26 K.Kojima [QC#25801,MOD]
            // for (int i = 0; i < MTH_MAX / bllgCycleMthAot.intValue(); i++) {
            for (int i = 0; i < MTH_MAX * 2 / bllgCycleMthAot.intValue(); i++) {
            // END 2018/04/26 K.Kojima [QC#25801,MOD]
                if (tmpFromDt.compareTo(bllgSchdFromDt) <= 0 && tmpThruDt.compareTo(bllgSchdFromDt) >= 0) {
                    tmpThruDt.add(Calendar.MONTH, -(bllgCycleMthAot.intValue()));
                    if (MONTH_END.equals(contrCloDay)) {
                        freqProdEndDt = setEndMonth(tmpThruDt);
                    } else {
                        freqProdEndDt = tmpThruDt;
                    }
                    break;
                }
                tmpFromDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
                tmpThruDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
                if (MONTH_END.equals(contrCloDay)) {
                    tmpThruDt = setEndMonth(tmpThruDt);
                }
            }
            // Add End 07/11/2016 <QC#11805>
            Calendar freqStartDt = (Calendar) freqProdEndDt.clone();
            freqStartDt.add(Calendar.DATE, 1);

            if (freqStartDt.compareTo(bllgSchdFromDt) != 0) {

                if (freqProdEndDt.compareTo(bllgSchdFromDt) <= 0) {

                    freqProdEndDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
                    if (MONTH_END.equals(contrCloDay)) {
                        freqProdEndDt = setEndMonth(freqProdEndDt);
                    }
                    // QC#29061
                    if (freqProdEndDt.compareTo(bllgSchdThruDt) > 0) {
                        freqProdEndDt = bllgSchdThruDt;
                    }
                    nextThruDt = freqProdEndDt;

                } else if (bllgSchdFromDt.compareTo(bllgSchdThruDt) > 0) {

                    nextThruDt = bllgSchdThruDt;
                } else {
                    nextThruDt = freqProdEndDt;
                }

                // per days
                seqNum++;
                int perSchdNum = getDiffDays(nextThruDt, bllgSchdFromDt) + 1;
                outBaseBean = new CalcSchdSmryTermAndAmtForBaseBean();
                outBaseBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
                outBaseBean.setPerSchdNum(new BigDecimal(perSchdNum));
                outBaseBean.setPerBllgCycleCd(BLLG_CYCLE.DAILY);
                outBaseBean.setBllgSchdFromDt(toString(bllgSchdFromDt));
                outBaseBean.setBllgSchdThruDt(toString(nextThruDt));
                outBaseList.add(outBaseBean);
            }
        }
        // ----------------------------------------
        // 2nd cycle
        // ----------------------------------------
        int diffMonth = getDiffMonths(bllgSchdThruDt, nextThruDt, contrCloDay);
        int perSchdNum = diffMonth / bllgCycleMthAot.intValue();
        if (perSchdNum > 0) {
            seqNum++;
            outBaseBean = new CalcSchdSmryTermAndAmtForBaseBean();
            outBaseBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
            outBaseBean.setPerSchdNum(new BigDecimal(perSchdNum));
            outBaseBean.setPerBllgCycleCd(inBean.getBllgCycleCd());

            Calendar nextThruDt2 = (Calendar) nextThruDt.clone();
            nextThruDt2.add(Calendar.DATE, 1);
            outBaseBean.setBllgSchdFromDt(toString(nextThruDt2));

            nextThruDt2 = (Calendar) nextThruDt.clone();
            nextThruDt2.add(Calendar.MONTH, perSchdNum * bllgCycleMthAot.intValue());
            if (MONTH_END.equals(contrCloDay)) {
                nextThruDt2 = setEndMonth(nextThruDt2);
            }

            outBaseBean.setBllgSchdThruDt(toString(nextThruDt2));
            outBaseList.add(outBaseBean);
        }
        // ----------------------------------------
        // 3rd cycle
        // ----------------------------------------
        String beforeCycleThruDt = "";
        if (outBaseList.isEmpty()) {
            Calendar calBeforeCycleThruDt = (Calendar) bllgSchdFromDt.clone();
            calBeforeCycleThruDt.add(Calendar.DATE, -1);
            beforeCycleThruDt = toString(calBeforeCycleThruDt);
        } else {
            beforeCycleThruDt = outBaseList.get(outBaseList.size() - 1).getBllgSchdThruDt();
        }
        Calendar beforeThruDt = toCalendar(beforeCycleThruDt);

        if (bllgSchdThruDt.compareTo(beforeThruDt) != 0) {
            // Per days
            int perSchdnum = getDiffDays(bllgSchdThruDt, beforeThruDt);

            seqNum++;
            outBaseBean = new CalcSchdSmryTermAndAmtForBaseBean();
            outBaseBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
            outBaseBean.setPerSchdNum(new BigDecimal(perSchdnum));
            outBaseBean.setPerBllgCycleCd(BLLG_CYCLE.DAILY);

            beforeThruDt.add(Calendar.DATE, 1);
            outBaseBean.setBllgSchdFromDt(toString(beforeThruDt));
            outBaseBean.setBllgSchdThruDt(toString(bllgSchdThruDt));
            outBaseList.add(outBaseBean);
        }
        // Add Start 07/11/2016 <QC#4961>
        for (CalcSchdSmryTermAndAmtForBaseBean baseBean : outBaseList) {
            baseBean.setBaseBllgCycleCd(inBean.getBllgCycleCd());
            baseBean.setBasePrcDealAmt(inBean.getBasePrcDealAmt());
        }
        // Add End 07/11/2016 <QC#4961>
    }

    /**
     * @param inBean
     * @param outBaseList
     * @param baseChrgFlg
     * @param bllgCycleTMsg
     */
    private static void usgChrgProcess(CalcSchdSmryTermAndAmtBean inBean, List<CalcSchdSmryTermAndAmtForUsageBean> outUsageList, BLLG_CYCLETMsg bllgCycleTMsg) {

        CalcSchdSmryTermAndAmtForUsageBean outUsgBean;
        BigDecimal bllgCycleMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();
        BigDecimal bllgCycleStartMth = toBigDecimal(bllgCycleTMsg.bllgCycleStartMth.getValue());
        String contrCloDay = inBean.getContrCloDay();

        int seqNum = 0;

        Calendar freqProdEndDt = getFreqEndDt(inBean.getBllgSchdFromDt(), inBean.getContrCloDay());
        Calendar bllgSchdFromDt = toCalendar(inBean.getBllgSchdFromDt());
        Calendar bllgSchdThruDt = toCalendar(inBean.getBllgSchdThruDt());
        Calendar nextThruDt = (Calendar) freqProdEndDt.clone();

        // ----------------------------------------
        // 1st Cycle
        // ----------------------------------------
        if (!hasValue(bllgCycleStartMth)) {
            // Not fixed end month
            Calendar freqStartDt = (Calendar) freqProdEndDt.clone();
            freqStartDt.add(Calendar.DATE, 1);

            if (freqStartDt.compareTo(bllgSchdFromDt) != 0) {
                // mod start 2017/07/11 QC#19823
//                if (freqProdEndDt.compareTo(bllgSchdFromDt) <= 0) {
//                    freqProdEndDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
//                    if (MONTH_END.equals(contrCloDay)) {
//                        freqProdEndDt = setEndMonth(freqProdEndDt);
//                    }
//                    nextThruDt = freqProdEndDt;
//                    // START 2017/04/25 K.Kitachi [QC#18222, ADD]
//                    if (nextThruDt.compareTo(bllgSchdThruDt) > 0) {
//                        nextThruDt = bllgSchdThruDt;
//                    }
//                    // END 2017/04/25 K.Kitachi [QC#18222, ADD]
//
//                } else if (bllgSchdFromDt.compareTo(bllgSchdThruDt) > 0) {
//                    nextThruDt = bllgSchdThruDt;
//
//                } else {
//                    nextThruDt = freqProdEndDt;
//                }
                int cloDay = Integer.parseInt(contrCloDay);
                int bllgSchdFromDay = bllgSchdFromDt.get(Calendar.DAY_OF_MONTH);
                nextThruDt = (Calendar) bllgSchdFromDt.clone();
                if (cloDay < bllgSchdFromDay) {
                    nextThruDt.add(Calendar.MONTH, 1);
                    nextThruDt.set(Calendar.DATE, cloDay);
                } else {
                    if (MONTH_END.equals(contrCloDay)) {
                        nextThruDt = setEndMonth(nextThruDt);
                    } else {
                        nextThruDt.set(Calendar.DATE, cloDay);
                    }
                }
                // mod end   2017/07/11 QC#19823

                // START 2018/04/19 K.Kojima [QC#25607,ADD]
                if (nextThruDt.compareTo(bllgSchdThruDt) > 0) {
                    nextThruDt = (Calendar) bllgSchdThruDt.clone();
                }
                // END 2018/04/19 K.Kojima [QC#25607,ADD]

                // per days
                seqNum++;
                int perSchdNum = getDiffDays(nextThruDt, bllgSchdFromDt) + 1;
                outUsgBean = new CalcSchdSmryTermAndAmtForUsageBean();
                outUsgBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
                outUsgBean.setPerSchdNum(new BigDecimal(perSchdNum));
                outUsgBean.setPerBllgCycleCd(BLLG_CYCLE.DAILY);
                outUsgBean.setBllgSchdFromDt(toString(bllgSchdFromDt));
                outUsgBean.setBllgSchdThruDt(toString(nextThruDt));
                outUsageList.add(outUsgBean);
            }

        } else {
            // Fixed end month
            // Mod Start 07/11/2016 <QC#11805>
            int fromDay;
            if (MONTH_END.equals(contrCloDay)) {
                fromDay = 1;
            } else {
                fromDay = Integer.parseInt(contrCloDay) + 1;
            }
            // START 2018/04/26 K.Kojima [QC#25801,MOD]
            // String tmpYear = String.valueOf(bllgSchdFromDt.get(Calendar.YEAR));
            String tmpYear = String.valueOf(bllgSchdFromDt.get(Calendar.YEAR) - 1);
            // END 2018/04/26 K.Kojima [QC#25801,MOD]
            String tmpMth = ZYPCommonFunc.leftPad(bllgCycleTMsg.bllgCycleStartMth.getValue(), 2, "0");
            String tmpDay = ZYPCommonFunc.leftPad(Integer.toString(fromDay), 2, "0");
            Calendar tmpFromDt = toCalendar(tmpYear + tmpMth + tmpDay);
            Calendar tmpThruDt = (Calendar) tmpFromDt.clone();
            tmpThruDt.add(Calendar.DATE, -1);
            tmpThruDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
            if (MONTH_END.equals(contrCloDay)) {
                tmpThruDt = setEndMonth(tmpThruDt);
            }
            // START 2018/04/26 K.Kojima [QC#25801,MOD]
            // for (int i = 0; i < MTH_MAX / bllgCycleMthAot.intValue(); i++) {
            for (int i = 0; i < MTH_MAX * 2 / bllgCycleMthAot.intValue(); i++) {
            // END 2018/04/26 K.Kojima [QC#25801,MOD]
                if (tmpFromDt.compareTo(bllgSchdFromDt) <= 0 && tmpThruDt.compareTo(bllgSchdFromDt) >= 0) {
                    tmpThruDt.add(Calendar.MONTH, -(bllgCycleMthAot.intValue()));
                    if (MONTH_END.equals(contrCloDay)) {
                        freqProdEndDt = setEndMonth(tmpThruDt);
                    } else {
                        freqProdEndDt = tmpThruDt;
                    }
                    break;
                }
                tmpFromDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
                tmpThruDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
                if (MONTH_END.equals(contrCloDay)) {
                    tmpThruDt = setEndMonth(tmpThruDt);
                }
            }
            // Add End 07/11/2016 <QC#11805>
            Calendar freqStartDt = (Calendar) freqProdEndDt.clone();
            freqStartDt.add(Calendar.DATE, 1);

            if (freqStartDt.compareTo(bllgSchdFromDt) != 0) {

                if (freqProdEndDt.compareTo(bllgSchdFromDt) <= 0) {

                    freqProdEndDt.add(Calendar.MONTH, bllgCycleMthAot.intValue());
                    if (MONTH_END.equals(contrCloDay)) {
                        freqProdEndDt = setEndMonth(freqProdEndDt);
                    }
                    // QC#29061
                    if (freqProdEndDt.compareTo(bllgSchdThruDt) > 0) {
                        freqProdEndDt = bllgSchdThruDt;
                    }
                    nextThruDt = freqProdEndDt;

                } else if (bllgSchdFromDt.compareTo(bllgSchdThruDt) > 0) {

                    nextThruDt = bllgSchdThruDt;
                } else {
                    nextThruDt = freqProdEndDt;
                }

                // per days
                seqNum++;
                int perSchdNum = getDiffDays(nextThruDt, bllgSchdFromDt) + 1;
                outUsgBean = new CalcSchdSmryTermAndAmtForUsageBean();
                outUsgBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
                outUsgBean.setPerSchdNum(new BigDecimal(perSchdNum));
                outUsgBean.setPerBllgCycleCd(BLLG_CYCLE.DAILY);
                outUsgBean.setBllgSchdFromDt(toString(bllgSchdFromDt));
                outUsgBean.setBllgSchdThruDt(toString(nextThruDt));
                outUsageList.add(outUsgBean);
            }
        }
        // ----------------------------------------
        // 2nd cycle
        // ----------------------------------------
        int diffMonth = getDiffMonths(bllgSchdThruDt, nextThruDt, contrCloDay);
        int perSchdNum = diffMonth / bllgCycleMthAot.intValue();
        if (perSchdNum > 0) {
            seqNum++;
            outUsgBean = new CalcSchdSmryTermAndAmtForUsageBean();
            outUsgBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
            outUsgBean.setPerSchdNum(new BigDecimal(perSchdNum));
            outUsgBean.setPerBllgCycleCd(inBean.getBllgCycleCd());

            Calendar nextThruDt2 = (Calendar) nextThruDt.clone();
            nextThruDt2.add(Calendar.DATE, 1);
            outUsgBean.setBllgSchdFromDt(toString(nextThruDt2));

            nextThruDt2 = (Calendar) nextThruDt.clone();
            nextThruDt2.add(Calendar.MONTH, perSchdNum * bllgCycleMthAot.intValue());
            if (MONTH_END.equals(contrCloDay)) {
                nextThruDt2 = setEndMonth(nextThruDt2);
            }

            outUsgBean.setBllgSchdThruDt(toString(nextThruDt2));
            outUsageList.add(outUsgBean);
        }
        // ----------------------------------------
        // 3rd cycle
        // ----------------------------------------
        String beforeCycleThruDt = "";
        if (outUsageList.isEmpty()) {
            Calendar calBeforeCycleThruDt = (Calendar) bllgSchdFromDt.clone();
            calBeforeCycleThruDt.add(Calendar.DATE, -1);
            beforeCycleThruDt = toString(calBeforeCycleThruDt);
        } else {
            beforeCycleThruDt = outUsageList.get(outUsageList.size() - 1).getBllgSchdThruDt();
        }
        Calendar beforeThruDt = toCalendar(beforeCycleThruDt);

        if (bllgSchdThruDt.compareTo(beforeThruDt) != 0) {
            // Per days
            int perSchdnum = getDiffDays(bllgSchdThruDt, beforeThruDt);

            seqNum++;
            outUsgBean = new CalcSchdSmryTermAndAmtForUsageBean();
            outUsgBean.setDsContrBllgSchdSqNum(String.valueOf(seqNum));
            outUsgBean.setPerSchdNum(new BigDecimal(perSchdnum));
            outUsgBean.setPerBllgCycleCd(BLLG_CYCLE.DAILY);

            beforeThruDt.add(Calendar.DATE, 1);
            outUsgBean.setBllgSchdFromDt(toString(beforeThruDt));
            outUsgBean.setBllgSchdThruDt(toString(bllgSchdThruDt));
            outUsageList.add(outUsgBean);
        }
    }

    /**
     * @param inBean
     * @param outBaseList
     * @param bllgCycleTMsg
     */
    private static void calcBasePrcDealAmt(CalcSchdSmryTermAndAmtBean inBean, List<CalcSchdSmryTermAndAmtForBaseBean> outBaseList, CCYTMsg ccyTMsg) {

        int digitNum = ccyTMsg.aftDeclPntDigitNum.getValueInt();

        for (CalcSchdSmryTermAndAmtForBaseBean baseBean : outBaseList) {
            String bllgCycleCd = inBean.getBllgCycleCd();
            String perBllgCycleCd = baseBean.getPerBllgCycleCd();
            BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycle(inBean.getGlblCmpyCd(), bllgCycleCd);
            BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
            BigDecimal bllgCycleMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();

            BigDecimal basePrcDealAmt = inBean.getBasePrcDealAmt();
            BigDecimal bPerSchdNum = baseBean.getPerSchdNum();
            BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
            BigDecimal divRate = year.divide(prrtDivRate, digitNum, BigDecimal.ROUND_HALF_UP);
            BigDecimal perDayDealAmt = bllgCycleMthAot.multiply(basePrcDealAmt);

            if (BLLG_CYCLE.DAILY.equals(perBllgCycleCd)) {
                perDayDealAmt = basePrcDealAmt.multiply(bPerSchdNum).divide(divRate, digitNum, BigDecimal.ROUND_HALF_UP);
            } else {
                perDayDealAmt = basePrcDealAmt.multiply(bPerSchdNum);
            }
            baseBean.setBaseSubTotPrcDealAmt(perDayDealAmt);
        }
    }

    // START 2018/08/27 [QC#24555, ADD]
    public static BigDecimal calcBasePrcAmtForStub(String glblCmpyCd, String bllgCycleCd, BigDecimal baseUnitAmt, BigDecimal bPerSchdNum, int digitNum) {
        BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycle(glblCmpyCd, bllgCycleCd);
        BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
        BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
        BigDecimal divRate = year.divide(prrtDivRate, digitNum, BigDecimal.ROUND_HALF_UP);
        BigDecimal perDayDealAmt = baseUnitAmt.multiply(bPerSchdNum).divide(divRate, digitNum, BigDecimal.ROUND_HALF_UP);
        return perDayDealAmt;
    }
    // END   2018/08/27 [QC#24555, ADD]
    /**
     * @param outBaseList
     * @param basePrcTermDealAmt
     */
    private static void calcTermAmount(List<CalcSchdSmryTermAndAmtForBaseBean> outBaseList, BigDecimal basePrcTermDealAmt, CCYTMsg ccyTMsg) {
        BigDecimal termAmt = basePrcTermDealAmt;
        BigDecimal ttlAmt = getTtlAmt(outBaseList);
        BigDecimal adjustAmt = termAmt.subtract(ttlAmt);
        int digitNum = ccyTMsg.aftDeclPntDigitNum.getValueInt();

        if (adjustAmt.compareTo(BigDecimal.ZERO) != 0) {

            BigDecimal adjAmtSq1;
            BigDecimal adjAmtSq2;
            BigDecimal adjAmtSq3;
            BigDecimal perSchdNumSq1;
            BigDecimal perSchdNumSq3;
            BigDecimal baseSubTotPrcDealAmtSq1;
            BigDecimal baseSubTotPrcDealAmtSq2;
            BigDecimal baseSubTotPrcDealAmtSq3;
            BigDecimal overAmt1 = BigDecimal.ZERO;
            BigDecimal overAmt2 = BigDecimal.ZERO;
            BigDecimal overAmt3 = BigDecimal.ZERO;

            if (outBaseList.size() == SQ3) {

                perSchdNumSq1 = outBaseList.get(0).getPerSchdNum();
                perSchdNumSq3 = outBaseList.get(2).getPerSchdNum();
                baseSubTotPrcDealAmtSq1 = outBaseList.get(0).getBaseSubTotPrcDealAmt();
                baseSubTotPrcDealAmtSq3 = outBaseList.get(2).getBaseSubTotPrcDealAmt();
                baseSubTotPrcDealAmtSq2 = outBaseList.get(1).getBaseSubTotPrcDealAmt();

                // SQ1
                adjAmtSq1 = adjustAmt.multiply(perSchdNumSq1.divide(perSchdNumSq1.add(perSchdNumSq3), digitNum, BigDecimal.ROUND_HALF_UP)).setScale(digitNum, BigDecimal.ROUND_HALF_UP);

                if (baseSubTotPrcDealAmtSq1.add(adjAmtSq1).compareTo(BigDecimal.ZERO) < 0) {
                    overAmt1 = baseSubTotPrcDealAmtSq1.add(adjAmtSq1);
                    outBaseList.get(0).setBaseSubTotPrcDealAmt(BigDecimal.ZERO);
                    outBaseList.get(0).setAdjAmt(baseSubTotPrcDealAmtSq1.negate());
                } else {
                    outBaseList.get(0).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq1.add(adjAmtSq1));
                    outBaseList.get(0).setAdjAmt(adjAmtSq1);
                }

                // SQ3
                adjAmtSq3 = adjustAmt.subtract(adjAmtSq1);

                if (baseSubTotPrcDealAmtSq3.add(adjAmtSq3).compareTo(BigDecimal.ZERO) < 0) {
                    overAmt3 = baseSubTotPrcDealAmtSq3.add(adjAmtSq3);
                    outBaseList.get(2).setBaseSubTotPrcDealAmt(BigDecimal.ZERO);
                    outBaseList.get(2).setAdjAmt(baseSubTotPrcDealAmtSq3.negate());
                } else {
                    outBaseList.get(2).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq3.add(adjAmtSq3));
                    outBaseList.get(2).setAdjAmt(adjAmtSq3);
                }

                // SQ2
                adjAmtSq2 = overAmt1.add(overAmt3);
                outBaseList.get(1).setAdjAmt(adjAmtSq2);
                outBaseList.get(1).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq2.add(adjAmtSq2));
            }

            if (outBaseList.size() == 2 && BLLG_CYCLE.DAILY.equals(outBaseList.get(0).getPerBllgCycleCd())) {

                baseSubTotPrcDealAmtSq1 = outBaseList.get(0).getBaseSubTotPrcDealAmt();
                baseSubTotPrcDealAmtSq2 = outBaseList.get(1).getBaseSubTotPrcDealAmt();

                // SQ1
                adjAmtSq1 = adjustAmt;

                if (baseSubTotPrcDealAmtSq1.add(adjAmtSq1).compareTo(BigDecimal.ZERO) < 0) {
                    overAmt1 = baseSubTotPrcDealAmtSq1.add(adjAmtSq1);
                    outBaseList.get(0).setBaseSubTotPrcDealAmt(BigDecimal.ZERO);
                    outBaseList.get(0).setAdjAmt(baseSubTotPrcDealAmtSq1.negate());
                } else {
                    outBaseList.get(0).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq1.add(adjAmtSq1));
                    outBaseList.get(0).setAdjAmt(adjAmtSq1);
                }

                // SQ2
                adjAmtSq2 = overAmt1;
                outBaseList.get(1).setAdjAmt(adjAmtSq2);
                outBaseList.get(1).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq2.add(adjAmtSq2));
            }

            if (outBaseList.size() == 2 && !BLLG_CYCLE.DAILY.equals(outBaseList.get(0).getPerBllgCycleCd())) {

                baseSubTotPrcDealAmtSq1 = outBaseList.get(0).getBaseSubTotPrcDealAmt();
                baseSubTotPrcDealAmtSq2 = outBaseList.get(1).getBaseSubTotPrcDealAmt();

                // SQ2
                adjAmtSq2 = adjustAmt;

                if (baseSubTotPrcDealAmtSq2.add(adjAmtSq2).compareTo(BigDecimal.ZERO) < 0) {
                    overAmt2 = baseSubTotPrcDealAmtSq2.add(adjAmtSq2);
                    outBaseList.get(1).setBaseSubTotPrcDealAmt(BigDecimal.ZERO);
                    outBaseList.get(1).setAdjAmt(baseSubTotPrcDealAmtSq2.negate());
                } else {
                    outBaseList.get(1).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq2.add(adjAmtSq2));
                    outBaseList.get(1).setAdjAmt(adjAmtSq2);
                }

                // SQ1
                adjAmtSq1 = overAmt2;
                outBaseList.get(0).setAdjAmt(adjAmtSq1);
                outBaseList.get(0).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq1.add(adjAmtSq1));
            }

            if (outBaseList.size() == 1) {

                baseSubTotPrcDealAmtSq1 = outBaseList.get(0).getBaseSubTotPrcDealAmt();

                // SQ1
                adjAmtSq1 = adjustAmt;
                outBaseList.get(0).setAdjAmt(adjAmtSq1);
                outBaseList.get(0).setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmtSq1.add(adjAmtSq1));
            }
        }
    }

    /**
     * @param inBean CalcSchdSmryTermAndAmtBean
     * @return CalcSchdSmryTermAndAmtBean
     */
    public static CalcSchdSmryTermAndAmtBean calcAllowance(CalcSchdSmryTermAndAmtBean inBean) {
        CalcSchdSmryTermAndAmtBean outBean = new CalcSchdSmryTermAndAmtBean();
        List<CalcSchdSmryTermAndAmtForUsageBean> outUsageList = new ArrayList<CalcSchdSmryTermAndAmtForUsageBean>();
        CalcSchdSmryTermAndAmtForUsageBean outUsgBean;

        // loop for XS Copy
        List<CalcSchdSmryTermAndAmtForUsageBean> inUsgList = inBean.getUsageList();
        for (CalcSchdSmryTermAndAmtForUsageBean inUsgBean : inUsgList) {

            String glblCmpyCd = inBean.getGlblCmpyCd();
            String bllgCycleCd = inBean.getBllgCycleCd();
            String perBllgCycleCd = inUsgBean.getPerBllgCycleCd();
            BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycle(glblCmpyCd, bllgCycleCd);
            if (bllgCycleTMsg == null) {
                return outBean;
            }

            BigDecimal allowance = inUsgBean.getXsMtrCopyQty();
            BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
            BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
            BigDecimal divRate = year.divide(prrtDivRate, 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal perSchdNum = inUsgBean.getPerSchdNum();

            outUsgBean = new CalcSchdSmryTermAndAmtForUsageBean();
            if (BLLG_CYCLE.DAILY.equals(perBllgCycleCd)) {
                allowance = allowance.multiply(perSchdNum).divide(divRate, 0, BigDecimal.ROUND_HALF_UP);
            } else {
                allowance = inUsgBean.getXsMtrCopyQty();
            }

            outUsgBean.setDsContrBllgMtrPk(inUsgBean.getDsContrBllgMtrPk());
            outUsgBean.setContrXsCopyPk(inUsgBean.getContrXsCopyPk());
            outUsgBean.setXsMtrCopyQty(allowance);
            outUsgBean.setXsMtrAmtRate(inUsgBean.getXsMtrAmtRate());
            outUsgBean.setXsMtrFirstFlg(inUsgBean.getXsMtrFirstFlg());

            outUsageList.add(outUsgBean);
        }
        outBean.setUsageList(outUsageList);
        return outBean;
    }

    // START 2018/08/27 [QC#24555, ADD]
    public static BigDecimal calcAllowanceForStub(String glblCmpyCd, String bllgCycleCd, BigDecimal unitAllowance, BigDecimal bPerSchdNum) {
        BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycle(glblCmpyCd, bllgCycleCd);
        BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
        BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
        BigDecimal divRate = year.divide(prrtDivRate, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal perDayAllowance = unitAllowance.multiply(bPerSchdNum).divide(divRate, 0, BigDecimal.ROUND_HALF_UP);
        return perDayAllowance;
    }
    // END   2018/08/27 [QC#24555, ADD]
    private static BigDecimal getTtlAmt(List<CalcSchdSmryTermAndAmtForBaseBean> outBaseList) {

        BigDecimal ttlAmt = BigDecimal.ZERO;

        for (CalcSchdSmryTermAndAmtForBaseBean outBaseBean : outBaseList) {

            ttlAmt = ttlAmt.add(outBaseBean.getBaseSubTotPrcDealAmt());
        }
        return ttlAmt;
    }

    private static Calendar getFreqEndDt(String bllgFromDt, String contrCloDay) {

        Calendar calBllgFromDt = toCalendar(bllgFromDt);
        if (hasValue(contrCloDay)) {

            int cloDay = Integer.parseInt(contrCloDay);

            if (MONTH_END.equals(contrCloDay)) {
                calBllgFromDt.add(Calendar.MONTH, -1);
                cloDay = calBllgFromDt.getActualMaximum(Calendar.DATE);
                calBllgFromDt.set(Calendar.DATE, cloDay);
            } else {
                calBllgFromDt.set(Calendar.DATE, cloDay);
            }

        }
        return calBllgFromDt;
    }

    private static int getDiffDays(Calendar after, Calendar before) {

        // START 2017/03/31 K.Kitachi [QC#18117, MOD]
//        long diffTime = after.getTimeInMillis() - before.getTimeInMillis();
//        int millisOfDay = MILLIS_OF_DAY;
//        int diffDays = (int) (diffTime / millisOfDay);
        int diffDays = ZYPDateUtil.getDiffDays(toString(after), toString(before));
        // END 2017/03/31 K.Kitachi [QC#18117, MOD]
        return diffDays;
    }

    private static int getDiffMonths(Calendar after, Calendar before, String contrCloDay) {

        int count = 0;

        Calendar tmp = (Calendar) before.clone();
        tmp.add(Calendar.MONTH, 1);
        if (MONTH_END.equals(contrCloDay)) {
            tmp = setEndMonth(tmp);
        }

        while (tmp.compareTo(after) <= 0) {
            tmp.add(Calendar.MONTH, 1);
            if (MONTH_END.equals(contrCloDay)) {
                tmp = setEndMonth(tmp);
            }
            count++;
        }

        return count;
    }

    private static Calendar setEndMonth(Calendar cal) {

        int cloDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, cloDay);
        return cal;
    }

    private static BLLG_CYCLETMsg getBllgCycle(String glblCmpyCd, String bllgCycleCd) {

        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.bllgCycleCd, bllgCycleCd);
        return (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static CCYTMsg getCcy(String glblCmpyCd, String ccyCd) {

        CCYTMsg inMsg = new CCYTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.ccyCd, ccyCd);
        return (CCYTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static BigDecimal toBigDecimal(String strNum) {

        if (hasValue(strNum)) {
            return new BigDecimal(strNum);
        }
        return null;
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
        SimpleDateFormat sdf = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        return sdf.format(cal.getTime());
    }
}
