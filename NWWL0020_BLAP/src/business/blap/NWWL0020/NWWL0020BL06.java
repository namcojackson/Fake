/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0020;

import static business.blap.NWWL0020.constant.NWWL0020Constant.BLANK;
import static business.blap.NWWL0020.constant.NWWL0020Constant.CHK_DAY_PATTERNM;
//import static business.blap.NWWL0020.constant.NWWL0020Constant.CHK_EMAIL_PATTERN;
import static business.blap.NWWL0020.constant.NWWL0020Constant.CHK_MIN_PATTERNM;
import static business.blap.NWWL0020.constant.NWWL0020Constant.CHK_PLACE_HOLDER_PATTERNM;
import static business.blap.NWWL0020.constant.NWWL0020Constant.COMMA;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0004E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0005E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0006E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0007E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0008E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0011E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0012E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0015E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0019E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0020E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0021E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0022E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0023E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0032E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0033E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0034E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0035E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TAB_NAME_HEADER;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TAB_NAME_SQL;
import static business.blap.NWWL0020.constant.NWWL0020Constant.ZZZM9004E;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWWL0020.common.NWWL0020CommonLogic;
import business.db.NTFY_ACT_DTLTMsg;
import business.db.NTFY_ACT_DTL_COLTMsg;
import business.db.NTFY_ACT_DTL_COLTMsgArray;
import business.db.NTFY_DIST_LISTTMsg;
import business.db.NTFY_DIST_LISTTMsgArray;
import business.db.NTFY_DIST_RELNTMsg;
import business.db.NTFY_DIST_RELNTMsgArray;
import business.db.NTFY_HDRTMsg;
import business.db.NTFY_RUN_JOBTMsg;

import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWWL0020BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 *</pre>
 */
