/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0240;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import static business.blap.NSBL0240.constant.NSBL0240Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSBL0240.common.NSBL0240CommonLogic;
import business.db.DS_MDL_GRPTMsg;
import business.db.SVC_TRVL_CHRGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Travel Charge Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/16   Hitachi         Y.Takeno        Update          QC#7746
 *</pre>
 */
public class NSBL0240BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0240Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_CMN_Submit((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0240Scrn00_CMN_Submit(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {

        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // data validation
        if (!NSBL0240CommonLogic.validateDetailLines(cMsg, sMsg)) {
            int errIndex = NSBL0240CommonLogic.getFirstErrorIndex(cMsg, sMsg);
            NSBL0240CommonLogic.pagenation(cMsg, sMsg, errIndex);
            return;
        }

        // delete SVC_TRVL_CHRG (Delete Row)
        for (int rowIndex = 0; rowIndex < sMsg.D.getValidCount(); rowIndex++) {
            if (!deleteSvcTrvlChrg(cMsg, sMsg, rowIndex)) {
                return;
            }
        }

        for (int rowIndex = 0; rowIndex < sMsg.A.getValidCount(); rowIndex++) {
            NSBL0240_ASMsg asMsg = sMsg.A.no(rowIndex);

            for (int zoneIndex = 0; zoneIndex < sMsg.C.getValidCount(); zoneIndex++) {
                if (isNewRecord(cMsg, asMsg, zoneIndex)) {
                    // insert SVC_TRVL_CHRG
                    if (!insertSvcTrvlChrg(cMsg, sMsg, asMsg, zoneIndex)) {
                        return;
                    }

                } else if (isUpdateRecord(cMsg, asMsg, zoneIndex)) {
                    // update SVC_TRVL_CHRG
                    if (!updateSvcTrvlChrg(cMsg, sMsg, asMsg, zoneIndex)) {
                        return;
                    }

                } else if (isDeleteRecord(cMsg, asMsg, zoneIndex)) {
                    // delete SVC_TRVL_CHRG (empty input)
                    if (!deleteSvcTrvlChrg(cMsg, sMsg, asMsg, zoneIndex)) {
                        return;
                    }
                }
            }
        }
    }

    private boolean isNewRecord(NSBL0240CMsg cMsg, NSBL0240_ASMsg asMsg, int zoneIndex) {
        EZDSBigDecimalItem svcTrvlChrgPk = (EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_PK, asMsg, zoneIndex);
        EZDSBigDecimalItem svcTrvlUnitAmt = (EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, zoneIndex);
        EZDSStringItem svcTrvlChrgTpCd = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, zoneIndex);

        if (!hasValue(svcTrvlChrgPk) && hasValue(svcTrvlUnitAmt) && hasValue(svcTrvlChrgTpCd)) {
            return true;
        }

        return false;
    }

    private boolean isUpdateRecord(NSBL0240CMsg cMsg, NSBL0240_ASMsg asMsg, int zoneIndex) {
        EZDSBigDecimalItem svcTrvlChrgPk = (EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_PK, asMsg, zoneIndex);
        EZDSBigDecimalItem svcTrvlUnitAmt = (EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, zoneIndex);
        EZDSStringItem svcTrvlChrgTpCd = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, zoneIndex);

        if (hasValue(svcTrvlChrgPk) && hasValue(svcTrvlUnitAmt) && hasValue(svcTrvlChrgTpCd)) {
            return true;
        }

        return false;
    }

    private boolean isDeleteRecord(NSBL0240CMsg cMsg, NSBL0240_ASMsg asMsg, int zoneIndex) {
        EZDSBigDecimalItem svcTrvlChrgPk = (EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_PK, asMsg, zoneIndex);
        EZDSBigDecimalItem svcTrvlUnitAmt = (EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, zoneIndex);
        EZDSStringItem svcTrvlChrgTpCd = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, zoneIndex);

        if (hasValue(svcTrvlChrgPk) && !hasValue(svcTrvlUnitAmt) && !hasValue(svcTrvlChrgTpCd)) {
            return true;
        }

        return false;
    }

    private SVC_TRVL_CHRGTMsg findSvcTrvlChrgForUpdate(NSBL0240CMsg cMsg, BigDecimal svcTrvlChrgPk) {
        SVC_TRVL_CHRGTMsg tMsg = new SVC_TRVL_CHRGTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcTrvlChrgPk, svcTrvlChrgPk);

        tMsg = (SVC_TRVL_CHRGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_SVC_TRVL_CHRG });
            return null;
        }

        return tMsg;
    }

    private boolean insertSvcTrvlChrg(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, NSBL0240_ASMsg asMsg, int zoneIndex) {
        SVC_TRVL_CHRGTMsg tMsg = new SVC_TRVL_CHRGTMsg();

        DS_MDL_GRPTMsg dsMdlGrp = NSBL0240CommonLogic.findDsMdlGrp(cMsg.glblCmpyCd.getValue(), asMsg.mdlGrpNm.getValue());

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcTrvlChrgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TRVL_CHRG_SQ));
        setValue(tMsg.svcMinChrgHrsAot, SVC_MIN_CHRG_HRS_AOT);
        // START 2016/05/16 [QC#7746, MOD]
        setValue(tMsg.svcChrgCcyCd, CCY.US_DOLLAR);
        // END   2016/05/16 [QC#7746, MOD]
        setValue(tMsg.intgMdseCd, asMsg.intgMdseCd);
        setValue(tMsg.svcLineBizCd, asMsg.svcLineBizCd);
        setValue(tMsg.svcZnCd, sMsg.C.no(zoneIndex).svcZnCd);
        setValue(tMsg.mdlGrpId, dsMdlGrp.mdlGrpId);

        setValue(tMsg.intgMdseCd, asMsg.intgMdseCd);
        setValue(tMsg.svcTrvlUnitAmt, ((EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, zoneIndex)).getValue());
        setValue(tMsg.svcTrvlChrgTpCd, ((EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, zoneIndex)).getValue());

        EZDTBLAccessor.insert(tMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL != tMsg.getReturnCode()) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_NM_SVC_TRVL_CHRG });
            return false;
        }
        return true;
    }

    private boolean updateSvcTrvlChrg(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, NSBL0240_ASMsg asMsg, int zoneIndex) {

        BigDecimal svcTrvlChrgPk = ((EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_PK, asMsg, zoneIndex)).getValue();
        SVC_TRVL_CHRGTMsg tMsg = findSvcTrvlChrgForUpdate(cMsg, svcTrvlChrgPk);
        if (tMsg == null) {
            return false;
        }

        EZDSStringItem ezUpTime = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_EZUPTIME, asMsg, zoneIndex);
        EZDSStringItem ezUpTimeZone = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_EZUPTIMEZONE, asMsg, zoneIndex);
        if (ezUpTime != null && ezUpTimeZone != null) {
            if (!ZYPDateUtil.isSameTimeStamp(ezUpTime.getValue(), ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return false;
            }
        }

        // check record is modified
        if (matchLine(tMsg, sMsg, asMsg, zoneIndex)) {
            return true;
        }

        setValue(tMsg.mdlGrpId, asMsg.mdlGrpId);
        setValue(tMsg.svcLineBizCd, asMsg.svcLineBizCd);
        setValue(tMsg.intgMdseCd, asMsg.intgMdseCd);
        setValue(tMsg.svcZnCd, sMsg.C.no(zoneIndex).svcZnCd);
        setValue(tMsg.svcTrvlUnitAmt, ((EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, zoneIndex)).getValue());
        setValue(tMsg.svcTrvlChrgTpCd, ((EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, zoneIndex)).getValue());

        EZDTBLAccessor.update(tMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL != tMsg.getReturnCode()) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"Service Travel Charge" });
            return false;
        }

        return true;
    }

    private boolean deleteSvcTrvlChrg(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, int deleteRowIndex) {

        BigDecimal svcTrvlChrgPk = sMsg.D.no(deleteRowIndex).svcTrvlChrgPk.getValue();
        SVC_TRVL_CHRGTMsg tMsg = findSvcTrvlChrgForUpdate(cMsg, svcTrvlChrgPk);
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

        EZDTBLAccessor.logicalRemove(tMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL != tMsg.getReturnCode()) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {"Service Travel Charge" });
            return false;
        }

        return true;
    }

    private boolean deleteSvcTrvlChrg(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, NSBL0240_ASMsg asMsg, int zoneIndex) {
        BigDecimal svcTrvlChrgPk = ((EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_PK, asMsg, zoneIndex)).getValue();
        SVC_TRVL_CHRGTMsg tMsg = findSvcTrvlChrgForUpdate(cMsg, svcTrvlChrgPk);
        if (tMsg == null) {
            return false;
        }

        EZDSStringItem ezUpTime = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_EZUPTIME, asMsg, zoneIndex);
        EZDSStringItem ezUpTimeZone = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_EZUPTIMEZONE, asMsg, zoneIndex);
        if (ezUpTime != null && ezUpTimeZone != null) {
            if (!ZYPDateUtil.isSameTimeStamp(ezUpTime.getValue(), ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return false;
            }
        }

        EZDTBLAccessor.logicalRemove(tMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL != tMsg.getReturnCode()) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {"Service Travel Charge" });
            return false;
        }

        return true;
    }

    private boolean matchLine(SVC_TRVL_CHRGTMsg tMsg, NSBL0240SMsg sMsg, NSBL0240_ASMsg asMsg, int zoneIndex) {
        if (!isMatchObject(asMsg.mdlGrpId, tMsg.mdlGrpId)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcLineBizCd, tMsg.svcLineBizCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.intgMdseCd, tMsg.intgMdseCd)) {
            return false;
        }
        if (!isMatchObject(sMsg.C.no(zoneIndex).svcZnCd, tMsg.svcZnCd)) {
            return false;
        }
        if (!isMatchObject((EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, zoneIndex), tMsg.svcTrvlUnitAmt)) {
            return false;
        }
        if (!isMatchObject((EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, zoneIndex), tMsg.svcTrvlChrgTpCd)) {
            return false;
        }

        return true;
    }

    private static boolean isMatchObject(EZDSStringItem asMsgObj, EZDTStringItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (!asMsgObj.getValue().equals(rstMsgObj.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMatchObject(EZDSBigDecimalItem asMsgObj, EZDTBigDecimalItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (asMsgObj.getValue().compareTo(rstMsgObj.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }
}
