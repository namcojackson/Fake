/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import business.db.SVC_FOLL_UP_STARTTMsg;
import business.db.SVC_PBLM_RSN_FOLL_UPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * Get Response Time
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/28/2016   Hitachi         T.Iwamoto       Create          QC#2696
 * 06/07/2016   Hitachi         T.Iwamoto       Update          QC#9218
 * 03/30/2018   Hitachi         T.Tomita        Update          QC#16339
 * 2022/04/11   Hitachi         K.Kitachi       Update          QC#59899
 * 2023/06/14   Hitachi         K.Kitachi       Update          QC#61365
 *</pre>
 */
public class NSXC001001GetFollUpInfo {

    /** VAR_CHAR_KEY : SVC_PBLM_RSN_TP */
    private static final String SVC_PBLM_RSN_TP = "SVC_PBLM_RSN_TP";

    /** Time Stamp Format */
    private static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** LEN_8 */
    private static final int LEN_8 = 8;

    /** LEN_14 */
    private static final int LEN_14 = 14;

    /** 60000 */
    private static final int MIN_60000 = 60000;

    /** Failed to search a FSR Visit Task record. */
    private static final String NSZM0423E = "NSZM0423E";

    /**
     * The entered 'Service Following Upper Start Code' does not
     * exist.
     */
    private static final String NSZM0621E = "NSZM0621E";

    /**
     * getFollUpInfo
     * @param bean NSXC001001GetFollUpInfoBean
     * @return true/false
     */
    public static boolean getFollUpInfo(NSXC001001GetFollUpInfoBean bean) {

        SVC_PBLM_RSN_FOLL_UPTMsg svcPblmRsnFollUpTMsg = getSvcPblmRsnFollUp(bean.getGlblCmpyCd(), bean.getSvcPblmCrctTpCd());
        if (svcPblmRsnFollUpTMsg == null) {
            bean.setXxMsgId(NSZM0423E);
            return false;
        }

        String erlStartTs = null;
        String lateStartTs = null;
        if (ZYPConstant.FLG_ON_Y.equals(svcPblmRsnFollUpTMsg.startStdApplyFlg.getValue())) {

            erlStartTs = getFollUpErlDtTmConsiderShift(svcPblmRsnFollUpTMsg.erlFollUpStartCd.getValue(), bean);
            lateStartTs = getFollUpLateDtTmConsiderShift(erlStartTs, svcPblmRsnFollUpTMsg.lateFollUpStartCd.getValue(), bean);

        } else {

            erlStartTs = getFollUpDtTm(svcPblmRsnFollUpTMsg.erlFollUpStartCd.getValue(), bean);
            lateStartTs = getFollUpDtTm(svcPblmRsnFollUpTMsg.lateFollUpStartCd.getValue(), bean);

        }

        if (hasValue(bean.getXxMsgId())) {
            return false;
        }

        // set return bean
        bean.setReqTechFlg(svcPblmRsnFollUpTMsg.reqTechFlg.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(svcPblmRsnFollUpTMsg.reqTechFlg.getValue())) {
            bean.setSvcAsgTpCd(SVC_ASG_TP.REQUIRED);
        }
        bean.setFsrVisitStsCd(svcPblmRsnFollUpTMsg.svcTaskStsCd.getValue());
        if (!ZYPConstant.FLG_ON_Y.equals(svcPblmRsnFollUpTMsg.reqPrtFlg.getValue())) {
            // Mod Start 2018/03/30 QC#16339
            bean.setErlStartTs(addOffsetDays(erlStartTs, svcPblmRsnFollUpTMsg));
            bean.setLateStartTs(addOffsetDays(lateStartTs, svcPblmRsnFollUpTMsg));
            // Mod End 2018/03/30 QC#16339
        }
        bean.setReqPrtFlg(svcPblmRsnFollUpTMsg.reqPrtFlg.getValue());
        return true;
    }

