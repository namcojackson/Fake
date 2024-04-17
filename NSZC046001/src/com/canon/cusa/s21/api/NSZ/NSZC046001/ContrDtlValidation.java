/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;

import business.db.MDSETMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PGM_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1655
 * 2016/01/20   Hitachi         K.Yamada        Update          CSA QC#3330
 * 2016/01/21   Hitachi         T.Iwamoto       Update          QC#3201
 * 2016/01/22   Hitachi         Y.Tsuchimoto    Update          QC#3405
 * 2016/01/25   Hitachi         T.Iwamoto       Update          QC#3531
 * 2016/03/03   Hitachi         T.Kanasaka      Update          QC#3188
 * 2016/03/11   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/01   Hitachi         Y.Tsuchimoto    Update          QC#6287
 * 2016/04/08   Hitachi         A.Kohinata      Update          QC#6622
 * 2016/05/25   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/06/20   Hitachi         T.Iwamoto       Update          QC#9944
 * 2016/06/21   Hitachi         Y.Tsuchimoto    Update          QC#9944
 * 2016/07/19   Hitachi         A.Kohinata      Update          QC#11027
 * 2016/09/23   Hitachi         K.Yamada        Update          QC#13686
 * 2016/10/20   Hitachi         T.Tomita        Update          QC#14868
 * 2016/10/26   Hitachi         A.Kohinata      Update          QC#13299
 * 2016/12/01   Hitachi         A.Kohinata      Update          QC#16205
 * 2017/06/07   Hitachi         A.Kohinata      Update          QC#18856
 * 2017/06/14   Hitachi         N.Arai          Update          QC#18930
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2019/06/04   Hitachi         K.Fujimoto      Update          QC#50631
 * </pre>
 */
public class ContrDtlValidation {

    /** ssm client */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(ContrDtlValidation.class);

    private static void checkContrDtlParam(NSZC046001_xxContrDtlListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxContrDtlListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId_DT)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId_DT, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt_DT, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean checkCloBllgDay(String day) {
        if (!ZYPCommonFunc.hasValue(day)) {
            return true;
        }
        // 2016/01/22 QC#3405 Y.Tsuchimoto Mod Start
        //if ((CLO_BLLG_MIN_DAY.compareTo(day) > 0 || CLO_BLLG_MAX_DAY.compareTo(day) < 0)
        //        && CLO_BLLG_LAST_DAY.compareTo(day) != 0) {
        //    return false;
        //}
        //return true;

        int intDay = Integer.parseInt(day);
        int minDay = Integer.parseInt(CLO_BLLG_MIN_DAY);
        int maxDay = Integer.parseInt(CLO_BLLG_MAX_DAY);
        int lastDay = Integer.parseInt(CLO_BLLG_LAST_DAY);

        if ((minDay > intDay || maxDay < intDay) && lastDay != intDay) {
            return false;
        }
        return true;
        // 2016/01/22 QC#3405 Y.Tsuchimoto Mod End
    }

