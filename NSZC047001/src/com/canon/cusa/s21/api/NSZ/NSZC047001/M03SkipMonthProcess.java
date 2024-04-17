package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.SVC_CONTR_BLLGTMsg;
import business.parts.NSZC047003PMsg;
import business.parts.NSZC047003_xxSkipListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcRvsSchdBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcRvsSchdLineBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcRvsSchdDt;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/28/2016   Hitachi         T.Aoyagi        Update          QC#3095
 * 08/08/2016   Hitachi         T.Aoyagi        Update          QC#4959
 * 10/21/2016   Hitachi         K.Kishimoto     Update          QC#15232
 * 2017/10/30   Hitachi         K.Kitachi       Update          QC#21449
 * 2018/03/09   Hitachi         K.Kojima        Update          QC#23600
 *</pre>
 */
public class M03SkipMonthProcess implements ZYPConstant {

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        skipMonthProcess(msgMap);
    }

    private void skipMonthProcess(S21ApiMessageMap msgMap) {

        NSZC047003PMsg pMsg = (NSZC047003PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        String svcBllgSkipMthTp = ZYPCodeDataUtil.getVarCharConstValue(SVC_BLLG_SKIP_MTH_TP, glblCmpyCd);
        if (!hasValue(svcBllgSkipMthTp)) {
            msgMap.addXxMsgIdWithPrm(ZZZM9006E, new String[] {"VAR_CHAR_CONST:SVC_BLLG_SKIP_MTH_TP" });
            return;
        }
        if (pMsg.xxSkipList.getValidCount() == 0) {
            return;
        }
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String slsDt = pMsg.slsDt.getValue();

        List<NSZC047003_xxSkipListPMsg> baseList = getBaseList(pMsg);
        // Mod Start 10/21/2016 <QC#15232>
        if (!baseList.isEmpty()) {
            // START 2018/03/09 K.Kojima [QC#23600,MOD]
            // List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getBllgSchdForSkipMonthBase(glblCmpyCd, dsContrDtlPk, slsDt);
            List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getBllgSchdForSkipMonthBase(glblCmpyCd, dsContrDtlPk);
            // END 2018/03/09 K.Kojima [QC#23600,MOD]
            String tmpRvcSchdDt = null;
            for (Map<String, Object> bllgSchdInfo : bllgSchdList) {

                String skipRecovTp = getSkipRecovTp(baseList, bllgSchdInfo, svcBllgSkipMthTp);

                CalcRvsSchdBean calcRvsSchdBean = new CalcRvsSchdBean();
                calcRvsSchdBean.setSkipRecovTpCd(skipRecovTp);

                if (SKIP_RECOV_TP.SKIP.equals(skipRecovTp)) {
                    tmpRvcSchdDt = null;
                    calcRvsSchdBean.setBllgSkipFlg(FLG_ON_Y);
                    calcRvsSchdBean.setRvsSchdDt(null);

                } else if (SKIP_RECOV_TP.SKIP_AND_RECOVER.equals(skipRecovTp)) {
                    String rvcSchdDt = null;
                    if (!hasValue(tmpRvcSchdDt)) {
                        String bllgSchdThruDt = (String) bllgSchdInfo.get("BLLG_SCHD_THRU_DT");
                        rvcSchdDt = NSZC0470Query.getInstance().getRvcSchdDt(glblCmpyCd, dsContrDtlPk, null, bllgSchdThruDt, FLG_ON_Y);
                        if (!hasValue(rvcSchdDt)) {
                            rvcSchdDt = (String) bllgSchdInfo.get("NEXT_BLLG_DT");
                        }
                    } else {
                        rvcSchdDt = tmpRvcSchdDt;
                    }
                    tmpRvcSchdDt = rvcSchdDt;
                    calcRvsSchdBean.setBllgSkipFlg(FLG_OFF_N);
                    calcRvsSchdBean.setRvsSchdDt(rvcSchdDt);

                } else {
                    tmpRvcSchdDt = null;
                    calcRvsSchdBean.setBllgSkipFlg(FLG_OFF_N);
                    calcRvsSchdBean.setRvsSchdDt(null);
                }

                BigDecimal dsContrBllgSchdPk = (BigDecimal) bllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
                updateBllgSchd(msgMap, dsContrBllgSchdPk, calcRvsSchdBean);

                if (FLG_ON_Y.equals(calcRvsSchdBean.getBllgSkipFlg())) {
                    // delete Service Contract Billing
                    NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk);
                } else {
                    // update Service Contract Billing
                    updateSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk, calcRvsSchdBean.getRvsSchdDt());
                }
            }
        }

        List<NSZC047003_xxSkipListPMsg> usageList = getUsageList(pMsg);

        if (!usageList.isEmpty()) {

            // START 2018/03/09 K.Kojima [QC#23600,MOD]
            // List<BigDecimal> bllgMtrList = NSZC0470Query.getInstance().getBllgMtrForSkipMonth(glblCmpyCd, dsContrDtlPk, slsDt);
            List<BigDecimal> bllgMtrList = NSZC0470Query.getInstance().getBllgMtrForSkipMonth(glblCmpyCd, dsContrDtlPk);
            // END 2018/03/09 K.Kojima [QC#23600,MOD]
            String tmpRvcSchdDt = null;
            for (BigDecimal bllgMtrPk : bllgMtrList) {

                // START 2018/03/09 K.Kojima [QC#23600,MOD]
                // List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getBllgSchdForSkipMonthUsg(glblCmpyCd, dsContrDtlPk, slsDt, bllgMtrPk);
                List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getBllgSchdForSkipMonthUsg(glblCmpyCd, dsContrDtlPk, bllgMtrPk);
                // END 2018/03/09 K.Kojima [QC#23600,MOD]
                for (Map<String, Object> bllgSchdInfo : bllgSchdList) {

                    String skipRecovTp = getSkipRecovTp(usageList, bllgSchdInfo, svcBllgSkipMthTp);

                    CalcRvsSchdBean calcRvsSchdBean = new CalcRvsSchdBean();
                    calcRvsSchdBean.setSkipRecovTpCd(skipRecovTp);

                    if (SKIP_RECOV_TP.SKIP.equals(skipRecovTp)) {
                        tmpRvcSchdDt = null;
                        calcRvsSchdBean.setBllgSkipFlg(FLG_ON_Y);
                        calcRvsSchdBean.setRvsSchdDt(null);

                    } else if (SKIP_RECOV_TP.SKIP_AND_RECOVER.equals(skipRecovTp)) {
                        String rvcSchdDt = null;
                        if (!hasValue(tmpRvcSchdDt)) {
                            String bllgSchdThruDt = (String) bllgSchdInfo.get("BLLG_SCHD_THRU_DT");
                            rvcSchdDt = NSZC0470Query.getInstance().getRvcSchdDt(glblCmpyCd, dsContrDtlPk, bllgMtrPk, bllgSchdThruDt, FLG_OFF_N);
                            if (!hasValue(rvcSchdDt)) {
                                rvcSchdDt = (String) bllgSchdInfo.get("NEXT_BLLG_DT");
                            }
                        } else {
                            rvcSchdDt = tmpRvcSchdDt;
                        }
                        tmpRvcSchdDt = rvcSchdDt;
                        calcRvsSchdBean.setBllgSkipFlg(FLG_OFF_N);
                        calcRvsSchdBean.setRvsSchdDt(rvcSchdDt);

                    } else {
                        tmpRvcSchdDt = null;
                        calcRvsSchdBean.setBllgSkipFlg(FLG_OFF_N);
                        calcRvsSchdBean.setRvsSchdDt(null);
                    }

                    BigDecimal dsContrBllgSchdPk = (BigDecimal) bllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");

                    updateBllgSchd(msgMap, dsContrBllgSchdPk, calcRvsSchdBean);
                    String dsContrDtlTpCd = (String) bllgSchdInfo.get("DS_CONTR_DTL_TP_CD");
                    if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {

                        List<Map<String, Object>> fleetMachSchdList = NSZC0470Query.getInstance().getBllgSchdForFleetMach(glblCmpyCd, dsContrBllgSchdPk);
                        for (Map<String, Object> fleetMachSchdInfo : fleetMachSchdList) {
                            BigDecimal prntDsContrBllgSchdPk = (BigDecimal) fleetMachSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
                            updateBllgSchd(msgMap, prntDsContrBllgSchdPk, calcRvsSchdBean);
                        }
                    }

                    if (FLG_ON_Y.equals(calcRvsSchdBean.getBllgSkipFlg())) {
                        // delete Service Contract Billing
                        // START 2017/10/30 K.Kitachi [QC#21449, ADD]
                        List<BigDecimal> childDsContrBllgSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, dsContrBllgSchdPk);
                        NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childDsContrBllgSchdPkList);
                        // END 2017/10/30 K.Kitachi [QC#21449, ADD]
                        NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk);
                    } else {
                        // update Service Contract Billing
                        updateSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk, calcRvsSchdBean.getRvsSchdDt());
                    }
                }
            }
        }
        // Mod End   10/21/2016 <QC#15232>
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047003PMsg pMsg = (NSZC047003PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[] {"Global Company Code" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[] {"Salse Date" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[] {"DS Contract Detail PK" });
        for (int i = 0; i < pMsg.xxSkipList.getValidCount(); i++) {
            NSZC047003_xxSkipListPMsg linePMsg = pMsg.xxSkipList.no(i);
            NSZC047001CommonLogic.mandatoryCheck(msgMap, linePMsg.skipRecovMth_SL, ZZZM9007E, new String[] {"Skip Recover Month" });
            NSZC047001CommonLogic.mandatoryCheck(msgMap, linePMsg.skipRecovTpCd_SL, ZZZM9007E, new String[] {"Skip Recover Type Code" });
            NSZC047001CommonLogic.mandatoryCheck(msgMap, linePMsg.svcInvChrgTpCd_SL, ZZZM9007E, new String[] {"Service Invoice Charge Type Code" });
        }
    }

    private List<NSZC047003_xxSkipListPMsg> getBaseList(NSZC047003PMsg pMsg) {

        List<NSZC047003_xxSkipListPMsg> baseList = new ArrayList<NSZC047003_xxSkipListPMsg>();

        for (int i = 0; i < pMsg.xxSkipList.getValidCount(); i++) {
            String svcInvChrgTp = pMsg.xxSkipList.no(i).svcInvChrgTpCd_SL.getValue();
            if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTp)) {
                baseList.add(pMsg.xxSkipList.no(i));
            }
        }
        return baseList;
    }

    private List<NSZC047003_xxSkipListPMsg> getUsageList(NSZC047003PMsg pMsg) {

        List<NSZC047003_xxSkipListPMsg> usageList = new ArrayList<NSZC047003_xxSkipListPMsg>();

        for (int i = 0; i < pMsg.xxSkipList.getValidCount(); i++) {
            String svcInvChrgTp = pMsg.xxSkipList.no(i).svcInvChrgTpCd_SL.getValue();
            if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTp)) {
                usageList.add(pMsg.xxSkipList.no(i));
            }
        }
        return usageList;
    }

    private boolean updateBllgSchd(S21ApiMessageMap msgMap, BigDecimal dsContrBllgSchdPk, CalcRvsSchdBean calcRvsSchdBean) {

        NSZC047003PMsg pMsg = (NSZC047003PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        // START 2016/01/28 T.Aoyagi [CSA-QC#3095,MOD]
        // DS_CONTR_BLLG_SCHDTMsg inTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, dsContrBllgSchdPk);
        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        inTMsg = (DS_CONTR_BLLG_SCHDTMsg)S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        if (inTMsg == null) {
            return false;
        }
        // END   2016/01/28 T.Aoyagi [CSA-QC#3095,MOD]

        String dsBllgSchdStsCd = DS_BLLG_SCHD_STS.OPEN;
        String bllblFlg = FLG_ON_Y;
        if (FLG_ON_Y.equals(calcRvsSchdBean.getBllgSkipFlg())) {
            // START 2016/08/08 T.Aoyagi [CSA-QC#4959,DEL]
//            dsBllgSchdStsCd = DS_BLLG_SCHD_STS.CLOSE;
            // END 2016/08/08 T.Aoyagi [CSA-QC#4959,DEL]
            bllblFlg = FLG_OFF_N;
        }

        String rvsSchdDt = calcRvsSchdBean.getRvsSchdDt();
        setValue(inTMsg.skipRecovTpCd, calcRvsSchdBean.getSkipRecovTpCd());
        setValue(inTMsg.bllgStageFlg, FLG_OFF_N);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.dsBllgSchdStsCd, dsBllgSchdStsCd);
        setValue(inTMsg.bllblFlg, bllblFlg);
        setValue(inTMsg.rvsSchdDt, rvsSchdDt);

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD" });
            return false;
        }

        return true;
    }

    private boolean updateSvcContrBllgInfo(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgSchdPk, String rvsSchdDt) {

        List<BigDecimal> svcContrBllgGrpSqList = NSZC0470Query.getInstance().getSvcContrBllgGrpSq(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgGrpSq : svcContrBllgGrpSqList) {
            List<BigDecimal> svcContrBllgPkList = NSZC0470Query.getInstance().getSvcContrBllgByGrpSq(glblCmpyCd, svcContrBllgGrpSq);

            for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {
                SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
                setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
                // START 2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]
                inTMsg = (SVC_CONTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
                if (inTMsg == null) {
                    return false;
                }
                // END   2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]
                setValue(inTMsg.ovrdNextBllgDt, rvsSchdDt);
                S21ApiTBLAccessor.update(inTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getSkipRecovTp(List<NSZC047003_xxSkipListPMsg> baseList, Map<String, Object> bllgSchdInfo, String svcBllgSkipMthTp) {

        CalcRvsSchdBean inBean = new CalcRvsSchdBean();
        List<CalcRvsSchdLineBean> skipLineList = new ArrayList<CalcRvsSchdLineBean>();

        inBean.setBllgSchdFromDt((String) bllgSchdInfo.get("BLLG_SCHD_FROM_DT"));
        inBean.setBllgSchdThruDt((String) bllgSchdInfo.get("BLLG_SCHD_THRU_DT"));
        inBean.setXxModeCd(svcBllgSkipMthTp);
        for (NSZC047003_xxSkipListPMsg linePMsg : baseList) {
            CalcRvsSchdLineBean lineBean = new CalcRvsSchdLineBean();
            lineBean.setSkipRecovMth(linePMsg.skipRecovMth_SL.getValue());
            lineBean.setSkipRecovTpCd(linePMsg.skipRecovTpCd_SL.getValue());
            skipLineList.add(lineBean);
        }
        inBean.setSkipLine(skipLineList);
        return NSXC003001CalcRvsSchdDt.getSkipRecovTp(inBean);
    }

}
