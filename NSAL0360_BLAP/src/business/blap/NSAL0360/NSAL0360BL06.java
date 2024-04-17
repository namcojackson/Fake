/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0360;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import business.blap.NSAL0360.common.NSAL0360CommonLogic;
import business.blap.NSAL0360.constant.NSAL0360Constant;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC047002PMsg;
import business.parts.NSZC047002_xxMtrLineListPMsgArray;
import business.parts.NSZC047021PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2015/10/23   Hitachi         T.Tomita        Update          N/A
 * 2016/01/14   Hitachi         T.Tomita        Update          CSA QC#2950
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 * 2016/02/25   Hitachi         T.Aoyagi        Update          QC3422
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/08/08   Hitachi         K.Kishimoto     Update          QC#12879
 * 2016/09/06   Hitachi         K.Kishimoto     Update          QC#12429
 *</pre>
 */
public class NSAL0360BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NSAL0360CMsg bizMsg = (NSAL0360CMsg) cMsg;
        NSAL0360SMsg sharedMsg = (NSAL0360SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0360Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_CMN_Save(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_Schedules".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_CMN_Save(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_CMN_Save <dd>The method
     * explanation: save button event
     * @param bizMsg NSAL0360CMsg
     * @param sharedMsg NSAL0360SMsg
     */
    private void doProcess_NSAL0360Scrn00_CMN_Save(NSAL0360CMsg bizMsg, NSAL0360SMsg sharedMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (!inputCheck(glblCmpyCd, bizMsg)) {
            return;
        }

        // START 2015/10/23 T.Tomita [N/A, MOD]
        boolean updateFlg = false;
        String chngRngFlg = ZYPConstant.FLG_ON_Y;
        if (NSAL0360CommonLogic.isChangedHeader(bizMsg, sharedMsg)) {
            chngRngFlg = ZYPConstant.FLG_OFF_N;
            if (!updateDsContrDtl(bizMsg, sharedMsg, glblCmpyCd)) {
                return;
            }
            updateFlg = true;
        }

        // START 2016/01/14 T.Tomita [QC#2950, MOD]
        if (updateFlg || NSAL0360CommonLogic.isChangedSchedules(bizMsg, sharedMsg)) {
            if (!createOrUpdateSvcMemo(bizMsg, sharedMsg, glblCmpyCd)) {
                return;
            }

            if (!updateScheduleInfo(bizMsg, sharedMsg, glblCmpyCd, chngRngFlg)) {
                return;
            }
        }
        bizMsg.setMessageInfo(NSAL0360Constant.NZZM0002I);
        // END 2016/01/14 T.Tomita [QC#2950, MOD]
        // START 2015/10/23 T.Tomita [N/A, MOD]
    }

    private boolean updateDsContrDtl(NSAL0360CMsg bizMsg, NSAL0360SMsg sharedMsg, String glblCmpyCd) {

        // START 2015/10/23 T.Tomita [N/A, DEL]
//        if (!NSAL0360CommonLogic.isChangedHeader(bizMsg, sharedMsg)) {
//            return true;
//        }
        // END 2015/10/23 T.Tomita [N/A, DEL]

        DS_CONTR_DTLTMsg exDsContrDtlTMsg = NSAL0360CommonLogic.findDsContrDtlForUpdate(glblCmpyCd, bizMsg.dsContrDtlPk_H1.getValue());
        if (exDsContrDtlTMsg == null) {
            String[] args = {"DS_CONTR_DTL" };
            bizMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_HH.getValue(), bizMsg.ezUpTimeZone_HH.getValue(), exDsContrDtlTMsg.ezUpTime.getValue(), exDsContrDtlTMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(NSAL0360Constant.NZZM0003E);
            return false;
        }

        if (!setDsContrDtlData(exDsContrDtlTMsg, bizMsg)) {
            return false;
        }

        // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.mtrCloDay_H1, exDsContrDtlTMsg.mtrCloDay);
        // END 2016/05/18 T.Kanasaka [QC#2184, ADD]

        S21FastTBLAccessor.update(exDsContrDtlTMsg);
        if (!RTNCD_NORMAL.equals(exDsContrDtlTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NSAL0360Constant.NSAM0001E, new String[] {"DS_CONTR_DTL", "glblCmpyCd=" + glblCmpyCd + ", dsContrDtlPk=" + exDsContrDtlTMsg.dsContrDtlPk.getValue() });
            return false;
        }
        return true;
    }

    private boolean createOrUpdateSvcMemo(NSAL0360CMsg bizMsg, NSAL0360SMsg sharedMsg, String glblCmpyCd) {

        // START 2015/10/23 T.Tomita [N/A, DEL]
//        if (!NSAL0360CommonLogic.isChangedFooter(bizMsg, sharedMsg)) {
//            return true;
//        }
        // END 2015/10/23 T.Tomita [N/A, DEL]

        // START 2016/03/02 T.Tomita [QC#4131, ADD]
        if (!inputSvcMemoCheck(bizMsg)) {
            return false;
        }
        // END 2016/03/02 T.Tomita [QC#4131, ADD]

        // START 2015/10/23 T.Tomita [N/A, MOD]
        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();

        if (!setSvcMemoData(svcMemoTMsg, bizMsg, true)) {
            return false;
        }

        S21FastTBLAccessor.create(svcMemoTMsg);
        if (!RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NSAL0360Constant.NSAM0012E, new String[] {"SVC_MEMO", "glblCmpyCd=" + glblCmpyCd + ", svcMemoPk=" + svcMemoTMsg.svcMemoPk.getValue() });
            return false;
        }
//        SVC_MEMOTMsg exSvcMemoTMsg = NSAL0360CommonLogic.findSvcMemoForUpdate(glblCmpyCd, bizMsg.svcMemoPk_F1.getValue());
//        if (exSvcMemoTMsg == null) {
//
//            SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
//
//            if (!setSvcMemoData(svcMemoTMsg, bizMsg, true)) {
//                return false;
//            }
//
//            S21FastTBLAccessor.create(svcMemoTMsg);
//            if (!RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NSAL0360Constant.NSAM0012E, new String[] {"SVC_MEMO", "glblCmpyCd=" + glblCmpyCd + ", svcMemoPk=" + svcMemoTMsg.svcMemoPk.getValue() });
//                return false;
//            }
//        } else {
//            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_FF.getValue(), bizMsg.ezUpTimeZone_FF.getValue(), exSvcMemoTMsg.ezUpTime.getValue(), exSvcMemoTMsg.ezUpTimeZone.getValue())) {
//                bizMsg.setMessageInfo(NSAL0360Constant.NZZM0003E);
//                return false;
//            }
//
//            if (!setSvcMemoData(exSvcMemoTMsg, bizMsg, false)) {
//                return false;
//            }
//
//            S21FastTBLAccessor.update(exSvcMemoTMsg);
//            if (!RTNCD_NORMAL.equals(exSvcMemoTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NSAL0360Constant.NSAM0001E, new String[] {"SVC_MEMO", "glblCmpyCd=" + glblCmpyCd + ", svcMemoPk=" + exSvcMemoTMsg.svcMemoPk.getValue() });
//                return false;
//            }
//        }
        // END 2015/10/23 T.Tomita [N/A, MOD]
        return true;

    }

    private boolean updateScheduleInfo(NSAL0360CMsg bizMsg, NSAL0360SMsg sharedMsg, String glblCmpyCd, String chngRngFlg) {

        // START 2015/10/23 T.Tomita [N/A, DEL]
//        if (!NSAL0360CommonLogic.isChangedSchedules(bizMsg, sharedMsg)) {
//            return true;
//        }
        // END 2015/10/23 T.Tomita [N/A, DEL]

        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(bizMsg, tblNm);
            setSameSqInputItem(tblArray, tblNm);
            int cnt = tblArray.getValidCount();
            for (int i = 0; i < cnt; i++) {
                EZDMsg ezdMsg = tblArray.get(i);
                EZDCBigDecimalItem dsContrBllgSchdSmryPk = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSmryPk_A1");
                EZDCStringItem ezUpTime = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTime_A1");
                EZDCStringItem ezUpTimeZone = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTimeZone_A1");

                if (ZYPCommonFunc.hasValue(dsContrBllgSchdSmryPk)) {
                    DS_CONTR_BLLG_SCHD_SMRYTMsg dsContrBllgSchdSmryTMsg = NSAL0360CommonLogic.findDsContrBllgSchdSmryForUpdate(glblCmpyCd, dsContrBllgSchdSmryPk.getValue(), bizMsg.dsContrDtlPk_H1.getValue());
                    if (dsContrBllgSchdSmryTMsg == null) {
                        String[] args = {"DS_CONTR_BLLG_SCHD_SMRY" };
                        bizMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
                        return false;
                    }
                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTime.getValue(), ezUpTimeZone.getValue(), dsContrBllgSchdSmryTMsg.ezUpTime.getValue(), dsContrBllgSchdSmryTMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NSAL0360Constant.NZZM0003E);
                        return false;
                    }
                }

                EZDCBigDecimalItem dsContrBllgSchdMtrPk = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdMtrPk_A1");
                EZDCStringItem ezUpTime_M = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTime_AM");
                EZDCStringItem ezUpTimeZone_M = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTimeZone_AM");
                if (ZYPCommonFunc.hasValue(dsContrBllgSchdMtrPk)) {
                    DS_CONTR_BLLG_SCHD_MTRTMsg dsContrBllgSchdMtrTMsg = NSAL0360CommonLogic.findDsContrBllgSchdMtrForUpdate(glblCmpyCd, dsContrBllgSchdMtrPk.getValue());
                    if (dsContrBllgSchdMtrTMsg == null) {
                        String[] args = {"DS_CONTR_BLLG_SCHD_MTR" };
                        bizMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
                        return false;
                    }
                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTime_M.getValue(), ezUpTimeZone_M.getValue(), dsContrBllgSchdMtrTMsg.ezUpTime.getValue(), dsContrBllgSchdMtrTMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NSAL0360Constant.NZZM0003E);
                        return false;
                    }
                }
            }
        }
        // START 2015/10/23 T.Tomita [N/A, MOD]
        return callScheduleApi(bizMsg, glblCmpyCd, chngRngFlg);
        // END 2015/10/23 T.Tomita [N/A, MOD]
    }

    // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
    private boolean setDsContrDtlData(DS_CONTR_DTLTMsg dsContrDtlTMsg, NSAL0360CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, bizMsg.mtrBllgTmgCd_H1);
        // START 2016/08/08 K.Kishimoto [QC#12879, MOD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, bizMsg.mtrDplyPerEndDay_H1);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrCloDay, bizMsg.mtrDplyPerEndDay_H1);
