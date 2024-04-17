/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0740;

import static business.blap.NSAL0740.constant.NSAL0740Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0740.common.NSAL0740CommonLogic;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMOTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/02/08   Hitachi         T.Aoyagi        Update          QC#4089
 * 2016/03/07   Hitachi         T.Aoyagi        Update          QC#3945
 * 2016/05/13   Hitachi         M.Gotou         Update          QC#4472
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#8608
 * 2017/09/08   Hitachi         K.Kim           Update          QC#20880
 * 2017/10/27   Hitachi         M.Kidokoro      Update          QC#21672
 * 2017/11/08   Hitachi         M.Kidokoro      Update          QC#21928
 * 2017/11/15   Hitachi         M.Kidokoro      Update          QC#21928-1
 * 2017/11/20   Hitachi         M.Kidokoro      Update          QC#22694
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 * 2018/11/27   Hitachi         T.Tomita        Update          QC#28638
 * 2018/11/28   Hitachi         T.Tomita        Update          QC#28638
 * 2018/11/28   CITS            R.Tamura        Update          QC#62046
 *</pre>
 */
// mod start 2016/05/17 CSA Defect#4472
public class NSAL0740BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0740CMsg cMsg = (NSAL0740CMsg) arg0;
        NSAL0740SMsg sMsg = (NSAL0740SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0740Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_CMN_Submit(cMsg, sMsg);

            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0740Scrn00_CMN_Submit(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        BigDecimal prevContractPk = BigDecimal.ZERO;
        BigDecimal currentContractPk = null;
        boolean result = false;
        boolean commitFlag = true;
        boolean isError = false;
        boolean headerSelected = false;
        boolean detailSelected = false;
        // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
        int hdrIndex = 0;
        int dtlIndex = 0;
        // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

        // CMsg -> SMsg
        cMsg.setCommitSMsg(true);
        setValue(sMsg.svcCmntTxt_H1, cMsg.svcCmntTxt_H1);
        setValue(sMsg.svcMemoRsnCd_H3, cMsg.svcMemoRsnCd_H3);
        reflectCMsgToSMsg(cMsg, sMsg);

        if (!NSAL0740CommonLogic.validateCheckBoxSMsg(sMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        // START 2016/03/07 T.Aoyagi [QC#3945, ADD]
        if (!validation(sMsg)) {
            reflectSMsgToCMsg(cMsg, sMsg);
            cMsg.setMessageInfo(NSAM0394W);
            return;
        }
        // EDN 2016/03/07 T.Aoyagi [QC#3945, ADD]

        prevContractPk = BigDecimal.ZERO;
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
            sMsg.A.no(index).xxGenlFldAreaTxt_D1.clear();
            // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

            currentContractPk = sMsg.A.no(index).dsContrPk_D1.getValue();

            if (prevContractPk.compareTo(currentContractPk) != 0) {
                if (index > 0) {
                    if (commitFlag) {
                        EZDConnectionMgr.getInstance().commit();
                    } else {
                        EZDConnectionMgr.getInstance().rollback();
                        isError = true;
                    }
                    commitFlag = true;
                }
            }
            result = registerSvcMemo(sMsg, index);
            if (!result && commitFlag) {
                commitFlag = false;
            }

            // START 2017/11/08 M.Kidokoro [QC#21928, MOD]
//            result = registerDsContrEscl(sMsg, index);
            if (LINE_LEVEL_CONTRACT.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue())) {
                hdrIndex = index;
            }
            if (LINE_LEVEL_CONTRACT_DETAIL.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue())) {
                dtlIndex = index;
            }
            result = registerDsContrEscl(sMsg, index, hdrIndex, dtlIndex);
            // END 2017/11/08 M.Kidokoro [QC#21928, MOD]
            if (!result) {
                cMsg.setMessageInfo(ZZZM9004E);
            }
            if (!result && commitFlag) {
                commitFlag = false;
            }

            headerSelected = ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S1.getValue())
                                && (LINE_LEVEL_CONTRACT.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) || LINE_LEVEL_CONTRACT_DETAIL.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()));

            detailSelected = LINE_LEVEL_BASE_OVERAGE.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S2.getValue());

            if (!hasValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1)
                    && (headerSelected || detailSelected)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1, getRtnMsg(NSAM0200I));
            }

            prevContractPk = currentContractPk;
        }
        reflectSMsgToCMsg(cMsg, sMsg);

        if (commitFlag) {
            EZDConnectionMgr.getInstance().commit();
        } else {
            EZDConnectionMgr.getInstance().rollback();
            isError = true;
        }

        if (isError) {
            cMsg.setMessageInfo(NSAM0394W);
        }
        cMsg.setMessageInfo(NSAM0200I);
    }

    // START 2017/11/08 M.Kidokoro [QC#21928, MOD]
