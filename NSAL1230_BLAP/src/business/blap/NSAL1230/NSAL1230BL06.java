/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1230;

import static business.blap.NSAL1230.constant.NSAL1230Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1230.common.NSAL1230CommonLogic;
import business.db.COA_ACCTTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_CHTMsg;
import business.db.COA_CMPYTMsg;
import business.db.COA_EXTNTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.parts.NSZC080001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC080001.NSZC080001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/01/21   Hitachi         A.Kohinata      Update          CSA QC#3352
 * 07/18/2017   CITS            K.Ogino         Update          QC#19433
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 * 2018/05/07   Hitachi         U.Kim           Update          QC#25895
 * 2018/05/18   Hitachi         U.Kim           Update          QC#25896
 *</pre>
 */
public class NSAL1230BL06 extends S21BusinessHandler {

    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL1230CMsg cMsg = (NSAL1230CMsg) ezdCMsg;
        NSAL1230SMsg sMsg = (NSAL1230SMsg) ezdSMsg;
        String screenAplId = cMsg.getScreenAplID();
        if ("NSAL1230Scrn00_CMN_Submit".equals(screenAplId)) {
            return checkInput_NSAL1230Scrn00_CMN_Submit(cMsg, sMsg);
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
        }
    }

    private boolean checkInput_NSAL1230Scrn00_CMN_Submit(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {

        if (!checkRelation(cMsg)) {
            return false;
        }

        if (!checkSegmentElementLength(cMsg)) {
            return false;
        }

        update9Segments(cMsg);

        if (!checkMaster(cMsg)) {
            return false;
        }

        return true;
    }

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1230CMsg cMsg = (NSAL1230CMsg) arg0;
        NSAL1230SMsg sMsg = (NSAL1230SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1230Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1230Scrn00_CMN_Submit(cMsg, sMsg);

            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1230Scrn00_CMN_Submit(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {

        // del start 2016/10/05 CSA Defect#13815
        //if (!registerDsContrSegAlloc(cMsg)) {
        //    return;
        //}

        //// add start 2016/01/21 CSA Defect#3352
        //if (!isExistsDsContrBrAlloc(cMsg)) {
        //    cMsg.setMessageInfo(NSAM0200I);
        //    return;
        //}
        //// add end 2016/01/21 CSA Defect#3352

        if (!callApi(cMsg)) {
            return;
        }
        cMsg.setMessageInfo(NSAM0200I);
    }

    private boolean checkRelation(NSAL1230CMsg cMsg) {

        boolean result = true;

        // START 2018/05/18 U.Kim [QC#25896, DEL]
        // if (cMsg.A.getValidCount() == 0) {
        //     cMsg.setMessageInfo(NSAM0020E);
        //     result = false;
        //     return result;
        // }
        // END 2018/05/18 U.Kim [QC#25896, DEL]

        Map<String, String> segmentMap = new HashMap<String, String>();
        BigDecimal prcAllocRateTotal = BigDecimal.ZERO;
        BigDecimal prcAllocAmtTotal = BigDecimal.ZERO;    // QC#23378(Sol#320)
        // START 2018/05/07 U.Kim [QC#25859, ADD]
        boolean percentSetFlag = false;
        boolean priceSetFlag = false;
        // END 2018/05/07 U.Kim [QC#25859, ADD]
        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            String segmentString = cMsg.A.no(index).coaAfflAcctNm_A1.getValue();
            if (segmentMap.containsKey(segmentString)) {
                cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0035E, new String[] {MSG_PARAM_SEGMENT });
                result = false;
            } else {
                segmentMap.put(segmentString, segmentString);
                // QC#23378(Sol#320) Mod Start
                if (ZYPCommonFunc.hasValue(cMsg.A.no(index).prcAllocRate_A1)) {
                    BigDecimal prcAllocRate = cMsg.A.no(index).prcAllocRate_A1.getValue();
                    prcAllocRateTotal = prcAllocRateTotal.add(prcAllocRate);
                    // START 2018/05/07 U.Kim [QC#25859, ADD]
                    percentSetFlag = true;
                    // END 2018/05/07 U.Kim [QC#25859, ADD]
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(index).prcAllocAmt_A1)) {
                    BigDecimal prcAllocAmt = cMsg.A.no(index).prcAllocAmt_A1.getValue();
                    prcAllocAmtTotal = prcAllocAmtTotal.add(prcAllocAmt);
                    // START 2018/05/07 U.Kim [QC#25859, ADD]
                    priceSetFlag = true;
                    // END 2018/05/07 U.Kim [QC#25859, ADD]
                }
                // QC#23378(Sol#320) Mod End
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.prcAllocRate, prcAllocRateTotal);
        // QC#23378(Sol#320) Mod Start
        if(result) {
            // START 2018/05/07 U.Kim [QC#25859, MOD]
            // if (prcAllocRateTotal.compareTo(BigDecimal.ZERO) != 0) {
            if (percentSetFlag) {
            // END 2018/05/07 U.Kim [QC#25859, MOD]
                if (DEFAULT_PERCENTAGE.compareTo(prcAllocRateTotal) != 0) {
                    cMsg.setMessageInfo(NSAM0402E);
                    result = false;
                    return result;
                }
            }
            // START 2018/05/07 U.Kim [QC#25859, ADD]
            if (priceSetFlag) {
                if (cMsg.basePrcDealAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                    cMsg.setMessageInfo(NSAM0448E, new String[] {PRICE, TOTALPRICE, BigDecimal.ZERO.toString() });
                    result = false;
                    return result;
                }
            }
            // END 2018/05/07 U.Kim [QC#25859, ADD]
            // START 2018/05/07 U.Kim [QC#25859, MOD]
            // if (prcAllocAmtTotal.compareTo(BigDecimal.ZERO) != 0 && ZYPCommonFunc.hasValue(cMsg.basePrcDealAmt)) {
            if (priceSetFlag && ZYPCommonFunc.hasValue(cMsg.basePrcDealAmt)) {
            // END 2018/05/07 U.Kim [QC#25859, MOD]
                if (cMsg.basePrcDealAmt.getValue().compareTo(prcAllocAmtTotal) != 0) {
                    cMsg.setMessageInfo(NSAM0715E, new String[] {cMsg.basePrcDealAmt.getValue().toString()});
                    result = false;
                    return result;
                }
            }
        }
        // QC#23378(Sol#320) Mod End

        return result;
    }

    private boolean checkSegmentElementLength(NSAL1230CMsg cMsg) {
        return NSAL1230CommonLogic.validateSegmentString(getGlobalCompanyCode(), cMsg);
    }

    private void update9Segments(NSAL1230CMsg cMsg) {
        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            NSAL1230CommonLogic.reflectSegmentStringTo9Segments(cMsg.A.no(index));
        }
    }

    private boolean checkMaster(NSAL1230CMsg cMsg) {

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            // check COA_CMPY_CD
            if (!isExistsCoaCmpyCd(cMsg, index)) {
                return false;
            }

            // check COA_EXTN_CD
            if (!isExistsCoaExtnCd(cMsg, index)) {
                return false;
            }

            // check COA_CC_CD
            if (!isExistsCoaCcCd(cMsg, index)) {
                return false;
            }

            // check COA_ACCT_CD
            if (!isExistsCoaAcctCd(cMsg, index)) {
                return false;
            }

            // check COA_PROJ_CD
            if (!isExistsCoaProjCd(cMsg, index)) {
                return false;
            }

            // check COA_PROD_CD
            if (!isExistsCoaProdCd(cMsg, index)) {
                return false;
            }

            // check COA_AFFL_CD
            if (!isExistsCoaAfflCd(cMsg, index)) {
                return false;
            }

            // check COA_CH_CD
            if (!isExistsCoaChCd(cMsg, index)) {
                return false;
            }
        }

        return true;
    }

    private boolean isExistsCoaCmpyCd(NSAL1230CMsg cMsg, int index) {

        COA_CMPYTMsg inMsg = new COA_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaCmpyCd, cMsg.A.no(index).coaCmpyCd_A1);
        COA_CMPYTMsg outMsg = (COA_CMPYTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_CMPY });
            return false;
        }
    }

    private boolean isExistsCoaExtnCd(NSAL1230CMsg cMsg, int index) {

        COA_EXTNTMsg inMsg = new COA_EXTNTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaExtnCd, cMsg.A.no(index).coaExtnCd_A1);
        COA_EXTNTMsg outMsg = (COA_EXTNTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_EXTN });
            return false;
        }
    }

    private boolean isExistsCoaCcCd(NSAL1230CMsg cMsg, int index) {

        COA_CCTMsg inMsg = new COA_CCTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaCcCd, cMsg.A.no(index).coaCcCd_A1);
        COA_CCTMsg outMsg = (COA_CCTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_CC });
            return false;
        }
    }

    private boolean isExistsCoaAcctCd(NSAL1230CMsg cMsg, int index) {

        COA_ACCTTMsg inMsg = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAcctCd, cMsg.A.no(index).coaAcctCd_A1);
        COA_ACCTTMsg outMsg = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_ACCT });
            return false;
        }
    }

    private boolean isExistsCoaProjCd(NSAL1230CMsg cMsg, int index) {

        COA_PROJTMsg inMsg = new COA_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProjCd, cMsg.A.no(index).coaProjCd_A1);
        COA_PROJTMsg outMsg = (COA_PROJTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_PROJ });
            return false;
        }
    }

    private boolean isExistsCoaProdCd(NSAL1230CMsg cMsg, int index) {

        COA_PRODTMsg inMsg = new COA_PRODTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, cMsg.A.no(index).coaProdCd_A1);
        COA_PRODTMsg outMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_PROD });
            return false;
        }
    }

    private boolean isExistsCoaAfflCd(NSAL1230CMsg cMsg, int index) {

        COA_AFFLTMsg inMsg = new COA_AFFLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAfflCd, cMsg.A.no(index).coaAfflCd_A1);
        COA_AFFLTMsg outMsg = (COA_AFFLTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_AFFL });
            return false;
        }
    }

    private boolean isExistsCoaChCd(NSAL1230CMsg cMsg, int index) {

        COA_CHTMsg inMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.A.no(index).glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.coaChCd, cMsg.A.no(index).coaChCd_A1);
        COA_CHTMsg outMsg = (COA_CHTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return true;
        } else {
            cMsg.A.no(index).coaAfflAcctNm_A1.setErrorInfo(1, NSAM0011E, new String[] {MSG_PARAM_CH });
            return false;
        }
    }

    private boolean registerDsContrSegAlloc(NSAL1230CMsg cMsg) {

        DS_CONTR_SEG_ALLOCTMsg findKeyTMsg = new DS_CONTR_SEG_ALLOCTMsg();

        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk.getValue()) && ZYPCommonFunc.hasValue(cMsg.svcInvChrgTpCd.getValue())) {
            findKeyTMsg.setSQLID("001");
            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
            findKeyTMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
            findKeyTMsg.setConditionValue("svcInvChrgTpCd01", cMsg.svcInvChrgTpCd.getValue());

        } else if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk.getValue())) {
            findKeyTMsg.setSQLID("002");
            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
            findKeyTMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());

        } else {
            findKeyTMsg.setSQLID("003");
            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }

        DS_CONTR_SEG_ALLOCTMsgArray tMsgArray = (DS_CONTR_SEG_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(findKeyTMsg);
        if (tMsgArray != null && tMsgArray.length() > 0) {
            for (int index = 0; index < tMsgArray.length(); index++) {
                EZDTBLAccessor.remove(tMsgArray.no(index));
            }
        }

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            DS_CONTR_SEG_ALLOCTMsg inMsg = new DS_CONTR_SEG_ALLOCTMsg();
            setInsertParamDsContrSegAlloc(inMsg, cMsg, cMsg.A.no(index));
            EZDTBLAccessor.insert(inMsg);
            String rtnCd = inMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                return false;
            }
        }

        return true;
    }

    private static void setInsertParamDsContrSegAlloc(DS_CONTR_SEG_ALLOCTMsg tmsg, NSAL1230CMsg cMsg, NSAL1230_ACMsg aCMsg) {
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, aCMsg.glblCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrSegAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SEG_ALLOC_SQ));
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, aCMsg.dsContrPk_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, aCMsg.dsContrDtlPk_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.svcInvChrgTpCd, aCMsg.svcInvChrgTpCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.coaCmpyCd, aCMsg.coaCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.coaAfflCd, aCMsg.coaAfflCd_A1);
        //
        ZYPEZDItemValueSetter.setValue(tmsg.coaBrCd, COA_BR_CD);
        //
        ZYPEZDItemValueSetter.setValue(tmsg.coaChCd, aCMsg.coaChCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.coaCcCd, aCMsg.coaCcCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.coaAcctCd, aCMsg.coaAcctCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, aCMsg.coaProdCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.coaProjCd, aCMsg.coaProjCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.coaExtnCd, aCMsg.coaExtnCd_A1);
        ZYPEZDItemValueSetter.setValue(tmsg.prcAllocRate, aCMsg.prcAllocRate_A1);
    }

    // add start 2016/01/21 CSA QC#3352
    private boolean isExistsDsContrBrAlloc(NSAL1230CMsg cMsg) {
        DS_CONTR_BR_ALLOCTMsg inMsg = new DS_CONTR_BR_ALLOCTMsg();
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        if (hasValue(cMsg.dsContrDtlPk) && hasValue(cMsg.svcInvChrgTpCd)) {
            inMsg.setSQLID("001");
            inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
            inMsg.setConditionValue("svcInvChrgTpCd01", cMsg.svcInvChrgTpCd.getValue());
        } else if (hasValue(cMsg.dsContrDtlPk)) {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
        } else {
            inMsg.setSQLID("003");
        }

        DS_CONTR_BR_ALLOCTMsgArray tMsgArray = (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }
    // add end 2016/01/21 CSA QC#3352

    private boolean callApi(NSAL1230CMsg cMsg) {

        NSZC080001 api = new NSZC080001();

        // mod start 2016/10/05 CSA Defect#13815
        NSZC080001PMsg pMsg = new NSZC080001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, cMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, cMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.svcInvChrgTpCd, cMsg.svcInvChrgTpCd);
        // QC#19433 Start
        ZYPEZDItemValueSetter.setValue(pMsg.resrcObjNm, BUSINESS_ID);
        // QC#19433 End

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaCmpyCd, cMsg.A.no(index).coaCmpyCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaAfflCd, cMsg.A.no(index).coaAfflCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaBrCd, cMsg.A.no(index).coaBrCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaChCd, cMsg.A.no(index).coaChCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaCcCd, cMsg.A.no(index).coaCcCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaAcctCd, cMsg.A.no(index).coaAcctCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaProdCd, cMsg.A.no(index).coaProdCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaProjCd, cMsg.A.no(index).coaProjCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).coaExtnCd, cMsg.A.no(index).coaExtnCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).prcAllocRate, cMsg.A.no(index).prcAllocRate_A1);
            // QC#23378(Sol#320) Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.segmentsList.no(index).prcAllocAmt, cMsg.A.no(index).prcAllocAmt_A1);
            // QC#23378(Sol#320) Add End
        }
        pMsg.segmentsList.setValidCount(cMsg.A.getValidCount());

        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            cMsg.setMessageInfo(msgList.get(0).getXxMsgid(), msgList.get(0).getXxMsgPrmArray());
            return false;
        }

        return true;
        // mod end 2016/10/05 CSA Defect#13815
    }
}
