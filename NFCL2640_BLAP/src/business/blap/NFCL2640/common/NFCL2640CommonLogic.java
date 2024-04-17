/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2640.common;

import static business.blap.NFCL2640.constant.NFCL2640Constant.CONST_AR_SUB_SYS_ID;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0501E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0798E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0803E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.ONL_BAT_TP_CD;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL2640.NFCL2640CMsg;
import business.blap.NFCL2640.NFCL2640Query;
import business.blap.NFCL2640.NFCL2640SMsg;
import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_ISS_CYCLE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   Fujitsu         M.Ohno          Create          N/A
 * 2016/07/12   Fujitsu         S.Yoshida       Update          QC#11461
 * 2019/02/26   Fujitsu         Y.Matsui        Update          QC#30302
 *</pre>
 */
public class NFCL2640CommonLogic {

    // START 2019/2/26 Y.Matsui [QC#30302, MOD]
    /**
     * addMonths
     * @param bizMsg NFCL2640CMsg
     * @param yyyyMMdd String
     * @param months int
     * @return String
     */
    public static String addMonths(NFCL2640CMsg bizMsg, String yyyyMMdd, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(yyyyMMdd.substring(0, 4)), Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1, Integer.parseInt(yyyyMMdd.substring(6, 8)));
        calendar.add(Calendar.MONTH, months);

        String cycleCd = bizMsg.arStmtIssCycleCd.getValue();
        if (AR_STMT_ISS_CYCLE.MONTHLY_LAST.equals(cycleCd)) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(calendar.getTime());
    }
    // END   2019/2/26 Y.Matsui [QC#30302, MOD]

    /**
     * Get AddYear
     * @param yyyyMMdd String
     * @param year int
     * @return String
     */
    public static String addYear(String yyyyMMdd, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(yyyyMMdd.substring(0, 4)), Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1, Integer.parseInt(yyyyMMdd.substring(6, 8)));
        calendar.add(Calendar.YEAR, year);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * createNextStmtDate
     * @param bizMsg NFCL2640CMsg
     * @param glblCmpyCd String
     * @return String
     */
    public static String createNextStmtDate(NFCL2640CMsg bizMsg, String glblCmpyCd) {
        String day = findArAcctDtInfoForAcctDt(bizMsg, glblCmpyCd);
        String cycleCd = bizMsg.arStmtIssCycleCd.getValue();

        // START 2019/2/26 Y.Matsui [QC#30302, ADD]
        if (AR_STMT_ISS_CYCLE.MONTHLY_LAST.equals(cycleCd)){
            day = addMonths(bizMsg, day, 1);
            return day;
        }
        // END   2019/2/26 Y.Matsui [QC#30302, ADD]

        if (!day.substring(6, 8).equals(cycleCd)) {
            if (day.substring(6, 8).compareTo(cycleCd) > 0) {
                day = addMonths(bizMsg, day, 1); // 2019/2/26 Y.Matsui [QC#30302, MOD]
                day = day.substring(0, 6) + cycleCd;
            } else {
                day = day.substring(0, 6) + cycleCd;
            }
        }
        return day;
    }

    /**
     * searchArStmtCtrl
     * @param bizMsg NFCL2640CMsg
     * @param glblMsg NFCL2640SMsg
     */
    public static void searchArStmtCtrl(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NFCL2640Query.getInstance().getArStmtCtrl(bizMsg, glblMsg);
        if (bizMsg.getScreenAplID().equals("NFCL2640Scrn00_Search") && ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NFCM0803E, null);
        }
        if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NFCM0798E, new String[] {Integer.toString(bizMsg.A.length()) });
        }
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (glblMsg.A.no(i).lateFeeCalcFlg_A1.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                glblMsg.A.no(i).lateFeeCalcFlg_A1.clear();
            }
        }
        EZDMsg.copy(glblMsg.A, "A1", glblMsg.B, "B1");
        EZDMsg.copy(glblMsg.A, "A1", bizMsg.A, "A1");
        EZDMsg.copy(glblMsg.B, "B1", bizMsg.B, "B1");
    }

    /**
     * findArAcctDtInfoForAcctDt
     * @param bizMsg NFCL2640CMsg
     * @param glblCmpyCd Global Company Code
     * @return AR Account Date
     */
    public static String findArAcctDtInfoForAcctDt(NFCL2640CMsg bizMsg, String glblCmpyCd) {

        String arSubSysId = ZYPCodeDataUtil.getVarCharConstValue(CONST_AR_SUB_SYS_ID, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(arSubSysId)) {
            bizMsg.setMessageInfo(NFCM0501E, new String[]{CONST_AR_SUB_SYS_ID});
            return null;
        }

        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.subSysCd.setValue(arSubSysId);
        inMsg.onlBatTpCd.setValue(ONL_BAT_TP_CD);

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            bizMsg.setMessageInfo(NFCM0501E, new String[]{new AR_ACCT_DTTMsg().getTableName()});
            return null;
        }

        return outMsg.acctDt.getValue();
    }

}
