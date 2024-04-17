/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0110;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import business.blap.ZZPL0110.constant.ZZPL0110Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/13   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public final class ZZPL0110Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZPL0110Query MYINSTANCE = new ZZPL0110Query();

    /**
     * Constructor.
     */
    private ZZPL0110Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZPL0110Query
     */
    public static ZZPL0110Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * Call search function "getProcLogList" defined in
     * [ZZPL0110Query.xml]
     * 
     * <pre>
     * get EIP printing process log list
     * </pre>
     * 
     * @param cMsg ZZPL0110CMsg
     * @param sMsg ZZPL0110SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProcLogList(ZZPL0110CMsg cMsg, ZZPL0110SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("rowNum", sMsg.A.length() + 1);

        if (cMsg.glblCmpyCd.getValue() != null && !cMsg.glblCmpyCd.getValue().equals("")) {
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        }

        if (cMsg.eipRptProcLogPk.getValue() != null && !cMsg.eipRptProcLogPk.getValue().equals("")) {
            ssmParam.put("eipRptProcLogPk", cMsg.eipRptProcLogPk.getValue());
        }

        if (cMsg.subSysCd.getValue() != null && !cMsg.subSysCd.getValue().equals("")) {
            ssmParam.put("subSysCd", cMsg.subSysCd.getValue());
        }

        if (cMsg.rptJobId.getValue() != null && !cMsg.rptJobId.getValue().equals("")) {
            ssmParam.put("rptJobId", cMsg.rptJobId.getValue());
        }

        if (cMsg.rptJobStsTxt.getValue() != null && !cMsg.rptJobStsTxt.getValue().equals("") && !cMsg.rptJobStsTxt.getValue().equals("ALL")) {
            ssmParam.put("rptJobStsTxt", cMsg.rptJobStsTxt.getValue());
        }

        if (cMsg.xxFromDt.getValue() != null && !cMsg.xxFromDt.getValue().equals("")) {
            String startDate = convertDateDataType(cMsg.xxFromDt.getValue(), false);
            ssmParam.put("xxFromDt", startDate);
        }

        if (cMsg.xxToDt.getValue() != null && !cMsg.xxToDt.getValue().equals("")) {
            String endDate = convertDateDataType(cMsg.xxToDt.getValue(), true);
            ssmParam.put("xxToDt", endDate);
        }

        return getSsmEZDClient().queryEZDMsgArray("getProcLogList", ssmParam, sMsg.A);

    }

    /**
     * convert input date format to search date format
     * @param inputDate input date
     * @param isToDate
     * @return search date
     */
    private String convertDateDataType(String inputDate, boolean isToDate) {

        SimpleDateFormat inputFormat = new SimpleDateFormat(ZZPL0110Constant.DATE_FORMAT);
        SimpleDateFormat searchFormat = new SimpleDateFormat(ZZPL0110Constant.SYSTEM_DATE_FORMAT);
        String srchDate = null;

        try {
            Date date = inputFormat.parse(inputDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (isToDate) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            srchDate = searchFormat.format(calendar.getTime()).toString();
        } catch (java.text.ParseException e) {
            throw new S21AbendException("ZZXM0022E", new String[] {ZYPDateUtil.TYPE_YYYYMMDD, inputDate });
        }

        return srchDate;
    }
}
