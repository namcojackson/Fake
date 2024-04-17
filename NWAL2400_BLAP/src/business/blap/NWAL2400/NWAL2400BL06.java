/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2400;

import static business.blap.NWAL2400.constant.NWAL2400Constant.BIZ_APP_ID;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_COLUMN_INVTY_LOC_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_CMN_SUBMIT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.MAX_DATE;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NMAM0803E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NMAM0836E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NMAM8509E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NWAM0181E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NWAM0221E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NWAM0223E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NWAM0429E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NWAM8457E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2400.common.NWAL2400CommonLogic;
import business.db.DS_RTL_DLR_INFOTMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/06/30   CITS            S.Tanikawa      Update          QC#10452
 * 2016/06/30   CITS            S.Tanikawa      Update          QC#10537
 * 2016/08/01   CITS            S.Tanikawa      Update          QC#11099
 *</pre>
 */
public class NWAL2400BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ((EVENT_NM_NWAL2400_CMN_SUBMIT.equals(screenAplID))) {
                doProcess_Submit((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Submit
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_Submit(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        NWAL2400CommonLogic.updateGlblMsg(cMsg, sMsg);

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        int maxDisplayRows = cMsg.A.length();
        int maxIndex = sMsg.A.getValidCount();

        try {
            // Check Input fields.
            for (int i = 0; i < maxIndex; i++) {

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).coaBrCd_A)) {
                    sMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NMAM0836E, new String[] {"Branch Code" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlDlrCd_A)) {
                    sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NMAM0836E, new String[] {"Dealer Code" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlDivCd_A)) {
                    sMsg.A.no(i).rtlDivCd_A.setErrorInfo(1, NMAM0836E, new String[] {"Division" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).dsOrdCatgDescTxt_A)) {
                    sMsg.A.no(i).dsOrdCatgDescTxt_A.setErrorInfo(1, NMAM0836E, new String[] {"Order Category" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).dsOrdTpDescTxt_A)) {
                    sMsg.A.no(i).dsOrdTpDescTxt_A.setErrorInfo(1, NMAM0836E, new String[] {"Reason" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A)) {
                    sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, NMAM0836E, new String[] {"Warehouse" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).billToCustCd_A)) {
                    sMsg.A.no(i).billToCustCd_A.setErrorInfo(1, NMAM0836E, new String[] {"Bill To Location" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).contrGrpCd_A)) {
                    sMsg.A.no(i).contrGrpCd_A.setErrorInfo(1, NMAM0836E, new String[] {"Contact Group" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A)) {
                    sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM0836E, new String[] {"Start Date" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Date check
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).dsRtlDlrInfoPk_A) 
                        && ZYPDateUtil.compare(sMsg.A.no(i).effFromDt_A.getValue(), slsDt) < 0) {
                    sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM0803E);
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A)) {
                    if (ZYPDateUtil.compare(sMsg.A.no(i).effFromDt_A.getValue(), sMsg.A.no(i).effThruDt_A.getValue()) > 0) {
                        sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NWAM0223E, new String[] {"End Date", "Start Date" });
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum.setValue(errScrnInex);
                        return;
                    }
                }

                // Check Duplication
                for (int j = i + 1; j < maxIndex; j++) {

                    // UPDATE START QC#10537
                    if (sMsg.A.no(i).coaBrCd_A.getValue().equals(sMsg.A.no(j).coaBrCd_A.getValue()) //
                            && sMsg.A.no(i).rtlDlrCd_A.getValue().equals(sMsg.A.no(j).rtlDlrCd_A.getValue()) //
                            && sMsg.A.no(i).rtlDivCd_A.getValue().equals(sMsg.A.no(j).rtlDivCd_A.getValue())) {

                        String srcEffFromDt = sMsg.A.no(i).effFromDt_A.getValue();

                        String srcEffThruDt;
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A)) {
                            srcEffThruDt = sMsg.A.no(i).effThruDt_A.getValue();
                        } else {
                            srcEffThruDt = MAX_DATE;
                        }

                        String trgEffFromDt = sMsg.A.no(j).effFromDt_A.getValue();

                        String trgEffThruDt;
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(j).effThruDt_A)) {
                            trgEffThruDt = sMsg.A.no(j).effThruDt_A.getValue();
                        } else {
                            trgEffThruDt = MAX_DATE;
                        }

                        if ((ZYPDateUtil.compare(srcEffFromDt, trgEffFromDt) >= 0 && ZYPDateUtil.compare(srcEffFromDt, trgEffThruDt) <= 0)
                                || (ZYPDateUtil.compare(srcEffThruDt, trgEffFromDt) >= 0 && ZYPDateUtil.compare(srcEffThruDt, trgEffThruDt) <= 0)) {
                            sMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NMAM8509E);
                            sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NMAM8509E);
                            sMsg.A.no(i).rtlDivCd_A.setErrorInfo(1, NMAM8509E);
                            sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM8509E);
                            sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAM8509E);
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }
                    }
                    // when Retail Dealer Code and Retail Division Code are same,
                    if (sMsg.A.no(i).rtlDlrCd_A.getValue().equals(sMsg.A.no(j).rtlDlrCd_A.getValue()) //
                            && sMsg.A.no(i).rtlDivCd_A.getValue().equals(sMsg.A.no(j).rtlDivCd_A.getValue())) {
                        // Order Type, Reason Code, WH Code, or Bill To Cust Code must be the same
                        if (!sMsg.A.no(i).dsOrdCatgDescTxt_A.getValue().equals(sMsg.A.no(j).dsOrdCatgDescTxt_A.getValue()) //
                                || !sMsg.A.no(i).dsOrdTpDescTxt_A.getValue().equals(sMsg.A.no(j).dsOrdTpDescTxt_A.getValue()) //
                                || !sMsg.A.no(i).rtlWhNm_A.getValue().equals(sMsg.A.no(j).rtlWhNm_A.getValue()) //
                                || !sMsg.A.no(i).billToCustCd_A.getValue().equals(sMsg.A.no(j).billToCustCd_A.getValue())) {
                            sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NWAM8457E);
                            sMsg.A.no(i).rtlDivCd_A.setErrorInfo(1, NWAM8457E);
                            sMsg.A.no(i).dsOrdTpDescTxt_A.setErrorInfo(1, NWAM8457E);
                            sMsg.A.no(i).dsOrdCatgCd_A.setErrorInfo(1, NWAM8457E);
                            sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, NWAM8457E);
                            sMsg.A.no(i).billToCustCd_A.setErrorInfo(1, NWAM8457E);
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }
                    }
                    // UPDATE END QC#10537
                }
            }

            // Check with DB records and DB Update
            for (int i = 0; i < maxIndex; i++) {

                // Check Branch Code
                int count = NWAL2400CommonLogic.chkCoaBrCd(glblCmpyCd, sMsg.A.no(i).coaBrCd_A.getValue());
                if (count == 0) {
                    sMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NWAM0181E, new String[] {"Branch" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Check Order Category
                String dsOrdCatgCd = NWAL2400CommonLogic.getDsOrdCatgCd(glblCmpyCd, sMsg.A.no(i).dsOrdCatgDescTxt_A.getValue());

                if (dsOrdCatgCd == null) {
                    sMsg.A.no(i).dsOrdCatgDescTxt_A.setErrorInfo(1, NWAM0181E, new String[] {"Order Category" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Check Order Reason
                String dsOrdTpCd = NWAL2400CommonLogic.getDsOrdTpCd(glblCmpyCd, dsOrdCatgCd, sMsg.A.no(i).dsOrdTpDescTxt_A.getValue());

                if (dsOrdTpCd == null) {
                    sMsg.A.no(i).dsOrdTpDescTxt_A.setErrorInfo(1, NWAM0181E, new String[] {"Reason" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Check Retail Warehouse Code
                // UPDATE START QC#10457
                S21SsmEZDResult result = NWAL2400CommonLogic.getRtlWh(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A.getValue(), ZYPDateUtil.getSalesDate(glblCmpyCd));
                if (!result.isCodeNormal() || result.getQueryResultCount() == 0) {
                    sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, NWAM0181E, new String[] {"Warehouse" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                // UPDATE START QC#10457

                Map<String, Object> map = (Map<String, Object>) result.getResultObject();
                String rtlWhCd = (String) map.get(DB_COLUMN_RTL_WH_CD);
                String invtyLocCd = (String) map.get(DB_COLUMN_INVTY_LOC_CD);

                // Check EnableWH
                NMXC100001EnableWHData enableWhData = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, invtyLocCd, BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N, new String[] {});
                if (enableWhData.getXxMsgId() != null) {
                    sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, enableWhData.getXxMsgId(), null);
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Check Bill To Customer Code
                count = NWAL2400CommonLogic.chkBillToCustCd(glblCmpyCd, sMsg.A.no(i).billToCustCd_A.getValue());
                if (count == 0) {
                    sMsg.A.no(i).billToCustCd_A.setErrorInfo(1, NWAM0181E, new String[] {"Bill To Location" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Check DB Duplicate
                count = NWAL2400CommonLogic.chkDsRtlDlrInfo(glblCmpyCd, sMsg.A.no(i));
                if (count > 0) {
                    sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NMAM8509E);
                    sMsg.A.no(i).rtlDivCd_A.setErrorInfo(1, NMAM8509E);
                    sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM8509E);
                    sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAM8509E);
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // DB Update
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).dsRtlDlrInfoPk_A)) {
                    // New Line
                    DS_RTL_DLR_INFOTMsg tMsg = new DS_RTL_DLR_INFOTMsg();
                    BigDecimal dsRtlDlrInfoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_RTL_DLR_INFO_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsRtlDlrInfoPk, dsRtlDlrInfoPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlDlrCd, sMsg.A.no(i).rtlDlrCd_A);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlDivCd, sMsg.A.no(i).rtlDivCd_A);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, dsOrdCatgCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.whCd, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, sMsg.A.no(i).billToCustCd_A);
                    ZYPEZDItemValueSetter.setValue(tMsg.contrGrpCd, sMsg.A.no(i).contrGrpCd_A);
                    ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, sMsg.A.no(i).coaBrCd_A);
                    ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A);
                    ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A);
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NWAM0221E, new String[] {"to insert", "DS_RTL_DLR_INFO", tMsg.getReturnCode() });
                        return;
                    }
                } else {
                    // Update Line
                    BigDecimal dsRtlDlrInfoPk = sMsg.A.no(i).dsRtlDlrInfoPk_A.getValue();

                    DS_RTL_DLR_INFOTMsg tMsg = new DS_RTL_DLR_INFOTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.dsRtlDlrInfoPk, dsRtlDlrInfoPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

                    tMsg = (DS_RTL_DLR_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
                    if (tMsg == null) {
                        sMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).rtlDivCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).dsOrdCatgDescTxt_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).dsOrdTpDescTxt_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).contrGrpCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).billToCustCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NWAM0429E);
                        return;
                    }

                    String findEzUpTime = sMsg.A.no(i).ezUpTime_A.getValue();
                    String findEzUpTimeZone = sMsg.A.no(i).ezUpTimeZone_A.getValue();
                    String currentEzUpTime = tMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        sMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).rtlDivCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).dsOrdCatgDescTxt_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).dsOrdTpDescTxt_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).contrGrpCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).billToCustCd_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NWAM0429E);
                        sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NWAM0429E);
                        return;
                    }

                    boolean updtFlg = false;
                    if (!tMsg.rtlDlrCd.getValue().equals(sMsg.A.no(i).rtlDlrCd_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(tMsg.rtlDlrCd, sMsg.A.no(i).rtlDlrCd_A);
                        updtFlg = true;
                    }

                    if (!tMsg.rtlDivCd.getValue().equals(sMsg.A.no(i).rtlDivCd_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(tMsg.rtlDivCd, sMsg.A.no(i).rtlDivCd_A);
                        updtFlg = true;
                    }

                    if (!tMsg.dsOrdCatgCd.getValue().equals(dsOrdCatgCd)) {
                        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, dsOrdCatgCd);
                        updtFlg = true;
                    }

                    if (!tMsg.dsOrdTpCd.getValue().equals(dsOrdTpCd)) {
                        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);
                        updtFlg = true;
                    }

                    if (!tMsg.whCd.getValue().equals(rtlWhCd)) {
                        ZYPEZDItemValueSetter.setValue(tMsg.whCd, rtlWhCd);
                        updtFlg = true;
                    }

                    if (!tMsg.billToCustCd.getValue().equals(sMsg.A.no(i).billToCustCd_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, sMsg.A.no(i).billToCustCd_A);
                        updtFlg = true;
                    }

                    if (!tMsg.contrGrpCd.getValue().equals(sMsg.A.no(i).contrGrpCd_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(tMsg.contrGrpCd, sMsg.A.no(i).contrGrpCd_A);
                        updtFlg = true;
                    }

                    if (!tMsg.coaBrCd.getValue().equals(sMsg.A.no(i).coaBrCd_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, sMsg.A.no(i).coaBrCd_A);
                        updtFlg = true;
                    }

                    if (!tMsg.effFromDt.getValue().equals(sMsg.A.no(i).effFromDt_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A);
                        updtFlg = true;
                    }

                    if (!tMsg.effThruDt.getValue().equals(sMsg.A.no(i).effThruDt_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A);
                        updtFlg = true;
                    }

                    if (updtFlg) {
                        EZDTBLAccessor.update(tMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NWAM0221E, new String[] {"to update", "DS_RTL_DLR_INFO", tMsg.getReturnCode() });
                            return;
                        }
                    }
                }
            }

            // Delete Process
            deleteProcess(cMsg, sMsg);

            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
        } finally {
            NWAL2400CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
        }
    }

    /**
     * Delete Process
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void deleteProcess(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        for (int i = 0; i < sMsg.D.getValidCount(); i++) {

            BigDecimal dsRtlDlrInfoPk = sMsg.D.no(i).dsRtlDlrInfoPk_D.getValue();

            DS_RTL_DLR_INFOTMsg tMsg = new DS_RTL_DLR_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.dsRtlDlrInfoPk, dsRtlDlrInfoPk);
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

            tMsg = (DS_RTL_DLR_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
            if (tMsg == null) {
                sMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).coaBrDescTxt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).dsOrdCatgDescTxt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).dsOrdTpDescTxt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).contrGrpCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).billToCustCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NWAM0429E);
                return;
            }

            String findEzUpTime = sMsg.D.no(i).ezUpTime_D.getValue();
            String findEzUpTimeZone = sMsg.D.no(i).ezUpTimeZone_D.getValue();
            String currentEzUpTime = tMsg.ezUpTime.getValue();
            String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                sMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).coaBrDescTxt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).rtlDlrCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).dsOrdCatgDescTxt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).dsOrdTpDescTxt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).rtlWhNm_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).contrGrpCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).billToCustCd_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).effFromDt_A.setErrorInfo(1, NWAM0429E);
                sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NWAM0429E);
                return;
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NWAM0221E, new String[] {"to logical remove", "DS_RTL_DLR_INFO", tMsg.getReturnCode() });
                return;
            }
        }
    }
}
