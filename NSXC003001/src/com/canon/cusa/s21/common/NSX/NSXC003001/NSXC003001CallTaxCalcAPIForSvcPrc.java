/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import business.db.DS_INV_TPTMsg;
import business.db.DS_TAX_GRP_EXEMTMsg;
import business.db.FSRTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINETMsg;
import business.parts.NWZC036101PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/30/2015   Hitachi         A.Kohinata      Create          N/A
 * 04/11/2016   Hitachi         T.Iwamoto       Update          QC#4488
 * 2017/06/26   Hitachi         K.Kojima        Update          QC#19008
 *</pre>
 */
public class NSXC003001CallTaxCalcAPIForSvcPrc {

    /** Const Key NSZC0060_DS_INV_TYPE_INVOICE */
    private static final String CONST_KEY_NSZC0060_DS_INV_TYPE_INVOICE = "NSZC0060_DS_INV_TYPE_INVOICE";

    /** Const Key DEFAULT_TAX_TRX_TP */
    private static final String DEFAULT_TAX_TRX_TP = "DEFAULT_TAX_TRX_TP";

    /** ssm batch client */
    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NSXC003001CallTaxCalcAPIForSvcPrc.class);

    /**
     * Call Tax Calc API
     * @param apiBean CallTaxCalcAPIBean
     * @param onBatchType ONBATCH_TYPE
     * @return NWZC036101PMsg
     */
    public static NWZC036101PMsg callTaxCalcApi(CallTaxCalcAPIBean apiBean, final ONBATCH_TYPE onBatchType) {
        NWZC036101 taxApi = new NWZC036101();
        NWZC036101PMsg taxApiPmsg = setTaxCalcApiParams(apiBean);
        taxApi.execute(taxApiPmsg, onBatchType);
        return taxApiPmsg;
    }

    /**
     * Set Tax Calc API params
     * @param apiBean CallTaxCalcAPIBean
     * @return NWZC036101PMsg
     */
    private static NWZC036101PMsg setTaxCalcApiParams(CallTaxCalcAPIBean apiBean) {
        NWZC036101PMsg taxApiPMsg = new NWZC036101PMsg();
        FSRTMsg fsrTmsg = apiBean.getFsrTmsg();
        SVC_INVTMsg svcInvTmsg = apiBean.getSvcInvTmsg();
        SVC_INV_LINETMsg svcInvLineTmsg = apiBean.getSvcInvLineTmsg();
        String glblCmpyCd = svcInvTmsg.glblCmpyCd.getValue();

        String billToCustLocCd = null;
        String shipToCustLocCd = null;
        if (ZYPConstant.FLG_ON_Y.equals(fsrTmsg.billToCustUpdFlg.getValue())) {
            billToCustLocCd = fsrTmsg.billToUpdCustCd.getValue();
            // START 2017/06/26 K.Kojima [QC#19008,DEL]
            // shipToCustLocCd = fsrTmsg.shipToUpdCustCd.getValue();
            // END 2017/06/26 K.Kojima [QC#19008,DEL]
        } else {
            billToCustLocCd = fsrTmsg.billToCustCd.getValue();
            // START 2017/06/26 K.Kojima [QC#19256,DEL]
            // shipToCustLocCd = fsrTmsg.shipToCustCd.getValue();
            // END 2017/06/26 K.Kojima [QC#19256,DEL]
        }
        // START 2017/06/26 K.Kojima [QC#19008,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(fsrTmsg.shipToCustUpdFlg.getValue())) {
            shipToCustLocCd = fsrTmsg.shipToUpdCustCd.getValue();
        } else {
            shipToCustLocCd = fsrTmsg.shipToCustCd.getValue();
        }
        // END 2017/06/26 K.Kojima [QC#19008,ADD]

        // Get Bill To Information
        String billToTaxGrpExemCd = null;
        String dsTaxGrpExemReslFlg = null;
        Map<String, String> billToCustMap = getBillToInfo(glblCmpyCd, billToCustLocCd);
        if (billToCustMap != null) {
            billToTaxGrpExemCd = (String) billToCustMap.get("DS_TAX_GRP_EXEM_CD");
            dsTaxGrpExemReslFlg = getDsTaxGrpExem(glblCmpyCd, billToTaxGrpExemCd);
        }

        // Get Ship To Information
        String shipToGeoCd = null;
        String shipToFirstLineAddr = null;
        String shipToScdLineAddr = null;
        String shipToCtyAddr = null;
        String shipToStCd = null;
        String shipToPostCd = null;
        String shipToCtryCd = null;
        String shipToTaxExemCd = null;
        String shipToInsdCtyLimitFlg = null;
        String shipTotaxAreaId = null;
        String shipToCntyNm = null;
        Map<String, String> shipToCustMap = getShipToInfo(glblCmpyCd, shipToCustLocCd);
        if (shipToCustMap != null) {
            shipToGeoCd = (String) shipToCustMap.get("GEO_CD");
            shipToFirstLineAddr = (String) shipToCustMap.get("FIRST_LINE_ADDR");
            shipToScdLineAddr = (String) shipToCustMap.get("SCD_LINE_ADDR");
            shipToCtyAddr = (String) shipToCustMap.get("CTY_ADDR");
            shipToStCd = (String) shipToCustMap.get("ST_CD");
            shipToPostCd = (String) shipToCustMap.get("POST_CD");
            shipToCtryCd = (String) shipToCustMap.get("CTRY_CD");
            shipToTaxExemCd = (String) shipToCustMap.get("DS_TAX_GRP_EXEM_CD");
            shipToInsdCtyLimitFlg = (String) shipToCustMap.get("DS_INSD_CTY_LIMIT_FLG");
            shipTotaxAreaId = (String) shipToCustMap.get("TAX_AREA_ID");
            shipToCntyNm = (String) shipToCustMap.get("CNTY_NM");
        }

        // Get Sales Rep Information
        String slsRepGeoCd = null;
        String slsRepFirstLineAddr = null;
        String slsRepScdLineAddr = null;
        String slsRepCtyAddr = null;
        String slsRepStCd = null;
        String slsRepPostCd = null;
        String slsRepCtryCd = null;
        String slsRepInsdCtyLimitFlg = null;
        String slsRepCntyNm = null;
        if (ZYPCommonFunc.hasValue(svcInvTmsg.techCd)) {
            Map<String, String> salesReptMap = getSalesRepInfo(glblCmpyCd, svcInvTmsg.techCd.getValue());
            if (salesReptMap != null) {
                slsRepGeoCd = (String) salesReptMap.get("GEO_CD");
                slsRepFirstLineAddr = (String) salesReptMap.get("FIRST_LINE_ADDR");
                slsRepScdLineAddr = (String) salesReptMap.get("SCD_LINE_ADDR");
                slsRepCtyAddr = (String) salesReptMap.get("CTY_ADDR");
                slsRepStCd = (String) salesReptMap.get("ST_CD");
                slsRepPostCd = (String) salesReptMap.get("POST_CD");
                slsRepCtryCd = (String) salesReptMap.get("CTRY_CD");
                slsRepInsdCtyLimitFlg = (String) salesReptMap.get("DS_INSD_CTY_LIMIT_FLG");
                slsRepCntyNm = (String) salesReptMap.get("CNTY_NM");
            }
        }

        // Get Mdse Info
        String svcAllocTpCd = null;
        String taxExemTpCd = null;
        String svcAllocTpNm = null;
        Map<String, String> mdseMap = getMdseInfo(glblCmpyCd, svcInvLineTmsg.mdseCd.getValue());
        if (mdseMap != null) {
            svcAllocTpCd = (String) mdseMap.get("SVC_ALLOC_TP_CD");
            taxExemTpCd = (String) mdseMap.get("TAX_EXEM_TP_CD");
            svcAllocTpNm = (String) mdseMap.get("SVC_ALLOC_TRX_TP_NM");
        }
        // mod start 2016/04/11 CSA Defect#4488
        if (!ZYPCommonFunc.hasValue(svcAllocTpNm)) {
            svcAllocTpNm = ZYPCodeDataUtil.getVarCharConstValue(DEFAULT_TAX_TRX_TP, glblCmpyCd);
        }
        // mod end 2016/04/11 CSA Defect#4488

        // Get DS Invoice Type Info
        String taxCalcFlg = null;
        String taxExemFlg = null;
        String taxExemRsnCd = null;
        DS_INV_TPTMsg dsInvTpTmsg = getDsInvTpInfo(glblCmpyCd, ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSZC0060_DS_INV_TYPE_INVOICE, glblCmpyCd));
        if (dsInvTpTmsg != null) {
            taxCalcFlg = dsInvTpTmsg.taxCalcFlg.getValue();
            taxExemFlg = dsInvTpTmsg.taxExemFlg.getValue();
            taxExemRsnCd = dsInvTpTmsg.taxExemRsnCd.getValue();
        }

        // Set Header
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.glblCmpyCd, svcInvTmsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.slsDt, ZYPDateUtil.getSalesDate(svcInvTmsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxModeCd, "I");
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.dsAcctNum_SE, fsrTmsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.billToAcctNum, fsrTmsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.billToLocNum, billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.dsTaxGrpExemCd_B, billToTaxGrpExemCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.dsTaxGrpExemReslFlg_B, dsTaxGrpExemReslFlg);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.dsAcctNum_ST, fsrTmsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.shipToLocNum, shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.dsTaxGrpExemCd_ST, shipToTaxExemCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalcFlg, taxCalcFlg);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxExemFlg, taxExemFlg);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxExemRsnCd, taxExemRsnCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.trxDt, svcInvTmsg.invDt);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxTaxCalcHdrNum, svcInvTmsg.svcInvNum);

        ZYPEZDItemValueSetter.setValue(taxApiPMsg.geoCd_ST, shipToGeoCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.dsInsdCtyLimitFlg_ST, shipToInsdCtyLimitFlg);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxAreaId_ST, shipTotaxAreaId);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.firstLineAddr_ST, shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.scdLineAddr_ST, shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.ctyAddr_ST, shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.stCd_ST, shipToStCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.cntyNm_ST, shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.postCd_ST, shipToPostCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.ctryCd_ST, shipToCtryCd);

        ZYPEZDItemValueSetter.setValue(taxApiPMsg.geoCd_SR, slsRepGeoCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.dsInsdCtyLimitFlg_SR, slsRepInsdCtyLimitFlg);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.firstLineAddr_SR, slsRepFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.scdLineAddr_SR, slsRepScdLineAddr);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.ctyAddr_SR, slsRepCtyAddr);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.stCd_SR, slsRepStCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.cntyNm_SR, slsRepCntyNm);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.postCd_SR, slsRepPostCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.ctryCd_SR, slsRepCtryCd);

        // Set Line
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).xxTaxCalcLineNum_A, svcInvLineTmsg.svcInvLinePk.getValue().toString());
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).trxDt_A, svcInvTmsg.invDt);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).mdseCd_A, svcInvLineTmsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).svcAllocTpCd_A, svcAllocTpCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).svcAllocTrxTpNm_A, svcAllocTpNm);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).taxExemTpCd_A, taxExemTpCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).shipQty_A, svcInvLineTmsg.svcInvQty);
        BigDecimal funcNetUnitPrcAmt = svcInvLineTmsg.invLineFuncNetAmt.getValue().divide(svcInvLineTmsg.svcInvQty.getValue(), 2, BigDecimal.ROUND_HALF_UP);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).funcNetUnitPrcAmt_A, funcNetUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).slsAmt_A, svcInvLineTmsg.invLineFuncNetAmt);

        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).billToAcctNum_A, fsrTmsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).billToLocNum_A, billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).dsAcctNum_AT, fsrTmsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(taxApiPMsg.taxCalculateInputLine.no(0).shipToLocNum_A, shipToCustLocCd);

        EZDMsg.copy(taxApiPMsg, "B", taxApiPMsg.taxCalculateInputLine.no(0), "AB");
        EZDMsg.copy(taxApiPMsg, "ST", taxApiPMsg.taxCalculateInputLine.no(0), "AT");
        EZDMsg.copy(taxApiPMsg, "SR", taxApiPMsg.taxCalculateInputLine.no(0), "AR");

        taxApiPMsg.taxCalculateInputLine.setValidCount(1);
        return taxApiPMsg;
    }

    /**
     * <pre>
     * Get Bill To information
     * </pre>
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return Map<String, String>
     */
    private static Map<String, String> getBillToInfo(String glblCmpyCd, String billToCustCd) {
        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("billToCustCd", billToCustCd);
        return (Map<String, String>) ssmClient.queryObject("getBillToInfo", prmMap);
    }

    /**
     * <pre>
     * Get Ship To information
     * </pre>
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return Map<String, String>
     */
    private static Map<String, String> getShipToInfo(String glblCmpyCd, String shipToCustCd) {
        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("shipToCustCd", shipToCustCd);
        return (Map<String, String>) ssmClient.queryObject("getShipToInfo", prmMap);
    }

    /**
     * <pre>
     * Get Sales Rep information
     * </pre>
     * @param glblCmpyCd String
     * @param techCd String
     * @return Map<String, String>
     */
    private static Map<String, String> getSalesRepInfo(String glblCmpyCd, String techCd) {
        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("techCd", techCd);
        return (Map<String, String>) ssmClient.queryObject("getSalesRepInfo", prmMap);
    }

    /**
     * <pre>
     * Get Mdse Information
     * </pre>
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return Map<String, String>
     */
    private static Map<String, String> getMdseInfo(String glblCmpyCd, String mdseCd) {
        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("mdseCd", mdseCd);
        return (Map<String, String>) ssmClient.queryObject("getMdseInfo", prmMap);
    }

    /**
     * <pre>
     * Get Tax Grp Exem information
     * </pre>
     * @param glblCmpyCd String
     * @param dsTaxGrpExemCd String
     * @return String
     */
    private static String getDsTaxGrpExem(String glblCmpyCd, String dsTaxGrpExemCd) {
        DS_TAX_GRP_EXEMTMsg inMsg = new DS_TAX_GRP_EXEMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsTaxGrpExemCd, dsTaxGrpExemCd);
        DS_TAX_GRP_EXEMTMsg dsTaxGrpExemTmsg = (DS_TAX_GRP_EXEMTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (dsTaxGrpExemTmsg == null) {
            return null;
        }
        return (String) dsTaxGrpExemTmsg.dsTaxGrpExemReslFlg.getValue();
    }

    /**
     * <pre>
     * Get DS Invoice Type information
     * </pre>
     * @param glblCmpyCd String
     * @param dsInvTpCd String
     * @return DS_INV_TPTMsg
     */
    private static DS_INV_TPTMsg getDsInvTpInfo(String glblCmpyCd, String dsInvTpCd) {
        DS_INV_TPTMsg inMsg = new DS_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsInvTpCd, dsInvTpCd);
        return (DS_INV_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
}
