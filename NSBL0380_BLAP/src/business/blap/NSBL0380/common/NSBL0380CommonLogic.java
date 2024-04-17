/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0380.common;


import static business.blap.NSBL0380.constant.NSBL0380Constant.AS_OF_DATE;
import static business.blap.NSBL0380.constant.NSBL0380Constant.DS_ORG_UNIT;
import static business.blap.NSBL0380.constant.NSBL0380Constant.NSBM0025E;
import static business.blap.NSBL0380.constant.NSBL0380Constant.NZZM0001W;
import static business.blap.NSBL0380.constant.NSBL0380Constant.NZZM0002I;
import static business.blap.NSBL0380.constant.NSBL0380Constant.NZZM0012E;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_CD;
import static business.blap.NSBL0380.constant.NSBL0380Constant.REGEX;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ZZZM9001E;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ZZZM9026E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDMsg;
import business.blap.NSBL0380.NSBL0380CMsg;
import business.blap.NSBL0380.NSBL0380Query;
import business.blap.NSBL0380.NSBL0380SMsg;
import business.db.DS_ORG_UNITTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * History Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         T.Tsuchida      Create          N/A
 * 2017/07/14   Hitachi         K.Kim           Update          QC#19934
 *</pre>
 */
public class NSBL0380CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0380CMsg
     * @param sMsg NSBL0380SMsg
     */
    public static void clearMsg(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    /**
     * Is Error Search Condition
     * @param cMsg NSBL0380CMsg
     * @return boolean
     */
    public static boolean isErrorSearchCondition(NSBL0380CMsg cMsg) {
        // mandatory check
        if (!hasValue(cMsg.orgCd_HT)) {
            cMsg.setMessageInfo(NZZM0012E, new String[] {ORG_CD });
            return false;
        }
        if (!hasValue(cMsg.cratDt_H)) {
            cMsg.setMessageInfo(NZZM0012E, new String[] {AS_OF_DATE });
            return false;
        }
        setInitParams(cMsg);
        if (!checkParams(cMsg)) {
            cMsg.orgCd_HT.clearErrorInfo();
        }
        if (!checkDate(cMsg)) {
            return false;
        }
        return true;
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSBL0380CMsg
     */
    public static void setInitParams(NSBL0380CMsg cMsg) {
        setValue(cMsg.xxScrItem7Txt_H, ZYPDateUtil.DateFormatter(cMsg.cratDt_H.getValue(), "yyyyMMdd", "MM/yyyy"));
        setValue(cMsg.xxSumYrMth_H, ZYPDateUtil.DateFormatter(cMsg.xxScrItem7Txt_H.getValue(), "MM/yyyy", "yyyyMM"));
    }

    /**
     * checkDate
     * @param cMsg NSBL0380CMsg
     * @return boolean
     */
       public static boolean checkDate(NSBL0380CMsg cMsg) {
           String regex = REGEX;
           Pattern p = Pattern.compile(regex);
           Matcher m = p.matcher(cMsg.xxScrItem7Txt_H.getValue());
           if (m.find()) {
               cMsg.xxScrItem7Txt_H.setValue(cMsg.xxScrItem7Txt_H.getValue());
               return true;
           } else {
               cMsg.xxScrItem7Txt_H.setErrorInfo(1, ZZZM9026E, new String[] {AS_OF_DATE });
               return false;
           }
        }

    /**
     * Check existence of Service Group
     * @param cMsg NSBL0380CMsg
     * @return DS_ORG_UNITTMsg
     */
    public static DS_ORG_UNITTMsg getDsOrgUnit(NSBL0380CMsg cMsg) {
        DS_ORG_UNITTMsg result = NSBL0380Query.getInstance().getDsOrgUnit(cMsg.glblCmpyCd.getValue(), cMsg.orgCd_HT.getValue());
        return result;
    }

    /**
     * Set Search Parameters
     * @param cMsg NSBL0380CMsg
     * @param sMsg NSBL0380SMsg
     */
    public static void setSearchParams(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        if (!hasValue(cMsg.xxScrItem7Txt_H)) {
            setValue(cMsg.xxSumYrMth_H, ZYPDateUtil.DateFormatter(cMsg.slsDt.getValue(), "yyyyMMdd", "yyyyMM"));
            setValue(cMsg.xxScrItem7Txt_H, ZYPDateUtil.DateFormatter(cMsg.slsDt.getValue(), "yyyyMMdd", "MM/yyyy"));
        } else {
            if (!checkDate(cMsg)) {
                return;
            }
            setValue(cMsg.xxSumYrMth_H, ZYPDateUtil.DateFormatter(cMsg.xxScrItem7Txt_H.getValue(), "MM/yyyy", "yyyyMM"));
        }
    }

    /**
     * Check existence of Service Group
     * @param cMsg NSBL0380CMsg
     * @return boolean
     */
    public static boolean checkParams(NSBL0380CMsg cMsg) {
        DS_ORG_UNITTMsg result = getDsOrgUnit(cMsg);
        if (result == null) {
            cMsg.orgCd_HT.setErrorInfo(1, NSBM0025E, new String[] {ORG_CD, DS_ORG_UNIT });
            return false;
        }
        setValue(cMsg.orgDescTxt_H, result.orgNm);
        return true;
    }

    /**
     * Get Search Data
     * @param cMsg NSBL0380CMsg
     * @param sMsg NSBL0380SMsg
     */
    public static void findItems(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        // find Item
        if (getSearchData(cMsg, sMsg)) {
            // calc average
            calAverage(sMsg);
            // display hhmm format
            hhmmFormat(sMsg);
        } else {
            return;
        }
    }

    /**
     * Get Search Data
     * @param cMsg NSBL0380CMsg
     * @param sMsg NSBL0380SMsg
     * @return boolean
     */
    public static boolean getSearchData(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSBL0380Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            return true;
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
            return false;
        }
    }

    /**
     * calculate for Average
     * @param sMsg NSBL0380SMsg
     */
    private static void calAverage(NSBL0380SMsg sMsg) {
        BigDecimal totInProcTaskCntSum = BigDecimal.ZERO;
        BigDecimal prtWaitTaskCntSum = BigDecimal.ZERO;
        BigDecimal spclWaitTaskCntSum = BigDecimal.ZERO;
        BigDecimal othOpenTaskCntSum = BigDecimal.ZERO;
        BigDecimal custTaskCntSum = BigDecimal.ZERO;
        BigDecimal esclTaskCntSum = BigDecimal.ZERO;
        BigDecimal cratTaskCntSum = BigDecimal.ZERO;
        BigDecimal avalTechCntSum = BigDecimal.ZERO;
        BigDecimal aftHourTaskCntSum = BigDecimal.ZERO;
        BigDecimal cloTaskCntSum = BigDecimal.ZERO;
        BigDecimal prtFailCntSum = BigDecimal.ZERO;
        BigDecimal postEntryTaskCntSum = BigDecimal.ZERO;
        BigDecimal rspTmCustTaskRateSum = BigDecimal.ZERO;
        BigDecimal rspTmAllTaskRateSum = BigDecimal.ZERO;
        BigDecimal aftHourAvalTechCntSum = BigDecimal.ZERO;
        BigDecimal custTaskTotRspAotSum = BigDecimal.ZERO;
        BigDecimal cloCustTaskCntSum = BigDecimal.ZERO;
        BigDecimal allTaskTotRspAotSum = BigDecimal.ZERO;
        BigDecimal cloAllTaskCntSum = BigDecimal.ZERO;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (hasValue(sMsg.A.no(i).totInProcTaskCnt_A)) {
                totInProcTaskCntSum = totInProcTaskCntSum.add(sMsg.A.no(i).totInProcTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).prtWaitTaskCnt_A)) {
                prtWaitTaskCntSum = prtWaitTaskCntSum.add(sMsg.A.no(i).prtWaitTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).spclWaitTaskCnt_A)) {
                spclWaitTaskCntSum = spclWaitTaskCntSum.add(sMsg.A.no(i).spclWaitTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).othOpenTaskCnt_A)) {
                othOpenTaskCntSum = othOpenTaskCntSum.add(sMsg.A.no(i).othOpenTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).custTaskCnt_A)) {
                custTaskCntSum = custTaskCntSum.add(sMsg.A.no(i).custTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).esclTaskCnt_A)) {
                esclTaskCntSum = esclTaskCntSum.add(sMsg.A.no(i).esclTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).cratTaskCnt_A)) {
                cratTaskCntSum = cratTaskCntSum.add(sMsg.A.no(i).cratTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).avalTechCnt_A)) {
                avalTechCntSum = avalTechCntSum.add(sMsg.A.no(i).avalTechCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).aftHourTaskCnt_A)) {
                aftHourTaskCntSum = aftHourTaskCntSum.add(sMsg.A.no(i).aftHourTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).cloTaskCnt_A)) {
                cloTaskCntSum = cloTaskCntSum.add(sMsg.A.no(i).cloTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).prtFailCnt_A)) {
                prtFailCntSum = prtFailCntSum.add(sMsg.A.no(i).prtFailCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).postEntryTaskCnt_A)) {
                postEntryTaskCntSum = postEntryTaskCntSum.add(sMsg.A.no(i).postEntryTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).rspTmCustTaskRate_A)) {
                rspTmCustTaskRateSum = rspTmCustTaskRateSum.add(sMsg.A.no(i).rspTmCustTaskRate_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).rspTmAllTaskRate_A)) {
                rspTmAllTaskRateSum = rspTmAllTaskRateSum.add(sMsg.A.no(i).rspTmAllTaskRate_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).aftHourAvalTechCnt_A)) {
                aftHourAvalTechCntSum = aftHourAvalTechCntSum.add(sMsg.A.no(i).aftHourAvalTechCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).custTaskTotRspAot_A)) {
                custTaskTotRspAotSum = custTaskTotRspAotSum.add(sMsg.A.no(i).custTaskTotRspAot_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).cloCustTaskCnt_A)) {
                cloCustTaskCntSum = cloCustTaskCntSum.add(sMsg.A.no(i).cloCustTaskCnt_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).allTaskTotRspAot_A)) {
                allTaskTotRspAotSum = allTaskTotRspAotSum.add(sMsg.A.no(i).allTaskTotRspAot_A.getValue());
            }
            if (hasValue(sMsg.A.no(i).cloAllTaskCnt_A)) {
                cloAllTaskCntSum = cloAllTaskCntSum.add(sMsg.A.no(i).cloAllTaskCnt_A.getValue());
            }
        }

        BigDecimal cratTaskPerTechRate;
        if (avalTechCntSum.compareTo(BigDecimal.ZERO) == 0) {
            cratTaskPerTechRate = BigDecimal.ZERO;
        } else {
            BigDecimal perTechCratNumer = cratTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), BigDecimal.ROUND_HALF_UP);
            BigDecimal perTechCratDenom = avalTechCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), BigDecimal.ROUND_HALF_UP);
            // mod start 2017/07/14 QC#19934
            if (perTechCratDenom.compareTo(BigDecimal.ZERO) == 0) {
                cratTaskPerTechRate = BigDecimal.ZERO;
            } else {
                cratTaskPerTechRate = perTechCratNumer.divide(perTechCratDenom, 2, BigDecimal.ROUND_HALF_UP);
            }
            // mod end 2017/07/14 QC#19934
        }
        BigDecimal aftHourTaskPerTechRate;
        if (aftHourAvalTechCntSum.compareTo(BigDecimal.ZERO) == 0) {
            aftHourTaskPerTechRate = BigDecimal.ZERO;
        } else {
            BigDecimal perTechAftNumer = aftHourTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), BigDecimal.ROUND_HALF_UP);
            BigDecimal perTechAftDenom = aftHourAvalTechCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), BigDecimal.ROUND_HALF_UP);
            // mod start 2017/07/14 QC#19934
            if (perTechAftDenom.compareTo(BigDecimal.ZERO) == 0) {
                aftHourTaskPerTechRate = BigDecimal.ZERO;
            } else {
                aftHourTaskPerTechRate = perTechAftNumer.divide(perTechAftDenom, 2, BigDecimal.ROUND_HALF_UP);
            }
            // mod end 2017/07/14 QC#19934
        }
        BigDecimal cloTaskPerTechRate;
        if (avalTechCntSum.compareTo(BigDecimal.ZERO) == 0) {
            cloTaskPerTechRate = BigDecimal.ZERO;
        } else {
            BigDecimal perTechCloNumer = cloTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), BigDecimal.ROUND_HALF_UP);
            BigDecimal perTechCloDenom = avalTechCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), BigDecimal.ROUND_HALF_UP);
            // mod start 2017/07/14 QC#19934
            if (perTechCloDenom.compareTo(BigDecimal.ZERO) == 0) {
                cloTaskPerTechRate = BigDecimal.ZERO;
            } else {
                cloTaskPerTechRate = perTechCloNumer.divide(perTechCloDenom, 2, BigDecimal.ROUND_HALF_UP);
            }
            // mod end 2017/07/14 QC#19934
        }

        BigDecimal rspTmCustTaskRate;
        if (cloCustTaskCntSum.compareTo(BigDecimal.ZERO) == 0) {
            rspTmCustTaskRate = BigDecimal.ZERO;
        } else {
            rspTmCustTaskRate = custTaskTotRspAotSum.divide(cloCustTaskCntSum, 0, BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal rspTmAllTaskRate;
        if (cloAllTaskCntSum.compareTo(BigDecimal.ZERO) == 0) {
            rspTmAllTaskRate = BigDecimal.ZERO;
        } else {
            rspTmAllTaskRate = allTaskTotRspAotSum.divide(cloAllTaskCntSum, 0, BigDecimal.ROUND_HALF_UP);
        }
        sMsg.B.setValidCount(1);
        setValue(sMsg.B.no(0).xxDtlAmt_B1, totInProcTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_B2, prtWaitTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_B3, spclWaitTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_B4, othOpenTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_B5, custTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_B6, esclTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_B7, cratTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_B8, cratTaskPerTechRate);
        setValue(sMsg.B.no(0).xxDtlAmt_B9, aftHourTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_BA, aftHourTaskPerTechRate);
        setValue(sMsg.B.no(0).xxDtlAmt_BB, cloTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_BC, cloTaskPerTechRate);
        setValue(sMsg.B.no(0).xxDtlAmt_BD, prtFailCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_BE, postEntryTaskCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_BF, avalTechCntSum.divide(new BigDecimal(sMsg.A.getValidCount()), 2, BigDecimal.ROUND_HALF_UP));
        setValue(sMsg.B.no(0).xxDtlAmt_BG, rspTmCustTaskRate);
        setValue(sMsg.B.no(0).xxDtlAmt_BH, rspTmAllTaskRate);
    }

    /**
     * hasValueItems
     * @param sMsg NSBL0380SMsg
     * @return boolean
     */
    private static NSBL0380SMsg hhmmFormat(NSBL0380SMsg sMsg) {

        BigDecimal hh = new BigDecimal("60.00");

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (hasValue(sMsg.A.no(i).rspTmCustTaskRate_A)) {

                BigDecimal rspTmCustTaskRate = null;
                BigDecimal rspTmCustTaskRate2 = null;
                rspTmCustTaskRate = sMsg.A.no(i).rspTmCustTaskRate_A.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmCustTaskRate2 = sMsg.A.no(i).rspTmCustTaskRate_A.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuffer hhmm = new StringBuffer();

                if (1 == rspTmCustTaskRate.precision()) {
                    hhmm.append('0');
                    hhmm.append(rspTmCustTaskRate);
                } else {
                    hhmm.append(rspTmCustTaskRate);
                }
                hhmm.append(':');
                if (1 == rspTmCustTaskRate2.precision()) {
                    hhmm.append('0');
                    hhmm.append(rspTmCustTaskRate2);
                } else {
                    hhmm.append(rspTmCustTaskRate2);
                }
                sMsg.A.no(i).xxDtTm_A1.setValue(hhmm.toString());
            }

            if (hasValue(sMsg.A.no(i).rspTmAllTaskRate_A)) {

                BigDecimal rspTmAllTaskRate = null;
                BigDecimal rspTmAllTaskRate2 = null;
                rspTmAllTaskRate = sMsg.A.no(i).rspTmAllTaskRate_A.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmAllTaskRate2 = sMsg.A.no(i).rspTmAllTaskRate_A.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuffer hhmm2 = new StringBuffer();

                if (1 == rspTmAllTaskRate.precision()) {
                    hhmm2.append('0');
                    hhmm2.append(rspTmAllTaskRate);
                } else {
                    hhmm2.append(rspTmAllTaskRate);
                }
                hhmm2.append(':');
                if (1 == rspTmAllTaskRate2.precision()) {
                    hhmm2.append('0');
                    hhmm2.append(rspTmAllTaskRate2);
                } else {
                    hhmm2.append(rspTmAllTaskRate2);
                }
                sMsg.A.no(i).xxDtTm_A2.setValue(hhmm2.toString());
            }
        }

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (hasValue(sMsg.B.no(i).xxDtlAmt_BG)) {

                BigDecimal rspTmCustTaskRate = null;
                BigDecimal rspTmCustTaskRate2 = null;
                rspTmCustTaskRate = sMsg.B.no(i).xxDtlAmt_BG.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmCustTaskRate2 = sMsg.B.no(i).xxDtlAmt_BG.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuffer hhmm = new StringBuffer();

                if (1 == rspTmCustTaskRate.precision()) {
                    hhmm.append('0');
                    hhmm.append(rspTmCustTaskRate);
                } else {
                    hhmm.append(rspTmCustTaskRate);
                }
                hhmm.append(':');
                if (1 == rspTmCustTaskRate2.precision()) {
                    hhmm.append('0');
                    hhmm.append(rspTmCustTaskRate2);
                } else {
                    hhmm.append(rspTmCustTaskRate2);
                }
                sMsg.B.no(i).xxDtTm_B1.setValue(hhmm.toString());
            }

            if (hasValue(sMsg.B.no(i).xxDtlAmt_BH)) {

                BigDecimal rspTmAllTaskRate = null;
                BigDecimal rspTmAllTaskRate2 = null;
                rspTmAllTaskRate = sMsg.B.no(i).xxDtlAmt_BH.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmAllTaskRate2 = sMsg.B.no(i).xxDtlAmt_BH.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuffer hhmm2 = new StringBuffer();

                if (1 == rspTmAllTaskRate.precision()) {
                    hhmm2.append('0');
                    hhmm2.append(rspTmAllTaskRate);
                } else {
                    hhmm2.append(rspTmAllTaskRate);
                }
                hhmm2.append(':');
                if (1 == rspTmAllTaskRate2.precision()) {
                    hhmm2.append('0');
                    hhmm2.append(rspTmAllTaskRate2);
                } else {
                    hhmm2.append(rspTmAllTaskRate2);
                }
                sMsg.B.no(i).xxDtTm_B2.setValue(hhmm2.toString());
            }
        }
        return sMsg;
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0380CMsg
     * @param sMsg NSBL0380SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg, int pageFrom) {

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
    }
}
