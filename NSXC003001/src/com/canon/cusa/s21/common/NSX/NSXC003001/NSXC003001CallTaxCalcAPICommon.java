/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INVTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/09/2015   Hitachi         T.Tomita        Create          N/A
 * 01/04/2016   Hitachi         T.Kanasaka      Update          N/A
 * 03/18/2016   Hitachi         T.Kanasaka      Update          QC#5719
 * 03/25/2016   Hitachi         K.Kishimoto     Update          QC#5879
 * 06/17/2016   Hitachi         M.Gotou         Update          QC#9801
 * 09/27/2016   Hitachi         T.Kanasaka      Update          QC#9905
 * 02/28/2017   Hitachi         N.Arai          Update          QC#17748
 * 10/16/2017   Hitachi         U.Kim           Update          QC#21584
 * 10/18/2017   Hitachi         U.Kim           Update          QC#21584-1
 * 2018/07/12   Hitachi         K.Kojima        Update          QC#26571
 * 2018/07/25   Hitachi         A.Kohinata      Update          QC#27329
 * 2018/09/10   Hitachi         K.Kojima        Update          QC#28107
 * 2020/05/13   Hitachi         K.Sakurai       Update          QC#56726
 * 2021/06/16   CITS            R.Cabral        Update          QC#58889
 * 2023/11/13   Hitachi         K.Kishimoto     Update          QC#61468
 *</pre>
 */
public class NSXC003001CallTaxCalcAPICommon {

    // START 2023/11/13 [QC#61468, ADD]
    /** default transaction type for tax varchar const key */
    private static final String CONST_KEY_DEFAULT_TAX_TRX_TP = "DEFAULT_TAX_TRX_TP";
    // END   2023/11/13 [QC#61468, ADD]

    /** SSM batch client */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NSXC003001CallTaxCalcAPICommon.class);

    /**
     * Set Bill to Information Parameters
     * @param pMsg NWZC036102PMsg
     * @param billToCustCd String
     */
    public static void setBillToInfoParams(NWZC036101PMsg pMsg, String billToCustCd) {
        if (!hasValue(billToCustCd)) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("billToCustCd", billToCustCd);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getBillToInfo", param);
        if (rtnMap == null) {
            return;
        }

        // Set PMsg Parameter
        setValue(pMsg.billToAcctNum, (String) rtnMap.get("SELL_TO_CUST_CD"));
        setValue(pMsg.billToLocNum, billToCustCd);
        setValue(pMsg.dsTaxGrpExemCd_B, (String) rtnMap.get("DS_TAX_GRP_EXEM_CD"));
        setValue(pMsg.dsTaxGrpExemReslFlg_B, (String) rtnMap.get("DS_TAX_GRP_EXEM_RESL_FLG"));
    }

    // START 2016/03/18 T.Kanasaka [QC#5719, MOD]
    /**
     * Set Ship to Information Parameters
     * @param pMsg NWZC036102PMsg
     * @param dsContrDtlTpCd String
     * @param svcMachMstrPk BighDecimal
     * @param dsAcctNum String
     * @param billToCustCd String
     * @param dsContrPk BigDecimal
     * @param logOutputFlg String
     */
    // START 2016/09/27 T.Kanasaka [QC#9905, MOD]
