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

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.batch.S21CommandProcessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZZOL0600Scrn01_DownloadJobLog extends S21CommonHandler implements ZZOL0600Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = new ZZOL0600CMsg();
        bizMsg.setBusinessID("ZZOL0600");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        String TEMP_PATH_NM = EZDSystemEnv.getString("up_down_file_dir");
        StringBuffer temppath = new StringBuffer(TEMP_PATH_NM);
        temppath.append("/");
        temppath.append("download");
        S21InfoLogOutput.println("temppath before filename is :" + temppath.toString());
        String fileName = downloadJobLog(scrnMsg, temppath.toString());
        StringBuffer fileNameBuf = new StringBuffer(fileName);
        fileNameBuf.append(".zip");

        temppath.append("/");
        temppath.append(fileNameBuf.toString());
        S21InfoLogOutput.println("temppath final..after filename added :" + fileNameBuf.toString());

        executeDownload(temppath.toString(), true, fileNameBuf.toString());

    }

    /**
     * Download job log.
     * @param scrnMsg the scrn msg
     * @param TEMP_PATH_NM the tEM p_ pat h_ nm
     * @return the string
     */
    public String downloadJobLog(ZZOL0600BMsg scrnMsg, String TEMP_PATH_NM) {
        S21CommandProcessor cp = new S21CommandProcessor();

        List<String> result2 = null;
        Iterator<String> itl2 = null;
        String fileExtn = ".zip";

        // (1)Achive JobLog file

        // BAT_SV have to be taken from the
        // propertyfile(EZDSystem.properties)as the
        // property(S21.requestbatch.batchserver.logon).
        String BAT_SV = EZDSystemEnv.getString("S21.requestbatch.batchserver.logon");

        // BAT_PROC_JOB_LOG_PATH_NM have to be taken from
        // table(BAT_PROG_LOG)
        String BAT_PROC_JOB_LOG_PATH_NM = ZZOL0600CommonLogic.getJobLogPath(scrnMsg);

        // SHELL_FILE is fixed value.
        String SHELL_FILE = "./dvlp/02shell/ZZZ/archiveJobLog.ksh";

        // TEMP_FILE_NM have to be taken from API of EZDeveloper's
        // Downloading.
        String TEMP_FILE_NM = getFileName(TEMP_PATH_NM);
        // EXEC_CMD is created by 4 variables as following.
        String EXEC_ZIP_CMD = "/usr/bin/ksh " + SHELL_FILE + " " + BAT_PROC_JOB_LOG_PATH_NM + " " + TEMP_FILE_NM;

        S21InfoLogOutput.println("-----------------------------");
        S21InfoLogOutput.println("(1)Archive JobLog File");
        S21InfoLogOutput.println("BAT_SV : " + BAT_SV);
        S21InfoLogOutput.println("EXEC_ZIP_CMD : " + EXEC_ZIP_CMD);
        result2 = cp.exeCommand("/usr/bin/ssh", BAT_SV, EXEC_ZIP_CMD);
        itl2 = result2.iterator();
        // this is log
        while (itl2.hasNext()) {
            String str = itl2.next();
            S21InfoLogOutput.println(str);
        }

        // (2)Download archived JobLog file
        List<String> result3 = null;
        Iterator<String> itl3 = null;

        // Create original file path and name.
        String COPY_FROM = BAT_SV + ":" + "./" + TEMP_FILE_NM + fileExtn;

        // TEMP_PATH_NM have to be taken from API of EZDeveloper's
        // Downloading.
        String COPY_TO = TEMP_PATH_NM + "/" + TEMP_FILE_NM + fileExtn;

        S21InfoLogOutput.println("-----------------------------");
        S21InfoLogOutput.println("(2)Download archived JobLog file");
        S21InfoLogOutput.println("COPY_FROM : " + COPY_FROM);
        S21InfoLogOutput.println("COPY_TO : " + COPY_TO);
        result3 = cp.exeCommand("/usr/bin/scp", "-C", COPY_FROM, COPY_TO);
        itl3 = result3.iterator();
        // this is log
        while (itl3.hasNext()) {
            String str = itl3.next();
            S21InfoLogOutput.println(str);
        }

        // (3)Remove achived joblog on the batch server
        List<String> result4 = null;
        Iterator<String> itl4 = null;

        String EXEC_RM_CMD = "rm -f ./" + TEMP_FILE_NM + fileExtn;

        S21InfoLogOutput.println("-----------------------------");
        S21InfoLogOutput.println("(3)Remove achived joblog on the batch server");
        S21InfoLogOutput.println("BAT_SV : " + BAT_SV);
        S21InfoLogOutput.println("EXEC_RM_CMD  CHANGED: " + EXEC_RM_CMD);
        result4 = cp.exeCommand("/usr/bin/ssh", BAT_SV, EXEC_RM_CMD);
        itl4 = result4.iterator();
        // this is log
        while (itl4.hasNext()) {
            String str = itl4.next();
            S21InfoLogOutput.println(str);
        }
        return TEMP_FILE_NM;
    }

    /**
     * Gets the file name.
     * @param filePath the file path
     * @return the file name
     */
    public String getFileName(String filePath) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo user = profileService.getContextUserInfo();
        String userId = user.getUserId();
        String TEMP_FILE_NM = ZYPFileNameUtil.createUniqueFileNm(filePath, userId);
        S21InfoLogOutput.println("TEMP FILE NAME :" + TEMP_FILE_NM);
        return TEMP_FILE_NM;
    }

}
