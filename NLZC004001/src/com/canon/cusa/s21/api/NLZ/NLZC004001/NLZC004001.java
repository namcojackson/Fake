/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC004001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ADJ_CATGTMsg;
import business.db.ADJ_TRX_TPTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INVTYTMsg;
import business.db.INVTY_ORDTMsg;
import business.db.MDSETMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_SERTMsg;
import business.db.RWS_SERTMsgArray;
import business.parts.NLXC023001PMsg;
import business.parts.NLZC001001PMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC004001PMsg;
import business.parts.NLZC004001_serialInfoListPMsg;
import business.parts.NLZC005001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NLZC305001PMsg;
import business.parts.NLZC309001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001;
import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.constant.NLZC004001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC005001.NLZC005001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NLZ.NLZC305001.NLZC305001;
import com.canon.cusa.s21.api.NLZ.NLZC305001.constant.NLZC305001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC309001.NLZC309001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.constant.NLZC309001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NLX.NLXC023001.NLXC023001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Adjustment Order API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CSAI            Y.Imazu         Create          CSA
 * 06/10/2016   CSAI            Y.Imazu         Update          QC#9772
 * 04/12/2018   CITS            S.Katsuma       Update          SOL#078,160
 * 05/07/2018   CITS            S.Katsuma       Update          QC#25984
 * 06/05/2018   CITS            K.Ogino         Update          QC#26383
 * 12/11/2019   Fujitsu         R.Nakamura      Update          QC#55021
 * 12/23/2019   CITS            K.Ogino         Update          QC#55187
 * 02/05/2020   CITS            T.Wada          Update          QC#55670
 * 04/28/2020   CITS            M.Furugoori     Update          QC#56461
 * 06/04/2020   CITS            M.Furugoori     Update          QC#56720
 * 10/29/2021   CITS            R.Azucena       Update          QC#58899
 * 09/22/2023   Hitachi         T.Kuroda        Update          QC#61265
 * </pre>
 */
public class NLZC004001 extends S21ApiCommonBase {

    /** Process Type Code */
    private String procTpCd;

    /** Warning Mode Code */
    private String warningModeCd;

    /** Inventory Order Number */
    private String invtyOrdNum;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE paramOnBatchType;

    /** Service Machine Master List for Allocation */
    private ArrayList<Map<String, Object>> allcSvcMachMstrList = new ArrayList<Map<String, Object>>();

    // QC#18002 ADD START
    /** Service Machine Master PK List for Create */
    private ArrayList<BigDecimal> svcMachMstrPkList = new ArrayList<BigDecimal>();
    // QC#18002 ADD END

    // START 2021/10/29 R.Azucena[QC#58899, ADD]
    /** Service Machine Master PK Map List for TRMN */
    private Map<String, ArrayList<BigDecimal>> svcMachMstrPkTrmnMapList =  new HashMap<String, ArrayList<BigDecimal>>();
    // END 2021/10/29 R.Azucena[QC#58899, ADD]

    /** Message ID List */
    private Map<BigDecimal, ArrayList<String>> msgListMap =  new HashMap<BigDecimal, ArrayList<String>>();

    /** Adjustment Transaction Type */
    private ADJ_TRX_TPTMsg adjTrxTpTMsg = new ADJ_TRX_TPTMsg();