//    private boolean registerDsContrEscl(NSAL0740SMsg sMsg, int index) {
    private boolean registerDsContrEscl(NSAL0740SMsg sMsg, int index, int hdrIndex, int dtlIndex) {
    // END 2017/11/08 M.Kidokoro [QC#21928, MOD]

        boolean result = false;

        boolean headerSelected = ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S1.getValue())
                && (LINE_LEVEL_CONTRACT.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) || LINE_LEVEL_CONTRACT_DETAIL.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()));

        boolean detailSelected = LINE_LEVEL_BASE_OVERAGE.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S2.getValue());

        if (headerSelected || detailSelected) {
            NSAL0740_ASMsg aSMsg = sMsg.A.no(index);

            if (aSMsg.dsContrRnwEsclPk_D1.getValue() != null) {
                DS_CONTR_RNW_ESCLTMsg findKeyTMsg = new DS_CONTR_RNW_ESCLTMsg();
                setValue(findKeyTMsg.glblCmpyCd, aSMsg.glblCmpyCd_D1);
                setValue(findKeyTMsg.dsContrRnwEsclPk, aSMsg.dsContrRnwEsclPk_D1);

                DS_CONTR_RNW_ESCLTMsg inTMsg = (DS_CONTR_RNW_ESCLTMsg) EZDTBLAccessor.findByKeyForUpdate(findKeyTMsg);
                if (inTMsg != null) {

                    if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime_D1.getValue(), aSMsg.ezUpTimeZone_D1.getValue(), inTMsg.ezUpTime.getValue(), inTMsg.ezUpTimeZone.getValue())) {
//                        cMsg.setMessageInfo(ZZZM9004E);
                        return result;
                    }

                    setUpdateParamDsContrRnwEscl(inTMsg, sMsg, sMsg.A.no(index));
                    EZDTBLAccessor.update(inTMsg);

                    String rtnCd = inTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1, getRtnMsg(NSAM0031E, new String[] {DS_CONTR_RNW_ESCL }));
                    } else {
                        result = true;
                    }
                } else {
//                    cMsg.setMessageInfo(ZZZM9004E);
                    return result;
                }

            } else {
                DS_CONTR_RNW_ESCLTMsg findKeyTMsg = new DS_CONTR_RNW_ESCLTMsg();

                if (LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {
                    // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
                    if (!checkOverlapValue(sMsg, aSMsg, hdrIndex)) {
                        // START 2017/11/15 M.Kidokoro [QC#21928-1, MOD]
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1, getRtnMsg(NSAM0032E, new String[] {DS_CONTR_RNW_ESCL }));
                        result = true;
                        // END 2017/11/15 M.Kidokoro [QC#21928-1, MOD]
                        return result;
                    }
                    // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
                    findKeyTMsg.setSQLID("001");
                    findKeyTMsg.setConditionValue("glblCmpyCd01", aSMsg.glblCmpyCd_D1.getValue());
                    findKeyTMsg.setConditionValue("dsContrPk01", aSMsg.dsContrPk_D1.getValue());
                    findKeyTMsg.setConditionValue("dsContrDtlPk01", aSMsg.dsContrDtlPk_D1.getValue());
                    findKeyTMsg.setConditionValue("dsContrMachLvlNum01", aSMsg.dsContrMachLvlNum_D1.getValue());

                } else if (LINE_LEVEL_BASE_OVERAGE.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {
                    // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
                    if (!checkOverlapValue(sMsg, aSMsg, dtlIndex)) {
                        // START 2017/11/15 M.Kidokoro [QC#21928-1, MOD]
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1, getRtnMsg(NSAM0032E, new String[] {DS_CONTR_RNW_ESCL }));
                        result = true;
                        // END 2017/11/15 M.Kidokoro [QC#21928-1, MOD]
                        return result;
                    }
                    // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
                    findKeyTMsg.setSQLID("002");
                    findKeyTMsg.setConditionValue("glblCmpyCd01", aSMsg.glblCmpyCd_D1.getValue());
                    findKeyTMsg.setConditionValue("dsContrPk", aSMsg.dsContrPk_D1.getValue());
                    findKeyTMsg.setConditionValue("dsContrDtlPk", aSMsg.dsContrDtlPk_D1.getValue());
                    findKeyTMsg.setConditionValue("dsContrMachLvlNum", aSMsg.dsContrMachLvlNum_D1.getValue());
                    findKeyTMsg.setConditionValue("dsContrBaseUsgNm", aSMsg.dsContrBaseUsgNm_D1.getValue());
                }

                DS_CONTR_RNW_ESCLTMsgArray tMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) EZDTBLAccessor.findByCondition(findKeyTMsg);

                if (tMsgArray == null || tMsgArray.length() == 0) {
                    DS_CONTR_RNW_ESCLTMsg inTMsg = new DS_CONTR_RNW_ESCLTMsg();

                    setInsertParamDsContrRnwEscl(inTMsg, sMsg, sMsg.A.no(index));
                    EZDTBLAccessor.insert(inTMsg);

                    String rtnCd = inTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1, getRtnMsg(NSAM0032E, new String[] {SVC_MEMO }));
                    } else {
                        result = true;
                    }

                } else {
//                    cMsg.setMessageInfo(ZZZM9004E);
                    return result;
                }
            }
        } else {
            result = true;
        }

        return result;
    }

    private static void setUpdateParamDsContrRnwEscl(DS_CONTR_RNW_ESCLTMsg tmsg, NSAL0740SMsg sMsg, NSAL0740_ASMsg aSMsg) {

        setValue(tmsg.contrUplftTpCd, aSMsg.contrUplftTpCd_D3);
        setValue(tmsg.uplftPrcMethCd, aSMsg.uplftPrcMethCd_D3);
        // START 2016/03/07 T.Aoyagi [QC#3945, ADD]
        setValue(tmsg.uplftBefEndRnwDaysAot, getUplftDaysAot(sMsg, aSMsg));
        // END 2016/03/07 T.Aoyagi [QC#3945, ADD]

        // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
//        setValue(tmsg.basePrcUpRatio, aCMsg.basePrcUpRatio_D1);
//        setValue(tmsg.mtrPrcUpRatio, aCMsg.mtrPrcUpRatio_D1);
        setValue(tmsg.uplftBasePrcUpRatio, aSMsg.uplftBasePrcUpRatio_D1);
        setValue(tmsg.uplftMtrPrcUpRatio, aSMsg.uplftMtrPrcUpRatio_D1);
        // END   2016/02/08 T.Aoyagi [QC#4089, MOD]

        // START 2017/09/08 K.Kim [QC#20880, MOD]
//        if (LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {
        // START 2017/10/27 M.Kidokoro [QC#21672, DEL]
//        if (LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())
//                || LINE_LEVEL_BASE_OVERAGE.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {
        // END 2017/10/27 M.Kidokoro [QC#21672, DEL]
        // END 2017/09/08 K.Kim [QC#20880, MOD]
        // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//        setValue(tmsg.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//        setValue(tmsg.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//        setValue(tmsg.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//        setValue(tmsg.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//        setValue(tmsg.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E1.getValue())) {
//            setValue(tmsg.firstYrContrUplftFlg, aSMsg.xxChkBox_E1);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E2.getValue())) {
//            setValue(tmsg.scdYrContrUplftFlg, aSMsg.xxChkBox_E2);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E3.getValue())) {
//            setValue(tmsg.thirdYrContrUplftFlg, aSMsg.xxChkBox_E3);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E4.getValue())) {
//            setValue(tmsg.frthYrContrUplftFlg, aSMsg.xxChkBox_E4);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E5.getValue())) {
//            setValue(tmsg.fifthYrContrUplftFlg, aSMsg.xxChkBox_E5);
//        }
        setValue(tmsg.maxPrcUpRatio, aSMsg.maxPrcUpRatio_D1);
        setValue(tmsg.fixTermInMthAot, aSMsg.fixTermInMthAot_D1);
        setValue(tmsg.uplftFixedDt, aSMsg.uplftFixedDt_D1);
        setValue(tmsg.uplftPcyDt, aSMsg.uplftPcyDt_D1);
        // END 2018/11/16 K.Kitachi [QC#28638, MOD]
        // START 2017/10/27 M.Kidokoro [QC#21672, DEL]
//        }
        // END 2017/10/27 M.Kidokoro [QC#21672, DEL]
    }

    private static void setInsertParamDsContrRnwEscl(DS_CONTR_RNW_ESCLTMsg tmsg, NSAL0740SMsg sMsg, NSAL0740_ASMsg aSMsg) {

        setValue(tmsg.dsContrRnwEsclPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ));
        setValue(tmsg.glblCmpyCd, aSMsg.glblCmpyCd_D1);
        setValue(tmsg.dsContrPk, aSMsg.dsContrPk_D1);
        setValue(tmsg.dsContrDtlPk, aSMsg.dsContrDtlPk_D1);
        setValue(tmsg.dsContrMachLvlNum, aSMsg.dsContrMachLvlNum_D1);
        setValue(tmsg.dsContrBaseUsgNm, aSMsg.dsContrBaseUsgNm_D1);

        setValue(tmsg.contrUplftTpCd, aSMsg.contrUplftTpCd_D3);
        setValue(tmsg.uplftPrcMethCd, aSMsg.uplftPrcMethCd_D3);
        // START 2016/03/07 T.Aoyagi [QC#3945, ADD]
        setValue(tmsg.uplftBefEndRnwDaysAot, getUplftDaysAot(sMsg, aSMsg));
        // END 2016/03/07 T.Aoyagi [QC#3945, ADD]

        // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
