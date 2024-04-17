/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1200;

import static business.blap.NSAL1200.constant.NSAL1200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL1200.common.NSAL1200CommonLogic;
import business.db.BLLG_MTR_MAPTMsg;
import business.db.DS_MDL_MTRTMsg;
import business.db.MTR_GRPTMsg;
import business.db.MTR_LBTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/26   Hitachi         Y.Takeno        Update          QC#6700
 * 2016/07/15   Hitachi         T.Tomita        Update          QC#11811
 * 2016/12/08   Hitachi         A.Kohinata      Update          QC#16418
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193,18195
 * 2017/09/04   Hitachi         T.Kanasaka      Update          QC#15134
 * 2018/12/05   Hitachi         K.Morita        Update          QC#28644
 *</pre>
 */
public class NSAL1200BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1200Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_CMN_Submit((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1200Scrn00_CMN_Submit(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {

        NSAL1200CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        if (!NSAL1200CommonLogic.validateHeader(cMsg, sMsg)) {
            return;
        }

        if (!NSAL1200CommonLogic.validateDetailLines(cMsg, sMsg)) {
            int errIndex = NSAL1200CommonLogic.getFirstErrorIndex(cMsg, sMsg);
            NSAL1200CommonLogic.pagenation(cMsg, sMsg, errIndex);
            return;
        }

        if (!saveHeader(cMsg, sMsg)) {
            return;
        }

        if (!saveDetailLines(cMsg, sMsg)) {
            return;
        }

        // START 2016/07/15 T.Tomita [QC#11811, ADD]
        if (!deleteDetailLines(cMsg, sMsg)) {
            return;
        }
        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        // END 2016/07/15 T.Tomita [QC#11811, ADD]

        setValue(cMsg.mtrGrpPk_H, sMsg.mtrGrpPk_H);

        cMsg.setMessageInfo(NZZM0002I);
    }

    private boolean saveHeader(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        if (!hasValue(sMsg.mtrGrpPk_H)) {
            return insertMtrGrp(cMsg, sMsg);
        } else {
            return updateMtrGrp(cMsg, sMsg);
        }
    }

    private boolean saveDetailLines(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isNewRecord(sMsg.A.no(i))) {
                if (!insertDsMdlMtr(cMsg, sMsg, i)) {
                    return false;
                }

                // START 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
                if (!insertBllgMtrMap(cMsg, sMsg, i)) {
                    return false;
                }
                // END 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
            }

            if (isUpdateRecord(sMsg.A.no(i))) {
                if (!updateDsMdlMtr(cMsg, sMsg, i)) {
                    return false;
                }

                // START 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
                if (!updateBllgMtrMap(cMsg, sMsg, i)) {
                    return false;
                }
                // END 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
            }
        }

        return true;
    }

    // START 2016/07/15 T.Tomita [QC#11811, ADD]
    private boolean deleteDetailLines(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!deleteDsMdlMtr(cMsg, sMsg, i)) {
                return false;
            }

            // START 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
            if (!deleteBllgMtrMap(cMsg, sMsg, i)) {
                return false;
            }
            // END 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
        }
        ZYPTableUtil.clear(sMsg.B);

        return true;
    }
    // END 2016/07/15 T.Tomita [QC#11811, ADD]

    private boolean isNewRecord(NSAL1200_ASMsg asMsg) {
        if (!hasValue(asMsg.dsMdlMtrPk_A)) {
            return true;
        }
        return false;
    }

    private boolean isUpdateRecord(NSAL1200_ASMsg asMsg) {
        if (hasValue(asMsg.dsMdlMtrPk_A) && ZYPConstant.FLG_ON_Y.equals(asMsg.xxValUpdFlg_A.getValue())) {
            return true;
        }
        return false;
    }

    private String getMtrLbNoteTxt(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg tMsg = new MTR_LBTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.mtrLbCd, mtrLbCd);
        tMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.mtrLbDescTxt.getValue();
        }
        return null;
    }

    private boolean insertMtrGrp(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        MTR_GRPTMsg mtrGrp = new MTR_GRPTMsg();
        setValue(mtrGrp.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(mtrGrp.mtrGrpPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.MTR_GRP_SQ));
        setValue(mtrGrp.mtrGrpNm, sMsg.mtrGrpNm_H);
        setValue(mtrGrp.mtrGrpDescTxt, sMsg.mtrGrpDescTxt_H);
        setValue(mtrGrp.mtrGrpTpCd, sMsg.mtrGrpTpCd_H);
        setValue(mtrGrp.colorMtrAutoDrvFlg, ZYPConstant.FLG_OFF_N);
        // add start 2016/12/08 QC#16418
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.prcVldFlg_H.getValue())) {
            setValue(mtrGrp.prcVldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(mtrGrp.prcVldFlg, ZYPConstant.FLG_OFF_N);
        }
        // add end 2016/12/08 QC#16418

        EZDTBLAccessor.insert(mtrGrp);
        if (mtrGrp.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_NM_DS_MDL_MTR });
            return false;
        }

        setValue(sMsg.mtrGrpPk_H, mtrGrp.mtrGrpPk);

        return true;
    }

    private boolean updateMtrGrp(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        MTR_GRPTMsg mtrGrp = new MTR_GRPTMsg();
        setValue(mtrGrp.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(mtrGrp.mtrGrpPk, sMsg.mtrGrpPk_H);

        mtrGrp = (MTR_GRPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(mtrGrp);
        // START 2016/04/26 [QC#6700, MOD]
        if (mtrGrp == null) {
        // END   2016/04/26 [QC#6700, MOD]
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_MTR_GRP });
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(sMsg.ezUpTime_H.getValue(), sMsg.ezUpTimeZone_H.getValue(), mtrGrp.ezUpTime.getValue(), mtrGrp.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }

        setValue(mtrGrp.mtrGrpNm, sMsg.mtrGrpNm_H);
        setValue(mtrGrp.mtrGrpDescTxt, sMsg.mtrGrpDescTxt_H);
        setValue(mtrGrp.mtrGrpTpCd, sMsg.mtrGrpTpCd_H);
        // add start 2016/12/08 QC#16418
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.prcVldFlg_H.getValue())) {
            setValue(mtrGrp.prcVldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(mtrGrp.prcVldFlg, ZYPConstant.FLG_OFF_N);
        }
        // add end 2016/12/08 QC#16418

        EZDTBLAccessor.update(mtrGrp);
        if (mtrGrp.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_DS_MDL_MTR });
            return false;
        }

        return true;
    }

    private boolean insertDsMdlMtr(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i) {
        DS_MDL_MTRTMsg dsMdlMtr = new DS_MDL_MTRTMsg();
        setValue(dsMdlMtr.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(dsMdlMtr.dsMdlMtrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_MDL_MTR_SQ));
        setValue(dsMdlMtr.svcMtrAvalFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsMdlMtr.bllgMtrAvalFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsMdlMtr.mdlMtrLbCd, sMsg.A.no(i).mdlMtrLbCd_A);
        setValue(dsMdlMtr.mdlMtrLbNoteTxt, getMtrLbNoteTxt(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).mdlMtrLbCd_A.getValue()));
        // START 2017/08/03 T.Kanasaka [QC#18193,18195,MOD]