//        if (NSAL0360Constant.RADIO_VALUE_CLOSING_DAY.compareTo(bizMsg.xxRadioBtn_H1.getValue()) == 0) {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, bizMsg.mtrDplyPerEndDay_H1);

        if (NSAL0360Constant.MIN_DAY.equals(bizMsg.mtrDplyPerEndDay_H1.getValue())) {
            int fromDt = 0;
            if (ZYPCommonFunc.hasValue(dsContrDtlTMsg.contrEffFromDt)) {
                fromDt = Integer.parseInt(dsContrDtlTMsg.contrEffFromDt.getValue().substring(6));
            }
            if (fromDt >= 2 && fromDt <= 28) {
                fromDt--;
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrCloDay, new Integer(fromDt).toString());
            } else {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrCloDay, NSAL0360Constant.MAX_DAY);
            }
        }
//            } else {
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrCloDay, bizMsg.mtrDplyPerEndDay_H1);
//            }
//        } else {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrCloDay, NSAL0360Constant.MAX_DAY);
//        }
//        if (NSAL0360Constant.RADIO_VALUE_BLLG_DAY.compareTo(bizMsg.xxRadioBtn_H2.getValue()) == 0) {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, bizMsg.mtrBllgDay_H1);
//        } else {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, NSAL0360Constant.MAX_DAY);
//        }
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, bizMsg.mtrBllgDay_H1);
        // END 2016/08/08 K.Kishimoto [QC#12879, MOD]
        return true;
    }
    // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

    private boolean setSvcMemoData(SVC_MEMOTMsg svcMemoTMsg, NSAL0360CMsg bizMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.dsContrPk, bizMsg.dsContrPk_H1);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.dsContrDtlPk, bizMsg.dsContrDtlPk_H1);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.CHANGE_VIEW_PRICING);
            // add start 2016/02/22 CSA Defect#3689
            String svcMemoRsnCd = bizMsg.svcMemoRsnCd_F3.getValue();
            if (ZYPCommonFunc.hasValue(svcMemoRsnCd)) {
                svcMemoRsnCd = ZYPCodeDataUtil.getVarCharConstValue(NSAL0360Constant.SVC_MEMO_RSN_FOR_OVRG_PRC, glblCmpyCd);
            }
            setValue(svcMemoTMsg.svcMemoRsnCd, svcMemoRsnCd);
            // add end 2016/02/22 CSA Defect#3689
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        }

        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, bizMsg.svcCmntTxt_F1);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoRsnCd, bizMsg.svcMemoRsnCd_F3);

        return true;
    }

    private boolean inputCheck(String glblCmpyCd, NSAL0360CMsg bizMsg) {

        boolean result = true;
        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
//        // Check closing day
//        if (NSAL0360Constant.RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH.equals(bizMsg.xxRadioBtn_H1.getValue())) {
//            if (ZYPCommonFunc.hasValue(bizMsg.mtrDplyPerEndDay_H1)) {
//                bizMsg.mtrDplyPerEndDay_H1.setErrorInfo(1, NSAL0360Constant.NSAM0149E, new String[] {"Last Day of the Month", "Closing Day" });
//                result = false;
//            }
//        } else {
//            if (!ZYPCommonFunc.hasValue(bizMsg.mtrDplyPerEndDay_H1)) {
//                bizMsg.mtrDplyPerEndDay_H1.setErrorInfo(1, NSAL0360Constant.NSAM0150E, new String[] {"Closing", "Closing Day" });
//                result = false;
//            }
//        }
//        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
//
//        // Check billing day
//        if (NSAL0360Constant.RADIO_VALUE_BLLG_LAST_DAY_OF_MONTH.equals(bizMsg.xxRadioBtn_H2.getValue())) {
//            if (ZYPCommonFunc.hasValue(bizMsg.mtrBllgDay_H1)) {
//                bizMsg.mtrBllgDay_H1.setErrorInfo(1, NSAL0360Constant.NSAM0149E, new String[] {"End of Month", "Billing Day" });
//                result = false;
//            }
//        } else {
//            if (!ZYPCommonFunc.hasValue(bizMsg.mtrBllgDay_H1)) {
//                bizMsg.mtrBllgDay_H1.setErrorInfo(1, NSAL0360Constant.NSAM0150E, new String[] {"Billing Day", "Billing Day" });
//                result = false;
//            }
//        }
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]

        if (!checkScheduleDate(glblCmpyCd, bizMsg)) {
            result = false;
        }

        return result;
    }

    private boolean checkScheduleDate(String glblCmpyCd, NSAL0360CMsg bizMsg) {

        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            if (!checkScheduleDate(glblCmpyCd, bizMsg, tblNm)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkScheduleDate(String glblCmpyCd, NSAL0360CMsg bizMsg, String tblNm) {
        boolean rtnFlg = true;

        NSAL0360CMsg copyBizMsg = new NSAL0360CMsg();

        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(bizMsg, tblNm);
        EZDMsgArray copyTblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(copyBizMsg, tblNm);

        if (tblArray.getValidCount() == 0) {
            return true;
        }

        int cnt = 0;
        String prevSq = null;
        for (int i = 0; i < tblArray.getValidCount(); i++) {
            EZDMsg ezdMsg = tblArray.get(i);
            String sq = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1").getValue();
            if (prevSq == null || !prevSq.equals(sq)) {
                EZDMsg.copy(tblArray.get(i), null, copyTblArray.get(cnt), null);
                cnt++;
                prevSq = sq;
            }
        }
        copyTblArray.setValidCount(cnt);
        String[][] contents = getBaseContents(copyTblArray);

        // SORT
        S21SortTarget sortTarget = new S21SortTarget(copyTblArray, contents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(NSAL0360CommonLogic.replSfx("bllgSchdFromDt_A1", tblNm), S21SortKey.ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, copyTblArray.getValidCount());

        // START 2015/10/23 T.Tomita [N/A, MOD]
        EZDMsg acMsg = null;
        EZDMsg prevAcMsg = null;
        cnt = copyTblArray.getValidCount();
//        boolean hasMissing = false;
//        boolean hasDuplication = false;
        EZDCDateItem prevThruDtItem;
        EZDCDateItem fromDtItem;
        EZDCDateItem errFromDtItem;
        String prevThruDt;
        String fromDt;
        String schdSqNum;

        for (int i = 0; i < cnt; i++) {
            if (i == 0) {
                continue;
            }
            prevAcMsg = copyTblArray.get(i - 1);
            acMsg = copyTblArray.get(i);
            prevThruDtItem = NSAL0360CommonLogic.getDateValueFromEZDMsg(prevAcMsg, tblNm, "bllgSchdThruDt_A1");
            fromDtItem = NSAL0360CommonLogic.getDateValueFromEZDMsg(acMsg, tblNm, "bllgSchdFromDt_A1");
            prevThruDt = prevThruDtItem.getValue();
            fromDt = fromDtItem.getValue();
            schdSqNum = NSAL0360CommonLogic.getStringValueFromEZDMsg(acMsg, tblNm, "dsContrBllgSchdSqNum_A1").getValue();
            if (fromDt.compareTo(prevThruDt) <= 0) {
//                hasDuplication = true;
//                break;
                errFromDtItem = getSchdDateItem(tblArray, tblNm, "bllgSchdFromDt_A1", schdSqNum);
                if (ZYPCommonFunc.hasValue(errFromDtItem)) {
                    errFromDtItem.setErrorInfo(1, NSAL0360Constant.NSAM0337E);
                }
                rtnFlg = false;
            }
            String addDate = ZYPDateUtil.addDays(prevThruDt, 1);
            if (!addDate.equals(fromDt)) {
//                hasMissing = true;
                errFromDtItem = getSchdDateItem(tblArray, tblNm, "bllgSchdFromDt_A1", schdSqNum);
                if (ZYPCommonFunc.hasValue(errFromDtItem)) {
                    errFromDtItem.setErrorInfo(1, NSAL0360Constant.NSAM0336E);
                }
                rtnFlg = false;
            }
        }

        // START 2016/02/25 T.Aoyagi [QC3422, ADD]
        String contrPrcEffFromDt = "";
        String contrPrcEffThruDt = "";
        BigDecimal dsContrPrcEffPk = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(0), tblNm, "dsContrPrcEffPk_A1").getValue();
        DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSAL0360CommonLogic.findDsContrPrcEff(glblCmpyCd, dsContrPrcEffPk);
        if (prcEffTMsg != null) {
            contrPrcEffFromDt = prcEffTMsg.contrPrcEffFromDt.getValue();
            contrPrcEffThruDt = prcEffTMsg.contrPrcEffThruDt.getValue();
        }
        // END 2016/02/25 T.Aoyagi [QC3422, ADD]

        // Check Price Effective From Date
        EZDCDateItem startSchdFromDtItem = NSAL0360CommonLogic.getDateValueFromEZDMsg(tblArray.get(0), tblNm, "bllgSchdFromDt_A1");
        String startSchdFromDt = startSchdFromDtItem.getValue();
        // START 2016/02/25 T.Aoyagi [QC3422, MOD]
        if (ZYPDateUtil.compare(startSchdFromDt, contrPrcEffFromDt) < 0) {
            // Mod Start 09/06/2016 <QC#12429>
            startSchdFromDtItem.setErrorInfo(1, NSAL0360Constant.NSAM0606E);
            // Mod End   09/06/2016 <QC#12429>
            rtnFlg = false;
        } else if (ZYPDateUtil.compare(startSchdFromDt, contrPrcEffFromDt) > 0) {
            startSchdFromDtItem.setErrorInfo(1, NSAL0360Constant.NSAM0336E);
            rtnFlg = false;
        }
        // END 2016/02/25 T.Aoyagi [QC3422, MOD]

        // Check Price Effective True Date
        int listSize = tblArray.getValidCount();
        String lastSchdSqNum = NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(listSize - 1), tblNm, "dsContrBllgSchdSqNum_A1").getValue();
        EZDCDateItem endSchdThruDtItem = getSchdDateItem(tblArray, tblNm, "bllgSchdThruDt_A1", lastSchdSqNum);
        String endSchdThruDt = endSchdThruDtItem.getValue();
        // START 2016/02/25 T.Aoyagi [QC3422, MOD]
        if (ZYPDateUtil.compare(endSchdThruDt, contrPrcEffThruDt) > 0) {
            // Mod Start 09/06/2016 <QC#12429>
            endSchdThruDtItem.setErrorInfo(1, NSAL0360Constant.NSAM0606E);
            // Mod End   09/06/2016 <QC#12429>
            rtnFlg = false;
        } else if (ZYPDateUtil.compare(endSchdThruDt, contrPrcEffThruDt) < 0) {
            endSchdThruDtItem.setErrorInfo(1, NSAL0360Constant.NSAM0336E);
            rtnFlg = false;
        }
        // END 2016/02/25 T.Aoyagi [QC3422, MOD]
//        if (hasDuplication) {
//            bizMsg.setMessageInfo(NSAL0360Constant.NSAM0337E);
//            rtnFlg = false;
//        } else if (hasMissing) {
//            bizMsg.setMessageInfo(NSAL0360Constant.NSAM0336E);
//            rtnFlg = false;
//        }
        // END 2015/10/23 T.Tomita [N/A, MOD]
        return rtnFlg;
    }

    private String[][] getBaseContents(EZDMsgArray copyTblArray) {
        String[][] contents = null;
        try {
            Method[] methods = copyTblArray.get(0).getClass().getDeclaredMethods();
            for (Method m : methods) {
                if ("getBaseContents".equals(m.getName())) {
                    contents = (String[][]) m.invoke(copyTblArray.get(0));
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contents;
    }

    // START 2015/10/23 T.Tomita [N/A, ADD]
    // START 2016/01/14 T.Tomita [QC#2950, MOD]
    private boolean callScheduleApi(NSAL0360CMsg cMsg, String glblCmpyCd, String chngRngFlg) {
        NSZC047001 api = new NSZC047001();
        NSZC047002PMsg pMsg;
        EZDMsgArray tblArray;
        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
            if (tblArray == null || tblArray.getValidCount() == 0) {
                continue;
            }

            // execute Schedule API
            pMsg = setPmsg(cMsg, glblCmpyCd, chngRngFlg, tblNm, tblArray);
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                cMsg.setMessageInfo(NSAL0360Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + cMsg.dsContrDtlPk_H1.getValue() });
                return false;
            }
        }
        return true;
    }
    // END 2016/01/14 T.Tomita [QC#2950, MOD]

    // START 2016/01/14 T.Tomita [QC#2950, MOD]
    private NSZC047002PMsg setPmsg(NSAL0360CMsg cMsg, String glblCmpyCd, String chngRngFlg, String tblNm, EZDMsgArray tblArray) {
        NSZC047002PMsg pMsg = new NSZC047002PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "02");
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, cMsg.dsContrDtlPk_H1);

        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);

        String mtrCloDay = cMsg.mtrCloDay_H1.getValue();
        // START 2016/05/18 T.Kanasaka [QC#2184, DEL]
