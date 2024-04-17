/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0600;

import static business.blap.NSAL0600.common.NSAL0600CommonLogic.*;
import static business.blap.NSAL0600.constant.NSAL0600Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Cascade Date
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NSAL0600BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0600CMsg cMsg = (NSAL0600CMsg) arg0;
        NSAL0600SMsg sMsg = (NSAL0600SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0600_INIT".equals(screenAplID)) {
                doProcess_NSAL0600_INIT(cMsg, sMsg);
            } else if ("NSAL0600Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_ApplyToAll(cMsg, sMsg);
            } else if ("NSAL0600Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL0600Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL0600Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0600Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0600Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_SelectAll(cMsg, sMsg);
            } else if ("NSAL0600Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_UnselectAll(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0600_INIT(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {

        clearMsg(cMsg, sMsg);

        createPullDown(cMsg, getGlobalCompanyCode());
        setInitParams(cMsg, sMsg, getGlobalCompanyCode());

        pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0600Scrn00_ApplyToAll(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        // check item
        if (isErrorApplyToAll(cMsg)) {
            return;
        }

        // check Invoice Date
        if (isErrorInvDt(cMsg)) {
            return;
        }

        // copy cMsg to sMsg
        copyCMsgToSMsg(cMsg, sMsg);
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        setPagenation(cMsg, sMsg, pageFromNum);

        // copy to new Date
        copyNewDate(sMsg);

        // copy sMsg to cMsg
        EZDMsg.copy(sMsg, null, cMsg, null);
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0600Scrn00_CMN_Reset(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        doProcess_NSAL0600_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL0600Scrn00_CMN_Submit(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        doProcess_NSAL0600_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL0600Scrn00_PageNext(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0600Scrn00_PagePrev(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0600Scrn00_SelectAll(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {

        setChkBox(sMsg, ZYPConstant.FLG_ON_Y);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0600Scrn00_UnselectAll(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {

        setChkBox(sMsg, ZYPConstant.FLG_OFF_N);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        pagenation(cMsg, sMsg, pagenationFrom);
    }

    private boolean isErrorApplyToAll(NSAL0600CMsg cMsg) {
        if (!hasValue(cMsg.xxFromDt_H) && !hasValue(cMsg.xxThruDt_H)) {
            cMsg.xxFromDt_H.setErrorInfo(1, NSAM0212E, new String[] {"New Start Date or New End Date" });
            cMsg.xxThruDt_H.setErrorInfo(1, NSAM0212E, new String[] {"New Start Date or New End Date" });
            return true;
        }

        if (hasValue(cMsg.xxFromDt_H) && hasValue(cMsg.xxThruDt_H)) {
            if (ZYPDateUtil.compare(cMsg.xxFromDt_H.getValue(), cMsg.xxThruDt_H.getValue()) > 0) {
                cMsg.xxFromDt_H.setErrorInfo(1, NSAM0346E, new String[] {"New End Date", "New Start Date" });
                cMsg.xxThruDt_H.setErrorInfo(1, NSAM0346E, new String[] {"New End Date", "New Start Date" });
                return true;
            }
        }
        return false;
    }

    private boolean isErrorInvDt(NSAL0600CMsg cMsg) {
        String invDt = NSAL0600Query.getInstance().getInvDt(getGlobalCompanyCode(), cMsg.dsContrPk_P.getValue());
        if (!hasValue(invDt)) {
            return false;
        }

        if (ZYPDateUtil.compare(cMsg.xxThruDt_H.getValue(), invDt) < 0) {
            cMsg.xxThruDt_H.setErrorInfo(1, NSAM0346E, new String[] {"New End Date", "Invoice Date" });
            return true;
        }
        return false;
    }

    private void copyNewDate(NSAL0600SMsg sMsg) {
        // Contract Data
        // From Date
        if (hasValue(sMsg.xxFromDt_H)) {
            setValue(sMsg.contrVrsnEffFromDt_N, sMsg.xxFromDt_H);
        } else {
            setValue(sMsg.contrVrsnEffFromDt_N, sMsg.contrVrsnEffFromDt);
        }
        // Thru Date
        if (hasValue(sMsg.xxThruDt_H)) {
            setValue(sMsg.contrVrsnEffThruDt_N, sMsg.xxThruDt_H);
        } else {
            setValue(sMsg.contrVrsnEffThruDt_N, sMsg.contrVrsnEffThruDt);
        }

        // Machine Data
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!hasValue(sMsg.A.no(i).xxChkBox_A) || !ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                sMsg.A.no(i).contrEffFromDt_AN.clear();
                sMsg.A.no(i).contrEffThruDt_AN.clear();
                continue;
            }
            // From Date
            if (hasValue(sMsg.xxFromDt_H)) {
                setValue(sMsg.A.no(i).contrEffFromDt_AN, sMsg.xxFromDt_H);
            } else {
                setValue(sMsg.A.no(i).contrEffFromDt_AN, sMsg.A.no(i).contrEffFromDt_A);
            }
            // Thru Date
            if (hasValue(sMsg.xxThruDt_H)) {
                setValue(sMsg.A.no(i).contrEffThruDt_AN, sMsg.xxThruDt_H);
            } else {
                setValue(sMsg.A.no(i).contrEffThruDt_AN, sMsg.A.no(i).contrEffThruDt_A);
            }
        }
    }
}