    private static NSZC046001_xxContrDtlListPMsg getMachContrDtl(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", hdr.glblCmpyCd.getValue());
        ssmPrm.put("svcMachMstrPk", dtl.svcMachMstrPk.getValue());
        ssmPrm.put("machine", SVC_MACH_TP.MACHINE);

        BigDecimal machPk = (BigDecimal) ssmBatchClient.queryObject("getMachMstrByAcc", ssmPrm);
        if (machPk == null) {
            return null;
        }

        for (int i = 0; i < hdr.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = hdr.xxContrDtlList.no(i);
            if (ZYPCommonFunc.hasValue(dtlMsg.svcMachMstrPk)) {
                if (machPk.compareTo(dtlMsg.svcMachMstrPk.getValue()) == 0) {
                    return dtlMsg;
                }
            }
        }
        return null;
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        if (!NSZC046001CommonLogic.isExistContrDtlTp(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlTpCd.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.dsContrDtlTpCd.getValue(), DS_CONTR_DTL_TP.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.dsContrDtlStsCd)
                && !NSZC046001CommonLogic.isExistContrDtlSts(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlStsCd.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.dsContrDtlStsCd.getValue(), DS_CONTR_DTL_STS.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.svcMachMstrPk)
                && !NSZC046001CommonLogic.isExistMachMstr(param.glblCmpyCd.getValue(), dtlMsg.svcMachMstrPk.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.svcMachMstrPk.getValue().toPlainString(), "SVC_MACH_MSTR");
            return false;
        }
        if (!checkCloBllgDay(dtlMsg.contrCloDay.getValue())) {
            setMsg(dtlMsg, NSZM0654E, CLO_BLLG_MIN_DAY, CLO_BLLG_MAX_DAY, CLO_BLLG_LAST_DAY);
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.baseBllgCycleCd)
                && !NSZC046001CommonLogic.isExistBllgCycle(param.glblCmpyCd.getValue(), dtlMsg.baseBllgCycleCd.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.baseBllgCycleCd.getValue(), BLLG_CYCLE.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.baseBllgTmgCd)
                && !NSZC046001CommonLogic.isExistBllgTmg(param.glblCmpyCd.getValue(), dtlMsg.baseBllgTmgCd.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.baseBllgTmgCd.getValue(), BLLG_TMG_TP.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.mtrBllgCycleCd)
                && !NSZC046001CommonLogic.isExistBllgCycle(param.glblCmpyCd.getValue(), dtlMsg.mtrBllgCycleCd.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.mtrBllgCycleCd.getValue(), BLLG_CYCLE.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.mtrBllgTmgCd)
                && !NSZC046001CommonLogic.isExistBllgTmg(param.glblCmpyCd.getValue(), dtlMsg.mtrBllgTmgCd.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.mtrBllgTmgCd.getValue(), BLLG_TMG_TP.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.mtrReadMethCd)
                && !NSZC046001CommonLogic.isExistMtrReadMeth(param.glblCmpyCd.getValue(), dtlMsg.mtrReadMethCd.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.mtrReadMethCd.getValue(), MTR_READ_METH.class.getSimpleName());
            return false;
        }
        // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
        if (ZYPCommonFunc.hasValue(dtlMsg.ctacPsnPk) && !NSZC046001CommonLogic.isExistCtacPsn(param.glblCmpyCd.getValue(), dtlMsg.ctacPsnPk.getValue(), param.slsDt.getValue())) {
            setMsg(dtlMsg, NSZM0652E, dtlMsg.ctacPsnPk.getValue().toPlainString(), "CTAC_PSN");
            return false;
        }
        // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
        return true;
    }

    private static boolean checkRange(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        if (!checkCloBllgDay(dtlMsg.contrBllgDay.getValue())) {
            setMsg(dtlMsg, NSZM0655E, CLO_BLLG_MIN_DAY, CLO_BLLG_MAX_DAY, CLO_BLLG_LAST_DAY);
            return false;
        }
        if (!checkCloBllgDay(dtlMsg.mtrCloDay.getValue())) {
            setMsg(dtlMsg, NSZM0654E, CLO_BLLG_MIN_DAY, CLO_BLLG_MAX_DAY, CLO_BLLG_LAST_DAY);
            return false;
        }
        if (!checkCloBllgDay(dtlMsg.mtrBllgDay.getValue())) {
            setMsg(dtlMsg, NSZM0655E, CLO_BLLG_MIN_DAY, CLO_BLLG_MAX_DAY, CLO_BLLG_LAST_DAY);
            return false;
        }
        if (ZYPCommonFunc.hasValue(dtlMsg.maxPrcUpRatio)) {
            BigDecimal ratio = dtlMsg.maxPrcUpRatio.getValue();
            if (BigDecimal.ZERO.compareTo(ratio) > 0
                    || BigDecimal.valueOf(100).compareTo(ratio) < 0) {
                setMsg(dtlMsg, NSZM0656E);
                return false;
            }
        }
        return true;
    }

    private static boolean checkConsistency(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg, ONBATCH_TYPE onBatchType) {

        if (dtlMsg.contrEffFromDt.getValue().compareTo(dtlMsg.contrEffThruDt.getValue()) > 0) {
            setMsg(dtlMsg, NSAM0279E);
            return false;
        }

        // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
        if (NSZC046001CommonLogic.isRegular(param, dtlMsg) || NSZC046001CommonLogic.isFleetLine(dtlMsg)
                || NSZC046001CommonLogic.isUnderAgg(param, dtlMsg)) {
            // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
            // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//            if (!NSXC001001ContrValidation.checkBllgCycle(param.glblCmpyCd.getValue()
//                    , dtlMsg.baseBllgCycleCd.getValue(), dtlMsg.contrCloDay.getValue())) {
//                String cycleNm = ZYPCodeDataUtil.getName(BLLG_CYCLE.class, param.glblCmpyCd.getValue(), dtlMsg.baseBllgCycleCd.getValue());
//                setMsg(dtlMsg, NSZM0657E, cycleNm);
//                return false;
//            }
            // END 2016/03/03 T.Kanasaka [QC3188, DEL]
            if (!NSXC001001ContrValidation.checkBaseAndUsgCloDay(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlTpCd.getValue()
                    , dtlMsg.contrCloDay.getValue(), dtlMsg.mtrCloDay.getValue())) {
                setMsg(dtlMsg, NSZM0658E);
                return false;
            }
            if (!NSXC001001ContrValidation.checkBaseAndUsgBllgDay(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlTpCd.getValue()
                    , dtlMsg.contrBllgDay.getValue(), dtlMsg.mtrBllgDay.getValue())) {
                setMsg(dtlMsg, NSZM0659E);
                return false;
            }
        }

        if (!NSXC001001ContrValidation.checkContrDtlEffPeriodWithHdr(dtlMsg.contrEffFromDt.getValue(), dtlMsg.contrEffThruDt.getValue()
                , param.contrVrsnEffFromDt.getValue(), param.contrVrsnEffThruDt.getValue())) {
            setMsg(dtlMsg, NSZM0660E);
            return false;
        }

        // mod start 2016/12/01 QC#16205
        if (NSZC046001CommonLogic.isAccessory(param, dtlMsg) && !(DS_CONTR_CATG.WARRANTY.equals(param.dsContrCatgCd.getValue()) && !ZYPCommonFunc.hasValue(dtlMsg.prntSvcMachMstrPk))) {
        // mod end 2016/12/01 QC#16205
            NSZC046001_xxContrDtlListPMsg machDtlMsg = getMachContrDtl(param, dtlMsg);
            if (machDtlMsg != null && !NSXC001001ContrValidation.checkAccEffPeriodWithMach(
                    dtlMsg.contrEffFromDt.getValue(), dtlMsg.contrEffThruDt.getValue()
                    , machDtlMsg.contrEffFromDt.getValue(), machDtlMsg.contrEffThruDt.getValue())) {
                setMsg(dtlMsg, NSZM0661E);
                return false;
            }
        }

//        if (ZYPCommonFunc.hasValue(dtlMsg.svcMachMstrPk) && !NSXC001001ContrValidation.checkDuplicateContr(param.glblCmpyCd.getValue()
//                , param.dsContrCatgCd.getValue() , dtlMsg.svcMachMstrPk.getValue(), dtlMsg.dsContrDtlPk.getValue()
//                , dtlMsg.contrEffFromDt.getValue(), dtlMsg.contrEffThruDt.getValue())) {
//            setMsg(dtlMsg, NSZM0662E);
//            return false;
//        }

        // START 2016/03/03 T.Kanasaka [QC3188, MOD]
        if (ZYPConstant.FLG_ON_Y.equals(param.baseChrgToLeaseCmpyFlg.getValue())
                && !NSXC001001ContrValidation.checkLeaseBillTo(param.leaseCmpyCd.getValue(), dtlMsg.baseBillToCustCd.getValue())) {
            setMsg(dtlMsg, NSZM0663E);
            return false;
        }
//
//        if (!NSXC001001ContrValidation.checkLeaseBillTo(param.leaseCmpyCd.getValue(), dtlMsg.usgBillToCustCd.getValue())) {
//            setMsg(dtlMsg, NSZM0664E);
//            return false;
//        }
        // END 2016/03/03 T.Kanasaka [QC3188, MOD]

//        if (ZYPCommonFunc.hasValue(dtlMsg.baseBillToCustCd)
//                && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), dtlMsg.baseBillToCustCd.getValue(), onBatchType)) {
//            setMsg(dtlMsg, NSZM0698E, "Base Bill To", "Account");
//            return false;
//        }
//
//        if (ZYPCommonFunc.hasValue(dtlMsg.usgBillToCustCd)
//                && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), dtlMsg.usgBillToCustCd.getValue(), onBatchType)) {
//            setMsg(dtlMsg, NSZM0698E, "Usage Bill To", "Account");
//            return false;
//        }
//
//        if (ZYPCommonFunc.hasValue(dtlMsg.baseBillToCustCd)
//                && !NSXC001001ContrValidation.checkPoRequired(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), dtlMsg.baseBillToCustCd.getValue(), onBatchType)
//                && !ZYPCommonFunc.hasValue(param.custPoNum)) {
//            setMsg(dtlMsg, NSZM0698E, "Base Bill To", "Account");
//            return false;
//        }
//
//        if (ZYPCommonFunc.hasValue(dtlMsg.usgBillToCustCd)
//                && !NSXC001001ContrValidation.checkPoRequired(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), dtlMsg.usgBillToCustCd.getValue(), onBatchType)
//                && !ZYPCommonFunc.hasValue(param.custPoNum)) {
//            setMsg(dtlMsg, NSZM0698E, "Usage Bill To", "Account");
//            return false;
//        }

        return true;
    }

    // START 2016/04/08 [QC#6622, ADD]
    // START 2016/07/19 [QC#11027, MOD]
    private static boolean checkContrDtlSts(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg, String validFlg, String... validStsCdList) {
        // START 2017/06/14 N.Arai [QC#18930, MOD]
//        Map<String, Object> dsContrDtlStsMap = getDsContrDtlStsV(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlPk.getValue());
        Map<String, Object> dsContrDtlStsMap = getDsContrDtlSts(param.glblCmpyCd.getValue(), dtlMsg.dsContrDtlPk.getValue());
        // END 2017/06/14 N.Arai [QC#18930, MOD]
        String dsContrDtlStsCd = (String) dsContrDtlStsMap.get("DS_CONTR_DTL_STS_CD");
        String dsContrDtlStsNm = (String) dsContrDtlStsMap.get("DS_CONTR_DTL_STS_NM");

        if (!NSZC046001CommonLogic.checkStsCd(dsContrDtlStsCd, validFlg, validStsCdList)) {
            setMsg(dtlMsg, NSZM0961E, "Contract Detail Status", dsContrDtlStsNm);
            return false;
        }
        return true;
    }

    // START 2017/06/14 N.Arai [QC#18930, MOD]
