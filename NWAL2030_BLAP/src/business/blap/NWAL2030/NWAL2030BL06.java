/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2030;

import static business.blap.NWAL2030.constant.NWAL2030Constant.NWAM0223E;
import static business.blap.NWAL2030.constant.NWAL2030Constant.NWAM0559E;
import static business.blap.NWAL2030.constant.NWAL2030Constant.NWAM0790E;
import static business.blap.NWAL2030.constant.NWAL2030Constant.NWAM0791E;
import static business.blap.NWAL2030.constant.NWAL2030Constant.ZZZM9004E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2030.common.NWAL2030CommonLogic;
import business.db.HLD_PROC_DFNTMsg;
import business.db.HLD_RSNTMsg;
import business.db.ORD_PROC_NODETMsg;
import business.db.WH_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Hold Set Up Screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Hitachi         K.Kojima        Create          N/A
 * 2016/04/04   Fujitsu         M.Suzuki        Update          QC#6370
 * 2016/04/08   Fujitsu         M.Suzuki        Update          QC#6760
 *</pre>
 */
public class NWAL2030BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL2030Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL2030Scrn00_CMN_Submit((NWAL2030CMsg) cMsg, (NWAL2030SMsg) sMsg); // 2016/04/04 S21_NA#6370
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * doProcess_NWAL2030Scrn00_CMN_Submit
     * @param cMsg NWAL2030CMsg
     */
    private void doProcess_NWAL2030Scrn00_CMN_Submit(NWAL2030CMsg cMsg, NWAL2030SMsg sMsg) { // 2016/04/04

        if (checkInputData(cMsg) == false) {
            return;
        }

        WH_TPTMsgArray whTpArray = (WH_TPTMsgArray) ZYPCodeDataUtil.findAll("WH_TP");
        BigDecimal sortNum = NWAL2030Query.getInstance().getMaxSortNum(cMsg.glblCmpyCd.getValue()).add(BigDecimal.ONE);

        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NWAL2030_ACMsg acMsg = cMsg.A.no(num);
            if (ZYPConstant.FLG_ON_Y.equals(acMsg.xxExstFlg_A.getValue())) {
                // 2016/04/04 S21_NA#6370 mod start-------------
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    String sHldId = sMsg.A.no(i).hldRsnCd_A.getValue();
                    if (sHldId.equals(acMsg.hldRsnCd_A.getValue())) {
                        if (isHldRsnUpdate(sMsg, acMsg, i)) {
                            if (updateHldRsn(cMsg, acMsg) == false) {
                                return;
                            }
                        }
                    }
                }
                // 2016/04/04 S21_NA#6370 mod End---------------
            } else {
                if (insertHldRsn(cMsg, acMsg, sortNum) == false) {
                    return;
                }

                if (insertHldProcDfn(cMsg, acMsg, whTpArray) == false) {
                    return;
                }
                sortNum = sortNum.add(BigDecimal.ONE);
            }

        }

    }

    // 2016/04/04 S21_NA#6370 mod start-------------
    private boolean isHldRsnUpdate(NWAL2030SMsg sMsg, NWAL2030_ACMsg acMsg, int i) {
        boolean upFlag = false;

        if (!acMsg.hldRsnNm_A.getValue().equals(sMsg.A.no(i).hldRsnNm_A.getValue()) || !acMsg.hldRsnDescTxt_A.getValue().equals(sMsg.A.no(i).hldRsnDescTxt_A.getValue())
                || !acMsg.hldEffFromDt_A.getValue().equals(sMsg.A.no(i).hldEffFromDt_A.getValue()) || !acMsg.hldEffToDt_A.getValue().equals(sMsg.A.no(i).hldEffToDt_A.getValue())) {
            upFlag = true;
        }

        return upFlag;
    }

    // 2016/04/04 S21_NA#6370 mod End---------------

    /**
     * checkInputData
     * @param cMsg NWAL2030CMsg
     * @return boolean
     */
    private boolean checkInputData(NWAL2030CMsg cMsg) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        boolean checkResult = true;
        HashMap<String, Integer> cdCheck = new HashMap<String, Integer>();
        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NWAL2030_ACMsg acMsg = cMsg.A.no(num);

            if (!ZYPConstant.FLG_ON_Y.equals(acMsg.xxExstFlg_A.getValue()) && ZYPDateUtil.compare(acMsg.hldEffFromDt_A.getValue(), cMsg.slsDt.getValue()) < 0) {
                acMsg.hldEffFromDt_A.setErrorInfo(1, NWAM0223E, new String[] {"Start Date", "Today" });
                checkResult = false;
            } else if (ZYPCommonFunc.hasValue(acMsg.hldEffToDt_A) && ZYPDateUtil.compare(acMsg.hldEffFromDt_A.getValue(), acMsg.hldEffToDt_A.getValue()) > 0) {
                acMsg.hldEffToDt_A.setErrorInfo(1, NWAM0223E, new String[] {"End Date", "Start Date" });
                checkResult = false;
            }

            String hldRsnCd = acMsg.hldRsnCd_A.getValue();
            if (cdCheck.containsKey(hldRsnCd)) {
                acMsg.hldRsnCd_A.setErrorInfo(1, NWAM0559E, new String[] {"Hold ID" });
                checkResult = false;
            } else {
                cdCheck.put(hldRsnCd, Integer.valueOf(num));
                if (!ZYPConstant.FLG_ON_Y.equals(acMsg.xxExstFlg_A.getValue())) {
                    HLD_RSNTMsg tMsg = NWAL2030CommonLogic.findByKeyHldRsn(glblCmpyCd, hldRsnCd);
                    if (tMsg != null) {
                        acMsg.hldRsnCd_A.setErrorInfo(1, NWAM0559E, new String[] {"Hold ID" });
                        checkResult = false;
                    }
                }
            }
        }
        return checkResult;
    }

    /**
     * updateHldRsn
     * @param cMsg NWAL2030CMsg
     * @param acMsg NWAL2030_ACMsg
     * @return boolean
     */
    private boolean updateHldRsn(NWAL2030CMsg cMsg, NWAL2030_ACMsg acMsg) {
        HLD_RSNTMsg tMsg = NWAL2030CommonLogic.findByKeyForUpdateNoWaitHldRsn(cMsg.glblCmpyCd.getValue(), acMsg.hldRsnCd_A.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_AH.getValue(), acMsg.ezUpTimeZone_AH.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setHldRsnTmsgForUpdate(tMsg, acMsg);
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0791E, new String[] {"HLD_RSN" });
            return false;
        }
        return true;
    }

    /**
     * insertHldRsn
     * @param cMsg NWAL2030CMsg
     * @param acMsg NWAL2030_ACMsg
     * @param hldRsnSortNum BigDecimal
     * @return boolean
     */
    private boolean insertHldRsn(NWAL2030CMsg cMsg, NWAL2030_ACMsg acMsg, BigDecimal hldRsnSortNum) {
        HLD_RSNTMsg tMsg = NWAL2030CommonLogic.findByKeyForUpdateNoWaitHldRsn(cMsg.glblCmpyCd.getValue(), acMsg.hldRsnCd_A.getValue());
        if (tMsg != null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        //---2016/04/08 QC#6760----------------
        ORD_PROC_NODETMsg ordProcNodeTmsg = new ORD_PROC_NODETMsg();
        ZYPEZDItemValueSetter.setValue(ordProcNodeTmsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ordProcNodeTmsg.ordProcNodeCd, acMsg.ordProcNodeCd_SV);
        ordProcNodeTmsg = (ORD_PROC_NODETMsg) EZDTBLAccessor.findByKey(ordProcNodeTmsg);
        tMsg = new HLD_RSNTMsg();
        setHldRsnTmsgForInsert(tMsg, acMsg, cMsg.glblCmpyCd.getValue(), hldRsnSortNum, ordProcNodeTmsg.manApplyHldTpCd.getValue());
        //---------------------------------------
        EZDTBLAccessor.create(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0790E, new String[] {"HLD_RSN" });
            return false;
        }
        return true;
    }

    /**
     * insertHldProcDfn
     * @param cMsg NWAL2030CMsg
     * @param acMsg NWAL2030_ACMsg
     * @param whTpArray WH_TPTMsgArray
     * @return boolean
     */
    private boolean insertHldProcDfn(NWAL2030CMsg cMsg, NWAL2030_ACMsg acMsg, WH_TPTMsgArray whTpArray) {
        List<HLD_PROC_DFNTMsg> tMsgList = new ArrayList<HLD_PROC_DFNTMsg>();
        setHldProcDfn(tMsgList, acMsg, cMsg.W, whTpArray, cMsg.glblCmpyCd.getValue());
        for (int i = 0; i < tMsgList.size(); i++) {
            HLD_PROC_DFNTMsg tMsg = tMsgList.get(i);
            EZDTBLAccessor.create(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NWAM0790E, new String[] {"DS_HLD_RSN" });
                return false;
            }
        }
        return true;
    }

    /**
     * setHldRsnTmsgForInsert
     * @param tMsg HLD_RSNTMsg
     * @param acMsg NWAL2030_ACMsg
     * @param glblCmpyCd String
     * @param hldRsnSortNum BigDecimal
     */
    private void setHldRsnTmsgForInsert(HLD_RSNTMsg tMsg, NWAL2030_ACMsg acMsg, String glblCmpyCd, BigDecimal hldRsnSortNum, String manApplyHldTpCd) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, acMsg.hldRsnCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnNm, acMsg.hldRsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnSortNum, hldRsnSortNum);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnDescTxt, acMsg.hldRsnDescTxt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldTpCd, HLD_TP.SALES_HOLD);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRelAvalFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tMsg.reqApvlNumFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.errOrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.exAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.hldLvlCd, acMsg.hldLvlCd_SV);
        ZYPEZDItemValueSetter.setValue(tMsg.nightBatFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.dtmBatFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.exReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.submtPsnNotRelFlg, ZYPConstant.FLG_OFF_N);
        //---2016/04/08 NWAL6760----------------
        ZYPEZDItemValueSetter.setValue(tMsg.relFuncTpCd, manApplyHldTpCd);
        //--------------------------------------
        ZYPEZDItemValueSetter.setValue(tMsg.ordProcNodeCd, acMsg.ordProcNodeCd_SV);
        ZYPEZDItemValueSetter.setValue(tMsg.hldEffFromDt, acMsg.hldEffFromDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldEffToDt, acMsg.hldEffToDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldApplyTpCd, HLD_APPLY_TP.MANUAL);
        ZYPEZDItemValueSetter.setValue(tMsg.blockBookHldRelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.byotBldgHldFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * setHldRsnTmsgForUpdate
     * @param tMsg HLD_RSNTMsg
     * @param acMsg NWAL2030_ACMsg
     */
    private void setHldRsnTmsgForUpdate(HLD_RSNTMsg tMsg, NWAL2030_ACMsg acMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnNm, acMsg.hldRsnNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnDescTxt, acMsg.hldRsnDescTxt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldEffFromDt, acMsg.hldEffFromDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.hldEffToDt, acMsg.hldEffToDt_A);
    }

    /**
     * setHldProcDfn
     * @param tMsgList List<HLD_PROC_DFNTMsg>
     * @param acMsg NWAL2030_ACMsg
     * @param wcMsgArray NWAL2030_WCMsgArray
     * @param whTpArray WH_TPTMsgArray
     * @param glblCmpyCd String
     */
    private void setHldProcDfn(List<HLD_PROC_DFNTMsg> tMsgList, NWAL2030_ACMsg acMsg, NWAL2030_WCMsgArray wcMsgArray, WH_TPTMsgArray whTpArray, String glblCmpyCd) {
        NWAL2030_WCMsg wcMsg = null;
        for (int i = 0; i < wcMsgArray.getValidCount(); i++) {
            if (wcMsgArray.no(i).ordProcNodeCd_W.getValue().equals(acMsg.ordProcNodeCd_SV.getValue())) {
                wcMsg = wcMsgArray.no(i);
                break;
            }
        }
        if (wcMsg == null) {
            return;
        }
        for (int i = 0; i < whTpArray.length(); i++) {
            HLD_PROC_DFNTMsg tMsg = new HLD_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, acMsg.hldRsnCd_A);
            ZYPEZDItemValueSetter.setValue(tMsg.whTpCd, whTpArray.no(i).whTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.chkPntOrdVldFlg, wcMsg.chkPntOrdVldFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.chkPntSoftAllocFlg, wcMsg.chkPntSoftAllocFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.chkPntShpgVldFlg, wcMsg.chkPntShpgVldFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.chkPntSoCancFlg, wcMsg.chkPntSoCancFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.chkPntCrChkFlg, wcMsg.chkPntCrChkFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.relPntSoftAllocFlg, wcMsg.relPntSoftAllocFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.relPntHardAllocFlg, wcMsg.relPntHardAllocFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.relPntCrChkFlg, wcMsg.relPntCrChkFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.relPntSoCratFlg, wcMsg.relPntSoCratFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.relPntSoPrintFlg, wcMsg.relPntSoPrintFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.relPntShpgFlg, wcMsg.relPntShpgFlg_W);
            ZYPEZDItemValueSetter.setValue(tMsg.relPntRevFlg, wcMsg.relPntRevFlg_W);
            tMsgList.add(tMsg);
        }
    }

}
