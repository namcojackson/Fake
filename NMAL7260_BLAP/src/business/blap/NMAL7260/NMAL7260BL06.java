/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7260;

import static business.blap.NMAL7260.constant.NMAL7260Constant.CHK_B;
import static business.blap.NMAL7260.constant.NMAL7260Constant.CHK_C;
import static business.blap.NMAL7260.constant.NMAL7260Constant.DB_DIGIT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8685E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0179E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8020E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8054E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8330I;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NUM_100;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NZZM0003E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.ROW_ID_B;
import static business.blap.NMAL7260.constant.NMAL7260Constant.ZZM8100I;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7260.common.NMAL7260CommonLogic;
import business.blap.NMAL7260.constant.NMAL7260Constant;
import business.db.PRC_ADJ_CONDTMsg;
import business.db.PRC_ADJ_DTLTMsg;
import business.db.PRC_RULE_HDRTMsg;
import business.db.SPEC_COND_PRCTMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7260BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/03/31   Fujitsu         M.Nakamura      Update          QC#6099
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/09/28   Fujitsu         R.Nakamura      Update          QC#6924
 * 2016/10/11   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/11/11   Fujitsu         R.Nakamura      Update          QC#5940
 * 2017/08/31   Fujitsu         R.Nakamura      Update          QC#20729-2
 * 2017/11/10   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569
 * 2018/07/19   Fujitsu         W.Honda         Update          QC#20267
 * 2018/09/12   Fujitsu         M.Ohno          Update          QC#9700
 *</pre>
 */