//    private static Map<String, Object> getDsContrDtlStsV(String glblCmpyCd, BigDecimal dsContrDtlPk) {
    private static Map<String, Object> getDsContrDtlSts(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);

//        return (Map<String, Object>) ssmBatchClient.queryObject("getDsContrDtlStsV", ssmPrm);
        return (Map<String, Object>) ssmBatchClient.queryObject("getDsContrDtlSts", ssmPrm);
    }
    // END 2017/06/14 N.Arai [QC#18930, MOD]
    // END 2016/07/19 [QC#11027, MOD]
    // END 2016/04/08 [QC#6622, ADD]

    /**
     * validateForCreateMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForCreateMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {

        // 2018/05/07 QC#22482 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(param.manContrOvrdFlg.getValue())) {
            return true;
        }
        // 2018/05/07 QC#22482 Add End

        boolean hasDtlError = false;
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrDtlTpCd, NSZM0589E, "DS Contract Detail Type Code");
            checkContrDtlParam(dtlMsg, dtlMsg.contrEffFromDt, NSZM0589E, "Contract Effective From Date");
            checkContrDtlParam(dtlMsg, dtlMsg.contrEffThruDt, NSZM0589E, "Contract Effective Thru Date");

            // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
            if (NSZC046001CommonLogic.isRegular(param, dtlMsg) || NSZC046001CommonLogic.isFleetLine(dtlMsg)
                    || NSZC046001CommonLogic.isUnderAgg(param, dtlMsg)) {
                // del start 2016/09/23 CSA Defect#13686
                //checkContrDtlParam(dtlMsg, dtlMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
                // del end 2016/09/23 CSA Defect#13686
                // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
                checkContrDtlParam(dtlMsg, dtlMsg.contrCloDay, NSZM0589E, "Contact Closing Day");
                checkContrDtlParam(dtlMsg, dtlMsg.baseBllgCycleCd, NSZM0589E, "Base Billing Cycle Code");
                checkContrDtlParam(dtlMsg, dtlMsg.baseBllgTmgCd, NSZM0589E, "Base Billing Timing Code");
                checkContrDtlParam(dtlMsg, dtlMsg.contrBllgDay, NSZM0589E, "Contact Billing Day");
                // START 2016/05/25 [QC#4061, MOD]
                if (!ZYPConstant.FLG_ON_Y.equals(param.manContrOvrdFlg.getValue())) {
                    checkContrDtlParam(dtlMsg, dtlMsg.basePrcDealAmt, NSZM0589E, "Base Price Deal Amount");
                }
                // END   2016/05/25 [QC#4061, MOD]
                checkContrDtlParam(dtlMsg, dtlMsg.baseBillToCustCd, NSZM0589E, "Base Bill To Customer Code");
                checkContrDtlParam(dtlMsg, dtlMsg.svcPgmMdseCd, NSZM0589E, "Service Program Merchandise Code");

                if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrBllgTmgCd, NSZM0589E, "Meter Billing Timing Code");
                    // del start 2016/10/26 QC#13299
                    //checkContrDtlParam(dtlMsg, dtlMsg.usgBillToCustCd, NSZM0589E, "Usage Bill To Customer Code");
                    // del end 2016/10/26 QC#13299
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrCloDay, NSZM0589E, "Meter Closing Day");
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrBllgDay, NSZM0589E, "Meter Billing Day");
                    // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//                    checkContrDtlParam(dtlMsg, dtlMsg.mtrReadMethCd, NSZM0589E, "Meter Read Method Code");
                    // END 2016/03/03 T.Kanasaka [QC3188, DEL]
                }
            }

            if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                hasDtlError = true;
                continue;
            }

            if (!checkMaster(param, dtlMsg)) {
                hasDtlError = true;
                continue;
            }
            if (!checkRange(param, dtlMsg)) {
                hasDtlError = true;
                continue;
            }
            if (!checkConsistency(param, dtlMsg, onBatchType)) {
                hasDtlError = true;
                continue;
            }
            // START 2016/06/21 [QC#9944, MOD]
            if (!checkDuplicateMachIncContractData(param, dtlMsg, i)) {
                setMsg(dtlMsg, NSZM1022E);
                hasDtlError = true;
                continue;
            }

            if (!checkParentMachineExistDataForAccessory(param, dtlMsg)) {
                setMsg(dtlMsg, NSZM1023E);
                hasDtlError = true;
                continue;
            }
            // END   2016/06/21 [QC#9944, ADD]
        }

        if (hasDtlError) {
            return false;
        }
        return true;
    }

    /**
     * validateForUpdateMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForUpdateMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {

        // 2018/05/07 QC#22482 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(param.manContrOvrdFlg.getValue())) {
            return true;
        }
        // 2018/05/07 QC#22482 Add End

        boolean hasDtlError = false;
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail PK");
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrPk, NSZM0589E, "DS Contract PK");
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrDtlTpCd, NSZM0589E, "DS Contract Detail Type Code");
            checkContrDtlParam(dtlMsg, dtlMsg.contrEffFromDt, NSZM0589E, "Contract Effective From Date");
            checkContrDtlParam(dtlMsg, dtlMsg.contrEffThruDt, NSZM0589E, "Contract Effective Thru Date");
            // START 2016/04/01 Y.Tsuchimoto [QC#6287, DEL]
            //checkContrDtlParam(dtlMsg, dtlMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
            // END   2016/04/01 Y.Tsuchimoto [QC#6287, DEL]
            checkContrDtlParam(dtlMsg, dtlMsg.qltyAsrnHldFlg, NSZM0589E, "Quality Asuarance Hold Flag");
            checkContrDtlParam(dtlMsg, dtlMsg.mtrHldFlg, NSZM0589E, "Meter Hold Flag");
            checkContrDtlParam(dtlMsg, dtlMsg.contrHldFlg, NSZM0589E, "Contract Hold Flag");
            checkContrDtlParam(dtlMsg, dtlMsg.bllgHldFlg, NSZM0589E, "Billing Hold Flag");
            checkContrDtlParam(dtlMsg, dtlMsg.svcPgmMdseCd, NSZM0589E, "Service Program Merchandise Code");

            // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
            if (NSZC046001CommonLogic.isRegular(param, dtlMsg) || NSZC046001CommonLogic.isFleetLine(dtlMsg)
                    || NSZC046001CommonLogic.isUnderAgg(param, dtlMsg)) {
                // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
                checkContrDtlParam(dtlMsg, dtlMsg.contrCloDay, NSZM0589E, "Contact Closing Day");
                checkContrDtlParam(dtlMsg, dtlMsg.baseBllgCycleCd, NSZM0589E, "Base Billing Cycle Code");
                checkContrDtlParam(dtlMsg, dtlMsg.baseBllgTmgCd, NSZM0589E, "Base Billing Timing Code");
                checkContrDtlParam(dtlMsg, dtlMsg.contrBllgDay, NSZM0589E, "Contact Billing Day");
                checkContrDtlParam(dtlMsg, dtlMsg.basePrcDealAmt, NSZM0589E, "Base Price Deal Amount");
                checkContrDtlParam(dtlMsg, dtlMsg.baseBillToCustCd, NSZM0589E, "Base Bill To Customer Code");
                // del start 2017/06/07 QC#18856
                //checkContrDtlParam(dtlMsg, dtlMsg.basePrcTermDealAmtRate, NSZM0589E, "Base Price Term Deal Amount");
                // del end 2017/06/07 QC#18856
                checkContrDtlParam(dtlMsg, dtlMsg.svcPgmMdseCd, NSZM0589E, "Service Program Merchandise Code");
                // del start 2016/09/23 CSA Defect#13686
                // START 2016/04/01 Y.Tsuchimoto [QC#6287, ADD]
                //checkContrDtlParam(dtlMsg, dtlMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
                // END   2016/04/01 Y.Tsuchimoto [QC#6287, ADD]
                // del end 2016/09/23 CSA Defect#13686
                if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrBllgTmgCd, NSZM0589E, "Meter Billing Timing Code");
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrCloDay, NSZM0589E, "Meter Closing Day");
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrBllgDay, NSZM0589E, "Meter Billing Day");
                    // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//                    checkContrDtlParam(dtlMsg, dtlMsg.mtrReadMethCd, NSZM0589E, "Meter Read Method Code");
                    // END 2016/03/03 T.Kanasaka [QC3188, DEL]
                }
            }

            if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                hasDtlError = true;
                continue;
            }

            if (!checkMaster(param, dtlMsg)) {
                hasDtlError = true;
                continue;
            }
            if (!checkRange(param, dtlMsg)) {
                hasDtlError = true;
                continue;
            }
            if (!checkConsistency(param, dtlMsg, onBatchType)) {
                hasDtlError = true;
                continue;
            }
            // START 2016/04/08 [QC#6622, ADD]
            // START 2017/06/14 N.Arai [QC#18930, MOD]
//          String[] ngStsList = new String[] {DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED };
            String[] ngStsList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED, DS_CONTR_DTL_STS.EXPIRED };
            // END 2017/06/14 N.Arai [QC#18930, MOD]
            if (!checkContrDtlSts(param, dtlMsg, ZYPConstant.FLG_OFF_N, ngStsList)) {
                hasDtlError = true;
                continue;
            }
            // END 2016/04/08 [QC#6622, ADD]
        }

        if (hasDtlError) {
            return false;
        }
        return true;
    }

    /**
     * validateForAddContrMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForAddContrMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {

        // 2018/05/07 QC#22482 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(param.manContrOvrdFlg.getValue())) {
            return true;
        }
        // 2018/05/07 QC#22482 Add End

        boolean hasDtlError = false;

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrDtlTpCd, NSZM0589E, "DS Contract Detail Type Code");
            checkContrDtlParam(dtlMsg, dtlMsg.contrEffFromDt, NSZM0589E, "Contract Effective From Date");
            checkContrDtlParam(dtlMsg, dtlMsg.contrEffThruDt, NSZM0589E, "Contract Effective Thru Date");
            // START 2016/04/01 Y.Tsuchimoto [QC#6287, DEL]
            //checkContrDtlParam(dtlMsg, dtlMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
            // END   2016/04/01 Y.Tsuchimoto [QC#6287, DEL]

            // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
            if (NSZC046001CommonLogic.isRegular(param, dtlMsg) || NSZC046001CommonLogic.isFleetLine(dtlMsg)
                    || NSZC046001CommonLogic.isUnderAgg(param, dtlMsg)) {
                // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
                // del start 2016/09/23 CSA Defect#13686
                // START 2016/04/01 Y.Tsuchimoto [QC#6287, ADD]
                //checkContrDtlParam(dtlMsg, dtlMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
                // END   2016/04/01 Y.Tsuchimoto [QC#6287, ADD]
                // del end 2016/09/23 CSA Defect#13686
                checkContrDtlParam(dtlMsg, dtlMsg.contrCloDay, NSZM0589E, "Contact Closing Day");
                checkContrDtlParam(dtlMsg, dtlMsg.baseBllgCycleCd, NSZM0589E, "Base Billing Cycle Code");
                checkContrDtlParam(dtlMsg, dtlMsg.baseBllgTmgCd, NSZM0589E, "Base Billing Timing Code");
                checkContrDtlParam(dtlMsg, dtlMsg.contrBllgDay, NSZM0589E, "Contact Billing Day");
                // START 2016/05/25 [QC#4061, MOD]
                if (!ZYPConstant.FLG_ON_Y.equals(param.manContrOvrdFlg.getValue())) {
                    checkContrDtlParam(dtlMsg, dtlMsg.basePrcDealAmt, NSZM0589E, "Base Price Deal Amount");
                }
                // END   2016/05/25 [QC#4061, MOD]
                checkContrDtlParam(dtlMsg, dtlMsg.baseBillToCustCd, NSZM0589E, "Base Bill To Customer Code");
                checkContrDtlParam(dtlMsg, dtlMsg.svcPgmMdseCd, NSZM0589E, "Service Program Merchandise Code");

                if (NSZC046001CommonLogic.isUsgChrg(param, dtlMsg)) {
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrBllgTmgCd, NSZM0589E, "Meter Billing Timing Code");
                    // del start 2016/10/26 QC#13299
                    //checkContrDtlParam(dtlMsg, dtlMsg.usgBillToCustCd, NSZM0589E, "Usage Bill To Customer Code");
                    // del end 2016/10/26 QC#13299
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrCloDay, NSZM0589E, "Meter Closing Day");
                    checkContrDtlParam(dtlMsg, dtlMsg.mtrBllgDay, NSZM0589E, "Meter Billing Day");
                    // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//                    checkContrDtlParam(dtlMsg, dtlMsg.mtrReadMethCd, NSZM0589E, "Meter Read Method Code");
                    // END 2016/03/03 T.Kanasaka [QC3188, DEL]
                }
            }

            if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                hasDtlError = true;
                continue;
            }

            if (!checkMaster(param, dtlMsg)) {
                hasDtlError = true;
                continue;
            }
            if (!checkRange(param, dtlMsg)) {
                hasDtlError = true;
                continue;
            }
            if (!checkConsistency(param, dtlMsg, onBatchType)) {
                hasDtlError = true;
                continue;
            }
            // START 2016/06/20 [QC#9944, ADD]
            // START 2016/06/21 [QC#9944, MOD]
            if (!checkDuplicateMachIncContractData(param, dtlMsg, i)) {
                setMsg(dtlMsg, NSZM1022E);
                hasDtlError = true;
                continue;
            }
            if (!checkDuplicateMachIncContract(param, dtlMsg)) {
                setMsg(dtlMsg, NSZM1022E);
                hasDtlError = true;
                continue;
            }
            // END 2016/06/21 [QC#9944, MOD]
            // END 2016/06/20 [QC#9944, ADD]

            // START 2016/06/21 [QC#9944, ADD]
            if (!checkParentMachineExistDataForAccessory(param, dtlMsg)) {
                if (!checkParentMachineExistDBForAccessory(param, dtlMsg)) {
                    setMsg(dtlMsg, NSZM1023E);
                    hasDtlError = true;
                    continue;
                }
            }
            // END   2016/06/21 [QC#9944, ADD]
        }

        if (hasDtlError) {
            return false;
        }
        return true;
    }

    /**
     * validateForDeleteMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForDeleteMode(NSZC046001PMsg param) {

        boolean hasDtlError = false;
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail PK");
            if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                hasDtlError = true;
                // START 2017/06/14 N.Arai [QC#18930, MOD]
                continue;
                // END 2017/06/14 N.Arai [QC#18930, MOD]
            }

            // START 2016/04/08 [QC#6622, ADD]
            // START 2017/06/14 N.Arai [QC#18930, MOD]
//          String[] ngStsList = new String[] {DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED };
            String[] ngStsList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED, DS_CONTR_DTL_STS.EXPIRED };
            // END 2017/06/14 N.Arai [QC#18930, MOD]
            if (!checkContrDtlSts(param, dtlMsg, ZYPConstant.FLG_OFF_N, ngStsList)) {
                hasDtlError = true;
                continue;
            }
            // END 2016/04/08 [QC#6622, ADD]
        }

        if (hasDtlError) {
            return false;
        }
        return true;
    }

    /**
     * validateForTerminateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForTerminateMode(NSZC046001PMsg param) {

        boolean hasDtlError = false;
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail PK");
            checkContrDtlParam(dtlMsg, dtlMsg.contrCloDt, NSZM0589E, "Contract Close Date");
            //checkContrDtlParam(dtlMsg, dtlMsg.trmnTotAmt, NSZM0589E, "Termination Total Amount");
            //checkContrDtlParam(dtlMsg, dtlMsg.trmnOvrdTotAmt, NSZM0589E, "Termination Override Total Amount");
            checkContrDtlParam(dtlMsg, dtlMsg.supprCrFlg, NSZM0589E, "Suppression Credit Flag");
            if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                hasDtlError = true;
                // START 2017/06/14 N.Arai [QC#18930, MOD]
                continue;
                // END 2017/06/14 N.Arai [QC#18930, MOD]
            }
            // START 2016/04/08 [QC#6622, ADD]
            // START 2017/06/14 N.Arai [QC#18930, MOD]
//            String[] ngStsList = new String[] {DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED };
            // START 2017/07/26 M.Kidokoro [QC#18122, DEL]
//            String[] ngStsList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED, DS_CONTR_DTL_STS.EXPIRED };
            // END 2017/07/26 M.Kidokoro [QC#18122, DEL]
            // END 2017/06/14 N.Arai [QC#18930, MOD]
            // START 2017/07/25 M.Kidokoro [QC#18122, ADD]
            String[] ngStsList = NSZC046001CommonLogic.getDsContrCtrlSts(param.glblCmpyCd.getValue(), ZYPConstant.FLG_OFF_N);

            if (ngStsList == null) {
                return false;
            }
            // END 2017/07/25 M.Kidokoro [QC#18122, ADD]
            if (!checkContrDtlSts(param, dtlMsg, ZYPConstant.FLG_OFF_N, ngStsList)) {
                hasDtlError = true;
                continue;
            }
            // END 2016/04/08 [QC#6622, ADD]
        }

        if (hasDtlError) {
            return false;
        }
        return true;
    }

    /**
     * validateForRenewalMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForRenewalMode(NSZC046001PMsg param) {

        boolean hasDtlError = false;
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(i);
            checkContrDtlParam(dtlMsg, dtlMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail PK");
            if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                hasDtlError = true;
            }

            // START 2016/01/25 T.Iwamoto [QC#3531, MOD]
            if (!NSZC046001CommonLogic.isBaseRnw(param, dtlMsg) && !NSZC046001CommonLogic.isUsgRnw(param, dtlMsg) && !NSZC046001CommonLogic.isUnderFleet(param, dtlMsg)) {
            // END 2016/01/25 T.Iwamoto [QC#3531, MOD]
                setMsg(dtlMsg, NSBM0050E, "Base Renewal", "Usage Renewal");
                hasDtlError = true;
                continue;
            }
            if (NSZC046001CommonLogic.isBaseRnw(param, dtlMsg)) {
                checkContrDtlParam(dtlMsg, dtlMsg.contrEffThruDt, NSZM0589E, "Contract Effective Thru Date");
                // START 2016/01/20 T.Iwamoto [QC#3201, MOD]
                if (isRenewalDtlCheck(param, dtlMsg)) {
                    checkContrDtlParam(dtlMsg, dtlMsg.basePrcDealAmt, NSZM0589E, "Base Price Deal Amount");
                    checkContrDtlParam(dtlMsg, dtlMsg.basePrcTermDealAmtRate, NSZM0589E, "Base Price Term Deal Amount");
                }
                // END 2016/01/20 T.Iwamoto [QC#3201, MOD]
                if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                    hasDtlError = true;
                    continue;
                }
            }
            if (NSZC046001CommonLogic.isUsgRnw(param, dtlMsg)) {
                checkContrDtlParam(dtlMsg, dtlMsg.contrEffThruDt, NSZM0589E, "Contract Effective Thru Date");
                if (ZYPCommonFunc.hasValue(dtlMsg.xxMsgId_DT)) {
                    hasDtlError = true;
                    continue;
                }
            }

        }

        if (hasDtlError) {
            return false;
        }
        return true;
    }

    /**
     * isRenewalDtlCheck
     * @param param
     * @param dtlMsg
     * @return
     */
    private static boolean isRenewalDtlCheck(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        if (NSZC046001CommonLogic.isUnderFleet(param, dtlMsg)) {
            return false;
        }
        if (NSZC046001CommonLogic.isAggLine(dtlMsg)) {
            return false;
        }
        return true;
    }

    // 2016/06/20 [QC#9944, ADD]
    private static boolean checkDuplicateMachIncContract(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        if (!ZYPCommonFunc.hasValue(dtlMsg.svcMachMstrPk)) {
            return true;
        }

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmPrm.put("dsContrPk", param.dsContrPk.getValue());
        ssmPrm.put("svcMachMstrPk", dtlMsg.svcMachMstrPk.getValue());
        // add start 2016/10/20 CSA Defect#14868
        ssmPrm.put("canc", DS_CONTR_DTL_STS.CANCELLED);
        ssmPrm.put("trmd", DS_CONTR_DTL_STS.TERMINATED);
        ssmPrm.put("expd", DS_CONTR_DTL_STS.EXPIRED);
        // add end 2016/10/20 CSA Defect#14868

        int cnt = (Integer) ssmBatchClient.queryObject("checkDuplicateMachIncContract", ssmPrm);
        if (cnt == 0) {
            return true;
        }

        return false;
    }

    // START 2016/06/21 [QC#9944, ADD]
    private static boolean checkDuplicateMachIncContractData(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg, int index) {

        BigDecimal targetSvcMachMstrPk = dtlMsg.svcMachMstrPk.getValue();
        // START 2019/06/04 [QC#50631, ADD]
        if (isRentalSplit(param.glblCmpyCd.getValue(), dtlMsg.svcPgmMdseCd.getValue())) {
            return true;
        }
        // END 2019/06/04 [QC#50631, ADD]
        if (!ZYPCommonFunc.hasValue(targetSvcMachMstrPk)) {
            targetSvcMachMstrPk = BigDecimal.ZERO;
        }
        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg wkDtlMsg = param.xxContrDtlList.no(i);
            BigDecimal svcMachMstrPk = wkDtlMsg.svcMachMstrPk.getValue();
            // START 2019/06/04 [QC#50631, ADD]
            if (isRentalSplit(param.glblCmpyCd.getValue(), wkDtlMsg.svcPgmMdseCd.getValue())) {
                continue;
            }
            // END 2019/06/04 [QC#50631, ADD]
            if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                svcMachMstrPk = BigDecimal.ZERO;
            }
            if (i != index && targetSvcMachMstrPk.compareTo(svcMachMstrPk) == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkParentMachineExistDataForAccessory(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        if (!NSZC046001CommonLogic.isAccessory(param, dtlMsg)) {
            return true;
        }

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsgForParent = param.xxContrDtlList.no(i);
            if (NSZC046001CommonLogic.isAccessory(param, dtlMsgForParent)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(dtlMsgForParent.svcConfigMstrPk) && dtlMsgForParent.svcConfigMstrPk.getValue().compareTo(dtlMsg.svcConfigMstrPk.getValue()) == 0) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkParentMachineExistDBForAccessory(NSZC046001PMsg param, NSZC046001_xxContrDtlListPMsg dtlMsg) {
        if (!NSZC046001CommonLogic.isAccessory(param, dtlMsg)) {
            return true;
        }

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmPrm.put("dsContrPk", param.dsContrPk.getValue());
        ssmPrm.put("svcConfigMstrPk", dtlMsg.svcConfigMstrPk.getValue());
        ssmPrm.put("accessories", DS_CONTR_DTL_TP.ACCESSORIES);

        int cnt = (Integer) ssmBatchClient.queryObject("checkParentMachineExistDBForAccessory", ssmPrm);
        if (cnt > 0) {
            return true;
        }

        return false;
    }
    // END   2016/06/21 [QC#9944, ADD]
    // 2019/06/04 START K.Fujimoto [QC#50613, ADD]
    private static boolean isRentalSplit(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = getMdse(glblCmpyCd, mdseCd);
        if (tMsg != null) {
            if (tMsg.svcPgmTpCd.getValue().equals(SVC_PGM_TP.RENTALSPLIT)) {
                return true;
            }
        }
        return false;
    }
    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mdseCd", mdseCd);
        return (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // 2019/06/04 END   K.Fujimoto [QC#50613, ADD]

}
