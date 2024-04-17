/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0210;

import static business.blap.NSBL0210.constant.NSBL0210Constant.*;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0210.common.NSBL0210CommonLogic;
import business.db.DS_MDL_GRPTMsg;
import business.db.SVC_LBOR_CHRGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Labor Charge Table Maintenance
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/23   Hitachi         Y.Takeno        Update          QC#8565
 * 2016/06/06   Hitachi         Y.Takeno        Update          QC#5489
 *</pre>
 */
public class NSBL0210BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0210Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_CMN_Submit((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0210Scrn00_CMN_Submit(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // data validation
        if (!NSBL0210CommonLogic.validateDetailLines(cMsg, sMsg)) {
            int errIndex = NSBL0210CommonLogic.getFirstErrorIndex(cMsg, sMsg);
            NSBL0210CommonLogic.pagenation(cMsg, sMsg, errIndex);
            return;
        }

        // delete SVC_TRVL_CHRG (Delete Row)
        for (int rowIndex = 0; rowIndex < sMsg.D.getValidCount(); rowIndex++) {
            // delete SVC_LBOR_CHRG
            if (!deleteSvcLborChrg(cMsg, sMsg, rowIndex)) {
                return;
            }
        }

        for (int rowIndex = 0; rowIndex < sMsg.A.getValidCount(); rowIndex++) {
            NSBL0210_ASMsg asMsg = sMsg.A.no(rowIndex);
            if (!ZYPCommonFunc.hasValue(asMsg.svcLborChrgPk)) {
                // insert SVC_LBOR_CHRG
                if (!insertSvcLborChrg(cMsg, sMsg, asMsg)) {
                    return;
                }

            } else {
                // update SVC_LBOR_CHRG
                if (!updateSvcLborChrg(cMsg, sMsg, asMsg)) {
                    return;
                }
            }
        }
    }

    private SVC_LBOR_CHRGTMsg findSvcLborChrgForUpdate(NSBL0210CMsg cMsg, BigDecimal svcLborChrgPk) {
        SVC_LBOR_CHRGTMsg tMsg = new SVC_LBOR_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborChrgPk, svcLborChrgPk);

        tMsg = (SVC_LBOR_CHRGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            cMsg.setMessageInfo(NSBM0163E, new String[] {TBL_NM_SVC_LBOR_CHRG });
            return null;
        }

        return tMsg;
    }

    private boolean insertSvcLborChrg(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg, NSBL0210_ASMsg asMsg) {
        SVC_LBOR_CHRGTMsg tMsg = new SVC_LBOR_CHRGTMsg();

        DS_MDL_GRPTMsg dsMdlGrp = NSBL0210CommonLogic.findDsMdlGrp(cMsg.glblCmpyCd.getValue(), asMsg.mdlGrpNm.getValue());
        // START 06/06/2016 [QC#5489, DEL]
        // SVC_PRC_SHIFTTMsg svcPrcShift = NSBL0210CommonLogic.findSvcPrcShift(cMsg.glblCmpyCd.getValue(), asMsg.svcPrcShiftNum.getValue());
        // END 06/06/2016 [QC#5489, DEL]

        // check-box -> flag
        if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.svcPrcTrvlChrgFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(asMsg.svcPrcTrvlChrgFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.svcPrcTrvlChrgFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborChrgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_LBOR_CHRG_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpNm, asMsg.mdlGrpNm);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMinChrgHrsAot, asMsg.svcMinChrgHrsAot);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborUnitHrsAot, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborUnitAmt, asMsg.svcLborUnitAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.svcChrgCcyCd, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(tMsg.intgMdseCd, asMsg.intgMdseCd);
        // START 06/06/2016 [QC#5489, MOD]
        // ZYPEZDItemValueSetter.setValue(tMsg.svcPrcShiftPk, svcPrcShift.svcPrcShiftPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrcShiftNum, asMsg.svcPrcShiftNum);
        // END 06/06/2016 [QC#5489, MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpId, dsMdlGrp.mdlGrpId);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, asMsg.svcLineBizCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrcTrvlChrgFlg, asMsg.svcPrcTrvlChrgFlg);

        // START 05/23/2016 [QC#8565, MOD]
        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0164E, new String[] {TBL_NM_SVC_LBOR_CHRG });
            return false;
        }
        // END   05/23/2016 [QC#8565, MOD]
        return true;
    }

    private boolean updateSvcLborChrg(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg, NSBL0210_ASMsg asMsg) {

        BigDecimal svcLborChrgPk = asMsg.svcLborChrgPk.getValue();
        SVC_LBOR_CHRGTMsg tMsg = findSvcLborChrgForUpdate(cMsg, svcLborChrgPk);
        if (tMsg == null) {
            cMsg.setMessageInfo(NSBM0163E, new String[] {TBL_NM_SVC_LBOR_CHRG });
            return false;
        }

        EZDSStringItem ezUpTime = (EZDSStringItem) asMsg.ezUpTime;
        EZDSStringItem ezUpTimeZone = (EZDSStringItem) asMsg.ezUpTimeZone;
        if (ezUpTime != null && ezUpTimeZone != null) {
            if (!ZYPDateUtil.isSameTimeStamp(ezUpTime.getValue(), ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return false;
            }
        }

        // check-box -> flag
        if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.svcPrcTrvlChrgFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(asMsg.svcPrcTrvlChrgFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.svcPrcTrvlChrgFlg, ZYPConstant.FLG_OFF_N);
        }

        // check record is modified
        if (matchLine(tMsg, sMsg, asMsg)) {
            return true;
        }

        DS_MDL_GRPTMsg dsMdlGrp = NSBL0210CommonLogic.findDsMdlGrp(cMsg.glblCmpyCd.getValue(), asMsg.mdlGrpNm.getValue());
        // START 06/06/2016 [QC#5489, DEL]
        // SVC_PRC_SHIFTTMsg svcPrcShift = NSBL0210CommonLogic.findSvcPrcShift(cMsg.glblCmpyCd.getValue(), asMsg.svcPrcShiftNum.getValue());
        // END 06/06/2016 [QC#5489, DEL]

        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpNm, asMsg.mdlGrpNm);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpId, dsMdlGrp.mdlGrpId);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, asMsg.svcLineBizCd);
        // START 06/06/2016 [QC#5489, MOD]
        // ZYPEZDItemValueSetter.setValue(tMsg.svcPrcShiftPk, svcPrcShift.svcPrcShiftPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrcShiftNum, asMsg.svcPrcShiftNum);
        // END 06/06/2016 [QC#5489, MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.svcMinChrgHrsAot, asMsg.svcMinChrgHrsAot);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborUnitAmt, asMsg.svcLborUnitAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.intgMdseCd, asMsg.intgMdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrcTrvlChrgFlg, asMsg.svcPrcTrvlChrgFlg);

        // START 05/23/2016 [QC#8565, MOD]
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0163E, new String[] {TBL_NM_SVC_LBOR_CHRG });
            return false;
        }
        // END   05/23/2016 [QC#8565, MOD]

        return true;
    }

    private boolean deleteSvcLborChrg(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg, int deleteRowIndex) {

        BigDecimal svcLborChrgPk = sMsg.D.no(deleteRowIndex).svcLborChrgPk.getValue();
        SVC_LBOR_CHRGTMsg tMsg = findSvcLborChrgForUpdate(cMsg, svcLborChrgPk);
        if (tMsg == null) {
            return false;
        }

        EZDSStringItem ezUpTime = (EZDSStringItem) sMsg.D.no(deleteRowIndex).ezUpTime;
        EZDSStringItem ezUpTimeZone = (EZDSStringItem) sMsg.D.no(deleteRowIndex).ezUpTimeZone;
        if (ezUpTime != null && ezUpTimeZone != null) {
            if (!ZYPDateUtil.isSameTimeStamp(ezUpTime.getValue(), ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return false;
            }
        }

        // START 05/23/2016 [QC#8565, MOD]
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0165E, new String[] {TBL_NM_SVC_LBOR_CHRG });
            return false;
        }
        // END   05/23/2016 [QC#8565, MOD]

        return true;
    }

    private boolean matchLine(SVC_LBOR_CHRGTMsg tMsg, NSBL0210SMsg sMsg, NSBL0210_ASMsg asMsg) {
        if (!isMatchObject(asMsg.mdlGrpId, tMsg.mdlGrpId)) {
            return false;
        }
        if (!isMatchObject(asMsg.mdlGrpNm, tMsg.mdlGrpNm)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcLineBizCd, tMsg.svcLineBizCd)) {
            return false;
        }
