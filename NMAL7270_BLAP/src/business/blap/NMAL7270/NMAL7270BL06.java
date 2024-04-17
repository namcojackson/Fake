/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7270;

import static business.blap.NMAL7260.constant.NMAL7260Constant.ZZM9015E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.CHK_A;
import static business.blap.NMAL7270.constant.NMAL7270Constant.CHK_B;
import static business.blap.NMAL7270.constant.NMAL7270Constant.COMMA;
import static business.blap.NMAL7270.constant.NMAL7270Constant.DB_DIGIT;
import static business.blap.NMAL7270.constant.NMAL7270Constant.MODE_OPENWIN_PRC;
import static business.blap.NMAL7270.constant.NMAL7270Constant.MODE_SUBMIT;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM0010E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM0011E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM0043E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM0163E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM8020E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM8054E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM8258E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM8259E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM8260E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM8326E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM8369E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NUM_100;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NZZM0003E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7270.common.NMAL7270CommonLogic;
import business.db.PRC_FMLATMsg;
import business.db.PRC_RULE_ADJ_TPTMsg;
import business.db.PRC_RULE_ATTRBTMsg;
import business.db.PRC_RULE_CATGTMsg;
import business.db.PRC_RULE_DTLTMsg;
import business.db.PRC_RULE_HDRTMsg;
import business.db.PRC_RULE_TRX_CONDTMsg;
import business.db.SPEC_COND_PRCTMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INP_OBJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7270BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         M.Nakamura      Create          N/A
 * 2017/09/01   Fujitsu         R.Nakamura      Update          QC#20729-2
 * 2018/06/07   Fujitsu         T.Noguchi       Update          QC#26099
 * 2018/09/12   Fujitsu         M.Ohno          Upadte          QC#9700
 *</pre>
 */