//    public static void setShipToInfoParams(NWZC036101PMsg pMsg, String dsContrDtlTpCd, BigDecimal svcMachMstrPk, String dsAcctNum, String billToCustCd) {
    // START 2018/09/10 K.Kojima [QC#28107,MOD]
    // public static void setShipToInfoParams(NWZC036101PMsg pMsg, String dsContrDtlTpCd, BigDecimal svcMachMstrPk, String dsAcctNum, String billToCustCd, BigDecimal dsContrPk) {
    // START 2020/05/13 K.Sakurai [QC#56726, MOD]
    // public static void setShipToInfoParams(NWZC036101PMsg pMsg, String dsContrDtlTpCd, BigDecimal svcMachMstrPk, String dsAcctNum, String billToCustCd, BigDecimal dsContrPk, String logOutputFlg) {
    public static void setShipToInfoParams(NWZC036101PMsg pMsg, String dsContrDtlTpCd, BigDecimal svcMachMstrPk, String dsAcctNum, String billToCustCd, BigDecimal dsContrPk, String logOutputFlg, String origSvcInvNum) {
    // END 2020/05/13 K.Sakurai [QC#56726, MOD]
    // END 2018/09/10 K.Kojima [QC#28107,MOD]
    // END 2016/09/27 T.Kanasaka [QC#9905, MOD]
        if (!hasValue(dsContrDtlTpCd)) {
            return;
        }
        // START 2020/05/13 K.Sakurai [QC#56726, ADD]
        /** Service Machine Master Status at Customer */
        List<String> svcMachMstrStsCust = new ArrayList<String>();
        svcMachMstrStsCust.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        svcMachMstrStsCust.add(SVC_MACH_MSTR_STS.INSTALLED);
        svcMachMstrStsCust.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        // END 2020/05/13 K.Sakurai [QC#56726, ADD]

        String shipToCustAcctCd = null;
        String shipToCustLocCd = null;
        if (!DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.svcMachMstrPk, svcMachMstrPk);

            SVC_MACH_MSTRTMsg svcMachMstrTmsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
            if (svcMachMstrTmsg == null) {
                return;
            }
            shipToCustAcctCd = svcMachMstrTmsg.curLocAcctNum.getValue();
            shipToCustLocCd = svcMachMstrTmsg.curLocNum.getValue();
            // START 2020/05/13 K.Sakurai [QC#56726, ADD]
            String svcMachMstrStsCd = svcMachMstrTmsg.svcMachMstrStsCd.getValue();
            if (!svcMachMstrStsCust.contains(svcMachMstrStsCd)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                param.put("svcMachMstrPk", svcMachMstrPk);
                param.put("svcMachMstrStsCdList", svcMachMstrStsCust);
                Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getSvcMachMstrHist", param);
                
                shipToCustAcctCd = (String) resultMap.get("CUR_LOC_ACCT_NUM");
                shipToCustLocCd = (String) resultMap.get("CUR_LOC_NUM");
            }
            // END 2020/05/13 K.Sakurai [QC#56726, ADD]
        } else {
            // Map<String, Object> param = new HashMap<String,
            // Object>();
            // param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            // param.put("sellToCustCd", dsAcctNum);
            // param.put("rgtnStsCd",
            // RGTN_STS.READY_FOR_ORDER_TAKING);

            // shipToCustAcctCd = dsAcctNum;
            // shipToCustLocCd = (String)
            // ssmBatchClient.queryObject("getDefShipToCustCd",
            // param);

            // FleetBrShipToCustCdInfoBean bean = new
            // FleetBrShipToCustCdInfoBean();
            // bean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            // bean.setBillToCustCd(billToCustCd);
            // bean =
            // NSXC003001FleetBrShipToCustCd.getFleetBrShipToCustCd(bean);
            // if (bean == null) {
            // return;
            // }
            //
            // shipToCustAcctCd = dsAcctNum;
            // shipToCustLocCd = bean.getDsDefShipToCd();

            // START 2016/09/27 T.Kanasaka [QC#9905, MOD]
            shipToCustAcctCd = dsAcctNum;
            shipToCustLocCd = getFleetShipToCustCd(pMsg.glblCmpyCd.getValue(), dsContrPk);
            if (!hasValue(shipToCustLocCd)) {
                // add start 2016/06/17 CSA Defect#9801
                NMZC610001PMsg billToCustPmsg = new NMZC610001PMsg();
                ZYPEZDItemValueSetter.setValue(billToCustPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustPmsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
                ZYPEZDItemValueSetter.setValue(billToCustPmsg.dsAcctNum_I1, dsAcctNum);
                ZYPEZDItemValueSetter.setValue(billToCustPmsg.billToCustCd, billToCustCd);
                // START 2018/09/10 K.Kojima [QC#28107,ADD]
                s21InfoLogOutputPrintln(logOutputFlg, "NSXC003001CallTaxCalcAPICommon-setShipToInfoParams-callNMZC6100 start");
                // END 2018/09/10 K.Kojima [QC#28107,ADD]
                new NMZC610001().execute(billToCustPmsg, ONBATCH_TYPE.BATCH);
                // START 2018/09/10 K.Kojima [QC#28107,ADD]
                s21InfoLogOutputPrintln(logOutputFlg, "NSXC003001CallTaxCalcAPICommon-setShipToInfoParams-callNMZC6100 end");
                // END 2018/09/10 K.Kojima [QC#28107,ADD]
                if (S21ApiUtil.isXxMsgId(billToCustPmsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(billToCustPmsg);
                    for (String msgId : msgIdList) {
                        if (msgId.endsWith("E")) {
                            return;
                        }
                    }
                }
//                shipToCustAcctCd = dsAcctNum;
                if (billToCustPmsg.DefaultBillShipList.getValidCount() > 0) {
                    shipToCustLocCd = billToCustPmsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
                }
            }
            // add end 2016/06/17 CSA Defect#9801
            // END 2016/09/27 T.Kanasaka [QC#9905, MOD]
        }

        if (!hasValue(shipToCustLocCd)) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("shipToCustCd", shipToCustLocCd);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", param);
        if (rtnMap == null) {
            return;
        }

        // Set PMsg Parameter
        setValue(pMsg.dsAcctNum_ST, shipToCustAcctCd);
        setValue(pMsg.shipToLocNum, shipToCustLocCd);
        setValue(pMsg.dsTaxGrpExemCd_ST, (String) rtnMap.get("DS_TAX_GRP_EXEM_CD"));
        setValue(pMsg.geoCd_ST, (String) rtnMap.get("GEO_CD"));
        setValue(pMsg.dsInsdCtyLimitFlg_ST, (String) rtnMap.get("DS_INSD_CTY_LIMIT_FLG"));
        setValue(pMsg.taxAreaId_ST, (String) rtnMap.get("TAX_AREA_ID"));
        setValue(pMsg.firstLineAddr_ST, (String) rtnMap.get("FIRST_LINE_ADDR"));
        setValue(pMsg.scdLineAddr_ST, (String) rtnMap.get("SCD_LINE_ADDR"));
        setValue(pMsg.ctyAddr_ST, (String) rtnMap.get("CTY_ADDR"));
        setValue(pMsg.stCd_ST, (String) rtnMap.get("ST_CD"));
        setValue(pMsg.cntyNm_ST, (String) rtnMap.get("CNTY_NM"));
        setValue(pMsg.postCd_ST, (String) rtnMap.get("POST_CD"));
        setValue(pMsg.ctryCd_ST, (String) rtnMap.get("CTRY_CD"));
    }

    // END 2016/03/18 T.Kanasaka [QC#5719, MOD]

    /**
     * Set DS Invoice Information Parameters
     * @param pMsg NWZC036102PMsg
     * @param dsContrPk BighDecimal
     */
    public static void setDsInvInfoParams(NWZC036101PMsg pMsg, BigDecimal dsContrPk) {
        if (!hasValue(dsContrPk)) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", dsContrPk);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getDsInvInfo", param);
        if (rtnMap == null) {
            return;
        }

        // Set PMsg Parameter
        setValue(pMsg.taxCalcFlg, (String) rtnMap.get("TAX_CALC_FLG"));
        setValue(pMsg.taxExemFlg, (String) rtnMap.get("TAX_EXEM_FLG"));
        setValue(pMsg.taxExemRsnCd, (String) rtnMap.get("TAX_EXEM_RSN_CD"));
    }

    /**
     * Set DS Merchandise Information Parameters
     * @param pMsg NWZC036102_taxCalculateInputLinePMsg
     * @param glblCmpyCd String
     * @param mdseCd String
     */
    public static void setDsMdseInfoParams(NWZC036101_taxCalculateInputLinePMsg pMsg, String glblCmpyCd, String mdseCd) {
        if (!hasValue(mdseCd)) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getDsMdseInfo", param);
        if (rtnMap == null) {
            return;
        }

        // Set PMsg Parameter
        setValue(pMsg.mdseCd_A, mdseCd);
        setValue(pMsg.svcAllocTpCd_A, (String) rtnMap.get("SVC_ALLOC_TP_CD"));
        // setValue(pMsg.svcAllocTrxTpNm_A, (String)
        // rtnMap.get("SVC_ALLOC_TP_NM"));
        setValue(pMsg.svcAllocTrxTpNm_A, (String) rtnMap.get("SVC_ALLOC_TRX_TP_NM"));
        // START 2023/11/13 [QC#61468, ADD]
        if (!hasValue(pMsg.svcAllocTrxTpNm_A)) {
            // default set if value is null
            setValue(pMsg.svcAllocTrxTpNm_A, ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_DEFAULT_TAX_TRX_TP, glblCmpyCd));
        }
        // END   2023/11/13 [QC#61468, ADD]
        setValue(pMsg.taxExemTpCd_A, (String) rtnMap.get("TAX_EXEM_TP_CD"));
    }

    // Add Start 03/25/2016 <QC#5879>
    /**
     * Set DS Merchandise Information Parameters
     * @param pMsg NWZC036102_taxCalculateInputLinePMsg
     * @param glblCmpyCd String
     * @param svcPgmMdseCd String
     * @param mdseCdForSvcAllocTp String
     */
    public static void setDsMdseInfoParams(NWZC036101_taxCalculateInputLinePMsg pMsg, String glblCmpyCd, String svcPgmMdseCd, String mdseCdForSvcAllocTp) {
        if (!hasValue(svcPgmMdseCd) || !hasValue(mdseCdForSvcAllocTp)) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCdForSvcAllocTp);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getDsMdseInfo", param);
        if (rtnMap == null) {
            return;
        }

        // START 2021/06/16 R.Cabral [QC#58889,MOD]
        // Set PMsg Parameter
        // setValue(pMsg.mdseCd_A, svcPgmMdseCd);
        // setValue(pMsg.svcAllocTpCd_A, (String) rtnMap.get("SVC_ALLOC_TP_CD"));
        // setValue(pMsg.svcAllocTrxTpNm_A, (String) rtnMap.get("SVC_ALLOC_TRX_TP_NM"));
        // setValue(pMsg.taxExemTpCd_A, (String) rtnMap.get("TAX_EXEM_TP_CD"));
        Map<String, Object> paramForTaxCd = new HashMap<String, Object>();
        paramForTaxCd.put("glblCmpyCd", glblCmpyCd);
        paramForTaxCd.put("mdseCd", svcPgmMdseCd);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMapForTaxCd = (Map<String, Object>) ssmBatchClient.queryObject("getDsMdseInfo", paramForTaxCd);
        if (rtnMapForTaxCd == null) {
            return;
        }

        // Set PMsg Parameter
        setValue(pMsg.mdseCd_A, svcPgmMdseCd);
        setValue(pMsg.svcAllocTpCd_A, (String) rtnMap.get("SVC_ALLOC_TP_CD"));
        setValue(pMsg.svcAllocTrxTpNm_A, (String) rtnMap.get("SVC_ALLOC_TRX_TP_NM"));
        setValue(pMsg.taxExemTpCd_A, (String) rtnMapForTaxCd.get("TAX_EXEM_TP_CD"));
        // END 2021/06/16 R.Cabral [QC#58889,MOD]
    }
    // Add End 03/25/2016 <QC#5879>

    // START 2018/07/12 K.Kojima [QC#26571,ADD]
    /**
     * Set DS Merchandise Information Parameters
     * @param pMsg NWZC036102_taxCalculateInputLinePMsg
     * @param glblCmpyCd String
     * @param svcPgmMdseCd String
     * @param mdseCdForSvcAllocTp String
     */
    public static void setDsMdseInfoParams(NWZC036101_taxCalculateInputLinePMsg pMsg, String glblCmpyCd, String svcPgmMdseCd, String mdseCdForSvcAllocTp, String mdseCdForSvcAllocTpAddlChrg) {
        if (!hasValue(svcPgmMdseCd) || !hasValue(mdseCdForSvcAllocTp) || !hasValue(mdseCdForSvcAllocTpAddlChrg)) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCdForSvcAllocTp);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getDsMdseInfo", param);
        if (rtnMap == null) {
            param = new HashMap<String, Object>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("mdseCd", mdseCdForSvcAllocTpAddlChrg);
            rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getDsMdseInfo", param);
            if (rtnMap == null) {
                return;
            }
        }

        // Set PMsg Parameter
        setValue(pMsg.mdseCd_A, svcPgmMdseCd);
        setValue(pMsg.svcAllocTpCd_A, (String) rtnMap.get("SVC_ALLOC_TP_CD"));
        setValue(pMsg.svcAllocTrxTpNm_A, (String) rtnMap.get("SVC_ALLOC_TRX_TP_NM"));
        setValue(pMsg.taxExemTpCd_A, (String) rtnMap.get("TAX_EXEM_TP_CD"));
    }

    // END 2018/07/12 K.Kojima [QC#26571,ADD]

    // START 2016/09/27 T.Kanasaka [QC#9905, ADD]
    private static String getFleetShipToCustCd(String glblCmpyCd, BigDecimal dsContrPk) {
        if (!hasValue(dsContrPk)) {
            return null;
        }

        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPk01", dsContrPk);
        inMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.FLEET);
        DS_CONTR_DTLTMsgArray outArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).shipToCustCd.getValue();
    }
    // END 2016/09/27 T.Kanasaka [QC#9905, ADD]

    // START 2017/02/28 N.Arai [QC#17748, ADD]
    /**
     * Set Sales Rep Information Parameters
     * @param pMsg NWZC036102PMsg
     * @param dsContrPk BigDecimal
     * @param nextBllgDt String
     */
    public static void setSalesRepInfoParams(NWZC036101PMsg pMsg, BigDecimal dsContrPk, String nextBllgDt) {
        if (!hasValue(dsContrPk)) {
            return;
        }

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        String tocCd = getTocCd(glblCmpyCd, dsContrPk);
        if (!hasValue(tocCd)) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("tocCd", tocCd);
        param.put("nextBllgDt", nextBllgDt);

        @SuppressWarnings("unchecked")
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getSalesRepInfo", param);
        if (rtnMap == null) {
            return;
        }

        // Set PMsg Parameter
        // Sales Rep Tax Area ID
        setValue(pMsg.geoCd_SR, (String) rtnMap.get("GEO_CD"));
        // Sales Rep Inside City Limit Flag
        setValue(pMsg.dsInsdCtyLimitFlg_SR, (String) rtnMap.get("DS_INSD_CTY_LIMIT_FLG"));
        // Sales Rep First Line Address
        setValue(pMsg.firstLineAddr_SR, (String) rtnMap.get("FIRST_LINE_ADDR"));
        // Sales Rep Second Line Address
        setValue(pMsg.scdLineAddr_SR, (String) rtnMap.get("SCD_LINE_ADDR"));
        // Sales Rep City Address
        setValue(pMsg.ctyAddr_SR, (String) rtnMap.get("CTY_ADDR"));
        // Sales Rep State Code
        setValue(pMsg.stCd_SR, (String) rtnMap.get("ST_CD"));
        // Sales Rep County Name
        setValue(pMsg.cntyNm_SR, (String) rtnMap.get("CNTY_NM"));
        // Sales Rep Post Code
        setValue(pMsg.postCd_SR, (String) rtnMap.get("POST_CD"));
        // Sales Rep Country Code
        setValue(pMsg.ctryCd_SR, (String) rtnMap.get("CTRY_CD"));
    }

    private static String getTocCd(String glblCmpyCd, BigDecimal dsContrPk) {

        DS_CONTRTMsg paramTMsg = new DS_CONTRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.dsContrPk, dsContrPk);

        DS_CONTRTMsg dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(paramTMsg);
        if (dsContrTMsg == null) {
            return null;
        }
        return dsContrTMsg.tocCd.getValue();
    }

    // END 2017/02/28 N.Arai [QC#17748, ADD]

    // START 2017/10/16 U.Kim [QC#21584, ADD]
    /**
     * Set Invoice Date Information Parameters
     * @param pMsg NWZC036102PMsg
     * @param svcInvNum BighDecimal
     */
    public static void setTrxDateInfoParams(NWZC036101PMsg pMsg, String svcInvNum) {
        if (!hasValue(svcInvNum)) {
            return;
        }

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
     // START 2017/10/18 U.Kim [QC#21584-1, MOD]
     // SVC_INVTMsg paramTMsg = new SVC_INVTMsg();
     // setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
     // setValue(paramTMsg.svcInvNum, svcInvNum);

     // SVC_INVTMsg svcInvTMsg = (SVC_INVTMsg)
     // EZDTBLAccessor.findByKey(paramTMsg);
     // if (svcInvTMsg == null) {
     // return;
     // }

      // setValue(pMsg.trxDt, svcInvTMsg.invDt);

        SVC_INVTMsg svcInvTMsg = getSvcInv(glblCmpyCd, svcInvNum);
        if (svcInvTMsg == null) {
            return;
        }

        String invDt = svcInvTMsg.invDt.getValue();

        while (ZYPCommonFunc.hasValue(svcInvTMsg.svcCrRebilDtlPk)) {
            SVC_CR_REBIL_DTLTMsg svcCrRebilDtlTMsg = getSvcCrRebilDtl(glblCmpyCd, svcInvTMsg.svcCrRebilDtlPk.getValue());
            svcInvTMsg = getSvcInv(glblCmpyCd, svcCrRebilDtlTMsg.origSvcInvNum.getValue());
            if (svcInvTMsg == null) {
                break;
            }
            invDt = svcInvTMsg.invDt.getValue();
        }

        ZYPEZDItemValueSetter.setValue(pMsg.trxDt, invDt);
    }

    private static SVC_INVTMsg getSvcInv(String glblCmpyCd, String svcInvNum) {
        SVC_INVTMsg svcInvTMsg = new SVC_INVTMsg();
        svcInvTMsg.setSQLID("001");
        svcInvTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcInvTMsg.setConditionValue("svcInvNum01", svcInvNum);
        SVC_INVTMsgArray svcInvTMsgArray = (SVC_INVTMsgArray) EZDTBLAccessor.findByCondition(svcInvTMsg);
        if (svcInvTMsgArray == null || svcInvTMsgArray.getValidCount() == 0) {
            return null;
        }
        return svcInvTMsgArray.no(0);
    }

    private static SVC_CR_REBIL_DTLTMsg getSvcCrRebilDtl(String glblCmpyCd, BigDecimal svcCrRebilDtlPk) {
        SVC_CR_REBIL_DTLTMsg rebilParamTMsg = new SVC_CR_REBIL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rebilParamTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rebilParamTMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
        return (SVC_CR_REBIL_DTLTMsg) EZDTBLAccessor.findByKey(rebilParamTMsg);
    }
    // END 2017/10/18 U.Kim [QC#21584-1, MOD]
    // END 2017/10/16 U.Kim [QC#21584, ADD]

    // add start 2018/07/25 QC#27329
    public static void setOrigSvcInvParams(NWZC036101PMsg pMsg, String invTp, String origSvcInvNum, BigDecimal dsContrBllgSchdPk) {
        if (!INV_TP.CREDIT_MEMO.equals(invTp) || !hasValue(origSvcInvNum)) {
            return;
        }
        DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = getDsContrBllgSchd(pMsg.glblCmpyCd.getValue(), dsContrBllgSchdPk);
        if (dsContrBllgSchdTMsg == null || !ZYPCommonFunc.hasValue(dsContrBllgSchdTMsg.origDsContrBllgSchdPk)) {
            return;
        }
        dsContrBllgSchdTMsg = getDsContrBllgSchd(pMsg.glblCmpyCd.getValue(), dsContrBllgSchdTMsg.origDsContrBllgSchdPk.getValue());
        if (dsContrBllgSchdTMsg == null) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrBllgSchdPk", dsContrBllgSchdTMsg.dsContrBllgSchdPk.getValue());
        Map<String, Object> rtnMap = (Map<String, Object>) ssmBatchClient.queryObject("getOrigSvcInvInfo", param);
        if (rtnMap == null) {
            return;
        }

        // BillTo Info
        setValue(pMsg.billToAcctNum, (String) rtnMap.get("BILL_TO_ACCT_NUM"));
        setValue(pMsg.billToLocNum, (String) rtnMap.get("BILL_TO_CUST_CD"));
        setValue(pMsg.dsTaxGrpExemCd_B, (String) rtnMap.get("BILL_TO_TAX_GRP_EXEM_CD"));
        setValue(pMsg.dsTaxGrpExemReslFlg_B, (String) rtnMap.get("BILL_TO_TAX_GRP_EXEM_RESL_FLG"));

        // ShipTo Info
        setValue(pMsg.dsAcctNum_ST, (String) rtnMap.get("SHIP_TO_ACCT_NUM"));
        setValue(pMsg.shipToLocNum, (String) rtnMap.get("SHIP_TO_CUST_CD"));
        setValue(pMsg.dsTaxGrpExemCd_ST, (String) rtnMap.get("SHIP_TO_TAX_GRP_EXEM_CD"));
        setValue(pMsg.geoCd_ST, (String) rtnMap.get("SHIP_TO_GEO_CD"));
        setValue(pMsg.dsInsdCtyLimitFlg_ST, (String) rtnMap.get("SHIP_TO_DS_INSD_CTY_LIMIT_FLG"));
        setValue(pMsg.taxAreaId_ST, (String) rtnMap.get("SHIP_TO_TAX_AREA_ID"));
        setValue(pMsg.firstLineAddr_ST, (String) rtnMap.get("SHIP_TO_FIRST_LINE_ADDR"));
        setValue(pMsg.scdLineAddr_ST, (String) rtnMap.get("SHIP_TO_SCD_LINE_ADDR"));
        setValue(pMsg.ctyAddr_ST, (String) rtnMap.get("SHIP_TO_CTY_ADDR"));
        setValue(pMsg.stCd_ST, (String) rtnMap.get("SHIP_TO_ST_CD"));
        setValue(pMsg.cntyNm_ST, (String) rtnMap.get("SHIP_TO_CNTY_NM"));
        setValue(pMsg.postCd_ST, (String) rtnMap.get("SHIP_TO_POST_CD"));
        setValue(pMsg.ctryCd_ST, (String) rtnMap.get("SHIP_TO_CTRY_CD"));

        // SalesRep Info
        setValue(pMsg.geoCd_SR, (String) rtnMap.get("SLS_REP_GEO_CD"));
        setValue(pMsg.dsInsdCtyLimitFlg_SR, (String) rtnMap.get("SLS_REP_DS_INSD_CTY_LIMIT_FLG"));
        setValue(pMsg.firstLineAddr_SR, (String) rtnMap.get("SLS_REP_FIRST_LINE_ADDR"));
        setValue(pMsg.scdLineAddr_SR, (String) rtnMap.get("SLS_REP_SCD_LINE_ADDR"));
        setValue(pMsg.ctyAddr_SR, (String) rtnMap.get("SLS_REP_CTY_ADDR"));
        setValue(pMsg.stCd_SR, (String) rtnMap.get("SLS_REP_ST_CD"));
        setValue(pMsg.cntyNm_SR, (String) rtnMap.get("SLS_REP_CNTY_NM"));
        setValue(pMsg.postCd_SR, (String) rtnMap.get("SLS_REP_POST_CD"));
        setValue(pMsg.ctryCd_SR, (String) rtnMap.get("SLS_REP_CTRY_CD"));
    }

    private static DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchd(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // add end 2018/07/25 QC#27329

    // START 2018/09/10 K.Kojima [QC#28107,ADD]
    private static void s21InfoLogOutputPrintln(String logOutputFlg, String message) {
        if (logOutputFlg != null && logOutputFlg.equals(ZYPConstant.FLG_ON_Y)) {
            S21InfoLogOutput.println("[Billing Calculation Log]" + message);
        }
    }
    // END 2018/09/10 K.Kojima [QC#28107,ADD]
}