//        setValue(dsMdlMtr.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
        setValue(dsMdlMtr.bllgMtrLbCd, getbllgMtrLbCd(sMsg.A.no(i)));
        // END 2017/08/03 T.Kanasaka [QC#18193,18195,MOD]
        setValue(dsMdlMtr.mtrGrpPk, sMsg.mtrGrpPk_H);
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).mtrEntryMndFlg_A.getValue())) {
            setValue(dsMdlMtr.mtrEntryMndFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(dsMdlMtr.mtrEntryMndFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(dsMdlMtr.mtrMultRate, sMsg.A.no(i).mtrMultRate_A);
        // START 2017/08/03 T.Kanasaka [QC#18193,18195,DEL]
//        setValue(dsMdlMtr.fleetMtrLbCd, sMsg.A.no(i).fleetMtrLbCd_A);
//        setValue(dsMdlMtr.aggrMtrLbCd, sMsg.A.no(i).aggrMtrLbCd_A);
        // END 2017/08/03 T.Kanasaka [QC#18193,18195,DEL]
        // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
        setValue(dsMdlMtr.cntrDigitNum, sMsg.A.no(i).cntrDigitNum_A);
        // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
        
        // START 2018/12/05 [QC#,28644,ADD]
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).techReadMndFlg_A.getValue())) {
            setValue(dsMdlMtr.techReadMndFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(dsMdlMtr.techReadMndFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2018/12/05 [QC#28644,ADD]
        
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).actvFlg_A.getValue())) {
            setValue(dsMdlMtr.actvFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(dsMdlMtr.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(dsMdlMtr.effFromDt, sMsg.A.no(i).effFromDt_A);
        setValue(dsMdlMtr.effThruDt, sMsg.A.no(i).effThruDt_A);
        EZDTBLAccessor.insert(dsMdlMtr);
        if (dsMdlMtr.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_NM_DS_MDL_MTR });
            return false;
        }

        return true;
    }

    private boolean updateDsMdlMtr(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i) {

        DS_MDL_MTRTMsg dsMdlMtr = new DS_MDL_MTRTMsg();
        setValue(dsMdlMtr.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(dsMdlMtr.dsMdlMtrPk, sMsg.A.no(i).dsMdlMtrPk_A);

        dsMdlMtr = (DS_MDL_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsMdlMtr);
        // START 2016/04/26 [QC#6700, MOD]
        if (dsMdlMtr == null) {
        // END   2016/04/26 [QC#6700, MOD]
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_DS_MDL_MTR });
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A.getValue(), sMsg.A.no(i).ezUpTimeZone_A.getValue(), dsMdlMtr.ezUpTime.getValue(), dsMdlMtr.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }

        setValue(dsMdlMtr.mdlMtrLbCd, sMsg.A.no(i).mdlMtrLbCd_A);
        setValue(dsMdlMtr.mdlMtrLbNoteTxt, getMtrLbNoteTxt(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).mdlMtrLbCd_A.getValue()));
        // START 2017/08/03 T.Kanasaka [QC#18193,18195,MOD]
