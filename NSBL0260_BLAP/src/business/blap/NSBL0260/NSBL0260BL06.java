/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0260;

import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0118E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0154E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSAM0127E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.ZZZM9003I;
import static business.blap.NSBL0260.constant.NSBL0260Constant.COLUMN_NAME_MDL_NM;
import static business.blap.NSBL0260.constant.NSBL0260Constant.COLUMN_NAME_SER_NUM;
import static business.blap.NSBL0260.constant.NSBL0260Constant.TABLE_NAME_MDL_NM;
import static business.blap.NSBL0260.constant.NSBL0260Constant.TABLE_NAME_SVC_MACH_MSTR;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashSet;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import business.blap.NSBL0260.common.NSBL0260CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract Validation List Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/07/03   Hitachi         K.Kim           Update          QC#18164
 * 2018/06/13   Hitachi         A.Kohinata      Update          QC#26028
 *</pre>
 */
public class NSBL0260BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSBL0260Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_CMN_Submit((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

  /**
     * do process (Submit)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_CMN_Submit(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        if (sMsg.A.getValidCount() == 0 && sMsg.D.getValidCount() == 0) {
            return;
        }

        if (isDuplicatedSerNum(cMsg, sMsg)) {
            return;
        }

        boolean errorFlg = false;
        int curr = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0260_ASMsg asMsg = (NSBL0260_ASMsg) sMsg.A.no(i);
            if (!NSBL0260CommonLogic.isCheckExistMdlNm(getGlobalCompanyCode(), asMsg)) {
                sMsg.A.no(i).mdlNm_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_MDL_NM, TABLE_NAME_MDL_NM });
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).mdlNm_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_MDL_NM, TABLE_NAME_MDL_NM });
                }
                errorFlg = true;
            }
            if (!NSBL0260CommonLogic.isCheckExistSvcMachMstr(getGlobalCompanyCode(), asMsg)) {
                sMsg.A.no(i).serNum_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_SER_NUM, TABLE_NAME_SVC_MACH_MSTR });
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).serNum_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_SER_NUM, TABLE_NAME_SVC_MACH_MSTR });
                }
                errorFlg = true;
            }
            // START 2017/07/03 K.Kim [QC#18164, ADD]
            if (!NSBL0260CommonLogic.isCheckExistMdlNmSvcMachMstr(getGlobalCompanyCode(), asMsg)){
            	sMsg.A.no(i).mdlNm_A.setErrorInfo(1, NSAM0127E, new String[] {COLUMN_NAME_MDL_NM, COLUMN_NAME_SER_NUM });
                sMsg.A.no(i).serNum_A.setErrorInfo(1, NSAM0127E, new String[] {COLUMN_NAME_MDL_NM, COLUMN_NAME_SER_NUM });
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).mdlNm_A.setErrorInfo(1, NSAM0127E, new String[] {COLUMN_NAME_MDL_NM, COLUMN_NAME_SER_NUM });
                    cMsg.A.no(curr).serNum_A.setErrorInfo(1, NSAM0127E, new String[] {COLUMN_NAME_MDL_NM, COLUMN_NAME_SER_NUM });
                }
                errorFlg = true;
            }
            // END 2017/07/03 K.Kim [QC#18164, ADD]
        }
        if (errorFlg) {
            return;
        }

        if (!NSBL0260CommonLogic.insertAndUpdateDsMdlEolEx(cMsg, sMsg)) {
            return;
        }
        if (!NSBL0260CommonLogic.deleteDsMdlEolEx(cMsg, sMsg)) {
            return;
        }

        if (!hasValue(cMsg.getMessageCode())) {
            // add start 2018/06/13 QC#26028
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
            // add end 2018/06/13 QC#26028
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
            return;
        }
    }

    private boolean isDuplicatedSerNum(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        if (sMsg == null) {
            return true;
        }

        Set<String> dupliSerNum = new HashSet<String>();
        Set<String> dupliCheckSerNum = new HashSet<String>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String nowSortNum = sMsg.A.no(i).serNum_A.getValue();
            if (dupliCheckSerNum.contains(nowSortNum)) {
                dupliSerNum.add(nowSortNum);
            } else {
                dupliCheckSerNum.add(nowSortNum);
            }
        }
        boolean errorFlg = false;
        int curr = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String nowSortNum = sMsg.A.no(i).serNum_A.getValue();
            if (dupliSerNum.contains(nowSortNum)) {
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).serNum_A.setErrorInfo(1, NSBM0118E, new String[] {"Serial Number" });
                }
                errorFlg = true;
            }
        }
        return errorFlg;
    }
}