    /** S21SsmBatchClient */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLZC004001.class);

    // START 2018/04/17 S.Katsuma[SOL#078,160,ADD]
    /** Cycle Count Merchandise Type Merchandise Cd */
    private static String CYCLE_CNT_MDSE_TP_CD_MDSE = "01";

    /** Cycle Count Merchandise Type Parts Cd */
    private static String CYCLE_CNT_MDSE_TP_CD_PARTS = "02";
    // END 2018/04/17 S.Katsuma[SOL#078,160,ADD]

    // START 2020/04/28 [QC#56461,ADD]
    /** Message Parameter List */
    private Map<BigDecimal, ArrayList<String[]>> msgListParam =  new HashMap<BigDecimal, ArrayList<String[]>>();
    // END 2020/04/28 [QC#56461,ADD]

    /** Constructor */
    public NLZC004001() {

        super();
    }

    /**
     * Execute Adjustment Order API
     * @param pMsgList List<NLZC405001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC004001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        paramOnBatchType = onBatchType;

        boolean isfirstline = true;
        boolean isParamChkNorm = true;

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        for (int i = 0; i < pMsgList.size(); i++) {

            if (isfirstline) {

                procTpCd = pMsgList.get(i).xxProcTpCd.getValue();
                warningModeCd = pMsgList.get(i).xxModeCd.getValue();
                isfirstline = false;
            }

            if (!chkReqParams(pMsgList.get(i), i)) {

                isParamChkNorm = false;
            }
        }

        /*********************************************************
         * 2. Specify & Execute Event
         ********************************************************/
        // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        if (isParamChkNorm) {
            // 1.Create
            if (NLZC004001Constant.PROC_TP_CRAT.equals(procTpCd)) {

                executeCreate(pMsgList);
                // Call Inventory Adjustment Approval to WF
                NLZC005001PMsg invtyAdjApprvlWFApiPMsg = cratInvtyAdjApprvlWFApiPMsg(pMsgList);
                execNLZC005001(invtyAdjApprvlWFApiPMsg, 0);

            // 3.Close
            } else if (NLZC004001Constant.PROC_TP_CLO.equals(procTpCd)) {

                executeClose(pMsgList);

           
            // 4.Cancel
            } else if (NLZC004001Constant.PROC_TP_CANC.equals(procTpCd)) {

                executeCancel(pMsgList);

            // 5.Create & Close
            } else if (NLZC004001Constant.PROC_TP_CRAT_CLO.equals(procTpCd)) {

                executeCreateAndClose(pMsgList);
            }
        }
        // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

        /*********************************************************
         * 3. Set Message
         ********************************************************/
        // START 2020/04/28 [QC#56461,MOD]
        for (int i = 0; i < pMsgList.size(); i++) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsgList.get(i));

            ArrayList<String> msgList = msgListMap.get(new BigDecimal(i));
            ArrayList<String[]> msgParam = msgListParam.get(new BigDecimal(i));

            if (msgList != null && !msgList.isEmpty()) {

//                for (String msgId : msgList) {
//
//                    msgMap.addXxMsgId(msgId);
//                }
                for (int c = 0; c < msgList.size(); c++) {
                    String msgId = msgList.get(c);
                    String[] msgPrm = msgParam.get(c);
                    msgMap.addXxMsgIdWithPrm(msgId, msgPrm);
                }
            }

            msgMap.flush();
        }
        // END 2020/04/28 [QC#56461,MOD]
    }

    /**
     * Execute Adjustment Order API
     * @param pMsg NLZC405001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC004001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        paramOnBatchType = onBatchType;
        procTpCd = pMsg.xxProcTpCd.getValue();

        //Check API Parameter
        if (chkReqParams(pMsg, 0)) {

            if (NLZC004001Constant.PROC_TP_CLO.equals(pMsg.xxProcTpCd.getValue())) {

                // 3.Close
                executeClose(pMsg, 0);

            // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
            } else if (NLZC004001Constant.PROC_TP_CANC.equals(pMsg.xxProcTpCd.getValue())) {
                // 4.Cancel
                executeCancel(pMsg, 0);
            // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]
            } else {

                setMsg(0, NLZC004001Constant.NLCM0052E);
            }
        }

        // Set Message
        // START 2020/04/28 [QC#56461,MOD]
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        ArrayList<String> msgList = msgListMap.get(BigDecimal.ZERO);
        ArrayList<String[]> msgParam = msgListParam.get(BigDecimal.ZERO);

        if (msgList != null && !msgList.isEmpty()) {

//            for (String msgId : msgList) {
//
//                msgMap.addXxMsgId(msgId);
//            }
            for (int c = 0; c < msgList.size(); c++) {
                String msgId = msgList.get(c);
                String[] msgPrm = msgParam.get(c);
                msgMap.addXxMsgIdWithPrm(msgId, msgPrm);
            }
        }

        msgMap.flush();
        // END 2020/04/28 [QC#56461,MOD]
    }

    /**
     * Check Request Parameter
     * @param pMsg NLZC004001PMsg
     * @param index int
     * @return boolean Check Result
     */
    private boolean chkReqParams(NLZC004001PMsg pMsg, int index) {

        boolean isParamChkNorm = true;

        /*************************************************************
         * 1. Check API Parameter (Common)
         ************************************************************/
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {

            setMsg(index, NLZC004001Constant.NLZM0003E);
            isParamChkNorm = false;
        }

         // Sales Date
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {

            setMsg(index, NLZC004001Constant.NLZM2079E);
            isParamChkNorm = false;
        }

        // Process Type
        if (!ZYPCommonFunc.hasValue(pMsg.xxProcTpCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM2375E);
            isParamChkNorm = false;

        } else if (!Arrays.asList(NLZC004001Constant.getProcType()).contains(pMsg.xxProcTpCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM0001E);
            isParamChkNorm = false;

        } else if (!procTpCd.equals(pMsg.xxProcTpCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM2378E);
            isParamChkNorm = false;
        }

        // Data Type
        if (!ZYPCommonFunc.hasValue(pMsg.xxDtTpCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM2376E);
            isParamChkNorm = false;

        } else if (!Arrays.asList(NLZC004001Constant.getDataType()).contains(pMsg.xxDtTpCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM0047E);
            isParamChkNorm = false;
        }

        // Inventory Order Type
        if (!ZYPCommonFunc.hasValue(pMsg.invtyOrdTpCd)) {

            setMsg(index, NLZC004001Constant.NLZM2374E);
            isParamChkNorm = false;

        } else if (!Arrays.asList(NLZC004001Constant.getInvtyOrdType()).contains(pMsg.invtyOrdTpCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM0115E);
            isParamChkNorm = false;
        }

        // Adjustment Transaction Type
        if (NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue()) && ZYPCommonFunc.hasValue(pMsg.adjTrxTpCd)) {
            // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
            adjTrxTpTMsg = getAdjTrxTp(pMsg.glblCmpyCd.getValue(), pMsg.adjTrxTpCd.getValue());
            isParamChkNorm = chkAdjTrxTp(pMsg, adjTrxTpTMsg, index);
            // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        }

        /*************************************************************
         * 2. Check API Parameter (For Each Mode)
         ************************************************************/
        // 1. Create
        if (NLZC004001Constant.PROC_TP_CRAT.equals(procTpCd)) {

            if (!chkReqParamsForCrat(pMsg, index)) {

                isParamChkNorm = false;
            }

        // 3.Close
        } else if (NLZC004001Constant.PROC_TP_CLO.equals(procTpCd)) {

            if (!chkReqParamsForClo(pMsg, index)) {

                isParamChkNorm = false;
            }

        // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
        // 4.Cancel
        } else if (NLZC004001Constant.PROC_TP_CANC.equals(procTpCd)) {

            if (!chkReqParamsForCanc(pMsg, index)) {

                isParamChkNorm = false;
            }
        // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]
        // 5.Create & Close
        } else if (NLZC004001Constant.PROC_TP_CRAT_CLO.equals(procTpCd)) {

            if (!chkReqParamsForCrat(pMsg, index)) {

                isParamChkNorm = false;
            }
        }

        return isParamChkNorm;
    }

    /**
     * Check Request Parameter (Creation)
     * @param pMsg NLZC004001PMsg
     * @param index int
     * @return boolean Check Result
     */
    private boolean chkReqParamsForCrat(NLZC004001PMsg pMsg, int index) {

        boolean isParamChkNorm = true;

        // Header
        if (NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue())) {

            // Inventory Location (Retail WH)
            if (!ZYPCommonFunc.hasValue(pMsg.invtyLocCd.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM2380E);
                isParamChkNorm = false;
            }

            // Location Status
            if (!ZYPCommonFunc.hasValue(pMsg.locStsCd.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM0007E);
                isParamChkNorm = false;
            }

            // Transaction Source Type
            if (!ZYPCommonFunc.hasValue(pMsg.trxSrcTpCd.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM0026E);
                isParamChkNorm = false;
            }

            // System Source
            if (!ZYPCommonFunc.hasValue(pMsg.sysSrcCd.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM0056E);
                isParamChkNorm = false;
            }

            // Adjustment Transaction Type
            if (!ZYPCommonFunc.hasValue(pMsg.adjTrxTpCd.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM2381E);
                isParamChkNorm = false;

            } else if (adjTrxTpTMsg == null) {

                setMsg(index, NLZC004001Constant.NLZM2377E);
                isParamChkNorm = false;
            }

        // Detail
        } else {

            // Merchandise Code
            if (!ZYPCommonFunc.hasValue(pMsg.mdseCd.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM0005E);
                isParamChkNorm = false;
            }

            // Stock Status
            if (!ZYPCommonFunc.hasValue(pMsg.stkStsCd.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM0008E);
                isParamChkNorm = false;
            }

            // Inventory Location (Retail WH + Sub Warehouse)
            if (!ZYPCommonFunc.hasValue(pMsg.invtyLocCd_D1.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM2383E);
                isParamChkNorm = false;
            }

            // Location Status
            if (!ZYPCommonFunc.hasValue(pMsg.locStsCd_D1.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM2384E);
                isParamChkNorm = false;
            }

            // Order Quantity
            if (!ZYPCommonFunc.hasValue(pMsg.ordQty.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM2083E);
                isParamChkNorm = false;

            } else if (BigDecimal.ZERO.equals(pMsg.ordQty.getValue())) {

                setMsg(index, NLZC004001Constant.NLZM2382E);
                isParamChkNorm = false;
            }

            // Chart of Account
            // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
//            if (adjTrxTpTMsg != null && ZYPConstant.FLG_ON_Y.equals(adjTrxTpTMsg.acctReqFlg.getValue())) {
//
//                if (!ZYPCommonFunc.hasValue(pMsg.coaCmpyCd) || !ZYPCommonFunc.hasValue(pMsg.coaBrCd) || !ZYPCommonFunc.hasValue(pMsg.coaAcctCd)
//                        || !ZYPCommonFunc.hasValue(pMsg.coaProdCd) || !ZYPCommonFunc.hasValue(pMsg.coaChCd) || !ZYPCommonFunc.hasValue(pMsg.coaCcCd)
//                        || !ZYPCommonFunc.hasValue(pMsg.coaAfflCd) || !ZYPCommonFunc.hasValue(pMsg.coaExtnCd) || !ZYPCommonFunc.hasValue(pMsg.coaProjCd)) {
//
//                    setMsg(index, NLZC004001Constant.NLZM2385E);
//                    isParamChkNorm = false;
//                }
//            }

            boolean isNeededDef = false;
            String adjCatgCd = pMsg.adjCatgCd.getValue();
            if (ZYPCommonFunc.hasValue(adjCatgCd)) {
                if (!ADJ_CATG.SPECIAL_ACCOUNT.equals(adjCatgCd)) {
                    ADJ_CATGTMsg adjCatgTMsg = getAdjCatg(pMsg.glblCmpyCd.getValue(), adjCatgCd);
                    if (adjCatgTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(pMsg.coaAcctCd, adjCatgTMsg.coaAcctCd);
                    } else {
                        // Get defaulg coaAcctCd
                        isNeededDef = true;
                    }
                } else {
                    if (!ZYPCommonFunc.hasValue(pMsg.coaAcctCd)) {
                        // Get defaulg coaAcctCd
                        isNeededDef = true;
                    } else {
                        // Do nothing because screen only
                    }
                }
            } else {
                // Get defaulg coaAcctCd
                isNeededDef = true;
            }

            if (isNeededDef) {
                // QC#26383
                if (!ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(pMsg.adjTrxTpCd.getValue())) {
                    Map<String, Object> partsOrItemCd = getPartsOrItem(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue());
                    String cycleCntMdseTpCd = null;
                    if (partsOrItemCd == null || partsOrItemCd.isEmpty()) {
                        cycleCntMdseTpCd = CYCLE_CNT_MDSE_TP_CD_MDSE;
                    } else {
                        cycleCntMdseTpCd = CYCLE_CNT_MDSE_TP_CD_PARTS;
                    }

                    Map<String, Object> defCoaAcct = getDefaultCOAAccount(pMsg.glblCmpyCd.getValue(), cycleCntMdseTpCd);
                    if (defCoaAcct != null && !defCoaAcct.isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(pMsg.coaAcctCd, defCoaAcct.get("COA_ACCT_CD").toString());

                    }
                }
            }
            // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        }

        return isParamChkNorm;
    }

    /**
     * Check Request Parameter (Close)
     * @param pMsg NLZC004001PMsg
     * @param index int
     * @return boolean Check Result
     */
    private boolean chkReqParamsForClo(NLZC004001PMsg pMsg, int index) {

        boolean isParamChkNorm = true;

        if (!NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM2373E);
            isParamChkNorm = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.invtyOrdNum)) {

            setMsg(index, NLZC004001Constant.NLZM0048E);
            isParamChkNorm = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.sysSrcCd)) {

            setMsg(index, NLZC004001Constant.NLZM0056E);
            isParamChkNorm = false;
        }

        return isParamChkNorm;
    }

    /**
     * Create Inventry Order
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeCreate(List<NLZC004001PMsg> pMsgList) {
        // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
        boolean result = true;

        if (ADJ_TRX_TP.ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            result = executeCreate4Adj(pMsgList);
        } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            result = executeCreate4SWT(pMsgList);
        }
        // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]

        return result;
    }

    /**
     * Create Adjustment Order
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeCreateAdjustmentOrd(List<NLZC004001PMsg> pMsgList) {

        List<NLZC003001PMsg> invtyOrdApiPMsgArray = new ArrayList<NLZC003001PMsg>();
        List<NLZC003001PMsg> invtyOrdApiPMsgArrayDtl = new ArrayList<NLZC003001PMsg>();

        // START 2020/06/04 [QC#56720,ADD]
        String rtlWhCd = "";
        // END 2020/06/04 [QC#56720,ADD]

        // Create Inventory Order API(NLZC0030) Parameter
        for (int i = 0; i < pMsgList.size(); i++) {

            // Header
            if (NLZC004001Constant.DT_TP_HDR.equals(pMsgList.get(i).xxDtTpCd.getValue())) {
                // START 2020/06/04 [QC#56720,ADD]
                rtlWhCd = pMsgList.get(i).invtyLocCd.getValue();
                // END 2020/06/04 [QC#56720,ADD]
                ZYPEZDItemValueSetter.setValue(pMsgList.get(i).trxCd, adjTrxTpTMsg.trxCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsgList.get(i).trxRsnCd, adjTrxTpTMsg.trxRsnCd.getValue());

                invtyOrdApiPMsgArray.add(cratAdjustmentOrdHdrApiPMsg(pMsgList.get(i)));

            // Detail
            } else {
                // START 2020/06/04 [QC#56720,ADD]
                if (ZYPCommonFunc.hasValue(pMsgList.get(i).cycleCntRsnCd)) {
                    List<String> autoApproveRsnList = new ArrayList<String>();
                    String rtlWhAutoApproveRsnCd = rtlWhCd + "_AUTO_APPROVE_RSN_CD";
                    String autoApproveRsnVal = ZYPCodeDataUtil.getVarCharConstValue(rtlWhAutoApproveRsnCd, pMsgList.get(i).glblCmpyCd.getValue());
                    if (ZYPCommonFunc.hasValue(autoApproveRsnVal)) {
                        autoApproveRsnList = Arrays.asList(autoApproveRsnVal.split(","));
                        if (pMsgList.get(i).adjCatgCd.getValue().equals(ADJ_CATG.OTHER) && autoApproveRsnList.contains(pMsgList.get(i).cycleCntRsnCd.getValue()) && !ZYPCommonFunc.hasValue(pMsgList.get(i).invtyOrdLineCmntTxt)) {
                            ZYPEZDItemValueSetter.setValue(pMsgList.get(i).invtyOrdLineCmntTxt, "WMS Reason Code:" + pMsgList.get(i).cycleCntRsnCd.getValue());
                        }
                    }
                }
                // END 2020/06/04 [QC#56720,ADD]
                ZYPEZDItemValueSetter.setValue(pMsgList.get(i).invtyOrdLineNum, ZYPCommonFunc.leftPad(Integer.toString(i), NLZC004001Constant.LINE_NUM_LENGTH, "0"));
                invtyOrdApiPMsgArrayDtl.add(cratAdjustmentOrdDtlApiPMsg(pMsgList.get(i), i));
            }
        }

        if (!isExistErr()) {
            for (NLZC003001PMsg invtyOrdDtlPMsg : invtyOrdApiPMsgArrayDtl) {
                invtyOrdApiPMsgArray.add(invtyOrdDtlPMsg);
            }

            // Inventory Order API(NLZC0030) result check
            if (!execNLZC003001(pMsgList, invtyOrdApiPMsgArray)) {

                return false;
            }

            invtyOrdNum = invtyOrdApiPMsgArray.get(0).invtyOrdNum.getValue();
        } else {
            return false;
        }

        return true;
    }

    // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
    /**
     * Inventory Allocation
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeInvtyAllocation(List<NLZC004001PMsg> pMsgList, String xxProcTpCd) {
        for (int i = 0; i < pMsgList.size(); i++) {
            if (!executeInvtyAllocation(pMsgList.get(i), xxProcTpCd, i)) {
                return false;
            }
        }

        return true;
    }
    // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

    /**
     * Machine Master Allocation
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeMachMstrAllocation(List<NLZC004001PMsg> pMsgList) {

        boolean isAllocMachMstr = true;

        if (allcSvcMachMstrList != null && !allcSvcMachMstrList.isEmpty()) {

            for (Map<String, Object> allocSvcMachMstrInfoMap : allcSvcMachMstrList) {

                BigDecimal svcMachMstrPk = (BigDecimal) allocSvcMachMstrInfoMap.get("SVC_MACH_MSTR_PK");
                int index = ((BigDecimal) allocSvcMachMstrInfoMap.get("MSG_INDEX")).intValue();

                // Create PMsg for Machine Master Allocation On
                NSZC001001PMsg svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgAlloc(pMsgList.get(index), svcMachMstrPk, invtyOrdNum, true);

                // Service Machine Master Update API(NSZC0010) is executed
                if (!execNSZC001001(svcMachMstrApiPMsg, index)) {
                    isAllocMachMstr = false;
                }
            }
        }

        return isAllocMachMstr;
    }

    /**
     * Machine Master Allocation Check
     * @param machMstrVal String
     * @param pMsgVal String
     * @return boolean
     */
    private boolean isMachMstrAllocChk(String machMstrVal, String pMsgVal) {

        if (!ZYPCommonFunc.hasValue(machMstrVal)) {

            return true;

        } else if (machMstrVal.equals(pMsgVal)) {

            return true;
        }

        return false;
    }

    /**
     * Check same value
     * @param val1 BigDecimal
     * @param val2 BigDecimal
     * @return boolean
     */
    private boolean isSameVal(BigDecimal val1, BigDecimal val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (val1.compareTo(val2) == 0) {

            return true;
        }

        return false;
    }

    /**
     * Close Adjustment Order
     * @param pMsgList List<NLZC004001PMsg>
     */
    private void executeClose(List<NLZC004001PMsg> pMsgList) {

        for (int i = 0; i < pMsgList.size(); i++) {

            executeClose(pMsgList.get(i), i);
        }
    }

    /**
     * Close Inventry Order
     * @param pMsg NLZC004001PMsg
     * @param index int
     */
    private void executeClose(NLZC004001PMsg pMsg, int index) {

        /*************************************************************
         * 1. Get Inventory Order
         ************************************************************/
        // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
        if (!getInvtyOrdAndChk(pMsg, index)) {
            return;
        }

        if (ADJ_TRX_TP.ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            executeClose4Adj(pMsg, index);
        } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            executeClose4SWT(pMsg, index);
        }
        // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]
    }

    /**
     * Execute Close for Detail for Adjustment
     * @param pMsg NLZC004001PMsg
     * @param invtyOrdDtlMap Map<String, Object>
     * @param index int
     * @return boolean
     */
    private boolean executeDtlClose4Adj(NLZC004001PMsg pMsg, Map<String, Object> invtyOrdDtlMap, int index) {

        /*************************************************************
         * 7. Get Inventory Order Serial
         ************************************************************/
        ArrayList<String> invtyOrdSerLst = getInvtyOrdSer(pMsg.glblCmpyCd.getValue(), invtyOrdDtlMap);

        List<String> updSerList = new ArrayList<String>();

        /*************************************************************
         * 1. Inventory Update API (NLZC0020)
         ************************************************************/
        // START 04/23/2020 [QC#56461,ADD]
        BigDecimal rqstQty = (BigDecimal) invtyOrdDtlMap.get("ORD_QTY");

        if (BigDecimal.ZERO.compareTo(rqstQty) > 0) {

            INVTYTMsg invtyTMsg = getInvty(pMsg.glblCmpyCd.getValue(), invtyOrdDtlMap);
            BigDecimal invtyQty = BigDecimal.ZERO;

            if (invtyTMsg != null) {
                invtyQty = invtyTMsg.invtyQty.getValue();
            }
            BigDecimal qty = invtyQty.add(rqstQty);

            if (BigDecimal.ZERO.compareTo(qty) > 0) {
                String[] msgPrms = new String[] {(String) invtyOrdDtlMap.get("INVTY_LOC_CD"), (String) invtyOrdDtlMap.get("MDSE_CD"), (String) invtyOrdDtlMap.get("STK_STS_CD")};
                setMsgParam(index, NLZC004001Constant.NLZM2525E, msgPrms);
                return false;
            }
        }
        // END   04/23/2020 [QC#56461,ADD]

        NLZC002001PMsg invtyUpdApiPMsg = cratInvtyUpdApiPMsg(pMsg, invtyOrdDtlMap, invtyOrdSerLst);

        // Inventory Update API(NLZC0020) is executed
        if (!execNLZC002001(invtyUpdApiPMsg, index)) {

            return false;
        }

        /*************************************************************
         * 2. Get Allocated Service Machine Master Data
         ************************************************************/
        ArrayList<Map<String, Object>> svcMachMstrList = getAllocSvcMachMstrList(pMsg.glblCmpyCd.getValue(), invtyOrdDtlMap);

        if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {

            for (Map<String, Object> svcMachMstrMap : svcMachMstrList) {

                BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK");

                /*************************************************************
                 * 3. Service Machine Master Update API (NSZC0010) for Allocation Off
                 ************************************************************/
                // Create PMsg for Machine Master Allocation Off
                NSZC001001PMsg svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgAlloc(pMsg, svcMachMstrPk, null, false);

                // Service Machine Master Update API(NSZC0010) is executed
                if (!execNSZC001001(svcMachMstrApiPMsg, index)) {

                    return false;
                }

                /*************************************************************
                 * 4. Service Machine Master Update API (NSZC0010) for Decrease
                 ************************************************************/
                if (BigDecimal.ZERO.compareTo((BigDecimal) invtyOrdDtlMap.get("ORD_QTY")) > 0) {

                    // Create PMsg for Machine Master Disposal
                    svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgDspl(pMsg.slsDt.getValue(), svcMachMstrPk, invtyOrdDtlMap);

                    // Service Machine Master Update API(NSZC0010) is executed
                    if (!execNSZC001001(svcMachMstrApiPMsg, index)) {

                        return false;
                    }

                    /*************************************************************
                     * 5. Asset Staging API (NLZC3090)
                     ************************************************************/
                    Map<String, Object> queryParamAsset = new HashMap<String, Object>();
                    queryParamAsset.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                    queryParamAsset.put("svcMachMstrPk", svcMachMstrPk);

                    ArrayList<BigDecimal> dsAsstMstrPkList = (ArrayList<BigDecimal>) ssmBatchClient.queryObjectList("getDsAsstMstrPk", queryParamAsset);

                    if (dsAsstMstrPkList != null && !dsAsstMstrPkList.isEmpty()) {

                        String mdseCd = (String) invtyOrdDtlMap.get("MDSE_CD");

                        // Create PMsg for Asset Staging
                        NLZC309001PMsg assetStgApiPMsg = cratAssetStgApiPMsg(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), mdseCd, svcMachMstrPk);

                        // Asset Staging API(NLZC3090) is executed
                        if (!execNLZC309001(assetStgApiPMsg, index)) {

                            return false;
                        }
                    }

                /*************************************************************
                 * 6. Service Machine Master Update API (NSZC0010) for Increase
                 ************************************************************/
                } else {

                    // Create PMsg for Machine Master Update
                    svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgUpd(pMsg.slsDt.getValue(), svcMachMstrPk, invtyOrdDtlMap);

                    // Service Machine Master Update API(NSZC0010) is executed
                    if (!execNSZC001001(svcMachMstrApiPMsg, index)) {

                        return false;
                    }

                    updSerList.add((String) svcMachMstrMap.get("SER_NUM"));
                }
            }
        }

        int svcMachMstrCratCnt = 0;

        if (invtyOrdSerLst != null && !invtyOrdSerLst.isEmpty()) {

            for (String invtyOrdSer : invtyOrdSerLst) {

                /*************************************************************
                 * 8. Service Machine Master Update API (NSZC0010) for Increase
                 ************************************************************/
                if (BigDecimal.ZERO.compareTo((BigDecimal) invtyOrdDtlMap.get("ORD_QTY")) < 0 && ZYPConstant.FLG_ON_Y.equals((String) invtyOrdDtlMap.get("INSTL_BASE_CTRL_FLG"))) {

                    if (updSerList.isEmpty() || !updSerList.contains(invtyOrdSer)) {

                        // Create PMsg for Machine Master Creation
                        NSZC001001PMsg svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgCrat(pMsg.slsDt.getValue(), invtyOrdDtlMap, invtyOrdSer);

                        // Service Machine Master Update API(NSZC0010) is executed
                        if (!execNSZC001001(svcMachMstrApiPMsg, index)) {

                            return false;
                        }

                        // QC#18002 ADD START
                        svcMachMstrPkList.add(svcMachMstrApiPMsg.svcMachMstrPk.getValue());
                        // QC#18002 ADD END

                    }

                    svcMachMstrCratCnt++;
                }

                /*************************************************************
                 * 9. Serial Transaction API (NLZC3020)
                 ************************************************************/
                NLZC302001PMsg serTrxApiPMsg = cratSerTrxApiPMsg(invtyOrdDtlMap, invtyOrdSer);

                // Serial Transaction API(NLZC3020) is executed
                if (!execNLZC302001(serTrxApiPMsg, index)) {

                    return false;
                }
            }
        }

        /*************************************************************
         * 10. Service Machine Master Update API (NSZC0010) for Increase
         ************************************************************/
        if (BigDecimal.ZERO.compareTo((BigDecimal) invtyOrdDtlMap.get("ORD_QTY")) < 0 && ZYPConstant.FLG_ON_Y.equals((String) invtyOrdDtlMap.get("INSTL_BASE_CTRL_FLG"))) {

            int ordQty = ((BigDecimal) invtyOrdDtlMap.get("ORD_QTY")).intValue();

            if (ordQty > svcMachMstrCratCnt ) {

                for (; svcMachMstrCratCnt < ordQty; svcMachMstrCratCnt++) {

                    // Create PMsg for Machine Master Creation
                    NSZC001001PMsg svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgCrat(pMsg.slsDt.getValue(), invtyOrdDtlMap, null);

                    // Service Machine Master Update API(NSZC0010) is executed
                    if (!execNSZC001001(svcMachMstrApiPMsg, index)) {

                        return false;
                    }

                    // QC#18002 ADD START
                    svcMachMstrPkList.add(svcMachMstrApiPMsg.svcMachMstrPk.getValue());
                    // QC#18002 ADD END

                }
            }
        }

        // QC#18002 ADD START
        boolean assetFlg = checkAsset(pMsg.glblCmpyCd.getValue(), pMsg.invtyLocCd.getValue());

        /*******************************************************************
         * 11. Asset Update API (NLZC3050) for Increase by Manual Entry Mode
         ******************************************************************/
        if (assetFlg && BigDecimal.ZERO.compareTo((BigDecimal) invtyOrdDtlMap.get("ORD_QTY")) < 0) {

            NLZC305001PMsg assetPMsg = new NLZC305001PMsg();
            ZYPEZDItemValueSetter.setValue(assetPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(assetPMsg.slsDt, pMsg.slsDt.getValue());

            int i = 0;
            for (BigDecimal svcMachMstrPk : svcMachMstrPkList) {
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).xxProcMd, NLZC305001Constant.PROC_MODE_ASSET_MAN_ENTRY);
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetTpCd, ASSET_TP.EMSD_ASSET);
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).svcMachMstrPk, svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaCmpyCd, (String) invtyOrdDtlMap.get("COA_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaProdCd, (String) invtyOrdDtlMap.get("COA_PROD_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaAcctCd, (String) invtyOrdDtlMap.get("COA_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaProjCd, (String) invtyOrdDtlMap.get("COA_PROJ_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaBrCd, (String) invtyOrdDtlMap.get("COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaChCd, (String) invtyOrdDtlMap.get("COA_CH_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaCcCd, (String) invtyOrdDtlMap.get("COA_CC_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaAfflCd, (String) invtyOrdDtlMap.get("COA_AFFL_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).coaExtnCd, (String) invtyOrdDtlMap.get("COA_EXTN_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).depcMthNum, "0");
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).curValAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetSrcTpCd, ASSET_SRC_TP.LEASED);
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).totAssetQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaCmpyCd, (String) invtyOrdDtlMap.get("COA_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaProdCd, (String) invtyOrdDtlMap.get("COA_PROD_CD"));
                // assetCoaAcctCd is not set.
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaProjCd, (String) invtyOrdDtlMap.get("COA_PROJ_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaBrCd, (String) invtyOrdDtlMap.get("COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaChCd, (String) invtyOrdDtlMap.get("COA_CH_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaCcCd, (String) invtyOrdDtlMap.get("COA_CC_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaAfflCd, (String) invtyOrdDtlMap.get("COA_AFFL_CD"));
                ZYPEZDItemValueSetter.setValue(assetPMsg.updDtlList.no(i).assetCoaExtnCd, (String) invtyOrdDtlMap.get("COA_EXTN_CD"));
                i++;
            }

            if (0 < i) {
                assetPMsg.updDtlList.setValidCount(i);

                if (!execNLZC305001(assetPMsg, index)) {
                    return false;
                }
            }
        }

        /*******************************************************************
         * 12. Asset Update API (NSZC0010) for Decrease by Disposal Mode
         ******************************************************************/
        if (assetFlg && BigDecimal.ZERO.compareTo((BigDecimal) invtyOrdDtlMap.get("ORD_QTY")) > 0) {

        }
        // QC#18002 ADD END

        return true;
    }

    /**
     * Create & Close Adjustment Order
     * @param pMsgList List<NLZC004001PMsg>
     */
    public void executeCreateAndClose(List<NLZC004001PMsg> pMsgList) {

        if (executeCreate(pMsgList)) {

            for (int i = 0; i < pMsgList.size(); i++) {

                if (NLZC004001Constant.DT_TP_HDR.equals(pMsgList.get(i).xxDtTpCd.getValue())) {

                    executeClose(pMsgList.get(i), i);
                }
            }
        }
    }

    /**
     * Create API PMsg for Adjustment Order Header Creation
     * @param pMsg NLZC004001PMsg
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg cratAdjustmentOrdHdrApiPMsg(NLZC004001PMsg pMsg) {
        // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        NLZC003001PMsg invtyOrdHdrApiPMsg = cratNLZC003001HdrApiCommonPMsg(pMsg);
        // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        return invtyOrdHdrApiPMsg;
    }

    /**
     * Create API PMsg for Adjustment Order Detail Creation
     * @param pMsg NLZC004001PMsg
     * @param mdseInfoMap Map<String, Object>
     * @param index int
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg cratAdjustmentOrdDtlApiPMsg(NLZC004001PMsg pMsg, int index) {

        NLZC003001PMsg invtyOrdDtlApiPMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdTpCd, pMsg.invtyOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdLineNum, pMsg.invtyOrdLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.mdseCd, pMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.stkStsCd, pMsg.stkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyLocCd, pMsg.invtyLocCd_D1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyLocCd_D1, pMsg.invtyLocCd_D1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.locStsCd_D1, pMsg.locStsCd_D1.getValue());
        // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.FINALIZED);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.ordQty, pMsg.ordQty.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.adjCatgCd, pMsg.adjCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdLineCmntTxt, pMsg.invtyOrdLineCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdLineCostAmt, pMsg.invtyOrdLineCostAmt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.adjAcctAliasNm, pMsg.adjAcctAliasNm.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaCmpyCd, pMsg.coaCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaBrCd, pMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaAcctCd, pMsg.coaAcctCd.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaProdCd, pMsg.coaProdCd.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaChCd, pMsg.coaChCd.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaCcCd, pMsg.coaCcCd.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaAfflCd, pMsg.coaAfflCd.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaExtnCd, pMsg.coaExtnCd.getValue());
//        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaProjCd, pMsg.coaProjCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.adjTrxTpCd, adjTrxTpTMsg.adjTrxTpCd.getValue());
        // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk.getValue());

        procIBMdse(invtyOrdDtlApiPMsg, pMsg, index);

        return invtyOrdDtlApiPMsg;
    }

    /**
     * Check Service Machine Master is available for Allocation
     * @param pMsg NLZC004001PMsg
     * @param serialInfo NLZC004001_serialInfoListPMsg
     * @param index int
     * @return ArrayList<Map<String, Object>>
     */
    private boolean isChkAllocMachMstr(NLZC004001PMsg pMsg, NLZC004001_serialInfoListPMsg serialInfo, int index) {

        boolean isMachMstrAlloc = true;

        BigDecimal ordQty = null;
        if (ADJ_TRX_TP.ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            ordQty = pMsg.ordQty.getValue();
        } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            ordQty = pMsg.ordQty.getValue().negate();
        }

        // Inventory Increase
        if (BigDecimal.ZERO.compareTo(ordQty) < 0) {

            Map<String, Object> svcConfigMainMachInfo = new HashMap<String, Object>();
            ArrayList<Map<String, Object>> svcMachMstrInfoList = new ArrayList<Map<String, Object>>();

            // Config Check
            if (ZYPCommonFunc.hasValue(pMsg.svcConfigMstrPk)) {

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                queryParam.put("svcConfigMstrPk", pMsg.svcConfigMstrPk.getValue());

                svcConfigMainMachInfo = (Map<String, Object>) ssmBatchClient.queryObject("getSvcConfigMainMachInfo", queryParam);

                if (svcConfigMainMachInfo != null) {

                    // Allocated by other order
                    if (!isMachMstrAllocChk((String) svcConfigMainMachInfo.get("TRX_HDR_NUM"), invtyOrdNum)
                            || !ZYPConstant.FLG_ON_Y.equals((String) svcConfigMainMachInfo.get("SVC_MACH_MAINT_AVAL_FLG"))) {

                        setMsg(index, NLZC004001Constant.NLZM2424W);
                        isMachMstrAlloc = false;
                    }

                    // Not Active Status
                    if (SVC_MACH_MSTR_STS.TERMINATED.equals((String) svcConfigMainMachInfo.get("SVC_MACH_MSTR_STS_CD"))) {

                        setMsg(index, NLZC004001Constant.NLZM2427W);
                        isMachMstrAlloc = false;
                    }

                    // Different Location
                    if (!isMachMstrAllocChk((String) svcConfigMainMachInfo.get("CUR_LOC_NUM"), pMsg.invtyLocCd_D1.getValue())) {

                        setMsg(index, NLZC004001Constant.NLZM2425W);
                        isMachMstrAlloc = false;
                    }

                    // Different Location Status
                    if (!isMachMstrAllocChk((String) svcConfigMainMachInfo.get("SVC_MACH_MSTR_LOC_STS_CD"), pMsg.locStsCd_D1.getValue())) {

                        setMsg(index, NLZC004001Constant.NLZM2426W);
                        isMachMstrAlloc = false;
                    }

                } else {

                    setMsg(index, NLZC004001Constant.NLZM2423W);
                    isMachMstrAlloc = false;
                }
            }

            if (isMachMstrAlloc && serialInfo != null) {

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                queryParam.put("serNum", serialInfo.serNum.getValue());
                queryParam.put("mdseCd", pMsg.mdseCd.getValue());

                svcMachMstrInfoList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getAllocMachMstrInfoList", queryParam);

                if (svcMachMstrInfoList != null && !svcMachMstrInfoList.isEmpty()) {

                    for (Map<String, Object> svcMachMstrInfo : svcMachMstrInfoList) {

                        // Not Terminated
                        if (!SVC_MACH_MSTR_STS.TERMINATED.equals((String) svcMachMstrInfo.get("SVC_MACH_MSTR_STS_CD"))) {

                            setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2408W, index);
                            isMachMstrAlloc = false;
                        }
                    }
                }
            }

            if (isMachMstrAlloc) {

                if (svcMachMstrInfoList != null && !svcMachMstrInfoList.isEmpty()) {

                    svcMachMstrInfoList.get(0).put("MSG_INDEX", new BigDecimal(index));
                    allcSvcMachMstrList.add(svcMachMstrInfoList.get(0));
                }
            }

        // Inventory Decrease
        } else {

            if (serialInfo != null) {

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                queryParam.put("serNum", serialInfo.serNum.getValue());
                queryParam.put("mdseCd", pMsg.mdseCd.getValue());
                queryParam.put("svcMachMstrStsTerm", SVC_MACH_MSTR_STS.TERMINATED);

                ArrayList<Map<String, Object>> svcMachMstrInfoList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getAllocMachMstrInfoList", queryParam);

                if (svcMachMstrInfoList != null && !svcMachMstrInfoList.isEmpty() && svcMachMstrInfoList.size() > 1) {

                    svcMachMstrInfoList.clear();

                    queryParam.put("curLocNum", pMsg.invtyLocCd_D1.getValue());
                    queryParam.put("stkStsCd", pMsg.stkStsCd.getValue());
                    queryParam.put("locStsCd", pMsg.locStsCd_D1.getValue());

                    svcMachMstrInfoList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getAllocMachMstrInfoList", queryParam);
                }

                if (svcMachMstrInfoList != null && !svcMachMstrInfoList.isEmpty() && svcMachMstrInfoList.size() > 1) {

                    setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2428E, NLZC004001Constant.NLZM2428W, index);
                    isMachMstrAlloc = false;

                } else if (svcMachMstrInfoList != null && !svcMachMstrInfoList.isEmpty()) {

                    if (isChkMachMstrInfoForDecr(pMsg, serialInfo, svcMachMstrInfoList.get(0), index)) {

                        svcMachMstrInfoList.get(0).put("MSG_INDEX", new BigDecimal(index));
                        allcSvcMachMstrList.add(svcMachMstrInfoList.get(0));

                    } else {

                        isMachMstrAlloc = false;
                    }

                } else {

                    setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2429E, NLZC004001Constant.NLZM2429W, index);
                    isMachMstrAlloc = false;
                }

            } else {
                // START 2021/10/29 R.Azucena[QC#58899, ADD]
                String key = S21StringUtil.concatStrings(pMsg.glblCmpyCd.getValue(),
                        NLZC004001Constant.COMMA_SEP, pMsg.svcConfigMstrPk.getValue(),
                        NLZC004001Constant.COMMA_SEP, pMsg.mdseCd.getValue(),
                        NLZC004001Constant.COMMA_SEP, pMsg.stkStsCd.getValue(),
                        NLZC004001Constant.COMMA_SEP, pMsg.locStsCd.getValue(),
                        NLZC004001Constant.COMMA_SEP, pMsg.invtyLocCd.getValue());
                ArrayList<BigDecimal> alTrmnList = svcMachMstrPkTrmnMapList.get(key);
                if (alTrmnList == null) {
                    alTrmnList = new ArrayList<BigDecimal>();
                    svcMachMstrPkTrmnMapList.put(key, alTrmnList);
                }
                // END 2021/10/29 R.Azucena[QC#58899, ADD]

                ArrayList<String> svcMachMsterStsList = new ArrayList<String>();
                svcMachMsterStsList.add(SVC_MACH_MSTR_STS.CREATED);
                svcMachMsterStsList.add(SVC_MACH_MSTR_STS.REMOVED);

                // Get Machine Master Data
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                queryParam.put("svcConfigMstrPk", pMsg.svcConfigMstrPk.getValue());
                queryParam.put("mdseCd", pMsg.mdseCd.getValue());
                queryParam.put("stkStsCd", pMsg.stkStsCd.getValue());
                // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
//                queryParam.put("locStsCd", pMsg.locStsCd_D1.getValue());
//                queryParam.put("curLocNum", pMsg.invtyLocCd_D1.getValue());
                queryParam.put("locStsCd", pMsg.locStsCd.getValue());
                queryParam.put("curLocNum", pMsg.invtyLocCd.getValue());
                queryParam.put("svcMachMsterStsList", svcMachMsterStsList);
                queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
                if (!ZYPCommonFunc.hasValue(pMsg.svcConfigMstrPk.getValue())) {
                    // START 2021/10/29 R.Azucena[QC#58899, MOD]
                    // queryParam.put("rownum", pMsg.ordQty.getValue().abs());
                    queryParam.put("rownum", pMsg.ordQty.getValue().abs().add(BigDecimal.valueOf(alTrmnList.size())));
                    // END 2021/10/29 R.Azucena[QC#58899, MOD]
                }
                queryParam.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
                // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

                ArrayList<Map<String, Object>> svcMachMstrInfoList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getAllocMachMstrInfoList", queryParam);

                if (svcMachMstrInfoList != null && !svcMachMstrInfoList.isEmpty()) {
                    // START 2021/10/29 R.Azucena[QC#58899, MOD]
                    // if (svcMachMstrInfoList.size() < pMsg.ordQty.getValue().abs().intValue()) {
                    int iExpectedSize = pMsg.ordQty.getValue().abs().intValue();
                    if (!ZYPCommonFunc.hasValue(pMsg.svcConfigMstrPk)) {
                        iExpectedSize += alTrmnList.size();
                    }
                    if (svcMachMstrInfoList.size() < iExpectedSize) {
                    // END 2021/10/29 R.Azucena[QC#58899, MOD]
                        setMsg(index, NLZC004001Constant.NLZM2430E, NLZC004001Constant.NLZM2430W);
                        isMachMstrAlloc = false;
                    }

                    if (isMachMstrAlloc || !ZYPCommonFunc.hasValue(pMsg.svcConfigMstrPk)) {

                        // QC#55670 Mod Start
                        int cnt = 0;
                        for (Map<String, Object> svcMachMstrInfo : svcMachMstrInfoList) {
                            // START 2021/10/29 R.Azucena[QC#58899, ADD]
                            if (alTrmnList.contains((BigDecimal) svcMachMstrInfo.get("SVC_MACH_MSTR_PK"))) {
                                continue;
                            }
                            // END 2021/10/29 R.Azucena[QC#58899, ADD]

                            if(ordQty.abs().intValue() == cnt) {
                                break;
                            }
                            cnt++;
                            // QC#55670 Mod End

                            svcMachMstrInfo.put("MSG_INDEX", new BigDecimal(index));
                            allcSvcMachMstrList.add(svcMachMstrInfo);
                            // START 2021/10/29 R.Azucena[QC#58899, ADD]
                            alTrmnList.add((BigDecimal) svcMachMstrInfo.get("SVC_MACH_MSTR_PK"));
                            // END 2021/10/29 R.Azucena[QC#58899, ADD]
                        }
                    }

                } else {

                    setMsg(index, NLZC004001Constant.NLZM2431E, NLZC004001Constant.NLZM2431W);
                    isMachMstrAlloc = false;
                }
            }
        }

        return isMachMstrAlloc;
    }

    /**
     * Check Machine Master for Decrease
     * @param pMsg NLZC004001PMsg
     * @param serialInfo NLZC004001_serialInfoListPMsg
     * @param svcMachMstrInfo Map<String, Object>
     * @param index int
     * @return boolean
     */
    private boolean isChkMachMstrInfoForDecr(NLZC004001PMsg pMsg, NLZC004001_serialInfoListPMsg serialInfo, Map<String, Object> svcMachMstrInfo, int index) {

        boolean isMachMstrAlloc = true;

        // Allocated by other order
        if (!isMachMstrAllocChk((String) svcMachMstrInfo.get("TRX_HDR_NUM"), invtyOrdNum) || !ZYPConstant.FLG_ON_Y.equals((String) svcMachMstrInfo.get("SVC_MACH_MAINT_AVAL_FLG"))) {

            setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2409E, NLZC004001Constant.NLZM2409W, index);
            isMachMstrAlloc = false;
        }

        // Customer Location
        if (!SVC_MACH_MSTR_STS.CREATED.equals((String) svcMachMstrInfo.get("SVC_MACH_MSTR_STS_CD")) && !SVC_MACH_MSTR_STS.REMOVED.equals((String) svcMachMstrInfo.get("SVC_MACH_MSTR_STS_CD"))) {

            setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2410E, NLZC004001Constant.NLZM2410W, index);
            isMachMstrAlloc = false;
        }

        // Different Configuration
        if (ZYPCommonFunc.hasValue(pMsg.svcConfigMstrPk)) {

            if (!isSameVal(pMsg.svcConfigMstrPk.getValue(), (BigDecimal) svcMachMstrInfo.get("SVC_CONFIG_MSTR_PK"))) {

                if (ZYPCommonFunc.hasValue((BigDecimal) svcMachMstrInfo.get("SVC_CONFIG_MSTR_PK"))) {

                    setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2411E, NLZC004001Constant.NLZM2411W, index);
                    isMachMstrAlloc = false;

                } else {

                    setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2452E, NLZC004001Constant.NLZM2452W, index);
                    isMachMstrAlloc = false;
                }
            }
        }

        // Main Machine of Configuration