public class NMAL7260BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;
            NMAL7260SMsg glblMsg = (NMAL7260SMsg) sMsg;

            if ("NMAL7260Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_DeleteRow_TblDef".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_DeleteRow_TblDef(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_DeleteRow_TblData".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_DeleteRow_TblData(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_CMN_Delete(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_OpenWin_QtyBreak".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OpenWin_QtyBreak(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_CMN_Save(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        String globalCompanyCode = getGlobalCompanyCode();
        boolean isHddErr = false;
        boolean isTblErr = false;

        bizMsg.xxRowNum.clear();
        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.B, ROW_ID_B, ZYPConstant.FLG_ON_Y);

        // check
        isHddErr = isErrHeader(bizMsg, globalCompanyCode);
        isTblErr = isErrTblData(bizMsg, glblMsg, selectRows);
        if (isHddErr || isTblErr) {
            NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, bizMsg.xxRowNum.getValueInt());
            bizMsg.setCommitSMsg(true);
            return;
        }
        NMAL7260CommonLogic.updateGlblMsgForDtl(bizMsg, glblMsg, selectRows);
        selectRows = ZYPTableUtil.getSelectedRows(glblMsg.U, ROW_ID_B, ZYPConstant.FLG_ON_Y);


        // ** insert/update **
        if (!submitPrcRuleHdr(bizMsg, glblMsg)) {
            return;
        }

        if (!submitPrcAdjCond(bizMsg, glblMsg)) {
            return;
        }

        if (!submitPrcAdjDtl(bizMsg, glblMsg, selectRows)) {
            return;
        }

        if (!submitSpecCondPrc(bizMsg, glblMsg, selectRows)) {
            return;
        }
    }

    /**
     * doProcess_NMAL7260Scrn00_CMN_Delete
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NMAL7260Scrn00_CMN_Delete(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        // Error Check
        if (NMAL7260CommonLogic.deleteCheck(bizMsg)) {
            return;
        }
        // Warning comment
        if (ZYPConstant.FLG_OFF_0.equals(bizMsg.xxWrnSkipFlg_H0.getValue())) {
            bizMsg.setMessageInfo(NMAM8330I, new String[] {"Price Adjustment Table" });
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H0, ZYPConstant.FLG_ON_1);
            return;
        }

        if (NMAL7260CommonLogic.deletePrcRuleHdr(bizMsg, glblCmpyCd, bizMsg.prcRuleHdrPk_H1.getValue(), bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue())) {
            
        }

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7260Query.getInstance().getClass());

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
            ps = ssmLLClient.createPreparedStatement("getPrcAdjCondData", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                BigDecimal prcAdjCondPk = rs.getBigDecimal("PRC_ADJ_COND_PK");
                if (ZYPCommonFunc.hasValue(prcAdjCondPk)) {
                    if (NMAL7260CommonLogic.deleteAdjTrxCond(bizMsg, glblCmpyCd, prcAdjCondPk, //
                            rs.getString("EZUPTIME"), rs.getString("EZUPTIMEZONE"))) {
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        List<Map<?, ?>> listMap = new ArrayList<Map<?, ?>>();
        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().getPriceAdjDtlForDelete(bizMsg);
        if (ssmResult.isCodeNormal()) {
            listMap = (List<Map<?, ?>>) ssmResult.getResultObject();
        }

        for (Map<?, ?> data : listMap) {
            // delete table(PRC_ADJ_DTL)
            BigDecimal prcRuleDtlPk = (BigDecimal) data.get("PRC_ADJ_DTL_PK");
            if (ZYPCommonFunc.hasValue(prcRuleDtlPk)) {
                if (NMAL7260CommonLogic.deletePrcAdjDtlTbl(bizMsg, glblCmpyCd, prcRuleDtlPk, (String)data.get("EZUPTIME"), (String)data.get("EZUPTIMEZONE"))) {
                    return;
                }
            }
            // delete table(SPEC_COND_PRC)
            BigDecimal specCondPrcPk = (BigDecimal) data.get("SPEC_COND_PRC_PK");
            if (ZYPCommonFunc.hasValue(specCondPrcPk)) {
                if (NMAL7260CommonLogic.deleteSpecCondPrcTbl(bizMsg, glblCmpyCd, specCondPrcPk, (String)data.get("EZUPTIME_SPEC_COND_PRC"), (String)data.get("EZUPTIMEZONE_SPEC_COND_PRC"))) {
                    return;
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H0, ZYPConstant.FLG_OFF_0);
        bizMsg.setMessageInfo(ZZM8100I);
        
    }

    /**
     * Del_TrxCond Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_DeleteRow_TblData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.B, CHK_B, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        // Warning comment
        if (ZYPConstant.FLG_OFF_0.equals(bizMsg.xxWrnSkipFlg_H2.getValue())) {
            bizMsg.setMessageInfo(NMAM8330I, new String[] {"Table Data" });
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_ON_1);
            return;
        }

        for (int idx : selectRows) {
            // delete table(PRC_ADJ_DTL)
            BigDecimal prcAdjDtlPk = glblMsg.B.no(idx).prcAdjDtlPk_B1.getValue();
            BigDecimal prcRuleDtlPk = glblMsg.B.no(idx).prcRuleDtlPk_B1.getValue();
            if (ZYPCommonFunc.hasValue(prcRuleDtlPk)) {
                if (NMAL7260CommonLogic.deletePrcAdjDtlTbl(bizMsg, glblCmpyCd, prcAdjDtlPk, glblMsg.B.no(idx).ezUpTime_B1.getValue(), glblMsg.B.no(idx).ezUpTimeZone_B1.getValue())) {
                    return;
                }
            }
            
            // delete table(SPEC_COND_PRC)
            BigDecimal specCondPrcPk = glblMsg.B.no(idx).specCondPrcPk_B1.getValue();
            if (ZYPCommonFunc.hasValue(specCondPrcPk)) {
                if (NMAL7260CommonLogic.deleteSpecCondPrcTbl(bizMsg, glblCmpyCd, specCondPrcPk, glblMsg.B.no(idx).ezUpTime_B3.getValue(), glblMsg.B.no(idx).ezUpTimeZone_B3.getValue())) {
                    return;
                }
            }

            List<Integer> list = NMAL7260CommonLogic.getQtyDiscountData(glblMsg, prcAdjDtlPk);
            // delete table(PRC_ADJ_DTL)
            for (Integer idx2 : list) {
                BigDecimal prcAdjDtlPk2 = glblMsg.T.no(idx2).prcAdjDtlPk_T.getValue();
                BigDecimal prcRuleDtlPk2 = glblMsg.T.no(idx2).prcRuleDtlPk_T.getValue();
                if (ZYPCommonFunc.hasValue(prcRuleDtlPk2)) {
                    if (ZYPCommonFunc.hasValue(prcRuleDtlPk2)) {
                        if (NMAL7260CommonLogic.deletePrcAdjDtlTbl(bizMsg, glblCmpyCd, prcAdjDtlPk2, glblMsg.T.no(idx2).ezUpTime_T1.getValue(), glblMsg.T.no(idx).ezUpTimeZone_T1.getValue())) {
                            return;
                        }
                    }

                    // delete table(SPEC_COND_PRC)
                    BigDecimal specCondPrcPk2 = glblMsg.T.no(idx2).specCondPrcPk_T.getValue();
                    if (ZYPCommonFunc.hasValue(specCondPrcPk2)) {
                        if (NMAL7260CommonLogic.deleteSpecCondPrcTbl(bizMsg, glblCmpyCd, specCondPrcPk2, glblMsg.T.no(idx2).ezUpTime_T3.getValue(), glblMsg.T.no(idx).ezUpTimeZone_T3.getValue())) {
                            return;
                        }
                    }
                } else{
                    ZYPTableUtil.deleteRows(glblMsg.T, list);
                }
            }
            glblMsg.B.no(idx).clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }

        ZYPTableUtil.deleteRows(glblMsg.B, selectRows);
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, 0);
    }

    /**
     * doProcess_NMAL7260Scrn00_OpenWin_QtyBreak
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     */
    private void doProcess_NMAL7260Scrn00_OpenWin_QtyBreak(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if(!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).prcAdjDtlPk_B1.getValue())){
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).prcAdjDtlPk_B1, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_ADJ_DTL_SQ));
        }
    }
    
    /**
     * Del_TrxCond Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_DeleteRow_TblDef(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_H1, ZYPConstant.FLG_ON_Y);

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.C, CHK_C, ZYPConstant.FLG_ON_Y);

        // Error Check
        if (NMAL7260CommonLogic.deleteRowCheck(bizMsg, selectRows)) {
            return;
        }

    }

    // error check(Header(7,9,10))
    private boolean isErrHeader(NMAL7260CMsg bizMsg, String globalCompanyCode) {
        boolean isErr = false;

        if (NMAL7260CommonLogic.isExistRuleNm(bizMsg)) {
            isErr = true;
        }

        if (!NMAL7260CommonLogic.isExistPrcRuleCatgCd(bizMsg, globalCompanyCode)) {
            isErr = true;
        }

        if (!NMAL7260CommonLogic.isExistPrcRuleAdjTpCd(bizMsg, globalCompanyCode)) {
            isErr = true;
        }

        return isErr;
    }

    // error check
    private boolean isErrTblData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {
        boolean isErr = false;

        if (NMAL7260CommonLogic.checkCommonTableData(bizMsg, glblMsg, selectRows)) {
            isErr = true;
        }

        for (int i : selectRows) {
            // item check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_10)) {
                if (!NMAL7260CommonLogic.isExistMdseNm(bizMsg, glblMsg, getGlobalCompanyCode(), i)) {
                    // 2018/07/17 QC#20267 Mod Start
//                  isErr = true;
                    if (glblMsg.B.no(i).prcRuleCondFromTxt_10.getErrorCode() != 0) {
                        isErr = true;
                    } else {
                        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().getBaseMdseCdFromMnfItemCd(bizMsg, glblMsg.B.no(i).prcRuleCondFromTxt_10.getValue());
                        if (ssmResult.isCodeNotFound()) {
                            glblMsg.B.no(i).prcRuleCondFromTxt_10.setErrorInfo(1, NMAM0179E, new String[] {glblMsg.B.no(i).prcRuleCondFromTxt_10.getValue(), "No Service Item" });
                            isErr = true;
                        } else {
                            if (!NMAL7260CommonLogic.isExistMnfItemCd(glblMsg, getGlobalCompanyCode(), i, ssmResult)) {
                                glblMsg.B.no(i).prcRuleCondFromTxt_10.setErrorInfo(1, NMAM8685E);
                                isErr = true;
                            }
                        }
                    }
                    // 2018/07/17 QC#20267 Mod End
                }
            }
        }
        if (!NMAL7260CommonLogic.isExistCodeTbl(bizMsg, glblMsg, selectRows, getGlobalCompanyCode())) {
            isErr = true;
        }
        if (NMAL7260CommonLogic.checkTableData(bizMsg, glblMsg, getGlobalCompanyCode(), selectRows)) {
            isErr = true;
        }
        return isErr;
    }

    private boolean submitPrcRuleHdr(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        PRC_RULE_HDRTMsg tMsg = new PRC_RULE_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk_BK);
            tMsg = (PRC_RULE_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
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
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrDescTxt, bizMsg.prcRuleHdrDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrCmntTxt, bizMsg.xxFldValTxt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, bizMsg.lineBizTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCatgCd, bizMsg.prcRuleCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.applyAutoFlg, getFlgVal(bizMsg.applyAutoFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.ovrdFlg, getFlgVal(bizMsg.ovrdFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, getFlgVal(bizMsg.actvFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.effThruDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.defRulePrcdNum, bizMsg.defRulePrcdNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondTpCd, PRC_RULE_COND_TP.PRICE_ADJUSTMENT_TABLE);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCatgCd, PRC_RULE_TRX_CATG.ORDER);
        ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAdjLvlCd, bizMsg.prcRuleAdjLvlCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleModTpCd, bizMsg.prcRuleModTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAdjTpCd, bizMsg.prcRuleAdjTpCd_H1);
        // 2018/09/12 QC#9700 add start
        ZYPEZDItemValueSetter.setValue(tMsg.prcGrpTrxTpCd, bizMsg.prcGrpTrxTpCd_H1);
        // 2018/09/12 QC#9700 add start

        boolean isExists = ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK);
        if (!submitTbl(tMsg, isExists)) {
            bizMsg.setMessageInfo(NMAM8020E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleHdrPk_H1, tMsg.prcRuleHdrPk);

//        if (!isExists) {
//            // Dummy Record
//            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//                EZDCStringItem prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1;
//                if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd.getValue()) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd.getValue()) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd.getValue())) {
//                    if (!submitDummyPrcRuleDtl(bizMsg, i, prcRuleAttrbCd)) {
//                        return false;
//                    }
//
//                    if (glblMsg.B.getValidCount() == 0) {
//                        glblMsg.B.no(0).effFromDt_B1.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
//                        glblMsg.B.setValidCount(1);
//                    }
//                }
//            }
//            
//        }

        return true;
    }

//    private boolean submitPrcRuleTrxCond(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {
//        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            EZDCStringItem prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1;
//
//            if (!PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd.getValue()) && !PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd.getValue()) && !PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd.getValue())) {
//                // Mod Start 2016/10/11 QC#14936
////                if (!submitPrcRuleTrxCond(bizMsg, glblMsg, i, prcRuleAttrbCd)) {
//                if (!submitPrcRuleTrxCond(bizMsg, glblMsg, i, selectRows, prcRuleAttrbCd)) {
//                // Mod End 2016/10/11 QC#14936
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

//    private boolean submitPrcAdjDtl(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {
//
//        boolean workNewFlg = false;
//        for (int i : selectRows) {
//
//            PRC_ADJ_DTLTMsg tMsg = new PRC_ADJ_DTLTMsg();
//            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcAdjDtlPk_B1)) {
//                ZYPEZDItemValueSetter.setValue(tMsg.prcAdjDtlPk, glblMsg.B.no(i).prcAdjDtlPk_B1);
//                tMsg = (PRC_ADJ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
//                if (tMsg == null) {
//                    bizMsg.setMessageInfo(NZZM0003E);
//                    return false;
//                } else {
//                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.B.no(i).ezUpTime_B1.getValue(), glblMsg.B.no(i).ezUpTimeZone_B1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
//                        bizMsg.setMessageInfo(NZZM0003E);
//                        return false;
//                    }
//                }
//                workNewFlg = true;
//            } else {
//                ZYPEZDItemValueSetter.setValue(tMsg.prcAdjDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_ADJ_DTL_SQ));
//                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_RULE_DTL_SQ));
//                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcAdjDtlPk_B1, tMsg.prcAdjDtlPk);
//                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcRuleDtlPk_B1, tMsg.prcRuleDtlPk);
//                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).effFromDt_B1.getValue())) {
//                    glblMsg.B.no(i).effFromDt_B1.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
//                }
//                workNewFlg = false;
//            }
//
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk_H1);
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlChrgNm, bizMsg.prcRuleNm_H1);
//
//            if(ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcFmlaPk_B1)){
//                ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaPk, glblMsg.B.no(i).prcFmlaPk_B1);
//            }
//            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleDtlRate_B1)) {
//                BigDecimal rate = glblMsg.B.no(i).prcRuleDtlRate_B1.getValue().divide(NUM_100, DB_DIGIT, BigDecimal.ROUND_HALF_DOWN);
//                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlRate, rate);
//            } else {
//                //for dummy row
//                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlRate, glblMsg.B.no(i).prcRuleDtlRate_B1);
//            }
//            if(ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleDtlTxt_B1)){
//                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlTxt, glblMsg.B.no(i).prcRuleDtlTxt_B1);
//            }
//            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.B.no(i).effFromDt_B1);
//            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.B.no(i).effThruDt_B1);
//
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_04, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_04.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_05, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_05.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_06, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_06.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_07, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_07.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_08, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_08.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_09, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_09.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_10, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_10.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_11, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_11.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_12, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_12.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_13, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_13.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_14, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_14.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_15, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_15.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_15, convVal(glblMsg.B.no(i).prcRuleCondThruTxt_15.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_16, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_16.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_17, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_17.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_18, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_18.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_19, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_19.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_20, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_20.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_21, convVal(glblMsg.B.no(i).xxSvcCallDt_FR.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_21, convVal(glblMsg.B.no(i).xxSvcCallDt_TH.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_22, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_22.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_22, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_22.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_24, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_24.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_24, convVal(glblMsg.B.no(i).prcRuleCondThruTxt_24.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_25, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_25.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_26, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_26.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_26, convVal(glblMsg.B.no(i).prcRuleCondThruTxt_26.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_27, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_27.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_28, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_28.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_29, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_29.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_30, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_30.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_30, convVal(glblMsg.B.no(i).prcRuleCondThruTxt_30.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_31, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_31.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_32, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_32.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_33, convVal(glblMsg.B.no(i).prcDt_FR.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_33, convVal(glblMsg.B.no(i).prcDt_TH.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_34, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_34.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_35, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_35.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_36, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_36.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_37, convVal(glblMsg.B.no(i).xxRqstDt_FR.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_37, convVal(glblMsg.B.no(i).xxRqstDt_TH.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_38, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_38.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_39, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_39.getValue()));
//            // Add Start 2017/08/31 QC#20729-2
//            EZDSStringItem setValueItem = glblMsg.B.no(i).prcRuleCondFromTxt_40;
//            if (ZYPCommonFunc.hasValue(setValueItem)) {
//                NMXC105001PriceMasterUtil.getMoldelIdName(getGlobalCompanyCode(), setValueItem.getValue(), false, setValueItem);
//            }
//            // Add Start 2017/08/31 QC#20729-2
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_40, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_40.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_41, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_41.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_42, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_42.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_44, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_44.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_45, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_45.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_45, convVal(glblMsg.B.no(i).prcRuleCondThruTxt_45.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_46, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_46.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_48, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_48.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_49, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_49.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_53, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_53.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_54, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_54.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_55, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_55.getValue()));
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_56, convVal(glblMsg.B.no(i).prcRuleCondFromTxt_56.getValue()));
//
//            if (!submitTbl(tMsg, workNewFlg)) {
//                bizMsg.setMessageInfo(NMAM8020E);
//                return false;
//            }
//        }
//        return true;
//    }

    private boolean submitPrcAdjDtl(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {

        String mode = "";
        String prcRuleAttrbCd = "";

        for(int i = 0; i < bizMsg.C.getValidCount(); i++){
            if (PRC_RULE_ATTRB.FORMULA.equals(bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue()) || PRC_RULE_ATTRB.PERCENT.equals(bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue()) || PRC_RULE_ATTRB.VALUE.equals(bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue())) {
                prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
            }
        }

        for (int i : selectRows) {

            PRC_ADJ_DTLTMsg tMsg = new PRC_ADJ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.U.no(i).prcRuleDtlPk_B1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcAdjDtlPk, glblMsg.U.no(i).prcAdjDtlPk_B1);
                tMsg = (PRC_ADJ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.U.no(i).ezUpTime_B1.getValue(), glblMsg.U.no(i).ezUpTimeZone_B1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.U.no(i).delFlg_B1.getValue())) {
                    mode = NMAL7260Constant.MODE_DELETE;
                } else {
                    mode = NMAL7260Constant.MODE_MODIFY;
                }
            } else {
                if (ZYPCommonFunc.hasValue(glblMsg.U.no(i).prcAdjDtlPk_B1)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcAdjDtlPk, glblMsg.U.no(i).prcAdjDtlPk_B1);
                }else{
                    ZYPEZDItemValueSetter.setValue(tMsg.prcAdjDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_ADJ_DTL_SQ));
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_RULE_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(glblMsg.U.no(i).prcAdjDtlPk_B1, tMsg.prcAdjDtlPk);
                ZYPEZDItemValueSetter.setValue(glblMsg.U.no(i).prcRuleDtlPk_B1, tMsg.prcRuleDtlPk);
                if (!ZYPCommonFunc.hasValue(glblMsg.U.no(i).effFromDt_B1.getValue())) {
                    glblMsg.U.no(i).effFromDt_B1.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                }
                mode = NMAL7260Constant.MODE_NEW;
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk_H1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlChrgNm, bizMsg.prcRuleNm_H1);

            if(ZYPCommonFunc.hasValue(glblMsg.U.no(i).prcFmlaPk_B1)){
                ZYPEZDItemValueSetter.setValue(tMsg.prcFmlaPk, glblMsg.U.no(i).prcFmlaPk_B1);
            }
            if (ZYPCommonFunc.hasValue(glblMsg.U.no(i).prcRuleDtlRate_B1)) {
                BigDecimal rate = glblMsg.U.no(i).prcRuleDtlRate_B1.getValue().divide(NUM_100, DB_DIGIT, BigDecimal.ROUND_HALF_DOWN);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlRate, rate);
            } else {
                //for dummy row
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlRate, glblMsg.U.no(i).prcRuleDtlRate_B1);
            }
            if(ZYPCommonFunc.hasValue(glblMsg.U.no(i).prcRuleDtlTxt_B1)){
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlTxt, glblMsg.U.no(i).prcRuleDtlTxt_B1);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.U.no(i).effFromDt_B1);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.U.no(i).effThruDt_B1);

            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_04, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_04.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_05, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_05.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_06, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_06.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_07, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_07.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_08, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_08.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_09, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_09.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_10, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_10.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_11, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_11.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_12, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_12.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_13, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_13.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_14, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_14.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_15, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_15.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_15, convVal(glblMsg.U.no(i).prcRuleCondThruTxt_15.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_16, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_16.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_17, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_17.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_18, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_18.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_19, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_19.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_20, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_20.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_21, convVal(glblMsg.U.no(i).xxSvcCallDt_FR.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_21, convVal(glblMsg.U.no(i).xxSvcCallDt_TH.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_22, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_22.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_22, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_22.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_24, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_24.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_24, convVal(glblMsg.U.no(i).prcRuleCondThruTxt_24.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_25, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_25.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_26, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_26.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_26, convVal(glblMsg.U.no(i).prcRuleCondThruTxt_26.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_27, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_27.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_28, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_28.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_29, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_29.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_30, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_30.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_30, convVal(glblMsg.U.no(i).prcRuleCondThruTxt_30.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_31, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_31.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_32, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_32.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_33, convVal(glblMsg.U.no(i).prcDt_FR.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_33, convVal(glblMsg.U.no(i).prcDt_TH.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_34, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_34.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_35, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_35.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_36, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_36.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_37, convVal(glblMsg.U.no(i).xxRqstDt_FR.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_37, convVal(glblMsg.U.no(i).xxRqstDt_TH.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_38, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_38.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_39, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_39.getValue()));
            EZDSStringItem setValueItem = glblMsg.U.no(i).prcRuleCondFromTxt_40;
            if (ZYPCommonFunc.hasValue(setValueItem)) {
                NMXC105001PriceMasterUtil.getMoldelIdName(getGlobalCompanyCode(), setValueItem.getValue(), false, setValueItem);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_40, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_40.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_41, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_41.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_42, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_42.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_44, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_44.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_45, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_45.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_45, convVal(glblMsg.U.no(i).prcRuleCondThruTxt_45.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_46, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_46.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_48, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_48.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_49, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_49.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_53, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_53.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_54, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_54.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_55, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_55.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_56, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_56.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAttrbCd, prcRuleAttrbCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prntPrcAdjDtlPk, glblMsg.U.no(i).prntPrcAdjDtlPk_B1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_57, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_57.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_58, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_58.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondThruTxt_58, convVal(glblMsg.U.no(i).prcRuleCondThruTxt_58.getValue()));
            // 2018/04/19 QC#22569 add start
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_59, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_59.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_60, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_60.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondFromTxt_61, convVal(glblMsg.U.no(i).prcRuleCondFromTxt_61.getValue()));
            // 2018/04/19 QC#22569 add end

            if (!submitTbl(tMsg, mode)) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }
        }
        return true;
    }

    private boolean submitPrcAdjCond(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        for(int i = 0; i < bizMsg.C.getValidCount(); i++){
            boolean isExists = false;
            NMAL7260_CCMsg ccMsg = bizMsg.C.no(i);
            PRC_ADJ_CONDTMsg tMsg = new PRC_ADJ_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(ccMsg.prcAdjCondPk_C1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcAdjCondPk, ccMsg.prcAdjCondPk_C1);
                tMsg = (PRC_ADJ_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                isExists = true;
                ZYPEZDItemValueSetter.setValue(tMsg.prcAdjCondPk, ccMsg.prcAdjCondPk_C1);
            } else{
                ZYPEZDItemValueSetter.setValue(tMsg.prcAdjCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_ADJ_COND_SQ));
                ZYPEZDItemValueSetter.setValue(ccMsg.prcAdjCondPk_C1, tMsg.prcAdjCondPk);
                isExists = false;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk_H1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, ccMsg.prcRuleCondNum_C1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleAttrbCd, ccMsg.prcRuleAttrbCd_C1);
            
            ZYPEZDItemValueSetter.setValue(tMsg.inpReqFlg, getFlgVal(ccMsg.inpReqFlg_C1.getValue()));

            if (!submitTbl(tMsg, isExists)) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }
        }

        return true;
    }

//    private boolean submitSpecCondPrc(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {
//        boolean workNewFlg = false;
//        for (int i : selectRows) {
//            SPEC_COND_PRCTMsg tMsg = new SPEC_COND_PRCTMsg();
//            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).specCondPrcPk_B1)) {
//                ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, glblMsg.B.no(i).specCondPrcPk_B1);
//                tMsg = (SPEC_COND_PRCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
//                if (tMsg == null) {
//                    bizMsg.setMessageInfo(NZZM0003E);
//                    return false;
//                }
//                workNewFlg = true;
//            } else {
//                ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPEC_COND_PRC_SQ));
//                workNewFlg = false;
//            }
//
//            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, glblMsg.B.no(i).prcRuleDtlPk_B1);
//
//            if (!submitTbl(tMsg, workNewFlg)) {
//                bizMsg.setMessageInfo(NMAM8020E);
//                return false;
//            }
//        }
//        return true;
//    }

    private boolean submitSpecCondPrc(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {
        String mode = "";
        for (int i : selectRows) {
            SPEC_COND_PRCTMsg tMsg = new SPEC_COND_PRCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(glblMsg.U.no(i).specCondPrcPk_B1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, glblMsg.U.no(i).specCondPrcPk_B1);
                tMsg = (SPEC_COND_PRCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.U.no(i).delFlg_B1.getValue())) {
                    mode = NMAL7260Constant.MODE_DELETE;
                } else {
                    mode = NMAL7260Constant.MODE_MODIFY;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPEC_COND_PRC_SQ));
                mode = NMAL7260Constant.MODE_NEW;
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcRuleDtlPk, glblMsg.U.no(i).prcRuleDtlPk_B1);

            if (!submitTbl(tMsg, mode)) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }
        }
        return true;
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

    private boolean submitTbl(EZDTMsg inTMsg, String mode) {
        if (NMAL7260Constant.MODE_MODIFY.equals(mode)) {
            EZDTBLAccessor.update(inTMsg);
        } else if (NMAL7260Constant.MODE_NEW.equals(mode)) {
            EZDTBLAccessor.insert(inTMsg);
        } else if (NMAL7260Constant.MODE_DELETE.equals(mode)) {
            EZDTBLAccessor.logicalRemove(inTMsg);
        } else{
            return false;
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
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

    private String convVal(String s) {
        if(!ZYPCommonFunc.hasValue(s)){
            return NMAL7260Constant.ASTERISK;
        }
        return s;
    }
}