public class NMAL7270BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7270CMsg bizMsg = (NMAL7270CMsg) cMsg;

            if ("NMAL7270Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_CMN_Submit(bizMsg, MODE_SUBMIT);

            } else if ("NMAL7270Scrn00_OpenWin_CondGrpRules".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_CMN_Submit(bizMsg, MODE_OPENWIN_PRC);

            } else if ("NMAL7270Scrn00_Del_PrcAdjDtl".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_Del_PrcAdjDtl(bizMsg);

            } else if ("NMAL7270Scrn00_Del_TrxCond".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_Del_TrxCond(bizMsg);

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
     * @param mode String
    */
    private void doProcess_NMAL7270Scrn00_CMN_Submit(NMAL7270CMsg bizMsg, String mode) {

        // check
        boolean isErrHdr = false;
        boolean isErrTrx = false;
        boolean isErrAdj = false;

        isErrHdr = isErrHeader(bizMsg);
        isErrTrx = isErrTrxCond(bizMsg);
        if (MODE_SUBMIT.equals(mode)) {
            isErrAdj = isErrAdjDtl(bizMsg);
        }

        if (isErrHdr || isErrTrx || isErrAdj) {
            return;
        }

        // Insert/Update
        if (!submitPrcRuleHdr(bizMsg)) {
            return;
        }

        if (!submitPrcRuleTrxCond(bizMsg)) {
            return;
        }

        if (MODE_SUBMIT.equals(mode)) {
            if (!submitPrcRuleDtl(bizMsg)) {
                return;
            }
        }

    }

    /**
     * Del_PrcAdjDtl Event
     * @param bizMsg Business Msg
     * @param bizMsg Global Msg
     */
    private void doProcess_NMAL7270Scrn00_Del_PrcAdjDtl(NMAL7270CMsg bizMsg) {


        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, CHK_B, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).prcRuleDtlPk_B1)) {
                continue;
            }

            PRC_RULE_DTLTMsg tMsg = new PRC_RULE_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, bizMsg.B.no(idx).prcRuleDtlPk_B1);
            tMsg = (PRC_RULE_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.B.no(idx).ezUpTime_B1.getValue(), bizMsg.B.no(idx).ezUpTimeZone_B1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM8020E);
                return;
            }

            SPEC_COND_PRCTMsg specCondPrcTMsg = new SPEC_COND_PRCTMsg();
            ZYPEZDItemValueSetter.setValue(specCondPrcTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(specCondPrcTMsg.specCondPrcPk, bizMsg.B.no(idx).specCondPrcPk_B1);
            specCondPrcTMsg = (SPEC_COND_PRCTMsg) EZDTBLAccessor.findByKey(specCondPrcTMsg);
            EZDTBLAccessor.logicalRemove(specCondPrcTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM8020E);
                return;
            }

        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }

        ZYPTableUtil.deleteRows(bizMsg.B, selectRows);
    }

    /**
     * Del_TrxCond Event
     * @param bizMsg Business Msg
     * @param bizMsg Global Msg
     */
    private void doProcess_NMAL7270Scrn00_Del_TrxCond(NMAL7270CMsg bizMsg) {

        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        boolean isError = false;
        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).prcRuleTrxCondPk_A1)) {
                continue;
            }

            if (getUsingRuleCondGrp(bizMsg.prcRuleHdrPk_BK.getValue(), bizMsg.A.no(idx))) {
                bizMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM8260E, new String[] {"Price Condition Group Rules"});
                isError = true;
                continue;
            }

            PRC_RULE_TRX_CONDTMsg tMsg = new PRC_RULE_TRX_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, bizMsg.A.no(idx).prcRuleTrxCondPk_A1);
            tMsg = (PRC_RULE_TRX_CONDTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(idx).ezUpTime_A1.getValue(), bizMsg.A.no(idx).ezUpTimeZone_A1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM8020E);
                return;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode()) || isError) {
            return;
        }

        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
    }

    private boolean getUsingRuleCondGrp(BigDecimal prcRuleHdrPk, NMAL7270_ACMsg aCMsg) {
        if (!ZYPCommonFunc.hasValue(prcRuleHdrPk)) {
            return false;
        }

        S21SsmEZDResult ssmResult = NMAL7270Query.getInstance().getUsingRuleCondGrp(prcRuleHdrPk, aCMsg.prcRuleTrxCondPk_A1.getValue());
        if (S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())) {
            return false;
        }

        return true;
    }

    private boolean isErrHeader(NMAL7270CMsg bizMsg) {
        boolean isErr = false;

        if (isExistRuleNm(bizMsg)) {
            isErr = true;
        }

        if (LINE_BIZ_TP.ALL.equals(bizMsg.lineBizTpCd_H1.getValue())) {
            bizMsg.lineBizTpCd_H1.setErrorInfo(1, NMAM8326E, new String[] {LINE_BIZ_TP.ALL, "Line of Business"});
            isErr = true;
        }
        return isErr;
    }

    private boolean isErrTrxCond(NMAL7270CMsg bizMsg) {
        boolean isErr = false;
        boolean isRecSuccess = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            isRecSuccess = NMXC105001PriceMasterUtil.checkRuleAttrbFormat(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue(), bizMsg.A.no(i).prcRuleCondFromTxt_A1, bizMsg.A.no(i).xxRecNmTxt_A1, "Value From");
            if (isRecSuccess) {
                isRecSuccess = NMXC105001PriceMasterUtil.checkRuleAttrbFormat(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue(), bizMsg.A.no(i).prcRuleCondThruTxt_A1, bizMsg.A.no(i).xxRecNmTxt_A1, "Value To");
            }
            if (!isRecSuccess) {
                isErr = true;
            }
            if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
                if (LINE_BIZ_TP.ALL.equals(bizMsg.A.no(i).prcRuleCondFromTxt_A1.getValue())) {
                    bizMsg.A.no(i).prcRuleCondFromTxt_A1.setErrorInfo(1, NMAM8326E, new String[] {LINE_BIZ_TP.ALL, "Line of Business"});
                    isErr = true;
                }
            }
            // QC#6939 2016/05/19 Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondThruTxt_A1)) {
                PRC_RULE_ATTRBTMsg tMsg = new PRC_RULE_ATTRBTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAttrbCd, bizMsg.A.no(i).prcRuleAttrbCd_A1);
                tMsg = (PRC_RULE_ATTRBTMsg) EZDTBLAccessor.findByKey(tMsg);
                if (tMsg != null) {
                    if (INP_OBJ_TP.TEXT_NUMBER.equals(tMsg.inpObjTpCd.getValue())) {
                        if ((new BigDecimal(bizMsg.A.no(i).prcRuleCondFromTxt_A1.getValue()).compareTo(new BigDecimal(bizMsg.A.no(i).prcRuleCondThruTxt_A1.getValue()))) > 0) {
                            bizMsg.A.no(i).prcRuleCondFromTxt_A1.setErrorInfo(1, NMAM0043E, new String[] {"Value From", "Value To" });
                            isErr = true;
                        }
                    } else if (INP_OBJ_TP.DATE.equals(tMsg.inpObjTpCd.getValue())) {
                        if (ZYPDateUtil.compare(bizMsg.A.no(i).xxFromDt_A1.getValue(), bizMsg.A.no(i).xxThruDt_A1.getValue()) > 0) {
                            bizMsg.A.no(i).xxFromDt_A1.setErrorInfo(1, NMAM0043E, new String[] {"Value From", "Value To" });
                            isErr = true;
                        }
                    } else {
                        if (bizMsg.A.no(i).prcRuleCondFromTxt_A1.getValue().compareTo(bizMsg.A.no(i).prcRuleCondThruTxt_A1.getValue()) > 0) {
                            bizMsg.A.no(i).prcRuleCondFromTxt_A1.setErrorInfo(1, NMAM0043E, new String[] {"Value From", "Value To" });
                            isErr = true;
                        }
                    }
                }
            }
            // QC#6939 2016/05/19 Add End
        }
        return isErr;
    }

    private boolean isErrAdjDtl(NMAL7270CMsg bizMsg) {
        boolean isErr = false;
        PRC_RULE_CATGTMsg inTMsg = new PRC_RULE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcRuleCatgCd, bizMsg.prcRuleCatgCd_H1);
        PRC_RULE_CATGTMsg outTMsg = (PRC_RULE_CATGTMsg) ZYPCodeDataUtil.findByKey(inTMsg);
        if (outTMsg == null) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.prcRuleCatgCd_H1.getValue(), "Price Rule Category"});
            isErr = true;
            return isErr;
        }

        String[] catgAvalArray = outTMsg.avalPrcRuleModTpTxt.getValue().split(COMMA);
        String[] adjAvalArray = null;
        String ruleCmbnExclExistsFlg = null;
        String ruleCmbnExclKey = null;
        Map<String, String[]> ruleAdjTpAvalMap = new HashMap<String, String[]>();
        Map<String, String> ruleCmbnExclAvalMap = new HashMap<String, String>();
        Map<BigDecimal, PRC_FMLATMsg> ruleFmlaMap = new HashMap<BigDecimal, PRC_FMLATMsg>();
        PRC_FMLATMsg prcFmlaInTMsg = new PRC_FMLATMsg();
        PRC_FMLATMsg prcFmlaTMsg = null;
        String prcFmlaTpCd = "";
        String prcFuncTpCd = "";
        String prcRuleAdjLvlCd = null;
        String prcRuleModTpCd = null;

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (!getPrcRuleCondHdr(bizMsg, i)) {
                isErr = true;
            }

            if (!isExistAvalModTp(catgAvalArray, bizMsg.B.no(i).prcRuleModTpCd_B1.getValue())) {
                bizMsg.B.no(i).prcRuleModTpCd_B1.setErrorInfo(1, NMAM8259E, new String[] {"Price Rule Category"});
                isErr = true;
            }

            if (ruleAdjTpAvalMap.containsKey(bizMsg.B.no(i).prcRuleAdjTpCd_B1.getValue())) {
                adjAvalArray = ruleAdjTpAvalMap.get(bizMsg.B.no(i).prcRuleAdjTpCd_B1.getValue());
            } else {
                PRC_RULE_ADJ_TPTMsg inAdjToTMsg = new PRC_RULE_ADJ_TPTMsg();
                ZYPEZDItemValueSetter.setValue(inAdjToTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inAdjToTMsg.prcRuleAdjTpCd, bizMsg.B.no(i).prcRuleAdjTpCd_B1);
                PRC_RULE_ADJ_TPTMsg outAdjToTMsg = (PRC_RULE_ADJ_TPTMsg) ZYPCodeDataUtil.findByKey(inAdjToTMsg);
                if (outAdjToTMsg == null) {
                    bizMsg.B.no(i).prcRuleAdjTpCd_B1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.B.no(i).prcRuleAdjTpCd_B1.getValue(), "Price Rule Adjustment Type"});
                    isErr = true;
                } else {
                    adjAvalArray = outAdjToTMsg.avalPrcRuleModTpTxt.getValue().split(COMMA);
                    ruleAdjTpAvalMap.put(bizMsg.B.no(i).prcRuleAdjTpCd_B1.getValue(), adjAvalArray);
                }
            }

            if (!isExistAvalModTp(adjAvalArray, bizMsg.B.no(i).prcRuleModTpCd_B1.getValue())) {
                bizMsg.B.no(i).prcRuleModTpCd_B1.setErrorInfo(1, NMAM8259E, new String[] {"Price Rule Adjustment Type"});
                isErr = true;
            }

            // QC#9694 2016/06/28 Mod Start
            if (ZYPCommonFunc.hasValue(prcRuleModTpCd)) {
                if (!prcRuleModTpCd.equals(bizMsg.B.no(i).prcRuleModTpCd_B1.getValue())) {
                    bizMsg.B.no(i).prcRuleModTpCd_B1.setErrorInfo(1, NMAM8258E);
                }
            } else {
                prcRuleModTpCd = bizMsg.B.no(i).prcRuleModTpCd_B1.getValue();
            }
            if (ZYPCommonFunc.hasValue(prcRuleAdjLvlCd)) {
                if (!prcRuleAdjLvlCd.equals(bizMsg.B.no(i).prcRuleAdjLvlCd_B1.getValue())) {
                    bizMsg.B.no(i).prcRuleAdjLvlCd_B1.setErrorInfo(1, NMAM8258E);
                }
            } else {
                prcRuleAdjLvlCd = bizMsg.B.no(i).prcRuleAdjLvlCd_B1.getValue();
            }
            // QC#9694 2016/06/28 Mod End

            prcFmlaTMsg = null;
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcFmlaPk_B1)) {
                if (ruleFmlaMap.containsKey(bizMsg.B.no(i).prcFmlaPk_B1.getValue())) {
                    prcFmlaTMsg = ruleFmlaMap.get(bizMsg.B.no(i).prcFmlaPk_B1.getValue());
                    if (prcFmlaTMsg == null) {
                        bizMsg.B.no(i).prcFmlaPk_B1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.B.no(i).prcFmlaPk_B1.getValue().toString(), "Price Formula"});
                        isErr = true;
                    }
                } else {
                    prcFmlaInTMsg.clear();
                    ZYPEZDItemValueSetter.setValue(prcFmlaInTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(prcFmlaInTMsg.prcFmlaPk, bizMsg.B.no(i).prcFmlaPk_B1);
                    prcFmlaTMsg = (PRC_FMLATMsg) EZDTBLAccessor.findByKey(prcFmlaInTMsg);
                    if (prcFmlaTMsg == null) {
                        bizMsg.B.no(i).prcFmlaPk_B1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.B.no(i).prcFmlaPk_B1.getValue().toString(), "Price Formula"});
                        isErr = true;
                    }
                    ruleFmlaMap.put(bizMsg.B.no(i).prcFmlaPk_B1.getValue(), prcFmlaTMsg);
                }
            }

            if (prcFmlaTMsg == null) {
                prcFmlaTpCd = "";
                prcFuncTpCd = "";
            } else {
                prcFmlaTpCd = prcFmlaTMsg.prcFmlaTpCd.getValue();
                prcFuncTpCd = prcFmlaTMsg.prcFuncTpCd.getValue();
            }

            ruleCmbnExclKey = S21StringUtil.concatStrings(bizMsg.B.no(i).prcRuleModTpCd_B1.getValue()
                    , COMMA, bizMsg.B.no(i).prcRuleAdjTpCd_B1.getValue()
                    , COMMA, bizMsg.B.no(i).prcRuleAdjLvlCd_B1.getValue()
                    , COMMA, bizMsg.B.no(i).prcRuleAttrbCd_B1.getValue()
                    , COMMA, prcFmlaTpCd
                    , COMMA, prcFuncTpCd);
            if (ruleCmbnExclAvalMap.containsKey(ruleCmbnExclKey)) {
                ruleCmbnExclExistsFlg = ruleCmbnExclAvalMap.get(ruleCmbnExclKey);
                if (ZYPConstant.FLG_ON_Y.equals(ruleCmbnExclExistsFlg)) {
                    bizMsg.B.no(i).prcRuleModTpCd_B1.setErrorInfo(1, NMAM8369E);
                    bizMsg.B.no(i).prcRuleAdjTpCd_B1.setErrorInfo(1, NMAM8369E);
                    bizMsg.B.no(i).prcRuleAdjLvlCd_B1.setErrorInfo(1, NMAM8369E);
                    bizMsg.B.no(i).prcRuleAttrbCd_B1.setErrorInfo(1, NMAM8369E);
                    if (PRC_RULE_ATTRB.FORMULA.equals(bizMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                        bizMsg.B.no(i).prcFmlaPk_B1.setErrorInfo(1, NMAM8369E);
                    }
                }
            } else {
                if (isExistsPrcRuleCmbnExcl(bizMsg.B.no(i), prcFmlaTpCd, prcFuncTpCd)) {
                    ruleCmbnExclAvalMap.put(ruleCmbnExclKey, ZYPConstant.FLG_ON_Y);
                    bizMsg.B.no(i).prcRuleModTpCd_B1.setErrorInfo(1, NMAM8369E);
                    bizMsg.B.no(i).prcRuleAdjTpCd_B1.setErrorInfo(1, NMAM8369E);
                    bizMsg.B.no(i).prcRuleAdjLvlCd_B1.setErrorInfo(1, NMAM8369E);
                    bizMsg.B.no(i).prcRuleAttrbCd_B1.setErrorInfo(1, NMAM8369E);
                    if (PRC_RULE_ATTRB.FORMULA.equals(bizMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                        bizMsg.B.no(i).prcFmlaPk_B1.setErrorInfo(1, NMAM8369E);
                    }
                } else {
                    ruleCmbnExclAvalMap.put(ruleCmbnExclKey, ZYPConstant.FLG_OFF_N);
                }
            }

            // 2018/06/07 QC#26099 Add Start
            if (!NMAL7270CommonLogic.checkDecimalDigit(bizMsg.B.no(i).prcRuleDtlTxt_B1, 2)){
                bizMsg.B.no(i).prcRuleDtlTxt_B1.setErrorInfo(1, ZZM9015E, new String[] {"Value" });
                isErr = true;
            }
            // 2018/06/07 QC#26099 Add End
        }

        return isErr;
    }

    private boolean isExistAvalModTp(String[] valArray, String modTpCd) {
        for (String val : valArray) {
            if (modTpCd.equals(val)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistRuleNm(NMAL7270CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7270Query.getInstance().isExistRuleNm(bizMsg.prcRuleHdrPk_BK.getValue(), bizMsg.prcRuleNm_H1.getValue());
        if (!S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())) {
            bizMsg.prcRuleNm_H1.setErrorInfo(1, NMAM0010E, new String[] {bizMsg.prcRuleNm_H1.getValue()});
            return true;
        }
        return false;
    }

    private boolean isExistsPrcRuleCmbnExcl(NMAL7270_BCMsg lineBCMsg, String prcFmlaTpCd, String prcFuncTpCd) {
        S21SsmEZDResult ssmResult = NMAL7270Query.getInstance().isExistsPrcRuleCmbnExcl(lineBCMsg, prcFmlaTpCd, prcFuncTpCd);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            return true;
        }
        return false;
    }

    private boolean getPrcRuleCondHdr(NMAL7270CMsg bizMsg, int idx) {
        if (!ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK)) {
            bizMsg.B.no(idx).prcRuleCondGrpCd_B1.setErrorInfo(1, NMAM0011E, new String[] {bizMsg.B.no(idx).prcRuleCondGrpCd_B1.getValue()});
            return false;
        }

        S21SsmEZDResult ssmResult = NMAL7270Query.getInstance().getPrcRuleCondHdr(bizMsg.prcRuleHdrPk_BK.getValue(), bizMsg.B.no(idx).prcRuleCondGrpCd_B1.getValue());
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            bizMsg.B.no(idx).prcRuleCondGrpCd_B1.setErrorInfo(1, NMAM0011E, new String[] {bizMsg.B.no(idx).prcRuleCondGrpCd_B1.getValue()});
            return false;
        }

        //ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).prcRuleCondGrpPk_B1, (BigDecimal) ssmResult.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).prcRuleCondHdrPk_B1, (BigDecimal) ssmResult.getResultObject());

        return true;
    }

    private boolean submitPrcRuleHdr(NMAL7270CMsg bizMsg) {
        PRC_RULE_HDRTMsg tMsg = new PRC_RULE_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk_BK);
            tMsg = (PRC_RULE_HDRTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_RULE_HDR_SQ));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleNm, bizMsg.prcRuleNm_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDescTxt, bizMsg.prcRuleDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, bizMsg.lineBizTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCatgCd, bizMsg.prcRuleCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.applyAutoFlg, getFlgVal(bizMsg.applyAutoFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.ovrdFlg, getFlgVal(bizMsg.ovrdFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, getFlgVal(bizMsg.actvFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.effThruDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.defRulePrcdNum, bizMsg.defRulePrcdNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondTpCd, PRC_RULE_COND_TP.PRICE_RULES);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCatgCd, bizMsg.A.no(0).prcRuleTrxCatgCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);
        // 2018/09/12 QC#9700 add start
        ZYPEZDItemValueSetter.setValue(tMsg.prcGrpTrxTpCd, bizMsg.prcGrpTrxTpCd_H1);
        // 2018/09/12 QC#9700 add start

        if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK))) {
            bizMsg.setMessageInfo(NMAM8020E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleHdrPk_H1, tMsg.prcRuleHdrPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleHdrPk_BK, tMsg.prcRuleHdrPk);

        return true;
    }

    private boolean submitPrcRuleTrxCond(NMAL7270CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            PRC_RULE_TRX_CONDTMsg tMsg = new PRC_RULE_TRX_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleTrxCondPk_A1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, bizMsg.A.no(i).prcRuleTrxCondPk_A1);
                tMsg = (PRC_RULE_TRX_CONDTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A1.getValue(), bizMsg.A.no(i).ezUpTimeZone_A1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }

            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_RULE_TRX_COND_SQ));
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk_H1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCatgCd, bizMsg.A.no(i).prcRuleTrxCatgCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAttrbCd, bizMsg.A.no(i).prcRuleAttrbCd_A1);
            if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
                // Mod Start 2017/09/01 QC#20729-2
                EZDCStringItem setValueItem = bizMsg.A.no(i).prcRuleCondFromTxt_A1;
                if (ZYPCommonFunc.hasValue(setValueItem)) {
                    NMXC105001PriceMasterUtil.getMoldelIdName(getGlobalCompanyCode(), setValueItem.getValue(), false, setValueItem);
                }