//        if (ZYPCommonFunc.hasValue((BigDecimal) svcMachMstrInfo.get("SVC_CONFIG_MSTR_PK")) && SVC_MACH_TP.MACHINE.equals((String) svcMachMstrInfo.get("SVC_MACH_TP_CD"))) {
//
//            // Check Component Count
//            Map<String, Object> queryParam = new HashMap<String, Object>();
//            queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//            queryParam.put("svcConfigMstrPk", (BigDecimal) svcMachMstrInfo.get("SVC_CONFIG_MSTR_PK"));
//            queryParam.put("svcMachMstrStsTerm", SVC_MACH_MSTR_STS.TERMINATED);
//
//            BigDecimal configCompCnt = (BigDecimal) ssmBatchClient.queryObject("getConfigCompCnt", queryParam);
//
//            if (ZYPCommonFunc.hasValue(configCompCnt) && BigDecimal.ONE.compareTo(configCompCnt) < 0) {
//
//                setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2412E, NLZC004001Constant.NLZM2412W, index);
//                isMachMstrAlloc = false;
//            }
//        }

        // Different Location
//        if (!isMachMstrAllocChk((String) svcMachMstrInfo.get("CUR_LOC_NUM"), pMsg.invtyLocCd_D1.getValue())) {
//
//            setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLBM1337E, NLZC004001Constant.NLBM1337W, index);
//            isMachMstrAlloc = false;
//        }

        // Different Location Status
        if (!isMachMstrAllocChk((String) svcMachMstrInfo.get("SVC_MACH_MSTR_LOC_STS_CD"), pMsg.locStsCd_D1.getValue())) {

            setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2413E, NLZC004001Constant.NLZM2413W, index);
            isMachMstrAlloc = false;
        }

        // Different Stock Status
        if (!isMachMstrAllocChk((String) svcMachMstrInfo.get("STK_STS_CD"), pMsg.stkStsCd.getValue())) {

            setMsgForChkMachMstr(serialInfo, NLZC004001Constant.NLZM2414E, NLZC004001Constant.NLZM2414W, index);
            isMachMstrAlloc = false;
        }

        return isMachMstrAlloc;
    }

    // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
    /**
     * Create API PMsg for Inventory Order Header Close / Cancel
     * @param pMsg NLZC004001PMsg
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg cratInvtyOrdHdrApiPMsgCloCancHdr(NLZC004001PMsg pMsg, String xxProcTpCd, String invtyOrdStsCd) {

        NLZC003001PMsg invtyOrdHdrApiPMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxProcTpCd, xxProcTpCd);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdNum, pMsg.invtyOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdTpCd, pMsg.invtyOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdStsCd, invtyOrdStsCd);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.adjTrxTpCd, adjTrxTpTMsg.adjTrxTpCd);

        if (ZYPCommonFunc.hasValue(pMsg.firstInvtyOrdCmntTxt)) {

            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.firstInvtyOrdCmntTxt, pMsg.firstInvtyOrdCmntTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.scdInvtyOrdCmntTxt)) {

            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.scdInvtyOrdCmntTxt, pMsg.scdInvtyOrdCmntTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.thirdInvtyOrdCmntTxt)) {

            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.thirdInvtyOrdCmntTxt, pMsg.thirdInvtyOrdCmntTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.frthInvtyOrdCmntTxt)) {

            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.frthInvtyOrdCmntTxt, pMsg.frthInvtyOrdCmntTxt.getValue());
        }

        return invtyOrdHdrApiPMsg;
    }
    // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

    /**
     * Create API PMsg for Inventory Allocation
     * @param pMsg NLZC004001PMsg
     * @return NLZC001001PMsg
     */
    private NLZC001001PMsg cratInvtyAllocApiPMsg(String glblCmpyCd, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd, BigDecimal ordQty) {

        NLZC001001PMsg invtyAllocApiPMsg = new NLZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(invtyAllocApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invtyAllocApiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(invtyAllocApiPMsg.invtyLocCd, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(invtyAllocApiPMsg.locStsCd, locStsCd);
        ZYPEZDItemValueSetter.setValue(invtyAllocApiPMsg.stkStsCd, stkStsCd);
        ZYPEZDItemValueSetter.setValue(invtyAllocApiPMsg.xxRqstQty, ordQty.abs());
        ZYPEZDItemValueSetter.setValue(invtyAllocApiPMsg.xxAllocOpt, NLZC001001.OPT_CHK_FREE);

        return invtyAllocApiPMsg;
    }

    /**
     * Create API PMsg for Inventory Update
     * @param pMsg NLZC004001PMsg
     * @param invtyOrdDtlMap Map<String, Object>
     * @param invtyOrdSerLst ArrayList<String
     * @return NLZC002001PMsg
     */
    private NLZC002001PMsg cratInvtyUpdApiPMsg(NLZC004001PMsg pMsg, Map<String, Object> invtyOrdDtlMap, ArrayList<String> invtyOrdSerLst) {

        NLZC002001PMsg invtyUpdApiPMsg = new NLZC002001PMsg();

        BigDecimal ordQty = (BigDecimal) invtyOrdDtlMap.get("ORD_QTY");

        if (BigDecimal.ZERO.compareTo(ordQty) < 0) {

            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstQty, ordQty);

        } else {

            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstQty, ordQty.abs());
        }

        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.trxCd, (String) invtyOrdDtlMap.get("TRX_CD"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.trxRsnCd, (String) invtyOrdDtlMap.get("TRX_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.mdseCd, (String) invtyOrdDtlMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyLocCd, (String) invtyOrdDtlMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.locStsCd, (String) invtyOrdDtlMap.get("LOC_STS_CD"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.stkStsCd, (String) invtyOrdDtlMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyTrxDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxSysTp, NLZC002001.SYS_TP_INVTY);
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.sysSrcCd, pMsg.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyOrdNum, (String) invtyOrdDtlMap.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyOrdLineNum, (String) invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.ccyCd, pMsg.ccyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.uomCd, PKG_UOM.EACH);

        if (invtyOrdSerLst != null && !invtyOrdSerLst.isEmpty()) {

            for (int i = 0; i < invtyOrdSerLst.size(); i++) {

                ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.serNumList.no(i).serNum, invtyOrdSerLst.get(i));
            }

            invtyUpdApiPMsg.serNumList.setValidCount(invtyOrdSerLst.size());
        }

        return invtyUpdApiPMsg;
    }

    /**
     * Create API PMsg for Machine Master Allocation On/Off
     * @param pMsg NLZC004001PMsg
     * @param svcMachMstrPk BigDecimal
     * @param trxHdrNum String
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg cratSvcMachMstrApiPMsgAlloc(NLZC004001PMsg pMsg, BigDecimal svcMachMstrPk, String trxHdrNum, boolean allocOn) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, svcMachMstrPk);

        if (allocOn) {

            ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
            ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, trxHdrNum);
            ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, pMsg.invtyOrdLineNum.getValue());

        } else {

            ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
        }

        return svcMachMstrApiPMsg;
    }

    /**
     * Create API PMsg for Machine Master (Create)
     * @param slsDt String
     * @param invtyOrdDtlMap Map<String, Object>
     * @param serNum String
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg cratSvcMachMstrApiPMsgCrat(String slsDt, Map<String, Object> invtyOrdDtlMap, String serNum) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, (String) invtyOrdDtlMap.get("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcConfigMstrPk, (BigDecimal) invtyOrdDtlMap.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.mdseCd, (String) invtyOrdDtlMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, (String) invtyOrdDtlMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.effFromDt, slsDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrLocStsCd, (String) invtyOrdDtlMap.get("LOC_STS_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, (String) invtyOrdDtlMap.get("INVTY_LOC_CD"));

        return svcMachMstrApiPMsg;
    }

    /**
     * Create API PMsg for Machine Master (Disposal)
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @param invtyOrdDtlMap Map<String, Object>
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg cratSvcMachMstrApiPMsgDspl(String slsDt, BigDecimal svcMachMstrPk, Map<String, Object> invtyOrdDtlMap) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, (String) invtyOrdDtlMap.get("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.DISPOSAL.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.effThruDt, slsDt);
        // START 2023/09/22 T.Kuroda [QC#61265, MOD]
        //ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
        // END   2022/09/22 T.Kuroda [QC#61265, MOD]

        return svcMachMstrApiPMsg;
    }

    /**
     * Create API PMsg for Machine Master (Update)
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @param invtyOrdDtlMap Map<String, Object>
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg cratSvcMachMstrApiPMsgUpd(String slsDt, BigDecimal svcMachMstrPk, Map<String, Object> invtyOrdDtlMap) {

        // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
        NSZC001001PMsg svcMachMstrApiPMsg = cratSvcMachMstrApiCommonPMsgUpd(slsDt, svcMachMstrPk, invtyOrdDtlMap, ProcessMode.UPDATE_WAREHOUSE.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcConfigMstrPk, (BigDecimal) invtyOrdDtlMap.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.mdseCd, (String) invtyOrdDtlMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, (String) invtyOrdDtlMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.effFromDt, slsDt);
        // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

        return svcMachMstrApiPMsg;
    }

    /**
     * Create API PMsg for Asset Master (Disposal)
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mdseCd String
     * @param svcMachMstrPk BigDecimal
     * @return NLZC309001PMsg
     */
    private NLZC309001PMsg cratAssetStgApiPMsg(String glblCmpyCd, String slsDt, String mdseCd, BigDecimal svcMachMstrPk) {

        NLZC309001PMsg assetStgnApiPMsg = new NLZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.procModeCd, NLZC309001Constant.PROC_MODE_ASSET_ADJ_OR_DISPOSAL);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.svcMachMstrPk, svcMachMstrPk);
        return assetStgnApiPMsg;
    }

    /**
     * Create API PMsg for Serial Transaction
     * @param invtyOrdDtlMap Map<String, Object>
     * @param serNum String
     * @return NLZC302001PMsg
     */
    private NLZC302001PMsg cratSerTrxApiPMsg(Map<String, Object> invtyOrdDtlMap, String serNum) {

        NLZC302001PMsg serTrxApiPMsg = new NLZC302001PMsg();

        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.glblCmpyCd, (String) invtyOrdDtlMap.get("GLBL_CMPY_CD"));

        BigDecimal ordQty = (BigDecimal) invtyOrdDtlMap.get("ORD_QTY");

        if (BigDecimal.ZERO.compareTo(ordQty) < 0) {

            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.ADJUSTMENT_IN);

        } else {

            ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.ADJUSTMENT_OUT);
        }

        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).serNum, serNum);
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).mdseCd, (String) invtyOrdDtlMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).serTrxTs, ZYPDateUtil.getCurrentSystemTime(NLZC004001Constant.TIMESTAMPFORMAT_MS));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).serTrxSrcTpCd, SER_TRX_SRC_TP.INVENTORY_ORDER);
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).serTrxSrcHdrNum, (String) invtyOrdDtlMap.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).serTrxSrcLineNum,  (String) invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).fromLocCd, (String) invtyOrdDtlMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).toLocCd, (String) invtyOrdDtlMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).manCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).toStkStsCd, (String) invtyOrdDtlMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).fromStkStsCd, (String) invtyOrdDtlMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApiPMsg.UpdateDetailList.no(0).locStsCd, (String) invtyOrdDtlMap.get("LOC_STS_CD"));

        serTrxApiPMsg.UpdateDetailList.setValidCount(1);
        return serTrxApiPMsg;
    }

    /**
     * Inventory Order API Result Check for Create Mode
     * @param pMsgList List<NLZC004001PMsg>
     * @param invtyOrdApiPMsgArray List<NLZC003001PMsg>
     * @return boolean
     */
    private boolean isInvtyOrdApiResult(List<NLZC004001PMsg> pMsgList, List<NLZC003001PMsg> invtyOrdApiPMsgArray) {

        boolean isInvtyOrdApiCratResult = true;

        for (int i = 0; i < invtyOrdApiPMsgArray.size(); i++) {

            NLZC003001PMsg invtyOrdApiPMsg = invtyOrdApiPMsgArray.get(i);
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(invtyOrdApiPMsg);

            if (!msgIdList.isEmpty()) {

                int pMsgLineNum = 0;

                for (; pMsgLineNum < pMsgList.size(); pMsgLineNum++) {

                    NLZC004001PMsg pMsg = pMsgList.get(pMsgLineNum);

                    if (i == 0 && NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue())) {

                        break;

                    } else if (i != 0 && invtyOrdApiPMsg.invtyOrdLineNum.getValue().equals(pMsg.invtyOrdLineNum.getValue())) {

                        break;
                    }
                }

                for (String msgId : msgIdList) {

                    if (ZYPCommonFunc.hasValue(msgId)) {

                        setMsg(pMsgLineNum, msgId);

                        if (msgId.endsWith("E")) {

                            isInvtyOrdApiCratResult = false;
                        }
                    }
                }
            }
        }

        return isInvtyOrdApiCratResult;
    }

    // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
    /**
     *  API Result Check
     * @param apiPMsg NLZC003001PMsg
     * @param index int
     * @return boolean
     */
    private boolean isApiResult(List<? extends EZDPMsg> apiPMsgList, int index) {

        boolean isApiResult = true;

        for (EZDPMsg apiPMsg : apiPMsgList) {
            if (!isApiResult(apiPMsg, index)) {
                isApiResult = false;
            }
        }

        return isApiResult;
    }

    /**
     *  API Result Check
     * @param apiPMsg EZDPMsg
     * @param index int
     * @return boolean
     */
    private boolean isApiResult(EZDPMsg apiPMsg, int index) {

        boolean isApiResult = true;

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
        if (!msgIdList.isEmpty()) {
            for (String msgId : msgIdList) {
                if (ZYPCommonFunc.hasValue(msgId)) {
                    setMsg(index, msgId);
                    if (msgId.endsWith("E")) {
                        isApiResult = false;
                    }
                }
            }
        }

        return isApiResult;
    }
    // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

    /**
     * Set Machine Master Check Result Message
     * @param serialInfo NLZC004001_serialInfoListPMsg
     * @param msgId String
     * @param index int
     */
    private void setMsgForChkMachMstr(NLZC004001_serialInfoListPMsg serialInfo, String errMsgId, String warningMsgId, int index) {
        String msgId = warningMsgId;

        if (NLZC004001Constant.MODE_NO_WARNING.equals(warningModeCd)) {
            msgId = errMsgId;
        }
        setMsgForChkMachMstr(serialInfo, msgId, index);
    }

    /**
     * Set Machine Master Check Result Message
     * @param serialInfo NLZC004001_serialInfoListPMsg
     * @param msgId String
     * @param index int
     */
    private void setMsgForChkMachMstr(NLZC004001_serialInfoListPMsg serialInfo, String msgId, int index) {

        if (serialInfo != null) {
            ZYPEZDItemValueSetter.setValue(serialInfo.xxMsgId, msgId);
        }
        setMsg(index, msgId);
    }

    /**
     * Set Message
     * @param index int
     * @param errMsg String
     */
    private void setMsg(int index, String errMsgId, String warningMsgId) {
        String msgId = warningMsgId;

        if (NLZC004001Constant.MODE_NO_WARNING.equals(warningModeCd)) {
            msgId = errMsgId;
        }
        setMsg(index, msgId);
    }

    /**
     * Set Message
     * @param index int
     * @param errMsg String
     */
    private void setMsg(int index, String errMsg) {
        // START 04/28/2020 [QC#56461,MOD]
        setMsgParam(index, errMsg, null);
//
//        ArrayList<String> msgList = new ArrayList<String>();
//
//        if (msgListMap.get(new BigDecimal(index)) == null) {
//
//            msgList.add(errMsg);
//            msgListMap.put(new BigDecimal(index), msgList);
//
//        } else {
//
//            msgList = msgListMap.get(new BigDecimal(index));
//            msgList.add(errMsg);
//            msgListMap.put(new BigDecimal(index), msgList);
//        }
//
//        S21InfoLogOutput.println(errMsg);
        // END 04/28/2020 [QC#56461,MOD]
    }

    // START 04/28/2020 [QC#56461,ADD]
    /**
     * Set Message
     * @param index int
     * @param errMsg String
     * @param param String[]
     */
    private void setMsgParam(int index, String errMsg, String[] param) {

        ArrayList<String> msgList = new ArrayList<String>();
        ArrayList<String[]> msgPrmList = new ArrayList<String[]>();

        if (msgListMap.get(new BigDecimal(index)) == null) {

            msgList.add(errMsg);
            msgPrmList.add(param);
            msgListMap.put(new BigDecimal(index), msgList);
            msgListParam.put(new BigDecimal(index), msgPrmList);

        } else {

            msgList = msgListMap.get(new BigDecimal(index));
            msgPrmList = msgListParam.get(new BigDecimal(index));
            msgList.add(errMsg);
            msgPrmList.add(param);
            msgListMap.put(new BigDecimal(index), msgList);
            msgListParam.put(new BigDecimal(index), msgPrmList);
        }

        S21InfoLogOutput.println(errMsg);
    }
    // END 04/28/2020 [QC#56461,ADD]

    /** QC#18002 ADD
     * Check Asset By Inventory Location (Retail WH)
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return boolean
     */
    private static boolean checkAsset(String glblCmpyCd, String invtyLocCd) {

        // Check Asset Location
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rtlWhCd", invtyLocCd);
        queryParam.put("invtyAcctCd", INVTY_ACCT.ASSET);

        if (BigDecimal.ZERO.compareTo((BigDecimal) ssmBatchClient.queryObject("checkAsset", queryParam)) == 0) {
             return false;
        }

        return true;
    }

    // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
    /**
     * Check Request Parameter (Cancel)
     * @param pMsg NLZC004001PMsg
     * @param index int
     * @return boolean Check Result
     */
    private boolean chkReqParamsForCanc(NLZC004001PMsg pMsg, int index) {
        return chkReqParamsForClo(pMsg, index);
    }

    /**
     * Create Adjustment Order
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeCreate4Adj(List<NLZC004001PMsg> pMsgList) {
        // Create Inventory Order
        if (!executeCreateAdjustmentOrd(pMsgList)) {

            return false;
        }

        // Inventory Allocation
        if (!executeInvtyAllocation(pMsgList, NLZC001001.PROC_TP_ALLOC)) {

            return false;
        }

        // Machine Master Allocation
        if (!executeMachMstrAllocation(pMsgList)) {

            return false;
        }

        // Set Inventory Order Number for output
        for (NLZC004001PMsg pMsg : pMsgList) {

            ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdNum, invtyOrdNum);
        }

        return true;
    }

    /**
     * Create Sub Warehouse Transfer Order
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeCreate4SWT(List<NLZC004001PMsg> pMsgList) {
        // Create Sub Warehouse Transfer Order
        if (!executeCreateSWTOrd(pMsgList)) {
            return false;
        }

        // Allocation For Non CPO
        if (!executeAllocationForNonCPO(pMsgList)) {
            return false;
        }

        // Machine Master Allocation
        if (!executeMachMstrAllocation(pMsgList)) {
            return false;
        }

        // Set Inventory Order Number for output
        for (NLZC004001PMsg pMsg : pMsgList) {
            ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdNum, invtyOrdNum);
        }

        return true;
    }

    /**
     * Close Adjustment Order
     * @param pMsg NLZC004001PMsg
     * @param index int
     */
    private void executeClose4Adj(NLZC004001PMsg pMsg, int index) {
        /*************************************************************
         * 2. Inventory Order API (NLZC0030)
         ************************************************************/
        NLZC003001PMsg invtyOrdApiPMsg = cratInvtyOrdHdrApiPMsgCloCancHdr(pMsg, NLZC003001.PROC_TP_CLO, INVTY_ORD_STS.CLOSED);
        if (!execNLZC003001(invtyOrdApiPMsg, index)) {
            return;
        }

        /*************************************************************
         * 3. Get Currency Code
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(pMsg.ccyCd)) {

            GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            glblCmpyTMsg = (GLBL_CMPYTMsg) S21ApiTBLAccessor.findByKey(glblCmpyTMsg);

            if (glblCmpyTMsg != null) {

                ZYPEZDItemValueSetter.setValue(pMsg.ccyCd, glblCmpyTMsg.stdCcyCd.getValue());

            } else {

                setMsg(index, NLZC004001Constant.NLZM0095E);
                return;
            }
        }

        /*************************************************************
         * 4. Get & Process Inventory Order Detail
         ************************************************************/
        ArrayList<Map<String, Object>> invtyOrdDtlLst = getInvtyOrdDtlAndChk(pMsg, index);
        for (Map<String, Object> invtyOrdDtlMap : invtyOrdDtlLst) {
            if(!executeDtlClose4Adj(pMsg, invtyOrdDtlMap, index)) {
                return;
            }
        }
    }

    /**
     * Close Sub Warehouse Transfer Order
     * @param pMsg
     * @param index
     */
    private void executeClose4SWT(NLZC004001PMsg pMsg, int index) {
        /*************************************************************
         * 2. Inventory Order API (NLZC0030)
         ************************************************************/
        NLZC003001PMsg invtyOrdApiPMsg = cratSWTOrdHdrApiPMsgUpdateHdr(getInvtyOrd(pMsg));
        if (!execNLZC003001(invtyOrdApiPMsg, index)) {
            return;
        }

        /*************************************************************
         * 4. Get & Process Inventory Order Detail
         ************************************************************/
        ArrayList<Map<String, Object>> invtyOrdDtlLst = getInvtyOrdDtlAndChk(pMsg, index);
        List<NLZC003001PMsg> invtyOrdApiPMsgList = cratSWTOrdHdrApiPMsgUpdateDtl(invtyOrdDtlLst);
        if (!execNLZC003001(invtyOrdApiPMsgList, index)) {
            return;
        }
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();

        // Call Shipping Plan Update API
        List<NWZC003001PMsg> shippingPlanUpdateApiPMsgList = cratShippingPlanUpdatePMsg(pMsg, invtyOrdDtlLst);
        if (!execNWZC003001(shippingPlanUpdateApiPMsgList, index)) {
            return;
        }

        // Call SO Update API
        List<NLZC205001PMsg> soApiPMsgList = cratSoApiPMsg(pMsg, invtyOrdDtlLst);
        if (!execNLZC205001(soApiPMsgList, index)) {
            return;
        }

        // Call RWS Update API
        NLZC200001PMsg rwsApiPMsg = cratRwsApiPMsg(soApiPMsgList.get(0));
        if (!execNLZC200001(rwsApiPMsg, index)) {
            return;
        }

        // Call RWS Serial Update API
        NLZC304001PMsg rwsSerApiPMsg = cratRwsSerApiPMsg(glblCmpyCd, rwsApiPMsg, invtyOrdDtlLst);
        if (rwsSerApiPMsg.SerialList.getValidCount() > 0) {
            if (!execNLZC304001(rwsSerApiPMsg, index)) {
                return;
            }
        }

        if (isAutoTransRetailWh(glblCmpyCd, invtyOrdDtlLst.get(0).get("FROM_RTL_WH_CD").toString())) {
            // Call RWS PutAway For S21DC API
            RWS_HDRTMsg rwsHdrTMsg = getRwsHdr(glblCmpyCd, rwsApiPMsg.RWSNumList.no(0).rwsNum.getValue());
            NLZC206001PMsg putAwayS21DcApiPMsg = cratRwsPutAwayApiPMsg(glblCmpyCd, slsDt, rwsHdrTMsg);
            if (!execNLZC206001(putAwayS21DcApiPMsg, index)) {
                return;
            }

            // Call RWS Completion For S21DC API
            NLZC207001PMsg rwsCpltApiPMsg = cratRwsCompletionApiPMsg(glblCmpyCd, slsDt, rwsHdrTMsg);
            if (!execNLZC207001(rwsCpltApiPMsg, index)) {
                return;
            }

            // Call Service Machine Master API For Update
            List<NSZC001001PMsg> svcMachMstrApiPMsgList = cratSvcMachMstrApiPMsgUpdForSWT(pMsg, invtyOrdDtlLst);
            if (svcMachMstrApiPMsgList.size() > 0) {
                if (!execNSZC001001(svcMachMstrApiPMsgList, index)) {
                    return;
                }
            }

            // Call Serial Update API
            NLZC302001PMsg serialUpdateApiPMsg = cratSerTrxApiPMsgForSWT(glblCmpyCd, invtyOrdDtlLst);
            if (serialUpdateApiPMsg.UpdateDetailList.getValidCount() > 0) {
                if (!execNLZC302001(serialUpdateApiPMsg, index)) {
                    return;
                }
            }
        }
    }

    /**
     * Cancel Inventry Order
     * @param pMsgList List<NLZC004001PMsg>
     */
    private void executeCancel(List<NLZC004001PMsg> pMsgList) {

        for (int i = 0; i < pMsgList.size(); i++) {

            executeCancel(pMsgList.get(i), i);
        }
    }

    /**
     * Cancel Inventry Order
     * @param pMsg NLZC004001PMsg
     * @param index int
     */
    private void executeCancel(NLZC004001PMsg pMsg, int index) {
        /*************************************************************
         * 1. Get Inventory Order
         ************************************************************/
        // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
        if (!getInvtyOrdAndChk(pMsg, index)) {
            return;
        }

        /*************************************************************
         * 2. Inventory Order API (NLZC0030)
         ************************************************************/
        NLZC003001PMsg invtyOrdApiPMsg = cratInvtyOrdHdrApiPMsgCloCancHdr(pMsg, NLZC003001.PROC_TP_CANC, INVTY_ORD_STS.CANCEL);
        if (!execNLZC003001(invtyOrdApiPMsg, index)) {
            return;
        }

        if (ADJ_TRX_TP.ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            executeCancel4Adj(pMsg, index);
        } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            executeCancel4SWT(pMsg, index);
        }
    }

    /**
     * Cancel Adjustment Order
     * @param pMsg NLZC004001PMsg
     * @param index int
     */
    private void executeCancel4Adj(NLZC004001PMsg pMsg, int index) {
        /*************************************************************
         * 4. Get & Process Inventory Order Detail
         ************************************************************/
        ArrayList<Map<String, Object>> invtyOrdDtlLst = getInvtyOrdDtlAndChk(pMsg, index);

        // Inventory Allocation off
        if (!executeInvtyAllocation(invtyOrdDtlLst, NLZC001001.PROC_TP_CANC_ALLOC, index)) {
            return;
        }

        // Machine Master Allocation off
        List<NSZC001001PMsg> svcMachMstrApiPMsgList = cratSvcMachMstrApiPMsgCanc(pMsg, invtyOrdDtlLst);
        if (!execNSZC001001(svcMachMstrApiPMsgList, index)) {
            return;
        }
    }

    /**
     * Cancel Sub Warehouse Transfer Order
     * @param pMsg NLZC004001PMsg
     * @param index int
     */
    private void executeCancel4SWT(NLZC004001PMsg pMsg, int index) {
        /*************************************************************
         * 4. Get & Process Inventory Order Detail
         ************************************************************/
        ArrayList<Map<String, Object>> invtyOrdDtlLst = getInvtyOrdDtlAndChk(pMsg, index);

        // Machine Master Allocation off
        List<NSZC001001PMsg> svcMachMstrApiPMsgList = cratSvcMachMstrApiPMsgCanc(pMsg, invtyOrdDtlLst);
        if (!execNSZC001001(svcMachMstrApiPMsgList, index)) {
            return;
        }
        return;
    }

    /**
     * Create Sub Warehouse Transfer Order
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeCreateSWTOrd(List<NLZC004001PMsg> pMsgList) {

        List<NLZC003001PMsg> invtyOrdApiPMsgArray = new ArrayList<NLZC003001PMsg>();
        List<NLZC003001PMsg> invtyOrdApiPMsgArrayDtl = new ArrayList<NLZC003001PMsg>();

        // Create Inventory Order API(NLZC0030) Parameter
        for (int i = 0; i < pMsgList.size(); i++) {

            // Header
            if (NLZC004001Constant.DT_TP_HDR.equals(pMsgList.get(i).xxDtTpCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(pMsgList.get(i).trxCd, adjTrxTpTMsg.trxCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsgList.get(i).trxRsnCd, adjTrxTpTMsg.trxRsnCd.getValue());

                invtyOrdApiPMsgArray.add(cratSWTOrdHdrApiPMsg(pMsgList.get(i)));

            // Detail
            } else {
                ZYPEZDItemValueSetter.setValue(pMsgList.get(i).invtyOrdLineNum, ZYPCommonFunc.leftPad(Integer.toString(i), NLZC004001Constant.LINE_NUM_LENGTH, "0"));
                invtyOrdApiPMsgArrayDtl.add(cratSWTOrdDtlApiPMsg(pMsgList.get(i), i));
            }
        }

        if (!isExistErr()) {
            for (NLZC003001PMsg invtyOrdDtlPMsg : invtyOrdApiPMsgArrayDtl) {
                invtyOrdApiPMsgArray.add(invtyOrdDtlPMsg);
            }

            // Inventory Order API(NLZC0030) result check
            if (!execNLZC003001(pMsgList, invtyOrdApiPMsgArray)) {

                return false;
            }

            invtyOrdNum = invtyOrdApiPMsgArray.get(0).invtyOrdNum.getValue();
        } else {
            return false;
        }

        return true;
    }

    /**
     * Allocation For Non CPO
     * @param pMsgList List<NLZC004001PMsg>
     * @return boolean
     */
    private boolean executeAllocationForNonCPO(List<NLZC004001PMsg> pMsgList) {

        boolean isAlloc = true;

        for (int i = 0; i < pMsgList.size(); i++) {
            // Header Skip for Allocation
            if (NLZC004001Constant.DT_TP_HDR.equals(pMsgList.get(i).xxDtTpCd.getValue())) {
                continue;
            }

            // Inventory Decrease
            NWZC107001PMsg allocForNonCpoApiPMsg = cratAllocForNonCpoApiPMsg(pMsgList.get(i));
            if (!execNWZC107001(allocForNonCpoApiPMsg, i)) {
                isAlloc = false;
            }
        }

        return isAlloc;
    }

    /**
     * Inventory Allocation
     * @param invtyOrdDtlLst ArrayList<Map<String, Object>>
     * @return boolean
     */
    private boolean executeInvtyAllocation(ArrayList<Map<String, Object>> invtyOrdDtlLst, String xxProcTpCd, int i) {
        boolean isAllocInvty = true;

        // Inventory Decrease
        for (Map<String, Object> invtyOrdDtlMap : invtyOrdDtlLst) {
            if (BigDecimal.ZERO.compareTo((BigDecimal)invtyOrdDtlMap.get("ORD_QTY")) > 0) {
                NLZC001001PMsg invtyAllocApiPMsg = cratInvtyAllocApiPMsg(invtyOrdDtlMap.get("GLBL_CMPY_CD").toString()
                        ,invtyOrdDtlMap.get("MDSE_CD").toString()
                        ,invtyOrdDtlMap.get("INVTY_LOC_CD").toString()
                        ,invtyOrdDtlMap.get("LOC_STS_CD").toString()
                        ,invtyOrdDtlMap.get("STK_STS_CD").toString()
                        ,(BigDecimal)invtyOrdDtlMap.get("ORD_QTY"));
                invtyAllocApiPMsg.xxProcTpCd.setValue(xxProcTpCd);

                if (!execNLZC001001(invtyAllocApiPMsg, i)) {
                    isAllocInvty = false;
                }
            }
        }

        return isAllocInvty;
    }

    /**
     * Inventory Allocation
     * @param pMsg NLZC004001PMsg
     * @return boolean
     */
    private boolean executeInvtyAllocation(NLZC004001PMsg pMsg, String xxProcTpCd, int i) {
        boolean isAllocInvty = true;

        // Header Skip for Allocation
        if (NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue())) {
            return isAllocInvty;
        }

        // Inventory Decrease
        if (BigDecimal.ZERO.compareTo(pMsg.ordQty.getValue()) > 0) {
            NLZC001001PMsg invtyAllocApiPMsg = cratInvtyAllocApiPMsg(pMsg.glblCmpyCd.getValue()
                    ,pMsg.mdseCd.getValue()
                    ,pMsg.invtyLocCd_D1.getValue()
                    ,pMsg.locStsCd_D1.getValue()
                    ,pMsg.stkStsCd.getValue()
                    ,pMsg.ordQty.getValue());
            invtyAllocApiPMsg.xxProcTpCd.setValue(xxProcTpCd);

            if (!execNLZC001001(invtyAllocApiPMsg, i)) {
                isAllocInvty = false;
            }
        }

        return isAllocInvty;
    }

    /**
     * Get Parts Or Item
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return Map<String, Object>
     */
    private Map<String, Object> getPartsOrItem(String glblCmpyCd, String mdseCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("partsItem", MDSE_TP_CTX_TP.PARTS_ITEM);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getMdseOrPartsCd", queryParam);

        return result;
    }

    /**
     * Get Default COA Account
     * @param glblCmpyCd String
     * @param cycleCntMdseTpCd String
     * @return Map<String, Object>
     */
    private Map<String, Object> getDefaultCOAAccount(String glblCmpyCd, String cycleCntMdseTpCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cycleCntMdseTpCd", cycleCntMdseTpCd);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDefaultCOAAccount", queryParam);

        return result;
    }

    /**
     * Get Adjustment Transaction Type
     * @param glblCmpyCd String
     * @param adjTrxTpCd String
     * @return
     */
    private ADJ_TRX_TPTMsg getAdjTrxTp(String glblCmpyCd, String adjTrxTpCd) {
        ADJ_TRX_TPTMsg adjTrxTpTMsg = new ADJ_TRX_TPTMsg();
        ZYPEZDItemValueSetter.setValue(adjTrxTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(adjTrxTpTMsg.adjTrxTpCd, adjTrxTpCd);

        return (ADJ_TRX_TPTMsg) findByKey(adjTrxTpTMsg);

    }

    /**
     * Get Inventry Order
     * @param pMsg NLZC004001PMsg
     * @return INVTY_ORDTMsg
     */
    private INVTY_ORDTMsg getInvtyOrd(NLZC004001PMsg pMsg) {
        INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.invtyOrdNum, pMsg.invtyOrdNum.getValue());

        return (INVTY_ORDTMsg) findByKey(invtyOrdTMsg);
    }

    /**
     * Get Inventry Order Detail
     * @param pMsg NLZC004001PMsg
     * @return ArrayList<Map<String, Object>>
     */
    private ArrayList<Map<String, Object>> getInvtyOrdDtl(NLZC004001PMsg pMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("invtyOrdNum", pMsg.invtyOrdNum.getValue());

        ArrayList<Map<String, Object>> invtyOrdDtlLst = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvtyOrdDtl", queryParam);
        return invtyOrdDtlLst;
    }

    /**
     * Get Inventry Order Detail And Check
     * @param pMsg NLZC004001PMsg
     * @param index int
     * @return ArrayList<Map<String, Object>>
     */
    private ArrayList<Map<String, Object>> getInvtyOrdDtlAndChk(NLZC004001PMsg pMsg, int index) {
        ArrayList<Map<String, Object>> invtyOrdDtlLst = getInvtyOrdDtl(pMsg);

        if (invtyOrdDtlLst == null || invtyOrdDtlLst.isEmpty()) {
            setMsg(index, NLZC004001Constant.NLZM0096E);
            return null;
        }

        return invtyOrdDtlLst;
    }

    /**
     * Get Inventry Order Serial
     * @param glblCmpyCd String
     * @param invtyOrdDtlMap Map<String, Object>
     * @return ArrayList<String>
     */
    private ArrayList<String> getInvtyOrdSer(String glblCmpyCd, Map<String, Object> invtyOrdDtlMap) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyOrdNum", (String) invtyOrdDtlMap.get("INVTY_ORD_NUM"));
        queryParam.put("invtyOrdLineNum", (String) invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM"));

        ArrayList<String> invtyOrdSerLst = (ArrayList<String>) ssmBatchClient.queryObjectList("getInvtyOrdSer", queryParam);

        return invtyOrdSerLst;
    }

    /**
     * Get RWS Header
     * @param String glblCmpyCd
     * @param String rwsNum
     * @return RWS_HDRTMsg
     */
    private RWS_HDRTMsg getRwsHdr(String glblCmpyCd, String rwsNum) {
        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, rwsNum);

        return (RWS_HDRTMsg) findByKey(rwsHdrTMsg);
    }

    /**
     * Get Adjustment Category
     * @param String glblCmpyCd
     * @param String adjCatgCd
     * @return ADJ_CATGTMsg
     */
    private ADJ_CATGTMsg getAdjCatg(String glblCmpyCd, String adjCatgCd) {
        ADJ_CATGTMsg adjCatgTMsg = new ADJ_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(adjCatgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(adjCatgTMsg.adjCatgCd, adjCatgCd);

        return (ADJ_CATGTMsg) findByKey(adjCatgTMsg);
    }

    /**
     * Get Allocated Service Machine Master List
     * @param glblCmpyCd String
     * @param invtyOrdDtlMap Map<String, Object>
     * @return ArrayList<Map<String, Object>>
     */
    private ArrayList<Map<String, Object>> getAllocSvcMachMstrList(String glblCmpyCd, Map<String, Object> invtyOrdDtlMap) {
        Map<String, Object> queryParamIB = new HashMap<String, Object>();
        queryParamIB.put("glblCmpyCd", glblCmpyCd);
        queryParamIB.put("invtyOrdNum", (String) invtyOrdDtlMap.get("INVTY_ORD_NUM"));
        queryParamIB.put("invtyOrdLineNum", (String) invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM"));
        queryParamIB.put("flgN", ZYPConstant.FLG_OFF_N);

        ArrayList<Map<String, Object>> svcMachMstrList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getAllocSvcMachMstrList", queryParamIB);

        return svcMachMstrList;
    }

    /**
     * Get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);

        return (MDSETMsg) findByKey(mdseTMsg);
    }

    /**
     * Get INVTY
     * @param glblCmpyCd String
     * @param invtyOrdDtlMap Map<String, Object>
     * @return INVTYTMsg
     */
    private INVTYTMsg getInvty(String glblCmpyCd, Map<String, Object> invtyOrdDtlMap) {
        INVTYTMsg invtyTMsg = new INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(invtyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invtyTMsg.invtyLocCd, invtyOrdDtlMap.get("INVTY_LOC_CD").toString());
        ZYPEZDItemValueSetter.setValue(invtyTMsg.mdseCd, invtyOrdDtlMap.get("MDSE_CD").toString());
        ZYPEZDItemValueSetter.setValue(invtyTMsg.stkStsCd, invtyOrdDtlMap.get("STK_STS_CD").toString());
        ZYPEZDItemValueSetter.setValue(invtyTMsg.locStsCd, invtyOrdDtlMap.get("LOC_STS_CD").toString());

        return (INVTYTMsg) findByKey(invtyTMsg);
    }

    /**
     * Find by key Update
     * @param tMsg EZDTMsg
     * @return EZDTMsg
     */
    private EZDTMsg findByKey(EZDTMsg tMsg) {
        tMsg = S21ApiTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            return tMsg;
        } else {
            return null;
        }
    }

    /**
     * Get Invntry Order And Check
     * @param pMsg NLZC004001PMsg
     * @param index int
     * @return
     */
    private boolean getInvtyOrdAndChk(NLZC004001PMsg pMsg, int index) {
        boolean result = true;

        INVTY_ORDTMsg invtyOrdTMsg = getInvtyOrd(pMsg);
        if (!chkInvtyOrd(pMsg, invtyOrdTMsg, index)) {
            result = false;
        }
        adjTrxTpTMsg = getAdjTrxTp(pMsg.glblCmpyCd.getValue(), pMsg.adjTrxTpCd.getValue());
        if (!chkAdjTrxTp(pMsg, adjTrxTpTMsg, index)) {
            result = false;
        }
        
        return result;
    }
    /**
     * Check Inventry Order
     * @param pMsg NLZC004001PMsg
     * @param invtyOrdTMsg INVTY_ORDTMsg
     * @param index int
     * @return boolean
     */
    private boolean chkInvtyOrd(NLZC004001PMsg pMsg, INVTY_ORDTMsg invtyOrdTMsg, int index) {
        boolean result = true;
        
        if (invtyOrdTMsg == null) {
            setMsg(index, NLZC004001Constant.NLZM0096E);
            result = false;

        } else if (!pMsg.invtyOrdTpCd.getValue().equals(invtyOrdTMsg.invtyOrdTpCd.getValue())) {
            setMsg(index, NLZC004001Constant.NLZM0117E);
            result = false;
        }

        if (ZYPCommonFunc.hasValue(pMsg.xxRqstTs) && ZYPCommonFunc.hasValue(pMsg.xxRqstTz)) {
            if (!ZYPDateUtil.isSameTimeStamp(pMsg.xxRqstTs.getValue(), pMsg.xxRqstTz.getValue(), invtyOrdTMsg.ezUpTime.getValue(), invtyOrdTMsg.ezUpTimeZone.getValue())) {
                setMsg(index, NLZC004001Constant.NLZM0050E);
                result = false;
            }
        }

        return result;
    }

    /**
     * Check Adjustment Transaction Type
     * @param pMsg NLZC004001PMsg
     * @param adjTrxTpTMsg ADJ_TRX_TPTMsg
     * @param index int
     * @return boolean
     */
    private boolean chkAdjTrxTp(NLZC004001PMsg pMsg, ADJ_TRX_TPTMsg adjTrxTpTMsg, int index) {
        boolean result = true;

        if (adjTrxTpTMsg == null) {

            setMsg(index, NLZC004001Constant.NLZM2377E);
            result = false;

        } else if (!ZYPCommonFunc.hasValue(adjTrxTpTMsg.trxCd.getValue()) || !ZYPCommonFunc.hasValue(adjTrxTpTMsg.trxRsnCd.getValue())) {

            setMsg(index, NLZC004001Constant.NLZM2379E);
            result = false;
        }
        
        return result;
    }

    /**
     * Check Auto Transfer Retail Warehouse
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean
     */
    private boolean isAutoTransRetailWh(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", rtlWhCd);

        // Execute
        BigDecimal result = (BigDecimal) ssmBatchClient.queryObject("countSubWarehouseTransferControl", param);

        if (BigDecimal.ZERO.compareTo(result) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Process Install Base Merchandise
     * @param invtyOrdDtlApiPMsg NLZC003001PMsg
     * @param pMsg NLZC004001PMsg
     * @param mdseInfoMap Map<String, Object>
     * @param index Map<String, Object>
     */
    private void procIBMdse(NLZC003001PMsg invtyOrdDtlApiPMsg, NLZC004001PMsg pMsg, int index) {
        // Get MDSE Information
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("mdseCd", pMsg.mdseCd.getValue());

        Map<String, Object> mdseInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getMdseInfo", queryParam);

        if (mdseInfoMap == null) {

            setMsg(index, NLZC004001Constant.NLZM0021E);
            return;
        }

        int serInfoListIndex = 0;

        if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get("INSTL_BASE_CTRL_FLG"))) {
            if (pMsg.serialInfoList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.serialInfoList.getValidCount(); i++) {
                    if (isChkAllocMachMstr(pMsg, pMsg.serialInfoList.no(i), index)) {
                        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.serialInfoList.no(serInfoListIndex).invtyOrdLineNum, pMsg.invtyOrdLineNum.getValue());
                        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.serialInfoList.no(serInfoListIndex).serNum, pMsg.serialInfoList.no(i).serNum.getValue());
                        serInfoListIndex++;
                    }
                }
            } else {
                isChkAllocMachMstr(pMsg, null, index);
            }

        } else {
            for (int i = 0; i < pMsg.serialInfoList.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.serialInfoList.no(serInfoListIndex).invtyOrdLineNum, pMsg.invtyOrdLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.serialInfoList.no(serInfoListIndex).serNum, pMsg.serialInfoList.no(i).serNum.getValue());
                serInfoListIndex++;
            }
        }

        invtyOrdDtlApiPMsg.serialInfoList.setValidCount(serInfoListIndex);

    }

    /**
     * Create API PMsg for NLZC003001 Common Header Creation
     * @param pMsg NLZC004001PMsg
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg cratNLZC003001HdrApiCommonPMsg(NLZC004001PMsg pMsg) {

        NLZC003001PMsg invtyOrdHdrApiPMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdTpCd, pMsg.invtyOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyLocCd, pMsg.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.locStsCd, pMsg.locStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.trxCd, pMsg.trxCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.trxRsnCd, pMsg.trxRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdStsCd, INVTY_ORD_STS.FINALIZED);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.firstInvtyOrdCmntTxt, pMsg.firstInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.scdInvtyOrdCmntTxt, pMsg.scdInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.thirdInvtyOrdCmntTxt, pMsg.thirdInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.frthInvtyOrdCmntTxt, pMsg.frthInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.trxSrcTpCd, pMsg.trxSrcTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.sysSrcCd, pMsg.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.adjTrxTpCd, adjTrxTpTMsg.adjTrxTpCd.getValue());

        return invtyOrdHdrApiPMsg;
    }

    /**
     * Create API PMsg for Sub Warehouse Transfer Order Header Creation
     * @param pMsg NLZC004001PMsg
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg cratSWTOrdHdrApiPMsg(NLZC004001PMsg pMsg) {

        NLZC003001PMsg invtyOrdHdrApiPMsg = cratNLZC003001HdrApiCommonPMsg(pMsg);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk.getValue());

        return invtyOrdHdrApiPMsg;
    }

    /**
     * Create API PMsg for Sub Warehouse Transfer Order Detail Creation
     * @param pMsg NLZC004001PMsg
     * @param mdseInfoMap Map<String, Object>
     * @param index int
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg cratSWTOrdDtlApiPMsg(NLZC004001PMsg pMsg, int index) {

        NLZC003001PMsg invtyOrdDtlApiPMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdTpCd, pMsg.invtyOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdLineNum, pMsg.invtyOrdLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.mdseCd, pMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.stkStsCd, pMsg.stkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyLocCd, pMsg.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyLocCd_D1, pMsg.invtyLocCd_D1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.locStsCd, pMsg.locStsCd);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.locStsCd_D1, pMsg.locStsCd_D1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.adjTrxTpCd, adjTrxTpTMsg.adjTrxTpCd);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.FINALIZED);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.ordQty, pMsg.ordQty.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.adjCatgCd, pMsg.adjCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdLineCmntTxt, pMsg.invtyOrdLineCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.invtyOrdLineCostAmt, pMsg.invtyOrdLineCostAmt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.adjAcctAliasNm, pMsg.adjAcctAliasNm.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.coaAcctCd, pMsg.coaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(invtyOrdDtlApiPMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk.getValue());

        procIBMdse(invtyOrdDtlApiPMsg, pMsg, index);

        return invtyOrdDtlApiPMsg;
    }

    /**
     * Create API PMsg for Allocation Non CPO
     * @param pMsg NLZC004001PMsg
     * @return List<NWZC107001PMsg>
     */
    private NWZC107001PMsg cratAllocForNonCpoApiPMsg(NLZC004001PMsg pMsg) {
        NWZC107001PMsg allocForNonCpoApiPMsg = new NWZC107001PMsg();
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.trxCd, adjTrxTpTMsg.trxCd.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.trxRsnCd, adjTrxTpTMsg.trxRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.trxHdrNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.trxLineNum, pMsg.invtyOrdLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.trxLineSubNum, "001");
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.mdseCd, pMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.invtyLocCd, pMsg.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.locStsCd, pMsg.locStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.stkStsCd, pMsg.stkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.xxRqstQty, pMsg.ordQty.getValue());

        NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
        bean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        bean.setInvtyLocCd(pMsg.invtyLocCd.getValue());
        bean.setMdseCd(pMsg.mdseCd.getValue());
        bean.setQty(pMsg.ordQty.getValue().abs());
        NLXC001001GetInventoryItemCostBean retNLXC001001GetInventoryItemCostBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
        BigDecimal unitPrcAmt = retNLXC001001GetInventoryItemCostBean.getUnitPrcAmt();
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.xxUnitPrc, unitPrcAmt);

        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.xxSlsAmt, pMsg.invtyOrdLineCostAmt.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.billToCustCd, pMsg.invtyLocCd_D1.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.sellToCustCd, pMsg.invtyLocCd_D1.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.shipToCustCd, pMsg.invtyLocCd_D1.getValue());
        ZYPEZDItemValueSetter.setValue(allocForNonCpoApiPMsg.rsdDt, pMsg.slsDt.getValue());

        return allocForNonCpoApiPMsg;
    }

    /**
     * Create API PMsg for Shipping Plan Update
     * @param pMsg NLZC004001PMsg
     * @return NWZC003001PMsg
     */
    private List<NWZC003001PMsg> cratShippingPlanUpdatePMsg(NLZC004001PMsg pMsg, ArrayList<Map<String, Object>> invtyOrdDtlLst) {
        List<NWZC003001PMsg> shpgPlnApiPMsgList = new ArrayList<NWZC003001PMsg>();
        for (int i = 0; i < invtyOrdDtlLst.size(); i++) {
            Map<String, Object> invtyOrdDtlMap = invtyOrdDtlLst.get(i);

            NWZC003001PMsg shpgPlnApiPMsg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxHdrNum, invtyOrdDtlMap.get("INVTY_ORD_NUM").toString());
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxLineNum, invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM").toString());
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxLineSubNum, "001");
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.avalSoQty, new BigDecimal(invtyOrdDtlMap.get("ORD_QTY").toString()));

            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);

            shpgPlnApiPMsgList.add(shpgPlnApiPMsg);
        }

        return shpgPlnApiPMsgList;
    }

    /**
     * Create API PMsg for SO
     * @param pMsg NLZC004001PMsg
     * @return NLZC205001PMsg
     */
    private List<NLZC205001PMsg> cratSoApiPMsg(NLZC004001PMsg pMsg, ArrayList<Map<String, Object>> invtyOrdDtlLst) {
        List<NLZC205001PMsg> soApiPMsgList = new ArrayList<NLZC205001PMsg>();
        for (int i = 0; i < invtyOrdDtlLst.size(); i++) {
            Map<String, Object> invtyOrdDtlMap = invtyOrdDtlLst.get(i);

            NLXC023001PMsg nlxc023001PMsg = new NLXC023001PMsg();
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxHdrNum, invtyOrdDtlMap.get("INVTY_ORD_NUM").toString());
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxLineNum, invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM").toString());
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            NLXC023001 nlxc023001 = new NLXC023001();
            nlxc023001.execute(nlxc023001PMsg, ONBATCH_TYPE.ONLINE);

            NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(soApiPMsg.sceOrdTpCd, SCE_ORD_TP.SUB_WH_CHANGE);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgPlnNum, nlxc023001PMsg.shpgPlnNum.getValue());
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_NEW);
            soApiPMsgList.add(soApiPMsg);
        }

        return soApiPMsgList;
    }

    /**
     * Create API PMsg for RWS
     * @param pMsg NLZC205001PMsg
     * @return NLZC200001PMsg
     */
    private NLZC200001PMsg cratRwsApiPMsg(NLZC205001PMsg pMsg) {
        NLZC200001PMsg rwsApiPMsg = new NLZC200001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.soNum, pMsg.soNum.getValue());

        return rwsApiPMsg;
    }

    /**
     * Create API PMsg for RWS Serial
     * @param pMsg NLZC004001PMsg
     * @param rwsApiPMsg NLZC200001PMsg
     * @return NLZC304001PMsg
     */
    private NLZC304001PMsg cratRwsSerApiPMsg(String glblCmpyCd, NLZC200001PMsg rwsApiPMsg, ArrayList<Map<String, Object>> invtyOrdDtlLst) {
        NLZC304001PMsg rwsSerApi = new NLZC304001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsSerApi.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsSerApi.rwsNum, rwsApiPMsg.RWSNumList.no(0).rwsNum);
        int SerialListIdx = 0;
        for (int i = 0; i < invtyOrdDtlLst.size(); i++) {
            Map<String, Object> invtyOrdDtlMap = invtyOrdDtlLst.get(i);
            ArrayList<String> serList = getInvtyOrdSer(glblCmpyCd, invtyOrdDtlMap);

            for (String ser : serList) {
                if (ZYPCommonFunc.hasValue(ser)) {
                    ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).rwsDtlLineNum, invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM").toString());
                    ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).mdseCd, invtyOrdDtlMap.get("MDSE_CD").toString());
                    ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                    ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).serNum, ser);
                    SerialListIdx++;
                }
            }
        }
        rwsSerApi.SerialList.setValidCount(SerialListIdx);

        return rwsSerApi;
    }

    /**
     * Create API PMsg for RWS Put Away For S21DC
     * @param pMsg NLZC004001PMsg
     * @param rwsHdrTMsg RWS_HDRTMsg
     * @return NLZC206001PMsg
     */
    private NLZC206001PMsg cratRwsPutAwayApiPMsg(String glblCmpyCd, String slsDt, RWS_HDRTMsg rwsHdrTMsg) {
        String rwsNum = rwsHdrTMsg.rwsNum.getValue();

        RWS_DTLTMsg rwsDtlInMsg = new RWS_DTLTMsg();
        rwsDtlInMsg.setSQLID("002");
        rwsDtlInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsDtlInMsg.setConditionValue("rwsNum01", rwsNum);
        RWS_DTLTMsgArray rwsDtlArray = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(rwsDtlInMsg);

        RWS_SERTMsg rwsSerInMsg = new RWS_SERTMsg();
        rwsSerInMsg.setSQLID("002");
        rwsSerInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsSerInMsg.setConditionValue("rwsNum01", rwsNum);
        RWS_SERTMsgArray rwsSerArray = (RWS_SERTMsgArray) EZDTBLAccessor.findByCondition(rwsSerInMsg);

        NLZC206001PMsg putAwayS21DcApiPMsg = new NLZC206001PMsg();

        // Set Parameter
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.slsDt, slsDt);

        int rwsDtlCnt = 0;
        int rwsSerCnt = 0;

        for (; rwsDtlCnt < rwsDtlArray.getValidCount(); rwsDtlCnt++) {

            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsNum, rwsDtlArray.no(rwsDtlCnt).rwsNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsDtlLineNum, rwsDtlArray.no(rwsDtlCnt).rwsDtlLineNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).invtyStkStsCd, rwsDtlArray.no(rwsDtlCnt).invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkDtTmTs, ZYPDateUtil.getCurrentSystemTime(NLZC004001Constant.TIMESTAMPFORMAT));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkQty, rwsDtlArray.no(rwsDtlCnt).rwsQty.getValue().abs());
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).whCd, rwsDtlArray.no(rwsDtlCnt).invtyLocCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).mdseCd, rwsDtlArray.no(rwsDtlCnt).mdseCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsRefNum, rwsHdrTMsg.rwsRefNum);
        }

        // Set Serial
        for (; rwsSerCnt < rwsSerArray.getValidCount(); rwsSerCnt++) {
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).rwsDtlLineNum, rwsSerArray.no(rwsSerCnt).rwsLineNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).serNum, rwsSerArray.no(rwsSerCnt).serNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).mdseCd, rwsSerArray.no(rwsSerCnt).mdseCd);
        }
        putAwayS21DcApiPMsg.RWSPutAwayList.setValidCount(rwsDtlCnt);
        putAwayS21DcApiPMsg.RcvSerNumList.setValidCount(rwsSerCnt);

        return putAwayS21DcApiPMsg;
    }

    /**
     * Create API PMsg for RWS Completion For S21DC
     * @param pMsg NLZC004001PMsg
     * @param rwsHdrTMsg RWS_HDRTMsg
     * @return NLZC207001PMsg
     */
    private NLZC207001PMsg cratRwsCompletionApiPMsg(String glblCmpyCd, String slsDt, RWS_HDRTMsg rwsHdrTMsg) {
        NLZC207001PMsg rwsCompletionMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsCompletionMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsCompletionMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(rwsCompletionMsg.rwsNum, rwsHdrTMsg.rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsCompletionMsg.sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(rwsCompletionMsg.rwsCloDtTmTs, ZYPDateUtil.getCurrentSystemTime(NLZC004001Constant.TIMESTAMPFORMAT));
        ZYPEZDItemValueSetter.setValue(rwsCompletionMsg.whCd, rwsHdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(rwsCompletionMsg.rwsRefNum, rwsHdrTMsg.rwsRefNum);

        return rwsCompletionMsg;
    }

    /**
     * Create API PMsg for Machine Master (Update) For Common
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @param invtyOrdDtlMap Map<String, Object>
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg cratSvcMachMstrApiCommonPMsgUpd(String slsDt, BigDecimal svcMachMstrPk, Map<String, Object> invtyOrdDtlMap, String procMode) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, (String) invtyOrdDtlMap.get("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, procMode);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrLocStsCd, (String) invtyOrdDtlMap.get("LOC_STS_CD"));
        if (ADJ_TRX_TP.ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())
                || ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, (String) invtyOrdDtlMap.get("INVTY_LOC_CD"));
        } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(adjTrxTpTMsg.adjTrxTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, (String) invtyOrdDtlMap.get("TO_INVTY_LOC_CD"));
        }

        return svcMachMstrApiPMsg;
    }

    /**
     * Create API PMsg for Machine Master (Update) For Sub Warehouse Transfer
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @param invtyOrdDtlMap Map<String, Object>
     * @return NSZC001001PMsg
     */
    private List<NSZC001001PMsg> cratSvcMachMstrApiPMsgUpdForSWT(NLZC004001PMsg pMsg, List<Map<String, Object>> invtyOrdDtlLst) {
        List<NSZC001001PMsg> svcMachMstrApiPMsgList = new ArrayList<NSZC001001PMsg>();

        for (Map<String, Object> invtyOrdDtlMap : invtyOrdDtlLst) {
            // Mod Start 2019/12/11 QC#55021
//            NSZC001001PMsg svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgUpdForSWT(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), invtyOrdDtlMap);
            List<NSZC001001PMsg> getSvcMachMstrApiPMsgList = cratSvcMachMstrApiPMsgUpdForSWT(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), invtyOrdDtlMap);
            for (NSZC001001PMsg svcMachMstrApiPMsg : getSvcMachMstrApiPMsgList) {
//                if (svcMachMstrApiPMsg != null) {
                svcMachMstrApiPMsgList.add(svcMachMstrApiPMsg);

                BigDecimal svcMachMstrPk = svcMachMstrApiPMsg.svcMachMstrPk.getValue();
                svcMachMstrApiPMsg = new NSZC001001PMsg();
                svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgAlloc(pMsg, svcMachMstrPk, null, false);
                svcMachMstrApiPMsgList.add(svcMachMstrApiPMsg);
            }
            // Mod End 2019/12/11 QC#55021
        }

        return svcMachMstrApiPMsgList;
    }

    /**
     * Create API PMsg for Machine Master (Update) For Sub Warehouse Transfer
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @param invtyOrdDtlMap Map<String, Object>
     * @return List<NSZC001001PMsg>
     */
    // Mod Start 2019/12/11 QC#55021
//    private NSZC001001PMsg cratSvcMachMstrApiPMsgUpdForSWT(String glblCmpyCd, String slsDt, Map<String, Object> invtyOrdDtlMap) {
    private List<NSZC001001PMsg> cratSvcMachMstrApiPMsgUpdForSWT(String glblCmpyCd, String slsDt, Map<String, Object> invtyOrdDtlMap) {
        ArrayList<Map<String, Object>> svcMachMstrList = getAllocSvcMachMstrList(glblCmpyCd, invtyOrdDtlMap);

        // Inventry
        NSZC001001PMsg svcMachMstrApiPMsg = null;
        List<NSZC001001PMsg> svcMachMstrApiPMstList = new ArrayList<NSZC001001PMsg>();
        if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {
            for (Map<String, Object> svcMachMstrMap : svcMachMstrList) {
                BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK");

                svcMachMstrApiPMsg = cratSvcMachMstrApiCommonPMsgUpd(slsDt, svcMachMstrPk, invtyOrdDtlMap, ProcessMode.UPDATE_INVENTORY.code);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, (String) invtyOrdDtlMap.get("STK_STS_CD"));
                svcMachMstrApiPMstList.add(svcMachMstrApiPMsg);
            }
        }

//        return svcMachMstrApiPMsg;
        return svcMachMstrApiPMstList;
    }
    // Mod End 2019/12/11 QC#55021

    /**
     * Create API PMsg for Machine Master (Cancel)
     * @param pMsg NLZC004001PMsg
     * @param invtyOrdDtlLst ArrayList<Map<String, Object>>
     * @return List<NSZC001001PMsg>
     */
    private List<NSZC001001PMsg> cratSvcMachMstrApiPMsgCanc(NLZC004001PMsg pMsg, ArrayList<Map<String, Object>> invtyOrdDtlLst) {
        List<NSZC001001PMsg> svcMachMstrApiPMsgList = new ArrayList<NSZC001001PMsg>();
        for (Map<String, Object> invtyOrdDtlMap : invtyOrdDtlLst) {
            svcMachMstrApiPMsgList.addAll(cratSvcMachMstrApiPMsgCanc(pMsg, invtyOrdDtlMap));
        }

        return svcMachMstrApiPMsgList;
    }

    /**
     * Create API PMsg for Machine Master (Cancel)
     * @param pMsg NLZC004001PMsg
     * @param invtyOrdDtlMap Map<String, Object>
     * @return List<NSZC001001PMsg>
     */
    private List<NSZC001001PMsg> cratSvcMachMstrApiPMsgCanc(NLZC004001PMsg pMsg, Map<String, Object> invtyOrdDtlMap) {
        List<NSZC001001PMsg> svcMachMstrApiPMsgList = new ArrayList<NSZC001001PMsg>();

        ArrayList<Map<String, Object>> svcMachMstrList = getAllocSvcMachMstrList(pMsg.glblCmpyCd.getValue(), invtyOrdDtlMap);
        if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {
            for (Map<String, Object> svcMachMstrMap : svcMachMstrList) {
                NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
                BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK");
                svcMachMstrApiPMsg = cratSvcMachMstrApiPMsgAlloc(pMsg, svcMachMstrPk, null, false);

                svcMachMstrApiPMsgList.add(svcMachMstrApiPMsg);
            }
        }

        return svcMachMstrApiPMsgList;
    }

    /**
     * Create API PMsg for Serial Transaction For Sub Warehouse Transfer
     * @param pMsg NLZC004001PMsg
     * @return NLZC302001PMsg
     */
    private NLZC302001PMsg cratSerTrxApiPMsgForSWT(String glblCmpyCd, ArrayList<Map<String, Object>> invtyOrdDtlLst) {
        NLZC302001PMsg serialUpdateApiPMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.glblCmpyCd, glblCmpyCd);
        int serialApiPMsgIdx = 0;
        for (int i = 0; i < invtyOrdDtlLst.size(); i++) {
            Map<String, Object> invtyOrdDtlMap = invtyOrdDtlLst.get(i);
            ArrayList<String> serList = getInvtyOrdSer(glblCmpyCd, invtyOrdDtlMap);

            for (String ser : serList) {
                if (ZYPCommonFunc.hasValue(ser)) {
                    ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serNum, ser);
                    ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).mdseCd, invtyOrdDtlMap.get("MDSE_CD").toString());
                    ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxTs, ZYPDateUtil.getCurrentSystemTime(NLZC004001Constant.TIMESTAMPFORMAT_MS));
                    ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxEventCd, SER_TRX_EVENT.SUB_WAREHOUSE_CHANGE);
                    ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).toStkStsCd, invtyOrdDtlMap.get("STK_STS_CD").toString());
                    ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).manCratFlg, ZYPConstant.FLG_ON_Y);
                    serialApiPMsgIdx++;
                }
            }

            serialUpdateApiPMsg.UpdateDetailList.setValidCount(serialApiPMsgIdx);
        }

        return serialUpdateApiPMsg;
    }

    /**
     * Create Inventory Adjustment Approval to WF API
     * @param pMsg NLZC004001PMsg
     * @return NLZC005001PMsg
     */
    private NLZC005001PMsg cratInvtyAdjApprvlWFApiPMsg(List<NLZC004001PMsg> pMsgList) {
        NLZC005001PMsg invtyAdjApprvlWFApiPMsg = new NLZC005001PMsg();
        ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.glblCmpyCd, pMsgList.get(0).glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.slsDt, pMsgList.get(0).slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.invtyOrdNum, pMsgList.get(0).invtyOrdNum.getValue());

        // QC#55187 Start
        boolean isParts = false;
        boolean isOther = false;
        for (NLZC004001PMsg pMsg : pMsgList) {
            MDSETMsg mdseTMsg = getMdse(pMsgList.get(0).glblCmpyCd.getValue(), pMsg.mdseCd.getValue());
            if (mdseTMsg != null) {
                if (MDSE_CATG.PARTS.equals(mdseTMsg.mdseCatgCd.getValue())) {
                    isParts = true;
                } else {
                    isOther = true;
                }
            }
            // START 2020/06/04 [QC#56720,ADD]
            if (!ZYPCommonFunc.hasValue(invtyAdjApprvlWFApiPMsg.cycleCntRsnCd) && ZYPCommonFunc.hasValue(pMsg.cycleCntRsnCd)) {
                ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.cycleCntRsnCd, pMsg.cycleCntRsnCd);
            }
            // END   2020/06/04 [QC#56720,ADD]
        }

        if (isOther && isParts) {
            ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_MERCHANDISE);
        } else if (!isOther && isParts){
            ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_PARTS);
        } else {
            ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_MERCHANDISE);
        }

