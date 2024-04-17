/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0150;

import static business.blap.NWCL0150.constant.NWCL0150Constant.CFS_LEASE_PKG_PO_HDR;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_CMN_SUBMIT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.NWCM0110E;
import static business.blap.NWCL0150.constant.NWCL0150Constant.NWCM0112E;
import static business.blap.NWCL0150.constant.NWCL0150Constant.NWCM0137E;
import static business.blap.NWCL0150.constant.NWCL0150Constant.SUBMIT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.ZZZM9003I;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWCL0150.common.NWCL0150CommonLogic;
import business.db.CFS_LEASE_PKG_PO_HDRTMsg;
import business.db.CFS_LEASE_PO_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_MAN_HLD_ACT_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * Function Name : update business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 * 09/21/2017   Fujitsu         H.Ikeda         Update          QC#20382
 * 01/09/2018   Fujitsu         H.Honda         Update          QC#21706-2
 * 02/21/2018   Fujitsu         H.Honda         Update          QC#24324
 * 09/30/2022   Hitachi         H.Watanabe      Update          QC#60253
 *</pre>
 */
public class NWCL0150BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NWCL0150_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NWCL0150CMsg) cMsg, (NWCL0150SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * doProcess_CMN_Submit
     * @param cMsg NWCL0150CMsg
     * @param sMsg NWCL0150SMsg
     */
    private void doProcess_CMN_Submit(NWCL0150CMsg cMsg, NWCL0150SMsg sMsg) {

        String glblCmpyCd = getUserProfileService().getGlobalCompanyCode();
        NWCL0150CommonLogic.updateGlblMsg(cMsg, sMsg);

        try {
            boolean result = update(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            cMsg.setMessageInfo(ZZZM9003I, new String[] {SUBMIT });
        } finally {
            NWCL0150CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
        }

    }

    /**
     * Update CFS_LEASE_PKG_PO_HDR
     * @param glblCmpyCd String
     * @param cMSg NWCL0150CMsg
     * @param sMsg NWCL0150SMsg
     * @return boolean
     */
    private boolean update(String glblCmpyCd, NWCL0150CMsg cMsg, NWCL0150SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // Update Line
            BigDecimal fsLeasePkgPoHdrPk = sMsg.A.no(i).cfsLeasePkgPoHdrPk_A1.getValue();
            CFS_LEASE_PKG_PO_HDRTMsg tMsg = lockCfsLeasePkgPoHdrForUpdate(cMsg, glblCmpyCd, fsLeasePkgPoHdrPk, sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue());
            if (NWCM0137E.equals(cMsg.getMessageCode())) {
                return false;
            }
            if (tMsg == null) {
                cMsg.setMessageInfo(NWCM0137E, null);
                return false;
            }

            // DB value
            String dbCfsManHldActTpCd = tMsg.cfsManHldActTpCd.getValue();
            // Current value
            String curCfsManHldActTpCd = sMsg.A.no(i).cfsManHldActTpCd_A1.getValue();

            if (dbCfsManHldActTpCd.equals(curCfsManHldActTpCd)) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(tMsg.cfsManHldActTpCd, curCfsManHldActTpCd);

            if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(curCfsManHldActTpCd)) {
                // QC#21706-2 2018/01/09 Mod Start
//                ZYPEZDItemValueSetter.setValue(tMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_ON_Y);
                if (tMsg.invCpltAmtRate.getValue().compareTo(cMsg.attrbValNum.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(tMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);
                    // QC#24324 2018/02/21 Del Start
//                    ZYPEZDItemValueSetter.setValue(tMsg.leasePkgCratFlg, ZYPConstant.FLG_ON_Y);
                    // QC#24324 2018/02/21 Del End
                    updateCfsLeasePoInfo(cMsg, glblCmpyCd, tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_ON_Y);
                }
                // QC#21706-2 2018/01/09 Mod End
            } else if (CFS_MAN_HLD_ACT_TP.HOLD.equals(curCfsManHldActTpCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_ON_Y);
            } else if (CFS_MAN_HLD_ACT_TP.IMMEDIATE_RELEASE.equals(curCfsManHldActTpCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);
                // QC#21706-2 2018/01/09 Mod Start
                // QC#24324 2018/02/21 Del Start
//                ZYPEZDItemValueSetter.setValue(tMsg.leasePkgCratFlg, ZYPConstant.FLG_ON_Y);
                // QC#24324 2018/02/21 Del End
//                // QC#20382 2017/09/21 Add Start
//                CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
//                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, tMsg.cfsLeasePoInfoPk.getValue());
//                cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
//                if (cfsLeasePoInfoTMsg == null) {
//                    cMsg.setMessageInfo(NWCM0112E, new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", tMsg.cfsLeasePoInfoPk.getValue())});
//                    return false;
//                }
//
//                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
//                EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
//                    cMsg.setMessageInfo(NWCM0110E, new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", tMsg.cfsLeasePoInfoPk.getValue())});
//                    return false;
//                }
//                // QC#20382 2017/09/21 Add End
                updateCfsLeasePoInfo(cMsg, glblCmpyCd, tMsg);
                // QC#21706-2 2018/01/09 Mod End
            }

            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NWCM0110E, new String[] {CFS_LEASE_PKG_PO_HDR, String.valueOf(fsLeasePkgPoHdrPk) });
                return false;
            }
        }
        return true;
    }

    /**
     * CFS_LEASE_PKG_PO_HDR Table Record Lock
     * @param cMsg NWCL0150CMsg
     * @param cfsLeaseEligAcctPk BigDecimal
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return CFS_LEASE_ELIG_ACCTTMsg
     */
    private static CFS_LEASE_PKG_PO_HDRTMsg lockCfsLeasePkgPoHdrForUpdate(NWCL0150CMsg cMsg, String glblCmpyCd, BigDecimal cfsLeasePkgPoHdrPk, String ezUpTime, String ezUpTimeZone) {

        CFS_LEASE_PKG_PO_HDRTMsg tMsg = new CFS_LEASE_PKG_PO_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

        tMsg = (CFS_LEASE_PKG_PO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            return null;
        }

        String findEzUpTime = ezUpTime;
        String findEzUpTimeZone = ezUpTimeZone;
        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NWCM0137E, null);
            return null;
        }
        return tMsg;
    }

    // QC#21706-2 2018/01/09 Add Start
    /**
     * CFS_LEASE_PKG_PO_HDR Table Record Lock
     * @param cMsg NWCL0150CMsg
     * @param cfsLeaseEligAcctPk BigDecimal
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return CFS_LEASE_ELIG_ACCTTMsg
     */
    private static boolean updateCfsLeasePoInfo(NWCL0150CMsg cMsg, String glblCmpyCd, CFS_LEASE_PKG_PO_HDRTMsg tMsg) {

        //2022/09/30 QC#60253 Add Start
        if (!ZYPCommonFunc.hasValue(tMsg.cfsLeasePoInfoPk)) {
            return true;
        }
        //2022/09/30 QC#60253 Add End
        CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, tMsg.cfsLeasePoInfoPk.getValue());
        cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
        if (cfsLeasePoInfoTMsg == null) {
            cMsg.setMessageInfo(NWCM0112E, new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", tMsg.cfsLeasePoInfoPk.getValue())});
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
        EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWCM0110E, new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", tMsg.cfsLeasePoInfoPk.getValue())});
            return false;
        }
        return true;
    }
    // QC#21706-2 2018/01/09 Add End
}
