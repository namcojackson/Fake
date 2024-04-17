/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0060;

import static business.blap.NSBL0060.constant.NSBL0060Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/20/2013   Hitachi         T.Aoyagi        Create          N/A
 * 06/28/2017   Hitachi         T.Mizuki        Update          QC#18613
 *</pre>
 */
public class NSBL0060BL02 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSBL0060CMsg cMsg = (NSBL0060CMsg) arg0;
        NSBL0060SMsg sMsg = (NSBL0060SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSBL0060_INIT".equals(screenAplID)) {
                doProcess_NSBL0060_INIT(cMsg, sMsg);
            } else if ("NSBL0060Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0060Scrn00_Search(cMsg, sMsg);
            } else if ("NSBL0060Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0060Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0060Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0060Scrn00_PageNext(cMsg, sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0060_INIT(NSBL0060CMsg cMsg, NSBL0060SMsg sMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        if (hasValue(cMsg.svcMachMstrPk)) {
            setHeaderInfo(cMsg);
            doProcess_NSBL0060Scrn00_Search(cMsg, sMsg);
        }
        // timezone
        setTimeZoneHeader(cMsg);
    }

    private void doProcess_NSBL0060Scrn00_Search(NSBL0060CMsg cMsg, NSBL0060SMsg sMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        String fromDt = addTime(cMsg.xxFromDt.getValue(), -1, Calendar.DATE);
        String toDt = addTime(cMsg.xxToDt.getValue(), 1, Calendar.DATE);
        if (hasValue(toDt) && DT_STORE_PATTERN.length() < toDt.length()) {
            toDt = cMsg.xxToDt.getValue();
        }

        S21SsmEZDResult ssmResult = NSBL0060Query.getInstance().getServiceTaskInfo(cMsg, sMsg, fromDt, toDt);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(ZZZM9001E, null);
        }
        if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
            cMsg.setMessageInfo(ZZZM9002W, null);
        }
        setDetailInfo(sMsg);
        // timezone
        setTimeZoneHeader(cMsg);

        pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0060Scrn00_PagePrev(NSBL0060CMsg cMsg, NSBL0060SMsg sMsg) {
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0060Scrn00_PageNext(NSBL0060CMsg cMsg, NSBL0060SMsg sMsg) {
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void setHeaderInfo(NSBL0060CMsg cMsg) {

        S21SsmEZDResult ssmResult = NSBL0060Query.getInstance().getMachineInfo(cMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

        setAvgMtrUsg(cMsg, salesDate);
        setRspCntAndAvg(cMsg);
        //setValue(cMsg.xxFromDt, addTime(salesDate, -1, Calendar.MONTH));
        //setValue(cMsg.xxToDt, salesDate);
    }

    private void setAvgMtrUsg(NSBL0060CMsg cMsg, String salesDate) {
        // mod start 2017/06/28 CSA QC#18613
        BigDecimal avgMtrCnt = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult = NSBL0060Query.getInstance().getAvgMtrCnt(cMsg);
        if (!ssmResult.isCodeNotFound()) {
            avgMtrCnt = (BigDecimal) ssmResult.getResultObject();
            if (avgMtrCnt == null) {
                avgMtrCnt = BigDecimal.ZERO;
            }
        }
        setValue(cMsg.mtrCnt, avgMtrCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
        // mod end 2017/06/28 CSA QC#18613

    }

    private void setRspCntAndAvg(NSBL0060CMsg cMsg) {

        Map<String, BigDecimal> resultMap = NSBL0060Query.getInstance().getResCntAndAvg(cMsg);
        if (resultMap != null) {
            setValue(cMsg.xxTotCnt, resultMap.get(COL_COUNT));

            BigDecimal avg = resultMap.get(COL_AVG);
            if (avg != null) {
                setValue(cMsg.xxDtTm_HD, convertFormat(avg.intValue()));
            } else {
                setValue(cMsg.xxDtTm_HD, convertFormat(0));
            }
        }
    }

    private void setDetailInfo(NSBL0060SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0060_ASMsg aSMsg = sMsg.A.no(i);
            setCodeName(aSMsg.xxEdtCdNm_ST, aSMsg.svcTaskStsCd, aSMsg.svcTaskStsNm_ST);
            setCodeName(aSMsg.xxEdtCdNm_ST, aSMsg.fsrVisitStsCd, aSMsg.svcTaskStsNm_FV);
            setCodeName(aSMsg.xxEdtCdNm_CA, aSMsg.dsSvcCallTpCd, aSMsg.dsSvcCallTpNm);
            // mod start 2017/06/28 CSA QC#18613
            setCodeName(aSMsg.xxEdtCdNm_SY, aSMsg.svcPblmTpCd, aSMsg.svcPblmTpNm);
            // mod end 2017/06/28 CSA QC#18613
            setCodeName(aSMsg.xxEdtCdNm_PR, aSMsg.svcPblmTpCd, aSMsg.svcPblmTpNm);
            setCodeName(aSMsg.xxEdtCdNm_RE, aSMsg.svcPblmRsnTpCd, aSMsg.svcPblmRsnTpNm);
            setCodeName(aSMsg.xxEdtCdNm_LO, aSMsg.svcPblmLocTpCd, aSMsg.svcPblmLocTpNm);
            setCodeName(aSMsg.xxEdtCdNm_CO, aSMsg.svcPblmCrctTpCd, aSMsg.svcPblmCrctTpNm);
            if (hasValue(aSMsg.techCd_FV)) {
                setCodeName(aSMsg.xxEdtCdNm_TE, aSMsg.techCd_FV, aSMsg.techNm_FV);
            } else {
                setCodeName(aSMsg.xxEdtCdNm_TE, aSMsg.techCd_ST, aSMsg.techNm_ST);
            }
            setCodeName(aSMsg.xxEdtCdNm_BI, aSMsg.svcBillTpCd, aSMsg.svcBillTpNm);

            if (aSMsg.svcRspTmNum != null) {
                setValue(aSMsg.xxDtTm_DL, convertFormat(aSMsg.svcRspTmNum.getValueInt()));
            }
            setValue(aSMsg.xxInpAmtNum_PA, aSMsg.svcPrtDealAmt);

            if (hasValue(aSMsg.svcLborDealAmt_FV)) {
                setValue(aSMsg.xxInpAmtNum_LA, aSMsg.svcLborDealAmt_FV);
            } else {
                setValue(aSMsg.xxInpAmtNum_LA, aSMsg.svcLborDealAmt_ST);
            }
            setValue(aSMsg.xxInpAmtNum_TR, aSMsg.svcTrvlDealAmt);

            // timezone
            setTimeZoneDetail(aSMsg);
        }
    }

    private void setCodeName(EZDSStringItem disp, EZDSStringItem code, EZDSStringItem name) {
        if (hasValue(code)) {
            setValue(disp, joinStrings(COLON, code.getValue(), name.getValue()));
        }
    }

    private void setPagenation(NSBL0060CMsg cMsg, NSBL0060SMsg sMsg, int pagenationFrom) {
        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    private void pagenation(NSBL0060CMsg cMsg, NSBL0060SMsg sMsg, int pageFrom) {
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

    private String addTime(String date, int addTime, int type) {

        if (!hasValue(date)) {
            return null;
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(convertStringToDate(date, DT_STORE_PATTERN));
        cl.add(type, addTime);

        return convertDateToString(cl.getTime());
    }

    private String convertFormat(int minute) {
        
        String strHour = DEFAULT_HOUR;
        String strMinute = DEFAULT_MINUTE;
        
        if (minute == 0) {
            return joinStrings(COLON, strHour, strMinute);
        }
        
        int hour = minute / ONE_HOUR;
        if (hour > 0) {
            if (hour > Integer.parseInt(MAX_HOUR)) {
                return MAX_RSP_TIME;
            } else {
                strHour = ZYPCommonFunc.leftPad(String.valueOf(hour), DEFAULT_HOUR.length(), STR_ZERO);
            }
        }

        int mod = minute % ONE_HOUR;
        strMinute = ZYPCommonFunc.leftPad(String.valueOf(mod), 2, STR_ZERO);

        return joinStrings(COLON, strHour, strMinute);
    }

    private String convertDateToString(Date date) {

        if (date != null) {
            return (new SimpleDateFormat(DT_STORE_PATTERN)).format(date);
        }
        return null;
    }

    private Date convertStringToDate(String date, String formatFrom) {
        try {
            return (new SimpleDateFormat(formatFrom)).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    private String joinStrings(String separator, String... collection) {

        StringBuilder sb = new StringBuilder();
        for (String str : collection) {
            if (hasValue(str)) {
                sb.append(str);
            }
            if (separator != null) {
                sb.append(separator);
            }
        }
        if (sb.length() > 0 && separator != null) {
            return sb.deleteCharAt(sb.length() - separator.length()).toString();
        }
        return sb.toString();
    }

    private void setTimeZoneHeader(NSBL0060CMsg cMsg) {
        if (hasValue(cMsg.xxFromDt)) {
            setValue(cMsg.tmZoneCd, NSXC001001SvcTimeZone.getSysTimeZone(cMsg.xxFromDt));
        } else {
            setValue(cMsg.tmZoneCd, NSXC001001SvcTimeZone.getSysTimeZone(cMsg.xxToDt));
        }
    }

    private void setTimeZoneDetail(NSBL0060_ASMsg asMsg) {
        if (hasValue(asMsg.svcTaskRcvDt)) {
            setValue(asMsg.tmZoneCd_A1, NSXC001001SvcTimeZone.getSysTimeZone(asMsg.svcTaskRcvDt, asMsg.svcTaskRcvTm));
        } else {
            asMsg.tmZoneCd_A1.clear();
        }
        if (hasValue(asMsg.svcTaskCpltDt)) {
            setValue(asMsg.tmZoneCd_A2, NSXC001001SvcTimeZone.getSysTimeZone(asMsg.svcTaskCpltDt, asMsg.svcTaskCpltTm));
        } else {
            asMsg.tmZoneCd_A2.clear();
        }
    }
}
