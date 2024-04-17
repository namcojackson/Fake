/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0340;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0340.constant.NSAL0340Constant;
import business.db.DS_CONTR_SKIP_RECOVTMsg;
import business.db.DS_CONTR_SKIP_RECOVTMsgArray;
import business.parts.NSZC047003PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/10   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/20   Hitachi         T.Tomita        Update          N/A
 * 2018/03/09   Hitachi         K.Kojima        Update          QC#23600
 *</pre>
 */
public class NSAL0340BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0340Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0340Scrn00_CMN_Submit((NSAL0340CMsg) cMsg, (NSAL0340SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0340CMsg
     * @param sMsg NSAL0340SMsg
     */
    private void doProcess_NSAL0340Scrn00_CMN_Submit(NSAL0340CMsg cMsg, NSAL0340SMsg sMsg) {

        String svcInvChrgTpCd = cMsg.svcInvChrgTpCd.getValue();

        if (!ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            allDeleteMtrChrg(cMsg);
        }

        // START 2018/03/09 K.Kojima [QC#23600,ADD]
        List<BigDecimal> childDsContrDtlPrForAgg = NSAL0340Query.getInstance().getChildDsContrDtlPkForAgg(cMsg, svcInvChrgTpCd);
        // END 2018/03/09 K.Kojima [QC#23600,ADD]

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0340_ACMsg acMsg = cMsg.A.no(i);
            NSAL0340_ASMsg asMsg = sMsg.A.no(i);

            String submitSkipTpCd = acMsg.skipRecovTpCd_A0.getValue();
            String initSkipTpCd = asMsg.skipRecovTpCd_A0.getValue();

            if (!submitSkipTpCd.equals(initSkipTpCd)) {
                if (SKIP_RECOV_TP.NONE.equals(initSkipTpCd)) {
                    // insert
                    // START 2018/03/09 K.Kojima [QC#23600,MOD]
                    // if (!doInsertSkipRecovInfo(cMsg, acMsg)) {
                    if (!doInsertSkipRecovInfo(cMsg, acMsg, childDsContrDtlPrForAgg)) {
                    // END 2018/03/09 K.Kojima [QC#23600,MOD]
                        return;
                    }
                } else {
                    if (SKIP_RECOV_TP.NONE.equals(submitSkipTpCd)) {
                        // delete
                        // START 2018/03/09 K.Kojima [QC#23600,MOD]
                        // if (!doDeleteSkipRecovInfo(cMsg, acMsg)) {
                        if (!doDeleteSkipRecovInfo(cMsg, acMsg, childDsContrDtlPrForAgg)) {
                        // END 2018/03/09 K.Kojima [QC#23600,MOD]
                            return;
                        }
                    } else {
                        // update
                        // START 2018/03/09 K.Kojima [QC#23600,MOD]
                        // if (!doUpdateSkipRecovInfo(cMsg, acMsg)) {
                        if (!doUpdateSkipRecovInfo(cMsg, acMsg, childDsContrDtlPrForAgg)) {
                        // END 2018/03/09 K.Kojima [QC#23600,MOD]
                            return;
                        }
                    }
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            allInsertMtrChrg(cMsg);
        }

        // START 2015/10/20 T.Tomita [N/A, ADD]
        // call NSZC0470 Contract Billing Schedule API
        if (ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            // START 2018/03/09 K.Kojima [QC#23600,MOD]
            // callScheduleApi(cMsg, svcInvChrgTpCd);
            callScheduleApi(cMsg, svcInvChrgTpCd, childDsContrDtlPrForAgg);
            // END 2018/03/09 K.Kojima [QC#23600,MOD]
        } else {
            // START 2018/03/09 K.Kojima [QC#23600,MOD]
            // if (!callScheduleApi(cMsg, SVC_INV_CHRG_TP.BASE_CHARGE)) {
            if (!callScheduleApi(cMsg, SVC_INV_CHRG_TP.BASE_CHARGE, childDsContrDtlPrForAgg)) {
                // END 2018/03/09 K.Kojima [QC#23600,MOD]
                return;
            }
            // START 2018/03/09 K.Kojima [QC#23600,MOD]
            // callScheduleApi(cMsg, SVC_INV_CHRG_TP.METER_CHARGE);
            callScheduleApi(cMsg, SVC_INV_CHRG_TP.METER_CHARGE, childDsContrDtlPrForAgg);
            // END 2018/03/09 K.Kojima [QC#23600,MOD]
        }
        // END 2015/10/20 T.Tomita [N/A, ADD]
    }

    /**
     * all Delete Skip Recover Info for Meter Charge
     * @param cMsg NSAL0340CMsg
     */
    private void allDeleteMtrChrg(NSAL0340CMsg cMsg) {

        List<DS_CONTR_SKIP_RECOVTMsg> deleteTMsgList = new ArrayList<DS_CONTR_SKIP_RECOVTMsg>();
        DS_CONTR_SKIP_RECOVTMsgArray tMsgArray = getSkipRecovInfoAllMth(cMsg, SVC_INV_CHRG_TP.METER_CHARGE);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            DS_CONTR_SKIP_RECOVTMsg dltTMsg = (DS_CONTR_SKIP_RECOVTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsgArray.no(i));
            deleteTMsgList.add(dltTMsg);
        }

        if (!deleteTMsgList.isEmpty()) {
            S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(new DS_CONTR_SKIP_RECOVTMsg[0]));
        }
    }

    /**
     * all Insert Skip Recover Info for Meter Charge
     * @param cMsg NSAL0340CMsg
     */
    private void allInsertMtrChrg(NSAL0340CMsg cMsg) {

        List<DS_CONTR_SKIP_RECOVTMsg> insertTMsgList = new ArrayList<DS_CONTR_SKIP_RECOVTMsg>();
        DS_CONTR_SKIP_RECOVTMsgArray tMsgArray = getSkipRecovInfoAllMth(cMsg, SVC_INV_CHRG_TP.BASE_CHARGE);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            DS_CONTR_SKIP_RECOVTMsg tMsg = tMsgArray.no(i);

            DS_CONTR_SKIP_RECOVTMsg insTMsg = new DS_CONTR_SKIP_RECOVTMsg();
            insTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            insTMsg.dsContrSkipRecovPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SKIP_RECOV_SQ));
            insTMsg.dsContrDtlPk.setValue(cMsg.dsContrDtlPk.getValue());
            insTMsg.skipRecovMth.setValue(tMsg.skipRecovMth.getValue());
            insTMsg.skipRecovTpCd.setValue(tMsg.skipRecovTpCd.getValue());
            insTMsg.svcInvChrgTpCd.setValue(SVC_INV_CHRG_TP.METER_CHARGE);
            insertTMsgList.add(insTMsg);
        }

        if (!insertTMsgList.isEmpty()) {
            S21FastTBLAccessor.insert(insertTMsgList.toArray(new DS_CONTR_SKIP_RECOVTMsg[0]));
        }
    }

    /**
     * do insert DS_CONTR_SKIP_RECOV
     * @param cMsg NSAL0340CMsg
     * @param acMsg NSAL0340_ACMsg
     * @return insert success : true
     */
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // private boolean doInsertSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg) {
    private boolean doInsertSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg, List<BigDecimal> childDsContrDtlPrForAgg) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]

        // check exclusion
        String svcInvChrgTpCd = cMsg.svcInvChrgTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            svcInvChrgTpCd = SVC_INV_CHRG_TP.BASE_CHARGE;
        }

        if (NSAL0340Query.getInstance().isExistSkipRecovInfo(cMsg, acMsg, svcInvChrgTpCd)) {
            cMsg.setMessageInfo(NSAL0340Constant.ZZZM9004E);
            return false;
        }

        DS_CONTR_SKIP_RECOVTMsg insTMsg = new DS_CONTR_SKIP_RECOVTMsg();
        insTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        insTMsg.dsContrSkipRecovPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SKIP_RECOV_SQ));
        insTMsg.dsContrDtlPk.setValue(cMsg.dsContrDtlPk.getValue());
        insTMsg.skipRecovMth.setValue(acMsg.skipRecovMth_A0.getValue());
        insTMsg.skipRecovTpCd.setValue(acMsg.skipRecovTpCd_A0.getValue());
        insTMsg.svcInvChrgTpCd.setValue(svcInvChrgTpCd);
        EZDTBLAccessor.insert(insTMsg);

        // START 2018/03/09 K.Kojima [QC#23600,ADD]
        for (BigDecimal dsContrDtlPk : childDsContrDtlPrForAgg) {
            insTMsg = new DS_CONTR_SKIP_RECOVTMsg();
            insTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            insTMsg.dsContrSkipRecovPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SKIP_RECOV_SQ));
            insTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
            insTMsg.skipRecovMth.setValue(acMsg.skipRecovMth_A0.getValue());
            insTMsg.skipRecovTpCd.setValue(acMsg.skipRecovTpCd_A0.getValue());
            insTMsg.svcInvChrgTpCd.setValue(svcInvChrgTpCd);
            EZDTBLAccessor.insert(insTMsg);
        }
        // END 2018/03/09 K.Kojima [QC#23600,ADD]

        return true;
    }

    /**
     * do delete DS_CONTR_SKIP_RECOV
     * @param cMsg NSAL0340CMsg
     * @param acMsg NSAL0340_ACMsg
     * @return insert success : true
     */
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // private boolean doDeleteSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg) {
    private boolean doDeleteSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg, List<BigDecimal> childDsContrDtlPrForAgg) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]

        // check exclusion
        String svcInvChrgTpCd = cMsg.svcInvChrgTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            svcInvChrgTpCd = SVC_INV_CHRG_TP.BASE_CHARGE;
        }

        // START 2018/03/09 K.Kojima [QC#23600,MOD]
        // DS_CONTR_SKIP_RECOVTMsgArray tMsgArray = getSkipRecovInfo(cMsg, acMsg, svcInvChrgTpCd);
        DS_CONTR_SKIP_RECOVTMsgArray tMsgArray = getSkipRecovInfo(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk.getValue(), acMsg, svcInvChrgTpCd);
        // END 2018/03/09 K.Kojima [QC#23600,MOD]
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAL0340Constant.ZZZM9004E);
            return false;
        }

        DS_CONTR_SKIP_RECOVTMsg dltTMsg = tMsgArray.no(0);

        String findEzUpTime = acMsg.ezUpTime_A0.getValue();
        String findEzUpTimeZone = acMsg.ezUpTimeZone_A0.getValue();
        String currentEzUpTime = dltTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = dltTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NSAL0340Constant.ZZZM9004E);
            return false;
        }

        dltTMsg = (DS_CONTR_SKIP_RECOVTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dltTMsg);
        EZDTBLAccessor.remove(dltTMsg);

        // START 2018/03/09 K.Kojima [QC#23600,ADD]
        for (BigDecimal dsContrDtlPk : childDsContrDtlPrForAgg) {
            tMsgArray = getSkipRecovInfo(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, acMsg, svcInvChrgTpCd);
            if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
                continue;
            }
            dltTMsg = (DS_CONTR_SKIP_RECOVTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsgArray.no(0));
            EZDTBLAccessor.remove(dltTMsg);
        }
        // END 2018/03/09 K.Kojima [QC#23600,ADD]

        return true;
    }

    /**
     * do update DS_CONTR_SKIP_RECOV
     * @param cMsg NSAL0340CMsg
     * @param acMsg NSAL0340_ACMsg
     * @return insert success : true
     */
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // private boolean doUpdateSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg) {
    private boolean doUpdateSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg, List<BigDecimal> childDsContrDtlPrForAgg) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]

        // check exclusion
        String svcInvChrgTpCd = cMsg.svcInvChrgTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            svcInvChrgTpCd = SVC_INV_CHRG_TP.BASE_CHARGE;
        }

        // START 2018/03/09 K.Kojima [QC#23600,MOD]
        // DS_CONTR_SKIP_RECOVTMsgArray tMsgArray = getSkipRecovInfo(cMsg, acMsg, svcInvChrgTpCd);
        DS_CONTR_SKIP_RECOVTMsgArray tMsgArray = getSkipRecovInfo(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk.getValue(), acMsg, svcInvChrgTpCd);
        // END 2018/03/09 K.Kojima [QC#23600,MOD]
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAL0340Constant.ZZZM9004E);
            return false;
        }

        DS_CONTR_SKIP_RECOVTMsg updTMsg = tMsgArray.no(0);

        String findEzUpTime = acMsg.ezUpTime_A0.getValue();
        String findEzUpTimeZone = acMsg.ezUpTimeZone_A0.getValue();
        String currentEzUpTime = updTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = updTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NSAL0340Constant.ZZZM9004E);
            return false;
        }

        updTMsg = (DS_CONTR_SKIP_RECOVTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
        updTMsg.skipRecovTpCd.setValue(acMsg.skipRecovTpCd_A0.getValue());
        EZDTBLAccessor.update(updTMsg);

        // START 2018/03/09 K.Kojima [QC#23600,ADD]
        for (BigDecimal dsContrDtlPk : childDsContrDtlPrForAgg) {
            tMsgArray = getSkipRecovInfo(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, acMsg, svcInvChrgTpCd);
            if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
                continue;
            }
            updTMsg = (DS_CONTR_SKIP_RECOVTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsgArray.no(0));
            updTMsg.skipRecovTpCd.setValue(acMsg.skipRecovTpCd_A0.getValue());
            EZDTBLAccessor.update(updTMsg);
        }
        // END 2018/03/09 K.Kojima [QC#23600,ADD]

        return true;
    }

    /**
     * get DS_CONTR_SKIP_RECOVTMsgArray
     * @param cMsg NSAL0340CMsg
     * @param svcInvChrgTpCd Service Invoice Charge Type Code
     * @return DS_CONTR_SKIP_RECOVTMsgArray
     */
    private DS_CONTR_SKIP_RECOVTMsgArray getSkipRecovInfoAllMth(NSAL0340CMsg cMsg, String svcInvChrgTpCd) {

        DS_CONTR_SKIP_RECOVTMsg cond = new DS_CONTR_SKIP_RECOVTMsg();
        cond.setSQLID("001");
        cond.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        cond.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
        cond.setConditionValue("svcInvChrgTpCd01", svcInvChrgTpCd);
        return (DS_CONTR_SKIP_RECOVTMsgArray) EZDTBLAccessor.findByCondition(cond);
    }

    /**
     * get DS_CONTR_SKIP_RECOVTMsgArray
     * @param cMsg NSAL0340CMsg
     * @param acMsg NSAL0340_ACMsg
     * @param svcInvChrgTpCd Service Invoice Charge Type Code
     * @return DS_CONTR_SKIP_RECOVTMsgArray
     */
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // private DS_CONTR_SKIP_RECOVTMsgArray getSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg, String svcInvChrgTpCd) {
    private DS_CONTR_SKIP_RECOVTMsgArray getSkipRecovInfo(String glblCmpyCd, BigDecimal dsContrDtlPk, NSAL0340_ACMsg acMsg, String svcInvChrgTpCd) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]

        DS_CONTR_SKIP_RECOVTMsg cond = new DS_CONTR_SKIP_RECOVTMsg();
        cond.setSQLID("002");
        // START 2018/03/09 K.Kojima [QC#23600,MOD]
        // cond.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        // cond.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        // END 2018/03/09 K.Kojima [QC#23600,MOD]
        cond.setConditionValue("skipRecovMth01", acMsg.skipRecovMth_A0.getValue());
        cond.setConditionValue("svcInvChrgTpCd01", svcInvChrgTpCd);
        return (DS_CONTR_SKIP_RECOVTMsgArray) EZDTBLAccessor.findByCondition(cond);
    }

    // START 2015/10/20 T.Tomita [N/A, ADD]
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // private boolean callScheduleApi(NSAL0340CMsg cMsg, String svcInvChrgTpCd) {
    private boolean callScheduleApi(NSAL0340CMsg cMsg, String svcInvChrgTpCd, List<BigDecimal> childDsContrDtlPrForAgg) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]
        NSZC047001 api = new NSZC047001();
        // START 2018/03/09 K.Kojima [QC#23600,MOD]
        // NSZC047003PMsg pMsg = setPmsg(cMsg, svcInvChrgTpCd);
        NSZC047003PMsg pMsg = setPmsg(cMsg, cMsg.dsContrDtlPk.getValue(), svcInvChrgTpCd);
        // END 2018/03/09 K.Kojima [QC#23600,MOD]
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            cMsg.setMessageInfo(NSAL0340Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + cMsg.dsContrDtlPk.getValue() });
            return false;
        }
        // START 2018/03/09 K.Kojima [QC#23600,ADD]
        for (BigDecimal dsContrDtlPk : childDsContrDtlPrForAgg) {
            api = new NSZC047001();
            pMsg = setPmsg(cMsg, dsContrDtlPk, svcInvChrgTpCd);
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                cMsg.setMessageInfo(NSAL0340Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + dsContrDtlPk });
                return false;
            }
        }
        // END 2018/03/09 K.Kojima [QC#23600,ADD]
        return true;
    }

    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // private NSZC047003PMsg setPmsg(NSAL0340CMsg cMsg, String svcInvChrgTpCd) {
    private NSZC047003PMsg setPmsg(NSAL0340CMsg cMsg, BigDecimal dsContrDtlPk, String svcInvChrgTpCd) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]
        NSZC047003PMsg pMsg = new NSZC047003PMsg();
        // Header
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSAL0340Constant.SKIP_MONTH);
        // START 2018/03/09 K.Kojima [QC#23600,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, cMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        // END 2018/03/09 K.Kojima [QC#23600,MOD]
        String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        // Detail
        int i = 0;
        for (i = 0; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSkipList.no(i).skipRecovMth_SL, cMsg.A.no(i).skipRecovMth_A0);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSkipList.no(i).skipRecovTpCd_SL, cMsg.A.no(i).skipRecovTpCd_A0);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSkipList.no(i).svcInvChrgTpCd_SL, svcInvChrgTpCd);
        }
        pMsg.xxSkipList.setValidCount(i);
        return pMsg;
    }
    // END 2015/10/20 T.Tomita [N/A, ADD]
}