public class NWWL0020BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0020CMsg bizMsg = (NWWL0020CMsg) cMsg;
            NWWL0020SMsg glblMsg = (NWWL0020SMsg) sMsg;

            if ("NWWL0020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWWL0020Scrn00_CMN_Submit(bizMsg, glblMsg);

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
    private void doProcess_NWWL0020Scrn00_CMN_Submit(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        // Update the selected TAB
        if (TAB_NAME_HEADER.equals(bizMsg.xxDplyTab.getValue())) {

            if (!isChangeDataHeaderTab(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(NWWM0033E, new String[] {"Header Tab" });
                return;
            }

            if (!checkHeaderTab(bizMsg)) {
                return;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.ntfyHdrPk_H0)) {
                updateHeaderTab(bizMsg);
            } else {
                insertHeaderTab(bizMsg);
            }

        } else if (TAB_NAME_SQL.equals(bizMsg.xxDplyTab.getValue())) {

            if (!isChangeDataSqlTab(bizMsg)) {
                bizMsg.setMessageInfo(NWWM0033E, new String[] {"SQL Tab" });
                return;
            }

            if (!checkSqlTab(bizMsg, glblMsg)) {
                return;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.ntfyHdrPk_H0)) {
                updateSqlTab(bizMsg);
            } else {
                bizMsg.setMessageInfo(NWWM0023E);
                return;
            }

        } else {

            if (ZYPCommonFunc.hasValue(bizMsg.ntfyHdrPk_H0)) {
                if (!isChangeDataActionDetailTab(bizMsg, glblMsg)) {
                    bizMsg.setMessageInfo(NWWM0033E, new String[] {"Action Detail Tab" });
                    return;
                }

                if (!checkActionDetailTab(bizMsg)) {
                    return;
                }

                deleteActionDetail(bizMsg, glblMsg);

                if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDtlProtFlg.getValue())) {
                    if (ZYPCommonFunc.hasValue(bizMsg.ntfyActDtlPk)) {
                        updateActionDetailTab(bizMsg, glblMsg);
                    } else {
                        insertActionDetailTab(bizMsg, glblMsg);
                    }
                }
            } else {
                bizMsg.setMessageInfo(NWWM0023E);
                return;
            }
        }
    }

    /**
     * Is Change Data Header TAB
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @return boolean
     */
    private boolean isChangeDataHeaderTab(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (S21StringUtil.isEquals(bizMsg.ntfyHdrNm_H0.getValue(), glblMsg.ntfyHdrNm_H0.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyHdrId_H0.getValue(), glblMsg.ntfyHdrId_H0.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyHdrDescTxt_H0.getValue(), glblMsg.ntfyHdrDescTxt_H0.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyBizAreaTpCd_SL.getValue(), glblMsg.ntfyBizAreaTpCd_SL.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfySubAreaTpCd_SL.getValue(), glblMsg.ntfySubAreaTpCd_SL.getValue()) && S21StringUtil.isEquals(bizMsg.effFromDt_H0.getValue(), glblMsg.effFromDt_H0.getValue())
                && S21StringUtil.isEquals(bizMsg.effThruDt_H0.getValue(), glblMsg.effThruDt_H0.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyHdrActvFlg_H0.getValue(), glblMsg.ntfyHdrActvFlg_H0.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyFreqTpCd_SL.getValue(), glblMsg.ntfyFreqTpCd_SL.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyRunDayListTxt_PD.getValue(), glblMsg.ntfyRunDayListTxt_PD.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyEomFlg_PD.getValue(), glblMsg.ntfyEomFlg_PD.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyRunSunFlg_PD.getValue(), glblMsg.ntfyRunSunFlg_PD.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyRunMonFlg_PD.getValue(), glblMsg.ntfyRunMonFlg_PD.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyRunTueFlg_PD.getValue(), glblMsg.ntfyRunTueFlg_PD.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyRunWedFlg_PD.getValue(), glblMsg.ntfyRunWedFlg_PD.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyRunThuFlg_PD.getValue(), glblMsg.ntfyRunThuFlg_PD.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyRunFriFlg_PD.getValue(), glblMsg.ntfyRunFriFlg_PD.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyRunSatFlg_PD.getValue(), glblMsg.ntfyRunSatFlg_PD.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyStartHourMn_PD.getValue(), glblMsg.ntfyStartHourMn_PD.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyEndHourMn_PD.getValue(), glblMsg.ntfyEndHourMn_PD.getValue())
                && S21StringUtil.isEquals(bizMsg.ntfyRunMnListTxt_PD.getValue(), glblMsg.ntfyRunMnListTxt_PD.getValue()) && S21StringUtil.isEquals(bizMsg.ntfyIntvlUomTpCd_SL.getValue(), glblMsg.ntfyIntvlUomTpCd_SL.getValue())
                && NWWL0020CommonLogic.isEqualsBigDecimalValue(bizMsg.ntfyIntvlAot_PD, glblMsg.ntfyIntvlAot_PD) && NWWL0020CommonLogic.isEqualsBigDecimalValue(bizMsg.histDaysAot_PD, glblMsg.histDaysAot_PD)) {
            return false;
        }
        return true;
    }

    /**
     * Is Change Data SQL TAB
     * @param bizMsg NWWL0020CMsg
     * @return boolean
     */
    private boolean isChangeDataSqlTab(NWWL0020CMsg bizMsg) {

        NTFY_HDRTMsg ntfyHdrTMsg = new NTFY_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrPk, bizMsg.ntfyHdrPk_H0);
        String sqltxt = new NtfySqlClodAccessor(ntfyHdrTMsg).getNtfySqlTxt();

        if (S21StringUtil.isEquals(bizMsg.xxNtfySqlTxt.getValue(), sqltxt)) {
            return false;
        }
        return true;
    }

    /**
     * Is Change Data Action Detail TAB
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @return boolean
     */
    private boolean isChangeDataActionDetailTab(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (bizMsg.A.getValidCount() == glblMsg.A.getValidCount()) {

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                NWWL0020_ACMsg acMsg = bizMsg.A.no(i);
                NWWL0020_ASMsg asMsg = glblMsg.A.no(i);

                if (!NWWL0020CommonLogic.isEqualsBigDecimalValue(acMsg.ntfyActDtlPk_A0, asMsg.ntfyActDtlPk_A0) || !S21StringUtil.isEquals(acMsg.ntfyActId_A0.getValue(), asMsg.ntfyActId_A0.getValue())
                        || !S21StringUtil.isEquals(acMsg.ntfyActNm_A0.getValue(), asMsg.ntfyActNm_A0.getValue()) || !S21StringUtil.isEquals(acMsg.ntfyActDescTxt_A0.getValue(), asMsg.ntfyActDescTxt_A0.getValue())
                        || !S21StringUtil.isEquals(acMsg.ntfyActTpCd_A0.getValue(), asMsg.ntfyActTpCd_A0.getValue())) {
                    return true;
                }
            }
        } else {
            return true;
        }

        if (NWWL0020CommonLogic.isChangeActionDetailData(bizMsg, glblMsg)) {
            return true;
        }

        return false;
    }

    /**
     * Check Header Tab
     * @param bizMsg NWWL0020CMsg
     * @return boolean
     */
    private boolean checkHeaderTab(NWWL0020CMsg bizMsg) {

        String effFromDt = bizMsg.effFromDt_H0.getValue();
        String effThruDt = bizMsg.effThruDt_H0.getValue();
        String salesDt = ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());

        if (ZYPCommonFunc.hasValue(effThruDt)) {
            if (effFromDt.compareTo(effThruDt) > 0) {
                bizMsg.effFromDt_H0.setErrorInfo(1, NWWM0005E, new String[] {"End Date", "Start Date" });
                bizMsg.effThruDt_H0.setErrorInfo(1, NWWM0005E, new String[] {"End Date", "Start Date" });
                return false;
            }

            if (salesDt.compareTo(effThruDt) > 0) {
                bizMsg.effThruDt_H0.setErrorInfo(1, NWWM0006E);
                return false;
            }
        }

        String startTm = bizMsg.ntfyStartHourMn_PD.getValue();
        String endTm = bizMsg.ntfyEndHourMn_PD.getValue();

        if (NTFY_FREQ_TP.DAILY.equals(bizMsg.ntfyFreqTpCd_SL.getValue())) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxRptChkFlg_PD.getValue())) {

                if (!ZYPCommonFunc.hasValue(endTm)) {
                    bizMsg.xxEndDplyTmTxt.setErrorInfo(1, NWWM0004E, new String[] {"End Time" });
                    return false;
                }

                if (startTm.compareTo(endTm) > 0) {
                    bizMsg.xxStartDplyTmTxt.setErrorInfo(1, NWWM0007E, new String[] {"End Time", "Start Time" });
                    bizMsg.xxEndDplyTmTxt.setErrorInfo(1, NWWM0007E, new String[] {"End Time", "Start Time" });
                    return false;
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.ntfyRunMnListTxt_PD)) {

                String[] minList = bizMsg.ntfyRunMnListTxt_PD.getValue().split(COMMA);
                for (String chkMin : minList) {
                    if (!chkMin.matches(CHK_MIN_PATTERNM)) {
                        bizMsg.ntfyRunMnListTxt_PD.setErrorInfo(1, NWWM0034E, new String[] {"0-59" });
                        return false;
                    }
                }
            }

        } else if (NTFY_FREQ_TP.WEEKLY.equals(bizMsg.ntfyFreqTpCd_SL.getValue())) {

            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyRunSunFlg_PD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyRunMonFlg_PD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyRunTueFlg_PD.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyRunWedFlg_PD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyRunThuFlg_PD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyRunFriFlg_PD.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyRunSatFlg_PD.getValue())) {

                bizMsg.ntfyRunSunFlg_PD.setErrorInfo(1, NWWM0008E);
                bizMsg.ntfyRunMonFlg_PD.setErrorInfo(1, NWWM0008E);
                bizMsg.ntfyRunTueFlg_PD.setErrorInfo(1, NWWM0008E);
                bizMsg.ntfyRunWedFlg_PD.setErrorInfo(1, NWWM0008E);
                bizMsg.ntfyRunThuFlg_PD.setErrorInfo(1, NWWM0008E);
                bizMsg.ntfyRunFriFlg_PD.setErrorInfo(1, NWWM0008E);
                bizMsg.ntfyRunSatFlg_PD.setErrorInfo(1, NWWM0008E);
                return false;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(bizMsg.ntfyRunDayListTxt_PD) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.ntfyEomFlg_PD.getValue())) {
                bizMsg.ntfyRunDayListTxt_PD.setErrorInfo(1, NWWM0019E);
                bizMsg.ntfyEomFlg_PD.setErrorInfo(1, NWWM0019E);
                return false;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.ntfyRunDayListTxt_PD)) {

                String[] dayList = bizMsg.ntfyRunDayListTxt_PD.getValue().split(COMMA);
                for (String chkDay : dayList) {
                    if (!chkDay.matches(CHK_DAY_PATTERNM)) {
                        bizMsg.ntfyRunDayListTxt_PD.setErrorInfo(1, NWWM0034E, new String[] {"1-31" });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Update Header Tab
     * @param bizMsg NWWL0020CMsg
     * @return boolean
     */
    private void updateHeaderTab(NWWL0020CMsg bizMsg) {

        NTFY_HDRTMsg ntfyHdrTMsg = new NTFY_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrPk, bizMsg.ntfyHdrPk_H0);

        NTFY_HDRTMsg updNtfyHdrTMsg = (NTFY_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(ntfyHdrTMsg);
        if (updNtfyHdrTMsg != null) {
            if (!ZYPDateUtil.isSameTimeStamp(updNtfyHdrTMsg.ezUpTime.getValue(), updNtfyHdrTMsg.ezUpTimeZone.getValue(), bizMsg.ezUpTime_H0.getValue(), bizMsg.ezUpTimeZone_H0.getValue())) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return;
            }
        }

        setNtfyHdrInfo(bizMsg, updNtfyHdrTMsg);

        EZDTBLAccessor.update(updNtfyHdrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updNtfyHdrTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWWM0020E, new String[] {updNtfyHdrTMsg.getTableName() });
            return;
        }

        // NTFY Run Job
        NTFY_RUN_JOBTMsg ntfyRunJobTMsg = new NTFY_RUN_JOBTMsg();
        ZYPEZDItemValueSetter.setValue(ntfyRunJobTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ntfyRunJobTMsg.ntfyHdrPk, bizMsg.ntfyHdrPk_H0);

        NTFY_RUN_JOBTMsg updNtfyRunJobTMsg = (NTFY_RUN_JOBTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(ntfyRunJobTMsg);
        if (updNtfyRunJobTMsg != null) {
            if (!ZYPDateUtil.isSameTimeStamp(updNtfyRunJobTMsg.ezUpTime.getValue(), updNtfyRunJobTMsg.ezUpTimeZone.getValue(), bizMsg.ezUpTime_JB.getValue(), bizMsg.ezUpTimeZone_JB.getValue())) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return;
            }
        }

        try {
            ZYPEZDItemValueSetter.setValue(updNtfyRunJobTMsg.nextRunTs, NWWL0020CommonLogic.getNextRunTs(updNtfyHdrTMsg));
        } catch (ParseException e) {
            bizMsg.setMessageInfo(NWWM0032E, new String[] {"Next Run Date" });
            return;
        }

        EZDTBLAccessor.update(updNtfyRunJobTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updNtfyRunJobTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWWM0020E, new String[] {updNtfyRunJobTMsg.getTableName() });
            return;
        }
    }

    /**
     * Insert Header Tab
     * @param bizMsg NWWL0020CMsg
     * @throws ParseException
     */
    private void insertHeaderTab(NWWL0020CMsg bizMsg) {

        NTFY_HDRTMsg insNtfyHdrTMsg = new NTFY_HDRTMsg();

        BigDecimal ntfyHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_HDR_SQ);
        ZYPEZDItemValueSetter.setValue(insNtfyHdrTMsg.ntfyHdrPk, ntfyHdrPk);

        final String ntfyHdrId = ZYPMaxTenDigitsNumbering.getUniqueID(bizMsg.glblCmpyCd.getValue(), "NTFY_HDR_ID");
        ZYPEZDItemValueSetter.setValue(insNtfyHdrTMsg.ntfyHdrId, ntfyHdrId);

        setNtfyHdrInfo(bizMsg, insNtfyHdrTMsg);

        EZDTBLAccessor.insert(insNtfyHdrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insNtfyHdrTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWWM0021E, new String[] {insNtfyHdrTMsg.getTableName() });
            return;
        }

        // NTFY Run Job
        NTFY_RUN_JOBTMsg insNtfyRunJobTMsg = new NTFY_RUN_JOBTMsg();
        ZYPEZDItemValueSetter.setValue(insNtfyRunJobTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(insNtfyRunJobTMsg.ntfyHdrPk, insNtfyHdrTMsg.ntfyHdrPk);
        ZYPEZDItemValueSetter.setValue(insNtfyRunJobTMsg.ntfyJobId, BLANK);
        ZYPEZDItemValueSetter.setValue(insNtfyRunJobTMsg.lastRunTs, BLANK);
        try {
            ZYPEZDItemValueSetter.setValue(insNtfyRunJobTMsg.nextRunTs, NWWL0020CommonLogic.getNextRunTs(insNtfyHdrTMsg));
        } catch (ParseException e) {
            bizMsg.setMessageInfo(NWWM0032E, new String[] {"Next Run Date" });
            return;
        }

        EZDTBLAccessor.insert(insNtfyRunJobTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insNtfyRunJobTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWWM0021E, new String[] {insNtfyRunJobTMsg.getTableName() });
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrId_H0, insNtfyHdrTMsg.ntfyHdrId);
    }

    /**
     * Set Notification Header Information
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @throws ParseException
     */
    private void setNtfyHdrInfo(NWWL0020CMsg bizMsg, NTFY_HDRTMsg ntfyHdrTMsg) {

        // Header
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrNm, bizMsg.ntfyHdrNm_H0);
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrDescTxt, bizMsg.ntfyHdrDescTxt_H0);
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyBizAreaTpCd, bizMsg.ntfyBizAreaTpCd_SL);
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfySubAreaTpCd, bizMsg.ntfySubAreaTpCd_SL);
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.effFromDt, bizMsg.effFromDt_H0);
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.effThruDt, bizMsg.effThruDt_H0);
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrActvFlg, getFlgItemValue(bizMsg.ntfyHdrActvFlg_H0));
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyFreqTpCd, bizMsg.ntfyFreqTpCd_SL);

        // Notification Periodic Detail
        if (NTFY_FREQ_TP.DAILY.equals(bizMsg.ntfyFreqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunDayListTxt, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyEomFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunSunFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunMonFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunTueFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunWedFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunThuFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunFriFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunSatFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyStartHourMn, bizMsg.ntfyStartHourMn_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyEndHourMn, bizMsg.ntfyEndHourMn_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyIntvlAot, bizMsg.ntfyIntvlAot_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyIntvlUomTpCd, bizMsg.ntfyIntvlUomTpCd_SL);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunMnListTxt, bizMsg.ntfyRunMnListTxt_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.histDaysAot, bizMsg.histDaysAot_PD);

        } else if (NTFY_FREQ_TP.WEEKLY.equals(bizMsg.ntfyFreqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunDayListTxt, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyEomFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunSunFlg, getFlgItemValue(bizMsg.ntfyRunSunFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunMonFlg, getFlgItemValue(bizMsg.ntfyRunMonFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunTueFlg, getFlgItemValue(bizMsg.ntfyRunTueFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunWedFlg, getFlgItemValue(bizMsg.ntfyRunWedFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunThuFlg, getFlgItemValue(bizMsg.ntfyRunThuFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunFriFlg, getFlgItemValue(bizMsg.ntfyRunFriFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunSatFlg, getFlgItemValue(bizMsg.ntfyRunSatFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyStartHourMn, bizMsg.ntfyStartHourMn_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyEndHourMn, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyIntvlAot, bizMsg.ntfyIntvlAot_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyIntvlUomTpCd, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunMnListTxt, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.histDaysAot, bizMsg.histDaysAot_PD);

        } else {

            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunDayListTxt, bizMsg.ntfyRunDayListTxt_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyEomFlg, getFlgItemValue(bizMsg.ntfyEomFlg_PD));
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunSunFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunMonFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunTueFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunWedFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunThuFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunFriFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunSatFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyStartHourMn, bizMsg.ntfyStartHourMn_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyEndHourMn, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyIntvlAot, bizMsg.ntfyIntvlAot_PD);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyIntvlUomTpCd, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyRunMnListTxt, BLANK);
            ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.histDaysAot, bizMsg.histDaysAot_PD);
        }
    }

    /**
     * Check SQL Tab
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @return boolean
     */
    private boolean checkSqlTab(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (!NWWL0020CommonLogic.checkProhibitedChar(bizMsg)) {
            return false;
        }

        if (!NWWL0020CommonLogic.checkSqlStatement(bizMsg.xxNtfySqlTxt)) {
            return false;
        }

        return true;
    }

    /**
     * Update SQL Tab
     * @param bizMsg NWWL0020CMsg
     */
    private void updateSqlTab(NWWL0020CMsg bizMsg) {

        NTFY_HDRTMsg ntfyHdrTMsg = new NTFY_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ntfyHdrTMsg.ntfyHdrPk, bizMsg.ntfyHdrPk_H0);

        NTFY_HDRTMsg updNtfyHdrTMsg = (NTFY_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(ntfyHdrTMsg);
        if (updNtfyHdrTMsg != null) {
            if (!ZYPDateUtil.isSameTimeStamp(updNtfyHdrTMsg.ezUpTime.getValue(), updNtfyHdrTMsg.ezUpTimeZone.getValue(), bizMsg.ezUpTime_H0.getValue(), bizMsg.ezUpTimeZone_H0.getValue())) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return;
            }
        }

        // Update 'NTFY_SQL_CLOD' (CLOB)
        new NtfySqlClodAccessor(updNtfyHdrTMsg).updateNtfySqlTxt(bizMsg.xxNtfySqlTxt.getValue());

    }

    /**
     * Check Action Detail Tab
     * @param bizMsg NWWL0020CMsg
     * @return boolean
     */
    private boolean checkActionDetailTab(NWWL0020CMsg bizMsg) {

        boolean chkSccessFlg = true;
        String rpyAddr = bizMsg.ntfyEmlRpyToAddr.getValue();

        // 2016/10/06 QC#1443 Modify Start --------------
        //if (ZYPCommonFunc.hasValue(rpyAddr) && !rpyAddr.matches(CHK_EMAIL_PATTERN)) {
        if (ZYPCommonFunc.hasValue(rpyAddr) && !NMXC106001CommonCheckUtil.checkEmailFormat(rpyAddr)) {
        // 2016/10/06 QC#1443 Modify End --------------
            bizMsg.ntfyEmlRpyToAddr.setErrorInfo(1, NWWM0011E);
            chkSccessFlg = false;
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWWL0020_BCMsg bcMsg = bizMsg.B.no(i);

            if (!ZYPCommonFunc.hasValue(bcMsg.ntfyActDtlColSortNum_B0)) {
                bizMsg.B.no(i).ntfyActDtlColSortNum_B0.setErrorInfo(1, NWWM0012E);
                chkSccessFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bcMsg.hdrLbNm_B0)) {
                bizMsg.B.no(i).hdrLbNm_B0.setErrorInfo(1, NWWM0012E);
                chkSccessFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bcMsg.placeHldNm_B0)) {
                bizMsg.B.no(i).placeHldNm_B0.setErrorInfo(1, NWWM0012E);
                chkSccessFlg = false;
            } else if (!bcMsg.placeHldNm_B0.getValue().matches(CHK_PLACE_HOLDER_PATTERNM)) {
                bizMsg.B.no(i).placeHldNm_B0.setErrorInfo(1, NWWM0035E, new String[] {"${SQL_COLUMN_NAME}" });
                return false;
            }
        }

        return chkSccessFlg;
    }

    /**
     * Delete Action Detail
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    private void deleteActionDetail(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            BigDecimal orgnNtfyActDtlPk = glblMsg.A.no(i).ntfyActDtlPk_A0.getValue();
            boolean deleteFlg = true;

            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {

                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).ntfyActDtlPk_A0) && orgnNtfyActDtlPk.compareTo(bizMsg.A.no(j).ntfyActDtlPk_A0.getValue()) == 0) {
                    deleteFlg = false;
                    break;
                }
            }

            if (deleteFlg) {
                NTFY_ACT_DTLTMsg delNtfyActDtlTMsg = new NTFY_ACT_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(delNtfyActDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(delNtfyActDtlTMsg.ntfyActDtlPk, orgnNtfyActDtlPk);

                NTFY_ACT_DTLTMsg preNtfyActDtlTMsg = (NTFY_ACT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(delNtfyActDtlTMsg);
                if (preNtfyActDtlTMsg != null) {
                    if (!ZYPDateUtil.isSameTimeStamp(preNtfyActDtlTMsg.ezUpTime.getValue(), preNtfyActDtlTMsg.ezUpTimeZone.getValue(), glblMsg.A.no(i).ezUpTime_A0.getValue(), glblMsg.A.no(i).ezUpTimeZone_A0.getValue())) {
                        bizMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                }
                EZDTBLAccessor.logicalRemove(delNtfyActDtlTMsg);

                // Delete NTFY_ACT_DTL_COL
                NTFY_ACT_DTL_COLTMsg delNtfyActDtlColTMsg = new NTFY_ACT_DTL_COLTMsg();
                delNtfyActDtlColTMsg.setSQLID("001");
                delNtfyActDtlColTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
                delNtfyActDtlColTMsg.setConditionValue("ntfyActDtlPk01", bizMsg.ntfyActDtlPk.getValue());

                NTFY_ACT_DTL_COLTMsgArray delNtfyActDtlColTMsgList = (NTFY_ACT_DTL_COLTMsgArray) EZDTBLAccessor.findByCondition(delNtfyActDtlColTMsg);
                if (delNtfyActDtlColTMsgList != null && 0 < delNtfyActDtlColTMsgList.getValidCount()) {
                    for (int k = 0; k < delNtfyActDtlColTMsgList.getValidCount(); k++) {

                        ZYPEZDItemValueSetter.setValue(delNtfyActDtlColTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(delNtfyActDtlColTMsg.ntfyActDtlColPk, delNtfyActDtlColTMsgList.no(k).ntfyActDtlColPk);
                        EZDTBLAccessor.logicalRemove(delNtfyActDtlColTMsg);
                    }
                }
            }
        }
    }

    /**
     * Update Action Detail Tab
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    private void updateActionDetailTab(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        BigDecimal ntfyActDtlPk = bizMsg.ntfyActDtlPk.getValue();

        // Action Detail Update
        NTFY_ACT_DTLTMsg ntfyActDtlTMsg = new NTFY_ACT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(ntfyActDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ntfyActDtlTMsg.ntfyActDtlPk, ntfyActDtlPk);

        NTFY_ACT_DTLTMsg updNtfyActDtlTMsg = (NTFY_ACT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(ntfyActDtlTMsg);
        if (updNtfyActDtlTMsg != null) {
            if (!ZYPDateUtil.isSameTimeStamp(updNtfyActDtlTMsg.ezUpTime.getValue(), updNtfyActDtlTMsg.ezUpTimeZone.getValue(), bizMsg.ezUpTime_DT.getValue(), bizMsg.ezUpTimeZone_DT.getValue())) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return;
            }
        }

        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyActNm, bizMsg.ntfyActNm);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyActDescTxt, bizMsg.ntfyActDescTxt);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyActTpCd, bizMsg.ntfyActTpCd_SL);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyOtptTpCd, bizMsg.ntfyOtptTpCd_SL);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyEmlRpyToAddr, bizMsg.ntfyEmlRpyToAddr);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyEmlToAddr, bizMsg.ntfyEmlToAddr);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyEmlCcAddr, bizMsg.ntfyEmlCcAddr);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyEmlBccAddr, bizMsg.ntfyEmlBccAddr);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyAttTpCd, bizMsg.ntfyAttTpCd_SL);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.ntfyEmlSubjTxt, bizMsg.ntfyEmlSubjTxt);
        ZYPEZDItemValueSetter.setValue(updNtfyActDtlTMsg.rtrvToAddrFromSqlFlg, getFlgItemValue(bizMsg.rtrvToAddrFromSqlFlg));

        // Update
        EZDTBLAccessor.update(updNtfyActDtlTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updNtfyActDtlTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWWM0020E, new String[] {updNtfyActDtlTMsg.getTableName() });
            return;
        }

        // Update 'NTFY_EML_BODY_CLOD' (CLOB)
        new NtfyEmlBodyClodAccessor(updNtfyActDtlTMsg).updateNtfyMailTxt(bizMsg.xxNtfyEmlBodyTxt.getValue());

        // Distribution List Update
        List<BigDecimal> distRelnList = new ArrayList<BigDecimal>();
        List<BigDecimal> distViewList = new ArrayList<BigDecimal>();

        NTFY_DIST_RELNTMsg ntfyDistRelnTMsg = new NTFY_DIST_RELNTMsg();
        ntfyDistRelnTMsg.setSQLID("001");
        ntfyDistRelnTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        ntfyDistRelnTMsg.setConditionValue("ntfyActDtlPk01", ntfyActDtlPk);

        NTFY_DIST_RELNTMsgArray ntfyDistRelnTMsgList = (NTFY_DIST_RELNTMsgArray) EZDTBLAccessor.findByCondition(ntfyDistRelnTMsg);

        if (ntfyDistRelnTMsgList != null && 0 < ntfyDistRelnTMsgList.getValidCount()) {
            for (int i = 0; i < ntfyDistRelnTMsgList.getValidCount(); i++) {
                distRelnList.add(ntfyDistRelnTMsgList.no(i).ntfyDistListPk.getValue());
            }
        }

        // Notification Distribution List
        String[] distListNmList = bizMsg.ntfyDistListNmListTxt.getValue().split(COMMA);

        for (String distListNm : distListNmList) {

            if (!ZYPCommonFunc.hasValue(distListNm)) {
                continue;
            }

            NTFY_DIST_LISTTMsg ntfyDistListTMsg = new NTFY_DIST_LISTTMsg();
            ntfyDistListTMsg.setSQLID("001");
            ntfyDistListTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            ntfyDistListTMsg.setConditionValue("ntfyDistListNm01", distListNm);

            NTFY_DIST_LISTTMsgArray ntfyDistListTMsgList = (NTFY_DIST_LISTTMsgArray) EZDTBLAccessor.findByCondition(ntfyDistListTMsg);

            if (ntfyDistListTMsgList != null && 0 < ntfyDistListTMsgList.getValidCount()) {
                distViewList.add(ntfyDistListTMsgList.no(0).ntfyDistListPk.getValue());
            } else {
                bizMsg.ntfyDistListNmListTxt.setErrorInfo(1, NWWM0015E, new String[] {ntfyDistListTMsg.getTableName(), distListNm });
                bizMsg.setMessageInfo(NWWM0015E, new String[] {ntfyDistListTMsg.getTableName(), distListNm });
                return;
            }
        }

        // Relation Create
        for (BigDecimal ntfyDistListPk : distViewList) {

            if (!distRelnList.contains(ntfyDistListPk)) {
                NTFY_DIST_RELNTMsg creNtfyDistRelnTMsg = new NTFY_DIST_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(creNtfyDistRelnTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(creNtfyDistRelnTMsg.ntfyActDtlPk, ntfyActDtlPk);
                ZYPEZDItemValueSetter.setValue(creNtfyDistRelnTMsg.ntfyDistListPk, ntfyDistListPk);

                // Create
                EZDTBLAccessor.create(creNtfyDistRelnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(creNtfyDistRelnTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWWM0021E, new String[] {creNtfyDistRelnTMsg.getTableName() });
                    return;
                }
            }
        }

        // Relation Delete
        for (BigDecimal ntfyDistRelnPk : distRelnList) {

            if (!distViewList.contains(ntfyDistRelnPk)) {
                NTFY_DIST_RELNTMsg delNtfyDistRelnTMsg = new NTFY_DIST_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(delNtfyDistRelnTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(delNtfyDistRelnTMsg.ntfyActDtlPk, ntfyActDtlPk);
                ZYPEZDItemValueSetter.setValue(delNtfyDistRelnTMsg.ntfyDistListPk, ntfyDistRelnPk);

                // Remove
                EZDTBLAccessor.logicalRemove(delNtfyDistRelnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(delNtfyDistRelnTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWWM0022E, new String[] {delNtfyDistRelnTMsg.getTableName() });
                    return;
                }
            }
        }

        // #of Column Update
        if (NWWL0020CommonLogic.isChangeActionDetailColumnData(bizMsg, glblMsg)) {

            // NTFY_ACT_DTL_COL Remove
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

                NTFY_ACT_DTL_COLTMsg delNtfyActDtlColTMsg = new NTFY_ACT_DTL_COLTMsg();
                ZYPEZDItemValueSetter.setValue(delNtfyActDtlColTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(delNtfyActDtlColTMsg.ntfyActDtlColPk, glblMsg.B.no(i).ntfyActDtlColPk_B0.getValue());

                NTFY_ACT_DTL_COLTMsg preNtfyActDtlColTMsg = (NTFY_ACT_DTL_COLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(delNtfyActDtlColTMsg);
                if (preNtfyActDtlColTMsg != null) {
                    if (!ZYPDateUtil.isSameTimeStamp(preNtfyActDtlColTMsg.ezUpTime.getValue(), preNtfyActDtlColTMsg.ezUpTimeZone.getValue(), glblMsg.B.no(i).ezUpTime_B0.getValue(), glblMsg.B.no(i).ezUpTimeZone_B0.getValue())) {
                        bizMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                }
                EZDTBLAccessor.logicalRemove(delNtfyActDtlColTMsg);
            }

            // NTFY_ACT_DTL_COL Insert
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NTFY_ACT_DTL_COLTMsg creNtfyActDtlColTMsg = new NTFY_ACT_DTL_COLTMsg();
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.ntfyActDtlColPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_ACT_DTL_COL_SQ));
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.ntfyActDtlPk, ntfyActDtlPk);
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.ntfyActDtlColSortNum, bizMsg.B.no(i).ntfyActDtlColSortNum_B0);
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.hdrLbNm, bizMsg.B.no(i).hdrLbNm_B0);
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.placeHldNm, bizMsg.B.no(i).placeHldNm_B0);

                EZDTBLAccessor.insert(creNtfyActDtlColTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(creNtfyActDtlColTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWWM0021E, new String[] {creNtfyActDtlColTMsg.getTableName() });
                    return;
                }
            }
        }
    }

    /**
     * Insert Action Detail Tab
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    private void insertActionDetailTab(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        BigDecimal ntfyActDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_ACT_DTL_SQ);

        NTFY_ACT_DTLTMsg insNtfyActDtlTMsg = new NTFY_ACT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyActDtlPk, ntfyActDtlPk);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyHdrPk, bizMsg.ntfyHdrPk_H0);
        final String ntfyActId = ZYPMaxTenDigitsNumbering.getUniqueID(bizMsg.glblCmpyCd.getValue(), "NTFY_ACT_ID");
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyActId, ntfyActId);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyActNm, bizMsg.ntfyActNm);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyActDescTxt, bizMsg.ntfyActDescTxt);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyActTpCd, bizMsg.ntfyActTpCd_SL);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyOtptTpCd, bizMsg.ntfyOtptTpCd_SL);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyEmlRpyToAddr, bizMsg.ntfyEmlRpyToAddr);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyEmlToAddr, bizMsg.ntfyEmlToAddr);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyEmlCcAddr, bizMsg.ntfyEmlCcAddr);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyEmlBccAddr, bizMsg.ntfyEmlBccAddr);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyAttTpCd, bizMsg.ntfyAttTpCd_SL);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.ntfyEmlSubjTxt, bizMsg.ntfyEmlSubjTxt);
        ZYPEZDItemValueSetter.setValue(insNtfyActDtlTMsg.rtrvToAddrFromSqlFlg, getFlgItemValue(bizMsg.rtrvToAddrFromSqlFlg));

        // Insert
        EZDTBLAccessor.insert(insNtfyActDtlTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insNtfyActDtlTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWWM0021E, new String[] {insNtfyActDtlTMsg.getTableName() });
            return;
        }

        // Update 'NTFY_EML_BODY_CLOD' (CLOB)
        new NtfyEmlBodyClodAccessor(insNtfyActDtlTMsg).updateNtfyMailTxt(bizMsg.xxNtfyEmlBodyTxt.getValue());

        // Distribution List Insert
        List<BigDecimal> distViewList = new ArrayList<BigDecimal>();

        // Notification Distribution List
        String[] distListNmList = bizMsg.ntfyDistListNmListTxt.getValue().split(COMMA);

        for (String distListNm : distListNmList) {

            if (!ZYPCommonFunc.hasValue(distListNm)) {
                continue;
            }

            NTFY_DIST_LISTTMsg ntfyDistListTMsg = new NTFY_DIST_LISTTMsg();
            ntfyDistListTMsg.setSQLID("001");
            ntfyDistListTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            ntfyDistListTMsg.setConditionValue("ntfyDistListNm01", distListNm);

            NTFY_DIST_LISTTMsgArray ntfyDistListTMsgList = (NTFY_DIST_LISTTMsgArray) EZDTBLAccessor.findByCondition(ntfyDistListTMsg);

            if (ntfyDistListTMsgList != null && 0 < ntfyDistListTMsgList.getValidCount()) {
                distViewList.add(ntfyDistListTMsgList.no(0).ntfyDistListPk.getValue());
            } else {
                bizMsg.ntfyDistListNmListTxt.setErrorInfo(1, NWWM0015E, new String[] {ntfyDistListTMsg.getTableName(), distListNm });
                bizMsg.setMessageInfo(NWWM0015E, new String[] {ntfyDistListTMsg.getTableName(), distListNm });
                return;
            }
        }

        // Relation Create
        for (BigDecimal ntfyDistListPk : distViewList) {

            NTFY_DIST_RELNTMsg creNtfyDistRelnTMsg = new NTFY_DIST_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(creNtfyDistRelnTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(creNtfyDistRelnTMsg.ntfyActDtlPk, ntfyActDtlPk);
            ZYPEZDItemValueSetter.setValue(creNtfyDistRelnTMsg.ntfyDistListPk, ntfyDistListPk);

            // Insert
            EZDTBLAccessor.insert(creNtfyDistRelnTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(creNtfyDistRelnTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NWWM0021E, new String[] {creNtfyDistRelnTMsg.getTableName() });
            }
        }

        // #of Column Update
        if (NWWL0020CommonLogic.isChangeActionDetailColumnData(bizMsg, glblMsg)) {

            // NTFY_ACT_DTL_COL Insert
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NTFY_ACT_DTL_COLTMsg creNtfyActDtlColTMsg = new NTFY_ACT_DTL_COLTMsg();
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.ntfyActDtlColPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_ACT_DTL_COL_SQ));
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.ntfyActDtlPk, ntfyActDtlPk);
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.ntfyActDtlColSortNum, bizMsg.B.no(i).ntfyActDtlColSortNum_B0);
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.hdrLbNm, bizMsg.B.no(i).hdrLbNm_B0);
                ZYPEZDItemValueSetter.setValue(creNtfyActDtlColTMsg.placeHldNm, bizMsg.B.no(i).placeHldNm_B0);

                EZDTBLAccessor.insert(creNtfyActDtlColTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(creNtfyActDtlColTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWWM0021E, new String[] {creNtfyActDtlColTMsg.getTableName() });
                    return;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyActDtlPk, ntfyActDtlPk);
    }

    /**
     * Get Flag Item Value
     * @param flgItem EZDCStringItem
     * @return String
     */
    private String getFlgItemValue(EZDCStringItem flgItem) {
        if (ZYPCommonFunc.hasValue(flgItem)) {
            return flgItem.getValue();
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }
}
