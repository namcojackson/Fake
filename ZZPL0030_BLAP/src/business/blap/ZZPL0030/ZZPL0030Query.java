/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0030;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.ZZPL0030.constant.ZZPL0030Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/23   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public final class ZZPL0030Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZPL0030Query MYINSTANCE = new ZZPL0030Query();

    /**
     * Constructor.
     */
    private ZZPL0030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZPL0030Query
     */
    public static ZZPL0030Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * Call search function "getReportList" defined in [ZZPL0030Query.xml]
     * 
     * <pre>
     * get accesible report list for each user created by EIP printing platform
     * </pre>
     * 
     * @param cMsg ZZPL0030CMsg
     * @param sMsg ZZPL0030SMsg
     * @param authorizedRptList authorized report list for each user 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReportList(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg, List authorizedRptList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("authorizedRptList", authorizedRptList);

        return getSsmEZDClient().queryEZDMsgArray("getReportList", ssmParam, sMsg.A);

    }

    /**
     * Call search function "getAdvSrchPulldownList" defined in [ZZPL0030Query.xml]
     * 
     * <pre>
     * get associated parameter list based on reportID
     * </pre>
     * 
     * @param cMsg ZZPL0030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdvSrchPulldownList(ZZPL0030CMsg cMsg) {

        return getSsmEZDClient().queryObjectList("getAdvSrchPulldownList", cMsg, -1, -1);

    }

    /**
     * Call search function "getReportHistList" defined in [ZZPL0030Query.xml]
     * 
     * <pre>
     * get report history list created by EIP printing platform
     * </pre>
     * 
     * @param cMsg ZZPL0030CMsg
     * @param sMsg ZZPL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReportHistList(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.B.length() + 1);
        ssmParam.put("cMsg", cMsg);

        // convert data type of creation date for SQL
        String srchKeyRptCratDtFrom = null;
        String srchKeyRptCratDtTo = null;

        if (cMsg.xxCratDt_FR.getValue() != null && !cMsg.xxCratDt_FR.getValue().equals("")) {
            srchKeyRptCratDtFrom = convertDateDataType(cMsg.xxCratDt_FR.getValue(), false);
        }
        ssmParam.put("srchKey_rptCratDt_FR", srchKeyRptCratDtFrom);

        if (cMsg.xxCratDt_TO.getValue() != null && !cMsg.xxCratDt_TO.getValue().equals("")) {
            srchKeyRptCratDtTo = convertDateDataType(cMsg.xxCratDt_TO.getValue(), true);
        }
        ssmParam.put("srchKey_rptCratDt_TO", srchKeyRptCratDtTo);

        return getSsmEZDClient().queryEZDMsgArray("getReportHistList", ssmParam, sMsg.B);

    }

    /**
     * convert input date format to search date format
     * @param inputDate input date
     * @param isDateTo 
     * @return search date
     */
    private String convertDateDataType(String inputDate, boolean isDateTo) {

        SimpleDateFormat inputFormat = new SimpleDateFormat(ZZPL0030Constant.DATE_FORMAT);
        SimpleDateFormat searchFormat = new SimpleDateFormat(ZZPL0030Constant.SYSTEM_DATE_FORMAT);
        String srchDate = null;

        try {
            Date date = inputFormat.parse(inputDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (isDateTo) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            srchDate = searchFormat.format(calendar.getTime()).toString();
        } catch (java.text.ParseException e) {
            throw new S21AbendException("ZZPM0045E", new String[] {e.getMessage()});
        }

        return srchDate;
    }
}
