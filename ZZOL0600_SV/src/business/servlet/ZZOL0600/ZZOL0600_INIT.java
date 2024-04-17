/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/08/16   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0600.ZZOL0600CMsg;
import business.servlet.ZZOL0600.common.ZZOL0600CommonLogic;
import business.servlet.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0600_INIT extends S21CommonHandler implements ZZOL0600Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = new ZZOL0600CMsg();

        // Set up initial buttons
        scrnMsg.setFocusItem(scrnMsg.A.no(0).glblCmpyCd_TR);

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        ZZOL0600CommonLogic.initPullDown00(scrnMsg);

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

        return bizMsg;

    }

    /*
     * (non-Javadoc)
     * @see com.canon.cusa.s21.framework.online.servlet.S21CommonHandler#doProcess(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg, parts.common.EZDCMsg)
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;

        S21SortColumnIMGController.clearIMG("ZZOL0600Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(ZZOL0600Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        ZZOL0600CommonLogic.initCommonButton(this);
        ZZOL0600CommonLogic.convertTimeToDisplay(scrnMsg, bizMsg);

    }

    /**
     * Sets the name for message.
     * @param bMsg the new name for message
     */
    protected void setNameForMessage(EZDBMsg bMsg) {
        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;

        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.almsAppVrsnTxt.setNameForMessage("Application Version");
        scrnMsg.almsBatProcId.setNameForMessage("Batch Process ID");
        scrnMsg.almsFwkVrsnTxt.setNameForMessage("Framework Version");
        scrnMsg.almsHostNm.setNameForMessage("Host Name");
        scrnMsg.almsJobId.setNameForMessage("Job ID");
        scrnMsg.almsJvmNm.setNameForMessage("JVM");
        scrnMsg.almsMsgId.setNameForMessage("Message ID");
        scrnMsg.almsOnlBatFlg_F2.setNameForMessage("Online/Batch Flag");
        scrnMsg.almsPgmId.setNameForMessage("Program ID");
        scrnMsg.almsRelVrsnTxt.setNameForMessage("Release Version");
        scrnMsg.almsSevtyLvlTxt.setNameForMessage("Security Level");
        scrnMsg.almsSysNm.setNameForMessage("System");
        scrnMsg.almsUnivsUniqId.setNameForMessage("Universal Unique ID");
        scrnMsg.almsUsrId.setNameForMessage("User ID");
        scrnMsg.xxFromDt.setNameForMessage("From Date");
        scrnMsg.xxHrs_FS.setNameForMessage("From Hour");
        scrnMsg.xxMn_FS.setNameForMessage("From Minutes");
        scrnMsg.xxToDt.setNameForMessage("To Date");
        scrnMsg.xxHrs_TS.setNameForMessage("To Hour");
        scrnMsg.xxMn_TS.setNameForMessage("To Minutes");
        scrnMsg.almsMsgTxt.setNameForMessage("Abend Message");

    }

}