    // mod 2016/06/07 CSA Defect#9218
    private static SVC_PBLM_RSN_FOLL_UPTMsg getSvcPblmRsnFollUp(String glblCmpyCd, String svcPblmCrctTpCd) {
        if (!ZYPCommonFunc.hasValue(svcPblmCrctTpCd)) {
            svcPblmCrctTpCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_PBLM_RSN_TP, glblCmpyCd);
        }
        SVC_PBLM_RSN_FOLL_UPTMsg tMsg = new SVC_PBLM_RSN_FOLL_UPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcPblmCrctTpCd, svcPblmCrctTpCd);
        return (SVC_PBLM_RSN_FOLL_UPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private static String getFollUpErlDtTmConsiderShift(String follUpStartCd, NSXC001001GetFollUpInfoBean bean) {

        String erlStartTs = getFollUpDtTm(follUpStartCd, bean);
        if (!hasValue(erlStartTs)) {
            return null;
        }

        // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//        erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(bean.getGlblCmpyCd(), BigDecimal.ZERO, bean.getSvcMachMstrPk(), erlStartTs.substring(0, LEN_8), erlStartTs.substring(LEN_8, LEN_14), true);
        erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(bean.getGlblCmpyCd(), BigDecimal.ZERO, bean.getSvcMachMstrPk(), erlStartTs.substring(0, LEN_8), erlStartTs.substring(LEN_8, LEN_14), true, null, bean.getShipToUpdCustCd());
        // END 2022/04/11 K.Kitachi [QC#59899, MOD]

        return erlStartTs;
    }

    private static String getFollUpLateDtTmConsiderShift(String erlStartTs, String follUpStartCd, NSXC001001GetFollUpInfoBean bean) {
        if (!hasValue(erlStartTs)) {
            return null;
        }

        SVC_FOLL_UP_STARTTMsg svcFollUpStartTMsg = getSvcFollUpStart(bean.getGlblCmpyCd(), follUpStartCd);
        if (svcFollUpStartTMsg == null) {

            bean.setXxMsgId(NSZM0621E);
            return null;
        }

        BigDecimal rspTmMnAot = getFollUpRspTmMnAot(follUpStartCd, bean, svcFollUpStartTMsg);

        // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//        String lateStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(bean.getGlblCmpyCd(), rspTmMnAot, bean.getSvcMachMstrPk(), erlStartTs.substring(0, LEN_8), erlStartTs.substring(LEN_8, LEN_14), true);
        String lateStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(bean.getGlblCmpyCd(), rspTmMnAot, bean.getSvcMachMstrPk(), erlStartTs.substring(0, LEN_8), erlStartTs.substring(LEN_8, LEN_14), true, null, bean.getShipToUpdCustCd());
        // END 2022/04/11 K.Kitachi [QC#59899, MOD]

        return lateStartTs;
    }

    private static SVC_FOLL_UP_STARTTMsg getSvcFollUpStart(String glblCmpyCd, String svcFollUpStartCd) {
        SVC_FOLL_UP_STARTTMsg svcFollUpStartPrmTMsg = new SVC_FOLL_UP_STARTTMsg();
        ZYPEZDItemValueSetter.setValue(svcFollUpStartPrmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcFollUpStartPrmTMsg.svcFollUpStartCd, svcFollUpStartCd);
        return (SVC_FOLL_UP_STARTTMsg) S21ApiTBLAccessor.findByKey(svcFollUpStartPrmTMsg);
    }

    private static String getFollUpDtTm(String follUpStartCd, NSXC001001GetFollUpInfoBean bean) {

        SVC_FOLL_UP_STARTTMsg svcFollUpStartTMsg = getSvcFollUpStart(bean.getGlblCmpyCd(), follUpStartCd);
        if (svcFollUpStartTMsg == null) {

            bean.setXxMsgId(NSZM0621E);
            return null;
        }

        String startTs = null;

        if (ZYPConstant.FLG_ON_Y.equals(svcFollUpStartTMsg.sysDtFlg.getValue())) {

            BigDecimal rspTmMnAot = getFollUpRspTmMnAot(follUpStartCd, bean, svcFollUpStartTMsg);
            startTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
            startTs = addTime(startTs, rspTmMnAot);
        }

        return startTs;
    }

    private static BigDecimal getFollUpRspTmMnAot(String follUpStartCd, NSXC001001GetFollUpInfoBean bean, SVC_FOLL_UP_STARTTMsg svcFollUpStartTMsg) {

        BigDecimal rspTmMnAot = BigDecimal.ZERO;

        if (ZYPConstant.FLG_ON_Y.equals(svcFollUpStartTMsg.sysDtFlg.getValue())) {

            if (ZYPConstant.FLG_ON_Y.equals(svcFollUpStartTMsg.svcRspTmMnAotFlg.getValue())) {

                rspTmMnAot = NSXC001001GetRspTime.getRspTmMnAot(bean.getGlblCmpyCd(), bean.getSvcMachMstrPk(), bean.getSvcTaskRcvDt(), bean.getMachDownFlg(), bean.getMdlNm());

            } else if (ZYPCommonFunc.hasValue(bean.getMdlNm())) {

                rspTmMnAot = svcFollUpStartTMsg.addlTmNum.getValue();
            }
        }

        return rspTmMnAot;
    }

    private static String addTime(String startTs, BigDecimal rspTmMnAot) {

        Long tempRspTm = rspTmMnAot.longValue() * MIN_60000;
        Date inParamDate = toDate(startTs, TIME_STAMP_FORMAT);
        if (inParamDate == null) {
            return null;
        }

        Calendar tmpCalendar = Calendar.getInstance();
        tmpCalendar.setTime(inParamDate);
        tmpCalendar.add(Calendar.MILLISECOND, tempRspTm.intValue());

        return getDtFmtString(tmpCalendar.getTime(), TIME_STAMP_FORMAT);
    }

    private static String getDtFmtString(Date date, String dateFmt) {

        SimpleDateFormat format = new SimpleDateFormat(dateFmt);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return format.format(cal.getTime());
    }

    private static Date toDate(String fromTs, String formatFrom) {

        if (!hasValue(fromTs)) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat(formatFrom);
        parser.setLenient(false);

        try {
            return parser.parse(fromTs);
        } catch (ParseException e) {
            return null;
        }
    }

    // Mod Start 2018/03/30 QC#16339
    private static String addOffsetDays(String paramTs, SVC_PBLM_RSN_FOLL_UPTMsg svcPblmRsnFollUpTMsg) {
        if (!hasValue(paramTs) || paramTs.length() < 17  || svcPblmRsnFollUpTMsg == null) {
            return paramTs;
        }
        String rtnDt = paramTs.substring(0, 8);
        String rtnTm = paramTs.substring(8);
        // START 2023/06/14 K.Kitachi [QC#61365, MOD]
//        rtnDt = ZYPDateUtil.addBusinessDay(svcPblmRsnFollUpTMsg.glblCmpyCd.getValue(), rtnDt, svcPblmRsnFollUpTMsg.ofsDaysAot.getValueInt());
        rtnDt = ZYPDateUtil.addBusinessDay(CAL_TP.SVC_CALENDAR, rtnDt, svcPblmRsnFollUpTMsg.ofsDaysAot.getValueInt());
        // END 2023/06/14 K.Kitachi [QC#61365, MOD]
        return rtnDt + rtnTm;
    }
    // Mod End 2018/03/30 QC#16339
}