//                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt, bizMsg.A.no(i).xxRecNmTxt_A1);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt, setValueItem);
                // Mod End 2017/09/01 QC#20729-2
            } else if (PRC_RULE_ATTRB.CALL_DATE.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
                    || PRC_RULE_ATTRB.PRICING_DATE.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
                    || PRC_RULE_ATTRB.REQUEST_DATE.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt, bizMsg.A.no(i).xxFromDt_A1.getValue().toString());
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt, bizMsg.A.no(i).prcRuleCondFromTxt_A1);
            }

            if (PRC_RULE_ATTRB.CALL_DATE.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
                    || PRC_RULE_ATTRB.PRICING_DATE.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())
                    || PRC_RULE_ATTRB.REQUEST_DATE.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt, bizMsg.A.no(i).xxThruDt_A1.getValue().toString());
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt, bizMsg.A.no(i).prcRuleCondThruTxt_A1);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.A.no(i).effFromDt_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.A.no(i).effThruDt_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.inpReqFlg, ZYPConstant.FLG_ON_Y);

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleTrxCondPk_A1))) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }
        }
        return true;
    }

    private boolean submitPrcRuleDtl(NMAL7270CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            PRC_RULE_DTLTMsg tMsg = new PRC_RULE_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcRuleDtlPk_B1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, bizMsg.B.no(i).prcRuleDtlPk_B1);
                tMsg = (PRC_RULE_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(bizMsg.B.no(i).ezUpTime_B1.getValue(), bizMsg.B.no(i).ezUpTimeZone_B1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }

            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_RULE_DTL_SQ));
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk_H1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondGrpPk, bizMsg.B.no(i).prcRuleCondGrpPk_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, bizMsg.B.no(i).prcRuleCondHdrPk_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlChrgNm, bizMsg.B.no(i).prcRuleDtlChrgNm_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleModTpCd, bizMsg.B.no(i).prcRuleModTpCd_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAdjTpCd, bizMsg.B.no(i).prcRuleAdjTpCd_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAdjLvlCd, bizMsg.B.no(i).prcRuleAdjLvlCd_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaPk, bizMsg.B.no(i).prcFmlaPk_B1);
            // QC#6397 2016/04/06 Mod Start
            // ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlRate, bizMsg.B.no(i).prcRuleDtlRate_B1);
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcRuleDtlRate_B1)) {
                BigDecimal rate = bizMsg.B.no(i).prcRuleDtlRate_B1.getValue().divide(NUM_100, DB_DIGIT, BigDecimal.ROUND_HALF_DOWN);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlRate, rate);
            }
            // QC#6397 2016/04/06 Mod End
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlTxt, bizMsg.B.no(i).prcRuleDtlTxt_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.B.no(i).effFromDt_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.B.no(i).effThruDt_B1);
            tMsg.prcRuleCondNum.clear();
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAttrbCd, bizMsg.B.no(i).prcRuleAttrbCd_B1);

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcRuleDtlPk_B1))) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcRuleDtlPk_B1)) {
                if (!submitSpecCondPrc(bizMsg, tMsg.prcRuleDtlPk.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean submitSpecCondPrc(NMAL7270CMsg bizMsg, BigDecimal prcRuleDtlPk) {

        SPEC_COND_PRCTMsg tMsg = new SPEC_COND_PRCTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPEC_COND_PRC_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, prcRuleDtlPk);

        if (!submitTbl(tMsg, false)) {
            bizMsg.setMessageInfo(NMAM8020E);
            return false;
        }

        return true;
    }

    private String getFlgVal(String flg) {
        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
            return flg;
        }
        return ZYPConstant.FLG_OFF_N;
    }

    private boolean submitTbl(EZDTMsg inTMsg, boolean isExists) {
        if (isExists) {
            EZDTBLAccessor.update(inTMsg);
        } else {
            EZDTBLAccessor.insert(inTMsg);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
}
