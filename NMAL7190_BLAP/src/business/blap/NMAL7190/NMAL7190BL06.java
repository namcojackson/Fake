/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7190;

import static business.blap.NMAL7190.constant.NMAL7190Constant.CHK_ATTRB_NM_LIST;
import static business.blap.NMAL7190.constant.NMAL7190Constant.COMBI_START_END;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DTL_EFF_FROM_DT;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DTL_EFF_FROM_DT_A1;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DTL_EFF_THRU_DT_A1;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DATE_FROM;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DATE_TO;
import static business.blap.NMAL7190.constant.NMAL7190Constant.GRP_ATTRB_NM_LIST;
import static business.blap.NMAL7190.constant.NMAL7190Constant.LOGIC_MODE_SUBMIT;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0009E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0042E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0043E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0070E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0072E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0163E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0176E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0177E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8296E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NZZM0003E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.OPEATOR_BETWEEN;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRC_GRP_FROM_TXT;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRC_GRP_OP_CD;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRC_GRP_TRGT_ATTRB_CD;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRC_GRP_TRGT_TP_CD;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRICE_GROUP_DETAIL;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRICE_GROUP_DETAIL_TABLE_NAME;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRICE_GROUP_TABLE_NAME;
import static business.blap.NMAL7190.constant.NMAL7190Constant.TARGET_BLANK;
import static business.blap.NMAL7190.constant.NMAL7190Constant.ZZM9000E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.ZZM9037E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8113E;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7190.common.NMAL7190CommonLogic;
import business.db.PRC_GRPTMsg;
import business.db.PRC_GRP_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7190BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2018/08/01   Fujitsu         M.Ishii         Update          QC#26297
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7190CMsg bizMsg = (NMAL7190CMsg) cMsg;
            NMAL7190SMsg glblMsg = (NMAL7190SMsg) sMsg;

            if ("NMAL7190Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_CMN_Submit(bizMsg, glblMsg);

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
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_CMN_Submit(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Detail Input Value Check
        if (!checkDetailValue(bizMsg, glblMsg)) {
            return;
        }

        // Price Group Name is Unique Check
        if (!checkPrcGrpNameUnique(bizMsg)) {
            return;
        }

        // Operator and target thru text relation check
        if (!checkDetailRelation(bizMsg, glblMsg)) {
            return;
        }

        // Detail entered value exist check
        if (!checkDetailExist(bizMsg, glblMsg)) {
            return;
        }

        // Detail value Duplicate check
        if (!checkDetailDup(bizMsg, glblMsg)) {
            return;
        }

        // update & insert
        if (ZYPCommonFunc.hasValue(bizMsg.prcGrpPk_BK)) {
            if (!updatePrcGrp(bizMsg, glblMsg)) {
                return;
            }
        } else {
            if (!insertPrcGrp(bizMsg, glblMsg)) {
                return;
            }
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpDtlPk_A1)) {
                if (!updatePrcGrpDtl(bizMsg, glblMsg, i)) {
                    bizMsg.xxPageShowFromNum.setValue(i);
                    NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
                    return;
                }
            } else {
                if (!insertPrcGrpDtl(bizMsg, glblMsg, i)) {
                    bizMsg.xxPageShowFromNum.setValue(i);
                    NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
                    return;
                }
            }
        }
    }

    /**
     * checkPackNameUnique
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkPrcGrpNameUnique(NMAL7190CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7190Query.getInstance().getPriceGroupName(bizMsg);
        if (ssmResult.isCodeNormal()) {
            if (!ZYPCommonFunc.hasValue(bizMsg.prcGrpPk_BK)) {
                bizMsg.setMessageInfo(NMAM8296E, new String[] {bizMsg.prcGrpNm.getValue(), "PRC_GRP"});
                return false;
            } else {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (int i = 0; i < resultList.size(); i++) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                    if (!(bizMsg.prcGrpPk_BK.getValue().compareTo((BigDecimal) resultMap.get("PRC_GRP_PK")) == 0)) {
                        bizMsg.setMessageInfo(NMAM8296E, new String[] {bizMsg.prcGrpNm.getValue().toString(), "PRC_GRP"});
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * checkDetailrelation
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkDetailRelation(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        int errIdx = -1;
        boolean isErr = false;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (PRC_GRP_OP.BETWEEN.equals(glblMsg.A.no(i).prcGrpOpCd_A1.getValue())
                    && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpThruTxt_A1)) {
                glblMsg.A.no(i).prcGrpOpCd_A1.setErrorInfo(1, NMAM0070E, new String[] {OPEATOR_BETWEEN, TARGET_BLANK});
                glblMsg.A.no(i).prcGrpThruTxt_A1.setErrorInfo(1, NMAM0070E, new String[] {OPEATOR_BETWEEN, TARGET_BLANK});
                bizMsg.setMessageInfo(NMAM0070E, new String[] {OPEATOR_BETWEEN, TARGET_BLANK});
                if (errIdx == -1) {
                    errIdx = i;
                }
                isErr = true;
            }
        }

        if (isErr) {
            bizMsg.xxPageShowFromNum.setValue(errIdx);
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            return false;
        }
        return true;
    }

    /**
     * checkDetailExist
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkDetailExist(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        boolean isErr = false;
        int errIdx = -1;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (PRC_GRP_OP.LIKE.equals(glblMsg.A.no(i).prcGrpOpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpFromTxt_CD, glblMsg.A.no(i).prcGrpFromTxt_A1);
                continue;
            } else if (PRC_GRP_OP.BETWEEN.equals(glblMsg.A.no(i).prcGrpOpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpFromTxt_CD, glblMsg.A.no(i).prcGrpFromTxt_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpThruTxt_CD, glblMsg.A.no(i).prcGrpThruTxt_A1);
                continue;
            }

            if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (!NMAL7190CommonLogic.convertPrcGrpAttrbForItemNumber(glblMsg.A.no(i).prcGrpFromTxt_A1
                        , glblMsg.A.no(i).prcGrpFromTxt_CD
                        , glblMsg.A.no(i).prcGrpConvFlg_A1
                        , glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue()
                        , getGlobalCompanyCode()
                        , LOGIC_MODE_SUBMIT)) {
                    bizMsg.setMessageInfo(NMAM0009E, new String[] {PRICE_GROUP_DETAIL});
                    isErr = true;
                    if (errIdx == -1) {
                        errIdx = i;
                    }
                    continue;
                }
            } else if (!NMAL7190CommonLogic.convertPrcGrpAttrb(glblMsg.A.no(i).prcGrpFromTxt_A1
                    , glblMsg.A.no(i).prcGrpFromTxt_CD
                    , glblMsg.A.no(i).prcGrpConvFlg_A1
                    , glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue()
                    , getGlobalCompanyCode()
                    , LOGIC_MODE_SUBMIT)) {
                glblMsg.A.no(i).prcGrpFromTxt_A1.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.A.no(i).prcGrpFromTxt_A1.getValue(), NMAL7190CommonLogic.getMsterName(glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue())});
                bizMsg.setMessageInfo(NMAM0009E, new String[] {PRICE_GROUP_DETAIL});
                isErr = true;
                if (errIdx == -1) {
                    errIdx = i;
                }
                continue;
            }
        }
        if (isErr) {
            bizMsg.xxPageShowFromNum.setValue(errIdx);
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        }
        return !isErr;
    }

    /**
     * checkDetailDup
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkDetailDup(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        Integer[] errIdxList = NMAL7190CommonLogic.checkDupAttrb(glblMsg.A, CHK_ATTRB_NM_LIST);
        if (errIdxList.length > 0) {
            for (int errIdx : errIdxList) {
                glblMsg.A.no(errIdx).effFromDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                glblMsg.A.no(errIdx).effThruDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
            }
            bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END});
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            return false;
        }

        Integer[] errIdxList2 = NMAL7190CommonLogic.checkDupTermByGrp(glblMsg.A, DTL_EFF_FROM_DT_A1, DTL_EFF_THRU_DT_A1, GRP_ATTRB_NM_LIST);
        if (errIdxList2.length > 0) {
            for (int errIdx : errIdxList2) {
                // 2018/08/01 Mod Start QC#26297
//                glblMsg.A.no(errIdx).effFromDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
//                glblMsg.A.no(errIdx).effThruDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                glblMsg.A.no(errIdx).effFromDt_A1.setErrorInfo(1, NMAM8113E);
                glblMsg.A.no(errIdx).effThruDt_A1.setErrorInfo(1, NMAM8113E);
                // 2018/08/01 Mod End QC#26297
            }
            // 2018/08/01 Mod Start QC#26297
//            bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END});
            bizMsg.setMessageInfo(NMAM8113E);
            // 2018/08/01 Mod End QC#26297
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            return false;
        }
        return true;
    }

    /**
     * update Price Group
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean updatePrcGrp(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        if (!checkChangedHeaderValueExist(glblMsg)) {
            return true;
        }

        PRC_GRPTMsg inTMsg = new PRC_GRPTMsg();
        PRC_GRPTMsg outTMsg = null;
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpPk, bizMsg.prcGrpPk_BK.getValue());
        outTMsg = (PRC_GRPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
        if (outTMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue()
                , outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpNm, bizMsg.prcGrpNm.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpDescTxt, bizMsg.prcGrpDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpTpCd, bizMsg.prcGrpTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpTrxTpCd, bizMsg.prcGrpTrxTpCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.actvFlg)) {
            ZYPEZDItemValueSetter.setValue(outTMsg.actvFlg, bizMsg.actvFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(outTMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.effFromDt, bizMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.effThruDt, bizMsg.effThruDt.getValue());

        EZDTBLAccessor.update(outTMsg);
        String returnCode = outTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0177E, new String[]{PRICE_GROUP_TABLE_NAME});
            return false;
        }

        return true;
    }

    /**
     * insert Price Group
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean insertPrcGrp(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        PRC_GRPTMsg inTMsg = new PRC_GRPTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal prcGrpPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_GRP_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpPk, prcGrpPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpNm, bizMsg.prcGrpNm.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpDescTxt, glblMsg.prcGrpDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpTpCd, bizMsg.prcGrpTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpTrxTpCd, bizMsg.prcGrpTrxTpCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.actvFlg)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.actvFlg, bizMsg.actvFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, bizMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, bizMsg.effThruDt.getValue());

        EZDTBLAccessor.insert(inTMsg);
        String returnCode = inTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0176E, new String[]{PRICE_GROUP_TABLE_NAME});
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpPk, prcGrpPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpPk_BK, prcGrpPk);
        return true;
    }

    /**
     * update Price Group Detail
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index Global Msg index
     */
    private Boolean updatePrcGrpDtl(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg, int index) {
        if (!checkChangedDetailValueExist(glblMsg.A.no(index), glblMsg)) {
            return true;
        }

        PRC_GRP_DTLTMsg inTMsg = new PRC_GRP_DTLTMsg();
        PRC_GRP_DTLTMsg outTMsg = null;
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpDtlPk, glblMsg.A.no(index).prcGrpDtlPk_A1);
        outTMsg = (PRC_GRP_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
        if (outTMsg == null) {
            glblMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NZZM0003E);
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblMsg.A.no(index).ezUpTime_A1.getValue(), glblMsg.A.no(index).ezUpTimeZone_A1.getValue()
                , outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
            // anyone update
            glblMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NZZM0003E);
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpTrgtTpCd, glblMsg.A.no(index).prcGrpTrgtTpCd_A1);
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpTrgtAttrbCd, glblMsg.A.no(index).prcGrpTrgtAttrbCd_A1);
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpOpCd, glblMsg.A.no(index).prcGrpOpCd_A1);
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpFromTxt, glblMsg.A.no(index).prcGrpFromTxt_CD);
        if (PRC_GRP_OP.BETWEEN.equals(glblMsg.A.no(index).prcGrpOpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpThruTxt, glblMsg.A.no(index).prcGrpThruTxt_CD);
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.prcGrpPrcdNum, glblMsg.A.no(index).prcGrpPrcdNum_A1);
        ZYPEZDItemValueSetter.setValue(outTMsg.effFromDt, glblMsg.A.no(index).effFromDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.effThruDt, glblMsg.A.no(index).effThruDt_A1.getValue());

        EZDTBLAccessor.update(outTMsg);
        String returnCode = outTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            glblMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAM0177E, new String[]{PRICE_GROUP_DETAIL_TABLE_NAME});
            bizMsg.setMessageInfo(NMAM0177E, new String[]{PRICE_GROUP_DETAIL_TABLE_NAME});
            return false;
        }

        return true;
    }

    /**
     * insert Price Group Detail
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index Global Msg index
     */
    private Boolean insertPrcGrpDtl(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg, int index) {
        PRC_GRP_DTLTMsg inTMsg = new PRC_GRP_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal prcGrpDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_GRP_DTL_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpDtlPk, prcGrpDtlPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpPk, bizMsg.prcGrpPk_BK);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpTrgtTpCd, glblMsg.A.no(index).prcGrpTrgtTpCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpTrgtAttrbCd, glblMsg.A.no(index).prcGrpTrgtAttrbCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpOpCd, glblMsg.A.no(index).prcGrpOpCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpFromTxt, glblMsg.A.no(index).prcGrpFromTxt_CD);
        if (PRC_GRP_OP.BETWEEN.equals(glblMsg.A.no(index).prcGrpOpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpThruTxt, glblMsg.A.no(index).prcGrpThruTxt_CD);
        }
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpPrcdNum, glblMsg.A.no(index).prcGrpPrcdNum_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, glblMsg.A.no(index).effFromDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, glblMsg.A.no(index).effThruDt_A1.getValue());

        EZDTBLAccessor.insert(inTMsg);
        String returnCode = inTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            glblMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAM0176E, new String[]{PRICE_GROUP_DETAIL_TABLE_NAME});
            bizMsg.setMessageInfo(NMAM0176E, new String[]{PRICE_GROUP_DETAIL_TABLE_NAME});
            return false;
        }

        return true;
    }

    /**
     * Changed Value Exist Check
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkChangedHeaderValueExist(NMAL7190SMsg glblMsg) {
        if (!glblMsg.prcGrpNm.getValue().equals(glblMsg.Y.no(0).prcGrpNm.getValue())
                || !glblMsg.prcGrpDescTxt.getValue().equals(glblMsg.Y.no(0).prcGrpDescTxt.getValue())
                || !glblMsg.prcGrpTpCd.getValue().equals(glblMsg.Y.no(0).prcGrpTpCd.getValue())
                || !glblMsg.prcGrpTrxTpCd.getValue().equals(glblMsg.Y.no(0).prcGrpTrxTpCd.getValue())
                || !glblMsg.effFromDt.getValue().equals(glblMsg.Y.no(0).effFromDt.getValue())
                || !glblMsg.effThruDt.getValue().equals(glblMsg.Y.no(0).effThruDt.getValue())
                || !nvlFlg(glblMsg.actvFlg.getValue()).equals(glblMsg.Y.no(0).actvFlg.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * Changed Value Exist Check
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkChangedDetailValueExist(NMAL7190_ASMsg glblMsgALine, NMAL7190SMsg glblMsg) {
        for (int i = 0; i < glblMsg.Z.getValidCount(); i++) {
            if (glblMsgALine.prcGrpDtlPk_A1.getValue().compareTo(glblMsg.Z.no(i).prcGrpDtlPk_A1.getValue()) != 0) {
                continue;
            }

            if (!glblMsgALine.prcGrpTrgtTpCd_A1.getValue().equals(glblMsg.Z.no(i).prcGrpTrgtTpCd_A1.getValue()) //
                    || !glblMsgALine.prcGrpTrgtAttrbCd_A1.getValue().equals(glblMsg.Z.no(i).prcGrpTrgtAttrbCd_A1.getValue()) //
                    || !glblMsgALine.prcGrpOpCd_A1.getValue().equals(glblMsg.Z.no(i).prcGrpOpCd_A1.getValue()) //
                    || !glblMsgALine.prcGrpFromTxt_A1.getValue().equals(glblMsg.Z.no(i).prcGrpFromTxt_A1.getValue()) //
                    || !glblMsgALine.prcGrpThruTxt_A1.getValue().equals(glblMsg.Z.no(i).prcGrpThruTxt_A1.getValue()) //
                    || !NMAL7190CommonLogic.isSameValue(glblMsgALine.prcGrpPrcdNum_A1.getValue(), glblMsg.Z.no(i).prcGrpPrcdNum_A1.getValue()) //
                    || !glblMsgALine.effFromDt_A1.getValue().equals(glblMsg.Z.no(i).effFromDt_A1.getValue()) //
                    || !glblMsgALine.effThruDt_A1.getValue().equals(glblMsg.Z.no(i).effThruDt_A1.getValue())) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * checkMandantoryHeaderItem
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkDetailValue(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        boolean isErr = false;
        int errFirstIdx = -1;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpTrgtTpCd_A1)) {
                glblMsg.A.no(i).prcGrpTrgtTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {PRC_GRP_TRGT_TP_CD});
                if (errFirstIdx == -1) {
                    errFirstIdx = i;
                }
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1)) {
                glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.setErrorInfo(1, ZZM9000E, new String[] {PRC_GRP_TRGT_ATTRB_CD});
                if (errFirstIdx == -1) {
                    errFirstIdx = i;
                }
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpOpCd_A1)) {
                glblMsg.A.no(i).prcGrpOpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {PRC_GRP_OP_CD});
                if (errFirstIdx == -1) {
                    errFirstIdx = i;
                }
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpFromTxt_A1)) {
                glblMsg.A.no(i).prcGrpFromTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {PRC_GRP_FROM_TXT});
                if (errFirstIdx == -1) {
                    errFirstIdx = i;
                }
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_A1)) {
                glblMsg.A.no(i).effFromDt_A1.setErrorInfo(1, ZZM9000E, new String[] {DTL_EFF_FROM_DT});
                if (errFirstIdx == -1) {
                    errFirstIdx = i;
                }
                isErr = true;
            }

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_A1)
                    && ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_A1)
                    && ZYPDateUtil.compare(glblMsg.A.no(i).effFromDt_A1.getValue(), glblMsg.A.no(i).effThruDt_A1.getValue()) > 0) {
                // 2023/04/20 QC#61200 Mod Start
//                glblMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0043E, new String[]{DTL_EFF_THRU_DT_A1, DTL_EFF_FROM_DT_A1});
//                glblMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0042E, new String[]{DTL_EFF_FROM_DT_A1, DTL_EFF_THRU_DT_A1});
                glblMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0043E, new String[]{DATE_TO, DATE_FROM});
                glblMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0042E, new String[]{DATE_FROM, DATE_TO});
                // 2023/04/20 QC#61200 Mod End
                if (errFirstIdx == -1) {
                    errFirstIdx = i;
                }
                isErr = true;
            }
        }

        if (isErr && errFirstIdx != -1) {
            bizMsg.setMessageInfo(ZZM9037E);
            bizMsg.xxPageShowFromNum.setValue(errFirstIdx + 1);
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * checkMandantoryHeaderItem
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private String nvlFlg(String checkBoxVal) {

        if (!ZYPCommonFunc.hasValue(checkBoxVal)) {
            return ZYPConstant.FLG_OFF_N;
        }

        return checkBoxVal;
    }

}
