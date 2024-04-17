/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0600;

import java.util.Iterator;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSystemEnv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0600.ZZOL0600CMsg;
import business.servlet.ZZOL0600.common.ZZOL0600CommonLogic;
import business.servlet.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.batch.S21CommandProcessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0600Scrn00_View extends S21CommonHandler implements ZZOL0600Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = new ZZOL0600CMsg();

        int i = getButtonSelectNumber();

        // Set ezReqBusinessID_B selected ezReqBusinessID_A
        if (scrnMsg.A.no(i).glblCmpyCd_TR.getValue() != null)
            scrnMsg.glblCmpyCd_B.setValue(scrnMsg.A.no(i).glblCmpyCd_TR.getValue());

        if (scrnMsg.A.no(i).almsJobId_TR.getValue() != null)
            scrnMsg.almsJobId_B.setValue(scrnMsg.A.no(i).almsJobId_TR.getValue());

        if (scrnMsg.A.no(i).almsAppVrsnTxt_TR.getValue() != null)
            scrnMsg.almsAppVrsnTxt_B.setValue(scrnMsg.A.no(i).almsAppVrsnTxt_TR.getValue());

        if (scrnMsg.A.no(i).almsBatProcId_TR.getValueInt() > 0)
            //scrnMsg.almsBatProcId_B.setValue(new Integer(scrnMsg.A.no(i).almsBatProcId_TR.getValueInt()));
        	scrnMsg.almsBatProcId_B.setValue(Integer.valueOf(scrnMsg.A.no(i).almsBatProcId_TR.getValueInt()));

        if (scrnMsg.A.no(i).xxAlmsOnlBatFlgTxt_TR.getValue() != null)
            scrnMsg.xxAlmsOnlBatFlgTxt_B.setValue(scrnMsg.A.no(i).xxAlmsOnlBatFlgTxt_TR.getValue());

        if (scrnMsg.A.no(i).almsMsgId_TR.getValue() != null)
            scrnMsg.almsMsgId_B.setValue(scrnMsg.A.no(i).almsMsgId_TR.getValue());

        if (scrnMsg.A.no(i).almsUsrId_TR.getValue() != null)
            scrnMsg.almsUsrId_B.setValue(scrnMsg.A.no(i).almsUsrId_TR.getValue());

        if (scrnMsg.A.no(i).almsUnivsUniqId_TR.getValue() != null)
            scrnMsg.almsUnivsUniqId_B.setValue(scrnMsg.A.no(i).almsUnivsUniqId_TR.getValue());

        if (scrnMsg.A.no(i).almsPgmId_TR.getValue() != null)
            scrnMsg.almsPgmId_B.setValue(scrnMsg.A.no(i).almsPgmId_TR.getValue());

        if (scrnMsg.A.no(i).almsJvmNm_TR.getValue() != null)
            scrnMsg.almsJvmNm_B.setValue(scrnMsg.A.no(i).almsJvmNm_TR.getValue());

        if (scrnMsg.A.no(i).almsRelVrsnTxt_TR.getValue() != null)
            scrnMsg.almsRelVrsnTxt_B.setValue(scrnMsg.A.no(i).almsRelVrsnTxt_TR.getValue());
        
        if (scrnMsg.A.no(i).almsDbVrsnTxt_TR.getValue() != null)
            scrnMsg.almsDbVrsnTxt_B.setValue(scrnMsg.A.no(i).almsDbVrsnTxt_TR.getValue());

        if (scrnMsg.A.no(i).almsSysNm_TR.getValue() != null)
            scrnMsg.almsSysNm_B.setValue(scrnMsg.A.no(i).almsSysNm_TR.getValue());

        if (scrnMsg.A.no(i).almsHostNm_TR.getValue() != null)
            scrnMsg.almsHostNm_B.setValue(scrnMsg.A.no(i).almsHostNm_TR.getValue());

        if (scrnMsg.A.no(i).almsMsgTxt_TR.getValue() != null)
            scrnMsg.almsMsgTxt_B.setValue(scrnMsg.A.no(i).almsMsgTxt_TR.getValue());

        if (scrnMsg.A.no(i).almsFwkVrsnTxt_TR.getValue() != null)
            scrnMsg.almsFwkVrsnTxt_B.setValue(scrnMsg.A.no(i).almsFwkVrsnTxt_TR.getValue());

        if (scrnMsg.A.no(i).almsSevtyLvlTxt_TR.getValue() != null)
            scrnMsg.almsSevtyLvlTxt_B.setValue(scrnMsg.A.no(i).almsSevtyLvlTxt_TR.getValue());

        if (scrnMsg.A.no(i).ezInTimeZone_TR.getValue() != null)
            scrnMsg.ezInTimeZone_B.setValue(scrnMsg.A.no(i).ezInTimeZone_TR.getValue());

        if (scrnMsg.A.no(i).xxDtTm_FR.getValue() != null)
            scrnMsg.xxDtTm_B.setValue(scrnMsg.A.no(i).xxDtTm_FR.getValue());

        bizMsg.setBusinessID("ZZOL0600");
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        ZZOL0600CommonLogic.initCommonButtonForScrn01(this);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if (ZZOL0600Constant.BATCH_FLG.equals(scrnMsg.xxAlmsOnlBatFlgTxt_B.getValue())) {
            boolean joblog_Flg = checkJobLog(scrnMsg);
            S21InfoLogOutput.println("BATCH JOB LOG FLAG :" + joblog_Flg);
//            if (!joblog_Flg) {
//                // disable the download job log button.
//                setButtonProperties(BTN11[0], BTN11[1], BTN11[2], 0, null);
//                scrnMsg.setMessageInfo("ZZOM0014I");
//            } else // enable the download joblog button
//            {
//                setButtonProperties(BTN11[0], BTN11[1], BTN11[2], 1, null);
//            }
            if (joblog_Flg) {
            	// enable the download joblog button
            	setButtonProperties(BTN11[0], BTN11[1], BTN11[2], 1, null);
            } else
            {
                // disable the download job log button.
                setButtonProperties(BTN11[0], BTN11[1], BTN11[2], 0, null);
                scrnMsg.setMessageInfo("ZZOM0014I");
            }
        } else // disable for online
        {
            setButtonProperties(BTN11[0], BTN11[1], BTN11[2], 0, null);
        }

    }

    /**
     * Check job log.
     * @param scrnMsg the scrn msg
     * @return true, if successful
     */
    public boolean checkJobLog(ZZOL0600BMsg scrnMsg) {

        S21CommandProcessor cp = new S21CommandProcessor();

        // BAT_SV have to be taken from the
        // propertyfile(EZDSystem.properties)as the
        // property(S21.requestbatch.batchserver.logon).
        String BAT_SV = EZDSystemEnv.getString("S21.requestbatch.batchserver.logon");

        // BAT_PROC_JOB_LOG_PATH_NM have to be taken from
        // table(BAT_PROG_LOG)
        String BAT_PROC_JOB_LOG_PATH_NM = ZZOL0600CommonLogic.getJobLogPath(scrnMsg);

        // SHELL_FILE is fixed value.
        String SHELL_FILE = "./dvlp/02shell/ZZZ/checkJobLog.ksh";

        // EXEC_CMD is created by 3 variables as following.
        String EXEC_CMD = "/usr/bin/ksh " + SHELL_FILE + " " + BAT_PROC_JOB_LOG_PATH_NM;
        List<String> result1 = null;
        Iterator<String> itl1 = null;
        S21InfoLogOutput.println("BAT_SV : " + BAT_SV);
        S21InfoLogOutput.println("EXEC_CMD : " + EXEC_CMD);

        result1 = cp.exeCommand("/usr/bin/ssh", BAT_SV, EXEC_CMD);

        itl1 = result1.iterator();
        // Checking existence of file
        String LOG_FND_MSG = "Joblog file is found";
        while (itl1.hasNext()) {
            String str = itl1.next();
            S21InfoLogOutput.println(str);
            if (str.equals(LOG_FND_MSG)) {
                return true;
            }
        }
        return false;

    }
}