//        setValue(dsMdlMtr.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
        setValue(dsMdlMtr.bllgMtrLbCd, getbllgMtrLbCd(sMsg.A.no(i)));
        // END 2017/08/03 T.Kanasaka [QC#18193,18195,MOD]
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).mtrEntryMndFlg_A.getValue())) {
            setValue(dsMdlMtr.mtrEntryMndFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(dsMdlMtr.mtrEntryMndFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(dsMdlMtr.mtrMultRate, sMsg.A.no(i).mtrMultRate_A);
        // START 2017/08/03 T.Kanasaka [QC#18193,18195,DEL]
//        setValue(dsMdlMtr.fleetMtrLbCd, sMsg.A.no(i).fleetMtrLbCd_A);
//        setValue(dsMdlMtr.aggrMtrLbCd, sMsg.A.no(i).aggrMtrLbCd_A);
        // END 2017/08/03 T.Kanasaka [QC#18193,18195,DEL]
        // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
        setValue(dsMdlMtr.cntrDigitNum, sMsg.A.no(i).cntrDigitNum_A);
        // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
        
        // START 2018/12/05 [QC#,28644,ADD]
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).techReadMndFlg_A.getValue())) {
            setValue(dsMdlMtr.techReadMndFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(dsMdlMtr.techReadMndFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2018/12/05 [QC#28644,ADD]
        
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).actvFlg_A.getValue())) {
            setValue(dsMdlMtr.actvFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(dsMdlMtr.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(dsMdlMtr.effFromDt, sMsg.A.no(i).effFromDt_A);
        setValue(dsMdlMtr.effThruDt, sMsg.A.no(i).effThruDt_A);
        EZDTBLAccessor.update(dsMdlMtr);
        if (dsMdlMtr.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_DS_MDL_MTR });
            return false;
        }

        return true;
    }

    // START 2016/07/15 T.Tomita [QC#11811, ADD]
    private boolean deleteDsMdlMtr(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i) {

        DS_MDL_MTRTMsg dsMdlMtr = new DS_MDL_MTRTMsg();
        setValue(dsMdlMtr.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(dsMdlMtr.dsMdlMtrPk, sMsg.B.no(i).dsMdlMtrPk_B);

        dsMdlMtr = (DS_MDL_MTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsMdlMtr);
        if (dsMdlMtr == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.B.no(i).ezUpTime_B.getValue(), sMsg.B.no(i).ezUpTimeZone_B.getValue(), dsMdlMtr.ezUpTime.getValue(), dsMdlMtr.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }

        EZDTBLAccessor.logicalRemove(dsMdlMtr);
        if (dsMdlMtr.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {TBL_NM_DS_MDL_MTR });
            return false;
        }

        return true;
    }
    // END 2016/07/15 T.Tomita [QC#11811, ADD]

    // START 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
    private String getbllgMtrLbCd(NSAL1200_ASMsg asMsg) {
        if (hasValue(asMsg.bllgMtrLbCd_L3)) {
            return asMsg.bllgMtrLbCd_L3.getValue();
        } else if (hasValue(asMsg.bllgMtrLbCd_L2)) {
            return asMsg.bllgMtrLbCd_L2.getValue();
        } else {
            return asMsg.bllgMtrLbCd_L1.getValue();
        }
    }

    private boolean insertBllgMtrMap(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i) {

        // Billing Counter LVL1
        if (hasValue(sMsg.A.no(i).bllgMtrLbCd_L1)) {
            if (!insertBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrLbCd_L1.getValue(), BLLG_MTR_MAP_LVL_NUM_1)) {
                return false;
            }
        }

        // Billing Counter LVL2
        if (hasValue(sMsg.A.no(i).bllgMtrLbCd_L2)) {
            if (!insertBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrLbCd_L2.getValue(), BLLG_MTR_MAP_LVL_NUM_2)) {
                return false;
            }
        }

        // Billing Counter LVL3
        if (hasValue(sMsg.A.no(i).bllgMtrLbCd_L3)) {
            if (!insertBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrLbCd_L3.getValue(), BLLG_MTR_MAP_LVL_NUM_3)) {
                return false;
            }
        }

        return true;
    }

    private boolean insertBllgMtrMap(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i, String bllgMtrLbCd, String bllgMtrMapLvlNum) {
        BLLG_MTR_MAPTMsg bllgMtrMap = new BLLG_MTR_MAPTMsg();
        setValue(bllgMtrMap.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(bllgMtrMap.bllgMtrMapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.BLLG_MTR_MAP_SQ));
        setValue(bllgMtrMap.bllgMtrLbCd, bllgMtrLbCd);
        setValue(bllgMtrMap.bllgMtrMapLvlNum, bllgMtrMapLvlNum);
        
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).actvFlg_A.getValue())) {
            setValue(bllgMtrMap.actvFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(bllgMtrMap.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(bllgMtrMap.effFromDt, sMsg.A.no(i).effFromDt_A);
        setValue(bllgMtrMap.effThruDt, sMsg.A.no(i).effThruDt_A);
        setValue(bllgMtrMap.mdlMtrLbCd, sMsg.A.no(i).mdlMtrLbCd_A);
        setValue(bllgMtrMap.mtrGrpPk, sMsg.mtrGrpPk_H);
        EZDTBLAccessor.insert(bllgMtrMap);
        if (bllgMtrMap.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_NM_BLLG_MTR_MAP });
            return false;
        }

        return true;
    }

    private boolean updateBllgMtrMap(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i) {

        // Billing Counter LVL1
        if (!hasValue(sMsg.A.no(i).bllgMtrMapPk_L1) && (hasValue(sMsg.A.no(i).mtrLbDescTxt_L1))) {
            // insert
            if (!insertBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrLbCd_L1.getValue(), BLLG_MTR_MAP_LVL_NUM_1)) {
                return false;
            }
        } else if (hasValue(sMsg.A.no(i).bllgMtrMapPk_L1) && (!hasValue(sMsg.A.no(i).mtrLbDescTxt_L1))) {
            // delete
            if (!deleteBllgMtrMap(cMsg, sMsg.A.no(i).bllgMtrMapPk_L1.getValue(), sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue())) {
                return false;
            }
        } else if (hasValue(sMsg.A.no(i).bllgMtrMapPk_L1) && (hasValue(sMsg.A.no(i).mtrLbDescTxt_L1))) {
            // update
            if (!updateBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrMapPk_L1.getValue(), sMsg.A.no(i).bllgMtrLbCd_L1.getValue(), BLLG_MTR_MAP_LVL_NUM_1, sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue())) {
                return false;
            }
        }

        // Billing Counter LVL2
        if (!hasValue(sMsg.A.no(i).bllgMtrMapPk_L2) && (hasValue(sMsg.A.no(i).mtrLbDescTxt_L2))) {
            // insert
            if (!insertBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrLbCd_L2.getValue(), BLLG_MTR_MAP_LVL_NUM_2)) {
                return false;
            }
        } else if (hasValue(sMsg.A.no(i).bllgMtrMapPk_L2) && (!hasValue(sMsg.A.no(i).mtrLbDescTxt_L2))) {
            // delete
            if (!deleteBllgMtrMap(cMsg, sMsg.A.no(i).bllgMtrMapPk_L2.getValue(), sMsg.A.no(i).ezUpTime_A2.getValue(), sMsg.A.no(i).ezUpTimeZone_A2.getValue())) {
                return false;
            }
        } else if (hasValue(sMsg.A.no(i).bllgMtrMapPk_L2) && (hasValue(sMsg.A.no(i).mtrLbDescTxt_L2))) {
            // update
            if (!updateBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrMapPk_L2.getValue(), sMsg.A.no(i).bllgMtrLbCd_L2.getValue(), BLLG_MTR_MAP_LVL_NUM_2, sMsg.A.no(i).ezUpTime_A2.getValue(), sMsg.A.no(i).ezUpTimeZone_A2.getValue())) {
                return false;
            }
        }

        // Billing Counter LVL3
        if (!hasValue(sMsg.A.no(i).bllgMtrMapPk_L3) && (hasValue(sMsg.A.no(i).mtrLbDescTxt_L3))) {
            // insert
            if (!insertBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrLbCd_L3.getValue(), BLLG_MTR_MAP_LVL_NUM_3)) {
                return false;
            }
        } else if (hasValue(sMsg.A.no(i).bllgMtrMapPk_L3) && (!hasValue(sMsg.A.no(i).mtrLbDescTxt_L3))) {
            // delete
            if (!deleteBllgMtrMap(cMsg, sMsg.A.no(i).bllgMtrMapPk_L3.getValue(), sMsg.A.no(i).ezUpTime_A3.getValue(), sMsg.A.no(i).ezUpTimeZone_A3.getValue())) {
                return false;
            }
        } else if (hasValue(sMsg.A.no(i).bllgMtrMapPk_L3) && (hasValue(sMsg.A.no(i).mtrLbDescTxt_L3))) {
            // update
            if (!updateBllgMtrMap(cMsg, sMsg, i, sMsg.A.no(i).bllgMtrMapPk_L3.getValue(), sMsg.A.no(i).bllgMtrLbCd_L3.getValue(), BLLG_MTR_MAP_LVL_NUM_3, sMsg.A.no(i).ezUpTime_A3.getValue(), sMsg.A.no(i).ezUpTimeZone_A3.getValue())) {
                return false;
            }
        }

        return true;
    }

    private boolean updateBllgMtrMap(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i, BigDecimal bllgMtrMapPk, String bllgMtrLbCd, String bllgMtrMapLvlNum, String ezUpTime, String ezUpTimeZone) {
        BLLG_MTR_MAPTMsg bllgMtrMap = new BLLG_MTR_MAPTMsg();
        setValue(bllgMtrMap.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(bllgMtrMap.bllgMtrMapPk, bllgMtrMapPk);

        bllgMtrMap = (BLLG_MTR_MAPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(bllgMtrMap);
        if (bllgMtrMap == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(ezUpTime, ezUpTimeZone, bllgMtrMap.ezUpTime.getValue(), bllgMtrMap.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }

        setValue(bllgMtrMap.bllgMtrLbCd, bllgMtrLbCd);
        setValue(bllgMtrMap.bllgMtrMapLvlNum, bllgMtrMapLvlNum);
        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).actvFlg_A.getValue())) {
            setValue(bllgMtrMap.actvFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(bllgMtrMap.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(bllgMtrMap.effFromDt, sMsg.A.no(i).effFromDt_A);
        setValue(bllgMtrMap.effThruDt, sMsg.A.no(i).effThruDt_A);
        setValue(bllgMtrMap.mdlMtrLbCd, sMsg.A.no(i).mdlMtrLbCd_A);
        setValue(bllgMtrMap.mtrGrpPk, sMsg.mtrGrpPk_H);
        EZDTBLAccessor.update(bllgMtrMap);
        if (bllgMtrMap.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_BLLG_MTR_MAP });
            return false;
        }

        return true;
    }
    
    private boolean deleteBllgMtrMap(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int i) {

        // Billing Counter LVL1
        if (hasValue(sMsg.B.no(i).bllgMtrMapPk_B1)) {
            if (!deleteBllgMtrMap(cMsg, sMsg.B.no(i).bllgMtrMapPk_B1.getValue(), sMsg.B.no(i).ezUpTime_B1.getValue(), sMsg.B.no(i).ezUpTimeZone_B1.getValue())) {
                return false;
            }
        }

        // Billing Counter LVL2
        if (hasValue(sMsg.B.no(i).bllgMtrMapPk_B2)) {
            if (!deleteBllgMtrMap(cMsg, sMsg.B.no(i).bllgMtrMapPk_B2.getValue(), sMsg.B.no(i).ezUpTime_B2.getValue(), sMsg.B.no(i).ezUpTimeZone_B2.getValue())) {
                return false;
            }
        }

        // Billing Counter LVL3
        if (hasValue(sMsg.B.no(i).bllgMtrMapPk_B3)) {
            if (!deleteBllgMtrMap(cMsg, sMsg.B.no(i).bllgMtrMapPk_B3.getValue(), sMsg.B.no(i).ezUpTime_B3.getValue(), sMsg.B.no(i).ezUpTimeZone_B3.getValue())) {
                return false;
            }
        }

        return true;
    }

    private boolean deleteBllgMtrMap(NSAL1200CMsg cMsg, BigDecimal bllgMtrMapPk, String ezUpTime, String ezUpTimeZone) {

        BLLG_MTR_MAPTMsg bllgMtrMap = new BLLG_MTR_MAPTMsg();
        setValue(bllgMtrMap.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(bllgMtrMap.bllgMtrMapPk, bllgMtrMapPk);

        bllgMtrMap = (BLLG_MTR_MAPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(bllgMtrMap);
        if (bllgMtrMap == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(ezUpTime, ezUpTimeZone, bllgMtrMap.ezUpTime.getValue(), bllgMtrMap.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }

        EZDTBLAccessor.logicalRemove(bllgMtrMap);
        if (bllgMtrMap.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {TBL_NM_BLLG_MTR_MAP });
            return false;
        }

        return true;
    }
    // END 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
}