//        setValue(tmsg.basePrcUpRatio, aCMsg.basePrcUpRatio_D1);
//        setValue(tmsg.mtrPrcUpRatio, aCMsg.mtrPrcUpRatio_D1);
        setValue(tmsg.uplftBasePrcUpRatio, aSMsg.uplftBasePrcUpRatio_D1);
        setValue(tmsg.uplftMtrPrcUpRatio, aSMsg.uplftMtrPrcUpRatio_D1);
        // END   2016/02/08 T.Aoyagi [QC#4089, MOD]

        // START 2017/09/08 K.Kim [QC#20880, MOD]
//        if (LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {
        // START 2017/10/27 M.Kidokoro [QC#21672, DEL]
//        if (LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())
//                || LINE_LEVEL_BASE_OVERAGE.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {
        // END 2017/10/27 M.Kidokoro [QC#21672, DEL]
        // END 2017/09/08 K.Kim [QC#20880, MOD]

        setValue(tmsg.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.sixthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.svnthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.eighthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.ninthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        setValue(tmsg.tenthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);

        // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E1.getValue())) {
//            setValue(tmsg.firstYrContrUplftFlg, aSMsg.xxChkBox_E1);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E2.getValue())) {
//            setValue(tmsg.scdYrContrUplftFlg, aSMsg.xxChkBox_E2);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E3.getValue())) {
//            setValue(tmsg.thirdYrContrUplftFlg, aSMsg.xxChkBox_E3);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E4.getValue())) {
//            setValue(tmsg.frthYrContrUplftFlg, aSMsg.xxChkBox_E4);
//        }
//        if (ZYPConstant.CHKBOX_ON_Y.equals(aSMsg.xxChkBox_E5.getValue())) {
//            setValue(tmsg.fifthYrContrUplftFlg, aSMsg.xxChkBox_E5);
//        }
        setValue(tmsg.maxPrcUpRatio, aSMsg.maxPrcUpRatio_D1);
        setValue(tmsg.fixTermInMthAot, aSMsg.fixTermInMthAot_D1);
        setValue(tmsg.uplftFixedDt, aSMsg.uplftFixedDt_D1);
        setValue(tmsg.uplftPcyDt, aSMsg.uplftPcyDt_D1);
        // END 2018/11/16 K.Kitachi [QC#28638, MOD]
        // START 2017/10/27 M.Kidokoro [QC#21672, DEL]
//        } else {
//            setValue(tmsg.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.sixthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.svnthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.eighthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.ninthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//            setValue(tmsg.tenthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
//        }
        // END 2017/10/27 M.Kidokoro [QC#21672, DEL]
    }

    private boolean registerSvcMemo(NSAL0740SMsg sMsg, int index) {

        boolean result = false;

        if ((LINE_LEVEL_CONTRACT.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S1.getValue()))
                || (LINE_LEVEL_CONTRACT_DETAIL.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S1.getValue()))
                || (LINE_LEVEL_BASE_OVERAGE.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S2.getValue()))) {
            SVC_MEMOTMsg findKeyTMsg = new SVC_MEMOTMsg();
            findKeyTMsg.setSQLID("007");
            findKeyTMsg.setConditionValue("glblCmpyCd01", sMsg.A.no(index).glblCmpyCd_D1.getValue());
            findKeyTMsg.setConditionValue("svcMemoCatgCd01", SVC_MEMO_CATG.CONTRACT_MEMO);
            findKeyTMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.ESCALATION_RULES);
            findKeyTMsg.setConditionValue("dsContrPk01", sMsg.A.no(index).dsContrPk_D1.getValue());

            SVC_MEMOTMsgArray tMsgArray = (SVC_MEMOTMsgArray) EZDTBLAccessor.findByCondition(findKeyTMsg);

            SVC_MEMOTMsg inTMsg = null;
            if (tMsgArray == null || tMsgArray.length() == 0) {
                inTMsg = new SVC_MEMOTMsg();
                setInsertParamSvcMemo(inTMsg, sMsg, sMsg.A.no(index));
                EZDTBLAccessor.insert(inTMsg);

                String rtnCd = inTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1, getRtnMsg(NSAM0032E, new String[] {SVC_MEMO }));
                } else {
                    result = true;
                }

            } else {
                inTMsg = tMsgArray.no(0);
                setUpdateParamSvcMemo(inTMsg, sMsg, sMsg.A.no(index));
                EZDTBLAccessor.update(inTMsg);

                String rtnCd = inTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxGenlFldAreaTxt_D1, getRtnMsg(NSAM0031E, new String[] {SVC_MEMO }));
                } else {
                    result = true;
                }
            }
        } else {
            result = true;
        }

        return result;
    }

    private void setUpdateParamSvcMemo(SVC_MEMOTMsg tmsg, NSAL0740SMsg sMsg, NSAL0740_ASMsg aSMsg) {
        setValue(tmsg.svcCmntTxt, sMsg.svcCmntTxt_H1);
        setValue(tmsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(tmsg.svcMemoRsnCd, sMsg.svcMemoRsnCd_H3);
    }

    private void setInsertParamSvcMemo(SVC_MEMOTMsg tmsg, NSAL0740SMsg sMsg, NSAL0740_ASMsg aSMsg) {
        setValue(tmsg.glblCmpyCd, aSMsg.glblCmpyCd_D1);
        setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.ESCALATION_RULES);
        setValue(tmsg.svcCmntTxt, sMsg.svcCmntTxt_H1);
        setValue(tmsg.dsContrPk, aSMsg.dsContrPk_D1);
        setValue(tmsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(tmsg.svcMemoRsnCd, sMsg.svcMemoRsnCd_H3);
    }

    private static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    private static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // START 2016/03/07 T.Aoyagi [QC#3945, ADD]
    private static boolean validation(NSAL0740SMsg sMsg) {
        boolean result = true;
        // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
        Map<BigDecimal, Boolean> diffThruDtMap = new HashMap<BigDecimal, Boolean>();
        // END 2017/12/21 M.Kidokoro [QC#22660, ADD]

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0740_ASMsg aSMsg = sMsg.A.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(aSMsg.xxChkBox_S1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(aSMsg.xxChkBox_S2.getValue())) {
                continue;
            }
            if (!hasValue(aSMsg.contrUplftTpCd_D3) || CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(aSMsg.contrUplftTpCd_D3.getValue())) {
                continue;
            }
            // START 2023/11/01 R.Tamura [QC#62046, ADD]
            if (DS_CONTR_DTL_STS.TERMINATED.equals(aSMsg.dsContrDtlStsCd_D1.getValue()) || DS_CONTR_DTL_STS.EXPIRED.equals(aSMsg.dsContrDtlStsCd_D1.getValue()) || DS_CONTR_DTL_STS.CANCELLED.equals(aSMsg.dsContrDtlStsCd_D1.getValue())) {
                continue;	
            }
            // END 2023/11/01 R.Tamura [QC#62046, ADD]
            if (!hasValue(aSMsg.uplftPrcMethCd_D3)) {
                aSMsg.uplftPrcMethCd_D3.setErrorInfo(1, NSAM0434E, new String[]{"Escalation Type", "Do Not Uplift", "Escalation Method"});
                result = false;
            }
            if (LINE_LEVEL_CONTRACT.equals(aSMsg.dsContrMachLvlNum_D1.getValue())
                    || LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {

                if (!hasValue(aSMsg.uplftBefEndRnwDaysAot_D1)) {
                    aSMsg.uplftBefEndRnwDaysAot_D1.setErrorInfo(1, NSAM0434E, new String[]{"Escalation Type", "Do Not Uplift", "#Days"});
                    result = false;
                }
            } else if (LINE_LEVEL_BASE_OVERAGE.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {

                int contrIdx = getContrIdx(sMsg, aSMsg);
                if (!hasValue(sMsg.A.no(contrIdx).uplftBefEndRnwDaysAot_D1)) {
                    int contrDtlIdx = getContrDtlIdx(sMsg, aSMsg);
                    if (!hasValue(sMsg.A.no(contrDtlIdx).uplftBefEndRnwDaysAot_D1)) {
                        sMsg.A.no(contrIdx).uplftBefEndRnwDaysAot_D1.setErrorInfo(1, NSAM0434E, new String[]{"Escalation Type", "Do Not Uplift", "#Days"});
                        sMsg.A.no(contrDtlIdx).uplftBefEndRnwDaysAot_D1.setErrorInfo(1, NSAM0434E, new String[]{"Escalation Type", "Do Not Uplift", "#Days"});
                        result = false;
                    }
                }
            }
            // mod start 2016/07/13 CSA Defect#8608
            if (!UPLFT_PRC_METH.MODEL_PERCENT.equals(aSMsg.uplftPrcMethCd_D3.getValue())) {
                // add start 2016/05/17 CSA Defect#4472
                if (CONTR_UPLFT_TP.UPLIFT_BASE_ONLY.equals(aSMsg.contrUplftTpCd_D3.getValue()) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(aSMsg.contrUplftTpCd_D3.getValue())) {
                    if (LINE_LEVEL_CONTRACT.equals(aSMsg.dsContrMachLvlNum_D1.getValue()) || LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())
                            || (LINE_LEVEL_BASE_OVERAGE.equals(aSMsg.dsContrMachLvlNum_D1.getValue()) && BASE.equals(aSMsg.dsContrBaseUsgNm_D1.getValue()))) {
                        // Mod Start 2018/11/28 QC#28638
                        if (!DS_CONTR_DTL_TP.AGGREGATE.equals(aSMsg.dsContrDtlTpCd_D1.getValue())) {
                            if (!hasValue(aSMsg.uplftBasePrcUpRatio_D1)) {
                                aSMsg.uplftBasePrcUpRatio_D1.setErrorInfo(1, ZZZM9025E, new String[] {"Base%" });
                                result = false;
                            }
                        }
                        // Mod End 2018/11/28 QC#28638
                    }
                }
                if (CONTR_UPLFT_TP.UPLIFT_OVERAGE_ONLY.equals(aSMsg.contrUplftTpCd_D3.getValue()) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(aSMsg.contrUplftTpCd_D3.getValue())) {
                    if (LINE_LEVEL_CONTRACT.equals(aSMsg.dsContrMachLvlNum_D1.getValue()) || LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())
                            || (LINE_LEVEL_BASE_OVERAGE.equals(aSMsg.dsContrMachLvlNum_D1.getValue()) && OVERAGE.equals(aSMsg.dsContrBaseUsgNm_D1.getValue()))) {
                        // Mod Start 2018/11/28 QC#28638
                        if (!isAggMachineLine(aSMsg)) {
                            if (!hasValue(aSMsg.uplftMtrPrcUpRatio_D1)) {
                                aSMsg.uplftMtrPrcUpRatio_D1.setErrorInfo(1, ZZZM9025E, new String[] {"Overage%" });
                                result = false;
                            }
                        }
                        // Mod End 2018/11/28 QC#28638
                    }
                }
                // add end 2016/05/17 CSA Defect#4472
            }
            // mod end 2016/07/13 CSA Defect#8608
            // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
            if (diffThruDtMap.containsKey(aSMsg.dsContrPk_D1.getValue())) {
                if (!diffThruDtMap.get(aSMsg.dsContrPk_D1.getValue())) {
                    aSMsg.contrUplftTpCd_D3.setErrorInfo(1, NSAM0710E, new String[] {"Escalation Type", "Orig End Date" });
                }
            } else {
                if (DS_CONTR_CATG.AGGREGATE.equals(aSMsg.dsContrCatgCd_D1.getValue())
                        && (CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(aSMsg.contrUplftTpCd_D3.getValue())
                                || CONTR_UPLFT_TP.UPLIFT_OVERAGE_ONLY.equals(aSMsg.contrUplftTpCd_D3.getValue()))) {
                    if (NSAL0740Query.getInstance().isExistDiffThruDate(aSMsg)) {
                        diffThruDtMap.put(aSMsg.dsContrPk_D1.getValue(), false);
                        aSMsg.contrUplftTpCd_D3.setErrorInfo(1, NSAM0710E, new String[] {"Escalation Type", "Orig End Date" });
                        result = false;
                    } else {
                        diffThruDtMap.put(aSMsg.dsContrPk_D1.getValue(), true);
                    }
                } else {
                    diffThruDtMap.put(aSMsg.dsContrPk_D1.getValue(), true);
                }
            }
            // END 2017/12/21 M.Kidokoro [QC#22660, ADD]

        }
        return result;
    }

    private static int getContrIdx(NSAL0740SMsg sMsg, NSAL0740_ASMsg curASMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0740_ASMsg aSMsg = sMsg.A.no(i);

            if (LINE_LEVEL_CONTRACT.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {
                if (curASMsg.dsContrPk_D1.getValue().compareTo(aSMsg.dsContrPk_D1.getValue()) == 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    private static int getContrDtlIdx(NSAL0740SMsg sMsg, NSAL0740_ASMsg curASMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0740_ASMsg aSMsg = sMsg.A.no(i);

            if (LINE_LEVEL_CONTRACT_DETAIL.equals(aSMsg.dsContrMachLvlNum_D1.getValue())) {

                if (hasValue(curASMsg.dsContrDtlPk_D1) && !hasValue(aSMsg.dsContrDtlPk_D1)) {
                    continue;
                }
                if (!hasValue(curASMsg.dsContrDtlPk_D1) && hasValue(aSMsg.dsContrDtlPk_D1)) {
                    continue;
                }
                if (!hasValue(curASMsg.dsContrDtlPk_D1) && !hasValue(aSMsg.dsContrDtlPk_D1)) {
                    return i;
                }
                if (curASMsg.dsContrDtlPk_D1.getValue().compareTo(aSMsg.dsContrDtlPk_D1.getValue()) == 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    private static BigDecimal getUplftDaysAot(NSAL0740SMsg sMsg, NSAL0740_ASMsg curASMsg) {

        BigDecimal uplftDaysAot = null;

        if (LINE_LEVEL_CONTRACT.equals(curASMsg.dsContrMachLvlNum_D1.getValue())
                || LINE_LEVEL_CONTRACT_DETAIL.equals(curASMsg.dsContrMachLvlNum_D1.getValue())) {

            uplftDaysAot = curASMsg.uplftBefEndRnwDaysAot_D1.getValue();

        } else if (LINE_LEVEL_BASE_OVERAGE.equals(curASMsg.dsContrMachLvlNum_D1.getValue())) {

            // Contract Detail Level
            int contrDtlIdx = getContrDtlIdx(sMsg, curASMsg);
            uplftDaysAot = sMsg.A.no(contrDtlIdx).uplftBefEndRnwDaysAot_D1.getValue();
            if (!hasValue(uplftDaysAot)) {

                // Contract Level
                int contrIdx = getContrIdx(sMsg, curASMsg);
                uplftDaysAot = sMsg.A.no(contrIdx).uplftBefEndRnwDaysAot_D1.getValue();
            }
        }
        return uplftDaysAot;
    }
    // END 2016/03/07 T.Aoyagi [QC#3945, ADD]

    // add start 2016/05/17 CSA Defect#4472
    private void reflectSMsgToCMsg(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        for (int cIndex = 0; cIndex < cMsg.A.getValidCount(); cIndex++) {
            for (int sIndex = 0; sIndex < sMsg.A.getValidCount(); sIndex++) {
                NSAL0740_ACMsg aCMsg = cMsg.A.no(cIndex);
                NSAL0740_ASMsg aSMsg = sMsg.A.no(sIndex);
                if (aCMsg.xxRowNum_D1.getValue().equals(aSMsg.xxRowNum_D1.getValue())) {
                    EZDMsg.copy(aSMsg, null, aCMsg, null);
                    break;
                }
            }
        }
    }
    private void reflectCMsgToSMsg(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        for (int sIndex = 0; sIndex < sMsg.A.getValidCount(); sIndex++) {
            for (int cIndex = 0; cIndex < cMsg.A.getValidCount(); cIndex++) {
                NSAL0740_ASMsg aSMsg = sMsg.A.no(sIndex);
                NSAL0740_ACMsg aCMsg = cMsg.A.no(cIndex);
                if (aSMsg.xxRowNum_D1.getValue().equals(aCMsg.xxRowNum_D1.getValue())) {
                    EZDMsg.copy(aCMsg, null, aSMsg, null);
                    break;
                }
            }
        }
    }
    // add end 2016/05/17 CSA Defect#4472
// mod end 2016/05/17 CSA Defect#4472

    // START 2017/11/15 M.Kidokoro [QC#21928-1, ADD]
    private boolean checkOverlapValue(NSAL0740SMsg sMsg, NSAL0740_ASMsg curASMsg, int index) {
        boolean result = true;
        int ovrlpCnt = 0;
        int chkObjCnt = 0;
        NSAL0740_ASMsg upperASMsg = sMsg.A.no(index);

        if (CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(curASMsg.contrUplftTpCd_D3.getValue())) {
            // Check_1:Type
            if (upperASMsg.contrUplftTpCd_D3.getValue().equals(curASMsg.contrUplftTpCd_D3.getValue())) {
                result = false;
                return result;
            }
        }

        // Check_1:Type
        chkObjCnt++;
        if (upperASMsg.contrUplftTpCd_D3.getValue().equals(curASMsg.contrUplftTpCd_D3.getValue())) {
            ovrlpCnt++;
        }

        // Check_2:Method
        // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//        chkObjCnt++;
//        if (upperASMsg.uplftPrcMethCd_D3.getValue().equals(curASMsg.uplftPrcMethCd_D3.getValue())) {
//            ovrlpCnt++;
//        }
        if (hasValue(upperASMsg.uplftPrcMethCd_D3.getValue()) && hasValue(curASMsg.uplftPrcMethCd_D3.getValue())) {
            chkObjCnt++;
            if (upperASMsg.uplftPrcMethCd_D3.getValue().equals(curASMsg.uplftPrcMethCd_D3.getValue())) {
                ovrlpCnt++;
            }
        }
        // END 2017/11/20 M.Kidokoro [QC#22694, MOD]

        if (LINE_LEVEL_CONTRACT_DETAIL.equals(curASMsg.dsContrMachLvlNum_D1.getValue())) {
            // Check_3:#Days
            if (hasValue(upperASMsg.uplftBefEndRnwDaysAot_D1.getValue()) && hasValue(curASMsg.uplftBefEndRnwDaysAot_D1.getValue())) {
                chkObjCnt++;
                if (upperASMsg.uplftBefEndRnwDaysAot_D1.getValue().compareTo(curASMsg.uplftBefEndRnwDaysAot_D1.getValue()) == 0) {
                    ovrlpCnt++;
                }
            }

            if ((CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(curASMsg.contrUplftTpCd_D3.getValue())
                    || CONTR_UPLFT_TP.UPLIFT_BASE_ONLY.equals(curASMsg.contrUplftTpCd_D3.getValue()))
                    && UPLFT_PRC_METH.MARKUP_PERCENT.equals(curASMsg.uplftPrcMethCd_D3.getValue())) {
                // Check_4:Base%
                if (hasValue(upperASMsg.uplftBasePrcUpRatio_D1.getValue()) && hasValue(curASMsg.uplftBasePrcUpRatio_D1.getValue())) {
                    chkObjCnt++;
                    if (upperASMsg.uplftBasePrcUpRatio_D1.getValue().compareTo(curASMsg.uplftBasePrcUpRatio_D1.getValue()) == 0) {
                        ovrlpCnt++;
                    }
                }
            }

            if ((CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(curASMsg.contrUplftTpCd_D3.getValue())
                    || CONTR_UPLFT_TP.UPLIFT_OVERAGE_ONLY.equals(curASMsg.contrUplftTpCd_D3.getValue()))
                    && UPLFT_PRC_METH.MARKUP_PERCENT.equals(curASMsg.uplftPrcMethCd_D3.getValue())) {
                // Check_5:Ovrg%
                if (hasValue(upperASMsg.uplftMtrPrcUpRatio_D1.getValue()) && hasValue(curASMsg.uplftMtrPrcUpRatio_D1.getValue())) {
                    chkObjCnt++;
                    if (upperASMsg.uplftMtrPrcUpRatio_D1.getValue().compareTo(curASMsg.uplftMtrPrcUpRatio_D1.getValue()) == 0) {
                        ovrlpCnt++;
                    }
                }
            }
        } else if (LINE_LEVEL_BASE_OVERAGE.equals(curASMsg.dsContrMachLvlNum_D1.getValue())) {
            if (BASE.equals(curASMsg.dsContrBaseUsgNm_D1.getValue())) {
                if ((CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(curASMsg.contrUplftTpCd_D3.getValue()) || CONTR_UPLFT_TP.UPLIFT_BASE_ONLY.equals(curASMsg.contrUplftTpCd_D3.getValue()))
                        && UPLFT_PRC_METH.MARKUP_PERCENT.equals(curASMsg.uplftPrcMethCd_D3.getValue())) {
                    // Check_3:Base%
                    // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//                    chkObjCnt++;
//                    if (upperASMsg.uplftBasePrcUpRatio_D1.getValue().compareTo(curASMsg.uplftBasePrcUpRatio_D1.getValue()) == 0) {
//                        ovrlpCnt++;
//                    }
                    if (hasValue(upperASMsg.uplftBasePrcUpRatio_D1.getValue()) && hasValue(curASMsg.uplftBasePrcUpRatio_D1.getValue())) {
                        chkObjCnt++;
                        if (upperASMsg.uplftBasePrcUpRatio_D1.getValue().compareTo(curASMsg.uplftBasePrcUpRatio_D1.getValue()) == 0) {
                            ovrlpCnt++;
                        }
                    }
                    // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
                }
            } else if (OVERAGE.equals(curASMsg.dsContrBaseUsgNm_D1.getValue())) {
                if ((CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(curASMsg.contrUplftTpCd_D3.getValue()) || CONTR_UPLFT_TP.UPLIFT_OVERAGE_ONLY.equals(curASMsg.contrUplftTpCd_D3.getValue()))
                        && UPLFT_PRC_METH.MARKUP_PERCENT.equals(curASMsg.uplftPrcMethCd_D3.getValue())) {
                    // Check_3:Ovrg%
                    // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//                    chkObjCnt++;
//                    if (upperASMsg.uplftMtrPrcUpRatio_D1.getValue().compareTo(curASMsg.uplftMtrPrcUpRatio_D1.getValue()) == 0) {
//                        ovrlpCnt++;
//                    }
                    if (hasValue(upperASMsg.uplftMtrPrcUpRatio_D1.getValue()) && hasValue(curASMsg.uplftMtrPrcUpRatio_D1.getValue())) {
                        chkObjCnt++;
                        if (upperASMsg.uplftMtrPrcUpRatio_D1.getValue().compareTo(curASMsg.uplftMtrPrcUpRatio_D1.getValue()) == 0) {
                            ovrlpCnt++;
                        }
                    }
                    // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
                }
            }
        }

        // START 2018/11/16 K.Kitachi [QC#28638, DEL]
        // Check_6:Escalation Year 1
//        chkObjCnt++;
//        if (upperASMsg.xxChkBox_E1.getValue().equals(curASMsg.xxChkBox_E1.getValue())) {
//            ovrlpCnt++;
//        }
//
//        // Check_7:Escalation Year 2
//        chkObjCnt++;
//        if (upperASMsg.xxChkBox_E2.getValue().equals(curASMsg.xxChkBox_E2.getValue())) {
//            ovrlpCnt++;
//        }
//
//        // Check_8:Escalation Year 3
//        chkObjCnt++;
//        if (upperASMsg.xxChkBox_E3.getValue().equals(curASMsg.xxChkBox_E3.getValue())) {
//            ovrlpCnt++;
//        }
//
//        // Check_9:Escalation Year 4
//        chkObjCnt++;
//        if (upperASMsg.xxChkBox_E4.getValue().equals(curASMsg.xxChkBox_E4.getValue())) {
//            ovrlpCnt++;
//        }
//
//        // Check_10:Escalation Year 5
//        chkObjCnt++;
//        if (upperASMsg.xxChkBox_E5.getValue().equals(curASMsg.xxChkBox_E5.getValue())) {
//            ovrlpCnt++;
//        }
        // END 2018/11/16 K.Kitachi [QC#28638, DEL]

        // Add Start 2018/11/27 QC#28638
        // Max%
        if (hasValue(upperASMsg.maxPrcUpRatio_D1) && hasValue(curASMsg.maxPrcUpRatio_D1)) {
            chkObjCnt++;
            if (upperASMsg.maxPrcUpRatio_D1.getValue().compareTo(curASMsg.maxPrcUpRatio_D1.getValue()) == 0) {
                ovrlpCnt++;
            }
        }

        // Fixed Month
        if (hasValue(upperASMsg.fixTermInMthAot_D1) && hasValue(curASMsg.fixTermInMthAot_D1)) {
            chkObjCnt++;
            if (upperASMsg.fixTermInMthAot_D1.getValue().compareTo(curASMsg.fixTermInMthAot_D1.getValue()) == 0) {
                ovrlpCnt++;
            }
        }

        // Fixed Month
        chkObjCnt++;
        if (upperASMsg.uplftFixedDt_D1.getValue().equals(curASMsg.uplftFixedDt_D1.getValue())) {
            ovrlpCnt++;
        }

        // Policy Date
        chkObjCnt++;
        if (upperASMsg.uplftPcyDt_D1.getValue().equals(curASMsg.uplftPcyDt_D1.getValue())) {
            ovrlpCnt++;
        }
        // Add End 2018/11/27 QC#28638

        if (chkObjCnt == ovrlpCnt) {
            result = false;
        }

        return result;
    }
    // END 2017/11/15 M.Kidokoro [QC#21928-1, ADD]
    // Add Start 2018/11/28 QC#28638
    private static boolean isAggMachineLine(NSAL0740_ASMsg aSMsg) {
        if (aSMsg == null) {
            return false;
        }

        if (!DS_CONTR_CATG.AGGREGATE.equals(aSMsg.dsContrCatgCd_D1.getValue())) {
            return false;
        }

        if (!hasValue(aSMsg.dsContrDtlTpCd_D1) || DS_CONTR_DTL_TP.AGGREGATE.equals(aSMsg.dsContrDtlTpCd_D1.getValue())) {
            return false;
        }
        return true;
    }
    // Add End 2018/11/28 QC#28638
}