//        if (NSAL0360Constant.RADIO_VALUE_CLOSING_DAY.compareTo(cMsg.xxRadioBtn_H1.getValue()) != 0) {
//            mtrCloDay = NSAL0360Constant.MAX_DAY;
//        }
        // END 2016/05/18 T.Kanasaka [QC#2184, DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.mtrCloDay, mtrCloDay);

        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgTmgCd, cMsg.mtrBllgTmgCd_H1);

        String mtrBllgDay = cMsg.mtrBllgDay_H1.getValue();
        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        if (NSAL0360Constant.RADIO_VALUE_BLLG_DAY.compareTo(cMsg.xxRadioBtn_H2.getValue()) != 0) {
//            mtrBllgDay = NSAL0360Constant.MAX_DAY;
//        }
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgDay, mtrBllgDay);

        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, cMsg.contrEffFromDt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, cMsg.contrEffThruDt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.chngRngFlg, chngRngFlg);

        NSZC047002_xxMtrLineListPMsgArray xxMtrLineList = pMsg.xxMtrLineList;
        //Mod Start 09/06/2016 <QC#12429>
        String ccyCd = cMsg.ccyCd_HH.getValue();
        int topSchdSq = -1;
        int useTopSchdSq = 1;
        int i = 0;
        int j = 0;
        for (; i < tblArray.getValidCount(); i++) {
            int wkTopSchdSq = Integer.parseInt(NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(i), tblNm, "dsContrBllgSchdSqNum_A1").getValue());
            if (topSchdSq != wkTopSchdSq) {
                topSchdSq = wkTopSchdSq;
                String bllgCycleCd = NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(i), tblNm, "bllgCycleCd_A1").getValue();
                String effFromDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdFromDt_A1").getValue();
                String effThruDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdThruDt_A1").getValue();
                NSZC047021PMsg m21PMsg = calcPerSeqNum(pMsg, bllgCycleCd, ccyCd, effFromDt, effThruDt);
                int setCnt = 0;
                for (int setIdx = i; setIdx < tblArray.getValidCount(); setIdx++) {
                    int curTopSchdSq = Integer.parseInt(NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(setIdx), tblNm, "dsContrBllgSchdSqNum_A1").getValue());
                    if (topSchdSq != curTopSchdSq) {
                        break;
                    }
                    setCnt++;
                }
                for (int k = 0; k < m21PMsg.xxBaseLineList.getValidCount(); k++) {
                    for (int l = i; l < i + setCnt; l++) {
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).dsContrPrcEffPk_ML, NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(l), tblNm, "dsContrPrcEffPk_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).dsContrPrcEffSqNum_ML, NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(l), tblNm, "dsContrPrcEffSqNum_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).effFromDt_ML, m21PMsg.xxBaseLineList.no(k).bllgSchdFromDt_BL);
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).effThruDt_ML, m21PMsg.xxBaseLineList.no(k).bllgSchdThruDt_BL);
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).mtrBllgCycleCd_ML, NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(l), tblNm, "bllgCycleCd_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).dsContrBllgMtrPk_ML, NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(l), tblNm, "dsContrBllgMtrPk_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).contrXsCopyPk_ML, NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(l), tblNm, "contrXsCopyPk_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).xsMtrCopyQty_ML, NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(l), tblNm, "xsMtrCopyQty_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).xsMtrAmtRate_ML, NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(l), tblNm, "xsMtrAmtRate_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).xsMtrFirstFlg_ML, NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(l), tblNm, "xsMtrFirstFlg_A1"));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).dsContrBllgSchdSqNum_ML, Integer.toString(useTopSchdSq));
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).perBllgCycleCd_ML, m21PMsg.xxBaseLineList.no(k).perBllgCycleCd_BL);
                        ZYPEZDItemValueSetter.setValue(xxMtrLineList.no(j).perSchdNum_ML, m21PMsg.xxBaseLineList.no(k).perSchdNum_BL);
                        j++;
                    }
                    useTopSchdSq++;
                }
                i = i + setCnt -1; 
            }
        }
        xxMtrLineList.setValidCount(j);
        //Mod End 09/06/2016 <QC#12429>
        return pMsg;
    }
    // END 2016/01/14 T.Tomita [QC#2950, MOD]

    //Add Start 09/06/2016 <QC#12429>
    private static NSZC047021PMsg calcPerSeqNum(NSZC047002PMsg m02pMsg, String bllgCycleCd, String ccyCd, String effFromDt, String effThruDt) {
        NSZC047021PMsg m21PMsg = new NSZC047021PMsg();
        setValue(m21PMsg.glblCmpyCd, m02pMsg.glblCmpyCd);
        setValue(m21PMsg.xxModeCd, "21");
        setValue(m21PMsg.slsDt, m02pMsg.slsDt);
        setValue(m21PMsg.dsContrDtlPk, m02pMsg.dsContrDtlPk);
        setValue(m21PMsg.contrCloDay, m02pMsg.mtrCloDay);
        setValue(m21PMsg.baseBllgTmgCd, m02pMsg.mtrBllgTmgCd);
        setValue(m21PMsg.baseBllgCycleCd, bllgCycleCd);
        setValue(m21PMsg.ccyCd, ccyCd);
        setValue(m21PMsg.effFromDt, effFromDt);
        setValue(m21PMsg.effThruDt, effThruDt);
        setValue(m21PMsg.basePrcDealAmt, BigDecimal.ZERO);

        NSZC047001 api = new NSZC047001();
        api.execute(m21PMsg, ONBATCH_TYPE.ONLINE);
        return m21PMsg;
    }
    //Add End   09/06/2016 <QC#12429>

    private EZDCDateItem getSchdDateItem(EZDMsgArray tblArray, String tblNm, String itemName, String schdSqNum) {
        EZDCDateItem rtnDateItem = null;
        String targetSqNum;
        for (int i = 0; i < tblArray.getValidCount(); i++) {
            EZDMsg ezdMsg = tblArray.get(i);
            targetSqNum = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1").getValue();
            if (schdSqNum.equals(targetSqNum)) {
                rtnDateItem = NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, itemName);
                break;
            }
        }
        return rtnDateItem;
    }

    private void setSameSqInputItem(EZDMsgArray tblArray, String tblNm) {
        String preSchdSqNum = "";
        String schdSqNum = "";
        // input Item
        BigDecimal perSchdNum = null;
        String perBllgCycleCd = null;
        String bllgSchdFromDt = null;
        String bllgSchdThruDt = null;
        for (int i = 0; i < tblArray.getValidCount(); i++) {
            schdSqNum = NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(i), tblNm, "dsContrBllgSchdSqNum_A1").getValue();
            if (preSchdSqNum.equals(schdSqNum)) {
                // equals Schedule Sequence
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(i), tblNm, "perSchdNum_A1"), perSchdNum);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(i), tblNm, "perBllgCycleCd_A1"), perBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdFromDt_A1"), bllgSchdFromDt);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdThruDt_A1"), bllgSchdThruDt);
            } else {
                preSchdSqNum = schdSqNum;
                perSchdNum = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(tblArray.get(i), tblNm, "perSchdNum_A1").getValue();
                perBllgCycleCd = NSAL0360CommonLogic.getStringValueFromEZDMsg(tblArray.get(i), tblNm, "perBllgCycleCd_A1").getValue();
                bllgSchdFromDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdFromDt_A1").getValue();
                bllgSchdThruDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(tblArray.get(i), tblNm, "bllgSchdThruDt_A1").getValue();
            }
        }
    }
    // END 2015/10/23 T.Tomita [N/A, ADD]
    // START 2016/03/02 T.Tomita [QC#4131, ADD]
    private boolean inputSvcMemoCheck(NSAL0360CMsg bizMsg) {

        boolean result = true;
        if (!ZYPCommonFunc.hasValue(bizMsg.svcMemoRsnCd_F3)) {
            bizMsg.svcMemoRsnCd_F3.setErrorInfo(1, NSAL0360Constant.ZZM9000E, new String[] {"Reason Code" });
            result = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.svcCmntTxt_F1)) {
            bizMsg.svcCmntTxt_F1.setErrorInfo(1, NSAL0360Constant.ZZM9000E, new String[] {"Comment" });
            result = false;
        }

        return result;
    }
    // END 2016/03/02 T.Tomita [QC#4131, ADD]
}