// START 06/06/2016 [QC#5489, MOD]
//        if (!isMatchObject(asMsg.svcPrcShiftPk, tMsg.svcPrcShiftPk)) {
//            return false;
//        }
        if (!isMatchObject(asMsg.svcPrcShiftNum, tMsg.svcPrcShiftNum)) {
            return false;
        }
// END 06/06/2016 [QC#5489, MOD]
        if (!isMatchObject(asMsg.svcMinChrgHrsAot, tMsg.svcMinChrgHrsAot)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcLborUnitAmt, tMsg.svcLborUnitAmt)) {
            return false;
        }
        if (!isMatchObject(asMsg.intgMdseCd, tMsg.intgMdseCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcPrcTrvlChrgFlg, tMsg.svcPrcTrvlChrgFlg)) {
            return false;
        }

        return true;
    }

    private static boolean isMatchObject(EZDSStringItem asMsgObj, EZDTStringItem rstMsgObj) {
        if (ZYPCommonFunc.hasValue(asMsgObj) && !ZYPCommonFunc.hasValue(rstMsgObj)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(asMsgObj) && ZYPCommonFunc.hasValue(rstMsgObj)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(asMsgObj) && ZYPCommonFunc.hasValue(rstMsgObj)) {
            if (!asMsgObj.getValue().equals(rstMsgObj.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMatchObject(EZDSBigDecimalItem asMsgObj, EZDTBigDecimalItem rstMsgObj) {
        if (ZYPCommonFunc.hasValue(asMsgObj) && !ZYPCommonFunc.hasValue(rstMsgObj)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(asMsgObj) && ZYPCommonFunc.hasValue(rstMsgObj)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(asMsgObj) && ZYPCommonFunc.hasValue(rstMsgObj)) {
            if (asMsgObj.getValue().compareTo(rstMsgObj.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }
}