//        if (isParts) {
//            ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_PARTS);
//        } else {
//            ZYPEZDItemValueSetter.setValue(invtyAdjApprvlWFApiPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.INVENTORY_ADJUSTMENT_MERCHANDISE);
//        }
        // QC#55187 Start End

        return invtyAdjApprvlWFApiPMsg;
    }

    /**
     * Create API PMsg for Sub Warehouse Transfer Order Header Update(Status Only)
     * @param invtyOrdTMsg INVTY_ORDTMsg
     * @return NLZC003001PMsg
     */
    private NLZC003001PMsg cratSWTOrdHdrApiPMsgUpdateHdr(INVTY_ORDTMsg invtyOrdTMsg) {

        NLZC003001PMsg invtyOrdHdrApiPMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxProcTpCd, NLZC003001.PROC_TP_UPD);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.glblCmpyCd, invtyOrdTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdNum, invtyOrdTMsg.invtyOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdTpCd, invtyOrdTMsg.invtyOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyLocCd, invtyOrdTMsg.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.locStsCd, invtyOrdTMsg.locStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.trxCd, invtyOrdTMsg.trxCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.trxRsnCd, invtyOrdTMsg.trxRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.dmgClmCpltFlg, invtyOrdTMsg.dmgClmCpltFlg.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.firstInvtyOrdCmntTxt, invtyOrdTMsg.firstInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.scdInvtyOrdCmntTxt, invtyOrdTMsg.scdInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.thirdInvtyOrdCmntTxt, invtyOrdTMsg.thirdInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.frthInvtyOrdCmntTxt, invtyOrdTMsg.frthInvtyOrdCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.trxSrcTpCd, invtyOrdTMsg.trxSrcTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.sysSrcCd, invtyOrdTMsg.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.adjTrxTpCd, invtyOrdTMsg.adjTrxTpCd.getValue());

        return invtyOrdHdrApiPMsg;
    }

    /**
     * Create API PMsg for Sub Warehouse Transfer Order Detail Update(Status Only)
     * @param invtyOrdDtlLst ArrayList<Map<String, Object>>
     * @return List<NLZC003001PMsg>
     */
    private List<NLZC003001PMsg> cratSWTOrdHdrApiPMsgUpdateDtl(ArrayList<Map<String, Object>> invtyOrdDtlLst) {
        List<NLZC003001PMsg> invtyOrdHdrApiPMsgList = new ArrayList<NLZC003001PMsg>();

        for (Map<String, Object> invtyOrdDtlMap : invtyOrdDtlLst) {
            NLZC003001PMsg invtyOrdHdrApiPMsg = new NLZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxProcTpCd, NLZC003001.PROC_TP_UPD);
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.glblCmpyCd, convNull2Val(invtyOrdDtlMap.get("GLBL_CMPY_CD")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdNum, convNull2Val(invtyOrdDtlMap.get("INVTY_ORD_NUM")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdLineNum, convNull2Val(invtyOrdDtlMap.get("INVTY_ORD_LINE_NUM")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.mdseCd, convNull2Val(invtyOrdDtlMap.get("MDSE_CD")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.stkStsCd, convNull2Val(invtyOrdDtlMap.get("STK_STS_CD")).toString());
            if (ZYPCommonFunc.hasValue(convNull2Val(invtyOrdDtlMap.get("ORD_QTY")).toString())) {
                ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.ordQty, new BigDecimal(convNull2Val(invtyOrdDtlMap.get("ORD_QTY")).toString()));
            }
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyLocCd, convNull2Val(invtyOrdDtlMap.get("INVTY_LOC_CD")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyLocCd_D1, convNull2Val(invtyOrdDtlMap.get("TO_INVTY_LOC_CD")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.locStsCd_D1, convNull2Val(invtyOrdDtlMap.get("LOC_STS_CD")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.adjCatgCd, convNull2Val(invtyOrdDtlMap.get("ADJ_CATG_CD")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdLineCmntTxt, convNull2Val(invtyOrdDtlMap.get("INVTY_ORD_LINE_CMNT_TXT")).toString());
            if (ZYPCommonFunc.hasValue(convNull2Val(invtyOrdDtlMap.get("INVTY_ORD_LINE_COST_AMT")).toString())) {
                ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.invtyOrdLineCostAmt, new BigDecimal(convNull2Val(invtyOrdDtlMap.get("INVTY_ORD_LINE_COST_AMT")).toString()));
            }
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.adjAcctAliasNm, convNull2Val(invtyOrdDtlMap.get("ADJ_ACCT_ALIAS_NM")).toString());
            ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.coaAcctCd, convNull2Val(invtyOrdDtlMap.get("COA_ACCT_CD")).toString());
            if (ZYPCommonFunc.hasValue(convNull2Val(invtyOrdDtlMap.get("SVC_CONFIG_MSTR_PK")).toString())) {
                ZYPEZDItemValueSetter.setValue(invtyOrdHdrApiPMsg.svcConfigMstrPk, new BigDecimal(convNull2Val(invtyOrdDtlMap.get("SVC_CONFIG_MSTR_PK")).toString()));
            }

            invtyOrdHdrApiPMsgList.add(invtyOrdHdrApiPMsg);
        }

        return invtyOrdHdrApiPMsgList;
    }

    /**
     * Execute NLZC003001
     * @param pMsgList List<NLZC004001PMsg>
     * @param pMsg List<NLZC003001PMsg>
     * @return boolean
     */
    private boolean execNLZC003001(List<NLZC004001PMsg> pMsgList, List<NLZC003001PMsg> pMsg) {
        // Inventory Order API(NLZC0030) is executed
        NLZC003001 api = new NLZC003001();
        api.execute(pMsg, paramOnBatchType);
        // Inventory Order API(NLZC0030) result check
        if (!isInvtyOrdApiResult(pMsgList, pMsg)) {
            return false;
        }

        return true;
    }

    /**
     * Execute NLZC003001
     * @param pMsg NLZC003001PMsg
     * @return boolean
     */
    private boolean execNLZC003001(NLZC003001PMsg pMsg, int i) {
        NLZC003001 api = new NLZC003001();
        api.execute(pMsg, paramOnBatchType);
        if (!isApiResult(pMsg, i)) {
            return false;
        }

        return true;
    }

    /**
     * Execute NLZC003001
     * @param pMsg List<NLZC003001PMsg>
     * @return boolean
     */
    private boolean execNLZC003001(List<NLZC003001PMsg> pMsg, int i) {
        NLZC003001 api = new NLZC003001();
        api.execute(pMsg, paramOnBatchType);
        if (!isApiResult(pMsg, i)) {
            return false;
        }

        return true;
    }

    /**
     * Execute NWCL107001
     * @param pMsg NWZC107001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNWZC107001(NWZC107001PMsg pMsg, int i) {
        NWZC107001 api = new NWZC107001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NWZC003001
     * @param pMsg List<NWZC003001PMsg>
     * @param i int
     * @return boolean
     */
    private boolean execNWZC003001(List<NWZC003001PMsg> pMsg, int i) {
        NWZC003001 api = new NWZC003001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC205001
     * @param pMsg List<NLZC205001PMsg>
     * @param i int
     * @return boolean
     */
    private boolean execNLZC205001(List<NLZC205001PMsg> pMsg, int i) {
        NLZC205001 api = new NLZC205001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC200001
     * @param pMsg List<NLZC200001PMsg>
     * @param i int
     * @return boolean
     */
    private boolean execNLZC200001(NLZC200001PMsg pMsg, int i) {
        NLZC200001 api = new NLZC200001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC304001
     * @param pMsg List<NLZC304001PMsg>
     * @param i int
     * @return boolean
     */
    private boolean execNLZC304001(NLZC304001PMsg pMsg, int i) {
        NLZC304001 api = new NLZC304001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC206001
     * @param pMsg List<NLZC206001PMsg>
     * @param i int
     * @return boolean
     */
    private boolean execNLZC206001(NLZC206001PMsg pMsg, int i) {
        NLZC206001 api = new NLZC206001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC207001
     * @param pMsg List<NLZC207001PMsg>
     * @param i int
     * @return boolean
     */
    private boolean execNLZC207001(NLZC207001PMsg pMsg, int i) {
        NLZC207001 api = new NLZC207001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NSZC001001
     * @param pMsg List<NLZC207001PMsg>
     * @param i int
     * @return boolean
     */
    private boolean execNSZC001001(List<NSZC001001PMsg> pMsg, int i) {
        NSZC001001 api = new NSZC001001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NSZC001001
     * @param pMsg NLZC207001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNSZC001001(NSZC001001PMsg pMsg, int i) {
        NSZC001001 api = new NSZC001001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC302001
     * @param pMsg NLZC302001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNLZC302001(NLZC302001PMsg pMsg, int i) {
        NLZC302001 api = new NLZC302001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC001001
     * @param pMsg NLZC001001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNLZC001001(NLZC001001PMsg pMsg, int i) {
        NLZC001001 api = new NLZC001001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC005001
     * @param pMsg NLZC005001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNLZC005001(NLZC005001PMsg pMsg, int i) {
        NLZC005001 api = new NLZC005001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC002001
     * @param pMsg NLZC002001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNLZC002001(NLZC002001PMsg pMsg, int i) {
        NLZC002001 api = new NLZC002001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC309001
     * @param pMsg NLZC309001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNLZC309001(NLZC309001PMsg pMsg, int i) {
        NLZC309001 api = new NLZC309001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     * Execute NLZC305001
     * @param pMsg NLZC305001PMsg
     * @param i int
     * @return boolean
     */
    private boolean execNLZC305001(NLZC305001PMsg pMsg, int i) {
        NLZC305001 api = new NLZC305001();
        api.execute(pMsg, paramOnBatchType);

        if (!isApiResult(pMsg, i)) {
            return false;
        }
        return true;
    }

    /**
     *  Message List Error Check
     * @return boolean
     */
    private boolean isExistErr() {
        boolean result = false;

        for (BigDecimal i : msgListMap.keySet()) {
            for (String msgId : msgListMap.get(i)) {
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        result = true;
                    }
                }
            }
        }

        return result;
    }

    /**
     * convNull2Val
     * @param val
     * @return Object
     */
    private Object convNull2Val(Object val) {
        if (val == null) {
            return "";
        }

        return val;
    }
    // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]
}
