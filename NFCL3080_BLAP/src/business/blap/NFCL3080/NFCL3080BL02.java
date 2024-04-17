package business.blap.NFCL3080;

import static business.blap.NFCL3080.constant.NFCL3080Constant.NFCM0041E;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NZZM0000E;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NZZM0001W;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3080.common.NFCL3080CommonLogic;
import business.db.INVTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFCL3080BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Fujita        Create          N/A
 * 2016/04/13   Fujitsu         S.Fujita        Create          QC#6975
 *</pre>
 */
public class NFCL3080BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL3080CMsg bizMsg = (NFCL3080CMsg) cMsg;
            NFCL3080SMsg glblMsg = (NFCL3080SMsg) sMsg;

            if ("NFCL3080_INIT".equals(screenAplID)) {
                doProcess_NFCL3080_INIT(bizMsg, glblMsg);

            } else if ("NFCL3080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3080Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3080_INIT(NFCL3080CMsg bizMsg, NFCL3080SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(bizMsg.invNum)) {
            bizMsg.setMessageInfo(NFCM0041E);
        } else {
            if (!checkInvNum(bizMsg)) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }
            if (!checkOrgInvNum(bizMsg)) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }
            if (!checkSvcInvNum(bizMsg)) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }
            if (!meterSearch(bizMsg, glblMsg)) {
                if (!svcInvSearch(bizMsg, glblMsg)) {
                    bizMsg.setMessageInfo(NZZM0000E);
                }
            }
        }
    }

    /**
     * Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3080Scrn00_CMN_Submit(NFCL3080CMsg bizMsg, NFCL3080SMsg glblMsg) {

        // research
        meterSearch(bizMsg, glblMsg);
    }

    /**
     * Check InvNum
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean checkInvNum(NFCL3080CMsg bizMsg) {

        INVTMsg inInvMsg = new INVTMsg();
        inInvMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inInvMsg.invNum.setValue(bizMsg.invNum.getValue());
        INVTMsg outInvMsg = (INVTMsg) EZDTBLAccessor.findByKey(inInvMsg);

        if (outInvMsg == null) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.invTpCd_H, outInvMsg.invTpCd.getValue());
           // START 2016/04/13 S.Fujita [S21 NA Unit Test QC#6975,MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.invNum_OR, outInvMsg.origInvNum.getValue());
           // END 2016/04/13 S.Fujita [S21 NA Unit Test QC#6975,MOD]
        }
        return true;
    }

    /**
     * Check OrgInvNum
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean checkOrgInvNum(NFCL3080CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFCL3080Query.getInstance().checkOrgInvNum(bizMsg);
        Integer orgCnt = (Integer) ssmResult.getResultObject();

        if (orgCnt == 0) {
            return false;
        }
        return true;
    }

    /**
     * Check SvcInvNum
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean checkSvcInvNum(NFCL3080CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFCL3080Query.getInstance().checkSvcInvNum(bizMsg);
        Integer svcCnt = (Integer) ssmResult.getResultObject();

        if (svcCnt == 0) {
            return false;
        }
        return true;
    }

    /**
     * Meter Search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     * @return boolean
     */
    private boolean meterSearch(NFCL3080CMsg bizMsg, NFCL3080SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NFCL3080Query.getInstance().meterSearch(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            return false;
        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            checkInvPrt(bizMsg);

            NFCL3080CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
        return true;
    }

    /**
     * SvcInv Search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     * @return boolean
     */
    private boolean svcInvSearch(NFCL3080CMsg bizMsg, NFCL3080SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NFCL3080Query.getInstance().svcInvSearch(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            return false;
        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            checkInvPrt(bizMsg);

            NFCL3080CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
        return true;
    }

    /**
     * Check InvPrt
     * @param bizMsg Business Component Interface Message
     */
    private void checkInvPrt(NFCL3080CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFCL3080Query.getInstance().checkInvPrt(bizMsg);
        Integer prtCnt = (Integer) ssmResult.getResultObject();

        if (prtCnt == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invNum_H, "NOT_EXIST");
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.invNum_H, "EXIST");
        }
    }
}
