/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0030;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZPL0030.constant.ZZPL0030Constant;
import business.db.ARC_VIEW_ACCS_LOGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/24   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (ZZPL0030Constant.EVENT_OPEN_RPT_TTL_ROW.equals(screenAplID)) {
                doProcess_ZZPL0030_OpenRptTtl((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZPL0030_OpenRptTtl(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        insertAccessLog(cMsg);
    }

    /**
     * Insert Report Access Log to table(ARC_VIEW_ACCS_LOG)
     * @param cMsg ZZPL0030CMsg
     */
    private void insertAccessLog(ZZPL0030CMsg cMsg) {

        // accesslog data
        String userId = cMsg.getUserID();

        SimpleDateFormat accessTmFmt = new SimpleDateFormat(ZZPL0030Constant.SYSTEM_DATE_FORMAT);
        String reportAccessTime = ZYPDateUtil.getCurrentSystemTime(accessTmFmt.toPattern());

        String reportId = cMsg.xxRptNm_1.getValue().toString();

        String reportTitle = cMsg.B.no(cMsg.xxCellIdx.getValueInt()).xxRptTtlNm.getValue().toString();

        String reportCreatedTime = cMsg.B.no(cMsg.xxCellIdx.getValueInt()).xxRptCratTm.getValue().toString();
        SimpleDateFormat displayFmt = new SimpleDateFormat(ZZPL0030Constant.DATE_TIME_FORMAT);
        Date dbFmtDate;
        try {
            dbFmtDate = displayFmt.parse(reportCreatedTime);
            reportCreatedTime = accessTmFmt.format(dbFmtDate);
        } catch (ParseException e) {
            throw new S21AbendException("ZZPM0051E", new String[] {e.getMessage() });
        }

        // insert accesslog data to DB
        ARC_VIEW_ACCS_LOGTMsg conditionTMsg = new ARC_VIEW_ACCS_LOGTMsg();
        conditionTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        BigDecimal nextSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZZPL0030Constant.SEQUENCE_FOR_ACCESS_LOG);
        conditionTMsg.arcViewAccsLogPk.setValue(nextSeqNum);
        ARC_VIEW_ACCS_LOGTMsg resultTMsg = (ARC_VIEW_ACCS_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(conditionTMsg);

        if (resultTMsg == null) {
            conditionTMsg.usrId.setValue(userId);
            conditionTMsg.rptAccsTs.setValue(reportAccessTime);
            conditionTMsg.rptId.setValue(reportId);
            conditionTMsg.rptTtlNm.setValue(reportTitle);
            conditionTMsg.rptCratTs.setValue(reportCreatedTime);

            EZDTBLAccessor.create(conditionTMsg);
            String sReturnCode = conditionTMsg.getReturnCode();
            if (!sReturnCode.equals(RTNCD_NORMAL)) {
                cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode });
            }
        } else {
            // DATA DUPLICATE
            cMsg.setMessageInfo("ZZZM9012E", new String[] {RTNCD_DUPLICATE });
            return;
        }
    }

}
