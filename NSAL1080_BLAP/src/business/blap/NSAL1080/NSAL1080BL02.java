/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1080;

import static business.blap.NSAL1080.constant.NSAL1080Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1080.common.NSAL1080CommonLogic;
import business.db.SVC_CR_REBILTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Credit Rebill Main Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         A.Kohinata      Create          N/A
 * 2017/09/21   CITS            M.Naito         Update          CSA QC#18758
 *</pre>
 */
public class NSAL1080BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1080CMsg cMsg = (NSAL1080CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1080_INIT".equals(screenAplID)) {
                doProcess_NSAL1080_INIT(cMsg);
            } else if ("NSAL1080Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1080Scrn00_CMN_Clear(cMsg);
            } else if ("NSAL1080Scrn00_CancelCI".equals(screenAplID)) {
                doProcess_NSAL1080Scrn00_CancelCI(cMsg);
            } else if ("NSAL1080Scrn00_Preview".equals(screenAplID)) {
                doProcess_NSAL1080Scrn00_Preview(cMsg);
            } else if ("NSAL1080Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1080Scrn00_Search(cMsg);
            } else if ("NSAL1080_NSAL1030".equals(screenAplID)) {
                doProcess_NSAL1080_NSAL1030(cMsg);
            } else if ("NSAL1080_NSAL1090".equals(screenAplID)) {
                doProcess_NSAL1080_NSAL1090(cMsg);
            } else if ("NSAL1080_NSAL1100".equals(screenAplID)) {
                doProcess_NSAL1080_NSAL1100(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL1080_INIT(NSAL1080CMsg cMsg) {

        // add start 2017/09/21 CSA Defect#18758
        String custIncdtId = cMsg.custIncdtId.getValue();

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL1080CommonLogic.clearMsg(cMsg);

        if (ZYPCommonFunc.hasValue(custIncdtId)) {
            ZYPEZDItemValueSetter.setValue(cMsg.custIncdtId, custIncdtId);
            NSAL1080Query.getInstance().getCIInfo(cMsg);
        }
        // add end 2017/09/21 CSA Defect#18758
    }

    private void doProcess_NSAL1080Scrn00_CMN_Clear(NSAL1080CMsg cMsg) {

        NSAL1080CommonLogic.clearMsg(cMsg);
    }

    private void doProcess_NSAL1080Scrn00_CancelCI(NSAL1080CMsg cMsg) {

        cMsg.svcCrRebilStsDescTxt.clear();
        cMsg.svcCrRebilDescTxt.clear();

        if (!hasValue(cMsg.custIncdtId)) {
            cMsg.custIncdtId.setErrorInfo(1, ZZZM9025E, new String[] {"Customer Incident#" });
            cMsg.setMessageInfo(ZZZM9025E, new String[] {"Customer Incident#" });
            return;
        }

        SVC_CR_REBILTMsgArray tMsgList = NSAL1080CommonLogic.getSvcCrRebil(cMsg);
        if (tMsgList.getValidCount() == 0) {
            cMsg.custIncdtId.setErrorInfo(1, NSAM0416E, new String[] {"Customer Incident#", "Service Credit Rebill" });
            cMsg.setMessageInfo(NSAM0416E, new String[] {"Customer Incident#", "Service Credit Rebill" });
            return;
        }

        String svcCrRebilStsCd = tMsgList.no(0).svcCrRebilStsCd.getValue();
        if (!SVC_CR_REBIL_STS.ENTERED.endsWith(svcCrRebilStsCd) && !SVC_CR_REBIL_STS.PENDING_APPROVAL.endsWith(svcCrRebilStsCd)) {
            cMsg.custIncdtId.setErrorInfo(1, NSAM0065E);
            cMsg.setMessageInfo(NSAM0065E);
        }

        ZYPEZDItemValueSetter.setValue(cMsg.svcCrRebilPk, tMsgList.no(0).svcCrRebilPk);
    }

    private void doProcess_NSAL1080Scrn00_Preview(NSAL1080CMsg cMsg) {

        cMsg.svcCrRebilStsDescTxt.clear();
        cMsg.svcCrRebilDescTxt.clear();

        if (!hasValue(cMsg.custIncdtId)) {
            cMsg.custIncdtId.setErrorInfo(1, ZZZM9025E, new String[] {"Customer Incident#" });
            cMsg.setMessageInfo(ZZZM9025E, new String[] {"Customer Incident#" });
            return;
        }

        SVC_CR_REBILTMsgArray tMsgList = NSAL1080CommonLogic.getSvcCrRebil(cMsg);
        if (tMsgList.getValidCount() == 0) {
            cMsg.custIncdtId.setErrorInfo(1, NSAM0416E, new String[] {"Customer Incident#", "Service Credit Rebill" });
            cMsg.setMessageInfo(NSAM0416E, new String[] {"Customer Incident#", "Service Credit Rebill" });
        }
    }

    private void doProcess_NSAL1080Scrn00_Search(NSAL1080CMsg cMsg) {

        if (!hasValue(cMsg.svcInvNum) && !hasValue(cMsg.dsContrNum) && !hasValue(cMsg.conslBillPk)) {
            cMsg.svcInvNum.setErrorInfo(1, NSAM0417E, new String[] {"Invoice#", "Contract#", "Consolidated Bill#" });
            cMsg.dsContrNum.setErrorInfo(1, NSAM0417E, new String[] {"Invoice#", "Contract#", "Consolidated Bill#" });
            cMsg.conslBillPk.setErrorInfo(1, NSAM0417E, new String[] {"Invoice#", "Contract#", "Consolidated Bill#" });
            cMsg.setMessageInfo(NSAM0417E, new String[] {"Invoice#", "Contract#", "Consolidated Bill#" });
            return;
        }

        if (hasValue(cMsg.svcInvNum) && !NSAL1080CommonLogic.existsSvcInv(cMsg)) {
            cMsg.svcInvNum.setErrorInfo(1, NSAM0416E, new String[] {"Invoice#", "Service Invoice" });
            cMsg.setMessageInfo(NSAM0416E, new String[] {"Invoice#", "Service Invoice" });
            return;
        }

        if (hasValue(cMsg.dsContrNum) && !NSAL1080CommonLogic.existsDsContr(cMsg)) {
            cMsg.dsContrNum.setErrorInfo(1, NSAM0416E, new String[] {"Contract#", "DS Contract" });
            cMsg.setMessageInfo(NSAM0416E, new String[] {"Contract#", "DS Contract" });
            return;
        }

        if (hasValue(cMsg.conslBillPk) && !NSAL1080CommonLogic.existsConslBillLine(cMsg)) {
            cMsg.conslBillPk.setErrorInfo(1, NSAM0416E, new String[] {"Consolidated Bill#", "Consolidated Bill Line" });
            cMsg.setMessageInfo(NSAM0416E, new String[] {"Consolidated Bill#", "Consolidated Bill Line" });
            return;
        }
    }

    private void doProcess_NSAL1080_NSAL1030(NSAL1080CMsg cMsg) {

        NSAL1080Query.getInstance().getCIInfo(cMsg);
    }

    private void doProcess_NSAL1080_NSAL1090(NSAL1080CMsg cMsg) {

        NSAL1080Query.getInstance().getCIInfo(cMsg);
    }

    private void doProcess_NSAL1080_NSAL1100(NSAL1080CMsg cMsg) {

        NSAL1080Query.getInstance().getCIInfo(cMsg);
    }
}
