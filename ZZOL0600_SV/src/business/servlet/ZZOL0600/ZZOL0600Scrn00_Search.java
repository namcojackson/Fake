/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0600.ZZOL0600CMsg;
import business.servlet.ZZOL0600.common.ZZOL0600CommonLogic;
import business.servlet.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0600Scrn00_Search extends S21CommonHandler implements ZZOL0600Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_FS);
        scrnMsg.addCheckItem(scrnMsg.xxMn_FS);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_TS);
        scrnMsg.addCheckItem(scrnMsg.xxMn_TS);
        scrnMsg.addCheckItem(scrnMsg.almsAppVrsnTxt);

        scrnMsg.addCheckItem(scrnMsg.almsBatProcId);
        scrnMsg.addCheckItem(scrnMsg.almsFwkVrsnTxt);
        scrnMsg.addCheckItem(scrnMsg.almsHostNm);
        scrnMsg.addCheckItem(scrnMsg.almsJobId);
        scrnMsg.addCheckItem(scrnMsg.almsJvmNm);
        scrnMsg.addCheckItem(scrnMsg.almsMsgId);
        scrnMsg.addCheckItem(scrnMsg.almsMsgTxt);

        scrnMsg.addCheckItem(scrnMsg.almsOnlBatFlg_F2);
        scrnMsg.addCheckItem(scrnMsg.almsPgmId);
        scrnMsg.addCheckItem(scrnMsg.almsRelVrsnTxt);
        scrnMsg.addCheckItem(scrnMsg.almsSevtyLvlTxt);
        scrnMsg.addCheckItem(scrnMsg.almsSysNm);
        scrnMsg.addCheckItem(scrnMsg.almsUnivsUniqId);
        scrnMsg.addCheckItem(scrnMsg.almsUsrId);
        scrnMsg.addCheckItem(scrnMsg.almsUsrId);
        correlativeTimeCheck(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = new ZZOL0600CMsg();

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        // From Time
        if (!scrnMsg.xxFromDt.isClear()) {
            String frDate = scrnMsg.xxFromDt.getValue();
            String frHour = null;
            String frMin = null;

            if (!scrnMsg.xxHrs_FS.isClear()) {
                frHour = scrnMsg.xxHrs_FS.getValue();
            } else {
                frHour = DEF_HRS_FROM;
            }

            if (!scrnMsg.xxMn_FS.isClear()) {
                frMin = scrnMsg.xxMn_FS.getValue();
            } else {
                frMin = DEF_MIN_FROM;
            }
            bizMsg.ezInTime_FR.setValue(frDate + frHour + frMin + DEF_SEC_MILLI_FROM);
        }

        // To Time
        if (!scrnMsg.xxToDt.isClear()) {
            String frDate = scrnMsg.xxToDt.getValue();
            String frHour = null;
            String frMin = null;

            if (!scrnMsg.xxHrs_TS.isClear()) {
                frHour = scrnMsg.xxHrs_TS.getValue();
            } else {
                frHour = DEF_HRS_TO;
            }

            if (!scrnMsg.xxMn_TS.isClear()) {
                frMin = scrnMsg.xxMn_TS.getValue();
            } else {
                frMin = DEF_MIN_TO;
            }
            bizMsg.ezInTime_TO.setValue(frDate + frHour + frMin + DEF_SEC_MILLI_TO);
        }

        bizMsg.setBusinessID("ZZOL0600");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        S21SortColumnIMGController.clearIMG("ZZOL0600Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZZOL0600CommonLogic.convertTimeToDisplay(scrnMsg, bizMsg);

        // Table Color Control
        S21TableColorController tblColor = new S21TableColorController(ZZOL0600Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.A.setInputProtected(true);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }

    /**
     * correlative check for date time fields.
     * @param ZZLL0010BMsg scrnMsg
     */
    protected void correlativeTimeCheck(ZZOL0600BMsg scrnMsg) {
    	String dateErrMsg_9024 = "ZZZM9024E";
    	String dateErrMsg_9010 = "ZZZM9010E";
        String frDateNm = scrnMsg.xxFromDt.getNameForMessage();
        String toDateNm = scrnMsg.xxToDt.getNameForMessage();
        String frHourNm = scrnMsg.xxHrs_FS.getNameForMessage();
        String frMinNm = scrnMsg.xxMn_FS.getNameForMessage();
        String toHourNm = scrnMsg.xxHrs_TS.getNameForMessage();
        String toMinNm = scrnMsg.xxMn_TS.getNameForMessage();

        // If time fields are filled, the date field should be filled.
        if (scrnMsg.xxFromDt.isClear() && !scrnMsg.xxHrs_FS.isClear()) {
            scrnMsg.xxHrs_FS.setErrorInfo(1, dateErrMsg_9024, new String[] {frDateNm });
        }
        if (scrnMsg.xxFromDt.isClear() && !scrnMsg.xxMn_FS.isClear()) {
            scrnMsg.xxMn_FS.setErrorInfo(1, dateErrMsg_9024, new String[] {frDateNm });
        }
        if (scrnMsg.xxHrs_FS.isClear() && !scrnMsg.xxMn_FS.isClear()) {
            scrnMsg.xxMn_FS.setErrorInfo(1, dateErrMsg_9024, new String[] {frHourNm });
        }
        if (scrnMsg.xxToDt.isClear() && !scrnMsg.xxHrs_TS.isClear()) {
            scrnMsg.xxHrs_TS.setErrorInfo(1, dateErrMsg_9024, new String[] {toDateNm });
        }
        if (scrnMsg.xxToDt.isClear() && !scrnMsg.xxMn_TS.isClear()) {
            scrnMsg.xxMn_TS.setErrorInfo(1, dateErrMsg_9024, new String[] {toDateNm });
        }
        if (scrnMsg.xxHrs_TS.isClear() && !scrnMsg.xxMn_TS.isClear()) {
            scrnMsg.xxMn_TS.setErrorInfo(1, dateErrMsg_9024, new String[] {toHourNm });
        }

        if (!scrnMsg.xxFromDt.isClear() && !scrnMsg.xxToDt.isClear()) {
            int cmpDate = ZYPDateUtil.compare(scrnMsg.xxFromDt.getValue(), scrnMsg.xxToDt.getValue());

            // Bigger the 'From Date' is error
            if (cmpDate > 0) {
                scrnMsg.xxFromDt.setErrorInfo(1, dateErrMsg_9010, new String[] {frDateNm, toDateNm });
                scrnMsg.xxToDt.setErrorInfo(1, dateErrMsg_9010, new String[] {frDateNm, toDateNm });

            } else if (cmpDate == 0) {

                if (!scrnMsg.xxHrs_FS.isClear() && !scrnMsg.xxHrs_TS.isClear()) {
                    int frHrs = Integer.parseInt(scrnMsg.xxHrs_FS.getValue());
                    int toHrs = Integer.parseInt(scrnMsg.xxHrs_TS.getValue());

                    // Bigger the 'From Hour' is error
                    if (frHrs > toHrs) {
                        scrnMsg.xxHrs_FS.setErrorInfo(1, dateErrMsg_9010, new String[] {frHourNm, toHourNm });
                        scrnMsg.xxHrs_TS.setErrorInfo(1,dateErrMsg_9010, new String[] {frHourNm, toHourNm });

                    } else if (frHrs == toHrs) {
                        if (!scrnMsg.xxMn_FS.isClear() && !scrnMsg.xxMn_TS.isClear()) {
                            int frMin = Integer.parseInt(scrnMsg.xxMn_FS.getValue());
                            int toMin = Integer.parseInt(scrnMsg.xxMn_TS.getValue());

                            // Bigger the 'From Minutes' is error
                            if (frMin > toMin) {
                                scrnMsg.xxMn_FS.setErrorInfo(1, dateErrMsg_9010, new String[] {frMinNm, toMinNm });
                                scrnMsg.xxMn_TS.setErrorInfo(1, dateErrMsg_9010, new String[] {frMinNm, toMinNm });
                            }
                        }
                    }
                }
            }
        }

    }

}
