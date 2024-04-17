package com.canon.cusa.s21.batch.NLC.NLCB046001;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.INTFC_SLS_MTH_MGTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Sales Month Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/06/2010   Fujitsu         S.Yoshida       Create          RQ1318
 * 04/09/2010   Fujitsu         S.Yoshida       Update          RQ1352
 * </pre>
 */
public class NLCB046001 extends S21BatchMain {

    //-- Error Message Code --------------------
    /** Message ID : ZZM9000E The field of [@] is not input. */
    private static final String MSG_ID_ZZM9000E = "ZZM9000E";
    /** Message ID : ZZBM0009I */
    private static final String MSG_ID_ZZBM0009I = "ZZBM0009I";
    /** Message ID : NLCM0053E The process abended. */
    private static final String MSG_ID_NLCM0053E = "NLCM0053E";

    //-- Error Message Parameter --------------------
    /** Message string : Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";
    /** Table ID : STK_IN_RSLT_WRK */
    private static final String TBL_ID_INTFC_SLS_MTH_MGT = "INTFC_SLS_MTH_MGT";

    //-- Other Internal constants --------------
    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;

    //-- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    //-- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount;
    /** The number of cases : Update */
    private int updateCount;
    /** The number of case : Skip */
    private int skipCount;

    //-- Other Internal Variable ---------------
    /** User Profile Service  */
    private S21UserProfileService profileService = null;
    /** Termination code */
    private TERM_CD termCd;
    /** Batch Proc Date */
    private String batProcDate;


    @Override
    protected void initRoutine() {

        // Initialization of variable
        selectCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Check on input parameter
        checkParameter();

    }

    @Override
    protected void mainRoutine() {

        // Get operation date.
        batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        // Create interface date.
        updateIFSlsMth();
    }

    @Override
    protected void termRoutine() {

        String[] tmp = null;

        // The number of cases : Select is output
        tmp = new String[]{TBL_ID_INTFC_SLS_MTH_MGT, "Read", Integer.toString(selectCount)};
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp);

        // The number of cases : Insert is output
        tmp = new String[]{TBL_ID_INTFC_SLS_MTH_MGT, "Update", Integer.toString(updateCount)};
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp);

        // Setting of termination code
        setTermState(termCd, selectCount, skipCount, updateCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[]{MSG_STR_COMP_CODE});
        }

    }

    private void updateIFSlsMth() {

        // Get Interface Sales Month.
        INTFC_SLS_MTH_MGTTMsg iFSlsMthTMsg = new INTFC_SLS_MTH_MGTTMsg();
        iFSlsMthTMsg.glblCmpyCd.setValue(glblCmpyCd);
        iFSlsMthTMsg = (INTFC_SLS_MTH_MGTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(iFSlsMthTMsg);
        if (iFSlsMthTMsg == null) {
            S21InfoLogOutput.println("Interface Sales Month dose not exist.");
            throw new S21AbendException(MSG_ID_NLCM0053E);
        }
        selectCount++;

        // Process Month compare to Interface Sales Month.
        String batProcDateMth = batProcDate.substring(0, 6);
        StringBuffer msg = new StringBuffer();
        msg.append("Process Month:");
        msg.append(batProcDateMth);
        msg.append(" cmpare to ");
        msg.append("Interface Sales Month:");
        msg.append(iFSlsMthTMsg.intfcSlsYrMth.getValue());
        EZDDebugOutput.println(CST_DEBUG_MSG_LVL, msg.toString(), true);
        if (batProcDateMth.compareTo(iFSlsMthTMsg.intfcSlsYrMth.getValue()) < 0) {
            S21InfoLogOutput.println("not updated");
            skipCount++;
            return;
        }

        // Get next Month.
        // * Month value is 0-based. 0 for January.
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, Integer.valueOf(batProcDate.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.valueOf(batProcDate.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(batProcDate.substring(6, 8)));

        // Format date.
        Calendar calDay = (Calendar) cal.clone();
        calDay.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String procExecDt = sdf.format(calDay.getTime());

        // Format date.
        cal.add(Calendar.MONTH, 1);
        sdf = new SimpleDateFormat("yyyyMM");
        String intfcSlsYrMthStr = sdf.format(cal.getTime());

        // Set next Month.
        msg.delete(0, msg.length());
        msg.append("New Interface Sales Month:");
        msg.append(intfcSlsYrMthStr);
        EZDDebugOutput.println(CST_DEBUG_MSG_LVL, msg.toString(), true);
        iFSlsMthTMsg.intfcSlsYrMth.setValue(intfcSlsYrMthStr);
        iFSlsMthTMsg.procExecDt.setValue(procExecDt);

        // Update Interface Sales Month
        EZDTBLAccessor.update(iFSlsMthTMsg);
        if (!"0000".equals(iFSlsMthTMsg.getReturnCode())) {
            S21InfoLogOutput.println("Failed to Interface Sales Month updated.");
            throw new S21AbendException(MSG_ID_NLCM0053E);
        }
        updateCount++;

    }

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameters.
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NLCB046001().executeBatch(NLCB046001.class.getSimpleName());

    }

}
