/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7060;

import static business.blap.NMAL7060.constant.NMAL7060Constant.BILLING_METER;
import static business.blap.NMAL7060.constant.NMAL7060Constant.COMBI_START_END;
import static business.blap.NMAL7060.constant.NMAL7060Constant.MODEL;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NLBM0009E;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM0072E;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM0073E;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM0163E;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM8296E;
import static business.blap.NMAL7060.constant.NMAL7060Constant.OUTSIDE_EFF_DT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7060.common.NMAL7060CommonLogic;
import business.db.PRC_MTR_PKGTMsg;
import business.db.PRC_MTR_PKG_BLLG_MTRTMsg;
import business.db.PRC_MTR_PKG_MDLTMsg;
import business.db.PRC_MTR_PKG_PHYS_MTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7060BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#6739
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#6738
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#7912
 * 2016/10/26   Fujitsu         W.Honda         Update          QC#15357
 * 2017/02/15   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7060BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7060CMsg bizMsg = (NMAL7060CMsg) cMsg;
            NMAL7060SMsg glblMsg = (NMAL7060SMsg) sMsg;

            if ("NMAL7060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_CMN_Submit(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        NMAL7060CommonLogic.saveCurrentCMsgToSMsgALL(bizMsg, glblMsg);

        // Package Name check what is unique
        if (!checkPackNameUnique(bizMsg, glblMsg)) {
            bizMsg.prcMtrPkgNm.setErrorInfo(1, NMAM8296E, new String[] {bizMsg.prcMtrPkgNm.getValue()});
            bizMsg.setMessageInfo(NMAM8296E, new String[] {bizMsg.prcMtrPkgNm.getValue()});
            return;
        }

        // Model exist Check
        if (!checkModelExist(bizMsg, glblMsg)) {
            return;
        }

        // Model Duplicate Check
        if (!checkDupModel(bizMsg, glblMsg)) {
            return;
        }

        // Billing Meter exist Check
        if (!checkBillMtrExist(bizMsg, glblMsg)) {
            return;
        }

        // QC#15357 2016/10/26 Del start
//        // Billing Meter Level equal Check
//        if (!checkBillMtrEqual(bizMsg, glblMsg)) {
//            return;
//        }
        // QC#15357 2016/10/26 Del end

        // Billing Meter Duplicate Check
        if (!checkBillMtrDup(bizMsg, glblMsg)) {
            return;
        }

        // QC#6739 2016/04/26 Add start
        // QC#15357 2016/10/26 Del start
//        // Same Billing Meter Level is all assign Check
//        if (!checkSameLevelAllAssign(bizMsg, glblMsg)) {
//            return;
//        }
//
//        // Common Counter exist Check in All Model on Package
//        if (!checkCommonCounterExist(bizMsg, glblMsg)) {
//            return;
//        }
        // QC#15357 2016/10/26 Del end
        // QC#6739 2016/04/26 Add end

        // QC#6738 2016/04/26 Del start
        // Detail Check
//        if (!checkDetailItem(glblMsg, bizMsg)) {
//            return;
//        }
        // QC#6738 2016/04/26 Del end

        // Update & Insert
        if (!ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
            if (!insertPrcMtrPkg(glblMsg, bizMsg)) {
                return;
            }
        } else {
            if (!updatetPrcMtrPkg(glblMsg, bizMsg)) {
                return;
            }
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcMtrPkgMdlPk_A1.getValue())) {
                if (!insertPrcMtrPkgMdl(glblMsg, bizMsg, i)) {
                    return;
                }
            } else {
                if (!updatePrcMtrPkgMdl(glblMsg, bizMsg, i)) {
                    return;
                }
            }
        }

        // Delete
        for (int i = 0; i < glblMsg.N.getValidCount(); i++) {
            deletePrcMtrPkgBllgMtr(glblMsg, bizMsg, i);
        }

        for (int i = 0; i < glblMsg.M.getValidCount(); i++) {
            deleteprcMtrPkgPhysMtr(glblMsg, bizMsg, i);
        }

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcMtrPkgBllgMtrPk_B1)) {
                if (!insertPrcMtrPkgBllgMtr(glblMsg, bizMsg, i)) {
                    return;
                }
            } else {
                if (!updatePrcMtrPkgBllgMtr(glblMsg, bizMsg, i)) {
                    return;
                }
            }

            for (int j = 0; j < glblMsg.C.getValidCount(); j++) {
                if (i != glblMsg.C.no(j).xxCellIdx_CB.getValueInt()) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(glblMsg.C.no(j).prcMtrPkgPhysMtrPk_C1)) {
                    if (!insertPrcMtrPkgPhysMtr(glblMsg, bizMsg, i, j)) {
                        return;
                    }
                } else {
                    if (!updatePrcMtrPkgPhysMtr(glblMsg, bizMsg, j)) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * insertPrcMtrPkg
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean insertPrcMtrPkg(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg) {
        PRC_MTR_PKGTMsg prcMtrPkg = new PRC_MTR_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal prcMtrPkgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_MTR_PKG_SQ);
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.prcMtrPkgPk, prcMtrPkgPk);
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.prcMtrPkgNm, glblMsg.prcMtrPkgNm.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.prcMtrPkgDescTxt, glblMsg.prcMtrPkgDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.effFromDt, glblMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.effThruDt, glblMsg.effThruDt.getValue());
        if (ZYPCommonFunc.hasValue(glblMsg.corpAdvPrcFlg)) {
            ZYPEZDItemValueSetter.setValue(prcMtrPkg.corpAdvPrcFlg, glblMsg.corpAdvPrcFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(prcMtrPkg.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
        }
        EZDTBLAccessor.insert(prcMtrPkg);
        String returnCode = prcMtrPkg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0176E", new String[]{"PRC_MTR_PKG"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(glblMsg.prcMtrPkgPk, prcMtrPkgPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcMtrPkgPk, prcMtrPkgPk);
        return true;
    }

    /**
     * updatetPrcMtrPkg
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean updatetPrcMtrPkg(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg) {
        PRC_MTR_PKGTMsg prcMtrPkg = new PRC_MTR_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.prcMtrPkgPk, glblMsg.prcMtrPkgPk.getValue());
        prcMtrPkg = (PRC_MTR_PKGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcMtrPkg);
        if (prcMtrPkg == null) {
            bizMsg.prcMtrPkgNm.setErrorInfo(1, NLBM0009E);
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblMsg.ezUpTime.getValue(), glblMsg.ezUpTimeZone.getValue()
                , prcMtrPkg.ezUpTime.getValue(), prcMtrPkg.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.prcMtrPkgNm.setErrorInfo(1, NLBM0009E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.prcMtrPkgNm, glblMsg.prcMtrPkgNm.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.prcMtrPkgDescTxt, glblMsg.prcMtrPkgDescTxt.getValue());
        if (ZYPCommonFunc.hasValue(glblMsg.corpAdvPrcFlg)) {
            ZYPEZDItemValueSetter.setValue(prcMtrPkg.corpAdvPrcFlg, glblMsg.corpAdvPrcFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(prcMtrPkg.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.effFromDt, glblMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkg.effThruDt, glblMsg.effThruDt.getValue());

        EZDTBLAccessor.update(prcMtrPkg);
        String returnCode = prcMtrPkg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0177E", new String[]{"PRC_MTR_PKG"});
            return false;
        }

        return true;
    }

    /**
     * insertPrcMtrPkgMdl
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexA Index of SMsg.A
     */
    private Boolean insertPrcMtrPkgMdl(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexA) {
        PRC_MTR_PKG_MDLTMsg prcMtrPkgMdl = new PRC_MTR_PKG_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal prcMtrPkgMdlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_MTR_PKG_MDL_SQ);
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.prcMtrPkgMdlPk, prcMtrPkgMdlPk);
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.prcMtrPkgPk, glblMsg.prcMtrPkgPk.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.mdlId, glblMsg.A.no(indexA).mdlId_A1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.mdlNm, glblMsg.A.no(indexA).mdlNm_A1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.effFromDt, glblMsg.A.no(indexA).effFromDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.effThruDt, glblMsg.A.no(indexA).effThruDt_A1.getValue());

        EZDTBLAccessor.insert(prcMtrPkgMdl);
        String returnCode = prcMtrPkgMdl.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0176E", new String[]{"PRC_MTR_PKG_MDL"});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indexA).prcMtrPkgMdlPk_A1, prcMtrPkgMdlPk);

        return true;
    }

    /**
     * updatePrcMtrPkgMdl
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexA Index of SMsg.A
     */
    private Boolean updatePrcMtrPkgMdl(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexA) {
        PRC_MTR_PKG_MDLTMsg prcMtrPkgMdl = new PRC_MTR_PKG_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.prcMtrPkgMdlPk, glblMsg.A.no(indexA).prcMtrPkgMdlPk_A1.getValue());
        prcMtrPkgMdl = (PRC_MTR_PKG_MDLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcMtrPkgMdl);
        if (prcMtrPkgMdl == null) {
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblMsg.A.no(indexA).ezUpTime_AK.getValue(), glblMsg.A.no(indexA).ezUpTimeZone_A1.getValue()
                , prcMtrPkgMdl.ezUpTime.getValue(), prcMtrPkgMdl.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.effFromDt, glblMsg.A.no(indexA).effFromDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgMdl.effThruDt, glblMsg.A.no(indexA).effThruDt_A1.getValue());

        EZDTBLAccessor.update(prcMtrPkgMdl);
        String returnCode = prcMtrPkgMdl.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0177E", new String[]{"PRC_MTR_PKG"});
            return false;
        }

        return true;
    }

    /**
     * insertPrcMtrPkgBllgMtr
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexB Index of SMsg.B
     */
    private Boolean insertPrcMtrPkgBllgMtr(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexB) {
        PRC_MTR_PKG_BLLG_MTRTMsg prcMtrPkgBllgMtr = new PRC_MTR_PKG_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal prcMtrPkgBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_MTR_PKG_BLLG_MTR_SQ);
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.prcMtrPkgBllgMtrPk, prcMtrPkgBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.prcMtrPkgPk, glblMsg.prcMtrPkgPk.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mtrLbCd, glblMsg.B.no(indexB).mtrLbCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mtrLbNm, glblMsg.B.no(indexB).mtrLbNm_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mtrLbDescTxt, glblMsg.B.no(indexB).mtrLbDescTxt_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mdseCd, glblMsg.B.no(indexB).mdseCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.bllgMtrLvlNum, glblMsg.B.no(indexB).bllgMtrLvlNum_B1.getValue());

        EZDTBLAccessor.insert(prcMtrPkgBllgMtr);
        String returnCode = prcMtrPkgBllgMtr.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0176E", new String[]{"PRC_MTR_PKG_BLLG_MTR"});
            return false;
        }

        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(indexB).prcMtrPkgBllgMtrPk_B1, prcMtrPkgBllgMtrPk);

        return true;
    }

    /**
     * updatePrcMtrPkgBllgMtr
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexB Index of SMsg.B
     */
    private Boolean updatePrcMtrPkgBllgMtr(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexB) {
        PRC_MTR_PKG_BLLG_MTRTMsg prcMtrPkgBllgMtr = new PRC_MTR_PKG_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.prcMtrPkgBllgMtrPk, glblMsg.B.no(indexB).prcMtrPkgBllgMtrPk_B1.getValue());
        prcMtrPkgBllgMtr = (PRC_MTR_PKG_BLLG_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcMtrPkgBllgMtr);
        if (prcMtrPkgBllgMtr == null) {
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblMsg.B.no(indexB).ezUpTime_B1.getValue(), glblMsg.B.no(indexB).ezUpTimeZone_B1.getValue()
                , prcMtrPkgBllgMtr.ezUpTime.getValue(), prcMtrPkgBllgMtr.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mtrLbCd, glblMsg.B.no(indexB).mtrLbCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.bllgMtrLvlNum, glblMsg.B.no(indexB).bllgMtrLvlNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mtrLbDescTxt, glblMsg.B.no(indexB).mtrLbDescTxt_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mtrLbNm, glblMsg.B.no(indexB).mtrLbNm_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.mdseCd, glblMsg.B.no(indexB).mdseCd_B1.getValue());

        EZDTBLAccessor.update(prcMtrPkgBllgMtr);
        String returnCode = prcMtrPkgBllgMtr.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0177E", new String[]{"PRC_MTR_PKG"});
            return false;
        }

        return true;
    }

    /**
     * insertPrcMtrPkgPhysMtr
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexB Index of SMsg.B
     * @param indexC Index of SMsg.C
     */
    private Boolean insertPrcMtrPkgPhysMtr(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexB, int indexC) {

        PRC_MTR_PKG_PHYS_MTRTMsg prcMtrPkgPhysMtr = new PRC_MTR_PKG_PHYS_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal prcMtrPkgPhysMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_MTR_PKG_PHYS_MTR_SQ);
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.prcMtrPkgPhysMtrPk, prcMtrPkgPhysMtrPk);
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.prcMtrPkgBllgMtrPk, glblMsg.B.no(indexB).prcMtrPkgBllgMtrPk_B1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.mtrLbCd, glblMsg.C.no(indexC).mtrLbCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.bllblFlg, glblMsg.C.no(indexC).bllblFlg_C1.getValue());
        if (ZYPCommonFunc.hasValue(glblMsg.C.no(indexC).actvFlg_C1)) {
            ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.actvFlg, glblMsg.C.no(indexC).actvFlg_C1.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.mtrMultRate, glblMsg.C.no(indexC).mtrMultRate_C1.getValue());

        EZDTBLAccessor.insert(prcMtrPkgPhysMtr);
        String returnCode = prcMtrPkgPhysMtr.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0176E", new String[]{"PRC_MTR_PKG_PHYS_MTR"});
            return false;
        }

        return true;
    }

    /**
     * updatePrcMtrPkgPhysMtr
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexC Index of SMsg.C
     */
    private Boolean updatePrcMtrPkgPhysMtr(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexC) {
        PRC_MTR_PKG_PHYS_MTRTMsg prcMtrPkgPhysMtr = new PRC_MTR_PKG_PHYS_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.prcMtrPkgPhysMtrPk, glblMsg.C.no(indexC).prcMtrPkgPhysMtrPk_C1.getValue());
        prcMtrPkgPhysMtr = (PRC_MTR_PKG_PHYS_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcMtrPkgPhysMtr);
        if (prcMtrPkgPhysMtr == null) {
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblMsg.C.no(indexC).ezUpTime_CK.getValue(), glblMsg.C.no(indexC).ezUpTimeZone_C1.getValue()
                , prcMtrPkgPhysMtr.ezUpTime.getValue(), prcMtrPkgPhysMtr.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.bllblFlg, glblMsg.C.no(indexC).bllblFlg_C1.getValue());
        if (ZYPCommonFunc.hasValue(glblMsg.C.no(indexC).actvFlg_C1)) {
            ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.actvFlg, glblMsg.C.no(indexC).actvFlg_C1.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.mtrMultRate, glblMsg.C.no(indexC).mtrMultRate_C1.getValue());

        EZDTBLAccessor.update(prcMtrPkgPhysMtr);
        String returnCode = prcMtrPkgPhysMtr.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0177E", new String[]{"PRC_MTR_PKG_PHYS_MTR"});
            return false;
        }

        return true;
    }

    /**
     * deletePrcMtrPkgBllgMtr
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexB Index of SMsg.N
     */
    private Boolean deletePrcMtrPkgBllgMtr(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexB) {
        PRC_MTR_PKG_BLLG_MTRTMsg prcMtrPkgBllgMtr = new PRC_MTR_PKG_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgMtr.prcMtrPkgBllgMtrPk, glblMsg.N.no(indexB).prcMtrPkgBllgMtrPk_N1.getValue());
        prcMtrPkgBllgMtr = (PRC_MTR_PKG_BLLG_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcMtrPkgBllgMtr);
        if (prcMtrPkgBllgMtr == null) {
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblMsg.N.no(indexB).ezUpTime_NU.getValue(), glblMsg.N.no(indexB).ezUpTimeZone_NU.getValue()
                , prcMtrPkgBllgMtr.ezUpTime.getValue(), prcMtrPkgBllgMtr.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        EZDTBLAccessor.logicalRemove(prcMtrPkgBllgMtr);
        String returnCode = prcMtrPkgBllgMtr.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0177E", new String[]{"PRC_MTR_PKG"});
            return false;
        }

        return true;
    }

    /**
     * deletePrcMtrPkgBllgMtr
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param indexC Index of SMsg.M
     */
    private Boolean deleteprcMtrPkgPhysMtr(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg, int indexC) {
        PRC_MTR_PKG_PHYS_MTRTMsg prcMtrPkgPhysMtr = new PRC_MTR_PKG_PHYS_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prcMtrPkgPhysMtr.prcMtrPkgPhysMtrPk, glblMsg.M.no(indexC).prcMtrPkgPhysMtrPk_M1.getValue());
        prcMtrPkgPhysMtr = (PRC_MTR_PKG_PHYS_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcMtrPkgPhysMtr);
        if (prcMtrPkgPhysMtr == null) {
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblMsg.M.no(indexC).ezUpTime_MU.getValue(), glblMsg.M.no(indexC).ezUpTimeZone_MU.getValue()
                , prcMtrPkgPhysMtr.ezUpTime.getValue(), prcMtrPkgPhysMtr.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        EZDTBLAccessor.logicalRemove(prcMtrPkgPhysMtr);
        String returnCode = prcMtrPkgPhysMtr.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo("NMAM0177E", new String[]{"PRC_MTR_PKG_PHYS_MTR"});
            return false;
        }

        return true;
    }

    /**
     * checkPackNameUnique
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkPackNameUnique(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NMAL7060Query.getInstance().getPackageName(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            if (!ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
                bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.prcMtrPkgNm.getValue(), "PRC_MTR_PKG"});
                return false;
            } else {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (int i = 0; i < resultList.size(); i++) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                    if (!(bizMsg.prcMtrPkgPk.getValue().compareTo((BigDecimal) resultMap.get("PRC_MTR_PKG_PK")) == 0)) {
                        bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.prcMtrPkgNm.getValue(), "PRC_MTR_PKG"});
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * checkModelExist
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkModelExist(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        List<String> mdlNmList = new ArrayList<String>();
        List<Integer> mdlIdx = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // QC#6739 2016/04/26 Del start
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcMtrPkgMdlPk_A1.getValue())) {
//                continue;
//            }
            // QC#6739 2016/04/26 Del end

            mdlNmList.add(bizMsg.A.no(i).mdlNm_A1.getValue());
            mdlIdx.add(Integer.valueOf(i));
        }
        String[] mdlNms = (String[]) mdlNmList.toArray(new String[mdlNmList.size()]);

        if (mdlNms.length == 0) {
            return true;
        }

        S21SsmEZDResult ssmResult = NMAL7060Query.getInstance().getModelName(bizMsg, mdlNms);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int idx : mdlIdx) {
                boolean matchFlg = false;
                for (int j = 0; j < resultList.size(); j++) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);
                    if (bizMsg.A.no(idx).mdlNm_A1.getValue().equals((String) resultMap.get("T_MDL_NM"))) {
                        // QC#6739 2016/04/26 Add start
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mtrGrpPk_A1, (BigDecimal) resultMap.get("MTR_GRP_PK"));
                        // QC#6739 2016/04/26 Add end
                        matchFlg = true;
                        break;
                    }
                }

                if (!matchFlg) {
                    bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.A.no(idx).mdlNm_A1.getValue(), "DS_MDL"});
                    return false;
                }
            }
        } else {
            for (int idx : mdlIdx) {
                bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.A.no(idx).mdlNm_A1.getValue(), "DS_MDL"});
                return false;
            }
        }

        // QC#6739 2016/04/26 Del start
//        for (int idx : mdlIdx) {
//            NMAL7060CommonLogic.searchCounters(bizMsg, glblMsg, getGlobalCompanyCode(), idx);
//            bizMsg.clearErrorInfo();
//            glblMsg.clearErrorInfo();
//        }
        // QC#6739 2016/04/26 Del end
        return true;
    }

    /**
     * checkBillMtrExist
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkBillMtrExist(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        List<String> bllgMtrNmList = new ArrayList<String>();
        List<Integer> bllgMtrIdx = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcMtrPkgBllgMtrPk_B1.getValue())) {
                continue;
            }

            bllgMtrNmList.add(bizMsg.B.no(i).mtrLbDescTxt_B1.getValue());
            bllgMtrIdx.add(Integer.valueOf(i));
        }
        String[] bllgMtrNms = (String[]) bllgMtrNmList.toArray(new String[bllgMtrNmList.size()]);

        if (bllgMtrNms.length == 0) {
            return true;
        }

        List<String> mdlNmList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // Add Start 2017/02/16 QC#17529
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcMtrPkgMdlPk_A1) //
                    || !ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdlNm_A1) //
                    || mdlNmList.contains(bizMsg.A.no(i).mdlNm_A1.getValue())) {
                continue;
            }
            // Add End 2017/02/16 QC#17529
            mdlNmList.add(bizMsg.A.no(i).mdlNm_A1.getValue());
        }
        String[] mdlNms = (String[]) mdlNmList.toArray(new String[mdlNmList.size()]);
        S21SsmEZDResult ssmResult = NMAL7060Query.getInstance().getBillingMeterName(bizMsg, mdlNms, bllgMtrNms);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int idx : bllgMtrIdx) {
                boolean matchFlg = false;
                for (int j = 0; j < resultList.size(); j++) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);
                    if (bizMsg.B.no(idx).mtrLbDescTxt_B1.getValue().equals((String) resultMap.get("MTR_LB_DESC_TXT"))) {
                        matchFlg = true;
                        break;
                    }
                }

                if (!matchFlg) {
                  glblMsg.B.no(idx).mtrLbDescTxt_B1.setErrorInfo(1, NMAM0073E, new String[]{BILLING_METER, OUTSIDE_EFF_DT});
                    return false;
                }
            }
        } else {
            for (int idx : bllgMtrIdx) {
                glblMsg.B.no(idx).mtrLbDescTxt_B1.setErrorInfo(1, NMAM0073E, new String[]{BILLING_METER, OUTSIDE_EFF_DT});
                return false;
            }
        }

        for (int idx : bllgMtrIdx) {
            NMAL7060CommonLogic.searchCounters(bizMsg, glblMsg, getGlobalCompanyCode(), idx);
            bizMsg.clearErrorInfo();
            glblMsg.clearErrorInfo();
        }
        return true;
    }

    // QC#15357 2016/10/26 Del start
//    /**
//     * checkBillMtrEqual
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private boolean checkBillMtrEqual(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
//
//        if (glblMsg.B.getValidCount() < 1) {
//            return true;
//        }
//
//        String checkBllgMtrLvl = glblMsg.B.no(0).bllgMtrLvlNum_B1.getValue();
//        List<Integer> errIdxList = new ArrayList<Integer>(glblMsg.B.getValidCount());
//        boolean EqFlg = true;
//
//        for (int i = 1; i < glblMsg.B.getValidCount(); i++) {
//            if (!checkBllgMtrLvl.equals(glblMsg.B.no(i).bllgMtrLvlNum_B1.getValue())) {
//                errIdxList.add(i);
//                EqFlg = false;
//            }
//        }
//
//        if (EqFlg) {
//            return true;
//        }
//
//         Integer[] errIdxs = errIdxList.toArray(new Integer[0]);
//         for (int errIdx : errIdxs) {
//             glblMsg.B.no(errIdx).bllgMtrLvlNum_B1.setErrorInfo(1, NMAM8341E);
//         }
//
//         NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
//         bizMsg.setMessageInfo(NMAM8341E);
//         return false;
//    }
    // QC#15357 2016/10/26 Del end

    /**
     * checkBillMtrDup
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkBillMtrDup(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {

        String[] chkAttrbNmList = new String[]{"mtrLbDescTxt_B1"};

        Integer[] errIdxList = NMAL7060CommonLogic.checkDupAttrb(glblMsg.B, chkAttrbNmList);
        if (errIdxList.length > 0) {
            for (int errIdx : errIdxList) {
                glblMsg.B.no(errIdx).mtrLbDescTxt_B1.setErrorInfo(1, NMAM0072E, new String[]{BILLING_METER});
            }
            NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
            bizMsg.setMessageInfo(NMAM0072E, new String[]{BILLING_METER});
            return false;
        }
        return true;
    }

    // QC#6739 2016/04/26 Add start
    // QC#15357 2016/10/26 Del start
//    /**
//     * checkSameLevelAllAssign
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private Boolean checkSameLevelAllAssign(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
//
//        S21SsmEZDResult ssmResult = NMAL7060Query.getInstance().getSameLevelBillingMeter(bizMsg);
//
//        boolean isErr = false;
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            for (int resultCnt = 0; resultCnt < resultList.size(); resultCnt++) {
//                boolean matchFlg = false;
//                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(resultCnt);
//                for (int bllgMtrCnt = 0; bllgMtrCnt < bizMsg.B.getValidCount(); bllgMtrCnt++) {
//                    if (ZYPCommonFunc.hasValue(bizMsg.B.no(bllgMtrCnt).mtrLbCd_B1)
//                            && bizMsg.B.no(bllgMtrCnt).mtrLbCd_B1.getValue().equals((String) resultMap.get("BLLG_MTR_LB_CD"))) {
//                        matchFlg = true;
//                        break;
//                    }
//                }
//
//                if (!matchFlg) {
//                    isErr = true;
//                }
//            }
//        }
//
//        if (isErr) {
//            NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
//            bizMsg.setMessageInfo(NMAM8483E);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * checkCommonCounterExist
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private Boolean checkCommonCounterExist(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
//        // QC#7912 2016/04/26 Add start
//        if (bizMsg.B.getValidCount() == 0) {
//            // If the billing counter has not been registered , check unnecessary
//            return true;
//        }
//
//        List<BigDecimal> mdlId = new ArrayList<BigDecimal>();
//        int mdlCnt = 0;
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).mtrGrpPk_A1)) {
//                mdlId.add(bizMsg.A.no(i).mdlId_A1.getValue());
//                mdlCnt++;
//            }
//        }
//
//        BigDecimal[] mdlIds = (BigDecimal[]) mdlId.toArray(new BigDecimal[mdlId.size()]);
//        // QC#7912 2016/04/26 Add end
//
//        S21SsmEZDResult ssmResult = NMAL7060Query.getInstance().getCommonCounter(bizMsg, mdlIds, mdlCnt);
//
//        if (ssmResult.isCodeNotFound()) {
//            NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
//            bizMsg.setMessageInfo(NMAM8485E);
//            return false;
//        }
//
//        return true;
//    }
    // QC#15357 2016/10/26 Del end
    // QC#6739 2016/04/26 Add end

    /**
     * checkDupModel
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkDupModel(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {

        String[] chkAttrbNmList = new String[]{
                "mdlNm_A1",
                "effFromDt_A1",
                "effThruDt_A1"};

        Integer[] errIdxList = NMAL7060CommonLogic.checkDupAttrb(glblMsg.A, chkAttrbNmList);
        if (errIdxList.length > 0) {
            for (int errIdx : errIdxList) {
                glblMsg.A.no(errIdx).effFromDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                glblMsg.A.no(errIdx).effThruDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
            }
            NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
            bizMsg.setMessageInfo(NMAM0072E, new String[]{MODEL});
            return false;
        }

        String[] grpAttrbNmList2 = new String[]{"mdlNm_A1"};

        Integer[] errIdxList2 = NMAL7060CommonLogic.checkDupTermByGrp(glblMsg.A, "effFromDt_A1", "effThruDt_A1", grpAttrbNmList2);
        if (errIdxList2.length > 0) {
            for (int errIdx : errIdxList2) {
                glblMsg.A.no(errIdx).effFromDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                glblMsg.A.no(errIdx).effThruDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
            }
            NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
            bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END});
            return false;
        }

        // Add Start 2017/02/14 QC#17529
        S21SsmEZDResult ssmResultForMdl = null;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL7060_ASMsg asMsg = glblMsg.A.no(i);
            ssmResultForMdl = NMAL7060Query.getInstance().getCheckPrcMtrPkgMdl( //
                    bizMsg, asMsg.mdlNm_A1.getValue(), asMsg.prcMtrPkgMdlPk_A1.getValue(), asMsg.effFromDt_A1.getValue(), asMsg.effThruDt_A1.getValue());

            if (ssmResultForMdl.isCodeNormal()) {
                glblMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                glblMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
                bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END});
                return false;
            }
        }
        // Add End 2017/02/14 QC#17529

        return true;
    }

    // QC#6738 2016/04/26 Del start
    /**
     * checkDetailItem
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
//    private Boolean checkDetailItem(NMAL7060SMsg glblMsg, NMAL7060CMsg bizMsg) {
//        boolean checkFlg = true;
//
//        int index = 0;
//        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.C.no(i).bllblFlg_C1.getValue())
//                    && !ZYPCommonFunc.hasValue(glblMsg.C.no(i).actvFlg_C1)) {
//                glblMsg.C.no(i).bllblFlg_C1.setErrorInfo(1, NMAM8299E, new String[] {BILLABLE_ACTIVE_Y, ZYPConstant.FLG_ON_Y, ACTIVE, ZYPConstant.FLG_ON_Y});
//                glblMsg.C.no(i).actvFlg_C1.setErrorInfo(1, NMAM8299E, new String[] {BILLABLE_ACTIVE_Y, ZYPConstant.FLG_ON_Y, ACTIVE, ZYPConstant.FLG_ON_Y});
//                bizMsg.setMessageInfo("ZZM9037E");
//                index = glblMsg.C.no(i).xxCellIdx_CB.getValueInt();
//                checkFlg = false;
//            }
//        }
//
//        if (!checkFlg) {
//            NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), index);
//        }
//
//        return checkFlg;
//    }
    // QC#6738 2016/04/26 Del end
}
