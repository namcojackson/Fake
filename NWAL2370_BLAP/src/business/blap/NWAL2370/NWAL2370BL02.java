/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2370;

import static business.blap.NWAL2300.constant.NWAL2300Constant.NZZM0000E;
import static business.blap.NWAL2370.constant.NWAL2370Constant.NWAL2370_IMPT_MODE_CD;
import static business.blap.NWAL2370.constant.NWAL2370Constant.NWAL2370_SHELL_MODE_CD;
import static business.blap.NWAL2370.constant.NWAL2370Constant.NWZM0833E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/10   Fujitsu         N.Sugiura       Create          QC#10374
 * 2018/04/10   Fujitsu         W.Honda         Update          QC#10374
 *</pre>
 */

public class NWAL2370BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2370CMsg bizMsg = (NWAL2370CMsg) cMsg;

            if ("NWAL2370_INIT".equals(screenAplID)) {
                doProcess_NWAL2370_INIT(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2370_INIT(NWAL2370CMsg bizMsg) {

        if (!checkParam(bizMsg)) {
            return;
        }

        if (NWAL2370_SHELL_MODE_CD.equals(bizMsg.xxModeCd.getValue())) {
            searchFromShell(bizMsg);
        } else if (NWAL2370_IMPT_MODE_CD.equals(bizMsg.xxModeCd.getValue())) {
            searchFromImport(bizMsg);
        }

        // Function Currency Digit Number
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        glblTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ccyMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            ccyMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());
            ccyMsg = (CCYTMsg) EZDTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                bizMsg.aftDeclPntDigitNum.setValue(ccyMsg.aftDeclPntDigitNum.getValueInt());
            }
        }

    }

    /**
     * check parameter
     * @param bizMsg Business Msg
     */
    private boolean checkParam(NWAL2370CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxModeCd)) {

            bizMsg.setMessageInfo(NWZM0833E);
            return false;

        }

        if (NWAL2370_SHELL_MODE_CD.equals(bizMsg.xxModeCd.getValue())
                && NWAL2370_IMPT_MODE_CD.equals(bizMsg.xxModeCd.getValue())) {

            bizMsg.setMessageInfo(NWZM0833E);
            return false;

        }

        if (NWAL2370_SHELL_MODE_CD.equals(bizMsg.xxModeCd.getValue())
                && !ZYPCommonFunc.hasValue(bizMsg.dsContrDtlPk)) {

            bizMsg.setMessageInfo(NWZM0833E);
            return false;

        }

        if (NWAL2370_IMPT_MODE_CD.equals(bizMsg.xxModeCd.getValue())
                && !ZYPCommonFunc.hasValue(bizMsg.dsImptSvcConfigRefPk)) {

            bizMsg.setMessageInfo(NWZM0833E);
            return false;

        }

        return true;

    }

    /**
     * Search From Shell
     * @param bizMsg Business Msg
     */
    private void searchFromShell(NWAL2370CMsg bizMsg) {
        // get Header Information.
        S21SsmEZDResult ssmResult = NWAL2370Query.getInstance().getHdrInfo(bizMsg, getGlobalCompanyCode());

        if (ssmResult.isCodeNotFound() || ssmResult.getQueryResultCount() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
        setHdrItem(bizMsg, result);
        BigDecimal dsContrPk = (BigDecimal) result.get("DS_CONTR_PK");
        BigDecimal dsCpoConfigPk = (BigDecimal) result.get("DS_CPO_CONFIG_PK");
        BigDecimal basePrcDealAmt = (BigDecimal) result.get("BASE_PRC_DEAL_AMT");

        ssmResult = NWAL2370Query.getInstance().getRntlEqInfo(dsContrPk, dsCpoConfigPk, getGlobalCompanyCode());

        if (ssmResult.isCodeNotFound() || ssmResult.getQueryResultCount() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        setRntlEqItem(bizMsg, ssmResult, basePrcDealAmt);

        ssmResult = NWAL2370Query.getInstance().getAccChrgInfo(dsContrPk, dsCpoConfigPk, getGlobalCompanyCode());

        setAccChrgItem(bizMsg, ssmResult);

        ssmResult = NWAL2370Query.getInstance().getAddlChrgInfo(dsContrPk, dsCpoConfigPk, getGlobalCompanyCode());

        setAddlChrgItem(bizMsg, ssmResult);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BigDecimal rntlPmtAmt = new BigDecimal(0);
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_EQ)) {
                rntlPmtAmt = rntlPmtAmt.add(bizMsg.A.no(i).xxTotAmt_EQ.getValue());
            }
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_SV)) {
                rntlPmtAmt = rntlPmtAmt.add(bizMsg.A.no(i).xxTotAmt_SV.getValue());
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_PY, rntlPmtAmt);
        }
    }

    /**
     * Search From Import
     * @param bizMsg Business Msg
     */
    private void searchFromImport(NWAL2370CMsg bizMsg) {

        // get Header Information.
        S21SsmEZDResult ssmResult = NWAL2370Query.getInstance().getHdrInfoFromImport(bizMsg, getGlobalCompanyCode());

        if (ssmResult.isCodeNotFound() || ssmResult.getQueryResultCount() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
        setHdrItem(bizMsg, result);
        BigDecimal dsImptSvcDtlPk = (BigDecimal) result.get("DS_IMPT_SVC_DTL_PK");
        String dsOrdPosnNum = (String) result.get("DS_ORD_POSN_NUM");
        BigDecimal basePrcDealAmt = (BigDecimal) result.get("BASE_PRC_DEAL_AMT");
        BigDecimal dsImptOrdDtlPk = (BigDecimal) result.get("DS_IMPT_ORD_DTL_PK");

        ssmResult = NWAL2370Query.getInstance().getRntlSvcInfoFromImpt(dsImptSvcDtlPk, dsOrdPosnNum, getGlobalCompanyCode());

        setRntlSvcItemFromImpt(bizMsg, ssmResult, basePrcDealAmt, dsImptOrdDtlPk);

        ssmResult = NWAL2370Query.getInstance().getAddlChrgInfoFromImpt(dsImptSvcDtlPk, dsOrdPosnNum, getGlobalCompanyCode());

        setAddlChrgItemFromImport(bizMsg, ssmResult);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BigDecimal rntlPmtAmt = new BigDecimal(0);
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_EQ)) {
                rntlPmtAmt = rntlPmtAmt.add(bizMsg.A.no(i).xxTotAmt_EQ.getValue());
            }
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_SV)) {
                rntlPmtAmt = rntlPmtAmt.add(bizMsg.A.no(i).xxTotAmt_SV.getValue());
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_PY, rntlPmtAmt);
        }
    }

    /**
     * set Header Item
     * @param bizMsg Business Msg
     */
    private void setHdrItem(NWAL2370CMsg bizMsg, Map<String, Object> result) {

        ZYPEZDItemValueSetter.setValue(bizMsg.shellLineNum,  (BigDecimal) result.get("SHELL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.fromPerMthNum, (BigDecimal) result.get("FROM_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.toPerMthNum,   (BigDecimal) result.get("TO_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.termMthAot,    (BigDecimal) result.get("TERM_MTH_AOT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum,  (String) result.get("DS_ORD_POSN_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.t_MdlNm,       (String) result.get("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd, (String) result.get("DS_CONTR_CATG_CD"));

    }

    /**
     * set Header Item
     * @param bizMsg Business Msg
     */
    private void setRntlEqItem(NWAL2370CMsg bizMsg, S21SsmEZDResult ssmResult, BigDecimal basePrcDealAmt) {
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int i = bizMsg.A.getValidCount();
        for (Map<String, Object> result: resultList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dsContrDtlPk_A1,         (BigDecimal) result.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxLineNum_A1,            (String) result.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseCd_A1,               (String) result.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseDescShortTxt_A1,     (String) result.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxFreqCycleCnt_A1,       (BigDecimal) result.get("TERM_MTH_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_EQ,             (BigDecimal) result.get("RNTL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).prcListEquipConfigNm_A1, (String) result.get("PRC_LIST_EQUIP_CONFIG_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).bllgCycleDescTxt_A1,     (String) result.get("BLLG_CYCLE_DESC_TXT"));

            if (bizMsg.dsContrDtlPk.getValue().compareTo(bizMsg.A.no(i).dsContrDtlPk_A1.getValue()) == 0) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_SV)) {
                    basePrcDealAmt = basePrcDealAmt.add(bizMsg.A.no(i).xxTotAmt_SV.getValue());
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_SV, basePrcDealAmt);
            }

            i++;
        }

        bizMsg.A.setValidCount(i);
    }

    /**
     * set Header Item
     * @param bizMsg Business Msg
     */
    private void setRntlSvcItemFromImpt(NWAL2370CMsg bizMsg, S21SsmEZDResult ssmResult, BigDecimal basePrcDealAmt, BigDecimal dsImptOrdDtlPk) {
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int msgIdx = bizMsg.A.getValidCount();
        for (Map<String, Object> result: resultList) {
            boolean isMachDtlFlg = false;
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxLineNum_A1)
                        && bizMsg.A.no(i).xxLineNum_A1.getValue().equals((String) result.get("DPLY_LINE_NUM"))) {

                    BigDecimal rntlEq = new BigDecimal(0);
                    if (SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE.equals((String) result.get("SVC_PRC_CATG_CD"))) {
                        rntlEq = bizMsg.A.no(msgIdx).xxTotAmt_EQ.getValue();
                    } else {
                        rntlEq = bizMsg.A.no(msgIdx).xxTotAmt_SV.getValue();
                    }

                    BigDecimal addRntlEq = (BigDecimal) result.get("ADDL_BASE_PRC_DEAL_AMT");
                    if (ZYPCommonFunc.hasValue(addRntlEq)) {
                        if (rntlEq == null) {
                            rntlEq = addRntlEq;
                        } else {
                            rntlEq = addRntlEq.add(rntlEq);
                        }
                    }

                    if (SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE.equals((String) result.get("SVC_PRC_CATG_CD"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_EQ, rntlEq);
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_SV, rntlEq);
                    }

                    isMachDtlFlg = true;
                    break;
                }
            }

            if (!isMachDtlFlg) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).dsImptOrdDtlPk_A1,       (BigDecimal) result.get("DS_IMPT_ORD_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).dsContrDtlPk_A1,         (BigDecimal) result.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).xxLineNum_A1,            (String) result.get("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).mdseCd_A1,               (String) result.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).mdseDescShortTxt_A1,     (String) result.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).xxFreqCycleCnt_A1,       (BigDecimal) result.get("TERM_MTH_NUM"));
                if (SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE.equals((String) result.get("SVC_PRC_CATG_CD"))) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).xxTotAmt_EQ,         (BigDecimal) result.get("ADDL_BASE_PRC_DEAL_AMT"));
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).xxTotAmt_SV,         (BigDecimal) result.get("ADDL_BASE_PRC_DEAL_AMT"));
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).prcListEquipConfigNm_A1, (String) result.get("PRC_LIST_EQUIP_CONFIG_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).bllgCycleDescTxt_A1,     (String) result.get("BLLG_CYCLE_DESC_TXT"));

                if (dsImptOrdDtlPk.compareTo(bizMsg.A.no(msgIdx).dsImptOrdDtlPk_A1.getValue()) == 0) {
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(msgIdx).xxTotAmt_SV)) {
                        basePrcDealAmt = basePrcDealAmt.add(bizMsg.A.no(msgIdx).xxTotAmt_SV.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(msgIdx).xxTotAmt_SV, basePrcDealAmt);
                }

                msgIdx++;
                bizMsg.A.setValidCount(msgIdx);
            }
        }

        bizMsg.A.setValidCount(msgIdx);
    }

    /**
     * set Header Item
     * @param bizMsg Business Msg
     */
    private void setAccChrgItem(NWAL2370CMsg bizMsg, S21SsmEZDResult ssmResult) {
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (Map<String, Object> result: resultList) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (bizMsg.A.no(i).dsContrDtlPk_A1.getValue().compareTo((BigDecimal) result.get("DS_CONTR_DTL_PK")) == 0) {
                    BigDecimal svcAmt = (BigDecimal) result.get("BASE_PRC_DEAL_AMT");
                    if (ZYPCommonFunc.hasValue(svcAmt) && ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_SV)) {
                        svcAmt = svcAmt.add(bizMsg.A.no(i).xxTotAmt_SV.getValue());
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_SV)) {
                        svcAmt = bizMsg.A.no(i).xxTotAmt_SV.getValue();
                    }
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_SV, svcAmt);
                }
            }
        }
    }

    /**
     * set Header Item
     * @param bizMsg Business Msg
     */
    private void setAddlChrgItem(NWAL2370CMsg bizMsg, S21SsmEZDResult ssmResult) {
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (Map<String, Object> result: resultList) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (bizMsg.A.no(i).dsContrDtlPk_A1.getValue().compareTo((BigDecimal) result.get("DS_CONTR_DTL_PK")) == 0) {
                    BigDecimal addlChrgAmt = (BigDecimal) result.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT");
                    if (ZYPCommonFunc.hasValue(addlChrgAmt) && ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_AC)) {
                        addlChrgAmt = addlChrgAmt.add(bizMsg.A.no(i).xxTotAmt_AC.getValue());
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_AC)) {
                        addlChrgAmt = bizMsg.A.no(i).xxTotAmt_AC.getValue();
                    }
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_AC, addlChrgAmt);
                }
            }
        }
    }

    /**
     * set Header Item
     * @param bizMsg Business Msg
     */
    private void setAddlChrgItemFromImport(NWAL2370CMsg bizMsg, S21SsmEZDResult ssmResult) {
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (Map<String, Object> result: resultList) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (bizMsg.A.no(i).dsImptOrdDtlPk_A1.getValue().compareTo((BigDecimal) result.get("DS_IMPT_ORD_DTL_PK")) == 0) {
                    BigDecimal addlChrgAmt = (BigDecimal) result.get("ADDL_CHRG_PRC_DEAL_AMT");
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxTotAmt_AC)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_AC,
                                bizMsg.A.no(i).xxTotAmt_AC.getValue().add(addlChrgAmt));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_AC, addlChrgAmt);
                    }
                }
            }
        }
    }

}
